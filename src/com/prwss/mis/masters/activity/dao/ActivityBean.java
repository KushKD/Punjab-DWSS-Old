package com.prwss.mis.masters.activity.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.subcomponent.dao.SubComponentBean;

@Entity
@Table(name="mst_activity", schema="prwss_main")
public class ActivityBean implements Serializable,Comparable<ActivityBean> {
	
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 2743261300592878383L;

	@Id
	@Column(name="activity_id", nullable=false)
	private String activityId;

	@ManyToOne(targetEntity=SubComponentBean.class)
	@JoinColumn(name="sub_component_id", nullable=false)
	private SubComponentBean subComponent;
	
	@ManyToOne(targetEntity=ComponentBean.class)
	@JoinColumn(name="component_id", nullable=false)
	private ComponentBean component;
	
	@Column(name="activity_name", nullable=false)
	private String activityName;
	
	@Column(name="activity_description")
	private String activityDescription;
	
	@Column(name="ent_by")
	private long insertedBy;
	
	@Column(name="ent_date")
	private Timestamp insertedDate;
	
	@Column(name="auth_by")
	private long authorizedBy;
	
	@Column(name="auth_date")
	private Timestamp authorizedDate;
	
	@Column(name="freeze_by")
	private long freezedBy;
	
	@Column(name="freeze_date")
	private Timestamp freezedDate;
	
	@Column(name="status")
	private String status;

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public SubComponentBean getSubComponent() {
		return subComponent;
	}

	public void setSubComponent(SubComponentBean subComponent) {
		this.subComponent = subComponent;
	}

	public ComponentBean getComponent() {
		return component;
	}

	public void setComponent(ComponentBean component) {
		this.component = component;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public long getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(long insertedBy) {
		this.insertedBy = insertedBy;
	}

	public Timestamp getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(Timestamp insertedDate) {
		this.insertedDate = insertedDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int compareTo(ActivityBean o) {
		
		return this.activityId.compareTo(o.activityId);
	}


}
