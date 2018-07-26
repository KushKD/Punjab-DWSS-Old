package com.prwss.mis.pmm.operatingarrangement;

import java.io.Serializable;

public class VillageOperatingGridBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String schemeId;
	private String villageId;
	private String villageCommissioningDate;
	private String villageName;
	private String habitationType;
	private String schemeOperatedBy;
	private String supplyServiceLevel;
	private String schemeUpgraded;
	private String operationArrangementDate;
	
	//KD Earlier Code Working 
/*	private String schemeHours;
	private String schemeFP;*/
	
	/**
	 * KD WORK
	 * @return
	 */
	
	private String villagecategory;
	
	public String getVillagecategory() {
		return villagecategory;
	}
	public void setVillagecategory(String villagecategory) {
		this.villagecategory = villagecategory;
	}
	
	/**
	 * KD WORK
	 * @return
	 */
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getVillageCommissioningDate() {
		return villageCommissioningDate;
	}
	public void setVillageCommissioningDate(String villageCommissioningDate) {
		this.villageCommissioningDate = villageCommissioningDate;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getHabitationType() {
		return habitationType;
	}
	public void setHabitationType(String habitationType) {
		this.habitationType = habitationType;
	}
	public String getSchemeOperatedBy() {
		return schemeOperatedBy;
	}
	public void setSchemeOperatedBy(String schemeOperatedBy) {
		this.schemeOperatedBy = schemeOperatedBy;
	}
	public String getSupplyServiceLevel() {
		return supplyServiceLevel;
	}
	public void setSupplyServiceLevel(String supplyServiceLevel) {
		this.supplyServiceLevel = supplyServiceLevel;
	}
	public String getSchemeUpgraded() {
		return schemeUpgraded;
	}
	public void setSchemeUpgraded(String schemeUpgraded) {
		this.schemeUpgraded = schemeUpgraded;
	}
	public String getOperationArrangementDate() {
		return operationArrangementDate;
	}
	public void setOperationArrangementDate(String operationArrangementDate) {
		this.operationArrangementDate = operationArrangementDate;
	}
	//Earlier Code Working
	/*public String getSchemeHours() {
		return schemeHours;
	}
	public void setSchemeHours(String schemeHours) {
		this.schemeHours = schemeHours;
	}
	public String getSchemeFP() {
		return schemeFP;
	}
	public void setSchemeFP(String schemeFP) {
		this.schemeFP = schemeFP;
	}*/
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VillageOperatingGridBean [schemeId=" + schemeId + ", villageId=" + villageId
				+ ", villageCommissioningDate=" + villageCommissioningDate + ", villageName=" + villageName
				+ ", habitationType=" + habitationType + ", schemeOperatedBy=" + schemeOperatedBy
				+ ", supplyServiceLevel=" + supplyServiceLevel + ", schemeUpgraded=" + schemeUpgraded
				+ ", operationArrangementDate=" + operationArrangementDate + ", villagecategory=" + villagecategory
				+ "]";
	}
	
	
}
