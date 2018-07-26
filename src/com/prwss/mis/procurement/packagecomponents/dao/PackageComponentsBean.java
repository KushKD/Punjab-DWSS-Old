package com.prwss.mis.procurement.packagecomponents.dao;

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

@Entity
@Table(name="t_proc_package_components", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class PackageComponentsBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1944118477067983727L;

	@Id
	@GeneratedValue(generator = "seq_proc_package_component", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_proc_package_component", sequenceName = "prwss_main.seq_proc_package_component")
	@Column(name = "id", nullable = false)
	private long id;
	
	@Column(name="package_id")
	private String packageId;
	
	@Column(name="component_name")
	private String componentName;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	
	

}



