package com.prwss.mis.masters.zone.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mst_zone", schema="prwss_main")
public class ZoneBean implements Serializable,Comparable<ZoneBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -2945445291107418559L;

	@Id
	@Column(name="zone_id", unique=true, nullable=false)
	private String zoneId;
	
	@Column(name="zone_name", nullable=false)
	private String zoneName;
	
	@Column(name="status")	
	private String status;
	
	@Column(name="ent_by")
	private long enteredBy;
	
	@Column(name="ent_date")
	private Timestamp enteredDate;
	
	@Column(name="auth_by")
	private long authorizedBy;
	
	@Column(name="auth_date")
	private Timestamp authorizedDate;
	
	@Column(name="freeze_by")
	private long freezedBy;
	
	@Column(name="freeze_date")
	private Timestamp freezedDate;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(long enteredBy) {
		this.enteredBy = enteredBy;
	}

	public Timestamp getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Timestamp enteredDate) {
		this.enteredDate = enteredDate;
	}

	public long getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(long authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public Timestamp getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(Timestamp authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	public long getFreezedBy() {
		System.out.println(freezedBy);
		return freezedBy;
	}

	public void setFreezedBy(long freezedBy) {
		System.out.println(freezedBy);
		this.freezedBy = freezedBy;
	}

	public Timestamp getFreezedDate() {
		return freezedDate;
	}

	public void setFreezedDate(Timestamp freezedDate) {
		this.freezedDate = freezedDate;
	}
	
	

	@Override
	public String toString() {
		return "ZoneBean [zoneId=" + zoneId + ", zoneName=" + zoneName + ", status=" + status + ", enteredBy="
				+ enteredBy + ", enteredDate=" + enteredDate + ", authorizedBy=" + authorizedBy + ", authorizedDate="
				+ authorizedDate + ", freezedBy=" + freezedBy + ", freezedDate=" + freezedDate + "]";
	}

	@Override
	public int compareTo(ZoneBean o) {
		
		return o.getZoneId().trim().compareTo(this.zoneId.trim());
	}


}
