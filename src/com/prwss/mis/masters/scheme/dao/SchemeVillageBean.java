package com.prwss.mis.masters.scheme.dao;

import java.io.Serializable;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name = "mst_scheme_village", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class SchemeVillageBean implements Serializable,
		Comparable<SchemeVillageBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4296343766255392619L;

	@Id
	@Column(name = "scheme_id", nullable = false)
	private String schemeId;

	@Id
	@Column(name = "village_id", nullable = false)
	private String villageId;

	@Id
	@Column(name = "scheme_upgraded")
	private String schemeUpgraded;

	@Column(name = "scheme_commissioned_date")
	private Date schemeCommissionedDate;

	@Column(name = "vill_name")
	private String villageName;

	@Column(name = "supply_service_level")
	private String supplyServiceLevel;

	@Column(name = "scheme_operated_by")
	private String schemeOperatedBy;

	@Column(name = "operation_date")
	private Date operationDate;

	@Column(name = "hours")
	private int schemeHours;

	@Column(name = "water_supply_fp")
	private String schemeFP;

	@Column(name = "village_category")
	private String villageCategory;

	@Column(name = "location_water_works", nullable = false)
	@Type(type = "yes_no")
	private boolean waterWorksLocation;

	@Embedded
	private MISAuditBean misAuditBean;

	public boolean isWaterWorksLocation() {
		return waterWorksLocation;
	}

	public void setWaterWorksLocation(boolean waterWorksLocation) {

		this.waterWorksLocation = waterWorksLocation;
	}

	/*
	 * public String getWaterWorksLocation() { return waterWorksLocation; }
	 * public void setWaterWorksLocation(String waterWorksLocation) {
	 * this.waterWorksLocation = waterWorksLocation; }
	 */
	public String getSchemeUpgraded() {
		return schemeUpgraded;
	}

	public void setSchemeUpgraded(String schemeUpgraded) {
		this.schemeUpgraded = schemeUpgraded;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Date getSchemeCommissionedDate() {
		return schemeCommissionedDate;
	}

	public void setSchemeCommissionedDate(Date schemeCommissionedDate) {
		this.schemeCommissionedDate = schemeCommissionedDate;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	@Override
	public String toString() {
		return "SchemeVillageBean [schemeId=" + schemeId + ", villageId="
				+ villageId + ", misAuditBean=" + misAuditBean
				+ ",waterWorksLocation= " + waterWorksLocation + ",schemeFP="
				+ schemeFP + "]";
	}

	@Override
	public int compareTo(SchemeVillageBean o) {

		return this.schemeId.compareTo(o.schemeId);
	}

	public void setSchemeOperatedBy(String schemeOperatedBy) {
		this.schemeOperatedBy = schemeOperatedBy;
	}

	public String getSchemeOperatedBy() {
		return schemeOperatedBy;
	}

	public void setSupplyServiceLevel(String supplyServiceLevel) {
		this.supplyServiceLevel = supplyServiceLevel;
	}

	public String getSupplyServiceLevel() {
		return supplyServiceLevel;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public int getSchemeHours() {
		return schemeHours;
	}

	public void setSchemeHours(int schemeHours) {
		this.schemeHours = schemeHours;
	}

	public String getSchemeFP() {
		return schemeFP;
	}

	public void setSchemeFP(String schemeFP) {
		this.schemeFP = schemeFP;
	}

	public String getVillageCategory() {
		return villageCategory;
	}

	public void setVillageCategory(String villageCategory) {
		this.villageCategory = villageCategory;
	}

}