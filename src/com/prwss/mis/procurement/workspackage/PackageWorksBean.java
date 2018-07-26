package com.prwss.mis.procurement.workspackage;

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
@Table(name="t_proc_package_works", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class PackageWorksBean implements Serializable,Comparable<PackageWorksBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8129642884535220860L;
	@Id
	@Column(name="package_id", nullable=false)
	private String packageId;
	
	@Column(name="method_procurement")
	private String methodOfProcurement;
	
	@Column(name="design_investigation_date")
	private Date designInvestigationDate;
	
	@Column(name="est_prepared_sanction_date")
	private Date estPreparedSanctionDate;
	
	@Column(name="prep_bid_doc_date")
	private Date prepBidDocDate;
	
	@Column(name="bank_noc_bid_date")
	private Date bankNocBidDate;
	
	@Column(name="bid_invitation_date")
	private Date bidInvitationDate;
	
	@Column(name="bid_opening_date")
	private Date bidOpeningDate;
	
	@Column(name="con_award_decide_date")
	private Date conAwardDecideDate;
	
	@Column(name="bank_noc_con_award_date")
	private Date bankNocConAwardDate;
	
	@Column(name="con_completion")
	private Date conCompletionDate ;
	
	@Column(name="con_sign_date")
	private Date conSignDate ;
	
	@Column(name="target_commissioning_date")
	private Date targetCommissioningDate ;
	
	
	public Date getTargetCommissioningDate() {
		return targetCommissioningDate;
	}



	public void setTargetCommissioningDate(Date targetCommissioningDate) {
		this.targetCommissioningDate = targetCommissioningDate;
	}



	@Embedded
	private MISAuditBean misAuditBean;


	public String getPackageId() {
		return packageId;
	}



	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}



	public String getMethodOfProcurement() {
		return methodOfProcurement;
	}



	public void setMethodOfProcurement(String methodOfProcurement) {
		this.methodOfProcurement = methodOfProcurement;
	}



	public Date getDesignInvestigationDate() {
		return designInvestigationDate;
	}



	public void setDesignInvestigationDate(Date designInvestigationDate) {
		this.designInvestigationDate = designInvestigationDate;
	}



	public Date getEstPreparedSanctionDate() {
		return estPreparedSanctionDate;
	}



	public void setEstPreparedSanctionDate(Date estPreparedSanctionDate) {
		this.estPreparedSanctionDate = estPreparedSanctionDate;
	}



	public Date getPrepBidDocDate() {
		return prepBidDocDate;
	}



	public void setPrepBidDocDate(Date prepBidDocDate) {
		this.prepBidDocDate = prepBidDocDate;
	}



	public Date getBankNocBidDate() {
		return bankNocBidDate;
	}



	public void setBankNocBidDate(Date bankNocBidDate) {
		this.bankNocBidDate = bankNocBidDate;
	}



	public Date getBidInvitationDate() {
		return bidInvitationDate;
	}



	public void setBidInvitationDate(Date bidInvitationDate) {
		this.bidInvitationDate = bidInvitationDate;
	}



	public Date getBidOpeningDate() {
		return bidOpeningDate;
	}



	public void setBidOpeningDate(Date bidOpeningDate) {
		this.bidOpeningDate = bidOpeningDate;
	}



	public Date getConAwardDecideDate() {
		return conAwardDecideDate;
	}



	public void setConAwardDecideDate(Date conAwardDecideDate) {
		this.conAwardDecideDate = conAwardDecideDate;
	}



	public Date getBankNocConAwardDate() {
		return bankNocConAwardDate;
	}



	public void setBankNocConAwardDate(Date bankNocConAwardDate) {
		this.bankNocConAwardDate = bankNocConAwardDate;
	}



	public Date getConCompletionDate() {
		return conCompletionDate;
	}



	public void setConCompletionDate(Date conCompletionDate) {
		this.conCompletionDate = conCompletionDate;
	}



	public Date getConSignDate() {
		return conSignDate;
	}



	public void setConSignDate(Date conSignDate) {
		this.conSignDate = conSignDate;
	}



	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}



	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}



	@Override
	public int compareTo(PackageWorksBean o) {
		
		return this.packageId.compareTo(o.packageId);
	}
	
	
}
