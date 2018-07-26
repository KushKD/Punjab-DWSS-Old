package com.prwss.mis.masters.employee.dao;

import java.io.Serializable;

public class EmployeeContactExtendedGridBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7659187243200069180L;
	
	private long id;
	
	private String extentionContarctNo;
	
	private String extentionContractDate;
	
	private String extentionContractUpto;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExtentionContarctNo() {
		return extentionContarctNo;
	}

	public void setExtentionContarctNo(String extentionContarctNo) {
		this.extentionContarctNo = extentionContarctNo;
	}

	public String getExtentionContractDate() {
		return extentionContractDate;
	}

	public void setExtentionContractDate(String extentionContractDate) {
		this.extentionContractDate = extentionContractDate;
	}

	public String getExtentionContractUpto() {
		return extentionContractUpto;
	}

	public void setExtentionContractUpto(String extentionContractUpto) {
		this.extentionContractUpto = extentionContractUpto;
	}
	
	

}
