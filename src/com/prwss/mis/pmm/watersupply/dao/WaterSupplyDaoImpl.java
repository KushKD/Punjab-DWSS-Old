/**
 * 
 */
package com.prwss.mis.pmm.watersupply.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.watersupply.WaterSupplyBean;

/**
 * @author pjha
 *
 */
public class WaterSupplyDaoImpl extends HibernateDaoSupport implements WaterSupplyDao{
	private Logger log = Logger.getLogger(WaterSupplyDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<WaterSupplyBean> findWaterSupply(
			WaterSupplyBean waterSupplyBean, List<String> statusList)
			throws DataAccessException {
		List<WaterSupplyBean> waterSupplyBeans = new  ArrayList<WaterSupplyBean>();
		try {
			if(MisUtility.ifEmpty(waterSupplyBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(WaterSupplyBean.class);
					criteria.add(Restrictions.eq("locationId",waterSupplyBean.getLocationId()));
					criteria.add(Restrictions.eq("villageId",waterSupplyBean.getVillageId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				waterSupplyBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return waterSupplyBeans;
	}

	@Override
	public boolean saveWaterSupply(WaterSupplyBean waterSupplyBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(waterSupplyBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveOrUpdateWaterSupply(WaterSupplyBean waterSupplyBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(waterSupplyBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

}
