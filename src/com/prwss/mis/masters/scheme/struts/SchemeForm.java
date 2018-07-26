package com.prwss.mis.masters.scheme.struts;


import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * @author 
 *
 */ 
public class SchemeForm extends ValidatorActionForm {
	
	/**
	 * Initial Version
	 */
	   
private static final long serialVersionUID = 6103941486368726944L;
	   
	private String schemeId;
	private String schemeType;
	private String locationId;
	private String blockId;
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
	private String villageId;
	private String programId;
	private String schemeSource;
	private String mouSigned;
	private String resolutionDate;
	private String constitutionDateSLC;
	
	private String adminAppGoPLetterNo;
	private String adminAppGoPLetterDate;
	private BigDecimal adminAppGoPAmount;
	
	private String adminAppSentSeVideEeNo;
	private String AdminAppSentSeVideEeDate;
	private BigDecimal adminAppSentSeVideEeAmount;
	
	private String adminAppSentCeVideSeNo;
	private String adminAppSentCeVideSeDate;
	private BigDecimal adminAppSentCeVideSeAmount;
	
	private String adminAppSentGovtVideCeNo;
	private String adminAppSentGovtVideCeDate;
	private BigDecimal adminAppSentGovtVideCeAmount;
	
	private BigDecimal adminAppLyingWithEeAmount;
	private String techAppEeLetterNo;
	private String techAppEeDate;
	private BigDecimal techAppEeAmount;
	private String techAppSeLetterNo;
	private String techAppSeLetterDate;
	private BigDecimal techAppSeAmount;
	private String techAppCeLetterNo;
	private String techAppCeLetterDate;
	private BigDecimal techAppCeAmount;
	private String techAppSentToSeVideEeNo;
	private String techAppSentToSeVideEeDate;
	private BigDecimal techAppSentToSeVideEeAmount;
	private String techAppSentToCeVideSeNo;
	private String techAppSentToCeVideSeDate;
	private BigDecimal techAppSentToCeVideSeAmount;
    private BigDecimal techAppLyingWithEeAmount;
	private BigDecimal  beneficiaryShareDue;
	private BigDecimal  beneficiaryByCommunity;
	private BigDecimal  beneficiaryShareVoluntarily;
	private BigDecimal  beneficiaryShareNonBudgGp;
	private BigDecimal  beneficiaryShareUntiedDistrict;
	private BigDecimal  beneficiaryShareStateGovtGrant;
	private boolean shareLessThanUpperLimit;
	private boolean schemeCompleted;
	private String schemeCommisionedDate;
	private String schemeCompletedDate;
	private String designInvestigationDate;
	private boolean digitalSurveyCompleted;
	private String schemeStatus;
	private String schemeUpgraded;
	
