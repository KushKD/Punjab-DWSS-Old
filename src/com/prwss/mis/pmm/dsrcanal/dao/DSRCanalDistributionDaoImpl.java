/**
 * 
 */
package com.prwss.mis.pmm.dsrcanal.dao;

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
public class DSRCanalDistributionDaoImpl extends HibernateDaoSupport implements DSRCanalDistributionDao {
	private Logger log = Logger.getLogger(DSRCanalDistributionDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<DSRCanalDistributionBean> findDSRCanalDistribution(
			DSRCanalDistributionBean dsrCanalDistributionBean,
			List<String> statusList) throws DataAccessException {
		List<DSRCanalDistributionBean> dsrCanalDistributionBeans = null;

		try {
			if(MisUtility.ifEmpty(dsrCanalDistributionBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(DSRCanalDistributionBean.class);
				if(MisUtility.ifEmpty(dsrCanalDistributionBean.getId()))
					criteria.add(Restrictions.eq("id", dsrCanalDistributionBean.getId()));
				if(MisUtility.ifEmpty(dsrCanalDistributionBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", dsrCanalDistributionBean.getSchemeId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));

				dsrCanalDistributionBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return dsrCanalDistributionBeans;
	}

	@Override
	public boolean saveOrUpdateDSRCanalDistribution(
			Collection<DSRCanalDistributionBean> dsrCanalDistributionBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(dsrCanalDistributionBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

}
