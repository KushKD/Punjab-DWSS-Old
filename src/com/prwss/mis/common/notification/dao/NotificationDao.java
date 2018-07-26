package com.prwss.mis.common.notification.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface NotificationDao {
	
	public List<NotificationBean> findNotification(NotificationBean notificationBean, List<String> statusList) throws DataAccessException;
	
	
	public List<NotificationBean> findNotification(List<Long> notificationIds) throws DataAccessException;
	
	public long saveNotification(NotificationBean notificationBean) throws DataAccessException;

	public boolean updateNotification(NotificationBean notificationBean)	throws DataAccessException;
	
	public boolean updateNotification(List<NotificationBean> notificationBeans) throws DataAccessException;
	
}
