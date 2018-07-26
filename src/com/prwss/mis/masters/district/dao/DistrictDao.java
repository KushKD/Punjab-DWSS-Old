package com.prwss.mis.masters.district.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface DistrictDao {
	
	public List<DistrictBean> findDistrict(DistrictBean districtBean, List<String> statusList) throws DataAccessException;
	
	public List<DistrictBean> findDistrict(List<String> districtIds) throws DataAccessException;

	public boolean saveDistrict(DistrictBean districtBean) throws DataAccessException;

	public boolean updateDistrict(DistrictBean districtBean)	throws DataAccessException;
	
	public boolean updateDistrict(List<DistrictBean> districtBeans) throws DataAccessException;

	/*public Set<DistrictBean> getDistinctDistrictCodes() throws DataAccessException;
*/
	public Set<DistrictBean> getDistinctDistrictCodes(List<String> statusList)
			throws DataAccessException;

}
