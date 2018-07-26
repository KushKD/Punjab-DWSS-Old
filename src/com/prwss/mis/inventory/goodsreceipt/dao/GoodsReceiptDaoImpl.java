/**
 * 
 */
package com.prwss.mis.inventory.goodsreceipt.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author PJHA
 *
 */
public class GoodsReceiptDaoImpl extends HibernateDaoSupport implements GoodsReceiptDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<GoodsReceiptHeaderBean> findGoodsReceiptHeaderBeans(
			GoodsReceiptHeaderBean goodsReceiptHeaderBean,
			List<String> statusList) throws MISException {

			List<GoodsReceiptHeaderBean> goodsReceiptHeaderBeans=null;
			try {				
				DetachedCriteria criteria = DetachedCriteria.forClass(GoodsReceiptHeaderBean.class);
				if(MisUtility.ifEmpty(goodsReceiptHeaderBean.getGoodsReceiptHeaderId()))
					criteria.add(Restrictions.eq("goodsReceiptHeaderId", goodsReceiptHeaderBean.getGoodsReceiptHeaderId()));
				if(MisUtility.ifEmpty(goodsReceiptHeaderBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", goodsReceiptHeaderBean.getLocationId()));
				if(MisUtility.ifEmpty(goodsReceiptHeaderBean.getProjectId()))
					criteria.add(Restrictions.eq("projectId", goodsReceiptHeaderBean.getProjectId()));
				if(MisUtility.ifEmpty(goodsReceiptHeaderBean.getStoreId()))
					criteria.add(Restrictions.eq("storeId", goodsReceiptHeaderBean.getStoreId()));
				if(MisUtility.ifEmpty(goodsReceiptHeaderBean.getSupplierId()))
					criteria.add(Restrictions.eq("supplierId", goodsReceiptHeaderBean.getSupplierId()));
				if(MisUtility.ifEmpty(goodsReceiptHeaderBean.getContractNumber()))
					criteria.add(Restrictions.eq("contractNumber", goodsReceiptHeaderBean.getContractNumber()));
				if(MisUtility.ifEmpty(goodsReceiptHeaderBean.getInvoiceNumber()))
					criteria.add(Restrictions.eq("invoiceNumber",goodsReceiptHeaderBean.getInvoiceNumber()));				
				if(MisUtility.ifEmpty(goodsReceiptHeaderBean.getInvoiceDate()))
					criteria.add(Restrictions.eq("invoiceDate",goodsReceiptHeaderBean.getInvoiceDate()));
				if(MisUtility.ifEmpty(goodsReceiptHeaderBean.getReceivedDate()))
					criteria.add(Restrictions.eq("receivedDate",goodsReceiptHeaderBean.getReceivedDate()));
				if(!MisUtility.ifEmpty(statusList))
				{
					criteria.add(Restrictions.in("misAuditBean.status", statusList));					
				}
				
				System.out.println("criteria::::"+criteria.toString());
				
				goodsReceiptHeaderBeans=getHibernateTemplate().findByCriteria(criteria);
				
			}catch (DataAccessException e) {
				throw new MISException("failed while retrieving the data ::daoimpl");
			}
			
		
		
		return goodsReceiptHeaderBeans;
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveGoodsReceiptHeaderBeans(
			GoodsReceiptHeaderBean goodsReceiptHeaderBean) throws MISException {
		
		try{
			getHibernateTemplate().save(goodsReceiptHeaderBean);
		}catch (Exception e) {
			throw new MISException("Failed Saving goods Receipt",e);
		}
		
	return true;	
	}


	@Override
	public boolean updateGoodsReceiptHeaderBeans(
			GoodsReceiptHeaderBean goodsReceiptHeaderBean) throws MISException {
		try{
			getHibernateTemplate().update(goodsReceiptHeaderBean);
		}catch (Exception e) {
			throw new MISException("Failed updating goods Receipt",e);
		}
		
	return true;
	}

	

}
