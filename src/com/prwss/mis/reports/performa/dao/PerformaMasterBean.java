package com.prwss.mis.reports.performa.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name = "performa_master_vw", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class PerformaMasterBean  implements Serializable  {

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 8719959082768089898L;

	@Id
	@Column(name = "village_id")
	private String villageId;
	
	@Column(name = "village_name")
	private String villageName;
	
	@Column(name = "program_name")
	private String program_name;
	
	@Column(name = "govt_building")
	private String govt_building;
	
	@Column(name = "private_building")
	private String private_building;
	
	@Column(name = "sv_mv")
	private String sv_mv;
	
	@Column(name = "pvc")
	private String pvc;
	
	@Column(name = "ms_di_ci")
	private String ms_di_ci;
	
	@Column(name = "network_gi")
	private String network_gi;
	
	
	// scheme based
	@Column(name = "ohsr_ct")
	private String ohsr_ct;
	
	@Column(name = "ohsr_ct_dep")
	private String ohsr_ct_dep;
	
	@Column(name = "ohsr_ugsr")
	private String ohsr_ugsr;
	
	@Column(name = "ohsr_ugsr_dep")
	private String ohsr_ugsr_dep;
	//
	
	@Column(name = "habitation_type")
	private String habitationType;
	
	@Column(name = "sc_population")
	private String scPopulation;
	
	@Column(name = "total_population")
	private String totalPopulation;
	
	@Column(name = "sc_households")
	private String scHouseholds;
	
	@Column(name = "total_households")
	private String totalHouseHolds;
	
	@Column(name = "assembly_constituency")
	private String parConsName;
	
	@Column(name = "no_of_habitation")
	private String noOfHabitation;
	
	@Column(name = "scheme_id")
	private String schemeId;
	
	@Column(name = "had_bast_no")
	private String hadBstNo;
	
	@Column(name = "scheme_name")
	private String schemeName;
	
	@Column(name = "scheme_source")
	private String schemeSource;
	
	@Column(name = "name_of_habitations")
	private String name_of_habitations;
	
	@Column(name = "panchayat_ghar")
	private String panchayat_ghar;
	
	@Column(name = "community_centre_or_dharamshala")
	private String community_centre_or_dharamshala;
	
	@Column(name = "angarwaries")
	private String angarwaries;
	
	@Column(name = "no_of_angarwaries")
	private String no_of_angarwaries;
	
/*	@Column(name = "angarwaries_buildings_type")
	private String angarwaries_buildings_type;
	*/
	@Column(name = "govt_schools")
	private String govt_schools;
	
	@Column(name = "no_of_govt_schools")
	private String no_of_govt_schools;
	
	@Column(name = "health_centre")
	private String health_centre;
	
	@Column(name = "no_of_health_centres")
	private String no_of_health_centres;
	
	@Column(name = "village_ponds")
	private String village_ponds;
	
	@Column(name = "no_of_village_ponds")
	private String no_of_village_ponds;
	
	@Column(name = "area_in_square_meters")
	private String area_in_square_meters;
	
	@Column(name = "sewrage")
	private String sewrage;
	
	@Column(name = "scheme_commissioned_date")
	private Date date_of_commissioning;
	
	@Column(name = "prog_id")
	private String prog_id;
	
	@Column(name = "supply_service_level")
	private String service_level;
	@Column(name = "scheme_upgraded")
	private Date scheme_upgraded;
	@Column(name = "programm_imp")
	private String programm_imp;
	@Column(name = "scheme_expenditure")
	private String scheme_expenditure;
	@Column(name = "present_service_level")
	private String present_service_level;
	@Column(name = "depth_of_tubewell")
	private String depth_of_tubewell;
	@Column(name = "size_of_tubewell")
	private String size_of_tubewell;
	@Column(name = "year_of_drilling")
	private Date year_of_drilling;
	@Column(name = "original_discharge_at_time_of_commissioning")
	private String original_discharge_at_time_of_commissioning;
	@Column(name = "present_discharge")
	private String present_discharge;
	@Column(name = "present_spring_level")
	private String present_spring_level;
	@Column(name = "installation_of_new_machinery")
	private Date installation_of_new_machinery;
	@Column(name = "capacity_of_machinery")
	private String capacity_of_machinery;
	@Column(name = "inlet_channel_type")
	private String inlet_channel_type;
	@Column(name = "inlet_channel_lenght")
	private String inlet_channel_lenght;
	
	@Column(name = "pipe_type")
	private String pipe_type;
	@Column(name = "capacity_of_ss_tank")
	private String capacity_of_ss_tank;
	
	@Column(name = "capacity_of_hl_tank")
	private String capacity_of_hl_tank;
	@Column(name = "capacity_of_cw_tank")
	private String capacity_of_cw_tank;
	@Column(name = "filtertion_type")
	private String filtertion_type;
	
	@Column(name = "filtertion_no")
	private String filtertion_no;
	@Column(name = "filtertion_capacity")
	private String filtertion_capacity;
	
	@Column(name = "capacity_of_raw_water")
	private String capacity_of_raw_water;
	
	@Column(name = "capacity_of_clear_water")
	private String capacity_of_clear_water;
	@Column(name = "no_of_ohsr")
	private String no_of_ohsr;
	
	@Column(name = "ohsr_construction_date")
	private Date ohsr_construction_date;
	
	@Column(name = "ohsr_capcity")
	private String ohsr_capcity;
	
	@Column(name = "ohsr_full_supply_level")
	private String ohsr_full_supply_level;
	
	@Column(name = "ohsr_working_condition")
	private String ohsr_working_condition;
	
	@Column(name = "ohsr_working_condition_if_no")
	private String ohsr_working_condition_if_no;
	
	@Column(name = "dismantling_received")
	private String dismantling_received;
	
	@Column(name = "disinfection_type")
	private String disinfection_type;
	
	@Column(name = "disinfection_instalation_time")
	private Date disinfection_instalation_time;
	
	@Column(name = "disinfection_present_status")
	private String disinfection_present_status;
	
	@Column(name = "scheme_operated_by")
	private String scheme_operated_by;
	
	@Column(name = "dwss_operated_arrangement")
	private String dwss_operated_arrangement;
	
	@Column(name = "sanctioned_load")
	private String sanctioned_load;
	
	@Column(name = "pending__bill_31032017")
	private String pending__bill_31032017;
	
	@Column(name = "pending__bill_30062017")
	private String pending__bill_30062017;
	
	@Column(name = "avg_month_billof_wss")
	private String avg_month_billof_wss;
	
	
	@Column(name = "pipeline_ac")
	private String pipeline_ac;
	@Column(name = "pipeline_ms_di_ci")
	private String pipeline_ms_di_ci;
	@Column(name = "pipeline_gi")
	private String pipeline_gi;
	
	@Column(name = "pipeline_pvc")
	private String pipeline_pvc;
	
	@Column(name = "functional_distribution_percentage")
	private String functional_distribution_percentage;
	@Column(name = "household_water_connection")
	private String household_water_connection;
	@Column(name = "watered_connection")
	private String watered_connection;
	
	@Column(name = "metered_connection")
	private String metered_connection;
	@Column(name = "metered_supply_recovery")
	private String metered_supply_recovery;
	
	@Column(name = "flat_rate_charges_per_month")
	private String flat_rate_charges_per_month;
	@Column(name = "arrear_of_recovery_with_consumers_as01042017")
	private String arrear_of_recovery_with_consumers_as01042017;
	@Column(name = "scheme_functional")
	private String scheme_functional;
	@Column(name = "scheme_nonfunctional")
	private String scheme_nonfunctional;
	@Column(name = "scheme_nonfunctional_date")
	private Date scheme_nonfunctional_date;
	
	@Column(name = "sew_connectio_sc_households")
	private String sew_connectio_sc_households;
	
	@Column(name = "water_connectio_sc_households")
	private String water_connectio_sc_households;
	
	@Column(name = "female_gen_population")
	private String female_gen_population;
	
	@Column(name = "female_sc_population")
	private String female_sc_population;
	
	@Column(name = "habitation_distance_from_water_supply_sch")
	private String sewrahabitation_distance_from_water_supply_schge;
	
	@Column(name = "ground_water_potable_no")
	private String ground_water_potable_no;
	
	@Column(name = "ground_water_potable_status")
	private String ground_water_potable_status;
	
	@Column(name = "preventive_measures_adopted")
	private String preventive_measures_adopted;
	
	@Column(name = "being_operated_by")
	private String being_operated_by;
	
	@Column(name = "capacity_of_plant")
	private String capacity_of_plant;

	@Column(name = "date_of_installation")
	private Date date_of_installation;
	
	@Column(name = "volume_of_water_daily_basis")
	private String volume_of_water_daily_basis;
	
	@Column(name = "disposal_of_reject_water")
	private String disposal_of_reject_water;
	
	@Column(name = "penetration_in_percentage")
	private String penetration_in_percentage;
	
	@Column(name = "present_rate_of_user_charges")
	private String present_rate_of_user_charges;
	@Column(name = "seperate_sanctioned_load")
	private String seperate_sanctioned_load;
	@Column(name = "seperate_pending_eletric_bill31032017")
	private String seperate_pending_eletric_bill31032017;
	@Column(name = "average_monthly_bill_of_treatment_plant")
	private String average_monthly_bill_of_treatment_plant;
	
	@Column(name = "independent_new_wss")
	private String independent_new_wss;
	
	
	@Column(name = "independent_new_wss_cost")
	private String independent_new_wss_cost;
	
	@Column(name = "upgradation_of_existing_wss")
	private String upgradation_of_existing_wss;
	
	@Column(name = "upgradation_of_existing_wss_cost")
	private String upgradation_of_existing_wss_cost;
	
	//private String instltion_wtr_treatment_plant
	@Column(name = "")
	private String instltion_wtr_treatment_plant_cost;
	
	@Column(name = "source_of_wss")
	private String source_of_wss;
	
	@Column(name = "shifted_to_canal_from_other")
	private String shifted_to_canal_from_other;
	
	@Column(name = "driling_of_new_tubewell_machinery_size")
	private String driling_of_new_tubewell_machinery_size;
	
	@Column(name = "driling_of_new_tubewell_machinery_depth")
	private String driling_of_new_tubewell_machinery_depth;
	
	@Column(name = "driling_of_new_tubewell_machinery_capacity")
	private String driling_of_new_tubewell_machinery_capacity;
	
	@Column(name = "driling_of_new_tubewell_machinery_cost")
	private String driling_of_new_tubewell_machinery_cost;
	
	@Column(name = "canal_based_inlet_channel_size_of_pipe")
	private String canal_based_inlet_channel_size_of_pipe;
	
	@Column(name = "canal_based_inlet_channel_length")
	private String canal_based_inlet_channel_length;
	
	@Column(name = "canal_based_s_and_s_capacity")
	private String canal_based_s_and_s_capacity;
	
	@Column(name = "canal_based_filteration_plan_type")
	private String canal_based_filteration_plan_type;
	
	@Column(name = "canal_based_filteration_plan_capacity")
	private String canal_based_filteration_plan_capacity;
	
	@Column(name = "canal_based_cost")
	private String canal_based_cost;
	
	@Column(name = "ohsr_capacity")
	private String ohsr_capacity;

	//@Column(name = "")
	//private String ohsr_full_supply_level;
	
	@Column(name = "ohsr_cost")
	private String ohsr_cost;
	
	@Column(name = "other_structures_at_waterworks")
	private String other_structures_at_waterworks;
	
	@Column(name = "other_structures_at_waterworks_cost")
	private String other_structures_at_waterworks_cost;
	
	@Column(name = "distribution_wss_to_village_type")
	private String distribution_wss_to_village_type;
	
	@Column(name = "distribution_wss_to_village_length")
	private String distribution_wss_to_village_length;
	
	@Column(name = "distribution_wss_within_village_type")
	private String distribution_wss_within_village_type;
	
	@Column(name = "distribution_wss_within_village_length")
	private String distribution_wss_within_village_length;
	
	@Column(name = "distribution_cost")
	private String distribution_cost;
	
	@Column(name = "no_of_connections_100_mtr")
	private String no_of_connections_100_mtr;
	
	@Column(name = "no_of_mtrs_100_mtr")
	private String no_of_mtrs_100_mtr;
	
	@Column(name = "cost_100_mtr")
	private String cost_100_mtr;
	
	@Column(name = "disinfection_unit_type")
	private String disinfection_unit_type;
	
	@Column(name = "disinfection_unit_cost")
	private String disinfection_unit_cost;
	
	@Column(name = "water_treatment_plant_in_case_of_quality_village_type")
	private String water_treatment_plant_in_case_of_quality_village_type;
	
	@Column(name = "water_treatment_plant_in_case_of_quality_village_capacity")
	private String water_treatment_plant_in_case_of_quality_village_capacity;
	
	@Column(name = "water_treatment_plant_in_case_of_quality_village_cost")
	private String water_treatment_plant_in_case_of_quality_village_cost;
	
	@Column(name = "bulk_water_meter_no")
	private String bulk_water_meter_no;
	
	@Column(name = "bulk_water_meter_cost")
	private String bulk_water_meter_cost;
	
	@Column(name = "extension_sanction_of_new_electric_connection_cost")
	private String extension_sanction_of_new_electric_connection_cost;
	
	@Column(name = "percentage_functional_pipeline")
	private String percentage_functional_pipeline;
	
	@Column(name = "ohsr_full_supply_level1")
	private String ohsr_full_supply_level1;
	
	@Column(name = "scheme_type")
	private String scheme_type;
	
	//scheme_type
	
	
	@Column(name = "no_of_water_connection")
	private String no_of_water_connection;
	
	//five year plan
	@Column(name = "ct_dia")
	private String ct_dia;
	
	@Column(name = "ct_dep")
	private String ct_dep;
	
	@Column(name = "ugsr_dep")
	private String ugsr_dep;
	
	@Column(name = "ugsr_dia")
	private String ugsr_dia;
	
	
	
	
	
	
	public String getOhsr_ct_dep() {
		return ohsr_ct_dep;
	}

	public void setOhsr_ct_dep(String ohsr_ct_dep) {
		this.ohsr_ct_dep = ohsr_ct_dep;
	}

	public String getOhsr_ugsr_dep() {
		return ohsr_ugsr_dep;
	}

	public void setOhsr_ugsr_dep(String ohsr_ugsr_dep) {
		this.ohsr_ugsr_dep = ohsr_ugsr_dep;
	}

	public String getCt_dia() {
		return ct_dia;
	}

	public void setCt_dia(String ct_dia) {
		this.ct_dia = ct_dia;
	}

	public String getCt_dep() {
		return ct_dep;
	}

	public void setCt_dep(String ct_dep) {
		this.ct_dep = ct_dep;
	}

	public String getUgsr_dep() {
		return ugsr_dep;
	}

	public void setUgsr_dep(String ugsr_dep) {
		this.ugsr_dep = ugsr_dep;
	}

	public String getUgsr_dia() {
		return ugsr_dia;
	}

	public void setUgsr_dia(String ugsr_dia) {
		this.ugsr_dia = ugsr_dia;
	}

	/**
	 * @return the pvc
	 */
	public String getPvc() {
		return pvc;
	}

	/**
	 * @param pvc the pvc to set
	 */
	public void setPvc(String pvc) {
		this.pvc = pvc;
	}

	/**
	 * @return the ms_di_ci
	 */
	public String getMs_di_ci() {
		return ms_di_ci;
	}

	/**
	 * @param ms_di_ci the ms_di_ci to set
	 */
	public void setMs_di_ci(String ms_di_ci) {
		this.ms_di_ci = ms_di_ci;
	}

	/**
	 * @return the network_gi
	 */
	public String getNetwork_gi() {
		return network_gi;
	}

	/**
	 * @param network_gi the network_gi to set
	 */
	public void setNetwork_gi(String network_gi) {
		this.network_gi = network_gi;
	}

	/**
	 * @return the ohsr_ct
	 */
	public String getOhsr_ct() {
		return ohsr_ct;
	}

	/**
	 * @param ohsr_ct the ohsr_ct to set
	 */
	public void setOhsr_ct(String ohsr_ct) {
		this.ohsr_ct = ohsr_ct;
	}

	/**
	 * @return the ohsr_ugsr
	 */
	public String getOhsr_ugsr() {
		return ohsr_ugsr;
	}

	/**
	 * @param ohsr_ugsr the ohsr_ugsr to set
	 */
	public void setOhsr_ugsr(String ohsr_ugsr) {
		this.ohsr_ugsr = ohsr_ugsr;
	}

	/**
	 * @return the govt_building
	 */
	public String getGovt_building() {
		return govt_building;
	}

	/**
	 * @param govt_building the govt_building to set
	 */
	public void setGovt_building(String govt_building) {
		this.govt_building = govt_building;
	}

	/**
	 * @return the private_building
	 */
	public String getPrivate_building() {
		return private_building;
	}

	/**
	 * @param private_building the private_building to set
	 */
	public void setPrivate_building(String private_building) {
		this.private_building = private_building;
	}

	/**
	 * @return the sv_mv
	 */
	public String getSv_mv() {
		return sv_mv;
	}

	/**
	 * @param sv_mv the sv_mv to set
	 */
	public void setSv_mv(String sv_mv) {
		this.sv_mv = sv_mv;
	}

	/**
	 * @return the program_name
	 */
	public String getProgram_name() {
		return program_name;
	}

	/**
	 * @param program_name the program_name to set
	 */
	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}
	
	
	/**
	 * @return the scheme_type
	 */
	public String getScheme_type() {
		return scheme_type;
	}

	/**
	 * @param scheme_type the scheme_type to set
	 */
	public void setScheme_type(String scheme_type) {
		this.scheme_type = scheme_type;
	}

	/**
	 * @return the no_of_water_connection
	 */
	public String getNo_of_water_connection() {
		return no_of_water_connection;
	}

	/**
	 * @param no_of_water_connection the no_of_water_connection to set
	 */
	public void setNo_of_water_connection(String no_of_water_connection) {
		this.no_of_water_connection = no_of_water_connection;
	}

	/**
	 * @return the ohsr_full_supply_level1
	 */
	public String getOhsr_full_supply_level1() {
		return ohsr_full_supply_level1;
	}

	/**
	 * @param ohsr_full_supply_level1 the ohsr_full_supply_level1 to set
	 */
	public void setOhsr_full_supply_level1(String ohsr_full_supply_level1) {
		this.ohsr_full_supply_level1 = ohsr_full_supply_level1;
	}

	/**
	 * @return the percentage_functional_pipeline
	 */
	public String getPercentage_functional_pipeline() {
		return percentage_functional_pipeline;
	}

	/**
	 * @param percentage_functional_pipeline the percentage_functional_pipeline to set
	 */
	public void setPercentage_functional_pipeline(String percentage_functional_pipeline) {
		this.percentage_functional_pipeline = percentage_functional_pipeline;
	}

	/**
	 * @return the independent_new_wss
	 */
	public String getIndependent_new_wss() {
		return independent_new_wss;
	}

	/**
	 * @param independent_new_wss the independent_new_wss to set
	 */
	public void setIndependent_new_wss(String independent_new_wss) {
		this.independent_new_wss = independent_new_wss;
	}

	/**
	 * @return the independent_new_wss_cost
	 */
	public String getIndependent_new_wss_cost() {
		return independent_new_wss_cost;
	}

	/**
	 * @param independent_new_wss_cost the independent_new_wss_cost to set
	 */
	public void setIndependent_new_wss_cost(String independent_new_wss_cost) {
		this.independent_new_wss_cost = independent_new_wss_cost;
	}

	/**
	 * @return the upgradation_of_existing_wss
	 */
	public String getUpgradation_of_existing_wss() {
		return upgradation_of_existing_wss;
	}

	/**
	 * @param upgradation_of_existing_wss the upgradation_of_existing_wss to set
	 */
	public void setUpgradation_of_existing_wss(String upgradation_of_existing_wss) {
		this.upgradation_of_existing_wss = upgradation_of_existing_wss;
	}

	/**
	 * @return the upgradation_of_existing_wss_cost
	 */
	public String getUpgradation_of_existing_wss_cost() {
		return upgradation_of_existing_wss_cost;
	}

	/**
	 * @param upgradation_of_existing_wss_cost the upgradation_of_existing_wss_cost to set
	 */
	public void setUpgradation_of_existing_wss_cost(String upgradation_of_existing_wss_cost) {
		this.upgradation_of_existing_wss_cost = upgradation_of_existing_wss_cost;
	}

	/**
	 * @return the instltion_wtr_treatment_plant_cost
	 */
	public String getInstltion_wtr_treatment_plant_cost() {
		return instltion_wtr_treatment_plant_cost;
	}

	/**
	 * @param instltion_wtr_treatment_plant_cost the instltion_wtr_treatment_plant_cost to set
	 */
	public void setInstltion_wtr_treatment_plant_cost(String instltion_wtr_treatment_plant_cost) {
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
	public String getDriling_of_new_tubewell_machinery_size() {
		return driling_of_new_tubewell_machinery_size;
	}

	/**
	 * @param driling_of_new_tubewell_machinery_size the driling_of_new_tubewell_machinery_size to set
	 */
	public void setDriling_of_new_tubewell_machinery_size(String driling_of_new_tubewell_machinery_size) {
		this.driling_of_new_tubewell_machinery_size = driling_of_new_tubewell_machinery_size;
	}

	/**
	 * @return the driling_of_new_tubewell_machinery_depth
	 */
	public String getDriling_of_new_tubewell_machinery_depth() {
		return driling_of_new_tubewell_machinery_depth;
	}

	/**
	 * @param driling_of_new_tubewell_machinery_depth the driling_of_new_tubewell_machinery_depth to set
	 */
	public void setDriling_of_new_tubewell_machinery_depth(String driling_of_new_tubewell_machinery_depth) {
		this.driling_of_new_tubewell_machinery_depth = driling_of_new_tubewell_machinery_depth;
	}

	/**
	 * @return the driling_of_new_tubewell_machinery_capacity
	 */
	public String getDriling_of_new_tubewell_machinery_capacity() {
		return driling_of_new_tubewell_machinery_capacity;
	}

	/**
	 * @param driling_of_new_tubewell_machinery_capacity the driling_of_new_tubewell_machinery_capacity to set
	 */
	public void setDriling_of_new_tubewell_machinery_capacity(String driling_of_new_tubewell_machinery_capacity) {
		this.driling_of_new_tubewell_machinery_capacity = driling_of_new_tubewell_machinery_capacity;
	}

	/**
	 * @return the driling_of_new_tubewell_machinery_cost
	 */
	public String getDriling_of_new_tubewell_machinery_cost() {
		return driling_of_new_tubewell_machinery_cost;
	}

	/**
	 * @param driling_of_new_tubewell_machinery_cost the driling_of_new_tubewell_machinery_cost to set
	 */
	public void setDriling_of_new_tubewell_machinery_cost(String driling_of_new_tubewell_machinery_cost) {
		this.driling_of_new_tubewell_machinery_cost = driling_of_new_tubewell_machinery_cost;
	}

	/**
	 * @return the canal_based_inlet_channel_size_of_pipe
	 */
	public String getCanal_based_inlet_channel_size_of_pipe() {
		return canal_based_inlet_channel_size_of_pipe;
	}

	/**
	 * @param canal_based_inlet_channel_size_of_pipe the canal_based_inlet_channel_size_of_pipe to set
	 */
	public void setCanal_based_inlet_channel_size_of_pipe(String canal_based_inlet_channel_size_of_pipe) {
		this.canal_based_inlet_channel_size_of_pipe = canal_based_inlet_channel_size_of_pipe;
	}

	/**
	 * @return the canal_based_inlet_channel_length
	 */
	public String getCanal_based_inlet_channel_length() {
		return canal_based_inlet_channel_length;
	}

	/**
	 * @param canal_based_inlet_channel_length the canal_based_inlet_channel_length to set
	 */
	public void setCanal_based_inlet_channel_length(String canal_based_inlet_channel_length) {
		this.canal_based_inlet_channel_length = canal_based_inlet_channel_length;
	}

	/**
	 * @return the canal_based_s_and_s_capacity
	 */
	public String getCanal_based_s_and_s_capacity() {
		return canal_based_s_and_s_capacity;
	}

	/**
	 * @param canal_based_s_and_s_capacity the canal_based_s_and_s_capacity to set
	 */
	public void setCanal_based_s_and_s_capacity(String canal_based_s_and_s_capacity) {
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
	public String getCanal_based_filteration_plan_capacity() {
		return canal_based_filteration_plan_capacity;
	}

	/**
	 * @param canal_based_filteration_plan_capacity the canal_based_filteration_plan_capacity to set
	 */
	public void setCanal_based_filteration_plan_capacity(String canal_based_filteration_plan_capacity) {
		this.canal_based_filteration_plan_capacity = canal_based_filteration_plan_capacity;
	}

	/**
	 * @return the canal_based_cost
	 */
	public String getCanal_based_cost() {
		return canal_based_cost;
	}

	/**
	 * @param canal_based_cost the canal_based_cost to set
	 */
	public void setCanal_based_cost(String canal_based_cost) {
		this.canal_based_cost = canal_based_cost;
	}

	/**
	 * @return the ohsr_capacity
	 */
	public String getOhsr_capacity() {
		return ohsr_capacity;
	}

	/**
	 * @param ohsr_capacity the ohsr_capacity to set
	 */
	public void setOhsr_capacity(String ohsr_capacity) {
		this.ohsr_capacity = ohsr_capacity;
	}

	/**
	 * @return the ohsr_cost
	 */
	public String getOhsr_cost() {
		return ohsr_cost;
	}

	/**
	 * @param ohsr_cost the ohsr_cost to set
	 */
	public void setOhsr_cost(String ohsr_cost) {
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
	public String getOther_structures_at_waterworks_cost() {
		return other_structures_at_waterworks_cost;
	}

	/**
	 * @param other_structures_at_waterworks_cost the other_structures_at_waterworks_cost to set
	 */
	public void setOther_structures_at_waterworks_cost(String other_structures_at_waterworks_cost) {
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
	public String getDistribution_wss_to_village_length() {
		return distribution_wss_to_village_length;
	}

	/**
	 * @param distribution_wss_to_village_length the distribution_wss_to_village_length to set
	 */
	public void setDistribution_wss_to_village_length(String distribution_wss_to_village_length) {
		this.distribution_wss_to_village_length = distribution_wss_to_village_length;
	}

	/**
	 * @return the distribution_wss_within_village_type
	 */
	public String getDistribution_wss_within_village_type() {
		return distribution_wss_within_village_type;
	}

	/**
	 * @param distribution_wss_within_village_type the distribution_wss_within_village_type to set
	 */
	public void setDistribution_wss_within_village_type(String distribution_wss_within_village_type) {
		this.distribution_wss_within_village_type = distribution_wss_within_village_type;
	}

	/**
	 * @return the distribution_wss_within_village_length
	 */
	public String getDistribution_wss_within_village_length() {
		return distribution_wss_within_village_length;
	}

	/**
	 * @param distribution_wss_within_village_length the distribution_wss_within_village_length to set
	 */
	public void setDistribution_wss_within_village_length(String distribution_wss_within_village_length) {
		this.distribution_wss_within_village_length = distribution_wss_within_village_length;
	}

	/**
	 * @return the distribution_cost
	 */
	public String getDistribution_cost() {
		return distribution_cost;
	}

	/**
	 * @param distribution_cost the distribution_cost to set
	 */
	public void setDistribution_cost(String distribution_cost) {
		this.distribution_cost = distribution_cost;
	}

	/**
	 * @return the no_of_connections_100_mtr
	 */
	public String getNo_of_connections_100_mtr() {
		return no_of_connections_100_mtr;
	}

	/**
	 * @param no_of_connections_100_mtr the no_of_connections_100_mtr to set
	 */
	public void setNo_of_connections_100_mtr(String no_of_connections_100_mtr) {
		this.no_of_connections_100_mtr = no_of_connections_100_mtr;
	}

	/**
	 * @return the no_of_mtrs_100_mtr
	 */
	public String getNo_of_mtrs_100_mtr() {
		return no_of_mtrs_100_mtr;
	}

	/**
	 * @param no_of_mtrs_100_mtr the no_of_mtrs_100_mtr to set
	 */
	public void setNo_of_mtrs_100_mtr(String no_of_mtrs_100_mtr) {
		this.no_of_mtrs_100_mtr = no_of_mtrs_100_mtr;
	}

	/**
	 * @return the cost_100_mtr
	 */
	public String getCost_100_mtr() {
		return cost_100_mtr;
	}

	/**
	 * @param cost_100_mtr the cost_100_mtr to set
	 */
	public void setCost_100_mtr(String cost_100_mtr) {
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
	public String getDisinfection_unit_cost() {
		return disinfection_unit_cost;
	}

	/**
	 * @param disinfection_unit_cost the disinfection_unit_cost to set
	 */
	public void setDisinfection_unit_cost(String disinfection_unit_cost) {
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
	public String getWater_treatment_plant_in_case_of_quality_village_capacity() {
		return water_treatment_plant_in_case_of_quality_village_capacity;
	}

	/**
	 * @param water_treatment_plant_in_case_of_quality_village_capacity the water_treatment_plant_in_case_of_quality_village_capacity to set
	 */
	public void setWater_treatment_plant_in_case_of_quality_village_capacity(
			String water_treatment_plant_in_case_of_quality_village_capacity) {
		this.water_treatment_plant_in_case_of_quality_village_capacity = water_treatment_plant_in_case_of_quality_village_capacity;
	}

	/**
	 * @return the water_treatment_plant_in_case_of_quality_village_cost
	 */
	public String getWater_treatment_plant_in_case_of_quality_village_cost() {
		return water_treatment_plant_in_case_of_quality_village_cost;
	}

	/**
	 * @param water_treatment_plant_in_case_of_quality_village_cost the water_treatment_plant_in_case_of_quality_village_cost to set
	 */
	public void setWater_treatment_plant_in_case_of_quality_village_cost(
			String water_treatment_plant_in_case_of_quality_village_cost) {
		this.water_treatment_plant_in_case_of_quality_village_cost = water_treatment_plant_in_case_of_quality_village_cost;
	}

	/**
	 * @return the bulk_water_meter_no
	 */
	public String getBulk_water_meter_no() {
		return bulk_water_meter_no;
	}

	/**
	 * @param bulk_water_meter_no the bulk_water_meter_no to set
	 */
	public void setBulk_water_meter_no(String bulk_water_meter_no) {
		this.bulk_water_meter_no = bulk_water_meter_no;
	}

	/**
	 * @return the bulk_water_meter_cost
	 */
	public String getBulk_water_meter_cost() {
		return bulk_water_meter_cost;
	}

	/**
	 * @param bulk_water_meter_cost the bulk_water_meter_cost to set
	 */
	public void setBulk_water_meter_cost(String bulk_water_meter_cost) {
		this.bulk_water_meter_cost = bulk_water_meter_cost;
	}

	/**
	 * @return the extension_sanction_of_new_electric_connection_cost
	 */
	public String getExtension_sanction_of_new_electric_connection_cost() {
		return extension_sanction_of_new_electric_connection_cost;
	}

	/**
	 * @param extension_sanction_of_new_electric_connection_cost the extension_sanction_of_new_electric_connection_cost to set
	 */
	public void setExtension_sanction_of_new_electric_connection_cost(
			String extension_sanction_of_new_electric_connection_cost) {
		this.extension_sanction_of_new_electric_connection_cost = extension_sanction_of_new_electric_connection_cost;
	}

	/**
	 * @return the ground_water_potable_no
	 */
	public String getGround_water_potable_no() {
		return ground_water_potable_no;
	}

	/**
	 * @return the date_of_installation
	 */
	public Date getDate_of_installation() {
		return date_of_installation;
	}

	/**
	 * @param date_of_installation the date_of_installation to set
	 */
	public void setDate_of_installation(Date date_of_installation) {
		this.date_of_installation = date_of_installation;
	}

	/**
	 * @param ground_water_potable_no the ground_water_potable_no to set
	 */
	public void setGround_water_potable_no(String ground_water_potable_no) {
		this.ground_water_potable_no = ground_water_potable_no;
	}

	/**
	 * @return the ground_water_potable_status
	 */
	public String getGround_water_potable_status() {
		return ground_water_potable_status;
	}

	/**
	 * @param ground_water_potable_status the ground_water_potable_status to set
	 */
	public void setGround_water_potable_status(String ground_water_potable_status) {
		this.ground_water_potable_status = ground_water_potable_status;
	}

	/**
	 * @return the preventive_measures_adopted
	 */
	public String getPreventive_measures_adopted() {
		return preventive_measures_adopted;
	}

	/**
	 * @param preventive_measures_adopted the preventive_measures_adopted to set
	 */
	public void setPreventive_measures_adopted(String preventive_measures_adopted) {
		this.preventive_measures_adopted = preventive_measures_adopted;
	}

	/**
	 * @return the being_operated_by
	 */
	public String getBeing_operated_by() {
		return being_operated_by;
	}

	/**
	 * @param being_operated_by the being_operated_by to set
	 */
	public void setBeing_operated_by(String being_operated_by) {
		this.being_operated_by = being_operated_by;
	}

	/**
	 * @return the capacity_of_plant
	 */
	public String getCapacity_of_plant() {
		return capacity_of_plant;
	}

	/**
	 * @param capacity_of_plant the capacity_of_plant to set
	 */
	public void setCapacity_of_plant(String capacity_of_plant) {
		this.capacity_of_plant = capacity_of_plant;
	}

	

	/**
	 * @return the volume_of_water_daily_basis
	 */
	public String getVolume_of_water_daily_basis() {
		return volume_of_water_daily_basis;
	}

	/**
	 * @param volume_of_water_daily_basis the volume_of_water_daily_basis to set
	 */
	public void setVolume_of_water_daily_basis(String volume_of_water_daily_basis) {
		this.volume_of_water_daily_basis = volume_of_water_daily_basis;
	}

	/**
	 * @return the disposal_of_reject_water
	 */
	public String getDisposal_of_reject_water() {
		return disposal_of_reject_water;
	}

	/**
	 * @param disposal_of_reject_water the disposal_of_reject_water to set
	 */
	public void setDisposal_of_reject_water(String disposal_of_reject_water) {
		this.disposal_of_reject_water = disposal_of_reject_water;
	}

	/**
	 * @return the penetration_in_percentage
	 */
	public String getPenetration_in_percentage() {
		return penetration_in_percentage;
	}

	/**
	 * @param penetration_in_percentage the penetration_in_percentage to set
	 */
	public void setPenetration_in_percentage(String penetration_in_percentage) {
		this.penetration_in_percentage = penetration_in_percentage;
	}

	/**
	 * @return the present_rate_of_user_charges
	 */
	public String getPresent_rate_of_user_charges() {
		return present_rate_of_user_charges;
	}

	/**
	 * @param present_rate_of_user_charges the present_rate_of_user_charges to set
	 */
	public void setPresent_rate_of_user_charges(String present_rate_of_user_charges) {
		this.present_rate_of_user_charges = present_rate_of_user_charges;
	}

	/**
	 * @return the seperate_sanctioned_load
	 */
	public String getSeperate_sanctioned_load() {
		return seperate_sanctioned_load;
	}

	/**
	 * @param seperate_sanctioned_load the seperate_sanctioned_load to set
	 */
	public void setSeperate_sanctioned_load(String seperate_sanctioned_load) {
		this.seperate_sanctioned_load = seperate_sanctioned_load;
	}

	/**
	 * @return the seperate_pending_eletric_bill31032017
	 */
	public String getSeperate_pending_eletric_bill31032017() {
		return seperate_pending_eletric_bill31032017;
	}

	/**
	 * @param seperate_pending_eletric_bill31032017 the seperate_pending_eletric_bill31032017 to set
	 */
	public void setSeperate_pending_eletric_bill31032017(String seperate_pending_eletric_bill31032017) {
		this.seperate_pending_eletric_bill31032017 = seperate_pending_eletric_bill31032017;
	}

	/**
	 * @return the average_monthly_bill_of_treatment_plant
	 */
	public String getAverage_monthly_bill_of_treatment_plant() {
		return average_monthly_bill_of_treatment_plant;
	}

	/**
	 * @param average_monthly_bill_of_treatment_plant the average_monthly_bill_of_treatment_plant to set
	 */
	public void setAverage_monthly_bill_of_treatment_plant(String average_monthly_bill_of_treatment_plant) {
		this.average_monthly_bill_of_treatment_plant = average_monthly_bill_of_treatment_plant;
	}

	/**
	 * @return the sew_connectio_sc_households
	 */
	public String getSew_connectio_sc_households() {
		return sew_connectio_sc_households;
	}

	/**
	 * @param sew_connectio_sc_households the sew_connectio_sc_households to set
	 */
	public void setSew_connectio_sc_households(String sew_connectio_sc_households) {
		this.sew_connectio_sc_households = sew_connectio_sc_households;
	}

	/**
	 * @return the water_connectio_sc_households
	 */
	public String getWater_connectio_sc_households() {
		return water_connectio_sc_households;
	}

	/**
	 * @param water_connectio_sc_households the water_connectio_sc_households to set
	 */
	public void setWater_connectio_sc_households(String water_connectio_sc_households) {
		this.water_connectio_sc_households = water_connectio_sc_households;
	}

	/**
	 * @return the female_gen_population
	 */
	public String getFemale_gen_population() {
		return female_gen_population;
	}

	/**
	 * @param female_gen_population the female_gen_population to set
	 */
	public void setFemale_gen_population(String female_gen_population) {
		this.female_gen_population = female_gen_population;
	}

	/**
	 * @return the female_sc_population
	 */
	public String getFemale_sc_population() {
		return female_sc_population;
	}

	/**
	 * @param female_sc_population the female_sc_population to set
	 */
	public void setFemale_sc_population(String female_sc_population) {
		this.female_sc_population = female_sc_population;
	}

	/**
	 * @return the sewrahabitation_distance_from_water_supply_schge
	 */
	public String getSewrahabitation_distance_from_water_supply_schge() {
		return sewrahabitation_distance_from_water_supply_schge;
	}

	/**
	 * @param sewrahabitation_distance_from_water_supply_schge the sewrahabitation_distance_from_water_supply_schge to set
	 */
	public void setSewrahabitation_distance_from_water_supply_schge(
			String sewrahabitation_distance_from_water_supply_schge) {
		this.sewrahabitation_distance_from_water_supply_schge = sewrahabitation_distance_from_water_supply_schge;
	}

	/**
	 * @return the pipeline_ac
	 */
	public String getPipeline_ac() {
		return pipeline_ac;
	}

	/**
	 * @param pipeline_ac the pipeline_ac to set
	 */
	public void setPipeline_ac(String pipeline_ac) {
		this.pipeline_ac = pipeline_ac;
	}

	/**
	 * @return the pipeline_ms_di_ci
	 */
	public String getPipeline_ms_di_ci() {
		return pipeline_ms_di_ci;
	}

	/**
	 * @param pipeline_ms_di_ci the pipeline_ms_di_ci to set
	 */
	public void setPipeline_ms_di_ci(String pipeline_ms_di_ci) {
		this.pipeline_ms_di_ci = pipeline_ms_di_ci;
	}

	/**
	 * @return the pipeline_gi
	 */
	public String getPipeline_gi() {
		return pipeline_gi;
	}

	/**
	 * @param pipeline_gi the pipeline_gi to set
	 */
	public void setPipeline_gi(String pipeline_gi) {
		this.pipeline_gi = pipeline_gi;
	}

	/**
	 * @return the pipeline_pvc
	 */
	public String getPipeline_pvc() {
		return pipeline_pvc;
	}

	/**
	 * @param pipeline_pvc the pipeline_pvc to set
	 */
	public void setPipeline_pvc(String pipeline_pvc) {
		this.pipeline_pvc = pipeline_pvc;
	}

	/**
	 * @return the functional_distribution_percentage
	 */
	public String getFunctional_distribution_percentage() {
		return functional_distribution_percentage;
	}

	/**
	 * @param functional_distribution_percentage the functional_distribution_percentage to set
	 */
	public void setFunctional_distribution_percentage(String functional_distribution_percentage) {
		this.functional_distribution_percentage = functional_distribution_percentage;
	}

	/**
	 * @return the household_water_connection
	 */
	public String getHousehold_water_connection() {
		return household_water_connection;
	}

	/**
	 * @param household_water_connection the household_water_connection to set
	 */
	public void setHousehold_water_connection(String household_water_connection) {
		this.household_water_connection = household_water_connection;
	}

	/**
	 * @return the watered_connection
	 */
	public String getWatered_connection() {
		return watered_connection;
	}

	/**
	 * @param watered_connection the watered_connection to set
	 */
	public void setWatered_connection(String watered_connection) {
		this.watered_connection = watered_connection;
	}

	/**
	 * @return the metered_connection
	 */
	public String getMetered_connection() {
		return metered_connection;
	}

	/**
	 * @param metered_connection the metered_connection to set
	 */
	public void setMetered_connection(String metered_connection) {
		this.metered_connection = metered_connection;
	}

	/**
	 * @return the metered_supply_recovery
	 */
	public String getMetered_supply_recovery() {
		return metered_supply_recovery;
	}

	/**
	 * @param metered_supply_recovery the metered_supply_recovery to set
	 */
	public void setMetered_supply_recovery(String metered_supply_recovery) {
		this.metered_supply_recovery = metered_supply_recovery;
	}

	/**
	 * @return the flat_rate_charges_per_month
	 */
	public String getFlat_rate_charges_per_month() {
		return flat_rate_charges_per_month;
	}

	/**
	 * @param flat_rate_charges_per_month the flat_rate_charges_per_month to set
	 */
	public void setFlat_rate_charges_per_month(String flat_rate_charges_per_month) {
		this.flat_rate_charges_per_month = flat_rate_charges_per_month;
	}

	/**
	 * @return the arrear_of_recovery_with_consumers_as01042017
	 */
	public String getArrear_of_recovery_with_consumers_as01042017() {
		return arrear_of_recovery_with_consumers_as01042017;
	}

	/**
	 * @param arrear_of_recovery_with_consumers_as01042017 the arrear_of_recovery_with_consumers_as01042017 to set
	 */
	public void setArrear_of_recovery_with_consumers_as01042017(String arrear_of_recovery_with_consumers_as01042017) {
		this.arrear_of_recovery_with_consumers_as01042017 = arrear_of_recovery_with_consumers_as01042017;
	}

	/**
	 * @return the scheme_functional
	 */
	public String getScheme_functional() {
		return scheme_functional;
	}

	/**
	 * @param scheme_functional the scheme_functional to set
	 */
	public void setScheme_functional(String scheme_functional) {
		this.scheme_functional = scheme_functional;
	}

	/**
	 * @return the scheme_nonfunctional
	 */
	public String getScheme_nonfunctional() {
		return scheme_nonfunctional;
	}

	/**
	 * @param scheme_nonfunctional the scheme_nonfunctional to set
	 */
	public void setScheme_nonfunctional(String scheme_nonfunctional) {
		this.scheme_nonfunctional = scheme_nonfunctional;
	}

	/**
	 * @return the scheme_nonfunctional_date
	 */
	public Date getScheme_nonfunctional_date() {
		return scheme_nonfunctional_date;
	}

	/**
	 * @param scheme_nonfunctional_date the scheme_nonfunctional_date to set
	 */
	public void setScheme_nonfunctional_date(Date scheme_nonfunctional_date) {
		this.scheme_nonfunctional_date = scheme_nonfunctional_date;
	}

	/**
	 * @return the prog_id
	 */
	public String getProg_id() {
		return prog_id;
	}

	/**
	 * @return the date_of_commissioning
	 */
	public Date getDate_of_commissioning() {
		return date_of_commissioning;
	}

	/**
	 * @param date_of_commissioning the date_of_commissioning to set
	 */
	public void setDate_of_commissioning(Date date_of_commissioning) {
		this.date_of_commissioning = date_of_commissioning;
	}

	/**
	 * @param prog_id the prog_id to set
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}

	/**
	 * @return the service_level
	 */
	public String getService_level() {
		return service_level;
	}

	/**
	 * @param service_level the service_level to set
	 */
	public void setService_level(String service_level) {
		this.service_level = service_level;
	}

	

	/**
	 * @return the scheme_upgraded
	 */
	public Date getScheme_upgraded() {
		return scheme_upgraded;
	}

	/**
	 * @param scheme_upgraded the scheme_upgraded to set
	 */
	public void setScheme_upgraded(Date scheme_upgraded) {
		this.scheme_upgraded = scheme_upgraded;
	}

	/**
	 * @return the programm_imp
	 */
	public String getProgramm_imp() {
		return programm_imp;
	}

	/**
	 * @param programm_imp the programm_imp to set
	 */
	public void setProgramm_imp(String programm_imp) {
		this.programm_imp = programm_imp;
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
	 * @return the scPopulation
	 */
	public String getScPopulation() {
		return scPopulation;
	}

	/**
	 * @param scPopulation the scPopulation to set
	 */
	public void setScPopulation(String scPopulation) {
		this.scPopulation = scPopulation;
	}

	/**
	 * @return the totalPopulation
	 */
	public String getTotalPopulation() {
		return totalPopulation;
	}

	/**
	 * @param totalPopulation the totalPopulation to set
	 */
	public void setTotalPopulation(String totalPopulation) {
		this.totalPopulation = totalPopulation;
	}

	/**
	 * @return the scHouseholds
	 */
	public String getScHouseholds() {
		return scHouseholds;
	}

	/**
	 * @param scHouseholds the scHouseholds to set
	 */
	public void setScHouseholds(String scHouseholds) {
		this.scHouseholds = scHouseholds;
	}

	/**
	 * @return the totalHouseHolds
	 */
	public String getTotalHouseHolds() {
		return totalHouseHolds;
	}

	/**
	 * @param totalHouseHolds the totalHouseHolds to set
	 */
	public void setTotalHouseHolds(String totalHouseHolds) {
		this.totalHouseHolds = totalHouseHolds;
	}

	/**
	 * @return the parConsName
	 */
	public String getParConsName() {
		return parConsName;
	}

	/**
	 * @param parConsName the parConsName to set
	 */
	public void setParConsName(String parConsName) {
		this.parConsName = parConsName;
	}

	/**
	 * @return the noOfHabitation
	 */
	public String getNoOfHabitation() {
		return noOfHabitation;
	}

	/**
	 * @param noOfHabitation the noOfHabitation to set
	 */
	public void setNoOfHabitation(String noOfHabitation) {
		this.noOfHabitation = noOfHabitation;
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
	 * @return the hadBstNo
	 */
	public String getHadBstNo() {
		return hadBstNo;
	}

	/**
	 * @param hadBstNo the hadBstNo to set
	 */
	public void setHadBstNo(String hadBstNo) {
		this.hadBstNo = hadBstNo;
	}

	/**
	 * @return the schemeName
	 */
	public String getSchemeName() {
		return schemeName;
	}

	/**
	 * @param schemeName the schemeName to set
	 */
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	/**
	 * @return the schemeSource
	 */
	public String getSchemeSource() {
		return schemeSource;
	}

	/**
	 * @param schemeSource the schemeSource to set
	 */
	public void setSchemeSource(String schemeSource) {
		this.schemeSource = schemeSource;
	}

	/**
	 * @return the name_of_habitations
	 */
	public String getName_of_habitations() {
		return name_of_habitations;
	}

	/**
	 * @param name_of_habitations the name_of_habitations to set
	 */
	public void setName_of_habitations(String name_of_habitations) {
		this.name_of_habitations = name_of_habitations;
	}

	/**
	 * @return the panchayat_ghar
	 */
	public String getPanchayat_ghar() {
		return panchayat_ghar;
	}

	/**
	 * @param panchayat_ghar the panchayat_ghar to set
	 */
	public void setPanchayat_ghar(String panchayat_ghar) {
		this.panchayat_ghar = panchayat_ghar;
	}

	/**
	 * @return the community_centre_or_dharamshala
	 */
	public String getCommunity_centre_or_dharamshala() {
		return community_centre_or_dharamshala;
	}

	/**
	 * @param community_centre_or_dharamshala the community_centre_or_dharamshala to set
	 */
	public void setCommunity_centre_or_dharamshala(String community_centre_or_dharamshala) {
		this.community_centre_or_dharamshala = community_centre_or_dharamshala;
	}

	

	/**
	 * @return the angarwaries
	 */
	public String getAngarwaries() {
		return angarwaries;
	}

	/**
	 * @param angarwaries the angarwaries to set
	 */
	public void setAngarwaries(String angarwaries) {
		this.angarwaries = angarwaries;
	}

	/**
	 * @return the no_of_angarwaries
	 */
	public String getNo_of_angarwaries() {
		return no_of_angarwaries;
	}

	/**
	 * @param no_of_angarwaries the no_of_angarwaries to set
	 */
	public void setNo_of_angarwaries(String no_of_angarwaries) {
		this.no_of_angarwaries = no_of_angarwaries;
	}

	/**
	 * @return the angarwaries_buildings_type
	 *//*
	public String getAngarwaries_buildings_type() {
		return angarwaries_buildings_type;
	}

	*//**
	 * @param angarwaries_buildings_type the angarwaries_buildings_type to set
	 *//*
	public void setAngarwaries_buildings_type(String angarwaries_buildings_type) {
		this.angarwaries_buildings_type = angarwaries_buildings_type;
	}*/

	/**
	 * @return the govt_schools
	 */
	public String getGovt_schools() {
		return govt_schools;
	}

	/**
	 * @param govt_schools the govt_schools to set
	 */
	public void setGovt_schools(String govt_schools) {
		this.govt_schools = govt_schools;
	}

	/**
	 * @return the no_of_govt_schools
	 */
	public String getNo_of_govt_schools() {
		return no_of_govt_schools;
	}

	/**
	 * @param no_of_govt_schools the no_of_govt_schools to set
	 */
	public void setNo_of_govt_schools(String no_of_govt_schools) {
		this.no_of_govt_schools = no_of_govt_schools;
	}

	/**
	 * @return the health_centre
	 */
	public String getHealth_centre() {
		return health_centre;
	}

	/**
	 * @param health_centre the health_centre to set
	 */
	public void setHealth_centre(String health_centre) {
		this.health_centre = health_centre;
	}

	/**
	 * @return the no_of_health_centres
	 */
	public String getNo_of_health_centres() {
		return no_of_health_centres;
	}

	/**
	 * @param no_of_health_centres the no_of_health_centres to set
	 */
	public void setNo_of_health_centres(String no_of_health_centres) {
		this.no_of_health_centres = no_of_health_centres;
	}

	/**
	 * @return the village_ponds
	 */
	public String getVillage_ponds() {
		return village_ponds;
	}

	/**
	 * @param village_ponds the village_ponds to set
	 */
	public void setVillage_ponds(String village_ponds) {
		this.village_ponds = village_ponds;
	}

	/**
	 * @return the no_of_village_ponds
	 */
	public String getNo_of_village_ponds() {
		return no_of_village_ponds;
	}

	/**
	 * @param no_of_village_ponds the no_of_village_ponds to set
	 */
	public void setNo_of_village_ponds(String no_of_village_ponds) {
		this.no_of_village_ponds = no_of_village_ponds;
	}

	/**
	 * @return the area_in_square_meters
	 */
	public String getArea_in_square_meters() {
		return area_in_square_meters;
	}

	/**
	 * @param area_in_square_meters the area_in_square_meters to set
	 */
	public void setArea_in_square_meters(String area_in_square_meters) {
		this.area_in_square_meters = area_in_square_meters;
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
	 * @return the scheme_expenditure
	 */
	public String getScheme_expenditure() {
		return scheme_expenditure;
	}

	/**
	 * @param scheme_expenditure the scheme_expenditure to set
	 */
	public void setScheme_expenditure(String scheme_expenditure) {
		this.scheme_expenditure = scheme_expenditure;
	}

	/**
	 * @return the present_service_level
	 */
	public String getPresent_service_level() {
		return present_service_level;
	}

	/**
	 * @param present_service_level the present_service_level to set
	 */
	public void setPresent_service_level(String present_service_level) {
		this.present_service_level = present_service_level;
	}

	/**
	 * @return the depth_of_tubewell
	 */
	public String getDepth_of_tubewell() {
		return depth_of_tubewell;
	}

	/**
	 * @param depth_of_tubewell the depth_of_tubewell to set
	 */
	public void setDepth_of_tubewell(String depth_of_tubewell) {
		this.depth_of_tubewell = depth_of_tubewell;
	}

	/**
	 * @return the size_of_tubewell
	 */
	public String getSize_of_tubewell() {
		return size_of_tubewell;
	}

	/**
	 * @param size_of_tubewell the size_of_tubewell to set
	 */
	public void setSize_of_tubewell(String size_of_tubewell) {
		this.size_of_tubewell = size_of_tubewell;
	}

	/**
	 * @return the year_of_drilling
	 */
	public Date getYear_of_drilling() {
		return year_of_drilling;
	}

	/**
	 * @param year_of_drilling the year_of_drilling to set
	 */
	public void setYear_of_drilling(Date year_of_drilling) {
		this.year_of_drilling = year_of_drilling;
	}

	/**
	 * @return the original_discharge_at_time_of_commissioning
	 */
	public String getOriginal_discharge_at_time_of_commissioning() {
		return original_discharge_at_time_of_commissioning;
	}

	/**
	 * @param original_discharge_at_time_of_commissioning the original_discharge_at_time_of_commissioning to set
	 */
	public void setOriginal_discharge_at_time_of_commissioning(String original_discharge_at_time_of_commissioning) {
		this.original_discharge_at_time_of_commissioning = original_discharge_at_time_of_commissioning;
	}

	/**
	 * @return the present_discharge
	 */
	public String getPresent_discharge() {
		return present_discharge;
	}

	/**
	 * @param present_discharge the present_discharge to set
	 */
	public void setPresent_discharge(String present_discharge) {
		this.present_discharge = present_discharge;
	}

	/**
	 * @return the present_spring_level
	 */
	public String getPresent_spring_level() {
		return present_spring_level;
	}

	/**
	 * @param present_spring_level the present_spring_level to set
	 */
	public void setPresent_spring_level(String present_spring_level) {
		this.present_spring_level = present_spring_level;
	}

	/**
	 * @return the installation_of_new_machinery
	 */
	public Date getInstallation_of_new_machinery() {
		return installation_of_new_machinery;
	}

	/**
	 * @param installation_of_new_machinery the installation_of_new_machinery to set
	 */
	public void setInstallation_of_new_machinery(Date installation_of_new_machinery) {
		this.installation_of_new_machinery = installation_of_new_machinery;
	}

	/**
	 * @return the capacity_of_machinery
	 */
	public String getCapacity_of_machinery() {
		return capacity_of_machinery;
	}

	/**
	 * @param capacity_of_machinery the capacity_of_machinery to set
	 */
	public void setCapacity_of_machinery(String capacity_of_machinery) {
		this.capacity_of_machinery = capacity_of_machinery;
	}

	/**
	 * @return the inlet_channel_type
	 */
	public String getInlet_channel_type() {
		return inlet_channel_type;
	}

	/**
	 * @param inlet_channel_type the inlet_channel_type to set
	 */
	public void setInlet_channel_type(String inlet_channel_type) {
		this.inlet_channel_type = inlet_channel_type;
	}

	

	/**
	 * @return the inlet_channel_lenght
	 */
	public String getInlet_channel_lenght() {
		return inlet_channel_lenght;
	}

	/**
	 * @param inlet_channel_lenght the inlet_channel_lenght to set
	 */
	public void setInlet_channel_lenght(String inlet_channel_lenght) {
		this.inlet_channel_lenght = inlet_channel_lenght;
	}

	/**
	 * @return the pipe_type
	 */
	public String getPipe_type() {
		return pipe_type;
	}

	/**
	 * @param pipe_type the pipe_type to set
	 */
	public void setPipe_type(String pipe_type) {
		this.pipe_type = pipe_type;
	}

	/**
	 * @return the capacity_of_ss_tank
	 */
	public String getCapacity_of_ss_tank() {
		return capacity_of_ss_tank;
	}

	/**
	 * @param capacity_of_ss_tank the capacity_of_ss_tank to set
	 */
	public void setCapacity_of_ss_tank(String capacity_of_ss_tank) {
		this.capacity_of_ss_tank = capacity_of_ss_tank;
	}

	/**
	 * @return the capacity_of_hl_tank
	 */
	public String getCapacity_of_hl_tank() {
		return capacity_of_hl_tank;
	}

	/**
	 * @param capacity_of_hl_tank the capacity_of_hl_tank to set
	 */
	public void setCapacity_of_hl_tank(String capacity_of_hl_tank) {
		this.capacity_of_hl_tank = capacity_of_hl_tank;
	}

	/**
	 * @return the capacity_of_cw_tank
	 */
	public String getCapacity_of_cw_tank() {
		return capacity_of_cw_tank;
	}

	/**
	 * @param capacity_of_cw_tank the capacity_of_cw_tank to set
	 */
	public void setCapacity_of_cw_tank(String capacity_of_cw_tank) {
		this.capacity_of_cw_tank = capacity_of_cw_tank;
	}

	/**
	 * @return the filtertion_type
	 */
	public String getFiltertion_type() {
		return filtertion_type;
	}

	/**
	 * @param filtertion_type the filtertion_type to set
	 */
	public void setFiltertion_type(String filtertion_type) {
		this.filtertion_type = filtertion_type;
	}

	/**
	 * @return the filtertion_no
	 */
	public String getFiltertion_no() {
		return filtertion_no;
	}

	/**
	 * @param filtertion_no the filtertion_no to set
	 */
	public void setFiltertion_no(String filtertion_no) {
		this.filtertion_no = filtertion_no;
	}

	/**
	 * @return the filtertion_capacity
	 */
	public String getFiltertion_capacity() {
		return filtertion_capacity;
	}

	/**
	 * @param filtertion_capacity the filtertion_capacity to set
	 */
	public void setFiltertion_capacity(String filtertion_capacity) {
		this.filtertion_capacity = filtertion_capacity;
	}

	/**
	 * @return the capacity_of_raw_water
	 */
	public String getCapacity_of_raw_water() {
		return capacity_of_raw_water;
	}

	/**
	 * @param capacity_of_raw_water the capacity_of_raw_water to set
	 */
	public void setCapacity_of_raw_water(String capacity_of_raw_water) {
		this.capacity_of_raw_water = capacity_of_raw_water;
	}

	/**
	 * @return the capacity_of_clear_water
	 */
	public String getCapacity_of_clear_water() {
		return capacity_of_clear_water;
	}

	/**
	 * @param capacity_of_clear_water the capacity_of_clear_water to set
	 */
	public void setCapacity_of_clear_water(String capacity_of_clear_water) {
		this.capacity_of_clear_water = capacity_of_clear_water;
	}

	/**
	 * @return the no_of_ohsr
	 */
	public String getNo_of_ohsr() {
		return no_of_ohsr;
	}

	/**
	 * @param no_of_ohsr the no_of_ohsr to set
	 */
	public void setNo_of_ohsr(String no_of_ohsr) {
		this.no_of_ohsr = no_of_ohsr;
	}

	/**
	 * @return the ohsr_construction_date
	 */
	public Date getOhsr_construction_date() {
		return ohsr_construction_date;
	}

	/**
	 * @param ohsr_construction_date the ohsr_construction_date to set
	 */
	public void setOhsr_construction_date(Date ohsr_construction_date) {
		this.ohsr_construction_date = ohsr_construction_date;
	}

	/**
	 * @return the ohsr_capcity
	 */
	public String getOhsr_capcity() {
		return ohsr_capcity;
	}

	/**
	 * @param ohsr_capcity the ohsr_capcity to set
	 */
	public void setOhsr_capcity(String ohsr_capcity) {
		this.ohsr_capcity = ohsr_capcity;
	}

	/**
	 * @return the ohsr_full_supply_level
	 */
	public String getOhsr_full_supply_level() {
		return ohsr_full_supply_level;
	}

	/**
	 * @param ohsr_full_supply_level the ohsr_full_supply_level to set
	 */
	public void setOhsr_full_supply_level(String ohsr_full_supply_level) {
		this.ohsr_full_supply_level = ohsr_full_supply_level;
	}

	/**
	 * @return the ohsr_working_condition
	 */
	public String getOhsr_working_condition() {
		return ohsr_working_condition;
	}

	/**
	 * @param ohsr_working_condition the ohsr_working_condition to set
	 */
	public void setOhsr_working_condition(String ohsr_working_condition) {
		this.ohsr_working_condition = ohsr_working_condition;
	}

	/**
	 * @return the ohsr_working_condition_if_no
	 */
	public String getOhsr_working_condition_if_no() {
		return ohsr_working_condition_if_no;
	}

	/**
	 * @param ohsr_working_condition_if_no the ohsr_working_condition_if_no to set
	 */
	public void setOhsr_working_condition_if_no(String ohsr_working_condition_if_no) {
		this.ohsr_working_condition_if_no = ohsr_working_condition_if_no;
	}

	/**
	 * @return the dismantling_received
	 */
	public String getDismantling_received() {
		return dismantling_received;
	}

	/**
	 * @param dismantling_received the dismantling_received to set
	 */
	public void setDismantling_received(String dismantling_received) {
		this.dismantling_received = dismantling_received;
	}

	/**
	 * @return the disinfection_type
	 */
	public String getDisinfection_type() {
		return disinfection_type;
	}

	/**
	 * @param disinfection_type the disinfection_type to set
	 */
	public void setDisinfection_type(String disinfection_type) {
		this.disinfection_type = disinfection_type;
	}

	/**
	 * @return the disinfection_instalation_time
	 */
	public Date getDisinfection_instalation_time() {
		return disinfection_instalation_time;
	}

	/**
	 * @param disinfection_instalation_time the disinfection_instalation_time to set
	 */
	public void setDisinfection_instalation_time(Date disinfection_instalation_time) {
		this.disinfection_instalation_time = disinfection_instalation_time;
	}

	/**
	 * @return the disinfection_present_status
	 */
	public String getDisinfection_present_status() {
		return disinfection_present_status;
	}

	/**
	 * @param disinfection_present_status the disinfection_present_status to set
	 */
	public void setDisinfection_present_status(String disinfection_present_status) {
		this.disinfection_present_status = disinfection_present_status;
	}

	/**
	 * @return the scheme_operated_by
	 */
	public String getScheme_operated_by() {
		return scheme_operated_by;
	}

	/**
	 * @param scheme_operated_by the scheme_operated_by to set
	 */
	public void setScheme_operated_by(String scheme_operated_by) {
		this.scheme_operated_by = scheme_operated_by;
	}

	/**
	 * @return the dwss_operated_arrangement
	 */
	public String getDwss_operated_arrangement() {
		return dwss_operated_arrangement;
	}

	/**
	 * @param dwss_operated_arrangement the dwss_operated_arrangement to set
	 */
	public void setDwss_operated_arrangement(String dwss_operated_arrangement) {
		this.dwss_operated_arrangement = dwss_operated_arrangement;
	}

	/**
	 * @return the sanctioned_load
	 */
	public String getSanctioned_load() {
		return sanctioned_load;
	}

	/**
	 * @param sanctioned_load the sanctioned_load to set
	 */
	public void setSanctioned_load(String sanctioned_load) {
		this.sanctioned_load = sanctioned_load;
	}

	/**
	 * @return the pending__bill_31032017
	 */
	public String getPending__bill_31032017() {
		return pending__bill_31032017;
	}

	/**
	 * @param pending__bill_31032017 the pending__bill_31032017 to set
	 */
	public void setPending__bill_31032017(String pending__bill_31032017) {
		this.pending__bill_31032017 = pending__bill_31032017;
	}

	/**
	 * @return the pending__bill_30062017
	 */
	public String getPending__bill_30062017() {
		return pending__bill_30062017;
	}

	/**
	 * @param pending__bill_30062017 the pending__bill_30062017 to set
	 */
	public void setPending__bill_30062017(String pending__bill_30062017) {
		this.pending__bill_30062017 = pending__bill_30062017;
	}

	

	/**
	 * @return the avg_month_billof_wss
	 */
	public String getAvg_month_billof_wss() {
		return avg_month_billof_wss;
	}

	/**
	 * @param avg_month_billof_wss the avg_month_billof_wss to set
	 */
	public void setAvg_month_billof_wss(String avg_month_billof_wss) {
		this.avg_month_billof_wss = avg_month_billof_wss;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PerformaMasterBean [villageId=" + villageId + ", villageName=" + villageName + ", program_name="
				+ program_name + ", govt_building=" + govt_building + ", private_building=" + private_building
				+ ", sv_mv=" + sv_mv + ", habitationType=" + habitationType + ", scPopulation=" + scPopulation
				+ ", totalPopulation=" + totalPopulation + ", scHouseholds=" + scHouseholds + ", totalHouseHolds="
				+ totalHouseHolds + ", parConsName=" + parConsName + ", noOfHabitation=" + noOfHabitation
				+ ", schemeId=" + schemeId + ", hadBstNo=" + hadBstNo + ", schemeName=" + schemeName + ", schemeSource="
				+ schemeSource + ", name_of_habitations=" + name_of_habitations + ", panchayat_ghar=" + panchayat_ghar
				+ ", community_centre_or_dharamshala=" + community_centre_or_dharamshala + ", angarwaries="
				+ angarwaries + ", no_of_angarwaries=" + no_of_angarwaries + ", govt_schools=" + govt_schools
				+ ", no_of_govt_schools=" + no_of_govt_schools + ", health_centre=" + health_centre
				+ ", no_of_health_centres=" + no_of_health_centres + ", village_ponds=" + village_ponds
				+ ", no_of_village_ponds=" + no_of_village_ponds + ", area_in_square_meters=" + area_in_square_meters
				+ ", sewrage=" + sewrage + ", date_of_commissioning=" + date_of_commissioning + ", prog_id=" + prog_id
				+ ", service_level=" + service_level + ", scheme_upgraded=" + scheme_upgraded + ", programm_imp="
				+ programm_imp + ", scheme_expenditure=" + scheme_expenditure + ", present_service_level="
				+ present_service_level + ", depth_of_tubewell=" + depth_of_tubewell + ", size_of_tubewell="
				+ size_of_tubewell + ", year_of_drilling=" + year_of_drilling
				+ ", original_discharge_at_time_of_commissioning=" + original_discharge_at_time_of_commissioning
				+ ", present_discharge=" + present_discharge + ", present_spring_level=" + present_spring_level
				+ ", installation_of_new_machinery=" + installation_of_new_machinery + ", capacity_of_machinery="
				+ capacity_of_machinery + ", inlet_channel_type=" + inlet_channel_type + ", inlet_channel_lenght="
				+ inlet_channel_lenght + ", pipe_type=" + pipe_type + ", capacity_of_ss_tank=" + capacity_of_ss_tank
				+ ", capacity_of_hl_tank=" + capacity_of_hl_tank + ", capacity_of_cw_tank=" + capacity_of_cw_tank
				+ ", filtertion_type=" + filtertion_type + ", filtertion_no=" + filtertion_no + ", filtertion_capacity="
				+ filtertion_capacity + ", capacity_of_raw_water=" + capacity_of_raw_water
				+ ", capacity_of_clear_water=" + capacity_of_clear_water + ", no_of_ohsr=" + no_of_ohsr
				+ ", ohsr_construction_date=" + ohsr_construction_date + ", ohsr_capcity=" + ohsr_capcity
				+ ", ohsr_full_supply_level=" + ohsr_full_supply_level + ", ohsr_working_condition="
				+ ohsr_working_condition + ", ohsr_working_condition_if_no=" + ohsr_working_condition_if_no
				+ ", dismantling_received=" + dismantling_received + ", disinfection_type=" + disinfection_type
				+ ", disinfection_instalation_time=" + disinfection_instalation_time + ", disinfection_present_status="
				+ disinfection_present_status + ", scheme_operated_by=" + scheme_operated_by
				+ ", dwss_operated_arrangement=" + dwss_operated_arrangement + ", sanctioned_load=" + sanctioned_load
				+ ", pending__bill_31032017=" + pending__bill_31032017 + ", pending__bill_30062017="
				+ pending__bill_30062017 + ", avg_month_billof_wss=" + avg_month_billof_wss + ", pipeline_ac="
				+ pipeline_ac + ", pipeline_ms_di_ci=" + pipeline_ms_di_ci + ", pipeline_gi=" + pipeline_gi
				+ ", pipeline_pvc=" + pipeline_pvc + ", functional_distribution_percentage="
				+ functional_distribution_percentage + ", household_water_connection=" + household_water_connection
				+ ", watered_connection=" + watered_connection + ", metered_connection=" + metered_connection
				+ ", metered_supply_recovery=" + metered_supply_recovery + ", flat_rate_charges_per_month="
				+ flat_rate_charges_per_month + ", arrear_of_recovery_with_consumers_as01042017="
				+ arrear_of_recovery_with_consumers_as01042017 + ", scheme_functional=" + scheme_functional
				+ ", scheme_nonfunctional=" + scheme_nonfunctional + ", scheme_nonfunctional_date="
				+ scheme_nonfunctional_date + ", sew_connectio_sc_households=" + sew_connectio_sc_households
				+ ", water_connectio_sc_households=" + water_connectio_sc_households + ", female_gen_population="
				+ female_gen_population + ", female_sc_population=" + female_sc_population
				+ ", sewrahabitation_distance_from_water_supply_schge="
				+ sewrahabitation_distance_from_water_supply_schge + ", ground_water_potable_no="
				+ ground_water_potable_no + ", ground_water_potable_status=" + ground_water_potable_status
				+ ", preventive_measures_adopted=" + preventive_measures_adopted + ", being_operated_by="
				+ being_operated_by + ", capacity_of_plant=" + capacity_of_plant + ", date_of_installation="
				+ date_of_installation + ", volume_of_water_daily_basis=" + volume_of_water_daily_basis
				+ ", disposal_of_reject_water=" + disposal_of_reject_water + ", penetration_in_percentage="
				+ penetration_in_percentage + ", present_rate_of_user_charges=" + present_rate_of_user_charges
				+ ", seperate_sanctioned_load=" + seperate_sanctioned_load + ", seperate_pending_eletric_bill31032017="
				+ seperate_pending_eletric_bill31032017 + ", average_monthly_bill_of_treatment_plant="
				+ average_monthly_bill_of_treatment_plant + ", independent_new_wss=" + independent_new_wss
				+ ", independent_new_wss_cost=" + independent_new_wss_cost + ", upgradation_of_existing_wss="
				+ upgradation_of_existing_wss + ", upgradation_of_existing_wss_cost=" + upgradation_of_existing_wss_cost
				+ ", instltion_wtr_treatment_plant_cost=" + instltion_wtr_treatment_plant_cost + ", source_of_wss="
				+ source_of_wss + ", shifted_to_canal_from_other=" + shifted_to_canal_from_other
				+ ", driling_of_new_tubewell_machinery_size=" + driling_of_new_tubewell_machinery_size
				+ ", driling_of_new_tubewell_machinery_depth=" + driling_of_new_tubewell_machinery_depth
				+ ", driling_of_new_tubewell_machinery_capacity=" + driling_of_new_tubewell_machinery_capacity
				+ ", driling_of_new_tubewell_machinery_cost=" + driling_of_new_tubewell_machinery_cost
				+ ", canal_based_inlet_channel_size_of_pipe=" + canal_based_inlet_channel_size_of_pipe
				+ ", canal_based_inlet_channel_length=" + canal_based_inlet_channel_length
				+ ", canal_based_s_and_s_capacity=" + canal_based_s_and_s_capacity
				+ ", canal_based_filteration_plan_type=" + canal_based_filteration_plan_type
				+ ", canal_based_filteration_plan_capacity=" + canal_based_filteration_plan_capacity
				+ ", canal_based_cost=" + canal_based_cost + ", ohsr_capacity=" + ohsr_capacity + ", ohsr_cost="
				+ ohsr_cost + ", other_structures_at_waterworks=" + other_structures_at_waterworks
				+ ", other_structures_at_waterworks_cost=" + other_structures_at_waterworks_cost
				+ ", distribution_wss_to_village_type=" + distribution_wss_to_village_type
				+ ", distribution_wss_to_village_length=" + distribution_wss_to_village_length
				+ ", distribution_wss_within_village_type=" + distribution_wss_within_village_type
				+ ", distribution_wss_within_village_length=" + distribution_wss_within_village_length
				+ ", distribution_cost=" + distribution_cost + ", no_of_connections_100_mtr="
				+ no_of_connections_100_mtr + ", no_of_mtrs_100_mtr=" + no_of_mtrs_100_mtr + ", cost_100_mtr="
				+ cost_100_mtr + ", disinfection_unit_type=" + disinfection_unit_type + ", disinfection_unit_cost="
				+ disinfection_unit_cost + ", water_treatment_plant_in_case_of_quality_village_type="
				+ water_treatment_plant_in_case_of_quality_village_type
				+ ", water_treatment_plant_in_case_of_quality_village_capacity="
				+ water_treatment_plant_in_case_of_quality_village_capacity
				+ ", water_treatment_plant_in_case_of_quality_village_cost="
				+ water_treatment_plant_in_case_of_quality_village_cost + ", bulk_water_meter_no=" + bulk_water_meter_no
				+ ", bulk_water_meter_cost=" + bulk_water_meter_cost
				+ ", extension_sanction_of_new_electric_connection_cost="
				+ extension_sanction_of_new_electric_connection_cost + ", percentage_functional_pipeline="
				+ percentage_functional_pipeline + ", ohsr_full_supply_level1=" + ohsr_full_supply_level1
				+ ", scheme_type=" + scheme_type + ", no_of_water_connection=" + no_of_water_connection + "]";
	}


	
}

