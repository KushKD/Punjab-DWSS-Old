package com.prwss.mis.pmm.village.household.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;


public interface VillageHouseHoldDao {

	public List<VillageHouseHoldBean> findVillageHouseHold(VillageHouseHoldBean villageHouseHoldBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveVillageHouseHold(Collection<VillageHouseHoldBean> villageHouseHoldBeans) throws DataAccessException;

	public boolean saveOrUpdateVillageHouseHold(Collection<VillageHouseHoldBean> villageHouseHoldBeans)	throws DataAccessException;

}
