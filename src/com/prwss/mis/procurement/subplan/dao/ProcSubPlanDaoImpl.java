package com.prwss.mis.procurement.subplan.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.procurement.subplan.ProcSubPlanHeaderBean;

public class ProcSubPlanDaoImpl extends HibernateDaoSupport implements ProcSubPlanDao {
    private Logger log = Logger.getLogger(ProcSubPlanDaoImpl.class);
	
   
    @Override
    @SuppressWarnings("unchecked")
	public List<ProcSubPlanHeaderBean> findSubplan(
			ProcSubPlanHeaderBean procSubPlanHeaderBean, List<String> statusList)
			throws DataAccessException {
		List<ProcSubPlanHeaderBean> procSubPlanHeaderBeans = null;
		try {
			if(MisUtility.ifEmpty(procSubPlanHeaderBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(ProcSubPlanHeaderBean.class);
					criteria.add(Restrictions.eq("locationId",procSubPlanHeaderBean.getLocationId()));
					criteria.add(Restrictions.eq("subPlanId",procSubPlanHeaderBean.getSubPlanId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				procSubPlanHeaderBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return procSubPlanHeaderBeans;
		
	}

   /* @Override
    @SuppressWarnings("unchecked")
	public List<ProcSubPlanPrw2HeaderBean> findSubplanPrw2(
			ProcSubPlanPrw2HeaderBean procSubPlanHeaderBean, List<String> statusList)
			throws DataAccessException {
    	System.out.println("inside dao====findSubplanPrw2====");
		List<ProcSubPlanPrw2HeaderBean> procSubPlanHeaderBeans = null;
		try {
			if(MisUtility.ifEmpty(procSubPlanHeaderBean)){
				System.out.println("inside if dao====findSubplanPrw2===="+procSubPlanHeaderBean.getSubPlanId());
				DetachedCriteria criteria = DetachedCriteria.forClass(ProcSubPlanPrw2HeaderBean.class);
					criteria.add(Restrictions.eq("locationId",procSubPlanHeaderBean.getLocationId()));
					criteria.add(Restrictions.eq("subPlanId",procSubPlanHeaderBean.getSubPlanId()));
					criteria.add(Restrictions.eq("projectCode",procSubPlanHeaderBean.getProjectCode()));
					
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				procSubPlanHeaderBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return procSubPlanHeaderBeans;
		
	}*/

    @Override
    @SuppressWarnings("unchecked")
    public Set<ProcSubPlanHeaderBean> getSubPlanIds(String planId, List<String> statusList)
    throws DataAccessException {
    	Set<ProcSubPlanHeaderBean> procSubPlanHeaderBeans = null;
    	try {
    		if(MisUtility.ifEmpty(planId)){

    			DetachedCriteria criteria = DetachedCriteria.forClass(ProcSubPlanHeaderBean.class);
    			criteria.add(Restrictions.eq("planId",planId));
    			if(!MisUtility.ifEmpty(statusList)){
    				criteria.add(Restrictions.in("misAuditBean.status",statusList));
    			}
    			procSubPlanHeaderBeans =  new TreeSet<ProcSubPlanHeaderBean>(getHibernateTemplate().findByCriteria(criteria));
    		}
    	} catch (DataAccessException e) {
    		log.error(e.getLocalizedMessage(),e);
    		throw e;
    	}
    	return procSubPlanHeaderBeans;
    }

    /*@Override
    @SuppressWarnings("unchecked")
    public Set<ProcSubPlanPrw2HeaderBean> getSubPlanIdsPrw2(String planId, List<String> statusList)
    throws DataAccessException {
    	Set<ProcSubPlanPrw2HeaderBean> procSubPlanHeaderBeans = null;
    	try {
    		if(MisUtility.ifEmpty(planId)){

    			DetachedCriteria criteria = DetachedCriteria.forClass(ProcSubPlanPrw2HeaderBean.class);
    			criteria.add(Restrictions.eq("planId",planId));
    			if(!MisUtility.ifEmpty(statusList)){
    				criteria.add(Restrictions.in("misAuditBean.status",statusList));
    			}
    			procSubPlanHeaderBeans =  new TreeSet<ProcSubPlanPrw2HeaderBean>(getHibernateTemplate().findByCriteria(criteria));
    		}
    	} catch (DataAccessException e) {
    		log.error(e.getLocalizedMessage(),e);
    		throw e;
    	}
    	return procSubPlanHeaderBeans;
    }*/
	@Override
	public long saveSubPlan(ProcSubPlanHeaderBean procSubPlanHeaderBean)
			throws DataAccessException {
		long subPlanId = 0;
		try {
			subPlanId = (Long)getHibernateTemplate().save(procSubPlanHeaderBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return subPlanId;
	}
	/*@Override
	public long saveSubPlanPrwss2(ProcSubPlanPrw2HeaderBean procSubPlanHeaderBean2)
			throws DataAccessException {
		long subPlanId = 0;
		try {
			subPlanId = (Long)getHibernateTemplate().save(procSubPlanHeaderBean2);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return subPlanId;
	}*/
	@Override
	public boolean updateSubPlan(ProcSubPlanHeaderBean procSubPlanHeaderBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(procSubPlanHeaderBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}
	/*@Override
	public boolean updateSubPlanPrw2(ProcSubPlanPrw2HeaderBean procSubPlanHeaderBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(procSubPlanHeaderBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}*/

	

	
}
