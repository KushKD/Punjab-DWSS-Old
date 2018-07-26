package com.prwss.mis.masters.village.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="vw_mst_village", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillageViewBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 62532777758136768L;
	
	@Id
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	@Column(name="village_name")
	private String villageName;
	
	@Column(name="category")
	private String category;
	
	@Column(name="is_batch")
	private String batch;
	
	@Column(name="constituency_id")
	private String constituencyId;
	
	@Column(name="constituency_name")
	private String constituencyName;
	
	@Column(name="block_id")
	private String blockId;
	
	@Column(name="block_name")
	private String blockName;
	
	@Column(name="water_source")
	private String waterSource;
	
	@Column(name="ncpc_village_list_serial_no")
	private long ncpcVillageListSerialNo;
	
	@Column(name="habitation_type")
	private String habitationType;
	
	@Column(name="parent_habitation_id")
	private String parentHabitationId;
	
	@Column(name="parent_habitation_name")
	private String parentHabitationName;
	
	@Column(name="number_of_ponds")
	private long numberOfPonds;
	
	@Column(name="divisional_office_id")
	private String divisionalOfficeId;
	
	@Column(name="district_id")
	private String districtId;
	
	@Column(name="district_name")
	private String districtName;
	
	
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getWaterSource() {
		return waterSource;
	}

	public void setWaterSource(String waterSource) {
		this.waterSource = waterSource;
	}

	public long getNcpcVillageListSerialNo() {
		return ncpcVillageListSerialNo;
	}

	public void setNcpcVillageListSerialNo(long ncpcVillageListSerialNo) {
		this.ncpcVillageListSerialNo = ncpcVillageListSerialNo;
	}

	public String getHabitationType() {
		return habitationType;
	}

	public void setHabitationType(String habitationType) {
		this.habitationType = habitationType;
	}

	public String getParentHabitationId() {
		return parentHabitationId;
	}

	public void setParentHabitationId(String parentHabitationId) {
		this.parentHabitationId = parentHabitationId;
	}

	public String getParentHabitationName() {
		return parentHabitationName;
	}

	public void setParentHabitationName(String parentHabitationName) {
		this.parentHabitationName = parentHabitationName;
	}

	public long getNumberOfPonds() {
		return numberOfPonds;
	}

	public void setNumberOfPonds(long numberOfPonds) {
		this.numberOfPonds = numberOfPonds;
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

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	@Override
	public String toString() {
		return "VillageBean [villageId=" + villageId + ", villageName="
				+ villageName + ", category=" + category + ", constituencyId="
				+ constituencyId + ", blockId=" + blockId + ", waterSource="
				+ waterSource + ", ncpcVillageListSerialNo="
				+ ncpcVillageListSerialNo + ", habitationType="
				+ habitationType + ", parentHabitationId=" + parentHabitationId
				+ ", numberOfPonds=" + numberOfPonds + ", divisionalOfficeId="
				+ divisionalOfficeId + ", misAuditBean=" + misAuditBean + "]";
	}
		
	
}
