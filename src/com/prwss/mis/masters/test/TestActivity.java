package com.prwss.mis.masters.test;

import junit.framework.TestCase;

import org.junit.Before;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.masters.activity.ActivityBO;
import com.prwss.mis.masters.activity.struts.ActivityForm;

public class TestActivity extends TestCase{
	
	ActivityBO activityBO;
	MISSessionBean misAuditBean;

	@Before
	public void setUp() throws Exception {
		activityBO = (ActivityBO)SpringContextLoader.getBean("activityBO");
		misAuditBean = new MISSessionBean();
	}

//	public void testFindComponent() {
//		ActivityForm activityForm = new ActivityForm();
//		activityForm.setActivityCode("A-A-A");
//		activityForm.setActivityName("Activity for A");
//		activityForm.setActivityDescription("Activity description");
//		activityForm.setComponentId(1);
//		activityForm.setSubComponentId(2);
//		try {
//			System.out.println(activityBO.findComponent(activityForm,null));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void testSaveComponent() {
		ActivityForm activityForm = new ActivityForm();
		activityForm.setActivityId("A-A-A");
		activityForm.setActivityName("Activity for A");
		activityForm.setActivityDescription("Activity description");
		activityForm.setComponentId("1");
		activityForm.setSubComponentId("2");
		try {
			activityBO.saveActivity(activityForm, misAuditBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	public void testUpdateComponent() {
//		ActivityForm activityForm = new ActivityForm();
//		activityForm.setActivityId(1);
//		activityForm.setActivityCode("A-A-A");
//		activityForm.setActivityName("Activity for A");
//		activityForm.setActivityDescription("Activity description");
//		activityForm.setComponentId(1);
//		activityForm.setSubComponentId(2);
//		try {
//			System.out.println(activityBO.updateActivity(activityForm,misAuditBean));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void testDeleteComponent() {
//		ActivityForm activityForm = new ActivityForm();
//		Long[] activityIds = {new Long(1)}; 
//		activityForm.setActivityIds(activityIds);
//		try {
//			System.out.println(activityBO.deleteActivity(activityForm,misAuditBean));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public void testPostComponent() {
//		ActivityForm activityForm = new ActivityForm();
//		Long[] activityIds = {new Long(1)}; 
//		activityForm.setActivityIds(activityIds);
//		try {
//			System.out.println(activityBO.postActivity(activityForm,misAuditBean));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
