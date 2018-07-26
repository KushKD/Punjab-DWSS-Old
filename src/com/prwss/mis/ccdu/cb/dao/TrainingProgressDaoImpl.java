package com.prwss.mis.ccdu.cb.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class TrainingProgressDaoImpl extends HibernateDaoSupport implements	TrainingProgressDao {

	private Logger log = Logger.getLogger(TrainingProgressDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<TrainingProgressBean> findCBTrainingProgress(TrainingProgressBean trainingProgressBean, List<String> statusList)
	throws DataAccessException {
		List<TrainingProgressBean> trainingProgressBeans = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(TrainingProgressBean.class);
		log.debug(trainingProgressBean);
		try {
			if(MisUtility.ifEmpty(trainingProgressBean)){
				if(MisUtility.ifEmpty(trainingProgressBean.getCbProgressId()))
					criteria.add(Restrictions.eq("cbProgressId", trainingProgressBean.getCbProgressId()));
				if(MisUtility.ifEmpty(trainingProgressBean.getTrainingId()))
					criteria.add(Restrictions.eq("trainingId", trainingProgressBean.getTrainingId()).ignoreCase());
				if(MisUtility.ifEmpty(trainingProgressBean.getPlanId()))
					criteria.add(Restrictions.eq("planId", trainingProgressBean.getPlanId()));
				if(MisUtility.ifEmpty(trainingProgressBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", trainingProgressBean.getVillageId()).ignoreCase());
				if(MisUtility.ifEmpty(trainingProgressBean.getTrainingId()))
					criteria.add(Restrictions.eq("trainingId", trainingProgressBean.getTrainingId()).ignoreCase());
				if(MisUtility.ifEmpty(trainingProgressBean.getBlockId()))
					criteria.add(Restrictions.eq("blockId", trainingProgressBean.getBlockId()).ignoreCase());
				if(MisUtility.ifEmpty(trainingProgressBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", trainingProgressBean.getLocationId()).ignoreCase());
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));

				log.debug(criteria);
				trainingProgressBeans = getHibernateTemplate().findByCriteria(criteria);	
			}
		} catch (DataAccessException e) {
			throw e;
		}		

		return trainingProgressBeans;
	}

	public TrainingProgressBean loadCBTrainingProgress(long cbProgressId) throws DataAccessException {
		TrainingProgressBean trainingProgressBean = null;
		try{			
			trainingProgressBean = getHibernateTemplate().load(TrainingProgressBean.class, cbProgressId);
		} catch (DataAccessException e) {
			throw e;
		}		

		return trainingProgressBean;
	}


	@Override
	public long saveCBTrainingProgress(TrainingProgressBean trainingProgressBean) throws DataAccessException {
		long cbProgressId = 0;
		log.debug(trainingProgressBean);
		try {
			
			System.out.print("inside saveCBTrainingProgress in dao++++++++++++++"+trainingProgressBean);
			cbProgressId = (Long)getHibernateTemplate().save(trainingProgressBean);
		} catch (DataAccessException e) {
			throw e;
		}

		return cbProgressId;
	}

	@Override
	public boolean updateCBTrainingProgress(TrainingProgressBean trainingProgressBean) throws DataAccessException {
		try {
			log.debug(trainingProgressBean);
			getHibernateTemplate().update(trainingProgressBean);
		} catch (DataAccessException e) {
			throw e;
		}

		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Set<TrainingProgressBean> getTrainingProgressIds(TrainingProgressBean trainingProgressBean)
			throws DataAccessException {
		Set<TrainingProgressBean> trainingProgressBeans = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(TrainingProgressBean.class);
		try {
				// System.out.println("raju 11111111111111");
				 if(MisUtility.ifEmpty(trainingProgressBean))
				 {
					 System.out.println(trainingProgressBean.getLocationId());
					 System.out.println(trainingProgressBean.getBlockId());
					 System.out.println(trainingProgressBean.getVillageId());
					 System.out.println(trainingProgressBean.getLevelOfTraining());
					 	if(MisUtility.ifEmpty(trainingProgressBean.getLocationId()))
					 			criteria.add(Restrictions.eq("locationId",trainingProgressBean.getLocationId()).ignoreCase());
					 	if(MisUtility.ifEmpty(trainingProgressBean.getBlockId()))
					 			criteria.add(Restrictions.eq("blockId",trainingProgressBean.getBlockId()).ignoreCase());
					 	if(MisUtility.ifEmpty(trainingProgressBean.getVillageId()))
					 			criteria.add(Restrictions.eq("villageId",trainingProgressBean.getVillageId()).ignoreCase());
					 	if(MisUtility.ifEmpty(trainingProgressBean.getLevelOfTraining()))
					 			criteria.add(Restrictions.eq("levelOfTraining",trainingProgressBean.getLevelOfTraining()).ignoreCase());
				 }
			log.debug(criteria);
			trainingProgressBeans = new TreeSet<TrainingProgressBean>(getHibernateTemplate().findByCriteria(criteria));	
		} catch (DataAccessException e) {
			throw e;
		}		
		System.out.println(trainingProgressBeans);
		return trainingProgressBeans;
		 
	 
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public Set<TrainingProgressBean> getTrainingProgressIds(String villageId)
//	throws DataAccessException {
//		Set<TrainingProgressBean> trainingProgressBeans = null;
//		DetachedCriteria criteria = DetachedCriteria.forClass(TrainingProgressBean.class);
//		try {
//			if(MisUtility.ifEmpty(villageId)){
//				criteria.add(Restrictions.eq("villageId", villageId).ignoreCase());
//			}
//			log.debug(criteria);
//			trainingProgressBeans = new TreeSet<TrainingProgressBean>(getHibernateTemplate().findByCriteria(criteria));	
//		} catch (DataAccessException e) {
//			throw e;
//		}		
//
//		return trainingProgressBeans;
//	}

}
