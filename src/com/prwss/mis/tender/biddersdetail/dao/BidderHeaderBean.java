package com.prwss.mis.tender.biddersdetail.dao;

import java.io.Serializable;
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
@Table(name="t_tender_bidder_hdr", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class BidderHeaderBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 6163541390640145180L;

	@Id
	@SequenceGenerator(name="seq_bid_info_id", sequenceName="prwss_main.seq_bid_info_id")
	@GeneratedValue(generator="seq_bid_info_id", strategy=GenerationType.AUTO)
	@Column(name="bid_info_id", nullable=false)
	private long bidInfoId;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="tender_id")
	private String tenderId;
	
	@Column(name="bidding_phase")
	private int biddingPhase;
	
	@Column(name="bid_opening_date")
	private Date bidOpeningDate;
	
	@Column(name="reference_tender_id")
	private String referenceTenderId;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	@OneToMany(targetEntity=BidderDetailBean.class, fetch=FetchType.EAGER)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="bid_info_id", insertable=false, updatable=false)
	private Set<BidderDetailBean> bidderDetailBeans;

	public long getBidInfoId() {
		return bidInfoId;
	}

	public void setBidInfoId(long bidInfoId) {
		this.bidInfoId = bidInfoId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public int getBiddingPhase() {
		return biddingPhase;
	}

	public void setBiddingPhase(int biddingPhase) {
		this.biddingPhase = biddingPhase;
	}

	public Date getBidOpeningDate() {
		return bidOpeningDate;
	}

	public void setBidOpeningDate(Date bidOpeningDate) {
		this.bidOpeningDate = bidOpeningDate;
	}

	public String getReferenceTenderId() {
		return referenceTenderId;
	}

	public void setReferenceTenderId(String referenceTenderId) {
		this.referenceTenderId = referenceTenderId;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "BidderHeaderBean [bidInfoId=" + bidInfoId + ", locationId=" + locationId + ", tenderId=" + tenderId
				+ ", biddingPhase=" + biddingPhase + ", bidOpeningDate=" + bidOpeningDate + ", referenceTenderId="
				+ referenceTenderId + ", misAuditBean=" + misAuditBean + "]";
	}

	public void setBidderDetailBeans(Set<BidderDetailBean> bidderDetailBeans) {
		this.bidderDetailBeans = bidderDetailBeans;
	}

	public Set<BidderDetailBean> getBidderDetailBeans() {
		return bidderDetailBeans;
	}

}
