package com.prwss.mis.tender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
import com.prwss.mis.tender.award.dao.TenderAwardBean;
import com.prwss.mis.tender.dao.AdvUploadBean;
import com.prwss.mis.tender.dao.CorrigendumUploadBean;
import com.prwss.mis.tender.dao.EOIUploadBean;
import com.prwss.mis.tender.dao.QuoatationUploadBean;
import com.prwss.mis.tender.dao.TenderDao;
import com.prwss.mis.tender.dao.TenderDetailBean;
import com.prwss.mis.tender.dao.TenderDetailDao;
import com.prwss.mis.tender.dao.TenderHeaderBean;
import com.prwss.mis.tender.dao.TenderUploadBean;
import com.prwss.mis.tender.responsive.NonResponsiveTenderBean;
import com.prwss.mis.tender.struts.AdvUploadForm;
import com.prwss.mis.tender.struts.CorrigendumUploadForm;
import com.prwss.mis.tender.struts.EOIUploadForm;
import com.prwss.mis.tender.struts.QuoatationUploadForm;
import com.prwss.mis.tender.struts.TenderManagementForm;
import com.prwss.mis.tender.struts.TenderUploadForm;

public class TenderBOImpl extends TenderAwardBOImpl implements TenderBO {
	private Logger log = Logger.getLogger(TenderBOImpl.class);
	private TenderDao tenderDao;
	private TenderDetailDao tenderDetailDao;

	public void setTenderDao(TenderDao tenderDao) {
		this.tenderDao = tenderDao;
	}

