package com.prwss.mis.procurement.nonconsultancypackage.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.procurement.nonconsultancypackage.PackageNonConsultancyBean;

public class PackageNonConsultancyDaoImpl extends HibernateDaoSupport implements PackageNonConsultancyDao {
	private Logger log = Logger.getLogger(PackageNonConsultancyDaoImpl.class);
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PackageNonConsultancyBean> findNonConsltPackage(
			PackageNonConsultancyBean packageNonConsultancyBean,
			List<String> statusList) throws DataAccessException {
		List<PackageNonConsultancyBean> packageNonConsultancyBeans = null;
		try {
			if(MisUtility.ifEmpty(packageNonConsultancyBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(PackageNonConsultancyBean.class);
					criteria.add(Restrictions.eq("packageId",packageNonConsultancyBean.getPackageId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				packageNonConsultancyBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return packageNonConsultancyBeans;
	}

	@Override
	public boolean saveNonConsltPackage(
			PackageNonConsultancyBean packageNonConsultancyBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(packageNonConsultancyBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateNonConsltPackage(
			PackageNonConsultancyBean packageNonConsultancyBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(packageNonConsultancyBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

}
