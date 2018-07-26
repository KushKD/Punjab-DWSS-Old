package com.prwss.mis.masters.scheme.struts;

public class ModelVillageScheme {
	
	public String scheme_id;
	public String scheme_name;
	public String village_id;
	public String scheme_upgraded;
	
	public String getScheme_upgraded() {
		return scheme_upgraded;
	}
	public void setScheme_upgraded(String scheme_upgraded) {
		this.scheme_upgraded = scheme_upgraded;
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
	public String getVillage_id() {
		return village_id;
	}
	public void setVillage_id(String village_id) {
		this.village_id = village_id;
	}
	@Override
	public String toString() {
		return "ModelVillageScheme [scheme_id=" + scheme_id + ", scheme_name="
				+ scheme_name + ", village_id=" + village_id
				+ ", scheme_upgraded=" + scheme_upgraded + "]";
	}
	
	
	
	

}
