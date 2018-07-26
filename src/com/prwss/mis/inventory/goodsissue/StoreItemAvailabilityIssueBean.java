package com.prwss.mis.inventory.goodsissue;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="vw_store_item_availability", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class StoreItemAvailabilityIssueBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2353827643782124595L;

	@Column(name="location_id")
	private String locationId;
	
	@Column(name="store_id")
	private long storeId;
	
	@Id
	@Column(name="item_id")
	private String itemId;
	
	@Column(name="opening_balance")
	private long openingBalance;
	
	@Column(name="quantity")
	private long quantity;
	
	@Column(name="total_available")
	private long totalAvailableItem;
	

	@Column(name="temp_available")
	private long tempAvailableItem;
	

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public long getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(long openingBalance) {
		this.openingBalance = openingBalance;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getTotalAvailableItem() {
		return totalAvailableItem;
	}

	public void setTotalAvailableItem(long totalAvailableItem) {
		this.totalAvailableItem = totalAvailableItem;
	}
	
	public void setTempAvailableItem(long tempAvailableItem) {
		this.tempAvailableItem = tempAvailableItem;
	}

	public long getTempAvailableItem() {
		return tempAvailableItem;
	}	
	
	@Override
	public String toString() {
		return "StoreItemAvailabilityIssueBean [locationId=" + locationId + ", storeId="
				+ storeId + ", itemId=" + itemId
				+ ", openingBalance=" + openingBalance + ", quantity="
				+ quantity + ", totalAvailableItem=" + totalAvailableItem + ",tempAvailableItem="+ tempAvailableItem +"]";
	}

	

}
