
/**
 * 
 */
package com.prwss.mis.masters.committee.struts;



import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author pjha
 *
 */
public class CommitteeForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8354331158086756680L;
	private long committeeId;	
	private String schemeId;	
	private String committeeConstitutionDate;	
	private String sLCStatus;	
	private String committeeName;
	private String locationId;
	private String blockId;
	private String villageId;
	private Datagrid committeeBankGrid;
	private Datagrid committeeMemberGrid;
	private long bankId;
	private String bankBranch;
	private String bankName;
	
	
	
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public long getBankId() {
		return bankId;
	}
	public void setBankId(long bankId) {
		this.bankId = bankId;
	}
	public String getCommitteeName() {
		return committeeName;
	}
	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public long getCommitteeId() {
		return committeeId;
	}
	public void setCommitteeId(long committeeId) {
		this.committeeId = committeeId;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getCommitteeConstitutionDate() {
		return committeeConstitutionDate;
	}
	public void setCommitteeConstitutionDate(String committeeConstitutionDate) {
		this.committeeConstitutionDate = committeeConstitutionDate;
	}
	public String getsLCStatus() {
		return sLCStatus;
	}
	public void setsLCStatus(String sLCStatus) {
		this.sLCStatus = sLCStatus;
	}
	public Datagrid getCommitteeBankGrid() {
		return committeeBankGrid;
	}
	public void setCommitteeBankGrid(Datagrid committeeBankGrid) {
		this.committeeBankGrid = committeeBankGrid;
	}
	public Datagrid getCommitteeMemberGrid() {
		return committeeMemberGrid;
	}
	public void setCommitteeMemberGrid(Datagrid committeeMemberGrid) {
		this.committeeMemberGrid = committeeMemberGrid;
	}
	
	
	
	
	

}
