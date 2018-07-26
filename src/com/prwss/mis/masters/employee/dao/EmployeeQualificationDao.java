package com.prwss.mis.masters.employee.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface EmployeeQualificationDao {
	
	public List<EmployeeQualificationBean> findEmployeeQualification(EmployeeQualificationBean employeeQualificationBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveOrUpdateEmployeeQualification(Collection<EmployeeQualificationBean> employeeQualificationBeans) throws DataAccessException;


}
