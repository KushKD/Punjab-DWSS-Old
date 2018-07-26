package com.prwss.mis.service.ticket;

import java.util.Set;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.service.ticket.dao.ComplaintBookBean;
import com.prwss.mis.service.ticket.dao.ComplaintHistoryBean;
import com.prwss.mis.service.ticket.struts.MyTicketForm;
import com.prwss.mis.service.ticket.struts.NewTicketForm;
import com.prwss.mis.service.ticket.struts.TicketResolutionForm;

public interface ComplaintBookBO {
	
	public Set<ComplaintBookBean> findMyComplaint(MyTicketForm myTicketForm, MISSessionBean misAuditBean) throws MISException;
	
	public Set<ComplaintBookBean> findAssignedComplaint(MyTicketForm myTicketForm, long pendingWithEmployeeId) throws MISException;
	
	public long saveComplaint(NewTicketForm newTicketForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean updateComplaintResolution(TicketResolutionForm ticketResolutionForm, MISSessionBean misAuditBean) throws MISException;
	
	public Set<ComplaintHistoryBean> getComplaintHistory(long ticketId) throws MISException;
	
}
