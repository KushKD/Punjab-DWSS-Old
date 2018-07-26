/**
 * 
 */
package com.prwss.mis.pmm.dsrsewerage.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.dsrsewerage.DSRSewerageBean;

/**
 * @author pjha
 *
 */
public class DSRSewerageDaoImpl extends HibernateDaoSupport implements DSRSewerageDao {
	private Logger log = Logger.getLogger(DSRSewerageDaoImpl.class);
	

	@Override
	public String saveDSRSewerage(DSRSewerageBean dsrSewerageBean)
			throws DataAccessException {
		String schemeId = null;
		try {
			schemeId=(String)getHibernateTemplate().save(dsrSewerageBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return schemeId;
	}

	@Override
	public boolean updateDSRSewerage(DSRSewerageBean dsrSewerageBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(dsrSewerageBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateDSRSewerage(List<DSRSewerageBean> dsrSewerageBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(dsrSewerageBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DSRSewerageBean> findDSRSewerage(
			DSRSewerageBean dsrSewerageBean, List<String> statusList)
			throws DataAccessException {
		List<DSRSewerageBean> dsrSewerageBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DSRSewerageBean.class);
			if(MisUtility.ifEmpty(dsrSewerageBean)){
				if(MisUtility.ifEmpty(dsrSewerageBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", dsrSewerageBean.getSchemeId()));

				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
			}
			log.debug(criteria);
			dsrSewerageBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}

		return dsrSewerageBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DSRSewerageBean> findDSRSewerage(List<String> schemeId)
			throws DataAccessException {
		List<DSRSewerageBean> dsrSewerageBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DSRSewerageBean.class);

			if(!MisUtility.ifEmpty(schemeId))
				criteria.add(Restrictions.in("schemeId", schemeId));

			log.debug(criteria);
			dsrSewerageBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}

		return dsrSewerageBeans;
	}

}
