package com.prwss.mis.hr.payroll;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.hr.payroll.struts.HRPayrollForm;

public interface HRPayrollBO {
	
	public List<HRPayrollBean> findHRPayroll(HRPayrollForm hrPayrollForm,List<String> statusList) throws MISException;
	public long saveHRPayroll(HRPayrollForm hrPayrollForm, MISSessionBean misSessionBean) throws MISException;


}
