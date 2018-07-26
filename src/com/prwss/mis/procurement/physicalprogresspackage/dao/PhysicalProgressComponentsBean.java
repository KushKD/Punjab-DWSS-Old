package com.prwss.mis.procurement.physicalprogresspackage.dao;

import java.io.Serializable;
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
@Entity
@Table(name="t_proc_package_components_progress", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class PhysicalProgressComponentsBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4061029820021650819L;
	
	
	@Id
	@GeneratedValue(generator = "seq_t_proc_package_progress_component", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_t_proc_package_progress_component", sequenceName = "prwss_main.seq_t_proc_package_progress_component")
	@Column(name = "id", nullable = false)
	private long id;
	
	@Column(name="package_id")
	private String packageId;
	@Column(name="component_name")
	private String componentName;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="percent_completion")
	private String percentCompletion;
	
	@Column(name="as_on_date")
	private Date asOnDate;

	@Embedded
	private MISAuditBean misAuditBean;

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

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getPercentCompletion() {
		return percentCompletion;
	}

	public void setPercentCompletion(String percentCompletion) {
		this.percentCompletion = percentCompletion;
	}

	public Date getAsOnDate() {
		return asOnDate;
	}

	public void setAsOnDate(Date asOnDate) {
		this.asOnDate = asOnDate;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	
	
}
