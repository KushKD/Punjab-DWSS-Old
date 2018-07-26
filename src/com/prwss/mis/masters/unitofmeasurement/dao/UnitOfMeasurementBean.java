package com.prwss.mis.masters.unitofmeasurement.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;



@Entity
@Table(name="mst_unit_of_measurement", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class UnitOfMeasurementBean implements Serializable,Comparable<UnitOfMeasurementBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1175943544862451832L;
	
	@Id
	@Column(name="unit_of_measurement_id")
	private String measurementId;
	
	@Column(name="unit_of_measurement_name")
	private String measurementName;
	
	@Column(name="unit_of_measurement_description")
	private String measurementDescription;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	public String getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(String measurementId) {
		this.measurementId = measurementId;
	}

	public String getMeasurementName() {
		return measurementName;
	}

	public void setMeasurementName(String measurementName) {
		this.measurementName = measurementName;
	}

	public String getMeasurementDescription() {
		return measurementDescription;
	}

	public void setMeasurementDescription(String measurementDescription) {
		this.measurementDescription = measurementDescription;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public int compareTo(UnitOfMeasurementBean o) {
		return this.getMeasurementId().compareTo(o.getMeasurementId());
	
	}

}
