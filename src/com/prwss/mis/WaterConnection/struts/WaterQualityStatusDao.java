package com.prwss.mis.WaterConnection.struts;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.masters.location.dao.LocationBean;

public interface WaterQualityStatusDao {

	public List<WaterQualityStatusBean> getWaterQualityStatus(String villageId) throws DataAccessException;
	public Set<LocationBean> getDistrictLocationIds(String locationType)
			throws DataAccessException;
}
