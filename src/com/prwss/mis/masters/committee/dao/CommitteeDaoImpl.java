/**
 * 
 */
package com.prwss.mis.masters.committee.dao;

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
public class CommitteeDaoImpl extends HibernateDaoSupport implements CommitteeDao {

	private Logger log = Logger.getLogger(CommitteeDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<CommitteeBean> findCommittee(CommitteeBean committeeBean,
			List<String> statusList) throws DataAccessException {
		List<CommitteeBean> committeeBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CommitteeBean.class);
			if(MisUtility.ifEmpty(committeeBean)){
				
				if(MisUtility.ifEmpty(committeeBean.getCommitteeId()))
					criteria.add(Restrictions.eq("committeeId", committeeBean.getCommitteeId()));
				
				if(MisUtility.ifEmpty(committeeBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", committeeBean.getSchemeId()));
				
				if(MisUtility.ifEmpty(committeeBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId", committeeBean.getVillageId()));
				
				if(MisUtility.ifEmpty(committeeBean.getsLCStatus()))
					criteria.add(Restrictions.eq("sLCStatus", committeeBean.getsLCStatus()));
				
				if(MisUtility.ifEmpty(committeeBean.getDistrictId()))
					criteria.add(Restrictions.eq("districtId", committeeBean.getDistrictId()));
				
				if(MisUtility.ifEmpty(committeeBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", committeeBean.getLocationId()));
				
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
			}
			log.debug(criteria);
			committeeBeans = getHibernateTemplate().findByCriteria(criteria);
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return committeeBeans;
	}

	

	@Override
	public long  saveCommittee(CommitteeBean committeeBean)
			throws DataAccessException {
		long committeeId = 0;
		try {
			committeeId=	(Long)getHibernateTemplate().save(committeeBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
	
		
	
		return committeeId;
	}

	@Override
	public boolean updateCommittee(CommitteeBean committeeBean)
			throws DataAccessException {
		try {
			 getHibernateTemplate().update(committeeBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateCommittee(List<CommitteeBean> committeeBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(committeeBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<CommitteeBean> findCommittee(List<String> committeeId)
			throws DataAccessException {
		List<CommitteeBean> committeeBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CommitteeBean.class);

			if(!MisUtility.ifEmpty(committeeId))
				criteria.add(Restrictions.in("committeeId", committeeId));

			log.debug(criteria);
			committeeBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}

		return committeeBeans;
	}

	

}
