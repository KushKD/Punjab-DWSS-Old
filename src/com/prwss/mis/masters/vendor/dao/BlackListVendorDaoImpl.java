package com.prwss.mis.masters.vendor.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.procurement.plan.CreateProcPlanBean;

public class BlackListVendorDaoImpl extends HibernateDaoSupport implements BlackListVendorDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BlackListVendorBean> findBlackListVendor(BlackListVendorBean blackListVendorBean, List<String> statusList) throws DataAccessException {
		List<BlackListVendorBean> blackListVendorBeans= null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BlackListVendorBean.class);
			if(MisUtility.ifEmpty(blackListVendorBean)){
				
				if(MisUtility.ifEmpty(blackListVendorBean.getVendorId()))
					criteria.add(Restrictions.eq("vendorId", blackListVendorBean.getVendorId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
			}
			blackListVendorBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}		
		return blackListVendorBeans;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<BlackListVendorBean> findBlackListVendor(List<String> vendorIds) throws DataAccessException {
		List<BlackListVendorBean> blackListVendorBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BlackListVendorBean.class);
			
			if(!MisUtility.ifEmpty(vendorIds))
				criteria.add(Restrictions.in("vendorId", vendorIds));
				blackListVendorBeans= getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		return blackListVendorBeans;
	}
	
	@Override
	public boolean saveBlackListVendor(BlackListVendorBean blackListVendorBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(blackListVendorBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}
	@Override
	public boolean updateBlackListVendor(BlackListVendorBean blackListVendorBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(blackListVendorBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateBlackListVendor(List<BlackListVendorBean> blackListVendorBeans) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(blackListVendorBeans);
		} catch (DataAccessException e) {
			throw e;
		}		
		return true;
	}
}