/**
 * 
 */
package com.prwss.mis.finance.loc.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

/**
 * @author PJHA
 *
 */
public class LOCDetailDaoImpl  extends HibernateDaoSupport implements LOCDetailDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<LOCDetailBean> findLOCtDetails(LOCDetailBean locDetailBean,
			List<String> statusList) throws DataAccessException {
		List<LOCDetailBean> locDetailBeans = null;
		
		try {
			if(MisUtility.ifEmpty(locDetailBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(LOCDetailBean.class);
				if(MisUtility.ifEmpty(locDetailBean.getLocId()))
					criteria.add(Restrictions.eq("locId", locDetailBean.getLocId()));
				if(MisUtility.ifEmpty(locDetailBean.getId()))
					criteria.add(Restrictions.eq("id", locDetailBean.getId()));
				if(MisUtility.ifEmpty(locDetailBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", locDetailBean.getSchemeId()));				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));

				locDetailBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return locDetailBeans;
	}

	@Override
	public boolean saveLOCDetails(Collection<LOCDetailBean> locDetailBeans)
			throws DataAccessException {
		try {

			getHibernateTemplate().saveOrUpdateAll(locDetailBeans);

		} catch (DataAccessException e) {
			throw e;
		}

		return true;
	}

	@Override
	public boolean updateLOCDetails(Collection<LOCDetailBean> locDetailBeans)
			throws DataAccessException {
		try {

			getHibernateTemplate().saveOrUpdateAll(locDetailBeans);

		} catch (DataAccessException e) {
			throw e;
		}

		return true;
	}

}
