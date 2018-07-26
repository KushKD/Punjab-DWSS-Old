package com.prwss.mis.ccdu.cb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.ccdu.MaterialUtilizationGridBean;
import com.prwss.mis.ccdu.cb.dao.TrainingMaterialUtilizationBean;
import com.prwss.mis.ccdu.cb.dao.TrainingMaterialUtilizationDao;
import com.prwss.mis.ccdu.cb.dao.TrainingOfficerBean;
import com.prwss.mis.ccdu.cb.dao.TrainingOfficerDao;
import com.prwss.mis.ccdu.cb.dao.TrainingProgressBean;
import com.prwss.mis.ccdu.cb.dao.TrainingProgressDao;
import com.prwss.mis.ccdu.cb.struts.CBTrainingProgressForm;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.item.dao.ItemBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class TrainingProgressBOImpl implements TrainingProgressBO {
	
	private Logger log = Logger.getLogger(TrainingProgressBOImpl.class);
	
	private TrainingProgressDao trainingProgressDao;
	private TrainingMaterialUtilizationDao trainingMaterialUtilizationDao;
	private TrainingOfficerDao trainingOfficerDao;

	public void setTrainingProgressDao(TrainingProgressDao trainingProgressDao) {
		this.trainingProgressDao = trainingProgressDao;
	}

	public void setTrainingMaterialUtilizationDao(TrainingMaterialUtilizationDao trainingMaterialUtilizationDao) {
		this.trainingMaterialUtilizationDao = trainingMaterialUtilizationDao;
	}

	public void setTrainingOfficerDao(TrainingOfficerDao trainingOfficerDao) {
		this.trainingOfficerDao = trainingOfficerDao;
	}

	@Override
	public List<TrainingProgressBean> findCBTrainingProgress(CBTrainingProgressForm cbTrainingProgressForm, List<String> statusList)
			throws MISException {
		List<TrainingProgressBean> trainingProgressBeans = null;
		try {
			TrainingProgressBean trainingProgressBean = new TrainingProgressBean();
			if(MisUtility.ifEmpty(cbTrainingProgressForm.getCbProgressId())){
			trainingProgressBean.setCbProgressId(cbTrainingProgressForm.getCbProgressId());
			trainingProgressBeans = trainingProgressDao.findCBTrainingProgress(trainingProgressBean, statusList);
			
			if(!MisUtility.ifEmpty(trainingProgressBeans)){
				TrainingMaterialUtilizationBean trainingMaterialUtilizationBean = null;
				for (TrainingProgressBean trainingProgressBean1 : trainingProgressBeans) {
					Set<TrainingMaterialUtilizationBean> trainingMaterialUtilizationBeans =  trainingProgressBean1.getTrainingMaterialUtilizationBeans();
					if(!MisUtility.ifEmpty(trainingMaterialUtilizationBeans)){
						Iterator<TrainingMaterialUtilizationBean> materialIterator =  trainingMaterialUtilizationBeans.iterator();
						while(materialIterator.hasNext()){
							trainingMaterialUtilizationBean = materialIterator.next();
							if(MISConstants.MASTER_STATUS_DELETED.equalsIgnoreCase(trainingMaterialUtilizationBean.getMisAuditBean().getStatus())){
								materialIterator.remove();
							}
						}
					}
				}
				
				TrainingOfficerBean trainingOfficerBean = null;
				for (TrainingProgressBean trainingProgressBean1 : trainingProgressBeans) {
					Set<TrainingOfficerBean> trainingOfficerBeans =  trainingProgressBean1.getTrainingOfficerBeans();
					if(!MisUtility.ifEmpty(trainingOfficerBeans)){
						Iterator<TrainingOfficerBean> officerIterator =  trainingOfficerBeans.iterator();
						while(officerIterator.hasNext()){
							trainingOfficerBean = officerIterator.next();
							if(MISConstants.MASTER_STATUS_DELETED.equalsIgnoreCase(trainingOfficerBean.getMisAuditBean().getStatus())){
								officerIterator.remove();
							}
						}
					}
				}
			}
		}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return trainingProgressBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveCBTrainingProgress(CBTrainingProgressForm cbTrainingProgressForm, MISSessionBean misSessionBean)
			throws MISException {
		long cbProgressId = 0;	
		try {
			TrainingProgressBean trainingProgressBean = populateTrainingProgressBean(cbTrainingProgressForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			trainingProgressBean.setMisAuditBean(misAuditBean);
			cbProgressId = trainingProgressDao.saveCBTrainingProgress(trainingProgressBean);
			if(MisUtility.ifEmpty(cbProgressId)){
//				Long[] trainingOfficerIds = cbTrainingProgressForm.getTrainingOfficers();
//				if(MisUtility.ifEmpty(trainingOfficerIds)){
//					List<TrainingOfficerBean> trainingOfficerBeans = new ArrayList<TrainingOfficerBean>();
//					TrainingOfficerBean trainingOfficerBean = null;
//					EmployeeBean employeeBean = null;
//					for (Long trainingOfficerId : trainingOfficerIds) {
//						trainingOfficerBean = new TrainingOfficerBean();
//						trainingOfficerBean.setCbProgressId(cbProgressId);
//						employeeBean = new EmployeeBean();
//						employeeBean.setEmployeeId(trainingOfficerId);
//						trainingOfficerBean.setEmployeeBean(employeeBean);
//						trainingOfficerBeans.add(trainingOfficerBean);
//					}
//					boolean trainingOfficersStatus = trainingOfficerDao.saveOrUpdateTrainingOfficer(trainingOfficerBeans);
//
//					if(!trainingOfficersStatus){
//						throw new MISException(MISExceptionCodes.MIS003,"Failed to save Training Officer Details for Training Progress");
//					}
//				}
				List<TrainingOfficerBean> trainingOfficerBeans = populateTrainingOfficerBeans(cbTrainingProgressForm, cbProgressId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(trainingOfficerBeans)){
					boolean childStatus = trainingOfficerDao.saveOrUpdateTrainingOfficer(trainingOfficerBeans);
					if(!childStatus){
						throw new MISException(MISExceptionCodes.MIS003,"Failed to save Material Utilization Details for Training Progress");
					}
				}    
			
				List<TrainingMaterialUtilizationBean> trainingMaterialUtilizationBeans = populateTrainingMaterialUtilizationBeans(cbTrainingProgressForm, misSessionBean, cbProgressId, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(trainingMaterialUtilizationBeans)){
					boolean childStatus = trainingMaterialUtilizationDao.saveOrUpdateCBTrainingMaterialUtilization(trainingMaterialUtilizationBeans);
					if(!childStatus){
						throw new MISException(MISExceptionCodes.MIS003,"Failed to save Material Utilization Details for Training Progress");
					}
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} catch(MISException e){
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

		return cbProgressId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateCBTrainingProgress(CBTrainingProgressForm cbTrainingProgressForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status = false;
		long cbProgressId = cbTrainingProgressForm.getCbProgressId();
		try {
			TrainingProgressBean trainingProgressBean = populateTrainingProgressBean(cbTrainingProgressForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			trainingProgressBean.setMisAuditBean(misAuditBean);
			status = trainingProgressDao.updateCBTrainingProgress(trainingProgressBean);
			if(status){
//				Long[] trainingOfficerIds = cbTrainingProgressForm.getTrainingOfficers();
//				if(MisUtility.ifEmpty(trainingOfficerIds)){
//					List<TrainingOfficerBean> trainingOfficerBeans = new ArrayList<TrainingOfficerBean>();
//					TrainingOfficerBean trainingOfficerBean = null;
//					EmployeeBean employeeBean = null;
//					for (Long trainingOfficerId : trainingOfficerIds) {
//						trainingOfficerBean = new TrainingOfficerBean();
//						trainingOfficerBean.setCbProgressId(cbTrainingProgressForm.getCbProgressId());
//						employeeBean = new EmployeeBean();
//						employeeBean.setEmployeeId(trainingOfficerId);
//						trainingOfficerBean.setEmployeeBean(employeeBean);
//						trainingOfficerBeans.add(trainingOfficerBean);
//					}
//					boolean trainingOfficersStatus = trainingOfficerDao.saveOrUpdateTrainingOfficer(trainingOfficerBeans);
//
//					if(!trainingOfficersStatus){
//						throw new MISException(MISExceptionCodes.MIS003,"Failed to save Training Officer Details for Training Progress");
//					}
//				}
				
				List<TrainingOfficerBean> trainingOfficerBeans = populateTrainingOfficerBeans(cbTrainingProgressForm, cbProgressId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(trainingOfficerBeans)){
					boolean childStatus = trainingOfficerDao.saveOrUpdateTrainingOfficer(trainingOfficerBeans);
					if(!childStatus){
						throw new MISException(MISExceptionCodes.MIS003,"Failed to save Material Utilization Details for Training Progress");
					}
				}    
				List<TrainingMaterialUtilizationBean> trainingMaterialUtilizationBeans = populateTrainingMaterialUtilizationBeans(cbTrainingProgressForm, misSessionBean, cbProgressId, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(trainingMaterialUtilizationBeans)){
					boolean childStatus = trainingMaterialUtilizationDao.saveOrUpdateCBTrainingMaterialUtilization(trainingMaterialUtilizationBeans);
					if(!childStatus){
						throw new MISException(MISExceptionCodes.MIS003,"Failed to save Material Utilization Details for Training Progress");
					}
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteCBTrainingProgress(CBTrainingProgressForm cbTrainingProgressForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status = false;
		long cbProgressId = cbTrainingProgressForm.getCbProgressId();
		try {
			TrainingProgressBean trainingProgressBean = populateTrainingProgressBean(cbTrainingProgressForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			trainingProgressBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			status = trainingProgressDao.updateCBTrainingProgress(trainingProgressBean);
			if(status){
//				Long[] trainingOfficerIds = cbTrainingProgressForm.getTrainingOfficers();
//				if(MisUtility.ifEmpty(trainingOfficerIds)){
//					List<TrainingOfficerBean> trainingOfficerBeans = new ArrayList<TrainingOfficerBean>();
//					TrainingOfficerBean trainingOfficerBean = null;
//					EmployeeBean employeeBean = null;
//					for (Long trainingOfficerId : trainingOfficerIds) {
//						trainingOfficerBean = new TrainingOfficerBean();
//						trainingOfficerBean.setCbProgressId(cbTrainingProgressForm.getCbProgressId());
//						employeeBean = new EmployeeBean();
//						employeeBean.setEmployeeId(trainingOfficerId);
//						trainingOfficerBean.setEmployeeBean(employeeBean);
//						trainingOfficerBeans.add(trainingOfficerBean);
//					}
//					boolean trainingOfficersStatus = trainingOfficerDao.saveOrUpdateTrainingOfficer(trainingOfficerBeans);
//
//					if(!trainingOfficersStatus){
//						throw new MISException(MISExceptionCodes.MIS003,"Failed to save Training Officer Details for Training Progress");
//					}
//				}
				TrainingOfficerBean trainingOfficerBean = new TrainingOfficerBean();
				trainingOfficerBean.setCbProgressId(cbProgressId);
				List<TrainingOfficerBean> trainingOfficerBeans = trainingOfficerDao.findTrainingOfficer(trainingOfficerBean, statusList);
				if(!MisUtility.ifEmpty(trainingOfficerBeans)){
					for (TrainingOfficerBean trainingOfficerBean2 : trainingOfficerBeans) {
						trainingOfficerBean2.setMisAuditBean(misAuditBean);
					}
					boolean trainingOfficerStatus = trainingOfficerDao.saveOrUpdateTrainingOfficer(trainingOfficerBeans);
					if(!trainingOfficerStatus){
						log.error(trainingOfficerStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Components details");
					}
				}
				TrainingMaterialUtilizationBean trainingMaterialUtilizationBean = new TrainingMaterialUtilizationBean();
				trainingMaterialUtilizationBean.setCbProgressId(cbProgressId);
				List<TrainingMaterialUtilizationBean> trainingMaterialUtilizationBeans = trainingMaterialUtilizationDao.findCBTrainingMaterialUtilization(trainingMaterialUtilizationBean, statusList);
				if(!MisUtility.ifEmpty(trainingMaterialUtilizationBeans)){
					for (TrainingMaterialUtilizationBean trainingMaterialUtilizationBean2 : trainingMaterialUtilizationBeans) {
						trainingMaterialUtilizationBean2.setMisAuditBean(misAuditBean);
					}
					boolean childStatus = trainingMaterialUtilizationDao.saveOrUpdateCBTrainingMaterialUtilization(trainingMaterialUtilizationBeans);
					if(!childStatus){
						throw new MISException(MISExceptionCodes.MIS003,"Failed to update Material Utilization Details for Training Progress");
					}
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postCBTrainingProgress(CBTrainingProgressForm cbTrainingProgressForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status = false;
		long cbProgressId = cbTrainingProgressForm.getCbProgressId();
		
		try {
			TrainingProgressBean trainingProgressBean = trainingProgressDao.loadCBTrainingProgress(cbProgressId);
			MISAuditBean misAuditBean = trainingProgressBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			trainingProgressBean.setMisAuditBean(misAuditBean);
			status = trainingProgressDao.updateCBTrainingProgress(trainingProgressBean);
			Set<TrainingMaterialUtilizationBean> trainingMaterialUtilizationBeans = trainingProgressBean.getTrainingMaterialUtilizationBeans();
			if(!trainingMaterialUtilizationBeans.isEmpty()){
				Iterator<TrainingMaterialUtilizationBean> trainingMaterialUtilizationBeanIterator = trainingMaterialUtilizationBeans.iterator();
				TrainingMaterialUtilizationBean trainingMaterialUtilizationBean = null;
				MISAuditBean misAuditBean1 = null;
				while(trainingMaterialUtilizationBeanIterator.hasNext()){
					trainingMaterialUtilizationBean = trainingMaterialUtilizationBeanIterator.next();
					misAuditBean1 = trainingMaterialUtilizationBean.getMisAuditBean();
					if(!MISConstants.MASTER_STATUS_DELETED.equalsIgnoreCase(trainingMaterialUtilizationBean.getMisAuditBean().getStatus())){
						misAuditBean1.setAuthBy(misSessionBean.getEnteredBy());
						misAuditBean1.setAuthDate(misSessionBean.getEnteredDate());
						misAuditBean1.setStatus(MISConstants.MASTER_STATUS_APPROVED);
					}
				}
			}
			
			if(status){
				
				TrainingOfficerBean trainingOfficerBean = new TrainingOfficerBean();
				trainingOfficerBean.setCbProgressId(cbProgressId);
				List<TrainingOfficerBean> trainingOfficerBeans = trainingOfficerDao.findTrainingOfficer(trainingOfficerBean, statusList);
				if(!MisUtility.ifEmpty(trainingOfficerBeans)){
					for (TrainingOfficerBean trainingOfficerBean2 : trainingOfficerBeans) {
						misAuditBean = trainingOfficerBean2.getMisAuditBean();
						misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
						misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						trainingOfficerBean2.setMisAuditBean(misAuditBean);
					}
					boolean trainingOfficerStatus = trainingOfficerDao.saveOrUpdateTrainingOfficer(trainingOfficerBeans);
					if(!trainingOfficerStatus){
						log.error(trainingOfficerStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Components details");
					}
				}
				
				if(!MisUtility.ifEmpty(trainingMaterialUtilizationBeans)){
					boolean childStatus = trainingMaterialUtilizationDao.saveOrUpdateCBTrainingMaterialUtilization(trainingMaterialUtilizationBeans);
					if(!childStatus){
						throw new MISException(MISExceptionCodes.MIS003,"Failed to post Material Utilization Details for Training Progress");
					}
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}
	
	private TrainingProgressBean populateTrainingProgressBean(CBTrainingProgressForm cbTrainingProgressForm){
		System.out.println("=================================="+cbTrainingProgressForm.getVillageId());
		System.out.println("=================================="+cbTrainingProgressForm.getBlockId());
		System.out.println(cbTrainingProgressForm.getVillageId());
		TrainingProgressBean trainingProgressBean = new TrainingProgressBean();
		trainingProgressBean.setCbProgressId(cbTrainingProgressForm.getCbProgressId());
		trainingProgressBean.setActionByWhen(MisUtility.convertStringToDate(cbTrainingProgressForm.getActionByWhen()));
		trainingProgressBean.setActionByWhom(cbTrainingProgressForm.getActionByWhom());
		trainingProgressBean.setBlockId(cbTrainingProgressForm.getBlockId());
		trainingProgressBean.setVillageId(cbTrainingProgressForm.getVillageId());
		trainingProgressBean.setDateOfTraining(MisUtility.convertStringToDate(cbTrainingProgressForm.getDateOfTraining()));
		trainingProgressBean.setIssueId(cbTrainingProgressForm.getIssues());
		trainingProgressBean.setOtherIssue(cbTrainingProgressForm.getOtherIssue());
		trainingProgressBean.setOtherOutcome(cbTrainingProgressForm.getOtherOutcome().trim());
		trainingProgressBean.setOtherRecommendation(cbTrainingProgressForm.getOtherRecommendation().trim());
		trainingProgressBean.setOutcomeId(cbTrainingProgressForm.getOutcome());
		trainingProgressBean.setRecommendation_id(cbTrainingProgressForm.getRecommendationsOfWssDpmc());
		trainingProgressBean.setNotes(cbTrainingProgressForm.getRemarks().trim());
		trainingProgressBean.setPhaseOfVillage(cbTrainingProgressForm.getPhaseOfVillage().trim());
		trainingProgressBean.setTotalParticipants(cbTrainingProgressForm.getTotalParticipants());
		trainingProgressBean.setTrainingId(cbTrainingProgressForm.getTrainingId());
		trainingProgressBean.setPlanId(cbTrainingProgressForm.getPlanId());
		trainingProgressBean.setLocationId(cbTrainingProgressForm.getLocationId());
		trainingProgressBean.setExpenditure(cbTrainingProgressForm.getExpenditure());
		trainingProgressBean.setLevelOfTraining(cbTrainingProgressForm.getLevelOfTraining());
		
		return trainingProgressBean;
	}
	
	@SuppressWarnings("unchecked")
	private List<TrainingMaterialUtilizationBean> populateTrainingMaterialUtilizationBeans(CBTrainingProgressForm cbTrainingProgressForm, MISSessionBean misSessionBean, long cbProgressId, String status) throws Exception{
		List<TrainingMaterialUtilizationBean> trainingMaterialUtilizationBeans = new ArrayList<TrainingMaterialUtilizationBean>();
		try {
			MISAuditBean misAuditBean = new MISAuditBean();
			if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			} else{
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			}
			misAuditBean.setStatus(status);
			
			Datagrid materialDatagrid = cbTrainingProgressForm.getMaterialDatagrid();
			Collection<MaterialUtilizationGridBean> addedTrainingMaterialUtilizationBeans = materialDatagrid.getAddedData();
			TrainingMaterialUtilizationBean trainingMaterialUtilizationBean = null;
			ItemBean itemBean = null;
			if(!MisUtility.ifEmpty(addedTrainingMaterialUtilizationBeans)){
				for (MaterialUtilizationGridBean materialUtilizationBean : addedTrainingMaterialUtilizationBeans) {
					trainingMaterialUtilizationBean = new TrainingMaterialUtilizationBean();
					trainingMaterialUtilizationBean.setCbProgressId(cbProgressId);
					itemBean = new ItemBean();
					itemBean.setItemId(materialUtilizationBean.getItemId());
					trainingMaterialUtilizationBean.setQuantity(materialUtilizationBean.getQuantity());
					trainingMaterialUtilizationBean.setItemBean(itemBean);
					trainingMaterialUtilizationBean.setMisAuditBean(misAuditBean);
					trainingMaterialUtilizationBeans.add(trainingMaterialUtilizationBean);
				}
			}
			Collection<MaterialUtilizationGridBean> modifiedTrainingMaterialUtilizationBeans = materialDatagrid.getModifiedData();
			if(!MisUtility.ifEmpty(modifiedTrainingMaterialUtilizationBeans)){
				for (MaterialUtilizationGridBean materialUtilizationBean : modifiedTrainingMaterialUtilizationBeans) {
					trainingMaterialUtilizationBean = new TrainingMaterialUtilizationBean();
					trainingMaterialUtilizationBean.setCbProgressId(cbProgressId);
					itemBean = new ItemBean();
					itemBean.setItemId(materialUtilizationBean.getItemId());
					trainingMaterialUtilizationBean.setQuantity(materialUtilizationBean.getQuantity());
					trainingMaterialUtilizationBean.setItemBean(itemBean);
					trainingMaterialUtilizationBean.setMisAuditBean(misAuditBean);
					trainingMaterialUtilizationBeans.add(trainingMaterialUtilizationBean);
				}
			}
			Collection<MaterialUtilizationGridBean> deletedTrainingMaterialUtilizationBeans = materialDatagrid.getDeletedData();
			if(!MisUtility.ifEmpty(deletedTrainingMaterialUtilizationBeans)){
				for (MaterialUtilizationGridBean materialUtilizationBean : deletedTrainingMaterialUtilizationBeans) {
					trainingMaterialUtilizationBean = new TrainingMaterialUtilizationBean();
					trainingMaterialUtilizationBean.setCbProgressId(cbProgressId);
					itemBean = new ItemBean();
					itemBean.setItemId(materialUtilizationBean.getItemId());
					trainingMaterialUtilizationBean.setQuantity(materialUtilizationBean.getQuantity());
					trainingMaterialUtilizationBean.setItemBean(itemBean);
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
					trainingMaterialUtilizationBean.setMisAuditBean(misAuditBean);
					trainingMaterialUtilizationBeans.add(trainingMaterialUtilizationBean);
					
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return trainingMaterialUtilizationBeans;
	}
	
	@SuppressWarnings("unchecked")
	private List<TrainingOfficerBean> populateTrainingOfficerBeans(CBTrainingProgressForm cbTrainingProgressForm,long cbProgressId, MISSessionBean misSessionBean, String status){
		List <TrainingOfficerBean> trainingOfficerBeans = new ArrayList<TrainingOfficerBean>();
		try {
			MISAuditBean misAuditBean = new MISAuditBean();
			if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			} else{
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			}
			misAuditBean.setStatus(status);
			
			Collection<TrainingOfficerBean> addedTrainingOfficerBeans = cbTrainingProgressForm.getTrainingOfficerDatagrid().getAddedData();
			if(!MisUtility.ifEmpty(addedTrainingOfficerBeans)){
				for (TrainingOfficerBean trainingOfficerBean : addedTrainingOfficerBeans) {
					trainingOfficerBean.setCbProgressId(cbProgressId);
					trainingOfficerBean.setMisAuditBean(misAuditBean);
					trainingOfficerBeans.add(trainingOfficerBean);
				}
			}
			
			Collection<TrainingOfficerBean> modifiedTrainingOfficerBeans = cbTrainingProgressForm.getTrainingOfficerDatagrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedTrainingOfficerBeans)){
				for (TrainingOfficerBean trainingOfficerBean : modifiedTrainingOfficerBeans) {
					trainingOfficerBean.setCbProgressId(cbProgressId);
					trainingOfficerBean.setMisAuditBean(misAuditBean);
					trainingOfficerBeans.add(trainingOfficerBean);
				}
			}
			
			Collection<TrainingOfficerBean> deletedTrainingOfficerBeans = cbTrainingProgressForm.getTrainingOfficerDatagrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedTrainingOfficerBeans)){
				for (TrainingOfficerBean trainingOfficerBean : deletedTrainingOfficerBeans) {
					trainingOfficerBean.setCbProgressId(cbProgressId);
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
					trainingOfficerBean.setMisAuditBean(misAuditBean);
					trainingOfficerBeans.add(trainingOfficerBean);
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		
		return trainingOfficerBeans;
	}

}
