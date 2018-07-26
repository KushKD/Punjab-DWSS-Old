package com.prwss.mis.common.notification.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.notification.MailNotificationBO;

public class MISNotificationJob extends QuartzJobBean {
	
	private Logger log = Logger.getLogger(MISNotificationJob.class);
	
	private MailNotificationBO mailNotificationBO;

	public void setMailNotificationBO(MailNotificationBO mailNotificationBO) {
		this.mailNotificationBO = mailNotificationBO;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			System.out.println("vinay"+new Date(System.currentTimeMillis()));
			mailNotificationBO.mailNotifier();
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
		}
		
	}

}
