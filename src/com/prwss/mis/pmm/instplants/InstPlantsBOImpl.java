/**
 * 
 */
package com.prwss.mis.pmm.instplants;

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
import com.prwss.mis.pmm.instplants.dao.InstPlantsDao;
import com.prwss.mis.pmm.instplants.struts.InstPlantsForm;

/**
 * @author pjha
 *
 */
public class InstPlantsBOImpl implements InstPlantsBO {
	private Logger log = Logger.getLogger(InstPlantsBOImpl.class);
	private InstPlantsDao instPlantsDao;

	public void setInstPlantsDao(InstPlantsDao instPlantsDao) {
		this.instPlantsDao = instPlantsDao;
	}

	@Override
	public List<InstPlantsBean> findInstPlants(InstPlantsForm instPlantsForm,
			List<String> statusList) throws MISException {
		List<InstPlantsBean> instPlantsBeans  = new ArrayList<InstPlantsBean>();
		
		try {
			InstPlantsBean instPlantsBean = new InstPlantsBean();
			
			instPlantsBean.setLocationId(instPlantsForm.getLocationId());
			instPlantsBean.setBlockId(instPlantsForm.getBlockId());

			instPlantsBean.setTransactionDate(MisUtility.convertStringToDate(instPlantsForm.getTransactionDate()));
			instPlantsBeans = instPlantsDao.findInstPlants(instPlantsBean, statusList);
		
	} catch (DataAccessException e) {
		throw e;
	}
	return instPlantsBeans;
	}

	@Override
	public boolean saveInstPlants(InstPlantsForm instPlantsForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			InstPlantsBean instPlantsBean =populateInstPlantsBean(instPlantsForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			instPlantsBean.setMisAuditBean(misAuditBean);

			status = instPlantsDao.saveInstPlants(instPlantsBean);

			if(!status){
				log.error(instPlantsBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to save Status of plant details");
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
	public boolean updateInstPlants(InstPlantsForm instPlantsForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			InstPlantsBean instPlantsBean =populateInstPlantsBean(instPlantsForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			instPlantsBean.setMisAuditBean(misAuditBean);

			status = instPlantsDao.saveOrUpdateInstPlants(instPlantsBean);

			if(!status){
				log.error(instPlantsBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to update Status of plant details");
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
	public boolean deleteInstPlants(InstPlantsForm instPlantsForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			InstPlantsBean instPlantsBean =populateInstPlantsBean(instPlantsForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			instPlantsBean.setMisAuditBean(misAuditBean);

			status = instPlantsDao.saveOrUpdateInstPlants(instPlantsBean);

			if(!status){
				log.error(instPlantsBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to delete Status of plant details");
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
	public boolean postInstPlants(InstPlantsForm instPlantsForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status = false;
			InstPlantsBean instPlantsBean= new InstPlantsBean();
			instPlantsBean.setLocationId(instPlantsForm.getLocationId());
			instPlantsBean=instPlantsDao.findInstPlants(instPlantsBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			instPlantsBean.setMisAuditBean(misAuditBean);

			status = instPlantsDao.saveOrUpdateInstPlants(instPlantsBean);

			if(!status){
				log.error(instPlantsBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Status of plant details");
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

	private InstPlantsBean populateInstPlantsBean(InstPlantsForm instPlantsForm){


		InstPlantsBean instPlantsBean = new InstPlantsBean();
		try {
			instPlantsBean.setActualDateCompletion(MisUtility.convertStringToDate(instPlantsForm.getActualDateCompletion()));
			instPlantsBean.setClusterNumber(instPlantsForm.getClusterNumber());
			instPlantsBean.setClustorNumber(instPlantsForm.getClustorNumber());
			instPlantsBean.setDivision(instPlantsForm.getDivision());
			instPlantsBean.setElectricConnection(instPlantsForm.getElectricConnection());
			instPlantsBean.setLocationId(instPlantsForm.getLocationId());
			instPlantsBean.setSiteSelection(instPlantsForm.getSiteSelection());
			instPlantsBean.setStipulatedDateCompletion(MisUtility.convertStringToDate(instPlantsForm.getStipulatedDateCompletion()));
			instPlantsBean.setTubewellAllotted(instPlantsForm.getTubewellAllotted());
			instPlantsBean.setTubewellCommissioningPlant(instPlantsForm.getTubewellCommissioningPlant());
			instPlantsBean.setTubewellCompleted(instPlantsForm.getTubewellCompleted());
			instPlantsBean.setTubewellDateStart(MisUtility.convertStringToDate(instPlantsForm.getTubewellDateStart()));
			instPlantsBean.setTubewellInprogress(instPlantsForm.getTubewellInprogress());
			instPlantsBean.setTubewellPhysicalCompletion(instPlantsForm.getTubewellPhysicalCompletion());
			instPlantsBean.setTubewellPlantArranged(instPlantsForm.getTubewellPlantArranged());
			instPlantsBean.setTubewellPlantInstalled(instPlantsForm.getTubewellPlantInstalled());
			
			instPlantsBean.setTubewellMachineryArranged(instPlantsForm.getMachineryArranged());
			instPlantsBean.setTubewellMachineryInstalled(instPlantsForm.getMachineryInstalled());
			instPlantsBean.setTubewellPlatformCompleted(instPlantsForm.getPlatformCompleted());
			
			instPlantsBean.setTubewellReleaseElectric(instPlantsForm.getTubewellReleaseElectric());
			instPlantsBean.setTypePlant(instPlantsForm.getTypePlant());
			instPlantsBean.setVillagesCovered(instPlantsForm.getVillagesCovered());
			instPlantsBean.setTransactionDate(MisUtility.convertStringToDate(instPlantsForm.getTransactionDate()));
            instPlantsBean.setBlockId(instPlantsForm.getBlockId());
            instPlantsBean.setVillageId(instPlantsForm.getVillageId());
            instPlantsBean.setTubewellHousingCompleted(instPlantsForm.getTubewellHousingCompleted());

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return instPlantsBean;
	}

}
