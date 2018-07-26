package com.prwss.mis.pmm.village.struts;

import java.io.Serializable;

public class VillageHouseHoldGridBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2381128701050794214L;
	private long householdId;
	private long scHouseholds;
	private long gcHouseholds;
	private String asOnDate;
	
	
	public long getHouseholdId() {
		return householdId;
	}
	public void setHouseholdId(long householdId) {
		this.householdId = householdId;
	}
	public long getScHouseholds() {
		return scHouseholds;
	}
	public void setScHouseholds(long scHouseholds) {
		this.scHouseholds = scHouseholds;
	}
	public long getGcHouseholds() {
		return gcHouseholds;
	}
	public void setGcHouseholds(long gcHouseholds) {
		this.gcHouseholds = gcHouseholds;
	}
	public String getAsOnDate() {
		return asOnDate;
	}
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}
	
	
}
