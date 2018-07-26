package com.prwss.mis.inventory.supplyorder.struts;

public class SupplyOrderGridBean {
	private long Id;
	private String itemGroupId;
	private String itemId;
	private String unitOfMeasurement;
	private long quantity;
	private double itemRate;
	private String remarks;
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
	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}
	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "SuppyOrderGridBean [Id=" + Id + ", itemGroupId=" + itemGroupId
				+ ", itemId=" + itemId + ", unitOfMeasurement="
				+ unitOfMeasurement + ", quantity=" + quantity + ", itemRate="
				+ itemRate + ", remarks=" + remarks + "]";
	}
	
	
}
