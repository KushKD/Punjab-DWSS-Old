package com.prwss.mis.pmm.village;

import java.util.ArrayList;
import java.util.Collection;
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
import com.prwss.mis.pmm.village.connection.dao.VillageConnectionBean;
import com.prwss.mis.pmm.village.connection.dao.VillageConnectionDao;
import com.prwss.mis.pmm.village.household.dao.VillageHouseHoldBean;
import com.prwss.mis.pmm.village.household.dao.VillageHouseHoldDao;
import com.prwss.mis.pmm.village.ncpcstatus.dao.VillageNCPCStatusBean;
import com.prwss.mis.pmm.village.ncpcstatus.dao.VillageNCPCStatusDao;
import com.prwss.mis.pmm.village.population.dao.VillagePopulationBean;
import com.prwss.mis.pmm.village.population.dao.VillagePopulationDao;
import com.prwss.mis.pmm.village.sewerage.dao.VillageSewerageBean;
import com.prwss.mis.pmm.village.sewerage.dao.VillageSewerageDao;
import com.prwss.mis.pmm.village.struts.VillageConnectionGridBean;
import com.prwss.mis.pmm.village.struts.VillageDetailsForm;
import com.prwss.mis.pmm.village.struts.VillageHouseHoldGridBean;
import com.prwss.mis.pmm.village.struts.VillagePopulationGridBean;
import com.prwss.mis.pmm.village.struts.VillageSewerageGridBean;
import com.prwss.mis.pmm.village.struts.VillageTariffGridBean;
import com.prwss.mis.pmm.village.tariff.dao.VillageTariffBean;
import com.prwss.mis.pmm.village.tariff.dao.VillageTariffDao;


public class VillageDetailsBOImpl implements VillageDetailsBO {
	private Logger log = Logger.getLogger(VillageDetailsBOImpl.class); 
	private VillageConnectionDao villageConnectionDao;
	private VillageHouseHoldDao villageHouseHoldDao;
	private VillageTariffDao villageTariffDao;
	private VillagePopulationDao villagePopulationDao;
	private VillageSewerageDao villageSewerageDao;
	private VillageNCPCStatusDao villageNCPCStatusDao;
	
	public void setVillageNCPCStatusDao(VillageNCPCStatusDao villageNCPCStatusDao) {
		this.villageNCPCStatusDao = villageNCPCStatusDao;
	}

	public void setVillageSewerageDao(VillageSewerageDao villageSewerageDao) {
		this.villageSewerageDao = villageSewerageDao;
	}

	public void setVillageConnectionDao(VillageConnectionDao villageConnectionDao) {
		this.villageConnectionDao = villageConnectionDao;
	}

	public void setVillageHouseHoldDao(VillageHouseHoldDao villageHouseHoldDao) {
		this.villageHouseHoldDao = villageHouseHoldDao;
	}

	public void setVillageTariffDao(VillageTariffDao villageTariffDao) {
		this.villageTariffDao = villageTariffDao;
	}

	public void setVillagePopulationDao(VillagePopulationDao villagePopulationDao) {
		this.villagePopulationDao = villagePopulationDao;
	}

