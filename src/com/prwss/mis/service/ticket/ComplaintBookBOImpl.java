package com.prwss.mis.service.ticket;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.complaint.dao.ComplaintBean;
import com.prwss.mis.masters.complaint.dao.ComplaintDao;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.service.ticket.dao.ComplaintBookBean;
import com.prwss.mis.service.ticket.dao.ComplaintBookDao;
import com.prwss.mis.service.ticket.dao.ComplaintHistoryBean;
import com.prwss.mis.service.ticket.struts.MyTicketForm;
import com.prwss.mis.service.ticket.struts.NewTicketForm;
import com.prwss.mis.service.ticket.struts.TicketResolutionForm;

public class ComplaintBookBOImpl implements ComplaintBookBO {
	
	private Logger log = Logger.getLogger(ComplaintBookBOImpl.class);
	private ComplaintBookDao complaintBookDao;
	private ComplaintDao complaintDao;

	public void setComplaintBookDao(ComplaintBookDao complaintBookDao) {
		this.complaintBookDao = complaintBookDao;
	}
	
	public void setComplaintDao(ComplaintDao complaintDao) {
		this.complaintDao = complaintDao;
	}

	@Override
	public Set<ComplaintBookBean> findMyComplaint(MyTicketForm myTicketForm, MISSessionBean misAuditBean)
			throws MISException {
		Set<ComplaintBookBean> complaintBookBeans = null;
		try {
			ComplaintBookBean complaintBookBean = new ComplaintBookBean();
			EmployeeBean ownedEmployeeBean = new EmployeeBean();
			ownedEmployeeBean.setEmployeeId(misAuditBean.getEnteredBy());
			complaintBookBean.setOwnedEmployeeBean(ownedEmployeeBean);
			complaintBookBean.setTicketId(myTicketForm.getTicketId());
			complaintBookBean.setStatus(myTicketForm.getStatus());
			Timestamp fromDate = null;
			Timestamp toDate = null;
			// Converting SQL Date to Timestamp
			if(MisUtility.ifEmpty(myTicketForm.getFromDate()))
			fromDate = new Timestamp(MisUtility.convertStringToDate(myTicketForm.getFromDate()).getTime());
			// Converting SQL Date to Timestamp and adding a one day to allow inclusive date in search criteria
			if(MisUtility.ifEmpty(myTicketForm.getToDate())){
				Calendar cal = GregorianCalendar.getInstance();
				cal.setTime(MisUtility.convertStringToDate(myTicketForm.getToDate()));
				cal.add(Calendar.DATE, 1);
				toDate = new Timestamp(cal.getTime().getTime());
			}
			
			complaintBookBeans = complaintBookDao.findComplaint(complaintBookBean, fromDate, toDate);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} 
		
		return complaintBookBeans;
	}
	
