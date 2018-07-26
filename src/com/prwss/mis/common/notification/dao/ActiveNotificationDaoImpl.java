/**
 * 
 */
package com.prwss.mis.common.notification.dao;

/**
 * @author pjha
 *
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class ActiveNotificationDaoImpl extends HibernateDaoSupport implements ActiveNotificationDao {

	private Logger log = Logger.getLogger(NotificationDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<ActiveNotificationBean> findNotification(ActiveNotificationBean activeNotificationBean)
	throws DataAccessException {
   List<ActiveNotificationBean> activeNotificationBeans = null;

try {
		//System.out.println("In Find");
		DetachedCriteria criteria = DetachedCriteria.forClass(ActiveNotificationBean.class);		
		if(MisUtility.ifEmpty(activeNotificationBean.getEmployeeId())){		
	     criteria.add(Restrictions.eq("employeeId", activeNotificationBean.getEmployeeId()));
		activeNotificationBeans = getHibernateTemplate().findByCriteria(criteria);
		}
} catch (DataAccessException e) {
	log.error(e);
	throw e;
}
	
return activeNotificationBeans;
}
	

	
	
}
