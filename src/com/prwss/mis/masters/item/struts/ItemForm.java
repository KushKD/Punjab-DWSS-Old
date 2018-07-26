package com.prwss.mis.masters.item.struts;

import java.util.Arrays;

import org.apache.struts.validator.ValidatorForm;

public class ItemForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4374788050866034980L;
	private String itemId;
	private String itemName;
	private String unitOfMeasurement;	
	private long storeId;	
	private String itemGroupId;
	private double openingBalance;
	private String serviceable;
	private double storeIssueRate;
	private String[] itemIds;
	private String locationId;
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getServiceable() {
		return serviceable;
	}
	public void setServiceable(String serviceable) {
		this.serviceable = serviceable;
	}
	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}
	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}
	
	public double getStoreIssueRate() {
		return storeIssueRate;
	}
	public void setStoreIssueRate(double storeIssueRate) {
		this.storeIssueRate = storeIssueRate;
	}
	
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public String getItemGroupId() {
		return itemGroupId;
	}
	public void setItemGroupId(String itemGroupId) {
		this.itemGroupId = itemGroupId;
	}
	public double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String[] getItemIds() {
		return itemIds;
	}
	public void setItemIds(String[] itemIds) {
		this.itemIds = itemIds;
	}
	
	@Override
	public String toString() {
		return "ItemForm [itemId=" + itemId + ", itemName=" + itemName
				+ ", unitOfMeasurement=" + unitOfMeasurement + ", storeId="
				+ storeId + ", itemGroupId=" + itemGroupId
				+ ", openingBalance=" + openingBalance + ", serviceable="
				+ serviceable + ", storeIssueRate=" + storeIssueRate
				+ ", itemIds=" + Arrays.toString(itemIds) + "]";
	}
	
	
}
