package com.prwss.mis.common.notification.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public  interface ActiveNotificationDao {
	
	public List<ActiveNotificationBean> findNotification(ActiveNotificationBean activeNotificationBean) throws DataAccessException;
	
	
}