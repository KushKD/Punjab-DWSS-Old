package com.prwss.mis.service.ticket.struts;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.exception.MISSessionTimeOutException;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.service.ticket.ComplaintBookBO;
import com.prwss.mis.service.ticket.dao.ComplaintBookBean;
import com.prwss.mis.service.ticket.dao.ComplaintHistoryBean;

public class TicketSearchAction extends DispatchAction {

	private ComplaintBookBO complaintBookBO;
	private MISSessionBean misAuditBean;
	
	public void setComplaintBookBO(ComplaintBookBO complaintBookBO) {
		this.complaintBookBO = complaintBookBO;
	}
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		System.out.println("In Find");
		MyTicketForm myTicketForm = (MyTicketForm) form;
		System.out.println(myTicketForm);
		try {
			getMyTickets(request, myTicketForm);
		} catch (MISSessionTimeOutException e) {
			log.error("User session timedout" + e);
			return mapping.findForward("login");
		}
		refreshMyTicketForm(myTicketForm);
		return mapping.findForward("display");
	}
	
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("Ticket Search unspecified");
		this.setAttrib(request);
		MyTicketForm myTicketForm = new MyTicketForm();
		myTicketForm.setStatus("Open");
		try {
			getMyTickets(request, myTicketForm);
		} catch (MISSessionTimeOutException e) {
			log.error("User session timedout" + e);
			return mapping.findForward("login");
		}
		refreshMyTicketForm(myTicketForm);
		
		return mapping.findForward("display");
	}
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		System.out.println("Ticket Search :----"+request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky","ticketId@status@fromDate@toDate");
	}
	
	public ActionForward getTicket(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		this.setAttrib(request);
		request.setAttribute("op", "ftfffffffffff");
		long ticketId = 0;
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{	log.error("You have session timed out");
				return mapping.findForward("login");
			}
		
		if(request.getParameter("ticketId") != null)
		ticketId = Long.parseLong(request.getParameter("ticketId").trim());
		MyTicketForm myTicketForm = new MyTicketForm();
		myTicketForm.setTicketId(ticketId);
		System.out.println("In getTicket\t"+ticketId);
		Set<ComplaintBookBean> complaintBookBeanList = null;
		
		try {

			complaintBookBeanList = complaintBookBO.findAssignedComplaint(myTicketForm,misAuditBean.getEnteredBy());
//			System.out.println(complaintBookBeanList);
				if(!MisUtility.ifEmpty(complaintBookBeanList)){
					//This method is called to fetch the ticket history
					try {
						getAssignedHistory(request,ticketId);
					} catch (MISException e) {
						
						e.printStackTrace();
					}
					//*********************************************************
					for (ComplaintBookBean complaintBookBean : complaintBookBeanList) {
						request.setAttribute("complaintBookBean", complaintBookBean);
					}
				}
					
			
		} catch (MISException e) {
			log.error(e);
			e.printStackTrace();

		}
		
		return mapping.findForward("ticketResolution");
	}
	
	private void getAssignedHistory(HttpServletRequest request,long ticketId) throws MISException {
		Set<ComplaintHistoryBean> ticketHistoryList = null;
		try {

			ticketHistoryList = complaintBookBO.getComplaintHistory(ticketId);

		} catch (MISException e) {
			log.error(e);
			e.printStackTrace();

		}

		request.setAttribute("ticketHistoryList", ticketHistoryList);
		
		
	}

	
	private void getMyTickets(HttpServletRequest request, MyTicketForm myTicketForm) throws MISSessionTimeOutException {
		Set<ComplaintBookBean> complaintBookBeanList = null;
		Set<ComplaintBookBean> complaintBookBeanListPublic = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				throw new MISSessionTimeOutException(MISExceptionCodes.MIS002, "Session Timed Out");
			}
			myTicketForm.setComplaintBookType("INTERNAL");
			complaintBookBeanList = complaintBookBO.findAssignedComplaint(myTicketForm, misAuditBean.getEnteredBy());
			myTicketForm.setComplaintBookType("PUBLIC");
			complaintBookBeanListPublic = complaintBookBO.findAssignedComplaint(myTicketForm, misAuditBean.getEnteredBy());
		} catch (MISException e) {
			log.debug(e);
		}
		request.setAttribute("complaintBookBeanList", complaintBookBeanList);
		request.setAttribute("complaintBookBeanListPublic", complaintBookBeanListPublic);
	}
	
	private void refreshMyTicketForm(MyTicketForm myTicketForm)

	{
		myTicketForm.setFromDate(null);
		myTicketForm.setToDate(null);
		myTicketForm.setTicketId(0);
		myTicketForm.setStatus("Open");

	}

}
