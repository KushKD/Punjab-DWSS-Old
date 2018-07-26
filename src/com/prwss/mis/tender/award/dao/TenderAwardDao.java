package com.prwss.mis.tender.award.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface TenderAwardDao {
	
	public List<TenderAwardBean> findTenderAwarded(TenderAwardBean tenderAwardBean, List<String> statusList) throws DataAccessException;
	
	public String saveTenderAwarded(TenderAwardBean tenderAwardBean) throws DataAccessException;
	
	public boolean updateTenderAwarded(TenderAwardBean tenderAwardBean) throws DataAccessException;
	
	
}
