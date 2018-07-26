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
@Table(name="vw_prwss_all_habitation", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class PrwssVillageAllHabitationBean implements Serializable
{
private static final long serialVersionUID = 2L;
	
	@Id
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	@Column(name="village_name")
	private String villageName;
	
	@Column(name="nc_pc_status")
	private String ncPcStatus;
	
	@Column(name="habitation_type")
	private String habitationType;
	
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
	public void setHabitationType(String habitationType) {
		this.habitationType = habitationType;
	}
	public String getHabitationType() {
		return habitationType;
	}	

	@Override
	public String toString() {
		return "PrwssVillageViewBean [villageId=" + villageId + ", villageName="
				+ villageName + ", ncPcStatus=" + ncPcStatus
				+ ",habitationType="+habitationType+", nameOfGramPanchayat=" + nameOfGramPanchayat + ", constituencyId="
				+ constituencyId + ", blockId=" + blockId + ", divisionalOfficeId="
				+ divisionalOfficeId + ", districtId=" + districtId	+ "]";
	}
	
}
