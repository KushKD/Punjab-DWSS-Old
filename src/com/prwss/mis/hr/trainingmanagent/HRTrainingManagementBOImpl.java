package com.prwss.mis.hr.trainingmanagent;

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
import com.prwss.mis.hr.trainingmanagent.dao.HRTrainingManagementDao;
import com.prwss.mis.hr.trainingmanagent.struts.HRTrainingManagementForm;
import com.prwss.mis.hr.trainingmanagent.struts.HRTrainingManagementGridBean;

public class HRTrainingManagementBOImpl implements HRTrainingManagementBO {
	
	private Logger log = Logger.getLogger(HRTrainingManagementBOImpl.class);
	private HRTrainingManagementDao hrTrainingManagementDao;
	
	public void setHrTrainingManagementDao(
			HRTrainingManagementDao hrTrainingManagementDao) {
		this.hrTrainingManagementDao = hrTrainingManagementDao;
	}

	@Override
	public List<HRTrainingManagementBean> findHRTrainingManagementBean(
			HRTrainingManagementForm hrTrainingManagementForm,
			List<String> statusList) throws MISException {
		List<HRTrainingManagementBean> hrTrainingManagementBeans = null;
		try {
			System.out.println("IN BO IMPL");
			HRTrainingManagementBean hrTrainingManagementBean = new HRTrainingManagementBean();
			if(MisUtility.ifEmpty(hrTrainingManagementForm.getTrainingPlanId())&& MisUtility.ifEmpty(hrTrainingManagementForm.getTrainingObjective())){
				hrTrainingManagementBean.setTrainingPlanId(hrTrainingManagementForm.getTrainingPlanId());
				hrTrainingManagementBean.setTrainingObjective(hrTrainingManagementForm.getTrainingObjective());
				hrTrainingManagementBeans = hrTrainingManagementDao.findHRTrainingManagementBeans(hrTrainingManagementBean, statusList);
				System.out.println("------------------"+hrTrainingManagementBeans);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return hrTrainingManagementBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateHRTrainingManagementBean(
			HRTrainingManagementForm hrTrainingManagementForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			List<HRTrainingManagementBean> hrTrainingManagementBeans = populateHRTrainingManagementBeans(hrTrainingManagementForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(hrTrainingManagementBeans)){
				status = hrTrainingManagementDao.saveOrUpdateHRTrainingManagementBeans(hrTrainingManagementBeans);
				if(!status){
					log.error(hrTrainingManagementBeans);
					throw new MISException(MISExceptionCodes.MIS003, "Failed to Update HR Training Management details");
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw new MISException(e);
		}
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postHRTrainingManagementBean(
			HRTrainingManagementForm hrTrainingManagementForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		List<HRTrainingManagementBean> hrTrainingManagementBeans =null;
		List<String>statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		MISAuditBean misAuditBean = new MISAuditBean();
		try {
			HRTrainingManagementBean hrTrainingManagementBean = new HRTrainingManagementBean();
			hrTrainingManagementBean.setTrainingPlanId(hrTrainingManagementForm.getTrainingPlanId());
			hrTrainingManagementBean.setTrainingObjective(hrTrainingManagementForm.getTrainingObjective());
			hrTrainingManagementBeans = hrTrainingManagementDao.findHRTrainingManagementBeans(hrTrainingManagementBean, statusList);
			if(!MisUtility.ifEmpty(hrTrainingManagementBeans)){
				for (HRTrainingManagementBean hrTrainingManagementBean2 : hrTrainingManagementBeans) {
					misAuditBean = hrTrainingManagementBean2.getMisAuditBean();
					misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
					misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
					hrTrainingManagementBean2.setMisAuditBean(misAuditBean);
				}
				status = hrTrainingManagementDao.saveOrUpdateHRTrainingManagementBeans(hrTrainingManagementBeans);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw new MISException(e);
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	private List<HRTrainingManagementBean> populateHRTrainingManagementBeans(HRTrainingManagementForm  hrTrainingManagementForm, MISSessionBean misSessionBean, String status){
		List <HRTrainingManagementBean> hrTrainingManagementBeans = new ArrayList<HRTrainingManagementBean>();
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
			
			
			HRTrainingManagementBean hrTrainingManagementBean = null;
			
			Collection<HRTrainingManagementGridBean> addedHRTrainingManagementGridBeans = hrTrainingManagementForm.getEmployeeTrainingDetailDatagrid().getAddedData();
			if(!MisUtility.ifEmpty(addedHRTrainingManagementGridBeans)){
				for (HRTrainingManagementGridBean employeeTrainingGridBean : addedHRTrainingManagementGridBeans) {
					hrTrainingManagementBean = new HRTrainingManagementBean();
					hrTrainingManagementBean.setId(employeeTrainingGridBean.getId());
					hrTrainingManagementBean.setEmployeeId(employeeTrainingGridBean.getEmployeeId());
					hrTrainingManagementBean.setFromDate(MisUtility.convertStringToDate(employeeTrainingGridBean.getFromDate()));
					hrTrainingManagementBean.setToDate(MisUtility.convertStringToDate(employeeTrainingGridBean.getToDate()));
					hrTrainingManagementBean.setRemarks(employeeTrainingGridBean.getRemarks());
					hrTrainingManagementBean.setTrainingVenue(employeeTrainingGridBean.getTrainingVenue());
					hrTrainingManagementBean.setTrainingObjective(hrTrainingManagementForm.getTrainingObjective());
					hrTrainingManagementBean.setTrainingPlanId(hrTrainingManagementForm.getTrainingPlanId());
					hrTrainingManagementBean.setLocationId(hrTrainingManagementForm.getLocationId());
					hrTrainingManagementBean.setMisAuditBean(misAuditBean);
					hrTrainingManagementBeans.add(hrTrainingManagementBean);
				}
			}
		
			Collection<HRTrainingManagementGridBean> modifiedHRTrainingManagementGridBeans = hrTrainingManagementForm.getEmployeeTrainingDetailDatagrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedHRTrainingManagementGridBeans)){
				for (HRTrainingManagementGridBean employeeTrainingGridBean : modifiedHRTrainingManagementGridBeans) {
					hrTrainingManagementBean = new HRTrainingManagementBean();
					hrTrainingManagementBean.setId(employeeTrainingGridBean.getId());
					hrTrainingManagementBean.setEmployeeId(employeeTrainingGridBean.getEmployeeId());
					hrTrainingManagementBean.setFromDate(MisUtility.convertStringToDate(employeeTrainingGridBean.getFromDate()));
					hrTrainingManagementBean.setToDate(MisUtility.convertStringToDate(employeeTrainingGridBean.getToDate()));
					hrTrainingManagementBean.setRemarks(employeeTrainingGridBean.getRemarks());
					hrTrainingManagementBean.setTrainingVenue(employeeTrainingGridBean.getTrainingVenue());
					hrTrainingManagementBean.setTrainingObjective(hrTrainingManagementForm.getTrainingObjective());
					hrTrainingManagementBean.setTrainingPlanId(hrTrainingManagementForm.getTrainingPlanId());
					hrTrainingManagementBean.setLocationId(hrTrainingManagementForm.getLocationId());
					hrTrainingManagementBean.setMisAuditBean(misAuditBean);
					hrTrainingManagementBeans.add(hrTrainingManagementBean);
				}
			}
			
			Collection<HRTrainingManagementGridBean> deletedHRTrainingManagementGridBeans = hrTrainingManagementForm.getEmployeeTrainingDetailDatagrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedHRTrainingManagementGridBeans)){
				for (HRTrainingManagementGridBean employeeTrainingGridBean : deletedHRTrainingManagementGridBeans) {
					hrTrainingManagementBean = new HRTrainingManagementBean();
					hrTrainingManagementBean.setId(employeeTrainingGridBean.getId());
					hrTrainingManagementBean.setEmployeeId(employeeTrainingGridBean.getEmployeeId());
					hrTrainingManagementBean.setFromDate(MisUtility.convertStringToDate(employeeTrainingGridBean.getFromDate()));
					hrTrainingManagementBean.setToDate(MisUtility.convertStringToDate(employeeTrainingGridBean.getToDate()));
					hrTrainingManagementBean.setRemarks(employeeTrainingGridBean.getRemarks());
					hrTrainingManagementBean.setTrainingVenue(employeeTrainingGridBean.getTrainingVenue());
					hrTrainingManagementBean.setTrainingObjective(hrTrainingManagementForm.getTrainingObjective());
					hrTrainingManagementBean.setTrainingPlanId(hrTrainingManagementForm.getTrainingPlanId());
					hrTrainingManagementBean.setLocationId(hrTrainingManagementForm.getLocationId());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
					hrTrainingManagementBean.setMisAuditBean(misAuditBean);
					hrTrainingManagementBeans.add(hrTrainingManagementBean);
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		
		return hrTrainingManagementBeans;
	}

}
