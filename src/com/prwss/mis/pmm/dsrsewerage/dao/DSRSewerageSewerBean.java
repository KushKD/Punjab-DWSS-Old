/**
 * 
 */
package com.prwss.mis.pmm.dsrsewerage.dao;

import java.io.Serializable;
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
 * @author pjha
 *
 */
@Entity
@Table(name="pmm_dsr_sewerage_sewer", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DSRSewerageSewerBean implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 620797807866564535L;
	@Id
	@GeneratedValue(generator="seq_pmm_dsrsewerage_sewer", strategy=GenerationType.AUTO)
	@SequenceGenerator(name="seq_pmm_dsrsewerage_sewer", sequenceName="prwss_main.seq_pmm_dsrsewerage_sewer")
	@Column(name="id", nullable=false)
	private long id;	

	@Column(name="scheme_id", nullable = false)
	private String schemeId;

	@Column(name="sewer_size")
	private String sewerSize;

	@Column(name="sewer_type")
	private String sewerType;

	@Column(name="sewer_length")
	private long sewerLength;
	@Embedded
	private MISAuditBean misAuditBean;
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
	public String getSewerSize() {
		return sewerSize;
	}
	public void setSewerSize(String sewerSize) {
		this.sewerSize = sewerSize;
	}
	@Override
	public String toString() {
		return "DSRSewerageSewerBean [id=" + id + ", schemeId=" + schemeId
				+ ", sewerSize=" + sewerSize + ", sewerType=" + sewerType
				+ ", sewerLength=" + sewerLength + ", misAuditBean="
				+ misAuditBean + "]";
	}
	public String getSewerType() {
		return sewerType;
	}
	public void setSewerType(String sewerType) {
		this.sewerType = sewerType;
	}
	public long getSewerLength() {
		return sewerLength;
	}
	public void setSewerLength(long sewerLength) {
		this.sewerLength = sewerLength;
	}
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}
	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	
	
}
