package com.prwss.mis.masters.scheme.struts;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name = "vw_scheme_village_procedure", schema = MISConstants.MIS_DB_SCHEMA_NAME)

public class SchemeVillagesBean implements Serializable{
	
	private static final long serialVersionUID = -4296343456255392619L;
	
	@Id
	@Column(name = "scheme_id", nullable = false)
	private String schemeId;

	@Column(name = "village_id", nullable = false)
	private String villageId;

	@Column(name = "scheme_source", nullable = false)
	private String scheme_source;

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
	 * @return the scheme_source
	 */
	public String getScheme_source() {
		return scheme_source;
	}

	/**
	 * @param scheme_source the scheme_source to set
	 */
	public void setScheme_source(String scheme_source) {
		this.scheme_source = scheme_source;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SchemeVillagesBean [schemeId=" + schemeId + ", villageId=" + villageId + ", scheme_source="
				+ scheme_source + "]";
	}
	
	
	
	
}
