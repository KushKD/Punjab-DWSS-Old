package com.prwss.mis.hr.leave.dao;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.hr.leave.LeaveManagementBean;

public class LeaveManagementDaoImpl extends HibernateDaoSupport implements LeaveManagementDao  {
private Logger log = Logger.getLogger(LeaveManagementDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<LeaveManagementBean> findLeave(
			LeaveManagementBean leaveManagementBean, List<String> statusList)
			throws DataAccessException {
		List<LeaveManagementBean> leaveManagementBeans = null;
		long entBy = 0;
		long reportingOfficerId = 0;
		if(MisUtility.ifEmpty(leaveManagementBean.getEntByEmployeeBean())){
			entBy = leaveManagementBean.getEntByEmployeeBean().getEmployeeId();
		}if(MisUtility.ifEmpty(leaveManagementBean.getReportingOfficerEmployeeBean())){
			reportingOfficerId = leaveManagementBean.getReportingOfficerEmployeeBean().getEmployeeId();
		}
		try {
			if(MisUtility.ifEmpty(leaveManagementBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(LeaveManagementBean.class);
				if(MisUtility.ifEmpty(entBy))
					criteria.add(Restrictions.eq("entByEmployeeBean.employeeId", entBy));
				if(MisUtility.ifEmpty(reportingOfficerId))
					criteria.add(Restrictions.eq("reportingOfficerEmployeeBean.employeeId",reportingOfficerId));
				if(MisUtility.ifEmpty(leaveManagementBean.getLeaveStatus()))
					criteria.add(Restrictions.eq("leaveStatus",leaveManagementBean.getLeaveStatus()));
				
				if(MisUtility.ifEmpty(leaveManagementBean.getLeaveFromDate()))
					criteria.add(Restrictions.eq("leaveFromDate",leaveManagementBean.getLeaveFromDate()));
				
				if(MisUtility.ifEmpty(leaveManagementBean.getLeaveId()))
					criteria.add(Restrictions.eq("leaveId",leaveManagementBean.getLeaveId()));
				
				if(MisUtility.ifEmpty(leaveManagementBean.getLeaveToDate()))
					criteria.add(Restrictions.eq("leaveToDate",leaveManagementBean.getLeaveToDate()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("status", statusList));
				
				criteria.addOrder(Property.forName("leaveId").desc() );
				leaveManagementBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return leaveManagementBeans;
	}

	@Override
	public long saveLeave(LeaveManagementBean leaveManagementBean)
			throws DataAccessException {
		long leaveId = 0;
		try {
			leaveId = (Long)getHibernateTemplate().save(leaveManagementBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return leaveId;
	}

	@Override
	public boolean updateLeave(LeaveManagementBean leaveManagementBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(leaveManagementBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LeaveManagementBean> findLeave(
			LeaveManagementBean leaveManagementBean, Date fromdate,
			Date toDate, List<String> statusList) throws DataAccessException {
		List<LeaveManagementBean> leaveManagementBeans = null;
		long entBy = 0;
		long reportingOfficerId = 0;
		if(MisUtility.ifEmpty(leaveManagementBean.getEntByEmployeeBean())){
			entBy = leaveManagementBean.getEntByEmployeeBean().getEmployeeId();
		}if(MisUtility.ifEmpty(leaveManagementBean.getReportingOfficerEmployeeBean())){
			reportingOfficerId = leaveManagementBean.getReportingOfficerEmployeeBean().getEmployeeId();
		}
		try {
			if(MisUtility.ifEmpty(leaveManagementBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(LeaveManagementBean.class);
				if(MisUtility.ifEmpty(entBy))
					criteria.add(Restrictions.eq("entByEmployeeBean.employeeId", entBy));
				if(MisUtility.ifEmpty(reportingOfficerId))
					criteria.add(Restrictions.eq("reportingOfficerEmployeeBean.employeeId",reportingOfficerId));
				if(MisUtility.ifEmpty(leaveManagementBean.getLeaveStatus()))
					criteria.add(Restrictions.eq("leaveStatus",leaveManagementBean.getLeaveStatus()));
				
				if(MisUtility.ifEmpty(leaveManagementBean.getLeaveFromDate()))
					criteria.add(Restrictions.eq("leaveFromDate",leaveManagementBean.getLeaveFromDate()));
				
				if(MisUtility.ifEmpty(leaveManagementBean.getLeaveId()))
					criteria.add(Restrictions.eq("leaveId",leaveManagementBean.getLeaveId()));
				
				if(MisUtility.ifEmpty(leaveManagementBean.getLeaveToDate()))
					criteria.add(Restrictions.eq("leaveToDate",leaveManagementBean.getLeaveToDate()));
				
				if(MisUtility.ifEmpty(fromdate) && MisUtility.ifEmpty(toDate) )
					criteria.add(Restrictions.between("entDate", fromdate, toDate));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("status", statusList));
				
				criteria.addOrder(Property.forName("leaveId").desc() );
				leaveManagementBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return leaveManagementBeans;
	}
}

