package com.prwss.mis.tender.award.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_tender_award_objection", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class TenderObjectionBean implements Serializable, Comparable<TenderObjectionBean>{

	/**
	 * Version 1
	 */
	private static final long serialVersionUID = 68075595358106369L;

	@Id
	@GeneratedValue(generator="seq_tender_objection_id", strategy=GenerationType.AUTO)
	@SequenceGenerator(name="seq_tender_objection_id", sequenceName="prwss_main.seq_tender_objection_id")
	@Column(name="objection_id")
	private long objectionId;

	@Column(name="tender_id")
	private String tenderId;
	
	@Column(name="objected_by")
	private String objectedBy;
	
	@Column(name="date_of_objection")
	private Date dateOfObjection;
	
	@Column(name="reasons")
	private String reasons;
	
	@Column(name="status_of_objection")
	private String statusOfObjection;
	
	@Column(name="location_id")
	private String locationId;
	
	@Embedded
	private MISAuditBean misAuditBean;

	
	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public String getObjectedBy() {
		return objectedBy;
	}

	public void setObjectedBy(String objectedBy) {
		this.objectedBy = objectedBy;
	}

	public Date getDateOfObjection() {
		return dateOfObjection;
	}

	public void setDateOfObjection(Date dateOfObjection) {
		this.dateOfObjection = dateOfObjection;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public String getStatusOfObjection() {
		return statusOfObjection;
	}

	public void setStatusOfObjection(String statusOfObjection) {
		this.statusOfObjection = statusOfObjection;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public void setObjectionId(long objectionId) {
		this.objectionId = objectionId;
	}

	public long getObjectionId() {
		return objectionId;
	}

	@Override
	public String toString() {
		return "TenderObjectionBean [objectionId=" + objectionId + ", tenderId=" + tenderId + ", objectedBy="
				+ objectedBy + ", dateOfObjection=" + dateOfObjection + ", reasons=" + reasons + ", statusOfObjection="
				+ statusOfObjection + ", locationId=" + locationId + ", misAuditBean=" + misAuditBean + "]";
	}

	@Override
	public int compareTo(TenderObjectionBean o) {
		
		return new Long(this.objectionId).compareTo(o.getObjectionId());
	}

	
}
