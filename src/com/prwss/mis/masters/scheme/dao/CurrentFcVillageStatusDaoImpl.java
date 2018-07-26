package com.prwss.mis.masters.scheme.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class CurrentFcVillageStatusDaoImpl extends HibernateDaoSupport implements CurrentFcVillageStatusDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<CurrentFcVillageStatusBean> getVillageStatus(String villageId)
			throws DataAccessException {
		List<CurrentFcVillageStatusBean> currentFcVillageStatusBeans = new ArrayList<CurrentFcVillageStatusBean>();
		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(CurrentFcVillageStatusBean.class);
				if(MisUtility.ifEmpty(villageId)){
					criteria.add(Restrictions.eq("villageId", villageId));
				}
				currentFcVillageStatusBeans = getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			throw e;
		}
		 
		return currentFcVillageStatusBeans;
	}

}
