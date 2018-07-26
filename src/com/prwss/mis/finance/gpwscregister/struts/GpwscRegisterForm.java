package com.prwss.mis.finance.gpwscregister.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

public class GpwscRegisterForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -920508028822244658L;
	private long transactionNumber;
	private String locationId;
	private long gpwcId;
	private String dateOfTransaction;
	private String transactionType;
	private String payeeName;
	private String schemeId;
	private String cashBookFolioNumber;
	private String paymentActivity;
	private String paymentType;
	private String receiptActivity;
	private String receiptType;
	private BigDecimal paymentAmount;
	private BigDecimal receiptAmount;
	private String billNo;
	private long bankId;
	private String blockId;
	private String villageId;
	
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentActivity() {
		return paymentActivity;
	}
	public void setPaymentActivity(String paymentActivity) {
		this.paymentActivity = paymentActivity;
	}
	public String getReceiptActivity() {
		return receiptActivity;
	}
	public void setReceiptActivity(String receiptActivity) {
		this.receiptActivity = receiptActivity;
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
	public String getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public BigDecimal getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(BigDecimal receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public long getBankId() {
		return bankId;
	}
	public void setBankId(long bankId) {
		this.bankId = bankId;
	}
	public long getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(long transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	public long getGpwcId() {
		return gpwcId;
	}
	public void setGpwcId(long gpwcId) {
		this.gpwcId = gpwcId;
		System.out.println("while seeting GPPWSC"+this.gpwcId);
	}
	public String getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(String dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getCashBookFolioNumber() {
		return cashBookFolioNumber;
	}
	public void setCashBookFolioNumber(String cashBookFolioNumber) {
		this.cashBookFolioNumber = cashBookFolioNumber;
	}
	
	
}
