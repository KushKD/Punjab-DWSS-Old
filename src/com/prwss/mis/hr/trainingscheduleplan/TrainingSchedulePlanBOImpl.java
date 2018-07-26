package com.prwss.mis.hr.trainingscheduleplan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.hr.trainingscheduleplan.dao.TrainingScheduleDetailBean;
import com.prwss.mis.hr.trainingscheduleplan.dao.TrainingScheduleDetailDao;
import com.prwss.mis.hr.trainingscheduleplan.dao.TrainingScheduleHeaderDao;
import com.prwss.mis.hr.trainingscheduleplan.struts.TrainingScheduleDetailGridBean;
import com.prwss.mis.hr.trainingscheduleplan.struts.TrainingScheduleForm;

public class TrainingSchedulePlanBOImpl implements TrainingSchedulePlanBO {

	private Logger log = Logger.getLogger(TrainingSchedulePlanBOImpl.class);
	private TrainingScheduleDetailDao trainingScheduleDetailDao;
	private TrainingScheduleHeaderDao trainingScheduleHeaderDao;
	
	public void setTrainingScheduleDetailDao(
			TrainingScheduleDetailDao trainingScheduleDetailDao) {
		this.trainingScheduleDetailDao = trainingScheduleDetailDao;
	}

	public void setTrainingScheduleHeaderDao(
			TrainingScheduleHeaderDao trainingScheduleHeaderDao) {
		this.trainingScheduleHeaderDao = trainingScheduleHeaderDao;
	}

