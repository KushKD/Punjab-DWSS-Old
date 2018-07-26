package com.prwss.mis.inventory.goodsreceipt.dao;

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
@Table(name=MISConstants.GOODSRECEIPTDETAILSTABLE, schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class GoodsReceiptDetailsBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2712637924820376862L;
	
	@Id
	@Column(name="goods_receipt_details_id")
	@SequenceGenerator(name="t_goods_receipt_details_goods_receipt_details_id_seq", sequenceName="prwss_main.t_goods_receipt_details_goods_receipt_details_id_seq")
	@GeneratedValue(generator="t_goods_receipt_details_goods_receipt_details_id_seq", strategy=GenerationType.AUTO)
	private int goodsReceiptDetailsId;
	
	
	@Column(name="goods_receipt_header_id",nullable=false)
	private long goodsReceiptDetailsHeaderId;
	
	@Column(name="item_group_id",nullable=false)
	private String itemGroupId;
	
	@Column(name="item_id",nullable=false)
	private String itemId;
	
	@Column(name="quantity")
	private long quantity;
	
	@Column(name="rate")
	private double rate;
	
	@Column(name="amount")
	private double amount;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public int getGoodsReceiptDetailsId() {
		return goodsReceiptDetailsId;
	}

	public void setGoodsReceiptDetailsId(int goodsReceiptDetailsId) {
		this.goodsReceiptDetailsId = goodsReceiptDetailsId;
	}

	public long getGoodsReceiptDetailsHeaderId() {
		return goodsReceiptDetailsHeaderId;
	}

	public void setGoodsReceiptDetailsHeaderId(long goodsReceiptDetailsHeaderId) {
		this.goodsReceiptDetailsHeaderId = goodsReceiptDetailsHeaderId;
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "GoodsReceiptDetailsBean [goodsReceiptDetailsId="
				+ goodsReceiptDetailsId + ", goodsReceiptDetailsHeaderId="
				+ goodsReceiptDetailsHeaderId + ", itemGroupId=" + itemGroupId
				+ ", itemId=" + itemId + ", quantity=" + quantity + ", rate="
				+ rate + ", amount=" + amount + "]";
	}
	  
	  
	

}
