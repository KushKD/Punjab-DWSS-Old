package com.prwss.mis.common.tenderCheck;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.notification.MailNotificationBO;
import com.prwss.mis.common.notification.quartz.MISNotificationJob;

public class TenderNotificationJob extends QuartzJobBean {

private Logger log = Logger.getLogger(TenderNotificationJob.class);
	
	private TenderNotificationBO tenderNotificationBO = null;

	public void setTenderNotificationBO(TenderNotificationBO tenderNotificationBO) {		
		this.tenderNotificationBO = tenderNotificationBO;
	}

	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		try{
			System.out.println("----------Mail----------------");
		try {
			tenderNotificationBO.mailNotifier();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		catch(MISException e){
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		
		
	}

}
