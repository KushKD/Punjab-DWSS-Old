package com.prwss.mis.hr.leave.balance.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface LeaveBalanceDao {
    
	public List<LeaveBalanceBean> findLeaveBalance(LeaveBalanceBean leaveBalanceBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveOrUpdateLeaveBalance(LeaveBalanceBean leaveBalanceBean) throws DataAccessException;

	public boolean saveEntitledLeave(LeaveBalanceBean leaveBalanceBean)throws DataAccessException;


}
