/**
 * 
 */
package com.prwss.mis.pmm.watersupply;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.watersupply.struts.WaterSupplyForm;

/**
 * @author pjha
 *
 */
public interface WaterSupplyBO {
	public List<WaterSupplyBean> findWaterSupply(WaterSupplyForm waterSupplyForm,List<String> statusList) throws MISException;
	public boolean saveWaterSupply(WaterSupplyForm waterSupplyForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean updateWaterSupply(WaterSupplyForm waterSupplyForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteWaterSupply(WaterSupplyForm waterSupplyForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postWaterSupply(WaterSupplyForm waterSupplyForm,  MISSessionBean misSessionBean) throws MISException;
}
