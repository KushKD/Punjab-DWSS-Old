package com.prwss.mis.masters.test;

import junit.framework.TestCase;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.masters.circle.CircleBO;
import com.prwss.mis.masters.circle.struts.CircleForm;

public class TestCircle extends TestCase {

	private CircleBO circleBO;
	private MISSessionBean misAuditBean;

	protected void setUp() throws Exception {
		circleBO = (CircleBO)SpringContextLoader.getBean("circleBO");
		misAuditBean = new MISSessionBean();
	}

//	public void testFindComponent() {
//		CircleForm circleForm = new CircleForm();
//		circleForm.setCircleId("1");
//		circleForm.setCircleName("Circle A");
//		circleForm.setZoneId("1");
//		try {
//			System.out.println(circleBO.findCircle(circleForm, null));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void testSaveComponent() {
		
		CircleForm circleForm = new CircleForm();
		circleForm.setCircleId("CERFRZ");
		circleForm.setCircleName("Firozpur Circle");
		circleForm.setZoneId("S");
		try {
			System.out.println(circleBO.saveCircle(circleForm, misAuditBean));
		} catch (MISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	public void testUpdateComponent() {
//		CircleForm circleForm = new CircleForm();
//		circleForm.setCircleId("1");
//		circleForm.setCircleName("Circle Aaaaaaaaaaa");
//		circleForm.setZoneId("1");
//		try {
//			System.out.println(circleBO.updateCircle(circleForm, misAuditBean));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public void testDeleteComponent() {
//		CircleForm circleForm = new CircleForm();
//		String[] circleIds = {"1"};
//		circleForm.setCircleIds(circleIds);
//		try {
//			System.out.println(circleBO.deleteCircle(circleForm, misAuditBean));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public void testPostComponent() {
//		CircleForm circleForm = new CircleForm();
//		String[] circleIds = {"1"};
//		circleForm.setCircleIds(circleIds);
//		try {
//			System.out.println(circleBO.postCircle(circleForm, misAuditBean));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
