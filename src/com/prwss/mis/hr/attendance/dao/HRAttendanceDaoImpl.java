package com.prwss.mis.hr.attendance.dao;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.hr.attendance.HRAttendanceBean;

public class HRAttendanceDaoImpl extends HibernateDaoSupport implements HRAttendanceDao {
private Logger log = Logger.getLogger(HRAttendanceDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<HRAttendanceBean> findAttendance(
			HRAttendanceBean hrAttendanceBean, List<String> statusList)
			throws DataAccessException {
		List<HRAttendanceBean> hrAttendanceBeans = null;
		try {
			if(MisUtility.ifEmpty(hrAttendanceBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(HRAttendanceBean.class);
//				
				if(MisUtility.ifEmpty(hrAttendanceBean.getAttendenceId()))
					criteria.add(Restrictions.eq("attendenceId", hrAttendanceBean.getAttendenceId()));
				
				if(MisUtility.ifEmpty(hrAttendanceBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", hrAttendanceBean.getLocationId()));
				
				if(MisUtility.ifEmpty(hrAttendanceBean.getAttendanceDate()))
					criteria.add(Restrictions.eq("attendanceDate",hrAttendanceBean.getAttendanceDate()));

				if(MisUtility.ifEmpty(hrAttendanceBean.getEmployeeBean().getEmployeeId()))
					criteria.add(Restrictions.eq("employeeBean.employeeId",hrAttendanceBean.getEmployeeBean().getEmployeeId()));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				hrAttendanceBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return hrAttendanceBeans;
	}

	@Override
	public boolean saveOrUpdateAttendance(HRAttendanceBean hrAttendanceBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(hrAttendanceBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

	@Override
	public boolean saveAttendance(HRAttendanceBean hrAttendanceBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(hrAttendanceBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<HRAttendanceBean> findAttendanceFromAttendencIds(List<Long> attendenceIds,
			List<String> statusList) throws DataAccessException {
		List<HRAttendanceBean> hrAttendanceBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(HRAttendanceBean.class);
				if(!MisUtility.ifEmpty(attendenceIds)){
					
					criteria.add(Restrictions.in("attendenceId",attendenceIds));
					
					if(!MisUtility.ifEmpty(statusList))
						criteria.add(Restrictions.in("misAuditBean.status", statusList));
					
					hrAttendanceBeans = getHibernateTemplate().findByCriteria(criteria);
					
				}		
			
		} catch (DataAccessException e) {
			throw e;
		}
		
		return hrAttendanceBeans;
	}

	@Override
	public boolean UpdateAttendance(List<HRAttendanceBean> hrAttendanceBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(hrAttendanceBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

}
