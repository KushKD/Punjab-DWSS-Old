package com.prwss.mis.pmm.village.struts;

import java.io.Serializable;

public class VillagePopulationGridBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1296791025501216095L;
	private long populationId;
	private long populationYear;
	private String asOnDate;
	private long genPopulation;
	private long scPopulation;
	private long stPopulation;
	
	
	public long getPopulationId() {
		return populationId;
	}
	public void setPopulationId(long populationId) {
		this.populationId = populationId;
	}
	
	public long getGenPopulation() {
		return genPopulation;
	}
	public void setGenPopulation(long genPopulation) {
		this.genPopulation = genPopulation;
	}
	public long getScPopulation() {
		return scPopulation;
	}
	public void setScPopulation(long scPopulation) {
		this.scPopulation = scPopulation;
	}
	public long getStPopulation() {
		return stPopulation;
	}
	public void setStPopulation(long stPopulation) {
		this.stPopulation = stPopulation;
	}
	public long getPopulationYear() {
		return populationYear;
	}
	public void setPopulationYear(long populationYear) {
		this.populationYear = populationYear;
	}
	public String getAsOnDate() {
		return asOnDate;
	}
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}
	
	
}
