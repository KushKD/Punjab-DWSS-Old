package com.prwss.mis.hr.trainingscheduleplan.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.hr.trainingscheduleplan.TrainingScheduleHeaderBean;

public class TrainingScheduleHeaderDaoImpl extends HibernateDaoSupport implements TrainingScheduleHeaderDao {
private Logger log = Logger.getLogger(TrainingScheduleHeaderDaoImpl.class);
	
	@Override
	@SuppressWarnings("unchecked")
	public List<TrainingScheduleHeaderBean> findTrainingScheduleHeader(
			TrainingScheduleHeaderBean trainingScheduleHeaderBean,
			List<String> statusList) throws DataAccessException {
		List<TrainingScheduleHeaderBean> trainingScheduleHeaderBeans = null;
		try {
			if(MisUtility.ifEmpty(trainingScheduleHeaderBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(TrainingScheduleHeaderBean.class);
				if(MisUtility.ifEmpty(trainingScheduleHeaderBean.getTrainingPlanId()))
				criteria.add(Restrictions.eq("trainingPlanId",trainingScheduleHeaderBean.getTrainingPlanId()));
				if(MisUtility.ifEmpty(trainingScheduleHeaderBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId",trainingScheduleHeaderBean.getLocationId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				trainingScheduleHeaderBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return trainingScheduleHeaderBeans;
	}

	@Override
	public long saveTrainingScheduleHeader(
			TrainingScheduleHeaderBean trainingScheduleHeaderBean)
			throws DataAccessException {
		long trainingPlanId = 0;
		try {
			trainingPlanId = (Long)getHibernateTemplate().save(trainingScheduleHeaderBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return trainingPlanId;
	}

	@Override
	public boolean updateTrainingScheduleHeader(
			TrainingScheduleHeaderBean trainingScheduleHeaderBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(trainingScheduleHeaderBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

}
