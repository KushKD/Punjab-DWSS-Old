package com.prwss.mis.admin.dao;


import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface MenuDao {	
	public Set<MenuBean> getMenuIds() throws DataAccessException ;
	public List<MenuBean> getMenuNames(MenuBean menuBean) throws DataAccessException;
}