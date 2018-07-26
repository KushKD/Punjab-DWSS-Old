/**
 * 
 */
package com.prwss.mis.pmm.watersupply.struts;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author Akash
 *
 */
public class WaterSupplyForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 460769886706057444L;

	private String locationId;
	private String villageId;
	private String blockId;
	private String dateOfTransaction;
	private String houseHolds;
	private String topography;
	private String cattlePopulation;
	private String typeOfRoads;
	private String widthOfRoad;
	private String sourceWater; 
	private String typeWaterSupply; 
	private String waterLevel;  
	private String groundwaterLevel;  
	private String waterQuality;  
	private String landRequirment;  
	private String availabilityOfLand;  
	private String landOwnership;  
	private String settlementsDistance; 
	private String waterBody; 
	private String usageWaterBody;
	private String waterBodyDistance; 
	private String landuseStpSite; 
	private String disposalLocation;
	private String receptorsWildlifeDistance;
	private String receptorsHealthcenterDistance; 
	private String receptorsReligiousStrDistance; 
	private String receptorsSchoolDistance;  
	private String municipalCorporataion;   
	private String noWaterbornDisease; 
	private String nameOfDisease;
	private String provisionLawnPlantation;//new
	private String rainWaterHarvesting; //new
	private String repairCleaning;//new
	private String solidWasteRemoval;//new
	private String publicAwareness;//new
	private String cleaningPondRequired;//new
	private String wasteWaterQuantity;//new
	private String currentSanitation;//new
	private String existingPondsSewageDisharge;//new
	
	
	public String getWasteWaterQuantity() {
		return wasteWaterQuantity;
	}
	public void setWasteWaterQuantity(String wasteWaterQuantity) {
		this.wasteWaterQuantity = wasteWaterQuantity;
	}
	public String getCurrentSanitation() {
		return currentSanitation;
	}
	public void setCurrentSanitation(String currentSanitation) {
		this.currentSanitation = currentSanitation;
	}
	public String getExistingPondsSewageDisharge() {
		return existingPondsSewageDisharge;
	}
	public void setExistingPondsSewageDisharge(String existingPondsSewageDisharge) {
		this.existingPondsSewageDisharge = existingPondsSewageDisharge;
	}
	public String getProvisionLawnPlantation() {
		return provisionLawnPlantation;
	}
	public void setProvisionLawnPlantation(String provisionLawnPlantation) {
		this.provisionLawnPlantation = provisionLawnPlantation;
	}
	public String getRainWaterHarvesting() {
		return rainWaterHarvesting;
	}
	public void setRainWaterHarvesting(String rainWaterHarvesting) {
		this.rainWaterHarvesting = rainWaterHarvesting;
	}
	public String getRepairCleaning() {
		return repairCleaning;
	}
	public void setRepairCleaning(String repairCleaning) {
		this.repairCleaning = repairCleaning;
	}
	public String getSolidWasteRemoval() {
		return solidWasteRemoval;
	}
	public void setSolidWasteRemoval(String solidWasteRemoval) {
		this.solidWasteRemoval = solidWasteRemoval;
	}
	public String getPublicAwareness() {
		return publicAwareness;
	}
	public void setPublicAwareness(String publicAwareness) {
		this.publicAwareness = publicAwareness;
	}
	public String getCleaningPondRequired() {
		return cleaningPondRequired;
	}
	public void setCleaningPondRequired(String cleaningPondRequired) {
		this.cleaningPondRequired = cleaningPondRequired;
	}
	public String getHouseHolds() {
		return houseHolds;
	}
	public void setHouseHolds(String houseHolds) {
		this.houseHolds = houseHolds;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(String dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	public String getTopography() {
		return topography;
	}
	public void setTopography(String topography) {
		this.topography = topography;
	}
	public String getCattlePopulation() {
		return cattlePopulation;
	}
	public void setCattlePopulation(String cattlePopulation) {
		this.cattlePopulation = cattlePopulation;
	}
	public String getTypeOfRoads() {
		return typeOfRoads;
	}
	public void setTypeOfRoads(String typeOfRoads) {
		this.typeOfRoads = typeOfRoads;
	}
	public String getWidthOfRoad() {
		return widthOfRoad;
	}
	public void setWidthOfRoad(String widthOfRoad) {
		this.widthOfRoad = widthOfRoad;
	}
	public String getSourceWater() {
		return sourceWater;
	}
	public void setSourceWater(String sourceWater) {
		this.sourceWater = sourceWater;
	}
	public String getTypeWaterSupply() {
		return typeWaterSupply;
	}
	public void setTypeWaterSupply(String typeWaterSupply) {
		this.typeWaterSupply = typeWaterSupply;
	}
	public String getWaterLevel() {
		return waterLevel;
	}
	public void setWaterLevel(String waterLevel) {
		this.waterLevel = waterLevel;
	}
	public String getGroundwaterLevel() {
		return groundwaterLevel;
	}
	public void setGroundwaterLevel(String groundwaterLevel) {
		this.groundwaterLevel = groundwaterLevel;
	}
	public String getWaterQuality() {
		return waterQuality;
	}
	public void setWaterQuality(String waterQuality) {
		this.waterQuality = waterQuality;
	}
	public String getLandRequirment() {
		return landRequirment;
	}
	public void setLandRequirment(String landRequirment) {
		this.landRequirment = landRequirment;
	}
	public String getAvailabilityOfLand() {
		return availabilityOfLand;
	}
	public void setAvailabilityOfLand(String availabilityOfLand) {
		this.availabilityOfLand = availabilityOfLand;
	}
	public String getLandOwnership() {
		return landOwnership;
	}
	public void setLandOwnership(String landOwnership) {
		this.landOwnership = landOwnership;
	}
	public String getSettlementsDistance() {
		return settlementsDistance;
	}
	public void setSettlementsDistance(String settlementsDistance) {
		this.settlementsDistance = settlementsDistance;
	}
	public String getWaterBody() {
		return waterBody;
	}
	public void setWaterBody(String waterBody) {
		this.waterBody = waterBody;
	}
	public String getUsageWaterBody() {
		return usageWaterBody;
	}
	public void setUsageWaterBody(String usageWaterBody) {
		this.usageWaterBody = usageWaterBody;
	}
	public String getWaterBodyDistance() {
		return waterBodyDistance;
	}
	public void setWaterBodyDistance(String waterBodyDistance) {
		this.waterBodyDistance = waterBodyDistance;
	}
	public String getLanduseStpSite() {
		return landuseStpSite;
	}
	public void setLanduseStpSite(String landuseStpSite) {
		this.landuseStpSite = landuseStpSite;
	}
	public String getDisposalLocation() {
		return disposalLocation;
	}
	public void setDisposalLocation(String disposalLocation) {
		this.disposalLocation = disposalLocation;
	}
	public String getReceptorsWildlifeDistance() {
		return receptorsWildlifeDistance;
	}
	public void setReceptorsWildlifeDistance(String receptorsWildlifeDistance) {
		this.receptorsWildlifeDistance = receptorsWildlifeDistance;
	}
	public String getReceptorsHealthcenterDistance() {
		return receptorsHealthcenterDistance;
	}
	public void setReceptorsHealthcenterDistance(
			String receptorsHealthcenterDistance) {
		this.receptorsHealthcenterDistance = receptorsHealthcenterDistance;
	}
	public String getReceptorsReligiousStrDistance() {
		return receptorsReligiousStrDistance;
	}
	public void setReceptorsReligiousStrDistance(
			String receptorsReligiousStrDistance) {
		this.receptorsReligiousStrDistance = receptorsReligiousStrDistance;
	}
	public String getReceptorsSchoolDistance() {
		return receptorsSchoolDistance;
	}
	public void setReceptorsSchoolDistance(String receptorsSchoolDistance) {
		this.receptorsSchoolDistance = receptorsSchoolDistance;
	}
	public String getMunicipalCorporataion() {
		return municipalCorporataion;
	}
	public void setMunicipalCorporataion(String municipalCorporataion) {
		this.municipalCorporataion = municipalCorporataion;
	}
	public String getNoWaterbornDisease() {
		return noWaterbornDisease;
	}
	public void setNoWaterbornDisease(String noWaterbornDisease) {
		this.noWaterbornDisease = noWaterbornDisease;
	}
	public String getNameOfDisease() {
		return nameOfDisease;
	}
	public void setNameOfDisease(String nameOfDisease) {
		this.nameOfDisease = nameOfDisease;
	}
	@Override
	public String toString() {
		return "WaterSupplyForm [locationId=" + locationId + ", villageId="
				+ villageId + ", blockId=" + blockId + ", dateOfTransaction="
				+ dateOfTransaction + ", houseHolds=" + houseHolds
				+ ", topography=" + topography + ", cattlePopulation="
				+ cattlePopulation + ", typeOfRoads=" + typeOfRoads
				+ ", widthOfRoad=" + widthOfRoad + ", sourceWater="
				+ sourceWater + ", typeWaterSupply=" + typeWaterSupply
				+ ", waterLevel=" + waterLevel + ", groundwaterLevel="
				+ groundwaterLevel + ", waterQuality=" + waterQuality
				+ ", landRequirment=" + landRequirment
				+ ", availabilityOfLand=" + availabilityOfLand
				+ ", landOwnership=" + landOwnership + ", settlementsDistance="
				+ settlementsDistance + ", waterBody=" + waterBody
				+ ", usageWaterBody=" + usageWaterBody + ", waterBodyDistance="
				+ waterBodyDistance + ", landuseStpSite=" + landuseStpSite
				+ ", disposalLocation=" + disposalLocation
				+ ", receptorsWildlifeDistance=" + receptorsWildlifeDistance
				+ ", receptorsHealthcenterDistance="
				+ receptorsHealthcenterDistance
				+ ", receptorsReligiousStrDistance="
				+ receptorsReligiousStrDistance + ", receptorsSchoolDistance="
				+ receptorsSchoolDistance + ", municipalCorporataion="
				+ municipalCorporataion + ", noWaterbornDisease="
				+ noWaterbornDisease + ", nameOfDisease=" + nameOfDisease
				+ ", provisionLawnPlantation=" + provisionLawnPlantation
				+ ", rainWaterHarvesting=" + rainWaterHarvesting
				+ ", repairCleaning=" + repairCleaning + ", solidWasteRemoval="
				+ solidWasteRemoval + ", publicAwareness=" + publicAwareness
				+ ", cleaningPondRequired=" + cleaningPondRequired
				+ ", wasteWaterQuantity=" + wasteWaterQuantity
				+ ", currentSanitation=" + currentSanitation
				+ ", existingPondsSewageDisharge="
				+ existingPondsSewageDisharge + "]";
	}
	
	
	
}
