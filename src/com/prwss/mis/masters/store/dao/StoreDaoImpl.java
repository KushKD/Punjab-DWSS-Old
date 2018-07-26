package com.prwss.mis.masters.store.dao;

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

public class StoreDaoImpl extends HibernateDaoSupport implements StoreDao {
private Logger log = Logger.getLogger(StoreDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<StoreBean> findStore(StoreBean storeBean, List<String> statusList) throws DataAccessException {
		 List<StoreBean> storeBeans = null;
		 
		 try {
			if(MisUtility.ifEmpty(storeBean)){
				 DetachedCriteria criteria = DetachedCriteria.forClass(StoreBean.class);
				 if(MisUtility.ifEmpty(storeBean.getStoreId()))
					 criteria.add(Restrictions.eq("storeId", storeBean.getStoreId()));
				 if(MisUtility.ifEmpty(storeBean.getStoreName()))
					 criteria.add(Restrictions.eq("storeName", storeBean.getStoreName()).ignoreCase());
				 if(MisUtility.ifEmpty(storeBean.getStoreAddress()))
					 criteria.add(Restrictions.eq("storeAddress", storeBean.getStoreAddress()).ignoreCase());
				 if(MisUtility.ifEmpty(storeBean.getLocationId()))
					 criteria.add(Restrictions.eq("locationId", storeBean.getLocationId()).ignoreCase());
				 if(!MisUtility.ifEmpty(statusList))
					 criteria.add(Restrictions.in("misAuditBean.status", statusList));
				 
				 storeBeans = getHibernateTemplate().findByCriteria(criteria);
			 }
		} catch (DataAccessException e) {
			throw e;
		}
		 
		return storeBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StoreBean> findStore(List<Long> storeIds) throws DataAccessException {
		List<StoreBean> storeBeans = null;

		try {
			if(!MisUtility.ifEmpty(storeIds)){
				DetachedCriteria criteria = DetachedCriteria.forClass(StoreBean.class);
				criteria.add(Restrictions.in("storeId", storeIds));
				storeBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return storeBeans;
	}

	@Override
	public long saveStore(StoreBean storeBean) throws DataAccessException {
		
		long storeId = 0;
		try {
			storeId = (Long)getHibernateTemplate().save(storeBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return storeId;
	}

	@Override
	public boolean updateStore(StoreBean storeBean) throws DataAccessException {

		try {
			getHibernateTemplate().update(storeBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateStore(List<StoreBean> storeBeans) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(storeBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<StoreBean> getDistinctStoreCodes(String locationId) throws DataAccessException {
		Set<StoreBean> storeBeans = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(StoreBean.class);
		if(MisUtility.ifEmpty(locationId)){			
			criteria.add(Restrictions.eq("locationId", locationId).ignoreCase());
		}
		criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_VERIFIED));
		storeBeans = new TreeSet<StoreBean>(getHibernateTemplate().findByCriteria(criteria));
		return storeBeans;
	}

}
