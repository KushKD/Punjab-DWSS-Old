package com.prwss.mis.masters.constituency.dao;

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

public class ConstituencyDaoImpl extends HibernateDaoSupport implements ConstituencyDao {

	private Logger log = Logger.getLogger(ConstituencyDaoImpl.class);

	@Override
	@SuppressWarnings("unchecked")
	public Set<ConstituencyBean> findConstituency(String districtId)
			throws DataAccessException {
		Set<ConstituencyBean> constituencyBeans = null;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ConstituencyBean.class);
				if(MisUtility.ifEmpty(districtId))					
					criteria.add(Restrictions.eq("districtId",districtId));
				
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
			
			constituencyBeans = new TreeSet<ConstituencyBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}
		
		return constituencyBeans;
	}

	@SuppressWarnings({"unchecked" })
	@Override
	public List<ConstituencyBean> findConstituency(ConstituencyBean constituencyBean, List<String> statusList)
			throws DataAccessException {
		List<ConstituencyBean> constituencyBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ConstituencyBean.class);
			if(MisUtility.ifEmpty(constituencyBean)){			
				if(MisUtility.ifEmpty(constituencyBean.getConstituencyId())){
					System.out.println("--------ConstituencyId"+constituencyBean.getConstituencyId());
					criteria.add(Restrictions.eq("constituencyId", constituencyBean.getConstituencyId()));
				}
				if(MisUtility.ifEmpty(constituencyBean.getConstituencyName()))
					criteria.add(Restrictions.eq("constituencyName", constituencyBean.getConstituencyName()));
				if(MisUtility.ifEmpty(constituencyBean.getDistrictId()) && MisUtility.ifEmpty(constituencyBean.getDistrictId())){
					System.out.println("-------------DistrictId"+constituencyBean.getDistrictId());
					criteria.add(Restrictions.eq("districtId", constituencyBean.getDistrictId()));
				}
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				criteria.addOrder(Order.asc("districtId"));
				constituencyBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return constituencyBeans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConstituencyBean> findConstituency(List<String> constituencyIds) throws DataAccessException {
		List<ConstituencyBean> constituencyBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ConstituencyBean.class);
			
			if(!MisUtility.ifEmpty(constituencyIds)){
				criteria.add(Restrictions.in("constituencyId", constituencyIds));
				constituencyBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}		
		
		return constituencyBeans;
	}

	
	@Override
	public boolean saveConstituency(ConstituencyBean constituencyBean) throws DataAccessException {
		
		try {
			getHibernateTemplate().save(constituencyBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateConstituency(ConstituencyBean constituencyBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(constituencyBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateConstituency(List<ConstituencyBean> constituencyBeans) throws DataAccessException {
		
		try {
			getHibernateTemplate().saveOrUpdateAll(constituencyBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ConstituencyBean> getDistinctConstituencyIds() throws DataAccessException {
		Set<ConstituencyBean> constituencyBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ConstituencyBean.class);
			
			constituencyBeans = new TreeSet<ConstituencyBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return constituencyBeans;
	}

}
