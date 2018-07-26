/**
 * 
 */
package com.prwss.mis.masters.committee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.prwss.mis.masters.committee.dao.CommitteeBankBean;
import com.prwss.mis.masters.committee.dao.CommitteeBankDao;
import com.prwss.mis.masters.committee.dao.CommitteeBean;
import com.prwss.mis.masters.committee.dao.CommitteeDao;
import com.prwss.mis.masters.committee.dao.CommitteeMemberBean;
import com.prwss.mis.masters.committee.dao.CommitteeMemberDao;
import com.prwss.mis.masters.committee.struts.CommitteeForm;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author pjha
 *
 */
public class CommitteeBOImpl implements CommitteeBO {
	
	private Logger log = Logger.getLogger(CommitteeBOImpl.class);
	private CommitteeBankDao committeeBankDao ;
	private CommitteeMemberDao committeeMemberDao;
	private CommitteeDao committeeDao;
	private LocationDao locationDao;
	
	
	
	
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setCommitteeDao(CommitteeDao committeeDao) {
		this.committeeDao = committeeDao;
	}

	public void setCommitteeBankDao(CommitteeBankDao committeeBankDao) {
		this.committeeBankDao = committeeBankDao;
	}

	public void setCommitteeMemberDao(CommitteeMemberDao committeeMemberDao) {
		this.committeeMemberDao = committeeMemberDao;
	}

	@Override
	public List<CommitteeBean> findCommittee(CommitteeForm committeeForm,
			List<String> statusList) throws MISException {
		List<CommitteeBean> committeeBeans = new  ArrayList<CommitteeBean>();
		try {
			
			CommitteeBean committeeBean = new CommitteeBean();
			
//			committeeBean.setCommitteeId(committeeForm.getCommitteeId());
		
			
			if(MisUtility.ifEmpty(committeeForm.getSchemeId()))
				committeeBean.setSchemeId(committeeForm.getSchemeId());
			
			if(MisUtility.ifEmpty(committeeForm.getVillageId()))
				committeeBean.setVillageId(committeeForm.getVillageId());
			
			if(MisUtility.ifEmpty(committeeForm.getsLCStatus()))
				committeeBean.setsLCStatus(committeeForm.getsLCStatus());
			
			if(MisUtility.ifEmpty(committeeForm.getLocationId()))
				committeeBean.setLocationId(committeeForm.getLocationId());
			
			
			
			
			committeeBeans= committeeDao.findCommittee(committeeBean, statusList);
			Set<CommitteeBankBean> committeeBankBeans = null;
			Iterator<CommitteeBankBean> iteratorCommitteeBankBean = null;
			CommitteeBankBean  committeeBankBean = null;
			if(!MisUtility.ifEmpty(committeeBeans)){

				for (CommitteeBean bean : committeeBeans) {

				
					committeeBankBeans = bean.getCommitteeBankBeans();
					if(!MisUtility.ifEmpty(committeeBankBeans)){
						iteratorCommitteeBankBean = committeeBankBeans.iterator();					
						while(iteratorCommitteeBankBean.hasNext()){
							committeeBankBean = iteratorCommitteeBankBean.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(committeeBankBean.getMisAuditBean().getStatus())){
								System.out.println("---------------removed");
								iteratorCommitteeBankBean.remove();								
							}
						}
					}
				}
			}
				Set<CommitteeMemberBean> committeeMemberBeans = null;
				Iterator<CommitteeMemberBean> iteratorCommitteeMemberBean = null;
				CommitteeMemberBean  committeeMemberBean = null;
				if(!MisUtility.ifEmpty(committeeBeans)){

					for (CommitteeBean bean : committeeBeans) {

					
						committeeMemberBeans = bean.getCommitteeMemberBeans();
						if(!MisUtility.ifEmpty(committeeMemberBeans)){
							iteratorCommitteeMemberBean = committeeMemberBeans.iterator();					
							while(iteratorCommitteeMemberBean.hasNext()){
								committeeMemberBean = iteratorCommitteeMemberBean.next();
								if(MISConstants.MASTER_STATUS_DELETED.equals(committeeMemberBean.getMisAuditBean().getStatus())){
									iteratorCommitteeMemberBean.remove();									
								}
							}
						}
					}
							
				}
		
				
		
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
			throw e;
		}
		 
