package com.prwss.mis.tender.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface TenderDetailDao {
	
	public Set<TenderDetailBean> findTenderDetails(String tenderId) throws DataAccessException;
	public boolean saveOrupdateTenderDetails(List<TenderDetailBean> tenderDetailBeans) throws DataAccessException;
}
