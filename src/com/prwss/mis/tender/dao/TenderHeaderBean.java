package com.prwss.mis.tender.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_tender_hdr", schema=MISConstants.MIS_DB_SCHEMA_NAME)
@org.hibernate.annotations.Entity(dynamicUpdate=true, selectBeforeUpdate=true)
public class TenderHeaderBean implements Serializable, Comparable<TenderHeaderBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -5998881600641479336L;

	@Id
	@Column(name="tender_id", nullable=false)
	private String tenderId;
	
	@Column(name="publish_date")
	private Date publishDate;
	
	@Column(name="open_date")
	private Date openDate;
	
	@Column(name="close_date")
	private Date closeDate;
	
	@Column(name="tender_type")
	private String tenderType;
	
	@Column(name="procurement_type")
	private String procurementType;
	
	@Column(name="scheme_id")
	private String schemeId;
	
	@Column(name="package_id")
	private String packageId;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="procurement_id")
	private long procurementId;
	
	@Column(name="plan_id")
	private String planId;
	
	@Column(name="user_location")
	private String userLocation;
	
	@Column(name="block_id")
	private String blockId;
	
	@Column(name="village_id")
	private String villageId;
	
	@Column(name="estimated_tender_amount")
	private BigDecimal estimatedTenderAmount;
	
	@Column(name="e_tender_ref_no")
	private String eTenderRefNo;
	
	@Column(name="scheme_code")
	private String schemeCode;
	
	
	@Column(name="date_advertising_shortlisting")
	private Date dateAdvertisingShortlisting;
	
	@Column(name="rfp_draft_to_bank_date")
	private Date rfpDraftToBankDate;
	
	@Column(name="bank_noc_for_tor_date")
	private Date bankNocForTorDate;
	
	@Column(name="bank_noc_for_shortlist_date")
	private Date bankNocForShortlistDate;
	
	@Column(name="bank_noc_for_rfp_date")
	private Date bankNocForRfpDate;
	
	@Column(name="rfp_issued_date")
	private Date rfpIssuedDate;
	
	@Column(name="tor_shortlist_finalized_date")
	private Date torShortlistFinalizedDate;
	
	@Column(name="bank_noc_technical_date")
	private Date bankNocTechnicalDate;
	
	@Column(name="bank_noc_combined_date")
	private Date bankNocCombinedDate;
	
	@Column(name="evaluation_final_technical_date")
	private Date evaluationFinalTechnicalDate;
	
	@Column(name="evaluation_final_combined_date")
	private Date evaluationFinalCombinedDate;
	
	@Column(name="noc_bank_draft_date")
	private Date nocBankDraftDate;
	
	@Column(name="evaluation_final_draft_date")
	private Date evaluationFinalDraftDate;
	
	@Column(name="bank_noc_final_contract_date")
	private Date bankNocFinalContractDate;
	
	@Column(name="evaluation_final_contract_date")
	private Date evaluationFinalContractDate;
	
	@Column(name="proposal_recipt_date")
	private Date proposalReciptDate;
	
	@Column(name="bid_sanction_date")
	private Date bidSanctionDate;
	
	@Column(name="bid_sanction_number")
	private String bidSanctionNumber;
	
	@Column(name="rebid_approval_date")
	private Date rebidApprovalDate;
	
	@Column(name="rebid_approval_number")
	private String rebidApprovalNumber;
	
	@Column(name="package_description")
	private String packageDescription;
	
	@Column(name="proposal_recipt_date_technical")
	private Date proposalReciptDateTechnical;
	
	@Column(name="proposal_recipt_date_financial")
	private Date proposalReciptDateFinancial;
	
	@Column(name="service_completion_date")
	private Date serviceCompletionDate;
	
	
	
	
	public Date getBidSanctionDate() {
		return bidSanctionDate;
	}

	public void setBidSanctionDate(Date bidSanctionDate) {
		this.bidSanctionDate = bidSanctionDate;
	}

	public String getBidSanctionNumber() {
		return bidSanctionNumber;
	}

	public void setBidSanctionNumber(String bidSanctionNumber) {
		this.bidSanctionNumber = bidSanctionNumber;
	}

	public Date getDateAdvertisingShortlisting() {
		return dateAdvertisingShortlisting;
	}

	public void setDateAdvertisingShortlisting(Date dateAdvertisingShortlisting) {
		this.dateAdvertisingShortlisting = dateAdvertisingShortlisting;
	}

	public Date getRfpDraftToBankDate() {
		return rfpDraftToBankDate;
	}

	public void setRfpDraftToBankDate(Date rfpDraftToBankDate) {
		this.rfpDraftToBankDate = rfpDraftToBankDate;
	}

	public Date getBankNocForTorDate() {
		return bankNocForTorDate;
	}

	public void setBankNocForTorDate(Date bankNocForTorDate) {
		this.bankNocForTorDate = bankNocForTorDate;
	}

	public Date getBankNocForShortlistDate() {
		return bankNocForShortlistDate;
	}

	public void setBankNocForShortlistDate(Date bankNocForShortlistDate) {
		this.bankNocForShortlistDate = bankNocForShortlistDate;
	}

	public Date getBankNocForRfpDate() {
		return bankNocForRfpDate;
	}

	public void setBankNocForRfpDate(Date bankNocForRfpDate) {
		this.bankNocForRfpDate = bankNocForRfpDate;
	}

	public Date getRfpIssuedDate() {
		return rfpIssuedDate;
	}

	public void setRfpIssuedDate(Date rfpIssuedDate) {
		this.rfpIssuedDate = rfpIssuedDate;
	}

	public Date getTorShortlistFinalizedDate() {
		return torShortlistFinalizedDate;
	}

	public void setTorShortlistFinalizedDate(Date torShortlistFinalizedDate) {
		this.torShortlistFinalizedDate = torShortlistFinalizedDate;
	}

	public Date getBankNocTechnicalDate() {
		return bankNocTechnicalDate;
	}

	public void setBankNocTechnicalDate(Date bankNocTechnicalDate) {
		this.bankNocTechnicalDate = bankNocTechnicalDate;
	}

	public Date getBankNocCombinedDate() {
		return bankNocCombinedDate;
	}

	public void setBankNocCombinedDate(Date bankNocCombinedDate) {
		this.bankNocCombinedDate = bankNocCombinedDate;
	}

	public Date getEvaluationFinalTechnicalDate() {
		return evaluationFinalTechnicalDate;
	}

	public void setEvaluationFinalTechnicalDate(Date evaluationFinalTechnicalDate) {
		this.evaluationFinalTechnicalDate = evaluationFinalTechnicalDate;
	}

	public Date getEvaluationFinalCombinedDate() {
		return evaluationFinalCombinedDate;
	}

	public void setEvaluationFinalCombinedDate(Date evaluationFinalCombinedDate) {
		this.evaluationFinalCombinedDate = evaluationFinalCombinedDate;
	}

	public Date getNocBankDraftDate() {
		return nocBankDraftDate;
	}

	public void setNocBankDraftDate(Date nocBankDraftDate) {
		this.nocBankDraftDate = nocBankDraftDate;
	}

	public Date getEvaluationFinalDraftDate() {
		return evaluationFinalDraftDate;
	}

	public void setEvaluationFinalDraftDate(Date evaluationFinalDraftDate) {
		this.evaluationFinalDraftDate = evaluationFinalDraftDate;
	}

	public Date getBankNocFinalContractDate() {
		return bankNocFinalContractDate;
	}

	public void setBankNocFinalContractDate(Date bankNocFinalContractDate) {
		this.bankNocFinalContractDate = bankNocFinalContractDate;
	}

	public Date getEvaluationFinalContractDate() {
		return evaluationFinalContractDate;
	}

	public void setEvaluationFinalContractDate(Date evaluationFinalContractDate) {
		this.evaluationFinalContractDate = evaluationFinalContractDate;
	}

	public Date getProposalReciptDate() {
		return proposalReciptDate;
	}

	public void setProposalReciptDate(Date proposalReciptDate) {
		this.proposalReciptDate = proposalReciptDate;
	}



	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String geteTenderRefNo() {
		return eTenderRefNo;
	}

	public void seteTenderRefNo(String eTenderRefNo) {
		this.eTenderRefNo = eTenderRefNo;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	@OneToMany(targetEntity=TenderDetailBean.class, fetch=FetchType.EAGER)
	@JoinColumn(name="tender_id", updatable=false, insertable=false)
	private Set<TenderDetailBean> tenderDetailBeans;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getTenderType() {
		return tenderType;
	}

	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}

	public String getProcurementType() {
		return procurementType;
	}

	public void setProcurementType(String procurementType) {
		this.procurementType = procurementType;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public long getProcurementId() {
		return procurementId;
	}

	public void setProcurementId(long procurementId) {
		this.procurementId = procurementId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public Set<TenderDetailBean> getTenderDetailBeans() {
		return tenderDetailBeans;
	}

	public void setTenderDetailBeans(Set<TenderDetailBean> tenderDetailBeans) {
		this.tenderDetailBeans = tenderDetailBeans;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "TenderHeaderBean [tenderId=" + tenderId + ", publishDate=" + publishDate + ", openDate=" + openDate
				+ ", closeDate=" + closeDate + ", tenderType=" + tenderType + ", procurementType=" + procurementType
				+ ", schemeId=" + schemeId + ", packageId=" + packageId + ", locationId=" + locationId
				+ ", procurementId=" + procurementId + ", planId=" + planId + ", tenderDetailBeans="
				+ tenderDetailBeans + ", misAuditBean=" + misAuditBean + "]";
	}

	@Override
	public int compareTo(TenderHeaderBean o) {
		
		return this.tenderId.compareTo(o.getTenderId());
	}

	public BigDecimal getEstimatedTenderAmount() {
		return estimatedTenderAmount;
	}

	public void setEstimatedTenderAmount(BigDecimal estimatedTenderAmount) {
		this.estimatedTenderAmount = estimatedTenderAmount;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setProposalReciptDateTechnical(
			Date proposalReciptDateTechnical) {
		this.proposalReciptDateTechnical = proposalReciptDateTechnical;
	}

	public Date getProposalReciptDateTechnical() {
		return proposalReciptDateTechnical;
	}

	public void setProposalReciptDateFinancial(
			Date proposalReciptDateFinancial) {
		this.proposalReciptDateFinancial = proposalReciptDateFinancial;
	}

	public Date getProposalReciptDateFinancial() {
		return proposalReciptDateFinancial;
	}

	public void setServiceCompletionDate(Date serviceCompletionDate) {
		this.serviceCompletionDate = serviceCompletionDate;
	}

	public Date getServiceCompletionDate() {
		return serviceCompletionDate;
	}

	public Date getRebidApprovalDate() {
		return rebidApprovalDate;
	}

	public void setRebidApprovalDate(Date rebidApprovalDate) {
		this.rebidApprovalDate = rebidApprovalDate;
	}

	public String getRebidApprovalNumber() {
		return rebidApprovalNumber;
	}

	public void setRebidApprovalNumber(String rebidApprovalNumber) {
		this.rebidApprovalNumber = rebidApprovalNumber;
	}
	
}
