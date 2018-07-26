package com.prwss.mis.hr.leave;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.hr.leave.balance.dao.LeaveBalanceBean;
import com.prwss.mis.hr.leave.balance.dao.LeaveBalanceDao;
import com.prwss.mis.hr.leave.dao.LeaveManagementDao;
import com.prwss.mis.hr.leave.struts.LeaveApprovalForm;
import com.prwss.mis.hr.leave.struts.LeaveForm;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;

public class LeaveManagementBOImpl implements LeaveManagementBO {
	private Logger log = Logger.getLogger(LeaveManagementBOImpl.class);
	private EmployeeDao employeeDao;
	private LeaveManagementDao leaveManagementDao;
	private LeaveBalanceDao leaveBalanceDao;
	
	public void setLeaveBalanceDao(LeaveBalanceDao leaveBalanceDao) {
		this.leaveBalanceDao = leaveBalanceDao;
	}

	public void setLeaveManagementDao(LeaveManagementDao leaveManagementDao) {
		this.leaveManagementDao = leaveManagementDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public List<LeaveManagementBean> findLeaveManagementForm(
			LeaveForm leaveForm, List<String> statusList,MISSessionBean misSessionBean) throws MISException {
		List<LeaveManagementBean> leaveManagementBeans = null;
		try {
			LeaveManagementBean leaveManagementBean = new LeaveManagementBean();
//			if(MisUtility.ifEmpty(leaveForm.getLeaveId())){
			leaveManagementBean.setLeaveId(leaveForm.getLeaveId());
			leaveManagementBean.setLeaveFromDate(MisUtility.convertStringToDate(leaveForm.getLeaveFromDate()));
			leaveManagementBean.setLeaveToDate(MisUtility.convertStringToDate(leaveForm.getLeaveToDate()));
			EmployeeBean bean = new EmployeeBean();
			bean.setEmployeeId(misSessionBean.getEnteredBy());
			leaveManagementBean.setEntByEmployeeBean(bean);
			leaveManagementBeans = leaveManagementDao.findLeave(leaveManagementBean, statusList);
//			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return leaveManagementBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateLeaveManagementForm(LeaveForm leaveForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			LeaveManagementBean leaveManagementBean = new LeaveManagementBean();
			leaveManagementBean.setLeaveId(leaveForm.getLeaveId());
			leaveManagementBean = leaveManagementDao.findLeave(leaveManagementBean, statusList).get(0);
			if(!leaveManagementBean.getLeaveStatus().equals(MISConstants.HR_LEAVE_NO_ACTION)){
				throw new MISException(MISExceptionCodes.MIS006, ", As Action Has Already Been Taken On This Leave By Your Reporting Officer");
			}
			LeaveManagementBean leaveManagementBean2 = popuplateLeaveManagementBean(leaveForm, misSessionBean);
			leaveManagementBean2.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			status = leaveManagementDao.updateLeave(leaveManagementBean2);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveLeaveManagementForm(LeaveForm leaveForm,
			MISSessionBean misSessionBean) throws MISException {
		long leaveId = 0;
		try {
			LeaveManagementBean leaveManagementBean = popuplateLeaveManagementBean(leaveForm, misSessionBean);
			leaveManagementBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			leaveId = leaveManagementDao.saveLeave(leaveManagementBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return leaveId;
	}
	
	private LeaveManagementBean popuplateLeaveManagementBean(LeaveForm leaveForm,MISSessionBean misSessionBean){
		LeaveManagementBean leaveManagementBean = new LeaveManagementBean();
		leaveManagementBean.setLeaveId(leaveForm.getLeaveId());
		leaveManagementBean.setLeaveReason(leaveForm.getLeaveReason());
		EmployeeBean bean = new EmployeeBean();
		
		bean.setEmployeeId(misSessionBean.getEnteredBy());
		leaveManagementBean.setEntByEmployeeBean(bean);
		
		System.out.println("ENTERED-----------"+leaveManagementBean.getEntByEmployeeBean().getEmployeeId());
		
		EmployeeBean bean2 = new EmployeeBean();
		bean2.setEmployeeId(getReportingOfficerId(misSessionBean));
		
		leaveManagementBean.setReportingOfficerEmployeeBean(bean2);
		leaveManagementBean.setLeaveFromDate(MisUtility.convertStringToDate(leaveForm.getLeaveFromDate()));
		leaveManagementBean.setLeaveToDate(MisUtility.convertStringToDate(leaveForm.getLeaveToDate()));
		leaveManagementBean.setEntDate(misSessionBean.getEnteredDate());
		leaveManagementBean.setLeaveStatus(MISConstants.HR_LEAVE_NO_ACTION);
		leaveManagementBean.setLocationId(leaveForm.getLocationId());
		return leaveManagementBean;
	}
	
	private long getReportingOfficerId(MISSessionBean misSessionBean){
		List<String> statusList = new ArrayList<String>();
		long id = 0;
		try {
			EmployeeBean bean = new EmployeeBean();
			bean.setEmployeeId(misSessionBean.getEnteredBy());
			bean = employeeDao.findEmployee(bean, statusList).get(0);
			id = bean.getReportingOfficerId();
		System.out.println("REPROTING OFFICER ID ---"+id);
		
		} catch (DataAccessException e){
			throw e;
		}
		return id;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteLeaveManagementForm(LeaveForm leaveForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			LeaveManagementBean leaveManagementBean = new LeaveManagementBean();
			leaveManagementBean.setLeaveId(leaveForm.getLeaveId());
			leaveManagementBean = leaveManagementDao.findLeave(leaveManagementBean, statusList).get(0);
			if(!leaveManagementBean.getLeaveStatus().equals(MISConstants.HR_LEAVE_NO_ACTION)){
				throw new MISException(MISExceptionCodes.MIS006, ", As Action Has Already Been Taken On This Leave By Your Reporting Officer");
			}
			leaveManagementBean = popuplateLeaveManagementBean(leaveForm, misSessionBean);
			leaveManagementBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			status = leaveManagementDao.updateLeave(leaveManagementBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}

	@Override
	public List<LeaveManagementBean> findLeaveApprovalForm(
			LeaveApprovalForm leaveApprovalForm, List<String> statusList,
			MISSessionBean misSessionBean) throws MISException {
		List<LeaveManagementBean> leaveManagementBeans = null;
		try {
			Date requestFromDate = MisUtility.convertStringToDate(leaveApprovalForm.getLeaveRequestFromDate());
			Date requestToDate = MisUtility.convertStringToDate(leaveApprovalForm.getLeaveRequestToDate());
			LeaveManagementBean leaveManagementBean = new LeaveManagementBean();
			leaveManagementBean.setLeaveId(leaveApprovalForm.getLeaveId());
			leaveManagementBean.setLeaveStatus(leaveApprovalForm.getLeaveStatus());
			EmployeeBean bean = new EmployeeBean();
			bean.setEmployeeId(misSessionBean.getEnteredBy());
			leaveManagementBean.setReportingOfficerEmployeeBean(bean);
			leaveManagementBeans = leaveManagementDao.findLeave(leaveManagementBean,requestFromDate,requestToDate, statusList);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return leaveManagementBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveLeaveApprovalForm(LeaveApprovalForm leaveApprovalForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		long leaveBalance=0;
		long appliedLeave = 0;
		long leaveAvailed = 0;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			LeaveManagementBean leaveManagementBean = new LeaveManagementBean();
			leaveManagementBean.setLeaveId(leaveApprovalForm.getLeaveId());
			leaveManagementBean = leaveManagementDao.findLeave(leaveManagementBean, statusList).get(0);
			if(!leaveManagementBean.getLeaveStatus().equals(MISConstants.HR_LEAVE_NO_ACTION)){
				throw new MISException(MISExceptionCodes.MIS004, ", As Action Has Already Been Taken By You On This Leave");
			}
			//This is done to get the year of the leave from date. So that we can update correct year record in Leave Balance Table
			Date leaveFromDate = leaveManagementBean.getLeaveFromDate();
			Locale aLocale = new Locale(Locale.ENGLISH.getLanguage(), Locale.UK.getCountry());
			Calendar cal = GregorianCalendar.getInstance(aLocale);
			cal.setTime(leaveFromDate);				
			int leaveFromYear = cal.get(Calendar.YEAR);
			//*********************************************************************************************************************
			
			if(leaveApprovalForm.getLeaveStatus().equals(MISConstants.HR_LEAVE_ACCEPTED)){
				String leaveFromDateString = MisUtility.convertDateToString(leaveFromDate);
				leaveBalance = getLeaveBalance(leaveManagementBean.getEntByEmployeeBean().getEmployeeId(),leaveFromDateString);
				appliedLeave = MisUtility.getNumberOfDays(leaveManagementBean.getLeaveFromDate(), leaveManagementBean.getLeaveToDate());
				if(leaveBalance>=appliedLeave){
					leaveManagementBean.setLeaveStatus(leaveApprovalForm.getLeaveStatus());
					leaveManagementBean.setOfficerComments(leaveApprovalForm.getOfficerComments());
					LeaveBalanceBean leaveBalanceBean = new LeaveBalanceBean();
					leaveBalanceBean.setEmployeeId(leaveManagementBean.getEntByEmployeeBean().getEmployeeId());
					leaveBalanceBean.setYearFor(leaveFromYear); // Setting Year to fetch Correct Year Record
					LeaveBalanceBean leaveBalanceBean2 = leaveBalanceDao.findLeaveBalance(leaveBalanceBean, null).get(0);
					leaveAvailed = leaveBalanceBean2.getTotalAvailed();
					leaveAvailed = leaveAvailed+appliedLeave;
					leaveBalanceBean2.setTotalAvailed(leaveAvailed);
					boolean statusOfLeaveBalance = leaveBalanceDao.saveOrUpdateLeaveBalance(leaveBalanceBean2);
					status = leaveManagementDao.updateLeave(leaveManagementBean);
				}else{
					throw new MISException(MISExceptionCodes.MIS005, ",As Leave Requester Has Already Taken All His Entitled Leaves");
				}
				
			}else if(leaveApprovalForm.getLeaveStatus().equals(MISConstants.HR_LEAVE_REJECTED)){
				leaveManagementBean.setLeaveStatus(leaveApprovalForm.getLeaveStatus());
				leaveManagementBean.setOfficerComments(leaveApprovalForm.getOfficerComments());
				status = leaveManagementDao.updateLeave(leaveManagementBean);
			}else{
				throw new MISException(MISExceptionCodes.MIS006,"No Action Executed");
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}
	
	private long getLeaveBalance(long employeeId,String leaveFromDate){
		long leaveBalance = 0;
		long totalAvailed = 0;
		long totalEligible = 0;
		try {
			System.out.println("IN LEAVE BALANCE");
//			String currentDate= MisUtility.now("dd-MM-yyyy");
			StringTokenizer stringTokenizer = new StringTokenizer(leaveFromDate, "-");
			int year = 0 ;
			int month = 0;
			int date = 0;
			while(stringTokenizer.hasMoreTokens()){
				date = new Integer(stringTokenizer.nextToken());
				month = new Integer(stringTokenizer.nextToken()) - 1; // subtracting 1 because Calender.MONTH starts with 0(Jan) 
				year = new Integer(stringTokenizer.nextToken());
			}
			LeaveBalanceBean leaveBalanceBean = new LeaveBalanceBean();
			leaveBalanceBean.setYearFor(year);
			leaveBalanceBean.setEmployeeId(employeeId);
			LeaveBalanceBean leaveBalanceBean2 = null;
			leaveBalanceBean2 = leaveBalanceDao.findLeaveBalance(leaveBalanceBean, null).get(0); 
			if(MisUtility.ifEmpty(leaveBalanceBean2)){
				totalAvailed = leaveBalanceBean2.getTotalAvailed();
				totalEligible = leaveBalanceBean2.getTotalEligibileLeave();
				leaveBalance = totalEligible-totalAvailed;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return leaveBalance;
	}
	
//	// To check entered Leave are not greater than leave balance and also year of leave applied is same as that of the current year
//	private boolean getAppliedLeave(LeaveForm leaveForm,MISSessionBean misSessionBean){
//		boolean status = false;
//		long leaveBalance = 0;
//		long appliedLeaveByUser = 0;
//		try {
////			Map<String,Long> leaveBalanceMap = getLeaveBalance(misSessionBean);
////			leaveBalance = leaveBalanceMap.get("leaveBalance");
//			
//			String currentDate = MisUtility.now("dd-MM-yyyy");
//			Date leaveFromDate = MisUtility.convertStringToDate(leaveForm.getLeaveFromDate());
//			Date leaveToDate = MisUtility.convertStringToDate(leaveForm.getLeaveToDate());
//			StringTokenizer stringTokenizer = new StringTokenizer(currentDate, "-");
//			int year = 0 ;
//			int month = 0;
//			int date = 0;
//			while(stringTokenizer.hasMoreTokens()){
//				date = new Integer(stringTokenizer.nextToken());
//				month = new Integer(stringTokenizer.nextToken()) - 1; // subtracting 1 because Calender.MONTH starts with 0(Jan) 
//				year = new Integer(stringTokenizer.nextToken());
//			}
//			
//			Locale aLocale = new Locale(Locale.ENGLISH.getLanguage(), Locale.UK.getCountry());
//			Calendar cal = GregorianCalendar.getInstance(aLocale);
//			
//			cal.setTime(leaveFromDate);				
//			int leaveFromYear = cal.get(Calendar.YEAR) ;		// To fetch Year
//			
//			cal.setTime(leaveToDate);
//			int leaveToYear = cal.get(Calendar.YEAR);			//to fetch year
//			
//			if(leaveFromYear!=year){
//				return false;
//			}
//			appliedLeaveByUser = MisUtility.getNumberOfDays(leaveFromDate, leaveToDate);
//			if(appliedLeaveByUser<=leaveBalance && appliedLeaveByUser>0)
//				return true;
//			
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return status;
//	}


}
