package com.prwss.mis.hr.targetplan;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.hr.employeeappraisal.struts.EmployeeAppraisalForm;
import com.prwss.mis.hr.employeevaluation.struts.EmployeeEvaluationForm;
import com.prwss.mis.hr.targetplan.struts.EmployeeTargetPlanForm;

public interface EmployeeTargetBO {
public List<EmployeeTargetHeaderBean> findEmployeeTargetPlanFrom(EmployeeTargetPlanForm employeeTargetPlanForm, List<String> statusList) throws MISException;
	
	public long saveEmployeeTargetPlanFrom(EmployeeTargetPlanForm employeeTargetPlanForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateEmployeeTargetPlanFrom(EmployeeTargetPlanForm employeeTargetPlanForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteEmployeeTargetPlanFrom(EmployeeTargetPlanForm employeeTargetPlanForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postEmployeeEvaluation(EmployeeEvaluationForm employeeEvaluationForm, MISSessionBean misSessionBean) throws MISException;

	public List<EmployeeTargetHeaderBean> findEmployeeAppraisalFrom(EmployeeAppraisalForm employeeAppraisalForm,List<String> statusList) throws MISException;
	
	public boolean updateEmployeeAppraisalFrom(	EmployeeAppraisalForm employeeAppraisalForm,MISSessionBean misSessionBean) throws MISException;
	
	public List<EmployeeTargetHeaderBean> findEmployeeEvaluationFrom(EmployeeEvaluationForm employeeEvaluationForm,List<String> statusList) throws MISException;
	public boolean updateEmployeeEvaluationFrom(EmployeeEvaluationForm employeeEvaluationForm,MISSessionBean misSessionBean) throws MISException;
}
