/**
 * 
 */
package com.prwss.mis.pmm.village.phase;

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
import com.prwss.mis.pmm.village.phase.dao.VillagePhaseStatusBean;
import com.prwss.mis.pmm.village.phase.dao.VillagePhaseStatusDao;
import com.prwss.mis.pmm.village.phase.struts.VillagePhaseStatusForm;

/**
 * @author pjha
 *
 */
public class VillagePhaseStatusBOImpl implements VillagePhaseStatusBO {

	private Logger log = Logger.getLogger(VillagePhaseStatusBOImpl.class);
	private VillagePhaseStatusDao villagePhaseStatusDao;
	
	public void setVillagePhaseStatusDao(VillagePhaseStatusDao villagePhaseStatusDao) {
		this.villagePhaseStatusDao = villagePhaseStatusDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public  List<VillagePhaseStatusBean> findVillagePhaseStatus(
			VillagePhaseStatusForm villagePhaseStatusForm,
			List<String> statusList) throws MISException {
		System.out.println("IN BOIMPL FIND ");
		List<VillagePhaseStatusBean> villagePhaseStatusBeans  = new ArrayList<VillagePhaseStatusBean>();
		
		try {
			VillagePhaseStatusBean villagePhaseStatusBean = new VillagePhaseStatusBean();
			
			villagePhaseStatusBean.setSchemeId(villagePhaseStatusForm.getSchemeId());
			villagePhaseStatusBean.setLocationId(villagePhaseStatusForm.getLocationId());
			villagePhaseStatusBeans = villagePhaseStatusDao.findVillagePhaseStatus(villagePhaseStatusBean, statusList);
		
	} catch (DataAccessException e) {
		log.error(e.getLocalizedMessage(),e);
		throw e;
	}
	return villagePhaseStatusBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveVillagePhaseStatus(
			VillagePhaseStatusForm villagePhaseStatusForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			VillagePhaseStatusBean villagePhaseStatusFindBean = new VillagePhaseStatusBean();
			villagePhaseStatusFindBean.setSchemeId(villagePhaseStatusForm.getSchemeId());
			List<String> findStatusList = new ArrayList<String>();
			findStatusList.add(MISConstants.MASTER_STATUS_APPROVED);
			findStatusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			List<VillagePhaseStatusBean> villagePhaseStatusFindBeans= villagePhaseStatusDao.findVillagePhaseStatus(villagePhaseStatusFindBean, findStatusList);
			if(!MisUtility.ifEmpty(villagePhaseStatusFindBeans)){
				throw new MISException(MISExceptionCodes.MIS001,"Scheme Code : "+villagePhaseStatusForm.getSchemeId()+" already exists.");
			}
			VillagePhaseStatusBean villagePhaseStatusBean = populateVillagePhaseStatusBean(villagePhaseStatusForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			villagePhaseStatusBean.setMisAuditBean(misAuditBean);

			status = villagePhaseStatusDao.saveVillagePhaseStatus(villagePhaseStatusBean);



			if(!status){
				log.error(villagePhaseStatusBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to save villagePhaseStatus details");
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

		return true;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateVillagePhaseStatus(
			VillagePhaseStatusForm villagePhaseStatusForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			VillagePhaseStatusBean villagePhaseStatusBean = populateVillagePhaseStatusBean(villagePhaseStatusForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			villagePhaseStatusBean.setMisAuditBean(misAuditBean);

			status = villagePhaseStatusDao.updateVillagePhaseStatus(villagePhaseStatusBean);



			if(!status){
				log.error(villagePhaseStatusBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Update villagePhaseStatus details");
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
	public boolean deleteVillagePhaseStatus(
			VillagePhaseStatusForm villagePhaseStatusForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			VillagePhaseStatusBean villagePhaseStatusBean = populateVillagePhaseStatusBean(villagePhaseStatusForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			villagePhaseStatusBean.setMisAuditBean(misAuditBean);

			status = villagePhaseStatusDao.updateVillagePhaseStatus(villagePhaseStatusBean);



			if(!status){
				log.error(villagePhaseStatusBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Update villagePhaseStatus details");
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
	public boolean postVillagePhaseStatus(
			VillagePhaseStatusForm villagePhaseStatusForm,
			MISSessionBean misSessionBean) throws MISException {
try {
			
			
			
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status = false;
			VillagePhaseStatusBean villagePhaseStatusBean= new VillagePhaseStatusBean();
			villagePhaseStatusBean.setSchemeId(villagePhaseStatusForm.getSchemeId());
			villagePhaseStatusBean= villagePhaseStatusDao.findVillagePhaseStatus(villagePhaseStatusBean,statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = villagePhaseStatusBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			villagePhaseStatusBean.setMisAuditBean(misAuditBean);			
		
			
			status = villagePhaseStatusDao.saveVillagePhaseStatus(villagePhaseStatusBean);

			if(!status){
				log.error(villagePhaseStatusBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Post village phase status details");
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

	
private VillagePhaseStatusBean populateVillagePhaseStatusBean(VillagePhaseStatusForm villagePhaseStatusForm ){
		
		
		VillagePhaseStatusBean villagePhaseStatusBean = new VillagePhaseStatusBean();
		try {
			villagePhaseStatusBean.setSchemeId(villagePhaseStatusForm.getSchemeId());
			villagePhaseStatusBean.setPrePlanningDate(MisUtility.convertStringToDate(villagePhaseStatusForm.getPrePlanningDate()));
			villagePhaseStatusBean.setPlanningDate(MisUtility.convertStringToDate(villagePhaseStatusForm.getPlanningDate()));
			villagePhaseStatusBean.setImplementationDate(MisUtility.convertStringToDate(villagePhaseStatusForm.getImplementationDate()));
			villagePhaseStatusBean.setOmDate(MisUtility.convertStringToDate(villagePhaseStatusForm.getOmDate()));
			villagePhaseStatusBean.setLocationId(villagePhaseStatusForm.getLocationId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return villagePhaseStatusBean;
		
}

}

