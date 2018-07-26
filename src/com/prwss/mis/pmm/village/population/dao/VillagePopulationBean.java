package com.prwss.mis.pmm.village.population.dao;

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
@Table(name="mst_village_population", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VillagePopulationBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -1208744129275881899L;

	@Id
	@SequenceGenerator(name="seq_village_population_id", sequenceName="prwss_main.seq_village_population_id")
	@GeneratedValue(generator="seq_village_population_id", strategy=GenerationType.AUTO)
	@Column(name="population_id", nullable=false)
	private long populationId;
	
	@Column(name="village_id", nullable=false)
	private String villageId;
	
	@Column(name="population_year")
	private long populationYear;
	
	@Column(name="as_on_date")
	private Date asOnDate;
	
	@Column(name="gen_population")
	private long genPopulation;
	@Column(name="sc_population")
	private long scPopulation;
	@Column(name="st_population")
	private long stPopulation;
	
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

	public long getPopulationId() {
		return populationId;
	}

	public void setPopulationId(long populationId) {
		this.populationId = populationId;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	

	public long getPopulationYear() {
		return populationYear;
	}

	public void setPopulationYear(long populationYear) {
		this.populationYear = populationYear;
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

	
	public long getGenPopulation() {
		return genPopulation;
	}

	public void setGenPopulation(long genPopulation) {
		this.genPopulation = genPopulation;
	}

	public long getScPopulation() {
		return scPopulation;
	}

	public void setScPopulation(long scPopulation) {
		this.scPopulation = scPopulation;
	}

	public long getStPopulation() {
		return stPopulation;
	}

	public void setStPopulation(long stPopulation) {
		this.stPopulation = stPopulation;
	}

	@Override
	public String toString() {
		return "VillagePopulationBean [populationId=" + populationId + ", villageId=" + villageId + "populationYear=" + populationYear + ", asOnDate=" + asOnDate + ", misAuditBean="
				+ misAuditBean + "]";
	}

}
