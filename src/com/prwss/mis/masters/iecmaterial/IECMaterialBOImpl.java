package com.prwss.mis.masters.iecmaterial;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.iecmaterial.dao.IECMaterialBean;
import com.prwss.mis.masters.iecmaterial.dao.IECMaterialDao;
import com.prwss.mis.masters.iecmaterial.struts.IecMaterialForm;

public class IECMaterialBOImpl implements IECMaterialBO {
	
	private Logger log = Logger.getLogger(IECMaterialBOImpl.class);
	private IECMaterialDao iecMaterialDao;

	public void setIecMaterialDao(IECMaterialDao iecMaterialDao) {
		this.iecMaterialDao = iecMaterialDao;
	}

	@Override
	public List<IECMaterialBean> findIECMaterial(IecMaterialForm iecMaterialForm, List<String> statusList)
			throws MISException {
		List<IECMaterialBean> iecMaterialBeans = null;
		try {
			IECMaterialBean iecMaterialBean = populateIECMaterialBean(iecMaterialForm);			
			iecMaterialBeans = iecMaterialDao.findIECMaterial(iecMaterialBean, statusList);
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return iecMaterialBeans;
	}

	@Override
	public String saveIECMaterial(IecMaterialForm iecMaterialForm, MISSessionBean misSessionBean) throws MISException {
		String iecMaterialId = null;
		try {
			IECMaterialBean iecMaterialBean = populateIECMaterialBean(iecMaterialForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			iecMaterialBean.setMisAuditBean(misAuditBean);
			iecMaterialId = iecMaterialDao.saveIECMaterial(iecMaterialBean);
		}  catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return iecMaterialId;
	}

	@Override
	public boolean updateIECMaterial(IecMaterialForm iecMaterialForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			IECMaterialBean iecMaterialBean = populateIECMaterialBean(iecMaterialForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			iecMaterialBean.setMisAuditBean(misAuditBean);
			status = iecMaterialDao.updateIECMaterial(iecMaterialBean);
		}  catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean deleteIECMaterial(IecMaterialForm iecMaterialForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			IECMaterialBean iecMaterialBean = populateIECMaterialBean(iecMaterialForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			iecMaterialBean.setMisAuditBean(misAuditBean);
			status = iecMaterialDao.updateIECMaterial(iecMaterialBean);
		}  catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean postIECMaterial(IecMaterialForm iecMaterialForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			IECMaterialBean iecMaterialBean = populateIECMaterialBean(iecMaterialForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			iecMaterialBean.setMisAuditBean(misAuditBean);
			status = iecMaterialDao.updateIECMaterial(iecMaterialBean);
		}  catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}
	
	private IECMaterialBean populateIECMaterialBean(IecMaterialForm iecMaterialForm){
		IECMaterialBean iecMaterialBean = new IECMaterialBean();
		iecMaterialBean.setIecMaterialId(iecMaterialForm.getIecMaterialCode());
		iecMaterialBean.setIecMaterialName(iecMaterialForm.getIecMaterialName());
		iecMaterialBean.setIecMaterialDescription(iecMaterialForm.getIecMaterialDescription());
		
		return iecMaterialBean;
	}

}
