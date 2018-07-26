package com.prwss.mis.masters.bank.dao;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.unitofmeasurement.dao.UnitOfMeasurementBean;
@Entity
@Table(name="mst_bank",schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class BankMasterBean implements Serializable, Comparable<BankMasterBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4929578314815060148L;
	
	@Id
	@GeneratedValue(generator = "seq_mst_bank_id", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_mst_bank_id", sequenceName = "prwss_main.seq_mst_bank_id")
	@Column(name="bank_id", nullable= false)
	private long bankId;
	
	
	@Column(name="bank_name", nullable= false)
	private String bankName;
	
	@Column(name="bank_branch")
	private String bankBranch;
	
	@ManyToOne(targetEntity=LocationBean.class)
	@JoinColumn(name="district_id" , referencedColumnName="location_id")
	private LocationBean distrcit;
	
	@Column(name="bank_address")
	private String bankAddress;
	
	@Embedded
	private MISAuditBean misAuditBean;
	

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

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

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	@Override
	public int compareTo(BankMasterBean o) {
		
		return new Long(this.bankId).compareTo(o.bankId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bankId ^ (bankId >>> 32));
		result = prime * result
				+ ((bankName == null) ? 0 : bankName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankMasterBean other = (BankMasterBean) obj;
		if (bankId != other.bankId)
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		return true;
	}

	
	public LocationBean getDistrcit() {
		return distrcit;
	}

	public void setDistrcit(LocationBean distrcit) {
		this.distrcit = distrcit;
	}

//	public String getDistrcitId() {
//		return distrcitId;
//	}
//
//	public void setDistrcitId(String distrcitId) {
//		this.distrcitId = distrcitId;
//	}
	
}
