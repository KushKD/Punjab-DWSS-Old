package com.prwss.mis.hr.trainingscheduleplan.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class TrainingScheduleDetailDaoImpl extends HibernateDaoSupport implements TrainingScheduleDetailDao {
private Logger log = Logger.getLogger(TrainingScheduleDetailDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<TrainingScheduleDetailBean> findTrainingScheduleDetailBeans(
			TrainingScheduleDetailBean trainingScheduleDetailBean,
			List<String> statusList) throws DataAccessException {
		List<TrainingScheduleDetailBean> trainingScheduleDetailBeans = null;
		try {
			if(MisUtility.ifEmpty(trainingScheduleDetailBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(TrainingScheduleDetailBean.class);
				if(MisUtility.ifEmpty(trainingScheduleDetailBean.getId()))
					criteria.add(Restrictions.eq("id", trainingScheduleDetailBean.getId()));
				if(MisUtility.ifEmpty(trainingScheduleDetailBean.getTrainingPlanId()))
					criteria.add(Restrictions.eq("trainingPlanId",trainingScheduleDetailBean.getTrainingPlanId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				trainingScheduleDetailBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return trainingScheduleDetailBeans;
	}

	@Override
	public boolean saveOrUpdateTrainingScheduleDetailBeans(
			Collection<TrainingScheduleDetailBean> trainingScheduleDetailBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(trainingScheduleDetailBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

}
