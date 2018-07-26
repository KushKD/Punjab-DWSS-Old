package com.prwss.mis.hr.leave.balance;

import java.util.List;

import com.prwss.mis.admin.struts.EntitledLeaveForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.hr.leave.balance.dao.LeaveBalanceBean;


public interface EntitledLeaveBO {
	public boolean saveEntitledLeaveForm(EntitledLeaveForm entitledLeaveForm, MISSessionBean misSessionBean) throws MISException;
	public  List<LeaveBalanceBean> findEntitledLeaveForm(EntitledLeaveForm entitledLeaveForm, List<String> statusList) throws MISException;
	public boolean updateEntitledLeaveForm(EntitledLeaveForm entitledLeaveForm, MISSessionBean misSessionBean) throws MISException;
}
