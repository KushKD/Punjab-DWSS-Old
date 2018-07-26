/**
 * 
 */
package com.prwss.mis.reports.performa.dao;

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
 * @author bhsingh
 *
 */
@Entity
@Table(name = "draft_performa1", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class VillagePerformaBean {

	@Id
	@GeneratedValue(generator = "draft_performa1_id1_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "draft_performa1_id1_seq", sequenceName = "prwss_main.draft_performa1_id1_seq")
	@Column(name = "id1")
	private int id;
	
	@Column(name = "village_id")
	private String villageId;
	
	@Column(name = "village_name")
	private String villageName;
	
	@Column(name = "scheme_id")
	private String schemeId;
	
	@Column(name = "hadbast_no")
	private Integer hadBastNo;
	
	@Column(name = "no_of_habitations")
	private Integer habitationNo;
	
	@Column(name = "name_of_habitations")
	private String habitationName;
	
	
	@Column(name = "type_of_habitations")
	private String habitationType;
	
	@Column(name = "assembly_constituency")
	private String assemblyCons;
	
	@Column(name = "total_population")
	private Integer totalPopulation;
	
	@Column(name = "sc_population")
	private Integer scPopulation;
	
	@Column(name = "total_households")
	private Integer totalHouseholds;
	
	@Column(name = "sc_households")
	private Integer scHouseholds;
	
	@Column(name = "panchayat_ghar")
	private Integer panchayatGhr;
	
	@Column(name = "community_centre_or_dharamshala")
	private Integer comCenter;
	
	@Column(name = "angarwaries")
	private Integer angarwaries;
	
	@Column(name = "govt_building")
	private Integer govtBuilding;
	
	@Column(name = "private_building")
	private Integer privateBuilding;
	
	@Column(name = "govt_schools")
	private Integer govtSchool;
	
	@Column(name = "health_centre")
	private Integer healthCenter;
	
	@Column(name = "village_ponds")
	private Integer villagePonds;
	
	@Column(name = "area_in_square_meters")
	private Double areaSquareMtr;
	
	@Column(name = "sewrage")
	private String sewrage;
	
	@Column(name = "sew_connectio_sc_households")
	private Integer sew_connectio_sc_households;
	
	@Column(name = "water_connectio_sc_households")
	private Integer water_connectio_sc_households;
	
	@Column(name = "female_gen_population")
	private Integer female_gen_population;
	
	@Column(name = "female_sc_population")
	private Integer female_sc_population;
	
	@Column(name = "habitation_distance_from_water_supply_sch")
	private Double sewrahabitation_distance_from_water_supply_schge;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the villageId
	 */
	public String getVillageId() {
		return villageId;
	}

	/**
	 * @param villageId the villageId to set
	 */
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	/**
	 * @return the villageName
	 */
	public String getVillageName() {
		return villageName;
	}

	/**
	 * @param villageName the villageName to set
	 */
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	/**
	 * @return the schemeId
	 */
	public String getSchemeId() {
		return schemeId;
	}

	/**
	 * @param schemeId the schemeId to set
	 */
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	/**
	 * @return the hadBastNo
	 */
	public Integer getHadBastNo() {
		return hadBastNo;
	}

	/**
	 * @param hadBastNo the hadBastNo to set
	 */
	public void setHadBastNo(Integer hadBastNo) {
		this.hadBastNo = hadBastNo;
	}

	/**
	 * @return the habitationNo
	 */
	public Integer getHabitationNo() {
		return habitationNo;
	}

	/**
	 * @param habitationNo the habitationNo to set
	 */
	public void setHabitationNo(Integer habitationNo) {
		this.habitationNo = habitationNo;
	}

	/**
	 * @return the habitationName
	 */
	public String getHabitationName() {
		return habitationName;
	}

	/**
	 * @param habitationName the habitationName to set
	 */
	public void setHabitationName(String habitationName) {
		this.habitationName = habitationName;
	}

	/**
	 * @return the habitationType
	 */
	public String getHabitationType() {
		return habitationType;
	}

	/**
	 * @param habitationType the habitationType to set
	 */
	public void setHabitationType(String habitationType) {
		this.habitationType = habitationType;
	}

	/**
	 * @return the assemblyCons
	 */
	public String getAssemblyCons() {
		return assemblyCons;
	}

	/**
	 * @param assemblyCons the assemblyCons to set
	 */
	public void setAssemblyCons(String assemblyCons) {
		this.assemblyCons = assemblyCons;
	}

	/**
	 * @return the totalPopulation
	 */
	public Integer getTotalPopulation() {
		return totalPopulation;
	}

	/**
	 * @param totalPopulation the totalPopulation to set
	 */
	public void setTotalPopulation(Integer totalPopulation) {
		this.totalPopulation = totalPopulation;
	}

	/**
	 * @return the scPopulation
	 */
	public Integer getScPopulation() {
		return scPopulation;
	}

	/**
	 * @param scPopulation the scPopulation to set
	 */
	public void setScPopulation(Integer scPopulation) {
		this.scPopulation = scPopulation;
	}

	/**
	 * @return the totalHouseholds
	 */
	public Integer getTotalHouseholds() {
		return totalHouseholds;
	}

	/**
	 * @param totalHouseholds the totalHouseholds to set
	 */
	public void setTotalHouseholds(Integer totalHouseholds) {
		this.totalHouseholds = totalHouseholds;
	}

	/**
	 * @return the scHouseholds
	 */
	public Integer getScHouseholds() {
		return scHouseholds;
	}

	/**
	 * @param scHouseholds the scHouseholds to set
	 */
	public void setScHouseholds(Integer scHouseholds) {
		this.scHouseholds = scHouseholds;
	}

	/**
	 * @return the panchayatGhr
	 */
	public Integer getPanchayatGhr() {
		return panchayatGhr;
	}

	/**
	 * @param panchayatGhr the panchayatGhr to set
	 */
	public void setPanchayatGhr(Integer panchayatGhr) {
		this.panchayatGhr = panchayatGhr;
	}

	/**
	 * @return the comCenter
	 */
	public Integer getComCenter() {
		return comCenter;
	}

	/**
	 * @param comCenter the comCenter to set
	 */
	public void setComCenter(Integer comCenter) {
		this.comCenter = comCenter;
	}

	/**
	 * @return the angarwaries
	 */
	public Integer getAngarwaries() {
		return angarwaries;
	}

	/**
	 * @param angarwaries the angarwaries to set
	 */
	public void setAngarwaries(Integer angarwaries) {
		this.angarwaries = angarwaries;
	}

	


	/**
	 * @return the govtBuilding
	 */
	public Integer getGovtBuilding() {
		return govtBuilding;
	}

	/**
	 * @param govtBuilding the govtBuilding to set
	 */
	public void setGovtBuilding(Integer govtBuilding) {
		this.govtBuilding = govtBuilding;
	}

	/**
	 * @return the privateBuilding
	 */
	public Integer getPrivateBuilding() {
		return privateBuilding;
	}

	/**
	 * @param privateBuilding the privateBuilding to set
	 */
	public void setPrivateBuilding(Integer privateBuilding) {
		this.privateBuilding = privateBuilding;
	}

	/**
	 * @return the govtSchool
	 */
	public Integer getGovtSchool() {
		return govtSchool;
	}

	/**
	 * @param govtSchool the govtSchool to set
	 */
	public void setGovtSchool(Integer govtSchool) {
		this.govtSchool = govtSchool;
	}

	/**
	 * @return the healthCenter
	 */
	public Integer getHealthCenter() {
		return healthCenter;
	}

	/**
	 * @param healthCenter the healthCenter to set
	 */
	public void setHealthCenter(Integer healthCenter) {
		this.healthCenter = healthCenter;
	}

	/**
	 * @return the villagePonds
	 */
	public Integer getVillagePonds() {
		return villagePonds;
	}

	/**
	 * @param villagePonds the villagePonds to set
	 */
	public void setVillagePonds(Integer villagePonds) {
		this.villagePonds = villagePonds;
	}

	/**
	 * @return the areaSquareMtr
	 */
	public Double getAreaSquareMtr() {
		return areaSquareMtr;
	}

	/**
	 * @param areaSquareMtr the areaSquareMtr to set
	 */
	public void setAreaSquareMtr(Double areaSquareMtr) {
		this.areaSquareMtr = areaSquareMtr;
	}

	/**
	 * @return the sewrage
	 */
	public String getSewrage() {
		return sewrage;
	}

	/**
	 * @param sewrage the sewrage to set
	 */
	public void setSewrage(String sewrage) {
		this.sewrage = sewrage;
	}

	/**
	 * @return the sew_connectio_sc_households
	 */
	public Integer getSew_connectio_sc_households() {
		return sew_connectio_sc_households;
	}

	/**
	 * @param sew_connectio_sc_households the sew_connectio_sc_households to set
	 */
	public void setSew_connectio_sc_households(Integer sew_connectio_sc_households) {
		this.sew_connectio_sc_households = sew_connectio_sc_households;
	}

	/**
	 * @return the water_connectio_sc_households
	 */
	public Integer getWater_connectio_sc_households() {
		return water_connectio_sc_households;
	}

	/**
	 * @param water_connectio_sc_households the water_connectio_sc_households to set
	 */
	public void setWater_connectio_sc_households(Integer water_connectio_sc_households) {
		this.water_connectio_sc_households = water_connectio_sc_households;
	}

	/**
	 * @return the female_gen_population
	 */
	public Integer getFemale_gen_population() {
		return female_gen_population;
	}

	/**
	 * @param female_gen_population the female_gen_population to set
	 */
	public void setFemale_gen_population(Integer female_gen_population) {
		this.female_gen_population = female_gen_population;
	}

	/**
	 * @return the female_sc_population
	 */
	public Integer getFemale_sc_population() {
		return female_sc_population;
	}

	/**
	 * @param female_sc_population the female_sc_population to set
	 */
	public void setFemale_sc_population(Integer female_sc_population) {
		this.female_sc_population = female_sc_population;
	}

	

	/**
	 * @return the sewrahabitation_distance_from_water_supply_schge
	 */
	public Double getSewrahabitation_distance_from_water_supply_schge() {
		return sewrahabitation_distance_from_water_supply_schge;
	}

	/**
	 * @param sewrahabitation_distance_from_water_supply_schge the sewrahabitation_distance_from_water_supply_schge to set
	 */
	public void setSewrahabitation_distance_from_water_supply_schge(
			Double sewrahabitation_distance_from_water_supply_schge) {
		this.sewrahabitation_distance_from_water_supply_schge = sewrahabitation_distance_from_water_supply_schge;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VillagePerformaBean [id=" + id + ", villageId=" + villageId + ", villageName=" + villageName
				+ ", schemeId=" + schemeId + ", hadBastNo=" + hadBastNo + ", habitationNo=" + habitationNo
				+ ", habitationName=" + habitationName + ", habitationType=" + habitationType + ", assemblyCons="
				+ assemblyCons + ", totalPopulation=" + totalPopulation + ", scPopulation=" + scPopulation
				+ ", totalHouseholds=" + totalHouseholds + ", scHouseholds=" + scHouseholds + ", panchayatGhr="
				+ panchayatGhr + ", comCenter=" + comCenter + ", angarwaries=" + angarwaries + ", govtBuilding="
				+ govtBuilding + ", privateBuilding=" + privateBuilding + ", govtSchool=" + govtSchool
				+ ", healthCenter=" + healthCenter + ", villagePonds=" + villagePonds + ", areaSquareMtr="
				+ areaSquareMtr + ", sewrage=" + sewrage + ", sew_connectio_sc_households="
				+ sew_connectio_sc_households + ", water_connectio_sc_households=" + water_connectio_sc_households
				+ ", female_gen_population=" + female_gen_population + ", female_sc_population=" + female_sc_population
				+ ", sewrahabitation_distance_from_water_supply_schge="
				+ sewrahabitation_distance_from_water_supply_schge + "]";
	}

}
