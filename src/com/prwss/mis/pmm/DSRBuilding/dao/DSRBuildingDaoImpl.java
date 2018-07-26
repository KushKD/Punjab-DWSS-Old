/**
 * 
 */
package com.prwss.mis.pmm.DSRBuilding.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.village.connection.dao.VillageConnectionBean;

/**
 * @author pjha
 *
 */
public class DSRBuildingDaoImpl extends HibernateDaoSupport implements DSRBuildingDao {
	private Logger log = Logger.getLogger(DSRBuildingDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<DSRBuildingBean> findDSRBuilding(
			DSRBuildingBean dsrBuildingBean, List<String> statusList)
			throws DataAccessException {
		List<DSRBuildingBean> dsrBuildingBeans = new ArrayList<DSRBuildingBean>() ;
		try {
			if(MisUtility.ifEmpty(dsrBuildingBeans)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(DSRBuildingBean.class);
				if(MisUtility.ifEmpty(dsrBuildingBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", dsrBuildingBean.getSchemeId()));
				
				if(MisUtility.ifEmpty(dsrBuildingBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", dsrBuildingBean.getLocationId()));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				dsrBuildingBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return dsrBuildingBeans;
	}

	@Override
	public boolean saveVillageDSRBuilding(
			DSRBuildingBean dsrBuildingBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(dsrBuildingBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveOrUpdateDSRBuilding(
			DSRBuildingBean dsrBuildingBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(dsrBuildingBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

}
