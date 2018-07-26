package com.prwss.mis.pmm.village.struts;

import java.io.Serializable;

public class VillageConnectionGridBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8550345724307845810L;

	private long connectionId;
	private long noOfWaterConnection;
	private long noOfStandpost;
	
	private String asOnDate;

	
	
	public long getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(long connectionId) {
		this.connectionId = connectionId;
	}

	public long getNoOfWaterConnection() {
		return noOfWaterConnection;
	}

	public void setNoOfWaterConnection(long noOfWaterConnection) {
		this.noOfWaterConnection = noOfWaterConnection;
	}

	public long getNoOfStandpost() {
		return noOfStandpost;
	}

	public void setNoOfStandpost(long noOfStandpost) {
		this.noOfStandpost = noOfStandpost;
	}

	public String getAsOnDate() {
		return asOnDate;
	}

	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}
	
	
}
