package com.prwss.mis.tender;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.tender.biddersdetail.dao.BidderHeaderBean;
import com.prwss.mis.tender.struts.BiddersDetailForm;

public interface BidderBO {
	
	public List<BidderHeaderBean> findBidder(BiddersDetailForm bidderDetailForm, List<String> statusList) throws MISException;
	
	public long saveBidder(BiddersDetailForm bidderDetailForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateBidder(BiddersDetailForm bidderDetailForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteBidder(BiddersDetailForm bidderDetailForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postBidder(BiddersDetailForm bidderDetailForm, MISSessionBean misSessionBean) throws MISException;


}
