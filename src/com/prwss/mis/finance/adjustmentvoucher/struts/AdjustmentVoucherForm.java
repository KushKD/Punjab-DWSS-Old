/**
 * 
 */
package com.prwss.mis.finance.adjustmentvoucher.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class AdjustmentVoucherForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1704822058602695982L;
	
	private String programId;
	private String locationId;
	private long voucherNo;
	private String voucherDate;
	private String notes;
	private BigDecimal debitAmountId;
	private BigDecimal creditAmountId;
	private String creditRemarks;
	private String debitRemarks;
	private String creditCodeHeadId;
	private String debitCodeHeadId;
	
	private String creditComponentId;
	private String creditSubComponentId;
	private String creditActivityId;
	
	private String debitComponentId;
	private String debitSubComponentId;
	private String debitActivityId;
		
	
	private Datagrid adjustmentVoucherDatagrid;
	private String remarks;
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
	public long getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(long voucherNo) {
		this.voucherNo = voucherNo;
	}
	public String getVoucherDate() {
		return voucherDate;
	}
	
	
	public String getCreditRemarks() {
		return creditRemarks;
	}
	public void setCreditRemarks(String creditRemarks) {
		this.creditRemarks = creditRemarks;
	}
	public String getDebitRemarks() {
		return debitRemarks;
	}
	public void setDebitRemarks(String debitRemarks) {
		this.debitRemarks = debitRemarks;
	}
	public void setVoucherDate(String voucherDate) {
		this.voucherDate = voucherDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String getCreditCodeHeadId() {
		return creditCodeHeadId;
	}
	public void setCreditCodeHeadId(String creditCodeHeadId) {
		this.creditCodeHeadId = creditCodeHeadId;
	}
	public String getDebitCodeHeadId() {
		return debitCodeHeadId;
	}
	public void setDebitCodeHeadId(String debitCodeHeadId) {
		this.debitCodeHeadId = debitCodeHeadId;
	}
	
	public String getCreditComponentId() {
		return creditComponentId;
	}
	public void setCreditComponentId(String creditComponentId) {
		this.creditComponentId = creditComponentId;
	}
	public String getCreditSubComponentId() {
		return creditSubComponentId;
	}
	public void setCreditSubComponentId(String creditSubComponentId) {
		this.creditSubComponentId = creditSubComponentId;
	}
	public String getCreditActivityId() {
		return creditActivityId;
	}
	public void setCreditActivityId(String creditActivityId) {
		this.creditActivityId = creditActivityId;
	}
	public String getDebitComponentId() {
		return debitComponentId;
	}
	public void setDebitComponentId(String debitComponentId) {
		this.debitComponentId = debitComponentId;
	}
	public String getDebitSubComponentId() {
		return debitSubComponentId;
	}
	public void setDebitSubComponentId(String debitSubComponentId) {
		this.debitSubComponentId = debitSubComponentId;
	}
	public String getDebitActivityId() {
		return debitActivityId;
	}
	public void setDebitActivityId(String debitActivityId) {
		this.debitActivityId = debitActivityId;
	}
	
	public Datagrid getAdjustmentVoucherDatagrid() {
		return adjustmentVoucherDatagrid;
	}
	public void setAdjustmentVoucherDatagrid(Datagrid adjustmentVoucherDatagrid) {
		this.adjustmentVoucherDatagrid = adjustmentVoucherDatagrid;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public BigDecimal getDebitAmountId() {
		return debitAmountId;
	}
	public void setDebitAmountId(BigDecimal debitAmountId) {
		this.debitAmountId = debitAmountId;
	}
	public BigDecimal getCreditAmountId() {
		return creditAmountId;
	}
	public void setCreditAmountId(BigDecimal creditAmountId) {
		this.creditAmountId = creditAmountId;
	}

	
}
