package com.prwss.mis.masters.employee.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface EmployeePromotionHistoryDao {
public List<EmployeePromotionHistoryBean> findEmployeePromotion(EmployeePromotionHistoryBean employeePromotionHistoryBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveOrUpdateEmployeePromotion(Collection<EmployeePromotionHistoryBean> employeePromotionHistoryBeans) throws DataAccessException;


}
