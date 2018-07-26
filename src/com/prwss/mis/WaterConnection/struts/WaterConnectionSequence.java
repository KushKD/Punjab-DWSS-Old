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
@Table(name="waterqual_sequence_connection", schema=MISConstants.MIS_DB_SCHEMA_NAME)

public class WaterConnectionSequence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2031093571019537091L;
	
	@Id
	@GeneratedValue(generator = "waterqual_sequence_connection_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "waterqual_sequence_connection_id_seq", sequenceName = "prwss_main.waterqual_sequence_connection_id_seq")
	
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "water_qual_area", nullable = false)
	private String water_qual_area;
	
	
	@Column(name = "water_qual_value", nullable = false)
	private String water_qual_value;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getWater_qual_area() {
		return water_qual_area;
	}


	public void setWater_qual_area(String water_qual_area) {
		this.water_qual_area = water_qual_area;
	}


	public String getWater_qual_value() {
		return water_qual_value;
	}


	public void setWater_qual_value(String water_qual_value) {
		this.water_qual_value = water_qual_value;
	}


	@Override
	public String toString() {
		return "WaterConnectionSequence [id=" + id + ", water_qual_area=" + water_qual_area + ", water_qual_value="
				+ water_qual_value + "]";
	}


	
	
	

}
