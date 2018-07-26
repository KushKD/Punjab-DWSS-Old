/**
 * 
 */
package com.prwss.mis.masters.component.struts;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * @author vgadiraju
 *
 */
public class ComponentForm extends ValidatorActionForm {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 6432618958836295091L;
	
	private String componentId;
	private String componentName;
	private String componentDescription;
	private String[] componentIds;
	
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getComponentDescription() {
		return componentDescription;
	}
	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}
	public String[] getComponentIds() {
		return componentIds;
	}
	public void setComponentIds(String[] componentIds) {
		this.componentIds = componentIds;
	}

	

}
