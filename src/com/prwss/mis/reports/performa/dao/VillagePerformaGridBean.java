/**
 * 
 */
package com.prwss.mis.reports.performa.dao;

import java.io.Serializable;

/**
 * @author bhsingh
 *
 */
public class VillagePerformaGridBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1541279477362102912L;

	private String villageId;
	private String villageName;
	private String hadBastNo;
	private String noOfHabitation;
	
	private String nameOfHabitation ;
	private String typeOfHabitation ;
	private String misCode;
	private String assemblyConstituency ;
	private String poputlationTotal ;
	private String populationSc ;
	private String noOfHouseholdsTotal ;
	private String noOfHouseholdsSc ;
	private String panchayatGhr ;
	private String commCenterDhar ;
	private String angarwaries ;
	private String govtSchools ;
	private String healthCntr ;
	private String villagePonds ;
	private String areaSqrMtr ;
	private String waterScheme ;
	private String sewerageScheme;
	private String depthTubewell;
	private String sizeTubewell;
	private String yearDrill;
	private String vilIds; 
	private String isAngarwaries; 	
	private String isGovtSchools; 
	private String isHealthCntr; 
	private String isVillagePonds; 
	private String sewerageCon;
	private String femaleScPop;
	private String femaleGnPop;
	private String waterConSc;
	private String sewerageConSc;
	private String habitationWaterSupply;
	private String privateBuildings;
	private String govtBuildings;
	
	
	/**
	 * @return the habitationWaterSupply
	 */
	public String getHabitationWaterSupply() {
		return habitationWaterSupply;
	}
	/**
	 * @param habitationWaterSupply the habitationWaterSupply to set
	 */
	public void setHabitationWaterSupply(String habitationWaterSupply) {
		this.habitationWaterSupply = habitationWaterSupply;
	}
	/**
	 * @return the sewerageCon
	 */
	public String getSewerageCon() {
		return sewerageCon;
	}
	/**
	 * @param sewerageCon the sewerageCon to set
	 */
	public void setSewerageCon(String sewerageCon) {
		this.sewerageCon = sewerageCon;
	}
	/**
	 * @return the femaleScPop
	 */
	public String getFemaleScPop() {
		return femaleScPop;
	}
	/**
	 * @param femaleScPop the femaleScPop to set
	 */
	public void setFemaleScPop(String femaleScPop) {
		this.femaleScPop = femaleScPop;
	}
	/**
	 * @return the femaleGnPop
	 */
	public String getFemaleGnPop() {
		return femaleGnPop;
	}
	/**
	 * @param femaleGnPop the femaleGnPop to set
	 */
	public void setFemaleGnPop(String femaleGnPop) {
		this.femaleGnPop = femaleGnPop;
	}
	/**
	 * @return the waterConSc
	 */
	public String getWaterConSc() {
		return waterConSc;
	}
	/**
	 * @param waterConSc the waterConSc to set
	 */
	public void setWaterConSc(String waterConSc) {
		this.waterConSc = waterConSc;
	}
	/**
	 * @return the sewerageConSc
	 */
	public String getSewerageConSc() {
		return sewerageConSc;
	}
	/**
	 * @param sewerageConSc the sewerageConSc to set
	 */
	public void setSewerageConSc(String sewerageConSc) {
		this.sewerageConSc = sewerageConSc;
	}
	/**
	 * @return the isGovtSchools
	 */
	public String getIsGovtSchools() {
		return isGovtSchools;
	}
	/**
	 * @param isGovtSchools the isGovtSchools to set
	 */
	public void setIsGovtSchools(String isGovtSchools) {
		this.isGovtSchools = isGovtSchools;
	}
	/**
	 * @return the isHealthCntr
	 */
	public String getIsHealthCntr() {
		return isHealthCntr;
	}
	/**
	 * @param isHealthCntr the isHealthCntr to set
	 */
	public void setIsHealthCntr(String isHealthCntr) {
		this.isHealthCntr = isHealthCntr;
	}
	/**
	 * @return the isVillagePonds
	 */
	public String getIsVillagePonds() {
		return isVillagePonds;
	}
	/**
	 * @param isVillagePonds the isVillagePonds to set
	 */
	public void setIsVillagePonds(String isVillagePonds) {
		this.isVillagePonds = isVillagePonds;
	}
	/**
	 * @return the isAngarwaries
	 */
	public String getIsAngarwaries() {
		return isAngarwaries;
	}
	/**
	 * @param isAngarwaries the isAngarwaries to set
	 */
	public void setIsAngarwaries(String isAngarwaries) {
		this.isAngarwaries = isAngarwaries;
	}
	/**
	 * @return the vilIds
	 */
	public String getVilIds() {
		return vilIds;
	}
	/**
	 * @param vilIds the vilIds to set
	 */
	public void setVilIds(String vilIds) {
		this.vilIds = vilIds;
	}
	/**
	 * @return the depthTubewell
	 */
	public String getDepthTubewell() {
		return depthTubewell;
	}
	/**
	 * @param depthTubewell the depthTubewell to set
	 */
	public void setDepthTubewell(String depthTubewell) {
		this.depthTubewell = depthTubewell;
	}
	/**
	 * @return the sizeTubewell
	 */
	public String getSizeTubewell() {
		return sizeTubewell;
	}
	/**
	 * @param sizeTubewell the sizeTubewell to set
	 */
	public void setSizeTubewell(String sizeTubewell) {
		this.sizeTubewell = sizeTubewell;
	}
	/**
	 * @return the yearDrill
	 */
	public String getYearDrill() {
		return yearDrill;
	}
	/**
	 * @param yearDrill the yearDrill to set
	 */
	public void setYearDrill(String yearDrill) {
		this.yearDrill = yearDrill;
	}
	/**
	 * @return the sewerageScheme
	 */
	public String getSewerageScheme() {
		return sewerageScheme;
	}
	/**
	 * @param sewerageScheme the sewerageScheme to set
	 */
	public void setSewerageScheme(String sewerageScheme) {
		this.sewerageScheme = sewerageScheme;
	}
	/**
	 * @return the nameOfHabitation
	 */
	public String getNameOfHabitation() {
		return nameOfHabitation;
	}
	/**
	 * @param nameOfHabitation the nameOfHabitation to set
	 */
	public void setNameOfHabitation(String nameOfHabitation) {
		this.nameOfHabitation = nameOfHabitation;
	}
	/**
	 * @return the typeOfHabitation
	 */
	public String getTypeOfHabitation() {
		return typeOfHabitation;
	}
	/**
	 * @param typeOfHabitation the typeOfHabitation to set
	 */
	public void setTypeOfHabitation(String typeOfHabitation) {
		this.typeOfHabitation = typeOfHabitation;
	}
	/**
	 * @return the misCode
	 */
	public String getMisCode() {
		return misCode;
	}
	/**
	 * @param misCode the misCode to set
	 */
	public void setMisCode(String misCode) {
		this.misCode = misCode;
	}
	/**
	 * @return the assemblyConstituency
	 */
	public String getAssemblyConstituency() {
		return assemblyConstituency;
	}
	/**
	 * @param assemblyConstituency the assemblyConstituency to set
	 */
	public void setAssemblyConstituency(String assemblyConstituency) {
		this.assemblyConstituency = assemblyConstituency;
	}
	/**
	 * @return the poputlationTotal
	 */
	public String getPoputlationTotal() {
		return poputlationTotal;
	}
	/**
	 * @param poputlationTotal the poputlationTotal to set
	 */
	public void setPoputlationTotal(String poputlationTotal) {
		this.poputlationTotal = poputlationTotal;
	}
	/**
	 * @return the populationSc
	 */
	public String getPopulationSc() {
		return populationSc;
	}
	/**
	 * @param populationSc the populationSc to set
	 */
	public void setPopulationSc(String populationSc) {
		this.populationSc = populationSc;
	}
	/**
	 * @return the noOfHouseholdsTotal
	 */
	public String getNoOfHouseholdsTotal() {
		return noOfHouseholdsTotal;
	}
	/**
	 * @param noOfHouseholdsTotal the noOfHouseholdsTotal to set
	 */
	public void setNoOfHouseholdsTotal(String noOfHouseholdsTotal) {
		this.noOfHouseholdsTotal = noOfHouseholdsTotal;
	}
	/**
	 * @return the noOfHouseholdsSc
	 */
	public String getNoOfHouseholdsSc() {
		return noOfHouseholdsSc;
	}
	/**
	 * @param noOfHouseholdsSc the noOfHouseholdsSc to set
	 */
	public void setNoOfHouseholdsSc(String noOfHouseholdsSc) {
		this.noOfHouseholdsSc = noOfHouseholdsSc;
	}
	/**
	 * @return the panchayatGhr
	 */
	public String getPanchayatGhr() {
		return panchayatGhr;
	}
	/**
	 * @param panchayatGhr the panchayatGhr to set
	 */
	public void setPanchayatGhr(String panchayatGhr) {
		this.panchayatGhr = panchayatGhr;
	}
	/**
	 * @return the commCenterDhar
	 */
	public String getCommCenterDhar() {
		return commCenterDhar;
	}
	/**
	 * @param commCenterDhar the commCenterDhar to set
	 */
	public void setCommCenterDhar(String commCenterDhar) {
		this.commCenterDhar = commCenterDhar;
	}
	/**
	 * @return the angarwaries
	 */
	public String getAngarwaries() {
		return angarwaries;
	}
	/**
	 * @param angarwaries the angarwaries to set
	 */
	public void setAngarwaries(String angarwaries) {
		this.angarwaries = angarwaries;
	}
	/**
	 * @return the typeBuilding
	 *//*
	public String getTypeBuilding() {
		return typeBuilding;
	}
	*//**
	 * @param typeBuilding the typeBuilding to set
	 *//*
	public void setTypeBuilding(String typeBuilding) {
		this.typeBuilding = typeBuilding;
	}*/
	
	
	
	/**
	 * @return the govtSchools
	 */
	public String getGovtSchools() {
		return govtSchools;
	}
	/**
	 * @param govtSchools the govtSchools to set
	 */
	public void setGovtSchools(String govtSchools) {
		this.govtSchools = govtSchools;
	}
	/**
	 * @return the healthCntr
	 */
	public String getHealthCntr() {
		return healthCntr;
	}
	/**
	 * @param healthCntr the healthCntr to set
	 */
	public void setHealthCntr(String healthCntr) {
		this.healthCntr = healthCntr;
	}
	/**
	 * @return the villagePonds
	 */
	public String getVillagePonds() {
		return villagePonds;
	}
	/**
	 * @param villagePonds the villagePonds to set
	 */
	public void setVillagePonds(String villagePonds) {
		this.villagePonds = villagePonds;
	}
	/**
	 * @return the areaSqrMtr
	 */
	public String getAreaSqrMtr() {
		return areaSqrMtr;
	}
	/**
	 * @param areaSqrMtr the areaSqrMtr to set
	 */
	public void setAreaSqrMtr(String areaSqrMtr) {
		this.areaSqrMtr = areaSqrMtr;
	}
	/**
	 * @return the waterScheme
	 */
	public String getWaterScheme() {
		return waterScheme;
	}
	/**
	 * @param waterScheme the waterScheme to set
	 */
	public void setWaterScheme(String waterScheme) {
		this.waterScheme = waterScheme;
	}
	/**
	 * @return the villageId
	 * 
	 */
	public String getVillageId() {
		return villageId;
	}
	/**
	 * @param villageId the villageId to set
	 */
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	/**
	 * @return the villageName
	 */
	public String getVillageName() {
		return villageName;
	}
	/**
	 * @param villageName the villageName to set
	 */
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	/**
	 * @return the hadBastNo
	 */
	public String getHadBastNo() {
		return hadBastNo;
	}
	/**
	 * @param hadBastNo the hadBastNo to set
	 */
	public void setHadBastNo(String hadBastNo) {
		this.hadBastNo = hadBastNo;
	}
	/**
	 * @return the noOfHabitation
	 */
	public String getNoOfHabitation() {
		return noOfHabitation;
	}
	/**
	 * @param noOfHabitation the noOfHabitation to set
	 */
	public void setNoOfHabitation(String noOfHabitation) {
		this.noOfHabitation = noOfHabitation;
	}
	/**
	 * @return the privateBuildings
	 */
	public String getPrivateBuildings() {
		return privateBuildings;
	}
	/**
	 * @param privateBuildings the privateBuildings to set
	 */
	public void setPrivateBuildings(String privateBuildings) {
		this.privateBuildings = privateBuildings;
	}
	/**
	 * @return the govtBuildings
	 */
	public String getGovtBuildings() {
		return govtBuildings;
	}
	/**
	 * @param govtBuildings the govtBuildings to set
	 */
	public void setGovtBuildings(String govtBuildings) {
		this.govtBuildings = govtBuildings;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VillagePerformaGridBean [villageId=" + villageId + ", villageName=" + villageName + ", hadBastNo="
				+ hadBastNo + ", noOfHabitation=" + noOfHabitation + ", nameOfHabitation=" + nameOfHabitation
				+ ", typeOfHabitation=" + typeOfHabitation + ", misCode=" + misCode + ", assemblyConstituency="
				+ assemblyConstituency + ", poputlationTotal=" + poputlationTotal + ", populationSc=" + populationSc
				+ ", noOfHouseholdsTotal=" + noOfHouseholdsTotal + ", noOfHouseholdsSc=" + noOfHouseholdsSc
				+ ", panchayatGhr=" + panchayatGhr + ", commCenterDhar=" + commCenterDhar + ", angarwaries="
				+ angarwaries + ", govtSchools=" + govtSchools + ", healthCntr=" + healthCntr + ", villagePonds="
				+ villagePonds + ", areaSqrMtr=" + areaSqrMtr + ", waterScheme=" + waterScheme + ", sewerageScheme="
				+ sewerageScheme + ", depthTubewell=" + depthTubewell + ", sizeTubewell=" + sizeTubewell
				+ ", yearDrill=" + yearDrill + ", vilIds=" + vilIds + ", isAngarwaries=" + isAngarwaries
				+ ", isGovtSchools=" + isGovtSchools + ", isHealthCntr=" + isHealthCntr + ", isVillagePonds="
				+ isVillagePonds + ", sewerageCon=" + sewerageCon + ", femaleScPop=" + femaleScPop + ", femaleGnPop="
				+ femaleGnPop + ", waterConSc=" + waterConSc + ", sewerageConSc=" + sewerageConSc
				+ ", habitationWaterSupply=" + habitationWaterSupply + ", privateBuildings=" + privateBuildings
				+ ", govtBuildings=" + govtBuildings + "]";
	}
	
}
