package com.prwss.mis.tender.contract.dao;
 

import java.io.Serializable;

import javax.persistence.Column;
 
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
 
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="vw_contract_info", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ContractManagementInfoBean implements Serializable{
	 
	private static final long serialVersionUID = 3L;
	
	@Id
	@Column(name="contract_id", nullable=false)
	private String contractId;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="tender_id")
	private String tenderId;
	
	@Column(name="scheme_id")
	private String schemeId;
	
	@Column(name="package_id")
	private String packageId;
	

	@Column(name="package_description")
	private String packageDescription;
		

	@Column(name="tender_type")
	private String tenderType;
	
	@Column(name="scheme_name", nullable=false)
	private String schemeName;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
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

	 

	public String getTenderType() {
		return tenderType;
	}

	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

//	@Override
//	public int compareTo(ContractManagementInfoBean o) {
//	
//		return this.contractId.compareTo(o.getContractId());
//	}

	
	@Override
	public String toString() {
		return "ContractManagementInfoBean [contractId=" + contractId + " locationId=" + locationId
				+ ",tenderId="+ tenderId +",schemeId="+ schemeId +",packageId="+ packageId +",packageDescription="+ packageDescription +",tenderType="+ tenderType +",schemeName="+ schemeName +",misAuditBean="+ misAuditBean +"]";
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getSchemeName() {
		return schemeName;
	}
	
	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}
	
	
	
}
