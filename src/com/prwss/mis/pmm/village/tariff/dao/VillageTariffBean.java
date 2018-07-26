package com.prwss.mis.pmm.village.tariff.dao;

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
@Table(name="mst_village_tariff", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillageTariffBean implements Serializable {

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -3659586774871243885L;

	@Id
	@SequenceGenerator(name="seq_village_tariff_id", sequenceName="prwss_main.seq_village_tariff_id")
	@GeneratedValue(generator="seq_village_tariff_id", strategy=GenerationType.AUTO)
	@Column(name="tariff_id", nullable=false)
	private long tariffId;
	
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	@Column(name="tariff_rate")
	private long tariffRate;
	
	@Column(name="as_on_date")
	private Date asOnDate;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getTariffId() {
		return tariffId;
	}

	public void setTariffId(long tariffId) {
		this.tariffId = tariffId;
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
		return "\nVillageTariffBean [tariffId=" + tariffId + ", villageId=" + villageId + ", tariffRate=" + tariffRate
				+ ", asOnDate=" + asOnDate + ", misAuditBean=" + misAuditBean + "]\n";
	}
	
}