	public Set<ComplaintBookBean> findAssignedComplaint(MyTicketForm myTicketForm, long pendingWithEmployeeId)
	throws MISException {
		Set<ComplaintBookBean> complaintBookBeans = null;
		try {
			System.out.println("Pending EMP"+pendingWithEmployeeId);
			ComplaintBookBean complaintBookBean = new ComplaintBookBean();
			EmployeeBean assignedEmployeeBean = new EmployeeBean();
			assignedEmployeeBean.setEmployeeId(pendingWithEmployeeId);
			complaintBookBean.setAssignedEmployeeBean(assignedEmployeeBean);
			complaintBookBean.setTicketId(myTicketForm.getTicketId());
			complaintBookBean.setStatus(myTicketForm.getStatus());
			complaintBookBean.setComplaintBookType(myTicketForm.getComplaintBookType());
			Timestamp fromDate = null;
			Timestamp toDate = null;
			// Converting SQL Date to Timestamp
			if(MisUtility.ifEmpty(myTicketForm.getFromDate()))
			fromDate = new Timestamp(MisUtility.convertStringToDate(myTicketForm.getFromDate()).getTime());
			// Converting SQL Date to Timestamp and adding a one day to allow inclusive date in search criteria
			if(MisUtility.ifEmpty(myTicketForm.getToDate())){
				Calendar cal = GregorianCalendar.getInstance();
				cal.setTime(MisUtility.convertStringToDate(myTicketForm.getToDate()));
				cal.add(Calendar.DATE, 1);
				toDate = new Timestamp(cal.getTime().getTime());
			}
			
			complaintBookBeans = complaintBookDao.findComplaint(complaintBookBean, fromDate, toDate);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} 

		return complaintBookBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveComplaint(NewTicketForm newTicketForm, MISSessionBean misAuditBean) throws MISException {
		long ticketId = 0;
		try {
			ComplaintBookBean complaintBookBean = new ComplaintBookBean();
			ComplaintBean complaintBean = new ComplaintBean();
			complaintBean.setComplaintId(newTicketForm.getComplaintId());
			List<ComplaintBean> complaintBeans = complaintDao.findComplaint(complaintBean, null);
			
			if(!MisUtility.ifEmpty(complaintBeans)){
				complaintBean = complaintBeans.get(0);
				System.out.println("ComplaintBean:"+complaintBean);
			}
			complaintBookBean.setComplaintBean(complaintBean);
			complaintBookBean.setSubject(newTicketForm.getSubject());
			complaintBookBean.setStatus(MISConstants.TICKET_STATUS_OPEN);
			complaintBookBean.setPriority(newTicketForm.getPriorityLevel());
			
			EmployeeBean assignedEmployeeBean = new EmployeeBean();
			assignedEmployeeBean.setEmployeeId(complaintBean.getLevel1EmployeeId());
			complaintBookBean.setAssignedEmployeeBean(assignedEmployeeBean);
			
			complaintBookBean.setLocationId(newTicketForm.getLocationId());
			complaintBookBean.setDescription(newTicketForm.getDescription());
			EmployeeBean ownedEmployeeBean = new EmployeeBean();
			ownedEmployeeBean.setEmployeeId(misAuditBean.getEnteredBy());
			complaintBookBean.setOwnedEmployeeBean(ownedEmployeeBean);
			complaintBookBean.setEntDate(misAuditBean.getEnteredDate());
			complaintBookBean.setComplaintBookType("INTERNAL");
			ticketId = complaintBookDao.saveComplaint(complaintBookBean);
			System.out.println("Complaint Book BO TicketId:"+ticketId);
			ComplaintHistoryBean complaintHistoryBean = new ComplaintHistoryBean();
			complaintHistoryBean.setTicketId(ticketId);
			complaintHistoryBean.setComments(newTicketForm.getDescription());
			
			EmployeeBean assignedEmployeeBeanInHistory = new EmployeeBean();
			assignedEmployeeBeanInHistory.setEmployeeId(complaintBean.getLevel1EmployeeId());
			complaintHistoryBean.setAssignedEmployeeBean(assignedEmployeeBeanInHistory);
			
			complaintHistoryBean.setEntDate(misAuditBean.getEnteredDate());
			complaintHistoryBean.setStatus(MISConstants.TICKET_STATUS_OPEN);
			complaintHistoryBean.setEntBy(misAuditBean.getEnteredBy());
			complaintBookDao.saveComplaintHistory(complaintHistoryBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} 
		
		return ticketId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateComplaintResolution(TicketResolutionForm ticketResolutionForm, MISSessionBean misAuditBean)
			throws MISException {
		boolean complaintBookStatus = false;
		long assignedToEmployeeId = 0;
		try {
			long pendingWith=0;
			//getting the present state of the bean
			ComplaintBookBean complaintBookBean = null;
			complaintBookBean = complaintBookDao.findComplaint(ticketResolutionForm.getTicketId());
			assignedToEmployeeId = misAuditBean.getEnteredBy();
			if(ticketResolutionForm.isForward()){
				ComplaintBean complaintBean = complaintBookBean.getComplaintBean();
				if(assignedToEmployeeId == complaintBean.getLevel1EmployeeId()){
					pendingWith = complaintBean.getLevel2EmployeeId();
				} else if(assignedToEmployeeId == complaintBean.getLevel2EmployeeId()) {
					pendingWith = complaintBean.getLevel3EmployeeId();
				} else if(assignedToEmployeeId == complaintBean.getLevel3EmployeeId()) {
					pendingWith = complaintBean.getLevel4EmployeeId();
				} else if(assignedToEmployeeId == complaintBean.getLevel4EmployeeId()) {
					pendingWith = complaintBean.getLevel5EmployeeId();
				}
				assignedToEmployeeId = pendingWith;
				EmployeeBean assignedEmployeeBean = new EmployeeBean();
				assignedEmployeeBean.setEmployeeId(assignedToEmployeeId);
				complaintBookBean.setAssignedEmployeeBean(assignedEmployeeBean);
			} else{
				EmployeeBean assignedEmployeeBean = new EmployeeBean();
				assignedEmployeeBean.setEmployeeId(assignedToEmployeeId);
				complaintBookBean.setAssignedEmployeeBean(assignedEmployeeBean);
				complaintBookBean.setStatus(ticketResolutionForm.getStatus());
			}
			complaintBookStatus = complaintBookDao.updateComplaintBook(complaintBookBean);
			if(complaintBookStatus){
				ComplaintHistoryBean complaintHistoryBean = new ComplaintHistoryBean();
				complaintHistoryBean.setComments(ticketResolutionForm.getComments());
				
				EmployeeBean assignedEmployeeBean = new EmployeeBean();
				assignedEmployeeBean.setEmployeeId(assignedToEmployeeId);
				complaintHistoryBean.setAssignedEmployeeBean(assignedEmployeeBean);
				
				complaintHistoryBean.setStatus(ticketResolutionForm.getStatus());
				complaintHistoryBean.setEntBy(misAuditBean.getEnteredBy());
				complaintHistoryBean.setEntDate(misAuditBean.getEnteredDate());
				complaintHistoryBean.setTicketId(ticketResolutionForm.getTicketId());
				complaintBookDao.saveComplaintHistory(complaintHistoryBean);
			}
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return true;
	}

	@Override
	public Set<ComplaintHistoryBean> getComplaintHistory(long ticketId) throws MISException {
		Set<ComplaintHistoryBean> complaintHistoryBeans = null;
		try {
			complaintHistoryBeans = complaintBookDao.getComplaintHistory(ticketId);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return complaintHistoryBeans;
	}

}
