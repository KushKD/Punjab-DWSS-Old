package com.prwss.mis.service.ticket.struts;

import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.service.ticket.ComplaintBookBO;
import com.prwss.mis.service.ticket.dao.ComplaintBookBean;
import com.prwss.mis.service.ticket.dao.ComplaintBookDao;

public class TicketResolutionAction extends DispatchAction {

	private ComplaintBookBO complaintBookBO;
	private MISSessionBean misAuditBean = null;
	private ComplaintBookDao complaintBookDao;

	public void setComplaintBookBO(ComplaintBookBO complaintBookBO) {
		this.complaintBookBO = complaintBookBO;
	}

	public void setComplaintBookDao(ComplaintBookDao complaintBookDao) {
		this.complaintBookDao = complaintBookDao;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		boolean status = false;
		TicketResolutionForm ticketResolutionForm = (TicketResolutionForm) form;

		if (request.getSession().getAttribute("misSessionBean") != null) {
			misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			log.error("You have session timed out");
			return mapping.findForward("login");
		}
		try {
		//	System.out.println("-------------------------------------------------------------------");
		//	System.out.println(ticketResolutionForm.getDescription());
		//	System.out.println(ticketResolutionForm.getLocationName());
		//	System.out.println(ticketResolutionForm.getTicketId());
		//	System.out.println(ticketResolutionForm.getComments());
		//	System.out.println(ticketResolutionForm.getStatus());
		//	System.out.println("In save mentod"+ticketResolutionForm.isForward());
		//	System.out.println("-------------------------------------------------------------------");
		//	System.out.println(misAuditBean);
			status = complaintBookBO.updateComplaintResolution(ticketResolutionForm, misAuditBean);
			request.setAttribute("d__mode", "");
			request.getSession().setAttribute("misSessionBean",misAuditBean);
			//System.out.println(status);
			if(ticketResolutionForm.getStatus().equalsIgnoreCase("Close")){
				String subject=null;
				StringBuffer emailBody=new StringBuffer();
				
				ComplaintBookBean complaintBookBean = new ComplaintBookBean();
				complaintBookBean = complaintBookDao.findComplaint(ticketResolutionForm.getTicketId());
				
				 if(MisUtility.ifEmpty(complaintBookBean.getPublicEmail())){
					if(complaintBookBean.getPublicEmail().contains("@")){
						JavaMailSenderImpl sender = new JavaMailSenderImpl();
						final String SMTP_HOST_NAME = "smtp.gmail.com";
						final String SMTP_PORT = "465";
						final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
						
						Properties javaMailProperties = System.getProperties();
						javaMailProperties.put("mail.smtp.host", SMTP_HOST_NAME);
						javaMailProperties.put("mail.smtp.auth", "true");
						javaMailProperties.put("mail.debug", "true");
						javaMailProperties.put("mail.smtp.port", SMTP_PORT);
						javaMailProperties.put("mail.smtp.socketFactory.port", SMTP_PORT);
						javaMailProperties.put("mail.smtp.socketFactory.class", SSL_FACTORY);
						javaMailProperties.put("mail.smtp.socketFactory.fallback", "false");
						sender.setJavaMailProperties(javaMailProperties);
						sender.setUsername("dwsshelpdesk@gmail.com");
						sender.setPassword("spmcmohali");
						MimeMessage message = sender.createMimeMessage();
						MimeMessageHelper helper = null;
						helper = new MimeMessageHelper(message, true);
						//helper.setTo("nikhilgupta.sre@gmail.com");
						helper.setTo(complaintBookBean.getPublicEmail());
						
						subject="DWSS Querry Redressal:Complaint/Feedback No. "+ticketResolutionForm.getTicketId();
						helper.setSubject(subject);
						emailBody.append("Dear Sir/Madam, \n\n Thanks for your "+ complaintBookBean.getPublicFeedbackType()+" registerd in query redressel system having "+complaintBookBean.getPublicFeedbackType()+
								" number "+ticketResolutionForm.getTicketId()+",. \n  your  " +complaintBookBean.getPublicFeedbackType()+ " has been closed with the following remarks..... "+" \n " +ticketResolutionForm.getComments()+
								"\n\n\n\nRegards \n Help Desk\n Department of Water Supply and Sanitation, Punjab");
						//System.out.println("Complainer emailBody: "+emailBody.toString());
						helper.setText(emailBody.toString());	
						sender.send(message);
					}	
				 }
				
				
			}
		
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {
				throw e;
			}

		}

		refreshTicketResolutionForm(ticketResolutionForm);
		return mapping.findForward("ticketSearch");
	}


	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		this.setAttrib(request);
		System.out.println("ticket resolution un specified is called");
		//		TicketResolutionForm ticketResolutionForm = (TicketResolutionForm)form;
		//		System.out.println("unspecified ticket Resolution \t"+ticketResolutionForm.isForward());
		////		System.out.println(ticketResolutionFo);
		//		System.out.println("Unspecified.......Ticket Resolution");
		//		return mapping.findForward("display");
		return null;
	}
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		//request.setAttribute("op", "ftfffffffffff");
		System.out.println("Ticket Resolution :----"+request.getParameter("menuId"));
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky","ticketId@status@fromDate@toDate");
	}
	
	private void refreshTicketResolutionForm(TicketResolutionForm ticketResolutionForm)
	{
		ticketResolutionForm.setComments(null);
		ticketResolutionForm.setForward(false);
		ticketResolutionForm.setStatus(null);
		System.out.println("In refersh Form of ticket Resolution Action");
	}
	

}