package com.prwss.mis.masters.unitofmeasurement;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.unitofmeasurement.dao.UnitOfMeasurementBean;
import com.prwss.mis.masters.unitofmeasurement.struts.UnitOfMeasurementForm;


public interface UnitOfMeasurementBO {
public List<UnitOfMeasurementBean> findUnitOfMeasurement(UnitOfMeasurementForm unitOfMeasurementForm, List<String> statusList) throws MISException;
	
	public boolean saveUnitOfMeasurement(UnitOfMeasurementForm unitOfMeasurementForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateUnitOfMeasurement(UnitOfMeasurementForm unitOfMeasurementForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteUnitOfMeasurement(UnitOfMeasurementForm unitOfMeasurementForm, MISSessionBean misSessionBean) throws MISException ;

}
