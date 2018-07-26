package com.prwss.mis.pdo.struts;

public class reportSection7Modal {
	
	private String scheme;
	private String  village;
	private String households;
	private String sew_connection;
	private String connection_percent;
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getHouseholds() {
		return households;
	}
	public void setHouseholds(String households) {
		this.households = households;
	}
	public String getSew_connection() {
		return sew_connection;
	}
	public void setSew_connection(String sew_connection) {
		this.sew_connection = sew_connection;
	}
	public String getConnection_percent() {
		return connection_percent;
	}
	public void setConnection_percent(String connection_percent) {
		this.connection_percent = connection_percent;
	}
	@Override
	public String toString() {
		return "reportSection7Modal [scheme=" + scheme + ", village=" + village
				+ ", households=" + households + ", sew_connection="
				+ sew_connection + ", connection_percent=" + connection_percent
				+ "]";
	}
	
	

}
