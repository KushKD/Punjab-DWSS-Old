package com.prwss.mis.masters.village.struts;

import org.apache.struts.validator.ValidatorActionForm;

import com.prwss.mis.common.MISAuditBean;

/**
 * @author akash
 *
 */ 
public class VillageForm extends ValidatorActionForm {
	
	/**
	 * Initial Version
	 */
private static final long serialVersionUID = 6103941486368726944L;
	private String villageId;
	private String villageName;
	private String blockId;
	private String district;
	private String constituencyId;
	private String[] category;
	private String ncPcVillageStatus;
	private String waterSource;
	private long numberOfPonds;	
	private long ncPcVillageListSerialNo;
	private String habitationType;
	private String parentHabitationId;
	private String divisionalOfficeId;
	private boolean dWSCApproved;
	private String hadBastNo;
	private String subDiv;
	private String pcDate;
	private String fcDate;
	private String parliamentConstituencyName;
	private String nameOfGramPanchayat;
	private MISAuditBean misAuditBean;
	
	
	public String getSubDiv() {
		return subDiv;
	}
	public void setSubDiv(String subDiv) {
		this.subDiv = subDiv;
	}	
	public String getNameOfGramPanchayat() {
		return nameOfGramPanchayat;
	}
	public void setNameOfGramPanchayat(String nameOfGramPanchayat) {
		this.nameOfGramPanchayat = nameOfGramPanchayat;
	}
	public String getParliamentConstituencyName() {
		return parliamentConstituencyName;
	}
	public void setParliamentConstituencyName(String parliamentConstituencyName) {
		this.parliamentConstituencyName = parliamentConstituencyName;
	}
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}
	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	public String getDistrict() {
		return district;
		
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getHadBastNo() {
		return hadBastNo;
	}
	public void setHadBastNo(String hadBastNo) {
		this.hadBastNo = hadBastNo;
	}
	public String getPcDate() {
		return pcDate;
	}
	public void setPcDate(String pcDate) {
		this.pcDate = pcDate;
	}
	public String getFcDate() {
		return fcDate;
	}
	public void setFcDate(String fcDate) {
		this.fcDate = fcDate;
	}
	public boolean isdWSCApproved() {
		return dWSCApproved;
	}
	public void setdWSCApproved(boolean dWSCApproved) {
		this.dWSCApproved = dWSCApproved;
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
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}
//	public String getCategory() {
//		return category;
//	}
//	public void setCategory(String category) {
//		this.category = category;
//	}
	
	
	public String getNcPcVillageStatus() {
		return ncPcVillageStatus;
	}
	public String[] getCategory() {
		return category;
	}
	public void setCategory(String[] category) {
		this.category = category;
	}
	public void setNcPcVillageStatus(String ncPcVillageStatus) {
		this.ncPcVillageStatus = ncPcVillageStatus;
	}
	public String getWaterSource() {
		return waterSource;
	}
	public void setWaterSource(String waterSource) {
		this.waterSource = waterSource;
	}
	public long getNumberOfPonds() {
		return numberOfPonds;
	}
	public void setNumberOfPonds(long numberOfPonds) {
		this.numberOfPonds = numberOfPonds;
	}
	
	public long getNcPcVillageListSerialNo() {
		return ncPcVillageListSerialNo;
	}
	public void setNcPcVillageListSerialNo(long ncPcVillageListSerialNo) {
		this.ncPcVillageListSerialNo = ncPcVillageListSerialNo;
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
	
		
}
