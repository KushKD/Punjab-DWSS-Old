package com.prwss.mis.hr.attendance;

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
import com.prwss.mis.hr.attendance.dao.HRAttendanceDao;
import com.prwss.mis.hr.attendance.struts.AttendanceForm;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public class HRAttendanceBOImpl implements HRAttendanceBO {
private Logger log = Logger.getLogger(HRAttendanceBOImpl.class);
private HRAttendanceDao hrAttendanceDao;


	public void setHrAttendanceDao(HRAttendanceDao hrAttendanceDao) {
	this.hrAttendanceDao = hrAttendanceDao;
}

	@Override
	public List<HRAttendanceBean> findHRAttendanceForm(
			AttendanceForm attendanceForm, List<String> statusList)
			throws MISException {
		List<HRAttendanceBean> hrAttendanceBeans = null;
		try {
			HRAttendanceBean hrAttendanceBean = new HRAttendanceBean();
			hrAttendanceBean.setLocationId(attendanceForm.getLocationId());
			hrAttendanceBean.setAttendanceDate(MisUtility.convertStringToDate(attendanceForm.getAttendanceDate()));
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmployeeId(attendanceForm.getEmployeeId());
			hrAttendanceBean.setEmployeeBean(employeeBean);
			hrAttendanceBeans = hrAttendanceDao.findAttendance(hrAttendanceBean, statusList);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return hrAttendanceBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateHRAttendanceForm(AttendanceForm attendanceForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			HRAttendanceBean hrAttendanceBean = populateHRAttendanceBean(attendanceForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			hrAttendanceBean.setMisAuditBean(misAuditBean);
			status = hrAttendanceDao.saveOrUpdateAttendance(hrAttendanceBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new MISException(e);
		}
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postHRAttendanceForm(AttendanceForm attendanceForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		
//		Long[] attendenceIds = ArrayUtils.toObject(attendanceForm.getAttendenceIds()); //this to allow creation of List of Long arrays
		HRAttendanceBean hrAttendanceBean = new HRAttendanceBean();
		hrAttendanceBean.setAttendenceId(attendanceForm.getAttendenceId());
		EmployeeBean bean = new EmployeeBean();
		bean.setEmployeeId(attendanceForm.getEmployeeId());
		hrAttendanceBean.setEmployeeBean(bean);
		System.out.println("Attendance ID+"+attendanceForm.getAttendenceId());
		List<HRAttendanceBean> hrAttendanceBeans = hrAttendanceDao.findAttendance(hrAttendanceBean, statusList);
		
		try {
			MISAuditBean misAuditBean = null;
			if(!MisUtility.ifEmpty(hrAttendanceBeans)){
			for (HRAttendanceBean attendanceBean : hrAttendanceBeans) {
				misAuditBean = attendanceBean.getMisAuditBean();
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				attendanceBean.setMisAuditBean(misAuditBean);
			}
			result = hrAttendanceDao.UpdateAttendance(hrAttendanceBeans);
			}
		} catch (DataAccessException e) {
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
	public boolean saveHRAttendanceForm(AttendanceForm attendanceForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			List<HRAttendanceBean> hrAttendanceBeans = null;
			HRAttendanceBean hrFindAttendanceBean = new HRAttendanceBean();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			hrFindAttendanceBean.setLocationId(attendanceForm.getLocationId());
			hrFindAttendanceBean.setAttendanceDate(MisUtility.convertStringToDate(attendanceForm.getAttendanceDate()));
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmployeeId(attendanceForm.getEmployeeId());
			hrFindAttendanceBean.setEmployeeBean(employeeBean);
			hrAttendanceBeans = hrAttendanceDao.findAttendance(hrFindAttendanceBean, statusList);
			if(!MisUtility.ifEmpty(hrAttendanceBeans)){
				throw new MISException(MISExceptionCodes.MIS001, " Employee Id - "+attendanceForm.getEmployeeId()+" For "+attendanceForm.getAttendanceDate());
			}
			HRAttendanceBean hrAttendanceBean = populateHRAttendanceBean(attendanceForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			hrAttendanceBean.setMisAuditBean(misAuditBean);
			status = hrAttendanceDao.saveAttendance(hrAttendanceBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}
	
	private HRAttendanceBean populateHRAttendanceBean(AttendanceForm attendanceForm){
		HRAttendanceBean hrAttendanceBean = new HRAttendanceBean();
		hrAttendanceBean.setAttendanceDate(MisUtility.convertStringToDate(attendanceForm.getAttendanceDate()));
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.setEmployeeId(attendanceForm.getEmployeeId());
		hrAttendanceBean.setEmployeeBean(employeeBean);
		hrAttendanceBean.setLocationId(attendanceForm.getLocationId());
		hrAttendanceBean.setAttendanceStatus(attendanceForm.getAttendanceStatus());
		hrAttendanceBean.setAttendenceId(attendanceForm.getAttendenceId());
		return hrAttendanceBean;
	}

}
