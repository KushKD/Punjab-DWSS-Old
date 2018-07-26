/**
 * 
 */
package com.prwss.mis.finance.budget;

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

/**
 * @author PJHA
 *
 */
@Entity
@Table(name="t_finance_budget_hdr", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class BudgetBean implements Serializable ,Comparable<BudgetBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7429578570806058575L;


	/**
	 * 
	 */
	
	@Id
	@SequenceGenerator(name="seq_t_budget_id", sequenceName="prwss_main.seq_t_budget_id")
	@GeneratedValue(generator="seq_t_budget_id", strategy=GenerationType.AUTO)
	@Column(name="budget_id", nullable=false)
	private long budgetId;

	
	@Column(name="program_id")
	private String programId;

	@Column(name="budget_from_date")
	private Date budgetFromDate;

	@Column(name="budget_to_date")
	private Date budgetToDate;

	@Embedded
	private MISAuditBean misAuditBean;

	public long getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(long budgetId) {
		this.budgetId = budgetId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public Date getBudgetFromDate() {
		return budgetFromDate;
	}

	public void setBudgetFromDate(Date budgetFromDate) {
		this.budgetFromDate = budgetFromDate;
	}

	public Date getBudgetToDate() {
		return budgetToDate;
	}

	public void setBudgetToDate(Date budgetToDate) {
		this.budgetToDate = budgetToDate;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	
	public int compareTo(BudgetBean o) {
		
		return new Long(this.budgetId).compareTo(o.budgetId);
	}
	
	
}
