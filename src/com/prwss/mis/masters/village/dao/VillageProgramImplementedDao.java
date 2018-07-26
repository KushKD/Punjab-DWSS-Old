package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.masters.village.VillageProgramImplementedBean;

public interface VillageProgramImplementedDao {
	
	public List<VillageProgramImplementedBean> findVillageImplemented(VillageProgramImplementedBean villageProgramImplementedBean, List<String> statusList) throws DataAccessException;
	
	public boolean updateVillageImplemented(VillageProgramImplementedBean villageProgramImplementedBean) throws DataAccessException;
	
	public boolean saveVillageImplemented(VillageProgramImplementedBean villageProgramImplementedBean) throws DataAccessException;

}
