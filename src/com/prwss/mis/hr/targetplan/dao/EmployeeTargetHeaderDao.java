package com.prwss.mis.hr.targetplan.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.hr.targetplan.EmployeeTargetHeaderBean;

public interface EmployeeTargetHeaderDao {
	
	public  List<EmployeeTargetHeaderBean> findEmployeeTargetHeader(EmployeeTargetHeaderBean employeeTargetHeaderBean ,List<String> statusList) throws DataAccessException;

	public long saveEmployeeTargetHeader(EmployeeTargetHeaderBean employeeTargetHeaderBean) throws DataAccessException;

	public boolean updateEmployeeTargetHeader(EmployeeTargetHeaderBean employeeTargetHeaderBean) throws DataAccessException;
	public List<EmployeeTargetHeaderBean> findEmployeeTargetHeaderByEmployeeId(EmployeeTargetHeaderBean employeeTargetHeaderBean,List<String> statusList) throws DataAccessException; 

}
