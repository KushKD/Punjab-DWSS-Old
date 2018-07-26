/**
 * 
 */
package com.prwss.mis.pmm.watersupply;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.watersupply.dao.WaterSupplyDao;
import com.prwss.mis.pmm.watersupply.struts.WaterSupplyForm;

/**
 * @author pjha
 *
 */
public class WaterSupplyBOImpl implements WaterSupplyBO {
	private Logger log = Logger.getLogger(WaterSupplyBOImpl.class);
	private WaterSupplyDao waterSupplyDao;
	
	
	
	public void setWaterSupplyDao(WaterSupplyDao waterSupplyDao) {
		this.waterSupplyDao = waterSupplyDao;
	}

	@Override
	public List<WaterSupplyBean> findWaterSupply(
			WaterSupplyForm waterSupplyForm, List<String> statusList)
			throws MISException {
		List<WaterSupplyBean> waterSupplyBeans  = new ArrayList<WaterSupplyBean>();

		try {
			WaterSupplyBean waterSupplyBean = new WaterSupplyBean();

			waterSupplyBean.setLocationId(waterSupplyForm.getLocationId());
			waterSupplyBean.setVillageId(waterSupplyForm.getVillageId());
			waterSupplyBeans=waterSupplyDao.findWaterSupply(waterSupplyBean, statusList);

		} catch (DataAccessException e) {
			throw e;
		}
		return waterSupplyBeans;

	}


	@Override
	public boolean saveWaterSupply(WaterSupplyForm waterSupplyForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			WaterSupplyBean waterSupplyBean = populateWaterSupplyBean(waterSupplyForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			waterSupplyBean.setMisAuditBean(misAuditBean);

			status = waterSupplyDao.saveWaterSupply(waterSupplyBean);

			if(!status){
				log.error(waterSupplyBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to save Water supply parameters details");
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
	public boolean updateWaterSupply(WaterSupplyForm waterSupplyForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			WaterSupplyBean waterSupplyBean = populateWaterSupplyBean(waterSupplyForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			waterSupplyBean.setMisAuditBean(misAuditBean);

			status = waterSupplyDao.saveOrUpdateWaterSupply(waterSupplyBean);

			if(!status){
				log.error(waterSupplyBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to update Water supply parameters details");
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
	public boolean deleteWaterSupply(WaterSupplyForm waterSupplyForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			WaterSupplyBean waterSupplyBean = populateWaterSupplyBean(waterSupplyForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			waterSupplyBean.setMisAuditBean(misAuditBean);

			status = waterSupplyDao.saveOrUpdateWaterSupply(waterSupplyBean);

			if(!status){
				log.error(waterSupplyBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to delete Water supply parameters details");
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
	public boolean postWaterSupply(WaterSupplyForm waterSupplyForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status = false;
			
			WaterSupplyBean waterSupplyBean = new WaterSupplyBean();
			waterSupplyBean.setLocationId(waterSupplyForm.getLocationId());
			waterSupplyBean.setVillageId(waterSupplyForm.getVillageId());
			waterSupplyBean=waterSupplyDao.findWaterSupply(waterSupplyBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			waterSupplyBean.setMisAuditBean(misAuditBean);

			status = waterSupplyDao.saveOrUpdateWaterSupply(waterSupplyBean);

			if(!status){
				log.error(waterSupplyBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to post Water supply parameters details");
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

	private WaterSupplyBean populateWaterSupplyBean(WaterSupplyForm waterSupplyForm){
		
		
		WaterSupplyBean waterSupplyBean = new WaterSupplyBean();
			try {
				waterSupplyBean.setAvailabilityOfLand(waterSupplyForm.getAvailabilityOfLand());
				waterSupplyBean.setCattlePopulation(waterSupplyForm.getCattlePopulation());
				waterSupplyBean.setDisposalLocation(waterSupplyForm.getDisposalLocation());
				waterSupplyBean.setGroundwaterLevel(waterSupplyForm.getGroundwaterLevel());
				waterSupplyBean.setLandOwnership(waterSupplyForm.getLandOwnership());
				waterSupplyBean.setLandRequirment(waterSupplyForm.getLandRequirment());
				waterSupplyBean.setLanduseStpSite(waterSupplyForm.getLanduseStpSite());
				waterSupplyBean.setLocationId(waterSupplyForm.getLocationId());
				waterSupplyBean.setMunicipalCorporataion(waterSupplyForm.getMunicipalCorporataion());
				waterSupplyBean.setNameOfDisease(waterSupplyForm.getNameOfDisease());
				waterSupplyBean.setNoWaterbornDisease(waterSupplyForm.getNoWaterbornDisease());
				waterSupplyBean.setReceptorsHealthcenterDistance(waterSupplyForm.getReceptorsHealthcenterDistance());
				waterSupplyBean.setReceptorsReligiousStrDistance(waterSupplyForm.getReceptorsReligiousStrDistance());
				waterSupplyBean.setReceptorsSchoolDistance(waterSupplyForm.getReceptorsSchoolDistance());
				waterSupplyBean.setReceptorsWildlifeDistance(waterSupplyForm.getReceptorsWildlifeDistance());
				waterSupplyBean.setSettlementsDistance(waterSupplyForm.getSettlementsDistance());
				waterSupplyBean.setSourceWater(waterSupplyForm.getSourceWater());
				waterSupplyBean.setTopography(waterSupplyForm.getTopography());
				waterSupplyBean.setTypeOfRoads(waterSupplyForm.getTypeOfRoads());
				waterSupplyBean.setTypeWaterSupply(waterSupplyForm.getTypeWaterSupply());
				waterSupplyBean.setUsageWaterBody(waterSupplyForm.getUsageWaterBody());
				waterSupplyBean.setWaterBody(waterSupplyForm.getWaterBody());
				waterSupplyBean.setWaterBodyDistance(waterSupplyForm.getWaterBodyDistance());
				waterSupplyBean.setWaterLevel(waterSupplyForm.getWaterLevel());
				waterSupplyBean.setWaterQuality(waterSupplyForm.getWaterQuality());
				waterSupplyBean.setWidthOfRoad(waterSupplyForm.getWidthOfRoad());
				waterSupplyBean.setDateOfTransaction(MisUtility.convertStringToDate(waterSupplyForm.getDateOfTransaction()));
				waterSupplyBean.setVillageId(waterSupplyForm.getVillageId());
				waterSupplyBean.setProvisionLawnPlantation(waterSupplyForm.getProvisionLawnPlantation());
				waterSupplyBean.setRainWaterHarvesting(waterSupplyForm.getRainWaterHarvesting());
				waterSupplyBean.setRepairCleaning(waterSupplyForm.getRepairCleaning());
				waterSupplyBean.setSolidWasteRemoval(waterSupplyForm.getSolidWasteRemoval());
				waterSupplyBean.setPublicAwareness(waterSupplyForm.getPublicAwareness());
				waterSupplyBean.setCleaningPondRequired(waterSupplyForm.getCleaningPondRequired());
				waterSupplyBean.setWasteWaterQuantity(waterSupplyForm.getWasteWaterQuantity());
				waterSupplyBean.setCurrentSanitation(waterSupplyForm.getCurrentSanitation());
				waterSupplyBean.setExistingPondsSewageDischarge(waterSupplyForm.getExistingPondsSewageDisharge());
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
			}
			
			return waterSupplyBean;
			
			
			
		}
}
