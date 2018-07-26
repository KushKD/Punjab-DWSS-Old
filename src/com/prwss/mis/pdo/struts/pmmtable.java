package com.prwss.mis.pdo.struts;

public class pmmtable {
	
private String district_id;
private int per_rural_households_as_survey;
public String getDistrict_id() {
	return district_id;
}
public void setDistrict_id(String district_id) {
	this.district_id = district_id;
}
public int getPer_rural_households_as_survey() {
	return per_rural_households_as_survey;
}
public void setPer_rural_households_as_survey(int per_rural_households_as_survey) {
	this.per_rural_households_as_survey = per_rural_households_as_survey;
}

@Override
public String toString() {
	return "pmmtable [district_id=" + district_id
			+ ", per_rural_households_as_survey="
			+ per_rural_households_as_survey + "]";
}



}
