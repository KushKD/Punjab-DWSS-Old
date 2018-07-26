/**
 * 
 */
package com.prwss.mis.pmm.environment.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.environment.EnvironmentBean;

/**
 * @author pjha
 *
 */
public class EnvironmentDaoImpl extends HibernateDaoSupport implements EnvironmentDao {
	private Logger log = Logger.getLogger(EnvironmentDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<EnvironmentBean> findEnvironmentBean(
			EnvironmentBean environmentBean, List<String> statusList)
			throws DataAccessException {
		List<EnvironmentBean> environmentBeans = new  ArrayList<EnvironmentBean>();
		try {
			if(MisUtility.ifEmpty(environmentBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(EnvironmentBean.class);
				if(MisUtility.ifEmpty(environmentBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId",environmentBean.getLocationId()));
				if(MisUtility.ifEmpty(environmentBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId",environmentBean.getVillageId()));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				environmentBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return environmentBeans;
	}

	@Override
	public boolean saveEnvironmentBean(EnvironmentBean environmentBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(environmentBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveOrUpdateEnvironmentBean(EnvironmentBean environmentBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(environmentBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

}
