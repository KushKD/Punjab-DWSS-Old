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
@Table(name="mst_village", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillageBean implements Serializable, Comparable<VillageBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 233473896461303282L;

	@Id
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	@Column(name="village_name", nullable=false)
	private String villageName;
	
	@Column(name="nc_pc_fc_status")
	private String ncPcVillageStatus;
	
	@Column(name="category")
	private String category;
	
	@Column(name="is_batch")
	private String batch;
	
	@Column(name="freez_value")
	private Date freezedValue;
	
	@Column(name="parliament_constituency_name")
	private String parliamentConstituencyName;
	
	@Column(name="name_of_gram_panchayat")
	private String nameOfGramPanchayat;
	
	@Column(name="constituency_id")
	private String constituencyId;
	
	@Column(name="block_id")
	private String blockId;
	
	@Column(name="water_source")
	private String waterSource;
	
	@Column(name="ncpc_village_list_serial_no")
	private long ncpcVillageListSerialNo;
	
	@Column(name="habitation_type")
	private String habitationType;
	
	@Column(name="parent_habitation_id")
	private String parentHabitationId;
	
	@Column(name="number_of_ponds")
	private long numberOfPonds;
	
	@Column(name="divisional_office_id")
	private String divisionalOfficeId;
	
	@Column(name="is_dwsc_approved")
	private boolean dWSCApproved;
	
	@Column(name="had_bast_no")
	private String hadBastNo;
	
	@Column(name="pc_date")
	private Date pcDate;
	
	@Column(name="sub_div")
	private String subDiv;

	@Column(name="fc_date")
	private Date fcDate;
	
	@Column(name="district_id")
	private String districtId;
	
	@Column(name="parent_habitation_name")
	private String parentHabitationName;
	
	@Column(name="spl_flags")
	private String spl_flags;
	
	
	@Embedded
	private MISAuditBean misAuditBean;

	
	
	/**
	 * @return the spl_flags
	 */
	public String getSpl_flags() {
		return spl_flags;
	}

	/**
	 * @param spl_flags the spl_flags to set
	 */
	public void setSpl_flags(String spl_flags) {
		this.spl_flags = spl_flags;
	}

	public Date getFreezedValue() {
		return freezedValue;
	}

	public void setFreezedValue(Date freezedValue) {
		this.freezedValue = freezedValue;
	}

	public String getSubDiv() {
		return subDiv;
	}

	public void setSubDiv(String subDiv) {
		this.subDiv = subDiv;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDivisionalOfficeId() {
		return divisionalOfficeId;
	}

	public void setDivisionalOfficeId(String divisionalOfficeId) {
		this.divisionalOfficeId = divisionalOfficeId;
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

	public String getNcPcVillageStatus() {
		return ncPcVillageStatus;
	}

	public void setNcPcVillageStatus(String ncPcVillageStatus) {
		this.ncPcVillageStatus = ncPcVillageStatus;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public long getNumberOfPonds() {
		return numberOfPonds;
	}

	public void setNumberOfPonds(long numberOfPonds) {
		this.numberOfPonds = numberOfPonds;
	}
	
	public boolean isdWSCApproved() {
		return dWSCApproved;
	}

	public void setdWSCApproved(boolean dWSCApproved) {
		this.dWSCApproved = dWSCApproved;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	
	public String getHadBastNo() {
		return hadBastNo;
	}

	public void setHadBastNo(String hadBastNo) {
		this.hadBastNo = hadBastNo;
	}

	public Date getPcDate() {
		return pcDate;
	}

	public void setPcDate(Date pcDate) {
		this.pcDate = pcDate;
	}

	public Date getFcDate() {
		return fcDate;
	}

	public void setFcDate(Date fcDate) {
		this.fcDate = fcDate;
	}

	
	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	@Override
	public String toString() {
		return "VillageBean [villageId=" + villageId + ", villageName="
				+ villageName + ", ncPcVillageStatus=" + ncPcVillageStatus
				+ ", category=" + category + ", constituencyId="
				+ constituencyId + ", blockId=" + blockId + ", waterSource="
				+ waterSource + ", ncpcVillageListSerialNo="
				+ ncpcVillageListSerialNo + ", habitationType="
				+ habitationType + ", parentHabitationId=" + parentHabitationId
				+ ", numberOfPonds=" + numberOfPonds + ", divisionalOfficeId="
				+ divisionalOfficeId + ", SubDivisionalID="
				+ subDiv + ", dWSCApproved=" + dWSCApproved
				+ ", hadBastNo=" + hadBastNo + ", pcDate=" + pcDate
				+ ", fcDate=" + fcDate + ", misAuditBean=" + misAuditBean + ",parentHabitationName="+ parentHabitationName +"]";
	}

	@Override
	public int compareTo(VillageBean o) {
		
		return o.getVillageId().compareTo(this.getVillageId());
	}

	public String getParliamentConstituencyName() {
		return parliamentConstituencyName;
	}

	public void setParliamentConstituencyName(String parliamentConstituencyName) {
		this.parliamentConstituencyName = parliamentConstituencyName;
	}

	public String getNameOfGramPanchayat() {
		return nameOfGramPanchayat;
	}

	public void setNameOfGramPanchayat(String nameOfGramPanchayat) {
		this.nameOfGramPanchayat = nameOfGramPanchayat;
	}

	public void setParentHabitationName(String parentHabitationName) {
		this.parentHabitationName = parentHabitationName;
	}

	public String getParentHabitationName() {
		return parentHabitationName;
	}
	
}
