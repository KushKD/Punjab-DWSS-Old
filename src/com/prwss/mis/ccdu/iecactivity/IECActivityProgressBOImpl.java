package com.prwss.mis.ccdu.iecactivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.ccdu.MaterialUtilizationGridBean;
import com.prwss.mis.ccdu.iecactivity.dao.IECActivityMaterialUtilizationBean;
import com.prwss.mis.ccdu.iecactivity.dao.IECActivityMaterialUtilizationDao;
import com.prwss.mis.ccdu.iecactivity.dao.IECActivityProgressBean;
import com.prwss.mis.ccdu.iecactivity.dao.IECActivityProgressDao;
import com.prwss.mis.ccdu.iecactivity.struts.IecActivityProgressForm;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.iecactivity.dao.IECActivityBean;
import com.prwss.mis.masters.iecmaterial.dao.IECMaterialBean;
import com.prwss.mis.masters.item.dao.ItemBean;

public class IECActivityProgressBOImpl implements IECActivityProgressBO {
	
	private Logger log = Logger.getLogger(IECActivityProgressBOImpl.class);
	private IECActivityProgressDao iecActivityProgressDao;
	private IECActivityMaterialUtilizationDao iecActivityMaterialUtilizationDao;
	

	public void setIecActivityProgressDao(IECActivityProgressDao iecActivityProgressDao) {
		this.iecActivityProgressDao = iecActivityProgressDao;
	}

	public void setIecActivityMaterialUtilizationDao(IECActivityMaterialUtilizationDao iecActivityMaterialUtilizationDao) {
		this.iecActivityMaterialUtilizationDao = iecActivityMaterialUtilizationDao;
	}

