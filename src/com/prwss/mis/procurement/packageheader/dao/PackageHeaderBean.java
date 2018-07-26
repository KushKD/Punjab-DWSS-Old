package com.prwss.mis.procurement.packageheader.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_proc_package_header", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class PackageHeaderBean implements Serializable,Comparable<PackageHeaderBean> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8496622357525743621L;

	@Id
	@Column(name="package_id")
	private String packageId;
	@Id
	@Column(name="location_id", nullable=false)
	private String locationId;
	
	@Column(name="plan_id",nullable=false)
	private String planId;
	
	@Column(name="scheme_id")
	private String schemeId;
	
	@Column(name="sub_plan_id",nullable=false)
	private long subPlanId;
	
	@Column(name="package_description")
	private String packageDescription;
	
	@Column(name="package_type")
	private String packageType;
	
	@Column(name="estimate_package_cost")
	private BigDecimal estimatePackageCost;
	
	@Column(name="anticipated_expenditure_cost")
	private BigDecimal anticipatedExpenditureCost;
	
	@Column(name="post_prior_status")
	private String postPriorStatus;
	
	@Column(name="wb_noc_date")
	private Date wbNocDate;
	
	@Column(name="wb_number")
	private String wbNumber;
	
	@Column(name="wb_remarks")
	private String wbRemarks ;
	
	@Column(name="wb_status")
	private String wbStatus ;
	
	@Column(name="wb_review_submitted_by")
	private long wbReviewSubmittedBy ;
	
	@Column(name="wb_review_ent_date")
	private Timestamp wbReviewEntDate ;
	
	@Column(name="wb_contract_award_noc_date")
	private Date wbContractAwardNocDate ;
	
	@Column(name="wb_bid_doc_noc_date")
	private Date wbBidDocNocDate ;
	
	@Column(name="wing_id")
	private String wingId;
	
	@Column(name="village_id")
	private String villageId;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public BigDecimal getAnticipatedExpenditureCost() {
		return anticipatedExpenditureCost;
	}

	public void setAnticipatedExpenditureCost(BigDecimal anticipatedExpenditureCost) {
		this.anticipatedExpenditureCost = anticipatedExpenditureCost;
	}

	public long getWbReviewSubmittedBy() {
		return wbReviewSubmittedBy;
	}

	public void setWbReviewSubmittedBy(long wbReviewSubmittedBy) {
		this.wbReviewSubmittedBy = wbReviewSubmittedBy;
	}

	public Timestamp getWbReviewEntDate() {
		return wbReviewEntDate;
	}

	public void setWbReviewEntDate(Timestamp wbReviewEntDate) {
		this.wbReviewEntDate = wbReviewEntDate;
	}

	public String getPostPriorStatus() {
		return postPriorStatus;
	}

	public void setPostPriorStatus(String postPriorStatus) {
		this.postPriorStatus = postPriorStatus;
	}

	public Date getWbNocDate() {
		return wbNocDate;
	}

	public void setWbNocDate(Date wbNocDate) {
		this.wbNocDate = wbNocDate;
	}

	public String getWbNumber() {
		return wbNumber;
	}

	public void setWbNumber(String wbNumber) {
		this.wbNumber = wbNumber;
	}

	public String getWbRemarks() {
		return wbRemarks;
	}

	public void setWbRemarks(String wbRemarks) {
		this.wbRemarks = wbRemarks;
	}

	public String getWbStatus() {
		return wbStatus;
	}

	public void setWbStatus(String wbStatus) {
		this.wbStatus = wbStatus;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public long getSubPlanId() {
		return subPlanId;
	}

	public void setSubPlanId(long subPlanId) {
		this.subPlanId = subPlanId;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public BigDecimal getEstimatePackageCost() {
		return estimatePackageCost;
	}

	public void setEstimatePackageCost(BigDecimal estimatePackageCost) {
		this.estimatePackageCost = estimatePackageCost;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	@Override
	public int compareTo(PackageHeaderBean o) {
		return this.packageId.compareTo(o.packageId);
	}

	public void setWbContractAwardNocDate(Date wbContractAwardNocDate) {
		this.wbContractAwardNocDate = wbContractAwardNocDate;
	}

	public Date getWbContractAwardNocDate() {
		return wbContractAwardNocDate;
	}

	public void setWbBidDocNocDate(Date wbBidDocNocDate) {
		this.wbBidDocNocDate = wbBidDocNocDate;
	}

	public Date getWbBidDocNocDate() {
		return wbBidDocNocDate;
	}

	public void setWingId(String wingId) {
		this.wingId = wingId;
	}

	public String getWingId() {
		return wingId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getVillageId() {
		return villageId;
	}

}
