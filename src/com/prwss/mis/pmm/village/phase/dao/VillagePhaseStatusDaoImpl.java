package com.prwss.mis.pmm.village.phase.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class VillagePhaseStatusDaoImpl extends HibernateDaoSupport implements VillagePhaseStatusDao {
	private Logger log = Logger.getLogger(VillagePhaseStatusDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<VillagePhaseStatusBean> findVillagePhaseStatus(VillagePhaseStatusBean villagePhaseStatusBean,
			List<String> statusList) throws DataAccessException {
		List<VillagePhaseStatusBean> villagePhaseStatusBeans = null;
		try {
			if(MisUtility.ifEmpty(villagePhaseStatusBeans)){
				DetachedCriteria criteria = DetachedCriteria.forClass(VillagePhaseStatusBean.class);
			
				if(MisUtility.ifEmpty(villagePhaseStatusBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", villagePhaseStatusBean.getSchemeId()));
				if(MisUtility.ifEmpty(villagePhaseStatusBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", villagePhaseStatusBean.getLocationId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				villagePhaseStatusBeans = getHibernateTemplate().findByCriteria(criteria);
				
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return villagePhaseStatusBeans;
	}

	@Override
	public boolean saveVillagePhaseStatus(VillagePhaseStatusBean villagePhaseStatusBean) throws DataAccessException {
		
		
		try {
			getHibernateTemplate().save(villagePhaseStatusBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateVillagePhaseStatus(VillagePhaseStatusBean villagePhaseStatusBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(villagePhaseStatusBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

}
