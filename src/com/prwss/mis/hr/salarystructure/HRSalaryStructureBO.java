package com.prwss.mis.hr.salarystructure;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.hr.salarystructure.struts.HRSalaryStructureForm;

public interface HRSalaryStructureBO {
	public List<HRSalaryStructureBean> findHRSalaryForm(HRSalaryStructureForm hrSalaryStructureForm, List<String> statusList) throws MISException;
	public boolean updateHRSalaryForm(HRSalaryStructureForm hrSalaryStructureForm, MISSessionBean misSessionBean) throws MISException;
	public boolean saveHRSalaryForm(HRSalaryStructureForm hrSalaryStructureForm, MISSessionBean misSessionBean) throws MISException;
	
}
