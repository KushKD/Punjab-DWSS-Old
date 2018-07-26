package com.prwss.mis.masters.employee.dao;


import java.util.List;
 

import org.springframework.dao.DataAccessException;

public interface EmployeeDesignationDao {
	
	public List<EmployeeDesignationBean> getEmployeeDesignation(EmployeeDesignationBean employeeDesignationBean) throws DataAccessException;

}
