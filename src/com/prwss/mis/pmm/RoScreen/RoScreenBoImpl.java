package com.prwss.mis.pmm.RoScreen;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.RoScreenDao.RoScreenDao;
import com.prwss.mis.pmm.RoScreenDao.RoScreenDaoImpl;
import com.prwss.mis.pmm.RoScreenStruts.RoScreenForm;

public class RoScreenBoImpl implements RoScreenBo {

	private Logger log = Logger.getLogger(RoScreenDaoImpl.class);

	private RoScreenDao roScreenDao;

	public RoScreenDao getRoScreenDao() {
		return roScreenDao;
	}

	public void setRoScreenDao(RoScreenDao roScreenDao) {
		this.roScreenDao = roScreenDao;
	}

// ----------------------------------------------------------------------------------------------------------------------------------------------------

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean saveData(RoScreenForm roScreenForm, Integer enteredBy) throws MISException {

		boolean status = false;
		int roConnection=0;
		List<RoScreenBean> roScreenBean = null;
		try {
			if (MisUtility.ifEmpty(roScreenForm)) {
				roScreenBean = roScreenDao.fetchRoConnectionBean(roScreenForm.getDivision(),roScreenForm.getSubDivision(), roScreenForm.getVillage());
				
				if (!MisUtility.ifEmpty(roScreenBean)) {
						for(RoScreenBean roBean : roScreenBean){
							
							//roBean.setActiveFlag(Integer.parseInt(MISConstants.ZERO));
							roConnection = roScreenDao.update(roBean, enteredBy);
						}
						if(MisUtility.ifEmpty(roConnection)){
							RoScreenBean roScreenBeans = populateRoScreenBean(roScreenForm, enteredBy);
							
							if (MisUtility.ifEmpty(roScreenBeans)) {
								status = roScreenDao.saveRoConnectionDetails(roScreenBeans);
							}
						}
					}
				}
		} catch (DataAccessException e) {
			log.debug(e.getMessage());
			throw new MISException(e);
		}
		return status;
	}
// ----------------------------------------------------------------------------------------------------------------------------------------------------

