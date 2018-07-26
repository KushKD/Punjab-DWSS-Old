package com.prwss.mis.tender;

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
import com.prwss.mis.masters.vendor.dao.VendorBean;
import com.prwss.mis.tender.award.dao.TenderAwardBean;
import com.prwss.mis.tender.award.dao.TenderAwardDao;
import com.prwss.mis.tender.award.dao.TenderObjectionBean;
import com.prwss.mis.tender.award.dao.TenderObjectionDao;
import com.prwss.mis.tender.award.dao.TenderSecurityDepositBean;
import com.prwss.mis.tender.award.dao.TenderSecurityDepositDao;
import com.prwss.mis.tender.contract.dao.ContractDao;
import com.prwss.mis.tender.contract.dao.ContractHeaderBean;
import com.prwss.mis.tender.struts.TenderAwardForm;
import com.prwss.mis.tender.struts.TenderObjectionGridBean;
import com.prwss.mis.tender.struts.TenderSecurityGridBean;

public abstract class TenderAwardBOImpl implements TenderBO {
	
	private Logger log = Logger.getLogger(TenderAwardBOImpl.class);

	protected TenderAwardDao tenderAwardDao;
	protected ContractDao contractDao;
	protected TenderObjectionDao tenderObjectionDao;
	protected TenderSecurityDepositDao tenderSecurityDepositDao;
	
