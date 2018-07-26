package com.prwss.mis.procurement.physicalprogresspackage.dao;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;


@Entity
@Table(name="vw_signing_of_contract", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class PrwssSigningContractViewBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -869736920766749421L;

	/**
	 * 
	 */
	
	@Id
	@Column(name="tender_id", nullable=false)
	private String tenderId;
	
	@Column(name="signing_of_contract")
	private String signingOfContract;
	
	@Column(name="package_id")
	private String packageId;
	
	
	
	/*@Embedded
	private MISAuditBean misAuditBean;
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}
	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	*/
	
	
	public String getTenderId() {
		return tenderId;
	}
	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}
	public String getSigningOfContract() {
		return signingOfContract;
	}
	public void setSigningOfContract(String signingOfContract) {
		this.signingOfContract = signingOfContract;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	@Override
	public String toString() {
		return "PrwssSigningContractViewBean [Tender Id ="+tenderId+"Signing of contract="+signingOfContract+"packageId="+packageId + "]";
	}	

}
