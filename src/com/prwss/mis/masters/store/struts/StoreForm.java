package com.prwss.mis.masters.store.struts;

import org.apache.struts.validator.ValidatorForm;

public class StoreForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5303340165527638534L;
	private long storeId;
	private String storeName ;
	private String storeAddress;
	private String locationId;	
	private Long[] storeIds;
	
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public void setStoreIds(Long[] storeIds) {
		this.storeIds = storeIds;
	}
	public Long[] getStoreIds() {
		return storeIds;
	}
	
	

}
