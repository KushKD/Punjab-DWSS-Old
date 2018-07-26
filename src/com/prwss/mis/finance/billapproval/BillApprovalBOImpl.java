package com.prwss.mis.finance.billapproval;

import java.util.ArrayList;
import java.util.List;

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
import com.prwss.mis.finance.billapproval.dao.BillApprovalBean;
import com.prwss.mis.finance.billapproval.dao.BillApprovalDao;
import com.prwss.mis.finance.billapproval.struts.BillApprovalForm;
import com.prwss.mis.finance.voucher.VoucherHeaderBean;
import com.prwss.mis.finance.voucher.dao.VoucherHeaderDao;
import com.prwss.mis.tender.contract.dao.ContractDao;
import com.prwss.mis.tender.contract.dao.ContractDetailBean;
import com.prwss.mis.tender.contract.dao.ContractHeaderBean;

public class BillApprovalBOImpl implements BillApprovalBO {
private BillApprovalDao billApprovalDao;
private VoucherHeaderDao voucherHeaderDao;
private ContractDao contractDao;
private Logger log = Logger.getLogger(BillApprovalBOImpl.class);

	public void setContractDao(ContractDao contractDao) {
	this.contractDao = contractDao;
}
	public void setVoucherHeaderDao(VoucherHeaderDao voucherHeaderDao) {
	this.voucherHeaderDao = voucherHeaderDao;
}
	public void setBillApprovalDao(BillApprovalDao billApprovalDao) {
	this.billApprovalDao = billApprovalDao;
}
	@Override
	public List<BillApprovalBean> findBillApproval(
			BillApprovalForm billApprovalForm, List<String> statusList)
			throws MISException {
		List<BillApprovalBean> billApprovalBeans = null;
		try {
			ContractHeaderBean contractHeaderBean = new ContractHeaderBean();
			BillApprovalBean billApprovalBean = new BillApprovalBean();
			billApprovalBean.setLocationId(billApprovalForm.getLocationId());
			contractHeaderBean.setContractId(billApprovalForm.getContractId());
			billApprovalBean.setContractHeaderBean(contractHeaderBean);
			billApprovalBeans = billApprovalDao.findBillApproval(billApprovalBean, statusList);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return billApprovalBeans;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveBillApproval(BillApprovalForm billApprovalForm,
			MISSessionBean misSessionBean) throws MISException {
		long vocId = 0;
		boolean status = false;
		
		try {
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			ContractHeaderBean contractHeaderBean = new ContractHeaderBean();
			contractHeaderBean.setContractId(billApprovalForm.getContractId());
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			VoucherHeaderBean voucherHeaderBean = new VoucherHeaderBean();
			ContractHeaderBean contractHeaderBean2 = contractDao.findContracts(contractHeaderBean, statusList).get(0);
			if(MisUtility.ifEmpty(contractHeaderBean2)){
				voucherHeaderBean.setPayeePayerId(contractHeaderBean2.getVendorBean().getVendorId());
			}else{
				throw new MISException();
			}
			voucherHeaderBean.setAmount(billApprovalForm.getReleasedAmount());
			voucherHeaderBean.setLocationId(billApprovalForm.getLocationId());
			voucherHeaderBean.setPayeePayerType(MISConstants.FIN_PAYMENT_VENDOR);
			voucherHeaderBean.setNotes("Contract Payment Voucher");
			voucherHeaderBean.setVocDate(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")));
			voucherHeaderBean.setPaymentMode("BANK");
			voucherHeaderBean.setTypeOfPayment("OTHER");
			voucherHeaderBean.setVocType(MISConstants.FIN_VOC_TYPE_PAYMENT);
			voucherHeaderBean.setMisAuditBean(misAuditBean);
			voucherHeaderBean.setProgramId(MISConstants.FIN_NOT_APPLICABLE);
			voucherHeaderBean.setCanModify(MISConstants.FIN_VOUCHER_CAN_MODIFY_NO);
			vocId = voucherHeaderDao.saveVoucher(voucherHeaderBean);
			if(MisUtility.ifEmpty(vocId)){
				BillApprovalBean billApprovalBean = populateBillApprovalBean(billApprovalForm, vocId);
				billApprovalBean.setMisAuditBean(misAuditBean);
				status = billApprovalDao.saveBillApproval(billApprovalBean);
				if(!status){
					throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Bill Approval Bean");
				}
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new MISException(e);
		}
		
		return vocId;
	}
	
	private BillApprovalBean populateBillApprovalBean(BillApprovalForm billApprovalForm,long vocId){
		BillApprovalBean billApprovalBean = new BillApprovalBean(); 
		billApprovalBean.setLocationId(billApprovalForm.getLocationId());
		ContractHeaderBean contractHeaderBean = new ContractHeaderBean();
		contractHeaderBean.setContractId(billApprovalForm.getContractId());
		billApprovalBean.setContractHeaderBean(contractHeaderBean);
		billApprovalBean.setBillAmount(billApprovalForm.getBillAmount());
		billApprovalBean.setReleasedAmount(billApprovalForm.getReleasedAmount());
		billApprovalBean.setVocId(vocId);
		ContractDetailBean contractDetailBean = new ContractDetailBean();
		contractDetailBean.setMilestoneId(billApprovalForm.getMilestonId());
		billApprovalBean.setContractDetailBean(contractDetailBean);
		billApprovalBean.setDocumentNo(billApprovalForm.getDocumentNo());
		billApprovalBean.setDocumentRefNo(billApprovalForm.getDocumentReferenceNo());
		return billApprovalBean;
	}

}

