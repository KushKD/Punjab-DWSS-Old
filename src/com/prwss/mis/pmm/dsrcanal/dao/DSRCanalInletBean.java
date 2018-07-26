/**
 * 
 */
package com.prwss.mis.pmm.dsrcanal.dao;

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
@Table(name="pmm_dsr_canal_inlet", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DSRCanalInletBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2577903975439189892L;

	@Id
	@GeneratedValue(generator="seq_pmm_dsrcanal_inlet", strategy=GenerationType.AUTO)
	@SequenceGenerator(name="seq_pmm_dsrcanal_inlet", sequenceName="prwss_main.seq_pmm_dsrcanal_inlet")
	@Column(name="id", nullable=false)
	private long id;	

	@Column(name="scheme_id", nullable = false)
	private String schemeId;

	@Column(name="inlet_size")
	private String inletSize;
	
	@Column(name="inlet_type")
	private String inletType;
	
	@Column(name="inlet_length")
	private long inletLength;
	@Embedded
	private MISAuditBean misAuditBean;
	

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
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

	public String getInletSize() {
		return inletSize;
	}

	public void setInletSize(String inletSize) {
		this.inletSize = inletSize;
	}

	public String getInletType() {
		return inletType;
	}

	public void setInletType(String inletType) {
		this.inletType = inletType;
	}

	public long getInletLength() {
		return inletLength;
	}

	public void setInletLength(long inletLength) {
		this.inletLength = inletLength;
	}

	@Override
	public String toString() {
		return "DSRCanalInletBean [id=" + id + ", schemeId=" + schemeId
				+ ", inletSize=" + inletSize + ", inletType=" + inletType
				+ ", inletLength=" + inletLength + "]";
	}
	
	
}
