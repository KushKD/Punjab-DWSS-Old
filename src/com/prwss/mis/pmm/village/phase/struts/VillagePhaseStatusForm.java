/**
 * 
 */
package com.prwss.mis.pmm.village.phase.struts;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author pjha
 *
 */
public class VillagePhaseStatusForm extends ValidatorActionForm{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9060265554117797129L;
	private String villageId;	
	private String locationId;
	private String prePlanningDate;	
	private String planningDate;	
	private String implementationDate;	
	private String omDate;
	private String blockId;
	private String schemeId;
	
	
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	
	public String getPrePlanningDate() {
		return prePlanningDate;
	}
	public void setPrePlanningDate(String prePlanningDate) {
		this.prePlanningDate = prePlanningDate;
	}
	
	public String getPlanningDate() {
		return planningDate;
	}
	public void setPlanningDate(String planningDate) {
		this.planningDate = planningDate;
	}
	
	public String getImplementationDate() {
		return implementationDate;
	}
	public void setImplementationDate(String implementationDate) {
		this.implementationDate = implementationDate;
	}
	
	public String getOmDate() {
		return omDate;
	}
	public void setOmDate(String omDate) {
		this.omDate = omDate;
	}
	
	

}
