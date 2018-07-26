/**
 * 
 */
package com.prwss.mis.pmm.dsrtubewell.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.dsrtubewell.DSRTubeWellBean;

/**
 * @author pjha
 *
 */
public class DSRTubeWellDaoImpl extends HibernateDaoSupport implements DSRTubeWellDao {
	private Logger log = Logger.getLogger(DSRTubeWellDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<DSRTubeWellBean> findDSRTubeWell(
			DSRTubeWellBean dsrTubeWellBean, List<String> statusList)
			throws DataAccessException {
		List<DSRTubeWellBean> dsrTubeWellBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DSRTubeWellBean.class);
			if(MisUtility.ifEmpty(dsrTubeWellBean)){
				if(MisUtility.ifEmpty(dsrTubeWellBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", dsrTubeWellBean.getSchemeId()));

				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
			}
			log.debug(criteria);
			dsrTubeWellBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}

		return dsrTubeWellBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DSRTubeWellBean> findDSRTubeWell(List<String> schemeId)
			throws DataAccessException {
		List<DSRTubeWellBean> dsrTubeWellBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(DSRTubeWellBean.class);

			if(!MisUtility.ifEmpty(schemeId))
				criteria.add(Restrictions.in("schemeId", schemeId));

			log.debug(criteria);
			dsrTubeWellBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}

		return dsrTubeWellBeans;
	}


	@Override
	public String saveDSRTubeWell(DSRTubeWellBean dsrTubeWellBean)
			throws DataAccessException {
		String schemeId = null;
		try {
			schemeId=(String)getHibernateTemplate().save(dsrTubeWellBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return schemeId;
	}

	@Override
	public boolean updateDSRTubeWell(DSRTubeWellBean dsrTubeWellBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(dsrTubeWellBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateDSRTubeWell(List<DSRTubeWellBean> dsrTubeWellBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(dsrTubeWellBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}
}
