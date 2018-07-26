package com.prwss.mis.hr.leave;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.hr.leave.struts.LeaveApprovalForm;
import com.prwss.mis.hr.leave.struts.LeaveForm;

public interface LeaveManagementBO {
	public List<LeaveManagementBean> findLeaveManagementForm(LeaveForm leaveForm, List<String> statusList,MISSessionBean misSessionBean) throws MISException;
	public boolean updateLeaveManagementForm(LeaveForm leaveForm, MISSessionBean misSessionBean) throws MISException;
	public long saveLeaveManagementForm(LeaveForm leaveForm, MISSessionBean misSessionBean) throws MISException;
	public boolean deleteLeaveManagementForm(LeaveForm leaveForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean saveLeaveApprovalForm(LeaveApprovalForm leaveApprovalForm, MISSessionBean misSessionBean) throws MISException;
	public List<LeaveManagementBean> findLeaveApprovalForm(LeaveApprovalForm leaveApprovalForm, List<String> statusList,MISSessionBean misSessionBean) throws MISException;
}
