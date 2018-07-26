/**
 * 
 */
package com.prwss.mis.pmm.village;

import java.io.Serializable;
import java.util.List;

import com.prwss.mis.pmm.village.connection.dao.VillageConnectionBean;
import com.prwss.mis.pmm.village.household.dao.VillageHouseHoldBean;
import com.prwss.mis.pmm.village.ncpcstatus.dao.VillageNCPCStatusBean;
import com.prwss.mis.pmm.village.population.dao.VillagePopulationBean;
import com.prwss.mis.pmm.village.sewerage.dao.VillageSewerageBean;
import com.prwss.mis.pmm.village.tariff.dao.VillageTariffBean;

/**
 * @author pjha
 *
 */
public class VillageDetailsBean implements Serializable {
	
	
	private static final long serialVersionUID = -5922528112354619023L;
	private List<VillageConnectionBean> villageConnectionBean = null;
	private  List<VillageHouseHoldBean> villageHouseHoldBean = null;
	private List<VillageTariffBean> villageTariffBean = null;
	private  List<VillagePopulationBean> villagePopulationBean = null;
	private List<VillageSewerageBean> villageSewerageBean = null;
	private List<VillageNCPCStatusBean> villageNCPCStatusBean = null;
	
	
	
	public List<VillageNCPCStatusBean> getVillageNCPCStatusBean() {
		return villageNCPCStatusBean;
	}
	public void setVillageNCPCStatusBean(
			List<VillageNCPCStatusBean> villageNCPCStatusBean) {
		this.villageNCPCStatusBean = villageNCPCStatusBean;
	}
	public List<VillageSewerageBean> getVillageSewerageBean() {
		return villageSewerageBean;
	}
	public void setVillageSewerageBean(List<VillageSewerageBean> villageSewerageBean) {
		this.villageSewerageBean = villageSewerageBean;
	}
	public List<VillageConnectionBean> getVillageConnectionBean() {
		return villageConnectionBean;
	}
	public void setVillageConnectionBean(
			List<VillageConnectionBean> villageConnectionBean) {
		this.villageConnectionBean = villageConnectionBean;
	}
	public List<VillageHouseHoldBean> getVillageHouseHoldBean() {
		return villageHouseHoldBean;
	}
	public void setVillageHouseHoldBean(
			List<VillageHouseHoldBean> villageHouseHoldBean) {
		this.villageHouseHoldBean = villageHouseHoldBean;
	}
	public List<VillageTariffBean> getVillageTariffBean() {
		return villageTariffBean;
	}
	public void setVillageTariffBean(List<VillageTariffBean> villageTariffBean) {
		this.villageTariffBean = villageTariffBean;
	}
	public List<VillagePopulationBean> getVillagePopulationBean() {
		return villagePopulationBean;
	}
	public void setVillagePopulationBean(
			List<VillagePopulationBean> villagePopulationBean) {
		this.villagePopulationBean = villagePopulationBean;
	}
	

}
