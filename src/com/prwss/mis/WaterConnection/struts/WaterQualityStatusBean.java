package com.prwss.mis.WaterConnection.struts;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="wq_citizen_water_status_final_rpt", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class WaterQualityStatusBean implements Serializable {
	private static final long serialVersionUID = 1343432434L;
	
	@Id
	@Column(name = "ctzn_water_stat_id", nullable = false)
	private Integer ctznWaterStatId;
	
	@Column(name = "district_name")
	private String districtName;
	
	@Column(name = "division_name")
	private String division_name;
	
	
	@Column(name = "constituency")
	private String constituency;
	
	@Column(name = "block_name")
	private String block_name;
	
	@Column(name = "village_name")
	private String village_name;
	
	@Column(name = "village_mis_code")
	private String village_mis_code;
	
	@Column(name = "source_of_scheme")
	private String source_of_scheme;
	
	@Column(name = "source_depth")
	private String source_depth;
	
	@Column(name = "installed_date")
	private String installed_date;
	
	@Column(name = "aluminium_val")
	private String aluminium_val;
	
	@Column(name = "lead_val")
	private String lead_val;
	
	@Column(name = "selenium_val")
	private String selenium_val;
	
	@Column(name = "chromium_val")
	private String chromium_val;
	

	@Column(name = "mercury_val")
	private String mercury_val;

	@Column(name = "arsenic_val")
	private String arsenic_val;

	@Column(name = "cadmium_val")
	private String cadmium_val;

	@Column(name = "nickel_val")
	private String nickel_val;

	@Column(name = "tds_val")
	private String tds_val;

	@Column(name = "iron_val")
	private String iron_val;

	@Column(name = "copper_val")
	private String copper_val;

	@Column(name = "uranium_val")
	private String uranium_val;

	@Column(name = "fluoride_val")
	private String fluoride_val;

	@Column(name = "chloride_val")
	private String chloride_val;

	@Column(name = "nitrate_val")
	private String nitrate_val;
	

	@Column(name = "sulphate_val")
	private String sulphate_val;

	@Column(name = "calcium_val")
	private String calcium_val;

	@Column(name = "magnesium_val")
	private String magnesium_val;

	@Column(name = "sodium_val")
	private String sodium_val;

	@Column(name = "potasium_val")
	private String potasium_val;

	@Column(name = "color_val")
	private String color_val;

	@Column(name = "turbidity_val")
	private String turbidity_val;

	@Column(name = "ph_val")
	private String ph_val;

	@Column(name = "hardness_val")
	private String hardness_val;

	@Column(name = "alkalinity_val")
	private String alkalinity_val;

	@Column(name = "taste_val")
	private String taste_val;

	@Column(name = "odor_val")
	private String odor_val;

	@Column(name = "test_phase")
	private String test_phase;

	@Column(name = "active_flag")
	private Integer active_flag;

	@Column(name = "water_staus")
	private String water_staus;
	
	@Column(name = "scheme_name")
	private String scheme_name;
	
	

	public String getScheme_name() {
		return scheme_name;
	}

	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}

	public Integer getCtznWaterStatId() {
		return ctznWaterStatId;
	}

	public void setCtznWaterStatId(Integer ctznWaterStatId) {
		this.ctznWaterStatId = ctznWaterStatId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDivision_name() {
		return division_name;
	}

	public void setDivision_name(String division_name) {
		this.division_name = division_name;
	}

	public String getConstituency() {
		return constituency;
	}

	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	public String getBlock_name() {
		return block_name;
	}

	public void setBlock_name(String block_name) {
		this.block_name = block_name;
	}

	public String getVillage_name() {
		return village_name;
	}

	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}

	public String getVillage_mis_code() {
		return village_mis_code;
	}

	public void setVillage_mis_code(String village_mis_code) {
		this.village_mis_code = village_mis_code;
	}

	public String getSource_of_scheme() {
		return source_of_scheme;
	}

	public void setSource_of_scheme(String source_of_scheme) {
		this.source_of_scheme = source_of_scheme;
	}

	public String getSource_depth() {
		return source_depth;
	}

	public void setSource_depth(String source_depth) {
		this.source_depth = source_depth;
	}

	public String getInstalled_date() {
		return installed_date;
	}

	public void setInstalled_date(String installed_date) {
		this.installed_date = installed_date;
	}

	public String getAluminium_val() {
		return aluminium_val;
	}

	public void setAluminium_val(String aluminium_val) {
		this.aluminium_val = aluminium_val;
	}

	public String getLead_val() {
		return lead_val;
	}

	public void setLead_val(String lead_val) {
		this.lead_val = lead_val;
	}

	public String getSelenium_val() {
		return selenium_val;
	}

	public void setSelenium_val(String selenium_val) {
		this.selenium_val = selenium_val;
	}

	public String getChromium_val() {
		return chromium_val;
	}

	public void setChromium_val(String chromium_val) {
		this.chromium_val = chromium_val;
	}

	public String getMercury_val() {
		return mercury_val;
	}

	public void setMercury_val(String mercury_val) {
		this.mercury_val = mercury_val;
	}

	public String getArsenic_val() {
		return arsenic_val;
	}

	public void setArsenic_val(String arsenic_val) {
		this.arsenic_val = arsenic_val;
	}

	public String getCadmium_val() {
		return cadmium_val;
	}

	public void setCadmium_val(String cadmium_val) {
		this.cadmium_val = cadmium_val;
	}

	public String getNickel_val() {
		return nickel_val;
	}

	public void setNickel_val(String nickel_val) {
		this.nickel_val = nickel_val;
	}

	public String getTds_val() {
		return tds_val;
	}

	public void setTds_val(String tds_val) {
		this.tds_val = tds_val;
	}

	public String getIron_val() {
		return iron_val;
	}

	public void setIron_val(String iron_val) {
		this.iron_val = iron_val;
	}

	public String getCopper_val() {
		return copper_val;
	}

	public void setCopper_val(String copper_val) {
		this.copper_val = copper_val;
	}

	public String getUranium_val() {
		return uranium_val;
	}

	public void setUranium_val(String uranium_val) {
		this.uranium_val = uranium_val;
	}

	public String getFluoride_val() {
		return fluoride_val;
	}

	public void setFluoride_val(String fluoride_val) {
		this.fluoride_val = fluoride_val;
	}

	public String getChloride_val() {
		return chloride_val;
	}

	public void setChloride_val(String chloride_val) {
		this.chloride_val = chloride_val;
	}

	public String getNitrate_val() {
		return nitrate_val;
	}

	public void setNitrate_val(String nitrate_val) {
		this.nitrate_val = nitrate_val;
	}

	public String getSulphate_val() {
		return sulphate_val;
	}

	public void setSulphate_val(String sulphate_val) {
		this.sulphate_val = sulphate_val;
	}

	public String getCalcium_val() {
		return calcium_val;
	}

	public void setCalcium_val(String calcium_val) {
		this.calcium_val = calcium_val;
	}

	public String getMagnesium_val() {
		return magnesium_val;
	}

	public void setMagnesium_val(String magnesium_val) {
		this.magnesium_val = magnesium_val;
	}

	public String getSodium_val() {
		return sodium_val;
	}

	public void setSodium_val(String sodium_val) {
		this.sodium_val = sodium_val;
	}

	public String getPotasium_val() {
		return potasium_val;
	}

	public void setPotasium_val(String potasium_val) {
		this.potasium_val = potasium_val;
	}

	public String getColor_val() {
		return color_val;
	}

	public void setColor_val(String color_val) {
		this.color_val = color_val;
	}

	public String getTurbidity_val() {
		return turbidity_val;
	}

	public void setTurbidity_val(String turbidity_val) {
		this.turbidity_val = turbidity_val;
	}

	public String getPh_val() {
		return ph_val;
	}

	public void setPh_val(String ph_val) {
		this.ph_val = ph_val;
	}

	public String getHardness_val() {
		return hardness_val;
	}

	public void setHardness_val(String hardness_val) {
		this.hardness_val = hardness_val;
	}

	public String getAlkalinity_val() {
		return alkalinity_val;
	}

	public void setAlkalinity_val(String alkalinity_val) {
		this.alkalinity_val = alkalinity_val;
	}

	public String getTaste_val() {
		return taste_val;
	}

	public void setTaste_val(String taste_val) {
		this.taste_val = taste_val;
	}

	public String getOdor_val() {
		return odor_val;
	}

	public void setOdor_val(String odor_val) {
		this.odor_val = odor_val;
	}

	public String getTest_phase() {
		return test_phase;
	}

	public void setTest_phase(String test_phase) {
		this.test_phase = test_phase;
	}

	public Integer getActive_flag() {
		return active_flag;
	}

	public void setActive_flag(Integer active_flag) {
		this.active_flag = active_flag;
	}

	public String getWater_staus() {
		return water_staus;
	}

	public void setWater_staus(String water_staus) {
		this.water_staus = water_staus;
	}

	@Override
	public String toString() {
		return "WaterQualityStatusBean [ctznWaterStatId=" + ctznWaterStatId + ", districtName=" + districtName
				+ ", division_name=" + division_name + ", constituency=" + constituency + ", block_name=" + block_name
				+ ", village_name=" + village_name + ", village_mis_code=" + village_mis_code + ", source_of_scheme="
				+ source_of_scheme + ", source_depth=" + source_depth + ", installed_date=" + installed_date
				+ ", aluminium_val=" + aluminium_val + ", lead_val=" + lead_val + ", selenium_val=" + selenium_val
				+ ", chromium_val=" + chromium_val + ", mercury_val=" + mercury_val + ", arsenic_val=" + arsenic_val
				+ ", cadmium_val=" + cadmium_val + ", nickel_val=" + nickel_val + ", tds_val=" + tds_val + ", iron_val="
				+ iron_val + ", copper_val=" + copper_val + ", uranium_val=" + uranium_val + ", fluoride_val="
				+ fluoride_val + ", chloride_val=" + chloride_val + ", nitrate_val=" + nitrate_val + ", sulphate_val="
				+ sulphate_val + ", calcium_val=" + calcium_val + ", magnesium_val=" + magnesium_val + ", sodium_val="
				+ sodium_val + ", potasium_val=" + potasium_val + ", color_val=" + color_val + ", turbidity_val="
				+ turbidity_val + ", ph_val=" + ph_val + ", hardness_val=" + hardness_val + ", alkalinity_val="
				+ alkalinity_val + ", taste_val=" + taste_val + ", odor_val=" + odor_val + ", test_phase=" + test_phase
				+ ", active_flag=" + active_flag + ", water_staus=" + water_staus + ", scheme_name=" + scheme_name
				+ "]";
	}

}
