package com.prwss.mis.procurement.servicespackage;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
@Entity
@Table(name="t_proc_package_services", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class PackageServicesBean implements Serializable,Comparable<PackageServicesBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8938755629367560747L;

	@Id
	@Column(name="package_id")
	private String packageId;
	
	@Column(name="method_of_selection")
	private String methodOfSelection;
	
	@Column(name="date_advertising_shortlisting")
	private Date dateAdvertisingShortlisting;
	
	@Column(name="rfp_draft_to_bank_date")
	private Date rfpDraftBankDate;
	
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
	
	@Column(name="service_completion_date")
	private Date serviceCompletionDate;
	
	@Column(name="financial_proposal_opening_date")
	private Date financialProposalOpeningDate;
	
	@Column(name="proposal_recipt_date_financial")
	private Date proposalReciptDateFinancial;
	
	@Embedded
	private MISAuditBean misAuditBean;
	

	public String getPackageId() {
		return packageId;
	}




	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}




	public String getMethodOfSelection() {
		return methodOfSelection;
	}




	public void setMethodOfSelection(String methodOfSelection) {
		this.methodOfSelection = methodOfSelection;
	}




	public Date getDateAdvertisingShortlisting() {
		return dateAdvertisingShortlisting;
	}




	public void setDateAdvertisingShortlisting(Date dateAdvertisingShortlisting) {
		this.dateAdvertisingShortlisting = dateAdvertisingShortlisting;
	}




	public Date getRfpDraftBankDate() {
		return rfpDraftBankDate;
	}




	public void setRfpDraftBankDate(Date rfpDraftBankDate) {
		this.rfpDraftBankDate = rfpDraftBankDate;
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




	public Date getServiceCompletionDate() {
		return serviceCompletionDate;
	}




	public void setServiceCompletionDate(Date serviceCompletionDate) {
		this.serviceCompletionDate = serviceCompletionDate;
	}




	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}




	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}




	@Override
	public int compareTo(PackageServicesBean o) {
		return this.packageId.compareTo(o.packageId);
	}








	public Date getFinancialProposalOpeningDate() {
		return financialProposalOpeningDate;
	}




	public void setFinancialProposalOpeningDate(Date financialProposalOpeningDate) {
		this.financialProposalOpeningDate = financialProposalOpeningDate;
	}




	public void setProposalReciptDateFinancial(
			Date proposalReciptDateFinancial) {
		this.proposalReciptDateFinancial = proposalReciptDateFinancial;
	}




	public Date getProposalReciptDateFinancial() {
		return proposalReciptDateFinancial;
	}

}
