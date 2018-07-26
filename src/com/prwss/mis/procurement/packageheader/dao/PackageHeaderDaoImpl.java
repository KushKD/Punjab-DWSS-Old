package com.prwss.mis.procurement.packageheader.dao;

import java.util.ArrayList;
import java.util.Iterator;
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
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;

public class PackageHeaderDaoImpl extends HibernateDaoSupport implements PackageHeaderDao {
    private Logger log = Logger.getLogger(PackageHeaderDaoImpl.class);
	@SuppressWarnings("unchecked")
    @Override
	public List<PackageHeaderBean> findPackage(
			PackageHeaderBean packageHeaderBean, List<String> statusList)
			throws DataAccessException {
		List<PackageHeaderBean> packageHeaderBeans = null;
		try {
			if(MisUtility.ifEmpty(packageHeaderBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(PackageHeaderBean.class);
				criteria.add(Restrictions.eq("locationId",packageHeaderBean.getLocationId()));
				criteria.add(Restrictions.eq("packageId",packageHeaderBean.getPackageId()));
				if(MisUtility.ifEmpty(packageHeaderBean.getPackageType()))
					criteria.add(Restrictions.eq("packageType",packageHeaderBean.getPackageType()));
				if(MisUtility.ifEmpty(packageHeaderBean.getPostPriorStatus()))
					criteria.add(Restrictions.eq("postPriorStatus",packageHeaderBean.getPostPriorStatus()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				packageHeaderBeans =  getHibernateTemplate().findByCriteria(criteria);				
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return packageHeaderBeans;
	}
	
	@SuppressWarnings("unchecked")
    @Override
	public List<PackageHeaderBean> findPackageForPriorReview(
			PackageHeaderBean packageHeaderBean, List<String> statusList)
			throws DataAccessException {
		List<PackageHeaderBean> packageHeaderBeans = null;
		try {
			if(MisUtility.ifEmpty(packageHeaderBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(PackageHeaderBean.class);
				if(MisUtility.ifEmpty(packageHeaderBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId",packageHeaderBean.getLocationId()));
				if(MisUtility.ifEmpty(packageHeaderBean.getPlanId()))
					criteria.add(Restrictions.eq("planId",packageHeaderBean.getPlanId()));
				if(MisUtility.ifEmpty(packageHeaderBean.getPackageId()))
					criteria.add(Restrictions.eq("packageId",packageHeaderBean.getPackageId()));
				if(MisUtility.ifEmpty(packageHeaderBean.getPackageType()))
					criteria.add(Restrictions.eq("packageType",packageHeaderBean.getPackageType()));
				if(MisUtility.ifEmpty(packageHeaderBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", packageHeaderBean.getSchemeId()));
				if(MisUtility.ifEmpty(packageHeaderBean.getPostPriorStatus()))
					criteria.add(Restrictions.eq("postPriorStatus",packageHeaderBean.getPostPriorStatus()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				packageHeaderBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return packageHeaderBeans;
	}

	
	@Override
	public boolean savePackageHeader(List<PackageHeaderBean> packageHeaderBean)
			throws DataAccessException {
		try {
			
			Iterator<PackageHeaderBean> PackageHeaderbean=packageHeaderBean.iterator();
			while(PackageHeaderbean.hasNext()){
				PackageHeaderBean PackageHeaderbeans=PackageHeaderbean.next();
				getHibernateTemplate().merge(PackageHeaderbeans);
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			   
			}
			        
				
			//getHibernateTemplate().save(packageHeaderBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}
	
		
	@Override
	public boolean savePackage(PackageHeaderBean packageHeaderBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().flush();
			getHibernateTemplate().save(packageHeaderBean);
			getHibernateTemplate().flush();
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}
	@Override
	public boolean updatePackage(PackageHeaderBean packageHeaderBean){
	try {
		getHibernateTemplate().update(packageHeaderBean);
	} catch (DataAccessException e) {
		log.error(e.getLocalizedMessage(),e);
		throw e;
	}
	return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<PackageHeaderBean> getPackageIds(String schemeId , Long subPlanId)
			throws DataAccessException {
		Set<PackageHeaderBean> packageHeaderBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(PackageHeaderBean.class);
			System.out.println("Package Header Dao: "+schemeId);
			if(MisUtility.ifEmpty(schemeId)&& !(schemeId.equals("null")))
				criteria.add(Restrictions.eq("schemeId", schemeId));
			
			if(MisUtility.ifEmpty(subPlanId))
			criteria.add(Restrictions.eq("subPlanId", subPlanId));
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			
			packageHeaderBeans =new TreeSet<PackageHeaderBean>(getHibernateTemplate().findByCriteria(criteria));
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
			
		}
		return packageHeaderBeans;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Set<PackageHeaderBean> getPackageIds(String schemeId , Long subPlanId , List<String> statusList)
			throws DataAccessException {
		Set<PackageHeaderBean> packageHeaderBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(PackageHeaderBean.class);
			if(MisUtility.ifEmpty(schemeId)&& !(schemeId.equals("null")))
				criteria.add(Restrictions.eq("schemeId", schemeId));
			if(MisUtility.ifEmpty(subPlanId))
				criteria.add(Restrictions.eq("subPlanId", subPlanId));
			if(!MisUtility.ifEmpty(statusList))
				criteria.add(Restrictions.in("misAuditBean.status", statusList));			
			packageHeaderBeans =new TreeSet<PackageHeaderBean>(getHibernateTemplate().findByCriteria(criteria));
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
			
		}
		return packageHeaderBeans;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Set<PackageHeaderBean> getPackageIdsAll(String schemeId , long subPlanId) throws DataAccessException {
		Set<PackageHeaderBean> packageHeaderBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(PackageHeaderBean.class);
			if(MisUtility.ifEmpty(schemeId)&& !(schemeId.equals("null")))
			criteria.add(Restrictions.eq("schemeId", schemeId));
			if(MisUtility.ifEmpty(subPlanId))
			criteria.add(Restrictions.eq("subPlanId", subPlanId));
			
			packageHeaderBeans =new TreeSet<PackageHeaderBean>(getHibernateTemplate().findByCriteria(criteria));
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
			
		}
		return packageHeaderBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageHeaderBean> getPackageForSubPlan(
			PackageHeaderBean packageHeaderBean) throws DataAccessException {
		 List<PackageHeaderBean> packageHeaderBeans = new ArrayList<PackageHeaderBean>();
		 try
		 {
			 DetachedCriteria criteria = DetachedCriteria.forClass(PackageHeaderBean.class);	
			 if(MisUtility.ifEmpty(packageHeaderBean.getSubPlanId())){
				 criteria.add(Restrictions.eq("subPlanId",packageHeaderBean.getSubPlanId()));
			 }
			 if(MisUtility.ifEmpty(packageHeaderBean.getLocationId())){
				 criteria.add(Restrictions.eq("locationId", packageHeaderBean.getLocationId()));
			 }
			 packageHeaderBeans = getHibernateTemplate().findByCriteria(criteria);
		}catch (DataAccessException e) {
	log.error(e.getLocalizedMessage(),e);
	e.printStackTrace();
	throw e;
	
	     }
			 return packageHeaderBeans;	
	}
}
