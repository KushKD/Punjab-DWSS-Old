/**
 * 
 */
package com.prwss.mis.finance.accountschart.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.accountschart.AccountsChartBean;

/**
 * @author PJHA
 *
 */
public class AccountsChartDaoImpl  extends HibernateDaoSupport implements AccountsChartDao {
	private Logger log = Logger.getLogger(AccountsChartDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<AccountsChartBean> findAccountsChart(
			AccountsChartBean accountsChartBean, List<String> statusList)
			throws DataAccessException {
		List<AccountsChartBean> accountsChartBeans = null;
		try {
			if(MisUtility.ifEmpty(accountsChartBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(AccountsChartBean.class);
				if(MisUtility.ifEmpty(accountsChartBean.getCodeHeadId()))
					criteria.add(Restrictions.eq("codeHeadId", accountsChartBean.getCodeHeadId()));
				if(MisUtility.ifEmpty(accountsChartBean.getMajorHeadId()))
					criteria.add(Restrictions.eq("majorHeadId", accountsChartBean.getMajorHeadId()));
				if(MisUtility.ifEmpty(accountsChartBean.getAccountNature()))
					criteria.add(Restrictions.eq("accountNature",accountsChartBean.getAccountNature()));				
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				accountsChartBeans =  getHibernateTemplate().findByCriteria(criteria);
				
			}
		} catch (DataAccessException e) {
			
			throw e;
		}
		return accountsChartBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountsChartBean> findAccountsChart1(
			AccountsChartBean accountsChartBean, List<String> statusList)
			throws DataAccessException {
		List<AccountsChartBean> accountsChartBeans = null;
		List<String> accountTypes = new ArrayList<String>();
		accountTypes.add(MISConstants.FIN_ACCOUNT_NATURE_RECEIPT);
		accountTypes.add(MISConstants.FIN_ACCOUNT_NATURE_BOTH);
		try {
			if(MisUtility.ifEmpty(accountsChartBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(AccountsChartBean.class);
				if(MisUtility.ifEmpty(accountsChartBean.getCodeHeadId()))
					criteria.add(Restrictions.eq("codeHeadId", accountsChartBean.getCodeHeadId()));
				if(MisUtility.ifEmpty(accountsChartBean.getMajorHeadId()))
					criteria.add(Restrictions.eq("majorHeadId", accountsChartBean.getMajorHeadId()));
				if(MisUtility.ifEmpty(accountsChartBean.getAccountNature()))
					criteria.add(Restrictions.in("accountNature",accountTypes ));				
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				accountsChartBeans =  getHibernateTemplate().findByCriteria(criteria);
				
			}
		} catch (DataAccessException e) {
			
			throw e;
		}
		return accountsChartBeans;
	}
	
	@Override
	public boolean saveAccountsChart(AccountsChartBean accountsChartBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(accountsChartBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveOrUpdateAccountsChart(AccountsChartBean accountsChartBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(accountsChartBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

}
