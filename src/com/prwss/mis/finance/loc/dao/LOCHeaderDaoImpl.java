/**
 * 
 */
package com.prwss.mis.finance.loc.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.ccdu.plan.struts.CCDUPlanSummaryBean;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.loc.LOCHeaderBean;
import com.prwss.mis.finance.releaseloc.struts.LocNotReleasedBean;
import com.prwss.mis.procurement.check.TenderCheckBean;

/**
 * @author PJHA
 *
 */
public class LOCHeaderDaoImpl extends HibernateDaoSupport implements LOCHeaderDao {
	private Logger log = Logger.getLogger(LOCHeaderDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<LOCHeaderBean> findLOCHeader(LOCHeaderBean locHeaderBean,
			List<String> statusList) throws DataAccessException {
		List<LOCHeaderBean> locHeaderBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LOCHeaderBean.class);
			if(MisUtility.ifEmpty(locHeaderBean)){
				
				if(MisUtility.ifEmpty(locHeaderBean.getLocId()))
					criteria.add(Restrictions.eq("locId", locHeaderBean.getLocId()));
				
				if(MisUtility.ifEmpty(locHeaderBean.getProgramId()))
					criteria.add(Restrictions.eq("programId", locHeaderBean.getProgramId()));
				
				if(MisUtility.ifEmpty(locHeaderBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", locHeaderBean.getLocationId()));
				
				if(MisUtility.ifEmpty(locHeaderBean.getRequestToLocationId()))
					criteria.add(Restrictions.eq("requestToLocationId", locHeaderBean.getRequestToLocationId()));
				//criteria.setProjection(Projections.distinct(Projections.property("requestToLocationId")));			
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				locHeaderBeans = getHibernateTemplate().findByCriteria(criteria);
			}
			log.debug(criteria);
			
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return locHeaderBeans;
	}

	@Override
	public long saveLOCHeader(LOCHeaderBean locHeaderBean)
			throws DataAccessException {
		long locId = 0;
		try {
			locId=	(Long)getHibernateTemplate().save(locHeaderBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return locId;
	}

	@Override
	public boolean updateLOCHeader(LOCHeaderBean locHeaderBean)
			throws DataAccessException {
		try {
			 getHibernateTemplate().saveOrUpdate(locHeaderBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateLOCHeader(List<LOCHeaderBean> locHeaderBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(locHeaderBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}
	
	@Override
	public List<LOCRequestFromLocationBean> findDistinctrequestFromLocationId(
			LOCHeaderBean locHeaderBean, List<String> statusList)
			throws DataAccessException {
		List<LOCRequestFromLocationBean> locRequestFromLocationBeans = new ArrayList<LOCRequestFromLocationBean>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LOCHeaderBean.class);
			if(MisUtility.ifEmpty(locHeaderBean)){
				System.out.println("locHeaderBean.getRequestToLocationId(): "+locHeaderBean.getRequestToLocationId());
				System.out.println("locHeaderBean.getProgramId(): "+locHeaderBean.getProgramId());
				if(MisUtility.ifEmpty(locHeaderBean.getRequestToLocationId()))
					criteria.add(Restrictions.eq("requestToLocationId", locHeaderBean.getRequestToLocationId()));
				if(MisUtility.ifEmpty(locHeaderBean.getProgramId()))
					criteria.add(Restrictions.eq("programId", locHeaderBean.getProgramId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				ProjectionList projectionList = Projections.projectionList();
	            projectionList.add(Projections.property("locationId"));
	            projectionList.add(Projections.property("locationName"));
	            criteria.setProjection(Projections.distinct(projectionList));
	            List<Object> locationFromObjects  = getHibernateTemplate().findByCriteria(criteria);
	            Object[] locationFromObject = null;
	            Iterator<Object> locationFromObjectsIterator =  locationFromObjects.iterator();
				LOCRequestFromLocationBean locRequestFromLocationBean=null;
				while(locationFromObjectsIterator.hasNext()){
					locationFromObject = (Object[])locationFromObjectsIterator.next();
					locRequestFromLocationBean = new LOCRequestFromLocationBean();
					locRequestFromLocationBean.setLocationId(locationFromObject[0].toString());
					locRequestFromLocationBean.setLocationName(locationFromObject[1].toString());
					System.out.println("**********DAO locationFromObject[1].toString(): "+locationFromObject[1].toString());
					locRequestFromLocationBeans.add(locRequestFromLocationBean);
				}
	            System.out.println("********************DAO locRequestFromLocationBeans: "+locRequestFromLocationBeans);
			}
			log.debug(criteria);
			
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return locRequestFromLocationBeans;
	}
	
	/*fetch method for not released LOC*/
	@SuppressWarnings("unchecked")
	@Override
	public List<LocNotReleasedBean> fetchReleaseLoc(LocNotReleasedBean locNotReleasedBean) {
		List<LocNotReleasedBean> locNotReleasedBeans = null;
		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(LocNotReleasedBean.class);
			if(MisUtility.ifEmpty(locNotReleasedBean.getLocId()))
				criteria.add(Restrictions.eq("locId", locNotReleasedBean.getLocId()));
			
			if(MisUtility.ifEmpty(locNotReleasedBean.getProgramId()))
				criteria.add(Restrictions.eq("programId", locNotReleasedBean.getProgramId()));
			
			if(MisUtility.ifEmpty(locNotReleasedBean.getLocationId()))
				criteria.add(Restrictions.eq("locationId", locNotReleasedBean.getLocationId()));
						
			locNotReleasedBeans = getHibernateTemplate().findByCriteria(criteria); 	
		}catch(Exception e){
			e.printStackTrace();
		}
		return locNotReleasedBeans;
	}

}
