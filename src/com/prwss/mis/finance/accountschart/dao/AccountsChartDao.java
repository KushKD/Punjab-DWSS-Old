/**
 * 
 */
package com.prwss.mis.finance.accountschart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.finance.accountschart.AccountsChartBean;

/**
 * @author PJHA
 *
 */
public interface  AccountsChartDao {

	public  List<AccountsChartBean> findAccountsChart(AccountsChartBean accountsChartBean ,List<String> statusList) throws DataAccessException;

	public boolean saveAccountsChart(AccountsChartBean accountsChartBean) throws DataAccessException;

	public boolean saveOrUpdateAccountsChart(AccountsChartBean accountsChartBean) throws DataAccessException;

	public List<AccountsChartBean> findAccountsChart1(
			AccountsChartBean accountsChartBean, List<String> statusList)
			throws DataAccessException;
}
