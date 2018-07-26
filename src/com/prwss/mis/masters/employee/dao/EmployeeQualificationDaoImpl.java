package com.prwss.mis.masters.employee.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class EmployeeQualificationDaoImpl extends HibernateDaoSupport implements EmployeeQualificationDao {
	private Logger log = Logger.getLogger(EmployeeQualificationDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeQualificationBean> findEmployeeQualification(
			EmployeeQualificationBean employeeQualificationBean, List<String> statusList) throws DataAccessException {
		List<EmployeeQualificationBean> employeeQualificationBeans = null;
		
		try {
			if(MisUtility.ifEmpty(employeeQualificationBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeQualificationBean.class);
				if(MisUtility.ifEmpty(employeeQualificationBean.getId()))
					criteria.add(Restrictions.eq("id", employeeQualificationBean.getId()));
				if(MisUtility.ifEmpty(employeeQualificationBean.getEmployeeId()))
					criteria.add(Restrictions.eq("employeeId", employeeQualificationBean.getEmployeeId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));

				employeeQualificationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return employeeQualificationBeans;
	}

	@Override
	public boolean saveOrUpdateEmployeeQualification(Collection<EmployeeQualificationBean> employeeQualificationBeans)
			throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(employeeQualificationBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

}
