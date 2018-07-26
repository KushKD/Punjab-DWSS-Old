/**
 * 
 */
package com.prwss.mis.reports.performa.dao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

/**
 * @author bhsingh
 *
 */
@Entity
@Table(name = "draft_performa4", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class SchemeBean1 implements Serializable{

	private static final long serialVersionUID = 8719959082768089678L;

	@Id
	@GeneratedValue(generator = "draft_performa4_id4_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "draft_performa4_id4_seq", sequenceName = "prwss_main.draft_performa4_id4_seq")
	@Column(name = "id4")
	private int id;
	
	@Column(name = "scheme_id")
	private String schemeId;
	
	@Column(name = "village_id")
	private String villageId;
	
	@Column(name = "pipeline_ac")
	private Double pipelineAc;
	
	@Column(name = "pipeline_ms_di_ci")
	private Double pipelineMsDi;
	
	@Column(name = "pipeline_gi")
	private Double pipelineGi;
	
	@Column(name = "pipeline_pvc")
	private Double pipelinePvc;
	
	@Column(name = "percentage_functional_pipeline")
	private String percentage_functional_pipeline;
	
	@Column(name = "household_water_connection")
	private Double householdWaterConnection;
	
	@Column(name = "watered_connection")
	private Double wateredConnection;
	
	@Column(name = "metered_connection")
	private Double meteredConnection;
	
	@Column(name = "metered_supply_recovery")
	private String meteredRupplyRecovery;
	
	
	
	@Column(name = "flat_rate_charges_per_month")
	private Double flat_rate_charges_per_month;
	
	@Column(name = "arrear_of_recovery_with_consumers_as01042017")
	private Double arrear_of_recovery_with_consumers_as01042017;
	
	@Column(name = "scheme_functional")
	private Double scheme_functional;
	
	@Column(name = "scheme_nonfunctional")
	private String scheme_nonfunctional;
	
	@Column(name = "scheme_nonfunctional_date")
	private Date scheme_nonfunctional_date;
	
	@Column(name = "ground_water_potable_status")
	private Integer ground_water_potable_status;
	
	@Column(name = "ground_water_potable_no")
	private String ground_water_potable_no;
	
	@Column(name = "preventive_measures_adopted")
	private String preventive_measures_adopted;
	
	@Column(name = "capacity_of_plant")
	private Double capacity_of_plant;
	
	@Column(name = "date_of_installation")
	private Date date_of_installation;
	
	@Column(name = "being_operated_by")
	private String being_operated_by;
	
	@Column(name = "volume_of_water_daily_basis")
	private Double volume_of_water_daily_basis;
	
	@Column(name = "disposal_of_reject_water")
	private String disposal_of_reject_water;
	
	@Column(name = "penetration_in_percentage")
	private Double penetration_in_percentage;
	
	@Column(name = "present_rate_of_user_charges")
	private Double present_rate_of_user_charges;
	
	@Column(name = "seperate_sanctioned_load")
	private Double seperate_sanctioned_load;
	
	@Column(name = "seperate_pending_eletric_bill31032017")
	private Double seperate_pending_eletric_bill31032017;
	
	@Column(name = "average_monthly_bill_of_treatment_plant")
	private Double average_monthly_bill_of_treatment_plant;
	
/*	@Embedded
	private MISAuditBean misAuditBean;*/

	@Column(name = "any_other_desc")
	private String any_other_desc;
	
	
	
	

	public String getAny_other_desc() {
		return any_other_desc;
	}



	public void setAny_other_desc(String any_other_desc) {
		this.any_other_desc = any_other_desc;
	}



	/**
	 * @return the schemeId
	 */
	public String getSchemeId() {
		return schemeId;
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
	 * @return the pipelineAc
	 */
	public Double getPipelineAc() {
		return pipelineAc;
	}

	/**
	 * @param pipelineAc the pipelineAc to set
	 */
	public void setPipelineAc(Double pipelineAc) {
		this.pipelineAc = pipelineAc;
	}

	/**
	 * @return the pipelineMsDi
	 */
	public Double getPipelineMsDi() {
		return pipelineMsDi;
	}

	/**
	 * @param pipelineMsDi the pipelineMsDi to set
	 */
	public void setPipelineMsDi(Double pipelineMsDi) {
		this.pipelineMsDi = pipelineMsDi;
	}

	/**
	 * @return the pipelineGi
	 */
	public Double getPipelineGi() {
		return pipelineGi;
	}

	/**
	 * @param pipelineGi the pipelineGi to set
	 */
	public void setPipelineGi(Double pipelineGi) {
		this.pipelineGi = pipelineGi;
	}

	/**
	 * @return the pipelinePvc
	 */
	public Double getPipelinePvc() {
		return pipelinePvc;
	}

	/**
	 * @param pipelinePvc the pipelinePvc to set
	 */
	public void setPipelinePvc(Double pipelinePvc) {
		this.pipelinePvc = pipelinePvc;
	}

	/**
	 * @return the wateredConnection
	 */
	public Double getWateredConnection() {
		return wateredConnection;
	}

	/**
	 * @param wateredConnection the wateredConnection to set
	 */
	public void setWateredConnection(Double wateredConnection) {
		this.wateredConnection = wateredConnection;
	}

	/**
	 * @return the meteredConnection
	 */
	public Double getMeteredConnection() {
		return meteredConnection;
	}

	/**
	 * @param meteredConnection the meteredConnection to set
	 */
	public void setMeteredConnection(Double meteredConnection) {
		this.meteredConnection = meteredConnection;
	}

	/**
	 * @return the meteredRupplyRecovery
	 */
	public String getMeteredRupplyRecovery() {
		return meteredRupplyRecovery;
	}

	/**
	 * @param meteredRupplyRecovery the meteredRupplyRecovery to set
	 */
	public void setMeteredRupplyRecovery(String meteredRupplyRecovery) {
		this.meteredRupplyRecovery = meteredRupplyRecovery;
	}

	/**
	 * @return the flat_rate_charges_per_month
	 */
	public Double getFlat_rate_charges_per_month() {
		return flat_rate_charges_per_month;
	}

	/**
	 * @param flat_rate_charges_per_month the flat_rate_charges_per_month to set
	 */
	public void setFlat_rate_charges_per_month(Double flat_rate_charges_per_month) {
		this.flat_rate_charges_per_month = flat_rate_charges_per_month;
	}

	/**
	 * @return the arrear_of_recovery_with_consumers_as01042017
	 */
	public Double getArrear_of_recovery_with_consumers_as01042017() {
		return arrear_of_recovery_with_consumers_as01042017;
	}

	/**
	 * @param arrear_of_recovery_with_consumers_as01042017 the arrear_of_recovery_with_consumers_as01042017 to set
	 */
	public void setArrear_of_recovery_with_consumers_as01042017(Double arrear_of_recovery_with_consumers_as01042017) {
		this.arrear_of_recovery_with_consumers_as01042017 = arrear_of_recovery_with_consumers_as01042017;
	}

	/**
	 * @return the scheme_functional
	 */
	public Double getScheme_functional() {
		return scheme_functional;
	}

	/**
	 * @param scheme_functional the scheme_functional to set
	 */
	public void setScheme_functional(Double scheme_functional) {
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
	 * @return the householdWaterConnection
	 */
	public Double getHouseholdWaterConnection() {
		return householdWaterConnection;
	}


	/**
	 * @return the ground_water_potable_status
	 */
	public Integer getGround_water_potable_status() {
		return ground_water_potable_status;
	}



	/**
	 * @param ground_water_potable_status the ground_water_potable_status to set
	 */
	public void setGround_water_potable_status(Integer ground_water_potable_status) {
		this.ground_water_potable_status = ground_water_potable_status;
	}



	/**
	 * @return the ground_water_potable_no
	 */
	public String getGround_water_potable_no() {
		return ground_water_potable_no;
	}

	/**
	 * @param ground_water_potable_no the ground_water_potable_no to set
	 */
	public void setGround_water_potable_no(String ground_water_potable_no) {
		this.ground_water_potable_no = ground_water_potable_no;
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
	 * @return the capacity_of_plant
	 */
	public Double getCapacity_of_plant() {
		return capacity_of_plant;
	}

	/**
	 * @param capacity_of_plant the capacity_of_plant to set
	 */
	public void setCapacity_of_plant(Double capacity_of_plant) {
		this.capacity_of_plant = capacity_of_plant;
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
	 * @return the volume_of_water_daily_basis
	 */
	public Double getVolume_of_water_daily_basis() {
		return volume_of_water_daily_basis;
	}

	/**
	 * @param volume_of_water_daily_basis the volume_of_water_daily_basis to set
	 */
	public void setVolume_of_water_daily_basis(Double volume_of_water_daily_basis) {
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
	public Double getPenetration_in_percentage() {
		return penetration_in_percentage;
	}

	/**
	 * @param penetration_in_percentage the penetration_in_percentage to set
	 */
	public void setPenetration_in_percentage(Double penetration_in_percentage) {
		this.penetration_in_percentage = penetration_in_percentage;
	}

	/**
	 * @return the present_rate_of_user_charges
	 */
	public Double getPresent_rate_of_user_charges() {
		return present_rate_of_user_charges;
	}

	/**
	 * @param present_rate_of_user_charges the present_rate_of_user_charges to set
	 */
	public void setPresent_rate_of_user_charges(Double present_rate_of_user_charges) {
		this.present_rate_of_user_charges = present_rate_of_user_charges;
	}

	/**
	 * @return the seperate_sanctioned_load
	 */
	public Double getSeperate_sanctioned_load() {
		return seperate_sanctioned_load;
	}

	/**
	 * @param seperate_sanctioned_load the seperate_sanctioned_load to set
	 */
	public void setSeperate_sanctioned_load(Double seperate_sanctioned_load) {
		this.seperate_sanctioned_load = seperate_sanctioned_load;
	}

	/**
	 * @return the seperate_pending_eletric_bill31032017
	 */
	public Double getSeperate_pending_eletric_bill31032017() {
		return seperate_pending_eletric_bill31032017;
	}

	/**
	 * @param seperate_pending_eletric_bill31032017 the seperate_pending_eletric_bill31032017 to set
	 */
	public void setSeperate_pending_eletric_bill31032017(Double seperate_pending_eletric_bill31032017) {
		this.seperate_pending_eletric_bill31032017 = seperate_pending_eletric_bill31032017;
	}

	/**
	 * @return the average_monthly_bill_of_treatment_plant
	 */
	public Double getAverage_monthly_bill_of_treatment_plant() {
		return average_monthly_bill_of_treatment_plant;
	}

	/**
	 * @param average_monthly_bill_of_treatment_plant the average_monthly_bill_of_treatment_plant to set
	 */
	public void setAverage_monthly_bill_of_treatment_plant(Double average_monthly_bill_of_treatment_plant) {
		this.average_monthly_bill_of_treatment_plant = average_monthly_bill_of_treatment_plant;
	}

	/**
	 * @param householdWaterConnection the householdWaterConnection to set
	 */
	public void setHouseholdWaterConnection(Double householdWaterConnection) {
		this.householdWaterConnection = householdWaterConnection;
	}



	@Override
	public String toString() {
		return "SchemeBean1 [id=" + id + ", schemeId=" + schemeId + ", villageId=" + villageId + ", pipelineAc="
				+ pipelineAc + ", pipelineMsDi=" + pipelineMsDi + ", pipelineGi=" + pipelineGi + ", pipelinePvc="
				+ pipelinePvc + ", percentage_functional_pipeline=" + percentage_functional_pipeline
				+ ", householdWaterConnection=" + householdWaterConnection + ", wateredConnection=" + wateredConnection
				+ ", meteredConnection=" + meteredConnection + ", meteredRupplyRecovery=" + meteredRupplyRecovery
				+ ", flat_rate_charges_per_month=" + flat_rate_charges_per_month
				+ ", arrear_of_recovery_with_consumers_as01042017=" + arrear_of_recovery_with_consumers_as01042017
				+ ", scheme_functional=" + scheme_functional + ", scheme_nonfunctional=" + scheme_nonfunctional
				+ ", scheme_nonfunctional_date=" + scheme_nonfunctional_date + ", ground_water_potable_status="
				+ ground_water_potable_status + ", ground_water_potable_no=" + ground_water_potable_no
				+ ", preventive_measures_adopted=" + preventive_measures_adopted + ", capacity_of_plant="
				+ capacity_of_plant + ", date_of_installation=" + date_of_installation + ", being_operated_by="
				+ being_operated_by + ", volume_of_water_daily_basis=" + volume_of_water_daily_basis
				+ ", disposal_of_reject_water=" + disposal_of_reject_water + ", penetration_in_percentage="
				+ penetration_in_percentage + ", present_rate_of_user_charges=" + present_rate_of_user_charges
				+ ", seperate_sanctioned_load=" + seperate_sanctioned_load + ", seperate_pending_eletric_bill31032017="
				+ seperate_pending_eletric_bill31032017 + ", average_monthly_bill_of_treatment_plant="
				+ average_monthly_bill_of_treatment_plant + ", any_other_desc=" + any_other_desc + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	
	

	
	
}
