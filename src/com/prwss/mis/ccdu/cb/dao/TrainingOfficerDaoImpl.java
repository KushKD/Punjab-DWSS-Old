package com.prwss.mis.ccdu.cb.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class TrainingOfficerDaoImpl extends HibernateDaoSupport implements TrainingOfficerDao {
	private Logger log = Logger.getLogger(TrainingOfficerDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<TrainingOfficerBean> findTrainingOfficer(TrainingOfficerBean trainingOfficerBean, List<String> statusList) throws DataAccessException {
		List<TrainingOfficerBean> trainingOfficerBeans =null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(TrainingOfficerBean.class);
			if(MisUtility.ifEmpty(trainingOfficerBean.getCbProgressId()))
				criteria.add(Restrictions.eq("cbProgressId", trainingOfficerBean.getCbProgressId()));
			if(MisUtility.ifEmpty(trainingOfficerBean.getEmployeeId()))
				criteria.add(Restrictions.eq("employeeId", trainingOfficerBean.getEmployeeId()));
			
			trainingOfficerBeans =  getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return trainingOfficerBeans;
	}

	@Override
	public String saveTrainingOfficer(TrainingOfficerBean trainingOfficerBean) throws DataAccessException {
		String trainingOfficerId = null;
		try {
			trainingOfficerId = (String)getHibernateTemplate().save(trainingOfficerBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return trainingOfficerId;
	}

	@Override
	public boolean updateTrainingOfficer(TrainingOfficerBean trainingOfficerBean) throws DataAccessException {

		try {
			getHibernateTemplate().update(trainingOfficerBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveOrUpdateTrainingOfficer(List<TrainingOfficerBean> trainingOfficerBeans) throws DataAccessException {
		
		try {
			System.out.print("inside trainingOfficerBeans-------------------BO-----"+trainingOfficerBeans);
			getHibernateTemplate().saveOrUpdateAll(trainingOfficerBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			 throw e;
		}
		
		return true;
	}


}
