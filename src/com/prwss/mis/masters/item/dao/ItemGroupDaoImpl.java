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

public class ItemGroupDaoImpl extends HibernateDaoSupport implements ItemGroupDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemGroupBean> findItemGroup(ItemGroupBean itemGroupBean, List<String> statusList)
			throws DataAccessException {
		List<ItemGroupBean> itemGroupBeans = null;
		try {
			if(MisUtility.ifEmpty(itemGroupBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(ItemGroupBean.class);
				if(MisUtility.ifEmpty(itemGroupBean.getItemGroupId()))
					criteria.add(Restrictions.eq("itemGroupId", itemGroupBean.getItemGroupId()).ignoreCase());
				if(MisUtility.ifEmpty(itemGroupBean.getItemGroupName()))
					criteria.add(Restrictions.eq("itemGroupName", itemGroupBean.getItemGroupName()).ignoreCase());
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				itemGroupBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return itemGroupBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemGroupBean> findItemGroup(List<String> itemGroupIds) throws DataAccessException {
		List<ItemGroupBean> itemGroupBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ItemGroupBean.class);
			if(!MisUtility.ifEmpty(itemGroupIds))
				criteria.add(Restrictions.in("itemGroupId", itemGroupIds));

			itemGroupBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}

		return itemGroupBeans;
	}

	@Override
	public boolean saveItemGroup(ItemGroupBean itemGroupBean) throws DataAccessException {
		boolean status=false;
		
		try {
			 getHibernateTemplate().save(itemGroupBean);
			 status=true;
		} catch (DataAccessException e) {
			throw e;
		}
		
		return status;
	}

	@Override
	public boolean updateItemGroup(ItemGroupBean itemGroupBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(itemGroupBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateItemGroup(List<ItemGroupBean> itemGroupBeans) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(itemGroupBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ItemGroupBean> getDistinctItemGroupCodes() throws DataAccessException {
		Set<ItemGroupBean> itemGroupBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ItemGroupBean.class);
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));

			itemGroupBeans = new TreeSet<ItemGroupBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}

		return itemGroupBeans;
	}

}
