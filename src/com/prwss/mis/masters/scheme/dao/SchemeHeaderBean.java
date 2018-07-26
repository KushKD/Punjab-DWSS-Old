/**
 * 
 */
package com.prwss.mis.masters.scheme.dao;

/**
 * @author pjha
 *
 */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Embeddable
@Entity
@Table(name="mst_scheme_header", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SchemeHeaderBean  implements Serializable, Comparable<SchemeHeaderBean>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7524121162361274864L;

	/**
	 * 
	 */
	
	/*@Column(name = "block_id", nullable = false)
	private String blockId;*/
 
	@Id	
	@Column(name="scheme_id", nullable=false)
	private String schemeId;
	
	@Id	
	@Column(name="location_id", nullable=false)
	private String locationId;

	@Column(name="scheme_name", nullable=false)
	private String schemeName;	
	
	@Column(name="prog_id", nullable=false)
	private String progId;
	
	@Column(name="scheme_source", nullable=false)
	private String schemeSource;
	
	@Column(name="district_id")
	private String districtId;
	
	@Column(name="water_works_existing_new")
	private String waterWorksExistingNew;
	
	@Id
	@Column(name="scheme_upgraded")
	private String schemeUpgraded;
	
	@Column(name="ref_scheme_id")
	private String refSchemeId;
	
	@Column(name="ws_sw")
	private String watersupply;
	
	/**
	 * KD WORK
	 */
	
	@Column(name="subCategoryProgramme")
	private String subCategoryProgramme;
	
	public String getSubCategoryProgramme() {
		return subCategoryProgramme;
	}

	public void setSubCategoryProgramme(String subCategoryProgramme) {
		this.subCategoryProgramme = subCategoryProgramme;
	}
	
	@Column(name="sub_division")
	private String subDivisionId;
	

	

	/**
	 * @return the subDivisionId
	 */
	public String getSubDivisionId() {
		return subDivisionId;
	}

	/**
	 * @param subDivisionId the subDivisionId to set
	 */
	public void setSubDivisionId(String subDivisionId) {
		this.subDivisionId = subDivisionId;
	}

	/**
	 * KD WORK DONE
	 */
	
	
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	
	

	public String getSchemeUpgraded() {
		return schemeUpgraded;
	}

	public void setSchemeUpgraded(String schemeUpgraded) {
		this.schemeUpgraded = schemeUpgraded;
	}

	public String getRefSchemeId() {
		return refSchemeId;
	}

	public void setRefSchemeId(String refSchemeId) {
		this.refSchemeId = refSchemeId;
	}

	public String getWaterWorksExistingNew() {
		return waterWorksExistingNew;
	}

	public void setWaterWorksExistingNew(String waterWorksExistingNew) {
		this.waterWorksExistingNew = waterWorksExistingNew;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getSchemeSource() {
		return schemeSource;
	}

	public void setSchemeSource(String schemeSource) {
		this.schemeSource = schemeSource;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getProgId() {
		return progId;
	}

	public void setProgId(String progId) {
		this.progId = progId;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public void setWatersupply(String watersupply) {
		this.watersupply = watersupply;
	}

	public String getWatersupply() {
		return watersupply;
	}


	/*public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}*/

	
	/**
	 * KD Updated
	 */
	
	

	
	

	@Override
	public int compareTo(SchemeHeaderBean o) {
		
		return this.schemeId.compareTo(o.schemeId);
	}

	

	

	@Override
	public String toString() {
		return "SchemeHeaderBean [schemeId=" + schemeId + ", locationId="
				+ locationId + ", schemeName=" + schemeName + ", progId="
				+ progId + ", schemeSource=" + schemeSource + ", districtId="
				+ districtId + ", waterWorksExistingNew="
				+ waterWorksExistingNew + ", schemeUpgraded=" + schemeUpgraded
				+ ", refSchemeId=" + refSchemeId + ", watersupply="
				+ watersupply + ", subCategoryProgramme="
				+ subCategoryProgramme + ", misAuditBean=" + misAuditBean + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((schemeId == null) ? 0 : schemeId.hashCode());
		result = prime * result
				+ ((schemeName == null) ? 0 : schemeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchemeHeaderBean other = (SchemeHeaderBean) obj;
		if (schemeId == null) {
			if (other.schemeId != null)
				return false;
		} else if (!schemeId.equals(other.schemeId))
			return false;
		if (schemeName == null) {
			if (other.schemeName != null)
				return false;
		} else if (!schemeName.equals(other.schemeName))
			return false;
		return true;
	}


	

}
