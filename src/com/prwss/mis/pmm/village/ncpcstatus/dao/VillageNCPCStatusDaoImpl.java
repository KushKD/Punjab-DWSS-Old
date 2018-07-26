package com.prwss.mis.pmm.village.ncpcstatus.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.program.dao.ProgramBean;

public class VillageNCPCStatusDaoImpl extends HibernateDaoSupport implements VillageNCPCStatusDao {
	private Logger log = Logger.getLogger(VillageNCPCStatusDaoImpl.class);
	
	@Override
	@SuppressWarnings("unchecked")
	public List<VillageNCPCStatusBean> findVillageNCPCStatus(
			VillageNCPCStatusBean villageNCPCStatusBean, List<String> statusList)
			throws DataAccessException {
		List<VillageNCPCStatusBean> villageNCPCStatusBeans = new ArrayList<VillageNCPCStatusBean>() ;
		try {
			if(MisUtility.ifEmpty(villageNCPCStatusBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(VillageNCPCStatusBean.class);
				if(MisUtility.ifEmpty(villageNCPCStatusBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", villageNCPCStatusBean.getVillageId()));
				if(MisUtility.ifEmpty(villageNCPCStatusBean.getId()))
					criteria.add(Restrictions.eq("id", villageNCPCStatusBean.getId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				criteria.addOrder(Property.forName("id").desc());
				villageNCPCStatusBeans = getHibernateTemplate().findByCriteria(criteria);
				System.out.println("villageNCPCStatusBeans from dao"+villageNCPCStatusBeans);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return villageNCPCStatusBeans;
	}

	@Override
	public boolean saveVillageNCPCStatus(
			Collection<VillageNCPCStatusBean> villageNCPCStatusBeans)
			throws DataAccessException {
		try {
			 getHibernateTemplate().saveOrUpdateAll(villageNCPCStatusBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdateVillageNCPCStatus(
			Collection<VillageNCPCStatusBean> villageNCPCStatusBeans)
	throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(villageNCPCStatusBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public VillageNCPCStatusBean getVillNcpcStatusBeanById(String villageId)throws DataAccessException{
		List<VillageNCPCStatusBean> ncpcStatusBeans = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(VillageNCPCStatusBean.class);
		try{
			System.out.println("----check1-----------");
			criteria.add(Restrictions.eq("villageId", villageId));
			ncpcStatusBeans = getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e)
		{
			e.printStackTrace();
			throw e;
			
		}
		
		return ncpcStatusBeans.get(0);
	}
}
