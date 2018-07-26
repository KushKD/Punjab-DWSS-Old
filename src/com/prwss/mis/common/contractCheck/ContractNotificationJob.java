package com.prwss.mis.common.contractCheck;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.prwss.mis.common.exception.MISException;

public class ContractNotificationJob extends QuartzJobBean{

	private Logger log = Logger.getLogger(ContractNotificationJob.class);

	ContractNotificationBO contractNotificationBO;
		
	public void setContractNotificationBO(
			ContractNotificationBO contractNotificationBO) {
		this.contractNotificationBO = contractNotificationBO;
	}


	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		try{
		contractNotificationBO.mailNotifier();
		}
		catch(MISException e){
			log.error(e.getLocalizedMessage(),e);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
