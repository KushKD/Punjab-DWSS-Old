package com.prwss.mis.pmm.village.ncpcstatus.dao;

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
@Entity
@Table(name="mst_village_nc_pc_status", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillageNCPCStatusBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3164200722649824775L;

	/**
	 * 
	 */
	
	@Id
	@SequenceGenerator(name="seq_village_nc_pc_id", sequenceName="prwss_main.seq_village_nc_pc_id")
	@GeneratedValue(generator="seq_village_nc_pc_id", strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private long id;
	
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	@Column(name="nc_pc_status")
	private String ncPcStatus;
	
	@Column(name="month_of_status")
	private String monthOfStatus;
	
	
	@Column(name="year_of_status")
	private String yearOfStatus;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getNcPcStatus() {
		return ncPcStatus;
	}

	public void setNcPcStatus(String ncPcStatus) {
		this.ncPcStatus = ncPcStatus;
	}

	public String getMonthOfStatus() {
		return monthOfStatus;
	}

	public void setMonthOfStatus(String monthOfStatus) {
		this.monthOfStatus = monthOfStatus;
	}

	public String getYearOfStatus() {
		return yearOfStatus;
	}

	public void setYearOfStatus(String yearOfStatus) {
		this.yearOfStatus = yearOfStatus;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	
	

}
