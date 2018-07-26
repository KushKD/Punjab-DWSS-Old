package com.prwss.mis.hr.salarystructure;

import java.sql.Date;
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
import com.prwss.mis.hr.salarystructure.dao.HRSalaryStructureDao;
import com.prwss.mis.hr.salarystructure.struts.HRSalaryStructureForm;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public class HRSalaryStructureBOImpl implements HRSalaryStructureBO {
private Logger log = Logger.getLogger(HRSalaryStructureBOImpl.class);
private HRSalaryStructureDao hrSalaryStructureDao;


	public void setHrSalaryStructureDao(HRSalaryStructureDao hrSalaryStructureDao) {
	this.hrSalaryStructureDao = hrSalaryStructureDao;
}

	@Override
	public List<HRSalaryStructureBean> findHRSalaryForm(
			HRSalaryStructureForm hrSalaryStructureForm, List<String> statusList)
			throws MISException {
		List<HRSalaryStructureBean> hrSalaryStructureBeans = null;
		try {
			HRSalaryStructureBean hrSalaryStructureBean = new HRSalaryStructureBean();
			if(MisUtility.ifEmpty(hrSalaryStructureForm.getEmployeeId())){
				EmployeeBean bean = new EmployeeBean();
				bean.setEmployeeId(hrSalaryStructureForm.getEmployeeId());
				hrSalaryStructureBean.setEmployeeBean(bean);
				hrSalaryStructureBeans = hrSalaryStructureDao.findSalary(hrSalaryStructureBean, statusList);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw new MISException(e);
		}
		
		return hrSalaryStructureBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateHRSalaryForm(
			HRSalaryStructureForm hrSalaryStructureForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			String currentDate = MisUtility.now("dd-MM-yyyy");
			HRSalaryStructureBean salaryStructureBean = new HRSalaryStructureBean();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			salaryStructureBean.setLocationId(hrSalaryStructureForm.getLocationId());
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmployeeId(hrSalaryStructureForm.getEmployeeId());
			salaryStructureBean.setEmployeeBean(employeeBean);
			HRSalaryStructureBean salaryStructureBean2 = hrSalaryStructureDao.findSalary(salaryStructureBean, statusList).get(0);
			if(MisUtility.isSameDate(salaryStructureBean2.getFromDate(), MisUtility.convertStringToDate(currentDate))){
				throw new MISException(MISExceptionCodes.MIS007, "Salary Structure Is Not Modifiable On The Same Day  Of Your Last Update For An Employee");
			}
			salaryStructureBean2.setToDate(returnDateBeforeThisDate(MisUtility.convertStringToDate(currentDate)));
			boolean statusOfUpdate = hrSalaryStructureDao.updateSalary(salaryStructureBean2);
			
			if(!statusOfUpdate){
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Last Salary Structure Record");
			}
			HRSalaryStructureBean hrSalaryStructureBean = populateHrSalaryStructureBean(hrSalaryStructureForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			hrSalaryStructureBean.setMisAuditBean(misAuditBean);
			hrSalaryStructureBean.setFromDate(MisUtility.convertStringToDate(currentDate)); // To set From Date from which the salary is valid
			status = hrSalaryStructureDao.saveSalary(hrSalaryStructureBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveHRSalaryForm(
			HRSalaryStructureForm hrSalaryStructureForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			List<HRSalaryStructureBean> hrSalaryStructureFindBeans = null;
			HRSalaryStructureBean salaryStructureBean = new HRSalaryStructureBean();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			salaryStructureBean.setLocationId(hrSalaryStructureForm.getLocationId());
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmployeeId(hrSalaryStructureForm.getEmployeeId());
			salaryStructureBean.setEmployeeBean(employeeBean);
			hrSalaryStructureFindBeans = hrSalaryStructureDao.findSalary(salaryStructureBean, statusList);
			
			if(!MisUtility.ifEmpty(hrSalaryStructureFindBeans)){
				throw new MISException(MISExceptionCodes.MIS001, "Salary Structure Already Exist For Employee ID - "+hrSalaryStructureForm.getEmployeeId());
			}
			
			HRSalaryStructureBean hrSalaryStructureBean = populateHrSalaryStructureBean(hrSalaryStructureForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			hrSalaryStructureBean.setMisAuditBean(misAuditBean);
			
			String currentDate = MisUtility.now("dd-MM-yyyy");
			hrSalaryStructureBean.setFromDate(MisUtility.convertStringToDate(currentDate)); // To set From Date from which the salary is valid
			
			status = hrSalaryStructureDao.saveSalary(hrSalaryStructureBean);
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}
	
	private HRSalaryStructureBean populateHrSalaryStructureBean(HRSalaryStructureForm hrSalaryStructureForm){
		HRSalaryStructureBean hrSalaryStructureBean = new HRSalaryStructureBean();
		hrSalaryStructureBean.setLocationId(hrSalaryStructureForm.getLocationId());
		hrSalaryStructureBean.setConveyanceAllowance(hrSalaryStructureForm.getConveyanceAllowance());
		hrSalaryStructureBean.setDearnessAllowance(hrSalaryStructureForm.getDearnessAllowance());
		hrSalaryStructureBean.setEducationAllowance(hrSalaryStructureForm.getEducationAllowance());
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.setEmployeeId(hrSalaryStructureForm.getEmployeeId());
		hrSalaryStructureBean.setBasicPay(hrSalaryStructureForm.getBasicPay());
		hrSalaryStructureBean.setEmployeeBean(employeeBean);
		hrSalaryStructureBean.setHra(hrSalaryStructureForm.getHra());
		hrSalaryStructureBean.setIncomeTax(hrSalaryStructureForm.getIncomeTax());
		hrSalaryStructureBean.setLeaveWithoutPay(hrSalaryStructureForm.getLeaveWithoutPay());
		hrSalaryStructureBean.setTotalAmount(hrSalaryStructureForm.getTotalAmount());
		hrSalaryStructureBean.setToDate(null);
		hrSalaryStructureBean.setTds(hrSalaryStructureForm.getTds());
		hrSalaryStructureBean.setReimbursement(hrSalaryStructureForm.getReimbursement());
		hrSalaryStructureBean.setProvidentFund(hrSalaryStructureForm.getProvidentFund());
		hrSalaryStructureBean.setPersonalAllowance(hrSalaryStructureForm.getPersonalAllowance());
		hrSalaryStructureBean.setFromDate(MisUtility.convertStringToDate(hrSalaryStructureForm.getFromDate()));
		hrSalaryStructureBean.setOthers(hrSalaryStructureForm.getOthers());
		return hrSalaryStructureBean;
	}
	
	private Date returnDateBeforeThisDate(Date currentDate){
		long dayBefore = 0;
		long currenTime = currentDate.getTime();
		dayBefore = currenTime - (24*60*60*1000);
		Date dayBeforDate = new Date(dayBefore);
		return dayBeforDate;
	}

}
