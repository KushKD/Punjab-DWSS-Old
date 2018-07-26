package com.prwss.mis.inventory.goodsreceipt.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name=MISConstants.GOODSRECEIPTHEADERTABLE, schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class GoodsReceiptHeaderBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1774936546697589486L;

	@Id
	@Column(name="goods_receipt_header_id")
	private long goodsReceiptHeaderId ;
	
	@Column(name="project_id")
	private String projectId;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="store_id")
	private long storeId;
	
	@Column(name="supplier_id")
	private String supplierId;
	
	@Column(name="contarct_number")
	private String contractNumber;
	
	@Column(name="invoice_number")
	private String invoiceNumber;
	
	@Column(name="receipt_amount")
	private double receiptAmount;
	
	@Column(name="invoice_date")
	private Date invoiceDate;
	
	@Column(name="received_date")
	private Date receivedDate;
	
	@Column(name="remarks")
	private String remarks;
	
	@OneToMany(targetEntity=GoodsReceiptDetailsBean.class,fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="goods_receipt_header_id")
	private List<GoodsReceiptDetailsBean> goodsReceiptDetailsBeans;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getGoodsReceiptHeaderId() {
		return goodsReceiptHeaderId;
	}

	public void setGoodsReceiptHeaderId(long goodsReceiptHeaderId) {
		this.goodsReceiptHeaderId = goodsReceiptHeaderId;
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

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public double getReceiptAmount() {
		return receiptAmount;
	}

	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<GoodsReceiptDetailsBean> getGoodsReceiptDetailsBeans() {
		return goodsReceiptDetailsBeans;
	}

	public void setGoodsReceiptDetailsBeans(
			List<GoodsReceiptDetailsBean> goodsReceiptDetailsBeans) {
		this.goodsReceiptDetailsBeans = goodsReceiptDetailsBeans;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

}
