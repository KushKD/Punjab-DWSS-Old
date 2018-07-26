package com.prwss.mis.tender.award.dao;

import java.io.Serializable;
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
@Table(name = "t_tender_award", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class TenderAwardBean implements Serializable {

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 6456564186365213580L;

	@Id
	@Column(name = "tender_id", nullable = false)
	private String tenderId;

	@Column(name = "vendor_id")
	private String vendorId;

	@Column(name = "tender_amount", precision = 2)
	private double tenderAmount;

	@Column(name = "contract_start_date")
	private Date contractStartDate;

	@Column(name = "contract_end_date")
	private Date contractEndDate;

	@Column(name = "contract_no")
	private String contractNo;

	@Column(name = "signing_of_contract")
	private Date signingOfContract;

	@Column(name = "notice_to_proceed")
	private Date noticeToProceed;

	

	@Column(name = "bid_evaluation_date")
	private Date bidEvaluationDate;

	public Date getBidEvaluationDate() {
		return bidEvaluationDate;
	}

	public void setBidEvaluationDate(Date bidEvaluationDate) {
		this.bidEvaluationDate = bidEvaluationDate;
	}

	
	@Column(name = "objection_status")
	private boolean objectionable;

	@Embedded
	private MISAuditBean misAuditBean;

	@OneToMany(targetEntity = TenderObjectionBean.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "tender_id", updatable = false, insertable = false)
	private Set<TenderObjectionBean> tenderObjectionBeans;

	@OneToMany(targetEntity = TenderSecurityDepositBean.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "tender_id", updatable = false, insertable = false)
	private Set<TenderSecurityDepositBean> tenderSecurityDepositBeans;

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public double getTenderAmount() {
		return tenderAmount;
	}

	public void setTenderAmount(double tenderAmount) {
		this.tenderAmount = tenderAmount;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Date getSigningOfContract() {
		return signingOfContract;
	}

	public void setSigningOfContract(Date signingOfContract) {
		this.signingOfContract = signingOfContract;
	}

	public Date getNoticeToProceed() {
		return noticeToProceed;
	}

	public void setNoticeToProceed(Date noticeToProceed) {
		this.noticeToProceed = noticeToProceed;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public void setTenderObjectionBeans(
			Set<TenderObjectionBean> tenderObjectionBeans) {
		this.tenderObjectionBeans = tenderObjectionBeans;
	}

	public Set<TenderObjectionBean> getTenderObjectionBeans() {
		return tenderObjectionBeans;
	}

	public void setTenderSecurityDepositBeans(
			Set<TenderSecurityDepositBean> tenderSecurityDepositBeans) {
		this.tenderSecurityDepositBeans = tenderSecurityDepositBeans;
	}

	public Set<TenderSecurityDepositBean> getTenderSecurityDepositBeans() {
		return tenderSecurityDepositBeans;
	}

	public void setObjectionable(boolean objectionable) {
		this.objectionable = objectionable;
	}

	public boolean isObjectionable() {
		return objectionable;
	}

	@Override
	public String toString() {
		return "TenderAwardBean [tenderId=" + tenderId + ", vendorId="
				+ vendorId + ", tenderAmount=" + tenderAmount
				+ ", contractStartDate=" + contractStartDate
				+ ", contractEndDate=" + contractEndDate + ", contractNo="
				+ contractNo + ", signingOfContract=" + signingOfContract
				+ ", noticeToProceed=" + noticeToProceed
				+ ", bidEvaluationDate=" + bidEvaluationDate
				+ ", objectionable=" + objectionable + ", misAuditBean="
				+ misAuditBean + ", tenderObjectionBeans="
				+ tenderObjectionBeans + ", tenderSecurityDepositBeans="
				+ tenderSecurityDepositBeans + "]";
	}

	// Old Code
	

	
	
	
	
}
