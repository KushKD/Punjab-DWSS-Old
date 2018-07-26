package com.prwss.mis.masters.village;

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
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.village.dao.VillageBean;
import com.prwss.mis.masters.village.dao.VillageDao;
import com.prwss.mis.masters.village.dao.VillageViewBean;
import com.prwss.mis.masters.village.dao.VillageViewDao;
import com.prwss.mis.masters.village.struts.VillageForm;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;

public class VillageBOImpl implements VillageBO {
	private VillageDao villageDao;
	private Logger log = Logger.getLogger(VillageBOImpl.class);
	private LocationDao locationDao; 
	private DocumentNumberDAO documentNumberDao;
	private VillageViewDao villageViewDao;
	
	public void setVillageViewDao(VillageViewDao villageViewDao) {
		this.villageViewDao = villageViewDao;
	}




	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}




	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}




	public void setVillageDao(VillageDao villageDao) {
		this.villageDao = villageDao;
	}


	@Override
	public List<VillageBean> findVillage(VillageForm villageForm,
			List<String> statusList) throws MISException {
		List<VillageBean> villageBeans = null;
		try {
			VillageBean villageBean = new VillageBean();
			if(MisUtility.ifEmpty(villageForm.getVillageId())){
				System.out.println("In");
			villageBean.setVillageId(villageForm.getVillageId());
			villageBeans = villageDao.findVillage(villageBean, statusList);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return villageBeans;
	}
	
	@Override
	public List<VillageViewBean> findVillageView(VillageForm villageForm,
			List<String> statusList) throws MISException {
		List<VillageViewBean> villageViewBeans = null;
		try {
			
			VillageBean villageBean = new VillageBean();
			
			if(MisUtility.ifEmpty(villageForm.getVillageId())){
			villageBean.setVillageId(villageForm.getVillageId().trim());
			}else{
				System.out.println("villageForm.getDistrict()"+villageForm.getDistrict());
			villageBean.setDistrictId(villageForm.getDistrict());
			}
			villageViewBeans = villageViewDao.findVillageFromView(villageBean, statusList);
	
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return villageViewBeans;
	}

	@Override
	public List<VillageViewBean> findOtherHabitation(VillageForm villageForm,
			List<String> statusList) throws MISException {
		List<VillageViewBean> villageViewBeans = null;
		try {
			
			VillageBean villageBean = new VillageBean();
			villageBean.setParentHabitationId(villageForm.getVillageId().trim());
			System.out.println("========="+villageForm.getParentHabitationId() );
			if(MisUtility.ifEmpty(villageForm.getParentHabitationId())){
			
			}else{
				System.out.println("villageForm.getDistrict()"+villageForm.getDistrict());
				villageBean.setDistrictId(villageForm.getDistrict());
			}
			villageViewBeans = villageViewDao.findVillageFromView(villageBean, statusList);
	
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return villageViewBeans;
	}




	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String saveVillage(VillageForm villageForm,
			MISSessionBean misSessionBean) throws MISException {
		String villageId = null;
		try {
			VillageBean villageFindBean = new VillageBean();
			villageFindBean.setVillageName(villageForm.getVillageName().trim());
			villageFindBean.setDistrictId(villageForm.getDistrict());
			villageFindBean.setBlockId(villageForm.getBlockId());
			List<String> checkList = new ArrayList<String>();
			checkList.add(MISConstants.MASTER_STATUS_APPROVED);
			checkList.add(MISConstants.MASTER_STATUS_VERIFIED);
			List<VillageBean> villageBeans = villageDao.findVillage(villageFindBean, checkList);
			if(!MisUtility.ifEmpty(villageBeans)){
				throw new MISException(MISExceptionCodes.MIS001, "Duplicate Village \t"+villageForm.getVillageName()+" in given District and Block");			
			}
			DocumentNumberBean documentNumebrBean=new DocumentNumberBean();
			documentNumebrBean.setDocumentType("VILLAGE");
			DocumentNumberBean documentNumberBean = documentNumberDao.getDocumentNumber(documentNumebrBean).get(0);
			if(!(MisUtility.ifEmpty(documentNumberBean))){
				throw new MISException();
			}
			VillageBean villageBean = populateVillageBean(villageForm);
			villageBean.setVillageId((new Long(documentNumberBean.getLastNumber()).toString()));
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			villageBean.setMisAuditBean(misAuditBean);
			villageId = villageDao.saveVillage(villageBean);
			if(MisUtility.ifEmpty(villageId)){
				documentNumberBean.setLastNumber(Long.parseLong(villageId));
			}
			boolean updateStatus = documentNumberDao.saveOrUpdateDocumentNumberBeans(documentNumberBean);
			if(!updateStatus){
				throw new MISException();
			}
		
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return villageId;
	}





	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateVillage(VillageForm villageForm,
			MISSessionBean misSessionBean,List<String> statusList) throws MISException {
		boolean status = false;
		try {
			VillageBean villageBean = populateVillageBean(villageForm);	
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(statusList.get(0));
			villageBean.setMisAuditBean(misAuditBean);
//			villageBean.setMisAuditBean(villageForm.getMisAuditBean());
			status = villageDao.updateVillage(villageBean);
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
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteVillage(VillageForm villageForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			VillageBean villageBean = populateVillageBean(villageForm);	
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			villageBean.setMisAuditBean(misAuditBean);
			status = villageDao.updateVillage(villageBean);
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
	public boolean postVillage(VillageForm villageForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		boolean status2 = false;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			VillageBean villageBean = populateVillageBean(villageForm);	
			MISAuditBean misAuditBean = new MISAuditBean();
			List<VillageBean> villageBeans = villageDao.findVillage(villageBean, statusList);
			for (VillageBean villageBean2 : villageBeans) {
			misAuditBean = villageBean2.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			villageBean.setMisAuditBean(misAuditBean);
			}
			status = villageDao.updateVillage(villageBean);
			if(status){
				LocationBean locationBean = new LocationBean();
				locationBean.setLocationId(villageForm.getVillageId());
				locationBean.setLocationName(villageForm.getVillageName());
				locationBean.setLocationType("VILLAGE");
				LocationBean locationFindBean =  locationDao.getLocation(locationBean);
				if(!MisUtility.ifEmpty(locationFindBean)){
				locationBean.setMisAuditBean(misAuditBean);
				locationBean.setParentLocation(villageForm.getBlockId());
				status2 = locationDao.saveLocation(locationBean);
				}
//				if(!status2){
//					throw new MISException(MISExceptionCodes.MIS003, "Location not saved");
//				}
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}
	

	

	private VillageBean populateVillageBean(VillageForm villageForm){
		String actualCatgory = "";
		VillageBean villageBean = new VillageBean();
		villageBean.setVillageId(villageForm.getVillageId());
		villageBean.setVillageName(villageForm.getVillageName().trim());
		villageBean.setBlockId(villageForm.getBlockId());
//		villageBean.setCategorory(villageForm.getCategory());
		villageBean.setConstituencyId(villageForm.getConstituencyId());
		villageBean.setHabitationType(villageForm.getHabitationType());
		villageBean.setNcPcVillageStatus(villageForm.getNcPcVillageStatus());
		villageBean.setNcpcVillageListSerialNo(villageForm.getNcPcVillageListSerialNo());
		villageBean.setNumberOfPonds(villageForm.getNumberOfPonds());
		villageBean.setParliamentConstituencyName(villageForm.getParliamentConstituencyName());
		villageBean.setNameOfGramPanchayat(villageForm.getNameOfGramPanchayat());
		villageBean.setFreezedValue(MisUtility.convertStringToDate("01-01-2013"));
		if(MisUtility.ifEmpty(villageForm.getParentHabitationId())){
			villageBean.setParentHabitationId(villageForm.getParentHabitationId());
			if(villageForm.getHabitationType().equals("OH")){
				VillageBean villBean = new VillageBean();
				villBean.setBlockId(villageForm.getBlockId());
				villBean.setHabitationType("MH");
				villBean.setVillageId(villageForm.getParentHabitationId());
				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
				List<VillageBean> villageBeans = villageDao.findVillage(villBean, statusList);
				villageBean.setParentHabitationName(villageBeans.get(0).getVillageName());
			}
		}
		else{
		villageBean.setParentHabitationId(null);
		}
		
		if(MisUtility.ifEmpty(villageForm.getCategory())){
			String[] category = villageForm.getCategory();
			for (String string : category) {
				if(actualCatgory.equals("")){
					actualCatgory = string;
				}else{
					actualCatgory = actualCatgory+ "~" + string;
				}
			}
			villageBean.setCategory(actualCatgory);
		}else{
			villageBean.setCategory(null);
		}
		
		
		villageBean.setWaterSource(villageForm.getWaterSource());
		villageBean.setDivisionalOfficeId(villageForm.getDivisionalOfficeId());
		villageBean.setSubDiv(villageForm.getSubDiv());
		villageBean.setdWSCApproved(villageForm.isdWSCApproved());
		villageBean.setHadBastNo(villageForm.getHadBastNo());
		villageBean.setPcDate(MisUtility.convertStringToDate(villageForm.getPcDate()));
		villageBean.setFcDate(MisUtility.convertStringToDate(villageForm.getFcDate()));
		villageBean.setDistrictId(villageForm.getDistrict());
		return villageBean;
	}




}
