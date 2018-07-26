package com.prwss.mis.masters.district.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class DistrictDaoImpl extends HibernateDaoSupport implements DistrictDao {
	
	private Logger log = Logger.getLogger(DistrictDaoImpl.class);

	@SuppressWarnings({"unchecked" })
	@Override
	public List<DistrictBean> findDistrict(DistrictBean districtBean, List<String> statusList)
			throws DataAccessException {
		List<DistrictBean> districtBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DistrictBean.class);
			if(MisUtility.ifEmpty(districtBean)){			
				if(MisUtility.ifEmpty(districtBean.getDistrictId())){
					System.out.println("--------DistrictId"+districtBean.getDistrictId());
					criteria.add(Restrictions.eq("districtId", districtBean.getDistrictId()));
				}
				if(MisUtility.ifEmpty(districtBean.getDistrictName()))
					criteria.add(Restrictions.eq("districtName", districtBean.getDistrictName()));
				if(MisUtility.ifEmpty(districtBean.getCircle()) && MisUtility.ifEmpty(districtBean.getCircle().getCircleId())){
					System.out.println("-------------CircleId"+districtBean.getCircle());
					criteria.add(Restrictions.eq("circle.circleId", districtBean.getCircle().getCircleId()));
				}
					if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("status", statusList));
					
				criteria.addOrder(Order.asc("districtId"));

				districtBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return districtBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DistrictBean> findDistrict(List<String> districtIds) throws DataAccessException {
		List<DistrictBean> districtBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DistrictBean.class);
			
			if(!MisUtility.ifEmpty(districtIds)){
				criteria.add(Restrictions.in("districtId", districtIds));
				districtBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}		
		
		return districtBeans;
	}

	@Override
	public boolean saveDistrict(DistrictBean districtBean) throws DataAccessException {
		
		try {
			getHibernateTemplate().save(districtBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateDistrict(DistrictBean districtBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(districtBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateDistrict(List<DistrictBean> districtBeans) throws DataAccessException {
		
		try {
			getHibernateTemplate().saveOrUpdateAll(districtBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<DistrictBean> getDistinctDistrictCodes(List<String> statusList) throws DataAccessException {
		Set<DistrictBean> districtBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DistrictBean.class);
			criteria.add(Restrictions.in("status", statusList));
			districtBeans = new TreeSet<DistrictBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return districtBeans;
	}

}
