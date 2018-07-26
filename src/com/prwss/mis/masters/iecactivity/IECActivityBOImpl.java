package com.prwss.mis.masters.iecactivity;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.iecactivity.dao.IECActivityBean;
import com.prwss.mis.masters.iecactivity.dao.IECActivityDao;
import com.prwss.mis.masters.iecactivity.struts.IecActivityForm;

public class IECActivityBOImpl implements IECActivityBO {
	
	private Logger log = Logger.getLogger(IECActivityBean.class);
	private IECActivityDao iecActivityDao;
	
	
	public void setIecActivityDao(IECActivityDao iecActivityDao) {
		this.iecActivityDao = iecActivityDao;
	}

	@Override
	public List<IECActivityBean> findIECActivity(IecActivityForm iecActivityForm, List<String> statusList)
			throws MISException {
		List<IECActivityBean> iecActivityBeans = null;
		
		try {
			IECActivityBean iecActivityBean = populateActivityBean(iecActivityForm);
			iecActivityBeans = iecActivityDao.findIECActivity(iecActivityBean, statusList);
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return iecActivityBeans;
	}

	@Override
	public String saveIECActivity(IecActivityForm iecActivityForm, MISSessionBean misSessionBean) throws MISException {
		String iecActivityId = null;
		try {
			IECActivityBean iecActivityBean = populateActivityBean(iecActivityForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			iecActivityBean.setMisAuditBean(misAuditBean);
			iecActivityId = iecActivityDao.saveIECActivity(iecActivityBean);
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return iecActivityId;
	}

	@Override
	public boolean updateIECActivity(IecActivityForm iecActivityForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status = false;
		 try {
			IECActivityBean iecActivityBean = populateActivityBean(iecActivityForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			iecActivityBean.setMisAuditBean(misAuditBean);
			status = iecActivityDao.updateIECActivity(iecActivityBean);
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean deleteIECActivity(IecActivityForm iecActivityForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status = false;
		 try {
			List<IECActivityBean> iecActivityCodes = iecActivityDao.findIECActivity(Arrays.asList(iecActivityForm.getIecActivityCodes()));
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			
			for (IECActivityBean iecActivityBean : iecActivityCodes) {
				iecActivityBean.setMisAuditBean(misAuditBean);
			}
			status = iecActivityDao.updateIECActivity(iecActivityCodes);
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean postIECActivity(IecActivityForm iecActivityForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		 try {
			List<IECActivityBean> iecActivityCodes = iecActivityDao.findIECActivity(Arrays.asList(iecActivityForm.getIecActivityCodes()));
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			
			for (IECActivityBean iecActivityBean : iecActivityCodes) {
				iecActivityBean.setMisAuditBean(misAuditBean);
			}
			status = iecActivityDao.updateIECActivity(iecActivityCodes);
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}
	
	private IECActivityBean populateActivityBean(IecActivityForm iecActivityForm){
		
		IECActivityBean iecActivityBean = new IECActivityBean();
		iecActivityBean.setIecActivityId(iecActivityForm.getIecActivityCode());
		iecActivityBean.setIecActivityName(iecActivityForm.getIecActivityName());
		iecActivityBean.setIecActivityDescription(iecActivityForm.getIecActivityDescription());
		
		return iecActivityBean;
	}
	
}
