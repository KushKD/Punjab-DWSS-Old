/**
 * 
 */
package com.prwss.mis.inventory.goodsissue.dao;

import java.util.List;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.inventory.goodsissue.GoodsIssueHeaderBean;
import com.prwss.mis.inventory.goodsissue.StoreItemAvailabilityIssueBean;
import com.prwss.mis.inventory.goodsissue.struts.GoodsIssueForm;


/**
 * @author PJHA
 *
 */
public interface GoodsIssueDao {
	
	public List<GoodsIssueHeaderBean> findGoodsIssueHeaderBeans(GoodsIssueHeaderBean goodsIssueHeaderBean,List<String> statusList)throws MISException;
	public boolean saveGoodsIssueHeaderBeans(GoodsIssueHeaderBean goodsIssueHeaderBean)throws MISException;
	public boolean updateGoodsIssueHeaderBeans(GoodsIssueHeaderBean goodsIssueHeaderBean)throws MISException;
	public List<StoreItemAvailabilityIssueBean> findTotalAvailabeItemInStore(StoreItemAvailabilityIssueBean storeItemAvailabilityIssueBean)throws MISException;
	//public boolean updateStoreItemAvailabilityIssueBean(StoreItemAvailabilityIssueBean storeItemAvailabilityIssueBean)throws MISException;
}
