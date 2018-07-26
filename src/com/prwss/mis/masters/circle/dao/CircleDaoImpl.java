package com.prwss.mis.masters.circle.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class CircleDaoImpl extends HibernateDaoSupport implements CircleDao {
	
	private Logger log = Logger.getLogger(CircleDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<CircleBean> findCircle(CircleBean circleBean, List<String> statusList) throws DataAccessException {
		List<CircleBean> circleBeans = null;
		try {
			if(MisUtility.ifEmpty(circleBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(CircleBean.class);

				if(MisUtility.ifEmpty(circleBean.getCircleId()))
					criteria.add(Restrictions.eq("circleId", circleBean.getCircleId()));
				if(MisUtility.ifEmpty(circleBean.getCircleName()))
					criteria.add(Restrictions.eq("circleName", circleBean.getCircleName()));
				if(MisUtility.ifEmpty(circleBean.getZone()) && MisUtility.ifEmpty(circleBean.getZone().getZoneId()))
					criteria.add(Restrictions.eq("zone.zoneId", circleBean.getZone().getZoneId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("status", statusList));
				criteria.addOrder(Order.asc("circleId"));

				circleBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		} 
		
		return circleBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CircleBean> findCircle(List<String> circleIdList) throws DataAccessException {
		List<CircleBean> circleBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CircleBean.class);
			if(!MisUtility.ifEmpty(circleIdList)){
				criteria.add(Restrictions.in("circleId", circleIdList));
			}		
			
			circleBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return circleBeans;
	}

	@Override
	public boolean saveCircle(CircleBean circleBean) throws DataAccessException {
		
		try {
			getHibernateTemplate().save(circleBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateCircle(CircleBean circleBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(circleBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return true;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Set<CircleBean> getDistinctCircleCodes(List<String> statusList ) throws DataAccessException {
		Set<CircleBean> circleBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CircleBean.class);
			criteria.add(Restrictions.in("status", statusList));
			circleBeans = new TreeSet<CircleBean>(getHibernateTemplate().findByCriteria(criteria));
		}catch (DataAccessException e) {
			log.error(e);
			throw e;
		}		
		return circleBeans;
	}

	@Override
	public boolean updateCircle(List<CircleBean> circleBeans) throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(circleBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

}
