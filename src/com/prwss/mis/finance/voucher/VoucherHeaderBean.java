package com.prwss.mis.finance.voucher;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_finance_voucher_hdr", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VoucherHeaderBean implements Serializable,Comparable<VoucherHeaderBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7331961634714345209L;
	@Id
	@SequenceGenerator(name="seq_t_voucher_id", sequenceName="prwss_main.seq_t_voucher_id")
	@GeneratedValue(generator="seq_t_voucher_id", strategy=GenerationType.AUTO)
	@Column(name="voc_id", nullable=false)
	private long vocId;
	
	@Column(name="voc_type")
	private String  vocType;
	
	@Column(name="program_id")
	private String  programId;
	
	@Column(name="location_id")
	private String  locationId;
	
	@Column(name="payment_mode")
	private String  paymentMode;
	
	@Column(name="voc_date")
	private Date  vocDate;
	
	@Column(name="payee_payer_id")
	private String payeePayerId;
	
	@Column(name="payee_payer_type")
	private String payeePayerType;
	
	@Column(name="transaction_date")
	private Date  transactionDate;
	
	@Column(name="document_no")
	private String  documentNo;
	
	@Column(name="type_of_payment")
	private String typeOfPayment;
	
	@Column(name="type_of_receipt")
	private String typeOfReceipt;
	
	@Column(name="instrument_type")
	private String instrumentType;
	
	@Column(name="amount")
	private BigDecimal amount ;
	
	@Column(name="instrument_date")
	private Date  instrumentDate;
	
	@Column(name="instrument_number")
	private String  instrumentNumber;
	
	// Currently leaving but have to remove it is not in use any more being replaced by bankId
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="can_modify")
	private String canModify;
	
	@Column(name="bank_id")
	private long bankId;
	
	@Column(name="scheme_id")
	private String schemeCode;
	
	@OneToMany(targetEntity=VoucherDetailBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="voc_id", referencedColumnName="voc_id", updatable = false , insertable = false)
	private Set<VoucherDetailBean> voucherDetailBeans;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	@Override
	public int compareTo(VoucherHeaderBean o) {
		return new Long(this.vocId).compareTo(o.vocId);
	}

	
	public String getSchemeCode() {
		return schemeCode;
	}


	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}


	public String getCanModify() {
		return canModify;
	}


	public void setCanModify(String canModify) {
		this.canModify = canModify;
	}


	public String getVocType() {
		return vocType;
	}

	public void setVocType(String vocType) {
		this.vocType = vocType;
	}

	public long getVocId() {
		return vocId;
	}

	public void setVocId(long vocId) {
		this.vocId = vocId;
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

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Date getVocDate() {
		return vocDate;
	}

	public void setVocDate(Date vocDate) {
		this.vocDate = vocDate;
	}

	public String getPayeePayerId() {
		return payeePayerId;
	}

	public void setPayeePayerId(String payeePayerId) {
		this.payeePayerId = payeePayerId;
	}

	public String getPayeePayerType() {
		return payeePayerType;
	}

	public void setPayeePayerType(String payeePayerType) {
		this.payeePayerType = payeePayerType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDocumentNo() {
		return documentNo;
	}


	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}


	public String getTypeOfPayment() {
		return typeOfPayment;
	}

	public void setTypeOfPayment(String typeOfPayment) {
		this.typeOfPayment = typeOfPayment;
	}

	public String getTypeOfReceipt() {
		return typeOfReceipt;
	}

	public void setTypeOfReceipt(String typeOfReceipt) {
		this.typeOfReceipt = typeOfReceipt;
	}

	public String getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getInstrumentDate() {
		return instrumentDate;
	}

	public void setInstrumentDate(Date instrumentDate) {
		this.instrumentDate = instrumentDate;
	}

	public String getInstrumentNumber() {
		return instrumentNumber;
	}

	public void setInstrumentNumber(String instrumentNumber) {
		this.instrumentNumber = instrumentNumber;
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

	public Set<VoucherDetailBean> getVoucherDetailBeans() {
		return voucherDetailBeans;
	}

	public void setVoucherDetailBeans(Set<VoucherDetailBean> voucherDetailBeans) {
		this.voucherDetailBeans = voucherDetailBeans;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public long getBankId() {
		return bankId;
	}

	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	@Override
	public String toString() {
		return "VoucherHeaderBean [vocId=" + vocId + ", vocType=" + vocType
				+ ", programId=" + programId + ", locationId=" + locationId
				+ ", paymentMode=" + paymentMode + ", vocDate=" + vocDate
				+ ", payeePayerId=" + payeePayerId + ", payeePayerType="
				+ payeePayerType + ", transactionDate=" + transactionDate
				+ ", documentNo=" + documentNo + ", typeOfPayment="
				+ typeOfPayment + ", typeOfReceipt=" + typeOfReceipt
				+ ", instrumentType=" + instrumentType + ", amount=" + amount
				+ ", instrumentDate=" + instrumentDate + ", instrumentNumber="
				+ instrumentNumber + ", bankName=" + bankName + ", notes="
				+ notes + ", bankId=" + bankId + ", voucherDetailBeans="
				+ voucherDetailBeans + ", misAuditBean=" + misAuditBean + "]";
	}

	
	

}
