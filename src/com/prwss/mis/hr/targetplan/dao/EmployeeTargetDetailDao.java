package com.prwss.mis.hr.targetplan.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.hr.targetplan.EmployeeTargetDetailsBean;

public interface EmployeeTargetDetailDao {
	 public List<EmployeeTargetDetailsBean> findEmployeeTargetDetailsBean(EmployeeTargetDetailsBean employeeTargetDetailsBean , List<String> statusList) throws DataAccessException;
	    
		
		public boolean saveOrUpdateEmployeeTargetDetailsBean(Collection<EmployeeTargetDetailsBean> employeeTargetDetailsBeans) throws DataAccessException;




}
