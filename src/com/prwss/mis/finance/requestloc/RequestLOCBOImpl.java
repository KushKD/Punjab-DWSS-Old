/**
 * 
 */
package com.prwss.mis.finance.requestloc;

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
import com.prwss.mis.finance.requestloc.struts.RequestLOCForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class RequestLOCBOImpl implements RequestLOCBO {

	private Logger log = Logger.getLogger(RequestLOCBOImpl.class);
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
	public long saveRequestLOC(RequestLOCForm requestLOCForm,
			MISSessionBean misSessionBean) throws MISException {
		long locId = 0;
		try {
			System.out.println("In save RequestLOC  ");
			LOCHeaderBean locHeaderBean = populateLOCHeaderBean(requestLOCForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			locHeaderBean.setMisAuditBean(misAuditBean);

			locId = locHeaderDao.saveLOCHeader(locHeaderBean);
		
			if(MisUtility.ifEmpty(locId)){
				List<LOCDetailBean> locDetailBeans = populateLOCDetailBeans(requestLOCForm, locId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(locDetailBeans)){
					boolean locDetailStatus = locDetailDao.saveLOCDetails(locDetailBeans);
					if(!locDetailStatus){
						log.error(locDetailBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to save LOC details");
					}
				}


				if(MisUtility.ifEmpty(locId)){
					List<LOCActivityBean> locActivityBeans = populateLOCActivityBeans(requestLOCForm, locId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
					if(!MisUtility.ifEmpty(locActivityBeans)){
						boolean locActivityStatus = locActivityDao.saveLOCActivity(locActivityBeans);
						if(!locActivityStatus){
							log.error(locActivityBeans);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to save LOC activity details");
						}
					}

				}
			}
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw new MISException(e);
		}
return locId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateRequestLOC(RequestLOCForm requestLOCForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean locDetailStatus=false;
		try {
			System.out.println("In Update RequestLOC  ");
			LOCHeaderBean locHeaderBean = populateLOCHeaderBean(requestLOCForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			locHeaderBean.setMisAuditBean(misAuditBean);

			boolean status = locHeaderDao.updateLOCHeader(locHeaderBean);
			long locId =requestLOCForm.getLocRequestNo();
		
			if(status){
				List<LOCDetailBean> locDetailBeans = populateLOCDetailBeans(requestLOCForm, locId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(locDetailBeans)){
					locDetailStatus = locDetailDao.updateLOCDetails(locDetailBeans);
					if(!locDetailStatus){
						log.error(locDetailBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to update LOC details");
					}
				}
				
				
					List<LOCActivityBean> locActivityBeans = populateLOCActivityBeans(requestLOCForm, locId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
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
	public boolean deleteRequestLOC(RequestLOCForm requestLOCForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean locDetailStatus=false;
		boolean status = false;
		try {
			System.out.println("In delete RequestLOC  ");
			LOCHeaderBean locHeaderBean = populateLOCHeaderBean(requestLOCForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			locHeaderBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			status = locHeaderDao.updateLOCHeader(locHeaderBean);
			long locId =requestLOCForm.getLocRequestNo();
			
			LOCDetailBean locDetailBean = new LOCDetailBean();
			locDetailBean.setLocId(locId);
			
			LOCActivityBean locActivityBean = new LOCActivityBean();
			locActivityBean.setLocId(locId);
			
		
			if(status){
				List<LOCDetailBean> locDetailBeans = locDetailDao.findLOCtDetails(locDetailBean, statusList);
				if(!MisUtility.ifEmpty(locDetailBeans)){
					for (LOCDetailBean locDetailBean2 : locDetailBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						locDetailBean2.setMisAuditBean(misAuditBean);
					}
					locDetailStatus = locDetailDao.updateLOCDetails(locDetailBeans);
					if(!locDetailStatus){
						log.error(locDetailBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to delete loc details");
					}
				}
				
				List<LOCActivityBean> locActivityBeans = locActivityDao.findLOCActivity(locActivityBean, statusList);
				if(!MisUtility.ifEmpty(locActivityBeans)){
					for (LOCActivityBean locActivityBean2 : locActivityBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						locActivityBean2.setMisAuditBean(misAuditBean);
					}
				boolean statusLocActivity = locActivityDao.updateActivity(locActivityBeans);
				if(!statusLocActivity){
					log.error(locActivityBeans);
					throw new MISException(MISExceptionCodes.MIS003, "Failed to delete loc Activity Beans");
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
return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<LOCHeaderBean> findRequestLOC(RequestLOCForm requestLOCForm,
			List<String> statusList) throws MISException {
		List<LOCHeaderBean> locHeaderBeans = new  ArrayList<LOCHeaderBean>();
		try {
			
			LOCHeaderBean locHeaderBean = new LOCHeaderBean();
			
			locHeaderBean.setLocId(requestLOCForm.getLocRequestNo());
			locHeaderBean.setLocationId(requestLOCForm.getLocationId());
			locHeaderBeans= locHeaderDao.findLOCHeader(locHeaderBean, statusList);
			
			
			Set<LOCDetailBean> locDetailBeans = null;
			Iterator<LOCDetailBean> iteratorLOCDetailBean = null;
			LOCDetailBean  locDetailBean = null;
			if(!MisUtility.ifEmpty(locHeaderBeans)){

				for (LOCHeaderBean bean : locHeaderBeans) {
				
					locDetailBeans = bean.getLocDetailBeans();
					if(!MisUtility.ifEmpty(locDetailBeans)){
						iteratorLOCDetailBean = locDetailBeans.iterator();					
						while(iteratorLOCDetailBean.hasNext()){
							locDetailBean = iteratorLOCDetailBean.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(locDetailBean.getMisAuditBean().getStatus())){
								iteratorLOCDetailBean.remove();
								break;
							}
						}
					}
				}
			}
			
			Set<LOCActivityBean> locActivityBeans = null;
			Iterator<LOCActivityBean> iteratorLOCActivityBean = null;
			LOCActivityBean  locActivityBean = null;
			if(!MisUtility.ifEmpty(locHeaderBeans)){

				for (LOCHeaderBean bean : locHeaderBeans) {
				
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
			log.error(e);
			e.printStackTrace();
			throw e;
		}
		return locHeaderBeans;
	}

	private LOCHeaderBean populateLOCHeaderBean(RequestLOCForm requestLOCForm){
		
		LOCHeaderBean locHeaderBean = new LOCHeaderBean();
		
		locHeaderBean.setLocId(requestLOCForm.getLocRequestNo());
		locHeaderBean.setProgramId(requestLOCForm.getProgramId());
		locHeaderBean.setLocationId(requestLOCForm.getLocationId());
		locHeaderBean.setLocationName(requestLOCForm.getLocationName());
		locHeaderBean.setLocRequestDate(MisUtility.convertStringToDate(requestLOCForm.getRequestDate()));
		locHeaderBean.setComponentAReqAmount(requestLOCForm.getComponentA());
		locHeaderBean.setComponentBReqAmount(requestLOCForm.getComponentB());
		locHeaderBean.setComponentCReqAmount(requestLOCForm.getComponentC());
		if(MisUtility.ifEmpty(requestLOCForm.getAmountFD())){
			locHeaderBean.setAmountFDRequest(requestLOCForm.getAmountFD());
		}else{
			locHeaderBean.setAmountFDRequest(new BigDecimal(0.0));
		}
		locHeaderBean.setAmountFDRelease(new BigDecimal(0.0));
		locHeaderBean.setRequestToLocationId(requestLOCForm.getRequestToLocationId());		
			return locHeaderBean;
		}

		@SuppressWarnings({ "unchecked" })
		private List<LOCDetailBean> populateLOCDetailBeans(RequestLOCForm requestLOCForm,long locId,  MISSessionBean misSessionBean, String status){
			
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
			Datagrid locDetailGrid = requestLOCForm.getRequestlocDatagrid();

			Collection<LOCDetailBean> addedLOCDetailBeans = locDetailGrid.getAddedData();
			if(!MisUtility.ifEmpty(addedLOCDetailBeans)){
				for (LOCDetailBean locDetailBean : addedLOCDetailBeans) {
					locDetailBean.setLocId(locId);
					locDetailBean.setMisAuditBean(misAuditBean);
					locDetailBean.setLocationId(requestLOCForm.getLocationId());
					locDetailBean.setProgramId(requestLOCForm.getProgramId());
					locDetailBean.setAuditedAmount(new BigDecimal(0.0));
					locDetailBean.setReleaseAmount(new BigDecimal(0.0));
					locDetailBeans.add(locDetailBean);
				}
			}

			Collection<LOCDetailBean> modifiedLOCDetailBeans = locDetailGrid.getModifiedData();
			if(!MisUtility.ifEmpty(modifiedLOCDetailBeans)){
				for (LOCDetailBean locDetailBean : modifiedLOCDetailBeans) {
					locDetailBean.setLocId(locId);
					locDetailBean.setMisAuditBean(misAuditBean);
					locDetailBean.setLocationId(requestLOCForm.getLocationId());
					locDetailBean.setProgramId(requestLOCForm.getProgramId());
					locDetailBean.setAuditedAmount(new BigDecimal(0.0));
					locDetailBean.setReleaseAmount(new BigDecimal(0.0));
					locDetailBeans.add(locDetailBean);
				}
					
				}
		
			
			Collection<LOCDetailBean> deletedLOCDetailBeans = locDetailGrid.getDeletedData();
			if(!MisUtility.ifEmpty(deletedLOCDetailBeans)){
				for (LOCDetailBean locDetailBean : deletedLOCDetailBeans) {
					MISAuditBean misAuditBean2 = new MISAuditBean();
					misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
					misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
					locDetailBean.setLocId(locId);
					locDetailBean.setLocationId(requestLOCForm.getLocationId());
					locDetailBean.setProgramId(requestLOCForm.getProgramId());
					locDetailBean.setAuditedAmount(new BigDecimal(0.0));
					locDetailBean.setReleaseAmount(new BigDecimal(0.0));
					locDetailBean.setMisAuditBean(misAuditBean2);
					locDetailBeans.add(locDetailBean);
				}
					
				}
			
			return locDetailBeans;
		}
		
		@SuppressWarnings({ "unchecked" })
		private List<LOCActivityBean> populateLOCActivityBeans(RequestLOCForm requestLOCForm,long locId,  MISSessionBean misSessionBean, String status){
			
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

			Collection<LOCActivityBean> addedLOCDetailBeans = requestLOCForm.getLocActivityDatagrid().getAddedData();
			if(!MisUtility.ifEmpty(addedLOCDetailBeans)){
				for (LOCActivityBean bean : addedLOCDetailBeans) {
					bean.setLocId(locId);
					bean.setMisAuditBean(misAuditBean);
					bean.setLocationId(requestLOCForm.getLocationId());
					bean.setProgramId(requestLOCForm.getProgramId());
					bean.setReleaseAmount(new BigDecimal(0.0));
					locActivityBeans.add(bean);
				}
			}

			Collection<LOCActivityBean> modifiedLOCDetailBeans = requestLOCForm.getLocActivityDatagrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedLOCDetailBeans)){
				for (LOCActivityBean bean : modifiedLOCDetailBeans) {
					bean.setLocId(locId);
					bean.setMisAuditBean(misAuditBean);
					bean.setLocationId(requestLOCForm.getLocationId());
					bean.setProgramId(requestLOCForm.getProgramId());
					bean.setReleaseAmount(new BigDecimal(0.0));
					locActivityBeans.add(bean);
				}
					
				}
		
			
			Collection<LOCActivityBean> deletedLOCDetailBeans = requestLOCForm.getLocActivityDatagrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedLOCDetailBeans)){
				for (LOCActivityBean bean : deletedLOCDetailBeans) {
					MISAuditBean misAuditBean2 = new MISAuditBean();
					misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
					misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
					bean.setLocId(locId);
					bean.setLocationId(requestLOCForm.getLocationId());
					bean.setProgramId(requestLOCForm.getProgramId());
					bean.setMisAuditBean(misAuditBean2);
					bean.setReleaseAmount(new BigDecimal(0.0));
					locActivityBeans.add(bean);
				}
					
				}
			
			return locActivityBeans;
		}

		@Override
		@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
		public boolean postRequestLOC(RequestLOCForm requestLOCForm,
				MISSessionBean misSessionBean) throws MISException {
			
			try {
				LOCHeaderBean locHeaderBean = populateLOCHeaderBean(requestLOCForm);	
				
				
				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
				locHeaderBean = locHeaderDao.findLOCHeader(locHeaderBean, statusList).get(0);
				MISAuditBean misAuditBean = new MISAuditBean();
				misAuditBean = locHeaderBean.getMisAuditBean();
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				locHeaderBean.setMisAuditBean(misAuditBean);

				boolean status = locHeaderDao.updateLOCHeader(locHeaderBean);
				long locId = requestLOCForm.getLocRequestNo();

				LOCDetailBean locDetailBean = new LOCDetailBean();
				locDetailBean.setLocId(locId);
				if(status){
					List<LOCDetailBean> locDetailBeans = locDetailDao.findLOCtDetails(locDetailBean, statusList);
					if(!MisUtility.ifEmpty(locDetailBeans)){
						for (LOCDetailBean locDetailBean2 : locDetailBeans) {
							misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
							locDetailBean2.setMisAuditBean(misAuditBean);
						}
						boolean locStatus = locDetailDao.updateLOCDetails(locDetailBeans);
						if(!locStatus){
							log.error(locStatus);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to post request LOC details");
						}
					}
					
					
				}


			} catch (DataAccessException e) {
				log.error(e.getLocalizedMessage(),e);
				throw new MISException(e);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(),e);
				throw new MISException(e);
			}
	return true;
		}
	
	
	
}
