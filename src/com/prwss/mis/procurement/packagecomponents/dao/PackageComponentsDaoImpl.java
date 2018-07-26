package com.prwss.mis.procurement.packagecomponents.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.procurement.physicalprogresspackage.dao.PrwssSigningContractViewBean;

public class PackageComponentsDaoImpl extends HibernateDaoSupport implements PackageComponentsDao {
	private Logger log = Logger.getLogger(PackageComponentsDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<PackageComponentsBean> findServicesPackageComponent(
			PackageComponentsBean packageComponentsBean, List<String> statusList)
			throws DataAccessException {
		List<PackageComponentsBean> packageComponentsBeans = null;

		try {
			if(MisUtility.ifEmpty(packageComponentsBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(PackageComponentsBean.class);
				if(MisUtility.ifEmpty(packageComponentsBean.getId()))
					criteria.add(Restrictions.eq("id", packageComponentsBean.getId()));
				if(MisUtility.ifEmpty(packageComponentsBean.getPackageId()))
					criteria.add(Restrictions.eq("packageId",packageComponentsBean.getPackageId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));

				packageComponentsBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return packageComponentsBeans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PrwssSigningContractViewBean> findSigningOfContract(
			String  packageId)
			throws DataAccessException {
		List<PrwssSigningContractViewBean> signingContractBeans = null;

		try {
			
				DetachedCriteria criteria = DetachedCriteria.forClass(PrwssSigningContractViewBean.class);
				System.out.println("packageId in dao===="+packageId);
				criteria.add(Restrictions.eq("packageId",packageId));
				
				signingContractBeans = getHibernateTemplate().findByCriteria(criteria);
				
			
		} catch (DataAccessException e) {
			throw e;
		}

		return signingContractBeans;
	}
	@Override
	public boolean saveOrUpdateServicesPackageComponent(
			Collection<PackageComponentsBean> packageComponentsBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(packageComponentsBean);
			getHibernateTemplate().flush();
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}
}
