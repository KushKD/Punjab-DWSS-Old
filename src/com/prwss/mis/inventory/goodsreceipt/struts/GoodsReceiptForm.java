/**
 * 
 */
package com.prwss.mis.inventory.goodsreceipt.struts;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;


public class GoodsReceiptForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3561236267489711773L;
	private long goodsReceiptHeaderId;
	private String projectId;
	private String locationId;
	private long storeId;
	private String supplierId;
	private String invoiceNumber;
	private String receivedDate;
	private String invoiceDate;
	private String contractId;
	private double invoiceAmount;
	private String remarks;
	private String itemGroupId;
	private String itemId;
	private	long quantity;
	private long itemAmount;
	private double itemRate;
	
	private Datagrid goodsReceiptDatagrid;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
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
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public double getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Datagrid getGoodsReceiptDatagrid() {
		return goodsReceiptDatagrid;
	}
	public void setGoodsReceiptDatagrid(Datagrid goodsReceiptDatagrid) {
		this.goodsReceiptDatagrid = goodsReceiptDatagrid;
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
	public long getItemAmount() {
		return itemAmount;
	}
	public void setItemAmount(long itemAmount) {
		this.itemAmount = itemAmount;
	}
	public double getItemRate() {
		return itemRate;
	}
	public void setItemRate(double itemRate) {
		this.itemRate = itemRate;
	}
	public long getGoodsReceiptHeaderId() {
		return goodsReceiptHeaderId;
	}
	public void setGoodsReceiptHeaderId(long goodsReceiptHeaderId) {
		this.goodsReceiptHeaderId = goodsReceiptHeaderId;
	}
	

	 

}
