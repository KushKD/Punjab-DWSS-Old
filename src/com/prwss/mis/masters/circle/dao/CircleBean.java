package com.prwss.mis.masters.circle.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prwss.mis.masters.zone.dao.ZoneBean;

@Entity
@Table(name="mst_circle", schema="prwss_main")
public class CircleBean implements Serializable,Comparable<CircleBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 997098013507040L;

	@Id
	@Column(name="circle_id", nullable=false)
	private String circleId;
	
	@Column(name="circle_name", nullable=false)
	private String circleName;
	
	@ManyToOne(targetEntity=ZoneBean.class)
	@JoinColumn(name="zone_id", nullable=false)
	private ZoneBean zone;
	
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

	public String getCircleId() {
		return circleId;
	}

	public void setCircleId(String circleId) {
		this.circleId = circleId;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public ZoneBean getZone() {
		return zone;
	}

	public void setZone(ZoneBean zone) {
		this.zone = zone;
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
		return freezedBy;
	}

	public void setFreezedBy(long freezedBy) {
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
		return "CircleBean [circleId=" + circleId + ", circleName=" + circleName + ", zone=" + zone + ", status="
				+ status + ", enteredBy=" + enteredBy + ", enteredDate=" + enteredDate + ", authorizedBy="
				+ authorizedBy + ", authorizedDate=" + authorizedDate + ", freezedBy=" + freezedBy + ", freezedDate="
				+ freezedDate + "]";
	}

	@Override
	public int compareTo(CircleBean o) {
		
		return o.getCircleId().trim().compareTo(this.circleId.trim());
	}


}
