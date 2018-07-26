package com.prwss.mis.finance.budgetdetail.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.budgetdetail.BudgetDetailBean;

public class BudgetDetailDaoImpl extends HibernateDaoSupport implements BudgetDetailDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BudgetDetailBean> findBudgetDetails(
			BudgetDetailBean budgetDetailBean, List<String> statusList)
			throws DataAccessException {
		 List<BudgetDetailBean> budgetDetailBeans = null;
			
			try {
				if(MisUtility.ifEmpty(budgetDetailBean)){
					DetachedCriteria criteria = DetachedCriteria.forClass(BudgetDetailBean.class);
					if(MisUtility.ifEmpty(budgetDetailBean.getBudgetId()))
						criteria.add(Restrictions.eq("budgetId", budgetDetailBean.getBudgetId()));
					if(MisUtility.ifEmpty(budgetDetailBean.getId()))
						criteria.add(Restrictions.eq("id", budgetDetailBean.getId()));
					if(MisUtility.ifEmpty(budgetDetailBean.getLocationId()))
						criteria.add(Restrictions.eq("locationId", budgetDetailBean.getLocationId()));		
					if(MisUtility.ifEmpty(budgetDetailBean.getProgramId()))
						criteria.add(Restrictions.eq("programId", budgetDetailBean.getProgramId()));		
					if(MisUtility.ifEmpty(budgetDetailBean.getComponentId()))
						criteria.add(Restrictions.eq("componentId", budgetDetailBean.getComponentId()));		
					if(MisUtility.ifEmpty(budgetDetailBean.getSubComponentId()))
						criteria.add(Restrictions.eq("subComponentId", budgetDetailBean.getSubComponentId()));					
					if(!MisUtility.ifEmpty(statusList))
						criteria.add(Restrictions.in("misAuditBean.status", statusList));

					budgetDetailBeans = getHibernateTemplate().findByCriteria(criteria);
				}
			} catch (DataAccessException e) {
				throw e;
			}
			
			return budgetDetailBeans;
	}

	@Override
	public boolean saveBudgetDetails(
			Collection<BudgetDetailBean> budgetDetailBeans)
			throws DataAccessException {
		try {
			
			getHibernateTemplate().saveOrUpdateAll(budgetDetailBeans);
			
		} catch (DataAccessException e) {
			throw e;
		}
	
		return true;
	}

	@Override
	public boolean updateBudgetDetails(
			Collection<BudgetDetailBean> budgetDetailBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(budgetDetailBeans);
		} catch (DataAccessException e) {
			throw e;
		}
	
		return true;
	}

	
	
}
