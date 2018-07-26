/**
 * 
 */
package com.prwss.mis.finance.budgetrevision;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.finance.budgetdetail.BudgetDetailBean;
import com.prwss.mis.finance.budgetrevision.struts.BudgetRevisionForm;

/**
 * @author PJHA
 *
 */
public interface BudgetRevisionBO {
	public List<BudgetDetailBean> findBudgetRevision(BudgetRevisionForm budgetRevisionForm,List<String> statusList) throws MISException;
	public boolean saveBudgetRevision(BudgetRevisionForm budgetRevisionForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean updateBudgetRevision(BudgetRevisionForm budgetRevisionForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean deleteBudgetRevision(BudgetRevisionForm budgetRevisionForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean postBudgetRevision(BudgetRevisionForm budgetRevisionForm,MISSessionBean misSessionBean) throws MISException;
	
}
