package com.prwss.mis.finance.budgetdetail;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="t_finance_budget_detail", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class BudgetDetailBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2446239031075023180L;

	/**
	 * 
	 */
	

	@Id
	@SequenceGenerator(name="seq_t_budgetdetail_id", sequenceName="prwss_main.seq_t_budgetdetail_id")
	@GeneratedValue(generator="seq_t_budgetdetail_id", strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private long id;
	
	@Column(name="budget_id", nullable=false)
	private long budgetId;

	@Column(name="location_id")
	private String locationId;
	
	@Column(name="program_id")
	private String programId;
	
	@Column(name="component_id")
	private String componentId;
	
	@Column(name="sub_component_id")
	private String subComponentId;
	
	@Column(name="activity_id")
	private String activityId;
	
	@Column(name="rev_physical_budgt")
	private BigDecimal revPhysicalBudgt;
	  
	@Column(name="rev_finance_budgt")
	private BigDecimal revFinanceBudgt;
	
	@Column(name="est_physical_budgt")
	private BigDecimal estPhysicalBudgt;
	  
	@Column(name="est_finance_budgt")
	private BigDecimal estFinanceBudgt;
	
	@Column(name="app_physical_budgt")
	private BigDecimal appPhysicalBudgt;
	  
	@Column(name="app_finance_budgt")
	private BigDecimal appFinanceBudgt;
	
	@Column(name="budgt_unit")
	private long budgtUnit;
	  
	@Column(name="code_head_id")
	private String codeHeadId;
	 	  
	  
	  @Embedded
		private MISAuditBean misAuditBean;


	public long getId() {
		return id;
	}


	

	public BigDecimal getRevPhysicalBudgt() {
		return revPhysicalBudgt;
	}




	public void setRevPhysicalBudgt(BigDecimal revPhysicalBudgt) {
		this.revPhysicalBudgt = revPhysicalBudgt;
	}




	public BigDecimal getRevFinanceBudgt() {
		return revFinanceBudgt;
	}




	public String getProgramId() {
		return programId;
	}




	public void setProgramId(String programId) {
		this.programId = programId;
	}




	public void setRevFinanceBudgt(BigDecimal revFinanceBudgt) {
		this.revFinanceBudgt = revFinanceBudgt;
	}




	public BigDecimal getAppPhysicalBudgt() {
		return appPhysicalBudgt;
	}




	public void setAppPhysicalBudgt(BigDecimal appPhysicalBudgt) {
		this.appPhysicalBudgt = appPhysicalBudgt;
	}




	public BigDecimal getAppFinanceBudgt() {
		return appFinanceBudgt;
	}




	public void setAppFinanceBudgt(BigDecimal appFinanceBudgt) {
		this.appFinanceBudgt = appFinanceBudgt;
	}




	public long getBudgtUnit() {
		return budgtUnit;
	}




	public void setBudgtUnit(long budgtUnit) {
		this.budgtUnit = budgtUnit;
	}




	public void setId(long id) {
		this.id = id;
	}



	public long getBudgetId() {
		return budgetId;
	}



	public void setBudgetId(long budgetId) {
		this.budgetId = budgetId;
	}



	public String getLocationId() {
		return locationId;
	}



	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}



	public String getComponentId() {
		return componentId;
	}



	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}



	public String getSubComponentId() {
		return subComponentId;
	}



	public void setSubComponentId(String subComponentId) {
		this.subComponentId = subComponentId;
	}



	public String getActivityId() {
		return activityId;
	}



	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}



	public BigDecimal getEstPhysicalBudgt() {
		return estPhysicalBudgt;
	}



	public void setEstPhysicalBudgt(BigDecimal estPhysicalBudgt) {
		this.estPhysicalBudgt = estPhysicalBudgt;
	}



	public BigDecimal getEstFinanceBudgt() {
		return estFinanceBudgt;
	}



	public void setEstFinanceBudgt(BigDecimal estFinanceBudgt) {
		this.estFinanceBudgt = estFinanceBudgt;
	}



	



	public String getCodeHeadId() {
		return codeHeadId;
	}



	public void setCodeHeadId(String codeHeadId) {
		this.codeHeadId = codeHeadId;
	}



	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}



	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}




	@Override
	public String toString() {
		return "BudgetDetailBean [id=" + id + ", budgetId=" + budgetId
				+ ", locationId=" + locationId + ", programId=" + programId
				+ ", componentId=" + componentId + ", subComponentId="
				+ subComponentId + ", activityId=" + activityId
				+ ", revPhysicalBudgt=" + revPhysicalBudgt
				+ ", revFinanceBudgt=" + revFinanceBudgt
				+ ", estPhysicalBudgt=" + estPhysicalBudgt
				+ ", estFinanceBudgt=" + estFinanceBudgt
				+ ", appPhysicalBudgt=" + appPhysicalBudgt
				+ ", appFinanceBudgt=" + appFinanceBudgt + ", budgtUnit="
				+ budgtUnit + ", codeHeadId=" + codeHeadId + ", misAuditBean="
				+ misAuditBean + "]";
	}
	
	
	  
}
