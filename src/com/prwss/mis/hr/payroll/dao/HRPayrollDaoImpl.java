package com.prwss.mis.hr.payroll.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.hr.payroll.HRPayrollBean;

public class HRPayrollDaoImpl extends HibernateDaoSupport implements HRPayrollDao {
private Logger log = Logger.getLogger(HRPayrollDaoImpl.class);

	
	@Override
	public boolean saveHRPayroll(HRPayrollBean hrPayrollBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(hrPayrollBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HRPayrollBean> findHRPayroll(HRPayrollBean hrPayrollBean,
			List<String> statusList) throws DataAccessException {
		List<HRPayrollBean> hrPayrollBeans = null;
		try {
			if(MisUtility.ifEmpty(hrPayrollBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(HRPayrollBean.class);
				if(MisUtility.ifEmpty(hrPayrollBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", hrPayrollBean.getLocationId()));
				if(MisUtility.ifEmpty(hrPayrollBean.getVocId()))
					criteria.add(Restrictions.eq("vocId",hrPayrollBean.getVocId()));
				if(MisUtility.ifEmpty(hrPayrollBean.getEmployeeBean().getEmployeeId()))
					criteria.add(Restrictions.eq("employeeBean.employeeId",hrPayrollBean.getEmployeeBean().getEmployeeId()));
				if(MisUtility.ifEmpty(hrPayrollBean.getPayrollMonth()))
					criteria.add(Restrictions.eq("payrollMonth",hrPayrollBean.getPayrollMonth()));
				if(MisUtility.ifEmpty(hrPayrollBean.getPayrollYear()))
					criteria.add(Restrictions.eq("payrollYear",hrPayrollBean.getPayrollYear()));
				
				hrPayrollBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return hrPayrollBeans;
		}

}
