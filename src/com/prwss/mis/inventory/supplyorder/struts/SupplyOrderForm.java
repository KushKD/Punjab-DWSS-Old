/**
 * 
 */
package com.prwss.mis.inventory.supplyorder.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;


public class SupplyOrderForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2696807402051724603L;

	private boolean submitForm;
	private long SupplyOrderHeaderId;
	private String projectId;
	private String locationId;
	private long storeId;
	private String supplierId;
	private String supplyOrderDate;
	private String supplyOrderNumber;	
	private String itemGroupId;
	private String itemId;
	private String unitOfMeasurement;
	private	long quantity;
	private double itemRate;
	private String remarks;
	private Datagrid supplyOrderDatagrid;
	
	public long getSupplyOrderHeaderId() {
		return SupplyOrderHeaderId;
	}
	public void setSupplyOrderHeaderId(long supplyOrderHeaderId) {
		SupplyOrderHeaderId = supplyOrderHeaderId;
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
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplyOrderDate() {
		return supplyOrderDate;
	}
	public void setSupplyOrderDate(String supplyOrderDate) {
		this.supplyOrderDate = supplyOrderDate;
	}
	public String getSupplyOrderNumber() {
		return supplyOrderNumber;
	}
	public void setSupplyOrderNumber(String supplyOrderNumber) {
		this.supplyOrderNumber = supplyOrderNumber;
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
	public Datagrid getSupplyOrderDatagrid() {
		return supplyOrderDatagrid;
	}
	public void setSupplyOrderDatagrid(Datagrid supplyOrederDatagrid) {
		this.supplyOrderDatagrid = supplyOrederDatagrid;
	}
	
	public boolean isSubmitForm() {
		return submitForm;
	}
	public void setSubmitForm(boolean submitForm) {
		this.submitForm = submitForm;
	}
	
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "supplyOrderHeaderId");
		
		
		if(this.submitForm)
		{
		ActionErrors errors = super.validate(mapping, request);
		//System.out.println("**********************************************************************IN VALIDATION");
		//System.out.println("**********************************************************************IN VALIDATION ----"+errors.toString());
		return errors;
		}
		else
		{
			//System.out.println("**********************************************************************NOT IN VALIDATION");
		return null;
		}

	}
		
	
}
