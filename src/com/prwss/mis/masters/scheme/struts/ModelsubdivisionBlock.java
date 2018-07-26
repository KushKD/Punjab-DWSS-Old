package com.prwss.mis.masters.scheme.struts;

public class ModelsubdivisionBlock {
	
	private String district_id;
	private String location_id;
	private String sub_div;
	private String block_id;
	private String block_name;
	public String getBlock_name() {
		return block_name;
	}
	public void setBlock_name(String block_name) {
		this.block_name = block_name;
	}
	public String getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}
	public String getLocation_id() {
		return location_id;
	}
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	public String getSub_div() {
		return sub_div;
	}
	public void setSub_div(String sub_div) {
		this.sub_div = sub_div;
	}
	public String getBlock_id() {
		return block_id;
	}
	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}
	@Override
	public String toString() {
		return "ModelsubdivisionBlock [district_id=" + district_id
				+ ", location_id=" + location_id + ", sub_div=" + sub_div
				+ ", block_id=" + block_id + ", block_name=" + block_name + "]";
	}
	
	

}
