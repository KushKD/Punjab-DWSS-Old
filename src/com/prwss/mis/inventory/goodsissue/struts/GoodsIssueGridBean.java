package com.prwss.mis.inventory.goodsissue.struts;

import java.io.Serializable;

public class GoodsIssueGridBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2716503081314520343L;
	
	private long Id;
	private String itemGroupId;
	private String itemId;
	private long quantity;
	private double itemRate;
	private double itemAmount;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getItemGroupId() {
		return itemGroupId;
	}
	public void setItemGroupId(String itemGroupId) {
		this.itemGroupId = itemGroupId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public double getItemRate() {
		return itemRate;
	}
	public void setItemRate(double itemRate) {
		this.itemRate = itemRate;
	}
	public double getItemAmount() {
		return itemAmount;
	}
	public void setItemAmount(double itemAmount) {
		this.itemAmount = itemAmount;
	}
	@Override
	public String toString() {
		return "GoodsIssueGridBean [Id=" + Id + ", itemGroupId=" + itemGroupId
				+ ", itemId=" + itemId + ", quantity=" + quantity
				+ ", itemRate=" + itemRate + ", itemAmount=" + itemAmount + "]";
	}
	
	
	

}
