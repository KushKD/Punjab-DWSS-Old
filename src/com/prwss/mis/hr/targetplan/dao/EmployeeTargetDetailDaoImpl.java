package com.prwss.mis.hr.targetplan.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.hr.targetplan.EmployeeTargetDetailsBean;

public class EmployeeTargetDetailDaoImpl extends HibernateDaoSupport implements EmployeeTargetDetailDao {
   private Logger log = Logger.getLogger(EmployeeTargetDetailDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeTargetDetailsBean> findEmployeeTargetDetailsBean(
			EmployeeTargetDetailsBean employeeTargetDetailsBean,
			List<String> statusList) throws DataAccessException {
		List<EmployeeTargetDetailsBean> employeeTargetDetailsBeans = null;

		try {
			if(MisUtility.ifEmpty(employeeTargetDetailsBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeTargetDetailsBean.class);
				if(MisUtility.ifEmpty(employeeTargetDetailsBean.getId()))
					criteria.add(Restrictions.eq("id", employeeTargetDetailsBean.getId()));
				if(MisUtility.ifEmpty(employeeTargetDetailsBean.getTargetPlanId()))
					criteria.add(Restrictions.eq("targetPlanId",employeeTargetDetailsBean.getTargetPlanId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				employeeTargetDetailsBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return employeeTargetDetailsBeans;
	}

	@Override
	public boolean saveOrUpdateEmployeeTargetDetailsBean(
			Collection<EmployeeTargetDetailsBean> employeeTargetDetailsBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(employeeTargetDetailsBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

}
