package com.prwss.mis.tender.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.tender.TenderBO;
import com.prwss.mis.tender.award.dao.TenderObjectionBean;
import com.prwss.mis.tender.struts.TenderAwardForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class TestTender extends TestCase {
	private TenderBO tenderBO;
	private MISSessionBean misAuditBean;
	
	protected void setUp() throws Exception {
		tenderBO = (TenderBO)SpringContextLoader.getBean("tenderBO");
		misAuditBean = new MISSessionBean();
	}

//	public void testFindTenders() {
//		TenderManagementForm tenderManagementForm = new TenderManagementForm();
////		tenderManagementForm.setTenderNo("T3");
//		
//		try {
//			System.out.println(tenderBO.findTenders(tenderManagementForm, null));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public void testSave() {
//		TenderManagementForm tenderManagementForm = new TenderManagementForm();
//		Date date = new Date(System.currentTimeMillis());
//		tenderManagementForm.setTenderNo("T3");
//		tenderManagementForm.setTypeOfTender("Test");
//		tenderManagementForm.setTenderNotificationDate(date);
//		tenderManagementForm.setClosingDate(date);
//		tenderManagementForm.setSchemeId("S1");
//		tenderManagementForm.setPackageId(1);
//		tenderManagementForm.setOpeningDate(date);
//		tenderManagementForm.setModeOfProcurement("mode");
//		tenderManagementForm.setA1ActualDateofCompletion(date);
//		tenderManagementForm.setA1EstimateDateofCompletion(date);
//		tenderManagementForm.setA1StatusOfActivity("Active");
//		
//		tenderManagementForm.setA2ActualDateofCompletion(date);
//		tenderManagementForm.setA2EstimateDateofCompletion(date);
//		tenderManagementForm.setA2StatusOfActivity("Active");
//		
//		tenderManagementForm.setA3ActualDateofCompletion(date);
//		tenderManagementForm.setA3EstimateDateofCompletion(date);
//		tenderManagementForm.setA3StatusOfActivity("Active");
//		
//		tenderManagementForm.setA4ActualDateofCompletion(date);
//		tenderManagementForm.setA4EstimateDateofCompletion(date);
//		tenderManagementForm.setA4StatusOfActivity("Active");
//		
//		tenderManagementForm.setA5ActualDateofCompletion(date);
//		tenderManagementForm.setA5EstimateDateofCompletion(date);
//		tenderManagementForm.setA5StatusOfActivity("Active");
//		
//		tenderManagementForm.setA6ActualDateofCompletion(date);
//		tenderManagementForm.setA6EstimateDateofCompletion(date);
//		tenderManagementForm.setA6StatusOfActivity("Active");
//		
//		tenderManagementForm.setA7ActualDateofCompletion(date);
//		tenderManagementForm.setA7EstimateDateofCompletion(date);
//		tenderManagementForm.setA7StatusOfActivity("Active");
//		
//		try {
//			tenderBO.post(tenderManagementForm, misAuditBean);
//		} catch (MISException e) {
//			e.printStackTrace();
//		}
//	}

//	public void testUpdate() {
//		TenderManagementForm tenderManagementForm = new TenderManagementForm();
//		Date date = new Date(System.currentTimeMillis());
//		tenderManagementForm.setTenderNo("T3");
//		tenderManagementForm.setTypeOfTender("Test");
//		tenderManagementForm.setTenderNotificationDate(date);
//		tenderManagementForm.setClosingDate(date);
//		tenderManagementForm.setSchemeId("S1");
//		//tenderManagementForm.setPackageId(1);
//		tenderManagementForm.setOpeningDate(date);
//		tenderManagementForm.setModeOfProcurement("mode");
//		tenderManagementForm.setC1ActualDateofCompletion(date);
//		tenderManagementForm.setC1EstimateDateofCompletion(date);
//		tenderManagementForm.setC1StatusOfActivity("Active");
//		
//		tenderManagementForm.setC2ActualDateofCompletion(date);
//		tenderManagementForm.setC2EstimateDateofCompletion(date);
//		tenderManagementForm.setC2StatusOfActivity("Active");
//		
//		tenderManagementForm.setC3ActualDateofCompletion(date);
//		tenderManagementForm.setC3EstimateDateofCompletion(date);
//		tenderManagementForm.setC3StatusOfActivity("Active");
//		
//		tenderManagementForm.setC4ActualDateofCompletion(date);
//		tenderManagementForm.setC4EstimateDateofCompletion(date);
//		tenderManagementForm.setC4StatusOfActivity("Active");
//		
//		tenderManagementForm.setC5ActualDateofCompletion(date);
//		tenderManagementForm.setC5EstimateDateofCompletion(date);
//		tenderManagementForm.setC5StatusOfActivity("Active");
//		
//		tenderManagementForm.setC6ActualDateofCompletion(date);
//		tenderManagementForm.setC6EstimateDateofCompletion(date);
//		tenderManagementForm.setC6StatusOfActivity("Active");
//		
//		tenderManagementForm.setC7ActualDateofCompletion(date);
//		tenderManagementForm.setC7EstimateDateofCompletion(date);
//		tenderManagementForm.setC7StatusOfActivity("Done");
//		
//		try {
//			tenderBO.update(tenderManagementForm, misAuditBean);
//		} catch (MISException e) {
//			e.printStackTrace();
//		}
//	}

//	public void testDelete() {
//		TenderManagementForm tenderManagementForm = new TenderManagementForm();
//		Date date = new Date(System.currentTimeMillis());
//		tenderManagementForm.setTenderNo("T3");
//		tenderManagementForm.setTypeOfTender("Test");
//		tenderManagementForm.setTenderNotificationDate(date);
//		tenderManagementForm.setClosingDate(date);
//		tenderManagementForm.setSchemeId("S1");
//		tenderManagementForm.setPackageId(1);
//		tenderManagementForm.setOpeningDate(date);
//		tenderManagementForm.setModeOfProcurement("mode");
//		tenderManagementForm.setC1ActualDateofCompletion(date);
//		tenderManagementForm.setA1EstimateDateofCompletion(date);
//		tenderManagementForm.setA1StatusOfActivity("Active");
//		
//		tenderManagementForm.setA2ActualDateofCompletion(date);
//		tenderManagementForm.setA2EstimateDateofCompletion(date);
//		tenderManagementForm.setA2StatusOfActivity("Active");
//		
//		tenderManagementForm.setA3ActualDateofCompletion(date);
//		tenderManagementForm.setA3EstimateDateofCompletion(date);
//		tenderManagementForm.setA3StatusOfActivity("Active");
//		
//		tenderManagementForm.setA4ActualDateofCompletion(date);
//		tenderManagementForm.setA4EstimateDateofCompletion(date);
//		tenderManagementForm.setA4StatusOfActivity("Active");
//		
//		tenderManagementForm.setA5ActualDateofCompletion(date);
//		tenderManagementForm.setA5EstimateDateofCompletion(date);
//		tenderManagementForm.setA5StatusOfActivity("Active");
//		
//		tenderManagementForm.setA6ActualDateofCompletion(date);
//		tenderManagementForm.setA6EstimateDateofCompletion(date);
//		tenderManagementForm.setA6StatusOfActivity("Active");
//		
//		tenderManagementForm.setA7ActualDateofCompletion(date);
//		tenderManagementForm.setA7EstimateDateofCompletion(date);
//		tenderManagementForm.setA7StatusOfActivity("Done");
//		
//		try {
//			tenderBO.delete(tenderManagementForm, misAuditBean);
//		} catch (MISException e) {
//			e.printStackTrace();
//		}
//	}
	
//	public void testPost(){
//		TenderManagementForm tenderManagementForm = new TenderManagementForm();
//		Date date = new Date(System.currentTimeMillis());
//		tenderManagementForm.setTenderNo("T3");
//		tenderManagementForm.setTypeOfTender("Test");
//		tenderManagementForm.setTenderNotificationDate(date);
//		tenderManagementForm.setClosingDate(date);
//		tenderManagementForm.setSchemeId("S1");
//		tenderManagementForm.setPackageId(1);
//		tenderManagementForm.setOpeningDate(date);
//		tenderManagementForm.setModeOfProcurement("mode");
//		tenderManagementForm.setA1ActualDateofCompletion(date);
//		tenderManagementForm.setA1EstimateDateofCompletion(date);
//		tenderManagementForm.setA1StatusOfActivity("Active");
//		
//		tenderManagementForm.setA2ActualDateofCompletion(date);
//		tenderManagementForm.setA2EstimateDateofCompletion(date);
//		tenderManagementForm.setA2StatusOfActivity("Active");
//		
//		tenderManagementForm.setA3ActualDateofCompletion(date);
//		tenderManagementForm.setA3EstimateDateofCompletion(date);
//		tenderManagementForm.setA3StatusOfActivity("Active");
//		
//		tenderManagementForm.setA4ActualDateofCompletion(date);
//		tenderManagementForm.setA4EstimateDateofCompletion(date);
//		tenderManagementForm.setA4StatusOfActivity("Active");
//		
//		tenderManagementForm.setA5ActualDateofCompletion(date);
//		tenderManagementForm.setA5EstimateDateofCompletion(date);
//		tenderManagementForm.setA5StatusOfActivity("Active");
//		
//		tenderManagementForm.setA6ActualDateofCompletion(date);
//		tenderManagementForm.setA6EstimateDateofCompletion(date);
//		tenderManagementForm.setA6StatusOfActivity("Active");
//		
//		tenderManagementForm.setA7ActualDateofCompletion(date);
//		tenderManagementForm.setA7EstimateDateofCompletion(date);
//		tenderManagementForm.setA7StatusOfActivity("Active");
//		
//		try {
//			tenderBO.post(tenderManagementForm, misAuditBean);
//		} catch (MISException e) {
//			e.printStackTrace();
//		}
//	}

//	public void testGetTenderDetails() {
//		fail("Not yet implemented"); // TODO
//	}
//	
//	public void testFindTenderAwarded(){
//		
//		TenderAwardForm tenderAwardForm = new TenderAwardForm();
//		tenderAwardForm.setTenderNo("T3");
//		tenderAwardForm.setTenderAwardAmount(0.0);
//		try {
//			System.out.println(tenderBO.findTenderAwarded(tenderAwardForm, null));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void testUpdateTenderAwarded(){
		TenderAwardForm tenderAwardForm = new TenderAwardForm();
//		Date date = new Date(System.currentTimeMillis());
		tenderAwardForm.setTenderNo("T3");
		tenderAwardForm.setTenderAwardAmount(99999999999.99);
//		tenderAwardForm.setContractEndDate(date);
		tenderAwardForm.setContractNo("1");
//		tenderAwardForm.setContractStartDate(date);
		tenderAwardForm.setVendorId("A");
		Datagrid objectionDatagrid = Datagrid.getInstance();
		objectionDatagrid.setDataClass(TenderObjectionBean.class);
		TenderObjectionBean tenderObjectionBean = new TenderObjectionBean();
		tenderObjectionBean.setLocationId("1");
		tenderObjectionBean.setTenderId("1");
		tenderObjectionBean.setObjectedBy("1");
		List<TenderObjectionBean> tenderObjectionBeans = new ArrayList<TenderObjectionBean>(); 
		tenderObjectionBeans.add(tenderObjectionBean);
		objectionDatagrid.setData(tenderObjectionBeans);
		objectionDatagrid.setDataState(0, Datagrid.REMOVED);
		tenderAwardForm.setObjectionDatagrid(objectionDatagrid);
		try {
			System.out.println(tenderBO.saveTenderAwarded(tenderAwardForm, misAuditBean));
		} catch (MISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void testUpdateTenderAwarded(){
//	TenderAwardForm tenderAwardForm = new TenderAwardForm();
//	Date date = new Date(System.currentTimeMillis());
//	tenderAwardForm.setTenderNo("T3");
//	tenderAwardForm.setTenderAwardAmount(99999999999.99);
//	tenderAwardForm.setContractEndDate(date);
//	tenderAwardForm.setContractNo("1");
//	tenderAwardForm.setContractStartDate(date);
//	tenderAwardForm.setVendorId("A");
//	try {
//		System.out.println(tenderBO.updateTenderAwarded(tenderAwardForm, misAuditBean));
//	} catch (MISException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}
	
//	public void testPostTenderAwarded(){
//		TenderAwardForm tenderAwardForm = new TenderAwardForm();
//		Date date = new Date(System.currentTimeMillis());
//		tenderAwardForm.setTenderNo("T3");
//		tenderAwardForm.setTenderAwardAmount(99999999999.99);
//		tenderAwardForm.setContractEndDate(date);
//		tenderAwardForm.setContractNo("1");
//		tenderAwardForm.setContractStartDate(date);
//		tenderAwardForm.setVendorId("A");
//		try {
//			System.out.println(tenderBO.postTenderAwarded(tenderAwardForm, misAuditBean));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
