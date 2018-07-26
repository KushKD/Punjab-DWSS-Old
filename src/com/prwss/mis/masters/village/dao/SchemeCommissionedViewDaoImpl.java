package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class SchemeCommissionedViewDaoImpl extends HibernateDaoSupport implements SchemeCommissionedViewDao{
	@Override
	@SuppressWarnings("unchecked")
	public List<SchemeCommissionedViewBean> findVillageSchemeFromView1(
			SchemeCommissionedViewBean villageSchemeViewBean1, List<String> statusList)
			throws DataAccessException {
	System.out.println("==============DAO===Rohit==================="+villageSchemeViewBean1.getLocationId());
		List<SchemeCommissionedViewBean> villageSchemeViewBeans = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SchemeCommissionedViewBean.class);
		if(MisUtility.ifEmpty(villageSchemeViewBean1)){
			if(MisUtility.ifEmpty(villageSchemeViewBean1.getLocationId()))
				criteria.add(Restrictions.eq("locationId", villageSchemeViewBean1.getLocationId()));
				villageSchemeViewBeans = getHibernateTemplate().findByCriteria(criteria);
				}
		return villageSchemeViewBeans;
	}
}
