package com.prwss.mis.masters.scheme.struts;

public class ModelVillageSchemeMapping {
	
	
	private String location_id;
	private String district_id;
	private String scheme_id;
	private String scheme_name;
	private String prog_id;
	private String village_id;
	private String vill_name;
	private String scheme_upgraded;
	private String scheme_source;
	
	
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public String getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}
	public String getScheme_id() {
		return scheme_id;
	}
	public void setScheme_id(String scheme_id) {
		this.scheme_id = scheme_id;
	}
	public String getScheme_name() {
		return scheme_name;
	}
	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}
	public String getProg_id() {
		return prog_id;
	}
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}
	public String getVillage_id() {
		return village_id;
	}
	public void setVillage_id(String village_id) {
		this.village_id = village_id;
	}
	public String getVill_name() {
		return vill_name;
	}
	public void setVill_name(String vill_name) {
		this.vill_name = vill_name;
	}
	public String getScheme_upgraded() {
		return scheme_upgraded;
	}
	public void setScheme_upgraded(String scheme_upgraded) {
		this.scheme_upgraded = scheme_upgraded;
	}
	public String getScheme_source() {
		return scheme_source;
	}
	public void setScheme_source(String scheme_source) {
		this.scheme_source = scheme_source;
	}
	@Override
	public String toString() {
		return "ModelVillageSchemeMapping [location_id=" + location_id
				+ ", district_id=" + district_id + ", scheme_id=" + scheme_id
				+ ", scheme_name=" + scheme_name + ", prog_id=" + prog_id
				+ ", village_id=" + village_id + ", vill_name=" + vill_name
				+ ", scheme_upgraded=" + scheme_upgraded + ", scheme_source="
				+ scheme_source + "]";
	}
	
	

}
