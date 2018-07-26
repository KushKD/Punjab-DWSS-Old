package com.prwss.mis.tender.responsive;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="vw_non_responsive_tenders", schema=MISConstants.MIS_DB_SCHEMA_NAME)
@org.hibernate.annotations.Entity(dynamicUpdate=true, selectBeforeUpdate=true)
public class NonResponsiveTenderBean implements Serializable,Comparable<NonResponsiveTenderBean> {


	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="tender_id", nullable=false)
	private String tenderId;
	

	@Column(name="tender_type")
	private String tenderType;
	
	@Column(name="package_id")
	private String packageId;
	
	public String getTenderId() {
		return tenderId;
	}



	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}



	public String getTenderType() {
		return tenderType;
	}



	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}



	public String getPackageId() {
		return packageId;
	}



	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}



	public String getPlanId() {
		return planId;
	}



	public void setPlanId(String planId) {
		this.planId = planId;
	}



	public String getLocationId() {
		return locationId;
	}



	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}



	public String getSchemeCode() {
		return schemeCode;
	}



	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	@Column(name="plan_id")
	private String planId;
	
	
	@Column(name="location_id")
	private String locationId;
		
	@Column(name="scheme_code")
	private String schemeCode;
			
	@Column(name="status")
	private String status;
	
	
	
	@Override
	public int compareTo(NonResponsiveTenderBean o) {
		return this.tenderId.compareTo(o.getTenderId());
	}
	
	

}
