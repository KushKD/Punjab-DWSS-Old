package com.prwss.mis.pmm.village.household.dao;

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
@Table(name="mst_village_households", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillageHouseHoldBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -7645496886307062272L;

	@Id
	@SequenceGenerator(name="seq_village_household_id", sequenceName="prwss_main.seq_village_household_id")
	@GeneratedValue(generator="seq_village_household_id", strategy=GenerationType.AUTO)
	@Column(name="household_id", nullable=false)
	private long householdId;
	
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	@Column(name="sc_households", nullable=false)
	private long scHouseholds;
	
	@Column(name="gc_households", nullable=false)
	private long gcHouseholds;
	
	@Column(name="as_on_date")
	private Date asOnDate;
	
	@Column(name = "freez_value")
	private Date freezedValue;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public Date getFreezedValue() {
		return freezedValue;
	}

	public void setFreezedValue(Date freezedValue) {
		this.freezedValue = freezedValue;
	}

	public long getHouseholdId() {
		return householdId;
	}

	public void setHouseholdId(long householdId) {
		this.householdId = householdId;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public long getScHouseholds() {
		return scHouseholds;
	}

	public void setScHouseholds(long scHouseholds) {
		this.scHouseholds = scHouseholds;
	}

	public long getGcHouseholds() {
		return gcHouseholds;
	}

	public void setGcHouseholds(long gcHouseholds) {
		this.gcHouseholds = gcHouseholds;
	}

	public Date getAsOnDate() {
		return asOnDate;
	}

	public void setAsOnDate(Date asOnDate) {
		this.asOnDate = asOnDate;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "\nVillageHouseHoldsBean [householdId=" + householdId + ", villageId=" + villageId + ", scHouseholds="
				+ scHouseholds + ", gcHouseholds=" + gcHouseholds + ", asOnDate=" + asOnDate + ", misAuditBean="
				+ misAuditBean + "]\n";
	}

}
