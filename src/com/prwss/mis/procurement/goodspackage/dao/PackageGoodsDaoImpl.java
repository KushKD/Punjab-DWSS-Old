package com.prwss.mis.procurement.goodspackage.dao;

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
import com.prwss.mis.procurement.goodspackage.PackageGoodsBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderBean;
import com.prwss.mis.procurement.workspackage.PackageWorksBean;

public class PackageGoodsDaoImpl extends HibernateDaoSupport implements PackageGoodsDao {
	private Logger log = Logger.getLogger(PackageGoodsDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<PackageGoodsBean> findGoodsPackage(
			PackageGoodsBean packageGoodsBean, List<String> statusList)
			throws DataAccessException {
		List<PackageGoodsBean> packageGoodsBeans = null;
		try {
			if(MisUtility.ifEmpty(packageGoodsBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(PackageGoodsBean.class);
					criteria.add(Restrictions.eq("packageId",packageGoodsBean.getPackageId())); 
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				packageGoodsBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return packageGoodsBeans;
	}

	@Override
	public boolean saveGoodsPackage(PackageGoodsBean packageGoodsBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(packageGoodsBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateGoodsPackage(PackageGoodsBean packageGoodsBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(packageGoodsBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}
 
	@Override
	@SuppressWarnings("unchecked")
	
	public Set<PackageGoodsBean> getPackageIds(String schemeId )
			throws DataAccessException {
		Set<PackageGoodsBean> packageGoodsBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(PackageWorksBean.class);
			if(MisUtility.ifEmpty(schemeId))
			criteria.add(Restrictions.eq("schemeId", schemeId));
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			
			packageGoodsBeans =new TreeSet<PackageGoodsBean>(getHibernateTemplate().findByCriteria(criteria));
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
			
		}
		return packageGoodsBeans;
	}
 
	
}


