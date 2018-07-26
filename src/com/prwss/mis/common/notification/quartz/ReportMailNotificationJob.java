package com.prwss.mis.common.notification.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.notification.MailNotificationBO;
import com.prwss.mis.common.notification.ReportMailNotificationBO;

public class ReportMailNotificationJob extends QuartzJobBean {
	
	private Logger log = Logger.getLogger(MISNotificationJob.class);
	
	private ReportMailNotificationBO reportMailNotificationBO;

	public void setReportMailNotificationBO(
			ReportMailNotificationBO reportMailNotificationBO) {
		this.reportMailNotificationBO = reportMailNotificationBO;
	}


	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			System.out.println("vinay"+new Date(System.currentTimeMillis()));
			reportMailNotificationBO.mailNotifier();
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
		}
		
	}

}