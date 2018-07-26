package com.prwss.mis.finance.gpwscregister.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.committee.dao.CommitteeBean;

@Entity
@Table(name="t_finance_gpwsc_register", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class GpwscRegisterBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2758259015875206748L;

	@Id
    @GeneratedValue(generator = "seq_fin_gpwsc_id", strategy = GenerationType.AUTO)
 	@SequenceGenerator(name = "seq_fin_gpwsc_id", sequenceName = "prwss_main.seq_fin_gpwsc_id")
	@Column(name = "transaction_number", nullable = false)
	private long transactionNumber;
	
	@Column(name = "location_id")
	private String locationId;
	
	@ManyToOne(targetEntity=CommitteeBean.class,fetch=FetchType.EAGER,  cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="committee_id", updatable = false )
    private CommitteeBean committeeBean;

	
	@Column(name = "date_of_transaction")
	private Date dateOfTransaction;
	
	@Column(name = "transaction_type")
	private String transactionType;
	
	@Column(name = "payee_name")
	private String payeeName;
	
	@Column(name = "payment_type")
	private String paymentType;
	
	@Column(name = "scheme_id")
	private String schemeId;
	
	@Column(name = "receipt_type")
	private String receiptType;
	
	@Column(name = "payment_amount")
	private BigDecimal paymentAmount;
	
	
	@Column(name = "receipt_amount")
	private BigDecimal receiptAmount;
	
	@Column(name = "bill_no")
	private String billNo;
	
	@Column(name = "bank_id")
	private long bankId;
	
	@Column(name = "payment_activity")
	private String paymentActivity;
	
	@Column(name = "receipt_activity")
	private String receiptActivity;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
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

	public CommitteeBean getCommitteeBean() {
		return committeeBean;
	}

	public void setCommitteeBean(CommitteeBean committeeBean) {
		this.committeeBean = committeeBean;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
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

	@Override
	public String toString() {
		return "GpwscRegisterBean [transactionNumber=" + transactionNumber
				+ ", locationId=" + locationId + ", committeeBean="
				+ committeeBean + ", dateOfTransaction=" + dateOfTransaction
				+ ", transactionType=" + transactionType + ", payeeName="
				+ payeeName + ", schemeId=" + schemeId + ", receiptType="
				+ receiptType + ", paymentAmount=" + paymentAmount
				+ ", receiptAmount=" + receiptAmount + ", billNo=" + billNo
				+ ", bankId=" + bankId + ", paymentActivity=" + paymentActivity
				+ ", receiptActivity=" + receiptActivity + "]";
	}
}
