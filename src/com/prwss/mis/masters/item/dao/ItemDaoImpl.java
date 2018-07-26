package com.prwss.mis.masters.item.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.store.dao.StoreBean;

public class ItemDaoImpl extends HibernateDaoSupport implements ItemDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemBean> findItem(ItemBean itemBean, List<String> statusList) throws DataAccessException {
		
		List<ItemBean> itemBeans = null;
		
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass(ItemBean.class);
				if(MisUtility.ifEmpty(itemBean)){
					
					if(MisUtility.ifEmpty(itemBean.getItemId()))
						criteria.add(Restrictions.eq("itemId", itemBean.getItemId()).ignoreCase());
						
					if(MisUtility.ifEmpty(itemBean.getItemName()))
						criteria.add(Restrictions.eq("itemName", itemBean.getItemName()).ignoreCase());
					
					if(MisUtility.ifEmpty(itemBean.getUnitOfMeasurement()))
						criteria.add(Restrictions.eq("unitOfMeasurement.measurementId", itemBean.getUnitOfMeasurement().getMeasurementId()));
					
					if(MisUtility.ifEmpty(itemBean.getStore()))
						criteria.add(Restrictions.eq("store.storeId", itemBean.getStore().getStoreId()));
					
					if(MisUtility.ifEmpty(itemBean.getServiceable()))
						criteria.add(Restrictions.eq("serviceable", itemBean.getServiceable()));					
					
					if(MisUtility.ifEmpty(itemBean.getItemGroup()))
						criteria.add(Restrictions.eq("itemGroup.itemGroupId", itemBean.getItemGroup().getItemGroupId()));
					
					if(!MisUtility.ifEmpty(statusList))
						criteria.add(Restrictions.in("misAuditBean.status", statusList));					
					
					
				}
				
			itemBeans = getHibernateTemplate().findByCriteria(criteria);
			
			
		} catch (DataAccessException e) {
			throw e;
		}
		
		return itemBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemBean> findItem(List<String> itemIds) throws DataAccessException {
		List<ItemBean> itemBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ItemBean.class);
				if(!MisUtility.ifEmpty(itemIds)){
					
					criteria.add(Restrictions.in("itemId",itemIds));
					itemBeans = getHibernateTemplate().findByCriteria(criteria);
					
				}		
			
		} catch (DataAccessException e) {
			throw e;
		}
		
		return itemBeans;
	}

	@Override
	public boolean saveItem(ItemBean itemBean) throws DataAccessException {
		
		
		try {
			getHibernateTemplate().save(itemBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateItem(ItemBean itemBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(itemBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateItem(List<ItemBean> itemBeans) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(itemBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ItemBean> getDistinctItemCodes(String itemGroupId, StoreBean store) throws DataAccessException {
		Set<ItemBean> itemBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ItemBean.class);
				if(MisUtility.ifEmpty(itemGroupId))
					criteria.add(Restrictions.eq("itemGroup.itemGroupId", itemGroupId).ignoreCase());
				if(MisUtility.ifEmpty(store))
					criteria.add(Restrictions.eq("store.storeId", store.getStoreId()));
//				criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));				
				criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_VERIFIED));
				
			itemBeans = new TreeSet<ItemBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}
		
		return itemBeans;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ItemBean> getItemList( String itemGroup)
			throws DataAccessException {
		DetachedCriteria criteria = DetachedCriteria.forClass(ItemBean.class);
		List<ItemBean> itemBeans=null;
		if(MisUtility.ifEmpty(itemGroup))
		{
			criteria.add(Restrictions.eqProperty("itemGroup", itemGroup));
		}
		try
		{
			
			itemBeans= getHibernateTemplate().findByCriteria(criteria);
		}
		catch(DataAccessException e)
		{
			throw e;
		}
		return itemBeans;
	}


}
