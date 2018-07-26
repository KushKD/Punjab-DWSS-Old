package com.prwss.mis.tender.biddersdetail.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;

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
@Table(name="t_tender_bidder_detail", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class BidderDetailBean implements Serializable {

	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -2234569083113434751L;

	
	@Id
	@GeneratedValue(generator = "seq_bid_id", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_bid_id", sequenceName = "prwss_main.seq_bid_id")
	@Column(name = "seq_bid_id", nullable = false)
	private long seqBidId;
	
	
	@Column(name="bid_info_id", nullable=false)
	private long bidInfoId;
	
	
	@Column(name="bidder_name", nullable=false)
	private String bidderName;
	
	@Column(name="bid_sale_date")
	private Date bidSaleDate;
	
	@Column(name="bid_submitted")
	private String bidSubmitted;
	
	@Column(name="emd_amount")
	private double emdAmount;
	
	@Column(name="emd_valid_upto")
	private Date emdValidUpto;
	
	@Column(name="emd_instrument_type")
	private String emdInstrumentType;
	
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="not_responsive")
	private String notResponsive;
	
	@Column(name="bidder_contact_number")
	private String contactNumber;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="bid_amount")
	private double bidAmount;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getBidInfoId() {
		return bidInfoId;
	}

	public void setBidInfoId(long bidInfoId) {
		this.bidInfoId = bidInfoId;
	}

	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}

	public Date getBidSaleDate() {
		return bidSaleDate;
	}

	public void setBidSaleDate(Date bidSaleDate) {
		this.bidSaleDate = bidSaleDate;
	}

	public String getBidSubmitted() {
		return bidSubmitted;
	}

	public void setBidSubmitted(String bidSubmitted) {
		this.bidSubmitted = bidSubmitted;
	}

	public double getEmdAmount() {
		return emdAmount;
	}

	public void setEmdAmount(double emdAmount) {
		this.emdAmount = emdAmount;
	}

	public Date getEmdValidUpto() {
		return emdValidUpto;
	}

	public void setEmdValidUpto(Date emdValidUpto) {
		this.emdValidUpto = emdValidUpto;
	}

	public String getEmdInstrumentType() {
		return emdInstrumentType;
	}

	public void setEmdInstrumentType(String emdInstrumentType) {
		this.emdInstrumentType = emdInstrumentType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getNotResponsive() {
		return notResponsive;
	}

	public void setNotResponsive(String notResponsive) {
		this.notResponsive = notResponsive;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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
		return "BiddersDetailBean [bidInfoId=" + bidInfoId + ", bidderName=" + bidderName + ", bidSaleDate="
				+ bidSaleDate + ", bidSubmitted=" + bidSubmitted + ", bidAmount="+bidAmount+", emdAmount=" + emdAmount + ", emdValidUpto="
				+ emdValidUpto + ", emdInstrumentType=" + emdInstrumentType + ", bankName=" + bankName
				+ ", notResponsive=" + notResponsive + ", contactNumber=" + contactNumber + ", remarks=" + remarks
				+ ", misAuditBean=" + misAuditBean + "]";
	}

	public long getSeqBidId() {
		return seqBidId;
	}

	public void setSeqBidId(long seqBidId) {
		this.seqBidId = seqBidId;
	}

	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}

	public double getBidAmount() {
		return bidAmount;
	}



	
	
	
	
	
}
