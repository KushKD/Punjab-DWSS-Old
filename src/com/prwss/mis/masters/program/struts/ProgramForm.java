package com.prwss.mis.masters.program.struts;


import org.apache.struts.validator.ValidatorActionForm;


/**
 * @author 
 *
 */ 
public class ProgramForm extends ValidatorActionForm {
	
	/**
	 * Initial Version
	 */
private static final long serialVersionUID = 6103941486368726944L;
	private String programId;
	private String programName;
	private String sponserAgencyShare;
	private String goiShare;
	private String gopShare;
	private String benifciaryShare;
	private String plannedNonPlanned;	
	private String swapNonSwap;
	private String program;
	
	
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}
	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
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

}