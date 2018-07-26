package com.prwss.mis.pmm.village.sewerage.dao;

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
@Table(name="mst_village_sewerage_tariff", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillageSewerageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4993654531063496135L;

	@Id
	@SequenceGenerator(name="seq_village_sewerage_id", sequenceName="prwss_main.seq_village_connection_id")
	@GeneratedValue(generator="seq_village_sewerage_id", strategy=GenerationType.AUTO)
	@Column(name="sewerage_tariff_id", nullable=false)
	private long sewerageId;
	
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	@Column(name="tariff_rate")
	private long tariffRate;
	
	@Column(name="as_on_date")
	private Date asOnDate;
	
	@Column(name="sew_connection")
	private long sewConnection;
	
	@Embedded
	private MISAuditBean misAuditBean;

	
	public long getSewConnection() {
		return sewConnection;
	}

	public void setSewConnection(long sewConnection) {
		this.sewConnection = sewConnection;
	}

	public long getSewerageId() {
		return sewerageId;
	}

	public void setSewerageId(long sewerageId) {
		this.sewerageId = sewerageId;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public long getTariffRate() {
		return tariffRate;
	}

	public void setTariffRate(long tariffRate) {
		this.tariffRate = tariffRate;
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
		return "VillageSewerageBean [sewerageId=" + sewerageId + ", villageId="
				+ villageId + ", tariffRate=" + tariffRate + ", asOnDate="
				+ asOnDate + ", misAuditBean=" + misAuditBean + "]";
	}

		
}