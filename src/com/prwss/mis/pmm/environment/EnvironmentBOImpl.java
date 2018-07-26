/**
 * 
 */
package com.prwss.mis.pmm.environment;

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
import com.prwss.mis.pmm.environment.dao.EnvironmentDao;
import com.prwss.mis.pmm.environment.struts.EnvironmentForm;

/**
 * @author pjha
 *
 */
public class EnvironmentBOImpl implements EnvironmentBO {
	public void setEnvironmentDao(EnvironmentDao environmentDao) {
		this.environmentDao = environmentDao;
	}

	private Logger log = Logger.getLogger(EnvironmentBOImpl.class);
	private EnvironmentDao environmentDao;
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<EnvironmentBean> findEnvironment(
			EnvironmentForm environmentForm, List<String> statusList)
			throws MISException {
		List<EnvironmentBean> environmentBeans  = new ArrayList<EnvironmentBean>();
		
		try {
			EnvironmentBean environmentBean = new EnvironmentBean();
			
			environmentBean.setLocationId(environmentForm.getLocationId());
			environmentBean.setVillageId(environmentForm.getVillageId());
			environmentBeans = environmentDao.findEnvironmentBean(environmentBean, statusList);
			
		
	} catch (DataAccessException e) {
		throw e;
	}
	return environmentBeans;
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveEnvironment(EnvironmentForm environmentForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			EnvironmentBean environmentBean = populateEnvironmentBean(environmentForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			environmentBean.setMisAuditBean(misAuditBean);

			status = environmentDao.saveEnvironmentBean(environmentBean);

			if(!status){
				log.error(environmentBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to save Environment details");
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
	public boolean updateEnvironment(EnvironmentForm environmentForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			EnvironmentBean environmentBean = populateEnvironmentBean(environmentForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			environmentBean.setMisAuditBean(misAuditBean);

			status = environmentDao.saveOrUpdateEnvironmentBean(environmentBean);

			if(!status){
				log.error(environmentBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Environment details");
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
	public boolean deleteEnvironment(EnvironmentForm environmentForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			EnvironmentBean environmentBean = populateEnvironmentBean(environmentForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);;
			environmentBean.setMisAuditBean(misAuditBean);

			status = environmentDao.saveOrUpdateEnvironmentBean(environmentBean);

			if(!status){
				log.error(environmentBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to delete Environment details");
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
	public boolean postEnvironment(EnvironmentForm environmentForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status = false;
			EnvironmentBean environmentBean= new EnvironmentBean();
			environmentBean.setLocationId(environmentForm.getLocationId());
			environmentBean.setVillageId(environmentForm.getVillageId());
			 environmentBean =environmentDao.findEnvironmentBean(environmentBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);;
			environmentBean.setMisAuditBean(misAuditBean);

			status = environmentDao.saveOrUpdateEnvironmentBean(environmentBean);

			if(!status){
				log.error(environmentBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Environment details");
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

private EnvironmentBean populateEnvironmentBean(EnvironmentForm environmentForm){
		
		
	EnvironmentBean environmentBean = new EnvironmentBean();
		try {
			environmentBean.setLocationId(environmentForm.getLocationId());
			environmentBean.setVillageId(environmentForm.getVillageId());
			environmentBean.setAffectNaturalHabitats(environmentForm.getAffectNaturalHabitats());
			environmentBean.setAntWaterAvlbIssue(environmentForm.getAntWaterAvlbIssue());
			environmentBean.setAntWaterAvlbMitigation(environmentForm.getAntWaterAvlbMitigation());
			environmentBean.setAntWaterQultIssue(environmentForm.getAntWaterQultIssue());
			environmentBean.setAntWaterQultMitigation(environmentForm.getAntWaterQultMitigation());
			environmentBean.setConstructionIssue(environmentForm.getConstructionIssue());
			environmentBean.setConstructionMitigation(environmentForm.getConstructionMitigation());
			environmentBean.setConstructionWastesIssue(environmentForm.getConstructionWastesIssue());
			environmentBean.setConstructionWastesMitigation(environmentForm.getConstructionWastesMitigation());
			environmentBean.setContaminationDrinkingWater(environmentForm.getContaminationDrinkingWater());
			environmentBean.setContaminationDrinkingWaterSource(environmentForm.getContaminationDrinkingWaterSource());
			environmentBean.setDiseaseName(environmentForm.getDiseaseName());
			environmentBean.setDisinfectionExists(environmentForm.getDisinfectionExists());
			environmentBean.setDomesticSolidWaste(environmentForm.getDomesticSolidWaste());
			environmentBean.setExistingRoads(environmentForm.getExistingRoads());
			environmentBean.setExistingWaterBody(environmentForm.getExistingWaterBody());
			environmentBean.setExistingWaterBodyOther(environmentForm.getExistingWaterBodyOther());
			environmentBean.setGreyBlackWaterMix(environmentForm.getGreyBlackWaterMix());
			environmentBean.setHistoricalImportance(environmentForm.getHistoricalImportance());
			environmentBean.setIncidentWaterborn(environmentForm.getIncidentWaterborn());
			environmentBean.setInfringeLocalRights(environmentForm.getInfringeLocalRights());
			environmentBean.setIntensityOfRainfall(environmentForm.getIntensityOfRainfall());
			environmentBean.setIonisationPlant(environmentForm.getIonisationPlant());
			environmentBean.setLandfillSiteSolidWaste(environmentForm.getLandfillSiteSolidWaste());
			environmentBean.setLandUse(environmentForm.getLandUse());
			environmentBean.setLandUsePattern(environmentForm.getLandUsePattern());
			environmentBean.setLocalVegetation(environmentForm.getLocalVegetation());
			environmentBean.setMajorSourceIncome(environmentForm.getMajorSourceIncome());
			environmentBean.setMethodTreatment(environmentForm.getMethodTreatment());
			environmentBean.setNatureOfProblem(environmentForm.getNatureOfProblem());
			environmentBean.setNumberHousehold(environmentForm.getNumberHousehold());
			environmentBean.setPondsDistanceSchool(environmentForm.getPondsDistanceSchool());
			environmentBean.setPondsDistanceSettlement(environmentForm.getPondsDistanceSettlement());
			environmentBean.setPondsExpansionLand(environmentForm.getPondsExpansionLand());
			environmentBean.setPondsExpansionRequired(environmentForm.getPondsExpansionRequired());
			environmentBean.setPondsSitePlantation(environmentForm.getPondsSitePlantation());
			environmentBean.setPondsSitePlantation(environmentForm.getPondsSitePlantation());
			environmentBean.setPondsStp(environmentForm.getPondsStp());
			environmentBean.setPondsWaterQuality(environmentForm.getPondsWaterQuality());
			environmentBean.setPondUse(environmentForm.getPondUse());
			environmentBean.setPopulation(environmentForm.getPopulation());
			environmentBean.setPopulationEffectedWaterLogging(environmentForm.getPopulationEffectedWaterLogging());
			environmentBean.setRiskContamination(environmentForm.getRiskContamination());
			environmentBean.setRoadWidthMaxMin(environmentForm.getRoadWidthMaxMin());
			environmentBean.setSanitationFeedback(environmentForm.getSanitationFeedback());
			environmentBean.setSanitationIssue(environmentForm.getSanitationIssue());
			environmentBean.setSanitationMitigation(environmentForm.getSanitationMitigation());
			environmentBean.setSchemeType(environmentForm.getSchemeType());
			environmentBean.setSewagePattern(environmentForm.getSewagePattern());
			environmentBean.setSewagePractices(environmentForm.getSewagePractices());
			environmentBean.setSewageSchemeType(environmentForm.getSewageSchemeType());
			environmentBean.setSlopeOfLand(environmentForm.getSlopeOfLand());
			environmentBean.setSourceOfDrinkingWater(environmentForm.getSourceOfDrinkingWater());
			environmentBean.setSourceWaterAssessed(environmentForm.getSourceWaterAssessed());
			environmentBean.setTempMax(environmentForm.getTempMax());
			environmentBean.setTempMin(environmentForm.getTempMin());
			environmentBean.setTopography(environmentForm.getTopography());
			environmentBean.setTreatmentTechnology(environmentForm.getTreatmentTechnology());
			environmentBean.setTreamentProposed(environmentForm.getTreamentProposed());
			environmentBean.setTypeOfSoil(environmentForm.getTypeOfSoil());
			environmentBean.setVectorDisease(environmentForm.getVectorDisease());
			environmentBean.setWastewaterCattle(environmentForm.getWastewaterCattle());
			environmentBean.setWastewaterQuantity(environmentForm.getWastewaterQuantity());
			environmentBean.setWaterAvailability(environmentForm.getWaterAvailability());
			environmentBean.setWaterLoggingArea(environmentForm.getWaterLoggingArea());
			environmentBean.setWaterLoggingAreaName(environmentForm.getWaterLoggingAreaName());
			environmentBean.setWaterLoggingPeriod(environmentForm.getWaterLoggingPeriod());
			environmentBean.setWaterTable(environmentForm.getWaterTable());
			environmentBean.setWindDirection(environmentForm.getWindDirection());
			environmentBean.setDateOfTransaction(MisUtility.convertStringToDate(environmentForm.getDateOfTransaction()));
						
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return environmentBean;
		
		
		
	}
}
