/**
 * 
 */
package com.prwss.mis.pmm.sustassessment.struts;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author Akash
 *
 */
public class SustAssessmentForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 464524999489765038L;
	private String villageId;
	private String schemeId;
	private String locationId;
	private String blockId;
	private int sourcefailSummer;
	private int disinfectionDaily;
	private int sourceRemainedPotable;
	private int properProtectionArrangement;
	private int yearInclusiveMaintenanceShut;
	private int supplyWaterLessThan50Percent;
	private int totalHouseholdPitTaps;
	private int supplyWaterLessThan120Percent;
	private int electricConsumGPWSC;
	private int revenueCollectedMoreThan50Percent;
	private int revenueCollectedMoreThan90Percent;
	private int revenueCollectedMoreThanOMExpenditure;
	private int preparedAnnualOMPlan;
	private int annualMeetingsHeld;
	private int preparedApprovedSOA;
	private int womenMoreThan33Percent;
	private String assessmentDate;
	private String villageName;
	private String schemeName;
	private String status;
		
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getAssessmentDate() {
		return assessmentDate;
	}
	public void setAssessmentDate(String assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public int getSupplyWaterLessThan50Percent() {
		return supplyWaterLessThan50Percent;
	}
	public void setSupplyWaterLessThan50Percent(int supplyWaterLessThan50Percent) {
		this.supplyWaterLessThan50Percent = supplyWaterLessThan50Percent;
	}
	public int getSupplyWaterLessThan120Percent() {
		return supplyWaterLessThan120Percent;
	}
	public void setSupplyWaterLessThan120Percent(int supplyWaterLessThan120Percent) {
		this.supplyWaterLessThan120Percent = supplyWaterLessThan120Percent;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public int getSourcefailSummer() {
		return sourcefailSummer;
	}
	public void setSourcefailSummer(int sourcefailSummer) {
		this.sourcefailSummer = sourcefailSummer;
	}
	public int getDisinfectionDaily() {
		return disinfectionDaily;
	}
	public void setDisinfectionDaily(int disinfectionDaily) {
		this.disinfectionDaily = disinfectionDaily;
	}
	public int getSourceRemainedPotable() {
		return sourceRemainedPotable;
	}
	public void setSourceRemainedPotable(int sourceRemainedPotable) {
		this.sourceRemainedPotable = sourceRemainedPotable;
	}
	public int getProperProtectionArrangement() {
		return properProtectionArrangement;
	}
	public void setProperProtectionArrangement(int properProtectionArrangement) {
		this.properProtectionArrangement = properProtectionArrangement;
	}
	public int getYearInclusiveMaintenanceShut() {
		return yearInclusiveMaintenanceShut;
	}
	public void setYearInclusiveMaintenanceShut(int yearInclusiveMaintenanceShut) {
		this.yearInclusiveMaintenanceShut = yearInclusiveMaintenanceShut;
	}
	public int getTotalHouseholdPitTaps() {
		return totalHouseholdPitTaps;
	}
	public void setTotalHouseholdPitTaps(int totalHouseholdPitTaps) {
		this.totalHouseholdPitTaps = totalHouseholdPitTaps;
	}
	public int getElectricConsumGPWSC() {
		return electricConsumGPWSC;
	}
	public void setElectricConsumGPWSC(int electricConsumGPWSC) {
		this.electricConsumGPWSC = electricConsumGPWSC;
	}
	public int getRevenueCollectedMoreThan50Percent() {
		return revenueCollectedMoreThan50Percent;
	}
	public void setRevenueCollectedMoreThan50Percent(
			int revenueCollectedMoreThan50Percent) {
		this.revenueCollectedMoreThan50Percent = revenueCollectedMoreThan50Percent;
	}
	public int getRevenueCollectedMoreThan90Percent() {
		return revenueCollectedMoreThan90Percent;
	}
	
	public void setRevenueCollectedMoreThan90Percent(
			int revenueCollectedMoreThan90Percent) {
		this.revenueCollectedMoreThan90Percent = revenueCollectedMoreThan90Percent;
	}
	
	public int getRevenueCollectedMoreThanOMExpenditure() {
		return revenueCollectedMoreThanOMExpenditure;
	}
	
	public void setRevenueCollectedMoreThanOMExpenditure(
			int revenueCollectedMoreThanOMExpenditure) {
		this.revenueCollectedMoreThanOMExpenditure = revenueCollectedMoreThanOMExpenditure;
	}
	public int getPreparedAnnualOMPlan() {
		return preparedAnnualOMPlan;
	}
	public void setPreparedAnnualOMPlan(int preparedAnnualOMPlan) {
		this.preparedAnnualOMPlan = preparedAnnualOMPlan;
	}
	public int getAnnualMeetingsHeld() {
		return annualMeetingsHeld;
	}
	public void setAnnualMeetingsHeld(int annualMeetingsHeld) {
		this.annualMeetingsHeld = annualMeetingsHeld;
	}
	public int getPreparedApprovedSOA() {
		return preparedApprovedSOA;
	}
	public void setPreparedApprovedSOA(int preparedApprovedSOA) {
		this.preparedApprovedSOA = preparedApprovedSOA;
	}
	public int getWomenMoreThan33Percent() {
		return womenMoreThan33Percent;
	}
	public void setWomenMoreThan33Percent(int womenMoreThan33Percent) {
		this.womenMoreThan33Percent = womenMoreThan33Percent;
	}
}
