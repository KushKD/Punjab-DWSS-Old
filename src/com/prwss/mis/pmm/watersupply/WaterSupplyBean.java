/**
 * 
 */
package com.prwss.mis.pmm.watersupply;

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
@Table(name="pmm_water_supply", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class WaterSupplyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7238517750294913445L;
	
	@Id
	@Column(name="location_id", nullable=false)
	private String locationId;
	@Id
	@Column(name="village_id", nullable=false)
	private String villageId;
	@Column(name="date_of_transaction")
	private Date dateOfTransaction;
	@Column(name="house_holds")
	private String houseHolds;
	@Column(name="topography")
	private String topography;
	@Column(name="cattle_population")
	private String cattlePopulation;
	@Column(name="type_of_roads")
	private String typeOfRoads;
	@Column(name="width_of_road")
	private String widthOfRoad;
	@Column(name="source_water")
	private String sourceWater; 
	@Column(name="type_water_supply")
	private String typeWaterSupply; 
	@Column(name="water_level")
	private String waterLevel;  
	@Column(name="groundwater_level")
	private String groundwaterLevel;  
	@Column(name="water_quality")
	private String waterQuality;  
	@Column(name="land_requirment")
	private String landRequirment;  
	@Column(name="availability_of_land")
	private String availabilityOfLand;  
	@Column(name="land_ownership")
	private String landOwnership;  
	@Column(name="settlements_distance")
	private String settlementsDistance; 
	@Column(name="water_body")
	private String waterBody; 
	@Column(name="usage_water_body")
	private String usageWaterBody;
	@Column(name="water_body_distance")
	private String waterBodyDistance; 
	@Column(name="landuse_stp_site")
	private String landuseStpSite; 
	@Column(name="disposal_location")
	private String disposalLocation;
	@Column(name="receptors_wildlife_distance")
	private String receptorsWildlifeDistance;
	@Column(name="receptors_healthcenter_distance")
	private String receptorsHealthcenterDistance; 
	@Column(name="receptors_religious_str_distance")
	private String receptorsReligiousStrDistance; 
	@Column(name="receptors_school_distance")
	private String receptorsSchoolDistance;  
	@Column(name="municipal_corporataion")
	private String municipalCorporataion;   
	@Column(name="no_waterborn_disease")
	private String noWaterbornDisease; 
	@Column(name="name_of_disease")
	private String nameOfDisease; 
	
	
	//new fields
	@Column(name="provision_lawn_plantation")
	private String provisionLawnPlantation;//new
	@Column(name="rain_water_harvesting")
	private String rainWaterHarvesting; //new
	@Column(name="repair_cleaning")
	private String repairCleaning;//new
	@Column(name="solid_waste_removal")
	private String solidWasteRemoval;//new
	@Column(name="public_awareness")
	private String publicAwareness;//new
	@Column(name="cleaning_pond_required")
	private String cleaningPondRequired;//new
	@Column(name="waste_water_quantity")
	private String wasteWaterQuantity;//new
	@Column(name="current_sanitation")
	private String currentSanitation;//new
	@Column(name="existing_ponds_sewage_discharge")
	private String existingPondsSewageDischarge;//new
	
	
	@Embedded
	private MISAuditBean misAuditBean;

	
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

	
	public String getExistingPondsSewageDischarge() {
		return existingPondsSewageDischarge;
	}

	public void setExistingPondsSewageDischarge(String existingPondsSewageDischarge) {
		this.existingPondsSewageDischarge = existingPondsSewageDischarge;
	}

	public String getHouseHolds() {
		return houseHolds;
	}

	public void setHouseHolds(String houseHolds) {
		this.houseHolds = houseHolds;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
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

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	@Override
	public String toString() {
		return "WaterSupplyBean [locationId=" + locationId + ", topography="
				+ topography + ", cattlePopulation=" + cattlePopulation
				+ ", typeOfRoads=" + typeOfRoads + ", widthOfRoad="
				+ widthOfRoad + ", sourceWater=" + sourceWater
				+ ", typeWaterSupply=" + typeWaterSupply + ", waterLevel="
				+ waterLevel + ", groundwaterLevel=" + groundwaterLevel
				+ ", waterQuality=" + waterQuality + ", landRequirment="
				+ landRequirment + ", availabilityOfLand=" + availabilityOfLand
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
				+ ", misAuditBean=" + misAuditBean + "]";
	}
	  

	

}
