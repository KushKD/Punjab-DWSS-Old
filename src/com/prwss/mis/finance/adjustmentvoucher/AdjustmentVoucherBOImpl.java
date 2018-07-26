/**
 * 
 */
package com.prwss.mis.finance.adjustmentvoucher;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.adjustmentvoucher.dao.AdjustmentVoucherDao;
import com.prwss.mis.finance.adjustmentvoucher.struts.AdjustmentVoucherForm;
import com.prwss.mis.finance.paymentvoucher.PaymentVoucherBO;
import com.prwss.mis.finance.receiptvoucher.ReceiptVoucherBO;
import com.prwss.mis.finance.voucher.VoucherDetailBean;
import com.prwss.mis.finance.voucher.VoucherHeaderBean;
import com.prwss.mis.finance.voucher.dao.VoucherDetailDao;
import com.prwss.mis.finance.voucher.dao.VoucherHeaderDao;

/**
 * @author PJHA
 *
 */
public class AdjustmentVoucherBOImpl implements AdjustmentVoucherBO {
	
	private Logger log = Logger.getLogger(AdjustmentVoucherBOImpl.class);
	private PaymentVoucherBO paymentVoucherBO;
	private ReceiptVoucherBO receiptVoucherBO;
	private AdjustmentVoucherDao adjustmentVoucherDao;
	private VoucherDetailDao voucherDetailDao;
	private VoucherHeaderDao voucherHeaderDao;
	

	public void setVoucherHeaderDao(VoucherHeaderDao voucherHeaderDao) {
		this.voucherHeaderDao = voucherHeaderDao;
	}


	public void setPaymentVoucherBO(PaymentVoucherBO paymentVoucherBO) {
		this.paymentVoucherBO = paymentVoucherBO;
	}

	
	public void setVoucherDetailDao(VoucherDetailDao voucherDetailDao) {
		this.voucherDetailDao = voucherDetailDao;
	}


	public void setReceiptVoucherBO(ReceiptVoucherBO receiptVoucherBO) {
		this.receiptVoucherBO = receiptVoucherBO;
	}

