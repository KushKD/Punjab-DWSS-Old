/**
 * 
 */
package com.prwss.mis.pmm.sustassessment.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.sustassessment.SustAssessmentBean;

/**
 * @author pjha
 *
 */
public class SustAssessmentDaoImpl extends HibernateDaoSupport implements SustAssessmentDao {
	private Logger log = Logger.getLogger(SustAssessmentDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<SustAssessmentBean> findSustAssessment(
			SustAssessmentBean sustAssessmentBean, List<String> statusList)
			throws DataAccessException {
		List<SustAssessmentBean> sustAssessmentBeans = new  ArrayList<SustAssessmentBean>();
		try {
			if(MisUtility.ifEmpty(sustAssessmentBean)){
				/*System.out.println("---------------------------------------1 Start Susta Dao----------------------------------------------------");
				System.out.println(sustAssessmentBean);
				System.out.println("--------------------------------------- 1 End  Susta Dao----------------------------------------------------");
				*/
				DetachedCriteria criteria = DetachedCriteria.forClass(SustAssessmentBean.class);
				if(MisUtility.ifEmpty(sustAssessmentBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId",sustAssessmentBean.getVillageId()));
				if(MisUtility.ifEmpty(sustAssessmentBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", sustAssessmentBean.getSchemeId()));
				if(MisUtility.ifEmpty(sustAssessmentBean.getAssessmentDate()))
					criteria.add(Restrictions.eq("assessmentDate", sustAssessmentBean.getAssessmentDate()));
				System.out.println("Hello "+statusList);
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				sustAssessmentBeans =  getHibernateTemplate().findByCriteria(criteria);
				System.out.println("---------------------------------------2 Start Susta Dao----------------------------------------------------");
				for (SustAssessmentBean sustAssessmentBean1 : sustAssessmentBeans) {
					System.out.println(sustAssessmentBean1);
				}
				System.out.println("--------------------------------------- 2 End  Susta Dao----------------------------------------------------");
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return sustAssessmentBeans;
	}
	
	@Override
	public List<SustAssessmentBean> findSustAssessment(
			SustAssessmentBean sustAssessmentBean)
			throws DataAccessException {
		List<SustAssessmentBean> sustAssessmentBeans = new  ArrayList<SustAssessmentBean>();
		try {
			if(MisUtility.ifEmpty(sustAssessmentBean)){
				/*System.out.println("---------------------------------------1 Start Susta Dao----------------------------------------------------");
				System.out.println(sustAssessmentBean);
				System.out.println("--------------------------------------- 1 End  Susta Dao----------------------------------------------------");
				*/
				DetachedCriteria criteria = DetachedCriteria.forClass(SustAssessmentBean.class);
				if(MisUtility.ifEmpty(sustAssessmentBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId",sustAssessmentBean.getVillageId()));
				if(MisUtility.ifEmpty(sustAssessmentBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", sustAssessmentBean.getSchemeId()));
				if(MisUtility.ifEmpty(sustAssessmentBean.getAssessmentDate()))
					criteria.add(Restrictions.eq("assessmentDate", sustAssessmentBean.getAssessmentDate()));
				if(!MisUtility.ifEmpty(sustAssessmentBean.getMisAuditBean().getStatus()))
					criteria.add(Restrictions.eq("misAuditBean.status", sustAssessmentBean.getMisAuditBean().getStatus()));
				
				sustAssessmentBeans =  getHibernateTemplate().findByCriteria(criteria);
				System.out.println("---------------------------------------2 Start Susta Dao----------------------------------------------------");
				for (SustAssessmentBean sustAssessmentBean1 : sustAssessmentBeans) {
					System.out.println(sustAssessmentBean1);
				}
				System.out.println("--------------------------------------- 2 End  Susta Dao----------------------------------------------------");
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return sustAssessmentBeans;
	}

	@Override
	public boolean saveSustAssessment(SustAssessmentBean sustAssessmentBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(sustAssessmentBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateSustAssessment(
			SustAssessmentBean sustAssessmentBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(sustAssessmentBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
		
		return true;
	}
	@Override
	public boolean saveOrUpdateSustAssessment(
			SustAssessmentBean sustAssessmentBean) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(sustAssessmentBean);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);	
			return false;			
		}
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SustAssessmentBean> checkSustAssessment(
			SustAssessmentBean sustAssessmentBean, List<String> statusList)
			throws DataAccessException {
		List<SustAssessmentBean> sustAssessmentBeans = new  ArrayList<SustAssessmentBean>();
		try {
			if(MisUtility.ifEmpty(sustAssessmentBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(SustAssessmentBean.class);
				Criterion id = Restrictions.eq("schemeId", sustAssessmentBean.getSchemeId());
				Criterion date = Restrictions.eq("assessmentDate", sustAssessmentBean.getAssessmentDate());
				Criterion vill = Restrictions.eq("villageId", sustAssessmentBean.getVillageId());
				 Conjunction conjunction = Restrictions.conjunction();
				    conjunction.add(id);
				    conjunction.add(date);
				    conjunction.add(vill);
				criteria.add(conjunction);
				criteria.add(Restrictions.in("misAuditBean.status", statusList));				
				sustAssessmentBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return sustAssessmentBeans;
	}

}
