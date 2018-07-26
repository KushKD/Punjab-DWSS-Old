package com.prwss.mis.hr.targetplan;

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
import com.prwss.mis.hr.employeeappraisal.struts.EmployeeAppraisalForm;
import com.prwss.mis.hr.employeevaluation.struts.EmployeeEvaluationForm;
import com.prwss.mis.hr.targetplan.dao.EmployeeTargetDetailDao;
import com.prwss.mis.hr.targetplan.dao.EmployeeTargetHeaderDao;
import com.prwss.mis.hr.targetplan.struts.EmployeeTargetGridBean;
import com.prwss.mis.hr.targetplan.struts.EmployeeTargetPlanForm;

public class EmployeeTargetBOImpl implements EmployeeTargetBO {
private Logger log = Logger.getLogger(EmployeeTargetBOImpl.class);
private EmployeeTargetHeaderDao employeeTargetHeaderDao;
private EmployeeTargetDetailDao employeeTargetDetailDao;


	public void setEmployeeTargetHeaderDao(
		EmployeeTargetHeaderDao employeeTargetHeaderDao) {
	this.employeeTargetHeaderDao = employeeTargetHeaderDao;
}

public void setEmployeeTargetDetailDao(
		EmployeeTargetDetailDao employeeTargetDetailDao) {
	this.employeeTargetDetailDao = employeeTargetDetailDao;
}

