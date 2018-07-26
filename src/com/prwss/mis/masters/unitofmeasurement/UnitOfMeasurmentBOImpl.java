package com.prwss.mis.masters.unitofmeasurement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.unitofmeasurement.dao.UnitOfMeasurementBean;
import com.prwss.mis.masters.unitofmeasurement.dao.UnitOfMeasurementDao;
import com.prwss.mis.masters.unitofmeasurement.struts.UnitOfMeasurementForm;

public class UnitOfMeasurmentBOImpl implements UnitOfMeasurementBO{
	
	private UnitOfMeasurementDao unitOfMeasurementDao;
	
	
	public void setUnitOfMeasurementDao(UnitOfMeasurementDao unitOfMeasurementDao) {
		this.unitOfMeasurementDao = unitOfMeasurementDao;
	}
	Logger log=Logger.getLogger(UnitOfMeasurmentBOImpl.class);

	@Override
	public List<UnitOfMeasurementBean> findUnitOfMeasurement(UnitOfMeasurementForm unitOfMeasurementForm, List<String> statusList)
			throws MISException {
		
				List<UnitOfMeasurementBean> unitOfMeasurementBeans = null;
		
		try {
			UnitOfMeasurementBean unitOfMeasurementBean = populateUnitOfMeasurementBean(unitOfMeasurementForm);
			
			unitOfMeasurementBeans = unitOfMeasurementDao.findUnitOfMeasurements(unitOfMeasurementBean, statusList);
			
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e) {
			throw new MISException(e);
		}
		
		return unitOfMeasurementBeans;
	}

	@Override
	public boolean saveUnitOfMeasurement(
			UnitOfMeasurementForm unitOfMeasurementForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status=false;
		try {
			List<String> unitOfMeasurementIds = new ArrayList<String>();
			unitOfMeasurementIds.add(unitOfMeasurementForm.getUnitOfMeasurementId());
			List<UnitOfMeasurementBean> unitOfMeasurementBeans = unitOfMeasurementDao.findUnitOfMeasurement(unitOfMeasurementIds);
			if(!MisUtility.ifEmpty(unitOfMeasurementBeans)){				
				throw new MISException(MISExceptionCodes.MIS001,"Duplicate Entry");
			}
			UnitOfMeasurementBean unitOfMeasurementBean = populateUnitOfMeasurementBean(unitOfMeasurementForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			unitOfMeasurementBean.setMisAuditBean(misAuditBean);			
			status = unitOfMeasurementDao.saveUnitOfMeasurement(unitOfMeasurementBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}

	@Override
	public boolean updateUnitOfMeasurement(
			UnitOfMeasurementForm unitOfMeasurementForm,
			MISSessionBean misSessionBean) throws MISException {
	boolean Status=false;
		try{
			UnitOfMeasurementBean unitOfMeasurementBean = populateUnitOfMeasurementBean(unitOfMeasurementForm);		
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			unitOfMeasurementBean.setMisAuditBean(misAuditBean);	
	
			Status=unitOfMeasurementDao.updateUnitOfMeasurement(unitOfMeasurementBean);
			} catch (DataAccessException e) {
				log.error(e.getLocalizedMessage(),e);
				throw new MISException(e);
			} catch (MISException e) {
				log.error(e.getLocalizedMessage(),e);
				throw e;
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(),e);
				throw new MISException(e);
			}
	return Status;
	}

	@Override
	public boolean deleteUnitOfMeasurement(
			UnitOfMeasurementForm unitOfMeasurementForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
		try{
			UnitOfMeasurementBean unitOfMeasurementBean = populateUnitOfMeasurementBean(unitOfMeasurementForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			unitOfMeasurementBean.setMisAuditBean(misAuditBean);
			result = unitOfMeasurementDao.updateUnitOfMeasurement(unitOfMeasurementBean);
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}
private UnitOfMeasurementBean populateUnitOfMeasurementBean(UnitOfMeasurementForm unitOfMeasurementForm) throws Exception{
		
		UnitOfMeasurementBean unitOfMeasurementBean;
		try {
			unitOfMeasurementBean = new UnitOfMeasurementBean();
			unitOfMeasurementBean.setMeasurementId(unitOfMeasurementForm.getUnitOfMeasurementId());
			unitOfMeasurementBean.setMeasurementName(unitOfMeasurementForm.getUnitOfMeasurementName());
			unitOfMeasurementBean.setMeasurementDescription(unitOfMeasurementForm.getUnitOfMeasurementDesc());
		} catch (Exception e) {
			throw e;
		}
		
		return unitOfMeasurementBean;
	}

}
