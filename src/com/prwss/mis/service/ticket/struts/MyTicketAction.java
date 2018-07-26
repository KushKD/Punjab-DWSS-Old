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
import com.prwss.mis.service.ticket.ComplaintBookBO;
import com.prwss.mis.service.ticket.dao.ComplaintBookBean;
import com.prwss.mis.service.ticket.dao.ComplaintHistoryBean;

public class MyTicketAction extends DispatchAction {

	private ComplaintBookBO complaintBookBO;
	private MISSessionBean misAuditBean = null;

	public void setComplaintBookBO(ComplaintBookBO complaintBookBO) {
		this.complaintBookBO = complaintBookBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		MyTicketForm myTicketForm = (MyTicketForm) form;
		System.out.println(myTicketForm.getFromDate() + "\t"
				+ myTicketForm.getToDate());
		try {
			getMyTickets(request, myTicketForm);
		} catch (MISSessionTimeOutException e) {
			log.error("User session timedout" + e);
			return mapping.findForward("login");
		}
		return mapping.findForward("display");
	}

	public ActionForward getHistory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		// request.setAttribute("op", "ffffffffffff");
		long ticketId = Long.parseLong(request.getParameter("ticketId").trim());
		Set<ComplaintHistoryBean> ticketHistoryList = null;
		try {

			ticketHistoryList = complaintBookBO.getComplaintHistory(ticketId);

		} catch (MISException e) {
			throw e;

		}

		request.setAttribute("ticketHistoryList", ticketHistoryList);
		System.out.println("In Ticket History");
		return mapping.findForward("ticketHistory");
	}

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		MyTicketForm myTicketForm = new MyTicketForm();
		try {
			getMyTickets(request, myTicketForm);
		} catch (MISSessionTimeOutException e) {
			log.error("User session timedout" + e);
			return mapping.findForward("login");
		}
		System.out.println("Unspecified.......MyTicket");
		refreshMyTicketForm(myTicketForm);
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("op", "tfffffffffff");
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "ticketId@status@fromDate@toDate");
	}

	private void refreshMyTicketForm(MyTicketForm myTicketForm)

	{
		myTicketForm.setFromDate(null);
		myTicketForm.setToDate(null);
		myTicketForm.setTicketId(0);
		myTicketForm.setStatus("Open");

	}

	private void getMyTickets(HttpServletRequest request,
			MyTicketForm myTicketForm) throws MISSessionTimeOutException {
		Set<ComplaintBookBean> complaintBookBeanList = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				misAuditBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");

			} else {
				throw new MISSessionTimeOutException(MISExceptionCodes.MIS002,
						"Session Timed Out");
			}
			complaintBookBeanList = complaintBookBO.findMyComplaint(
					myTicketForm, misAuditBean);
		} catch (MISException e) {
			log.debug(e);
		}
		request.setAttribute("complaintBookBeanList", complaintBookBeanList);

	}

//	@Override
//	protected Map<String, String> getKeyMethodMap() {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("MyTicketForm.find", "find");
//		map.put("MyTicketForm.getHistory", "getHistory");
//		return map;
//	}

}
