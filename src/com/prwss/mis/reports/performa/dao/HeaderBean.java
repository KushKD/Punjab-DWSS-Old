/**
 * 
 */
package com.prwss.mis.reports.performa.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

/**
 * @author bhsingh
 *
 */

@Entity
@Table(name = "prwss_main.performa_headers_vw", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class HeaderBean  implements Serializable{
	
	private static final long serialVersionUID = 871995908276374898L;


	@Id
	@Column(name = "village_id")
	private String villageId;
	
	@Column(name = "village_name")
	private String villageName;
	
	@Column(name = "scheme_name")
	private String schemeName;
	
	@Column(name = "scheme_id")
	private String schemeId;

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
	
	
}
