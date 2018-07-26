package com.prwss.mis.tender.biddersdetail.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface BidderDetailDao {
	
	public List<BidderDetailBean> getBidderDetailBeans(BidderDetailBean bidderDetailBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveOrUpdateBidderDetailBeans(Collection<BidderDetailBean> bidderDetailBeans) throws DataAccessException;
	
	public boolean saveOrUpdateBidderDetailBeans1(Collection<BidderDetailBean> bidderDetailBeans) throws DataAccessException;
	public boolean saveOrUpdateBidderDetailBeans2(Collection<BidderDetailBean> bidderDetailBeans) throws DataAccessException;
	public boolean saveOrUpdateBidderDetailBeans3(Collection<BidderDetailBean> bidderDetailBeans) throws DataAccessException;
	

}
