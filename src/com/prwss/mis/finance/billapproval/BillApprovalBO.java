package com.prwss.mis.finance.billapproval;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.finance.billapproval.dao.BillApprovalBean;
import com.prwss.mis.finance.billapproval.struts.BillApprovalForm;

public interface BillApprovalBO {
	public List<BillApprovalBean> findBillApproval(BillApprovalForm billApprovalForm,List<String> statusList) throws MISException;
	public long saveBillApproval(BillApprovalForm billApprovalForm, MISSessionBean misSessionBean) throws MISException;

}