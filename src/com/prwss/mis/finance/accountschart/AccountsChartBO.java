/**
 * 
 */
package com.prwss.mis.finance.accountschart;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.finance.accountschart.struts.AccountsChartForm;

/**
 * @author PJHA
 *
 */
public interface AccountsChartBO  {

	public List<AccountsChartBean> findAccountsChart(AccountsChartForm accountsChartForm,List<String> statusList) throws MISException;
	public boolean saveAccountsChart(AccountsChartForm accountsChartForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean updateAccountsChart(AccountsChartForm accountsChartForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean deleteAccountsChart(AccountsChartForm accountsChartForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean postAccountsChart(AccountsChartForm accountsChartForm,MISSessionBean misSessionBean) throws MISException;
	
	
}
