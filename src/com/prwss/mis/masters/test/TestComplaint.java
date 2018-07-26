package com.prwss.mis.masters.test;

import junit.framework.TestCase;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.masters.complaint.ComplaintBO;
import com.prwss.mis.masters.complaint.struts.ComplaintForm;

public class TestComplaint extends TestCase {
	
	private ComplaintBO complaintBO;
	private MISSessionBean misAuditBean;
	

	protected void setUp() throws Exception {
		complaintBO = (ComplaintBO)SpringContextLoader.getBean("complaintBO");
		misAuditBean = new MISSessionBean();
	}

//	public void testFindComplaint() {
//		ComplaintForm complaintForm = new ComplaintForm();
//		complaintForm.setComplaintId(1L);
////		complaintForm.setComplaintType("A");
////		complaintForm.setComplaintDescription("AAAA");
////		complaintForm.setLevel1EmployeeId(1);
////		complaintForm.setLevel2EmployeeId(2);
////		complaintForm.setLevel3EmployeeId(3);
////		complaintForm.setLevel4EmployeeId(4);
////		complaintForm.setLevel5EmployeeId(5);
////		complaintForm.setLevel1RedressalDays(5);
////		complaintForm.setLevel2RedressalDays(4);
////		complaintForm.setLevel3RedressalDays(3);
////		complaintForm.setLevel4RedressalDays(2);
////		complaintForm.setLevel5RedressalDays(1);
//		
//		try {
//			System.out.println(complaintBO.findComplaint(complaintForm, null));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void testSaveComplaint() {
		ComplaintForm complaintForm = new ComplaintForm();
//		complaintForm.setComplaintId(1);
		complaintForm.setComplaintType("Software");
		complaintForm.setComplaintDescription("Software Problems");
		complaintForm.setLevel1EmployeeId(1005l);
		complaintForm.setLevel2EmployeeId(1006l);
		complaintForm.setLevel3EmployeeId(1007l);
		complaintForm.setLevel4EmployeeId(1008l);
		complaintForm.setLevel5EmployeeId(1009l);
		complaintForm.setLevel1RedressalDays(2l);
		complaintForm.setLevel2RedressalDays(4l);
		complaintForm.setLevel3RedressalDays(3l);
		complaintForm.setLevel4RedressalDays(2l);
		complaintForm.setLevel5RedressalDays(1l);
		
		try {
			complaintBO.saveComplaint(complaintForm, misAuditBean);
		} catch (MISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/*	public void testUpdateComplaint() {
		fail("Not yet implemented"); // TODO
	}

	public void testDeleteComplaint() {
		fail("Not yet implemented"); // TODO
	}

	public void testPostComplaint() {
		fail("Not yet implemented"); // TODO
	}*/

}
