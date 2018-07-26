package com.prwss.mis.tender.struts;

public class ResponsiveBiddersModel {
	
	
	
	private String tender_id;
	public String getTender_id() {
		return tender_id;
	}
	public void setTender_id(String tender_id) {
		this.tender_id = tender_id;
	}
	public String getBidder_name() {
		return bidder_name;
	}
	public void setBidder_name(String bidder_name) {
		this.bidder_name = bidder_name;
	}
	public String getNot_responsive() {
		return not_responsive;
	}
	public void setNot_responsive(String not_responsive) {
		this.not_responsive = not_responsive;
	}
	public String getBid_info_id() {
		return bid_info_id;
	}
	public void setBid_info_id(String bid_info_id) {
		this.bid_info_id = bid_info_id;
	}
	
	/**
	 * @return the vendor_name
	 */
	public String getVendor_name() {
		return vendor_name;
	}
	/**
	 * @param vendor_name the vendor_name to set
	 */
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	private String vendor_name;
	private String bidder_name;
	private String not_responsive;
	private String bid_info_id;
	@Override
	public String toString() {
		return "ResponsiveBiddersModel [tender_id=" + tender_id
				+ ", bidder_name=" + bidder_name + ", not_responsive="
				+ not_responsive + ", bid_info_id=" + bid_info_id + "]";
	}
	
	
	
	

}
