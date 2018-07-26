package com.prwss.mis.masters.test;

import junit.framework.TestCase;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.masters.component.dao.ComponentDao;
import com.prwss.mis.masters.subcomponent.SubComponentBO;
import com.prwss.mis.masters.subcomponent.struts.SubComponentForm;

public class TestSubComponent extends TestCase{
	
	SubComponentBO subComponentBO;
	ComponentDao componentDao;
	MISSessionBean misAuditBean;

	protected void setUp() throws Exception {
		subComponentBO = (SubComponentBO)SpringContextLoader.getBean("subComponentBO");
		componentDao = (ComponentDao)SpringContextLoader.getBean("componentDao");
		misAuditBean = new MISSessionBean();
	}
//
//	public void testFindSubComponent() {
//		
//		SubComponentForm subComponentForm = new SubComponentForm();
//		subComponentForm.setComponentId("A");
//
//		subComponentForm.setSubComponentDescription("Sub Component A - Component A");
//		subComponentForm.setSubComponentName("Sub A - A");
//		subComponentForm.setSubComponentId("A-A");
//		
//		try {
//			System.out.println(subComponentBO.findSubComponent(subComponentForm,null));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

//	public void testSaveSubComponent() {
//		SubComponentForm subComponentForm = new SubComponentForm();
//		subComponentForm.setComponentId("A");
//
//		subComponentForm.setSubComponentDescription("Sub Component A - Component A");
//		subComponentForm.setSubComponentName("Sub A - A");
//		subComponentForm.setSubComponentId("A-A");
//		
//		try {
//			subComponentBO.saveSubComponent(subComponentForm,misAuditBean);
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public void testUpdateSubComponent() {
//		SubComponentForm subComponentForm = new SubComponentForm();
//		subComponentForm.setComponentId("A");
//
//		subComponentForm.setSubComponentDescription("Sub Component A - Component A");
//		subComponentForm.setSubComponentName("Sub A - A");
//		subComponentForm.setSubComponentId("A-A");
//		
//		try {
//			subComponentBO.updateSubComponent(subComponentForm,misAuditBean);
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public void testDeleteSubComponent() {
//		SubComponentForm subComponentForm = new SubComponentForm();
//		String[] subComponentIds = {"A-A"};
//		subComponentForm.setSubComponentIds(subComponentIds);
//		
//		try {
//			subComponentBO.deleteSubComponent(subComponentForm,misAuditBean);
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void testPostSubComponent() {
		SubComponentForm subComponentForm = new SubComponentForm();
		String[] subComponentIds = {"A-A"};
		subComponentForm.setSubComponentIds(subComponentIds);
		
		try {
			subComponentBO.postSubComponent(subComponentForm,misAuditBean);
		} catch (MISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
