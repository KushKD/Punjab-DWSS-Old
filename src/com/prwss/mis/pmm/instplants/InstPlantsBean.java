package com.prwss.mis.pmm.instplants;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="pmm_installation_plants", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class InstPlantsBean implements Serializable{
	private static final long serialVersionUID = -7207841195269120440L;
	
	@Id	
	@Column(name="location_id", nullable=false)
	private String locationId; 
	
	@Id	
	@Column(name="transaction_date", nullable=false)
	private Date transactionDate;
	
	@Column(name="block_id")
	private String blockId;
	
	
	@Column(name="village_id")
	private String villageId;
	

	
	@Column(name="type_plant")
	private String typePlant;
	@Column(name="division")
	private String division;
	@Column(name="clustor_number")
	private String clustorNumber;
	@Column(name="site_selection")
	private String siteSelection;
	@Column(name="electric_connection")
	private String electricConnection;
	@Column(name="villages_covered")
	private long villagesCovered;
	@Column(name="cluster_number")
	private String clusterNumber;
	@Column(name="tubewell_allotted")
	private String tubewellAllotted;
	@Column(name="tubewell_inprogress")
	private long tubewellInprogress;
	@Column(name="tubewell_completed")
	private String tubewellCompleted;
	@Column(name="tubewell_housing_completed")
	private String tubewellHousingCompleted;
	@Column(name="tubewell_plant_arranged")
	private String tubewellPlantArranged;
	@Column(name="tubewell_plant_installed")
	private String tubewellPlantInstalled;
	@Column(name="tubewell_commissioning_plant")
	private String tubewellCommissioningPlant;
	@Column(name="tubewell_release_electric")
	private String tubewellReleaseElectric;
	@Column(name="tubewell_physical_completion")
	private long tubewellPhysicalCompletion;
	@Column(name="tubewell_date_start")
	private Date tubewellDateStart;
	
	@Column(name="tubewell_machinery_arranged")
	private String tubewellMachineryArranged;
	
	@Column(name="tubewell_machinery_installed")
	private String tubewellMachineryInstalled;
	
	@Column(name="tubewell_platform_completed")
	private String tubewellPlatformCompleted;
		
	@Column(name="stipulated_date_completion")
	private Date stipulatedDateCompletion;
	
	@Column(name="actual_date_completion")
	private Date actualDateCompletion;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	
	public String getTubewellMachineryArranged() {
		return tubewellMachineryArranged;
	}
	public void setTubewellMachineryArranged(String tubewellMachineryArranged) {
		this.tubewellMachineryArranged = tubewellMachineryArranged;
	}
	public String getTubewellMachineryInstalled() {
		return tubewellMachineryInstalled;
	}
	public void setTubewellMachineryInstalled(String tubewellMachineryInstalled) {
		this.tubewellMachineryInstalled = tubewellMachineryInstalled;
	}
	public String getTubewellPlatformCompleted() {
		return tubewellPlatformCompleted;
	}
	public void setTubewellPlatformCompleted(String tubewellPlatformCompleted) {
		this.tubewellPlatformCompleted = tubewellPlatformCompleted;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getTypePlant() {
		return typePlant;
	}
	public void setTypePlant(String typePlant) {
		this.typePlant = typePlant;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getClustorNumber() {
		return clustorNumber;
	}
	public void setClustorNumber(String clustorNumber) {
		this.clustorNumber = clustorNumber;
	}
	public String getSiteSelection() {
		return siteSelection;
	}
	public void setSiteSelection(String siteSelection) {
		this.siteSelection = siteSelection;
	}
	public String getElectricConnection() {
		return electricConnection;
	}
	public void setElectricConnection(String electricConnection) {
		this.electricConnection = electricConnection;
	}
	public long getVillagesCovered() {
		return villagesCovered;
	}
	public void setVillagesCovered(long villagesCovered) {
		this.villagesCovered = villagesCovered;
	}
	public String getClusterNumber() {
		return clusterNumber;
	}
	public void setClusterNumber(String clusterNumber) {
		this.clusterNumber = clusterNumber;
	}
	public String getTubewellAllotted() {
		return tubewellAllotted;
	}
	public void setTubewellAllotted(String tubewellAllotted) {
		this.tubewellAllotted = tubewellAllotted;
	}
	public long getTubewellInprogress() {
		return tubewellInprogress;
	}
	public void setTubewellInprogress(long tubewellInprogress) {
		this.tubewellInprogress = tubewellInprogress;
	}
	public String getTubewellCompleted() {
		return tubewellCompleted;
	}
	public void setTubewellCompleted(String tubewellCompleted) {
		this.tubewellCompleted = tubewellCompleted;
	}
	public String getTubewellHousingCompleted() {
		return tubewellHousingCompleted;
	}
	public void setTubewellHousingCompleted(String tubewellHousingCompleted) {
		this.tubewellHousingCompleted = tubewellHousingCompleted;
	}
	public String getTubewellPlantArranged() {
		return tubewellPlantArranged;
	}
	public void setTubewellPlantArranged(String tubewellPlantArranged) {
		this.tubewellPlantArranged = tubewellPlantArranged;
	}
	public String getTubewellPlantInstalled() {
		return tubewellPlantInstalled;
	}
	public void setTubewellPlantInstalled(String tubewellPlantInstalled) {
		this.tubewellPlantInstalled = tubewellPlantInstalled;
	}
	public String getTubewellCommissioningPlant() {
		return tubewellCommissioningPlant;
	}
	public void setTubewellCommissioningPlant(String tubewellCommissioningPlant) {
		this.tubewellCommissioningPlant = tubewellCommissioningPlant;
	}
	public String getTubewellReleaseElectric() {
		return tubewellReleaseElectric;
	}
	public void setTubewellReleaseElectric(String tubewellReleaseElectric) {
		this.tubewellReleaseElectric = tubewellReleaseElectric;
	}
	
	public long getTubewellPhysicalCompletion() {
		return tubewellPhysicalCompletion;
	}
	public void setTubewellPhysicalCompletion(long tubewellPhysicalCompletion) {
		this.tubewellPhysicalCompletion = tubewellPhysicalCompletion;
	}
	public Date getTubewellDateStart() {
		return tubewellDateStart;
	}
	public void setTubewellDateStart(Date tubewellDateStart) {
		this.tubewellDateStart = tubewellDateStart;
	}
	public Date getStipulatedDateCompletion() {
		return stipulatedDateCompletion;
	}
	public void setStipulatedDateCompletion(Date stipulatedDateCompletion) {
		this.stipulatedDateCompletion = stipulatedDateCompletion;
	}
	public Date getActualDateCompletion() {
		return actualDateCompletion;
	}
	public void setActualDateCompletion(Date actualDateCompletion) {
		this.actualDateCompletion = actualDateCompletion;
	
	}
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}
	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	@Override
	public String toString() {
		return "InstPlantsBean [locationId=" + locationId + ", typePlant="
				+ typePlant + ", division=" + division + ", clustorNumber="
				+ clustorNumber + ", siteSelection=" + siteSelection
				+ ", electricConnection=" + electricConnection
				+ ", villagesCovered=" + villagesCovered + ", clusterNumber="
				+ clusterNumber + ", tubewellAllotted=" + tubewellAllotted
				+ ", tubewellInprogress=" + tubewellInprogress
				+ ", tubewellCompleted=" + tubewellCompleted
				+ ", tubewellHousingCompleted=" + tubewellHousingCompleted
				+ ", tubewellPlantArranged=" + tubewellPlantArranged
				+ ", tubewellPlantInstalled=" + tubewellPlantInstalled
				+ ", tubewellCommissioningPlant=" + tubewellCommissioningPlant
				+ ", tubewellReleaseElectric=" + tubewellReleaseElectric
				+ ", tubewellPhysicalCompletion=" + tubewellPhysicalCompletion
				+ ", tubewellDateStart=" + tubewellDateStart
				+ ", stipulatedDateCompletion=" + stipulatedDateCompletion
				+ ", actualDateCompletion=" + actualDateCompletion
				+ ", misAuditBean=" + misAuditBean + "]";
	}
	 
	  
	

	

}