	private String digitalSurveyDate ;
	
	
	
	
	
	
	public String getSchemeType() {
		return schemeType;
	}
	
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public String getDigitalSurveyDate() {
		return digitalSurveyDate;
	}
	public void setDigitalSurveyDate(String digitalSurveyDate) {
		this.digitalSurveyDate = digitalSurveyDate;
	}
	public boolean isDigitalSurveyCompleted() {
		return digitalSurveyCompleted;
	}
	public void setDigitalSurveyCompleted(boolean digitalSurveyCompleted) {
		this.digitalSurveyCompleted = digitalSurveyCompleted;
	}
	public String getDesignInvestigationDate() {
		return designInvestigationDate;
	}
	public void setDesignInvestigationDate(String designInvestigationDate) {
		this.designInvestigationDate = designInvestigationDate;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public BigDecimal getBeneficiaryShareDue() {
		return beneficiaryShareDue;
	}
	public void setBeneficiaryShareDue(BigDecimal beneficiaryShareDue) {
		this.beneficiaryShareDue = beneficiaryShareDue;
	}
	public BigDecimal getBeneficiaryByCommunity() {
		return beneficiaryByCommunity;
	}
	public void setBeneficiaryByCommunity(BigDecimal beneficiaryByCommunity) {
		this.beneficiaryByCommunity = beneficiaryByCommunity;
	}
	public BigDecimal getBeneficiaryShareVoluntarily() {
		return beneficiaryShareVoluntarily;
	}
	public void setBeneficiaryShareVoluntarily(
			BigDecimal beneficiaryShareVoluntarily) {
		this.beneficiaryShareVoluntarily = beneficiaryShareVoluntarily;
	}
	public BigDecimal getBeneficiaryShareNonBudgGp() {
		return beneficiaryShareNonBudgGp;
	}
	public void setBeneficiaryShareNonBudgGp(BigDecimal beneficiaryShareNonBudgGp) {
		this.beneficiaryShareNonBudgGp = beneficiaryShareNonBudgGp;
	}
	public BigDecimal getBeneficiaryShareUntiedDistrict() {
		return beneficiaryShareUntiedDistrict;
	}
	public void setBeneficiaryShareUntiedDistrict(
			BigDecimal beneficiaryShareUntiedDistrict) {
		this.beneficiaryShareUntiedDistrict = beneficiaryShareUntiedDistrict;
	}
	public BigDecimal getBeneficiaryShareStateGovtGrant() {
		return beneficiaryShareStateGovtGrant;
	}
	public void setBeneficiaryShareStateGovtGrant(
			BigDecimal beneficiaryShareStateGovtGrant) {
		this.beneficiaryShareStateGovtGrant = beneficiaryShareStateGovtGrant;
	}
	public boolean isShareLessThanUpperLimit() {
		return shareLessThanUpperLimit;
	}
	public void setShareLessThanUpperLimit(boolean shareLessThanUpperLimit) {
		this.shareLessThanUpperLimit = shareLessThanUpperLimit;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getSchemeSource() {
		return schemeSource;
	}
	public void setSchemeSource(String schemeSource) {
		this.schemeSource = schemeSource;
	}
	
	public String getMouSigned() {
		return mouSigned;
	}
	public void setMouSigned(String mouSigned) {
		this.mouSigned = mouSigned;
	}
	
	public String getResolutionDate() {
		return resolutionDate;
	}
	public void setResolutionDate(String resolutionDate) {
		this.resolutionDate = resolutionDate;
	}
	public String getConstitutionDateSLC() {
		return constitutionDateSLC;
	}
	public void setConstitutionDateSLC(String constitutionDateSLC) {
		this.constitutionDateSLC = constitutionDateSLC;
	}
	public String getAdminAppGoPLetterNo() {
		return adminAppGoPLetterNo;
	}
	public void setAdminAppGoPLetterNo(String adminAppGoPLetterNo) {
		this.adminAppGoPLetterNo = adminAppGoPLetterNo;
	}
	public String getAdminAppGoPLetterDate() {
		return adminAppGoPLetterDate;
	}
	public void setAdminAppGoPLetterDate(String adminAppGoPLetterDate) {
		this.adminAppGoPLetterDate = adminAppGoPLetterDate;
	}
	public BigDecimal getAdminAppGoPAmount() {
		return adminAppGoPAmount;
	}
	public void setAdminAppGoPAmount(BigDecimal adminAppGoPAmount) {
		this.adminAppGoPAmount = adminAppGoPAmount;
	}
	public String getAdminAppSentSeVideEeNo() {
		return adminAppSentSeVideEeNo;
	}
	public void setAdminAppSentSeVideEeNo(String adminAppSentSeVideEeNo) {
		this.adminAppSentSeVideEeNo = adminAppSentSeVideEeNo;
	}
	public String getAdminAppSentSeVideEeDate() {
		return AdminAppSentSeVideEeDate;
	}
	public void setAdminAppSentSeVideEeDate(String adminAppSentSeVideEeDate) {
		AdminAppSentSeVideEeDate = adminAppSentSeVideEeDate;
	}
	public BigDecimal getAdminAppSentSeVideEeAmount() {
		return adminAppSentSeVideEeAmount;
	}
	public void setAdminAppSentSeVideEeAmount(BigDecimal adminAppSentSeVideEeAmount) {
		this.adminAppSentSeVideEeAmount = adminAppSentSeVideEeAmount;
	}
	public String getAdminAppSentCeVideSeNo() {
		return adminAppSentCeVideSeNo;
	}
	public void setAdminAppSentCeVideSeNo(String adminAppSentCeVideSeNo) {
		this.adminAppSentCeVideSeNo = adminAppSentCeVideSeNo;
	}
	public String getAdminAppSentCeVideSeDate() {
		return adminAppSentCeVideSeDate;
	}
	public void setAdminAppSentCeVideSeDate(String adminAppSentCeVideSeDate) {
		this.adminAppSentCeVideSeDate = adminAppSentCeVideSeDate;
	}
	public BigDecimal getAdminAppSentCeVideSeAmount() {
		return adminAppSentCeVideSeAmount;
	}
	public void setAdminAppSentCeVideSeAmount(BigDecimal adminAppSentCeVideSeAmount) {
		this.adminAppSentCeVideSeAmount = adminAppSentCeVideSeAmount;
	}
	public String getAdminAppSentGovtVideCeNo() {
		return adminAppSentGovtVideCeNo;
	}
	public void setAdminAppSentGovtVideCeNo(String adminAppSentGovtVideCeNo) {
		this.adminAppSentGovtVideCeNo = adminAppSentGovtVideCeNo;
	}
	public String getAdminAppSentGovtVideCeDate() {
		return adminAppSentGovtVideCeDate;
	}
	public void setAdminAppSentGovtVideCeDate(String adminAppSentGovtVideCeDate) {
		this.adminAppSentGovtVideCeDate = adminAppSentGovtVideCeDate;
	}
	public BigDecimal getAdminAppSentGovtVideCeAmount() {
		return adminAppSentGovtVideCeAmount;
	}
	public void setAdminAppSentGovtVideCeAmount(
			BigDecimal adminAppSentGovtVideCeAmount) {
		this.adminAppSentGovtVideCeAmount = adminAppSentGovtVideCeAmount;
	}
	public BigDecimal getAdminAppLyingWithEeAmount() {
		return adminAppLyingWithEeAmount;
	}
	public void setAdminAppLyingWithEeAmount(BigDecimal adminAppLyingWithEeAmount) {
		this.adminAppLyingWithEeAmount = adminAppLyingWithEeAmount;
	}
	public String getTechAppEeLetterNo() {
		return techAppEeLetterNo;
	}
	public void setTechAppEeLetterNo(String techAppEeLetterNo) {
		this.techAppEeLetterNo = techAppEeLetterNo;
	}
	public String getTechAppEeDate() {
		return techAppEeDate;
	}
	public void setTechAppEeDate(String techAppEeDate) {
		this.techAppEeDate = techAppEeDate;
	}
	public BigDecimal getTechAppEeAmount() {
		return techAppEeAmount;
	}
	public void setTechAppEeAmount(BigDecimal techAppEeAmount) {
		this.techAppEeAmount = techAppEeAmount;
	}
	public String getTechAppSeLetterNo() {
		return techAppSeLetterNo;
	}
	public void setTechAppSeLetterNo(String techAppSeLetterNo) {
		this.techAppSeLetterNo = techAppSeLetterNo;
	}
	public String getTechAppSeLetterDate() {
		return techAppSeLetterDate;
	}
	public void setTechAppSeLetterDate(String techAppSeLetterDate) {
		this.techAppSeLetterDate = techAppSeLetterDate;
	}
	public BigDecimal getTechAppSeAmount() {
		return techAppSeAmount;
	}
	public void setTechAppSeAmount(BigDecimal techAppSeAmount) {
		this.techAppSeAmount = techAppSeAmount;
	}
	public String getTechAppCeLetterNo() {
		return techAppCeLetterNo;
	}
	public void setTechAppCeLetterNo(String techAppCeLetterNo) {
		this.techAppCeLetterNo = techAppCeLetterNo;
	}
	public String getTechAppCeLetterDate() {
		return techAppCeLetterDate;
	}
	public void setTechAppCeLetterDate(String techAppCeLetterDate) {
		this.techAppCeLetterDate = techAppCeLetterDate;
	}
	public BigDecimal getTechAppCeAmount() {
		return techAppCeAmount;
	}
	public void setTechAppCeAmount(BigDecimal techAppCeAmount) {
		this.techAppCeAmount = techAppCeAmount;
	}
	public String getTechAppSentToSeVideEeNo() {
		return techAppSentToSeVideEeNo;
	}
	public void setTechAppSentToSeVideEeNo(String techAppSentToSeVideEeNo) {
		this.techAppSentToSeVideEeNo = techAppSentToSeVideEeNo;
	}
	public String getTechAppSentToSeVideEeDate() {
		return techAppSentToSeVideEeDate;
	}
	public void setTechAppSentToSeVideEeDate(String techAppSentToSeVideEeDate) {
		this.techAppSentToSeVideEeDate = techAppSentToSeVideEeDate;
	}
	public BigDecimal getTechAppSentToSeVideEeAmount() {
		return techAppSentToSeVideEeAmount;
	}
	public void setTechAppSentToSeVideEeAmount(
			BigDecimal techAppSentToSeVideEeAmount) {
		this.techAppSentToSeVideEeAmount = techAppSentToSeVideEeAmount;
	}
	public String getTechAppSentToCeVideSeNo() {
		return techAppSentToCeVideSeNo;
	}
	public void setTechAppSentToCeVideSeNo(String techAppSentToCeVideSeNo) {
		this.techAppSentToCeVideSeNo = techAppSentToCeVideSeNo;
	}
	public String getTechAppSentToCeVideSeDate() {
		return techAppSentToCeVideSeDate;
	}
	public void setTechAppSentToCeVideSeDate(String techAppSentToCeVideSeDate) {
		this.techAppSentToCeVideSeDate = techAppSentToCeVideSeDate;
	}
	public BigDecimal getTechAppSentToCeVideSeAmount() {
		return techAppSentToCeVideSeAmount;
	}
	public void setTechAppSentToCeVideSeAmount(
			BigDecimal techAppSentToCeVideSeAmount) {
		this.techAppSentToCeVideSeAmount = techAppSentToCeVideSeAmount;
	}
	public BigDecimal getTechAppLyingWithEeAmount() {
		return techAppLyingWithEeAmount;
	}
	public void setTechAppLyingWithEeAmount(BigDecimal techAppLyingWithEeAmount) {
		this.techAppLyingWithEeAmount = techAppLyingWithEeAmount;
	}
	
	public boolean isSchemeCompleted() {
		return schemeCompleted;
	}
	public void setSchemeCompleted(boolean schemeCompleted) {
		this.schemeCompleted = schemeCompleted;
	}
	public String getSchemeCommisionedDate() {
		return schemeCommisionedDate;
	}
	public void setSchemeCommisionedDate(String schemeCommisionedDate) {
		this.schemeCommisionedDate = schemeCommisionedDate;
	}
	public String getSchemeCompletedDate() {
		return schemeCompletedDate;
	}
	public void setSchemeCompletedDate(String schemeCompletedDate) {
		this.schemeCompletedDate = schemeCompletedDate;
	}
	public void setSchemeStatus(String schemeStatus) {
		this.schemeStatus = schemeStatus;
	}
	public String getSchemeStatus() {
		return schemeStatus;
	}
	public void setSchemeUpgraded(String schemeUpgraded) {
		this.schemeUpgraded = schemeUpgraded;
	}
	public String getSchemeUpgraded() {
		return schemeUpgraded;
	}
	
	
    
	
}