	@Override
	public VillageDetailsBean  findVillageDetails(
			VillageDetailsForm villageDetailsForm, List<String> statusList)
			throws MISException {
		VillageDetailsBean villageDetailsBean =new VillageDetailsBean();
		try {
			
			if(MisUtility.ifEmpty(villageDetailsForm.getVillageId())){
				System.out.println("In Find");
				System.out.println("Village Id in ----->"+villageDetailsForm.getVillageId());
			VillageConnectionBean villageConnectionBean = new VillageConnectionBean();
			villageConnectionBean.setVillageId(villageDetailsForm.getVillageId());
			
			VillagePopulationBean villagePopulationBean= new VillagePopulationBean();
			villagePopulationBean.setVillageId(villageDetailsForm.getVillageId());
			
			VillageHouseHoldBean villageHouseHoldBean = new VillageHouseHoldBean();
			villageHouseHoldBean.setVillageId(villageDetailsForm.getVillageId());
			
			VillageTariffBean villageTariffBean=new VillageTariffBean();
			villageTariffBean.setVillageId(villageDetailsForm.getVillageId());			
			
			VillageSewerageBean villageSewerageBean = new VillageSewerageBean();
			villageSewerageBean.setVillageId(villageDetailsForm.getVillageId());
			
			VillageNCPCStatusBean villageNCPCStatusBean = new VillageNCPCStatusBean();
			villageNCPCStatusBean.setVillageId(villageDetailsForm.getVillageId());
			
			villageDetailsBean.setVillageConnectionBean(villageConnectionDao.findVillageConnection(villageConnectionBean, statusList));
			villageDetailsBean.setVillagePopulationBean(villagePopulationDao.findVillagePopulation(villagePopulationBean, statusList));
			villageDetailsBean.setVillageHouseHoldBean(villageHouseHoldDao.findVillageHouseHold(villageHouseHoldBean, statusList));
			villageDetailsBean.setVillageTariffBean(villageTariffDao.findVillageTariff(villageTariffBean, statusList));
			villageDetailsBean.setVillageSewerageBean(villageSewerageDao.findVillageSewerage(villageSewerageBean, statusList));
			villageDetailsBean.setVillageNCPCStatusBean(villageNCPCStatusDao.findVillageNCPCStatus(villageNCPCStatusBean, statusList));
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return villageDetailsBean;
	
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveVillageDetails(VillageDetailsForm villageDetailsForm,
			MISSessionBean misSessionBean) throws MISException{
		try {
			Collection<VillageConnectionBean> villageConnectionBeans = populateVillageConnectionBeans(villageDetailsForm,misSessionBean,MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(villageConnectionBeans)){
				if(!villageConnectionDao.saveVillageConnection(villageConnectionBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Connection Details  not saved for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
			
			
			Collection<VillagePopulationBean> villagePopulationBeans = populateVillagePopulationBeans(villageDetailsForm,misSessionBean,MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(villagePopulationBeans)){
				if(!villagePopulationDao.saveVillagePopulation(villagePopulationBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Population Details  not saved for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
				
			
			Collection<VillageTariffBean> villageTariffBeans = populateVillageTariffBeans(villageDetailsForm,misSessionBean,MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(villageTariffBeans)){
				if(!villageTariffDao.saveVillageTariff(villageTariffBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Tariff Details  not saved for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
			
			Collection<VillageHouseHoldBean> villageHouseHoldBeans = populateVillageHousHoldBeans(villageDetailsForm,misSessionBean,MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(villageHouseHoldBeans)){
				if(!villageHouseHoldDao.saveVillageHouseHold(villageHouseHoldBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Household Details  not saved for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
			
			Collection<VillageSewerageBean> villageSewerageBeans = populateVillageSewerageBeans(villageDetailsForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(villageSewerageBeans)){
				if(!villageSewerageDao.saveVillageSewerage(villageSewerageBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Sewerage Details  not saved for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
			
			Collection<VillageNCPCStatusBean> villageNCPCStatusBeans = populateVillageNCPCStatusBeans(villageDetailsForm, misSessionBean,  MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(villageNCPCStatusBeans)){
				if(!villageNCPCStatusDao.saveVillageNCPCStatus(villageNCPCStatusBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village NC PC Details  not saved for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw e;
					
		}
		
		
		return true;
	}

	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateVillageDetails(VillageDetailsForm villageDetailsForm,
			MISSessionBean misSessionBean) throws MISException {
		System.out.println("IN Update BO");
		try {
			Collection<VillageConnectionBean> villageConnectionBeans = populateVillageConnectionBeans(villageDetailsForm,misSessionBean,MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(villageConnectionBeans)){
				if(!villageConnectionDao.saveOrUpdateVillageConnection(villageConnectionBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Connection Details  not updated for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
			
			
			Collection<VillagePopulationBean> villagePopulationBeans = populateVillagePopulationBeans(villageDetailsForm,misSessionBean,MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(villagePopulationBeans)){
				if(!villagePopulationDao.saveOrUpdateVillagePopulation(villagePopulationBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Population Details  not updated for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
				
			
			Collection<VillageTariffBean> villageTariffBeans = populateVillageTariffBeans(villageDetailsForm,misSessionBean,MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(villageTariffBeans)){
				if(!villageTariffDao.saveOrUpdateVillageTariff(villageTariffBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Tariff Details  not updated for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
			
			Collection<VillageHouseHoldBean> villageHouseHoldBeans = populateVillageHousHoldBeans(villageDetailsForm,misSessionBean,MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(villageHouseHoldBeans)){
				if(!villageHouseHoldDao.saveOrUpdateVillageHouseHold(villageHouseHoldBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Household Details  not updated for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
			
			Collection<VillageSewerageBean> villageSewerageBeans = populateVillageSewerageBeans(villageDetailsForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(villageSewerageBeans)){
				if(!villageSewerageDao.saveVillageSewerage(villageSewerageBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Sewerage Details  not saved for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
			
		Collection<VillageNCPCStatusBean> villageNCPCStatusBeans = populateVillageNCPCStatusBeans(villageDetailsForm, misSessionBean,  MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(villageNCPCStatusBeans)){
				if(!villageNCPCStatusDao.saveVillageNCPCStatus(villageNCPCStatusBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village NC PC Details  not saved for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw e;
					
		}
		
		
		return true;
	}

	/*@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteVillageDetails(VillageDetailsForm villageDetailsForm,
			MISSessionBean misSessionBean) throws MISException {
		System.out.println("IN Delete BO");
		
		try {
			Collection<VillageConnectionBean> villageConnectionBeans = populateVillageConnectionBeans(villageDetailsForm,misSessionBean,MISConstants.MASTER_STATUS_DELETED);
			if(!MisUtility.ifEmpty(villageConnectionBeans)){
				if(!villageConnectionDao.saveOrUpdateVillageConnection(villageConnectionBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Connection Details  not deleted for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
			
			
			Collection<VillagePopulationBean> villagePopulationBeans = populateVillagePopulationBeans(villageDetailsForm,misSessionBean,MISConstants.MASTER_STATUS_DELETED);
			if(!MisUtility.ifEmpty(villagePopulationBeans)){
				if(!villagePopulationDao.saveOrUpdateVillagePopulation(villagePopulationBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Population Details  not deleted for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
				
			
			Collection<VillageTariffBean> villageTariffBeans = populateVillageTariffBeans(villageDetailsForm,misSessionBean,MISConstants.MASTER_STATUS_DELETED);
			if(!MisUtility.ifEmpty(villageTariffBeans)){
				if(!villageTariffDao.saveOrUpdateVillageTariff(villageTariffBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Tariff Details  not deleted for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
			
			Collection<VillageHouseHoldBean> villageHouseHoldBeans = populateVillageHousHoldBeans(villageDetailsForm,misSessionBean,MISConstants.MASTER_STATUS_DELETED);
			if(!MisUtility.ifEmpty(villageHouseHoldBeans)){
				if(!villageHouseHoldDao.saveOrUpdateVillageHouseHold(villageHouseHoldBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Household Details  not deleted for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw e;
					
		}
		
		
		return true;		
		
		
	}*/

	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postVillageDetails(VillageDetailsForm villageDetailsForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			VillageConnectionBean villageConnectionBean = new VillageConnectionBean();
			villageConnectionBean.setVillageId(villageDetailsForm.getVillageId());
			List <String> statusList = new ArrayList<String>() ;
			MISAuditBean misAuditBean = new MISAuditBean();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			Collection<VillageConnectionBean> villageConnectionBeans = villageConnectionDao.findVillageConnection(villageConnectionBean , statusList);
				for (VillageConnectionBean villageConnectionBean2 : villageConnectionBeans) {
					misAuditBean = villageConnectionBean2.getMisAuditBean();
					misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
					misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
					villageConnectionBean2.setMisAuditBean(misAuditBean);
				}
			
			if(!MisUtility.ifEmpty(villageConnectionBeans)){
				if(!villageConnectionDao.saveOrUpdateVillageConnection(villageConnectionBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Connection Details  not approved for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
			
			VillagePopulationBean villagePopulationBean = new VillagePopulationBean();
			villagePopulationBean.setVillageId(villageDetailsForm.getVillageId());
			Collection<VillagePopulationBean> villagePopulationBeans = villagePopulationDao.findVillagePopulation(villagePopulationBean, statusList);
			for (VillagePopulationBean villagePopulationBean2 : villagePopulationBeans) {
				misAuditBean = villagePopulationBean2.getMisAuditBean();
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				villagePopulationBean2.setMisAuditBean(misAuditBean);
			}
			if(!MisUtility.ifEmpty(villagePopulationBeans)){
				if(!villagePopulationDao.saveOrUpdateVillagePopulation(villagePopulationBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Population Details  not approved for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
				
			
			VillageTariffBean villageTariffBean = new VillageTariffBean();
			villageTariffBean.setVillageId(villageDetailsForm.getVillageId());
			Collection<VillageTariffBean> villageTariffBeans = villageTariffDao.findVillageTariff(villageTariffBean, statusList);
			for (VillageTariffBean villageTariffBean2 : villageTariffBeans) {
				misAuditBean = villageTariffBean2.getMisAuditBean();
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				villageTariffBean2.setMisAuditBean(misAuditBean);
			}
			if(!MisUtility.ifEmpty(villageTariffBeans)){
				if(!villageTariffDao.saveOrUpdateVillageTariff(villageTariffBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Tariff Details  not approved for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
			
			VillageHouseHoldBean villageHouseHoldBean = new VillageHouseHoldBean();
			villageHouseHoldBean.setVillageId(villageDetailsForm.getVillageId());
			Collection<VillageHouseHoldBean> villageHouseHoldBeans = villageHouseHoldDao.findVillageHouseHold(villageHouseHoldBean, statusList)	;
			for (VillageHouseHoldBean villageHouseHoldBean2 : villageHouseHoldBeans) {
				misAuditBean = villageHouseHoldBean2.getMisAuditBean();
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				villageHouseHoldBean2.setMisAuditBean(misAuditBean);
			}
			if(!MisUtility.ifEmpty(villageHouseHoldBeans)){
				if(!villageHouseHoldDao.saveOrUpdateVillageHouseHold(villageHouseHoldBeans)){
					throw new MISException(MISExceptionCodes.MIS003, "Village Household Details  not approved for the Village Id : "+villageDetailsForm.getVillageId());
				}
			}
			
			/*VillageSewerage post functionality is missing as Post function not to be used in Village Detail transaction */
		
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw e;
					
		}
		return true;
	}
	

	private Collection<VillageConnectionBean> populateVillageConnectionBeans(VillageDetailsForm villageDetailsForm,	MISSessionBean misSessionBean, String status) throws MISException {
		System.out.println("Populate");
		List<VillageConnectionBean> villageConnectionBeans;
		
			MISAuditBean misAuditBean = new MISAuditBean();
			if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			} else{
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			}
			misAuditBean.setStatus(status);
			
			villageConnectionBeans = new ArrayList<VillageConnectionBean>();
			VillageConnectionBean villageConnectionBean = null;
		try {
			Collection<VillageConnectionGridBean> addedConnectionGridBeans = villageDetailsForm.getWaterConnectionGrid().getAddedData();
			if(!MisUtility.ifEmpty(addedConnectionGridBeans)){
				System.out.println("inside add====");
				for (VillageConnectionGridBean villageConnectionGridBean : addedConnectionGridBeans) {
					villageConnectionBean =new VillageConnectionBean();
					villageConnectionBean.setAsOnDate(MisUtility.convertStringToDate(villageConnectionGridBean.getAsOnDate()));
					villageConnectionBean.setMisAuditBean(misAuditBean);
					villageConnectionBean.setNoOfStandpost(villageConnectionGridBean.getNoOfStandpost());
					villageConnectionBean.setNoOfWaterConnection(villageConnectionGridBean.getNoOfWaterConnection());
					villageConnectionBean.setVillageId(villageDetailsForm.getVillageId());
					villageConnectionBean.setConnectionId(villageConnectionGridBean.getConnectionId());
					villageConnectionBeans.add(villageConnectionBean);
					
				}
				
			}
			Collection<VillageConnectionGridBean> modifiedConnectionGridBeans =  villageDetailsForm.getWaterConnectionGrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedConnectionGridBeans)){
				System.out.println("inside update====");
				for (VillageConnectionGridBean villageConnectionGridBean : modifiedConnectionGridBeans) {
					
					villageConnectionBean =new VillageConnectionBean();
					villageConnectionBean.setAsOnDate(MisUtility.convertStringToDate(villageConnectionGridBean.getAsOnDate()));
					villageConnectionBean.setMisAuditBean(misAuditBean);
					villageConnectionBean.setNoOfStandpost(villageConnectionGridBean.getNoOfStandpost());
					villageConnectionBean.setNoOfWaterConnection(villageConnectionGridBean.getNoOfWaterConnection());
					villageConnectionBean.setVillageId(villageDetailsForm.getVillageId());
					villageConnectionBean.setConnectionId(villageConnectionGridBean.getConnectionId());
					villageConnectionBeans.add(villageConnectionBean);
					
				}
				
			}
			
			Collection<VillageConnectionGridBean> deletedConnectionGridBeans =  villageDetailsForm.getWaterConnectionGrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedConnectionGridBeans)){
				System.out.println("inside delete====");
				for (VillageConnectionGridBean villageConnectionGridBean : deletedConnectionGridBeans) {
					
					villageConnectionBean =new VillageConnectionBean();
					villageConnectionBean.setAsOnDate(MisUtility.convertStringToDate(villageConnectionGridBean.getAsOnDate()));
					MISAuditBean misAuditBean2 = new MISAuditBean();
					misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
					misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
					villageConnectionBean.setMisAuditBean(misAuditBean2);
					villageConnectionBean.setNoOfStandpost(villageConnectionGridBean.getNoOfStandpost());
					villageConnectionBean.setNoOfWaterConnection(villageConnectionGridBean.getNoOfWaterConnection());
					villageConnectionBean.setVillageId(villageDetailsForm.getVillageId());
					villageConnectionBean.setConnectionId(villageConnectionGridBean.getConnectionId());
					villageConnectionBeans.add(villageConnectionBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return villageConnectionBeans;
	}

	private Collection<VillagePopulationBean> populateVillagePopulationBeans(VillageDetailsForm villageDetailsForm,	MISSessionBean misSessionBean, String status) throws MISException {	
	
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		
		List<VillagePopulationBean> villagePopulationBeans = new ArrayList<VillagePopulationBean>();
		VillagePopulationBean villagePopulationBean = null;
		
		try {
			Collection<VillagePopulationGridBean> addedPopulationGridBeans = villageDetailsForm.getPopulationGrid().getAddedData();
			if(!MisUtility.ifEmpty(addedPopulationGridBeans)){
				for (VillagePopulationGridBean villagePopulationGridBean : addedPopulationGridBeans) {
					villagePopulationBean =new VillagePopulationBean();
					villagePopulationBean.setAsOnDate(MisUtility.convertStringToDate(villagePopulationGridBean.getAsOnDate()));
					villagePopulationBean.setMisAuditBean(misAuditBean);
					villagePopulationBean.setGenPopulation(villagePopulationGridBean.getGenPopulation());
					villagePopulationBean.setScPopulation(villagePopulationGridBean.getScPopulation());
					villagePopulationBean.setStPopulation(villagePopulationGridBean.getStPopulation());
					villagePopulationBean.setPopulationYear(villagePopulationGridBean.getPopulationYear());				
					villagePopulationBean.setVillageId(villageDetailsForm.getVillageId());
					villagePopulationBean.setFreezedValue(MisUtility.convertStringToDate("01-04-2013"));
					villagePopulationBean.setPopulationId(villagePopulationGridBean.getPopulationId());
					villagePopulationBeans.add(villagePopulationBean);
					
				}
				
			}
			Collection<VillagePopulationGridBean> modifiedPopulationGridBeans =  villageDetailsForm.getPopulationGrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedPopulationGridBeans)){
				for (VillagePopulationGridBean villagePopulationGridBean : modifiedPopulationGridBeans) {	
				
					
					villagePopulationBean =new VillagePopulationBean();
					villagePopulationBean.setAsOnDate(MisUtility.convertStringToDate(villagePopulationGridBean.getAsOnDate()));
					villagePopulationBean.setMisAuditBean(misAuditBean);
					villagePopulationBean.setGenPopulation(villagePopulationGridBean.getGenPopulation());
					villagePopulationBean.setScPopulation(villagePopulationGridBean.getScPopulation());
					villagePopulationBean.setStPopulation(villagePopulationGridBean.getStPopulation());
					villagePopulationBean.setPopulationYear(villagePopulationGridBean.getPopulationYear());				
					villagePopulationBean.setVillageId(villageDetailsForm.getVillageId());
					villagePopulationBean.setFreezedValue(MisUtility.convertStringToDate("01-04-2013"));
					villagePopulationBean.setPopulationId(villagePopulationGridBean.getPopulationId());
					villagePopulationBeans.add(villagePopulationBean);
					
				}
				
			}
			
			Collection<VillagePopulationGridBean> deletedPopulationGridBeans =  villageDetailsForm.getPopulationGrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedPopulationGridBeans)){
				for (VillagePopulationGridBean villagePopulationGridBean : deletedPopulationGridBeans) {
					villagePopulationBean =new VillagePopulationBean();
					villagePopulationBean.setAsOnDate(MisUtility.convertStringToDate(villagePopulationGridBean.getAsOnDate()));
					MISAuditBean misAuditBean2 = new MISAuditBean();
					misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
					misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
					villagePopulationBean.setMisAuditBean(misAuditBean2);
					villagePopulationBean.setGenPopulation(villagePopulationGridBean.getGenPopulation());
					villagePopulationBean.setScPopulation(villagePopulationGridBean.getScPopulation());
					villagePopulationBean.setStPopulation(villagePopulationGridBean.getStPopulation());
					villagePopulationBean.setPopulationYear(villagePopulationGridBean.getPopulationYear());				
					villagePopulationBean.setVillageId(villageDetailsForm.getVillageId());
					villagePopulationBean.setPopulationId(villagePopulationGridBean.getPopulationId());
					villagePopulationBeans.add(villagePopulationBean);
				}
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
	return villagePopulationBeans;	
}
	
	private Collection<VillageNCPCStatusBean> populateVillageNCPCStatusBeans(VillageDetailsForm villageDetailsForm,	MISSessionBean misSessionBean, String status) throws MISException {	
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		
		List<VillageNCPCStatusBean> villageNCPCStatusBeans = new ArrayList<VillageNCPCStatusBean>();
		
		
		try {
			Collection<VillageNCPCStatusBean> addedVillageNCPCStatusBeans = villageDetailsForm.getNcPcStatusDatagrid().getAddedData();
			if(!MisUtility.ifEmpty(addedVillageNCPCStatusBeans)){
				for (VillageNCPCStatusBean villageNCPCStatusBean : addedVillageNCPCStatusBeans) {
					villageNCPCStatusBean.setVillageId(villageDetailsForm.getVillageId());
					villageNCPCStatusBean.setMisAuditBean(misAuditBean);
					villageNCPCStatusBeans.add(villageNCPCStatusBean);
					
				}
				
			}
			Collection<VillageNCPCStatusBean> modifiedVillageNCPCStatusBeans = villageDetailsForm.getNcPcStatusDatagrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedVillageNCPCStatusBeans)){
				for (VillageNCPCStatusBean villageNCPCStatusBean : modifiedVillageNCPCStatusBeans) {
					villageNCPCStatusBean.setVillageId(villageDetailsForm.getVillageId());
					villageNCPCStatusBean.setMisAuditBean(misAuditBean);
					villageNCPCStatusBeans.add(villageNCPCStatusBean);
				}
				
			}
			
			Collection<VillageNCPCStatusBean> deletedVillageNCPCStatusBeans = villageDetailsForm.getNcPcStatusDatagrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedVillageNCPCStatusBeans)){
				for (VillageNCPCStatusBean villageNCPCStatusBean : deletedVillageNCPCStatusBeans) {
					villageNCPCStatusBean.setVillageId(villageDetailsForm.getVillageId());
					MISAuditBean misAuditBean2 = new MISAuditBean();
					misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
					misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
					villageNCPCStatusBean.setMisAuditBean(misAuditBean2);
					villageNCPCStatusBeans.add(villageNCPCStatusBean);
				}
				
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
	return villageNCPCStatusBeans;	
}
	
	private Collection<VillageHouseHoldBean> populateVillageHousHoldBeans(VillageDetailsForm villageDetailsForm,	MISSessionBean misSessionBean, String status) throws MISException {	
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		
		List<VillageHouseHoldBean> villageHouseHoldBeans = new ArrayList<VillageHouseHoldBean>();
		VillageHouseHoldBean villageHouseHoldBean = null;
		
		try {
			Collection<VillageHouseHoldGridBean> addedHouseHoldGridBeans = villageDetailsForm.getHouseHoldGrid().getAddedData();
			if(!MisUtility.ifEmpty(addedHouseHoldGridBeans)){
				for (VillageHouseHoldGridBean villageHouseHoldGridBean : addedHouseHoldGridBeans) {
					villageHouseHoldBean =new VillageHouseHoldBean();
					villageHouseHoldBean.setAsOnDate(MisUtility.convertStringToDate(villageHouseHoldGridBean.getAsOnDate()));
					villageHouseHoldBean.setMisAuditBean(misAuditBean);
					villageHouseHoldBean.setGcHouseholds(villageHouseHoldGridBean.getGcHouseholds());
					villageHouseHoldBean.setScHouseholds(villageHouseHoldGridBean.getScHouseholds());				
					villageHouseHoldBean.setVillageId(villageDetailsForm.getVillageId());
					villageHouseHoldBean.setHouseholdId(villageHouseHoldGridBean.getHouseholdId());
					villageHouseHoldBean.setFreezedValue(MisUtility.convertStringToDate("01-04-2013"));
					villageHouseHoldBeans.add(villageHouseHoldBean);
					
				}
				
			}
			Collection<VillageHouseHoldGridBean> modifiedHouseHoldGridBeans = villageDetailsForm.getHouseHoldGrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedHouseHoldGridBeans)){
				for (VillageHouseHoldGridBean villageHouseHoldGridBean : modifiedHouseHoldGridBeans) {	
					villageHouseHoldBean =new VillageHouseHoldBean();
					villageHouseHoldBean.setAsOnDate(MisUtility.convertStringToDate(villageHouseHoldGridBean.getAsOnDate()));
					villageHouseHoldBean.setMisAuditBean(misAuditBean);
					villageHouseHoldBean.setGcHouseholds(villageHouseHoldGridBean.getGcHouseholds());
					villageHouseHoldBean.setScHouseholds(villageHouseHoldGridBean.getScHouseholds());				
					villageHouseHoldBean.setVillageId(villageDetailsForm.getVillageId());
					villageHouseHoldBean.setHouseholdId(villageHouseHoldGridBean.getHouseholdId());
					villageHouseHoldBean.setFreezedValue(MisUtility.convertStringToDate("01-04-2013"));
					villageHouseHoldBeans.add(villageHouseHoldBean);
					
				}
				
			}
			
			Collection<VillageHouseHoldGridBean> deletedHouseHoldGridBeans = villageDetailsForm.getHouseHoldGrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedHouseHoldGridBeans)){
				for (VillageHouseHoldGridBean villageHouseHoldGridBean : deletedHouseHoldGridBeans) {		
					villageHouseHoldBean =new VillageHouseHoldBean();
					villageHouseHoldBean.setAsOnDate(MisUtility.convertStringToDate(villageHouseHoldGridBean.getAsOnDate()));
					MISAuditBean misAuditBean2 = new MISAuditBean();
					misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
					misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
					villageHouseHoldBean.setMisAuditBean(misAuditBean2);
					villageHouseHoldBean.setGcHouseholds(villageHouseHoldGridBean.getGcHouseholds());
					villageHouseHoldBean.setScHouseholds(villageHouseHoldGridBean.getScHouseholds());				
					villageHouseHoldBean.setVillageId(villageDetailsForm.getVillageId());
					villageHouseHoldBean.setHouseholdId(villageHouseHoldGridBean.getHouseholdId());
					villageHouseHoldBeans.add(villageHouseHoldBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	return villageHouseHoldBeans;	
}	

private Collection<VillageTariffBean> populateVillageTariffBeans(VillageDetailsForm villageDetailsForm,	MISSessionBean misSessionBean, String status) throws MISException {	
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		
		List<VillageTariffBean> villageTariffBeans = new ArrayList<VillageTariffBean>();
		VillageTariffBean villageTariffBean = null;
		
		try {
			Collection<VillageTariffGridBean> addedTariffGridBeans = villageDetailsForm.getTariffRateGrid().getAddedData();
			if(!MisUtility.ifEmpty(addedTariffGridBeans)){
				for (VillageTariffGridBean villageTariffGridBean : addedTariffGridBeans) {
					villageTariffBean =new VillageTariffBean();
					villageTariffBean.setAsOnDate(MisUtility.convertStringToDate(villageTariffGridBean.getAsOnDate()));
					villageTariffBean.setMisAuditBean(misAuditBean);
					villageTariffBean.setTariffRate(villageTariffGridBean.getTariffRate());			
					villageTariffBean.setVillageId(villageDetailsForm.getVillageId());
					villageTariffBean.setTariffId(villageTariffGridBean.getTariffId());
					villageTariffBeans.add(villageTariffBean);
					
				}
				
			}
			Collection<VillageTariffGridBean> modifiedTariffGridBeans = villageDetailsForm.getTariffRateGrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedTariffGridBeans)){
				for (VillageTariffGridBean villageTariffGridBean : modifiedTariffGridBeans) {
					villageTariffBean =new VillageTariffBean();
					villageTariffBean.setAsOnDate(MisUtility.convertStringToDate(villageTariffGridBean.getAsOnDate()));
					villageTariffBean.setMisAuditBean(misAuditBean);
					villageTariffBean.setTariffRate(villageTariffGridBean.getTariffRate());			
					villageTariffBean.setVillageId(villageDetailsForm.getVillageId());
					villageTariffBean.setTariffId(villageTariffGridBean.getTariffId());
					villageTariffBeans.add(villageTariffBean);
					
				}
				
			}
			
			Collection<VillageTariffGridBean> deletedTariffGridBeans = villageDetailsForm.getTariffRateGrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedTariffGridBeans)){
				for (VillageTariffGridBean villageTariffGridBean : deletedTariffGridBeans) {
					villageTariffBean =new VillageTariffBean();
					villageTariffBean.setAsOnDate(MisUtility.convertStringToDate(villageTariffGridBean.getAsOnDate()));
					MISAuditBean misAuditBean2 = new MISAuditBean();
					misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
					misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
					villageTariffBean.setMisAuditBean(misAuditBean2);
					villageTariffBean.setTariffRate(villageTariffGridBean.getTariffRate());			
					villageTariffBean.setVillageId(villageDetailsForm.getVillageId());
					villageTariffBean.setTariffId(villageTariffGridBean.getTariffId());
					villageTariffBeans.add(villageTariffBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
	return villageTariffBeans;	
}	


private Collection<VillageSewerageBean> populateVillageSewerageBeans(VillageDetailsForm villageDetailsForm,	MISSessionBean misSessionBean, String status) throws MISException {	
	
	MISAuditBean misAuditBean = new MISAuditBean();
	if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
		misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
		misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
	} else{
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
	}
	misAuditBean.setStatus(status);
	
	List<VillageSewerageBean> villageSewerageBeans = new ArrayList<VillageSewerageBean>();
	VillageSewerageBean villageSewerageBean = null;
	
	try {
		Collection<VillageSewerageGridBean> addedSewerageGridBeans = villageDetailsForm.getSewerageGrid().getAddedData();
		if(!MisUtility.ifEmpty(addedSewerageGridBeans)){
			for (VillageSewerageGridBean villageSewerageGridBean : addedSewerageGridBeans) {
				villageSewerageBean =new VillageSewerageBean();
				villageSewerageBean.setAsOnDate(MisUtility.convertStringToDate(villageSewerageGridBean.getAsOnDate()));
				villageSewerageBean.setMisAuditBean(misAuditBean);
				villageSewerageBean.setTariffRate(villageSewerageGridBean.getTariffRate());			
				villageSewerageBean.setVillageId(villageDetailsForm.getVillageId());
				villageSewerageBean.setSewerageId(villageSewerageGridBean.getSewerageId());
				villageSewerageBean.setSewConnection(villageSewerageGridBean.getSewConnection());
				villageSewerageBeans.add(villageSewerageBean);
				
			}
			
		}
		Collection<VillageSewerageGridBean> modifiedSewerageGridBeans = villageDetailsForm.getSewerageGrid().getModifiedData();
		if(!MisUtility.ifEmpty(modifiedSewerageGridBeans)){
			for (VillageSewerageGridBean villageSewerageGridBean : modifiedSewerageGridBeans) {
				villageSewerageBean =new VillageSewerageBean();
				villageSewerageBean.setAsOnDate(MisUtility.convertStringToDate(villageSewerageGridBean.getAsOnDate()));
				villageSewerageBean.setMisAuditBean(misAuditBean);
				villageSewerageBean.setTariffRate(villageSewerageGridBean.getTariffRate());			
				villageSewerageBean.setVillageId(villageDetailsForm.getVillageId());
				villageSewerageBean.setSewerageId(villageSewerageGridBean.getSewerageId());
				villageSewerageBean.setSewConnection(villageSewerageGridBean.getSewConnection());
				villageSewerageBeans.add(villageSewerageBean);
				
			}
			
		}
		
		Collection<VillageSewerageGridBean> deletedSewerageGridBeans = villageDetailsForm.getSewerageGrid().getDeletedData();
		if(!MisUtility.ifEmpty(deletedSewerageGridBeans)){
			for (VillageSewerageGridBean villageSewerageGridBean : deletedSewerageGridBeans) {
				villageSewerageBean =new VillageSewerageBean();
				villageSewerageBean.setAsOnDate(MisUtility.convertStringToDate(villageSewerageGridBean.getAsOnDate()));
				MISAuditBean misAuditBean2 = new MISAuditBean();
				misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
				villageSewerageBean.setMisAuditBean(misAuditBean2);
				villageSewerageBean.setTariffRate(villageSewerageGridBean.getTariffRate());			
				villageSewerageBean.setVillageId(villageDetailsForm.getVillageId());
				villageSewerageBean.setSewerageId(villageSewerageGridBean.getSewerageId());
				villageSewerageBean.setSewConnection(villageSewerageGridBean.getSewConnection());
				villageSewerageBeans.add(villageSewerageBean);;
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
		log.error(e.getLocalizedMessage(),e);
	}
return villageSewerageBeans;	
}	
}