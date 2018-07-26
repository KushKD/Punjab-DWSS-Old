package com.prwss.mis.hr.attendance.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.hr.attendance.HRAttendanceBean;

public interface HRAttendanceDao {

	public List<HRAttendanceBean> findAttendance(HRAttendanceBean hrAttendanceBean, List<String> statusList) throws DataAccessException;
	
	public List<HRAttendanceBean> findAttendanceFromAttendencIds(List<Long> attendencsIds, List<String> statusList) throws DataAccessException;

	public boolean saveOrUpdateAttendance(HRAttendanceBean hrAttendanceBean)throws DataAccessException;
	
	public boolean UpdateAttendance(List<HRAttendanceBean> hrAttendanceBeans)throws DataAccessException;
	
	public boolean saveAttendance(HRAttendanceBean hrAttendanceBean)throws DataAccessException;

}
