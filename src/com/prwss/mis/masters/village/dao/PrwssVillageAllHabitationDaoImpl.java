package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class PrwssVillageAllHabitationDaoImpl extends HibernateDaoSupport implements PrwssVillageAllHabitationDao
{
	@SuppressWarnings("unchecked")
	@Override
	public List<PrwssVillageAllHabitationBean> findPrwssAllHabitationView(VillageBean villageBean) throws DataAccessException {
		List<PrwssVillageAllHabitationBean> prwssVillageAllHabitationBean = null;

		try {
			//System.out.println("rrrrrrrrrrrrrrrrrrrrrr");
			System.out.println("dao vill---"+villageBean.getVillageId());
			DetachedCriteria criteria = DetachedCriteria.forClass(PrwssVillageAllHabitationBean.class);
			if(MisUtility.ifEmpty(villageBean)){
					//System.out.println("ram ji");
				if(MisUtility.ifEmpty(villageBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", villageBean.getVillageId()));
				
				if(MisUtility.ifEmpty(villageBean.getDistrictId()))
					criteria.add(Restrictions.eq("districtId",villageBean.getDistrictId()));
				
				if(MisUtility.ifEmpty(villageBean.getBlockId()))
					criteria.add(Restrictions.eq("blockId",villageBean.getBlockId()));
				
				if(MisUtility.ifEmpty(villageBean.getVillageName()))
				criteria.add(Restrictions.eq("villageName",villageBean.getVillageName()));				
			}
			prwssVillageAllHabitationBean = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		System.out.println("dao"+prwssVillageAllHabitationBean);
		return prwssVillageAllHabitationBean;
	}

}