		return committeeBeans;
	}

		
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveCommittee(CommitteeForm committeeForm,
			MISSessionBean misSessionBean) throws MISException {
		long committeeId = 0;
		try {
			System.out.println("In saveCommittee1  ");
			CommitteeBean committeeFindBean = new CommitteeBean();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			
			if(committeeForm.getsLCStatus().equalsIgnoreCase("SLC")){
				committeeFindBean.setSchemeId(committeeForm.getSchemeId());
				committeeFindBean.setsLCStatus(committeeForm.getsLCStatus());
				
				List<CommitteeBean> committeeFindBeans = committeeDao.findCommittee(committeeFindBean, statusList);
				System.out.println("committeeFindBeans"+committeeFindBeans);
				if(!MisUtility.ifEmpty(committeeFindBeans)){
					throw new MISException(MISExceptionCodes.MIS011,"Scheme Level Committee already exist for this scheme code --> "+committeeForm.getSchemeId());
				}
			}
			
			if(committeeForm.getsLCStatus().equalsIgnoreCase("GPWSC")){
				committeeFindBean.setVillageId(committeeForm.getVillageId());
				committeeFindBean.setsLCStatus(committeeForm.getsLCStatus());
				committeeFindBean.setSchemeId(committeeForm.getSchemeId());
				List<CommitteeBean> committeeFindBeans = committeeDao.findCommittee(committeeFindBean, statusList);
				System.out.println("committeeFindBeans"+committeeFindBeans);
				if(!MisUtility.ifEmpty(committeeFindBeans)){
					throw new MISException(MISExceptionCodes.MIS011,"GPWSC already exist for this village code --> "+committeeForm.getVillageId());
				}
			}
			
			
			CommitteeBean committeeBean = populateCommitteeBean(committeeForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			committeeBean.setMisAuditBean(misAuditBean);
			
			// This code sinpet is written to enter district id which is fetch from  user location
			/* ****************************************************************************** */
			LocationBean locationBean = new LocationBean();
			locationBean.setLocationId(committeeForm.getLocationId());
			locationBean = locationDao.getLocation(locationBean);
			committeeBean.setDistrictId(locationBean.getParentLocation());
			/* ****************************************************************************** */
			

			committeeId = committeeDao.saveCommittee(committeeBean);
		
			if(MisUtility.ifEmpty(committeeId)){
				List<CommitteeBankBean> committeeBankBeans = populateCommitteeBankBeans(committeeForm,committeeId,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(committeeBankBeans)){
					boolean committeeBankStatus = committeeBankDao.saveOrUpdateCommitteeBank(committeeBankBeans);
					if(!committeeBankStatus){
						log.error(committeeBankBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Committee bank details");
					}
				}

				List<CommitteeMemberBean> committeeMemberBeans = populateCommitteeMemberBeans(committeeForm,committeeId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(committeeMemberBeans)){
					System.out.println("Committee Member Beans"+committeeMemberBeans);
					boolean committeeMembeStatus = committeeMemberDao.saveOrUpdateCommitteeMember(committeeMemberBeans);
					if(!committeeMembeStatus){
						log.error(committeeMemberBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Committee members details");
					}
				}
			}
			
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return committeeId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateCommittee(CommitteeForm committeeForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			CommitteeBean committeeBean = populateCommitteeBean(committeeForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			committeeBean.setMisAuditBean(misAuditBean);
			
			// This code sinpet is written to enter district id which is fetch from  user location
			/* ****************************************************************************** */
			LocationBean locationBean = new LocationBean();
			locationBean.setLocationId(committeeForm.getLocationId());
			locationBean = locationDao.getLocation(locationBean);
			committeeBean.setDistrictId(locationBean.getParentLocation());
			/* ****************************************************************************** */

			boolean status = committeeDao.updateCommittee(committeeBean);
			long committeeId=committeeForm.getCommitteeId();
			if(status){
				List<CommitteeBankBean> committeeBankBeans = populateCommitteeBankBeans(committeeForm,committeeId,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(committeeBankBeans)){
					boolean committeeBankStatus = committeeBankDao.saveOrUpdateCommitteeBank(committeeBankBeans);
					if(!committeeBankStatus){
						log.error(committeeBankBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Committee bank details");
					}
				}

				List<CommitteeMemberBean> committeeMemberBeans = populateCommitteeMemberBeans(committeeForm,committeeId,misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(committeeMemberBeans)){
					boolean committeeMembeStatus = committeeMemberDao.saveOrUpdateCommitteeMember(committeeMemberBeans);
					if(!committeeMembeStatus){
						log.error(committeeMemberBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Committee members details");
					}
				}
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
	public boolean deleteCommittee(CommitteeForm committeeForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			CommitteeBean committeeBean = populateCommitteeBean(committeeForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			committeeBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			
			// This code sinpet is written to enter district id which is fetch from  user location
			/* ****************************************************************************** */
			LocationBean locationBean = new LocationBean();
			locationBean.setLocationId(committeeForm.getLocationId());
			locationBean = locationDao.getLocation(locationBean);
			committeeBean.setDistrictId(locationBean.getParentLocation());
			/* ****************************************************************************** */
			
			
			boolean status = committeeDao.updateCommittee(committeeBean);
			long committeeId=committeeForm.getCommitteeId();
			
			CommitteeBankBean committeeBankBean = new CommitteeBankBean();
			committeeBankBean.setCommitteeId(committeeId);
			if(status){
				List<CommitteeBankBean> committeeBankBeans = committeeBankDao.findCommitteeBank(committeeBankBean, statusList);
				if(!MisUtility.ifEmpty(committeeBankBeans)){
					for (CommitteeBankBean committeeBankBean2 : committeeBankBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						committeeBankBean2.setMisAuditBean(misAuditBean);
					}
					boolean committeeBankStatus = committeeBankDao.saveOrUpdateCommitteeBank(committeeBankBeans);
					if(!committeeBankStatus){
						log.error(committeeBankBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to delete Committee bank details");
					}
				}
         CommitteeMemberBean committeeMemberBean = new  CommitteeMemberBean();
         committeeMemberBean.setCommitteeId(committeeId);
				List<CommitteeMemberBean> committeeMemberBeans = committeeMemberDao.findCommitteeMember(committeeMemberBean, statusList);
				if(!MisUtility.ifEmpty(committeeMemberBeans)){
					for (CommitteeMemberBean committeeMemberBean2 : committeeMemberBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						committeeMemberBean2.setMisAuditBean(misAuditBean);
					}
					boolean committeeMembeStatus = committeeMemberDao.saveOrUpdateCommitteeMember(committeeMemberBeans);
					if(!committeeMembeStatus){
						log.error(committeeMemberBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to delete Committee members details");
					}
				}
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
	public boolean postCommittee(CommitteeForm committeeForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			CommitteeBean committeeBean = populateCommitteeBean(committeeForm);	
			
			
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			committeeBean = committeeDao.findCommittee(committeeBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = committeeBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			committeeBean.setMisAuditBean(misAuditBean);

			boolean status = committeeDao.updateCommittee(committeeBean);
			long committeeId = committeeForm.getCommitteeId();

			CommitteeBankBean committeeBankBean = new CommitteeBankBean();
			committeeBankBean.setCommitteeId(committeeId);
			if(status){
				List<CommitteeBankBean> committeeBankBeans = committeeBankDao.findCommitteeBank(committeeBankBean, statusList);
				if(!MisUtility.ifEmpty(committeeBankBeans)){
					for (CommitteeBankBean committeeBankBean2 : committeeBankBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						committeeBankBean2.setMisAuditBean(misAuditBean);
					}
					boolean committeeBankStatus = committeeBankDao.saveOrUpdateCommitteeBank(committeeBankBeans);
					if(!committeeBankStatus){
						log.error(committeeBankBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to delete Committee bank details");
					}
				}
				CommitteeMemberBean committeeMemberBean = new  CommitteeMemberBean();
				committeeMemberBean.setCommitteeId(committeeId);
				List<CommitteeMemberBean> committeeMemberBeans = committeeMemberDao.findCommitteeMember(committeeMemberBean, statusList);
				if(!MisUtility.ifEmpty(committeeMemberBeans)){
					for (CommitteeMemberBean committeeMemberBean2 : committeeMemberBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						committeeMemberBean2.setMisAuditBean(misAuditBean);
					}
					boolean committeeMembeStatus = committeeMemberDao.saveOrUpdateCommitteeMember(committeeMemberBeans);
					if(!committeeMembeStatus){
						log.error(committeeMemberBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to delete Committee members details");
					}
				}
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
	
private CommitteeBean populateCommitteeBean(CommitteeForm committeeForm){
		
	CommitteeBean committeeBean = new CommitteeBean();
	committeeBean.setCommitteeConstitutionDate(MisUtility.convertStringToDate(committeeForm.getCommitteeConstitutionDate()));
	committeeBean.setCommitteeId(committeeForm.getCommitteeId());
	if(MisUtility.ifEmpty(committeeForm.getSchemeId())){
	committeeBean.setSchemeId(committeeForm.getSchemeId());
	}else{
	committeeBean.setSchemeId(null);
	}
	committeeBean.setLocationId(committeeForm.getLocationId());
	committeeBean.setVillageId(committeeForm.getVillageId());
	committeeBean.setBlockId(committeeForm.getBlockId());
	committeeBean.setCommitteeName(committeeForm.getCommitteeName());
	committeeBean.setsLCStatus(committeeForm.getsLCStatus());
		
		return committeeBean;
	}

	@SuppressWarnings({ "unchecked" })
	private List<CommitteeBankBean> populateCommitteeBankBeans(CommitteeForm committeeForm,long committeeId,  MISSessionBean misSessionBean, String status){
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);

		List<CommitteeBankBean> committeeBankBeans = new ArrayList<CommitteeBankBean>();


		Datagrid committeeBankGrid = committeeForm.getCommitteeBankGrid();

		Collection<CommitteeBankBean> addedCommitteeBankBeans = committeeBankGrid.getAddedData();
		if(!MisUtility.ifEmpty(addedCommitteeBankBeans)){
			for (CommitteeBankBean committeeBankBean : addedCommitteeBankBeans) {
				committeeBankBean.setCommitteeId(committeeId);
				committeeBankBean.setMisAuditBean(misAuditBean);
				committeeBankBeans.add(committeeBankBean);
			}
		}

		Collection<CommitteeBankBean> modifiedCommitteeBankBeans = committeeBankGrid.getModifiedData();
		if(!MisUtility.ifEmpty(modifiedCommitteeBankBeans)){
			for (CommitteeBankBean committeeBankBean : modifiedCommitteeBankBeans) {
				committeeBankBean.setCommitteeId(committeeId);
				committeeBankBean.setMisAuditBean(misAuditBean);
				committeeBankBeans.add(committeeBankBean);
			}
				
			}
	
		
		Collection<CommitteeBankBean> deletedCommitteeBankBeans = committeeBankGrid.getDeletedData();
		if(!MisUtility.ifEmpty(deletedCommitteeBankBeans)){
			for (CommitteeBankBean committeeBankBean : deletedCommitteeBankBeans) {
				MISAuditBean misAuditBean2 = new MISAuditBean();
				misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
				committeeBankBean.setCommitteeId(committeeId);
				committeeBankBean.setMisAuditBean(misAuditBean2);
				committeeBankBeans.add(committeeBankBean);
			}
				
			}
		
		return committeeBankBeans;
	}
	
	@SuppressWarnings("unchecked")
	private List<CommitteeMemberBean> populateCommitteeMemberBeans(CommitteeForm committeeForm,long committeeId, MISSessionBean misSessionBean, String status){
		
		List<CommitteeMemberBean> committeeMemberBeans = new ArrayList<CommitteeMemberBean>();
	
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		//Datagrid committeeMemberGrid = committeeForm.getCommitteeMemberGrid();
		
		Collection<CommitteeMemberBean> addedCommitteeMemberGridBeans = committeeForm.getCommitteeMemberGrid().getAddedData();
		if(!MisUtility.ifEmpty(addedCommitteeMemberGridBeans)){
			for (CommitteeMemberBean committeeMemberBean : addedCommitteeMemberGridBeans) {
				committeeMemberBean.setMisAuditBean(misAuditBean);
				committeeMemberBean.setCommitteeId(committeeId);
				committeeMemberBeans.add(committeeMemberBean);
				
				
			}
		}
		
		Collection<CommitteeMemberBean> modifiedCommitteeMemberBeans = committeeForm.getCommitteeMemberGrid().getModifiedData();
		if(!MisUtility.ifEmpty(modifiedCommitteeMemberBeans)){
			for (CommitteeMemberBean committeeMemberBean : modifiedCommitteeMemberBeans) {
               committeeMemberBean.setMisAuditBean(misAuditBean);
				committeeMemberBean.setCommitteeId(committeeId);
				committeeMemberBeans.add(committeeMemberBean);
				
			}
		}
		
		Collection<CommitteeMemberBean> deletedCommitteeMemberBeans = committeeForm.getCommitteeMemberGrid().getDeletedData();
		if(!MisUtility.ifEmpty(deletedCommitteeMemberBeans)){
			for (CommitteeMemberBean committeeMemberBean : deletedCommitteeMemberBeans) {
				MISAuditBean misAuditBean2 = new MISAuditBean();
				misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
               committeeMemberBean.setMisAuditBean(misAuditBean2);
				committeeMemberBean.setCommitteeId(committeeId);
				committeeMemberBeans.add(committeeMemberBean);
			}
		}
		
		return committeeMemberBeans;
	}

	
	
}
