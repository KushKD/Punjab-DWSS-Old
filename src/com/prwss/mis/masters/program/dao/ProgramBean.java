/**
 * 
 */
package com.prwss.mis.masters.program.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author pjha
 *
 */
@Entity
@Table(name="mst_program", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ProgramBean implements Serializable,Comparable<ProgramBean> {


	private static final long serialVersionUID = -3980195761642478117L;



	@Id	
	@Column(name="program_id", nullable=false)
	private String programId;

	
	@Column(name="program_name", nullable=false)
	private String programName;

	@Column(name="sponser_agency_share")
	private String sponserAgencyShare;
	
	@Column(name="goi_share")
	private String goiShare;
	
	@Column(name="gop_share")
	private String gopShare;
	
	@Column(name="benifciary_share")
	private String benifciaryShare;
	
	@Column(name="planned_nopplanned")
	private String plannedNonPlanned;
	
	@Column(name="swap_nonswap")
	private String swapNonSwap;
	
	@Column(name="program")
	private String program;
	
	@Embedded
	private MISAuditBean misAuditBean;

	

	public String getProgram() {
		return program;
	}



	public void setProgram(String program) {
		this.program = program;
	}





	


	public String getProgramId() {
		return programId;
	}



	public void setProgramId(String programId) {
		this.programId = programId;
	}



	public String getProgramName() {
		return programName;
	}



	public void setProgramName(String programName) {
		this.programName = programName;
	}



	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}



	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}



	public String getSponserAgencyShare() {
		return sponserAgencyShare;
	}



	public void setSponserAgencyShare(String sponserAgencyShare) {
		this.sponserAgencyShare = sponserAgencyShare;
	}



	public String getGoiShare() {
		return goiShare;
	}



	public void setGoiShare(String goiShare) {
		this.goiShare = goiShare;
	}



	public String getGopShare() {
		return gopShare;
	}



	public void setGopShare(String gopShare) {
		this.gopShare = gopShare;
	}



	public String getBenifciaryShare() {
		return benifciaryShare;
	}



	public void setBenifciaryShare(String benifciaryShare) {
		this.benifciaryShare = benifciaryShare;
	}



	public String getPlannedNonPlanned() {
		return plannedNonPlanned;
	}



	public void setPlannedNonPlanned(String plannedNonPlanned) {
		this.plannedNonPlanned = plannedNonPlanned;
	}



	public String getSwapNonSwap() {
		return swapNonSwap;
	}



	public void setSwapNonSwap(String swapNonSwap) {
		this.swapNonSwap = swapNonSwap;
	}



	@Override
	public String toString() {
		return "ProgramBean [programId=" + programId + ", programName="
				+ programName + ", sponserAgencyShare=" + sponserAgencyShare
				+ ", goiShare=" + goiShare + ", gopShare=" + gopShare
				+ ", benifciaryShare=" + benifciaryShare
				+ ", plannedNonPlanned=" + plannedNonPlanned + ", swapNonSwap="
				+ swapNonSwap + ", program=" + program + ", misAuditBean="
				+ misAuditBean + "]";
	}



	@Override
	public int compareTo(ProgramBean o) {
		// TODO Auto-generated method stub
		return this.programName.compareTo(o.programName);
	}




}
