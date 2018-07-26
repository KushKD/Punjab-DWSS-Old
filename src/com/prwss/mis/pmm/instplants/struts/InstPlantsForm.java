/**
 * 
 */
package com.prwss.mis.pmm.instplants.struts;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author Akash
 *
 */
public class InstPlantsForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2410428388000153242L;
	private String locationId;
	private String transactionDate;
	private String typePlant;
	private String division;
	private String clustorNumber;
	private String siteSelection;
	private String electricConnection;
	private long villagesCovered;
	private String clusterNumber;
	private String tubewellAllotted;
	private long tubewellInprogress;
	private String tubewellCompleted;
	private String tubewellHousingCompleted;
	private String tubewellPlantArranged;
	private String tubewellPlantInstalled;
	private String tubewellCommissioningPlant;
	private String tubewellReleaseElectric;
	private long tubewellPhysicalCompletion;
	private String tubewellDateStart;
	private String stipulatedDateCompletion;
	private String actualDateCompletion;
	private String machineryArranged;//to be added
	private String machineryInstalled;//to be added
	private String platformCompleted;


	private String villageId;
	private String blockId;

	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getMachineryArranged() {
		return machineryArranged;
	}
	public void setMachineryArranged(String machineryArranged) {
		this.machineryArranged = machineryArranged;
	}
	public String getMachineryInstalled() {
		return machineryInstalled;
	}
	public void setMachineryInstalled(String machineryInstalled) {
		this.machineryInstalled = machineryInstalled;
	}
	public String getPlatformCompleted() {
		return platformCompleted;
	}
	public void setPlatformCompleted(String platformCompleted) {
		this.platformCompleted = platformCompleted;
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
	public String getTubewellDateStart() {
		return tubewellDateStart;
	}
	public void setTubewellDateStart(String tubewellDateStart) {
		this.tubewellDateStart = tubewellDateStart;
	}
	public String getStipulatedDateCompletion() {
		return stipulatedDateCompletion;
	}
	public void setStipulatedDateCompletion(String stipulatedDateCompletion) {
		this.stipulatedDateCompletion = stipulatedDateCompletion;
	}
	public String getActualDateCompletion() {
		return actualDateCompletion;
	}
	public void setActualDateCompletion(String actualDateCompletion) {
		this.actualDateCompletion = actualDateCompletion;
	}
	
	
}
