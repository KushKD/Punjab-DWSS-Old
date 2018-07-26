package com.prwss.mis.admin.divisional.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface DivisionalDao {
	public List<DivisionalBean> findDivisional(DivisionalBean divisionalBean, List<String> statusList) throws DataAccessException;
	
	public List<DivisionalBean> findDivisional(List<String> divisionalIds) throws DataAccessException;

	public boolean saveDivisional(DivisionalBean divisionalBean) throws DataAccessException;

	public boolean updateDivisional(DivisionalBean divisionalBean)	throws DataAccessException;
	
	public boolean updateDivisional(List<DivisionalBean> divisionalBeans) throws DataAccessException;

	public Set<DivisionalBean> getDistinctDivisionalCodes() throws DataAccessException;

	public List<DivisionalBean> getDivisions(String districtId,List<String> locationType);

}
