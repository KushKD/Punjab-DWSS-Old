package com.prwss.mis.masters.activity;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.activity.dao.ActivityBean;
import com.prwss.mis.masters.activity.dao.ActivityDao;
import com.prwss.mis.masters.activity.struts.ActivityForm;
import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.subcomponent.dao.SubComponentBean;

public class ActivityBOImpl implements ActivityBO {
	
	Logger log = Logger.getLogger(ActivityBOImpl.class);
	
	private ActivityDao activityDao;

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	@Override
	public List<ActivityBean> findActivity(ActivityForm activityForm, List<String> statusList) throws MISException {
		List<ActivityBean> activityBeans = null;
		ActivityBean activityBean = new ActivityBean();
		
		try {
			activityBean.setActivityId(activityForm.getActivityId());
			activityBean.setActivityName(activityForm.getActivityName());
			activityBean.setActivityDescription(activityForm.getActivityDescription());
			
			SubComponentBean subComponent = new SubComponentBean();
			subComponent.setSubComponentId(activityForm.getSubComponentId());
			activityBean.setSubComponent(subComponent);
			
			ComponentBean component = new ComponentBean();
			component.setComponentId(activityForm.getComponentId());
			activityBean.setComponent(component);
			
			activityBeans = activityDao.findActivity(activityBean, statusList);
			
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return activityBeans;
	}

	@Override
	public boolean saveActivity(ActivityForm activityForm, MISSessionBean misAuditBean) throws MISException {
		
		boolean status = false;
		ActivityBean activityBean = new ActivityBean();
		try {
			
			activityBean.setActivityId(activityForm.getActivityId());
			String activityName = activityForm.getActivityName();
			 List<ActivityBean> activityBeans = activityDao.findActivity(activityBean, null);
			 if(!MisUtility.ifEmpty(activityBeans)){					
				 StringBuffer message = new StringBuffer();
				 message.append(" Activity code ").append(activityForm.getActivityId());
				 if(activityName.equalsIgnoreCase(activityBeans.get(0).getActivityName())){
					 message.append(" and Activity Name ").append(activityName);
				 }
				 log.debug("Duplicate Entry for "+activityForm.getActivityId()+" and "+ activityForm.getActivityName());
				 log.debug("Activity already exist \n"+activityBeans);
				 throw new MISException(MISExceptionCodes.MIS001 , message.toString());
			 }
			activityBean.setActivityName(activityForm.getActivityName());
			activityBean.setActivityDescription(activityForm.getActivityDescription());
			
			SubComponentBean subComponent = new SubComponentBean();
			subComponent.setSubComponentId(activityForm.getSubComponentId());
			activityBean.setSubComponent(subComponent);
			
			ComponentBean component = new ComponentBean();
			component.setComponentId(activityForm.getComponentId());
			activityBean.setComponent(component);
			
			activityBean.setInsertedBy(misAuditBean.getEnteredBy());
			activityBean.setInsertedDate(misAuditBean.getEnteredDate());
			activityBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);

			activityDao.saveActivity(activityBean);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
			
		return status;
	}

	@Override
	public boolean updateActivity(ActivityForm activityForm, MISSessionBean misAuditBean,List<String> statusList) throws MISException {
		
		boolean status = false;
		ActivityBean activityBean = new ActivityBean();
		try {
			activityBean.setActivityId(activityForm.getActivityId());
			activityBean.setActivityName(activityForm.getActivityName());
			activityBean.setActivityDescription(activityForm.getActivityDescription());
			
			SubComponentBean subComponent = new SubComponentBean();
			subComponent.setSubComponentId(activityForm.getSubComponentId());
			activityBean.setSubComponent(subComponent);
			
			ComponentBean component = new ComponentBean();
			component.setComponentId(activityForm.getComponentId());
			activityBean.setComponent(component);
			
			activityBean.setInsertedBy(misAuditBean.getEnteredBy());
			activityBean.setInsertedDate(misAuditBean.getEnteredDate());
			activityBean.setStatus(statusList.get(0));
			
			status = activityDao.updateActivity(activityBean);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}

	@Override
	public boolean deleteActivity(ActivityForm activityForm, MISSessionBean misAuditBean) throws MISException {
		boolean status = false; 
		try {
			ActivityBean activityBean = new ActivityBean();
			
//			List<ActivityBean> activityBeans = activityDao.findActivity(Arrays.asList(activityForm.getActivityIds()));
//			for (ActivityBean activityBean : activityBeans) {
			activityBean.setActivityId(activityForm.getActivityId());
			activityBean.setActivityName(activityForm.getActivityName());
			activityBean.setActivityDescription(activityForm.getActivityDescription());
			SubComponentBean subComponent = new SubComponentBean();
			subComponent.setSubComponentId(activityForm.getSubComponentId());
			activityBean.setSubComponent(subComponent);
			ComponentBean component = new ComponentBean();
			component.setComponentId(activityForm.getComponentId());
			activityBean.setComponent(component);
			activityBean.setInsertedBy(misAuditBean.getEnteredBy());
			activityBean.setInsertedDate(misAuditBean.getEnteredDate());
				activityBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				activityBean.setInsertedBy(misAuditBean.getEnteredBy());
				activityBean.setInsertedDate(misAuditBean.getEnteredDate());
//			}
			status = activityDao.updateActivity(activityBean);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean postActivity(ActivityForm activityForm, MISSessionBean misAuditBean) throws MISException {
		boolean status = false; 
		try {
			ActivityBean activityBean = new ActivityBean();
			activityBean.setActivityId(activityForm.getActivityId());
			ActivityBean activityBean2 = activityDao.findActivity(activityBean, null).get(0); 
//			List<ActivityBean> activityBeans = activityDao.findActivity(Arrays.asList(activityForm.getActivityIds()));
//			for (ActivityBean activityBean : activityBeans) {
			activityBean2.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			activityBean2.setAuthorizedBy(misAuditBean.getEnteredBy());
			activityBean2.setAuthorizedDate(misAuditBean.getEnteredDate());
//				}
			status = activityDao.updateActivity(activityBean2);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

}
