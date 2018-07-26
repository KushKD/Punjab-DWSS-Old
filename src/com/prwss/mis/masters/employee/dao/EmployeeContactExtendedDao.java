package com.prwss.mis.masters.employee.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface EmployeeContactExtendedDao {
	public boolean saveOrUpdateEmployeeContract(Collection<EmployeeContactExtendedBean> employeeContactExtendedBeans)  throws DataAccessException;

	public List<EmployeeContactExtendedBean> findEmployeeContractExtention(EmployeeContactExtendedBean employeeContactExtendedBean,List<String> statuslist) throws DataAccessException;

}
