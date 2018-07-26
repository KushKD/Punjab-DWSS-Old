package com.prwss.mis.reports.performa;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name = "draft_performa3", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class PerformaFiveYearPlanBean {

	@Id
	@GeneratedValue(generator = "draft_performa3_id3_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "draft_performa3_id3_seq", sequenceName = "prwss_main.draft_performa3_id3_seq")
	@Column(name = "id3")
	private long id;
	
	@Column(name = "scheme_id")
	private String schemeId;
	
	@Column(name = "village_id")
	private String villageId;
	
	
	@Column(name = "independent_new_wss")
	private Double independent_new_wss;
	
	@Column(name = "independent_new_wss_cost")
	private Double independent_new_wss_cost;
	
	@Column(name = "upgradation_of_existing_wss")
	private Double upgradation_of_existing_wss;
	
	@Column(name = "upgradation_of_existing_wss_cost")
	private Double upgradation_of_existing_wss_cost;
	
	@Column(name = "instltion_wtr_treatment_plant")
	private String instltion_wtr_treatment_plant;
	
	@Column(name = "instltion_wtr_treatment_plant_cost")
	private Double instltion_wtr_treatment_plant_cost;
	
	@Column(name = "source_of_wss")
	private String source_of_wss;
	
	@Column(name = "shifted_to_canal_from_other")
	private String shifted_to_canal_from_other;
	
	@Column(name = "driling_of_new_tubewell_machinery_size")
	private Double driling_of_new_tubewell_machinery_size;
	
	@Column(name = "driling_of_new_tubewell_machinery_depth")
	private Double driling_of_new_tubewell_machinery_depth;
	
	@Column(name = "driling_of_new_tubewell_machinery_capacity")
	private Double driling_of_new_tubewell_machinery_capacity;
	
	@Column(name = "driling_of_new_tubewell_machinery_cost")
	private Double driling_of_new_tubewell_machinery_cost;
	
	@Column(name = "canal_based_inlet_channel_size_of_pipe")
	private Double canal_based_inlet_channel_size_of_pipe;
	
	@Column(name = "canal_based_inlet_channel_length")
	private Double canal_based_inlet_channel_length;
	
	@Column(name = "canal_based_s_and_s_capacity")
	private Double canal_based_s_and_s_capacity;
	
	@Column(name = "canal_based_filteration_plan_type")
	private String canal_based_filteration_plan_type;
	
	@Column(name = "canal_based_filteration_plan_capacity")
	private Double canal_based_filteration_plan_capacity;
	
	@Column(name = "canal_based_cost")
	private Double canal_based_cost;
	
	@Column(name = "clear_water_tank")
	private String clear_water_tank;
	
	@Column(name = "ohsr_capacity")
	private Double ohsr_capacity;
	@Column(name = "ohsr_full_supply_level")
	private Double ohsr_full_supply_level;
	@Column(name = "ohsr_cost")
	private Double ohsr_cost;
	@Column(name = "other_structures_at_waterworks")
	private String other_structures_at_waterworks;
	
	@Column(name = "other_structures_at_waterworks_cost")
	private Double other_structures_at_waterworks_cost;
	
	@Column(name = "distribution_wss_to_village_type")
	private String distribution_wss_to_village_type;
	@Column(name = "distribution_wss_to_village_length")
	private Double distribution_wss_to_village_length;
	
	
	@Column(name = "pvc")
	private Double pvc;
	
	
	@Column(name = "ms_di_ci")
	private Double ms_di_ci;
	

	@Column(name = "network_gi")
	private Double network_gi;
	
	@Column(name = "distribution_cost")
	private Double distribution_cost;
	@Column(name = "no_of_connections_100_mtr")
	private Double no_of_connections_100_mtr;
	@Column(name = "no_of_mtrs_100_mtr")
	private Double no_of_mtrs_100_mtr;
	@Column(name = "cost_100_mtr")
	private Double cost_100_mtr;
	@Column(name = "disinfection_unit_type")
	private String disinfection_unit_type;
	@Column(name = "disinfection_unit_cost")
	private Double disinfection_unit_cost;
	@Column(name = "water_treatment_plant_in_case_of_quality_village_type")
	private String water_treatment_plant_in_case_of_quality_village_type;
	@Column(name = "water_treatment_plant_in_case_of_quality_village_capacity")
	private Double water_treatment_plant_in_case_of_quality_village_capacity;
	@Column(name = "water_treatment_plant_in_case_of_quality_village_cost")
	private Double water_treatment_plant_in_case_of_quality_village_cost;
	@Column(name = "bulk_water_meter_no")
	private Double bulk_water_meter_no;
	@Column(name = "bulk_water_meter_cost")
	private Double bulk_water_meter_cost;
	@Column(name = "extension_sanction_of_new_electric_connection_cost")
	private Double extension_sanction_of_new_electric_connection_cost;
	/*@Column(name = "status")
	private String status;
	@Column(name = "ent_date")
	private Date ent_date;*/
	
	@Column(name = "ohsr_ct_dep")
	private Double ohsr_ct_dep;
	@Column(name = "ohsr_ugsr_dep")
	private Double ohsr_ugsr_dep;
	@Column(name = "ohsr_ugsr_dia")
	private Double ohsr_ugsr_dia;
	@Column(name = "ohsr_ct_dia")
	private Double ohsr_ct_dia;
	
	
	/** KD Updated Code *
	 * 
	 */
	@Column(name = "dateOfComplition")
	private Date dateOfComplition;
	
	@Column(name = "remarksdateOfComplition")
	private String remarksdateOfComplition;
	
	
	
	
	
	
	
	public Date getDateOfComplition() {
		return dateOfComplition;
	}



	public void setDateOfComplition(Date dateOfComplition) {
		this.dateOfComplition = dateOfComplition;
	}



	public String getRemarksdateOfComplition() {
		return remarksdateOfComplition;
	}



	public void setRemarksdateOfComplition(String remarksdateOfComplition) {
		this.remarksdateOfComplition = remarksdateOfComplition;
	}



	public Double getOhsr_ct_dep() {
		return ohsr_ct_dep;
	}



	public void setOhsr_ct_dep(Double ohsr_ct_dep) {
		this.ohsr_ct_dep = ohsr_ct_dep;
	}



	public Double getOhsr_ugsr_dep() {
		return ohsr_ugsr_dep;
	}



	public void setOhsr_ugsr_dep(Double ohsr_ugsr_dep) {
		this.ohsr_ugsr_dep = ohsr_ugsr_dep;
	}



	public Double getOhsr_ugsr_dia() {
		return ohsr_ugsr_dia;
	}



	public void setOhsr_ugsr_dia(Double ohsr_ugsr_dia) {
		this.ohsr_ugsr_dia = ohsr_ugsr_dia;
	}



	public Double getOhsr_ct_dia() {
		return ohsr_ct_dia;
	}



	public void setOhsr_ct_dia(Double ohsr_ct_dia) {
		this.ohsr_ct_dia = ohsr_ct_dia;
	}



	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	

	/**
	 * @return the pvc
	 */
	public Double getPvc() {
		return pvc;
	}



	/**
	 * @param pvc the pvc to set
	 */
	public void setPvc(Double pvc) {
		this.pvc = pvc;
	}



	/**
	 * @return the ms_di_ci
	 */
	public Double getMs_di_ci() {
		return ms_di_ci;
	}

	/**
	 * @param ms_di_ci the ms_di_ci to set
	 */
	public void setMs_di_ci(Double ms_di_ci) {
		this.ms_di_ci = ms_di_ci;
	}

	/**
	 * @return the network_gi
	 */
	public Double getNetwork_gi() {
		return network_gi;
	}

	/**
	 * @param network_gi the network_gi to set
	 */
	public void setNetwork_gi(Double network_gi) {
		this.network_gi = network_gi;
	}

	/**
	 * @return the clear_water_tank
	 */
	public String getClear_water_tank() {
		return clear_water_tank;
	}

	/**
	 * @param clear_water_tank the clear_water_tank to set
	 */
	public void setClear_water_tank(String clear_water_tank) {
		this.clear_water_tank = clear_water_tank;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the independent_new_wss
	 */
	public Double getIndependent_new_wss() {
		return independent_new_wss;
	}

	/**
	 * @param independent_new_wss the independent_new_wss to set
	 */
	public void setIndependent_new_wss(Double independent_new_wss) {
		this.independent_new_wss = independent_new_wss;
	}

	/**
	 * @return the independent_new_wss
	 *//*
	public Double getIndependent_new_wss() {
		return independent_new_wss;
	}
	*//**
	 * @param independent_new_wss the independent_new_wss to set
	 *//*
	public void setIndependent_new_wss(Double independent_new_wss) {
		this.independent_new_wss = independent_new_wss;
	}*/
	/**
	 * @return the independent_new_wss_cost
	 */
	public Double getIndependent_new_wss_cost() {
		return independent_new_wss_cost;
	}
	/**
	 * @param independent_new_wss_cost the independent_new_wss_cost to set
	 */
	public void setIndependent_new_wss_cost(Double independent_new_wss_cost) {
		this.independent_new_wss_cost = independent_new_wss_cost;
	}
	
	/**
	 * @return the upgradation_of_existing_wss
	 */
	public Double getUpgradation_of_existing_wss() {
		return upgradation_of_existing_wss;
	}

	/**
	 * @param upgradation_of_existing_wss the upgradation_of_existing_wss to set
	 */
	public void setUpgradation_of_existing_wss(Double upgradation_of_existing_wss) {
		this.upgradation_of_existing_wss = upgradation_of_existing_wss;
	}

	/**
	 * @return the upgradation_of_existing_wss_cost
	 */
	public Double getUpgradation_of_existing_wss_cost() {
		return upgradation_of_existing_wss_cost;
	}
	/**
	 * @param upgradation_of_existing_wss_cost the upgradation_of_existing_wss_cost to set
	 */
	public void setUpgradation_of_existing_wss_cost(Double upgradation_of_existing_wss_cost) {
		this.upgradation_of_existing_wss_cost = upgradation_of_existing_wss_cost;
	}
	/**
	 * @return the instltion_wtr_treatment_plant
	 */
	public String getInstltion_wtr_treatment_plant() {
		return instltion_wtr_treatment_plant;
	}
	/**
	 * @param instltion_wtr_treatment_plant the instltion_wtr_treatment_plant to set
	 */
	public void setInstltion_wtr_treatment_plant(String instltion_wtr_treatment_plant) {
		this.instltion_wtr_treatment_plant = instltion_wtr_treatment_plant;
	}
	
	/**
	 * @return the instltion_wtr_treatment_plant_cost
	 */
	public Double getInstltion_wtr_treatment_plant_cost() {
		return instltion_wtr_treatment_plant_cost;
	}
	/**
	 * @param instltion_wtr_treatment_plant_cost the instltion_wtr_treatment_plant_cost to set
	 */
	public void setInstltion_wtr_treatment_plant_cost(Double instltion_wtr_treatment_plant_cost) {
		this.instltion_wtr_treatment_plant_cost = instltion_wtr_treatment_plant_cost;
	}
	/**
	 * @return the source_of_wss
	 */
	public String getSource_of_wss() {
		return source_of_wss;
	}
	/**
	 * @param source_of_wss the source_of_wss to set
	 */
	public void setSource_of_wss(String source_of_wss) {
		this.source_of_wss = source_of_wss;
	}
	/**
	 * @return the shifted_to_canal_from_other
	 */
	public String getShifted_to_canal_from_other() {
		return shifted_to_canal_from_other;
	}
	/**
	 * @param shifted_to_canal_from_other the shifted_to_canal_from_other to set
	 */
	public void setShifted_to_canal_from_other(String shifted_to_canal_from_other) {
		this.shifted_to_canal_from_other = shifted_to_canal_from_other;
	}
	/**
	 * @return the driling_of_new_tubewell_machinery_size
	 */
	public Double getDriling_of_new_tubewell_machinery_size() {
		return driling_of_new_tubewell_machinery_size;
	}
	/**
	 * @param driling_of_new_tubewell_machinery_size the driling_of_new_tubewell_machinery_size to set
	 */
	public void setDriling_of_new_tubewell_machinery_size(Double driling_of_new_tubewell_machinery_size) {
		this.driling_of_new_tubewell_machinery_size = driling_of_new_tubewell_machinery_size;
	}
	/**
	 * @return the driling_of_new_tubewell_machinery_depth
	 */
	public Double getDriling_of_new_tubewell_machinery_depth() {
		return driling_of_new_tubewell_machinery_depth;
	}
	/**
	 * @param driling_of_new_tubewell_machinery_depth the driling_of_new_tubewell_machinery_depth to set
	 */
	public void setDriling_of_new_tubewell_machinery_depth(Double driling_of_new_tubewell_machinery_depth) {
		this.driling_of_new_tubewell_machinery_depth = driling_of_new_tubewell_machinery_depth;
	}
	/**
	 * @return the driling_of_new_tubewell_machinery_capacity
	 */
	public Double getDriling_of_new_tubewell_machinery_capacity() {
		return driling_of_new_tubewell_machinery_capacity;
	}
	/**
	 * @param driling_of_new_tubewell_machinery_capacity the driling_of_new_tubewell_machinery_capacity to set
	 */
	public void setDriling_of_new_tubewell_machinery_capacity(Double driling_of_new_tubewell_machinery_capacity) {
		this.driling_of_new_tubewell_machinery_capacity = driling_of_new_tubewell_machinery_capacity;
	}
	/**
	 * @return the driling_of_new_tubewell_machinery_cost
	 */
	public Double getDriling_of_new_tubewell_machinery_cost() {
		return driling_of_new_tubewell_machinery_cost;
	}
	/**
	 * @param driling_of_new_tubewell_machinery_cost the driling_of_new_tubewell_machinery_cost to set
	 */
	public void setDriling_of_new_tubewell_machinery_cost(Double driling_of_new_tubewell_machinery_cost) {
		this.driling_of_new_tubewell_machinery_cost = driling_of_new_tubewell_machinery_cost;
	}
	/**
	 * @return the canal_based_inlet_channel_size_of_pipe
	 */
	public Double getCanal_based_inlet_channel_size_of_pipe() {
		return canal_based_inlet_channel_size_of_pipe;
	}
	/**
	 * @param canal_based_inlet_channel_size_of_pipe the canal_based_inlet_channel_size_of_pipe to set
	 */
	public void setCanal_based_inlet_channel_size_of_pipe(Double canal_based_inlet_channel_size_of_pipe) {
		this.canal_based_inlet_channel_size_of_pipe = canal_based_inlet_channel_size_of_pipe;
	}
	/**
	 * @return the canal_based_inlet_channel_length
	 */
	public Double getCanal_based_inlet_channel_length() {
		return canal_based_inlet_channel_length;
	}
	/**
	 * @param canal_based_inlet_channel_length the canal_based_inlet_channel_length to set
	 */
	public void setCanal_based_inlet_channel_length(Double canal_based_inlet_channel_length) {
		this.canal_based_inlet_channel_length = canal_based_inlet_channel_length;
	}
	/**
	 * @return the canal_based_s_and_s_capacity
	 */
	public Double getCanal_based_s_and_s_capacity() {
		return canal_based_s_and_s_capacity;
	}
	/**
	 * @param canal_based_s_and_s_capacity the canal_based_s_and_s_capacity to set
	 */
	public void setCanal_based_s_and_s_capacity(Double canal_based_s_and_s_capacity) {
		this.canal_based_s_and_s_capacity = canal_based_s_and_s_capacity;
	}
	/**
	 * @return the canal_based_filteration_plan_type
	 */
	public String getCanal_based_filteration_plan_type() {
		return canal_based_filteration_plan_type;
	}
	/**
	 * @param canal_based_filteration_plan_type the canal_based_filteration_plan_type to set
	 */
	public void setCanal_based_filteration_plan_type(String canal_based_filteration_plan_type) {
		this.canal_based_filteration_plan_type = canal_based_filteration_plan_type;
	}
	/**
	 * @return the canal_based_filteration_plan_capacity
	 */
	public Double getCanal_based_filteration_plan_capacity() {
		return canal_based_filteration_plan_capacity;
	}
	/**
	 * @param canal_based_filteration_plan_capacity the canal_based_filteration_plan_capacity to set
	 */
	public void setCanal_based_filteration_plan_capacity(Double canal_based_filteration_plan_capacity) {
		this.canal_based_filteration_plan_capacity = canal_based_filteration_plan_capacity;
	}
	/**
	 * @return the canal_based_cost
	 */
	public Double getCanal_based_cost() {
		return canal_based_cost;
	}
	/**
	 * @param canal_based_cost the canal_based_cost to set
	 */
	public void setCanal_based_cost(Double canal_based_cost) {
		this.canal_based_cost = canal_based_cost;
	}
	/**
	 * @return the ohsr_capacity
	 */
	public Double getOhsr_capacity() {
		return ohsr_capacity;
	}
	/**
	 * @param ohsr_capacity the ohsr_capacity to set
	 */
	public void setOhsr_capacity(Double ohsr_capacity) {
		this.ohsr_capacity = ohsr_capacity;
	}
	/**
	 * @return the ohsr_full_supply_level
	 */
	public Double getOhsr_full_supply_level() {
		return ohsr_full_supply_level;
	}
	/**
	 * @param ohsr_full_supply_level the ohsr_full_supply_level to set
	 */
	public void setOhsr_full_supply_level(Double ohsr_full_supply_level) {
		this.ohsr_full_supply_level = ohsr_full_supply_level;
	}
	/**
	 * @return the ohsr_cost
	 */
	public Double getOhsr_cost() {
		return ohsr_cost;
	}
	/**
	 * @param ohsr_cost the ohsr_cost to set
	 */
	public void setOhsr_cost(Double ohsr_cost) {
		this.ohsr_cost = ohsr_cost;
	}
	/**
	 * @return the other_structures_at_waterworks
	 */
	public String getOther_structures_at_waterworks() {
		return other_structures_at_waterworks;
	}
	/**
	 * @param other_structures_at_waterworks the other_structures_at_waterworks to set
	 */
	public void setOther_structures_at_waterworks(String other_structures_at_waterworks) {
		this.other_structures_at_waterworks = other_structures_at_waterworks;
	}
	/**
	 * @return the other_structures_at_waterworks_cost
	 */
	public Double getOther_structures_at_waterworks_cost() {
		return other_structures_at_waterworks_cost;
	}
	/**
	 * @param other_structures_at_waterworks_cost the other_structures_at_waterworks_cost to set
	 */
	public void setOther_structures_at_waterworks_cost(Double other_structures_at_waterworks_cost) {
		this.other_structures_at_waterworks_cost = other_structures_at_waterworks_cost;
	}
	/**
	 * @return the distribution_wss_to_village_type
	 */
	public String getDistribution_wss_to_village_type() {
		return distribution_wss_to_village_type;
	}
	/**
	 * @param distribution_wss_to_village_type the distribution_wss_to_village_type to set
	 */
	public void setDistribution_wss_to_village_type(String distribution_wss_to_village_type) {
		this.distribution_wss_to_village_type = distribution_wss_to_village_type;
	}
	/**
	 * @return the distribution_wss_to_village_length
	 */
	public Double getDistribution_wss_to_village_length() {
		return distribution_wss_to_village_length;
	}
	/**
	 * @param distribution_wss_to_village_length the distribution_wss_to_village_length to set
	 */
	public void setDistribution_wss_to_village_length(Double distribution_wss_to_village_length) {
		this.distribution_wss_to_village_length = distribution_wss_to_village_length;
	}
	
	/**
	 * @return the distribution_cost
	 */
	public Double getDistribution_cost() {
		return distribution_cost;
	}
	/**
	 * @param distribution_cost the distribution_cost to set
	 */
	public void setDistribution_cost(Double distribution_cost) {
		this.distribution_cost = distribution_cost;
	}
	/**
	 * @return the no_of_connections_100_mtr
	 */
	public Double getNo_of_connections_100_mtr() {
		return no_of_connections_100_mtr;
	}
	/**
	 * @param no_of_connections_100_mtr the no_of_connections_100_mtr to set
	 */
	public void setNo_of_connections_100_mtr(Double no_of_connections_100_mtr) {
		this.no_of_connections_100_mtr = no_of_connections_100_mtr;
	}
	/**
	 * @return the no_of_mtrs_100_mtr
	 */
	public Double getNo_of_mtrs_100_mtr() {
		return no_of_mtrs_100_mtr;
	}
	/**
	 * @param no_of_mtrs_100_mtr the no_of_mtrs_100_mtr to set
	 */
	public void setNo_of_mtrs_100_mtr(Double no_of_mtrs_100_mtr) {
		this.no_of_mtrs_100_mtr = no_of_mtrs_100_mtr;
	}
	/**
	 * @return the cost_100_mtr
	 */
	public Double getCost_100_mtr() {
		return cost_100_mtr;
	}
	/**
	 * @param cost_100_mtr the cost_100_mtr to set
	 */
	public void setCost_100_mtr(Double cost_100_mtr) {
		this.cost_100_mtr = cost_100_mtr;
	}
	/**
	 * @return the disinfection_unit_type
	 */
	public String getDisinfection_unit_type() {
		return disinfection_unit_type;
	}
	/**
	 * @param disinfection_unit_type the disinfection_unit_type to set
	 */
	public void setDisinfection_unit_type(String disinfection_unit_type) {
		this.disinfection_unit_type = disinfection_unit_type;
	}
	/**
	 * @return the disinfection_unit_cost
	 */
	public Double getDisinfection_unit_cost() {
		return disinfection_unit_cost;
	}
	/**
	 * @param disinfection_unit_cost the disinfection_unit_cost to set
	 */
	public void setDisinfection_unit_cost(Double disinfection_unit_cost) {
		this.disinfection_unit_cost = disinfection_unit_cost;
	}
	/**
	 * @return the water_treatment_plant_in_case_of_quality_village_type
	 */
	public String getWater_treatment_plant_in_case_of_quality_village_type() {
		return water_treatment_plant_in_case_of_quality_village_type;
	}
	/**
	 * @param water_treatment_plant_in_case_of_quality_village_type the water_treatment_plant_in_case_of_quality_village_type to set
	 */
	public void setWater_treatment_plant_in_case_of_quality_village_type(
			String water_treatment_plant_in_case_of_quality_village_type) {
		this.water_treatment_plant_in_case_of_quality_village_type = water_treatment_plant_in_case_of_quality_village_type;
	}
	/**
	 * @return the water_treatment_plant_in_case_of_quality_village_capacity
	 */
	public Double getWater_treatment_plant_in_case_of_quality_village_capacity() {
		return water_treatment_plant_in_case_of_quality_village_capacity;
	}
	/**
	 * @param water_treatment_plant_in_case_of_quality_village_capacity the water_treatment_plant_in_case_of_quality_village_capacity to set
	 */
	public void setWater_treatment_plant_in_case_of_quality_village_capacity(
			Double water_treatment_plant_in_case_of_quality_village_capacity) {
		this.water_treatment_plant_in_case_of_quality_village_capacity = water_treatment_plant_in_case_of_quality_village_capacity;
	}
	/**
	 * @return the water_treatment_plant_in_case_of_quality_village_cost
	 */
	public Double getWater_treatment_plant_in_case_of_quality_village_cost() {
		return water_treatment_plant_in_case_of_quality_village_cost;
	}
	/**
	 * @param water_treatment_plant_in_case_of_quality_village_cost the water_treatment_plant_in_case_of_quality_village_cost to set
	 */
	public void setWater_treatment_plant_in_case_of_quality_village_cost(
			Double water_treatment_plant_in_case_of_quality_village_cost) {
		this.water_treatment_plant_in_case_of_quality_village_cost = water_treatment_plant_in_case_of_quality_village_cost;
	}
	/**
	 * @return the bulk_water_meter_no
	 */
	public Double getBulk_water_meter_no() {
		return bulk_water_meter_no;
	}
	/**
	 * @param bulk_water_meter_no the bulk_water_meter_no to set
	 */
	public void setBulk_water_meter_no(Double bulk_water_meter_no) {
		this.bulk_water_meter_no = bulk_water_meter_no;
	}
	/**
	 * @return the bulk_water_meter_cost
	 */
	public Double getBulk_water_meter_cost() {
		return bulk_water_meter_cost;
	}
	/**
	 * @param bulk_water_meter_cost the bulk_water_meter_cost to set
	 */
	public void setBulk_water_meter_cost(Double bulk_water_meter_cost) {
		this.bulk_water_meter_cost = bulk_water_meter_cost;
	}
	/**
	 * @return the extension_sanction_of_new_electric_connection_cost
	 */
	public Double getExtension_sanction_of_new_electric_connection_cost() {
		return extension_sanction_of_new_electric_connection_cost;
	}
	/**
	 * @param extension_sanction_of_new_electric_connection_cost the extension_sanction_of_new_electric_connection_cost to set
	 */
	public void setExtension_sanction_of_new_electric_connection_cost(
			Double extension_sanction_of_new_electric_connection_cost) {
		this.extension_sanction_of_new_electric_connection_cost = extension_sanction_of_new_electric_connection_cost;
	}
	
	
	
	
}
