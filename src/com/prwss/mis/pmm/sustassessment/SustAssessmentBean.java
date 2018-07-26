/**
 * 
 */
package com.prwss.mis.pmm.sustassessment;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author pjha
 *
 */
@Entity
@Table(name="pmm_sust_assessment", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SustAssessmentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5521022302632703870L;
	
	@Id
	@Column(name="scheme_id", nullable=false)
	private String schemeId;
	@Id
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	@Column(name="location_id", nullable=false)
	private String locationId;
	
	@Column(name="sourcefail_summer")
	private int sourcefailSummer;
	@Id
	@Column(name="assessment_date")
	private Date assessmentDate;

	@Column(name="disinfection_daily")
	private int disinfectionDaily;
	
	@Column(name="source_remained_potable ")
	private int sourceRemainedPotable;
     
    @Column(name="proper_protection_arrangement")
	private int properProtectionArrangement;
	
    @Column(name="year_inclusive_maintenance_shut")
    private int yearInclusiveMaintenanceShut;
	
    @Column(name="supply_water_less_than50Percent")
    private int supplyWaterLessThan50Percent;
	
    @Column(name="total_household_pitTaps")
	private int totalHouseholdPitTaps;
	
    @Column(name="supply_water_less_than120Percent")
	private int supplyWaterLessThan120Percent;
	
    @Column(name="electric_ConsumGPWSC")
	private int electricConsumGPWSC;
	
    @Column(name="revenue_collected_moreThan50Percent")
	private int revenueCollectedMoreThan50Percent;
   
    @Column(name="revenue_collected_moreThan90Percent")
	private int revenueCollectedMoreThan90Percent;
   
    @Column(name="revenue_collected_moreThanOMExpenditure")
	private int revenueCollectedMoreThanOMExpenditure;
	
    @Column(name="prepared_annual_oMPlan")
    private int preparedAnnualOMPlan;
	
    @Column(name="annual_meetings_held")
    private int annualMeetingsHeld;
	
    @Column(name="prepared_approvedSOA")
    private int preparedApprovedSOA;
	
    @Column(name="women_moreThan33Percent")
	private int womenMoreThan33Percent;
	
	
	@Embedded
	private MISAuditBean misAuditBean;


	
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



	public Date getAssessmentDate() {
		return assessmentDate;
	}



	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
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



	public int getSupplyWaterLessThan50Percent() {
		return supplyWaterLessThan50Percent;
	}



	public void setSupplyWaterLessThan50Percent(int supplyWaterLessThan50Percent) {
		this.supplyWaterLessThan50Percent = supplyWaterLessThan50Percent;
	}



	public int getTotalHouseholdPitTaps() {
		return totalHouseholdPitTaps;
	}



	public void setTotalHouseholdPitTaps(int totalHouseholdPitTaps) {
		this.totalHouseholdPitTaps = totalHouseholdPitTaps;
	}



	public int getSupplyWaterLessThan120Percent() {
		return supplyWaterLessThan120Percent;
	}



	public void setSupplyWaterLessThan120Percent(int supplyWaterLessThan120Percent) {
		this.supplyWaterLessThan120Percent = supplyWaterLessThan120Percent;
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



	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}



	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}



	@Override
	public String toString() {
		return "SustAssessmentBean [schemeId=" + schemeId + ", villageId="
				+ villageId + ", locationId=" + locationId
				+ ", sourcefailSummer=" + sourcefailSummer
				+ ", assessmentDate=" + assessmentDate + ", disinfectionDaily="
				+ disinfectionDaily + ", sourceRemainedPotable="
				+ sourceRemainedPotable + ", properProtectionArrangement="
				+ properProtectionArrangement
				+ ", yearInclusiveMaintenanceShut="
				+ yearInclusiveMaintenanceShut
				+ ", supplyWaterLessThan50Percent="
				+ supplyWaterLessThan50Percent + ", totalHouseholdPitTaps="
				+ totalHouseholdPitTaps + ", supplyWaterLessThan120Percent="
				+ supplyWaterLessThan120Percent + ", electricConsumGPWSC="
				+ electricConsumGPWSC + ", revenueCollectedMoreThan50Percent="
				+ revenueCollectedMoreThan50Percent
				+ ", revenueCollectedMoreThan90Percent="
				+ revenueCollectedMoreThan90Percent
				+ ", revenueCollectedMoreThanOMExpenditure="
				+ revenueCollectedMoreThanOMExpenditure
				+ ", preparedAnnualOMPlan=" + preparedAnnualOMPlan
				+ ", annualMeetingsHeld=" + annualMeetingsHeld
				+ ", preparedApprovedSOA=" + preparedApprovedSOA
				+ ", womenMoreThan33Percent=" + womenMoreThan33Percent
				+ ", misAuditBean=" + misAuditBean + "]";
	}
	
	

	
	  

	
	

}
