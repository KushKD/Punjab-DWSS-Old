/**
 * 
 */
package com.prwss.mis.finance.budgetsubmission;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.finance.budgetdetail.BudgetDetailBean;
import com.prwss.mis.finance.budgetsubmission.struts.BudgetSubmissionForm;

/**
 * @author PJHA
 *
 */
public interface BudgetSubmissionBO {
	public List<BudgetDetailBean> findBudgetSubmission(BudgetSubmissionForm budgetSubmissionForm,List<String> statusList) throws MISException;
	public boolean saveBudgetSubmission(BudgetSubmissionForm budgetSubmissionForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean updateBudgetSubmission(BudgetSubmissionForm budgetSubmissionForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean deleteBudgetSubmission(BudgetSubmissionForm budgetSubmissionForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean postBudgetSubmission(BudgetSubmissionForm budgetSubmissionForm,MISSessionBean misSessionBean) throws MISException;
	
}
