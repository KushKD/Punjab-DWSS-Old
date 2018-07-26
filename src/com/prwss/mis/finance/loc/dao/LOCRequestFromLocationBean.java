package com.prwss.mis.finance.loc.dao;


import java.io.Serializable;

import com.prwss.mis.finance.loc.LOCHeaderBean;

public class LOCRequestFromLocationBean  implements Serializable , Comparable<LOCRequestFromLocationBean>{
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -5234803145045427677L;
	
	
	private String locationId;
	private String locationName;
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}	
	
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationId() {
		return locationId;
	}
	
	@Override
	public String toString() {
		return "LOCRequestFromLocationBean [locationName=" + locationName + ", locationId=" + locationId + "]";
	}
	@Override
	public int compareTo(LOCRequestFromLocationBean o) {
		// TODO Auto-generated method stub
		return new String(this.locationId).compareTo(o.locationId);
		
	}
}
