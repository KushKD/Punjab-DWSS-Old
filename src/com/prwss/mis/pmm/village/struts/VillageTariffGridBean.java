package com.prwss.mis.pmm.village.struts;

import java.io.Serializable;

public class VillageTariffGridBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8441719035108966413L;
	private long tariffRate;
	private long tariffId;
	
	private String asOnDate;
	
	
	
	public long getTariffId() {
		return tariffId;
	}
	public void setTariffId(long tariffId) {
		this.tariffId = tariffId;
	}
	public long getTariffRate() {
		return tariffRate;
	}
	public void setTariffRate(long tariffRate) {
		this.tariffRate = tariffRate;
	}
	public String getAsOnDate() {
		return asOnDate;
	}
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}
	
	
	
}
