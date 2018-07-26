package com.prwss.mis.masters.employee;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.struts.EmployeeForm;

public interface EmployeeBO {
	
	public List<EmployeeBean> findEmployee(EmployeeForm employeeForm, List<String> statusList) throws MISException;
	
	public long saveEmployee(EmployeeForm employeeForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean updateEmployee(EmployeeForm employeeForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean deleteEmployee(EmployeeForm employeeForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postEmployee(EmployeeForm employeeForm, MISSessionBean misAuditBean) throws MISException;


}
