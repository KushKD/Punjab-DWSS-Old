package com.prwss.mis.masters.village;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;
@Entity
@Table(name="tbl_proposed_program_2008", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ProposedProgramBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="village_id")
	private String villageId;
		
	@Column(name="proposed_program_id")
	private String proposedProgramId;
	
	@Column(name = "proposed_program_name")
	private String proposedProgramName;
	
	@Column(name = "proposed_swap_nonswap")
	private String proposedSwapNonswap;
	
	@Column (name = "nc_pc_status")
	private String ncPcStatus;
	
	@Column(name = "freez_value")
	private Date freezValue;

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getProposedProgramId() {
		return proposedProgramId;
	}

	public void setProposedProgramId(String proposedProgramId) {
		this.proposedProgramId = proposedProgramId;
	}

	public String getProposedProgramName() {
		return proposedProgramName;
	}

	public void setProposedProgramName(String proposedProgramName) {
		this.proposedProgramName = proposedProgramName;
	}

	public String getProposedSwapNonswap() {
		return proposedSwapNonswap;
	}

	public void setProposedSwapNonswap(String proposedSwapNonswap) {
		this.proposedSwapNonswap = proposedSwapNonswap;
	}

	public String getNcPcStatus() {
		return ncPcStatus;
	}

	public void setNcPcStatus(String ncPcStatus) {
		this.ncPcStatus = ncPcStatus;
	}

	public Date getFreezValue() {
		return freezValue;
	}

	public void setFreezValue(Date freezValue) {
		this.freezValue = freezValue;
	}

	
	
	
}