	public void setTenderDetailDao(TenderDetailDao tenderDetailDao) {
		this.tenderDetailDao = tenderDetailDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TenderHeaderBean> findTenders(TenderManagementForm tenderManagementForm, List<String> statusList) throws MISException {
		List<TenderHeaderBean> tenderHeaderBeans = null;
		
		try {
			TenderHeaderBean tenderHeaderBean = new TenderHeaderBean();
			//if(MisUtility.ifEmpty(tenderManagementForm.getTenderNo())){
			tenderHeaderBean.setLocationId(tenderManagementForm.getLocationId());
			tenderHeaderBean.setTenderId(tenderManagementForm.getTenderNo());
			System.out.println("tenderHeaderBean.tenderId="+tenderHeaderBean.getTenderId()+"  location:"+tenderManagementForm.getLocationId()+"   Plan:"+tenderManagementForm.getPlanId()+"  Procurement:"+tenderManagementForm.getProcurementId()+"  Package :"+tenderManagementForm.getPackageId());
			tenderHeaderBean.setPlanId(tenderManagementForm.getPlanId());
			tenderHeaderBean.setProcurementId(tenderManagementForm.getProcurementId());
			tenderHeaderBean.setPackageId(tenderManagementForm.getPackageId());
			tenderHeaderBean.setSchemeCode(tenderManagementForm.getSchemeCode());
			
			tenderHeaderBeans = tenderDao.findTender(tenderHeaderBean, statusList);
			 
			//}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} 
		return tenderHeaderBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String save(TenderManagementForm tenderManagementForm, MISSessionBean misSessionBean) throws MISException {
		String typeOfTender = tenderManagementForm.getTypeOfTender();
		String tenderId = null;
		List<String> statusList =new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		statusList.add(MISConstants.MASTER_STATUS_DELETED);
		try {
		/*TenderHeaderBean tenderBean = new TenderHeaderBean();
		tenderBean.setTenderId(tenderManagementForm.getTenderNo());
		List<TenderHeaderBean> tenderHeaderBeans = tenderDao.findTender(tenderBean, null);
		if(!MisUtility.ifEmpty(tenderHeaderBeans)){
			throw new MISException(MISExceptionCodes.MIS001, "Tender No\t"+tenderManagementForm.getTenderNo());			
		}*/
		String tender="T-"+tenderManagementForm.getPackageId();
		Set<TenderHeaderBean> tenderHeaderBeans2= new TreeSet<TenderHeaderBean>();
		TenderHeaderBean tenderHeaderBean3=new TenderHeaderBean();
		//tenderHeaderBean3.setTenderId(tender);
		tenderHeaderBean3.setPackageId(tenderManagementForm.getPackageId());
		tenderHeaderBeans2=tenderDao.getDistinctTenders(tenderHeaderBean3,statusList);
		Set<NonResponsiveTenderBean> tenderBeans = tenderDao.getDistinctTenderCodesNONResponsive(tenderManagementForm.getPackageId());
		int tenderCnt=tenderHeaderBeans2.size()+1;
		System.out.println("Cnt: "+tenderCnt+": e ref no: "+tenderManagementForm.geteTenderRefNo());
		statusList.remove(MISConstants.MASTER_STATUS_DELETED);
		
		List<TenderHeaderBean> headerBeans = new ArrayList<TenderHeaderBean>(tenderDao.getDistinctTenders(tenderHeaderBean3, statusList));
		if(tenderCnt==1){
			System.out.println("-------------Check1");
			tenderManagementForm.setTenderNo(tender);					
		}else{
			boolean flag = false;
			if(tenderBeans.size()!=0){
				for(TenderHeaderBean headerBean:tenderHeaderBeans2){				
					for(NonResponsiveTenderBean bean:tenderBeans){
						if(bean.getTenderId().equals(headerBean.getTenderId())){
							flag = true;
							System.out.println("-------------Check2");
							if(!tenderManagementForm.getTenderNotificationDate().equals("")){
								System.out.println("-------------Check3");
									if(checkRebidDate(tenderManagementForm.getTenderNotificationDate(),headerBeans.get(headerBeans.size()-1).getPublishDate())){
										System.out.println("-------------Check4");
										throw new MISException(MISExceptionCodes.MIS003, "Rebiding Publishing Date must be greater than Previous Tender Publishing Date");	
									}								
							}
						}
						
					}
				}
				if(flag){
					System.out.println("----------------------Its a NonResponsive Tender");
					tenderManagementForm.setTenderNo(tender+"/"+tenderCnt);
				}
				else{
					System.out.println("----------------Exception--------------------");
					throw new MISException(MISExceptionCodes.MIS003, "New tender can be created only if 'No bid received' or 'bid/s received are not responsive'");	
				}
			}
			else{
				SortedSet<TenderHeaderBean> sortedSet = (SortedSet<TenderHeaderBean>) tenderHeaderBeans2;
				String status = sortedSet.last().getMisAuditBean().getStatus();
				System.out.println("Status is Deleted");
				if(status.equals(MISConstants.MASTER_STATUS_DELETED)){
					tenderManagementForm.setTenderNo(tender+"/"+tenderCnt);
				}
				else{
				throw new MISException(MISExceptionCodes.MIS003, "New tender can be created only if 'No bid received' or 'bid/s received are not responsive'");	
				}
			}
			
		}						
		TenderHeaderBean tenderHeaderBean = populateTenderHeaderBean(tenderManagementForm);
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
		tenderHeaderBean.setMisAuditBean(misAuditBean);
		tenderHeaderBean.setLocationId(tenderManagementForm.getLocationId());
		
		boolean detailStatus = false;
		
			tenderId = tenderDao.saveTender(tenderHeaderBean);		
			
			if(MisUtility.ifEmpty(tenderId)){
				List<TenderDetailBean> tenderDetailBeans = null;
				if(MISConstants.MIS_TYPE_OF_TENDER_WORKS.equalsIgnoreCase(typeOfTender)){
					tenderDetailBeans = populateWorksTenderDetailBean(tenderManagementForm, tenderId, misSessionBean);
				} else if(MISConstants.MIS_TYPE_OF_TENDER_CONSULTANCY.equalsIgnoreCase(typeOfTender)){
					tenderDetailBeans = populateConsultancyTenderDetailBean(tenderManagementForm, tenderId, misSessionBean);
				} else if(MISConstants.MIS_TYPE_OF_TENDER_GOODS.equalsIgnoreCase(typeOfTender)){
					tenderDetailBeans = populateGoodsTenderDetailBean(tenderManagementForm, tenderId, misSessionBean);
				} else if(MISConstants.MIS_TYPE_OF_TENDER_SERVICES.equalsIgnoreCase(typeOfTender)){
					tenderDetailBeans = populateServicesTenderDetailBean(tenderManagementForm, tenderId, misSessionBean);
				}
				for (TenderDetailBean tenderDetailBean : tenderDetailBeans) {
					tenderDetailBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
					tenderDetailBean.setEnteredBy(misSessionBean.getEnteredBy());
					tenderDetailBean.setEnteredDate(misSessionBean.getEnteredDate());
				}
				// save tender details in the detail bean
				detailStatus = tenderDetailDao.saveOrupdateTenderDetails(tenderDetailBeans);
				
				if(!detailStatus){
					throw new MISException(MISExceptionCodes.MIS003, "Tender Details not saved for the Tender Id :\t"+tenderId);
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return tenderId;
	}

	private boolean checkRebidDate(String tenderNotificationDate,
			Date closeDate) throws MISException {
		try {
			
					Date notificationDate = new SimpleDateFormat("dd-MM-yyyy").parse(tenderNotificationDate);
					Date closingDate = closeDate;
					Locale aLocale = new Locale(Locale.ENGLISH.getLanguage(), Locale.UK.getCountry());
					Calendar cal = GregorianCalendar.getInstance(aLocale);
					Calendar cal2 = GregorianCalendar.getInstance(aLocale);
					cal.setTime(notificationDate);
					cal2.setTime(closingDate);
				
					System.out.println("-----------notification Date "+notificationDate+"-----------------closing Date"+closingDate);
					long millsecOfNotificationDate = cal.getTimeInMillis();
					long millsecOfCloseDate = cal2.getTimeInMillis();
				
					System.out.println("-----------notification Date "+millsecOfNotificationDate+"-----------------closing Date"+millsecOfCloseDate);
					if(millsecOfNotificationDate<=millsecOfCloseDate){
						return true;
					}			
						
					
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			return false;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean update(TenderManagementForm tenderManagementForm, MISSessionBean misSessionBean) throws MISException {
		String typeOfTender = tenderManagementForm.getTypeOfTender();
		String tenderId = tenderManagementForm.getTenderNo();
		TenderHeaderBean tenderHeaderBean = populateTenderHeaderBean(tenderManagementForm);
		tenderHeaderBean.setTenderId(tenderManagementForm.getTenderNo());
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
		tenderHeaderBean.setMisAuditBean(misAuditBean);
		tenderHeaderBean.setLocationId(tenderManagementForm.getLocationId());
		boolean status = false;
		boolean detailStatus = false;
		List<String> statusList =new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		statusList.add(MISConstants.MASTER_STATUS_DELETED);	
		try {
		/*	TenderHeaderBean tenderHeaderBean3=new TenderHeaderBean();
			//tenderHeaderBean3.setTenderId(tender);
			tenderHeaderBean3.setPackageId(tenderManagementForm.getPackageId());
			List<TenderHeaderBean> headerBeans = new ArrayList<TenderHeaderBean>(tenderDao.getDistinctTenders(tenderHeaderBean3, statusList));
			
			if(headerBeans.size()!=0){
			System.out.println("-------------Check2");
			if(!tenderManagementForm.getTenderNotificationDate().equals("")){
				System.out.println("-------------Check3");
					if(checkRebidDate(tenderManagementForm.getTenderNotificationDate(),headerBeans.get(headerBeans.size()-1).getPublishDate())){
						System.out.println("-------------Check4");
						throw new MISException(MISExceptionCodes.MIS003, "Rebiding Publishing Date must be greater than Previous Tender Publishing Date");	
					}								
			}
			}
			*/
			
			status = tenderDao.updateTender(tenderHeaderBean);
			if(status){
				List<TenderDetailBean> tenderDetailBeans = null;
				if(MISConstants.MIS_TYPE_OF_TENDER_WORKS.equalsIgnoreCase(typeOfTender)){
					tenderDetailBeans = populateWorksTenderDetailBean(tenderManagementForm, tenderId, misSessionBean);
				} else if(MISConstants.MIS_TYPE_OF_TENDER_CONSULTANCY.equalsIgnoreCase(typeOfTender)){
					tenderDetailBeans = populateConsultancyTenderDetailBean(tenderManagementForm, tenderId, misSessionBean);
				} else if(MISConstants.MIS_TYPE_OF_TENDER_GOODS.equalsIgnoreCase(typeOfTender)){
					tenderDetailBeans = populateGoodsTenderDetailBean(tenderManagementForm, tenderId, misSessionBean);
				} else if(MISConstants.MIS_TYPE_OF_TENDER_SERVICES.equalsIgnoreCase(typeOfTender)){
					tenderDetailBeans = populateServicesTenderDetailBean(tenderManagementForm, tenderId, misSessionBean);
				}
				for (TenderDetailBean tenderDetailBean : tenderDetailBeans) {
					tenderDetailBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
					tenderDetailBean.setEnteredBy(misSessionBean.getEnteredBy());
					tenderDetailBean.setEnteredDate(misSessionBean.getEnteredDate());
				}
				// save tender details in the detail bean
				detailStatus = tenderDetailDao.saveOrupdateTenderDetails(tenderDetailBeans);
				if(!detailStatus){
					throw new MISException(MISExceptionCodes.MIS003, "Tender Details not saved or updated for the Tender Id :\t"+tenderManagementForm.getTenderNo());
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} 
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean delete(TenderManagementForm tenderManagementForm, MISSessionBean misSessionBean) throws MISException {
		String typeOfTender = tenderManagementForm.getTypeOfTender();
		String tenderId = tenderManagementForm.getTenderNo();
		TenderHeaderBean tenderHeaderBean = populateTenderHeaderBean(tenderManagementForm);
		tenderHeaderBean.setTenderId(tenderManagementForm.getTenderNo());
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
		tenderHeaderBean.setMisAuditBean(misAuditBean);
		tenderHeaderBean.setLocationId(tenderManagementForm.getLocationId());
		boolean status = false;
		boolean detailStatus = false;
		try {
			
			status = tenderDao.updateTender(tenderHeaderBean);		
			if(status){
				List<TenderDetailBean> tenderDetailBeans = null;
				if(MISConstants.MIS_TYPE_OF_TENDER_WORKS.equalsIgnoreCase(typeOfTender)){
					tenderDetailBeans = populateWorksTenderDetailBean(tenderManagementForm, tenderId, misSessionBean);
				} else if(MISConstants.MIS_TYPE_OF_TENDER_CONSULTANCY.equalsIgnoreCase(typeOfTender)){
					tenderDetailBeans = populateConsultancyTenderDetailBean(tenderManagementForm, tenderId, misSessionBean);
				} else if(MISConstants.MIS_TYPE_OF_TENDER_GOODS.equalsIgnoreCase(typeOfTender)){
					tenderDetailBeans = populateGoodsTenderDetailBean(tenderManagementForm, tenderId, misSessionBean);
				} else if(MISConstants.MIS_TYPE_OF_TENDER_SERVICES.equalsIgnoreCase(typeOfTender)){
					tenderDetailBeans = populateServicesTenderDetailBean(tenderManagementForm, tenderId, misSessionBean);
				}
				for (TenderDetailBean tenderDetailBean : tenderDetailBeans) {
					tenderDetailBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
					tenderDetailBean.setEnteredBy(misSessionBean.getEnteredBy());
					tenderDetailBean.setEnteredDate(misSessionBean.getEnteredDate());
				}
				// save tender details in the detail bean
				detailStatus = tenderDetailDao.saveOrupdateTenderDetails(tenderDetailBeans);
				if(!detailStatus){
					throw new MISException(MISExceptionCodes.MIS003, "Tender Details not saved or updated for the Tender Id :\t"+tenderManagementForm.getTenderNo());
				}
			}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} 
		
		return status;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String post(TenderManagementForm tenderManagementForm, MISSessionBean misSessionBean) throws MISException {
		String tenderId = tenderManagementForm.getTenderNo();
		TenderHeaderBean tenderHeaderBean = new TenderHeaderBean();// populateTenderHeaderBean(tenderManagementForm);
		tenderHeaderBean.setLocationId(tenderManagementForm.getLocationId());
		tenderHeaderBean.setTenderId(tenderManagementForm.getTenderNo());
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		tenderHeaderBean = tenderDao.findTender(tenderHeaderBean, statusList).get(0);
		MISAuditBean misAuditBean = tenderHeaderBean.getMisAuditBean();
		misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
		misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
		tenderHeaderBean.setMisAuditBean(misAuditBean);
		boolean status = false;
		boolean detailStatus = false;
		try {
			status = tenderDao.updateTender(tenderHeaderBean);		
			if(status){
				List<TenderDetailBean> tenderDetailBeans = new ArrayList<TenderDetailBean>(tenderHeaderBean.getTenderDetailBeans());
				for (TenderDetailBean tenderDetailBean : tenderDetailBeans) {
					tenderDetailBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
					tenderDetailBean.setAuthorizedBy(misSessionBean.getEnteredBy());
					tenderDetailBean.setAuthorizedDate(misSessionBean.getEnteredDate());
				}
// save tender details in the detail bean
				detailStatus = tenderDetailDao.saveOrupdateTenderDetails(tenderDetailBeans);
				
				if(!detailStatus){
					throw new MISException(MISExceptionCodes.MIS003, "Tender Details not saved or updated for the Tender Id :\t"+tenderManagementForm.getTenderNo());
				}
			}
			if(detailStatus){
				TenderAwardBean tenderAwardBean = new TenderAwardBean();
				tenderAwardBean.setTenderId(tenderManagementForm.getTenderNo());
				MISAuditBean misAuditBean1 = new MISAuditBean();
				misAuditBean1.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
				misAuditBean1.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean1.setEntDate(misSessionBean.getEnteredDate());
				tenderAwardBean.setMisAuditBean(misAuditBean1);
				tenderId = tenderAwardDao.saveTenderAwarded(tenderAwardBean);
				if(!MisUtility.ifEmpty(tenderId)){
					throw new MISException(MISExceptionCodes.MIS003, "Tender not saved for the Tender Id : "+tenderManagementForm.getTenderNo()+" in Tender Award Table");
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		}
		
		return tenderId;
	}
	

	@Override
	public Set<TenderDetailBean> getTenderDetails(String tenderId) throws MISException {
		Set<TenderDetailBean> tenderDetailBeans = null;
		
		try {
			if(MisUtility.ifEmpty(tenderId)){
				tenderDetailBeans = tenderDetailDao.findTenderDetails(tenderId);
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} 
		
		return tenderDetailBeans;
	}
	
	
	
	
	private TenderHeaderBean populateTenderHeaderBean(TenderManagementForm tenderManagementForm) throws MISException{
		TenderHeaderBean tenderHeaderBean = new TenderHeaderBean();
			tenderHeaderBean.setCloseDate(MisUtility.convertStringToDate(tenderManagementForm.getClosingDate()));
			tenderHeaderBean.seteTenderRefNo(tenderManagementForm.geteTenderRefNo());
			tenderHeaderBean.setPackageDescription(tenderManagementForm.getPackageDescription());
			tenderHeaderBean.setOpenDate(MisUtility.convertStringToDate(tenderManagementForm.getOpeningDate()));
			tenderHeaderBean.setPackageId(tenderManagementForm.getPackageId());
			tenderHeaderBean.setProcurementType(tenderManagementForm.getModeOfProcurement());
			tenderHeaderBean.setPublishDate(MisUtility.convertStringToDate(tenderManagementForm.getTenderNotificationDate()));
			tenderHeaderBean.setTenderId(tenderManagementForm.getTenderNo());
			tenderHeaderBean.setTenderType(tenderManagementForm.getTypeOfTender());
			tenderHeaderBean.setProcurementId(tenderManagementForm.getProcurementId());
			tenderHeaderBean.setPlanId(tenderManagementForm.getPlanId());
			tenderHeaderBean.setLocationId(tenderManagementForm.getLocationId());
			tenderHeaderBean.setEstimatedTenderAmount(tenderManagementForm.getEstimatedTenderAmount());
			tenderHeaderBean.setSchemeCode(tenderManagementForm.getSchemeCode());
			
			
			tenderHeaderBean.setDateAdvertisingShortlisting(MisUtility.convertStringToDate(tenderManagementForm.getDateAdvertisingShortlisting()));
			tenderHeaderBean.setRfpDraftToBankDate(MisUtility.convertStringToDate(tenderManagementForm.getRfpDraftToBankDate()));
			tenderHeaderBean.setBankNocForTorDate(MisUtility.convertStringToDate(tenderManagementForm.getBankNocForTorDate()));
			tenderHeaderBean.setBankNocForShortlistDate(MisUtility.convertStringToDate(tenderManagementForm.getBankNocForShortlistDate()));
			tenderHeaderBean.setBankNocForRfpDate(MisUtility.convertStringToDate(tenderManagementForm.getBankNocForRfpDate()));
			tenderHeaderBean.setRfpIssuedDate(MisUtility.convertStringToDate(tenderManagementForm.getRfpIssuedDate()));
			tenderHeaderBean.setTorShortlistFinalizedDate(MisUtility.convertStringToDate(tenderManagementForm.getTorShortlistFinalizedDate()));
			tenderHeaderBean.setBankNocTechnicalDate(MisUtility.convertStringToDate(tenderManagementForm.getBankNocTechnicalDate()));
			tenderHeaderBean.setBankNocCombinedDate(MisUtility.convertStringToDate(tenderManagementForm.getBankNocCombinedDate()));
			tenderHeaderBean.setEvaluationFinalTechnicalDate(MisUtility.convertStringToDate(tenderManagementForm.getEvaluationFinalTechnicalDate()));
			tenderHeaderBean.setEvaluationFinalCombinedDate(MisUtility.convertStringToDate(tenderManagementForm.getEvaluationFinalCombinedDate()));
			tenderHeaderBean.setNocBankDraftDate(MisUtility.convertStringToDate(tenderManagementForm.getNocBankDraftDate()));
			tenderHeaderBean.setEvaluationFinalDraftDate(MisUtility.convertStringToDate(tenderManagementForm.getEvaluationFinalDraftDate()));
			tenderHeaderBean.setBankNocFinalContractDate(MisUtility.convertStringToDate(tenderManagementForm.getBankNocFinalContractDate()));
			tenderHeaderBean.setEvaluationFinalContractDate(MisUtility.convertStringToDate(tenderManagementForm.getEvaluationFinalContractDate()));
			tenderHeaderBean.setProposalReciptDate(MisUtility.convertStringToDate(tenderManagementForm.getProposalReciptDate()));
			tenderHeaderBean.setProposalReciptDateTechnical(MisUtility.convertStringToDate(tenderManagementForm.getProposalReciptDateTechnical()));
			tenderHeaderBean.setProposalReciptDateFinancial(MisUtility.convertStringToDate(tenderManagementForm.getProposalReciptDateFinancial()));
			tenderHeaderBean.setServiceCompletionDate(MisUtility.convertStringToDate(tenderManagementForm.getServiceCompletionDate()));
			
			tenderHeaderBean.setBidSanctionDate(MisUtility.convertStringToDate(tenderManagementForm.getBidSanctionDate()));
			tenderHeaderBean.setBidSanctionNumber(tenderManagementForm.getBidSanctionNumber());
			tenderHeaderBean.setRebidApprovalDate(MisUtility.convertStringToDate(tenderManagementForm.getRebidApprovalDate()));
			tenderHeaderBean.setRebidApprovalNumber(tenderManagementForm.getRebidApprovalNumber());
		
			return tenderHeaderBean;
	}
	
	private List<TenderDetailBean> populateWorksTenderDetailBean(TenderManagementForm tenderManagementForm, 
			String tenderId, MISSessionBean misSessionBean) throws MISException{
		
		List<TenderDetailBean> tenderDetailBeans = new ArrayList<TenderDetailBean>();
		
		
			TenderDetailBean tenderDetailBean = new TenderDetailBean();		
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("W1");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getW1ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getW1StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getW1EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("W2");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getW2ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getW2StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getW2EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("W3");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getW3ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getW3StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getW3EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("W4");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getW4ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getW4StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getW4EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("W5");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getW5ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getW5StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getW5EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("W6");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getW6ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getW6StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getW6EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();	
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("W7");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getW7ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getW7StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getW7EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
		
		return tenderDetailBeans;
	}
	
	private List<TenderDetailBean> populateConsultancyTenderDetailBean(TenderManagementForm tenderManagementForm, 
			String tenderId, MISSessionBean misSessionBean) throws MISException{
		
		List<TenderDetailBean> tenderDetailBeans = new ArrayList<TenderDetailBean>();
		
		
			TenderDetailBean tenderDetailBean = new TenderDetailBean();		
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("C1");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getC1ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getC1StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getC1EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("C2");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getC2ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getC2StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getC2EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("C3");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getC3ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getC3StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getC3EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("C4");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getC4ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getC4StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getC4EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("C5");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getC5ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getC5StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getC5EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("C6");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getC6ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getC6StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getC6EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();	
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("C7");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getC7ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getC7StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getC7EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			

			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("C8");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getC8ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getC8StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getC8EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("C9");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getC9ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getC9StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getC9EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("C10");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getC10ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getC10StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getC10EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();	
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("C11");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getC11ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getC11StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getC11EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();	
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("C12");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getC12ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getC12StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getC12EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();	
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("C13");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getC13ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getC13StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getC13EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
		
		
		return tenderDetailBeans;
	}
	
	private List<TenderDetailBean> populateGoodsTenderDetailBean(TenderManagementForm tenderManagementForm, String tenderId, MISSessionBean misSessionBean) throws MISException{
		
		List<TenderDetailBean> tenderDetailBeans = new ArrayList<TenderDetailBean>();
		
		
			TenderDetailBean tenderDetailBean = new TenderDetailBean();		
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("G1");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getG1ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getG1StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getG1EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("G2");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getG2ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getG2StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getG2EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("G3");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getG3ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getG3StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getG3EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("G4");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getG4ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getG4StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getG4EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
		
		
		return tenderDetailBeans;
	}
	
	private List<TenderDetailBean> populateServicesTenderDetailBean(TenderManagementForm tenderManagementForm, String tenderId, MISSessionBean misSessionBean) throws MISException{
		
		List<TenderDetailBean> tenderDetailBeans = new ArrayList<TenderDetailBean>();
		
		
			TenderDetailBean tenderDetailBean = new TenderDetailBean();		
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("S1");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getS1ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getS1StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getS1EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("S2");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getS2ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getS2StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getS2EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("S3");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getS3ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getS3StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getS3EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("S4");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getS4ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getS4StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getS4EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("S5");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getS5ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getS5StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getS5EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("S6");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getS6ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getS6StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getS6EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
			
			
			tenderDetailBean = new TenderDetailBean();
			tenderDetailBean.setTenderId(tenderId);
			tenderDetailBean.setTenderActivity("S7");
			tenderDetailBean.setActualDate(MisUtility.convertStringToDate(tenderManagementForm.getS7ActualDateofCompletion()));
			tenderDetailBean.setTenderActivityStatus(tenderManagementForm.getS7StatusOfActivity());
			tenderDetailBean.setEstimateDate(MisUtility.convertStringToDate(tenderManagementForm.getS7EstimateDateofCompletion()));
			tenderDetailBeans.add(tenderDetailBean);
		
		
		return tenderDetailBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean uploadTender(TenderUploadBean tenderUploadBean,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		
		try {
			TenderUploadBean tenderUploadFindBean = new TenderUploadBean();
			tenderUploadFindBean.setTenderId(tenderUploadBean.getTenderId());
//			tenderUploadFindBean.setLocationId(tenderUploadBean.getLocationId());
			List<TenderUploadBean> tenderUploadFindBean2 = tenderDao.getTenderUploaded(tenderUploadFindBean);
			if(!MisUtility.ifEmpty(tenderUploadFindBean2)){
				throw new MISException(MISExceptionCodes.MIS001,"Tender already uploaded for the given tender id");
			}
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			tenderUploadBean.setMisAuditBean(misAuditBean);
			status = tenderDao.uploadTender(tenderUploadBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean uploadEOI(EOIUploadBean eoiUploadBean,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		
		try {
			EOIUploadBean eoiUploadFindBean = new EOIUploadBean();
			eoiUploadFindBean.setEoiUploadId(eoiUploadBean.getEoiUploadId());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			eoiUploadBean.setMisAuditBean(misAuditBean);
			status = tenderDao.uploadEOI(eoiUploadBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return status;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean uploadAdv(AdvUploadBean advUploadBean,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		
		try {
			AdvUploadBean advUploadFindBean = new AdvUploadBean();
			advUploadFindBean.setAdvUploadId(advUploadBean.getAdvUploadId());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			advUploadBean.setMisAuditBean(misAuditBean);
			status = tenderDao.uploadAdv(advUploadBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return status;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateAdv(AdvUploadBean advUploadBean,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		
		try {
		/*	AdvUploadBean advUploadFindBean = new AdvUploadBean();
			advUploadFindBean.setAdvUploadId(advUploadBean.getAdvUploadId());*/
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			advUploadBean.setMisAuditBean(misAuditBean);
			status = tenderDao.updateAdv(advUploadBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return status;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<TenderUploadBean> getTenderUploaded(
			TenderUploadBean tenderUploadBean) throws DataAccessException {
		
		List<TenderUploadBean> testSchemeViewBeans = tenderDao.getTenderUploaded(tenderUploadBean);
		return testSchemeViewBeans;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<EOIUploadBean> getEOIUploaded(
			EOIUploadBean eoiUploadBean) throws DataAccessException {
		
		List<EOIUploadBean> testSchemeViewBeans = tenderDao.getEOIUploaded(eoiUploadBean);
		return testSchemeViewBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<AdvUploadBean> getAdvUploaded(
			AdvUploadBean advUploadBean) throws DataAccessException {
		
		List<AdvUploadBean> testSchemeViewBeans = tenderDao.getAdvUploaded(advUploadBean);
		return testSchemeViewBeans;
	}
	
	@Override
	public List<TenderUploadBean> findTenderUploaded(TenderUploadForm tenderUploadForm, List<String> statusList)throws MISException {
			List<TenderUploadBean> tenderUploadBeans = null;		
		try {
			TenderUploadBean tenderUploadBean= new TenderUploadBean();
			if(MisUtility.ifEmpty(tenderUploadForm.getTenderNo())){
			tenderUploadBean.setLocationId(tenderUploadForm.getLocationId());
			tenderUploadBean.setTenderId(tenderUploadForm.getTenderNo().trim());
			tenderUploadBeans = tenderDao.findTenderUploaded(tenderUploadBean, statusList);
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		}
		
		return tenderUploadBeans;
	}
	@Override
	public List<EOIUploadBean> findEOIUploaded(EOIUploadForm eoiUploadForm, List<String> statusList)throws MISException {
			List<EOIUploadBean> eoiUploadBeans = null;		
		try {
			EOIUploadBean eoiUploadBean= new EOIUploadBean();
			if(MisUtility.ifEmpty(eoiUploadForm.getEoiUploadId())){
			eoiUploadBean.setLocationId(eoiUploadForm.getLocationId());
			eoiUploadBean.setEoiUploadId(eoiUploadForm.getEoiUploadId());
			eoiUploadBeans = tenderDao.findEOIUploaded(eoiUploadBean, statusList);
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		}		
		return eoiUploadBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<QuoatationUploadBean> getQuoatationUploaded(
			QuoatationUploadBean quoatationUploadBean)
			throws DataAccessException {

		List<QuoatationUploadBean> testSchemeViewBeans = tenderDao.getQuoatationUploaded(quoatationUploadBean);
		return testSchemeViewBeans;
	}

	@Override
	public List<QuoatationUploadBean> findQuoatationUploaded(
			QuoatationUploadForm quoatationUploadForm, List<String> statusList)
			throws MISException {
		List<QuoatationUploadBean> quoatationUploadBeans = null;		
		try {
			QuoatationUploadBean quoatationUploadBean= new QuoatationUploadBean();
			if(MisUtility.ifEmpty(quoatationUploadForm.getQuoatationUploadId())){
			quoatationUploadBean.setLocationId(quoatationUploadForm.getLocationId());
			quoatationUploadBean.setQuoatationUploadId(quoatationUploadForm.getQuoatationUploadId());
			quoatationUploadBeans = tenderDao.findQuoatationUploaded(quoatationUploadBean, statusList);
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		}		
		return quoatationUploadBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean uploadQuoatation(QuoatationUploadBean quoatationUploadBean,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			QuoatationUploadBean quoatationUploadFindBean = new QuoatationUploadBean();
			quoatationUploadFindBean.setQuoatationUploadId(quoatationUploadBean.getQuoatationUploadId());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			quoatationUploadBean.setMisAuditBean(misAuditBean);
			status = tenderDao.uploadQuoatation(quoatationUploadBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return status;	
	}
	@Override
	public List<AdvUploadBean> findAdvUploaded(
			AdvUploadForm advUploadForm, List<String> statusList)
			throws MISException {
		List<AdvUploadBean> advUploadBeans = null;		
		try {
			AdvUploadBean advUploadBean= new AdvUploadBean();
			if(MisUtility.ifEmpty(advUploadForm.getAdvUploadId())){
			advUploadBean.setLocationId(advUploadForm.getLocationId());
			advUploadBean.setAdvUploadId(advUploadForm.getAdvUploadId());
			advUploadBeans = tenderDao.findAdvUploaded(advUploadBean, statusList);
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		}		
		return advUploadBeans;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean uploadCorrigendum(CorrigendumUploadBean corrigendumUploadBean,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;		
		try {
			CorrigendumUploadBean corrigendumUploadFindBean = new CorrigendumUploadBean();
			corrigendumUploadFindBean.setCorrigendumUploadId(corrigendumUploadBean.getCorrigendumUploadId());
			List<CorrigendumUploadBean> corrigendumUploadFindBean2 = tenderDao.getCorrigendumUploaded(corrigendumUploadFindBean);
			if(!MisUtility.ifEmpty(corrigendumUploadFindBean2) && corrigendumUploadBean.getCorrigendumUploadId()!=0){
				throw new MISException(MISExceptionCodes.MIS001,"Tender already uploaded for the given tender id");
			}
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			corrigendumUploadBean.setMisAuditBean(misAuditBean);
			status = tenderDao.uploadCorrigendum(corrigendumUploadBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return status;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<CorrigendumUploadBean> getCorrigendumUploaded(
			CorrigendumUploadBean corrigendumUploadBean) throws DataAccessException {
		
		List<CorrigendumUploadBean> testSchemeViewBeans = tenderDao.getCorrigendumUploaded(corrigendumUploadBean);
		return testSchemeViewBeans;
	}
	@Override
	public List<CorrigendumUploadBean> findCorrigendumUploaded(CorrigendumUploadForm corrigendumUploadForm, List<String> statusList)throws MISException {
			List<CorrigendumUploadBean> corrigendumUploadBeans = null;		
		try {
			CorrigendumUploadBean corrigendumUploadBean= new CorrigendumUploadBean();
			if(MisUtility.ifEmpty(corrigendumUploadForm.getDocId())){
			corrigendumUploadBean.setLocationId(corrigendumUploadForm.getLocationId());
			corrigendumUploadBean.setDocId(corrigendumUploadForm.getDocId().trim());
			corrigendumUploadBeans = tenderDao.findCorrigendumUploaded(corrigendumUploadBean, statusList);
			}
		} catch (DataAccessException e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		} 
		
		return corrigendumUploadBeans;
	}

	/*@Override
	public Set<TenderHeaderBean> getDistinctTenderCodesResponsive(
			String parameter, List<String> statusList) {
		Set<BidderDetailBean> detailBeans = null;
		
		
		Set<TenderHeaderBean> tenderHeaderBeans = tenderDao.getDistinctTenderCodesResponsive(parameter, statusList);
		BidderHeaderBean headerBean = new BidderHeaderBean();
		headerBean.setLocationId(parameter);
		Set<BidderHeaderBean> beans = (Set<BidderHeaderBean>) bidderHeaderDao.findBidderHeader(headerBean, statusList);
		for(BidderHeaderBean bean:beans){
			Set<BidderDetailBean> bidderDetailBeans = bean.getBidderDetailBeans();
			for(BidderDetailBean bean2:bidderDetailBeans){
				if(!bean2.getBidderName().equals("NOBIDDER")||!bean2.getNotResponsive().equals("Select")||!bean2.getNotResponsive().equals("")){
					detailBeans.add(bean2);
				}
			}
		}
		for(BidderDetailBean detailBean:detailBeans){
			
		}
		return tenderHeaderBeans;
	}*/
}
