package com.prwss.mis.pmm.village.connection.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface VillageConnectionDao {
	
	
	public List<VillageConnectionBean> findVillageConnection(VillageConnectionBean villageConnectionBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveVillageConnection(Collection<VillageConnectionBean> villageConnectionBeans) throws DataAccessException;

	public boolean saveOrUpdateVillageConnection(Collection<VillageConnectionBean> villageConnectionBeans)	throws DataAccessException;
	
	//public boolean deleteVillageConnection(Collection<VillageConnectionBean> villageConnectionBeans)	throws DataAccessException;

}
