package com.prwss.mis.masters.village.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class VillageDaoImpl extends HibernateDaoSupport implements VillageDao {
	
	private Logger log = Logger.getLogger(VillageDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Set<VillageBean> getVillageIds(String blockId) throws DataAccessException {
		Set<VillageBean> villageBeans = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(VillageBean.class);
		criteria.add(Restrictions.eq("blockBean.blockId", blockId).ignoreCase());
		//criteria.add(Restrictions.eq("blockId", blockId).ignoreCase());
		criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
		log.debug(criteria);
		villageBeans =new TreeSet<VillageBean>(getHibernateTemplate().findByCriteria(criteria));
		
		return villageBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VillageBean> findVillage(VillageBean villageBean,
			List<String> statusList) throws DataAccessException {

		List<VillageBean> villageBeans = null;

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(VillageBean.class);
			if(MisUtility.ifEmpty(villageBean)){

				if(MisUtility.ifEmpty(villageBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", villageBean.getVillageId()));
				
				if(MisUtility.ifEmpty(villageBean.getDistrictId()))
					criteria.add(Restrictions.eq("districtId",villageBean.getDistrictId()));
				
				if(MisUtility.ifEmpty(villageBean.getBlockId()))
					criteria.add(Restrictions.eq("blockId",villageBean.getBlockId()));
				
				if(MisUtility.ifEmpty(villageBean.getVillageName()))
					criteria.add(Restrictions.eq("villageName",villageBean.getVillageName()));
				
				if(MisUtility.ifEmpty(villageBean.getHabitationType()))
					criteria.add(Restrictions.eq("habitationType",villageBean.getHabitationType()));		
		
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
			}
			criteria.addOrder(Order.asc("villageName"));
			villageBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return villageBeans;
		}

	@Override
	public String saveVillage(VillageBean villageBean)
			throws DataAccessException {
		String villageId = null;
		try {
			villageId = (String)getHibernateTemplate().save(villageBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return villageId;
	}

	@Override
	public boolean updateVillage(VillageBean villageBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(villageBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<VillageBean> getVillageOnHabitationType(String habitationType, String blockId)
			throws DataAccessException {
			List<VillageBean> villageBeans = null;
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			try {
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageBean.class);
				if(MisUtility.ifEmpty(habitationType)){
				criteria.add(Restrictions.eq("habitationType", habitationType).ignoreCase());
				criteria.add(Restrictions.eq("blockId", blockId));
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
				villageBeans =getHibernateTemplate().findByCriteria(criteria);
				}
			} catch (DataAccessException e) {
				e.printStackTrace();
				throw e;
			}
			return villageBeans;
		}

	@SuppressWarnings("unchecked")
	@Override
	public List<VillageBean> findVillage1(VillageBean villageBean,
			List<String> statusList) throws DataAccessException {

		List<VillageBean> villageBeans = null;

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(VillageBean.class);
			if(MisUtility.ifEmpty(villageBean)){

				if(MisUtility.ifEmpty(villageBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", villageBean.getVillageId()));
				
				if(MisUtility.ifEmpty(villageBean.getDistrictId()))
					criteria.add(Restrictions.eq("districtId",villageBean.getDistrictId()));
				
				if(MisUtility.ifEmpty(villageBean.getBlockId())){
					criteria.add(Restrictions.eq("blockId",villageBean.getBlockId()));
					criteria.add(Restrictions.isNull("spl_flags"));
					
				}
				
				if(MisUtility.ifEmpty(villageBean.getVillageName()))
					criteria.add(Restrictions.eq("villageName",villageBean.getVillageName()));
				
				if(MisUtility.ifEmpty(villageBean.getHabitationType()))
					criteria.add(Restrictions.eq("habitationType",villageBean.getHabitationType()));		
		
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
			}
			criteria.addOrder(Order.asc("villageName"));
			villageBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return villageBeans;
		}

	

}
