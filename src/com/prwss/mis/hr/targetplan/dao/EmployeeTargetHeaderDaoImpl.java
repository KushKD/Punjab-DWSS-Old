package com.prwss.mis.hr.targetplan.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.hr.targetplan.EmployeeTargetHeaderBean;

public class EmployeeTargetHeaderDaoImpl extends HibernateDaoSupport implements EmployeeTargetHeaderDao {
private Logger log = Logger.getLogger(EmployeeTargetHeaderDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeTargetHeaderBean> findEmployeeTargetHeader(
			EmployeeTargetHeaderBean employeeTargetHeaderBean,
			List<String> statusList) throws DataAccessException {
		List<EmployeeTargetHeaderBean> employeeTargetHeaderBeans = null;
		try {
			if(MisUtility.ifEmpty(employeeTargetHeaderBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeTargetHeaderBean.class);
				if(MisUtility.ifEmpty(employeeTargetHeaderBean.getTargetPlanId()))
				criteria.add(Restrictions.eq("targetPlanId",employeeTargetHeaderBean.getTargetPlanId()));
				if(MisUtility.ifEmpty(employeeTargetHeaderBean.getEmployeeId()))
					criteria.add(Restrictions.eq("employeeId",employeeTargetHeaderBean.getEmployeeId()));
				if(MisUtility.ifEmpty(employeeTargetHeaderBean.getReportingOfficerId()))
					criteria.add(Restrictions.eq("reportingOfficerId",employeeTargetHeaderBean.getReportingOfficerId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				employeeTargetHeaderBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return employeeTargetHeaderBeans;
	}

	@Override
	public long saveEmployeeTargetHeader(
			EmployeeTargetHeaderBean employeeTargetHeaderBean)
			throws DataAccessException {
		long targetPlanId = 0;
		try {
			targetPlanId = (Long)getHibernateTemplate().save(employeeTargetHeaderBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return targetPlanId;
	}

	@Override
	public boolean updateEmployeeTargetHeader(
			EmployeeTargetHeaderBean employeeTargetHeaderBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(employeeTargetHeaderBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}
	@Override
    public List<EmployeeTargetHeaderBean> findEmployeeTargetHeaderByEmployeeId(
                EmployeeTargetHeaderBean employeeTargetHeaderBean,
                List<String> statusList) throws DataAccessException {
          List<EmployeeTargetHeaderBean> employeeTargetHeaderBeans = null;
          try {
                if(MisUtility.ifEmpty(employeeTargetHeaderBean)){
                      DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeTargetHeaderBean.class);
                            criteria.add(Restrictions.eq("employeeId",employeeTargetHeaderBean.getEmployeeId()));
                      if(!MisUtility.ifEmpty(statusList))
                            criteria.add(Restrictions.in("misAuditBean.status", statusList));
                      employeeTargetHeaderBeans =  getHibernateTemplate().findByCriteria(criteria);
                }
          } catch (DataAccessException e) {
                log.error(e.getLocalizedMessage(),e);
                throw e;
          }
          return employeeTargetHeaderBeans;
    }

}
