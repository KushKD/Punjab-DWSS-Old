/**
 * 
 */
package com.prwss.mis.finance.loc.dao;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 * @author PJHA
 *
 */
@Entity
@Table(name="t_finance_loc_detail", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class LOCDetailBean implements Serializable,Comparable<LOCDetailBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3499826032107688052L;

	@Id
	@SequenceGenerator(name="seq_t_locdetail_id", sequenceName="prwss_main.seq_t_locdetail_id")
	@GeneratedValue(generator="seq_t_locdetail_id", strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private long id;
	
	@Column(name="scheme_id", nullable=false)
	private String schemeId;
	
	@Column(name="loc_id", nullable =false)
	private long locId;
	
	
	
	@Column(name="program_id")
	private String programId;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="installment_no")
	private long installmentNo;
	 
	@Column(name="audit_completed_date")
	private Date auditCompletedDate;
	
	@Column(name="audited_amount")
	private BigDecimal auditedAmount;
	 
	@Column(name="requested_amount")
	private BigDecimal amount;
	
	@Column(name="release_amount")
	private BigDecimal releaseAmount;
	
	@Column(name="sch_name")
	private String schemeName;
	
	@Column(name="loc_for")
	private String locFor;
	
	
	
	public String getLocFor() {
		return locFor;
	}

	public void setLocFor(String locFor) {
		this.locFor = locFor;
	}

	@Column(name="release_status")
	private String releaseStatus;
	 
	@Embedded
	private MISAuditBean misAuditBean;


	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public long getInstallmentNo() {
		return installmentNo;
	}

	public void setInstallmentNo(long installmentNo) {
		this.installmentNo = installmentNo;
	}

	public Date getAuditCompletedDate() {
		return auditCompletedDate;
	}

	public void setAuditCompletedDate(Date auditCompletedDate) {
		this.auditCompletedDate = auditCompletedDate;
	}

	public BigDecimal getAuditedAmount() {
		return auditedAmount;
	}

	public void setAuditedAmount(BigDecimal auditedAmount) {
		this.auditedAmount = auditedAmount;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLocId() {
		return locId;
	}

	public void setLocId(long locId) {
		this.locId = locId;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "LOCDetailBean [id=" + id + ", schemeId=" + schemeId
				+ ", locId=" + locId + ", programId=" + programId
				+ ", locationId=" + locationId + ", installmentNo="
				+ installmentNo + ", auditCompletedDate=" + auditCompletedDate
				+ ", auditedAmount=" + auditedAmount + ", amount=" + amount
				+ ", releaseAmount=" + releaseAmount + ", releaseStatus="
				+ releaseStatus + ", misAuditBean=" + misAuditBean + "]";
	}

	@Override
	public int compareTo(LOCDetailBean o) {
		// TODO Auto-generated method stub
		return new Long(this.locId).compareTo(o.locId);
	}
	

}
