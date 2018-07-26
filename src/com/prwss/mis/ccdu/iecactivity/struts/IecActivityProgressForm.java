package com.prwss.mis.ccdu.iecactivity.struts;

import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.ccdu.iecactivity.dao.IECActivityProgressBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class IecActivityProgressForm extends ValidatorForm {

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -5256636132274754294L;
	private long iecProgressId;
	private String locationId;
	private String blockId;
	private String villageId;
	private String iecActivityId;
	private String dateOfActivity;
	private String venueOfActivity;
	private long numberOfParticipants;
	private String remarks;
	private String outcomes;
	private Datagrid materialUtilizationDatagrid;
	private String itemGroup;
	private String stores;
	private double expenditure;
	
	List<IECActivityProgressBean> iECActivityProgressBean;
	
	
	public String getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}
	public List<IECActivityProgressBean> getiECActivityProgressBean() {
		return iECActivityProgressBean;
	}
	public void setiECActivityProgressBean(
			List<IECActivityProgressBean> iECActivityProgressBean) {
		this.iECActivityProgressBean = iECActivityProgressBean;
	}
	public String getStores() {
		return stores;
	}
	public void setStores(String stores) {
		this.stores = stores;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getIecActivityId() {
		return iecActivityId;
	}
	public void setIecActivityId(String iecActivityId) {
		this.iecActivityId = iecActivityId;
	}
	public String getDateOfActivity() {
		return dateOfActivity;
	}
	public void setDateOfActivity(String dateOfActivity) {
		this.dateOfActivity = dateOfActivity;
	}
	public String getVenueOfActivity() {
		return venueOfActivity;
	}
	public void setVenueOfActivity(String venueOfActivity) {
		this.venueOfActivity = venueOfActivity;
	}
	public long getNumberOfParticipants() {
		return numberOfParticipants;
	}
	public void setNumberOfParticipants(long numberOfParticipants) {
		this.numberOfParticipants = numberOfParticipants;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks =remarks;
	}
	public String getOutcomes() {
		return outcomes;
	}
	public void setOutcomes(String outcomes) {
		this.outcomes = outcomes;
	}
	public void setMaterialUtilizationDatagrid(Datagrid materialUtilizationDatagrid) {
		this.materialUtilizationDatagrid = materialUtilizationDatagrid;
	}
	public Datagrid getMaterialUtilizationDatagrid() {
		return materialUtilizationDatagrid;
	}
	public void setIecProgressId(long iecProgressId) {
		this.iecProgressId = iecProgressId;
	}
	public long getIecProgressId() {
		return iecProgressId;
	}
	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}
	public double getExpenditure() {
		return expenditure;
	}
	
	
}
