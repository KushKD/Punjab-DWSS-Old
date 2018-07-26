/**
 * 
 */
package com.prwss.mis.inventory.goodsreceipt.dao;

import java.util.List;

import com.prwss.mis.common.exception.MISException;

/**
 * @author PJHA
 *
 */
public interface GoodsReceiptDao {
	
	public List<GoodsReceiptHeaderBean> findGoodsReceiptHeaderBeans(GoodsReceiptHeaderBean goodsReceiptHeaderBean,List<String> statusList)throws MISException;
	public boolean saveGoodsReceiptHeaderBeans(GoodsReceiptHeaderBean goodsReceiptHeaderBean)throws MISException;
	public boolean updateGoodsReceiptHeaderBeans(GoodsReceiptHeaderBean goodsReceiptHeaderBean)throws MISException;

}
