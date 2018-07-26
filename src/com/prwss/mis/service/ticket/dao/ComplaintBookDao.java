package com.prwss.mis.service.ticket.dao;

import java.sql.Timestamp;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface ComplaintBookDao {

	public Set<ComplaintBookBean> findComplaint(ComplaintBookBean complaintBookBean, Timestamp fromDate, Timestamp toDate) throws DataAccessException;
	
	public ComplaintBookBean findComplaint(Long ticketId) throws DataAccessException;
	
	public long saveComplaint(ComplaintBookBean complaintBookBean) throws DataAccessException;
	
	public long saveComplaintHistory(ComplaintHistoryBean complaintHistoryBean) throws DataAccessException;
	
	public boolean updateComplaintBook(ComplaintBookBean complaintBookBean) throws DataAccessException;
	
	public Set<ComplaintHistoryBean> getComplaintHistory(long ticketId) throws DataAccessException;
	
}
