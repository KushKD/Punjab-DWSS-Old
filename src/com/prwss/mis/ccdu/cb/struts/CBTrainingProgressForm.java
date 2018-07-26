package com.prwss.mis.ccdu.cb.struts;

import java.util.Arrays;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class CBTrainingProgressForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1387643153943329273L;
	private long cbProgressId;
	private long planId;
	private String villageId;
	private String locationId;
	private String phaseOfVillage;
	private double communityContributionReceived;
	private String trainingId;
	private String dateOfTraining;
	private Long[] trainingOfficers;
	private int totalParticipants;
	private String issues;
	private String recommendationsOfWssDpmc;
	private String otherRecommendation;
	private String actionByWhom;
	private String actionByWhen;
	private String outcome;
	private String otherOutcome;
	private String remarks;
	private String otherIssue;
	private String blockId;
	private String districtId;
	private Datagrid materialDatagrid;
	private Datagrid trainingOfficerDatagrid;
	private String itemGroup;
	private String stores;
	private String employeename;
	private double expenditure;
	private String levelOfTraining;

	
	
	
	public double getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}
	public Datagrid getTrainingOfficerDatagrid() {
		return trainingOfficerDatagrid;
	}
	public void setTrainingOfficerDatagrid(Datagrid trainingOfficerDatagrid) {
		this.trainingOfficerDatagrid = trainingOfficerDatagrid;
	}
	public String getStores() {
		return stores;
	}
	public void setStores(String stores) {
		this.stores = stores;
	}
	public String getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}
	public Datagrid getMaterialDatagrid() {
		return materialDatagrid;
	}
	public void setMaterialDatagrid(Datagrid materialDatagrid) {
		this.materialDatagrid = materialDatagrid;
	}
	public String getVillageId() {
		return villageId;
	}
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getPhaseOfVillage() {
		return phaseOfVillage;
	}
	public void setPhaseOfVillage(String phaseOfVillage) {
		this.phaseOfVillage = phaseOfVillage;
	}
	public double getCommunityContributionReceived() {
		return communityContributionReceived;
	}
	public void setCommunityContributionReceived(double communityContributionReceived) {
		this.communityContributionReceived = communityContributionReceived;
	}
	public String getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}
	public String getDateOfTraining() {
		return dateOfTraining;
	}
	public void setDateOfTraining(String dateOfTraining) {
		this.dateOfTraining = dateOfTraining;
	}
	public Long[] getTrainingOfficers() {
		return trainingOfficers;
	}
	public void setTrainingOfficers(Long[] trainingOfficers) {
		this.trainingOfficers = trainingOfficers;
	}
	public int getTotalParticipants() {
		return totalParticipants;
	}
	public void setTotalParticipants(int totalParticipants) {
		this.totalParticipants = totalParticipants;
	}
	public String getIssues() {
		return issues;
	}
	public void setIssues(String issues) {
		this.issues = issues;
	}
	public String getRecommendationsOfWssDpmc() {
		return recommendationsOfWssDpmc;
	}
	public void setRecommendationsOfWssDpmc(String recommendationsOfWssDpmc) {
		this.recommendationsOfWssDpmc = recommendationsOfWssDpmc;
	}
	public String getOtherRecommendation() {
		return otherRecommendation;
	}
	public void setOtherRecommendation(String otherRecommendation) {
		this.otherRecommendation = otherRecommendation;
	}
	public String getActionByWhom() {
		return actionByWhom;
	}
	public void setActionByWhom(String actionByWhom) {
		this.actionByWhom = actionByWhom;
	}
	public String getActionByWhen() {
		return actionByWhen;
	}
	public void setActionByWhen(String actionByWhen) {
		this.actionByWhen = actionByWhen;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public String getOtherOutcome() {
		return otherOutcome;
	}
	public void setOtherOutcome(String otherOutcome) {
		this.otherOutcome = otherOutcome;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getOtherIssue() {
		return otherIssue;
	}
	public void setOtherIssue(String otherIssue) {
		this.otherIssue = otherIssue;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	public long getPlanId() {
		return planId;
	}
	public void setPlanId(long planId) {
		this.planId = planId;
	}
	public void setCbProgressId(long cbProgressId) {
		this.cbProgressId = cbProgressId;
	}
	public long getCbProgressId() {
		return cbProgressId;
	}
	@Override
	public String toString() {
		return "CBTrainingProgressForm [cbProgressId=" + cbProgressId
				+ ", planId=" + planId + ", villageId=" + villageId
				+ ", locationId=" + locationId + ", phaseOfVillage="
				+ phaseOfVillage + ", communityContributionReceived="
				+ communityContributionReceived + ", trainingId=" + trainingId
				+ ", dateOfTraining=" + dateOfTraining + ", trainingOfficers="
				+ Arrays.toString(trainingOfficers) + ", totalParticipants="
				+ totalParticipants + ", issues=" + issues
				+ ", recommendationsOfWssDpmc=" + recommendationsOfWssDpmc
				+ ", otherRecommendation=" + otherRecommendation
				+ ", actionByWhom=" + actionByWhom + ", actionByWhen="
				+ actionByWhen + ", outcome=" + outcome + ", otherOutcome="
				+ otherOutcome + ", remarks=" + remarks + ", otherIssue="
				+ otherIssue + ", blockId=" + blockId + ", districtId="
				+ districtId + ", materialDatagrid=" + materialDatagrid
				+ ", itemGroup=" + itemGroup + ", stores=" + stores + ",expenditure=" +expenditure + ",levelOfTraining=" +levelOfTraining+ "]";
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setLevelOfTraining(String levelOfTraining) {
		this.levelOfTraining = levelOfTraining;
	}
	public String getLevelOfTraining() {
		return levelOfTraining;
	}
	 
	

}
