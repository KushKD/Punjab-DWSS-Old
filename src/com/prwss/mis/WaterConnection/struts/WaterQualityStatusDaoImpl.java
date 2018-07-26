package com.prwss.mis.WaterConnection.struts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationBean;

public class WaterQualityStatusDaoImpl extends HibernateDaoSupport implements WaterQualityStatusDao {
	private Logger log = Logger.getLogger(WaterQualityStatusDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<WaterQualityStatusBean> getWaterQualityStatus(String villageId) throws DataAccessException {
		
		List<WaterQualityStatusBean> waterQualityStatusBeans=null;
		try{
			DetachedCriteria criteria=DetachedCriteria.forClass(WaterQualityStatusBean.class);
			criteria.add(Restrictions.eq("village_mis_code",villageId));
			criteria.add(Restrictions.eq("active_flag",Integer.parseInt(MISConstants.ONE)));
			waterQualityStatusBeans=getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			log.debug(e.getLocalizedMessage());
			throw e;
		}
		return waterQualityStatusBeans;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Set<LocationBean> getDistrictLocationIds(String locationType)
			throws DataAccessException {
		Set<LocationBean> locationBeans = null; 
		try {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
				
				if(MisUtility.ifEmpty(locationType))
					criteria.add(Restrictions.eq("locationType",locationType));

				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				criteria.add(Restrictions.in("misAuditBean.status",statusList));
				locationBeans = new TreeSet<LocationBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

}
