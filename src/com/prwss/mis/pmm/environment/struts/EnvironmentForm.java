/**
 * 
 */
package com.prwss.mis.pmm.environment.struts;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author Akash
 *
 */
public class EnvironmentForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1292755687773192606L;

	private String locationId;
	private String villageId;
	private String blockId;
	private String dateOfTransaction;
	private String topography;
	private String typeOfSoil;
	private String intensityOfRainfall;
	private String tempMax;
	private String tempMin;
	private String slopeOfLand;
	private String windDirection;
	private String waterTable;
	private String existingWaterBody;
	private String existingWaterBodyOther; // to be added in Bean
	private String pondUse;
	private String waterLoggingAreaName;
	private String waterLoggingArea;
	private String waterLoggingPeriod;
	private long populationEffectedWaterLogging;
	private String contaminationDrinkingWater;
	private String roadWidthMaxMin;
	private String existingRoads;
	private String localVegetation;
	private long population;
	private long numberHousehold;
	private String landUsePattern;
	private String historicalImportance;
	private String majorSourceIncome;
	private String incidentWaterborn;
	private String diseaseName;
	private String vectorDisease;
	private String schemeType;
	private String sourceOfDrinkingWater;
	private String waterAvailability;
	private String sourceWaterAssessed;
	private String natureOfProblem;
	private String riskContamination;
	private String affectNaturalHabitats;
	private String infringeLocalRights;
	private String treatmentTechnology;
	private String treamentProposed;
	private String disinfectionExists;
	private String ionisationPlant;
	private String antWaterAvlbIssue;
	private String antWaterAvlbMitigation;
	private String antWaterQultIssue;
	private String antWaterQultMitigation;
	private String sanitationIssue;
	private String sanitationMitigation;
	private String constructionIssue;
	private String constructionMitigation;
	private String constructionWastesIssue;
	private String constructionWastesMitigation;
	private String sewageSchemeType;
	private String sewagePractices;
	private String sewagePattern;
	private String wastewaterCattle;
	private String greyBlackWaterMix;
	private String sanitationFeedback;
	private String wastewaterQuantity;
	private String methodTreatment;
	private String pondsStp;
	private String pondsDistanceSettlement;
	private String pondsDistanceSchool;
	private String pondsWaterQuality;
	private String pondsExpansionRequired;
	private String pondsExpansionLand;
	private String landUse;
	private String pondsSitePlantation;
	private String contaminationDrinkingWaterSource;
	private String domesticSolidWaste;
	private String landfillSiteSolidWaste;
	
	
	public String getExistingWaterBodyOther() {
		return existingWaterBodyOther;
	}
	public void setExistingWaterBodyOther(String existingWaterBodyOther) {
		this.existingWaterBodyOther = existingWaterBodyOther;
	}
	public String getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(String dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
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
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
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
	public String getLandUsePattern() {
		return landUsePattern;
	}
	public void setLandUsePattern(String landUsePattern) {
		this.landUsePattern = landUsePattern;
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

}
