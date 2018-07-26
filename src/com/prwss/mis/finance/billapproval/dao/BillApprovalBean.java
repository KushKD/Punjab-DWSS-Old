package com.prwss.mis.finance.billapproval.dao;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.tender.contract.dao.ContractDetailBean;
import com.prwss.mis.tender.contract.dao.ContractHeaderBean;

@Entity
@Table(name="t_finance_bill_approval",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class BillApprovalBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7359490403959233266L;
	@Id
	@Column(name="voc_id")
	private long vocId;
	
	@ManyToOne(targetEntity=ContractHeaderBean.class)
	@JoinColumn(name="contract_id")
	private ContractHeaderBean contractHeaderBean;
	
	@Column(name="location_id", nullable=false)
	private String locationId;
	
	@Column(name="document_no", nullable=false)
	private String documentNo;
	
	@Column(name="document_ref_no", nullable=false)
	private String documentRefNo;
	
	@ManyToOne(targetEntity=ContractDetailBean.class)
	@JoinColumn(name="milestone_id")
	private ContractDetailBean contractDetailBean;
	
	@Column(name="bill_amount",nullable=false)
	private BigDecimal billAmount;
	
	@Column(name="released_amount",nullable=false)
	private BigDecimal releasedAmount;
	
	@Embedded
	private MISAuditBean misAuditBean;

	
	public String getDocumentRefNo() {
		return documentRefNo;
	}

	public void setDocumentRefNo(String documentRefNo) {
		this.documentRefNo = documentRefNo;
	}

	public long getVocId() {
		return vocId;
	}

	public void setVocId(long vocId) {
		this.vocId = vocId;
	}

	public ContractHeaderBean getContractHeaderBean() {
		return contractHeaderBean;
	}

	public void setContractHeaderBean(ContractHeaderBean contractHeaderBean) {
		this.contractHeaderBean = contractHeaderBean;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public ContractDetailBean getContractDetailBean() {
		return contractDetailBean;
	}

	public void setContractDetailBean(ContractDetailBean contractDetailBean) {
		this.contractDetailBean = contractDetailBean;
	}

	public BigDecimal getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}

	public BigDecimal getReleasedAmount() {
		return releasedAmount;
	}

	public void setReleasedAmount(BigDecimal releasedAmount) {
		this.releasedAmount = releasedAmount;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	

}