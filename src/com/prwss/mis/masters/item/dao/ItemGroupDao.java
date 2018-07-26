package com.prwss.mis.masters.item.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface ItemGroupDao {
	
	public List<ItemGroupBean> findItemGroup(ItemGroupBean itemGroupBean, List<String> statusList) throws DataAccessException;
	
	public List<ItemGroupBean> findItemGroup(List<String> itemGroupIds) throws DataAccessException;

	public boolean saveItemGroup(ItemGroupBean itemGroupBean) throws DataAccessException;

	public boolean updateItemGroup(ItemGroupBean itemGroupBean)	throws DataAccessException;
	
	public boolean updateItemGroup(List<ItemGroupBean> itemGroupBeans) throws DataAccessException;
	
	public Set<ItemGroupBean> getDistinctItemGroupCodes() throws DataAccessException;


}
