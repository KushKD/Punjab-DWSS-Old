package com.prwss.mis.hr.payroll.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.hr.payroll.HRPayrollBean;

public interface HRPayrollDao {

	public boolean saveHRPayroll(HRPayrollBean hrPayrollBean) throws DataAccessException;
	
	public List<HRPayrollBean> findHRPayroll(HRPayrollBean hrPayrollBean, List<String> statusList) throws DataAccessException;
}
