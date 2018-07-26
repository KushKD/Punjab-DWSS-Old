/**
 * 
 */
package com.prwss.mis.finance.paymentvoucher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.paymentvoucher.struts.PaymentVoucherForm;
import com.prwss.mis.finance.voucher.VoucherDetailBean;
import com.prwss.mis.finance.voucher.VoucherHeaderBean;
import com.prwss.mis.finance.voucher.dao.VoucherDetailDao;
import com.prwss.mis.finance.voucher.dao.VoucherHeaderDao;
import com.prwss.mis.masters.scheme.dao.SchemeVillageDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class PaymentVoucherBOImpl implements PaymentVoucherBO {
	
	private Logger log = Logger.getLogger(PaymentVoucherBOImpl.class);
	private VoucherHeaderDao voucherHeaderDao;
	private VoucherDetailDao voucherDetailDao;
	
	public void setVoucherHeaderDao(VoucherHeaderDao voucherHeaderDao) {
		this.voucherHeaderDao = voucherHeaderDao;
	}

	public VoucherDetailDao getVoucherDetailDao() {
		return voucherDetailDao;
	}
	
	public void setVoucherDetailDao(VoucherDetailDao voucherDetailDao) {
		this.voucherDetailDao = voucherDetailDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<VoucherHeaderBean> findPaymentVoucher(
			PaymentVoucherForm paymentVoucherForm, List<String> statusList)
			throws MISException {
		
		List<VoucherHeaderBean> voucherHeaderBeans  = new  ArrayList<VoucherHeaderBean>();
		try {
			
			if(MisUtility.ifEmpty(paymentVoucherForm.getVoucherNo())){
			VoucherHeaderBean voucherHeaderBean = new VoucherHeaderBean();
			voucherHeaderBean.setVocId(paymentVoucherForm.getVoucherNo());
			voucherHeaderBean.setLocationId(paymentVoucherForm.getLocationId());
//			voucherHeaderBean.setProgramId(paymentVoucherForm.getProgramId());
			
			voucherHeaderBeans= voucherHeaderDao.findVoucher(voucherHeaderBean, statusList);
			
			
			Set<VoucherDetailBean> voucherDetailBeans = null;
			Iterator<VoucherDetailBean> iteratorPaymentVoucherDetailBean = null;
			VoucherDetailBean  paymentVoucherDetailBean = null;
			if(!MisUtility.ifEmpty(voucherHeaderBeans)){

				for (VoucherHeaderBean bean : voucherHeaderBeans) {
					voucherDetailBeans = bean.getVoucherDetailBeans();
					if(!MisUtility.ifEmpty(voucherDetailBeans)){
						iteratorPaymentVoucherDetailBean = voucherDetailBeans.iterator();					
						while(iteratorPaymentVoucherDetailBean.hasNext()){
							paymentVoucherDetailBean = iteratorPaymentVoucherDetailBean.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(paymentVoucherDetailBean.getMisAuditBean().getStatus())){
								iteratorPaymentVoucherDetailBean.remove();
								break;
							}
						}
					}
				}
			}
			}
				
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return voucherHeaderBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long savePaymentVoucher(PaymentVoucherForm paymentVoucherForm,
			MISSessionBean misSessionBean) throws MISException {
		long vocId = 0;
		try {
			System.out.println("In  save PaymentVoucher  ");
			VoucherHeaderBean voucherHeaderBean = populateVoucherHeaderBean(paymentVoucherForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			voucherHeaderBean.setMisAuditBean(misAuditBean);
			vocId = voucherHeaderDao.saveVoucher(voucherHeaderBean);
		
			if(MisUtility.ifEmpty(vocId)){
				List<VoucherDetailBean> paymentVoucherDetailBeans = populatePaymentVoucherDetailBeans(paymentVoucherForm, vocId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				System.out.println("**************************************"+paymentVoucherDetailBeans);
				if(!MisUtility.ifEmpty(paymentVoucherDetailBeans)){
					boolean paymentStatus = voucherDetailDao.saveOrUpdateVoucherDetail(paymentVoucherDetailBeans);
					if(!paymentStatus){
						log.error(paymentVoucherDetailBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save payment voucher details");
					}
				}

				
			}
			
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
			return vocId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updatePaymentVoucher(PaymentVoucherForm paymentVoucherForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			VoucherHeaderBean headerBean = new VoucherHeaderBean();
			headerBean.setVocId(paymentVoucherForm.getVoucherNo());
			VoucherHeaderBean voucherHeaderFindBean2 =  voucherHeaderDao.findVoucher(headerBean, null).get(0);
			if(voucherHeaderFindBean2.getCanModify()!= null){
				if(voucherHeaderFindBean2.getCanModify().equals(MISConstants.FIN_VOUCHER_CAN_MODIFY_NO)){
					if(!MisUtility.isSameDate(MisUtility.convertStringToDate(paymentVoucherForm.getVoucherDate()), voucherHeaderFindBean2.getVocDate())||!(voucherHeaderFindBean2.getPayeePayerId().equalsIgnoreCase(paymentVoucherForm.getPayeeName()))|| !(voucherHeaderFindBean2.getAmount().equals(paymentVoucherForm.getInstrAmount()))||!(voucherHeaderFindBean2.getPayeePayerType().equalsIgnoreCase(paymentVoucherForm.getPayeeType()))||!(voucherHeaderFindBean2.getDocumentNo()==paymentVoucherForm.getDocumentNo())){
						throw new MISException(MISExceptionCodes.MIS010,"Updation Failed, This Is An Auto Generated Voucher, Hence Following Fields Cannot Be Changed Namely: Voucher Date,Amount,Payee Type,Payee Name And Document Number. Please Check !");
					}
					voucherHeaderFindBean2.setAmount(paymentVoucherForm.getInstrAmount());
					voucherHeaderFindBean2.setBankId(paymentVoucherForm.getBankId());
					voucherHeaderFindBean2.setDocumentNo(paymentVoucherForm.getDocumentNo());
					voucherHeaderFindBean2.setInstrumentDate(MisUtility.convertStringToDate(paymentVoucherForm.getInstrumentDate()));
					voucherHeaderFindBean2.setInstrumentNumber(paymentVoucherForm.getInstrumentNo());
					voucherHeaderFindBean2.setLocationId(paymentVoucherForm.getLocationId());
					voucherHeaderFindBean2.setNotes(paymentVoucherForm.getNotes());
					voucherHeaderFindBean2.setPayeePayerId(paymentVoucherForm.getPayeeName());
					voucherHeaderFindBean2.setPayeePayerType(paymentVoucherForm.getPayeeType());
					voucherHeaderFindBean2.setPaymentMode(paymentVoucherForm.getPaymentMode());
					voucherHeaderFindBean2.setProgramId(paymentVoucherForm.getProgramId());
					voucherHeaderFindBean2.setTransactionDate(MisUtility.convertStringToDate(paymentVoucherForm.getTransactionDate()));
					voucherHeaderFindBean2.setTypeOfPayment(paymentVoucherForm.getPaymentType());
					voucherHeaderFindBean2.setInstrumentType(paymentVoucherForm.getInstrumentType());
					voucherHeaderFindBean2.setVocDate(MisUtility.convertStringToDate(paymentVoucherForm.getVoucherDate()));
					voucherHeaderFindBean2.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
					voucherHeaderFindBean2.setVocId(paymentVoucherForm.getVoucherNo());
					voucherHeaderFindBean2.setSchemeCode(paymentVoucherForm.getSchemeCode());
				}
			}else{
				voucherHeaderFindBean2.setAmount(paymentVoucherForm.getInstrAmount());
				voucherHeaderFindBean2.setBankId(paymentVoucherForm.getBankId());
				voucherHeaderFindBean2.setDocumentNo(paymentVoucherForm.getDocumentNo());
				voucherHeaderFindBean2.setInstrumentDate(MisUtility.convertStringToDate(paymentVoucherForm.getInstrumentDate()));
				voucherHeaderFindBean2.setInstrumentNumber(paymentVoucherForm.getInstrumentNo());
				voucherHeaderFindBean2.setLocationId(paymentVoucherForm.getLocationId());
				voucherHeaderFindBean2.setNotes(paymentVoucherForm.getNotes());
				voucherHeaderFindBean2.setPayeePayerId(paymentVoucherForm.getPayeeName());
				voucherHeaderFindBean2.setPayeePayerType(paymentVoucherForm.getPayeeType());
				voucherHeaderFindBean2.setPaymentMode(paymentVoucherForm.getPaymentMode());
				voucherHeaderFindBean2.setProgramId(paymentVoucherForm.getProgramId());
				voucherHeaderFindBean2.setTransactionDate(MisUtility.convertStringToDate(paymentVoucherForm.getTransactionDate()));
				voucherHeaderFindBean2.setTypeOfPayment(paymentVoucherForm.getPaymentType());
				voucherHeaderFindBean2.setInstrumentType(paymentVoucherForm.getInstrumentType());
				voucherHeaderFindBean2.setVocDate(MisUtility.convertStringToDate(paymentVoucherForm.getVoucherDate()));
				voucherHeaderFindBean2.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
				voucherHeaderFindBean2.setVocId(paymentVoucherForm.getVoucherNo());
				voucherHeaderFindBean2.setSchemeCode(paymentVoucherForm.getSchemeCode());
			}			
			boolean status = voucherHeaderDao.updateVoucher(voucherHeaderFindBean2);
			long vocId=paymentVoucherForm.getVoucherNo();
			if(status){				
					List<VoucherDetailBean> paymentVoucherDetailBeans2 = populatePaymentVoucherDetailBeans(paymentVoucherForm, vocId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
					System.out.println("paymentVoucherDetailBeans2---------------------"+paymentVoucherDetailBeans2);		
					if(!MisUtility.ifEmpty(paymentVoucherDetailBeans2)){
						boolean paymentStatus = voucherDetailDao.saveOrUpdateVoucherDetail(paymentVoucherDetailBeans2);
						if(!paymentStatus){
							log.error(paymentVoucherDetailBeans2);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Save payment voucher details");
						}
					}					
				}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return true;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deletePaymentVoucher(PaymentVoucherForm paymentVoucherForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			VoucherHeaderBean voucherHeaderBean = populateVoucherHeaderBean(paymentVoucherForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			voucherHeaderBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			status = voucherHeaderDao.updateVoucher(voucherHeaderBean);
			long vocId=paymentVoucherForm.getVoucherNo();
			
			VoucherDetailBean voucherDetailBean = new VoucherDetailBean();
			voucherDetailBean.setVocId(vocId);
			if(status){
				List<VoucherDetailBean> paymentVoucherDetailBeans = voucherDetailDao.findVoucherDetail(voucherDetailBean, statusList);
				if(!MisUtility.ifEmpty(paymentVoucherDetailBeans)){
					for (VoucherDetailBean paymentVoucherDetailBean2 : paymentVoucherDetailBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						paymentVoucherDetailBean2.setMisAuditBean(misAuditBean);
					}
					boolean paymentStatus = voucherDetailDao.saveOrUpdateVoucherDetail(paymentVoucherDetailBeans);
					if(!paymentStatus){
						log.error(paymentVoucherDetailBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to delete Payment details");
					}
				}
        
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} 
		return status;
	}

private VoucherHeaderBean populateVoucherHeaderBean(PaymentVoucherForm paymentVoucherForm){
	VoucherHeaderBean voucherHeaderBean = new VoucherHeaderBean();
	voucherHeaderBean.setAmount(paymentVoucherForm.getInstrAmount());
//	voucherHeaderBean.setBankName(paymentVoucherForm.getBankName());
	voucherHeaderBean.setBankId(paymentVoucherForm.getBankId());
	voucherHeaderBean.setDocumentNo(paymentVoucherForm.getDocumentNo());
	voucherHeaderBean.setInstrumentDate(MisUtility.convertStringToDate(paymentVoucherForm.getInstrumentDate()));
	voucherHeaderBean.setInstrumentNumber(paymentVoucherForm.getInstrumentNo());
	voucherHeaderBean.setLocationId(paymentVoucherForm.getLocationId());
	voucherHeaderBean.setNotes(paymentVoucherForm.getNotes());
	voucherHeaderBean.setPayeePayerId(paymentVoucherForm.getPayeeName());
	voucherHeaderBean.setPayeePayerType(paymentVoucherForm.getPayeeType());
	voucherHeaderBean.setPaymentMode(paymentVoucherForm.getPaymentMode());
	voucherHeaderBean.setProgramId(paymentVoucherForm.getProgramId());
	voucherHeaderBean.setTransactionDate(MisUtility.convertStringToDate(paymentVoucherForm.getTransactionDate()));
	voucherHeaderBean.setTypeOfPayment(paymentVoucherForm.getPaymentType());
	voucherHeaderBean.setInstrumentType(paymentVoucherForm.getInstrumentType());
	voucherHeaderBean.setVocDate(MisUtility.convertStringToDate(paymentVoucherForm.getVoucherDate()));
	voucherHeaderBean.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
	voucherHeaderBean.setVocId(paymentVoucherForm.getVoucherNo());
	voucherHeaderBean.setSchemeCode(paymentVoucherForm.getSchemeCode());
	return voucherHeaderBean;
	}

	@SuppressWarnings({ "unchecked" })
	private List<VoucherDetailBean> populatePaymentVoucherDetailBeans(PaymentVoucherForm paymentVoucherForm,long vocId,  MISSessionBean misSessionBean, String status){
		
		MISAuditBean misAuditBean1 = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean1.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean1.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean1.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean1.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean1.setStatus(status);
		
		MISAuditBean misAuditBean2 = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean2.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean2.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean2.setStatus(status);
		MISAuditBean misAuditBean3 = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean3.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean3.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean3.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean3.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean3.setStatus(status);
		
		List<VoucherDetailBean> paymentVoucherDetailBeans = new ArrayList<VoucherDetailBean>();
		Datagrid paymentVoucherDatagrid = paymentVoucherForm.getPaymentVoucherDatagrid();
		Collection<VoucherDetailBean> addedPaymentVoucherDetailBeans = paymentVoucherDatagrid.getAddedData();
		if(!MisUtility.ifEmpty(addedPaymentVoucherDetailBeans)){
			for (VoucherDetailBean paymentVoucherDetailBean : addedPaymentVoucherDetailBeans) {
				System.out.println("Add: "+paymentVoucherDetailBean);
				paymentVoucherDetailBean.setVocId(vocId);
				paymentVoucherDetailBean.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
				paymentVoucherDetailBean.setMisAuditBean(misAuditBean1);
				paymentVoucherDetailBeans.add(paymentVoucherDetailBean);
			}
		}
		Collection<VoucherDetailBean> modifiedPaymentVoucherDetailBeans = paymentVoucherDatagrid.getModifiedData();
		if(!MisUtility.ifEmpty(modifiedPaymentVoucherDetailBeans)){
			for (VoucherDetailBean paymentVoucherDetailBean : modifiedPaymentVoucherDetailBeans) {
				System.out.println("Modified: "+paymentVoucherDetailBean);
				paymentVoucherDetailBean.setVocId(vocId);
				paymentVoucherDetailBean.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
				paymentVoucherDetailBean.setMisAuditBean(misAuditBean2);
				paymentVoucherDetailBeans.add(paymentVoucherDetailBean);
			}
		}
		Collection<VoucherDetailBean> deletedPaymentVoucherDetailBeans = paymentVoucherDatagrid.getDeletedData();
		if(!MisUtility.ifEmpty(deletedPaymentVoucherDetailBeans)){
			for (VoucherDetailBean paymentVoucherDetailBean : deletedPaymentVoucherDetailBeans) {
				misAuditBean3.setStatus(MISConstants.MASTER_STATUS_DELETED);				
				paymentVoucherDetailBean.setVocId(vocId);
				paymentVoucherDetailBean.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
				paymentVoucherDetailBean.setMisAuditBean(misAuditBean3);
				paymentVoucherDetailBeans.add(paymentVoucherDetailBean);
			}
		}
		return paymentVoucherDetailBeans;
	}
	
	private boolean canModifyVoucher(PaymentVoucherForm paymentVoucherForm){
		VoucherHeaderBean voucherHeaderBean = new VoucherHeaderBean();
		try {
			voucherHeaderBean.setVocId(paymentVoucherForm.getVoucherNo());
			VoucherHeaderBean voucherHeaderBean2 =  voucherHeaderDao.findVoucher(voucherHeaderBean, null).get(0);
			if(voucherHeaderBean2.getCanModify()!= null){
			if(voucherHeaderBean2.getCanModify().equals(MISConstants.FIN_VOUCHER_CAN_MODIFY_NO)){
			System.out.println("Document Number From Form"+paymentVoucherForm.getDocumentNo());
				if(!MisUtility.isSameDate(MisUtility.convertStringToDate(paymentVoucherForm.getVoucherDate()), voucherHeaderBean2.getVocDate())||!(voucherHeaderBean2.getPayeePayerId().equalsIgnoreCase(paymentVoucherForm.getPayeeName()))|| !(voucherHeaderBean2.getAmount().equals(paymentVoucherForm.getInstrAmount()))||!(voucherHeaderBean2.getPayeePayerType().equalsIgnoreCase(paymentVoucherForm.getPayeeType()))||!(voucherHeaderBean2.getDocumentNo()==paymentVoucherForm.getDocumentNo())){
			return false;	
			}
			}
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return true;
	}

	@Override
	public boolean postPaymentVoucher(PaymentVoucherForm paymentVoucherForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			VoucherHeaderBean voucherHeaderBean = new VoucherHeaderBean();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			voucherHeaderBean.setVocId(paymentVoucherForm.getVoucherNo());
			VoucherHeaderBean voucherFindHeaderBean = voucherHeaderDao.findVoucher(voucherHeaderBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = voucherFindHeaderBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			voucherHeaderBean.setMisAuditBean(misAuditBean);
			status = voucherHeaderDao.updateVoucher(voucherFindHeaderBean);
			
			long vocId=paymentVoucherForm.getVoucherNo();
			VoucherDetailBean voucherDetailBean = new VoucherDetailBean();
			voucherDetailBean.setVocId(vocId);
			if(status){
				List<VoucherDetailBean> paymentVoucherDetailBeans = voucherDetailDao.findVoucherDetail(voucherDetailBean, statusList);
				if(!MisUtility.ifEmpty(paymentVoucherDetailBeans)){
					for (VoucherDetailBean paymentVoucherDetailBean2 : paymentVoucherDetailBeans) {
						misAuditBean = paymentVoucherDetailBean2.getMisAuditBean();
						misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
						misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						paymentVoucherDetailBean2.setMisAuditBean(misAuditBean);
					}
					boolean paymentStatus = voucherDetailDao.saveOrUpdateVoucherDetail(paymentVoucherDetailBeans);
					if(!paymentStatus){
						log.error(paymentVoucherDetailBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to delete Payment details");
					}
				}
        
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}
}
