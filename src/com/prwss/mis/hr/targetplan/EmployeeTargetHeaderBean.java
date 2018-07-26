package com.prwss.mis.hr.targetplan;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
@Entity
@Table(name="t_hr_employeetarget_header", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class EmployeeTargetHeaderBean implements Serializable, Comparable<EmployeeTargetHeaderBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1100843401429889949L;
	
	@Id
	@GeneratedValue(generator = "seq_t_employeetarget_plan", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_t_employeetarget_plan", sequenceName = "prwss_main.seq_t_employeetarget_plan")
	@Column(name = "target_plan_id", nullable = false)
	private long targetPlanId;

	@Column(name = "employee_id", nullable = false)
	private long employeeId;
	
	@Column(name = "plan_from_date", nullable = false)
	private Date planFromDate;
	
	@Column(name = "plan_to_date", nullable = false)
	private Date planToDate;
	
	@Column(name = "reporting_officer_id")
	private long reportingOfficerId;
	
	@Column(name = "location_id")
	private String locationId;
	
	@Column(name = "performance_rating")
	private String performanceRating;
	
	@Column(name = "appraisal")
	private String appraisal;
	
	@Embedded
	private MISAuditBean misAuditBean;
	

	public String getPerformanceRating() {
		return performanceRating;
	}

	public void setPerformanceRating(String performanceRating) {
		this.performanceRating = performanceRating;
	}

	public String getAppraisal() {
		return appraisal;
	}

	public void setAppraisal(String appraisal) {
		this.appraisal = appraisal;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public long getTargetPlanId() {
		return targetPlanId;
	}

	public void setTargetPlanId(long targetPlanId) {
		this.targetPlanId = targetPlanId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public Date getPlanFromDate() {
		return planFromDate;
	}

	public void setPlanFromDate(Date planFromDate) {
		this.planFromDate = planFromDate;
	}

	public Date getPlanToDate() {
		return planToDate;
	}

	public void setPlanToDate(Date planToDate) {
		this.planToDate = planToDate;
	}

	public long getReportingOfficerId() {
		return reportingOfficerId;
	}

	public void setReportingOfficerId(long reportingOfficerId) {
		this.reportingOfficerId = reportingOfficerId;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	
	public Set<EmployeeTargetDetailsBean> getEmployeeTargetDetailsBeans() {
		return employeeTargetDetailsBeans;
	}

	public void setEmployeeTargetDetailsBeans(
			Set<EmployeeTargetDetailsBean> employeeTargetDetailsBeans) {
		this.employeeTargetDetailsBeans = employeeTargetDetailsBeans;
	}

	@OneToMany(targetEntity=EmployeeTargetDetailsBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="target_plan_id", updatable = false , insertable = false)
	private Set<EmployeeTargetDetailsBean> employeeTargetDetailsBeans;



	@Override
	public String toString() {
		return "EmployeeTargetHeaderBean [targetPlanId=" + targetPlanId
				+ ", employeeId=" + employeeId + ", planFromDate="
				+ planFromDate + ", planToDate=" + planToDate
				+ ", reportingOfficerId=" + reportingOfficerId
				+ ", locationId=" + locationId + ", misAuditBean="
				+ misAuditBean + ", employeeTargetDetailsBeans="
				+ employeeTargetDetailsBeans + "]";
	}

	@Override
	public int compareTo(EmployeeTargetHeaderBean o) {
		return new Long(this.targetPlanId).compareTo(o.targetPlanId);
	}

}
