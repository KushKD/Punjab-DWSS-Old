package com.prwss.mis.masters.village.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class PrwssVillageViewWithMhOhDaoImpl extends HibernateDaoSupport implements PrwssVillageViewWithMhOhDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<PrwssVillageViewBeanWithMhOh> find3016Villages(String villageId)
			throws DataAccessException {
		List<PrwssVillageViewBeanWithMhOh> beanWithMhOhs = new ArrayList<PrwssVillageViewBeanWithMhOh>();
		try {
			System.out.println("Village id in dao -----"+villageId);
			DetachedCriteria criteria = DetachedCriteria.forClass(PrwssVillageViewBeanWithMhOh.class);
			 if(MisUtility.ifEmpty(villageId)){
				 	
				 System.out.println("uppppppppppp");
				 criteria.add(Restrictions.eq("villageId", villageId));
				 System.out.println("bottammmmmmmmmmmmm");
			 }
			beanWithMhOhs = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			 throw e;
		}
		 System.out.println("---------------------------------------------");
		 System.out.println(beanWithMhOhs);
		 return beanWithMhOhs;
	}

}
