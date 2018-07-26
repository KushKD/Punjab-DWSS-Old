package com.prwss.mis.pmm.village.sewerage.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
/*
 * 
 * 
 *author : Akash 
 * 
 * */



public class VillageSewerageDaoImpl extends HibernateDaoSupport implements VillageSewerageDao {
	private Logger log = Logger.getLogger(VillageSewerageDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VillageSewerageBean> findVillageSewerage(
			VillageSewerageBean villageSewerageBean, List<String> statusList)
			throws DataAccessException {
		List<VillageSewerageBean> villageSewerageBeans = new ArrayList<VillageSewerageBean>() ;
		try {
			if(MisUtility.ifEmpty(villageSewerageBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageSewerageBean.class);
				if(MisUtility.ifEmpty(villageSewerageBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", villageSewerageBean.getVillageId()));
				if(MisUtility.ifEmpty(villageSewerageBean.getSewerageId()))
					criteria.add(Restrictions.eq("sewerageId", villageSewerageBean.getSewerageId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				villageSewerageBeans = getHibernateTemplate().findByCriteria(criteria);
				System.out.println("villageSewerageBeans from dao"+villageSewerageBeans);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return villageSewerageBeans;
	}

	@Override
	public boolean saveVillageSewerage(
			Collection<VillageSewerageBean> villageSewerageBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(villageSewerageBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return true;
	}

}
