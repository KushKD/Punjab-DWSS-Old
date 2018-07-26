package com.prwss.mis.ccdu.issue.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_ccdu_issue", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class CCDUIssueBean implements Serializable,Comparable<CCDUIssueBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 1865789114015000427L;

	@Id
	@Column(name="ccdu_issue_id")
	private String ccduIssueId;
	
	@Column(name="ccdu_issue_name")
	private String ccduIssueName;
	
	@Column(name="ccdu_issue_description")
	private String ccduIssueDescription;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public String getCcduIssueId() {
		return ccduIssueId;
	}

	public void setCcduIssueId(String ccduIssueId) {
		this.ccduIssueId = ccduIssueId;
	}

	public String getCcduIssueName() {
		return ccduIssueName;
	}

	public void setCcduIssueName(String ccduIssueName) {
		this.ccduIssueName = ccduIssueName;
	}

	public String getCcduIssueDescription() {
		return ccduIssueDescription;
	}

	public void setCcduIssueDescription(String ccduIssueDescription) {
		this.ccduIssueDescription = ccduIssueDescription;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "CCDUIssueBean [ccduIssueId=" + ccduIssueId + ", ccduIssueName=" + ccduIssueName
				+ ", ccduIssueDescription=" + ccduIssueDescription + ", misAuditBean=" + misAuditBean + "]";
	}

	@Override
	public int compareTo(CCDUIssueBean o) {
		
		return o.getCcduIssueId().compareTo(this.ccduIssueId);
	}

}
