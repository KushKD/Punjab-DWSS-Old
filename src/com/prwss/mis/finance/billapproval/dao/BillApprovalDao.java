package com.prwss.mis.finance.billapproval.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface BillApprovalDao {

	public boolean saveBillApproval(BillApprovalBean billApprovalBean) throws DataAccessException;
	
	public List<BillApprovalBean> findBillApproval(BillApprovalBean billApprovalBean, List<String> statusList) throws DataAccessException;
}


