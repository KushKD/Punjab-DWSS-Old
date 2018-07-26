package com.prwss.mis.procurement.physicalprogresspackage.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class PhysicalProgressComponentsDaoImpl extends HibernateDaoSupport implements
		PhysicalProgressComponentsDao {
    private Logger log = Logger.getLogger(PhysicalProgressComponentsDaoImpl.class);
	
   
    @Override
   	@SuppressWarnings("unchecked")
    public List<PhysicalProgressComponentsBean> findPhysicalProgressAsOnDate(String packageId,List<String> statusList)  throws DataAccessException{
    	List<PhysicalProgressComponentsBean> physicalProgressComponentsBeans = null;
    	
    	try{
    		if(MisUtility.ifEmpty(packageId)){
				DetachedCriteria criteria = DetachedCriteria.forClass(PhysicalProgressComponentsBean.class);
				if(MisUtility.ifEmpty(packageId))
					criteria.add(Restrictions.eq("packageId", packageId));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));

				physicalProgressComponentsBeans = getHibernateTemplate().findByCriteria(criteria);
    		}
    	}
    	catch(DataAccessException e){
    		throw e ;
    	}
    	return physicalProgressComponentsBeans;
    }
    
    @Override
	@SuppressWarnings("unchecked")
	public List<PhysicalProgressComponentsBean> findPhysicalProgressComponents(
			PhysicalProgressComponentsBean physicalProgressComponentsBean,
			List<String> statusList) throws DataAccessException {
	List<PhysicalProgressComponentsBean> physicalProgressComponentsBeans = null;
		
		try {
			if(MisUtility.ifEmpty(physicalProgressComponentsBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(PhysicalProgressComponentsBean.class);
				if(MisUtility.ifEmpty(physicalProgressComponentsBean.getPackageId()))
					criteria.add(Restrictions.eq("packageId", physicalProgressComponentsBean.getPackageId()));
				if(MisUtility.ifEmpty(physicalProgressComponentsBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", physicalProgressComponentsBean.getLocationId()));
				if(MisUtility.ifEmpty(physicalProgressComponentsBean.getComponentName()))
					criteria.add(Restrictions.eq("componentName", physicalProgressComponentsBean.getComponentName()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));

				physicalProgressComponentsBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return physicalProgressComponentsBeans;
	}

	@Override
	public boolean saveOrUpdatePhysicalProgressComponents(
			Collection<PhysicalProgressComponentsBean> physicalProgressComponentsBean)
			throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(physicalProgressComponentsBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

}
