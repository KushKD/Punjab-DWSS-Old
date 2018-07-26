package com.prwss.mis.tender.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.tender.biddersdetail.dao.BidderDetailBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class BiddersDetailForm extends ValidatorForm {
/**
	 * 
	 */
private static final long serialVersionUID = -1589908173571520959L;
private String locationId;
private String tenderId;
private long bidInfoId;
private int biddingPhase;
private String bidOpeningDate;
private Datagrid bidderDetailDatagrid;
private String referenceTenderId;
private String bidderNameId;
private List<BidderDetailBean> BidderDetailBean = null;
 
public List<BidderDetailBean> getBidderDetailBean() {
	return BidderDetailBean;
}
public void setBidderDetailBean(List<BidderDetailBean> bidderDetailBean) {
	BidderDetailBean = bidderDetailBean;
}
public String getReferenceTenderId() {
	return referenceTenderId;
}
public void setReferenceTenderId(String referenceTenderId) {
	this.referenceTenderId = referenceTenderId;
}
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
public String getBidOpeningDate() {
	return bidOpeningDate;
}
public void setBidOpeningDate(String bidOpeningDate) {
	this.bidOpeningDate = bidOpeningDate;
}
public Datagrid getBidderDetailDatagrid() {
	return bidderDetailDatagrid;
}
public void setBidderDetailDatagrid(Datagrid bidderDetailDatagrid) {
	this.bidderDetailDatagrid = bidderDetailDatagrid;
}
public void setBidderNameId(String bidderNameId) {
	this.bidderNameId = bidderNameId;
}
public String getBidderNameId() {
	return bidderNameId;
}
 
}