	public void setTenderAwardDao(TenderAwardDao tenderAwardDao) {
		this.tenderAwardDao = tenderAwardDao;
	}

	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}

	public void setTenderObjectionDao(TenderObjectionDao tenderObjectionDao) {
		this.tenderObjectionDao = tenderObjectionDao;
	}

	public void setTenderSecurityDepositDao(TenderSecurityDepositDao tenderSecurityDepositDao) {
		this.tenderSecurityDepositDao = tenderSecurityDepositDao;
	}

	@Override
	public List<TenderAwardBean> findTenderAwarded(TenderAwardForm tenderAwardForm, List<String> statusList)
			throws MISException {
		
		List<TenderAwardBean> tenderAwardBeans = null;
		try {
			TenderAwardBean tenderAwardBean = findPopulateTenderAwardBean(tenderAwardForm);
			tenderAwardBeans = tenderAwardDao.findTenderAwarded(tenderAwardBean, statusList);
			
			// Logic to remove Tender Objection Details whose status is not in the given statusList object
			Iterator<TenderObjectionBean> tenderObjectionBeanIterator = null;
			TenderObjectionBean tenderObjectionBean = null;
			for (TenderAwardBean tenderAwardBean1 : tenderAwardBeans) {
				Set<TenderObjectionBean> tenderObjectionBeans = tenderAwardBean1.getTenderObjectionBeans();
				tenderObjectionBeanIterator = tenderObjectionBeans.iterator();
				if(!MisUtility.ifEmpty(tenderObjectionBeans)){
					while(tenderObjectionBeanIterator.hasNext()){
						tenderObjectionBean = tenderObjectionBeanIterator.next();
						if(MISConstants.MASTER_STATUS_DELETED.equals(tenderObjectionBean.getMisAuditBean().getStatus())){
							tenderObjectionBeanIterator.remove();
							break;
						}
					}
				}
			}
			
			// Logic to remove Tender Security Deposit Details whose status is not in the given statusList object
			Iterator<TenderSecurityDepositBean> tenderSecurityDepositBeanIterator = null;
			TenderSecurityDepositBean tenderSecurityDepositBean = null;
			for (TenderAwardBean tenderAwardBean1 : tenderAwardBeans) {
				Set<TenderSecurityDepositBean> tenderSecurityDepositBeans = tenderAwardBean1.getTenderSecurityDepositBeans();
				tenderSecurityDepositBeanIterator = tenderSecurityDepositBeans.iterator();
				if(!MisUtility.ifEmpty(tenderSecurityDepositBeans)){
					while(tenderSecurityDepositBeanIterator.hasNext()){
						tenderSecurityDepositBean = tenderSecurityDepositBeanIterator.next();
						if(MISConstants.MASTER_STATUS_DELETED.equals(tenderSecurityDepositBean.getMisAuditBean().getStatus())){
							tenderSecurityDepositBeanIterator.remove();
							break;
						}
					}
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		}
		
		return tenderAwardBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String saveTenderAwarded(TenderAwardForm tenderAwardForm, MISSessionBean misSessionBean) throws MISException {
		String tenderId = null;
		
		try {
			TenderAwardBean tenderAwardBean = populateTenderAwardBean(tenderAwardForm);
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			tenderAwardBean.setMisAuditBean(misAuditBean);
			
			tenderId = tenderAwardDao.saveTenderAwarded(tenderAwardBean);
			if(tenderAwardForm.isObjectionIfAny()){
				Collection<TenderObjectionBean> tenderObjectionBeans = populateTenderObjectionBeans(tenderAwardForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(tenderObjectionBeans)){
					tenderObjectionDao.saveOrUpdateTenderObjectionBeans(tenderObjectionBeans);
				}
			}
			Collection<TenderSecurityDepositBean> tenderSecurityDepositBeans = populateTenderSecurityDepositBeans(tenderAwardForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
			if(!MisUtility.ifEmpty(tenderSecurityDepositBeans)){
				tenderSecurityDepositDao.saveOrUpdateTenderSecurityDepositBeans(tenderSecurityDepositBeans);
			}
			
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		}
		
		return tenderId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateTenderAwarded(TenderAwardForm tenderAwardForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			TenderAwardBean tenderAwardBean = populateTenderAwardBean(tenderAwardForm);;
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			tenderAwardBean.setMisAuditBean(misAuditBean);
			if(MisUtility.ifEmpty(tenderAwardForm.getContractNo())){
				
				ContractHeaderBean contractHeaderBean = new ContractHeaderBean();
				contractHeaderBean.setContractId(tenderAwardForm.getContractNo());
				/* --------------------to check tender ID already exit--------------------------------  */
				contractHeaderBean.setTenderId(tenderAwardForm.getTenderNo());
				List<String> statusList1 = new ArrayList<String>();
				statusList1.add(MISConstants.MASTER_STATUS_APPROVED);
				statusList1.add(MISConstants.MASTER_STATUS_VERIFIED);
				List<ContractHeaderBean> contractHeaderBeans= contractDao.findContracts(contractHeaderBean, statusList1);
				if(contractHeaderBeans.size()!=0){
					
					throw new MISException(MISExceptionCodes.MIS003,"Contract Id Already exist");
				}					
				/*------------------*/
			}
			status = tenderAwardDao.updateTenderAwarded(tenderAwardBean);
			if(status){
				if(tenderAwardForm.isObjectionIfAny()){
					Collection<TenderObjectionBean> tenderObjectionBeans = populateTenderObjectionBeans(tenderAwardForm,misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
					if(!MisUtility.ifEmpty(tenderObjectionBeans)){
						if(!tenderObjectionDao.saveOrUpdateTenderObjectionBeans(tenderObjectionBeans)){
							String message = "Tender Objections are not updated.";
							log.error(message);
							log.error(tenderObjectionBeans);
							throw new MISException(MISExceptionCodes.MIS003, message);
						}
					}	
				}

				Collection<TenderSecurityDepositBean> tenderSecurityDepositBeans = populateTenderSecurityDepositBeans(tenderAwardForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(tenderSecurityDepositBeans)){
					if(!tenderSecurityDepositDao.saveOrUpdateTenderSecurityDepositBeans(tenderSecurityDepositBeans)){
						String message = "Tender Security Deposits are not Updated.";
						log.error(message);
						log.error(tenderSecurityDepositBeans);
						throw new MISException(MISExceptionCodes.MIS003, message);
					}
				}
			}
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
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteTenderAwarded(TenderAwardForm tenderAwardForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			TenderAwardBean tenderAwardBean = findPopulateTenderAwardBean(tenderAwardForm);
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			tenderAwardBean.setMisAuditBean(misAuditBean);
			
			status = tenderAwardDao.updateTenderAwarded(tenderAwardBean);
			if(status){
				if(tenderAwardForm.isObjectionIfAny()){
					Collection<TenderObjectionBean> tenderObjectionBeans = populateTenderObjectionBeans(tenderAwardForm,misSessionBean, MISConstants.MASTER_STATUS_DELETED);
					if(!MisUtility.ifEmpty(tenderObjectionBeans)){
						if(!tenderObjectionDao.saveOrUpdateTenderObjectionBeans(tenderObjectionBeans)){
							String message = "Tender Objections are not Deleted.";
							log.error(message);
							log.error(tenderObjectionBeans);
							throw new MISException(MISExceptionCodes.MIS003, message);
						}
					}
				}
				Collection<TenderSecurityDepositBean> tenderSecurityDepositBeans = populateTenderSecurityDepositBeans(tenderAwardForm, misSessionBean, MISConstants.MASTER_STATUS_DELETED);
				if(!MisUtility.ifEmpty(tenderSecurityDepositBeans)){
					if(!tenderSecurityDepositDao.saveOrUpdateTenderSecurityDepositBeans(tenderSecurityDepositBeans)){
						String message = "Tender Security Deposits are not Deleted.";
						log.error(message);
						log.error(tenderSecurityDepositBeans);
						throw new MISException(MISExceptionCodes.MIS003, message);
					}
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		}
		return status;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String postTenderAwarded(TenderAwardForm tenderAwardForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		String contractId = null;
		try {
			List <String> statusList = new ArrayList<String>() ;
		    statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			TenderAwardBean tenderAwardBean = new TenderAwardBean();
			tenderAwardBean.setTenderId(tenderAwardForm.getTenderNo().trim());
			tenderAwardBean = tenderAwardDao.findTenderAwarded(tenderAwardBean, statusList).get(0);
			//System.out.println("-------------Tender Award "+tenderAwardBean.getTenderId());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = tenderAwardBean.getMisAuditBean();
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			tenderAwardBean.setMisAuditBean(misAuditBean);
			List<TenderObjectionBean> tenderObjectionBeans = null;
			if(tenderAwardForm.isObjectionIfAny()){
				TenderObjectionBean tenderObjectionBean = new TenderObjectionBean();
				tenderObjectionBean.setTenderId(tenderAwardForm.getTenderNo());
				tenderObjectionBeans = tenderObjectionDao.getTenderObjectionBeans(tenderObjectionBean, statusList);
				for (TenderObjectionBean tenderObjection : tenderObjectionBeans) {
					if(MISConstants.MIS_TENDER_OBJECTION_OPEN.equalsIgnoreCase(tenderObjection.getStatusOfObjection())){
						throw new MISException(MISExceptionCodes.MIS004, "Objection status is open for tender id "+ tenderAwardForm.getTenderNo());
					}else{
						status = tenderAwardDao.updateTenderAwarded(tenderAwardBean);
					}
					misAuditBean = tenderObjection.getMisAuditBean();
					misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
					misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
					tenderObjection.setMisAuditBean(misAuditBean);
				}
			}else{
			//	System.out.println("-----------updating Tender-------------");
				status = tenderAwardDao.updateTenderAwarded(tenderAwardBean);
			}
			
			if(status){
				if(!MisUtility.ifEmpty(tenderObjectionBeans)){
					if(!tenderObjectionDao.saveOrUpdateTenderObjectionBeans(tenderObjectionBeans)){
						String message = "Tender Objections are not Approved.";
						log.error(message);
						log.error(tenderObjectionBeans);
						throw new MISException(MISExceptionCodes.MIS003, message);
					}
				}
				TenderSecurityDepositBean securityDepositBean = new TenderSecurityDepositBean();
				securityDepositBean.setTenderId(tenderAwardForm.getTenderNo());
				Collection<TenderSecurityDepositBean> tenderSecurityDepositBeans = tenderSecurityDepositDao.getTenderSecurityDepositBeans(securityDepositBean, statusList);
				for (TenderSecurityDepositBean tenderSecurityDepositBean : tenderSecurityDepositBeans) {
					misAuditBean = tenderSecurityDepositBean.getMisAuditBean();
					misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
					misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
					tenderSecurityDepositBean.setMisAuditBean(misAuditBean);	
				}
				
				if(!MisUtility.ifEmpty(tenderSecurityDepositBeans)){
					if(!tenderSecurityDepositDao.saveOrUpdateTenderSecurityDepositBeans(tenderSecurityDepositBeans)){
						String message = "Tender Security Deposits are not Approved.";
						log.error(message);
						log.error(tenderSecurityDepositBeans);
						throw new MISException(MISExceptionCodes.MIS003, message);
					}
				}
				 
//				ContractHeaderBean contractHeaderBean1 = new ContractHeaderBean();
//				contractHeaderBean1.setTenderId(tenderAwardForm.getTenderNo());
//				contractHeaderBean1.setLocationId(tenderAwardForm.getLocationId());
//				List<ContractHeaderBean> contractHeaderBeans = contractDao.findTender(contractHeaderBean1);
					ContractHeaderBean contractHeaderBean = new ContractHeaderBean();
					contractHeaderBean.setContractId(tenderAwardForm.getContractNo());
					/* --------------------to check tender ID already exit--------------------------------  */
					contractHeaderBean.setTenderId(tenderAwardForm.getTenderNo());
					List<String> statusList1 = new ArrayList<String>();
					statusList1.add(MISConstants.MASTER_STATUS_APPROVED);
					statusList1.add(MISConstants.MASTER_STATUS_VERIFIED);
					List<ContractHeaderBean> contractHeaderBeans= contractDao.findContracts(contractHeaderBean, statusList1);
					if(contractHeaderBeans.size()!=0){
						
						throw new MISException(MISExceptionCodes.MIS003,"Contract Id Already exist");
					}					
					/*------------------*/
					VendorBean vendorBean = new VendorBean();
					vendorBean.setVendorId(tenderAwardForm.getVendorId());
					contractHeaderBean.setVendorBean(vendorBean);
					contractHeaderBean.setContractDate(MisUtility.convertStringToDate(tenderAwardForm.getContractStartDate()));
					misAuditBean = new MISAuditBean();
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
					misAuditBean.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean.setEntDate(misSessionBean.getEnteredDate());
					contractHeaderBean.setMisAuditBean(misAuditBean);
					contractHeaderBean.setLocationId(tenderAwardForm.getLocationId());
					
					contractId = contractDao.saveContract(contractHeaderBean);
				}
			
				
//			}
//		}
			
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return contractId;
	}
	
	private TenderAwardBean populateTenderAwardBean(TenderAwardForm tenderAwardForm){
		TenderAwardBean tenderAwardBean = null;
		try {
			tenderAwardBean = new TenderAwardBean();
			tenderAwardBean.setTenderId(tenderAwardForm.getTenderNo());
			tenderAwardBean.setTenderAmount(tenderAwardForm.getTenderAwardAmount());
			tenderAwardBean.setContractEndDate(MisUtility.convertStringToDate(tenderAwardForm.getContractEndDate()));
			tenderAwardBean.setContractStartDate(MisUtility.convertStringToDate(tenderAwardForm.getContractStartDate()));
			tenderAwardBean.setContractNo(tenderAwardForm.getContractNo());
			tenderAwardBean.setVendorId(tenderAwardForm.getVendorId());
			tenderAwardBean.setNoticeToProceed(MisUtility.convertStringToDate(tenderAwardForm.getNoticeToProceed()));
			
			if(tenderAwardForm.getTenderType().equalsIgnoreCase("SHOPPING") ||tenderAwardForm.getTenderType().equalsIgnoreCase("NCB") ||tenderAwardForm.getTenderType().equalsIgnoreCase("WORKS") ){
				tenderAwardBean.setBidEvaluationDate(MisUtility.convertStringToDate(tenderAwardForm.getBidEvaluationDate()));
			}
			
			tenderAwardBean.setSigningOfContract(MisUtility.convertStringToDate(tenderAwardForm.getSigningOfContract()));
			tenderAwardBean.setObjectionable(tenderAwardForm.isObjectionIfAny());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
		}
		return tenderAwardBean;
	}
	
	private TenderAwardBean findPopulateTenderAwardBean(TenderAwardForm tenderAwardForm){
		TenderAwardBean tenderAwardBean = null;
		try {
			tenderAwardBean = new TenderAwardBean();
			tenderAwardBean.setTenderId(tenderAwardForm.getTenderNo());
			tenderAwardBean.setTenderAmount(tenderAwardForm.getTenderAwardAmount());
			tenderAwardBean.setContractEndDate(MisUtility.convertStringToDate(tenderAwardForm.getContractEndDate()));
			tenderAwardBean.setContractStartDate(MisUtility.convertStringToDate(tenderAwardForm.getContractStartDate()));
			tenderAwardBean.setContractNo(tenderAwardForm.getContractNo());
			tenderAwardBean.setVendorId(tenderAwardForm.getVendorId());
			tenderAwardBean.setNoticeToProceed(MisUtility.convertStringToDate(tenderAwardForm.getNoticeToProceed()));
			if(tenderAwardForm.getTenderType().equalsIgnoreCase("SHOPPING") ||tenderAwardForm.getTenderType().equalsIgnoreCase("NCB") ||tenderAwardForm.getTenderType().equalsIgnoreCase("WORKS") ){
				tenderAwardBean.setBidEvaluationDate(MisUtility.convertStringToDate(tenderAwardForm.getBidEvaluationDate()));
			}
			tenderAwardBean.setSigningOfContract(MisUtility.convertStringToDate(tenderAwardForm.getSigningOfContract()));
			tenderAwardBean.setObjectionable(tenderAwardForm.isObjectionIfAny());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
		}
		return tenderAwardBean;
	}
	
	@SuppressWarnings("unchecked")
	private Collection<TenderObjectionBean> populateTenderObjectionBeans(TenderAwardForm tenderAwardForm, MISSessionBean misSessionBean, String status) throws MISException{
		String tenderId = tenderAwardForm.getTenderNo();
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		List<TenderObjectionBean> tenderObjectionBeans = new ArrayList<TenderObjectionBean>();
		try {
			TenderObjectionBean tenderObjectionBean = null;
			Collection<TenderObjectionGridBean> addedTenderObjectionBeans = tenderAwardForm.getObjectionDatagrid().getAddedData();
			for (TenderObjectionGridBean tenderObjectionGridBean : addedTenderObjectionBeans) {
				 tenderObjectionBean = new TenderObjectionBean();
				 tenderObjectionBean.setDateOfObjection(MisUtility.convertStringToDate(tenderObjectionGridBean.getDateOfObjection()));
				 tenderObjectionBean.setLocationId(tenderObjectionGridBean.getLocationId());
				 tenderObjectionBean.setReasons(tenderObjectionGridBean.getReasons());
				 tenderObjectionBean.setStatusOfObjection(tenderObjectionGridBean.getStatusOfObjection());
				 tenderObjectionBean.setObjectionId(tenderObjectionGridBean.getObjectionId());
				 tenderObjectionBean.setTenderId(tenderId);
				 tenderObjectionBean.setObjectedBy(tenderObjectionGridBean.getObjectedBy());
				 tenderObjectionBean.setMisAuditBean(misAuditBean);
				 tenderObjectionBeans.add(tenderObjectionBean);
			}
			
			Collection<TenderObjectionGridBean> modifiedTenderObjectionBeans = tenderAwardForm.getObjectionDatagrid().getModifiedData();
			for (TenderObjectionGridBean tenderObjectionGridBean : modifiedTenderObjectionBeans) {
				 tenderObjectionBean = new TenderObjectionBean();
				 tenderObjectionBean.setDateOfObjection(MisUtility.convertStringToDate(tenderObjectionGridBean.getDateOfObjection()));
				 tenderObjectionBean.setLocationId(tenderObjectionGridBean.getLocationId());
				 tenderObjectionBean.setReasons(tenderObjectionGridBean.getReasons());
				 tenderObjectionBean.setStatusOfObjection(tenderObjectionGridBean.getStatusOfObjection());
				 tenderObjectionBean.setObjectionId(tenderObjectionGridBean.getObjectionId());
				 tenderObjectionBean.setTenderId(tenderId);
				 tenderObjectionBean.setObjectedBy(tenderObjectionGridBean.getObjectedBy());
				 tenderObjectionBean.setMisAuditBean(misAuditBean);
				 tenderObjectionBeans.add(tenderObjectionBean);
			}
			Collection<TenderObjectionGridBean> deletedTenderObjectionBeans = tenderAwardForm.getObjectionDatagrid().getDeletedData();
			for (TenderObjectionGridBean tenderObjectionGridBean : deletedTenderObjectionBeans) {
				tenderObjectionBean = new TenderObjectionBean();
				 tenderObjectionBean.setDateOfObjection(MisUtility.convertStringToDate(tenderObjectionGridBean.getDateOfObjection()));
				 tenderObjectionBean.setLocationId(tenderObjectionGridBean.getLocationId());
				 tenderObjectionBean.setReasons(tenderObjectionGridBean.getReasons());
				 tenderObjectionBean.setStatusOfObjection(tenderObjectionGridBean.getStatusOfObjection());
				 tenderObjectionBean.setObjectionId(tenderObjectionGridBean.getObjectionId());
				 tenderObjectionBean.setTenderId(tenderId);
				 tenderObjectionBean.setObjectedBy(tenderObjectionGridBean.getObjectedBy());
				 misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				 tenderObjectionBean.setMisAuditBean(misAuditBean);
				 tenderObjectionBeans.add(tenderObjectionBean);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return tenderObjectionBeans;
	}
	
	@SuppressWarnings("unchecked")
	private Collection<TenderSecurityDepositBean> populateTenderSecurityDepositBeans(TenderAwardForm tenderAwardForm, MISSessionBean misSessionBean, String status) throws MISException{
		String tenderId = tenderAwardForm.getTenderNo();
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		
		List<TenderSecurityDepositBean> tenderSecurityDepositBeans = new ArrayList<TenderSecurityDepositBean>();
		try {
			TenderSecurityDepositBean tenderSecurityDepositBean = null;
			Collection<TenderSecurityGridBean> addedTenderSecurityDepositBeans = tenderAwardForm.getSecurityDepositDatagrid().getAddedData();
			for (TenderSecurityGridBean tenderSecurityGridBean : addedTenderSecurityDepositBeans) {
				tenderSecurityDepositBean = new TenderSecurityDepositBean();
				tenderSecurityDepositBean.setAmount(tenderSecurityGridBean.getAmount());
				tenderSecurityDepositBean.setDateOfIssue(MisUtility.convertStringToDate(tenderSecurityGridBean.getDateOfIssue()));
				tenderSecurityDepositBean.setDepositId(tenderSecurityGridBean.getDepositId());
				tenderSecurityDepositBean.setEndDate(MisUtility.convertStringToDate(tenderSecurityGridBean.getEndDate()));
				tenderSecurityDepositBean.setInstrumentIssuer(tenderSecurityGridBean.getInstrumentIssuer());
				tenderSecurityDepositBean.setInstrumentType(tenderSecurityGridBean.getInstrumentType());
				tenderSecurityDepositBean.setLocationId(tenderSecurityGridBean.getLocationId());
				tenderSecurityDepositBean.setRemarks(tenderSecurityGridBean.getRemarks());
				tenderSecurityDepositBean.setStartDate(MisUtility.convertStringToDate(tenderSecurityGridBean.getStartDate()));
				tenderSecurityDepositBean.setTenderId(tenderId);
				tenderSecurityDepositBean.setMisAuditBean(misAuditBean);
				tenderSecurityDepositBeans.add(tenderSecurityDepositBean);
			}
			Collection<TenderSecurityGridBean> modifiedTenderSecurityDepositBeans = tenderAwardForm.getSecurityDepositDatagrid().getModifiedData();
			for (TenderSecurityGridBean tenderSecurityGridBean : modifiedTenderSecurityDepositBeans) {
				tenderSecurityDepositBean = new TenderSecurityDepositBean();
				tenderSecurityDepositBean.setAmount(tenderSecurityGridBean.getAmount());
				tenderSecurityDepositBean.setDateOfIssue(MisUtility.convertStringToDate(tenderSecurityGridBean.getDateOfIssue()));
				tenderSecurityDepositBean.setDepositId(tenderSecurityGridBean.getDepositId());
				tenderSecurityDepositBean.setEndDate(MisUtility.convertStringToDate(tenderSecurityGridBean.getEndDate()));
				tenderSecurityDepositBean.setInstrumentIssuer(tenderSecurityGridBean.getInstrumentIssuer());
				tenderSecurityDepositBean.setInstrumentType(tenderSecurityGridBean.getInstrumentType());
				tenderSecurityDepositBean.setLocationId(tenderSecurityGridBean.getLocationId());
				tenderSecurityDepositBean.setRemarks(tenderSecurityGridBean.getRemarks());
				tenderSecurityDepositBean.setStartDate(MisUtility.convertStringToDate(tenderSecurityGridBean.getStartDate()));
				tenderSecurityDepositBean.setTenderId(tenderId);
				tenderSecurityDepositBean.setMisAuditBean(misAuditBean);
				tenderSecurityDepositBeans.add(tenderSecurityDepositBean);
			}
			
			Collection<TenderSecurityGridBean> deletedTenderSecurityDepositBeans = tenderAwardForm.getSecurityDepositDatagrid().getDeletedData();
			for (TenderSecurityGridBean tenderSecurityGridBean : deletedTenderSecurityDepositBeans) {
				tenderSecurityDepositBean = new TenderSecurityDepositBean();
				tenderSecurityDepositBean.setAmount(tenderSecurityGridBean.getAmount());
				tenderSecurityDepositBean.setDateOfIssue(MisUtility.convertStringToDate(tenderSecurityGridBean.getDateOfIssue()));
				tenderSecurityDepositBean.setDepositId(tenderSecurityGridBean.getDepositId());
				tenderSecurityDepositBean.setEndDate(MisUtility.convertStringToDate(tenderSecurityGridBean.getEndDate()));
				tenderSecurityDepositBean.setInstrumentIssuer(tenderSecurityGridBean.getInstrumentIssuer());
				tenderSecurityDepositBean.setInstrumentType(tenderSecurityGridBean.getInstrumentType());
				tenderSecurityDepositBean.setLocationId(tenderSecurityGridBean.getLocationId());
				tenderSecurityDepositBean.setRemarks(tenderSecurityGridBean.getRemarks());
				tenderSecurityDepositBean.setStartDate(MisUtility.convertStringToDate(tenderSecurityGridBean.getStartDate()));
				tenderSecurityDepositBean.setTenderId(tenderId);
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				tenderSecurityDepositBean.setMisAuditBean(misAuditBean);
				tenderSecurityDepositBeans.add(tenderSecurityDepositBean);
			}
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return tenderSecurityDepositBeans;
	}

}
