/**
 * 
 */
package com.prwss.mis.pmm.dsrtubewell.dao;

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
@Table(name="pmm_dsr_tubwell_distribution", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DSRTubeWellDistributionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -805280161841185514L;

@Id
@GeneratedValue(generator="seq_pmm_dsrtubwell_distribution", strategy=GenerationType.AUTO)
@SequenceGenerator(name="seq_pmm_dsrtubwell_distribution", sequenceName="prwss_main.seq_pmm_dsrtubwell_distribution")
@Column(name="id", nullable=false)
private long id;	

@Column(name="scheme_id", nullable = false)
private String schemeId;

@Column(name="distribution_size")
private String distributionSize;

@Column(name="distribution_type")
private String distributionType;

@Column(name="distribution_length")
private long distributionLength;

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

public String getDistributionSize() {
	return distributionSize;
}

public void setDistributionSize(String distributionSize) {
	this.distributionSize = distributionSize;
}

public String getDistributionType() {
	return distributionType;
}

public void setDistributionType(String distributionType) {
	this.distributionType = distributionType;
}

public long getDistributionLength() {
	return distributionLength;
}

public void setDistributionLength(long distributionLength) {
	this.distributionLength = distributionLength;
}

public MISAuditBean getMisAuditBean() {
	return misAuditBean;
}

public void setMisAuditBean(MISAuditBean misAuditBean) {
	this.misAuditBean = misAuditBean;
}

@Override
public String toString() {
	return "DSRTubeWellDistributionBean [id=" + id + ", schemeId=" + schemeId
			+ ", distributionSize=" + distributionSize + ", distributionType="
			+ distributionType + ", distributionLength=" + distributionLength
			+ ", misAuditBean=" + misAuditBean + "]";
}


}
