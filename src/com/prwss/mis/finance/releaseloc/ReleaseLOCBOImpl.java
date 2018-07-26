/**
 * 
 */
package com.prwss.mis.finance.releaseloc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.loc.LOCHeaderBean;
import com.prwss.mis.finance.loc.dao.LOCActivityBean;
import com.prwss.mis.finance.loc.dao.LOCActivityDao;
import com.prwss.mis.finance.loc.dao.LOCDetailBean;
import com.prwss.mis.finance.loc.dao.LOCDetailDao;
import com.prwss.mis.finance.loc.dao.LOCHeaderDao;
import com.prwss.mis.finance.releaseloc.struts.ReleaseLOCForm;
import com.prwss.mis.finance.releaseloc.struts.ReleaseLocGridBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class ReleaseLOCBOImpl implements ReleaseLOCBO {
	
	private Logger log = Logger.getLogger(ReleaseLOCBOImpl.class);
	private LOCHeaderDao locHeaderDao ;
	private LOCDetailDao locDetailDao;
	private LOCActivityDao locActivityDao;
	
	public void setLocActivityDao(LOCActivityDao locActivityDao) {
		this.locActivityDao = locActivityDao;
	}
	
	

	public void setLocHeaderDao(LOCHeaderDao locHeaderDao) {
		this.locHeaderDao = locHeaderDao;
	}

	public void setLocDetailDao(LOCDetailDao locDetailDao) {
		this.locDetailDao = locDetailDao;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<LOCHeaderBean> findReleaseLOC(ReleaseLOCForm releaseLOCForm,
			List<String> statusList) throws MISException {
		List<LOCHeaderBean> locHeaderBeans = new  ArrayList<LOCHeaderBean>();
		try {
			
			LOCHeaderBean locHeaderBean = new LOCHeaderBean();
			System.out.println(" LOC ID : "+releaseLOCForm.getLocRequestNo()+" request From : "+releaseLOCForm.getRequestFromLocationId()+" Location : "+releaseLOCForm.getLocationId());
			locHeaderBean.setLocId(releaseLOCForm.getLocRequestNo());
			locHeaderBean.setLocationId(releaseLOCForm.getRequestFromLocationId());
			locHeaderBean.setRequestToLocationId(releaseLOCForm.getLocationId());
			
			locHeaderBeans= locHeaderDao.findLOCHeader(locHeaderBean, statusList);
//			Set<LOCDetailBean> locDetailBeans = null;
//			Iterator<LOCDetailBean> iteratorLOCDetailBean = null;
//			LOCDetailBean  locDetailBean = null;
//			if(!MisUtility.ifEmpty(locHeaderBeans)){
//
//				for (LOCHeaderBean bean : locHeaderBeans) {
//					System.out.println("locHeaderBean.getLocDetailBeans()"+bean.getLocDetailBeans());
//					locDetailBeans = bean.getLocDetailBeans();
//					if(!MisUtility.ifEmpty(locDetailBeans)){
//						iteratorLOCDetailBean = locDetailBeans.iterator();					
//						while(iteratorLOCDetailBean.hasNext()){
//							locDetailBean = iteratorLOCDetailBean.next();
//							if(MISConstants.MASTER_STATUS_DELETED.equals(locDetailBean.getMisAuditBean().getStatus())){
//								iteratorLOCDetailBean.remove();
//								break;
//							}
//						}
//					}
//				}
//			}
			System.out.println("IN FIND"+locHeaderBeans);
			Set<LOCActivityBean> locActivityBeans = null;
			Iterator<LOCActivityBean> iteratorLOCActivityBean = null;
			LOCActivityBean  locActivityBean = null;
			if(!MisUtility.ifEmpty(locHeaderBeans)){

				for (LOCHeaderBean bean : locHeaderBeans) {
					System.out.println("bean.getLocActivtyBeans();"+bean.getLocActivtyBeans());
					locActivityBeans = bean.getLocActivtyBeans();
					if(!MisUtility.ifEmpty(locActivityBeans)){
						iteratorLOCActivityBean = locActivityBeans.iterator();					
						while(iteratorLOCActivityBean.hasNext()){
							locActivityBean = iteratorLOCActivityBean.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(locActivityBean.getMisAuditBean().getStatus())){
								iteratorLOCActivityBean.remove();
								break;
							}
						}
					}
				}
			}
				
				
		
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return locHeaderBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<LOCDetailBean> findReleaseLOCDetail(ReleaseLOCForm releaseLOCForm,
			List<String> statusList) throws MISException {
		List<LOCDetailBean> locDetailBeans = new  ArrayList<LOCDetailBean>();
		try {
			
			LOCDetailBean locDetailBean = new LOCDetailBean();
			locDetailBean.setProgramId(releaseLOCForm.getProgramId());
			locDetailBean.setLocId(releaseLOCForm.getLocRequestNo());
			locDetailBean.setLocationId(releaseLOCForm.getRequestFromLocationId());
			
			locDetailBeans= locDetailDao.findLOCtDetails(locDetailBean, statusList);
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return locDetailBeans;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveReleaseLOC(ReleaseLOCForm releaseLOCForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In save RequestLOC  ");
			LOCHeaderBean locHeaderBean = populateLOCHeaderBean(releaseLOCForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			locHeaderBean.setMisAuditBean(misAuditBean);
			
			long locId = locHeaderDao.saveLOCHeader(locHeaderBean);
		
			if(MisUtility.ifEmpty(locId)){
				List<LOCDetailBean> locDetailBeans = populateLOCDetailBeans(releaseLOCForm, locId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(locDetailBeans)){
					boolean locDetailStatus = locDetailDao.saveLOCDetails(locDetailBeans);
					if(!locDetailStatus){
						log.error(locDetailBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to save LOC details");
					}
				}

				
			}
			
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return true;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateReleaseLOC(ReleaseLOCForm releaseLOCForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In Update ReleaseLOC  ");
			LOCHeaderBean locHeaderBean = populateLOCHeaderBean(releaseLOCForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			locHeaderBean.setMisAuditBean(misAuditBean);

			boolean status = locHeaderDao.updateLOCHeader(locHeaderBean);
			long locId =releaseLOCForm.getLocRequestNo();
		
			if(status){
				List<LOCDetailBean> locDetailBeans = populateLOCDetailBeans(releaseLOCForm, locId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(locDetailBeans)){
					boolean locDetailStatus = locDetailDao.updateLOCDetails(locDetailBeans);
					if(!locDetailStatus){
						log.error(locDetailBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to update LOC details");
					}
				}
				
				List<LOCActivityBean> locActivityBeans = populateLOCActivityBeans(releaseLOCForm, locId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(locActivityBeans)){
					boolean locActivityStatus = locActivityDao.saveLOCActivity(locActivityBeans);
					if(!locActivityStatus){
						log.error(locActivityBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to save LOC activity details");
					}
				}
			}
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return true;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteReleaseLOC(ReleaseLOCForm releaseLOCForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			System.out.println("In delete ReleaseLOC  ");
			LOCHeaderBean locHeaderBean = populateLOCHeaderBean(releaseLOCForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			locHeaderBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status = locHeaderDao.updateLOCHeader(locHeaderBean);
			long locId =releaseLOCForm.getLocRequestNo();
			
			LOCDetailBean locDetailBean = new LOCDetailBean();
			locDetailBean.setLocId(locId);
			
		
			if(status){
				List<LOCDetailBean> locDetailBeans = locDetailDao.findLOCtDetails(locDetailBean, statusList);
				if(!MisUtility.ifEmpty(locDetailBeans)){
					for (LOCDetailBean locDetailBean2 : locDetailBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						locDetailBean2.setMisAuditBean(misAuditBean);
					}
					boolean locDetailStatus = locDetailDao.updateLOCDetails(locDetailBeans);
					if(!locDetailStatus){
						log.error(locDetailBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to delete loc details");
					}
				}
			
			}
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return true;
	}

private LOCHeaderBean populateLOCHeaderBean(ReleaseLOCForm releaseLOCForm){
		
		LOCHeaderBean locHeaderBean = new LOCHeaderBean();
		
		locHeaderBean.setLocId(releaseLOCForm.getLocRequestNo());
		locHeaderBean.setProgramId(releaseLOCForm.getProgramId());
		locHeaderBean.setLocationId(releaseLOCForm.getRequestFromLocationId());
		locHeaderBean.setLocationName(releaseLOCForm.getLocationName());
		locHeaderBean.setRequestToLocationId(releaseLOCForm.getLocationId());
		locHeaderBean.setLocRequestDate(MisUtility.convertStringToDate(releaseLOCForm.getRequestDate()));
		locHeaderBean.setLocReleaseDate(MisUtility.convertStringToDate(releaseLOCForm.getLocDate()));		
			locHeaderBean.setAmountFDRequest(releaseLOCForm.getAmountFDRequest());
			locHeaderBean.setAmountFDRelease(releaseLOCForm.getAmountFDRelease());
		System.out.println("xxxxxxxxxx updateBO releaseLOCForm.setAmountFDRequest:"+releaseLOCForm.getAmountFDRequest());
		System.out.println("xxxxxxxxxx updateBO releaseLOCForm.setAmountFDRelease:"+releaseLOCForm.getAmountFDRelease());
//		locHeaderBean.setComponentAReqAmount(releaseLOCForm.getComponentA());
//		locHeaderBean.setComponentBReqAmount(releaseLOCForm.getComponentB());
//		locHeaderBean.setComponentCReqAmount(releaseLOCForm.getComponentC());
//		locHeaderBean.setComponentARelsAmount(releaseLOCForm.getReleaseAmountA());
//		locHeaderBean.setComponentBRelsAmount(releaseLOCForm.getReleaseAmountB());
//		locHeaderBean.setComponentCRelsAmount(releaseLOCForm.getReleaseAmountC());
			return locHeaderBean;
		}

		@SuppressWarnings({ "unchecked" })
		private List<LOCDetailBean> populateLOCDetailBeans(ReleaseLOCForm releaseLOCForm,long locId,  MISSessionBean misSessionBean, String status){
			
			MISAuditBean misAuditBean = new MISAuditBean();
			if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			} else{
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			}
			misAuditBean.setStatus(status);

			List<LOCDetailBean> locDetailBeans = new ArrayList<LOCDetailBean>();
			LOCDetailBean locDetailBean = null;
			Datagrid locDetailGrid = releaseLOCForm.getReleaselocDatagrid();

			Collection<ReleaseLocGridBean> addedLOCDetailBeans = locDetailGrid.getAddedData();
			if(!MisUtility.ifEmpty(addedLOCDetailBeans)){
				for (ReleaseLocGridBean releaseLocGridBean : addedLOCDetailBeans) {
					locDetailBean = new LOCDetailBean();
					locDetailBean.setLocId(locId);
					locDetailBean.setMisAuditBean(misAuditBean);
					locDetailBean.setSchemeId(releaseLocGridBean.getSchemeId());
					locDetailBean.setAmount(releaseLocGridBean.getAmount());
					locDetailBean.setAuditCompletedDate(MisUtility.convertStringToDate(releaseLocGridBean.getAuditCompletedDate()));
					locDetailBean.setAuditedAmount(releaseLocGridBean.getAuditedAmount());
					locDetailBean.setInstallmentNo(releaseLocGridBean.getInstallmentNo());
					locDetailBean.setReleaseAmount(releaseLocGridBean.getReleaseAmount());
					locDetailBean.setReleaseStatus(releaseLocGridBean.getReleaseStatus());
					locDetailBean.setLocationId(releaseLOCForm.getRequestFromLocationId());
					locDetailBean.setProgramId(releaseLOCForm.getProgramId());
					locDetailBean.setId(releaseLocGridBean.getId());
					locDetailBean.setSchemeName(releaseLocGridBean.getSchemeName());
					locDetailBean.setLocFor(releaseLocGridBean.getLocFor());
					locDetailBeans.add(locDetailBean);
				}
			}

			Collection<ReleaseLocGridBean> modifiedLOCDetailBeans = locDetailGrid.getModifiedData();
			if(!MisUtility.ifEmpty(modifiedLOCDetailBeans)){
				for (ReleaseLocGridBean releaseLocGridBean : modifiedLOCDetailBeans) {
					locDetailBean = new LOCDetailBean();
					locDetailBean.setLocId(locId);
					locDetailBean.setMisAuditBean(misAuditBean);
					locDetailBean.setSchemeId(releaseLocGridBean.getSchemeId());
					locDetailBean.setAmount(releaseLocGridBean.getAmount());
					locDetailBean.setAuditCompletedDate(MisUtility.convertStringToDate(releaseLocGridBean.getAuditCompletedDate()));
					locDetailBean.setAuditedAmount(releaseLocGridBean.getAuditedAmount());
					locDetailBean.setInstallmentNo(releaseLocGridBean.getInstallmentNo());
					locDetailBean.setReleaseAmount(releaseLocGridBean.getReleaseAmount());
					locDetailBean.setReleaseStatus(releaseLocGridBean.getReleaseStatus());
					locDetailBean.setLocationId(releaseLOCForm.getRequestFromLocationId());
					locDetailBean.setProgramId(releaseLOCForm.getProgramId());
					locDetailBean.setId(releaseLocGridBean.getId());
					locDetailBean.setSchemeName(releaseLocGridBean.getSchemeName());
					locDetailBean.setLocFor(releaseLocGridBean.getLocFor());
					locDetailBeans.add(locDetailBean);
				}
					
				}
		
			
			Collection<ReleaseLocGridBean> deletedLOCDetailBeans = locDetailGrid.getDeletedData();
			if(!MisUtility.ifEmpty(deletedLOCDetailBeans)){
				for (ReleaseLocGridBean releaseLocGridBean : deletedLOCDetailBeans) {
					locDetailBean = new LOCDetailBean();
					locDetailBean.setLocId(locId);
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
					locDetailBean.setMisAuditBean(misAuditBean);
					locDetailBean.setSchemeId(releaseLocGridBean.getSchemeId());
					locDetailBean.setAmount(releaseLocGridBean.getAmount());
					locDetailBean.setAuditCompletedDate(MisUtility.convertStringToDate(releaseLocGridBean.getAuditCompletedDate()));
					locDetailBean.setAuditedAmount(releaseLocGridBean.getAuditedAmount());
					locDetailBean.setInstallmentNo(releaseLocGridBean.getInstallmentNo());
					locDetailBean.setReleaseAmount(releaseLocGridBean.getReleaseAmount());
					locDetailBean.setReleaseStatus(releaseLocGridBean.getReleaseStatus());
					locDetailBean.setLocationId(releaseLOCForm.getRequestFromLocationId());
					locDetailBean.setProgramId(releaseLOCForm.getProgramId());
					locDetailBean.setId(releaseLocGridBean.getId());
					locDetailBean.setSchemeName(releaseLocGridBean.getSchemeName());
					locDetailBean.setLocFor(releaseLocGridBean.getLocFor());
					locDetailBeans.add(locDetailBean);
				}
					
				}
			
			return locDetailBeans;
		}
		
		@SuppressWarnings({ "unchecked" })
		private List<LOCActivityBean> populateLOCActivityBeans(ReleaseLOCForm releaseLOCForm,long locId,  MISSessionBean misSessionBean, String status){
			
			MISAuditBean misAuditBean = new MISAuditBean();
			if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			} else{
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			}
			misAuditBean.setStatus(status);

			List<LOCActivityBean> locActivityBeans = new ArrayList<LOCActivityBean>();
//			Datagrid locDetailGrid = requestLOCForm.getRequestlocDatagrid();

			Collection<LOCActivityBean> addedLOCDetailBeans = releaseLOCForm.getLocActivityDatagrid().getAddedData();
			if(!MisUtility.ifEmpty(addedLOCDetailBeans)){
				for (LOCActivityBean bean : addedLOCDetailBeans) {
					bean.setLocId(locId);
					bean.setMisAuditBean(misAuditBean);
					bean.setLocationId(releaseLOCForm.getRequestFromLocationId());
					bean.setProgramId(releaseLOCForm.getProgramId());
					locActivityBeans.add(bean);
				}
			}

			Collection<LOCActivityBean> modifiedLOCDetailBeans = releaseLOCForm.getLocActivityDatagrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedLOCDetailBeans)){
				for (LOCActivityBean bean : modifiedLOCDetailBeans) {
					bean.setLocId(locId);
					bean.setMisAuditBean(misAuditBean);
					bean.setLocationId(releaseLOCForm.getRequestFromLocationId());
					bean.setProgramId(releaseLOCForm.getProgramId());
					locActivityBeans.add(bean);
				}
					
				}
		
			
			Collection<LOCActivityBean> deletedLOCDetailBeans = releaseLOCForm.getLocActivityDatagrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedLOCDetailBeans)){
				for (LOCActivityBean bean : deletedLOCDetailBeans) {
					MISAuditBean misAuditBean2 = new MISAuditBean();
					misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
					misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
					bean.setLocId(locId);
					bean.setLocationId(releaseLOCForm.getRequestFromLocationId());
					bean.setProgramId(releaseLOCForm.getProgramId());
					bean.setMisAuditBean(misAuditBean2);
					locActivityBeans.add(bean);
				}
					
				}
			
			return locActivityBeans;
		}

	
	
}
