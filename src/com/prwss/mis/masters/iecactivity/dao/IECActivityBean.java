package com.prwss.mis.masters.iecactivity.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_ccdu_iec_activity", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class IECActivityBean implements Serializable,Comparable<IECActivityBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -4019995051238142814L;

	@Id
	@Column(name="iec_activity_id", nullable=false)
	private String iecActivityId;
	
	@Column(name="iec_name", nullable=false)
	private String iecActivityName;
	
	@Column(name="iec_description")
	private String iecActivityDescription;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public String getIecActivityId() {
		return iecActivityId;
	}

	public void setIecActivityId(String iecActivityId) {
		this.iecActivityId = iecActivityId;
	}

	public String getIecActivityName() {
		return iecActivityName;
	}

	public void setIecActivityName(String iecActivityName) {
		this.iecActivityName = iecActivityName;
	}

	public String getIecActivityDescription() {
		return iecActivityDescription;
	}

	public void setIecActivityDescription(String iecActivityDescription) {
		this.iecActivityDescription = iecActivityDescription;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "\nIECActivityBean [iecActivityId=" + iecActivityId + ", iecActivityName=" + iecActivityName
				+ ", iecActivityDescription=" + iecActivityDescription + ", misAuditBean=" + misAuditBean + "]\n";
	}

	@Override
	public int compareTo(IECActivityBean o) {
		return this.getIecActivityId().compareTo(o.getIecActivityId());
	}

	
}
