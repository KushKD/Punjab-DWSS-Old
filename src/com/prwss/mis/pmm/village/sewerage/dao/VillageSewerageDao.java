package com.prwss.mis.pmm.village.sewerage.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface VillageSewerageDao {
	
	public List<VillageSewerageBean> findVillageSewerage(VillageSewerageBean villageSewerageBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveVillageSewerage(Collection<VillageSewerageBean> villageSewerageBeans) throws DataAccessException;
}
