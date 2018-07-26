package com.prwss.mis.masters.zone.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class ZoneDaoImpl extends HibernateDaoSupport implements ZoneDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ZoneBean> findZone(ZoneBean zoneBean, List<String> statusList) throws DataAccessException {
		List<ZoneBean> zoneBeans =null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ZoneBean.class);
			if(MisUtility.ifEmpty(zoneBean.getZoneId()))
				criteria.add(Restrictions.eq("zoneId", zoneBean.getZoneId()).ignoreCase());
			if(MisUtility.ifEmpty(zoneBean.getZoneName()))
				criteria.add(Restrictions.eq("zoneName", zoneBean.getZoneName()).ignoreCase());
			if(!MisUtility.ifEmpty(statusList))
				criteria.add(Restrictions.in("status", statusList));
			
			zoneBeans =  getHibernateTemplate().findByCriteria(criteria);
			System.out.println("---------Size "+zoneBeans.size());
		} catch (DataAccessException e) {
			throw e;
		}
		
		return zoneBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZoneBean> findZone(List<String> zoneIds) throws DataAccessException {
		List<ZoneBean> zoneBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ZoneBean.class);
			if(!MisUtility.ifEmpty(zoneIds))
			criteria.add(Restrictions.in("zoneId", zoneIds));
			
			zoneBeans =  getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return zoneBeans;
	}

	@Override
	public String saveZone(ZoneBean zoneBean) throws DataAccessException {
		String zoneId = null;
		try {
			zoneId = (String)getHibernateTemplate().save(zoneBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return zoneId;
	}

	@Override
	public boolean updateZone(ZoneBean zoneBean) throws DataAccessException {

		try {
			getHibernateTemplate().update(zoneBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateZone(List<ZoneBean> zoneBeans) throws DataAccessException {
		
		try {
			getHibernateTemplate().saveOrUpdateAll(zoneBeans);
		} catch (DataAccessException e) {
			 throw e;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ZoneBean> getDistinctZoneCodes() throws DataAccessException {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(ZoneBean.class);
		Set<ZoneBean> zones = new TreeSet<ZoneBean>(getHibernateTemplate().findByCriteria(criteria));
		
		return zones;
	}

}
