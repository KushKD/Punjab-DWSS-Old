package com.prwss.mis.masters.activity.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class ActivityDaoImpl extends HibernateDaoSupport implements ActivityDao {

private Logger log = Logger.getLogger(ActivityDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBean> findActivity(ActivityBean activityBean, List<String> statusList)
			throws DataAccessException {
		DetachedCriteria criteria = DetachedCriteria.forClass(ActivityBean.class);
		List<ActivityBean> activityBeans = null;
		if(MisUtility.ifEmpty(activityBean)){
		try {
			if(MisUtility.ifEmpty(activityBean.getComponent()) && MisUtility.ifEmpty(activityBean.getComponent().getComponentId()))
				criteria.add(Restrictions.eq("component.componentId", activityBean.getComponent().getComponentId()).ignoreCase());
			if(MisUtility.ifEmpty(activityBean.getSubComponent()) && MisUtility.ifEmpty(activityBean.getSubComponent().getSubComponentId()))
				criteria.add(Restrictions.eq("subComponent.subComponentId", activityBean.getSubComponent().getSubComponentId()).ignoreCase());
			if(MisUtility.ifEmpty(activityBean.getActivityId()))
				criteria.add(Restrictions.eq("activityId", activityBean.getActivityId()).ignoreCase());
			if(MisUtility.ifEmpty(activityBean.getActivityName()))
				criteria.add(Restrictions.eq("activityName", activityBean.getActivityName()).ignoreCase());
			if(MisUtility.ifEmpty(activityBean.getActivityDescription()))
				criteria.add(Restrictions.eq("activityDescription", activityBean.getActivityDescription()).ignoreCase());
			if(!MisUtility.ifEmpty(statusList))
				criteria.add(Restrictions.in("status", statusList));
			activityBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		}
		return activityBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityBean> findActivity(List<String> activityIds) throws DataAccessException {
		List<ActivityBean> activityBeans = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(ActivityBean.class);
		
		try {
			if(!MisUtility.ifEmpty(activityIds)){
				criteria.add(Restrictions.in("activityId", activityIds));
			}
			activityBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return activityBeans;
	}

	@Override
	public boolean saveActivity(ActivityBean activityBean) throws DataAccessException {
//		boolean status = false;
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		try {
			
		String activityId =	(String) hibernateTemplate.save(activityBean);
			log.debug("Saved \t"+activityId);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateActivity(ActivityBean activityBean) throws DataAccessException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		try {
			hibernateTemplate.update(activityBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateActivity(List<ActivityBean> activityBeans) throws DataAccessException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		try {
			hibernateTemplate.saveOrUpdateAll(activityBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

}