	@Override
	public List<EmployeeTargetHeaderBean> findEmployeeTargetPlanFrom(
			EmployeeTargetPlanForm employeeTargetPlanForm,
			List<String> statusList) throws MISException {
		List<EmployeeTargetHeaderBean> employeeTargetHeaderBeans = null;
		try {
			EmployeeTargetHeaderBean employeeTargetHeaderBean = new EmployeeTargetHeaderBean();
			employeeTargetHeaderBean.setTargetPlanId(employeeTargetPlanForm.getTargetPlanId());
			employeeTargetHeaderBeans = employeeTargetHeaderDao.findEmployeeTargetHeader(employeeTargetHeaderBean, statusList);
			Set<EmployeeTargetDetailsBean> employeeTargetDetailsBeans = null;
			Iterator<EmployeeTargetDetailsBean> employeeTargetDetailsIterator = null;
			EmployeeTargetDetailsBean  employeeTargetDetailsBean = null;
			if(!MisUtility.ifEmpty(employeeTargetHeaderBeans)){
				for (EmployeeTargetHeaderBean bean : employeeTargetHeaderBeans) {
					employeeTargetDetailsBeans = bean.getEmployeeTargetDetailsBeans();
					if(!MisUtility.ifEmpty(employeeTargetDetailsBeans)){
						employeeTargetDetailsIterator = employeeTargetDetailsBeans.iterator();					
						while(employeeTargetDetailsIterator.hasNext()){
							employeeTargetDetailsBean = employeeTargetDetailsIterator.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(employeeTargetDetailsBean.getMisAuditBean().getStatus())){
								employeeTargetDetailsIterator.remove();
								break;
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
		
		return employeeTargetHeaderBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveEmployeeTargetPlanFrom(
			EmployeeTargetPlanForm employeeTargetPlanForm,
			MISSessionBean misSessionBean) throws MISException {
		long targetPlanId = 0;
		try {
			EmployeeTargetHeaderBean employeeTargetHeaderBean = popuplateEmployeeTargetHeaderBean(employeeTargetPlanForm, misSessionBean);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			employeeTargetHeaderBean.setMisAuditBean(misAuditBean);
			
			targetPlanId = employeeTargetHeaderDao.saveEmployeeTargetHeader(employeeTargetHeaderBean);
			if(MisUtility.ifEmpty(targetPlanId)){
				List<EmployeeTargetDetailsBean> employeeTargetDetailsBeans = populateTargetDetailBeans(employeeTargetPlanForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED, targetPlanId);
				if(!MisUtility.ifEmpty(employeeTargetDetailsBeans)){
					boolean employeeTargetDetailsStatus = employeeTargetDetailDao.saveOrUpdateEmployeeTargetDetailsBean(employeeTargetDetailsBeans);
					if(!employeeTargetDetailsStatus){
						log.error(employeeTargetDetailsBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Employee Target details");
					}
				}
		}
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw new MISException(e);
		}
		return targetPlanId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateEmployeeTargetPlanFrom(
			EmployeeTargetPlanForm employeeTargetPlanForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			EmployeeTargetHeaderBean employeeTargetHeaderBean = popuplateEmployeeTargetHeaderBean(employeeTargetPlanForm, misSessionBean);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			employeeTargetHeaderBean.setMisAuditBean(misAuditBean);
			status = employeeTargetHeaderDao.updateEmployeeTargetHeader(employeeTargetHeaderBean);
			if(status){
				List<EmployeeTargetDetailsBean> employeeTargetDetailsBeans = populateTargetDetailBeans(employeeTargetPlanForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED, employeeTargetPlanForm.getTargetPlanId());
				if(!MisUtility.ifEmpty(employeeTargetDetailsBeans)){
					boolean employeeTargetDetailsStatus = employeeTargetDetailDao.saveOrUpdateEmployeeTargetDetailsBean(employeeTargetDetailsBeans);
					if(!employeeTargetDetailsStatus){
						log.error(employeeTargetDetailsBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Employee Target details");
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
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteEmployeeTargetPlanFrom(
			EmployeeTargetPlanForm employeeTargetPlanForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			EmployeeTargetHeaderBean employeeTargetHeaderBean = popuplateEmployeeTargetHeaderBean(employeeTargetPlanForm, misSessionBean);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			employeeTargetHeaderBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			status = employeeTargetHeaderDao.updateEmployeeTargetHeader(employeeTargetHeaderBean);
			if(status){
				EmployeeTargetDetailsBean employeeTargetDetailsBean = new EmployeeTargetDetailsBean();
				employeeTargetDetailsBean.setTargetPlanId(employeeTargetPlanForm.getTargetPlanId());
					List<EmployeeTargetDetailsBean> employeeTargetDetailsBeans = employeeTargetDetailDao.findEmployeeTargetDetailsBean(employeeTargetDetailsBean, statusList);
					if(!MisUtility.ifEmpty(employeeTargetDetailsBeans)){
						for (EmployeeTargetDetailsBean employeeTargetDetailsBean2 : employeeTargetDetailsBeans) {
							misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
							employeeTargetDetailsBean2.setMisAuditBean(misAuditBean);
						}
						boolean employeeTargetDetailsBeanStatus = employeeTargetDetailDao.saveOrUpdateEmployeeTargetDetailsBean(employeeTargetDetailsBeans);
						if(!employeeTargetDetailsBeanStatus){
							log.error(employeeTargetDetailsBeans);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Target Details details");
						}
					}
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new MISException(e);
		}
		return status;
	}

	
	
	private EmployeeTargetHeaderBean popuplateEmployeeTargetHeaderBean(EmployeeTargetPlanForm employeeTargetPlanForm , MISSessionBean misSessionBean){
		EmployeeTargetHeaderBean employeeTargetHeaderBean = new EmployeeTargetHeaderBean();
		employeeTargetHeaderBean.setEmployeeId(employeeTargetPlanForm.getEmployeeId());
		employeeTargetHeaderBean.setTargetPlanId(employeeTargetPlanForm.getTargetPlanId());
		employeeTargetHeaderBean.setLocationId(employeeTargetPlanForm.getLocationId());
		employeeTargetHeaderBean.setPlanFromDate(MisUtility.convertStringToDate(employeeTargetPlanForm.getPlanFromDate()));
		employeeTargetHeaderBean.setPlanToDate(MisUtility.convertStringToDate(employeeTargetPlanForm.getPlanToDate()));
		employeeTargetHeaderBean.setReportingOfficerId(misSessionBean.getEnteredBy());
		return employeeTargetHeaderBean;
	}
	
	private EmployeeTargetHeaderBean popuplateEmployeeEvaluationHeaderBean(EmployeeEvaluationForm employeeEvaluationForm , MISSessionBean misSessionBean){
		EmployeeTargetHeaderBean employeeTargetHeaderBean = new EmployeeTargetHeaderBean();
		employeeTargetHeaderBean.setEmployeeId(employeeEvaluationForm.getEmployeeId());
		employeeTargetHeaderBean.setTargetPlanId(employeeEvaluationForm.getTargetPlanId());
		employeeTargetHeaderBean.setLocationId(employeeEvaluationForm.getLocationId());
		employeeTargetHeaderBean.setPlanFromDate(MisUtility.convertStringToDate(employeeEvaluationForm.getPlanFromDate()));
		employeeTargetHeaderBean.setPlanToDate(MisUtility.convertStringToDate(employeeEvaluationForm.getPlanToDate()));
		employeeTargetHeaderBean.setReportingOfficerId(misSessionBean.getEnteredBy());
		employeeTargetHeaderBean.setAppraisal(employeeEvaluationForm.getAppraisal());
		employeeTargetHeaderBean.setPerformanceRating(employeeEvaluationForm.getPerformanceRating());
		return employeeTargetHeaderBean;
	}
	
	@SuppressWarnings("unchecked")
	private List<EmployeeTargetDetailsBean> populateTargetDetailBeans(EmployeeTargetPlanForm employeeTargetPlanForm, MISSessionBean misSessionBean, String status,long targetPlanId){
		List <EmployeeTargetDetailsBean> employeeTargetDetailsBeans = new ArrayList<EmployeeTargetDetailsBean>();
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
			
			
			EmployeeTargetDetailsBean employeeTargetDetailsBean  = null;
			Collection<EmployeeTargetGridBean> addedEmployeeTargetGridBeans = employeeTargetPlanForm.getEmployeeTargetDatagrid().getAddedData();
			if(!MisUtility.ifEmpty(addedEmployeeTargetGridBeans)){
				for (EmployeeTargetGridBean employeeTargetGridBean : addedEmployeeTargetGridBeans) {
					employeeTargetDetailsBean = new EmployeeTargetDetailsBean();
					employeeTargetDetailsBean.setTargetName(employeeTargetGridBean.getTargetName().trim());
					employeeTargetDetailsBean.setTargetPlanId(targetPlanId);
					employeeTargetDetailsBean.setTargetCompletionDate(MisUtility.convertStringToDate(employeeTargetGridBean.getTargetCompletionDate()));
					employeeTargetDetailsBean.setId(employeeTargetGridBean.getId());
					employeeTargetDetailsBean.setPlannerRemarks(employeeTargetGridBean.getPlannerRemarks());
					employeeTargetDetailsBean.setMisAuditBean(misAuditBean);
					employeeTargetDetailsBeans.add(employeeTargetDetailsBean);
				}
			}
			
			Collection<EmployeeTargetGridBean> modifiedEmployeeTargetGridBeans = employeeTargetPlanForm.getEmployeeTargetDatagrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedEmployeeTargetGridBeans)){
				for (EmployeeTargetGridBean employeeTargetGridBean : modifiedEmployeeTargetGridBeans) {
					employeeTargetDetailsBean = new EmployeeTargetDetailsBean();
					employeeTargetDetailsBean.setTargetName(employeeTargetGridBean.getTargetName().trim());
					employeeTargetDetailsBean.setTargetPlanId(targetPlanId);
					employeeTargetDetailsBean.setTargetCompletionDate(MisUtility.convertStringToDate(employeeTargetGridBean.getTargetCompletionDate()));
					employeeTargetDetailsBean.setId(employeeTargetGridBean.getId());
					employeeTargetDetailsBean.setPlannerRemarks(employeeTargetGridBean.getPlannerRemarks());
					employeeTargetDetailsBean.setMisAuditBean(misAuditBean);
					employeeTargetDetailsBeans.add(employeeTargetDetailsBean);
				}
			}
			
			Collection<EmployeeTargetGridBean> deletedEmployeeTargetGridBeans = employeeTargetPlanForm.getEmployeeTargetDatagrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedEmployeeTargetGridBeans)){
				for (EmployeeTargetGridBean employeeTargetGridBean : deletedEmployeeTargetGridBeans) {
					employeeTargetDetailsBean = new EmployeeTargetDetailsBean();
					employeeTargetDetailsBean.setTargetName(employeeTargetGridBean.getTargetName().trim());
					employeeTargetDetailsBean.setTargetPlanId(targetPlanId);
					employeeTargetDetailsBean.setTargetCompletionDate(MisUtility.convertStringToDate(employeeTargetGridBean.getTargetCompletionDate()));
					employeeTargetDetailsBean.setId(employeeTargetGridBean.getId());
					employeeTargetDetailsBean.setPlannerRemarks(employeeTargetGridBean.getPlannerRemarks());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
					employeeTargetDetailsBean.setMisAuditBean(misAuditBean);
					employeeTargetDetailsBeans.add(employeeTargetDetailsBean);
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		
		return employeeTargetDetailsBeans;
	}
	
	@Override
	public List<EmployeeTargetHeaderBean> findEmployeeAppraisalFrom(
			EmployeeAppraisalForm employeeAppraisalForm,
			List<String> statusList) throws MISException {
		List<EmployeeTargetHeaderBean> employeeTargetHeaderBeans = null;
		try {
			EmployeeTargetHeaderBean employeeTargetHeaderBean = new EmployeeTargetHeaderBean();
			if(MisUtility.ifEmpty(employeeAppraisalForm.getTargetPlanId())){
			employeeTargetHeaderBean.setTargetPlanId(employeeAppraisalForm.getTargetPlanId());
			employeeTargetHeaderBeans = employeeTargetHeaderDao.findEmployeeTargetHeader(employeeTargetHeaderBean, statusList);
			Set<EmployeeTargetDetailsBean> employeeTargetDetailsBeans = null;
			Iterator<EmployeeTargetDetailsBean> employeeTargetDetailsIterator = null;
			EmployeeTargetDetailsBean  employeeTargetDetailsBean = null;
			if(!MisUtility.ifEmpty(employeeTargetHeaderBeans)){
				for (EmployeeTargetHeaderBean bean : employeeTargetHeaderBeans) {
					employeeTargetDetailsBeans = bean.getEmployeeTargetDetailsBeans();
					if(!MisUtility.ifEmpty(employeeTargetDetailsBeans)){
						employeeTargetDetailsIterator = employeeTargetDetailsBeans.iterator();					
						while(employeeTargetDetailsIterator.hasNext()){
							employeeTargetDetailsBean = employeeTargetDetailsIterator.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(employeeTargetDetailsBean.getMisAuditBean().getStatus())){
								employeeTargetDetailsIterator.remove();
								break;
							}
						}
					}
				}
			}
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return employeeTargetHeaderBeans;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateEmployeeAppraisalFrom(
			EmployeeAppraisalForm employeeAppraisalForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			List<EmployeeTargetDetailsBean> employeeTargetDetailsBeans = populateAppraisalTargetDetailBeans(employeeAppraisalForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED, employeeAppraisalForm.getTargetPlanId());
				if(!MisUtility.ifEmpty(employeeTargetDetailsBeans)){
					status = employeeTargetDetailDao.saveOrUpdateEmployeeTargetDetailsBean(employeeTargetDetailsBeans);
					if(!status){
						log.error(employeeTargetDetailsBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Employee Target details");
					}
				}
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw new MISException(e);
		}
		return status;
	}

//Populate method to populate Target Detail Bean VIA Employee Appraisal Screen
@SuppressWarnings("unchecked")
private List<EmployeeTargetDetailsBean> populateAppraisalTargetDetailBeans(EmployeeAppraisalForm employeeAppraisalForm, MISSessionBean misSessionBean, String status,long targetPlanId){
	List <EmployeeTargetDetailsBean> employeeTargetDetailsBeans = new ArrayList<EmployeeTargetDetailsBean>();
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
		
		
		EmployeeTargetDetailsBean employeeTargetDetailsBean  = null;
		/*Only modified data as User can only modify its target information and cannot add or delete*/
		Collection<EmployeeTargetGridBean> modifiedEmployeeTargetGridBeans = employeeAppraisalForm.getEmployeeTargetDatagrid().getModifiedData();
		System.out.println("IN POPULATE");
		if(!MisUtility.ifEmpty(modifiedEmployeeTargetGridBeans)){
			for (EmployeeTargetGridBean employeeTargetGridBean : modifiedEmployeeTargetGridBeans) {
				employeeTargetDetailsBean = new EmployeeTargetDetailsBean();
				employeeTargetDetailsBean.setTargetName(employeeTargetGridBean.getTargetName().trim());
				employeeTargetDetailsBean.setTargetPlanId(targetPlanId);
				employeeTargetDetailsBean.setTargetCompletionDate(MisUtility.convertStringToDate(employeeTargetGridBean.getTargetCompletionDate()));
				employeeTargetDetailsBean.setId(employeeTargetGridBean.getId());
				employeeTargetDetailsBean.setPlannerRemarks(employeeTargetGridBean.getPlannerRemarks());
				employeeTargetDetailsBean.setTargetActualCompletionDate(MisUtility.convertStringToDate(employeeTargetGridBean.getTargetActualCompletionDate()));
				employeeTargetDetailsBean.setTargetStatus(employeeTargetGridBean.getTargetStatus());
				employeeTargetDetailsBean.setEmployeeRemarks(employeeTargetGridBean.getEmployeeRemarks());
				employeeTargetDetailsBean.setMisAuditBean(misAuditBean);
				employeeTargetDetailsBeans.add(employeeTargetDetailsBean);
			}
		}
	} catch (Exception e) {
		log.error(e.getLocalizedMessage(),e);
		e.printStackTrace();
	}
	
	return employeeTargetDetailsBeans;
}

@Override
public List<EmployeeTargetHeaderBean> findEmployeeEvaluationFrom(
		EmployeeEvaluationForm employeeEvaluationForm,
		List<String> statusList) throws MISException {
	List<EmployeeTargetHeaderBean> employeeTargetHeaderBeans = null;
	try {
		EmployeeTargetHeaderBean employeeTargetHeaderBean = new EmployeeTargetHeaderBean();
		employeeTargetHeaderBean.setTargetPlanId(employeeEvaluationForm.getTargetPlanId());
		employeeTargetHeaderBeans = employeeTargetHeaderDao.findEmployeeTargetHeader(employeeTargetHeaderBean, statusList);
		Set<EmployeeTargetDetailsBean> employeeTargetDetailsBeans = null;
		Iterator<EmployeeTargetDetailsBean> employeeTargetDetailsIterator = null;
		EmployeeTargetDetailsBean  employeeTargetDetailsBean = null;
		if(!MisUtility.ifEmpty(employeeTargetHeaderBeans)){
			for (EmployeeTargetHeaderBean bean : employeeTargetHeaderBeans) {
				employeeTargetDetailsBeans = bean.getEmployeeTargetDetailsBeans();
				if(!MisUtility.ifEmpty(employeeTargetDetailsBeans)){
					employeeTargetDetailsIterator = employeeTargetDetailsBeans.iterator();					
					while(employeeTargetDetailsIterator.hasNext()){
						employeeTargetDetailsBean = employeeTargetDetailsIterator.next();
						if(MISConstants.MASTER_STATUS_DELETED.equals(employeeTargetDetailsBean.getMisAuditBean().getStatus())){
							employeeTargetDetailsIterator.remove();
							break;
						}
					}
				}
			}
		}
	} catch (DataAccessException e) {
		e.printStackTrace();
		log.error(e.getLocalizedMessage(),e);
		throw new MISException(e);
	}
	
	return employeeTargetHeaderBeans;
}

@Override
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public boolean updateEmployeeEvaluationFrom(
		EmployeeEvaluationForm employeeEvaluationForm,
		MISSessionBean misSessionBean) throws MISException {
	boolean status = false;
	try {
		EmployeeTargetHeaderBean employeeTargetHeaderBean = popuplateEmployeeEvaluationHeaderBean(employeeEvaluationForm, misSessionBean);
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
		employeeTargetHeaderBean.setMisAuditBean(misAuditBean);
		status = employeeTargetHeaderDao.updateEmployeeTargetHeader(employeeTargetHeaderBean);
		if(status){
			List<EmployeeTargetDetailsBean> employeeTargetDetailsBeans = populateEvaluationTargetDetailBeans(employeeEvaluationForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED, employeeEvaluationForm.getTargetPlanId());
			if(!MisUtility.ifEmpty(employeeTargetDetailsBeans)){
				boolean employeeTargetDetailsStatus = employeeTargetDetailDao.saveOrUpdateEmployeeTargetDetailsBean(employeeTargetDetailsBeans);
				if(!employeeTargetDetailsStatus){
					log.error(employeeTargetDetailsBeans);
					throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Employee Target details");
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

//Populate method to populate Target Detail Bean VIA Employee Evaluation Screen
@SuppressWarnings("unchecked")
private List<EmployeeTargetDetailsBean> populateEvaluationTargetDetailBeans(EmployeeEvaluationForm employeeEvaluationForm, MISSessionBean misSessionBean, String status,long targetPlanId){
	List <EmployeeTargetDetailsBean> employeeTargetDetailsBeans = new ArrayList<EmployeeTargetDetailsBean>();
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
		
		
		EmployeeTargetDetailsBean employeeTargetDetailsBean  = null;
		/*Only modified data as User can only modify its target information and cannot add or delete*/
		Collection<EmployeeTargetGridBean> modifiedEmployeeTargetGridBeans = employeeEvaluationForm.getEmployeeTargetDatagrid().getModifiedData();
		if(!MisUtility.ifEmpty(modifiedEmployeeTargetGridBeans)){
			for (EmployeeTargetGridBean employeeTargetGridBean : modifiedEmployeeTargetGridBeans) {
				employeeTargetDetailsBean = new EmployeeTargetDetailsBean();
				employeeTargetDetailsBean.setTargetName(employeeTargetGridBean.getTargetName().trim());
				employeeTargetDetailsBean.setTargetPlanId(targetPlanId);
				employeeTargetDetailsBean.setTargetCompletionDate(MisUtility.convertStringToDate(employeeTargetGridBean.getTargetCompletionDate()));
				employeeTargetDetailsBean.setId(employeeTargetGridBean.getId());
				employeeTargetDetailsBean.setPlannerRemarks(employeeTargetGridBean.getPlannerRemarks());
				employeeTargetDetailsBean.setTargetActualCompletionDate(MisUtility.convertStringToDate(employeeTargetGridBean.getTargetActualCompletionDate()));
				employeeTargetDetailsBean.setTargetStatus(employeeTargetGridBean.getTargetStatus());
				employeeTargetDetailsBean.setEmployeeRemarks(employeeTargetGridBean.getEmployeeRemarks());
				employeeTargetDetailsBean.setEvaluatorRemarks(employeeTargetGridBean.getEvaluatorRemarks());
				employeeTargetDetailsBean.setMisAuditBean(misAuditBean);
				employeeTargetDetailsBeans.add(employeeTargetDetailsBean);
			}
		}
	} catch (Exception e) {
		log.error(e.getLocalizedMessage(),e);
		e.printStackTrace();
	}
	
	return employeeTargetDetailsBeans;
}

@Override
public boolean postEmployeeEvaluation(
		EmployeeEvaluationForm employeeEvaluationForm,
		MISSessionBean misSessionBean) throws MISException {
	boolean status = false;
	try {
		EmployeeTargetHeaderBean employeeTargetHeaderBean = new EmployeeTargetHeaderBean();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		employeeTargetHeaderBean.setTargetPlanId(employeeEvaluationForm.getTargetPlanId());
		EmployeeTargetHeaderBean employeeTargetFindHeaderBean = employeeTargetHeaderDao.findEmployeeTargetHeader(employeeTargetHeaderBean, statusList).get(0);
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean = employeeTargetFindHeaderBean.getMisAuditBean();
		misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
		misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
		employeeTargetHeaderBean.setMisAuditBean(misAuditBean);
		status = employeeTargetHeaderDao.updateEmployeeTargetHeader(employeeTargetFindHeaderBean);
		if(status){
			EmployeeTargetDetailsBean employeeTargetDetailsBean = new EmployeeTargetDetailsBean();
			employeeTargetDetailsBean.setTargetPlanId(employeeEvaluationForm.getTargetPlanId());
				List<EmployeeTargetDetailsBean> employeeTargetDetailsBeans = employeeTargetDetailDao.findEmployeeTargetDetailsBean(employeeTargetDetailsBean, statusList);
				if(!MisUtility.ifEmpty(employeeTargetDetailsBeans)){
					for (EmployeeTargetDetailsBean employeeTargetDetailsBean2 : employeeTargetDetailsBeans) {
						misAuditBean = employeeTargetDetailsBean2.getMisAuditBean();
						misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
						misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						employeeTargetDetailsBean2.setMisAuditBean(misAuditBean);
					}
					boolean employeeTargetDetailsBeanStatus = employeeTargetDetailDao.saveOrUpdateEmployeeTargetDetailsBean(employeeTargetDetailsBeans);
					if(!employeeTargetDetailsBeanStatus){
						log.error(employeeTargetDetailsBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Target Details details");
					}
				}
		}
	} catch (DataAccessException e) {
		e.printStackTrace();
		throw new MISException(e);
	}
	return status;
}
}




