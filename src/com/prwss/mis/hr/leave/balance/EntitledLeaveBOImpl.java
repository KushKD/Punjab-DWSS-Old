package com.prwss.mis.hr.leave.balance;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.admin.struts.EntitledLeaveForm;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.hr.leave.balance.dao.LeaveBalanceBean;
import com.prwss.mis.hr.leave.balance.dao.LeaveBalanceDao;
import com.prwss.mis.hr.salarystructure.HRSalaryStructureBOImpl;

public class EntitledLeaveBOImpl implements EntitledLeaveBO{
	private LeaveBalanceDao leaveBalanceDao;
	private EntitledLeaveBO entitledLeaveBO;
	private Logger log = Logger.getLogger(EntitledLeaveBOImpl.class);

	public void setEntitledLeaveBO(EntitledLeaveBO entitledLeaveBO) {
		this.entitledLeaveBO = entitledLeaveBO;
	}
	public void setLeaveBalanceDao(LeaveBalanceDao leaveBalanceDao) {
		this.leaveBalanceDao = leaveBalanceDao;
	}
	@Override
	public boolean saveEntitledLeaveForm(EntitledLeaveForm entitledLeaveForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try{
		LeaveBalanceBean leaveBalanceBean = new LeaveBalanceBean();
		leaveBalanceBean.setLocationId(entitledLeaveForm.getLocationId());
		leaveBalanceBean.setEmployeeId(entitledLeaveForm.getEmployeeId());
		leaveBalanceBean.setYearFor(entitledLeaveForm.getYearFor());
		leaveBalanceBean.setTotalEligibileLeave(entitledLeaveForm.getTotalEligibileLeave());
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());	
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
		leaveBalanceBean.setMisAuditBean(misAuditBean);
		status = leaveBalanceDao.saveOrUpdateLeaveBalance(leaveBalanceBean);
		}catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		}  
		return status;
	}
	@Override
	public List<LeaveBalanceBean> findEntitledLeaveForm(
			EntitledLeaveForm entitledLeaveForm, List<String> statusList) throws MISException {
		List<LeaveBalanceBean> leaveBalanceBeans = null;
		try{
		LeaveBalanceBean leaveBalanceBean = new LeaveBalanceBean();
		leaveBalanceBean.setLocationId(entitledLeaveForm.getLocationId());
		leaveBalanceBean.setEmployeeId(entitledLeaveForm.getEmployeeId());
		leaveBalanceBean.setYearFor(entitledLeaveForm.getYearFor());
		leaveBalanceBeans = leaveBalanceDao.findLeaveBalance(leaveBalanceBean, statusList);		 
		}catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} 
		 System.out.println(leaveBalanceBeans);
		return leaveBalanceBeans;
	}
	@Override
	public boolean updateEntitledLeaveForm(EntitledLeaveForm entitledLeaveForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		 try{
		LeaveBalanceBean leaveBalanceBean = new LeaveBalanceBean();
		leaveBalanceBean.setLocationId(entitledLeaveForm.getLocationId());
		leaveBalanceBean.setEmployeeId(entitledLeaveForm.getEmployeeId());
		leaveBalanceBean.setYearFor(entitledLeaveForm.getYearFor());
		leaveBalanceBean.setTotalEligibileLeave(entitledLeaveForm.getTotalEligibileLeave());
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());	
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
		leaveBalanceBean.setMisAuditBean(misAuditBean);
		status = leaveBalanceDao.saveOrUpdateLeaveBalance(leaveBalanceBean);
		}catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		}  
		return status;
	}

}
