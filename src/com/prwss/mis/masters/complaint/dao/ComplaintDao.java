package com.prwss.mis.masters.complaint.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface ComplaintDao {

	public List<ComplaintBean> findComplaint(ComplaintBean complaintBean, List<String> statusList) throws DataAccessException;
	
	public List<ComplaintBean> findComplaint(List<Long> complaintIds) throws DataAccessException;

	public boolean saveComplaint(ComplaintBean complaintBean) throws DataAccessException;

	public boolean updateComplaint(ComplaintBean complaintBean)	throws DataAccessException;
	
	public boolean updateComplaint(List<ComplaintBean> complaintBeans) throws DataAccessException;

	public Set<ComplaintBean> getDistinctComplaintType() throws DataAccessException;
	
}
