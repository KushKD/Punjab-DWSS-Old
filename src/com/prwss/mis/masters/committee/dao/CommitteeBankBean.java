/**
 * 
 */
package com.prwss.mis.masters.committee.dao;

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

/**
 * @author pjha
 *
 */
@Entity
@Table(name="mst_committee_bank", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class CommitteeBankBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4447092662550816010L;
	
	 @Id
//	 @GeneratedValue(generator = "seq_mst_committeebank", strategy = GenerationType.AUTO)
//	 @SequenceGenerator(name = "seq_mst_committeebank", sequenceName = "prwss_main.seq_mst_committeebank")
	@Column(name="bank_id", nullable = false)
	private long bankId;
	
	
	@Column(name="bank_name")
	private String bankName;
	
	
	@Column(name="bank_branch")
	private String bankBranch;
	
	@Id
	@Column(name="account_number")
	private String accountNumber;
	
	
	@Column(name="account_type")
	private String accountType;
	
	@Id
	@Column(name="committee_id")
	private long committeeId;
	
	
	@Column(name="gpwsc_bank")
	private String gpwscBank;
	
	
	@Embedded
	private MISAuditBean misAuditBean;


	public long getBankId() {
		return bankId;
	}


	public void setBankId(long bankId) {
		this.bankId = bankId;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getBankBranch() {
		return bankBranch;
	}


	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public String getGpwscBank() {
		return gpwscBank;
	}


	public void setGpwscBank(String gpwscBank) {
		this.gpwscBank = gpwscBank;
	}


	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}


	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}


	public long getCommitteeId() {
		return committeeId;
	}


	public void setCommitteeId(long committeeId) {
		this.committeeId = committeeId;
	}


	@Override
	public String toString() {
		return "CommitteeBankBean [bankId=" + bankId + ", bankName=" + bankName
				+ ", bankBranch=" + bankBranch + ", accountNumber="
				+ accountNumber + ", accountType=" + accountType
				+ ", committeeId=" + committeeId + ", gpwscBank=" + gpwscBank
				+ ", misAuditBean=" + misAuditBean + "]";
	}
	

}
