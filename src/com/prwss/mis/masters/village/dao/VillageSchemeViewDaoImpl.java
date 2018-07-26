package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class VillageSchemeViewDaoImpl extends HibernateDaoSupport implements VillageSchemeViewDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<VillageSchemeViewBean> findVillageSchemeFromView(
			VillageSchemeViewBean villageSchemeViewBean, List<String> statusList)
			throws DataAccessException {
//		System.out.println("==============DAO======================");
		List<VillageSchemeViewBean> villageSchemeViewBeans = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(VillageSchemeViewBean.class);
		if(MisUtility.ifEmpty(villageSchemeViewBean)){
			if(MisUtility.ifEmpty(villageSchemeViewBean.getLocationId()))
				criteria.add(Restrictions.eq("locationId", villageSchemeViewBean.getLocationId()));
		
			if(MisUtility.ifEmpty(villageSchemeViewBean.getVillageId()))
				criteria.add(Restrictions.eq("villageId", villageSchemeViewBean.getVillageId()));
			
			if(MisUtility.ifEmpty(villageSchemeViewBean.getProgramId()))
				criteria.add(Restrictions.eq("programId", villageSchemeViewBean.getProgramId()));
			
			if(MisUtility.ifEmpty(villageSchemeViewBean.getSchemeId()))
				criteria.add(Restrictions.eq("schemeId", villageSchemeViewBean.getSchemeId()));
			
			if(MisUtility.ifEmpty(villageSchemeViewBean.getSchemeSource()))
				criteria.add(Restrictions.eq("schemeSource", villageSchemeViewBean.getSchemeSource()));
			
			if(!MisUtility.ifEmpty(statusList))
				criteria.add(Restrictions.in("status", statusList));
			
			villageSchemeViewBeans = getHibernateTemplate().findByCriteria(criteria);	
		}
//		System.out.println(villageSchemeViewBeans);
		return villageSchemeViewBeans;
		
	}
}
