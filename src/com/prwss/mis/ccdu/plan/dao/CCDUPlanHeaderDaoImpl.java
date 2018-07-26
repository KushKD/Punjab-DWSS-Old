package com.prwss.mis.ccdu.plan.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class CCDUPlanHeaderDaoImpl extends HibernateDaoSupport implements CCDUPlanHeaderDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CCDUPlanHeaderBean> findCCDUPlanHeader(CCDUPlanHeaderBean ccduPlanHeaderBean, List<String> statusList)
			throws DataAccessException {
		List<CCDUPlanHeaderBean> ccduPlanHeaderBeans = null;
		try {
			if(MisUtility.ifEmpty(ccduPlanHeaderBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(CCDUPlanHeaderBean.class);
				if(MisUtility.ifEmpty(ccduPlanHeaderBean.getPlanId()))
					criteria.add(Restrictions.eq("planId", ccduPlanHeaderBean.getPlanId()));
				if(MisUtility.ifEmpty(ccduPlanHeaderBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", ccduPlanHeaderBean.getLocationId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				ccduPlanHeaderBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return ccduPlanHeaderBeans;
	}

	@Override
	public boolean saveCCDUPlanHeader(CCDUPlanHeaderBean ccduPlanHeaderBean) throws DataAccessException {
		
		try {
			getHibernateTemplate().save(ccduPlanHeaderBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateCCDUPlanHeader(CCDUPlanHeaderBean ccduPlanHeaderBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(ccduPlanHeaderBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

}
