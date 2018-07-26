package com.prwss.mis.inventory.supplyorder;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name=MISConstants.SUPPLYORDERDETAILSTABLE, schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SupplyOrderDetailsBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8984832644375200995L;
	@Id
	@Column(name="supply_order_details_id")
	@SequenceGenerator(name="t_supply_order_details_supply_order_details_id_seq", sequenceName="prwss_main.t_supply_order_details_supply_order_details_id_seq")
	@GeneratedValue(generator="t_supply_order_details_supply_order_details_id_seq", strategy=GenerationType.AUTO)
	private int supplyOrderDetailsId;
	
	
	@Column(name="supply_order_header_id")
	private long supplyOrderHeaderId;
	
	@Column(name="item_group_id",nullable=false)
	private String itemGroupId;
	
	@Column(name="item_id",nullable=false)
	private String itemId;
	
	@Column(name="unit_of_measurement")
	private String unitOfMeasurementId;
	
	@Column(name="quantity")
	private long quantity;
	
	@Column(name="rate")
	private double rate;
	
	@Column(name="remarks")
	private String remarks;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public int getSupplyOrderDetailsId() {
		return supplyOrderDetailsId;
	}

	public void setSupplyOrderDetailsId(int supplyOrderDetailsId) {
		this.supplyOrderDetailsId = supplyOrderDetailsId;
	}

	public long getSupplyOrderHeaderId() {
		return supplyOrderHeaderId;
	}

	public void setSupplyOrderHeaderId(long supplyOrderHeaderId) {
		this.supplyOrderHeaderId = supplyOrderHeaderId;
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

	public String getUnitOfMeasurementId() {
		return unitOfMeasurementId;
	}

	public void setUnitOfMeasurementId(String unitOfMeasurementId) {
		this.unitOfMeasurementId = unitOfMeasurementId;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "SupplyOrderDetailsBean [supplyOrderDetailsId="
				+ supplyOrderDetailsId + ", supplyOrderHeaderId="
				+ supplyOrderHeaderId + ", itemGroupId=" + itemGroupId
				+ ", itemId=" + itemId + ", unitOfMeasurementId="
				+ unitOfMeasurementId + ", quantity=" + quantity + ", rate="
				+ rate + ", remarks=" + remarks + ", misAuditBean="
				+ misAuditBean + "]";
	}
	
	

}
