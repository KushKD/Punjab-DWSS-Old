package com.prwss.mis.masters.scheme.struts;

import java.io.Serializable;
import java.math.BigDecimal;

public class SchemeVillageCommissioningVillageGridBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1541279477362102979L;
	private String schemeId;
	private String villageId;
	private String villageCommissioningDate;
	private String villageName;
	private String habitationType;
	private String schemeOperatedBy;
	private String supplyServiceLevel;
	private String schemeUpgraded;
	
	/**
	 * KD CODE Start
	 * @return
	 */
	private String villageCategory;
	
	
	
	@Override
	public String toString() {
		return "SchemeVillageCommissioningVillageGridBean [schemeId="
				+ schemeId + ", villageId=" + villageId
				+ ", villageCommissioningDate=" + villageCommissioningDate
				+ ", villageName=" + villageName + ", habitationType="
				+ habitationType + ", schemeOperatedBy=" + schemeOperatedBy
				+ ", supplyServiceLevel=" + supplyServiceLevel
				+ ", schemeUpgraded=" + schemeUpgraded + ", villageCategory="
				+ villageCategory + "]";
	}
	
	public String getVillageCategory() {
		return villageCategory;
	}
	public void setVillageCategory(String villageCategory) {
		this.villageCategory = villageCategory;
	}
	public String getSchemeUpgraded() {
		return schemeUpgraded;
	}
	public void setSchemeUpgraded(String schemeUpgraded) {
		this.schemeUpgraded = schemeUpgraded;
	}
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
	public void setSchemeOperatedBy(String schemeOperatedBy) {
		this.schemeOperatedBy = schemeOperatedBy;
	}
	public String getSchemeOperatedBy() {
		return schemeOperatedBy;
	}
	 public void setSupplyServiceLevel(String supplyServiceLevel) {
		this.supplyServiceLevel = supplyServiceLevel;
	}
	 public String getSupplyServiceLevel() {
		return supplyServiceLevel;
	}
	
	
}
