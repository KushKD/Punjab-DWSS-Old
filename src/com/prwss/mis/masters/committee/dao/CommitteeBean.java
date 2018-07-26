/**
 * 
 */
package com.prwss.mis.masters.committee.dao;

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

/**
 * @author pjha
 *
 */
@Entity
@Table(name="mst_committee", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class CommitteeBean implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2352720964481834530L;
	
	
    @Id
    @GeneratedValue(generator = "seq_mst_committee", strategy = GenerationType.AUTO)
 	@SequenceGenerator(name = "seq_mst_committee", sequenceName = "prwss_main.seq_mst_committee")
	@Column(name = "committee_id", nullable = false)
	private long committeeId;
	
	@Column(name = "scheme_id")
	private String schemeId;
	
	@Column(name = "committee_name")
	private String committeeName;
	
	@Column(name = "location_id")
	private String locationId;
	
	@Column(name = "village_id")
	private String villageId;
	
	@Column(name = "block_id")
	private String blockId;
	
	@Column(name = "district_id")
	private String districtId;
	
	@Column(name = "committee_constitution_date")
	private Date  committeeConstitutionDate;
	
	@Column(name = "committee_type")
	private String sLCStatus;
	 
	 
	@OneToMany(targetEntity=CommitteeBankBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="committee_id", updatable = false , insertable = false)
	private Set<CommitteeBankBean> committeeBankBeans;
	
	@OneToMany(targetEntity=CommitteeMemberBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="committee_id", updatable = false , insertable = false)
	private Set<CommitteeMemberBean> committeeMemberBeans;
	
	@Embedded
	private MISAuditBean misAuditBean;

	

	public String getDistrictId() {
		return districtId;
	}


	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}


	public String getCommitteeName() {
		return committeeName;
	}


	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}


	public String getLocationId() {
		return locationId;
	}


	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}


	public String getVillageId() {
		return villageId;
	}


	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}


	public String getBlockId() {
		return blockId;
	}


	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}


	public long getCommitteeId() {
		return committeeId;
	}


	public void setCommitteeId(long committeeId) {
		this.committeeId = committeeId;
	}


	public String getSchemeId() {
		return schemeId;
	}


	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}


	public Date getCommitteeConstitutionDate() {
		return committeeConstitutionDate;
	}


	public void setCommitteeConstitutionDate(Date committeeConstitutionDate) {
		this.committeeConstitutionDate = committeeConstitutionDate;
	}


	

	public String getsLCStatus() {
		return sLCStatus;
	}


	public void setsLCStatus(String sLCStatus) {
		this.sLCStatus = sLCStatus;
	}


	public Set<CommitteeBankBean> getCommitteeBankBeans() {
		return committeeBankBeans;
	}


	public void setCommitteeBankBeans(Set<CommitteeBankBean> committeeBankBeans) {
		this.committeeBankBeans = committeeBankBeans;
	}


	public Set<CommitteeMemberBean> getCommitteeMemberBeans() {
		return committeeMemberBeans;
	}


	public void setCommitteeMemberBeans(
			Set<CommitteeMemberBean> committeeMemberBeans) {
		this.committeeMemberBeans = committeeMemberBeans;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}


	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	
	@Override
	public String toString() {
		return "CommitteeBean [committeeId=" + committeeId + ", schemeId="
				+ schemeId + ", committeeConstitutionDate="
				+ committeeConstitutionDate + ", sLCStatus=" + sLCStatus
				+ ", committeeBankBeans=" + committeeBankBeans
				+ ", committeeMemberBeans=" + committeeMemberBeans
				+ ", misAuditBean=" + misAuditBean + "]";
	}


	
	
	

}
