/**
 * 
 */
package com.prwss.mis.pmm.dsrsewerage.dao;

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
public class DSRSewerageSewerDaoImpl extends HibernateDaoSupport implements DSRSewerageSewerDao{
	private Logger log = Logger.getLogger(DSRSewerageSewerDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<DSRSewerageSewerBean> findDSRCanalDistribution(
			DSRSewerageSewerBean dsrSewerageSewerBean, List<String> statusList)
			throws DataAccessException {
		List<DSRSewerageSewerBean> dsrSewerageSewerBeans = null;

		try {
			if(MisUtility.ifEmpty(dsrSewerageSewerBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(DSRSewerageSewerBean.class);
				if(MisUtility.ifEmpty(dsrSewerageSewerBean.getId()))
					criteria.add(Restrictions.eq("id", dsrSewerageSewerBean.getId()));
				if(MisUtility.ifEmpty(dsrSewerageSewerBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", dsrSewerageSewerBean.getSchemeId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));

				dsrSewerageSewerBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return dsrSewerageSewerBeans;
	}

	@Override
	public boolean saveOrUpdateDSRSewerageSewerBean(
			Collection<DSRSewerageSewerBean> dsrSewerageSewerBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(dsrSewerageSewerBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

}
