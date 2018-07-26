package com.prwss.mis.pdo.struts;

public class reportSection4Modal {

	// No of GPSWCPC run schemes
	private String gpwsc_sch;

	// No of GPSWCPC run Village
	private String gpwsc_vill;

	// No of DWSS run schemes
	private String dwss_sch;

	// No of DWSS run schemes
	private String dwss_vill;

	// No of Village with Nore than 10 hours power supply
	private String hrs_vill;

	// No of villages with more than 90% water connection
	private String conn_vill;

	// No of villages with sevice level70 lpcd or more
	private String lpcd_vill;

	public String getGpwsc_sch() {
		return gpwsc_sch;
	}

	public void setGpwsc_sch(String gpwsc_sch) {
		this.gpwsc_sch = gpwsc_sch;
	}

	public String getGpwsc_vill() {
		return gpwsc_vill;
	}

	public void setGpwsc_vill(String gpwsc_vill) {
		this.gpwsc_vill = gpwsc_vill;
	}

	public String getDwss_sch() {
		return dwss_sch;
	}

	public void setDwss_sch(String dwss_sch) {
		this.dwss_sch = dwss_sch;
	}

	public String getDwss_vill() {
		return dwss_vill;
	}

	public void setDwss_vill(String dwss_vill) {
		this.dwss_vill = dwss_vill;
	}

	public String getHrs_vill() {
		return hrs_vill;
	}

	public void setHrs_vill(String hrs_vill) {
		this.hrs_vill = hrs_vill;
	}

	public String getConn_vill() {
		return conn_vill;
	}

	public void setConn_vill(String conn_vill) {
		this.conn_vill = conn_vill;
	}

	public String getLpcd_vill() {
		return lpcd_vill;
	}

	public void setLpcd_vill(String lpcd_vill) {
		this.lpcd_vill = lpcd_vill;
	}

	@Override
	public String toString() {
		return "reportSection4Modal [gpwsc_sch=" + gpwsc_sch + ", gpwsc_vill="
				+ gpwsc_vill + ", dwss_sch=" + dwss_sch + ", dwss_vill="
				+ dwss_vill + ", hrs_vill=" + hrs_vill + ", conn_vill="
				+ conn_vill + ", lpcd_vill=" + lpcd_vill + "]";
	}

}
