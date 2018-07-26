package com.prwss.mis.masters.test;

import junit.framework.TestCase;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.masters.zone.ZoneBO;
import com.prwss.mis.masters.zone.struts.ZoneForm;

public class TestZone extends TestCase {
	
	private ZoneBO zoneBO;
	private MISSessionBean misAuditBean;
	
	@Override
	protected void setUp() throws Exception {

		zoneBO = (ZoneBO)SpringContextLoader.getBean("zoneBO");
		misAuditBean = new MISSessionBean();
		
	}
	
	public void testSaveZone(){
		
		ZoneForm zoneForm = new ZoneForm();
		zoneForm.setZoneId("C");
		zoneForm.setZoneName("Central Zone");
		try {
			zoneBO.saveZone(zoneForm, misAuditBean);
		} catch (MISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void testFindZone(){
//	
//	ZoneForm zoneForm = new ZoneForm();
////
////	zoneForm.setZoneId("A");
////	zoneForm.setZoneName("Ak");
//
//	try {
//		System.out.println(zoneBO.findZone(zoneForm,null));
//	} catch (MISException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//    }
	
//
//	public void testUpdateZone(){
//		
//		ZoneForm zoneForm = new ZoneForm();
//		zoneForm.setZoneId("4");
//		zoneForm.setZoneName("Zone D");
//		try {
//			zoneBO.updateZone(zoneForm, misAuditBean);
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public void testPostZone(){
//		String[] zoneIds ={"1","2"};
//		ZoneForm zoneForm = new ZoneForm();
//		zoneForm.setZoneIds(zoneIds);
//		try {
//			zoneBO.postZone(zoneForm, misAuditBean);
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public void testDeleteZone(){
//		String[] zoneIds ={"1","2"};
//		ZoneForm zoneForm = new ZoneForm();
//		zoneForm.setZoneIds(zoneIds);
//		try {
//			zoneBO.deleteZone(zoneForm, misAuditBean);
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
//	public void testGetDistinctZoneCodes(){
//		
//		System.out.println(zoneBO.getDistinctZoneCodes());
//	}
	
}
