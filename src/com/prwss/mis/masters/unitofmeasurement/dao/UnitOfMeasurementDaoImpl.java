package com.prwss.mis.masters.unitofmeasurement.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class UnitOfMeasurementDaoImpl extends HibernateDaoSupport implements UnitOfMeasurementDao{
	@SuppressWarnings("unchecked")
	@Override
	public List<UnitOfMeasurementBean> findUnitOfMeasurements(UnitOfMeasurementBean unitOfMeasurementBean, List<String> statusList)
			throws DataAccessException {
		
		List<UnitOfMeasurementBean> unitOfMeasurementBeans=null;
		try{
		if(MisUtility.ifEmpty(unitOfMeasurementBean))
		{
			
			
				 DetachedCriteria criteria = DetachedCriteria.forClass(UnitOfMeasurementBean.class);
				 if(MisUtility.ifEmpty(unitOfMeasurementBean.getMeasurementId()))
				 {
					 criteria.add(Restrictions.eq("measurementId", unitOfMeasurementBean.getMeasurementId()));
				
				 }
				 if(MisUtility.ifEmpty(unitOfMeasurementBean.getMeasurementName()))
				 {
					 criteria.add(Restrictions.eq("measurementName", unitOfMeasurementBean.getMeasurementName()).ignoreCase());
				 
				 }
				 if(MisUtility.ifEmpty(unitOfMeasurementBean.getMeasurementDescription()))
				 {
					 criteria.add(Restrictions.eq("measurementDescription",unitOfMeasurementBean.getMeasurementDescription()).ignoreCase());
				
				 }
				 if(!MisUtility.ifEmpty(statusList))
				 {
					 criteria.add(Restrictions.in("misAuditBean.status", statusList));
				 
				 }
				 
				 unitOfMeasurementBeans = getHibernateTemplate().findByCriteria(criteria);
				
			 
		}
		}
		 catch (DataAccessException e) {
				throw e;
			}
		return unitOfMeasurementBeans;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UnitOfMeasurementBean> findUnitOfMeasurement(List<String> unitOfMeasurementIds)
			throws DataAccessException {
		List<UnitOfMeasurementBean> unitOfMeasurementBeans=null;

		try {
			if(!MisUtility.ifEmpty(unitOfMeasurementIds)){
				DetachedCriteria criteria = DetachedCriteria.forClass(UnitOfMeasurementBean.class);
				criteria.add(Restrictions.in("measurementId", unitOfMeasurementIds));
				unitOfMeasurementBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return unitOfMeasurementBeans;
	}

	@Override
	public boolean saveUnitOfMeasurement(
			UnitOfMeasurementBean unitOfMeasurementBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(unitOfMeasurementBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}
	
	@Override
	public boolean updateUnitOfMeasurement(
			UnitOfMeasurementBean unitOfMeasurementBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(unitOfMeasurementBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateUnitOfMeasurement(
			List<UnitOfMeasurementBean> unitOfMeasurementBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(unitOfMeasurementBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Set<UnitOfMeasurementBean> getUnitOfMeasurementIds(String locationId)
			throws DataAccessException {
		
		Set<UnitOfMeasurementBean> unitOfMeasurementBeans = null;
		
		DetachedCriteria criteria = DetachedCriteria.forClass(UnitOfMeasurementBean.class);
		
		if(MisUtility.ifEmpty(locationId)){	
			
			criteria.add(Restrictions.eq("locationId", locationId).ignoreCase());
		
		}
		criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_VERIFIED));
		
		unitOfMeasurementBeans = new TreeSet<UnitOfMeasurementBean>(getHibernateTemplate().findByCriteria(criteria));
		return unitOfMeasurementBeans;

	}

}
