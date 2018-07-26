package com.prwss.mis.masters.employee;

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
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeContactExtendedBean;
import com.prwss.mis.masters.employee.dao.EmployeeContactExtendedDao;
import com.prwss.mis.masters.employee.dao.EmployeeContactExtendedGridBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;
import com.prwss.mis.masters.employee.dao.EmployeeDesignationBean;
import com.prwss.mis.masters.employee.dao.EmployeeDesignationDao;
import com.prwss.mis.masters.employee.dao.EmployeePromotionHistoryBean;
import com.prwss.mis.masters.employee.dao.EmployeePromotionHistoryDao;
import com.prwss.mis.masters.employee.dao.EmployeeQualificationBean;
import com.prwss.mis.masters.employee.dao.EmployeeQualificationDao;
import com.prwss.mis.masters.employee.dao.EmploymentHistoryBean;
import com.prwss.mis.masters.employee.dao.EmploymentHistoryDao;
import com.prwss.mis.masters.employee.struts.EmployeeForm;
import com.prwss.mis.masters.employee.struts.EmployeePromotionHistoryGridBean;
import com.prwss.mis.masters.employee.struts.EmployementHistoryGridBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class EmployeeBOImpl implements EmployeeBO {
	
	private Logger log = Logger.getLogger(EmployeeBOImpl.class);
	
	private EmployeeDao employeeDao;
	private EmploymentHistoryDao employmentHistoryDao;
	private EmployeeQualificationDao employeeQualificationDao;
	private EmployeePromotionHistoryDao employeePromotionHistoryDao;
	private EmployeeContactExtendedDao employeeContactExtendedDao;
	private EmployeeDesignationDao employeeDesignationDao;
	
	
	public void setEmployeeDesignationDao(
			EmployeeDesignationDao employeeDesignationDao) {
		this.employeeDesignationDao = employeeDesignationDao;
	}
	
	public void setEmployeeContactExtendedDao(
			EmployeeContactExtendedDao employeeContactExtendedDao) {
		this.employeeContactExtendedDao = employeeContactExtendedDao;
	}
	
	public void setEmployeePromotionHistoryDao(
			EmployeePromotionHistoryDao employeePromotionHistoryDao) {
		this.employeePromotionHistoryDao = employeePromotionHistoryDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void setEmploymentHistoryDao(EmploymentHistoryDao employmentHistoryDao) {
		this.employmentHistoryDao = employmentHistoryDao;
	}

	public void setEmployeeQualificationDao(EmployeeQualificationDao employeeQualificationDao) {
		this.employeeQualificationDao = employeeQualificationDao;
	}

	@Override
	public List<EmployeeBean> findEmployee(EmployeeForm employeeForm, List<String> statusList) throws MISException {
		List<EmployeeBean> employeeBeans = null;
		try {
			EmployeeBean employeeBean = new EmployeeBean();
			//System.out.println("id-----"+employeeForm.getEmployeeId());
			//System.out.println("name----"+employeeForm.getEmployeeName());
			//System.out.println("father---"+employeeForm.getFatherName());
			employeeBean.setEmployeeId(employeeForm.getEmployeeId());
			employeeBean.setEmployeeName(employeeForm.getEmployeeName());
			employeeBean.setFatherName(employeeForm.getFatherName());
			employeeBean.setDateOfBirth(MisUtility.convertStringToDate(employeeForm.getDateOfBirth()));
			employeeBeans = employeeDao.findEmployee(employeeBean, statusList);
			Set<EmployeeQualificationBean> employeeQualificationBeans = null;
			Iterator<EmployeeQualificationBean> iteratorQualification = null;
			EmployeeQualificationBean  employeeQualificationBean = null;
			if(!MisUtility.ifEmpty(employeeBeans)){
				for (EmployeeBean bean : employeeBeans) {
					employeeQualificationBeans = bean.getEmployeeQualificationBeans();
					if(!MisUtility.ifEmpty(employeeQualificationBeans)){
						iteratorQualification = employeeQualificationBeans.iterator();					
						while(iteratorQualification.hasNext()){
							employeeQualificationBean = iteratorQualification.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(employeeQualificationBean.getMisAuditBean().getStatus())){
								iteratorQualification.remove();
								break;
							}
						}
					}
				}
			}
			
			Set<EmploymentHistoryBean> employmentHistoryBeans = null;
			Iterator<EmploymentHistoryBean> iteratorEmploymentHistory = null;
			EmploymentHistoryBean  employmentHistoryBean = null;
			if(!MisUtility.ifEmpty(employeeBeans)){
				for (EmployeeBean bean : employeeBeans) {
					employmentHistoryBeans = bean.getEmploymentHistoryBeans();
					if(!MisUtility.ifEmpty(employmentHistoryBeans)){
						iteratorEmploymentHistory = employmentHistoryBeans.iterator();					
						while(iteratorEmploymentHistory.hasNext()){
							employmentHistoryBean = iteratorEmploymentHistory.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(employmentHistoryBean.getMisAuditBean().getStatus())){
								iteratorEmploymentHistory.remove();
								break;
							}
						}
					}
				}
			}
			Set<EmployeePromotionHistoryBean> employeePromotionHistoryBeans = null;
			Iterator<EmployeePromotionHistoryBean> iteratorPromotionHistoryBeans = null;
			EmployeePromotionHistoryBean  employeePromotionHistoryBean = null;
			if(!MisUtility.ifEmpty(employeeBeans)){
				for (EmployeeBean bean : employeeBeans) {
					employeePromotionHistoryBeans = bean.getEmployeePromotionHistoryBeans();
					if(!MisUtility.ifEmpty(employeePromotionHistoryBeans)){
						iteratorPromotionHistoryBeans = employeePromotionHistoryBeans.iterator();					
						while(iteratorPromotionHistoryBeans.hasNext()){
							employeePromotionHistoryBean = iteratorPromotionHistoryBeans.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(employeePromotionHistoryBean.getMisAuditBean().getStatus())){
								iteratorPromotionHistoryBeans.remove();
								break;
							}
						}
					}
				}
			}
			
			Set<EmployeeContactExtendedBean> employeeContactExtendedBeans = null;
			Iterator<EmployeeContactExtendedBean> iteratorEmployeeContactExtendedBeans = null;
			EmployeeContactExtendedBean employeeContactExtendedBean = null;
			if(!MisUtility.ifEmpty(employeeBeans)){
				for(EmployeeBean employeeBean2 : employeeBeans){
					employeeContactExtendedBeans = employeeBean2.getEmployeeContactExtendedBeans();
					if(!MisUtility.ifEmpty(employeeContactExtendedBeans)){
						iteratorEmployeeContactExtendedBeans = employeeContactExtendedBeans.iterator();
						while(iteratorEmployeeContactExtendedBeans.hasNext()){
							employeeContactExtendedBean = iteratorEmployeeContactExtendedBeans.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(employeeContactExtendedBean.getMisAuditBean().getStatus())){
								iteratorEmployeeContactExtendedBeans.remove();
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
		
		return employeeBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveEmployee(EmployeeForm employeeForm, MISSessionBean misSessionBean) throws MISException {
		long employeeId = 0;
		try {
			List<String> statusFindList = new ArrayList<String>();
			statusFindList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusFindList.add(MISConstants.MASTER_STATUS_VERIFIED);
			EmployeeBean employeeFindBean = new EmployeeBean();
			employeeFindBean.setEmployeeName(employeeForm.getEmployeeName().trim());
			employeeFindBean.setFatherName(employeeForm.getFatherName().trim());
			employeeFindBean.setDateOfBirth(MisUtility.convertStringToDate(employeeForm.getDateOfBirth()));
			List<EmployeeBean> employeeFindBeans = employeeDao.findEmployee(employeeFindBean, statusFindList);
			if(!MisUtility.ifEmpty(employeeFindBeans)){
				throw new MISException(MISExceptionCodes.MIS001,"Employee : " + employeeForm.getEmployeeName()+" already exists");
			}
			EmployeeBean employeeBean = populateEmployeeBean(employeeForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			employeeBean.setMisAuditBean(misAuditBean);

			employeeId = employeeDao.saveEmployee(employeeBean);
			
			if(MisUtility.ifEmpty(employeeId)){
				
				List<EmploymentHistoryBean> employmentHistoryBeans = populateEmployeeHistoryBeans(employeeForm, employeeId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(employmentHistoryBeans)){
			
					boolean employeeHistoryStatus = employmentHistoryDao.saveOrUpdateEmploymentHistory(employmentHistoryBeans);
					if(!employeeHistoryStatus){
						log.error(employmentHistoryBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Employee History details");
					}
				}

				List<EmployeeQualificationBean> employeeQualificationBeans = populateEmployeeQualificationBeans(employeeForm, employeeId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(employeeQualificationBeans)){
					boolean employeeQualificationStatus = employeeQualificationDao.saveOrUpdateEmployeeQualification(employeeQualificationBeans);
					if(!employeeQualificationStatus){
						log.error(employeeQualificationBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Employee Qualification details");
					}
				}
			
				if(employeeForm.getEmployeeType().equals(MISConstants.MIS_EMPLOYEE_TYPE_PERMANENT)){
					List<EmployeePromotionHistoryBean> employeePromotionHistoryBeans = populateEmployeePromotionHistoryBeans(employeeForm, employeeId, misSessionBean,  MISConstants.MASTER_STATUS_VERIFIED);
					if(!MisUtility.ifEmpty(employeePromotionHistoryBeans)){
						boolean employeePromotionHistoryStatus = employeePromotionHistoryDao.saveOrUpdateEmployeePromotion(employeePromotionHistoryBeans);
						if(!employeePromotionHistoryStatus){
							log.error(employeeQualificationBeans);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Employee Promotion details");
						}
					}
				}
				if(employeeForm.getEmployeeType().equals(MISConstants.MIS_EMPLOYEE_TYPE_CONTRACTUAL)){
					List<EmployeeContactExtendedBean> employeeContactExtendedBeans = populateEmployeeContractExtentionBeans(employeeForm, employeeId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
					if(!MisUtility.ifEmpty(employeeContactExtendedBeans)){
						boolean employeeContractHistoryStatus = employeeContactExtendedDao.saveOrUpdateEmployeeContract(employeeContactExtendedBeans);
						if(!employeeContractHistoryStatus){
							log.error(employeeContactExtendedBeans);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Employee Contract History details");
						}
					}
					
				}
			
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

		return employeeId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateEmployee(EmployeeForm employeeForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		long employeeId = employeeForm.getEmployeeId();
		try {
			EmployeeBean employeeBean = populateEmployeeBean(employeeForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			employeeBean.setMisAuditBean(misAuditBean);
			
			status = employeeDao.updateEmployee(employeeBean);
			
			if(status && MisUtility.ifEmpty(employeeId)){
				List<EmploymentHistoryBean> employmentHistoryBeans = populateEmployeeHistoryBeans(employeeForm, employeeId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(employmentHistoryBeans)){
					boolean employeeHistoryStatus = employmentHistoryDao.saveOrUpdateEmploymentHistory(employmentHistoryBeans);
					if(!employeeHistoryStatus){
						log.error(employmentHistoryBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Employee History details");
					}
				}

				List<EmployeeQualificationBean> employeeQualificationBeans = populateEmployeeQualificationBeans(employeeForm, employeeId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(employeeQualificationBeans)){
					boolean employeeQualificationStatus = employeeQualificationDao.saveOrUpdateEmployeeQualification(employeeQualificationBeans);
					if(!employeeQualificationStatus){
						log.error(employeeQualificationBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Employee Qualification details");
					}
				}
				
				if(employeeForm.getEmployeeType().equals(MISConstants.MIS_EMPLOYEE_TYPE_PERMANENT)){
					List<EmployeePromotionHistoryBean> employeePromotionHistoryBeans = populateEmployeePromotionHistoryBeans(employeeForm, employeeId, misSessionBean,  MISConstants.MASTER_STATUS_VERIFIED);
					if(!MisUtility.ifEmpty(employeePromotionHistoryBeans)){
						boolean employeePromotionHistoryStatus = employeePromotionHistoryDao.saveOrUpdateEmployeePromotion(employeePromotionHistoryBeans);
						if(!employeePromotionHistoryStatus){
							log.error(employeePromotionHistoryBeans);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Employee Promotion details");
						}
					}
				}
				
				if(employeeForm.getEmployeeType().equals(MISConstants.MIS_EMPLOYEE_TYPE_CONTRACTUAL)){
					List<EmployeeContactExtendedBean> employeeContactExtendedBeans = populateEmployeeContractExtentionBeans(employeeForm, employeeId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
					if(!MisUtility.ifEmpty(employeeContactExtendedBeans)){
						boolean employeeContractHistoryStatus = employeeContactExtendedDao.saveOrUpdateEmployeeContract(employeeContactExtendedBeans);
						if(!employeeContractHistoryStatus){
							log.error(employeeContactExtendedBeans);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Employee Contract History details");
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
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteEmployee(EmployeeForm employeeForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
		try {
//			List<EmployeeBean> employeeBeans = employeeDao.findEmployee(Arrays.asList(employeeForm.getEmployeeIds()));
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean = populateEmployeeBean(employeeForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			employeeBean.setMisAuditBean(misAuditBean);
			EmployeeQualificationBean employeeQualificationBean = new EmployeeQualificationBean();
			employeeQualificationBean.setEmployeeId(employeeForm.getEmployeeId());
			EmploymentHistoryBean employmentHistoryBean = new EmploymentHistoryBean();
			employmentHistoryBean.setEmployeeId(employeeForm.getEmployeeId());
			EmployeePromotionHistoryBean employeePromotionHistoryBean = new EmployeePromotionHistoryBean();
			employeePromotionHistoryBean.setEmployeeId(employeeForm.getEmployeeId());
			
			EmployeeContactExtendedBean employeeContactExtendedBean = new EmployeeContactExtendedBean();
			employeeContactExtendedBean.setEmployeeId(employeeForm.getEmployeeId());
			
			List<String> statuslist = new ArrayList<String>();
			statuslist.add(MISConstants.MASTER_STATUS_VERIFIED);
				result = employeeDao.updateEmployee(employeeBean);
				if(result){
					List<EmployeeQualificationBean> employeeQualificationBeans = employeeQualificationDao.findEmployeeQualification(employeeQualificationBean, statuslist);
					if(!MisUtility.ifEmpty(employeeQualificationBeans)){
						for (EmployeeQualificationBean employeeQualificationBean2 : employeeQualificationBeans) {
							employeeQualificationBean2.setMisAuditBean(misAuditBean);
						}
						boolean employeeQualificationStatus = employeeQualificationDao.saveOrUpdateEmployeeQualification(employeeQualificationBeans);
						if(!employeeQualificationStatus){
							log.error(employeeQualificationBeans);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Employee Qualification details for employee "+employeeBean.getEmployeeId());
						}

					}

					List<EmploymentHistoryBean> employmentHistoryBeans = employmentHistoryDao.findEmploymentHistory(employmentHistoryBean, statuslist);
					if(!MisUtility.ifEmpty(employmentHistoryBeans)){
						for (EmploymentHistoryBean employmentHistoryBean2 : employmentHistoryBeans) {
							employmentHistoryBean2.setMisAuditBean(misAuditBean);
						}
						boolean employeeHistoryStatus = employmentHistoryDao.saveOrUpdateEmploymentHistory(employmentHistoryBeans);
						if(!employeeHistoryStatus){
							log.error(employmentHistoryBeans);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Employee History details for employee "+employeeBean.getEmployeeId());
						}

					}
				
				
				List<EmployeePromotionHistoryBean> employeePromotionHistoryBeans = employeePromotionHistoryDao.findEmployeePromotion(employeePromotionHistoryBean, statuslist);
				if(!MisUtility.ifEmpty(employeePromotionHistoryBeans)){
					for (EmployeePromotionHistoryBean employeePromotionHistoryBean2 : employeePromotionHistoryBeans) {
						employeePromotionHistoryBean2.setMisAuditBean(misAuditBean);
					}
					boolean employeePromotionHistoryStatus = employeePromotionHistoryDao.saveOrUpdateEmployeePromotion(employeePromotionHistoryBeans);
					if(!employeePromotionHistoryStatus){
						log.error(employeePromotionHistoryStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Employee Promotion details");
					}

				}
				
				List<EmployeeContactExtendedBean> employeeContactExtendedBeans  = employeeContactExtendedDao.findEmployeeContractExtention(employeeContactExtendedBean, statuslist);
				if(!MisUtility.ifEmpty(employeeContactExtendedBeans)){
					for(EmployeeContactExtendedBean employeeContactExtendedBean2  : employeeContactExtendedBeans){
						employeeContactExtendedBean2.setMisAuditBean(misAuditBean);
					}
					boolean employeeContractExtentionHistoryStatus = employeeContactExtendedDao.saveOrUpdateEmployeeContract(employeeContactExtendedBeans);
					if(!employeeContractExtentionHistoryStatus){
						log.error(employeeContractExtentionHistoryStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Employee Contract History details");
					}
				}
			}
			else{
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Employee details for employee "+employeeBean.getEmployeeId());
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
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postEmployee(EmployeeForm employeeForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
		try {
			EmployeeBean bean = new EmployeeBean();
			List<String> statuslist = new ArrayList<String>();
			statuslist.add(MISConstants.MASTER_STATUS_VERIFIED);
			bean = findEmployee(employeeForm, statuslist).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = bean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			bean.setMisAuditBean(misAuditBean);
			EmployeeQualificationBean employeeQualificationBean = new EmployeeQualificationBean();
			employeeQualificationBean.setEmployeeId(employeeForm.getEmployeeId());
			EmploymentHistoryBean employmentHistoryBean = new EmploymentHistoryBean();
			employmentHistoryBean.setEmployeeId(employeeForm.getEmployeeId());
			EmployeePromotionHistoryBean employeePromotionHistoryBean = new EmployeePromotionHistoryBean();
			employeePromotionHistoryBean.setEmployeeId(employeeForm.getEmployeeId());
			
			EmployeeContactExtendedBean employeeContactExtendedBean = new EmployeeContactExtendedBean();
			employeeContactExtendedBean.setEmployeeId(employeeForm.getEmployeeId());
//			
				result = employeeDao.updateEmployee(bean);
				if(result){
					List<EmployeeQualificationBean> employeeQualificationBeans = employeeQualificationDao.findEmployeeQualification(employeeQualificationBean, statuslist);
					if(!MisUtility.ifEmpty(employeeQualificationBeans)){
						for (EmployeeQualificationBean employeeQualificationBean2 : employeeQualificationBeans) {
							misAuditBean = employeeQualificationBean2.getMisAuditBean();
							misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
							misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
							misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
							employeeQualificationBean2.setMisAuditBean(misAuditBean);
						}
						boolean employeeQualificationStatus = employeeQualificationDao.saveOrUpdateEmployeeQualification(employeeQualificationBeans);
						if(!employeeQualificationStatus){
							log.error(employeeQualificationBeans);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Employee Qualification details for employee "+bean.getEmployeeId());
						}

					}
//
					List<EmploymentHistoryBean> employmentHistoryBeans = employmentHistoryDao.findEmploymentHistory(employmentHistoryBean, statuslist);
					if(!MisUtility.ifEmpty(employmentHistoryBeans)){
						for (EmploymentHistoryBean employmentHistoryBean2 : employmentHistoryBeans) {
							misAuditBean = employmentHistoryBean2.getMisAuditBean();
							misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
							misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
							misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
							employmentHistoryBean2.setMisAuditBean(misAuditBean);
						}
						boolean employeeHistoryStatus = employmentHistoryDao.saveOrUpdateEmploymentHistory(employmentHistoryBeans);
						if(!employeeHistoryStatus){
							log.error(employmentHistoryBeans);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Employee History details for employee "+bean.getEmployeeId());
						}

					}
					
					List<EmployeePromotionHistoryBean> employeePromotionHistoryBeans = employeePromotionHistoryDao.findEmployeePromotion(employeePromotionHistoryBean, statuslist);
					if(!MisUtility.ifEmpty(employeePromotionHistoryBeans)){
						for (EmployeePromotionHistoryBean employeePromotionHistoryBean2 : employeePromotionHistoryBeans) {
							misAuditBean = employeePromotionHistoryBean2.getMisAuditBean();
							misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
							misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
							misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
							employeePromotionHistoryBean2.setMisAuditBean(misAuditBean);
						}
						boolean employeePromotionHistoryStatus = employeePromotionHistoryDao.saveOrUpdateEmployeePromotion(employeePromotionHistoryBeans);
						if(!employeePromotionHistoryStatus){
							log.error(employeePromotionHistoryStatus);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Employee Promotion details");
						}

					}
					
					List<EmployeeContactExtendedBean> employeeContactExtendedBeans = employeeContactExtendedDao.findEmployeeContractExtention(employeeContactExtendedBean, statuslist);
					if(!MisUtility.ifEmpty(employeeContactExtendedBeans)){
						for(EmployeeContactExtendedBean employeeContactExtendedBean2  : employeeContactExtendedBeans){
							misAuditBean = employeeContactExtendedBean2.getMisAuditBean();
							misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
							misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
							misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
							employeeContactExtendedBean2.setMisAuditBean(misAuditBean);
						}
						boolean employeeContractExtentionHistoryStatus = employeeContactExtendedDao.saveOrUpdateEmployeeContract(employeeContactExtendedBeans);
						if(!employeeContractExtentionHistoryStatus){
							log.error(employeeContractExtentionHistoryStatus);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Employee Contract History details");
						}
					}
					
				}else{
					throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Employee details for employee "+bean.getEmployeeId());
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
	
	private EmployeeBean populateEmployeeBean(EmployeeForm employeeForm){
		
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.setEmployeeId(employeeForm.getEmployeeId());
		employeeBean.setCategory(employeeForm.getCategory());
		employeeBean.setPayscale(employeeForm.getPayScale());
		employeeBean.setEmployeeName(employeeForm.getEmployeeName().trim());
		employeeBean.setEmployeeType(employeeForm.getEmployeeType());
		employeeBean.setAddressLine1(employeeForm.getAddressLine1());
		employeeBean.setAddressLine2(employeeForm.getAddressLine2());
		employeeBean.setAppointmentNo(employeeForm.getAppointmentNo());
		employeeBean.setCity(employeeForm.getCity());
		
		if(employeeForm.getEmployeeType().equals(MISConstants.MIS_EMPLOYEE_TYPE_CONTRACTUAL)){
		employeeBean.setContractEndDate(MisUtility.convertStringToDate(employeeForm.getContractEndDate()));
		employeeBean.setContractStartDate(MisUtility.convertStringToDate(employeeForm.getContractStartDate()));
		employeeBean.setContractExtendedUpto(MisUtility.convertStringToDate(employeeForm.getContractExtendedUpto()));
		}
		employeeBean.setSpecialization(employeeForm.getSpecialization());
		employeeBean.setDateOfBirth(MisUtility.convertStringToDate(employeeForm.getDateOfBirth().trim()));
		employeeBean.setDesignationId(getDesignationName(employeeForm));
		employeeBean.setDesigId(Integer.parseInt(employeeForm.getDesignation()));
		employeeBean.setLocationId(employeeForm.getLocationId());
		employeeBean.setFatherName(employeeForm.getFatherName().trim());
		employeeBean.setGender(employeeForm.getGender());
		employeeBean.setJoiningDate(MisUtility.convertStringToDate(employeeForm.getJoiningDate()));
		employeeBean.setMaritalStatus(employeeForm.getMaritalStatus());
		employeeBean.setMobilePhone(employeeForm.getMobilePhoneNo());
		employeeBean.setNationality(employeeForm.getNationality());
		employeeBean.setOfficialEmailId(employeeForm.getOfficeEmail());
		employeeBean.setPanNo(employeeForm.getPanNo());
		employeeBean.setPermanentEmployeeId(employeeForm.getPermanentEmployeeId());
		employeeBean.setPersonalEmailId(employeeForm.getPersonalEmail());
		employeeBean.setPin(employeeForm.getPinCode());
		employeeBean.setSanctionNo(employeeForm.getSanctionNo());
		employeeBean.setState(employeeForm.getState());
		employeeBean.setStreet(employeeForm.getStreet());
		employeeBean.setWorkPhone(employeeForm.getWorkPhoneNo());
		employeeBean.setAppointedWing(employeeForm.getAppointedWing());
		employeeBean.setEmployeeType(employeeForm.getEmployeeType());
//		employeeBean.setReportingOfficerLoactionId(employeeForm.getReportingOfficerLocation());
		employeeBean.setReportingOfficerId(employeeForm.getReportingOfficerId());
		employeeBean.setContractExtentionNo(employeeForm.getContractExtentionNo());
		employeeBean.setContractExtentionDate(MisUtility.convertStringToDate(employeeForm.getContractExtentionDate()));
		employeeBean.setRetirementDate(MisUtility.convertStringToDate(employeeForm.getRetirementDate()));
		return employeeBean;
	}
	
	@SuppressWarnings("unchecked")
	private List<EmploymentHistoryBean> populateEmployeeHistoryBeans(EmployeeForm employeeForm, long employeeId, MISSessionBean misSessionBean, String status){
		
			MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		
		List<EmploymentHistoryBean> employmentHistoryBeans = new ArrayList<EmploymentHistoryBean>();
		EmploymentHistoryBean employmentHistoryBean = null;
		Datagrid employeeHistoryGrid = employeeForm.getEmployeeHistoryGrid();
		Collection<EmployementHistoryGridBean> addedEmployeeHistoryGridBeans = employeeHistoryGrid.getAddedData();
		if(!MisUtility.ifEmpty(addedEmployeeHistoryGridBeans)){
			for (EmployementHistoryGridBean employementHistoryGridBean : addedEmployeeHistoryGridBeans) {
				employmentHistoryBean = new EmploymentHistoryBean();
				employmentHistoryBean.setCompanyName(employementHistoryGridBean.getCompanyName());
				employmentHistoryBean.setDurationInMonths(employementHistoryGridBean.getDurationInMonths());
				
				EmployeeBean employeeBean = new EmployeeBean();
				employeeBean.setEmployeeId(employeeId);
//		employmentHistoryBean.setEmployeeBean(employeeBean);
				employmentHistoryBean.setFromDate(MisUtility.convertStringToDate(employementHistoryGridBean.getFromDate()));
				employmentHistoryBean.setEmployeeId(employeeId);
				employmentHistoryBean.setMisAuditBean(misAuditBean);
				employmentHistoryBean.setCompanyName(employementHistoryGridBean.getCompanyName());
				System.out.println(employementHistoryGridBean.getCompanyName());
				employmentHistoryBean.setNatureOfBusiness(employementHistoryGridBean.getNatureOfBusiness());
				employmentHistoryBean.setReasonForLeaving(employementHistoryGridBean.getReasonForLeaving());
				employmentHistoryBean.setDurationInMonths(employementHistoryGridBean.getDurationInMonths());
				employmentHistoryBean.setToDate(MisUtility.convertStringToDate(employementHistoryGridBean.getToDate()));
				employmentHistoryBean.setFromDate(MisUtility.convertStringToDate(employementHistoryGridBean.getFromDate()));
				employmentHistoryBean.setId(employementHistoryGridBean.getId());
				employmentHistoryBeans.add(employmentHistoryBean);
			}
		}
		
		Collection<EmployementHistoryGridBean> modifiedEmployeeHistoryGridBeans = employeeHistoryGrid.getModifiedData();
		if(!MisUtility.ifEmpty(modifiedEmployeeHistoryGridBeans)){
			for (EmployementHistoryGridBean employementHistoryGridBean : modifiedEmployeeHistoryGridBeans) {
				employmentHistoryBean = new EmploymentHistoryBean();
				employmentHistoryBean.setCompanyName(employementHistoryGridBean.getCompanyName());
				employmentHistoryBean.setDurationInMonths(employementHistoryGridBean.getDurationInMonths());
				
				EmployeeBean employeeBean = new EmployeeBean();
				employeeBean.setEmployeeId(employeeId);
//				employmentHistoryBean.setEmployeeBean(employeeBean);
				employmentHistoryBean.setEmployeeId(employeeId);
				employmentHistoryBean.setFromDate(MisUtility.convertStringToDate(employementHistoryGridBean.getFromDate()));
				employmentHistoryBean.setMisAuditBean(misAuditBean);
			
				employmentHistoryBean.setNatureOfBusiness(employementHistoryGridBean.getNatureOfBusiness());
				employmentHistoryBean.setReasonForLeaving(employementHistoryGridBean.getReasonForLeaving());
				employmentHistoryBean.setToDate(MisUtility.convertStringToDate(employementHistoryGridBean.getToDate()));
				employmentHistoryBean.setId(employementHistoryGridBean.getId());
				employmentHistoryBeans.add(employmentHistoryBean);
			}
		}
		
		Collection<EmployementHistoryGridBean> deleteEmployeeHistoryGridBeans = employeeHistoryGrid.getDeletedData();
		if(!MisUtility.ifEmpty(deleteEmployeeHistoryGridBeans)){
			for (EmployementHistoryGridBean employementHistoryGridBean : deleteEmployeeHistoryGridBeans) {
				employmentHistoryBean = new EmploymentHistoryBean();
				employmentHistoryBean.setCompanyName(employementHistoryGridBean.getCompanyName());
				employmentHistoryBean.setDurationInMonths(employementHistoryGridBean.getDurationInMonths());
				
				EmployeeBean employeeBean = new EmployeeBean();
				employeeBean.setEmployeeId(employeeId);
//				employmentHistoryBean.setEmployeeBean(employeeBean);
				employmentHistoryBean.setEmployeeId(employeeId);
				employmentHistoryBean.setFromDate(MisUtility.convertStringToDate(employementHistoryGridBean.getFromDate()));
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				employmentHistoryBean.setMisAuditBean(misAuditBean);
				employmentHistoryBean.setNatureOfBusiness(employementHistoryGridBean.getNatureOfBusiness());
				employmentHistoryBean.setReasonForLeaving(employementHistoryGridBean.getReasonForLeaving());
				employmentHistoryBean.setToDate(MisUtility.convertStringToDate(employementHistoryGridBean.getToDate()));
				employmentHistoryBean.setId(employementHistoryGridBean.getId());
				employmentHistoryBeans.add(employmentHistoryBean);
			}
		}
		return employmentHistoryBeans;
	}
	
	@SuppressWarnings("unchecked")
	private List<EmployeePromotionHistoryBean> populateEmployeePromotionHistoryBeans(EmployeeForm employeeForm, long employeeId, MISSessionBean misSessionBean, String status){
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		
		List<EmployeePromotionHistoryBean> employeePromotionHistoryBeans = new ArrayList<EmployeePromotionHistoryBean>();
		EmployeePromotionHistoryBean employeePromotionHistoryBean = null;
		
		
		
		Collection<EmployeePromotionHistoryGridBean> addedEmployeePromotionHistoryGridBeans = employeeForm.getEmployeePromotionGrid().getAddedData();
		if(!MisUtility.ifEmpty(addedEmployeePromotionHistoryGridBeans)){
			for (EmployeePromotionHistoryGridBean employeePromotionHistoryGridBean : addedEmployeePromotionHistoryGridBeans) {
				employeePromotionHistoryBean = new EmployeePromotionHistoryBean();
				employeePromotionHistoryBean.setEmployeeId(employeeId);
				employeePromotionHistoryBean.setMisAuditBean(misAuditBean);
				employeePromotionHistoryBean.setPayrollExtended(employeePromotionHistoryGridBean.getPayrollExtended());
				employeePromotionHistoryBean.setDateOfPromotion(MisUtility.convertStringToDate(employeePromotionHistoryGridBean.getDateOfPromotion()));
				employeePromotionHistoryBean.setId(employeePromotionHistoryGridBean.getId());
				employeePromotionHistoryBean.setPromotedDesignation(employeePromotionHistoryGridBean.getPromotedDesignation());
				employeePromotionHistoryBeans.add(employeePromotionHistoryBean);
			}
		}
		
		Collection<EmployeePromotionHistoryGridBean> modifiedEmployeePromotionHistoryGridBeans = employeeForm.getEmployeePromotionGrid().getModifiedData();
		if(!MisUtility.ifEmpty(modifiedEmployeePromotionHistoryGridBeans)){
			for (EmployeePromotionHistoryGridBean employeePromotionHistoryGridBean : modifiedEmployeePromotionHistoryGridBeans) {
				employeePromotionHistoryBean = new EmployeePromotionHistoryBean();
				employeePromotionHistoryBean.setEmployeeId(employeeId);
				employeePromotionHistoryBean.setMisAuditBean(misAuditBean);
				employeePromotionHistoryBean.setPayrollExtended(employeePromotionHistoryGridBean.getPayrollExtended());
				employeePromotionHistoryBean.setDateOfPromotion(MisUtility.convertStringToDate(employeePromotionHistoryGridBean.getDateOfPromotion()));
				employeePromotionHistoryBean.setId(employeePromotionHistoryGridBean.getId());
				employeePromotionHistoryBean.setPromotedDesignation(employeePromotionHistoryGridBean.getPromotedDesignation());
				employeePromotionHistoryBeans.add(employeePromotionHistoryBean);
			}
		}
		
		Collection<EmployeePromotionHistoryGridBean> deleteEmployeePromotionHistoryGridBeans = employeeForm.getEmployeePromotionGrid().getDeletedData();
		if(!MisUtility.ifEmpty(deleteEmployeePromotionHistoryGridBeans)){
			if(!MisUtility.ifEmpty(deleteEmployeePromotionHistoryGridBeans)){
				for (EmployeePromotionHistoryGridBean employeePromotionHistoryGridBean : deleteEmployeePromotionHistoryGridBeans) {
					employeePromotionHistoryBean = new EmployeePromotionHistoryBean();
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
					employeePromotionHistoryBean.setMisAuditBean(misAuditBean);
					employeePromotionHistoryBean.setPayrollExtended(employeePromotionHistoryGridBean.getPayrollExtended());
					employeePromotionHistoryBean.setEmployeeId(employeeId);
					employeePromotionHistoryBean.setDateOfPromotion(MisUtility.convertStringToDate(employeePromotionHistoryGridBean.getDateOfPromotion()));
					employeePromotionHistoryBean.setId(employeePromotionHistoryGridBean.getId());
					employeePromotionHistoryBean.setPromotedDesignation(employeePromotionHistoryGridBean.getPromotedDesignation());
					employeePromotionHistoryBeans.add(employeePromotionHistoryBean);
				}
			}
		}
		return employeePromotionHistoryBeans;
	}
	@SuppressWarnings("unchecked")
	private List<EmployeeContactExtendedBean> populateEmployeeContractExtentionBeans(EmployeeForm employeeForm, long employeeId, MISSessionBean misSessionBean, String status){
		
		System.out.println("Inside Contract extention");
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		
		List<EmployeeContactExtendedBean> employeeContactExtendedBeans = new ArrayList<EmployeeContactExtendedBean>();
		EmployeeContactExtendedBean employeeContactExtendedBean = null;
		
		
		Collection<EmployeeContactExtendedGridBean> addedEmployeeContactExtendedGridBeans = employeeForm.getContractExtentionGrid().getAddedData();
		if(!MisUtility.ifEmpty(addedEmployeeContactExtendedGridBeans)){
			System.out.println("added");
			for(EmployeeContactExtendedGridBean employeeContactExtendedGridBean : addedEmployeeContactExtendedGridBeans ){
				employeeContactExtendedBean = new EmployeeContactExtendedBean();
				employeeContactExtendedBean.setEmployeeId(employeeId);
				employeeContactExtendedBean.setMisAuditBean(misAuditBean);
				employeeContactExtendedBean.setExtentionContarctNo(employeeContactExtendedGridBean.getExtentionContarctNo());
				employeeContactExtendedBean.setExtentionContractDate(MisUtility.convertStringToDate(employeeContactExtendedGridBean.getExtentionContractDate()));
				employeeContactExtendedBean.setExtentionContractUpto(MisUtility.convertStringToDate(employeeContactExtendedGridBean.getExtentionContractUpto()));
				employeeContactExtendedBean.setId(employeeContactExtendedGridBean.getId());
				employeeContactExtendedBeans.add(employeeContactExtendedBean);
			}
		}
		 
		Collection<EmployeeContactExtendedGridBean> modifiedEmployeeContactExtendedGridBean = employeeForm.getContractExtentionGrid().getModifiedData();
		if(!MisUtility.ifEmpty(modifiedEmployeeContactExtendedGridBean)){
			System.out.println("modified");
			for(EmployeeContactExtendedGridBean employeeContactExtendedGridBean : modifiedEmployeeContactExtendedGridBean){
				employeeContactExtendedBean = new EmployeeContactExtendedBean();
				employeeContactExtendedBean.setEmployeeId(employeeId);
				employeeContactExtendedBean.setMisAuditBean(misAuditBean);
				employeeContactExtendedBean.setExtentionContarctNo(employeeContactExtendedGridBean.getExtentionContarctNo());
				employeeContactExtendedBean.setExtentionContractDate(MisUtility.convertStringToDate(employeeContactExtendedGridBean.getExtentionContractDate()));
				employeeContactExtendedBean.setExtentionContractUpto(MisUtility.convertStringToDate(employeeContactExtendedGridBean.getExtentionContractUpto()));
				employeeContactExtendedBean.setId(employeeContactExtendedGridBean.getId());
				employeeContactExtendedBeans.add(employeeContactExtendedBean);
			}
		}
		
		Collection<EmployeeContactExtendedGridBean> deletedEmployeeContactExtendedGridBean = employeeForm.getContractExtentionGrid().getDeletedData();
		if(!MisUtility.ifEmpty(deletedEmployeeContactExtendedGridBean)){
			System.out.println("deleted");
			for(EmployeeContactExtendedGridBean employeeContactExtendedGridBean : deletedEmployeeContactExtendedGridBean){
				employeeContactExtendedBean = new EmployeeContactExtendedBean();
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				employeeContactExtendedBean.setMisAuditBean(misAuditBean);
				employeeContactExtendedBean.setExtentionContarctNo(employeeContactExtendedGridBean.getExtentionContarctNo());
				employeeContactExtendedBean.setEmployeeId(employeeId);
				employeeContactExtendedBean.setExtentionContractDate(MisUtility.convertStringToDate(employeeContactExtendedGridBean.getExtentionContractDate()));
				employeeContactExtendedBean.setExtentionContractUpto(MisUtility.convertStringToDate(employeeContactExtendedGridBean.getExtentionContractUpto()));
				employeeContactExtendedBean.setId(employeeContactExtendedGridBean.getId());
				employeeContactExtendedBeans.add(employeeContactExtendedBean);
			}
		}
		
		return employeeContactExtendedBeans;
	}
	
	@SuppressWarnings("unchecked")
	private List<EmployeeQualificationBean> populateEmployeeQualificationBeans(EmployeeForm employeeForm, long employeeId, MISSessionBean misSessionBean, String status){
		
		List<EmployeeQualificationBean> employeeQualificationBeans = new ArrayList<EmployeeQualificationBean>();
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		
		Collection<EmployeeQualificationBean> addedEmployeeQualificationBeans = employeeForm.getEmployeeQualificationGrid().getAddedData();
		if(!MisUtility.ifEmpty(addedEmployeeQualificationBeans)){
			for (EmployeeQualificationBean employeeQualificationBean : addedEmployeeQualificationBeans) {
				employeeQualificationBean.setMisAuditBean(misAuditBean);
				employeeQualificationBean.setEmployeeId(employeeId);
				employeeQualificationBeans.add(employeeQualificationBean);
			}
		}
		
		Collection<EmployeeQualificationBean> modifiedEmployeeQualificationBeans = employeeForm.getEmployeeQualificationGrid().getModifiedData();
		if(!MisUtility.ifEmpty(modifiedEmployeeQualificationBeans)){
			for (EmployeeQualificationBean employeeQualificationBean : modifiedEmployeeQualificationBeans) {
				employeeQualificationBean.setMisAuditBean(misAuditBean);
				employeeQualificationBean.setEmployeeId(employeeId);
				employeeQualificationBeans.add(employeeQualificationBean);
				
			}
		}
		
		Collection<EmployeeQualificationBean> deletedEmployeeQualificationBeans = employeeForm.getEmployeeQualificationGrid().getDeletedData();
		if(!MisUtility.ifEmpty(deletedEmployeeQualificationBeans)){
			for (EmployeeQualificationBean employeeQualificationBean : deletedEmployeeQualificationBeans) {
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				employeeQualificationBean.setMisAuditBean(misAuditBean);
				employeeQualificationBean.setEmployeeId(employeeId);
				employeeQualificationBeans.add(employeeQualificationBean);
			}
		}
		
		return employeeQualificationBeans;
	}
	
	private String getDesignationName(EmployeeForm employeeForm){
		List<EmployeeDesignationBean> designationBeans = null;
		EmployeeDesignationBean employeeDesignationBean = new EmployeeDesignationBean();
		employeeDesignationBean.setDesignationId(Integer.parseInt(employeeForm.getDesignation()));
		designationBeans = employeeDesignationDao.getEmployeeDesignation(employeeDesignationBean);
		
		String designationName = designationBeans.get(0).getDesignationName();
		return designationName;
		
	}

}
