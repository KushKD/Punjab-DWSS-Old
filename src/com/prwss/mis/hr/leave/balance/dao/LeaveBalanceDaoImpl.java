package com.prwss.mis.hr.leave.balance.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.hr.salarystructure.HRSalaryStructureBean;

public class LeaveBalanceDaoImpl extends HibernateDaoSupport implements LeaveBalanceDao {
	private Logger log = Logger.getLogger(LeaveBalanceDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<LeaveBalanceBean> findLeaveBalance(
			LeaveBalanceBean leaveBalanceBean, List<String> statusList)
			throws DataAccessException {
		List<LeaveBalanceBean> leaveBalanceBeans = null;
		
		try {
			if(MisUtility.ifEmpty(leaveBalanceBean)){
				System.out.println("criteria");
				System.out.println(leaveBalanceBean.getEmployeeId());
				System.out.println(leaveBalanceBean.getLocationId());
				System.out.println(leaveBalanceBean.getYearFor());
				System.out.println(leaveBalanceBean.getTotalEligibileLeave());
				DetachedCriteria criteria = DetachedCriteria.forClass(LeaveBalanceBean.class);
				if(MisUtility.ifEmpty(leaveBalanceBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId",leaveBalanceBean.getLocationId()));
				if(MisUtility.ifEmpty(leaveBalanceBean.getEmployeeId()))
					criteria.add(Restrictions.eq("employeeId", leaveBalanceBean.getEmployeeId()));
				if(MisUtility.ifEmpty(leaveBalanceBean.getYearFor()))
					criteria.add(Restrictions.eq("yearFor", leaveBalanceBean.getYearFor()));
				leaveBalanceBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return leaveBalanceBeans;
	}
	
	@Override
	public boolean saveEntitledLeave(LeaveBalanceBean leaveBalanceBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(leaveBalanceBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdateLeaveBalance(
			LeaveBalanceBean leaveBalanceBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(leaveBalanceBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

}
