package com.prwss.mis.finance.loc.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class LOCActivityDaoImpl extends HibernateDaoSupport implements LOCActivityDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<LOCActivityBean> findLOCActivity(LOCActivityBean locDetailBean,
			List<String> statusList) throws DataAccessException {
		List<LOCActivityBean> locActivityBeans = null;
		
		try {
			if(MisUtility.ifEmpty(locDetailBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(LOCActivityBean.class);
				if(MisUtility.ifEmpty(locDetailBean.getLocId()))
					criteria.add(Restrictions.eq("locId", locDetailBean.getLocId()));
				if(MisUtility.ifEmpty(locDetailBean.getId()))
					criteria.add(Restrictions.eq("id", locDetailBean.getId()));			
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));

				locActivityBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return locActivityBeans;
	}

	@Override
	public boolean saveLOCActivity(Collection<LOCActivityBean> locActivityBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(locActivityBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateActivity(Collection<LOCActivityBean> locActivityBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(locActivityBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

}
