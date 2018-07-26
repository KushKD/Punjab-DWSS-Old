/**
 * 
 */
package com.prwss.mis.pmm.environment;

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
@Table(name="pmm_environment", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EnvironmentBean implements Serializable {

	/**
	 * Temperature to changed as Character varying in table
	 */
	private static final long serialVersionUID = -1842113027289340482L;
	@Id
	@Column(name="location_id", nullable=false)
	private String locationId;
	
	@Id
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	
	@Column(name="date_of_transaction")
	private Date dateOfTransaction;
	
	@Column(name="topography")
	private String topography;
	@Column(name="type_of_soil")
	private String typeOfSoil;
	@Column(name="intensity_of_rainfall")
	private String intensityOfRainfall;
	@Column(name="temp_max")
	private String tempMax;
	@Column(name="temp_min")
	private String tempMin;
	@Column(name="slope_of_land")
	private String slopeOfLand;
	@Column(name="wind_direction")
	private String windDirection;
	@Column(name="water_table")
	private String waterTable;
	@Column(name="existing_water_body")
	private String existingWaterBody;
	
	@Column(name="existing_water_body_other")
	private String existingWaterBodyOther;
	
	@Column(name="pond_use")
	private String pondUse;
	@Column(name="water_logging_area_name")
	private String waterLoggingAreaName;
	@Column(name="water_logging_area")
	private String waterLoggingArea;
	@Column(name="water_logging_period")
	private String waterLoggingPeriod;
	
	@Column(name="population_effected_water_logging")
	private long populationEffectedWaterLogging;
	
	@Column(name="contamination_drinking_water")
	private String contaminationDrinkingWater;
	
	@Column(name="road_width_max_min")
	private String roadWidthMaxMin;
	
	@Column(name="existing_roads")
	private String existingRoads;
	@Column(name="local_vegetation")
	private String localVegetation;
	@Column(name="population")
	private long population;
	@Column(name="number_household")
	private long numberHousehold;
	
	@Column(name="land_use_pattern")
	private String landUsePattern;
	
	@Column(name="historical_importance")
	private String historicalImportance;
	
	@Column(name="major_source_income")
	private String majorSourceIncome;
	
	@Column(name="incident_waterborn")
	private String incidentWaterborn;
	
	@Column(name="disease_name")
	private String diseaseName;
	
	@Column(name="vector_disease")
	private String vectorDisease;
	
	@Column(name="scheme_type")
	private String schemeType;
	
	@Column(name="source_of_drinking_water")
	private String sourceOfDrinkingWater;
	
	@Column(name="water_availability")
	private String waterAvailability;
	
	@Column(name="source_water_assessed")
	private String sourceWaterAssessed;
	
	@Column(name="nature_of_problem")
	private String natureOfProblem;
	
	@Column(name="risk_contamination")
	private String riskContamination;
	
	@Column(name="affect_natural_habitats")
	private String affectNaturalHabitats;
	
	@Column(name="infringe_local_rights")
	private String infringeLocalRights;
	
	@Column(name="treatment_technology")
	private String treatmentTechnology;
	
	@Column(name="treament_proposed")
	private String treamentProposed;
	
	@Column(name="disinfection_exists")
	private String disinfectionExists;
	
	@Column(name="ionisation_plant")
	private String ionisationPlant;
	
	@Column(name="ant_water_avlb_issue")
	private String antWaterAvlbIssue;
	@Column(name="ant_water_avlb_mitigation")
	private String antWaterAvlbMitigation;
	
	@Column(name="ant_water_qult_issue")
	private String antWaterQultIssue;
	
	@Column(name="ant_water_qult_mitigation")
	private String antWaterQultMitigation;
	
	@Column(name="sanitation_issue")
	private String sanitationIssue;
	
	@Column(name="sanitation_mitigation")
	private String sanitationMitigation;
	
	@Column(name="construction_issue")
	private String constructionIssue;
	
	@Column(name="construction_mitigation")
	private String constructionMitigation;
	@Column(name="construction_wastes_issue")
	private String constructionWastesIssue;
	@Column(name="construction_wastes_mitigation")
	private String constructionWastesMitigation;
	
	@Column(name="sewage_scheme_type")
	private String sewageSchemeType;
	@Column(name="sewage_practices")
	private String sewagePractices;
	
	@Column(name="sewage_pattern")
	private String sewagePattern;
	@Column(name="wastewater_cattle")
	private String wastewaterCattle;
	
	@Column(name="grey_black_water_mix")
	private String greyBlackWaterMix;
	
	@Column(name="sanitation_feedback")
	private String sanitationFeedback;
	@Column(name="wastewater_quantity")
	private String wastewaterQuantity;
	
	@Column(name="method_treatment")
	private String methodTreatment;
	@Column(name="ponds_stp")
	private String pondsStp;
	
	@Column(name="ponds_distance_settlement")
	private String pondsDistanceSettlement;
	@Column(name="ponds_distance_school")
	private String pondsDistanceSchool;
	@Column(name="ponds_water_quality")
	private String pondsWaterQuality;
	@Column(name="ponds_expansion_required")
	private String pondsExpansionRequired;
	@Column(name="ponds_expansion_land")
	private String pondsExpansionLand;
	@Column(name="land_use")
	private String landUse;

	@Column(name="ponds_site_plantation")
	private String pondsSitePlantation;
	@Column(name="contamination_drinking_water_source")
	private String contaminationDrinkingWaterSource;
	
	@Column(name="domestic_solid_waste")
	private String domesticSolidWaste;
	@Column(name="landfill_site_solid_waste")
	private String landfillSiteSolidWaste;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	
	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}
	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
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
	public String getTypeOfSoil() {
		return typeOfSoil;
	}
	public void setTypeOfSoil(String typeOfSoil) {
		this.typeOfSoil = typeOfSoil;
	}
	public String getIntensityOfRainfall() {
		return intensityOfRainfall;
	}
	public void setIntensityOfRainfall(String intensityOfRainfall) {
		this.intensityOfRainfall = intensityOfRainfall;
	}
	public String getTempMax() {
		return tempMax;
	}
	public void setTempMax(String tempMax) {
		this.tempMax = tempMax;
	}
	public String getTempMin() {
		return tempMin;
	}
	public void setTempMin(String tempMin) {
		this.tempMin = tempMin;
	}
	public String getSlopeOfLand() {
		return slopeOfLand;
	}
	public void setSlopeOfLand(String slopeOfLand) {
		this.slopeOfLand = slopeOfLand;
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public String getWaterTable() {
		return waterTable;
	}
	public void setWaterTable(String waterTable) {
		this.waterTable = waterTable;
	}
	public String getExistingWaterBody() {
		return existingWaterBody;
	}
	public void setExistingWaterBody(String existingWaterBody) {
		this.existingWaterBody = existingWaterBody;
	}
	public String getPondUse() {
		return pondUse;
	}
	public void setPondUse(String pondUse) {
		this.pondUse = pondUse;
	}
	public String getWaterLoggingAreaName() {
		return waterLoggingAreaName;
	}
	public void setWaterLoggingAreaName(String waterLoggingAreaName) {
		this.waterLoggingAreaName = waterLoggingAreaName;
	}
	public String getWaterLoggingArea() {
		return waterLoggingArea;
	}
	public void setWaterLoggingArea(String waterLoggingArea) {
		this.waterLoggingArea = waterLoggingArea;
	}
	public String getWaterLoggingPeriod() {
		return waterLoggingPeriod;
	}
	public void setWaterLoggingPeriod(String waterLoggingPeriod) {
		this.waterLoggingPeriod = waterLoggingPeriod;
	}
	public long getPopulationEffectedWaterLogging() {
		return populationEffectedWaterLogging;
	}
	public void setPopulationEffectedWaterLogging(
			long populationEffectedWaterLogging) {
		this.populationEffectedWaterLogging = populationEffectedWaterLogging;
	}
	public String getContaminationDrinkingWater() {
		return contaminationDrinkingWater;
	}
	public void setContaminationDrinkingWater(String contaminationDrinkingWater) {
		this.contaminationDrinkingWater = contaminationDrinkingWater;
	}
	public String getRoadWidthMaxMin() {
		return roadWidthMaxMin;
	}
	public void setRoadWidthMaxMin(String roadWidthMaxMin) {
		this.roadWidthMaxMin = roadWidthMaxMin;
	}
	public String getExistingRoads() {
		return existingRoads;
	}
	public void setExistingRoads(String existingRoads) {
		this.existingRoads = existingRoads;
	}
	public String getLocalVegetation() {
		return localVegetation;
	}
	public void setLocalVegetation(String localVegetation) {
		this.localVegetation = localVegetation;
	}
	public long getPopulation() {
		return population;
	}
	public void setPopulation(long population) {
		this.population = population;
	}
	public long getNumberHousehold() {
		return numberHousehold;
	}
	public void setNumberHousehold(long numberHousehold) {
		this.numberHousehold = numberHousehold;
	}
	public String getLandUse_pattern() {
		return landUsePattern;
	}
	public void setLandUse_pattern(String landUse_pattern) {
		this.landUsePattern = landUse_pattern;
	}
	public String getHistoricalImportance() {
		return historicalImportance;
	}
	public void setHistoricalImportance(String historicalImportance) {
		this.historicalImportance = historicalImportance;
	}
	public String getMajorSourceIncome() {
		return majorSourceIncome;
	}
	public void setMajorSourceIncome(String majorSourceIncome) {
		this.majorSourceIncome = majorSourceIncome;
	}
	public String getIncidentWaterborn() {
		return incidentWaterborn;
	}
	public void setIncidentWaterborn(String incidentWaterborn) {
		this.incidentWaterborn = incidentWaterborn;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getVectorDisease() {
		return vectorDisease;
	}
	public void setVectorDisease(String vectorDisease) {
		this.vectorDisease = vectorDisease;
	}
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public String getSourceOfDrinkingWater() {
		return sourceOfDrinkingWater;
	}
	public void setSourceOfDrinkingWater(String sourceOfDrinkingWater) {
		this.sourceOfDrinkingWater = sourceOfDrinkingWater;
	}
	public String getWaterAvailability() {
		return waterAvailability;
	}
	public void setWaterAvailability(String waterAvailability) {
		this.waterAvailability = waterAvailability;
	}
	public String getSourceWaterAssessed() {
		return sourceWaterAssessed;
	}
	public void setSourceWaterAssessed(String sourceWaterAssessed) {
		this.sourceWaterAssessed = sourceWaterAssessed;
	}
	public String getNatureOfProblem() {
		return natureOfProblem;
	}
	public void setNatureOfProblem(String natureOfProblem) {
		this.natureOfProblem = natureOfProblem;
	}
	public String getRiskContamination() {
		return riskContamination;
	}
	public void setRiskContamination(String riskContamination) {
		this.riskContamination = riskContamination;
	}
	public String getAffectNaturalHabitats() {
		return affectNaturalHabitats;
	}
	public void setAffectNaturalHabitats(String affectNaturalHabitats) {
		this.affectNaturalHabitats = affectNaturalHabitats;
	}
	public String getInfringeLocalRights() {
		return infringeLocalRights;
	}
	public void setInfringeLocalRights(String infringeLocalRights) {
		this.infringeLocalRights = infringeLocalRights;
	}
	public String getTreatmentTechnology() {
		return treatmentTechnology;
	}
	public void setTreatmentTechnology(String treatmentTechnology) {
		this.treatmentTechnology = treatmentTechnology;
	}
	public String getTreamentProposed() {
		return treamentProposed;
	}
	public void setTreamentProposed(String treamentProposed) {
		this.treamentProposed = treamentProposed;
	}
	public String getDisinfectionExists() {
		return disinfectionExists;
	}
	public void setDisinfectionExists(String disinfectionExists) {
		this.disinfectionExists = disinfectionExists;
	}
	public String getIonisationPlant() {
		return ionisationPlant;
	}
	public void setIonisationPlant(String ionisationPlant) {
		this.ionisationPlant = ionisationPlant;
	}
	public String getAntWaterAvlbIssue() {
		return antWaterAvlbIssue;
	}
	public void setAntWaterAvlbIssue(String antWaterAvlbIssue) {
		this.antWaterAvlbIssue = antWaterAvlbIssue;
	}
	public String getAntWaterAvlbMitigation() {
		return antWaterAvlbMitigation;
	}
	public void setAntWaterAvlbMitigation(String antWaterAvlbMitigation) {
		this.antWaterAvlbMitigation = antWaterAvlbMitigation;
	}
	public String getAntWaterQultIssue() {
		return antWaterQultIssue;
	}
	public void setAntWaterQultIssue(String antWaterQultIssue) {
		this.antWaterQultIssue = antWaterQultIssue;
	}
	public String getAntWaterQultMitigation() {
		return antWaterQultMitigation;
	}
	public void setAntWaterQultMitigation(String antWaterQultMitigation) {
		this.antWaterQultMitigation = antWaterQultMitigation;
	}
	public String getSanitationIssue() {
		return sanitationIssue;
	}
	public void setSanitationIssue(String sanitationIssue) {
		this.sanitationIssue = sanitationIssue;
	}
	public String getSanitationMitigation() {
		return sanitationMitigation;
	}
	public void setSanitationMitigation(String sanitationMitigation) {
		this.sanitationMitigation = sanitationMitigation;
	}
	public String getConstructionIssue() {
		return constructionIssue;
	}
	public void setConstructionIssue(String constructionIssue) {
		this.constructionIssue = constructionIssue;
	}
	public String getConstructionMitigation() {
		return constructionMitigation;
	}
	public void setConstructionMitigation(String constructionMitigation) {
		this.constructionMitigation = constructionMitigation;
	}
	public String getConstructionWastesIssue() {
		return constructionWastesIssue;
	}
	public void setConstructionWastesIssue(String constructionWastesIssue) {
		this.constructionWastesIssue = constructionWastesIssue;
	}
	public String getConstructionWastesMitigation() {
		return constructionWastesMitigation;
	}
	public void setConstructionWastesMitigation(String constructionWastesMitigation) {
		this.constructionWastesMitigation = constructionWastesMitigation;
	}
	public String getSewageSchemeType() {
		return sewageSchemeType;
	}
	public void setSewageSchemeType(String sewageSchemeType) {
		this.sewageSchemeType = sewageSchemeType;
	}
	public String getSewagePractices() {
		return sewagePractices;
	}
	public void setSewagePractices(String sewagePractices) {
		this.sewagePractices = sewagePractices;
	}
	public String getSewagePattern() {
		return sewagePattern;
	}
	public void setSewagePattern(String sewagePattern) {
		this.sewagePattern = sewagePattern;
	}
	public String getWastewaterCattle() {
		return wastewaterCattle;
	}
	public void setWastewaterCattle(String wastewaterCattle) {
		this.wastewaterCattle = wastewaterCattle;
	}
	public String getGreyBlackWaterMix() {
		return greyBlackWaterMix;
	}
	public void setGreyBlackWaterMix(String greyBlackWaterMix) {
		this.greyBlackWaterMix = greyBlackWaterMix;
	}
	public String getSanitationFeedback() {
		return sanitationFeedback;
	}
	public void setSanitationFeedback(String sanitationFeedback) {
		this.sanitationFeedback = sanitationFeedback;
	}
	public String getWastewaterQuantity() {
		return wastewaterQuantity;
	}
	public void setWastewaterQuantity(String wastewaterQuantity) {
		this.wastewaterQuantity = wastewaterQuantity;
	}
	public String getMethodTreatment() {
		return methodTreatment;
	}
	public void setMethodTreatment(String methodTreatment) {
		this.methodTreatment = methodTreatment;
	}
	public String getPondsStp() {
		return pondsStp;
	}
	public void setPondsStp(String pondsStp) {
		this.pondsStp = pondsStp;
	}
	public String getPondsDistanceSettlement() {
		return pondsDistanceSettlement;
	}
	public void setPondsDistanceSettlement(String pondsDistanceSettlement) {
		this.pondsDistanceSettlement = pondsDistanceSettlement;
	}
	public String getPondsDistanceSchool() {
		return pondsDistanceSchool;
	}
	public void setPondsDistanceSchool(String pondsDistanceSchool) {
		this.pondsDistanceSchool = pondsDistanceSchool;
	}
	public String getPondsWaterQuality() {
		return pondsWaterQuality;
	}
	public void setPondsWaterQuality(String pondsWaterQuality) {
		this.pondsWaterQuality = pondsWaterQuality;
	}
	public String getPondsExpansionRequired() {
		return pondsExpansionRequired;
	}
	public void setPondsExpansionRequired(String pondsExpansionRequired) {
		this.pondsExpansionRequired = pondsExpansionRequired;
	}
	public String getPondsExpansionLand() {
		return pondsExpansionLand;
	}
	public void setPondsExpansionLand(String pondsExpansionLand) {
		this.pondsExpansionLand = pondsExpansionLand;
	}
	public String getLandUse() {
		return landUse;
	}
	public void setLandUse(String landUse) {
		this.landUse = landUse;
	}
	public String getPondsSitePlantation() {
		return pondsSitePlantation;
	}
	public void setPondsSitePlantation(String pondsSitePlantation) {
		this.pondsSitePlantation = pondsSitePlantation;
	}
	public String getContaminationDrinkingWaterSource() {
		return contaminationDrinkingWaterSource;
	}
	public void setContaminationDrinkingWaterSource(
			String contaminationDrinkingWaterSource) {
		this.contaminationDrinkingWaterSource = contaminationDrinkingWaterSource;
	}
	public String getDomesticSolidWaste() {
		return domesticSolidWaste;
	}
	public void setDomesticSolidWaste(String domesticSolidWaste) {
		this.domesticSolidWaste = domesticSolidWaste;
	}
	public String getLandfillSiteSolidWaste() {
		return landfillSiteSolidWaste;
	}
	public void setLandfillSiteSolidWaste(String landfillSiteSolidWaste) {
		this.landfillSiteSolidWaste = landfillSiteSolidWaste;
	}
	
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getLandUsePattern() {
		return landUsePattern;
	}
	public void setLandUsePattern(String landUsePattern) {
		this.landUsePattern = landUsePattern;
	}
	@Override
	public String toString() {
		return "EnvironmentBean [locationId=" + locationId + ", topography="
				+ topography + ", typeOfSoil=" + typeOfSoil
				+ ", intensityOfRainfall=" + intensityOfRainfall + ", tempMax="
				+ tempMax + ", tempMin=" + tempMin + ", slopeOfLand="
				+ slopeOfLand + ", windDirection=" + windDirection
				+ ", waterTable=" + waterTable + ", existingWaterBody="
				+ existingWaterBody + ", pondUse=" + pondUse
				+ ", waterLoggingAreaName=" + waterLoggingAreaName
				+ ", waterLoggingArea=" + waterLoggingArea
				+ ", waterLoggingPeriod=" + waterLoggingPeriod
				+ ", populationEffectedWaterLogging="
				+ populationEffectedWaterLogging
				+ ", contaminationDrinkingWater=" + contaminationDrinkingWater
				+ ", roadWidthMaxMin=" + roadWidthMaxMin + ", existingRoads="
				+ existingRoads + ", localVegetation=" + localVegetation
				+ ", population=" + population + ", numberHousehold="
				+ numberHousehold + ", landUse_pattern=" + landUsePattern
				+ ", historicalImportance=" + historicalImportance
				+ ", majorSourceIncome=" + majorSourceIncome
				+ ", incidentWaterborn=" + incidentWaterborn + ", diseaseName="
				+ diseaseName + ", vectorDisease=" + vectorDisease
				+ ", schemeType=" + schemeType + ", sourceOfDrinkingWater="
				+ sourceOfDrinkingWater + ", waterAvailability="
				+ waterAvailability + ", sourceWaterAssessed="
				+ sourceWaterAssessed + ", natureOfProblem=" + natureOfProblem
				+ ", riskContamination=" + riskContamination
				+ ", affectNaturalHabitats=" + affectNaturalHabitats
				+ ", infringeLocalRights=" + infringeLocalRights
				+ ", treatmentTechnology=" + treatmentTechnology
				+ ", treamentProposed=" + treamentProposed
				+ ", disinfectionExists=" + disinfectionExists
				+ ", ionisationPlant=" + ionisationPlant
				+ ", antWaterAvlbIssue=" + antWaterAvlbIssue
				+ ", antWaterAvlbMitigation=" + antWaterAvlbMitigation
				+ ", antWaterQultIssue=" + antWaterQultIssue
				+ ", antWaterQultMitigation=" + antWaterQultMitigation
				+ ", sanitationIssue=" + sanitationIssue
				+ ", sanitationMitigation=" + sanitationMitigation
				+ ", constructionIssue=" + constructionIssue
				+ ", constructionMitigation=" + constructionMitigation
				+ ", constructionWastesIssue=" + constructionWastesIssue
				+ ", constructionWastesMitigation="
				+ constructionWastesMitigation + ", sewageSchemeType="
				+ sewageSchemeType + ", sewagePractices=" + sewagePractices
				+ ", sewagePattern=" + sewagePattern + ", wastewaterCattle="
				+ wastewaterCattle + ", greyBlackWaterMix=" + greyBlackWaterMix
				+ ", sanitationFeedback=" + sanitationFeedback
				+ ", wastewaterQuantity=" + wastewaterQuantity
				+ ", methodTreatment=" + methodTreatment + ", pondsStp="
				+ pondsStp + ", pondsDistanceSettlement="
				+ pondsDistanceSettlement + ", pondsDistanceSchool="
				+ pondsDistanceSchool + ", pondsWaterQuality="
				+ pondsWaterQuality + ", pondsExpansionRequired="
				+ pondsExpansionRequired + ", pondsExpansionLand="
				+ pondsExpansionLand + ", landUse=" + landUse
				+ ", pondsSitePlantation=" + pondsSitePlantation
				+ ", contaminationDrinkingWaterSource="
				+ contaminationDrinkingWaterSource + ", domesticSolidWaste="
				+ domesticSolidWaste + ", landfillSiteSolidWaste="
				+ landfillSiteSolidWaste + "]";
	}
	public String getExistingWaterBodyOther() {
		return existingWaterBodyOther;
	}
	public void setExistingWaterBodyOther(String existingWaterBodyOther) {
		this.existingWaterBodyOther = existingWaterBodyOther;
	}


}
