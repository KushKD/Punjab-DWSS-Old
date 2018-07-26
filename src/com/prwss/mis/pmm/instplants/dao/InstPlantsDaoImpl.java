/**
 * 
 */
package com.prwss.mis.pmm.instplants.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.instplants.InstPlantsBean;

/**
 * @author pjha
 *
 */
public class InstPlantsDaoImpl extends HibernateDaoSupport implements InstPlantsDao {
	private Logger log = Logger.getLogger(InstPlantsDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<InstPlantsBean> findInstPlants(InstPlantsBean instPlantsBean,
			List<String> statusList) throws DataAccessException {
		List<InstPlantsBean> instPlantsBeans = new  ArrayList<InstPlantsBean>();
		try {
			if(MisUtility.ifEmpty(instPlantsBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(InstPlantsBean.class);
					criteria.add(Restrictions.eq("locationId", instPlantsBean.getLocationId()));
					
					if(MisUtility.ifEmpty(instPlantsBean.getBlockId()))
		                criteria.add(Restrictions.eq("blockId", instPlantsBean.getBlockId()));

					
				if(MisUtility.ifEmpty(instPlantsBean.getTransactionDate()))
					criteria.add(Restrictions.eq("transactionDate", instPlantsBean.getTransactionDate()));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				instPlantsBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return instPlantsBeans;
	}

	@Override
	public boolean saveInstPlants(InstPlantsBean instPlantsBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(instPlantsBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveOrUpdateInstPlants(InstPlantsBean instPlantsBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(instPlantsBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

}
