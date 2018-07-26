package com.prwss.mis.service.test;

import junit.framework.TestCase;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.service.ticket.ComplaintBookBO;
import com.prwss.mis.service.ticket.struts.NewTicketForm;

public class TestComplaintBook extends TestCase {
	
	private ComplaintBookBO complaintBookBO;
	private MISSessionBean misAuditBean = new MISSessionBean();

	protected void setUp() throws Exception {
		complaintBookBO = (ComplaintBookBO)SpringContextLoader.getBean("complaintBookBO");
	}

//	public void testFindComplaint() {
//		MyTicketForm myTicketForm = new MyTicketForm();
////		myTicketForm.setTicketId(1L);
//		Calendar cal = GregorianCalendar.getInstance();
//		cal.add(Calendar.DATE, -4);
//		Date fromDate = new Date(cal.getTime().getTime());
//		
//		
//		cal.add(Calendar.DATE, 6);
//		Date toDate = new Date(cal.getTime().getTime());
//		
//		 
//		System.out.println(fromDate+"----------"+toDate);
////		myTicketForm.setFromDate(fromDate);
////		myTicketForm.setToDate(toDate);
////		myTicketForm.setStatus("closed");
////		System.out.println(myTicketForm.getStatus());
//		try {
//			System.out.println();
////			Set<ComplaintBookBean> complaintBookBeans = complaintBookBO.findAssignedComplaint(myTicketForm, 26);
////			System.out.println("_______________________________________________________________________________________________");
//			Set<ComplaintBookBean> complaintBookBeans = complaintBookBO.findMyComplaint(myTicketForm, misAuditBean);
//			for (ComplaintBookBean complaintBookBean : complaintBookBeans) {
//			System.out.println(complaintBookBean.getTicketId()+"\t"+complaintBookBean.getStatus()+"\t"+complaintBookBean.getEntDate());	
//			}
//		} catch (MISException e) {
//			e.printStackTrace();
//		}
//	}

	public void testSaveComplaint() {
		NewTicketForm newTicketForm = new NewTicketForm();
		newTicketForm.setComplaintId(new Long(1));
		newTicketForm.setDescription("This is Junk1");
		newTicketForm.setPriorityLevel("low");
		newTicketForm.setSubject("This is new junk111111");
		try {
			System.out.println(complaintBookBO.saveComplaint(newTicketForm, misAuditBean));
		} catch (MISException e) {
			System.out.println(e.getCause());
		}
		
	}

//	public void testUpdateComplaintResolution() {
//		TicketResolutionForm ticketResolutionForm = new TicketResolutionForm();
//		ticketResolutionForm.setTicketId(15L);
//		ticketResolutionForm.setComments("Re Opened");
//		ticketResolutionForm.setStatus("Open");
//		ticketResolutionForm.setForward(true);
//		try {
//			complaintBookBO.updateComplaintResolution(ticketResolutionForm, misAuditBean);
//		} catch (MISException e) {
//			System.out.println("_________________________________________________________________________________");
//			e.printStackTrace();
//		}
//	}
	
//	public void testGetComplaintHistory(){
//		long ticketId = 14;
//		try {
//			System.out.println(complaintBookBO.getComplaintHistory(ticketId));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
