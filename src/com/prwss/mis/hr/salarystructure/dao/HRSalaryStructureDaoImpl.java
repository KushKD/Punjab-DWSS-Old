package com.prwss.mis.hr.salarystructure.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.hr.salarystructure.HRSalaryStructureBean;

public class HRSalaryStructureDaoImpl extends HibernateDaoSupport implements HRSalaryStructureDao{
private Logger log = Logger.getLogger(HRSalaryStructureDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<HRSalaryStructureBean> findSalary(
			HRSalaryStructureBean hrSalaryStructureBean, List<String> statusList)
			throws DataAccessException {
		List<HRSalaryStructureBean> hrSalaryStructureBeans = null;
		try {
			if(MisUtility.ifEmpty(hrSalaryStructureBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(HRSalaryStructureBean.class);
				if(MisUtility.ifEmpty(hrSalaryStructureBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", hrSalaryStructureBean.getLocationId()));
				if(MisUtility.ifEmpty(hrSalaryStructureBean.getFromDate()))
					criteria.add(Restrictions.eq("fromDate",hrSalaryStructureBean.getFromDate()));
				if(MisUtility.ifEmpty(hrSalaryStructureBean.getEmployeeBean().getEmployeeId()))
					criteria.add(Restrictions.eq("employeeBean.employeeId",hrSalaryStructureBean.getEmployeeBean().getEmployeeId()));
				
				criteria.add(Restrictions.isNull("toDate")); //to fetch the latest modified salary where to date is empty
				hrSalaryStructureBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		//System.out.println("daoiml-----------------------------------"+hrSalaryStructureBeans);
		return hrSalaryStructureBeans;
	}

	@Override
	public boolean saveSalary(HRSalaryStructureBean hrSalaryStructureBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(hrSalaryStructureBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateSalary(HRSalaryStructureBean hrSalaryStructureBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(hrSalaryStructureBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

}
