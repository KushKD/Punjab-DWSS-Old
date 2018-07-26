package com.prwss.mis.masters.iecmaterial.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class IECMaterialDaoImpl extends HibernateDaoSupport implements IECMaterialDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<IECMaterialBean> findIECMaterial(IECMaterialBean iecMaterialBean, List<String> statusList)
			throws DataAccessException {
		List<IECMaterialBean> iecMaterialBeans = null;
		try {
			if(MisUtility.ifEmpty(iecMaterialBeans)){
				DetachedCriteria criteria = DetachedCriteria.forClass(IECMaterialBean.class);
				if(MisUtility.ifEmpty(iecMaterialBean.getIecMaterialId()))
					criteria.add(Restrictions.eq("iecMaterialId", iecMaterialBean.getIecMaterialId()).ignoreCase());
				if(MisUtility.ifEmpty(iecMaterialBean.getIecMaterialName()))
					criteria.add(Restrictions.eq("iecMaterialName", iecMaterialBean.getIecMaterialName()).ignoreCase());
				if(MisUtility.ifEmpty(iecMaterialBean.getIecMaterialDescription()))
					criteria.add(Restrictions.eq("iecMaterialDescription", iecMaterialBean.getIecMaterialDescription()).ignoreCase());
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("status", statusList));
				iecMaterialBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return iecMaterialBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IECMaterialBean> findIECMaterial(List<String> iecMaterialIds) throws DataAccessException {
		List<IECMaterialBean> iecMaterialBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(IECMaterialBean.class);
			if(!MisUtility.ifEmpty(iecMaterialIds))
				criteria.add(Restrictions.in("iecMaterialId", iecMaterialIds));
			iecMaterialBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		return iecMaterialBeans;
	}

	@Override
	public String saveIECMaterial(IECMaterialBean iecMaterialBean) throws DataAccessException {
		String iecMaterialId = null;
		try {
			iecMaterialId = (String)getHibernateTemplate().save(iecMaterialBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return iecMaterialId;
	}

	@Override
	public boolean updateIECMaterial(IECMaterialBean iecMaterialBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(iecMaterialBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateIECMaterial(List<IECMaterialBean> iecMaterialBeans) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(iecMaterialBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set<IECMaterialBean> getDistinctIECMaterialId() throws DataAccessException {
		Set<IECMaterialBean> iecMaterialBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(IECMaterialBean.class);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			if(!MisUtility.ifEmpty(statusList))
				criteria.add(Restrictions.in("status", statusList));
			iecMaterialBeans = new TreeSet(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}
		return iecMaterialBeans;
	}

}
