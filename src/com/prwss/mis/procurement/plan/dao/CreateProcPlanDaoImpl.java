package com.prwss.mis.procurement.plan.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.procurement.plan.CreateProcPlanBean;

public class CreateProcPlanDaoImpl extends HibernateDaoSupport implements CreateProcPlanDao {
private Logger log = Logger.getLogger(CreateProcPlanDaoImpl.class);
    @SuppressWarnings("unchecked")
	@Override
	public List<CreateProcPlanBean> findCreateProcPlanFrom(
			CreateProcPlanBean createProcPlanBean, List<String> statusList)
			throws DataAccessException {
		List<CreateProcPlanBean> createProcPlanBeans = null;
		try {
			if(MisUtility.ifEmpty(createProcPlanBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(CreateProcPlanBean.class);
				if(MisUtility.ifEmpty(createProcPlanBean.getLocationId())){
					criteria.add(Restrictions.eq("locationId",createProcPlanBean.getLocationId()));
				}
				if(MisUtility.ifEmpty(createProcPlanBean.getPlanId())){
					criteria.add(Restrictions.eq("planId",createProcPlanBean.getPlanId()));
				}
				if(MisUtility.ifEmpty(createProcPlanBean.getPlanType()))
					criteria.add(Restrictions.eq("planType", createProcPlanBean.getPlanType()));	
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				createProcPlanBeans =  getHibernateTemplate().findByCriteria(criteria);
				System.out.println("DAO createProcPlanBeans: "+createProcPlanBeans);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return createProcPlanBeans;
	}

	@Override
	public boolean saveCreateProcPlanFrom(CreateProcPlanBean createProcPlanBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(createProcPlanBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateCreateProcPlanFrom(
			CreateProcPlanBean createProcPlanBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(createProcPlanBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<CreateProcPlanBean> getProcPlanIds(String locationId,boolean releaseStatus,String procurementType) throws DataAccessException {
		Set<CreateProcPlanBean> createProcPlanBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CreateProcPlanBean.class);
			if(MisUtility.ifEmpty(locationId))
				criteria.add(Restrictions.eq("locationId",locationId));
			if(MisUtility.ifEmpty(releaseStatus))
				criteria.add(Restrictions.eq("releaseStatus",releaseStatus));
			if(MisUtility.ifEmpty(procurementType))
				criteria.add(Restrictions.eq("planType",procurementType));	
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			createProcPlanBeans =new TreeSet<CreateProcPlanBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw e;
			
		}
		return createProcPlanBeans;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Set<CreateProcPlanBean> getProcPlanIdsOnPlanType(String locationId,String procurementType) throws DataAccessException {
		Set<CreateProcPlanBean> createProcPlanBeans = null;
//		List<String> statusList = new ArrayList<String>();
//		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CreateProcPlanBean.class);
			if(MisUtility.ifEmpty(locationId))
			criteria.add(Restrictions.eq("locationId", locationId));
			if(MisUtility.ifEmpty(procurementType))
				criteria.add(Restrictions.eq("planType", procurementType));	
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			createProcPlanBeans =new TreeSet<CreateProcPlanBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
			
		}
		return createProcPlanBeans;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Set<CreateProcPlanBean> getProcPlanTypeOnPlanIds(String locationId,String planId) throws DataAccessException{
		Set<CreateProcPlanBean> createProcPlanBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CreateProcPlanBean.class);
			if(MisUtility.ifEmpty(locationId))
			criteria.add(Restrictions.eq("locationId", locationId));
			if(MisUtility.ifEmpty(planId))
				criteria.add(Restrictions.eq("planId", planId));	
			createProcPlanBeans =new TreeSet<CreateProcPlanBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
			
		}
		return createProcPlanBeans;
	}
}
