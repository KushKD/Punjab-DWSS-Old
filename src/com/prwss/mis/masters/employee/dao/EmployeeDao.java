package com.prwss.mis.masters.employee.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface EmployeeDao {
	
	public List<EmployeeBean> findEmployee(EmployeeBean employeeBean, List<String> statusList) throws DataAccessException;
	
	public List<EmployeeBean> findEmployee(List<Long> employeeIds) throws DataAccessException;

	public long saveEmployee(EmployeeBean employeeBean) throws DataAccessException;

	public boolean updateEmployee(EmployeeBean employeeBean)	throws DataAccessException;
	
	public boolean updateEmployee(List<EmployeeBean> employeeBeans) throws DataAccessException;

	public Set<EmployeeBean> getDistinctEmployeeIds(String locationId,List<String> statusList) throws DataAccessException;
	
	public Set<EmployeeBean> getEmployeeByReportingOfficer(EmployeeBean employeeBean,List<String> statusList ) throws DataAccessException ;

	public Set<EmployeeBean> getEmployeeByDeployedLocation(EmployeeBean employeeBean,List<String> statusList ) throws DataAccessException ;
	
	public List<EmployeeBean> getEmployeeByName(long employeeId,List<String> statusList ) throws DataAccessException ;

	public List<EmployeeBean> getDistinctEmployeeDetails(long employeeId,List<String> statusList)throws DataAccessException;;

	//Set<EmployeeBean> getDistinctEmployeeIdsForPermanentEmployees(String locationId, List<String> statusList)throws DataAccessException;
	
	public List<EmployeeBean> getEmployeeByOldOfficer(EmployeeBean employeeBean,List<String> statusList ) throws DataAccessException ;
	public int updateEmployeeReportingOfficer(long empIds[],long reportingofficer, String reportingOfficerLocaion) throws DataAccessException;

	public int updateEmployeeLocation(int employeeId,String previousLocation, String changeLocation)throws DataAccessException;

	public List<EmployeeBean> findEmployeeForLocation(EmployeeBean employeeBean,
			List<String> statusList) throws DataAccessException;;

 
}
