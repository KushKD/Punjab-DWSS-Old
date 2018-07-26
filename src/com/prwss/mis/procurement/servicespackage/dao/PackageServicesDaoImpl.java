package com.prwss.mis.procurement.servicespackage.dao;

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
import com.prwss.mis.procurement.servicespackage.PackageServicesBean;
import com.prwss.mis.procurement.workspackage.PackageWorksBean;

public class PackageServicesDaoImpl extends HibernateDaoSupport implements PackageServicesDao {
	private Logger log = Logger.getLogger(PackageServicesDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<PackageServicesBean> findServicesPackage(
			PackageServicesBean packageServicesBean, List<String> statusList)
			throws DataAccessException {
		List<PackageServicesBean> packageServicesBeans = null;
		try {
			if(MisUtility.ifEmpty(packageServicesBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(PackageServicesBean.class);
					criteria.add(Restrictions.eq("packageId",packageServicesBean.getPackageId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				packageServicesBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return packageServicesBeans;
	}

	@Override
	public boolean saveServicesPackage(PackageServicesBean packageServicesBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().flush();
			getHibernateTemplate().save(packageServicesBean);
			getHibernateTemplate().flush();
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateServicesPackage(PackageServicesBean packageServicesBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(packageServicesBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<PackageServicesBean> getPackageIds(String schemeId)
			throws DataAccessException {
		Set<PackageServicesBean> packageServicesBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(PackageWorksBean.class);
			if(MisUtility.ifEmpty(schemeId))
			criteria.add(Restrictions.eq("schemeId", schemeId));
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			
			packageServicesBeans =new TreeSet<PackageServicesBean>(getHibernateTemplate().findByCriteria(criteria));
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
			
		}
		return packageServicesBeans;

}
}
