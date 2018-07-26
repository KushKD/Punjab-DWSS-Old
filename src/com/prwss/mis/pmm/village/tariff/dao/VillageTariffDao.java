package com.prwss.mis.pmm.village.tariff.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface VillageTariffDao {

	public List<VillageTariffBean> findVillageTariff(VillageTariffBean villageTariffBean, List<String> statusList) throws DataAccessException;
	
	public boolean saveVillageTariff(Collection<VillageTariffBean> villageTariffBeans) throws DataAccessException;

	public boolean saveOrUpdateVillageTariff(Collection<VillageTariffBean> villageTariffBeans)	throws DataAccessException;

	
}
