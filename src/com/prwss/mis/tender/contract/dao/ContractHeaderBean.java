package com.prwss.mis.tender.contract.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

 

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.vendor.dao.VendorBean;

@Entity
@Table(name="t_contract_mgmt_hdr", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ContractHeaderBean implements Serializable, Comparable<ContractHeaderBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -108168947239521067L;

	@Id
	@Column(name="contract_id", nullable=false)
	private String contractId;
	
	@OneToOne(targetEntity=VendorBean.class)
	@JoinColumn(name="vendor_id", nullable=false)
	private VendorBean vendorBean;
	
	@Column(name="contract_date")
	private Date contractDate;
	
	@Column(name="revised_contract_date")
	private Date revisedContractDate;
	
	@Column(name="actual_completion_date")
	private Date actualCompletionDate;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="max_ld_rate")
	private double maxLDRate;
	
	@Column(name="ld_amount")
	private double ldAmount;
	
	@Column(name="revised_contract_amount")
	private double revisedContractAmount;
	
	@Column(name="tender_id")
	private String tenderId;
	 
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	@OneToMany(targetEntity=ContractDetailBean.class, fetch=FetchType.EAGER )
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="contract_id", insertable=false, updatable=false)
	private Set<ContractDetailBean> contractDetailBeans;

	public String getContractId() {
		return contractId;
	}

	public double getRevisedContractAmount() {
		return revisedContractAmount;
	}


	public void setRevisedContractAmount(double revisedContractAmount) {
		this.revisedContractAmount = revisedContractAmount;
	}



	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public VendorBean getVendorBean() {
		return vendorBean;
	}

	public void setVendorBean(VendorBean vendorBean) {
		this.vendorBean = vendorBean;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public Date getRevisedContractDate() {
		return revisedContractDate;
	}

	public void setRevisedContractDate(Date revisedContractDate) {
		this.revisedContractDate = revisedContractDate;
	}
	
	public Set<ContractDetailBean> getContractDetailBeans() {
		return contractDetailBeans;
	}

	public void setContractDetailBeans(Set<ContractDetailBean> contractDetailBeans) {
		this.contractDetailBeans = contractDetailBeans;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public double getMaxLDRate() {
		return maxLDRate;
	}

	public void setMaxLDRate(double maxLDRate) {
		this.maxLDRate = maxLDRate;
	}

	public double getLdAmount() {
		return ldAmount;
	}

	public void setLdAmount(double ldAmount) {
		this.ldAmount = ldAmount;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "ContractHeaderBean [contractId=" + contractId + ", vendorBean=" + vendorBean + ", contractDate="
				+ contractDate + ", revisedContractDate=" + revisedContractDate + ", locationId=" + locationId
				+ ", maxLDRate=" + maxLDRate + ", ldAmount=" + ldAmount + ", misAuditBean=" + misAuditBean
				+ ", contractDetailBeans=" + contractDetailBeans + ",tenderId="+ tenderId +"]";
	}

	@Override
	public int compareTo(ContractHeaderBean o) {
	
		return this.contractId.compareTo(o.getContractId());
	}

	public void setActualCompletionDate(Date actualCompletionDate) {
		this.actualCompletionDate = actualCompletionDate;
	}

	public Date getActualCompletionDate() {
		return actualCompletionDate;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public String getTenderId() {
		return tenderId;
	}

	
}
