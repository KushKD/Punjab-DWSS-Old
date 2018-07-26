package com.prwss.mis.hr.trainingmanagent.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.hr.trainingmanagent.HRTrainingManagementBean;

public class HRTrainingManagementDaoImpl extends HibernateDaoSupport implements HRTrainingManagementDao {
	private Logger log = Logger.getLogger(HRTrainingManagementDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<HRTrainingManagementBean> findHRTrainingManagementBeans(
			HRTrainingManagementBean hrTrainingManagementBean,
			List<String> statusList) throws DataAccessException {
		List<HRTrainingManagementBean> hrTrainingManagementBeans = null;
		try {
			if(MisUtility.ifEmpty(hrTrainingManagementBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(HRTrainingManagementBean.class);
				if(MisUtility.ifEmpty(hrTrainingManagementBean.getId()))
					criteria.add(Restrictions.eq("id", hrTrainingManagementBean.getId()));
				if(MisUtility.ifEmpty(hrTrainingManagementBean.getTrainingPlanId()))
					criteria.add(Restrictions.eq("trainingPlanId",hrTrainingManagementBean.getTrainingPlanId()));
				if(MisUtility.ifEmpty(hrTrainingManagementBean.getTrainingObjective()))
					criteria.add(Restrictions.eq("trainingObjective",hrTrainingManagementBean.getTrainingObjective()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				hrTrainingManagementBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return hrTrainingManagementBeans;
	}

	@Override
	public boolean saveOrUpdateHRTrainingManagementBeans(
			Collection<HRTrainingManagementBean> hrTrainingManagementBeans)
	throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(hrTrainingManagementBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}

		return true;
	}
}


