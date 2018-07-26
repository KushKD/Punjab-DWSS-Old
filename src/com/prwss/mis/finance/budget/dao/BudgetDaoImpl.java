/**
 * 
 */
package com.prwss.mis.finance.budget.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.budget.BudgetBean;

/**
 * @author pjha
 *
 */


public class BudgetDaoImpl extends HibernateDaoSupport implements BudgetDao{
	private Logger log = Logger.getLogger(BudgetDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<BudgetBean> findBudgetPlan(
			BudgetBean budgetBean, List<String> statusList)
			throws DataAccessException {
		List<BudgetBean> budgetSubmissionBeans = new  ArrayList<BudgetBean>();
		try {
			if(MisUtility.ifEmpty(budgetBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(BudgetBean.class);
				if(MisUtility.ifEmpty(budgetBean.getBudgetId()))
					criteria.add(Restrictions.eq("budgetId", budgetBean.getBudgetId()));
				if(MisUtility.ifEmpty(budgetBean.getProgramId()))
					criteria.add(Restrictions.eq("programId", budgetBean.getProgramId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				budgetSubmissionBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return budgetSubmissionBeans;
	}
	@Override
	public long saveBudgetPlan(BudgetBean budgetBean)
			throws DataAccessException {
		long planId = 0;
		try {
			planId = (Long)getHibernateTemplate().save(budgetBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return planId;
	}
	@Override
	public boolean saveOrUpdateBudgetPlan(BudgetBean budgetBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(budgetBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Set<BudgetBean> getDistinctBudgetId() throws DataAccessException {
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			Set<BudgetBean> budgetBeans = null;
			try {
				DetachedCriteria criteria = DetachedCriteria.forClass(BudgetBean.class);
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
				criteria.addOrder(Order.asc("budgetToDate"));
				budgetBeans = new TreeSet<BudgetBean>(getHibernateTemplate().findByCriteria(criteria));
			} catch (DataAccessException e) {
				throw e;
			}
			return budgetBeans;
		}

	

}
