package com.prwss.mis.common.notification.dao;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import junit.framework.TestCase;

import com.prwss.mis.common.util.SpringContextLoader;

public class TestNotifcationDao extends TestCase {

	private NotificationJdcDaoImpl notificationDao;
	
	public void setNotificationDao(NotificationJdcDaoImpl notificationDao) {
		this.notificationDao = notificationDao;
	}

	protected void setUp() throws Exception {
		notificationDao = (NotificationJdcDaoImpl)SpringContextLoader.getBean("notificationJdcDao");
	}

	public void testGetNotificationById() {
		
		try {
			System.out.println(notificationDao.getNotificationById("125"));
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
