package com.prwss.mis.inventory.goodsreceipt.struts;

import java.io.Serializable;

public class GoodsReceiptGridBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6326256691871365279L;
	
	private long Id;
	private String itemGroupId;
	private String itemId;
	private long quantity;
	private double itemRate;
	private double itemAmount;
	
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
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	
	
}
