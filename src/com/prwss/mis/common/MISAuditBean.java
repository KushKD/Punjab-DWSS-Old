package com.prwss.mis.common;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MISAuditBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	protected static final long serialVersionUID = -1988104486848057197L;
	@Column(name="ent_by")
	protected long entBy;
	
	@Column(name="ent_date")
	protected Timestamp entDate;
	
	@Column(name="auth_by")
	protected long authBy;
	
	@Column(name="auth_date")
	protected Timestamp authDate;
	
	@Column(name="freeze_by")
	protected long freezedBy;
	
	@Column(name="freeze_date")
	protected Timestamp freezedDate;
	
	@Column(name="status")
	protected String status;

	public Long getEntBy() {
		return entBy;
	}
	public void setEntBy(Long entBy) {
		this.entBy = entBy;
	}
	public Timestamp getEntDate() {
		return entDate;
	}
	public void setEntDate(Timestamp entDate) {
		this.entDate = entDate;
	}
	public long getAuthBy() {
		return authBy;
	}
	public void setAuthBy(long authBy) {
		this.authBy = authBy;
	}
	public Timestamp getAuthDate() {
		return authDate;
	}
	public void setAuthDate(Timestamp authDate) {
		this.authDate = authDate;
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
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "MISAuditBean [entBy=" + entBy + ", entDate=" + entDate + ", authBy=" + authBy + ", authDate="
				+ authDate + ", freezedBy=" + freezedBy + ", freezedDate=" + freezedDate + ", status=" + status + "]";
	}
	
	
	
}
