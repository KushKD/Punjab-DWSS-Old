/**
 * 
 */
package com.prwss.mis.inventory.goodsissue.struts;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class GoodsIssueForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1245207576105707434L;
	
	private long GoodsIssueHeaderId;
	private String projectId;
	private String locationId;
	private long storeId;
	private String indentDate;
	private String indentNumber;	
	private String itemGroupId;
	private String itemId;
	private	long quantity;
	private double itemRate;
	private double itemAmount;	
	private String issuedType;
	private String issuedTo;
	private Datagrid goodsIssueDatagrid;
	public long getGoodsIssueHeaderId() {
		return GoodsIssueHeaderId;
	}
	public void setGoodsIssueHeaderId(long goodsIssueHeaderId) {
		GoodsIssueHeaderId = goodsIssueHeaderId;
	}
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
	public String getIndentDate() {
		return indentDate;
	}
	public void setIndentDate(String indentDate) {
		this.indentDate = indentDate;
	}
	public String getIndentNumber() {
		return indentNumber;
	}
	public void setIndentNumber(String indentNumber) {
		this.indentNumber = indentNumber;
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
	public Datagrid getGoodsIssueDatagrid() {
		return goodsIssueDatagrid;
	}
	public void setGoodsIssueDatagrid(Datagrid goodsIssueDatagrid) {
		this.goodsIssueDatagrid = goodsIssueDatagrid;
	}
	
	public String getIssuedType() {
		return issuedType;
	}
	public void setIssuedType(String issuedType) {
		this.issuedType = issuedType;
	}
	public String getIssuedTo() {
		return issuedTo;
	}
	public void setIssuedTo(String issuedTo) {
		this.issuedTo = issuedTo;
	}
	@Override
	public String toString() {
		return "GoodsIssueForm [GoodsIssueHeaderId=" + GoodsIssueHeaderId
				+ ", projectId=" + projectId + ", locationId=" + locationId
				+ ", storeId=" + storeId + ", indentDate=" + indentDate
				+ ", indentNumber=" + indentNumber + ", itemGroupId="
				+ itemGroupId + ", itemId=" + itemId + ", quantity=" + quantity
				+ ", itemRate=" + itemRate + ", itemAmount=" + itemAmount
				+ ", goodsIssueDatagrid=" + goodsIssueDatagrid + "]";
	}

	
	

}