	@Override
	public List<TrainingScheduleHeaderBean> findTrainingSchedulePlan(
			TrainingScheduleForm trainingScheduleForm, List<String> statusList)
			throws MISException {
		List<TrainingScheduleHeaderBean> trainingScheduleHeaderBeans = null;
		try {
			TrainingScheduleHeaderBean trainingScheduleHeaderBean = new TrainingScheduleHeaderBean();
			if(MisUtility.ifEmpty(trainingScheduleForm.getLocationId())&& MisUtility.ifEmpty(trainingScheduleForm.getTrainingPlanId())){
				trainingScheduleHeaderBean.setLocationId(trainingScheduleForm.getLocationId());
				trainingScheduleHeaderBean.setTrainingPlanId(trainingScheduleForm.getTrainingPlanId());
				trainingScheduleHeaderBeans = trainingScheduleHeaderDao.findTrainingScheduleHeader(trainingScheduleHeaderBean, statusList);
			Set<TrainingScheduleDetailBean> trainingScheduleDetailBeans = null;
			Iterator<TrainingScheduleDetailBean> trainingScheduleDetailIterator = null;
			TrainingScheduleDetailBean  trainingScheduleDetailBean = null;
			if(!MisUtility.ifEmpty(trainingScheduleHeaderBeans)){
				for (TrainingScheduleHeaderBean bean : trainingScheduleHeaderBeans) {
					trainingScheduleDetailBeans = bean.getTrainingScheduleDetailBeans();
					if(!MisUtility.ifEmpty(trainingScheduleDetailBeans)){
						trainingScheduleDetailIterator = trainingScheduleDetailBeans.iterator();					
						while(trainingScheduleDetailIterator.hasNext()){
							trainingScheduleDetailBean = trainingScheduleDetailIterator.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(trainingScheduleDetailBean.getMisAuditBean().getStatus())){
								trainingScheduleDetailIterator.remove();
								break;
							}
						}
					}
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
		
		return trainingScheduleHeaderBeans;
	}

	@Override
	public long saveTrainingSchedulePlan(
			TrainingScheduleForm trainingScheduleForm,
			MISSessionBean misSessionBean) throws MISException {
		long trainingPlanId = 0;
		try {
			TrainingScheduleHeaderBean trainingScheduleHeaderBean = populateTrainingScheduleHeaderBean(trainingScheduleForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			trainingScheduleHeaderBean.setMisAuditBean(misAuditBean);
			
			trainingPlanId = trainingScheduleHeaderDao.saveTrainingScheduleHeader(trainingScheduleHeaderBean);
			if(MisUtility.ifEmpty(trainingPlanId)){
				List<TrainingScheduleDetailBean> trainingScheduleDetailBeans = populateTrainingScheduleDetailBeans(trainingScheduleForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED, trainingPlanId);
				if(!MisUtility.ifEmpty(trainingScheduleDetailBeans)){
					boolean trainingScheduleDetailStatus = trainingScheduleDetailDao.saveOrUpdateTrainingScheduleDetailBeans(trainingScheduleDetailBeans);
					if(!trainingScheduleDetailStatus){
						log.error(trainingScheduleDetailStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Training Schedule Training details");
					}
				}
		}
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw new MISException(e);
		}
		return trainingPlanId;
	}

	@Override
	public boolean updateTrainingSchedulePlan(
			TrainingScheduleForm trainingScheduleForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			TrainingScheduleHeaderBean trainingScheduleHeaderBean = populateTrainingScheduleHeaderBean(trainingScheduleForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			trainingScheduleHeaderBean.setMisAuditBean(misAuditBean);
			
			status = trainingScheduleHeaderDao.updateTrainingScheduleHeader(trainingScheduleHeaderBean);
			if(status){
				List<TrainingScheduleDetailBean> trainingScheduleDetailBeans = populateTrainingScheduleDetailBeans(trainingScheduleForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED, trainingScheduleForm.getTrainingPlanId());
				if(!MisUtility.ifEmpty(trainingScheduleDetailBeans)){
					boolean trainingScheduleDetailStatus = trainingScheduleDetailDao.saveOrUpdateTrainingScheduleDetailBeans(trainingScheduleDetailBeans);
					if(!trainingScheduleDetailStatus){
						log.error(trainingScheduleDetailStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Training Schedule Training details");
					}
				}
		}
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean deleteTrainingSchedulePlan(
			TrainingScheduleForm trainingScheduleForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			TrainingScheduleHeaderBean trainingScheduleHeaderBean = populateTrainingScheduleHeaderBean(trainingScheduleForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			trainingScheduleHeaderBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			status = trainingScheduleHeaderDao.updateTrainingScheduleHeader(trainingScheduleHeaderBean);
			if(status){
				TrainingScheduleDetailBean trainingScheduleDetailBean = new TrainingScheduleDetailBean();
				trainingScheduleDetailBean.setTrainingPlanId(trainingScheduleForm.getTrainingPlanId());
					List<TrainingScheduleDetailBean> trainingScheduleDetailBeans = trainingScheduleDetailDao.findTrainingScheduleDetailBeans(trainingScheduleDetailBean, statusList);
					if(!MisUtility.ifEmpty(trainingScheduleDetailBeans)){
						for (TrainingScheduleDetailBean trainingScheduleDetailBean2 : trainingScheduleDetailBeans) {
							misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
							trainingScheduleDetailBean2.setMisAuditBean(misAuditBean);
						}
						boolean trainingScheduleDetailBeanStatus = trainingScheduleDetailDao.saveOrUpdateTrainingScheduleDetailBeans(trainingScheduleDetailBeans);
						if(!trainingScheduleDetailBeanStatus){
							log.error(trainingScheduleDetailBeans);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Training Schedule details");
						}
					}
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean postTrainingSchedulePlan(
			TrainingScheduleForm trainingScheduleForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		TrainingScheduleHeaderBean trainingScheduleHeaderBean = new TrainingScheduleHeaderBean();
		List<String> statuslist = new ArrayList<String>();
		statuslist.add(MISConstants.MASTER_STATUS_VERIFIED);
		trainingScheduleHeaderBean.setLocationId(trainingScheduleForm.getLocationId());
		trainingScheduleHeaderBean.setTrainingPlanId(trainingScheduleForm.getTrainingPlanId());
		trainingScheduleHeaderBean = trainingScheduleHeaderDao.findTrainingScheduleHeader(trainingScheduleHeaderBean, statuslist).get(0);
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean = trainingScheduleHeaderBean.getMisAuditBean();
		misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
		misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
		trainingScheduleHeaderBean.setMisAuditBean(misAuditBean);
		status = trainingScheduleHeaderDao.updateTrainingScheduleHeader(trainingScheduleHeaderBean);
		if(status){
			TrainingScheduleDetailBean trainingScheduleDetailBean = new TrainingScheduleDetailBean();
			trainingScheduleDetailBean.setTrainingPlanId(trainingScheduleForm.getTrainingPlanId());
				List<TrainingScheduleDetailBean> trainingScheduleDetailBeans = trainingScheduleDetailDao.findTrainingScheduleDetailBeans(trainingScheduleDetailBean, statuslist);
				if(!MisUtility.ifEmpty(trainingScheduleDetailBeans)){
					for (TrainingScheduleDetailBean trainingScheduleDetailBean2 : trainingScheduleDetailBeans) {
						misAuditBean = trainingScheduleDetailBean2.getMisAuditBean();
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
						misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
						trainingScheduleDetailBean2.setMisAuditBean(misAuditBean);
					}
					boolean trainingScheduleDetailBeanStatus = trainingScheduleDetailDao.saveOrUpdateTrainingScheduleDetailBeans(trainingScheduleDetailBeans);
					if(!trainingScheduleDetailBeanStatus){
						log.error(trainingScheduleDetailBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Training Schedule details");
					}
				}
		}
		
		return status;
	}
	
	private TrainingScheduleHeaderBean populateTrainingScheduleHeaderBean(TrainingScheduleForm trainingScheduleForm){
		TrainingScheduleHeaderBean trainingScheduleHeaderBean = new TrainingScheduleHeaderBean();
		trainingScheduleHeaderBean.setLocationId(trainingScheduleForm.getLocationId());
		trainingScheduleHeaderBean.setPlanFromDate(MisUtility.convertStringToDate(trainingScheduleForm.getPlanFromDate()));
		trainingScheduleHeaderBean.setPlanToDate(MisUtility.convertStringToDate(trainingScheduleForm.getPlanToDate()));
		trainingScheduleHeaderBean.setTrainingPlanId(trainingScheduleForm.getTrainingPlanId());
		return trainingScheduleHeaderBean;
		
	}
	
	@SuppressWarnings("unchecked")
	private List<TrainingScheduleDetailBean> populateTrainingScheduleDetailBeans(TrainingScheduleForm trainingScheduleForm, MISSessionBean misSessionBean, String status, long trainingPlanId){
		List <TrainingScheduleDetailBean> trainingScheduleDetailBeans = new ArrayList<TrainingScheduleDetailBean>();
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
			
			
			
			TrainingScheduleDetailBean trainingScheduleDetailBean  = null;
			Collection<TrainingScheduleDetailGridBean> addedTrainingScheduleDetailGridBeans = trainingScheduleForm.getTrainingDetailsDatagrid().getAddedData();
			if(!MisUtility.ifEmpty(addedTrainingScheduleDetailGridBeans)){
				for (TrainingScheduleDetailGridBean trainingScheduleDetailGridBean : addedTrainingScheduleDetailGridBeans) {
					trainingScheduleDetailBean = new TrainingScheduleDetailBean();
					trainingScheduleDetailBean.setId(trainingScheduleDetailGridBean.getId());
					trainingScheduleDetailBean.setNumberOfTraining(trainingScheduleDetailGridBean.getNumberOfTraining());
					trainingScheduleDetailBean.setTrainingPlanId(trainingPlanId);
					trainingScheduleDetailBean.setTargetDestination(trainingScheduleDetailGridBean.getTargetDestination());
					trainingScheduleDetailBean.setTrainingObjective(trainingScheduleDetailGridBean.getTrainingObjective());
					trainingScheduleDetailBean.setTrainingDate(MisUtility.convertStringToDate(trainingScheduleDetailGridBean.getTrainingDate()));
					trainingScheduleDetailBean.setRemarks(trainingScheduleDetailGridBean.getRemarks());
					trainingScheduleDetailBean.setMisAuditBean(misAuditBean);
					trainingScheduleDetailBeans.add(trainingScheduleDetailBean);
				}
			}
			
			Collection<TrainingScheduleDetailGridBean> modifiedTrainingScheduleDetailGridBeans = trainingScheduleForm.getTrainingDetailsDatagrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedTrainingScheduleDetailGridBeans)){
				for (TrainingScheduleDetailGridBean trainingScheduleDetailGridBean : modifiedTrainingScheduleDetailGridBeans) {
					trainingScheduleDetailBean = new TrainingScheduleDetailBean();
					trainingScheduleDetailBean.setId(trainingScheduleDetailGridBean.getId());
					trainingScheduleDetailBean.setNumberOfTraining(trainingScheduleDetailGridBean.getNumberOfTraining());
					trainingScheduleDetailBean.setTrainingPlanId(trainingPlanId);
					trainingScheduleDetailBean.setTargetDestination(trainingScheduleDetailGridBean.getTargetDestination());
					trainingScheduleDetailBean.setTrainingObjective(trainingScheduleDetailGridBean.getTrainingObjective().trim());
					trainingScheduleDetailBean.setTrainingDate(MisUtility.convertStringToDate(trainingScheduleDetailGridBean.getTrainingDate()));
					trainingScheduleDetailBean.setRemarks(trainingScheduleDetailGridBean.getRemarks());
					trainingScheduleDetailBean.setMisAuditBean(misAuditBean);
					trainingScheduleDetailBeans.add(trainingScheduleDetailBean);
				}
			}
			
			Collection<TrainingScheduleDetailGridBean> deletedTrainingScheduleDetailGridBeans = trainingScheduleForm.getTrainingDetailsDatagrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedTrainingScheduleDetailGridBeans)){
				for (TrainingScheduleDetailGridBean trainingScheduleDetailGridBean : deletedTrainingScheduleDetailGridBeans) {
					trainingScheduleDetailBean = new TrainingScheduleDetailBean();
					trainingScheduleDetailBean.setId(trainingScheduleDetailGridBean.getId());
					trainingScheduleDetailBean.setNumberOfTraining(trainingScheduleDetailGridBean.getNumberOfTraining());
					trainingScheduleDetailBean.setTrainingPlanId(trainingPlanId);
					trainingScheduleDetailBean.setTargetDestination(trainingScheduleDetailGridBean.getTargetDestination());
					trainingScheduleDetailBean.setTrainingObjective(trainingScheduleDetailGridBean.getTrainingObjective().trim());
					trainingScheduleDetailBean.setTrainingDate(MisUtility.convertStringToDate(trainingScheduleDetailGridBean.getTrainingDate()));
					trainingScheduleDetailBean.setRemarks(trainingScheduleDetailGridBean.getRemarks());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
					trainingScheduleDetailBean.setMisAuditBean(misAuditBean);
					trainingScheduleDetailBeans.add(trainingScheduleDetailBean);
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		
		return trainingScheduleDetailBeans;
	}


}
