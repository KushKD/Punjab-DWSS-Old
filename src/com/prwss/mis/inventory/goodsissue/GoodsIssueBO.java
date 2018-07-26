/**
 * 
 */
package com.prwss.mis.inventory.goodsissue;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.inventory.goodsissue.GoodsIssueHeaderBean;
import com.prwss.mis.inventory.goodsissue.struts.GoodsIssueForm;

/**
 * @author PJHA
 *
 */
public interface GoodsIssueBO {
	
	public List<GoodsIssueHeaderBean> finGoodsIssueHeaderBeans(GoodsIssueForm goodsIssueForm,List<String> statusList)throws MISException;	
	public boolean saveGoodsIssueHeaderBeans(GoodsIssueForm goodsIssueForm,MISSessionBean misSessionBean)throws MISException;
	public boolean updateGoodsIssueHeaderBeans(GoodsIssueForm goodsIssueForm,MISSessionBean misSessionBean)throws MISException;
	public boolean deleteGoodsIssueHeaderBeans(GoodsIssueForm goodsIssueForm,MISSessionBean misSessionBean)throws MISException;

}
