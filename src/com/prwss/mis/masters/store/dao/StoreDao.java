package com.prwss.mis.masters.store.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface StoreDao {
	
	public List<StoreBean> findStore(StoreBean storeBean, List<String> statusList) throws DataAccessException;
	
	public List<StoreBean> findStore(List<Long> storeIds) throws DataAccessException;

	public long saveStore(StoreBean storeBean) throws DataAccessException;

	public boolean updateStore(StoreBean storeBean)	throws DataAccessException;
	
	public boolean updateStore(List<StoreBean> storeBeans) throws DataAccessException;

	public Set<StoreBean> getDistinctStoreCodes(String locationId) throws DataAccessException;
}
