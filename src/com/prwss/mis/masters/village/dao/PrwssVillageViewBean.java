package com.prwss.mis.masters.village.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="vw_prwss_project_habitation", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class PrwssVillageViewBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 62532777758136768L;
	
	@Id
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	@Column(name="village_name")
	private String villageName;
	
	@Column(name="name_of_gram_panchayat")
	private String nameOfGramPanchayat;
	
	@Column(name="constituency_id")
	private String constituencyId;
	
	@Column(name="block_id")
	private String blockId;
	
	@Column(name="divisional_office_id")
	private String divisionalOfficeId;
	
	
	@Column(name="district_id")
	private String districtId;
	
	@Column(name="nc_pc_status")
	private String ncPcStatus;
	
	@Embedded
	private MISAuditBean misAuditBean;
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}
	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getDivisionalOfficeId() {
		return divisionalOfficeId;
	}
	public void setDivisionalOfficeId(String divisionalOfficeId) {
		this.divisionalOfficeId = divisionalOfficeId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public void setNcPcStatus(String ncPcStatus) {
		this.ncPcStatus = ncPcStatus;
	}
	public String getNcPcStatus() {
		return ncPcStatus;
	}
	public void setNameOfGramPanchayat(String nameOfGramPanchayat) {
		this.nameOfGramPanchayat = nameOfGramPanchayat;
	}
	public String getNameOfGramPanchayat() {
		return nameOfGramPanchayat;
	}
	@Override
	public String toString() {
		return "PrwssVillageViewBean [villageId=" + villageId + ", villageName="
				+ villageName + ", ncPcStatus=" + ncPcStatus
				+ ", nameOfGramPanchayat=" + nameOfGramPanchayat + ", constituencyId="
				+ constituencyId + ", blockId=" + blockId + ", divisionalOfficeId="
				+ divisionalOfficeId + ", districtId=" + districtId	+  "]";
	}	
}
