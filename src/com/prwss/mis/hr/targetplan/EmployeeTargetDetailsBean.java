package com.prwss.mis.hr.targetplan;

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
@Table(name="t_hr_employeetarget_details", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EmployeeTargetDetailsBean implements Serializable,Comparable<EmployeeTargetDetailsBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4742781451351437405L;
	@Id
	@GeneratedValue(generator = "seq_t_employeetarget_detail", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_t_employeetarget_detail", sequenceName = "prwss_main.seq_t_employeetarget_detail")
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "target_plan_id", nullable = false)
	private long targetPlanId;
	
	@Column(name = "target_name", nullable = false)
	private String targetName;
	
	@Column(name = "target_completion_date", nullable = false)
	private Date targetCompletionDate;
	
	@Column(name = "planner_remarks")
	private String plannerRemarks;
	
	@Column(name = "target_actual_completion_date")
	private Date targetActualCompletionDate;
	
	@Column(name = "target_status")
	private String targetStatus;
	
	@Column(name = "employee_remarks")
	private String employeeRemarks;
	
	@Column(name = "evaluator_remarks")
	private String evaluatorRemarks;
	
	
	
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

	public long getTargetPlanId() {
		return targetPlanId;
	}

	public void setTargetPlanId(long targetPlanId) {
		this.targetPlanId = targetPlanId;
	}

	public String getTargetName() {
		return targetName;
	}



	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}



	public Date getTargetCompletionDate() {
		return targetCompletionDate;
	}



	public void setTargetCompletionDate(Date targetCompletionDate) {
		this.targetCompletionDate = targetCompletionDate;
	}



	public String getPlannerRemarks() {
		return plannerRemarks;
	}



	public void setPlannerRemarks(String plannerRemarks) {
		this.plannerRemarks = plannerRemarks;
	}



	public Date getTargetActualCompletionDate() {
		return targetActualCompletionDate;
	}



	public void setTargetActualCompletionDate(Date targetActualCompletionDate) {
		this.targetActualCompletionDate = targetActualCompletionDate;
	}




	public String getTargetStatus() {
		return targetStatus;
	}

	public void setTargetStatus(String targetStatus) {
		this.targetStatus = targetStatus;
	}

	public String getEmployeeRemarks() {
		return employeeRemarks;
	}



	public void setEmployeeRemarks(String employeeRemarks) {
		this.employeeRemarks = employeeRemarks;
	}



	public String getEvaluatorRemarks() {
		return evaluatorRemarks;
	}



	public void setEvaluatorRemarks(String evaluatorRemarks) {
		this.evaluatorRemarks = evaluatorRemarks;
	}


	@Override
	public int compareTo(EmployeeTargetDetailsBean o) {
		return new Long(this.targetPlanId).compareTo(o.targetPlanId);
	}

}
