package com.prwss.mis.master.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.master.training.dao.TrainingBean;
import com.prwss.mis.master.training.dao.TrainingDao;
import com.prwss.mis.master.training.struts.TrainingMasterForm;

public class TrainingBOImpl implements TrainingBO {
	
	private Logger log = Logger.getLogger(TrainingBOImpl.class);
	private TrainingDao trainingDao;

	public void setTrainingDao(TrainingDao trainingDao) {
		this.trainingDao = trainingDao;
	}

	@Override
	public List<TrainingBean> findTrainingMaster(TrainingMasterForm trainingMasterForm, List<String> statusList)
			throws MISException {
		List<TrainingBean> trainingBeans = null;
		try {
			TrainingBean trainingBean = populateTrainingBean(trainingMasterForm);		
			trainingBeans = trainingDao.findTraining(trainingBean, statusList);
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return trainingBeans;
	}

	@Override
	public String saveTrainingMaster(TrainingMasterForm trainingMasterForm, MISSessionBean misSessionBean)
			throws MISException {
		String trainingId = null;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			TrainingBean trainingFindBean = new TrainingBean();
			trainingFindBean.setTrainingId(trainingMasterForm.getTrainingId());
			List<TrainingBean> trainingFindBeans = trainingDao.findTraining(trainingFindBean, statusList);
			
			if(!MisUtility.ifEmpty(trainingFindBeans)){
				throw new MISException(MISExceptionCodes.MIS001,"training Id already exist");
			}
			
			TrainingBean trainingBean = populateTrainingBean(trainingMasterForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			
			trainingBean.setMisAuditBean(misAuditBean);
			trainingId = trainingDao.saveTraining(trainingBean);
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return trainingId;
	}

	@Override
	public boolean updateTrainingMaster(TrainingMasterForm trainingMasterForm, MISSessionBean misSessionBean,List<String> statusList)
			throws MISException {
		boolean status = false;
		try {
			TrainingBean trainingBean = populateTrainingBean(trainingMasterForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(statusList.get(0));
			
			trainingBean.setMisAuditBean(misAuditBean);
			status = trainingDao.updateTraining(trainingBean);
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean deleteTrainingMaster(TrainingMasterForm trainingMasterForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean result = false;
		List<TrainingBean> trainingBeans = trainingDao.findTraining(Arrays.asList(trainingMasterForm.getTrainingIds()));
		System.out.println(trainingBeans);
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
		try {
			if(!MisUtility.ifEmpty(trainingBeans)){
				for (TrainingBean trainingBean : trainingBeans) {
					trainingBean.setMisAuditBean(misAuditBean);			
				}
				result = trainingDao.updateTraining(trainingBeans);
		}
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}

	@Override
	public boolean postTrainingMaster(TrainingMasterForm trainingMasterForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean result = false;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		TrainingBean trainingFindBean = new TrainingBean();
		trainingFindBean.setTrainingId(trainingMasterForm.getTrainingId());
		List<TrainingBean> trainingBeans = trainingDao.findTraining(trainingFindBean, statusList);
		/*
		List<TrainingBean> trainingBeans = trainingDao.findTraining(Arrays.asList(trainingMasterForm.getTrainingIds()));*/
		
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
		misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
		try {
			for (TrainingBean trainingBean : trainingBeans) {
				trainingBean.setMisAuditBean(misAuditBean);			
			}
			result = trainingDao.updateTraining(trainingBeans);
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}
	
	
	private TrainingBean populateTrainingBean(TrainingMasterForm trainingMasterForm){
		TrainingBean trainingBean = new TrainingBean();
		trainingBean.setCategory(trainingMasterForm.getCategory());
		trainingBean.setDurationInDays(trainingMasterForm.getDurations());
		trainingBean.setEstimatedCost(trainingMasterForm.getEstimateCost());
		trainingBean.setExpectedOutcome(trainingMasterForm.getExpectedOutcomes());
		trainingBean.setFocusArea(trainingMasterForm.getFocusArea());
		trainingBean.setNoOfProgrammes(trainingMasterForm.getNumberOfPrograms());
		trainingBean.setResourceInstitution(trainingMasterForm.getResourceInstitution());
		trainingBean.setResponsibility(trainingMasterForm.getResponsibility());
		trainingBean.setTargetGroup(trainingMasterForm.getTargetGroup());
		trainingBean.setTrainingId(trainingMasterForm.getTrainingId());
		trainingBean.setTrainingName(trainingMasterForm.getTrainingName());
		trainingBean.setTrainingType(trainingMasterForm.getTypeOfTraining());
		
		return trainingBean;
	}

}
