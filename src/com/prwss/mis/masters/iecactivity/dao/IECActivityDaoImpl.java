package com.prwss.mis.masters.iecactivity.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class IECActivityDaoImpl extends HibernateDaoSupport implements IECActivityDao {
	
	private Logger log = Logger.getLogger(IECActivityDaoImpl.class);
	

	@SuppressWarnings("unchecked")
	@Override
	public List<IECActivityBean> findIECActivity(IECActivityBean iecActivityBean, List<String> statusList)
			throws DataAccessException {
		log.debug(iecActivityBean);
		List<IECActivityBean> iecActivityBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(IECActivityBean.class);
			if(MisUtility.ifEmpty(iecActivityBean)){
				if(MisUtility.ifEmpty(iecActivityBean.getIecActivityId()))
					criteria.add(Restrictions.eq("iecActivityId", iecActivityBean.getIecActivityId()).ignoreCase());
				if(MisUtility.ifEmpty(iecActivityBean.getIecActivityName()))
					criteria.add(Restrictions.eq("iecActivityName", iecActivityBean.getIecActivityName()).ignoreCase());
				if(MisUtility.ifEmpty(iecActivityBean.getIecActivityDescription()))
					criteria.add(Restrictions.eq("iecActivityDescription", iecActivityBean.getIecActivityDescription()).ignoreCase());
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
			}
			log.debug("Criteria----\t"+criteria);
			iecActivityBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return iecActivityBeans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IECActivityBean> findIECActivity(List<String> iecActivityIds) throws DataAccessException {
		List<IECActivityBean> iecActivityBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(IECActivityBean.class);
			if(!MisUtility.ifEmpty(iecActivityIds))
				criteria.add(Restrictions.in("iecActivityId", iecActivityIds));
			log.debug("Criteria----\t"+criteria);
			iecActivityBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return iecActivityBeans;
	}

	@Override
	public String saveIECActivity(IECActivityBean iecActivityBean) throws DataAccessException {
		String iecActivityId = null;
		 try {
			 iecActivityId = (String)getHibernateTemplate().save(iecActivityBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return iecActivityId;
	}

	@Override
	public boolean updateIECActivity(IECActivityBean iecActivityBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(iecActivityBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateIECActivity(List<IECActivityBean> iecActivityBeans) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(iecActivityBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<IECActivityBean> getDistinctIECActivityId() throws DataAccessException {
		Set<IECActivityBean> iecActivityBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(IECActivityBean.class);
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			iecActivityBeans = new TreeSet<IECActivityBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}
		
		return iecActivityBeans;
	}


}
