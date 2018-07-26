/**
 * 
 */
package com.prwss.mis.pmm.sustassessment;

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
import com.prwss.mis.pmm.sustassessment.dao.SustAssessmentDao;
import com.prwss.mis.pmm.sustassessment.struts.SustAssessmentForm;

/**
 * @author pjha
 *
 */
public class SustAssessmentBOImpl implements SustAssessmentBO {
	private Logger log = Logger.getLogger(SustAssessmentBOImpl.class);
	private SustAssessmentDao sustAssessmentDao;
	
	public void setSustAssessmentDao(SustAssessmentDao sustAssessmentDao) {
		this.sustAssessmentDao = sustAssessmentDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<SustAssessmentBean> findSustAssessment(
			SustAssessmentForm sustAssessmentForm, List<String> statusList)
			throws MISException {
		List<SustAssessmentBean> sustAssessmentBeans  = new ArrayList<SustAssessmentBean>();
		
		try {
			SustAssessmentBean sustAssessmentBean = new SustAssessmentBean();
			sustAssessmentBean.setLocationId(sustAssessmentForm.getLocationId());
			sustAssessmentBean.setSchemeId(sustAssessmentForm.getSchemeId());
			sustAssessmentBean.setVillageId(sustAssessmentForm.getVillageId());
			sustAssessmentBean.setAssessmentDate(MisUtility.convertStringToDate(sustAssessmentForm.getAssessmentDate()));
			/*System.out.println("---------------------------------------Start Susta BO----------------------------------------------------");
			System.out.println(sustAssessmentBean);
			System.out.println("---------------------------------------End Susta BO----------------------------------------------------");
			*/sustAssessmentBeans = sustAssessmentDao.findSustAssessment(sustAssessmentBean, statusList);
		
	} catch (DataAccessException e) {
		throw e;
	}
	return sustAssessmentBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveSustAssessment(SustAssessmentForm sustAssessmentForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			SustAssessmentBean sustAssessmentBean = populateSustAssessmentBean(sustAssessmentForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			sustAssessmentBean.setMisAuditBean(misAuditBean);

			status = sustAssessmentDao.saveSustAssessment(sustAssessmentBean);

			if(!status){
				log.error(sustAssessmentBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to  save Sus. Assessment details");
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
	public boolean updateSustAssessment(SustAssessmentForm sustAssessmentForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			SustAssessmentBean sustAssessmentBean = populateSustAssessmentBean(sustAssessmentForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			sustAssessmentBean.setMisAuditBean(misAuditBean);
			status = sustAssessmentDao.saveOrUpdateSustAssessment(sustAssessmentBean);

			if(!status){
				log.error(sustAssessmentBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to update Sus. Assessment details");
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw new MISException(e);
		}


		return true;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteSustAssessment(SustAssessmentForm sustAssessmentForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			SustAssessmentBean sustAssessmentBean = populateSustAssessmentBean(sustAssessmentForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			sustAssessmentBean.setMisAuditBean(misAuditBean);
			
			
			status = sustAssessmentDao.saveOrUpdateSustAssessment(sustAssessmentBean);

			if(!status){
				log.error(sustAssessmentBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to delete  Sus. Assessment details");
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
	public boolean postSustAssessment(SustAssessmentForm sustAssessmentForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		SustAssessmentBean sustAssessmentBean = new SustAssessmentBean();
		try {
			
			sustAssessmentBean.setSchemeId(sustAssessmentForm.getSchemeId());
			sustAssessmentBean.setVillageId(sustAssessmentForm.getVillageId());
			sustAssessmentBean.setAssessmentDate(MisUtility.convertStringToDate(sustAssessmentForm.getAssessmentDate()));
			sustAssessmentBean=sustAssessmentDao.findSustAssessment(sustAssessmentBean, statusList).get(0);
			System.out.println("--------------Size-----------------"+sustAssessmentBean);
				MISAuditBean misAuditBean = sustAssessmentBean.getMisAuditBean();
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				sustAssessmentBean.setMisAuditBean(misAuditBean);
			
			status = sustAssessmentDao.updateSustAssessment(sustAssessmentBean);

			if(!status){
				log.error(sustAssessmentBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to post  Sus. Assessment details");
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw new MISException(e);
		}


		return true;
	}

private SustAssessmentBean populateSustAssessmentBean(SustAssessmentForm sustAssessmentForm){
	SustAssessmentBean sustAssessmentBean = new SustAssessmentBean();
	
		try {
			sustAssessmentBean.setSchemeId(sustAssessmentForm.getSchemeId());
			sustAssessmentBean.setVillageId(sustAssessmentForm.getVillageId());
			sustAssessmentBean.setAnnualMeetingsHeld(sustAssessmentForm.getAnnualMeetingsHeld());
			sustAssessmentBean.setDisinfectionDaily(sustAssessmentForm.getDisinfectionDaily());
			sustAssessmentBean.setElectricConsumGPWSC(sustAssessmentForm.getElectricConsumGPWSC());
			sustAssessmentBean.setLocationId(sustAssessmentForm.getLocationId());
			sustAssessmentBean.setPreparedAnnualOMPlan(sustAssessmentForm.getPreparedAnnualOMPlan());
			sustAssessmentBean.setPreparedApprovedSOA(sustAssessmentForm.getPreparedApprovedSOA());
			sustAssessmentBean.setProperProtectionArrangement(sustAssessmentForm.getProperProtectionArrangement());
			sustAssessmentBean.setRevenueCollectedMoreThan50Percent(sustAssessmentForm.getRevenueCollectedMoreThan50Percent());
			sustAssessmentBean.setRevenueCollectedMoreThan90Percent(sustAssessmentForm.getRevenueCollectedMoreThan90Percent());
			sustAssessmentBean.setRevenueCollectedMoreThanOMExpenditure(sustAssessmentForm.getRevenueCollectedMoreThanOMExpenditure());
			sustAssessmentBean.setSourcefailSummer(sustAssessmentForm.getSourcefailSummer());
			sustAssessmentBean.setSourceRemainedPotable(sustAssessmentForm.getSourceRemainedPotable());
			sustAssessmentBean.setSupplyWaterLessThan120Percent(sustAssessmentForm.getSupplyWaterLessThan120Percent());
			sustAssessmentBean.setSupplyWaterLessThan50Percent(sustAssessmentForm.getSupplyWaterLessThan50Percent());
			sustAssessmentBean.setTotalHouseholdPitTaps(sustAssessmentForm.getTotalHouseholdPitTaps());
			sustAssessmentBean.setWomenMoreThan33Percent(sustAssessmentForm.getWomenMoreThan33Percent());
			sustAssessmentBean.setVillageId(sustAssessmentForm.getVillageId());
			sustAssessmentBean.setYearInclusiveMaintenanceShut(sustAssessmentForm.getYearInclusiveMaintenanceShut());
			sustAssessmentBean.setAssessmentDate(MisUtility.convertStringToDate(sustAssessmentForm.getAssessmentDate()));
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return sustAssessmentBean;	
		
		
	}
}
