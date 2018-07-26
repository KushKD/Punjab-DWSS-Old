package com.prwss.mis.procurement.check;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;
import com.prwss.mis.tender.dao.TenderHeaderBean;

@Entity
@Table(name="tender_check_2", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class TenderCheckBean implements Serializable,Comparable<TenderCheckBean>{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "location_id", nullable = false)
	private String locationId;

	@Column(name ="loc_name")
	private String locationName;
	/*
	@Column(name ="status")
	private String status;
	
	*/
	@Column(name ="tender_id")
	private String tenderId;
	
	@Column(name ="open_date")
	private Date openDate;
	
	@Column(name ="notice_to_proceed")
	private Date noticeToProceed;
		
	@Column(name ="diff")
	private long difference;
	
	
	@Column(name ="open_diff")
	private long open_date_diff;
	
	@Column(name ="scheme_code")
	private String schemeCode;
	
	@Column(name ="scheme_name")
	private String schemeName;
	
	@Column(name ="office_email")
	private String officeEmail;
	
	@Column(name ="not_responsive")
	private String notResponsive;
	
	
	public String getNotResponsive() {
		return notResponsive;
	}

	public void setNotResponsive(String notResponsive) {
		this.notResponsive = notResponsive;
	}

	/*public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
*/
	public long getOpen_date_diff() {
		return open_date_diff;
	}

	public void setOpen_date_diff(long open_date_diff) {
		this.open_date_diff = open_date_diff;
	}
	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getOfficeEmail() {
		return officeEmail;
	}

	public void setOfficeEmail(String officeEmail) {
		this.officeEmail = officeEmail;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getNoticeToProceed() {
		return noticeToProceed;
	}

	public void setNoticeToProceed(Date noticeToProceed) {
		this.noticeToProceed = noticeToProceed;
	}

	public long getDifference() {
		return difference;
	}

	public void setDifference(long difference) {
		this.difference = difference;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	@Override
	public int compareTo(TenderCheckBean o) {
		
		return this.tenderId.compareTo(o.getTenderId());
	}
	@Override
	  public String toString(){
		  return "TenderCheckBean :"+"tenderid=="+tenderId;
	  }
	

}
