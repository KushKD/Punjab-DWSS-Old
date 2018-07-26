package com.prwss.mis.common.tenderCheck;

import java.util.List;

import com.prwss.mis.procurement.check.TenderCheckBean;
import com.prwss.mis.tender.biddersdetail.dao.BidderHeaderBean;
import com.prwss.mis.tender.dao.TenderHeaderBean;

public interface TenderCheckDao {
	
	public List<TenderHeaderBean> findAllTenders();
	public List<TenderCheckBean> findAllTender(String tenderId);
		

}
