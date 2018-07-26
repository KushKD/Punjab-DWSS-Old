/**
 * 
 */
package com.prwss.mis.pmm.dsrtubewell.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

/**
 * @author pjha
 *
 */
public class DSRTubeWellDistributionDaoImpl extends HibernateDaoSupport implements DSRTubeWellDistributionDao  {
	private Logger log = Logger.getLogger(DSRTubeWellDistributionDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<DSRTubeWellDistributionBean> findDSRTubeWellDistribution(
			DSRTubeWellDistributionBean dsrTubeWellDistributionBean,
			List<String> statusList) throws DataAccessException {
		List<DSRTubeWellDistributionBean> dsrTubeWellDistributionBeans = null;

		try {
			if(MisUtility.ifEmpty(dsrTubeWellDistributionBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(DSRTubeWellDistributionBean.class);
				if(MisUtility.ifEmpty(dsrTubeWellDistributionBean.getId()))
					criteria.add(Restrictions.eq("id", dsrTubeWellDistributionBean.getId()));
				if(MisUtility.ifEmpty(dsrTubeWellDistributionBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", dsrTubeWellDistributionBean.getSchemeId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));

				dsrTubeWellDistributionBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return dsrTubeWellDistributionBeans;
	}

	@Override
	public boolean saveOrUpdateDSRTubeWellDistribution(
			Collection<DSRTubeWellDistributionBean> dsrTubeWellDistributionBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(dsrTubeWellDistributionBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

}
