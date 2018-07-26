package com.prwss.mis.masters.employee.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface EmploymentHistoryDao {

	public List<EmploymentHistoryBean> findEmploymentHistory(EmploymentHistoryBean employmentHistoryBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveOrUpdateEmploymentHistory(Collection<EmploymentHistoryBean> employmentHistoryBeans) throws DataAccessException;

}
