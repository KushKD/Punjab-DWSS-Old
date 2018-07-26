package com.prwss.mis.pmm.village.household.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class VillageHouseHoldDaoImpl extends HibernateDaoSupport implements VillageHouseHoldDao {
	private Logger log = Logger.getLogger(VillageHouseHoldDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<VillageHouseHoldBean> findVillageHouseHold(VillageHouseHoldBean villageHouseHoldBean,
			List<String> statusList) throws DataAccessException {
		List<VillageHouseHoldBean> villageHouseHoldBeans = new ArrayList<VillageHouseHoldBean>();
		try {
			if(MisUtility.ifEmpty(villageHouseHoldBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageHouseHoldBean.class);
				
				if(MisUtility.ifEmpty(villageHouseHoldBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", villageHouseHoldBean.getVillageId()));
				if(MisUtility.ifEmpty(villageHouseHoldBean.getHouseholdId()))
					criteria.add(Restrictions.eq("householdId", villageHouseHoldBean.getHouseholdId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				villageHouseHoldBeans = getHibernateTemplate().findByCriteria(criteria);
				System.out.println("Village Connect from Dao"+villageHouseHoldBeans);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return villageHouseHoldBeans;
	}

	@Override
	public boolean saveVillageHouseHold(Collection<VillageHouseHoldBean> villageHouseHoldBeans) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(villageHouseHoldBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveOrUpdateVillageHouseHold(Collection<VillageHouseHoldBean> villageHouseHoldBeans) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(villageHouseHoldBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

}