	@Override
	public List<IECActivityProgressBean> findCBIECActivityProgress(IecActivityProgressForm iecActivityProgressForm,
			List<String> statusList) throws MISException {
		List<IECActivityProgressBean> iecActivityProgressBeans = null;
		System.out.println("Finf Bo"+iecActivityProgressForm.getIecProgressId());
		try {
			IECActivityProgressBean iecActivityProgressBean = populateIecActivityProgressBean(iecActivityProgressForm);
			
			iecActivityProgressBeans = iecActivityProgressDao.findCBIECActivityProgress(iecActivityProgressBean, statusList);
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return iecActivityProgressBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveCBIECActivityProgress(IecActivityProgressForm iecActivityProgressForm, MISSessionBean misSessionBean)
			throws MISException {
		long iecProgressId = 0;
		try {
			
			System.out.println("in IECBO+++++++++++++++++++++++++++==========outcomes+++++++++++"+iecActivityProgressForm.getOutcomes());
			
			IECActivityProgressBean iecActivityProgressBean = populateIecActivityProgressBean(iecActivityProgressForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			iecActivityProgressBean.setMisAuditBean(misAuditBean);
			iecProgressId = iecActivityProgressDao.saveCBIECActivityProgress(iecActivityProgressBean);
			if(MisUtility.ifEmpty(iecProgressId)){			
				Collection<IECActivityMaterialUtilizationBean> iecActivityMaterialUtilizationBeans = populateActivityMaterialUtilizationBeans(iecActivityProgressForm, iecProgressId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(iecActivityMaterialUtilizationBeans)){
					boolean	childStatus = iecActivityMaterialUtilizationDao.saveOrUpdateCBIECActivityMaterialUtilization(iecActivityMaterialUtilizationBeans);
					if(!childStatus){
						log.error("Failed to Save IECActivityProgress and its Material Utilization Details");
						throw new MISException(MISExceptionCodes.MIS003,"Failed to Save IECActivityProgress and its Material Utilization Details");
					}
				}
			}
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return iecProgressId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateCBIECActivityProgress(IecActivityProgressForm iecActivityProgressForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		long iecProgressId = iecActivityProgressForm.getIecProgressId();
		try {
			IECActivityProgressBean iecActivityProgressBean = populateIecActivityProgressBean(iecActivityProgressForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			iecActivityProgressBean.setMisAuditBean(misAuditBean);
			status = iecActivityProgressDao.updateCBIECActivityProgress(iecActivityProgressBean);
			if(status){			
				if(MisUtility.ifEmpty(iecProgressId)){			
					Collection<IECActivityMaterialUtilizationBean> iecActivityMaterialUtilizationBeans = populateActivityMaterialUtilizationBeans(iecActivityProgressForm, iecProgressId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
					if(!MisUtility.ifEmpty(iecActivityMaterialUtilizationBeans)){
						boolean	childStatus = iecActivityMaterialUtilizationDao.saveOrUpdateCBIECActivityMaterialUtilization(iecActivityMaterialUtilizationBeans);
						if(!childStatus){
							log.error("Failed to Save IECActivityProgress and its Material Utilization Details");
							throw new MISException(MISExceptionCodes.MIS003,"Failed to update IECActivityProgress and its Material Utilization Details");
						}
					}
				}
			}
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
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteCBIECActivityProgress(IecActivityProgressForm iecActivityProgressForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		long iecProgressId = iecActivityProgressForm.getIecProgressId();
		try {
			IECActivityProgressBean iecActivityProgressBean = populateIecActivityProgressBean(iecActivityProgressForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			iecActivityProgressBean.setMisAuditBean(misAuditBean);
			status = iecActivityProgressDao.updateCBIECActivityProgress(iecActivityProgressBean);
			if(status){			
				if(MisUtility.ifEmpty(iecProgressId)){			
					Collection<IECActivityMaterialUtilizationBean> iecActivityMaterialUtilizationBeans = populateActivityMaterialUtilizationBeans(iecActivityProgressForm, iecProgressId, misSessionBean, MISConstants.MASTER_STATUS_DELETED);
					if(!MisUtility.ifEmpty(iecActivityMaterialUtilizationBeans)){
						boolean	childStatus = iecActivityMaterialUtilizationDao.saveOrUpdateCBIECActivityMaterialUtilization(iecActivityMaterialUtilizationBeans);
						if(!childStatus){
							log.error("Failed to Save IECActivityProgress and its Material Utilization Details");
							throw new MISException(MISExceptionCodes.MIS003,"Failed to delete IECActivityProgress and its Material Utilization Details");
						}
					}
				}
			}
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
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postCBIECActivityProgress(IecActivityProgressForm iecActivityProgressForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			IECActivityProgressBean iecActivityProgressBean = new IECActivityProgressBean();
			iecActivityProgressBean.setIecProgressId(iecActivityProgressForm.getIecProgressId());
			iecActivityProgressBean = iecActivityProgressDao.findCBIECActivityProgress(iecActivityProgressBean, statusList).get(0);
			MISAuditBean misAuditBean = iecActivityProgressBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			iecActivityProgressBean.setMisAuditBean(misAuditBean);
			status = iecActivityProgressDao.updateCBIECActivityProgress(iecActivityProgressBean);
			
			if(status){
//				IECActivityMaterialUtilizationBean iecActivityMaterialUtilizationBean = new IECActivityMaterialUtilizationBean();
//				iecActivityMaterialUtilizationBean.setIecProgressId(iecActivityProgressForm.getIecProgressId());
				Collection<IECActivityMaterialUtilizationBean> iecActivityMaterialUtilizationBeans = iecActivityProgressBean.getIecActivityMaterialUtilizationBeans();
				if(!MisUtility.ifEmpty(iecActivityMaterialUtilizationBeans)){
					Iterator<IECActivityMaterialUtilizationBean> iecActivityMaterialUtilizationBeansIterator =  iecActivityMaterialUtilizationBeans.iterator();
					IECActivityMaterialUtilizationBean iecActivityMaterialUtilizationBean = null;
					while(iecActivityMaterialUtilizationBeansIterator.hasNext()){
						iecActivityMaterialUtilizationBean = iecActivityMaterialUtilizationBeansIterator.next();
						if(!MISConstants.MASTER_STATUS_DELETED.equalsIgnoreCase(iecActivityMaterialUtilizationBean.getMisAuditBean().getStatus())){
							MISAuditBean misAuditBean1 = iecActivityMaterialUtilizationBean.getMisAuditBean();
							misAuditBean1.setAuthBy(misSessionBean.getEnteredBy());
							misAuditBean1.setAuthDate(misSessionBean.getEnteredDate());
							misAuditBean1.setStatus(MISConstants.MASTER_STATUS_APPROVED);
							iecActivityMaterialUtilizationBean.setMisAuditBean(misAuditBean1);
						}
					}
					boolean	childStatus = iecActivityMaterialUtilizationDao.saveOrUpdateCBIECActivityMaterialUtilization(iecActivityMaterialUtilizationBeans);
					if(!childStatus){
						log.error("Failed to Save IECActivityProgress and its Material Utilization Details");
						throw new MISException(MISExceptionCodes.MIS003,"Failed to delete IECActivityProgress and its Material Utilization Details");
					}
				}
				
			}
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
			throw new MISException(e.getMostSpecificCause());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}
	
	private IECActivityProgressBean populateIecActivityProgressBean(IecActivityProgressForm iecActivityProgressForm){
		IECActivityProgressBean iecActivityProgressBean = new IECActivityProgressBean();
		iecActivityProgressBean.setIecProgressId(iecActivityProgressForm.getIecProgressId());
		iecActivityProgressBean.setActivityDate(MisUtility.convertStringToDate(iecActivityProgressForm.getDateOfActivity()));
		IECActivityBean iecActivityBean = new IECActivityBean();
		iecActivityBean.setIecActivityId(iecActivityProgressForm.getIecActivityId());
		iecActivityProgressBean.setIecActivityBean(iecActivityBean);
		iecActivityProgressBean.setBlockId(iecActivityProgressForm.getBlockId());
		iecActivityProgressBean.setIecProgressId(iecActivityProgressForm.getIecProgressId());
		iecActivityProgressBean.setNoOfParticipants(iecActivityProgressForm.getNumberOfParticipants());
		iecActivityProgressBean.setNotes(iecActivityProgressForm.getRemarks());
		iecActivityProgressBean.setOutcome(iecActivityProgressForm.getOutcomes());
		iecActivityProgressBean.setVenue(iecActivityProgressForm.getVenueOfActivity());
		iecActivityProgressBean.setVillageId(iecActivityProgressForm.getVillageId());
		iecActivityProgressBean.setLocationId(iecActivityProgressForm.getLocationId());		
		iecActivityProgressBean.setExpenditure(iecActivityProgressForm.getExpenditure());
		return iecActivityProgressBean;
	}
	
	@SuppressWarnings("unchecked")
	private List<IECActivityMaterialUtilizationBean> populateActivityMaterialUtilizationBeans(IecActivityProgressForm iecActivityProgressForm, long iecProgressId, MISSessionBean misSessionBean, String status){
		List<IECActivityMaterialUtilizationBean> iecActivityMaterialUtilizationBeans = new ArrayList<IECActivityMaterialUtilizationBean>();
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		IECActivityMaterialUtilizationBean iecActivityMaterialUtilizationBean = null;
		Collection<MaterialUtilizationGridBean> addedIecActivityMaterialUtilizationBeans = iecActivityProgressForm.getMaterialUtilizationDatagrid().getAddedData();
		if(!MisUtility.ifEmpty(addedIecActivityMaterialUtilizationBeans)){
			for (MaterialUtilizationGridBean materialUtilizationGridBean : addedIecActivityMaterialUtilizationBeans) {
				iecActivityMaterialUtilizationBean = new IECActivityMaterialUtilizationBean();
				iecActivityMaterialUtilizationBean.setIecProgressId(iecProgressId);
				iecActivityMaterialUtilizationBean.setQuantity(materialUtilizationGridBean.getQuantity());
				
				IECMaterialBean iecMaterialBean = new IECMaterialBean();
				iecMaterialBean.setIecMaterialId(materialUtilizationGridBean.getItemId());
				ItemBean itemBean = new ItemBean();
				itemBean.setItemId(materialUtilizationGridBean.getItemId());
				iecActivityMaterialUtilizationBean.setItemBean(itemBean);
				
				iecActivityMaterialUtilizationBean.setMisAuditBean(misAuditBean);
				iecActivityMaterialUtilizationBeans.add(iecActivityMaterialUtilizationBean);
			}
		}
		
		Collection<MaterialUtilizationGridBean> modifiedIecActivityMaterialUtilizationBeans = iecActivityProgressForm.getMaterialUtilizationDatagrid().getModifiedData();
		if(!MisUtility.ifEmpty(modifiedIecActivityMaterialUtilizationBeans)){
			for (MaterialUtilizationGridBean materialUtilizationGridBean : modifiedIecActivityMaterialUtilizationBeans) {
				iecActivityMaterialUtilizationBean = new IECActivityMaterialUtilizationBean();
				iecActivityMaterialUtilizationBean.setIecProgressId(iecProgressId);
				iecActivityMaterialUtilizationBean.setQuantity(materialUtilizationGridBean.getQuantity());
				
				IECMaterialBean iecMaterialBean = new IECMaterialBean();
				iecMaterialBean.setIecMaterialId(materialUtilizationGridBean.getItemId());
				ItemBean itemBean = new ItemBean();
				itemBean.setItemId(materialUtilizationGridBean.getItemId());
				iecActivityMaterialUtilizationBean.setItemBean(itemBean);
				
				iecActivityMaterialUtilizationBean.setMisAuditBean(misAuditBean);
				iecActivityMaterialUtilizationBeans.add(iecActivityMaterialUtilizationBean);
			}
		}
		
		Collection<MaterialUtilizationGridBean> deletedIecActivityMaterialUtilizationBeans = iecActivityProgressForm.getMaterialUtilizationDatagrid().getDeletedData();
		if(!MisUtility.ifEmpty(deletedIecActivityMaterialUtilizationBeans)){
			for (MaterialUtilizationGridBean materialUtilizationGridBean : deletedIecActivityMaterialUtilizationBeans) {
				iecActivityMaterialUtilizationBean = new IECActivityMaterialUtilizationBean();
				iecActivityMaterialUtilizationBean.setIecProgressId(iecProgressId);
				iecActivityMaterialUtilizationBean.setQuantity(materialUtilizationGridBean.getQuantity());
				
				IECMaterialBean iecMaterialBean = new IECMaterialBean();
				iecMaterialBean.setIecMaterialId(materialUtilizationGridBean.getItemId());
				ItemBean itemBean = new ItemBean();
				itemBean.setItemId(materialUtilizationGridBean.getItemId());
				iecActivityMaterialUtilizationBean.setItemBean(itemBean);
				
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				iecActivityMaterialUtilizationBean.setMisAuditBean(misAuditBean);
				iecActivityMaterialUtilizationBeans.add(iecActivityMaterialUtilizationBean);
			}
		}
		
		return iecActivityMaterialUtilizationBeans;
	}

}
