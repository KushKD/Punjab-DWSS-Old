/**
 * 
 */
package com.prwss.mis.masters.subcomponent.struts;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author vgadiraju
 *
 */
public class SubComponentForm extends ValidatorForm {

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 7530881113472308294L;
	
	private String componentId;
	private String subComponentId;
	private String subComponentName;
	private String subComponentDescription;
	private String[] subComponentIds;
	
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getSubComponentId() {
		return subComponentId;
	}
	public void setSubComponentId(String subComponentId) {
		this.subComponentId = subComponentId;
	}
	public String getSubComponentName() {
		return subComponentName;
	}
	public void setSubComponentName(String subComponentName) {
		this.subComponentName = subComponentName;
	}
	public String getSubComponentDescription() {
		return subComponentDescription;
	}
	public void setSubComponentDescription(String subComponentDescription) {
		this.subComponentDescription = subComponentDescription;
	}
	public String[] getSubComponentIds() {
		return subComponentIds;
	}
	public void setSubComponentIds(String[] subComponentIds) {
		this.subComponentIds = subComponentIds;
	}
	
	
}
