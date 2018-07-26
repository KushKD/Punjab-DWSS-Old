/**
 * 
 */
package com.prwss.mis.pmm.dsrcanal.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.dsrcanal.DSRCanalBean;

/**
 * @author pjha
 *
 */
public class DSRCanalDaoImpl extends HibernateDaoSupport implements DSRCanalDao {
	private Logger log = Logger.getLogger(DSRCanalDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<DSRCanalBean> findDSRCanal(DSRCanalBean dsrCanalBean,
			List<String> statusList) throws DataAccessException {
		List<DSRCanalBean> dsrCanalBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DSRCanalBean.class);
			if(MisUtility.ifEmpty(dsrCanalBean)){
				if(MisUtility.ifEmpty(dsrCanalBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", dsrCanalBean.getSchemeId()));
				if(MisUtility.ifEmpty(dsrCanalBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", dsrCanalBean.getLocationId()));

				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
			}
			log.debug(criteria);
			dsrCanalBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}

		return dsrCanalBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DSRCanalBean> findDSRCanal(List<String> schemeId)
			throws DataAccessException {
		List<DSRCanalBean> dsrCanalBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DSRCanalBean.class);
			if(!MisUtility.ifEmpty(schemeId))
				criteria.add(Restrictions.in("schemeId", schemeId));
			
			log.debug(criteria);
			dsrCanalBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}

		return dsrCanalBeans;
	}

	@Override
	public String saveDSRCanal(DSRCanalBean dsrCanalBean)
			throws DataAccessException {
		String schemeId = null;
		try {
			schemeId=(String)getHibernateTemplate().save(dsrCanalBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return schemeId;
	}

	@Override
	public boolean updateDSRCanal(DSRCanalBean dsrCanalBean)
			throws DataAccessException {
		try {
			 getHibernateTemplate().update(dsrCanalBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateDSRCanal(List<DSRCanalBean> dsrCanalBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(dsrCanalBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}
	
	

}
