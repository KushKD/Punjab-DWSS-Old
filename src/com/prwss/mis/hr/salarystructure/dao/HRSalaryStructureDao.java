package com.prwss.mis.hr.salarystructure.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.hr.salarystructure.HRSalaryStructureBean;

public interface HRSalaryStructureDao {
public List<HRSalaryStructureBean> findSalary(HRSalaryStructureBean hrSalaryStructureBean, List<String> statusList) throws DataAccessException;

	public boolean saveSalary(HRSalaryStructureBean hrSalaryStructureBean) throws DataAccessException;

	public boolean updateSalary(HRSalaryStructureBean hrSalaryStructureBean)	throws DataAccessException;
}
