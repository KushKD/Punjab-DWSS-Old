/**
 * 
 */
package com.prwss.mis.finance.accountschart;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author PJHA
 *
 */
@Entity
@Table(name="t_finance_chart_accounts", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class AccountsChartBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2468647704674412093L;
	
	@Id	
	@Column(name="code_head_id", nullable=false)
	private String codeHeadId;
	
	@Column(name="code_head_description")
	private String codeHeadIdDescription;

	@Column(name="account_type")
	private String accountType;
	
	@Column(name="account_nature")
	private String accountNature;
	
	@Column(name="major_head_id")
	private String majorHeadId;
	
	@Column(name="minor_head_id")
	private String minorHeadId;
	
	@Column(name="sub_head_id")
	private String subHeadId;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	

	public String getAccountNature() {
		return accountNature;
	}

	public void setAccountNature(String accountNature) {
		this.accountNature = accountNature;
	}

	public String getCodeHeadId() {
		return codeHeadId;
	}

	public void setCodeHeadId(String codeHeadId) {
		this.codeHeadId = codeHeadId;
	}

	public String getAccountType() {
		return accountType;
	}

	
	public String getCodeHeadIdDescription() {
		return codeHeadIdDescription;
	}

	public void setCodeHeadIdDescription(String codeHeadIdDescription) {
		this.codeHeadIdDescription = codeHeadIdDescription;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getMajorHeadId() {
		return majorHeadId;
	}

	public void setMajorHeadId(String majorHeadId) {
		this.majorHeadId = majorHeadId;
	}

	public String getMinorHeadId() {
		return minorHeadId;
	}

	public void setMinorHeadId(String minorHeadId) {
		this.minorHeadId = minorHeadId;
	}

	

	public String getSubHeadId() {
		return subHeadId;
	}

	public void setSubHeadId(String subHeadId) {
		this.subHeadId = subHeadId;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "AccountsChartBean [codeHeadId=" + codeHeadId
				+ ", codeHeadIdDescription=" + codeHeadIdDescription
				+ ", accountType=" + accountType + ", accountNature="
				+ accountNature + ", majorHeadId=" + majorHeadId
				+ ", minorHeadId=" + minorHeadId + ", subHeadId=" + subHeadId
				+ ", misAuditBean=" + misAuditBean + "]";
	}
	

}
