package com.prwss.mis.tender.struts;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;

import com.prwss.mis.common.MISAuditBean;


public class ContractDetailGridBean {
	@Embedded
	private MISAuditBean misAuditBean;

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	private long milestoneId;
	
	private String milestone;
	
	private String contractId;
	
	private String milestoneStatus;
	
	private String estimatedCompletionDate;
	
	private String revisedCompletionDate;
	
	private String actualCompletionDate;
	
	private double amountDue;
	
	private String billNo;
	
	private double billAmount;
	
	private String billDate;
	
	private double releaseAmount;
	
	private String amountReleaseDate;
	
	private String remarks;
	
	private double approvedAmount;
	private String amountApprovedDate;
	
	public double getApprovedAmount() {
		return approvedAmount;
	}

	public void setApprovedAmount(double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
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

	public String getEstimatedCompletionDate() {
		return estimatedCompletionDate;
	}

	public void setEstimatedCompletionDate(String estimatedCompletionDate) {
		this.estimatedCompletionDate = estimatedCompletionDate;
	}

	public String getRevisedCompletionDate() {
		return revisedCompletionDate;
	}

	public void setRevisedCompletionDate(String revisedCompletionDate) {
		this.revisedCompletionDate = revisedCompletionDate;
	}

	public String getActualCompletionDate() {
		return actualCompletionDate;
	}

	public void setActualCompletionDate(String actualCompletionDate) {
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

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public double getReleaseAmount() {
		return releaseAmount;
	}

	public void setReleaseAmount(double releaseAmount) {
		this.releaseAmount = releaseAmount;
	}

	public String getAmountReleaseDate() {
		return amountReleaseDate;
	}

	public void setAmountReleaseDate(String amountReleaseDate) {
		this.amountReleaseDate = amountReleaseDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setAmountApprovedDate(String amountApprovedDate) {
		this.amountApprovedDate = amountApprovedDate;
	}

	public String getAmountApprovedDate() {
		return amountApprovedDate;
	}
	
	
}
