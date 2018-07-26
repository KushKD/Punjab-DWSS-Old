package com.prwss.mis.ccdu.plan.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.ccdu.plan.struts.CCDUPlanSummaryBean;
import com.prwss.mis.common.util.MisUtility;

public class CCDUPlanDetailDaoImpl extends HibernateDaoSupport implements CCDUPlanDetailDao {
	private Logger log = Logger.getLogger(CCDUPlanDetailDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<CCDUPlanDetailBean> findCCDUPlanDetail(CCDUPlanDetailBean ccduPlanDetailBean, List<String> statusList)
			throws DataAccessException {
		
		List<CCDUPlanDetailBean> ccduPlanDetailBeans = null;
		try {
			if(MisUtility.ifEmpty(ccduPlanDetailBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(CCDUPlanDetailBean.class);
				if(MisUtility.ifEmpty(ccduPlanDetailBean.getPlanId()))
					criteria.add(Restrictions.eq("planId", ccduPlanDetailBean.getPlanId()));
				if(MisUtility.ifEmpty(ccduPlanDetailBean.getTrainingBean()) && MisUtility.ifEmpty(ccduPlanDetailBean.getTrainingBean().getTrainingId()))
					criteria.add(Restrictions.eq("trainingBean.trainingId", ccduPlanDetailBean.getTrainingBean().getTrainingId()).ignoreCase());
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				ccduPlanDetailBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}	
		return ccduPlanDetailBeans;
	}

	@Override
	public boolean saveOrUpdateCCDUPlanDetail(Collection<CCDUPlanDetailBean> ccduPlanDetailBeans) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(ccduPlanDetailBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<CCDUPlanDetailBean> getTrainingIdByPlanIdLocationId(Long planId) throws DataAccessException {
		Set<CCDUPlanDetailBean> ccduPlanDetailBeans = null;
		System.out.println("in DAO CCDU PLAN");
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CCDUPlanDetailBean.class);
			criteria.add(Restrictions.eq("planId", planId));
			
//			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			ccduPlanDetailBeans = new TreeSet<CCDUPlanDetailBean>(getHibernateTemplate().findByCriteria(criteria));
			
			
		} catch (DataAccessException e) {
			throw e;
		}
		
		return ccduPlanDetailBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CCDUPlanSummaryBean> getPlanSummary(long planId, List<String> statusList) throws DataAccessException {
		List<CCDUPlanSummaryBean> ccduPlanSummaryBeans = new ArrayList<CCDUPlanSummaryBean>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CCDUPlanDetailBean.class);
			if(MisUtility.ifEmpty(planId))
				criteria.add(Restrictions.eq("planId", planId));
			if(!MisUtility.ifEmpty(statusList))
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.distinct(Projections.groupProperty("locationId")));
			projectionList.add(Projections.count("trainingBean.trainingId"));
			criteria.setProjection(projectionList);
			log.debug("Querry for plan summary"+criteria);
			Object[] planDetailObject = null;
			List<Object> planSummaryObjects = getHibernateTemplate().findByCriteria(criteria);
			Iterator<Object> planSummaryObjectsIterator =  planSummaryObjects.iterator();
			CCDUPlanSummaryBean ccduPlanSummaryBean = null;
			while(planSummaryObjectsIterator.hasNext()){
				planDetailObject = (Object[])planSummaryObjectsIterator.next();
				ccduPlanSummaryBean = new CCDUPlanSummaryBean();
				ccduPlanSummaryBean.setLocationName(planDetailObject[0].toString());
				ccduPlanSummaryBean.setTrainingsCount(Long.parseLong(planDetailObject[1].toString()));
				ccduPlanSummaryBeans.add(ccduPlanSummaryBean);
			}
		} catch (DataAccessException e) {
			throw e;
		}	
		return ccduPlanSummaryBeans;
	}

}
