/**
 * 
 */
package com.prwss.mis.finance.adjustmentvoucher;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.finance.voucher.VoucherHeaderBean;

/**
 * @author PJHA
 *
 */
@Entity
@Table(name="t_finance_adjust_voucher_hdr", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class AdjustmentVoucherBean implements Serializable,Comparable<AdjustmentVoucherBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3396866238768919499L;
	@Id
	@SequenceGenerator(name="seq_t_adjustmentvoucher_id", sequenceName="prwss_main.seq_t_adjustmentvoucher_id")
	@GeneratedValue(generator="seq_t_adjustmentvoucher_id", strategy=GenerationType.AUTO)
	@Column(name="voc_id", nullable=false)
	private long vocId;
	
	@Column(name="voc_date")
	private Date  vocDate;

	@Column(name="program_id")
	private String  programId;

	@Column(name="location_id")
	private String  locationId;

	@Column(name="remarks")
	private String  remarks;
	
	@ManyToOne(targetEntity=VoucherHeaderBean.class)
	@JoinColumn(name="payment_voc_id", referencedColumnName ="voc_id")
	private VoucherHeaderBean  paymentVocId;
	
	@ManyToOne(targetEntity=VoucherHeaderBean.class)
	@JoinColumn(name="receipt_voc_id", referencedColumnName ="voc_id")
	private VoucherHeaderBean  receiptVocId;
	 
	  
	@Embedded
	private MISAuditBean misAuditBean;
	  
	public long getVocId() {
		return vocId;
	}


	public void setVocId(long vocId) {
		this.vocId = vocId;
	}



	public Date getVocDate() {
		return vocDate;
	}



	public void setVocDate(Date vocDate) {
		this.vocDate = vocDate;
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



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public VoucherHeaderBean getPaymentVocId() {
		return paymentVocId;
	}



	public void setPaymentVocId(VoucherHeaderBean paymentVocId) {
		this.paymentVocId = paymentVocId;
	}



	public VoucherHeaderBean getReceiptVocId() {
		return receiptVocId;
	}



	public void setReceiptVocId(VoucherHeaderBean receiptVocId) {
		this.receiptVocId = receiptVocId;
	}



	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}



	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}



	

	@Override
	public String toString() {
		return "AdjustmentVoucherBean [vocId=" + vocId + ", vocDate=" + vocDate
				+ ", programId=" + programId + ", locationId=" + locationId
				+ ", remarks=" + remarks + ", paymentVocId=" + paymentVocId
				+ ", receiptVocId=" + receiptVocId + ", misAuditBean="
				+ misAuditBean + "]";
	}


	@Override
	public int compareTo(AdjustmentVoucherBean o) {
		
		return new Long(this.vocId).compareTo(o.vocId);
		
		
	}

}
