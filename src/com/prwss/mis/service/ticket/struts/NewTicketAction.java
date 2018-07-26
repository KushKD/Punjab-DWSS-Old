package com.prwss.mis.service.ticket.struts;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.complaint.dao.ComplaintBean;
import com.prwss.mis.masters.complaint.dao.ComplaintDao;
import com.prwss.mis.service.ticket.ComplaintBookBO;

public class NewTicketAction extends DispatchAction{
	private Logger log = Logger.getLogger(NewTicketAction.class);
	private ComplaintBookBO complaintBookBO;
	private ComplaintDao complaintDao;
	private MISSessionBean misSessionBean;
	
	public void setComplaintDao(ComplaintDao complaintDao) {
		this.complaintDao = complaintDao;
	}
	
	
	public void setComplaintBookBO(ComplaintBookBO complaintBookBO) {
		this.complaintBookBO = complaintBookBO;
	}

	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		
		NewTicketForm newTicketForm = (NewTicketForm)form;
		long ticketId = 0;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			ticketId = complaintBookBO.saveComplaint(newTicketForm, misSessionBean);
			if (MisUtility.ifEmpty(ticketId)){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("ticket.save", "Ticket",ticketId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("fatal.error.save", "Ticket Raising");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			}
		} catch (MISException e) {
				log.error(e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Ticket Raising");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save", "Ticket Raising");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		
		refeshNewTicketForm(newTicketForm);
		
		return mapping.findForward("display");
	}
	
	private Set<LabelValueBean> getComplaintIds() {
	
		Set<LabelValueBean> complaint = null;
		Set<ComplaintBean> complaintIds = null;
		try {
			complaintIds = complaintDao.getDistinctComplaintType();
			complaint = new HashSet<LabelValueBean>();
			for (ComplaintBean complaintId : complaintIds) {
				complaint.add(new LabelValueBean(complaintId.getComplaintType(),""+complaintId.getComplaintId()));
			}
		} catch (Exception e) {
			log.error(e);
		}
		return complaint;

	}
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		System.out.println("Unspecified.......NewTicket");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			System.out.println("in if");	
		}else{
			System.out.println("in else\t"+request.getSession().getAttribute("misSessionBean")); 
			log.error("Your Session Timed Out");
			return mapping.findForward("login");
		}
		
		NewTicketForm newTicketForm = (NewTicketForm)form;
		refeshNewTicketForm(newTicketForm);
		Set<LabelValueBean> complaints = null;
		complaints = getComplaintIds();
		request.getSession().setAttribute("complaintIds", complaints);
		return mapping.findForward("display");
	}
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("op", "ftfffffffffff");
		request.setAttribute("d__mode", request.getParameter("d__mode"));

	}
	
	private void refeshNewTicketForm(NewTicketForm newTicketForm)
	{
		newTicketForm.setDescription(null);
		newTicketForm.setSubject(null);
		
	}
	
}