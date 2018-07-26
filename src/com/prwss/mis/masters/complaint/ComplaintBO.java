package com.prwss.mis.masters.complaint;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.complaint.dao.ComplaintBean;
import com.prwss.mis.masters.complaint.struts.ComplaintForm;

public interface ComplaintBO {
	
	public List<ComplaintBean> findComplaint(ComplaintForm complaintForm, List<String> statusList) throws MISException;
	
	public boolean saveComplaint(ComplaintForm complaintForm, MISSessionBean misAuditBean) throws MISException;
	
	/*public boolean updateComplaint(ComplaintForm complaintForm, MISSessionBean misAuditBean) throws MISException;
	*/
	public boolean deleteComplaint(ComplaintForm complaintForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postComplaint(ComplaintForm complaintForm, MISSessionBean misAuditBean) throws MISException;

	boolean updateComplaint(ComplaintForm complaintForm,
			MISSessionBean misAuditBean, List<String> statusList)
			throws MISException;

}
