package com.prwss.mis.WaterConnection.struts;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="wq_urban_sdo_mpg", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class WaterConnectionUrbanSDOMappingBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7333052958487356209L;

	
	
	
	@Id
	@GeneratedValue(generator = "wq_urban_sdo_mpg_urban_sdo_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "wq_urban_sdo_mpg_urban_sdo_id_seq", sequenceName = "prwss_main.wq_urban_sdo_mpg_urban_sdo_id_seq")
	
	@Column(name = "urban_sdo_id", nullable = false)
	private Integer urban_sdo_id;
	
	@Column(name = "district_id", nullable = false)
	private String district_id;
	
	
	@Column(name = "user_id", nullable = false)
	private String user_id;
	
	@Column(name = "active_flag", nullable = false)
	private String active_flag;

	public Integer getUrban_sdo_id() {
		return urban_sdo_id;
	}

	public void setUrban_sdo_id(Integer urban_sdo_id) {
		this.urban_sdo_id = urban_sdo_id;
	}

	public String getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getActive_flag() {
		return active_flag;
	}

	public void setActive_flag(String active_flag) {
		this.active_flag = active_flag;
	}

	@Override
	public String toString() {
		return "WaterConnectionUrbanSDOMappingBean [urban_sdo_id=" + urban_sdo_id + ", district_id=" + district_id
				+ ", user_id=" + user_id + ", active_flag=" + active_flag + "]";
	}
	
	
	
	
	
}
