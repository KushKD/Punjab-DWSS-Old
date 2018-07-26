package com.prwss.mis.masters.item.dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.masters.store.dao.StoreBean;
import com.prwss.mis.masters.unitofmeasurement.dao.UnitOfMeasurementBean;

@Entity
@Table(name="mst_item", schema="prwss_main")
public class ItemBean implements Serializable, Comparable<ItemBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 355529458431785549L;

	@Id
	@Column(name="item_id", nullable=false)
	private String itemId;
	
	@Column(name="item_name", nullable=false)
	private String itemName;
	
	@ManyToOne(targetEntity=UnitOfMeasurementBean.class)
	@JoinColumn(name="unit_of_measurement_id")
	private UnitOfMeasurementBean unitOfMeasurement;
	
	@ManyToOne(targetEntity=StoreBean.class)
	@JoinColumn(name="store_id")
	private StoreBean store;
	
	@ManyToOne(targetEntity=ItemGroupBean.class)
	@JoinColumn(name="item_group_id")
	private ItemGroupBean itemGroup;
	
	@Column(name="opening_balance") 
	private double openingBalance;
	
	@Column(name="serviceable")
	private String serviceable;
	
	@Column(name="store_issue_rate")
	private double storeIssueRate;
	
	@Column(name="location_id")
	private String locationId;
	
	@Embedded
	private MISAuditBean misAuditBean;

	
	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
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

	public UnitOfMeasurementBean getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(UnitOfMeasurementBean unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public StoreBean getStore() {
		return store;
	}

	public void setStore(StoreBean store) {
		this.store = store;
	}

	public ItemGroupBean getItemGroup() {
		return itemGroup;
	}

	public void setItemGroup(ItemGroupBean itemGroup) {
		this.itemGroup = itemGroup;
	}

	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}	

	public String getServiceable() {
		return serviceable;
	}

	public void setServiceable(String serviceable) {
		this.serviceable = serviceable;
	}

	public double getStoreIssueRate() {
		return storeIssueRate;
	}

	public void setStoreIssueRate(double storeIssueRate) {
		this.storeIssueRate = storeIssueRate;
	}

	

	@Override
	public int compareTo(ItemBean o) {
		return this.itemId.compareTo(o.getItemId());
	}

	@Override
	public String toString() {
		return "ItemBean [itemId=" + itemId + ", itemName=" + itemName
				+ ", unitOfMeasurement=" + unitOfMeasurement + ", store="
				+ store + ", itemGroup=" + itemGroup + ", openingBalance="
				+ openingBalance + ", serviceable=" + serviceable
				+ ", storeIssueRate=" + storeIssueRate + ", misAuditBean="
				+ misAuditBean + "]";
	}

}
