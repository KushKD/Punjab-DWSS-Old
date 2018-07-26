package com.prwss.mis.ccdu.plan;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.ccdu.plan.dao.CCDUPlanMasterBean;
import com.prwss.mis.ccdu.plan.dao.CCDUPlanMasterDao;
import com.prwss.mis.ccdu.plan.struts.CreateCCDUPlanForm;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class CCDUPlanBOImpl extends CCDUPlanDetailBOImpl implements CCDUPlanBO {
	
	private Logger log = Logger.getLogger(CCDUPlanBOImpl.class);
	
	private CCDUPlanMasterDao ccduPlanMasterDao;
	
	public void setCcduPlanMasterDao(CCDUPlanMasterDao ccduPlanMasterDao) {
		this.ccduPlanMasterDao = ccduPlanMasterDao;
	}

	@Override
	public List<CCDUPlanMasterBean> findCCDUPlan(CreateCCDUPlanForm createCCDUPlanForm, List<String> statusList)
			throws MISException {
		List<CCDUPlanMasterBean> ccduPlanMasterBeans = null;
		try {
			CCDUPlanMasterBean ccduPlanMasterBean = new CCDUPlanMasterBean();
			ccduPlanMasterBean.setPlanId(createCCDUPlanForm.getPlanId());
			ccduPlanMasterBean.setFromDate(MisUtility.convertStringToDate(createCCDUPlanForm.getPlanStartDate()));
			ccduPlanMasterBean.setToDate(MisUtility.convertStringToDate(createCCDUPlanForm.getPlanEndDate()));
			ccduPlanMasterBeans = ccduPlanMasterDao.findCCDUPlanMaster(ccduPlanMasterBean, statusList);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}	
		
		return ccduPlanMasterBeans;
	}
	
	@Override
	public long createCCDUPlan(CreateCCDUPlanForm createCCDUPlanForm, MISSessionBean misSessionBean) throws MISException {
		 long plainId = 0;
		 try {
			CCDUPlanMasterBean ccduPlanMasterBean = new CCDUPlanMasterBean();
			 ccduPlanMasterBean.setPlanId(createCCDUPlanForm.getPlanId());
			 ccduPlanMasterBean.setFromDate(MisUtility.convertStringToDate(createCCDUPlanForm.getPlanStartDate()));
			 ccduPlanMasterBean.setToDate(MisUtility.convertStringToDate(createCCDUPlanForm.getPlanEndDate()));
			 
			 MISAuditBean misAuditBean = new MISAuditBean();
			 misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			 misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			 misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			 
			 ccduPlanMasterBean.setMisAuditBean(misAuditBean);
			 plainId = ccduPlanMasterDao.saveCCDUPlanMaster(ccduPlanMasterBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		 
		return plainId;
	}

}
