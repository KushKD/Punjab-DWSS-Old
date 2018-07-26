package com.prwss.mis.masters.village.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class PrwssVillageViewDaoImpl extends HibernateDaoSupport implements PrwssVillageViewDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<PrwssVillageViewBean> findPrwssVillageFromView(VillageBean villageBean) throws DataAccessException {
		List<PrwssVillageViewBean> prwssVillageViewBeans = null;

		try {
		//	System.out.println("hhhhhhhhhhhhhhhhhhhhh");
			DetachedCriteria criteria = DetachedCriteria.forClass(PrwssVillageViewBean.class);
			if(MisUtility.ifEmpty(villageBean)){

				if(MisUtility.ifEmpty(villageBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", villageBean.getVillageId()));
				
				if(MisUtility.ifEmpty(villageBean.getDistrictId()))
					criteria.add(Restrictions.eq("districtId",villageBean.getDistrictId()));
				
				if(MisUtility.ifEmpty(villageBean.getBlockId()))
					criteria.add(Restrictions.eq("blockId",villageBean.getBlockId()));
				
				if(MisUtility.ifEmpty(villageBean.getVillageName()))
					criteria.add(Restrictions.eq("villageName",villageBean.getVillageName()));				
			}
			prwssVillageViewBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
	//	System.out.println("daovillage"+prwssVillageViewBeans);
		return prwssVillageViewBeans;
	}

	 

}
