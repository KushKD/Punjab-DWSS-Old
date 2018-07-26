package com.prwss.mis.masters.vendor.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class VendorDaoImpl extends HibernateDaoSupport implements VendorDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<VendorBean> findVendor(VendorBean vendorBean, List<String> statusList) throws DataAccessException {
		List<VendorBean> vendorBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(VendorBean.class);
			if(MisUtility.ifEmpty(vendorBean)){
				
				if(MisUtility.ifEmpty(vendorBean.getVendorId()))
					criteria.add(Restrictions.eq("vendorId", vendorBean.getVendorId()));
				if(MisUtility.ifEmpty(vendorBean.getVendorName()))
					criteria.add(Restrictions.eq("vendorName", vendorBean.getVendorName()));
				if(MisUtility.ifEmpty(vendorBean.getVendorType()))
					criteria.add(Restrictions.eq("vendorType", vendorBean.getVendorType()));
				if(MisUtility.ifEmpty(vendorBean.getAddress1()))
					criteria.add(Restrictions.eq("address1", vendorBean.getAddress1()));
				if(MisUtility.ifEmpty(vendorBean.getAddress2()))
					criteria.add(Restrictions.eq("address2", vendorBean.getAddress2()));
				if(MisUtility.ifEmpty(vendorBean.getWorkPhone()))
					criteria.add(Restrictions.eq("workPhone", vendorBean.getWorkPhone()));
				if(MisUtility.ifEmpty(vendorBean.getMobilePhone()))
					criteria.add(Restrictions.eq("mobilePhone", vendorBean.getMobilePhone()));
				if(MisUtility.ifEmpty(vendorBean.getStreet()))
					criteria.add(Restrictions.eq("street", vendorBean.getStreet()));
				if(MisUtility.ifEmpty(vendorBean.getCity()))
					criteria.add(Restrictions.eq("city", vendorBean.getCity()));
				if(MisUtility.ifEmpty(vendorBean.getState()))
					criteria.add(Restrictions.eq("state", vendorBean.getState()));
				if(MisUtility.ifEmpty(vendorBean.getPin()))
					criteria.add(Restrictions.eq("pin", vendorBean.getPin()));
				if(MisUtility.ifEmpty(vendorBean.getLandmark()))
					criteria.add(Restrictions.eq("landmark", vendorBean.getLandmark()));
				if(MisUtility.ifEmpty(vendorBean.getEmail()))
					criteria.add(Restrictions.eq("email", vendorBean.getEmail()));
				if(MisUtility.ifEmpty(vendorBean.getPanNo()))
					criteria.add(Restrictions.eq("panNo", vendorBean.getPanNo()).ignoreCase());
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
			}
			
			vendorBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return vendorBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VendorBean> findVendor(List<String> vendorIds) throws DataAccessException {
		List<VendorBean> vendorBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(VendorBean.class);
			
			if(!MisUtility.ifEmpty(vendorIds))
				criteria.add(Restrictions.in("vendorId", vendorIds));
			
			vendorBeans= getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
			
		return vendorBeans;
	}

	@Override
	public boolean saveVendor(VendorBean vendorBean) throws DataAccessException {

		try {
			getHibernateTemplate().save(vendorBean);
			System.out.println("after vendor dao");
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateVendor(VendorBean vendorBean) throws DataAccessException {
		
		try {
			getHibernateTemplate().update(vendorBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateVendor(List<VendorBean> vendorBeans) throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(vendorBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public Set<VendorBean> getDistinctVendorCodes() throws DataAccessException{
		Set<VendorBean> vendorBeans = null;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(VendorBean.class);
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			criteria.addOrder(Order.asc("vendorName"));
			vendorBeans =  new TreeSet<VendorBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}
 
		return vendorBeans;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VendorBean> getVendersNames()
			throws DataAccessException {
		List<VendorBean> vendorBeans = null;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(VendorBean.class);
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			criteria.addOrder(Property.forName("vendorName").asc());
			vendorBeans =  new ArrayList<VendorBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}
		// TODO Auto-generated method stub
		Collections.sort(vendorBeans);
		return vendorBeans;
	}

}
