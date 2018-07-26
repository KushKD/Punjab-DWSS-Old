package com.prwss.mis.pmm.village.struts;

import java.io.Serializable;

public class VillageSewerageGridBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2434112392540662904L;
	
	private long tariffRate;
	private long sewerageId;
	private String asOnDate;
	private long sewConnection;
	
	public long getSewConnection() {
		return sewConnection;
	}
	public void setSewConnection(long sewConnection) {
		this.sewConnection = sewConnection;
	}
	public long getTariffRate() {
		return tariffRate;
	}
	public void setTariffRate(long tariffRate) {
		this.tariffRate = tariffRate;
	}
	public long getSewerageId() {
		return sewerageId;
	}
	public void setSewerageId(long sewerageId) {
		this.sewerageId = sewerageId;
	}
	public String getAsOnDate() {
		return asOnDate;
	}
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}
	
	
}
