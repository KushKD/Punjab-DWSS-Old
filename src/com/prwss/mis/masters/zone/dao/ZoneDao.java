package com.prwss.mis.masters.zone.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface ZoneDao {
	
	public List<ZoneBean> findZone(ZoneBean zoneBean, List<String> statusList) throws DataAccessException;
	
	public List<ZoneBean> findZone(List<String> zoneIds) throws DataAccessException;

	public String saveZone(ZoneBean zoneBean) throws DataAccessException;

	public boolean updateZone(ZoneBean zoneBean)	throws DataAccessException;
	
	public boolean updateZone(List<ZoneBean> zoneBeans) throws DataAccessException;

	public Set<ZoneBean> getDistinctZoneCodes() throws DataAccessException;

}
