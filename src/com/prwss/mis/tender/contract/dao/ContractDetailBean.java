package com.prwss.mis.tender.contract.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_contract_mgmt_detail", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ContractDetailBean implements Serializable,Comparable<ContractDetailBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 3286299135998923311L;

	@Id
	@SequenceGenerator(name="seq_t_milestone_id", sequenceName="prwss_main.seq_t_milestone_id")
	@GeneratedValue(generator="seq_t_milestone_id", strategy=GenerationType.AUTO)
	@Column(name="milestone_id", nullable=false)
	private long milestoneId;
	
	@Column(name="milestone")
	private String milestone;
	
	@Column(name="contract_id")
	private String contractId;
	
	@Column(name="milestone_status", length=1)
	private String milestoneStatus;
	
	@Column(name="estimated_completion_date")
	private Date estimatedCompletionDate;
	
	@Column(name="revised_completion_date")
	private Date revisedCompletionDate;
	
	@Column(name="actual_completion_date")
	private Date actualCompletionDate;
	
	@Column(name="amount_due")
	private double amountDue;
	
	@Column(name="bill_no")
	private String billNo;
	
	@Column(name="bill_amount", precision=2)
	private double billAmount;
	
	@Column(name="bill_date")
	private Date billDate;
	
	@Column(name="approved_amount", precision=2)
	private double approvedAmount;
	
	@Column(name="amount_approved_date")
	private Date amountApprovedDate;
	
	@Column(name="release_amount", precision=2)
	private double releaseAmount;
	
	@Column(name="amount_release_date")
	private Date amountReleaseDate;
	
	@Column(name="remarks")
	private String remarks;
	
	@Embedded
	private MISAuditBean misAuditBean;


	public long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getMilestoneStatus() {
		return milestoneStatus;
	}

	public void setMilestoneStatus(String milestoneStatus) {
		this.milestoneStatus = milestoneStatus;
	}

	public Date getEstimatedCompletionDate() {
		return estimatedCompletionDate;
	}

	public void setEstimatedCompletionDate(Date estimatedCompletionDate) {
		this.estimatedCompletionDate = estimatedCompletionDate;
	}

	public Date getRevisedCompletionDate() {
		return revisedCompletionDate;
	}

	public void setRevisedCompletionDate(Date revisedCompletionDate) {
		this.revisedCompletionDate = revisedCompletionDate;
	}

	public Date getActualCompletionDate() {
		return actualCompletionDate;
	}

	public void setActualCompletionDate(Date actualCompletionDate) {
		this.actualCompletionDate = actualCompletionDate;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public double getReleaseAmount() {
		return releaseAmount;
	}

	public void setReleaseAmount(double releaseAmount) {
		this.releaseAmount = releaseAmount;
	}

	public Date getAmountReleaseDate() {
		return amountReleaseDate;
	}

	public void setAmountReleaseDate(Date amountReleaseDate) {
		this.amountReleaseDate = amountReleaseDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
		result = prime * result + ((milestone == null) ? 0 : milestone.hashCode());
		result = prime * result + (int) (milestoneId ^ (milestoneId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContractDetailBean other = (ContractDetailBean) obj;
		if (contractId == null) {
			if (other.contractId != null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		if (milestone == null) {
			if (other.milestone != null)
				return false;
		} else if (!milestone.equals(other.milestone))
			return false;
		if (milestoneId != other.milestoneId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContractDetailBean [milestoneId=" + milestoneId + ", milestone=" + milestone + ", contractId="
				+ contractId + ", milestoneStatus=" + milestoneStatus + ", estimatedCompletionDate="
				+ estimatedCompletionDate + ", revisedCompletionDate=" + revisedCompletionDate
				+ ", actualCompletionDate=" + actualCompletionDate + ", amountDue=" + amountDue + ", billNo=" + billNo
				+ ", billAmount=" + billAmount + ", billDate=" + billDate + ", releaseAmount=" + releaseAmount
				+ ", amountReleaseDate=" + amountReleaseDate + ", remarks=" + remarks 
				+ ", approvedAmount="+approvedAmount+", amountApprovedDate="+amountApprovedDate	+", misAuditBean="
				+ misAuditBean + "]";
	}

	@Override
	public int compareTo(ContractDetailBean o) {
		return new Long(this.getMilestoneId()).compareTo(o.getMilestoneId());
	}

	public void setApprovedAmount(double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	public double getApprovedAmount() {
		return approvedAmount;
	}

	public void setAmountApprovedDate(Date amountApprovedDate) {
		this.amountApprovedDate = amountApprovedDate;
	}

	public Date getAmountApprovedDate() {
		return amountApprovedDate;
	}

	
}
