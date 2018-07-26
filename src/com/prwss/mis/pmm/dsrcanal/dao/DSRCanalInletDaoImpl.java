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
public class DSRCanalInletDaoImpl extends HibernateDaoSupport implements DSRCanalInletDao {
	private Logger log = Logger.getLogger(DSRCanalInletDaoImpl.class);
	@Override
	@SuppressWarnings("unchecked")
	public List<DSRCanalInletBean> findDSRCanalDistribution(
			DSRCanalInletBean dsrCanalInletBean, List<String> statusList)
			throws DataAccessException {
		List<DSRCanalInletBean> dsrCanalInletBeans = null;

		try {
			if(MisUtility.ifEmpty(dsrCanalInletBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(DSRCanalInletBean.class);
				if(MisUtility.ifEmpty(dsrCanalInletBean.getId()))
					criteria.add(Restrictions.eq("id", dsrCanalInletBean.getId()));
				if(MisUtility.ifEmpty(dsrCanalInletBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", dsrCanalInletBean.getSchemeId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));

				dsrCanalInletBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return dsrCanalInletBeans;
	}

	@Override
	public boolean saveOrUpdateDSRCanalDistribution(
			Collection<DSRCanalInletBean> dsrCanalInletBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(dsrCanalInletBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

}
