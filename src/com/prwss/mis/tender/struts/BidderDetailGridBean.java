package com.prwss.mis.tender.struts;

import java.io.Serializable;

public class BidderDetailGridBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5097098399561968491L;

	private long bidInfoId;
	
	private long seqBidId;
	private String bidderName;
	
	private String bidSaleDate;
	
	private String bidSubmitted;
	
	private double bidAmount;
	
	private double emdAmount;
	
	private String emdValidUpto;
	
	private String emdInstrumentType;
	
	private String bankName;
	
	private String notResponsive;
	
	private String contactNumber;
	
	private String remarks;
	
	private String other;
	
	
	public long getSeqBidId() {
		return seqBidId;
	}

	public void setSeqBidId(long seqBidId) {
		this.seqBidId = seqBidId;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

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

	public String getBidSaleDate() {
		return bidSaleDate;
	}

	public void setBidSaleDate(String bidSaleDate) {
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

	public String getEmdValidUpto() {
		return emdValidUpto;
	}

	public void setEmdValidUpto(String emdValidUpto) {
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

	@Override
	public String toString() {
		return "BidderDetailGridBean [bidInfoId=" + bidInfoId + ", seqBidId="
				+ seqBidId + ", bidderName=" + bidderName + ", bidSaleDate="
				+ bidSaleDate + ", bidSubmitted=" + bidSubmitted
				+ "bidAmount="+bidAmount+", emdAmount=" + emdAmount + ", emdValidUpto=" + emdValidUpto
				+ ", emdInstrumentType=" + emdInstrumentType + ", bankName="
				+ bankName + ", notResponsive=" + notResponsive
				+ ", contactNumber=" + contactNumber + ", remarks=" + remarks
				+ ", other=" + other + "]";
	}

	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}

	public double getBidAmount() {
		return bidAmount;
	}

	
	
	
}
