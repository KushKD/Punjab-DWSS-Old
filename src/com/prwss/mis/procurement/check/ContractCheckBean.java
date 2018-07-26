package com.prwss.mis.procurement.check;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="contract_check_2", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ContractCheckBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tender_id", nullable = false)
	private String tenderId;
		
	@Column(name ="loc_name")
	private String locationName;
	
	@Column(name ="location_id")
	private String locationId;
	
	@Column(name ="contract_no")
	private String contractNo;
	
	@Column(name ="rel_amnt")
	private long relAmount;
	
	@Column(name ="tender_amount")
	private long tenderAmount;
	
	@Column(name ="office_email")
	private String officeEmail;
	
	@Column(name ="open_diff")
	private long openDiff;
	
	@Column(name="status")
	private String status;
	
	@Column(name="package_id")
	private String packageId;
	
	@Column(name="signing_of_contract")
	private Date signing_of_contract;
	
	@Column(name="actual_completion_date")
	private Date  actualCompletionDate;
	
	@Column(name="contract_end_date")
	private Date contractEndDate;
	
	
	

	public Date getActualCompletionDate() {
		return actualCompletionDate;
	}

	public void setActualCompletionDate(Date actualCompletionDate) {
		this.actualCompletionDate = actualCompletionDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	
	public Date getSigning_of_contract() {
		return signing_of_contract;
	}

	public void setSigning_of_contract(Date signing_of_contract) {
		this.signing_of_contract = signing_of_contract;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public long getOpenDiff() {
		return openDiff;
	}

	public void setOpenDiff(long openDiff) {
		this.openDiff = openDiff;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getOfficeEmail() {
		return officeEmail;
	}

	public void setOfficeEmail(String officeEmail) {
		this.officeEmail = officeEmail;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public long getRelAmount() {
		return relAmount;
	}

	public void setRelAmount(long relAmount) {
		this.relAmount = relAmount;
	}

	public long getTenderAmount() {
		return tenderAmount;
	}

	public void setTenderAmount(long tenderAmount) {
		this.tenderAmount = tenderAmount;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	@Column(name ="scheme_code")
	private String schemeCode;
	
	@Column(name ="scheme_name")
	private String schemeName;

	
}
