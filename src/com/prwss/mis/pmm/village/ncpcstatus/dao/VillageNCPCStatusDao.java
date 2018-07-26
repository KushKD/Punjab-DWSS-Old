package com.prwss.mis.pmm.village.ncpcstatus.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface VillageNCPCStatusDao {
public List<VillageNCPCStatusBean> findVillageNCPCStatus(VillageNCPCStatusBean villageNCPCStatusBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveVillageNCPCStatus(Collection<VillageNCPCStatusBean> villageNCPCStatusBeans) throws DataAccessException;
	
	public boolean saveOrUpdateVillageNCPCStatus(Collection<VillageNCPCStatusBean> villageNCPCStatusBeans) throws DataAccessException;

	public VillageNCPCStatusBean getVillNcpcStatusBeanById(String villageId)
			throws DataAccessException;
}
