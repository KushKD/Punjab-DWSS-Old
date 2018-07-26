package com.prwss.mis.pmm.RoScreenStruts;

import java.io.Serializable;
import java.sql.Date;

public class RoScreenDto implements Serializable{

	private static final long serialVersionUID = -9102585962278689L;
	
	private String division;
	private String divisionName;
	private String subDivision;
	private String subDivisionName;
	private String village;
	private String villageName;
	private String capacityOfRO;
	private String headProgramme;
	private String executingAgency;
	private Date dateCompOandM;
	private Long adminAppCost;
	private Integer roConnectionId;
	private String seasonalRO;
	private Long noHHsVillage;
	
	
	
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getSubDivision() {
		return subDivision;
	}
	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}
	public String getSubDivisionName() {
		return subDivisionName;
	}
	public void setSubDivisionName(String subDivisionName) {
		this.subDivisionName = subDivisionName;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getCapacityOfRO() {
		return capacityOfRO;
	}
	public void setCapacityOfRO(String capacityOfRO) {
		this.capacityOfRO = capacityOfRO;
	}
	public String getHeadProgramme() {
		return headProgramme;
	}
	public void setHeadProgramme(String headProgramme) {
		this.headProgramme = headProgramme;
	}
	public String getExecutingAgency() {
		return executingAgency;
	}
	public void setExecutingAgency(String executingAgency) {
		this.executingAgency = executingAgency;
	}
	public Date getDateCompOandM() {
		return dateCompOandM;
	}
	public void setDateCompOandM(Date dateCompOandM) {
		this.dateCompOandM = dateCompOandM;
	}
	public Long getAdminAppCost() {
		return adminAppCost;
	}
	public void setAdminAppCost(Long adminAppCost) {
		this.adminAppCost = adminAppCost;
	}
	public Integer getRoConnectionId() {
		return roConnectionId;
	}
	public void setRoConnectionId(Integer roConnectionId) {
		this.roConnectionId = roConnectionId;
	}
	public String getSeasonalRO() {
		return seasonalRO;
	}
	public void setSeasonalRO(String seasonalRO) {
		this.seasonalRO = seasonalRO;
	}
	public Long getNoHHsVillage() {
		return noHHsVillage;
	}
	public void setNoHHsVillage(Long noHHsVillage) {
		this.noHHsVillage = noHHsVillage;
	}
	@Override
	public String toString() {
		return "RoScreenDto [division=" + division + ", divisionName=" + divisionName + ", subDivision="
				+ subDivision + ", subDivisionName=" + subDivisionName + "]";
	}
}
