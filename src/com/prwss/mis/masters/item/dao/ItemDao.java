package com.prwss.mis.masters.item.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.masters.store.dao.StoreBean;

public interface ItemDao {
	
	public List<ItemBean> findItem(ItemBean itemBean, List<String> statusList) throws DataAccessException;
	
	public List<ItemBean> findItem(List<String> itemIds) throws DataAccessException;

	public boolean saveItem(ItemBean itemBean) throws DataAccessException;

	public boolean updateItem(ItemBean itemBean)	throws DataAccessException;
	
	public boolean updateItem(List<ItemBean> itemBeans) throws DataAccessException;

	public Set<ItemBean> getDistinctItemCodes(String itemGroupId, StoreBean store) throws DataAccessException;
	
	public List<ItemBean> getItemList(String itemgroup)throws DataAccessException;

}
