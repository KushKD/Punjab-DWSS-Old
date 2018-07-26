package com.prwss.mis.ccdu.iecactivity.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class IECActivityMaterialUtilizationDaoImpl extends HibernateDaoSupport implements
		IECActivityMaterialUtilizationDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<IECActivityMaterialUtilizationBean> findCBIECActivityMaterialUtilization(
			IECActivityMaterialUtilizationBean iecActivityMaterialUtilizationBean, List<String> statusList)
			throws DataAccessException {
		List<IECActivityMaterialUtilizationBean> iecActivityMaterialUtilizationBeans = null;
		try {
			if(MisUtility.ifEmpty(iecActivityMaterialUtilizationBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(IECActivityMaterialUtilizationBean.class);
				if(MisUtility.ifEmpty(iecActivityMaterialUtilizationBean.getIecProgressId()))
					criteria.add(Restrictions.eq("iecProgressId", iecActivityMaterialUtilizationBean.getIecProgressId()).ignoreCase());
				
				iecActivityMaterialUtilizationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}		
		
		return iecActivityMaterialUtilizationBeans;
	}

	@Override
	public boolean saveOrUpdateCBIECActivityMaterialUtilization(
			Collection<IECActivityMaterialUtilizationBean> iecActivityMaterialUtilizationBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(iecActivityMaterialUtilizationBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

}