	public void setAdjustmentVoucherDao(AdjustmentVoucherDao adjustmentVoucherDao) {
		this.adjustmentVoucherDao = adjustmentVoucherDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<AdjustmentVoucherBean> findAdjustmentVoucher(
			AdjustmentVoucherForm adjustmentVoucherForm, List<String> statusList)
			throws MISException {
		List<AdjustmentVoucherBean> adjustmentVoucherBeans  = new ArrayList<AdjustmentVoucherBean>();
		
		try {
			AdjustmentVoucherBean adjustmentVoucherBean = new AdjustmentVoucherBean();
			adjustmentVoucherBean.setProgramId(adjustmentVoucherForm.getProgramId());
			adjustmentVoucherBean.setVocId(adjustmentVoucherForm.getVoucherNo());
			adjustmentVoucherBean.setLocationId(adjustmentVoucherForm.getLocationId());
			adjustmentVoucherBeans = adjustmentVoucherDao.findAdjustmentVoucher(adjustmentVoucherBean, statusList);
		
	} catch (DataAccessException e) {
		throw e;
	}
	return adjustmentVoucherBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveAdjustmentVoucher(
			AdjustmentVoucherForm adjustmentVoucherForm,
			MISSessionBean misSessionBean) throws MISException {
		
		long vocId=0;
		long receiptVocId=0;
		long paymentVocId=0;
		
		try {
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			
		    VoucherHeaderBean voucherHeaderBean= new VoucherHeaderBean();
			
		    voucherHeaderBean.setLocationId(adjustmentVoucherForm.getLocationId());
		    voucherHeaderBean.setProgramId(adjustmentVoucherForm.getProgramId());
		    voucherHeaderBean.setPaymentMode(MISConstants.FIN_ADJUSTMENT_MODE);
		    voucherHeaderBean.setVocDate(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")));
		    voucherHeaderBean.setPayeePayerType(MISConstants.FIN_ADJUSTMENT_PAYEE_PAYER_TYPE);
		    voucherHeaderBean.setPayeePayerId(MISConstants.FIN_ADJUSTMENT_PAYEE_PAYER_ID);
		    voucherHeaderBean.setTransactionDate(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")));
		    voucherHeaderBean.setTypeOfPayment(MISConstants.FIN_ADJUSTMENT_PAYMENT_RECEIPT_TYPE);
		    voucherHeaderBean.setAmount(adjustmentVoucherForm.getDebitAmountId());
		    voucherHeaderBean.setNotes(adjustmentVoucherForm.getDebitRemarks());
		    voucherHeaderBean.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
		    voucherHeaderBean.setMisAuditBean(misAuditBean);
		  
		    paymentVocId = voucherHeaderDao.saveVoucher(voucherHeaderBean);
		    
			VoucherDetailBean voucherDetailBean = new VoucherDetailBean();
			voucherDetailBean.setActivityId(adjustmentVoucherForm.getDebitActivityId());
			voucherDetailBean.setCodeHeadId(adjustmentVoucherForm.getDebitCodeHeadId());
			voucherDetailBean.setComponentId(adjustmentVoucherForm.getDebitComponentId());
			voucherDetailBean.setSubComponentId(adjustmentVoucherForm.getDebitSubComponentId());
			voucherDetailBean.setAmount(adjustmentVoucherForm.getDebitAmountId());
			voucherDetailBean.setRemarks(adjustmentVoucherForm.getDebitRemarks());
			voucherDetailBean.setVocId(paymentVocId);
			voucherDetailBean.setMisAuditBean(misAuditBean);
			voucherDetailBean.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
			Set<VoucherDetailBean> paymentVoucherDetailBeans= new TreeSet<VoucherDetailBean>();
			paymentVoucherDetailBeans.add(voucherDetailBean);
			voucherDetailDao.saveOrUpdateVoucherDetail(paymentVoucherDetailBeans);

			VoucherHeaderBean voucherHeaderBean2 = new VoucherHeaderBean();
			voucherHeaderBean2.setLocationId(adjustmentVoucherForm.getLocationId());
			voucherHeaderBean2.setProgramId(adjustmentVoucherForm.getProgramId());
			voucherHeaderBean2.setPaymentMode(MISConstants.FIN_ADJUSTMENT_MODE);
			voucherHeaderBean2.setVocDate(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")));
			voucherHeaderBean2.setPayeePayerType(MISConstants.FIN_ADJUSTMENT_PAYEE_PAYER_TYPE);
			voucherHeaderBean2.setPayeePayerId(MISConstants.FIN_ADJUSTMENT_PAYEE_PAYER_ID);
			voucherHeaderBean2.setTransactionDate(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")));
			voucherHeaderBean2.setTypeOfReceipt(MISConstants.FIN_ADJUSTMENT_PAYMENT_RECEIPT_TYPE);
			voucherHeaderBean2.setAmount(adjustmentVoucherForm.getCreditAmountId());
			voucherHeaderBean2.setNotes(adjustmentVoucherForm.getCreditRemarks());
			voucherHeaderBean2.setVocType(MISConstants.FIN_VOC_TYPE_RECEIPT);
			voucherHeaderBean2.setMisAuditBean(misAuditBean);
			receiptVocId = voucherHeaderDao.saveVoucher(voucherHeaderBean2);
			VoucherDetailBean voucherDetailBean2 = new VoucherDetailBean();
			voucherDetailBean2.setActivityId(adjustmentVoucherForm.getCreditActivityId());
			voucherDetailBean2.setCodeHeadId(adjustmentVoucherForm.getCreditCodeHeadId());
			voucherDetailBean2.setComponentId(adjustmentVoucherForm.getCreditComponentId());
			voucherDetailBean2.setSubComponentId(adjustmentVoucherForm.getCreditSubComponentId());
			voucherDetailBean2.setAmount(adjustmentVoucherForm.getCreditAmountId());
			voucherDetailBean2.setRemarks(adjustmentVoucherForm.getCreditRemarks());
			voucherDetailBean2.setVocId(receiptVocId);
			voucherDetailBean2.setVocType(MISConstants.FIN_VOC_TYPE_RECEIPT);
			voucherDetailBean2.setMisAuditBean(misAuditBean);
			Set<VoucherDetailBean> receiptVoucherDetailBeans= new TreeSet<VoucherDetailBean>();
			receiptVoucherDetailBeans.add(voucherDetailBean2);
			voucherDetailDao.saveOrUpdateVoucherDetail(receiptVoucherDetailBeans);
			AdjustmentVoucherBean adjustmentVoucherBean = populateAdjustmentVoucherBean(adjustmentVoucherForm, paymentVocId, receiptVocId);
			adjustmentVoucherBean.setMisAuditBean(misAuditBean);
			vocId = adjustmentVoucherDao.saveAdjustmentVoucher(adjustmentVoucherBean);
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
	public boolean updateAdjustmentVoucher(
			AdjustmentVoucherForm adjustmentVoucherForm,
			MISSessionBean misSessionBean) throws MISException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAdjustmentVoucher(
			AdjustmentVoucherForm adjustmentVoucherForm,
			MISSessionBean misSessionBean) throws MISException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postAdjustmentVoucher(
			AdjustmentVoucherForm adjustmentVoucherForm,
			MISSessionBean misSessionBean) throws MISException {
		// TODO Auto-generated method stub
		return false;
	}

	private AdjustmentVoucherBean populateAdjustmentVoucherBean(AdjustmentVoucherForm adjustmentVoucherForm,long paymentVocId,long receiptVocId ){
		AdjustmentVoucherBean adjustmentVoucherBean = new AdjustmentVoucherBean();
		adjustmentVoucherBean.setLocationId(adjustmentVoucherForm.getLocationId());
		VoucherHeaderBean voucherHeaderBean = new VoucherHeaderBean();
		voucherHeaderBean.setVocId(paymentVocId);
		adjustmentVoucherBean.setPaymentVocId(voucherHeaderBean);
		adjustmentVoucherBean.setProgramId(adjustmentVoucherForm.getProgramId());
		VoucherHeaderBean voucherHeaderBean2 = new VoucherHeaderBean();
		voucherHeaderBean2.setVocId(receiptVocId);
		adjustmentVoucherBean.setReceiptVocId(voucherHeaderBean2);
		adjustmentVoucherBean.setRemarks(adjustmentVoucherForm.getNotes());
		adjustmentVoucherBean.setVocDate(MisUtility.convertStringToDate(adjustmentVoucherForm.getVoucherDate()));
		adjustmentVoucherBean.setVocId(adjustmentVoucherForm.getVoucherNo());
		return adjustmentVoucherBean;
		}
	
		
}
