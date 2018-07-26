package com.prwss.mis.hr.leave.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.hr.leave.LeaveManagementBean;

public interface LeaveManagementDao {
	
	public List<LeaveManagementBean> findLeave(LeaveManagementBean leaveManagementBean, List<String> statusList) throws DataAccessException;
	
	public List<LeaveManagementBean> findLeave(LeaveManagementBean leaveManagementBean,Date fromdate,Date toDate, List<String> statusList) throws DataAccessException;

	public long saveLeave(LeaveManagementBean leaveManagementBean)throws DataAccessException;
	
	public boolean updateLeave(LeaveManagementBean leaveManagementBean)throws DataAccessException;
	

}
