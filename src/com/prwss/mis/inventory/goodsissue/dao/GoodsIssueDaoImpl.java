/**
 * 
 */
package com.prwss.mis.inventory.goodsissue.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.UniqueConstraint;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.inventory.goodsissue.GoodsIssueHeaderBean;
import com.prwss.mis.inventory.goodsissue.StoreItemAvailabilityIssueBean;
import com.prwss.mis.inventory.goodsissue.struts.GoodsIssueForm;

/**
 * @author PJHA
 *
 */
public class GoodsIssueDaoImpl extends HibernateDaoSupport implements GoodsIssueDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsIssueHeaderBean> findGoodsIssueHeaderBeans(
			GoodsIssueHeaderBean goodsIssueHeaderBean,
			List<String> statusList) throws MISException {

			List<GoodsIssueHeaderBean> goodsIssueHeaderBeans=null;
			try {				
				DetachedCriteria criteria = DetachedCriteria.forClass(GoodsIssueHeaderBean.class);
				if(MisUtility.ifEmpty(goodsIssueHeaderBean.getGoodsIssueHeaderId()))
					criteria.add(Restrictions.eq("goodsIssueHeaderId", goodsIssueHeaderBean.getGoodsIssueHeaderId()));
				if(MisUtility.ifEmpty(goodsIssueHeaderBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", goodsIssueHeaderBean.getLocationId()));
				if(MisUtility.ifEmpty(goodsIssueHeaderBean.getProjectId()))
					criteria.add(Restrictions.eq("projectId", goodsIssueHeaderBean.getProjectId()));
				if(MisUtility.ifEmpty(goodsIssueHeaderBean.getStoreId()))
					criteria.add(Restrictions.eq("storeId", goodsIssueHeaderBean.getStoreId()));				
				if(MisUtility.ifEmpty(goodsIssueHeaderBean.getIndentNumber()))
					criteria.add(Restrictions.eq("indentNumber", goodsIssueHeaderBean.getIndentNumber()));				
				if(MisUtility.ifEmpty(goodsIssueHeaderBean.getIndentDate()))
					criteria.add(Restrictions.eq("indentDate",goodsIssueHeaderBean.getIndentDate()));
				if(!MisUtility.ifEmpty(statusList))
				{
					criteria.add(Restrictions.in("misAuditBean.status", statusList));					
				}
				
				System.out.println("criteria::::"+criteria.toString());
				
				goodsIssueHeaderBeans=getHibernateTemplate().findByCriteria(criteria);
				
			}catch (DataAccessException e) {
				throw new MISException("failed while retrieving the data ::daoimpl");
			}
			
		
		
		return goodsIssueHeaderBeans;
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveGoodsIssueHeaderBeans(
			GoodsIssueHeaderBean goodsIssueHeaderBean) throws MISException {
		
		try{
			getHibernateTemplate().save(goodsIssueHeaderBean);
		}catch (Exception e) {
			throw new MISException("Failed Saving goods Issue",e);
		}
		
	return true;	
	}


	@Override
	public boolean updateGoodsIssueHeaderBeans(
			GoodsIssueHeaderBean goodsIssueHeaderBean) throws MISException {
		try{
			getHibernateTemplate().update(goodsIssueHeaderBean);
		}catch (Exception e) {
			throw new MISException("Failed updating goods Issue",e);
		}
		
	return true;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<StoreItemAvailabilityIssueBean> findTotalAvailabeItemInStore(
			StoreItemAvailabilityIssueBean goodsIssueForm) throws MISException {
		
//		System.out.println("inside dao imple");
		//System.out.println("Location"+goodsIssueForm.getLocationId());
//		System.out.println("store"+goodsIssueForm.getStoreId());
	//System.out.println("Item Id"+goodsIssueForm.getItemId());
	//	System.out.println("quantiyy"+goodsIssueForm.getQuantity());
		List<StoreItemAvailabilityIssueBean> storeItemAvailabilityIssueBeans = new ArrayList<StoreItemAvailabilityIssueBean>();
		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(StoreItemAvailabilityIssueBean.class);
			if(MisUtility.ifEmpty(goodsIssueForm.getLocationId()))
				criteria.add(Restrictions.eq("locationId", goodsIssueForm.getLocationId()));
			if(MisUtility.ifEmpty(goodsIssueForm.getStoreId()))
				criteria.add(Restrictions.eq("storeId", goodsIssueForm.getStoreId()));
			if(MisUtility.ifEmpty(goodsIssueForm.getItemId()))
				criteria.add(Restrictions.eq("itemId", goodsIssueForm.getItemId()));
			
			storeItemAvailabilityIssueBeans = getHibernateTemplate().findByCriteria(criteria);
			
		}catch (DataAccessException e) {
			throw e;
		}
		
		return storeItemAvailabilityIssueBeans;
	}

//	
//	@Override
//	public boolean updateStoreItemAvailabilityIssueBean(
//			StoreItemAvailabilityIssueBean storeItemAvailabilityIssueBean)
//			throws MISException {
//		try{
//			getHibernateTemplate().update(storeItemAvailabilityIssueBean);
//		}catch (Exception e) {
//			throw new MISException("Failed updating goods Issue view",e);
//		}
//		
//	return true;
//	}

	


}
