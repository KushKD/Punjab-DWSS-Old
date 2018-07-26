/**
 * 
 */
package com.prwss.mis.pmm.DSRPonds.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

/**
 * @author pjha
 *
 */
public class DSRPondsDaoImpl extends HibernateDaoSupport implements DSRPondsDao {
	private Logger log = Logger.getLogger(DSRPondsDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<DSRPondsBean> findDSRPonds(DSRPondsBean dsrPondsBean,
			List<String> statusList) throws DataAccessException {
		List<DSRPondsBean> dsrPondsBeans = new ArrayList<DSRPondsBean>() ;
		try {
			if(MisUtility.ifEmpty(dsrPondsBeans)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(DSRPondsBean.class);
				if(MisUtility.ifEmpty(dsrPondsBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", dsrPondsBean.getSchemeId()));
				
				if(MisUtility.ifEmpty(dsrPondsBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", dsrPondsBean.getLocationId()));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				dsrPondsBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return dsrPondsBeans;
	}

	@Override
	public boolean saveDSRPonds(DSRPondsBean dsrPondsBean)
			throws DataAccessException {
		
		try {
			getHibernateTemplate().save(dsrPondsBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveOrUpdateDSRPonds(DSRPondsBean dsrPondsBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(dsrPondsBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return true;
	}
	
}
