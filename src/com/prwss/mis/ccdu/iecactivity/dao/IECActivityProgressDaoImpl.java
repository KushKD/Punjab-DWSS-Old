package com.prwss.mis.ccdu.iecactivity.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class IECActivityProgressDaoImpl extends HibernateDaoSupport implements IECActivityProgressDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<IECActivityProgressBean> findCBIECActivityProgress(IECActivityProgressBean iecActivityProgressBean,	List<String> statusList) 
			throws DataAccessException {
		List<IECActivityProgressBean> iecActivityProgressBeans = null;
		try {
			if(MisUtility.ifEmpty(iecActivityProgressBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(IECActivityProgressBean.class);
				if(MisUtility.ifEmpty(iecActivityProgressBean.getIecProgressId()))
					System.out.println("iecprogressid-----"+iecActivityProgressBean.getIecProgressId());
					criteria.add(Restrictions.eq("iecProgressId", iecActivityProgressBean.getIecProgressId()));
				if(MisUtility.ifEmpty(iecActivityProgressBean.getIecActivityBean()) && MisUtility.ifEmpty(iecActivityProgressBean.getIecActivityBean().getIecActivityId()))
					criteria.add(Restrictions.eq("iecActivityBean.iecActivityId", iecActivityProgressBean.getIecActivityBean().getIecActivityId()));
				if(MisUtility.ifEmpty(iecActivityProgressBean.getBlockId()))
					criteria.add(Restrictions.eq("blockId", iecActivityProgressBean.getBlockId()));
				if(MisUtility.ifEmpty(iecActivityProgressBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", iecActivityProgressBean.getLocationId()));
				if(MisUtility.ifEmpty(iecActivityProgressBean.getVenue()))
					criteria.add(Restrictions.eq("venue", iecActivityProgressBean.getVenue()));
				if(MisUtility.ifEmpty(iecActivityProgressBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", iecActivityProgressBean.getVillageId()));
				if(MisUtility.ifEmpty(iecActivityProgressBean.getActivityDate()))
					criteria.add(Restrictions.eq("activityDate", iecActivityProgressBean.getActivityDate()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				iecActivityProgressBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return iecActivityProgressBeans;
	}

	@Override
	public long saveCBIECActivityProgress(IECActivityProgressBean iecActivityProgressBean) throws DataAccessException {
		long iecActivityProgressId = 0;
		
		try {
			
			
			System.out.println("in iec dao+++++++++++++++++ isComplete"+iecActivityProgressBean.isComplete());
			
			System.out.println("in iec dao+++++++++++++++++"+iecActivityProgressBean);
			iecActivityProgressId = (Long)getHibernateTemplate().save(iecActivityProgressBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return iecActivityProgressId;
	}

	@Override
	public boolean updateCBIECActivityProgress(IECActivityProgressBean iecActivityProgressBean)
			throws DataAccessException {

		try {
			getHibernateTemplate().update(iecActivityProgressBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<IECActivityProgressBean> getCBIECActivityProgressIds(String villageId) throws DataAccessException {
		Set<IECActivityProgressBean> iecActivityProgressBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(IECActivityProgressBean.class);
			if(MisUtility.ifEmpty(villageId))
			criteria.add(Restrictions.eq("villageId", villageId).ignoreCase());
			iecActivityProgressBeans  = new TreeSet<IECActivityProgressBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}
		System.out.println("List is:"+iecActivityProgressBeans);
		return iecActivityProgressBeans;
	}

}
