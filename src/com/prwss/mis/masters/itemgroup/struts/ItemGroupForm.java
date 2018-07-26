package com.prwss.mis.masters.itemgroup.struts;

import org.apache.struts.validator.ValidatorForm;

public class ItemGroupForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6071894676021967405L;
	
	private String itemGroupId;
	private String itemGroupName;
	private String itemGroupIds[];
	
	public String getItemGroupId() {
		return itemGroupId;
	}
	public void setItemGroupId(String itemGroupId) {
		this.itemGroupId = itemGroupId;
	}
	public String getItemGroupName() {
		return itemGroupName;
	}
	public void setItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
	}
	public String[] getItemGroupIds() {
		return itemGroupIds;
	}
	public void setItemGroupIds(String[] itemGroupIds) {
		this.itemGroupIds = itemGroupIds;
	}
	
	

}
