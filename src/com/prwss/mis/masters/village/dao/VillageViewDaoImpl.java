package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class VillageViewDaoImpl extends HibernateDaoSupport implements VillageViewDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<VillageViewBean> findVillageFromView(VillageBean villageBean,
			List<String> statusList) throws DataAccessException {
		List<VillageViewBean> villageViewBeans = null;
		//System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"+villageBean.getParentHabitationId());
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(VillageViewBean.class);
			if(MisUtility.ifEmpty(villageBean)){
				//System.out.println("1010");
				if(MisUtility.ifEmpty(villageBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", villageBean.getVillageId()));
				
				if(MisUtility.ifEmpty(villageBean.getDistrictId()))
					criteria.add(Restrictions.eq("districtId",villageBean.getDistrictId()));
				
				if(MisUtility.ifEmpty(villageBean.getBlockId()))
					criteria.add(Restrictions.eq("blockId",villageBean.getBlockId()));
				
				if(MisUtility.ifEmpty(villageBean.getVillageName()))
					criteria.add(Restrictions.eq("villageName",villageBean.getVillageName()));
				
				if(MisUtility.ifEmpty(villageBean.getParentHabitationId())){
					//System.out.println("hello");
					criteria.add(Restrictions.eq("parentHabitationId",villageBean.getParentHabitationId()));
				}
				if(MisUtility.ifEmpty(villageBean.getHabitationType()))
					criteria.add(Restrictions.eq("habitationType",villageBean.getHabitationType()));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
			}
			villageViewBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
	//	System.out.println(villageViewBeans);
		return villageViewBeans;
	}

}
