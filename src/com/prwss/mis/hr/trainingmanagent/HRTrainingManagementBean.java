package com.prwss.mis.hr.trainingmanagent;

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
@Table(name="t_hr_training_management_details", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class HRTrainingManagementBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3335949106768717627L;
	
	@Id
	@GeneratedValue(generator = "seq_t_hr_training_management", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_t_hr_training_management", sequenceName = "prwss_main.seq_t_hr_training_management")
	@Column(name = "id", nullable = false)
	private long id;
	
	@Column(name = "location_id", nullable = false)
	private String locationId;
	
	@Column(name = "from_date")
	private Date fromDate;
	
	@Column(name = "to_date")
	private Date toDate;
	
	@Column(name = "training_plan_id")
	private long trainingPlanId;
	
	@Column(name = "training_objective")
	private String trainingObjective;
	
	@Column(name = "employee_id")
	private long employeeId;
	
	@Column(name = "training_venue")
	private String trainingVenue;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public long getTrainingPlanId() {
		return trainingPlanId;
	}

	public void setTrainingPlanId(long trainingPlanId) {
		this.trainingPlanId = trainingPlanId;
	}

	public String getTrainingObjective() {
		return trainingObjective;
	}

	public void setTrainingObjective(String trainingObjective) {
		this.trainingObjective = trainingObjective;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getTrainingVenue() {
		return trainingVenue;
	}

	public void setTrainingVenue(String trainingVenue) {
		this.trainingVenue = trainingVenue;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	
}
