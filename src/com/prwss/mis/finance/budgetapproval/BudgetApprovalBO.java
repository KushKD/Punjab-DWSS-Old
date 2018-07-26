/**
 * 
 */
package com.prwss.mis.finance.budgetapproval;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.finance.budgetapproval.struts.BudgetApprovalForm;
import com.prwss.mis.finance.budgetdetail.BudgetDetailBean;

/**
 * @author PJHA
 *
 */
public interface BudgetApprovalBO {
	public List<BudgetDetailBean> findBudgetApproval(BudgetApprovalForm budgetApprovalForm,List<String> statusList) throws MISException;
	public boolean saveBudgetApproval(BudgetApprovalForm budgetApprovalForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean updateBudgetApproval(BudgetApprovalForm budgetApprovalForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean deleteBudgetApproval(BudgetApprovalForm budgetApprovalForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean postBudgetApproval(BudgetApprovalForm budgetApprovalForm,MISSessionBean misSessionBean) throws MISException;
	
}
