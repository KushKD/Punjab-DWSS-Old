package com.prwss.mis.masters.employee.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class EmployeePromotionHistoryDaoImpl extends HibernateDaoSupport implements EmployeePromotionHistoryDao {
private Logger log = Logger.getLogger(EmployeePromotionHistoryDaoImpl.class);
	
	@Override
	@SuppressWarnings("unchecked")
	public List<EmployeePromotionHistoryBean> findEmployeePromotion(
			EmployeePromotionHistoryBean employeePromotionHistoryBean,
			List<String> statusList) throws DataAccessException {
		List<EmployeePromotionHistoryBean> employeePromotionHistoryBeans = null;
		try {
			if(MisUtility.ifEmpty(employeePromotionHistoryBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(EmployeePromotionHistoryBean.class);
				if(MisUtility.ifEmpty(employeePromotionHistoryBean.getId()))
					criteria.add(Restrictions.eq("id", employeePromotionHistoryBean.getId()));
				if(MisUtility.ifEmpty(employeePromotionHistoryBean.getEmployeeId()))
					criteria.add(Restrictions.eq("employeeId", employeePromotionHistoryBean.getEmployeeId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				employeePromotionHistoryBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return employeePromotionHistoryBeans;
	}

	@Override
	public boolean saveOrUpdateEmployeePromotion(
			Collection<EmployeePromotionHistoryBean> employeePromotionHistoryBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(employeePromotionHistoryBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

}
