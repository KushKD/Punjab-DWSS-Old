package com.prwss.mis.admin.divisional.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class SubDivisionalDaoImpl extends HibernateDaoSupport implements SubDivisionalDao {
	
	private Logger log = Logger.getLogger(SubDivisionalDaoImpl.class);

	@SuppressWarnings({"unchecked" })
	@Override
	public List<SubDivisionalBean> findSubDivisional(SubDivisionalBean subdivisionalBean, List<String> statusList)
			throws DataAccessException {
		List<SubDivisionalBean> subdivisionalBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SubDivisionalBean.class);
			if(MisUtility.ifEmpty(subdivisionalBean)){			
				if(MisUtility.ifEmpty(subdivisionalBean.getSubdivisionId())){
					System.out.println("--------SubDivisionalId"+subdivisionalBean.getSubdivisionId());
					criteria.add(Restrictions.eq("subdivisionId", subdivisionalBean.getSubdivisionId()));
				}
				if(MisUtility.ifEmpty(subdivisionalBean.getSubdivisionName()))
					criteria.add(Restrictions.eq("subdivisionName", subdivisionalBean.getSubdivisionName()));
				if(MisUtility.ifEmpty(subdivisionalBean.getDivision()) && MisUtility.ifEmpty(subdivisionalBean.getDivision().getDivisionalId())){
					System.out.println("-------------CircleId"+subdivisionalBean.getDivision());
					criteria.add(Restrictions.eq("division.divisionalId", subdivisionalBean.getDivision().getDivisionalId()));
				}
					if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("status", statusList));
				
				subdivisionalBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
			throw e;
		}
		
		return subdivisionalBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubDivisionalBean> findSubDivisional(List<String> subdivisionalIds) throws DataAccessException {
		List<SubDivisionalBean> subdivisionalBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SubDivisionalBean.class);
			
			if(!MisUtility.ifEmpty(subdivisionalIds)){
				criteria.add(Restrictions.in("subdivisionalId", subdivisionalIds));
				subdivisionalBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}		
		
		return subdivisionalBeans;
	}

	@Override
	public boolean saveSubDivisional(SubDivisionalBean subdivisionalBean) throws DataAccessException {
		
		try {
			getHibernateTemplate().save(subdivisionalBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateSubDivisional(SubDivisionalBean subdivisionalBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(subdivisionalBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateSubDivisional(List<SubDivisionalBean> subdivisionalBeans) throws DataAccessException {
		
		try {
			getHibernateTemplate().saveOrUpdateAll(subdivisionalBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<SubDivisionalBean> getDistinctSubDivisionalCodes() throws DataAccessException {
		Set<SubDivisionalBean> subdivisionalBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SubDivisionalBean.class);
			
			subdivisionalBeans = new TreeSet<SubDivisionalBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return subdivisionalBeans;
	}

}
