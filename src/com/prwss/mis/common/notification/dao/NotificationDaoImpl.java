package com.prwss.mis.common.notification.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class NotificationDaoImpl extends HibernateDaoSupport implements NotificationDao {
	
	private Logger log = Logger.getLogger(NotificationDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationBean> findNotification(NotificationBean notificationBean, List<String> statusList)
			throws DataAccessException {
		List<NotificationBean> notificationBeans = null;
		
		try {
			if(MisUtility.ifEmpty(notificationBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(NotificationBean.class);
				if(MisUtility.ifEmpty(notificationBean.getNotificationId()))
					criteria.add(Restrictions.eq("notificationId", notificationBean.getNotificationId()));
//				if(MisUtility.ifEmpty(notificationBean.getOpenedDate()))
//					criteria.add(Restrictions.eq("openedDate", notificationBean.getOpenedDate()));
//				if(MisUtility.ifEmpty(notificationBean.getClosedDate()))
//					criteria.add(Restrictions.eq("closedDate", notificationBean.getClosedDate()));
//				if(MisUtility.ifEmpty(notificationBean.getOpenedStatus()))
//					criteria.add(Restrictions.eq("openedStatus", notificationBean.getOpenedStatus()));
//				if(MisUtility.ifEmpty(notificationBean.getExceptionType()))
//					criteria.add(Restrictions.eq("exceptionType", notificationBean.getExceptionType()));
//				if(MisUtility.ifEmpty(notificationBean.getClosedBy()))
//					criteria.add(Restrictions.eq("closedBy", notificationBean.getClosedBy()));
//				if(MisUtility.ifEmpty(notificationBean.getLocationId()))
//					criteria.add(Restrictions.eq("locationId", notificationBean.getLocationId()));
				
				notificationBeans = getHibernateTemplate().findByCriteria(criteria); 
			}
		} catch (DataAccessException e) {
			log.error(notificationBean);
			throw e;
		}
			
		return notificationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationBean> findNotification(List<Long> notificationIds) throws DataAccessException {
		List<NotificationBean> notificationBeans = null;

		try {
			if(!MisUtility.ifEmpty(notificationIds)){
			DetachedCriteria criteria = DetachedCriteria.forClass(NotificationBean.class);
			if(!MisUtility.ifEmpty(notificationIds))
				criteria.add(Restrictions.in("notificationId", notificationIds));
			criteria.addOrder(Order.desc("openedDate"));
			notificationBeans = getHibernateTemplate().findByCriteria(criteria); 
			}
			
		} catch (DataAccessException e) {
			log.error(notificationIds);
			throw e;
		}

		return notificationBeans;
	}

	
	
	@Override
	public long saveNotification(NotificationBean notificationBean) throws DataAccessException {
		long notificationId = 0;
		
		try {
			notificationId = (Long)getHibernateTemplate().save(notificationBean);
		}catch (DataAccessException e) {
			log.error(notificationBean);
			throw e;
		}
		
		return notificationId;
	}

	@Override
	public boolean updateNotification(NotificationBean notificationBean) throws DataAccessException {

		
		try {
			getHibernateTemplate().update(notificationBean);
		}catch (DataAccessException e) {
			log.error(notificationBean);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateNotification(List<NotificationBean> notificationBeans) throws DataAccessException {
			
		try {
			getHibernateTemplate().saveOrUpdateAll(notificationBeans);
		} catch (DataAccessException e) {
			log.error(notificationBeans);
			throw e;
		}
		
		return true;
	}


	
	

}
