/**
 * 
 */
package com.prwss.mis.finance.receiptvoucher;

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
import com.prwss.mis.finance.receiptvoucher.struts.ReceiptVoucherForm;
import com.prwss.mis.finance.voucher.VoucherDetailBean;
import com.prwss.mis.finance.voucher.VoucherHeaderBean;
import com.prwss.mis.finance.voucher.dao.VoucherDetailDao;
import com.prwss.mis.finance.voucher.dao.VoucherHeaderDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class ReceiptVoucherBOImpl implements ReceiptVoucherBO{
	
	private Logger log = Logger.getLogger(ReceiptVoucherBOImpl.class);
	private VoucherHeaderDao voucherHeaderDao ;
	private VoucherDetailDao voucherDetailDao;

	
	
	
	public void setVoucherHeaderDao(VoucherHeaderDao voucherHeaderDao) {
		this.voucherHeaderDao = voucherHeaderDao;
	}

	public void setVoucherDetailDao(VoucherDetailDao voucherDetailDao) {
		this.voucherDetailDao = voucherDetailDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<VoucherHeaderBean> findReceiptVoucher(
			ReceiptVoucherForm receiptVoucherForm, List<String> statusList)
			throws MISException {
		List<VoucherHeaderBean> voucherHeaderBeans = new  ArrayList<VoucherHeaderBean>();
		try {
			
			VoucherHeaderBean voucherHeaderBean = new VoucherHeaderBean();
			
			voucherHeaderBean.setVocId(receiptVoucherForm.getVoucherNo());
			voucherHeaderBean.setLocationId(receiptVoucherForm.getLocationId());
			voucherHeaderBeans= voucherHeaderDao.findVoucher(voucherHeaderBean, statusList);
			Set<VoucherDetailBean> voucherDetailBeans = null;
			Iterator<VoucherDetailBean> iteratorVoucherDetailBean = null;
			VoucherDetailBean  voucherDetailBean = null;
			
			if(!MisUtility.ifEmpty(voucherHeaderBeans)){

				for (VoucherHeaderBean bean : voucherHeaderBeans) {
					voucherDetailBeans = bean.getVoucherDetailBeans();
					if(!MisUtility.ifEmpty(voucherDetailBeans)){
						iteratorVoucherDetailBean = voucherDetailBeans.iterator();					
						while(iteratorVoucherDetailBean.hasNext()){
							voucherDetailBean = iteratorVoucherDetailBean.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(voucherDetailBean.getMisAuditBean().getStatus())){
								iteratorVoucherDetailBean.remove();
								break;
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
	public long saveReceiptVoucher(ReceiptVoucherForm receiptVoucherForm,
			MISSessionBean misSessionBean) throws MISException {
		long vocId = 0;
		try {
			System.out.println("In  save ReceiptVoucher  ");
			VoucherHeaderBean voucherHeaderBean = populateReceiptVoucher(receiptVoucherForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			voucherHeaderBean.setMisAuditBean(misAuditBean);

			 vocId = voucherHeaderDao.saveVoucher(voucherHeaderBean);
			
		
			if(MisUtility.ifEmpty(vocId)){
				List<VoucherDetailBean> voucherDetailBeans = populateReceiptVoucherDetailBeans(receiptVoucherForm, vocId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
			
				if(!MisUtility.ifEmpty(voucherDetailBeans)){
					
					boolean receiptStatus = voucherDetailDao.saveOrUpdateVoucherDetail(voucherDetailBeans);
					if(!receiptStatus){
						log.error(voucherDetailBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save receipt voucher details");
					}
				}

				
			}
			
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return vocId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateReceiptVoucher(ReceiptVoucherForm receiptVoucherForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In  Update ReceiptVoucher  ");
			//VoucherHeaderBean voucherHeaderBean = populateReceiptVoucher(receiptVoucherForm);
			VoucherHeaderBean headerBean = new VoucherHeaderBean();
			headerBean.setVocId(receiptVoucherForm.getVoucherNo());
			VoucherHeaderBean voucherHeaderFindBean2 =  voucherHeaderDao.findVoucher(headerBean, null).get(0);
			voucherHeaderFindBean2.setAmount(receiptVoucherForm.getInstrAmount());
//				receiptVoucherBean.setBankName(receiptVoucherForm.getBankName());
			voucherHeaderFindBean2.setBankId(receiptVoucherForm.getBankId());
			voucherHeaderFindBean2.setDocumentNo(receiptVoucherForm.getDocumentNo());
			voucherHeaderFindBean2.setInstrumentDate(MisUtility.convertStringToDate(receiptVoucherForm.getInstrumentDate()));
			voucherHeaderFindBean2.setInstrumentNumber(receiptVoucherForm.getInstrumentNo());
			voucherHeaderFindBean2.setInstrumentType(receiptVoucherForm.getInstrumentType());
			voucherHeaderFindBean2.setLocationId(receiptVoucherForm.getLocationId());
			voucherHeaderFindBean2.setNotes(receiptVoucherForm.getNotes());
			voucherHeaderFindBean2.setPayeePayerId(receiptVoucherForm.getPayerName());
			voucherHeaderFindBean2.setProgramId(receiptVoucherForm.getProgramId());
			voucherHeaderFindBean2.setPaymentMode(receiptVoucherForm.getReceiptType());
			voucherHeaderFindBean2.setTransactionDate(MisUtility.convertStringToDate(receiptVoucherForm.getTransactionDate()));
			voucherHeaderFindBean2.setVocDate(MisUtility.convertStringToDate(receiptVoucherForm.getVoucherDate()));
			voucherHeaderFindBean2.setPayeePayerType(receiptVoucherForm.getPayerType());
			voucherHeaderFindBean2.setVocType(MISConstants.FIN_VOC_TYPE_RECEIPT);
			voucherHeaderFindBean2.setTypeOfReceipt(receiptVoucherForm.getReceiptMode());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			voucherHeaderFindBean2.setMisAuditBean(misAuditBean);

			
			boolean status = voucherHeaderDao.updateVoucher(voucherHeaderFindBean2);
			long vocId=receiptVoucherForm.getVoucherNo();
			if(status){
				
				List<VoucherDetailBean> voucherDetailBeans = populateReceiptVoucherDetailBeans(receiptVoucherForm, vocId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(voucherDetailBeans)){
					boolean receiptStatus = voucherDetailDao.saveOrUpdateVoucherDetail(voucherDetailBeans);
					if(!receiptStatus){
						log.error(voucherDetailBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Update receipt voucher details");
					}
				}
					
				}
				
			

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return true;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteReceiptVoucher(ReceiptVoucherForm receiptVoucherForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			VoucherHeaderBean voucherHeaderBean = populateReceiptVoucher(receiptVoucherForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			voucherHeaderBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			voucherHeaderBean.setVocId(receiptVoucherForm.getVoucherNo());
			boolean status = voucherHeaderDao.updateVoucher(voucherHeaderBean);
			long vocId=receiptVoucherForm.getVoucherNo();
			
			VoucherDetailBean voucherDetailBean = new VoucherDetailBean();
			voucherDetailBean.setVocId(vocId);
			if(status){
				List<VoucherDetailBean> voucherDetailBeans = voucherDetailDao.findVoucherDetail(voucherDetailBean, statusList);
				if(!MisUtility.ifEmpty(voucherDetailBeans)){
					for (VoucherDetailBean voucherDetailBean2 : voucherDetailBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						voucherDetailBean2.setMisAuditBean(misAuditBean);
					}
					boolean receiptStatus =voucherDetailDao.saveOrUpdateVoucherDetail(voucherDetailBeans);
					if(!receiptStatus){
						log.error(voucherDetailBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to delete Receipt details");
					}
				}
        
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return true;
	}
	
	private VoucherHeaderBean populateReceiptVoucher(ReceiptVoucherForm receiptVoucherForm){
		
		VoucherHeaderBean receiptVoucherBean = new VoucherHeaderBean();
		receiptVoucherBean.setAmount(receiptVoucherForm.getInstrAmount());
//		receiptVoucherBean.setBankName(receiptVoucherForm.getBankName());
		receiptVoucherBean.setBankId(receiptVoucherForm.getBankId());
		receiptVoucherBean.setDocumentNo(receiptVoucherForm.getDocumentNo());
		receiptVoucherBean.setInstrumentDate(MisUtility.convertStringToDate(receiptVoucherForm.getInstrumentDate()));
		receiptVoucherBean.setInstrumentNumber(receiptVoucherForm.getInstrumentNo());
		receiptVoucherBean.setInstrumentType(receiptVoucherForm.getInstrumentType());
		receiptVoucherBean.setLocationId(receiptVoucherForm.getLocationId());
		receiptVoucherBean.setNotes(receiptVoucherForm.getNotes());
		receiptVoucherBean.setPayeePayerId(receiptVoucherForm.getPayerName());
		receiptVoucherBean.setProgramId(receiptVoucherForm.getProgramId());
		receiptVoucherBean.setPaymentMode(receiptVoucherForm.getReceiptType());
		receiptVoucherBean.setTransactionDate(MisUtility.convertStringToDate(receiptVoucherForm.getTransactionDate()));
		receiptVoucherBean.setVocDate(MisUtility.convertStringToDate(receiptVoucherForm.getVoucherDate()));
		receiptVoucherBean.setPayeePayerType(receiptVoucherForm.getPayerType());
		receiptVoucherBean.setVocType(MISConstants.FIN_VOC_TYPE_RECEIPT);
		receiptVoucherBean.setTypeOfReceipt(receiptVoucherForm.getReceiptMode());
		
		
			
			return receiptVoucherBean;
	}

	@SuppressWarnings({ "unchecked" })
	private List<VoucherDetailBean> populateReceiptVoucherDetailBeans(ReceiptVoucherForm receiptVoucherForm,long vocId,  MISSessionBean misSessionBean, String status){
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);

		List<VoucherDetailBean> voucherDetailBeans = new ArrayList<VoucherDetailBean>();


		Datagrid receiptVoucherDatagrid = receiptVoucherForm.getReceiptVoucherDatagrid();

		Collection<VoucherDetailBean> addedReceiptVoucherDetailBeans = receiptVoucherDatagrid.getAddedData();
		if(!MisUtility.ifEmpty(addedReceiptVoucherDetailBeans)){
			for (VoucherDetailBean voucherDetailBean : addedReceiptVoucherDetailBeans) {
				voucherDetailBean.setVocId(vocId);
				voucherDetailBean.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
				voucherDetailBean.setMisAuditBean(misAuditBean);
				voucherDetailBeans.add(voucherDetailBean);
			}
		}

		Collection<VoucherDetailBean> modifiedReceiptVoucherDetailBeans = receiptVoucherDatagrid.getModifiedData();
		if(!MisUtility.ifEmpty(modifiedReceiptVoucherDetailBeans)){
			for (VoucherDetailBean voucherDetailBean : modifiedReceiptVoucherDetailBeans) {
				voucherDetailBean.setVocId(vocId);
				voucherDetailBean.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
				voucherDetailBean.setMisAuditBean(misAuditBean);
				voucherDetailBeans.add(voucherDetailBean);
			}
				
			}
	
		
		Collection<VoucherDetailBean> deletedReceiptVoucherDetailBeans = receiptVoucherDatagrid.getDeletedData();
		if(!MisUtility.ifEmpty(deletedReceiptVoucherDetailBeans)){
			for (VoucherDetailBean voucherDetailBean : deletedReceiptVoucherDetailBeans) {
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				voucherDetailBean.setVocId(vocId);
				voucherDetailBean.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
				voucherDetailBean.setMisAuditBean(misAuditBean);
				voucherDetailBeans.add(voucherDetailBean);
			}
				
			}
		
		return voucherDetailBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postReceiptVoucher(ReceiptVoucherForm receiptVoucherForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			VoucherHeaderBean voucherHeaderBean = new VoucherHeaderBean();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			voucherHeaderBean.setVocId(receiptVoucherForm.getVoucherNo());
			VoucherHeaderBean voucherFindHeaderBean = voucherHeaderDao.findVoucher(voucherHeaderBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = voucherFindHeaderBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			voucherHeaderBean.setMisAuditBean(misAuditBean);
			status = voucherHeaderDao.updateVoucher(voucherFindHeaderBean);
			
			long vocId=receiptVoucherForm.getVoucherNo();
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
