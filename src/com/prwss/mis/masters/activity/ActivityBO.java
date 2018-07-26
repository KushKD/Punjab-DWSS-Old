package com.prwss.mis.masters.activity;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.activity.dao.ActivityBean;
import com.prwss.mis.masters.activity.struts.ActivityForm;

public interface ActivityBO {
	
	public List<ActivityBean> findActivity(ActivityForm activityForm, List<String> statusList) throws MISException;
	
	public boolean saveActivity(ActivityForm activityForm, MISSessionBean misAuditBean) throws MISException;
	
	/*public boolean updateActivity(ActivityForm activityForm, MISSessionBean misAuditBean) throws MISException;*/
	
	public boolean deleteActivity(ActivityForm activityForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postActivity(ActivityForm activityForm, MISSessionBean misAuditBean) throws MISException;

	boolean updateActivity(ActivityForm activityForm,
			MISSessionBean misAuditBean, List<String> statusList)
			throws MISException;

}
