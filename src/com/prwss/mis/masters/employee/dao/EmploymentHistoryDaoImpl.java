package com.prwss.mis.masters.employee.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class EmploymentHistoryDaoImpl extends HibernateDaoSupport implements EmploymentHistoryDao {
	private Logger log = Logger.getLogger(EmploymentHistoryDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<EmploymentHistoryBean> findEmploymentHistory(EmploymentHistoryBean employmentHistoryBean,
			List<String> statusList) throws DataAccessException {
		List<EmploymentHistoryBean> employmentHistoryBeans = null;
		
		try {
			if(MisUtility.ifEmpty(employmentHistoryBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(EmploymentHistoryBean.class);
				if(MisUtility.ifEmpty(employmentHistoryBean.getId()))
					criteria.add(Restrictions.eq("id", employmentHistoryBean.getId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				if(MisUtility.ifEmpty(employmentHistoryBean.getEmployeeId()))
					criteria.add(Restrictions.eq("employeeId", employmentHistoryBean.getEmployeeId()));
 
				employmentHistoryBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return employmentHistoryBeans;
	}

	@Override
	public boolean saveOrUpdateEmploymentHistory(Collection<EmploymentHistoryBean> employmentHistoryBeans)
			throws DataAccessException {
		try {
		getHibernateTemplate().saveOrUpdateAll(employmentHistoryBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

}
