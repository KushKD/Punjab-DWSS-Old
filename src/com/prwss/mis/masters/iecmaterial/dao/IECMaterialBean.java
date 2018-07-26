package com.prwss.mis.masters.iecmaterial.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_ccdu_iec_material", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class IECMaterialBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 5864485049755572530L;

	@Id
	@Column(name="iec_material_id", nullable=false)
	private String iecMaterialId;
	
	@Column(name="material_name")
	private String iecMaterialName;
	
	@Column(name="material_description")
	private String iecMaterialDescription;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public String getIecMaterialId() {
		return iecMaterialId;
	}

	public void setIecMaterialId(String iecMaterialId) {
		this.iecMaterialId = iecMaterialId;
	}

	public String getIecMaterialName() {
		return iecMaterialName;
	}

	public void setIecMaterialName(String iecMaterialName) {
		this.iecMaterialName = iecMaterialName;
	}

	public String getIecMaterialDescription() {
		return iecMaterialDescription;
	}

	public void setIecMaterialDescription(String iecMaterialDescription) {
		this.iecMaterialDescription = iecMaterialDescription;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "\nIECMaterialBean [iecMaterialId=" + iecMaterialId + ", iecMaterialName=" + iecMaterialName
				+ ", iecMaterialDescription=" + iecMaterialDescription + ", misAuditBean=" + misAuditBean + "]\n";
	}
	
	

}
