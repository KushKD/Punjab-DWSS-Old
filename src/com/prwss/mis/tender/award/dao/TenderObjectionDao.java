package com.prwss.mis.tender.award.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface TenderObjectionDao {
	
	public List<TenderObjectionBean> getTenderObjectionBeans(TenderObjectionBean tenderObjectionBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveOrUpdateTenderObjectionBeans(Collection<TenderObjectionBean> tenderObjectionBeans) throws DataAccessException;

}
