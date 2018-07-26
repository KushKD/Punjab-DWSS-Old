package com.prwss.mis.common.notification.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface MailNotificationDao {
	
	public List<MailNotificationBean> findMailNotification(MailNotificationBean mailNotificationBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveMailNotification(List<MailNotificationArchiveBean> mailNotificationArchiveBeans) throws DataAccessException;
	
	public boolean saveMailNotificationBean(List<MailNotificationBean> mailNotificationBeans) throws DataAccessException;
	
	public boolean deleteMailNotification(List<MailNotificationBean> mailNotificationBeans) throws DataAccessException;

}
