package com.prwss.mis.master.training.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class TrainingDaoImpl extends HibernateDaoSupport implements TrainingDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TrainingBean> findTraining(TrainingBean trainingBean, List<String> statusList)
			throws DataAccessException {
		List<TrainingBean> trainingBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(TrainingBean.class);
			if(MisUtility.ifEmpty(trainingBean)){
				if(MisUtility.ifEmpty(trainingBean.getTrainingId()))
					criteria.add(Restrictions.eq("trainingId", trainingBean.getTrainingId()).ignoreCase());
				if(MisUtility.ifEmpty(trainingBean.getTrainingName()))
					criteria.add(Restrictions.eq("trainingName", trainingBean.getTrainingName()).ignoreCase());
				if(MisUtility.ifEmpty(trainingBean.getTargetGroup()))
					criteria.add(Restrictions.eq("targetGroup", trainingBean.getTargetGroup()).ignoreCase());
				if(MisUtility.ifEmpty(trainingBean.getCategory()))
					criteria.add(Restrictions.eq("category", trainingBean.getCategory()).ignoreCase());
				if(MisUtility.ifEmpty(trainingBean.getFocusArea()))
					criteria.add(Restrictions.eq("focusArea", trainingBean.getFocusArea()).ignoreCase());
				if(MisUtility.ifEmpty(trainingBean.getResourceInstitution()))
					criteria.add(Restrictions.eq("resourceInstitution", trainingBean.getResourceInstitution()).ignoreCase());
				if(MisUtility.ifEmpty(trainingBean.getResponsibility()))
					criteria.add(Restrictions.eq("responsibility", trainingBean.getResponsibility()).ignoreCase());
				if(MisUtility.ifEmpty(trainingBean.getTrainingType()))
					criteria.add(Restrictions.eq("trainingType", trainingBean.getTrainingType()).ignoreCase());
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				trainingBeans = getHibernateTemplate().findByCriteria(criteria);	
			}
		} catch (DataAccessException e) {
			throw e;
		}		
		
		return trainingBeans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TrainingBean> findTraining(List<String> trainingIds) throws DataAccessException {
		List<TrainingBean> trainingBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(TrainingBean.class);
			if(!MisUtility.ifEmpty(trainingIds)){
				criteria.add(Restrictions.in("trainingId", trainingIds));
				trainingBeans = getHibernateTemplate().findByCriteria(criteria);	
			}
		} catch (DataAccessException e) {
			throw e;
		}		
		
		return trainingBeans;
	}

	@Override
	public String saveTraining(TrainingBean trainingBean) throws DataAccessException {
		 String trainingId = null;
		 
		 try {
			trainingId = (String)getHibernateTemplate().save(trainingBean);
		} catch (DataAccessException e) {
			throw e;
		}
		 
		return trainingId;
	}

	@Override
	public boolean updateTraining(TrainingBean trainingBean) throws DataAccessException {

		try {
			getHibernateTemplate().update(trainingBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateTraining(List<TrainingBean> trainingBeans) throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(trainingBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<TrainingBean> getDistinctTrainingIds(String trainingType) throws DataAccessException {
		Set<TrainingBean> trainingBeans = null;
		List<String> statusList = new ArrayList<String>();
		statusList.add("A");
		statusList.add("C");
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(TrainingBean.class);
			criteria.add(Restrictions.eq("trainingType", trainingType).ignoreCase());
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			//criteria.add(Restrictions.eq("misAuditBean.status","C").ignoreCase());
			trainingBeans = new TreeSet<TrainingBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}
		return trainingBeans;
	}


}
