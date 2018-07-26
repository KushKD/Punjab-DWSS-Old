package com.prwss.mis.common.notification.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MailNotificationDaoImpl extends HibernateDaoSupport implements MailNotificationDao {
	private Logger log = Logger.getLogger(MailNotificationBean.class); 

	@SuppressWarnings("unchecked")
	@Override
	public List<MailNotificationBean> findMailNotification(MailNotificationBean mailNotificationBean, List<String> statusList)
			throws DataAccessException {
		List<MailNotificationBean> mailNotificationBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(MailNotificationBean.class);
			
			criteria.add(Restrictions.in("emailStatus", statusList));
			log.debug(criteria);
			mailNotificationBeans = getHibernateTemplate().findByCriteria(criteria);
			
		} catch (DataAccessException e) {
			throw e;
		}
		
		return mailNotificationBeans;
	}
	
	@Override
	public boolean saveMailNotification(List<MailNotificationArchiveBean> mailNotificationArchiveBeans) throws DataAccessException{
		try {
			getHibernateTemplate().saveOrUpdateAll(mailNotificationArchiveBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;		
	}
	
	@Override
	public boolean saveMailNotificationBean(List<MailNotificationBean> mailNotificationBeans) throws DataAccessException{
		try {
			getHibernateTemplate().saveOrUpdateAll(mailNotificationBeans);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		
		return true;		
	}

	@Override
	public boolean deleteMailNotification(List<MailNotificationBean> mailNotificationBeans) throws DataAccessException {
		
		try {
			getHibernateTemplate().deleteAll(mailNotificationBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

}
