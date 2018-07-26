package com.prwss.mis.finance.gpwscregister;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.gpwscregister.dao.GpwscRegisterBean;
import com.prwss.mis.finance.gpwscregister.dao.GpwscRegisterDao;
import com.prwss.mis.finance.gpwscregister.struts.GpwscRegisterForm;
import com.prwss.mis.masters.committee.dao.CommitteeBean;

public class GpwscRegisterBOImpl implements GpwscRegisterBO {
private GpwscRegisterDao gpwscRegisterDao;
private Logger log = Logger.getLogger(GpwscRegisterBOImpl.class);


	public void setGpwscRegisterDao(GpwscRegisterDao gpwscRegisterDao) {
	this.gpwscRegisterDao = gpwscRegisterDao;
}

	@Override
	public List<GpwscRegisterBean> findGpwsc(
			GpwscRegisterForm gpwscRegisterForm, List<String> statusList)
			throws MISException {
		List<GpwscRegisterBean> gpwscRegisterBeans = null;
		try {
			GpwscRegisterBean gpwscRegisterBean = new GpwscRegisterBean();
			if(MisUtility.ifEmpty(gpwscRegisterForm.getGpwcId())){
			CommitteeBean committeeBean = new CommitteeBean();
			committeeBean.setCommitteeId(gpwscRegisterForm.getGpwcId());
			gpwscRegisterBean.setCommitteeBean(committeeBean);
			gpwscRegisterBean.setTransactionType(gpwscRegisterForm.getTransactionType());
			System.out.println("-------Inside BO--------");
			gpwscRegisterBeans = gpwscRegisterDao.findGPWSCRegister(gpwscRegisterBean, statusList);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return gpwscRegisterBeans;
	}

	@Override
	public boolean updateGpwsc(GpwscRegisterForm gpwscRegisterForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			GpwscRegisterBean gpwscRegisterBean = populateGpwscRegisterBean(gpwscRegisterForm);	
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			gpwscRegisterBean.setMisAuditBean(misAuditBean);
//			villageBean.setMisAuditBean(villageForm.getMisAuditBean());
			status = gpwscRegisterDao.updateGPWSCRegister(gpwscRegisterBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean deleteGpwsc(GpwscRegisterForm gpwscRegisterForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			GpwscRegisterBean gpwscRegisterBean = populateGpwscRegisterBean(gpwscRegisterForm);	
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			gpwscRegisterBean.setMisAuditBean(misAuditBean);
//			villageBean.setMisAuditBean(villageForm.getMisAuditBean());
			status = gpwscRegisterDao.updateGPWSCRegister(gpwscRegisterBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean postGpwsc(GpwscRegisterForm gpwscRegisterForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			GpwscRegisterBean 	gpwscRegisterBean = populateGpwscRegisterBean(gpwscRegisterForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			GpwscRegisterBean gpwscRegisterBean2 = gpwscRegisterDao.findGPWSCRegister(gpwscRegisterBean, statusList).get(0);
//		for (VillageBean villageBean2 : villageBeans) {
			misAuditBean = gpwscRegisterBean2.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			gpwscRegisterBean2.setMisAuditBean(misAuditBean);
//		}
			status = gpwscRegisterDao.updateGPWSCRegister(gpwscRegisterBean2);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			log.error(e.getLocalizedMessage(),e);
			throw(e);
		}
		
//			if(!status2){
//				throw new MISException(MISExceptionCodes.MIS003, "Location not saved");
//			}
		return status;
		}
	
	private GpwscRegisterBean populateGpwscRegisterBean(GpwscRegisterForm gpwscRegisterForm){
		GpwscRegisterBean gpwscRegisterBean = new GpwscRegisterBean();
		gpwscRegisterBean.setBankId(gpwscRegisterForm.getBankId());
		CommitteeBean committeeBean = new CommitteeBean();
//		System.out.println("I am In Populate"+gpwscRegisterForm.getGpwcId());
		committeeBean.setCommitteeId(gpwscRegisterForm.getGpwcId());
		gpwscRegisterBean.setCommitteeBean(committeeBean);
		gpwscRegisterBean.setBillNo(gpwscRegisterForm.getBillNo());
		gpwscRegisterBean.setDateOfTransaction(MisUtility.convertStringToDate(gpwscRegisterForm.getDateOfTransaction()));
		gpwscRegisterBean.setLocationId(gpwscRegisterForm.getLocationId());
		gpwscRegisterBean.setPayeeName(gpwscRegisterForm.getPayeeName());
		gpwscRegisterBean.setReceiptActivity(gpwscRegisterForm.getReceiptActivity());
		gpwscRegisterBean.setPaymentAmount(gpwscRegisterForm.getPaymentAmount());
		gpwscRegisterBean.setPaymentType(gpwscRegisterForm.getPaymentType());
		gpwscRegisterBean.setPaymentActivity(gpwscRegisterForm.getPaymentActivity());
		gpwscRegisterBean.setReceiptAmount(gpwscRegisterForm.getReceiptAmount());
		gpwscRegisterBean.setReceiptType(gpwscRegisterForm.getReceiptType());
		gpwscRegisterBean.setSchemeId(gpwscRegisterForm.getSchemeId());
		gpwscRegisterBean.setTransactionNumber(gpwscRegisterForm.getTransactionNumber());
		gpwscRegisterBean.setTransactionType(gpwscRegisterForm.getTransactionType());
//		System.out.println("GPWSC REgister"+gpwscRegisterBean);
		return  gpwscRegisterBean;
	}

	@Override
	public long saveGpwsc(GpwscRegisterForm gpwscRegisterForm,
			MISSessionBean misSessionBean) throws MISException {
		long transactionNumber = 0;
		try {
			GpwscRegisterBean gpwscRegisterBean = populateGpwscRegisterBean(gpwscRegisterForm);	
			//System.out.println("Kaha HU");
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			gpwscRegisterBean.setMisAuditBean(misAuditBean);
//			villageBean.setMisAuditBean(villageForm.getMisAuditBean());
			transactionNumber = gpwscRegisterDao.saveGPWSCRegister(gpwscRegisterBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return transactionNumber;
	}

}
