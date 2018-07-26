/**
 * 
 */
package com.prwss.mis.inventory.supplyorder.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.inventory.supplyorder.SupplyOrderHeaderBean;


public class SupplyOrderDaoImpl extends HibernateDaoSupport implements SupplyOrderDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<SupplyOrderHeaderBean> findSupplyOrderHeaderBeans(
			SupplyOrderHeaderBean SupplyOrderHeaderBean,
			List<String> statusList) throws MISException {

			List<SupplyOrderHeaderBean> SupplyOrderHeaderBeans=null;
			try {				
				DetachedCriteria criteria = DetachedCriteria.forClass(SupplyOrderHeaderBean.class);
				if(MisUtility.ifEmpty(SupplyOrderHeaderBean.getSupplyOrderHeaderId()))
					criteria.add(Restrictions.eq("supplyOrderHeaderId", SupplyOrderHeaderBean.getSupplyOrderHeaderId()));
				if(MisUtility.ifEmpty(SupplyOrderHeaderBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", SupplyOrderHeaderBean.getLocationId()));
				if(MisUtility.ifEmpty(SupplyOrderHeaderBean.getProjectId()))
					criteria.add(Restrictions.eq("projectId", SupplyOrderHeaderBean.getProjectId()));
				if(MisUtility.ifEmpty(SupplyOrderHeaderBean.getStoreId()))
					criteria.add(Restrictions.eq("storeId", SupplyOrderHeaderBean.getStoreId()));
				if(MisUtility.ifEmpty(SupplyOrderHeaderBean.getSupplierId()))
					criteria.add(Restrictions.eq("supplierId", SupplyOrderHeaderBean.getSupplierId()));							
				if(MisUtility.ifEmpty(SupplyOrderHeaderBean.getSupplyOrderDate()))
					criteria.add(Restrictions.eq("supplyOrderDate",SupplyOrderHeaderBean.getSupplyOrderDate()));
				if(MisUtility.ifEmpty(SupplyOrderHeaderBean.getSupplyOrderNumber()))
					criteria.add(Restrictions.eq("supplyOrderNumber",SupplyOrderHeaderBean.getSupplyOrderNumber()));
				if(!MisUtility.ifEmpty(statusList))
				{
					criteria.add(Restrictions.in("misAuditBean.status", statusList));					
				}
				
				System.out.println("criteria::::: "+criteria);				
				SupplyOrderHeaderBeans=getHibernateTemplate().findByCriteria(criteria);
				
			}catch (DataAccessException e) {
				throw new MISException("failed while retrieving the data ::daoimpl");
			}
			
		
		
		return SupplyOrderHeaderBeans;
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveSupplyOrderHeaderBeans(
			SupplyOrderHeaderBean SupplyOrderHeaderBean) throws MISException {		
		try{
			getHibernateTemplate().save(SupplyOrderHeaderBean);
		}catch (Exception e) {
			throw new MISException("Failed Saving goods Receipt",e);
		}
		
	return true;	
	}

	@Transactional()
	@Override
	public boolean updateSupplyOrderHeaderBeans(SupplyOrderHeaderBean SupplyOrderHeaderBean) throws MISException {
		try{
			
			getHibernateTemplate().update(SupplyOrderHeaderBean);
		}catch (Exception e) {
			System.out.println("Supply daoimpl header id::: Exception");
			throw new MISException("Failed updating goods Receipt",e);
		}
		
	return true;
	}

}
