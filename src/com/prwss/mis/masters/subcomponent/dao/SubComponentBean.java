package com.prwss.mis.masters.subcomponent.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prwss.mis.masters.component.dao.ComponentBean;

@Entity
@Table(name="mst_sub_component", schema="prwss_main")
public class SubComponentBean implements Serializable,Comparable<SubComponentBean> {

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 4516865809826241422L;
	
	@ManyToOne(targetEntity=ComponentBean.class)
	@JoinColumn(name="component_id")
	private ComponentBean componentBean;

	@Id
	@Column(name="sub_component_id", nullable=false)	
	private String subComponentId;
	
	@Column(name="sub_component_name")
	private String subComponentName;
	
	@Column(name="sub_component_desc")
	private String subComponentDescription;
	
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
	
	@Column (name="status")
	private String status;


	public ComponentBean getComponentBean() {
		return componentBean;
	}

	public void setComponentBean(ComponentBean componentBean) {
		this.componentBean = componentBean;
	}

	public String getSubComponentId() {
		return subComponentId;
	}

	public void setSubComponentId(String subComponentId) {
		this.subComponentId = subComponentId;
	}

	public String getSubComponentName() {
		return subComponentName;
	}

	public void setSubComponentName(String subComponentName) {
		this.subComponentName = subComponentName;
	}

	public String getSubComponentDescription() {
		return subComponentDescription;
	}

	public void setSubComponentDescription(String subComponentDescription) {
		this.subComponentDescription = subComponentDescription;
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
	public String toString() {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("**********Sub Component**********\n");
		buffer.append("\nsubComponentId\t"+subComponentId);
		buffer.append("\ncomponentBean\t"+componentBean);
		buffer.append("\nsubComponentName\t"+subComponentName);
		buffer.append("\nsubComponentDescription\t"+subComponentDescription);
		buffer.append("\ninsertedBy\t"+insertedBy);
		buffer.append("\ninsertedDate\t"+insertedDate);
		buffer.append("\nauthorizedBy\t"+authorizedBy);
		buffer.append("\nauthorizedDate\t"+authorizedDate);
		buffer.append("\nstatus\t"+status);
		buffer.append("\n**********************************\n");
		return buffer.toString();
	}

	@Override
	public int compareTo(SubComponentBean o) {
		return this.subComponentId.compareTo(o.getSubComponentId());
	}
	
	
	

}
