package com.prwss.mis.masters.scheme.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_scheme_details", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SchemeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7369362483310729544L;

	@Id
	@Column(name="scheme_id", nullable=false)
	private String schemeId;
	
	
	@Column(name="scheme_source")	
	private String schemeSource;
	
	
	@Column(name="mou_signed")	
	private String mouSigned;
	
	@Column(name="admin_app_gop_letter_no")	
	private String adminAppGoPLetterNo;
	
	@Column(name="admin_app_gop_letter_date")	
	private Date adminAppGoPLetterDate;
	
	@Column(name="admin_app_gop_amount")	
	private BigDecimal adminAppGoPAmount;
	
	@Column(name="admin_app_sent_se_vide_ee_no")
	private String adminAppSentSeVideEeNo;
	
	
	@Column(name="admin_app_sent_se_vide_ee_date")	
	private Date adminAppSentSeVideEeDate;
	
	@Column(name="admin_app_sent_se_vide_ee_amount")	
	private BigDecimal adminAppSentSeVideEeAmount;
	
	@Column(name="admin_app_sent_ce_vide_se_no")
	private String adminAppSentCeVideSeNo;
	
	
	@Column(name="admin_app_sent_ce_vide_se_date")	
	private Date adminAppSentCeVideSeDate;
	
	@Column(name="admin_app_sent_ce_vide_se_amount")	
	private BigDecimal adminAppSentCeVideSeAmount;
	
	
	
	
	@Column(name="admin_app_sent_govt_vide_ce_no")		
	private String adminAppSentGovtVideCeNo;
	
	@Column(name="admin_app_sent_govt_vide_ce_date")	
	private Date adminAppSentGovtVideCeDate;
	
	@Column(name="admin_app_sent_govt_vide_ce_amount")	
	private BigDecimal adminAppSentGovtVideCeAmount;
	
	
	
	
	@Column(name="admin_app_lying_with_ee_amount")		
	private BigDecimal adminAppLyingWithEeAmount;
	
	
	
	@Column(name="tech_app_ee_letter_no")	
	private String techAppEeLetterNo;
	
	@Column(name=" tech_app_ee_date")	
	private Date techAppEeDate;
	
	@Column(name="tech_app_ee_amount")	
	private BigDecimal techAppEeAmount;
	
	
	
	@Column(name="tech_app_se_letter_no")	
	private String techAppSeLetterNo;
	
	@Column(name="tech_app_se_letter_date")	
	private Date techAppSeLetterDate;
	
	@Column(name="tech_app_se_amount")	
	private BigDecimal techAppSeAmount;
	
	
	
	
	@Column(name="tech_app_ce_letter_no")	
	private String techAppCeLetterNo;
	
	@Column(name="tech_app_ce_letter_date")	
	private Date techAppCeLetterDate;
	
	@Column(name="tech_app_ce_amount")	
	private BigDecimal techAppCeAmount;  
	
	
	@Column(name="tech_app_sent_to_se_vide_ee_no")	
	private String techAppSentToSeVideEeNo;
	
	@Column(name="tech_app_sent_to_se_vide_ee_date")	
	private Date techAppSentToSeVideEeDate;
	
	@Column(name="tech_app_sent_to_se_vide_ee_amount")	
	private BigDecimal techAppSentToSeVideEeAmount;
	
	
	
	@Column(name="tech_app_sent_to_ce_vide_se_no")	
	private String techAppSentToCeVideSeNo;
	
	@Column(name="tech_app_sent_to_ce_vide_se_date")	
	private Date techAppSentToCeVideSeDate;
	
	@Column(name="tech_app_sent_to_ce_vide_se_amount")	
	private BigDecimal techAppSentToCeVideSeAmount;
	
	@Column(name="tech_app_lying_with_ee_amount")	
    private BigDecimal techAppLyingWithEeAmount;
	
	
	@Column(name="resolution_date")	
	private Date resolutionDate;
	
	@Column(name="constitution_date_slc")	
	private Date constitutionDateSLC;
	
	@Column(name="beneficiary_share_due")	
	private BigDecimal  beneficiaryShareDue;
	@Column(name="beneficiary_by_community")	
	private BigDecimal  beneficiaryByCommunity;
	@Column(name="beneficiary_share_voluntarily")	
	private BigDecimal  beneficiaryShareVoluntarily;
	@Column(name="beneficiary_share_non_budg_gp")	
	private BigDecimal  beneficiaryShareNonBudgGp;
	@Column(name="beneficiary_share_untied_district")	
	private BigDecimal  beneficiaryShareUntiedDistrict;
	  
	@Column(name="beneficiary_share_state_govt_grant")	
	private BigDecimal  beneficiaryShareStateGovtGrant;
	
	@Column(name="share_less_than_upper_limit")	
	private boolean shareLessThanUpperLimit;
	
	@Column(name="scheme_completed")	
	private boolean schemeCompleted;
	
	@Column(name="scheme_commisioned_date")	
	private Date schemeCommisionedDate;
	
	@Column(name="scheme_completed_date")	
	private Date schemeCompletedDate;
	
	@Column(name="design_investigation_date ")	
	private Date designInvestigationDate ;

	@Column(name="is_digital_survey_completed")	
	private boolean digitalSurveyCompleted;
	
	@Column(name="digital_survey_date ")	
	private Date digitalSurveyDate ;

	@Id
	@Column(name="scheme_upgraded")
	private String schemeUpgraded;
	
	@Embedded
	private MISAuditBean misAuditBean;
	

	public Date getDigitalSurveyDate() {
		return digitalSurveyDate;
	}

	public void setDigitalSurveyDate(Date digitalSurveyDate) {
		this.digitalSurveyDate = digitalSurveyDate;
	}

	public boolean isDigitalSurveyCompleted() {
		return digitalSurveyCompleted;
	}

	public void setDigitalSurveyCompleted(boolean digitalSurveyCompleted) {
		this.digitalSurveyCompleted = digitalSurveyCompleted;
	}

	public Date getDesignInvestigationDate() {
		return designInvestigationDate;
	}

	public void setDesignInvestigationDate(Date designInvestigationDate) {
		this.designInvestigationDate = designInvestigationDate;
	}

	public boolean isSchemeCompleted() {
		return schemeCompleted;
	}

	public void setSchemeCompleted(boolean schemeCompleted) {
		this.schemeCompleted = schemeCompleted;
	}

	public Date getSchemeCommisionedDate() {
		return schemeCommisionedDate;
	}

	public void setSchemeCommisionedDate(Date schemeCommisionedDate) {
		this.schemeCommisionedDate = schemeCommisionedDate;
	}

	public Date getSchemeCompletedDate() {
		return schemeCompletedDate;
	}

	public void setSchemeCompletedDate(Date schemeCompletedDate) {
		this.schemeCompletedDate = schemeCompletedDate;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
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

	public String getAdminAppGoPLetterNo() {
		return adminAppGoPLetterNo;
	}

	public void setAdminAppGoPLetterNo(String adminAppGoPLetterNo) {
		this.adminAppGoPLetterNo = adminAppGoPLetterNo;
	}

	public Date getAdminAppGoPLetterDate() {
		return adminAppGoPLetterDate;
	}

	public void setAdminAppGoPLetterDate(Date adminAppGoPLetterDate) {
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

	public Date getAdminAppSentSeVideEeDate() {
		return adminAppSentSeVideEeDate;
	}

	public void setAdminAppSentSeVideEeDate(Date adminAppSentSeVideEeDate) {
		this.adminAppSentSeVideEeDate = adminAppSentSeVideEeDate;
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

	public Date getAdminAppSentCeVideSeDate() {
		return adminAppSentCeVideSeDate;
	}

	public void setAdminAppSentCeVideSeDate(Date adminAppSentCeVideSeDate) {
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

	public Date getAdminAppSentGovtVideCeDate() {
		return adminAppSentGovtVideCeDate;
	}

	public void setAdminAppSentGovtVideCeDate(Date adminAppSentGovtVideCeDate) {
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

	public Date getTechAppEeDate() {
		return techAppEeDate;
	}

	public void setTechAppEeDate(Date techAppEeDate) {
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

	public Date getTechAppSeLetterDate() {
		return techAppSeLetterDate;
	}

	public void setTechAppSeLetterDate(Date techAppSeLetterDate) {
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

	public Date getTechAppCeLetterDate() {
		return techAppCeLetterDate;
	}

	public void setTechAppCeLetterDate(Date techAppCeLetterDate) {
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

	public Date getTechAppSentToSeVideEeDate() {
		return techAppSentToSeVideEeDate;
	}

	public void setTechAppSentToSeVideEeDate(Date techAppSentToSeVideEeDate) {
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

	public Date getTechAppSentToCeVideSeDate() {
		return techAppSentToCeVideSeDate;
	}

	public void setTechAppSentToCeVideSeDate(Date techAppSentToCeVideSeDate) {
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

	public Date getResolutionDate() {
		return resolutionDate;
	}

	public void setResolutionDate(Date resolutionDate) {
		this.resolutionDate = resolutionDate;
	}

	public Date getConstitutionDateSLC() {
		return constitutionDateSLC;
	}

	public void setConstitutionDateSLC(Date constitutionDateSLC) {
		this.constitutionDateSLC = constitutionDateSLC;
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

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "SchemeBean [schemeId=" + schemeId + ", schemeSource="
				+ schemeSource + ", mouSigned=" + mouSigned
				+ ", schemeCommisioned=" 
				+ ", adminAppGoPLetterNo=" + adminAppGoPLetterNo
				+ ", adminAppGoPLetterDate=" + adminAppGoPLetterDate
				+ ", adminAppGoPAmount=" + adminAppGoPAmount
				+ ", adminAppSentSeVideEeNo=" + adminAppSentSeVideEeNo
				+ ", adminAppSentSeVideEeDate=" + adminAppSentSeVideEeDate
				+ ", adminAppSentSeVideEeAmount=" + adminAppSentSeVideEeAmount
				+ ", adminAppSentCeVideSeNo=" + adminAppSentCeVideSeNo
				+ ", adminAppSentCeVideSeDate=" + adminAppSentCeVideSeDate
				+ ", adminAppSentCeVideSeAmount=" + adminAppSentCeVideSeAmount
				+ ", adminAppSentGovtVideCeNo=" + adminAppSentGovtVideCeNo
				+ ", adminAppSentGovtVideCeDate=" + adminAppSentGovtVideCeDate
				+ ", adminAppSentGovtVideCeAmount="
				+ adminAppSentGovtVideCeAmount + ", adminAppLyingWithEeAmount="
				+ adminAppLyingWithEeAmount + ", techAppEeLetterNo="
				+ techAppEeLetterNo + ", techAppEeDate=" + techAppEeDate
				+ ", techAppEeAmount=" + techAppEeAmount
				+ ", techAppSeLetterNo=" + techAppSeLetterNo
				+ ", techAppSeLetterDate=" + techAppSeLetterDate
				+ ", techAppSeAmount=" + techAppSeAmount
				+ ", techAppCeLetterNo=" + techAppCeLetterNo
				+ ", techAppCeLetterDate=" + techAppCeLetterDate
				+ ", techAppCeAmount=" + techAppCeAmount
				+ ", techAppSentToSeVideEeNo=" + techAppSentToSeVideEeNo
				+ ", techAppSentToSeVideEeDate=" + techAppSentToSeVideEeDate
				+ ", techAppSentToSeVideEeAmount="
				+ techAppSentToSeVideEeAmount + ", techAppSentToCeVideSeNo="
				+ techAppSentToCeVideSeNo + ", techAppSentToCeVideSeDate="
				+ techAppSentToCeVideSeDate + ", techAppSentToCeVideSeAmount="
				+ techAppSentToCeVideSeAmount + ", techAppLyingWithEeAmount="
				+ techAppLyingWithEeAmount + ", resolutionDate="
				+ resolutionDate + ", constitutionDateSLC="
				+ constitutionDateSLC + ", beneficiaryShareDue="
				+ beneficiaryShareDue + ", beneficiaryByCommunity="
				+ beneficiaryByCommunity + ", beneficiaryShareVoluntarily="
				+ beneficiaryShareVoluntarily + ", beneficiaryShareNonBudgGp="
				+ beneficiaryShareNonBudgGp
				+ ", beneficiaryShareUntiedDistrict="
				+ beneficiaryShareUntiedDistrict
				+ ", beneficiaryShareStateGovtGrant="
				+ beneficiaryShareStateGovtGrant + ", shareLessThanUpperLimit="
				+ shareLessThanUpperLimit + ", schemeCompleted="
				+ schemeCompleted + ", schemeCommisionedDate="
				+ schemeCommisionedDate + ", schemeCompletedDate="
				+ schemeCompletedDate + ", misAuditBean=" + misAuditBean + ",schemeUpgraded="+ schemeUpgraded +"]";
	}

	public void setSchemeUpgraded(String schemeUpgraded) {
		this.schemeUpgraded = schemeUpgraded;
	}

	public String getSchemeUpgraded() {
		return schemeUpgraded;
	}   
   
    
   
    
    
    
    
}
