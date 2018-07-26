package com.prwss.mis.ccdu.plan.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class CCDUPlanMasterDaoImpl extends HibernateDaoSupport implements CCDUPlanMasterDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CCDUPlanMasterBean> findCCDUPlanMaster(CCDUPlanMasterBean ccduPlanMasterBean, List<String> statusList) throws DataAccessException {
		List<CCDUPlanMasterBean> ccduPlanMasterBeans = null;
		if(MisUtility.ifEmpty(ccduPlanMasterBean)){
		DetachedCriteria criteria = DetachedCriteria.forClass(CCDUPlanMasterBean.class);
			if(MisUtility.ifEmpty(ccduPlanMasterBean.getPlanId()))
				criteria.add(Restrictions.eq("planId", ccduPlanMasterBean.getPlanId()));
			if(MisUtility.ifEmpty(ccduPlanMasterBean.getFromDate()))
				criteria.add(Restrictions.eq("fromDate", ccduPlanMasterBean.getFromDate()));
			if(MisUtility.ifEmpty(ccduPlanMasterBean.getToDate()))
				criteria.add(Restrictions.eq("toDate", ccduPlanMasterBean.getToDate()));
		ccduPlanMasterBeans = getHibernateTemplate().findByCriteria(criteria);
		}
		return ccduPlanMasterBeans;
	}

	@Override
	public long saveCCDUPlanMaster(CCDUPlanMasterBean ccduPlanMasterBean) throws DataAccessException {
		long planId = 0;
		try {
			planId = (Long)getHibernateTemplate().save(ccduPlanMasterBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return planId;
	}

	@Override
	public boolean updateCCDUPlanMaster(CCDUPlanMasterBean ccduPlanMasterBean) throws DataAccessException {
		
		try {
			getHibernateTemplate().update(ccduPlanMasterBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<CCDUPlanMasterBean> getDistinctCCDUPlan() throws DataAccessException {
		Set<CCDUPlanMasterBean> ccduPlanMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CCDUPlanMasterBean.class);
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_VERIFIED));
			ccduPlanMasterBeans = new TreeSet<CCDUPlanMasterBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}
		return ccduPlanMasterBeans;
	}

}