	private RoScreenBean populateRoScreenBean(RoScreenForm roScreenForm, Integer enteredBy) {

		RoScreenBean roScreenBean = null;

		try {
			if (MisUtility.ifEmpty(roScreenForm)) {
				roScreenBean = new RoScreenBean();
				
				if (MisUtility.ifEmpty(roScreenForm.getDivision())) {
					roScreenBean.setDivision(roScreenForm.getDivision());
				}
				if (MisUtility.ifEmpty(roScreenForm.getSubDivision())) {
					roScreenBean.setSubDivision(roScreenForm.getSubDivision());
				}
				if (MisUtility.ifEmpty(roScreenForm.getVillage())) {
					roScreenBean.setVillage(roScreenForm.getVillage());
				}
				if (MisUtility.ifEmpty(roScreenForm.getDivisionName())){
					roScreenBean.setDivisionName(roScreenForm.getDivisionName());
				}
				if(MisUtility.ifEmpty(roScreenForm.getSubDivisionName())){
					roScreenBean.setSubDivisionName(roScreenForm.getSubDivisionName());
				}
				if(MisUtility.ifEmpty(roScreenForm.getVillageName())){
					roScreenBean.setVillageName(roScreenForm.getVillageName());
				}
				if (MisUtility.ifEmpty(roScreenForm.getCapacityOfRO())) {
					roScreenBean.setCapacityOfRO(roScreenForm.getCapacityOfRO());
				}
				if (MisUtility.ifEmpty(roScreenForm.getHeadProgramme())) {
					roScreenBean.setHeadProgramme(roScreenForm.getHeadProgramme());
				}
				if (MisUtility.ifEmpty(roScreenForm.getAdminAppCost())) {
					roScreenBean.setAdminAppCost(Double.parseDouble(roScreenForm.getAdminAppCost()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getExecutingAgency())) {
					roScreenBean.setExecutingAgency(roScreenForm.getExecutingAgency());
				}
				if (MisUtility.ifEmpty(roScreenForm.getCompOandMDate())) {
					roScreenBean.setCompOandMDate(MisUtility.convertStringToDate(roScreenForm.getCompOandMDate()));
				}
				if(MisUtility.ifEmpty(roScreenForm.getSeasonalRO())){
					roScreenBean.setSeasonalRO(roScreenForm.getSeasonalRO());
				}
				if (MisUtility.ifEmpty(roScreenForm.getNoHHsVillage())) {
					roScreenBean.setNoHHsVillage(Integer.parseInt(roScreenForm.getNoHHsVillage()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getLockedHouses())) {
					roScreenBean.setLockedHouses(Integer.parseInt(roScreenForm.getLockedHouses()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getCardHolders())) {
					roScreenBean.setCardHolders(Integer.parseInt(roScreenForm.getCardHolders()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getPercenPenetration())) {
					roScreenBean.setPercenPenetration(Double.parseDouble(roScreenForm.getPercenPenetration()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getTubeInstStatus())) {
					roScreenBean.setTubeInstStatus(Integer.parseInt(roScreenForm.getTubeInstStatus()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getPlatform())) {
					roScreenBean.setPlatform(Integer.parseInt(roScreenForm.getPlatform()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getHousingStr())) {
					roScreenBean.setHousingStr(Integer.parseInt(roScreenForm.getHousingStr()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getMachinery())) {
					roScreenBean.setMachinery(Integer.parseInt(roScreenForm.getMachinery()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getPlant())) {
					roScreenBean.setPlant(Integer.parseInt(roScreenForm.getPlant()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getElecConnStatus())) {
					roScreenBean.setElecConnStatus(Integer.parseInt(roScreenForm.getElecConnStatus()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getAllotCost())) {
					roScreenBean.setAllotCost(Double.parseDouble(roScreenForm.getAllotCost()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getExpenTilldate())) {
					roScreenBean.setExpenTillDate(Double.parseDouble(roScreenForm.getExpenTilldate()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getPlantCommissioned())) {
					roScreenBean.setPlantCommissioned(Integer.parseInt(roScreenForm.getPlantCommissioned()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getCommisioningDate())) {
					roScreenBean
							.setCommisioningDate(MisUtility.convertStringToDate(roScreenForm.getCommisioningDate()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getTargetCommDate())) {
					roScreenBean.setTargetCommDate(MisUtility.convertStringToDate(roScreenForm.getTargetCommDate()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getPlantComplete())) {
					roScreenBean.setPlantComplete(Integer.parseInt(roScreenForm.getPlantComplete()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getCompleteDate())) {
					roScreenBean.setCompleteDate(MisUtility.convertStringToDate(roScreenForm.getCompleteDate()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getTargetCopmDate())) {
					roScreenBean.setTargetCopmDate(MisUtility.convertStringToDate(roScreenForm.getTargetCopmDate()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getPlantFunction())) {
					roScreenBean.setPlantFunction(Integer.parseInt(roScreenForm.getPlantFunction()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getSinceNonFunctionDate())) {
					roScreenBean.setSinceNonFunction(
							MisUtility.convertStringToDate(roScreenForm.getSinceNonFunctionDate()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getReason())) {
					roScreenBean.setReason(Integer.parseInt(roScreenForm.getReason()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getFunctionalDate())) {
					roScreenBean.setFunctionalDate(MisUtility.convertStringToDate(roScreenForm.getFunctionalDate()));
				}
				if (MisUtility.ifEmpty(roScreenForm.getOthersReason())){
					roScreenBean.setOthersReason(roScreenForm.getOthersReason());
				}

				roScreenBean.setCreatedByUser(Long.valueOf(enteredBy));
				roScreenBean.setActiveFlag(Integer.parseInt(MISConstants.ONE));

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e.getMessage());
		}
		return roScreenBean;
	}
// ----------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public List<RoScreenBean> find(RoScreenForm roScreenForm,
			List<String> statusList) throws MISException {
		List<RoScreenBean> roScreenBeans  = new ArrayList<RoScreenBean>();
		
		try {
			RoScreenBean roScreenBean = new RoScreenBean();
			
			roScreenBean.setDivision(roScreenForm.getDivision());
			roScreenBean.setSubDivision(roScreenForm.getSubDivision());
			roScreenBean.setVillage(roScreenForm.getVillage());
			roScreenBeans = roScreenDao.find(roScreenBean, statusList);
		
	} catch (DataAccessException e) {
		throw e;
	}
	return roScreenBeans;
	}

}
