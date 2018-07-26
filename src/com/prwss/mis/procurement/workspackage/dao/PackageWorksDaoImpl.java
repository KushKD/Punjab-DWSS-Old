package com.prwss.mis.procurement.workspackage.dao;

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
import com.prwss.mis.procurement.workspackage.PackageWorksBean;

public class PackageWorksDaoImpl extends HibernateDaoSupport implements PackageWorksDao {
	private Logger log = Logger.getLogger(PackageWorksDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PackageWorksBean> findWorksPackage(
			PackageWorksBean packageWorksBean, List<String> statusList)
			throws DataAccessException {
		List<PackageWorksBean> packageWorksBeans = null;
		try {
			if(MisUtility.ifEmpty(packageWorksBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(PackageWorksBean.class);
					criteria.add(Restrictions.eq("packageId",packageWorksBean.getPackageId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				packageWorksBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return packageWorksBeans;
	}

	@Override
	public boolean saveWorksPackage(PackageWorksBean packageWorksBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(packageWorksBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateWorksPackage(PackageWorksBean packageWorksBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(packageWorksBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<PackageWorksBean> getPackageIds(String schemeId)
			throws DataAccessException {
		Set<PackageWorksBean> packageWorksBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(PackageWorksBean.class);
			if(MisUtility.ifEmpty(schemeId))
			criteria.add(Restrictions.eq("schemeId", schemeId));
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			
			packageWorksBeans =new TreeSet<PackageWorksBean>(getHibernateTemplate().findByCriteria(criteria));
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
			
		}
		return packageWorksBeans;
	}
	
}


