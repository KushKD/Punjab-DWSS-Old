/**
 * 
 */
package com.prwss.mis.inventory.supplyorder;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.inventory.supplyorder.struts.SupplyOrderForm;

public interface SupplyOrderBO {
	public List<SupplyOrderHeaderBean> finSupplyOrderHeaderBeans(SupplyOrderForm supplyOrderForm,List<String> statusList)throws MISException;	
	public boolean saveSupplyOrderHeaderBeans(SupplyOrderForm supplyOrderForm,MISSessionBean misSessionBean)throws MISException;
	public boolean updateSupplyOrderHeaderBeans(SupplyOrderForm supplyOrderForm,MISSessionBean misSessionBean)throws MISException;
	public boolean deleteSupplyOrderHeaderBeans(SupplyOrderForm supplyOrderForm,MISSessionBean misSessionBean)throws MISException;
	

}
