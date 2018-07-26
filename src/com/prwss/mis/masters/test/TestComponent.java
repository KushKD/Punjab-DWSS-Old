package com.prwss.mis.masters.test;

import junit.framework.TestCase;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.masters.component.ComponentBO;
import com.prwss.mis.masters.component.struts.ComponentForm;

public class TestComponent extends TestCase {
	
	private ComponentBO componentBO;
//	private ComponentDao componentDao;
	private MISSessionBean misAuditBean;

	protected void setUp() throws Exception {
		componentBO = (ComponentBO)SpringContextLoader.getBean("componentBO");
//		componentDao = (ComponentDao)SpringContextLoader.getBean("componentDao");
//		System.out.println(componentBO);
		misAuditBean = new MISSessionBean();
	}

//	public void testDeleteComponent() {
//		System.out.println(componentBO);
//	}

//	public void testFindComponent() {
//		ComponentBean component = new ComponentBean();
//		component.setComponentId("10");
//		component.setComponentName("Program Management1");
//		component.setComponentDescription("Component A");
//		System.out.println(componentDao.findComponent(component,null));
//	}
	
	public void testSaveComponent(){
		ComponentForm componentForm = new ComponentForm();
		componentForm.setComponentId("A111");
		componentForm.setComponentName("Program Management");
		
		try {
			componentBO.saveComponent(componentForm, misAuditBean );
		} catch (MISException e) {
			e.printStackTrace();
			System.out.println(e.getCode()+"\t"+e.getMessage());
		}
	}
	
//	public void testUpdateComponent(){
//	ComponentForm componentForm = new ComponentForm();
//	componentForm.setComponentId("A");
//	componentForm.setComponentName("Program Management");
////	componentForm.setComponentDescription("Program Management");
//	
//		try {
//			componentBO.postComponent(componentForm, misAuditBean );
//		} catch (MISException e) {
//			e.printStackTrace();
//			System.out.println(e.getCode()+"\t"+e.getMessage());
//		}
//    }

//	public void testGetDistinctComponentCodes(){
//		
//		System.out.println(componentDao.getDistinctComponentCodes());
//		
//	}
	
}
