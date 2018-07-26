package com.prwss.mis.masters.unitofmeasurement.dao;

import java.util.List;
import java.util.Set;
import org.springframework.dao.DataAccessException;

public interface UnitOfMeasurementDao {
	public List<UnitOfMeasurementBean> findUnitOfMeasurements(UnitOfMeasurementBean unitOfMeasurementBean, List<String> statusList) throws DataAccessException;
	
	public List<UnitOfMeasurementBean> findUnitOfMeasurement(List<String> unitOfMeasurementIds) throws DataAccessException;

	public boolean saveUnitOfMeasurement(UnitOfMeasurementBean unitOfMeasurementBean) throws DataAccessException;

	public boolean updateUnitOfMeasurement(UnitOfMeasurementBean unitOfMeasurementBean)	throws DataAccessException;
	
	public boolean updateUnitOfMeasurement(List<UnitOfMeasurementBean> unitOfMeasurementBean) throws DataAccessException;

	public Set<UnitOfMeasurementBean> getUnitOfMeasurementIds(String locationId) throws DataAccessException;

}
