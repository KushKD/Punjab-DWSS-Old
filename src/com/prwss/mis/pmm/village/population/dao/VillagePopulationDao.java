package com.prwss.mis.pmm.village.population.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface VillagePopulationDao {
	
	public List<VillagePopulationBean> findVillagePopulation(VillagePopulationBean villagePopulationBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveVillagePopulation(Collection<VillagePopulationBean> villagePopulationBeans) throws DataAccessException;

	public boolean saveOrUpdateVillagePopulation(Collection<VillagePopulationBean> villagePopulationBeans)	throws DataAccessException;
	

}
