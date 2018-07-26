package com.prwss.mis.tender.award.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class TenderObjectionDaoImpl extends HibernateDaoSupport implements TenderObjectionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TenderObjectionBean> getTenderObjectionBeans(TenderObjectionBean tenderObjectionBean, List<String> statusList)
			throws DataAccessException {
		List<TenderObjectionBean> tenderObjectionBeans = null;
		try {
			if(MisUtility.ifEmpty(tenderObjectionBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(TenderObjectionBean.class);
				if(MisUtility.ifEmpty(tenderObjectionBean.getTenderId()))
					criteria.add(Restrictions.eq("tenderId", tenderObjectionBean.getTenderId()).ignoreCase());
				if(MisUtility.ifEmpty(tenderObjectionBean.getObjectedBy()))
					criteria.add(Restrictions.eq("objectedBy", tenderObjectionBean.getObjectedBy()).ignoreCase());
				if(MisUtility.ifEmpty(tenderObjectionBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", tenderObjectionBean.getLocationId()).ignoreCase());
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				tenderObjectionBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return tenderObjectionBeans;
	}

	@Override
	public boolean saveOrUpdateTenderObjectionBeans(Collection<TenderObjectionBean> tenderObjectionBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(tenderObjectionBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

}
