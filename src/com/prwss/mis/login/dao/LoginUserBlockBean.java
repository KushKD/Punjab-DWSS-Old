package com.prwss.mis.login.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="vw_divsional_block_data", schema=MISConstants.MIS_DB_SCHEMA_NAME)

public class LoginUserBlockBean implements Serializable {
	
	//prwss_main.vw_divsional_block_data
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -4558686924325941111L;   //-4558686924325947878L

	@Id
	@Column(name="district_id", nullable=false)
	private String district_id;
	
	
	public String getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}

	public String getDivisional_id() {
		return divisional_id;
	}

	public void setDivisional_id(String divisional_id) {
		this.divisional_id = divisional_id;
	}

	public String getDivisional_name() {
		return divisional_name;
	}

	public void setDivisional_name(String divisional_name) {
		this.divisional_name = divisional_name;
	}

	public String getBlock_id() {
		return block_id;
	}

	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}

	public String getBlock_name() {
		return block_name;
	}

	public void setBlock_name(String block_name) {
		this.block_name = block_name;
	}

	@Column(name="divisional_id", nullable=false)
	private String divisional_id;
	
	@Column(name="divisional_name")
	private String  divisional_name;
	
	@Column(name="block_id")
	private String  block_id;
	
	@Column(name="block_name")
	private String  block_name;

}
