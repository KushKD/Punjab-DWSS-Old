package com.prwss.mis.finance.releaseloc.struts;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReleaseLocGridBean implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 6579603705189111572L;
	private long id;
	private String schemeId;
	private long installmentNo;
	private String auditCompletedDate;
	private BigDecimal auditedAmount;
	private BigDecimal amount;
	private BigDecimal releaseAmount;
	private String releaseStatus;
	private String schemeName;
	private String locFor;
	
	public String getLocFor() {
		return locFor;
	}
	public void setLocFor(String locFor) {
		this.locFor = locFor;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public long getInstallmentNo() {
		return installmentNo;
	}
	public void setInstallmentNo(long installmentNo) {
		this.installmentNo = installmentNo;
	}
	public String getAuditCompletedDate() {
		return auditCompletedDate;
	}
	public void setAuditCompletedDate(String auditCompletedDate) {
		this.auditCompletedDate = auditCompletedDate;
	}
	public BigDecimal getAuditedAmount() {
		return auditedAmount;
	}
	public void setAuditedAmount(BigDecimal auditedAmount) {
		this.auditedAmount = auditedAmount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getReleaseAmount() {
		return releaseAmount;
	}
	public void setReleaseAmount(BigDecimal releaseAmount) {
		this.releaseAmount = releaseAmount;
	}
	public String getReleaseStatus() {
		return releaseStatus;
	}
	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	
	
}
