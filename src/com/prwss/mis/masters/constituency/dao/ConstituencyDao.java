package com.prwss.mis.masters.constituency.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface ConstituencyDao {
	
	public Set<ConstituencyBean> findConstituency(String districtId) throws DataAccessException;

	List<ConstituencyBean> findConstituency(ConstituencyBean constituencyBean,
			List<String> statusList) throws DataAccessException;

	boolean saveConstituency(ConstituencyBean constituencyBean)
			throws DataAccessException;

	boolean updateConstituency(ConstituencyBean constituencyBean)
			throws DataAccessException;

	boolean updateConstituency(List<ConstituencyBean> constituencyBeans)
			throws DataAccessException;

	Set<ConstituencyBean> getDistinctConstituencyIds()
			throws DataAccessException;

	List<ConstituencyBean> findConstituency(List<String> constituencyIds)
			throws DataAccessException;

}
