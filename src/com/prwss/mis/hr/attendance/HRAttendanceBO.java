package com.prwss.mis.hr.attendance;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.hr.attendance.struts.AttendanceForm;

public interface HRAttendanceBO {
	public List<HRAttendanceBean> findHRAttendanceForm(AttendanceForm attendanceForm, List<String> statusList) throws MISException;
	public boolean updateHRAttendanceForm(AttendanceForm attendanceForm, MISSessionBean misSessionBean) throws MISException;
	public boolean postHRAttendanceForm(AttendanceForm attendanceForm, MISSessionBean misSessionBean) throws MISException;
	public boolean saveHRAttendanceForm(AttendanceForm attendanceForm, MISSessionBean misSessionBean) throws MISException;
	
}
