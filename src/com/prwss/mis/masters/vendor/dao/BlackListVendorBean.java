package com.prwss.mis.masters.vendor.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;

@Entity
@Table(name="t_vendor_blacklist", schema="prwss_main")
public class BlackListVendorBean implements Serializable,Comparable<BlackListVendorBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -3064138600990791500L;

	@Id
	@Column(name="vendor_id", nullable=false)
	private String vendorId;
	
	
	@Column(name="blacklist_from")
	private Date blackListFrom;
	
	@Column(name="blacklist_to")
	private Date blackListTo;
	
	@Column(name="reason")
	private String reason;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	
	@Override
	public String toString() {
		return "VendorBean [vendorId=" + vendorId + ", blackListFrom=" + blackListFrom + ", blackListTo="
				+ blackListTo + "]";
	}
	@Override
	public int compareTo(BlackListVendorBean o) {
		return o.getVendorId().trim().compareTo(this.vendorId.trim());
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public Date getBlackListFrom() {
		return blackListFrom;
	}
	public void setBlackListFrom(Date blackListFrom) {
		this.blackListFrom = blackListFrom;
	}
	public Date getBlackListTo() {
		return blackListTo;
	}
	public void setBlackListTo(Date blackListTo) {
		this.blackListTo = blackListTo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
