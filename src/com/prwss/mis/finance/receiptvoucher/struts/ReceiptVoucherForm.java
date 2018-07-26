/**
 * 
 */
package com.prwss.mis.finance.receiptvoucher.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class ReceiptVoucherForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8649089165975524171L;
	
	private String programId;
	private String locationId;
	private String receiptMode;
	private long voucherNo;
	private long bankId;
	private String voucherDate;
	private String payerName;
	private  String payerType;
	private String transactionDate;
	private String documentNo;
	private String receiptType;
	private String instrumentType;
	private BigDecimal instrAmount;
	private String instrumentNo;
	private String instrumentDate;
	private String bankName;
	private String notes;
	private String codeHeadId;
	private String componentId;
	private String subComponentId;
	private String activityId;
	private Datagrid receiptVoucherDatagrid;
	private String remarks;
	
	
	
	public long getBankId() {
		return bankId;
	}
	public void setBankId(long bankId) {
		this.bankId = bankId;
	}
	public String getPayerType() {
		return payerType;
	}
	public void setPayerType(String payerType) {
		this.payerType = payerType;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getReceiptMode() {
		return receiptMode;
	}
	public void setReceiptMode(String receiptMode) {
		this.receiptMode = receiptMode;
	}
	public long getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(long voucherNo) {
		this.voucherNo = voucherNo;
	}
	public String getVoucherDate() {
		return voucherDate;
	}
	public void setVoucherDate(String voucherDate) {
		this.voucherDate = voucherDate;
	}
	
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}
	public String getInstrumentType() {
		return instrumentType;
	}
	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}
	public BigDecimal getInstrAmount() {
		return instrAmount;
	}
	public void setInstrAmount(BigDecimal instrAmount) {
		this.instrAmount = instrAmount;
	}
	public String getInstrumentNo() {
		return instrumentNo;
	}
	public void setInstrumentNo(String instrumentNo) {
		this.instrumentNo = instrumentNo;
	}
	public String getInstrumentDate() {
		return instrumentDate;
	}
	public void setInstrumentDate(String instrumentDate) {
		this.instrumentDate = instrumentDate;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getCodeHeadId() {
		return codeHeadId;
	}
	public void setCodeHeadId(String codeHeadId) {
		this.codeHeadId = codeHeadId;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getSubComponentId() {
		return subComponentId;
	}
	public void setSubComponentId(String subComponentId) {
		this.subComponentId = subComponentId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	public Datagrid getReceiptVoucherDatagrid() {
		return receiptVoucherDatagrid;
	}
	public void setReceiptVoucherDatagrid(Datagrid receiptVoucherDatagrid) {
		this.receiptVoucherDatagrid = receiptVoucherDatagrid;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
