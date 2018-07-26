package com.prwss.mis.pmm.alert.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.masters.employee.dao.EmployeeBean;

public interface AlertChecklistDao {
	public List<AlertChecklistBean> findAlertChecklistBean(String locationId, String month, long year, String mailStatus) throws DataAccessException;
	
	public boolean saveOrUpdate(AlertChecklistBean alertChecklistBean) throws DataAccessException;
	public boolean saveOrUpdateAll(List<AlertChecklistBean> alertChecklistBeans) throws DataAccessException;
	public List<Object> getMailData(String month, long year, String mailStatus)throws DataAccessException;
	public List<EmployeeBean> getEmployeeMail()throws DataAccessException;
	
}
