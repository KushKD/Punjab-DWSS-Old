/**
 * 
 */
package com.prwss.mis.inventory.supplyorder.dao;

import java.util.List;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.inventory.supplyorder.SupplyOrderHeaderBean;

/**
 * @author PJHA
 *
 */
public interface SupplyOrderDao {
	public List<SupplyOrderHeaderBean> findSupplyOrderHeaderBeans(SupplyOrderHeaderBean supplyOrderHeaderBean,List<String> statusList)throws MISException;
	public boolean saveSupplyOrderHeaderBeans(SupplyOrderHeaderBean supplyOrderHeaderBean)throws MISException;
	public boolean updateSupplyOrderHeaderBeans(SupplyOrderHeaderBean supplyOrderHeaderBean)throws MISException;

}
