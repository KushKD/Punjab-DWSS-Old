package com.prwss.mis.tender.biddersdetail.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface BidderHeaderDao {
	
	public List<BidderHeaderBean> findBidderHeader(BidderHeaderBean bidderHeaderBean, List<String> statusList) throws DataAccessException;
	
	public long saveBidderHeader(BidderHeaderBean bidderHeaderBean) throws DataAccessException;

	public boolean updateBidderHeader(BidderHeaderBean bidderHeaderBean) throws DataAccessException;
	
}
