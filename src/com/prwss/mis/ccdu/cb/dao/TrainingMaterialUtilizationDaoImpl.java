package com.prwss.mis.ccdu.cb.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class TrainingMaterialUtilizationDaoImpl extends HibernateDaoSupport implements TrainingMaterialUtilizationDao {
	private Logger log = Logger.getLogger(TrainingMaterialUtilizationDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TrainingMaterialUtilizationBean> findCBTrainingMaterialUtilization(TrainingMaterialUtilizationBean trainingMaterialUtilizationBean, 
			List<String> statusList) throws DataAccessException {
		
		List<TrainingMaterialUtilizationBean> trainingMaterialUtilizationBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(TrainingMaterialUtilizationBean.class);
			if(MisUtility.ifEmpty(trainingMaterialUtilizationBean)){
				
				if(MisUtility.ifEmpty(trainingMaterialUtilizationBean.getCbProgressId()))
					criteria.add(Restrictions.eq("cbProgressId", trainingMaterialUtilizationBean.getCbProgressId()));
				if(MisUtility.ifEmpty(trainingMaterialUtilizationBean.getItemBean()) && MisUtility.ifEmpty(trainingMaterialUtilizationBean.getItemBean().getItemId()))
					criteria.add(Restrictions.eq("itemBean.itemId", trainingMaterialUtilizationBean.getItemBean().getItemId()).ignoreCase());
				if(MisUtility.ifEmpty(trainingMaterialUtilizationBean.getItemBean()) && MisUtility.ifEmpty(trainingMaterialUtilizationBean.getItemBean().getItemName()))
					criteria.add(Restrictions.eq("iecMaterialBean.itemName", trainingMaterialUtilizationBean.getItemBean().getItemName()).ignoreCase());
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				trainingMaterialUtilizationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return trainingMaterialUtilizationBeans;
	}

	@Override
	public boolean saveOrUpdateCBTrainingMaterialUtilization(Collection<TrainingMaterialUtilizationBean> trainingMaterialUtilizationBeans) throws DataAccessException{
		
		try {
			log.debug("Training Material BO"+trainingMaterialUtilizationBeans);
			getHibernateTemplate().saveOrUpdateAll(trainingMaterialUtilizationBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

	
}
