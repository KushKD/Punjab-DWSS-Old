package com.prwss.mis.masters.zone.struts;

import org.apache.struts.validator.ValidatorForm;

public class ZoneForm extends ValidatorForm {

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -3989805263135372727L;
	private String zoneId;
	private String zoneName;	
	private String[] zoneIds;
	
	public String getZoneId() {
		return zoneId;
	}
	
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String[] getZoneIds() {
		return zoneIds;
	}

	public void setZoneIds(String[] zoneIds) {
		this.zoneIds = zoneIds;
	}
	
		
	
}
