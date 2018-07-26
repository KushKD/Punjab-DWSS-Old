/**
 * 
 */
package com.prwss.mis.inventory.goodsreceipt;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.inventory.goodsreceipt.dao.GoodsReceiptHeaderBean;
import com.prwss.mis.inventory.goodsreceipt.struts.GoodsReceiptForm;

/**
 * @author PJHA
 *
 */
public interface GoodsReceiptBO {
	
	public List<GoodsReceiptHeaderBean> finGoodsReceiptHeaderBeans(GoodsReceiptForm goodsReceiptForm,List<String> statusList)throws MISException;
	
	public boolean saveGoodsReceiptHeaderBeans(GoodsReceiptForm goodsReceiptForm,MISSessionBean misSessionBean)throws MISException;
	public boolean updateGoodsReceiptHeaderBeans(GoodsReceiptForm goodsReceiptForm,MISSessionBean misSessionBean)throws MISException;
	public boolean deleteGoodsReceiptHeaderBeans(GoodsReceiptForm goodsReceiptForm,MISSessionBean misSessionBean)throws MISException;
}
