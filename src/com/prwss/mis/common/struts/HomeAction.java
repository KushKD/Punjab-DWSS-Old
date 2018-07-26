package com.prwss.mis.common.struts;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.prwss.mis.admin.MessageBrodcastBean;
import com.prwss.mis.admin.dao.MessageBrodcastDao;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.notification.dao.ActiveNotificationBean;
import com.prwss.mis.common.notification.dao.ActiveNotificationDao;
import com.prwss.mis.common.notification.dao.NotificationBean;
import com.prwss.mis.common.notification.dao.NotificationDao;
import com.prwss.mis.common.util.MisUtility;

public class HomeAction extends Action {

	private ActiveNotificationDao activeNotificationDao;
	private NotificationDao notificationDao;
	private Logger log = Logger.getLogger(HomeAction.class);	
	private MISSessionBean misSessionBean;
	private MessageBrodcastDao messageBrodcastDao;
	
	
	public void setMessageBrodcastDao(MessageBrodcastDao messageBrodcastDao) {
		this.messageBrodcastDao = messageBrodcastDao;
	}
	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}

	public void setActiveNotificationDao(ActiveNotificationDao activeNotificationDao) {
		this.activeNotificationDao = activeNotificationDao;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws Exception{		
		this.setAttrib(request);
		long employeeId = 0;
		List<ActiveNotificationBean> activeNotificationBeans = null;
		
		List<Long> notificationIds = null;		
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			employeeId=misSessionBean.getEnteredBy();

			//NotificationBean notificationBean = new NotificationBean();
			List<NotificationBean> notificationBeans2 = new ArrayList<NotificationBean>();
			List<NotificationBean> notificationBeans3= new ArrayList<NotificationBean>();
			List<NotificationBean> notificationBeans4= new ArrayList<NotificationBean>();
			
			List<MessageBrodcastBean> messageBrodcastBeans = new ArrayList<MessageBrodcastBean>();
			messageBrodcastBeans = messageBrodcastDao.findMessages();
			request.setAttribute("MessageBrodcastList", messageBrodcastBeans);
			
			ActiveNotificationBean activeNotificationBean = new ActiveNotificationBean();		

			activeNotificationBean.setEmployeeId(employeeId);
			activeNotificationBeans= activeNotificationDao.findNotification(activeNotificationBean);
			if(!MisUtility.ifEmpty(activeNotificationBeans)){
			notificationIds = new ArrayList<Long>();
			for (ActiveNotificationBean activeNotificationBean2 : activeNotificationBeans) {
				notificationIds.add(new Long(activeNotificationBean2.getNotificationId()));

			}		
             //NotificationBean notificationBean = new NotificationBean();
			List<NotificationBean> notificationBeans  = new ArrayList<NotificationBean>();
			notificationBeans = notificationDao.findNotification(notificationIds);	
			for (NotificationBean notificationBean2 : notificationBeans) {
				if(notificationBean2.getExceptionType().toString().equals("NOTIFICATION"))
					notificationBeans2.add(notificationBean2);
				else if(notificationBean2.getExceptionType().toString().equals("APPROVAL"))
					notificationBeans3.add(notificationBean2);
				else if (notificationBean2.getExceptionType().toString().equals("TASK"))
					notificationBeans4.add(notificationBean2);
			}	
			
			
			
			
			request.setAttribute("NotificationBeanList", notificationBeans2);
			request.setAttribute("NotificationApprovalBeanList", notificationBeans3);
			request.setAttribute("NotificationTaskBeanList", notificationBeans4);
			
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}


		return mapping.findForward("success");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("op", "fffffffffff");
		request.setAttribute("d__mode", request.getParameter("d__mode"));

	}



}