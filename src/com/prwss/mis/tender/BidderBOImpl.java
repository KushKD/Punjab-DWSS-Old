package com.prwss.mis.tender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
import com.prwss.mis.tender.biddersdetail.dao.BidderDetailBean;
import com.prwss.mis.tender.biddersdetail.dao.BidderDetailDao;
import com.prwss.mis.tender.biddersdetail.dao.BidderHeaderBean;
import com.prwss.mis.tender.biddersdetail.dao.BidderHeaderDao;
import com.prwss.mis.tender.dao.TenderHeaderBean;
import com.prwss.mis.tender.struts.BidderDetailGridBean;
import com.prwss.mis.tender.struts.BiddersDetailForm;

public class BidderBOImpl implements BidderBO {
	
	private Logger log = Logger.getLogger(BidderBOImpl.class);
	
	private BidderHeaderDao bidderHeaderDao;
	
	private BidderDetailDao bidderDetailDao;
	
	public void setBidderHeaderDao(BidderHeaderDao bidderHeaderDao) {
		this.bidderHeaderDao = bidderHeaderDao;
	}

	public void setBidderDetailDao(BidderDetailDao bidderDetailDao) {
		this.bidderDetailDao = bidderDetailDao;
	}

	@Override
	public List<BidderHeaderBean> findBidder(BiddersDetailForm bidderDetailForm, List<String> statusList) throws MISException {
		List<BidderHeaderBean> bidderHeaderBeans = null;
		
		try {
			BidderHeaderBean bidderHeaderBean = populaBidderHeaderBean(bidderDetailForm);
			bidderHeaderBeans = bidderHeaderDao.findBidderHeader(bidderHeaderBean, statusList);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e.getMostSpecificCause());
		}
		
		return bidderHeaderBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveBidder(BiddersDetailForm bidderDetailForm, MISSessionBean misSessionBean) throws MISException {
		long bidderInfoId = 0;
		try {
			BidderHeaderBean bidderHeaderBean = populaBidderHeaderBean(bidderDetailForm);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			
			List<BidderHeaderBean> bidderHeaderBean2= bidderHeaderDao.findBidderHeader(bidderHeaderBean, statusList);
			if(!MisUtility.ifEmpty(bidderHeaderBean2)){
				throw new MISException(MISExceptionCodes.MIS001, "Tender No "+bidderHeaderBean.getTenderId());			
			}
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			bidderHeaderBean.setMisAuditBean(misAuditBean);
			
			bidderInfoId = bidderHeaderDao.saveBidderHeader(bidderHeaderBean);
				
			if(MisUtility.ifEmpty(bidderInfoId)){
				
				List<BidderDetailBean> bidderDetailBeans = populateBidderDetailBeans(bidderDetailForm, bidderInfoId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				
				boolean status = bidderDetailDao.saveOrUpdateBidderDetailBeans(bidderDetailBeans);
				if(!status){
					throw new MISException(MISExceptionCodes.MIS003, "Bidder Details are not saved for "+bidderDetailForm.getTenderId());
				}
				
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e.getMostSpecificCause());
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}	
		return bidderInfoId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateBidder(BiddersDetailForm bidderDetailForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		boolean childStatus =false;
		boolean flag=false;
		int count1;
		int count2;
		try {
			BidderHeaderBean bidderHeaderBean = populaBidderHeaderBean(bidderDetailForm);
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			bidderHeaderBean.setMisAuditBean(misAuditBean);
			
			status = bidderHeaderDao.updateBidderHeader(bidderHeaderBean);
				
			if(status){
				count1=0;
				count2=0;
				long bidderInfoId = bidderDetailForm.getBidInfoId();
				List<BidderDetailBean> bidderDetailBeans1 = populateBidderDetailBeans1(bidderDetailForm, bidderInfoId,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				log.debug("\nBidder DetailBeans\t"+bidderDetailBeans1);
				
				for(BidderDetailBean bidderDetailBean:bidderDetailBeans1 ){
					log.debug("bidderDetailBean.getMisAuditBean().getStatus()===="+bidderDetailBean.getMisAuditBean().getStatus());
					log.debug("bank name===="+bidderDetailBean.getBankName());
					log.debug("bank name===="+bidderDetailBean.getBidInfoId());
					if(bidderDetailBean.getMisAuditBean().getStatus()=="U"){
							count1++;
						}
					if(bidderDetailBean.getMisAuditBean().getStatus()=="D"){
						count2++;
					}	
				}
				if(count1==count2){
					throw new MISException(MISExceptionCodes.MIS003, "At least one bidder's information required in the grid");
				}
				if(!MisUtility.ifEmpty(bidderDetailBeans1)){
					if(!bidderDetailDao.saveOrUpdateBidderDetailBeans1(bidderDetailBeans1)){
						throw new MISException(MISExceptionCodes.MIS003, "Bidder Details are not updated for "+bidderDetailForm.getTenderId());
						}
				}
				/*List<BidderDetailBean> bidderDetailBeans2 = populateBidderDetailBeans3(bidderDetailForm, bidderInfoId,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				log.debug("\nBidder DetailBeans\t"+bidderDetailBeans2);
				
				for(BidderDetailBean bidderDetailBean:bidderDetailBeans2 ){
					
					if(bidderDetailBean.getMisAuditBean().getStatus()=="D"){
						log.debug("delete status of bidder---"+bidderDetailBean.getMisAuditBean().getStatus());
						count2++;
					}
					log.debug("count2===="+count2);
					}
			
				if(!MisUtility.ifEmpty(bidderDetailBeans2)){
					if(!bidderDetailDao.saveOrUpdateBidderDetailBeans3(bidderDetailBeans2)){
						throw new MISException(MISExceptionCodes.MIS003, "Bidder Details are not updated for "+bidderDetailForm.getTenderId());
						}
				}
				
				
				
				
				if(count1==count2){
					throw new MISException(MISExceptionCodes.MIS003, "At least one bidder's information required in the grid");
				}
				
				if(!MisUtility.ifEmpty(bidderDetailBeans1)){
					if(!bidderDetailDao.saveOrUpdateBidderDetailBeans1(bidderDetailBeans1)){
						throw new MISException(MISExceptionCodes.MIS003, "Bidder Details are not updated for "+bidderDetailForm.getTenderId());
						}
				}
				List<BidderDetailBean> bidderDetailBeans = populateBidderDetailBeans2(bidderDetailForm, bidderInfoId,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				log.debug("\nBidder DetailBeans\t"+bidderDetailBeans);
				
				
				if(!MisUtility.ifEmpty(bidderDetailBeans)){
					if(!bidderDetailDao.saveOrUpdateBidderDetailBeans2(bidderDetailBeans)){
						System.out.println("--------------- inside inside --------------- inside inside -----------------");
						throw new MISException(MISExceptionCodes.MIS003, "Bidder Details are not updated for "+bidderDetailForm.getTenderId());
						}
				}
			*/
				//childStatus = bidderDetailDao.saveOrUpdateBidderDetailBeans(bidderDetailBeans);
				/*if(!childStatus){
					throw new MISException(MISExceptionCodes.MIS003, "Bidder Details are not updated for "+bidderDetailForm.getTenderId());
				}*/
				
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e.getMostSpecificCause());
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new MISException(e);
		}
		return true;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteBidder(BiddersDetailForm bidderDetailForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		boolean childStatus =false;
		try {
			BidderHeaderBean bidderHeaderBean = populaBidderHeaderBean(bidderDetailForm);
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			bidderHeaderBean.setMisAuditBean(misAuditBean);
			
			status = bidderHeaderDao.updateBidderHeader(bidderHeaderBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			BidderDetailBean bidderDetailBean = new BidderDetailBean();
				
			if(status){
			/*	long bidderInfoId = 0;
				List<BidderDetailBean> bidderDetailBeans = populateBidderDetailBeans(bidderDetailForm, bidderInfoId, misSessionBean, MISConstants.MASTER_STATUS_DELETED);
				log.debug("\nBidder DetailBeans\t"+bidderDetailBeans.toString());
				*/
				bidderDetailBean.setBidInfoId(bidderDetailForm.getBidInfoId());
				List<BidderDetailBean> bidderDetailBeans = bidderDetailDao.getBidderDetailBeans(bidderDetailBean, statusList);
				for (BidderDetailBean bidderDetailBean2 : bidderDetailBeans) {
					MISAuditBean misAuditBean2 = new MISAuditBean();
					misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
					misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
					bidderDetailBean2.setMisAuditBean(misAuditBean2);
					}
				childStatus = bidderDetailDao.saveOrUpdateBidderDetailBeans(bidderDetailBeans);
				if(!childStatus){
					throw new MISException(MISExceptionCodes.MIS003, "Bidder Details are not updated for "+bidderDetailForm.getTenderId());
				}
				
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e.getMostSpecificCause());
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} 
		return status && childStatus;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postBidder(BiddersDetailForm bidderDetailForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		boolean childStatus =false;
		try {
			BidderHeaderBean bidderHeaderBean = populaBidderHeaderBean(bidderDetailForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			bidderHeaderBean.setMisAuditBean(misAuditBean);
			status = bidderHeaderDao.updateBidderHeader(bidderHeaderBean);
			if(status){
				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
				BidderDetailBean bidderDetailBean = new BidderDetailBean();
				bidderDetailBean.setBidInfoId(bidderDetailForm.getBidInfoId());
				List<BidderDetailBean> bidderDetailBeans = bidderDetailDao.getBidderDetailBeans(bidderDetailBean, statusList);
				for (BidderDetailBean bidderDetailBean2 : bidderDetailBeans) {
					bidderDetailBean2.setMisAuditBean(misAuditBean);
				}
				
				childStatus = bidderDetailDao.saveOrUpdateBidderDetailBeans(bidderDetailBeans);
				if(!childStatus){
					throw new MISException(MISExceptionCodes.MIS003, "Bidder Details are not updated for "+bidderDetailForm.getTenderId());
				}
				
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e.getMostSpecificCause());
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} 
		return status && childStatus;
	}
	
	private BidderHeaderBean populaBidderHeaderBean(BiddersDetailForm biddersDetailForm){
		BidderHeaderBean bidderHeaderBean = new BidderHeaderBean();
		bidderHeaderBean.setBiddingPhase(biddersDetailForm.getBiddingPhase());
		bidderHeaderBean.setBidInfoId(biddersDetailForm.getBidInfoId());
		bidderHeaderBean.setBidOpeningDate(MisUtility.convertStringToDate(biddersDetailForm.getBidOpeningDate()));
		bidderHeaderBean.setLocationId(biddersDetailForm.getLocationId());
		bidderHeaderBean.setReferenceTenderId(biddersDetailForm.getReferenceTenderId());
		bidderHeaderBean.setTenderId(biddersDetailForm.getTenderId());
		return bidderHeaderBean;
	}
	

	@SuppressWarnings("unchecked")
	private List<BidderDetailBean> populateBidderDetailBeans1(BiddersDetailForm biddersDetailForm, long bidderInfoId, MISSessionBean misSessionBean, String status){
		List<BidderDetailBean> bidderDetailBeans = new ArrayList<BidderDetailBean>();
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		BidderDetailBean bidderDetailBean = null;
		Collection<BidderDetailGridBean> modifiedBidderDetailGridBeans =  biddersDetailForm.getBidderDetailDatagrid().getModifiedData();
		
		for (BidderDetailGridBean bidderDetailGridBean : modifiedBidderDetailGridBeans) {
			bidderDetailBean = new BidderDetailBean();
				
			bidderDetailBean.setSeqBidId(bidderDetailGridBean.getSeqBidId());
			bidderDetailBean.setBidInfoId(bidderInfoId);
			bidderDetailBean.setBidderName(bidderDetailGridBean.getBidderName());
			bidderDetailBean.setBankName(bidderDetailGridBean.getBankName());
			bidderDetailBean.setBidSaleDate(MisUtility.convertStringToDate(bidderDetailGridBean.getBidSaleDate()));
			bidderDetailBean.setBidSubmitted(bidderDetailGridBean.getBidSubmitted());
			bidderDetailBean.setBidAmount(bidderDetailGridBean.getBidAmount());
			bidderDetailBean.setContactNumber(bidderDetailGridBean.getContactNumber());
			bidderDetailBean.setEmdAmount(bidderDetailGridBean.getEmdAmount());
			bidderDetailBean.setEmdInstrumentType(bidderDetailGridBean.getEmdInstrumentType());
			bidderDetailBean.setEmdValidUpto(MisUtility.convertStringToDate(bidderDetailGridBean.getEmdValidUpto()));
			bidderDetailBean.setNotResponsive("Others".equalsIgnoreCase(bidderDetailGridBean.getNotResponsive())?bidderDetailGridBean.getOther():bidderDetailGridBean.getNotResponsive());
			bidderDetailBean.setRemarks(bidderDetailGridBean.getRemarks());
			bidderDetailBean.setMisAuditBean(misAuditBean);
			bidderDetailBeans.add(bidderDetailBean);
		
		}
	     Collection<BidderDetailGridBean> deletedBidderDetailGridBeans =  biddersDetailForm.getBidderDetailDatagrid().getDeletedData();
		
		for (BidderDetailGridBean bidderDetailGridBean : deletedBidderDetailGridBeans) {
			bidderDetailBean = new BidderDetailBean();
			bidderDetailBean.setSeqBidId(bidderDetailGridBean.getSeqBidId());
			bidderDetailBean.setBidInfoId(bidderDetailGridBean.getBidInfoId());
			bidderDetailBean.setBidderName(bidderDetailGridBean.getBidderName());
			bidderDetailBean.setBankName(bidderDetailGridBean.getBankName());
			bidderDetailBean.setBidSaleDate(MisUtility.convertStringToDate(bidderDetailGridBean.getBidSaleDate()));
			bidderDetailBean.setBidSubmitted(bidderDetailGridBean.getBidSubmitted());
			bidderDetailBean.setBidAmount(bidderDetailGridBean.getBidAmount());
			bidderDetailBean.setContactNumber(bidderDetailGridBean.getContactNumber());
			bidderDetailBean.setEmdAmount(bidderDetailGridBean.getEmdAmount());
			bidderDetailBean.setEmdInstrumentType(bidderDetailGridBean.getEmdInstrumentType());
			bidderDetailBean.setEmdValidUpto(MisUtility.convertStringToDate(bidderDetailGridBean.getEmdValidUpto()));
			bidderDetailBean.setNotResponsive("Others".equalsIgnoreCase(bidderDetailGridBean.getNotResponsive())?bidderDetailGridBean.getOther():bidderDetailGridBean.getNotResponsive());
			bidderDetailBean.setRemarks(bidderDetailGridBean.getRemarks());
			MISAuditBean misAuditBean2 = new MISAuditBean();
			misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
			bidderDetailBean.setMisAuditBean(misAuditBean2);
			bidderDetailBeans.add(bidderDetailBean);
		}
		Collection<BidderDetailGridBean> addedBidderDetailGridBeans =  biddersDetailForm.getBidderDetailDatagrid().getAddedData();
		for (BidderDetailGridBean bidderDetailGridBean : addedBidderDetailGridBeans) {
			bidderDetailBean = new BidderDetailBean();
			bidderDetailBean.setSeqBidId(bidderDetailGridBean.getSeqBidId());
			bidderDetailBean.setBidInfoId(bidderInfoId);
			bidderDetailBean.setBidderName(bidderDetailGridBean.getBidderName());
			bidderDetailBean.setBankName(bidderDetailGridBean.getBankName());
			bidderDetailBean.setBidSaleDate(MisUtility.convertStringToDate(bidderDetailGridBean.getBidSaleDate()));
			bidderDetailBean.setBidSubmitted(bidderDetailGridBean.getBidSubmitted());
			bidderDetailBean.setBidAmount(bidderDetailGridBean.getBidAmount());
			bidderDetailBean.setContactNumber(bidderDetailGridBean.getContactNumber());
			bidderDetailBean.setEmdAmount(bidderDetailGridBean.getEmdAmount());
			bidderDetailBean.setEmdInstrumentType(bidderDetailGridBean.getEmdInstrumentType());
			bidderDetailBean.setEmdValidUpto(MisUtility.convertStringToDate(bidderDetailGridBean.getEmdValidUpto()));
			bidderDetailBean.setNotResponsive("Others".equalsIgnoreCase(bidderDetailGridBean.getNotResponsive())?bidderDetailGridBean.getOther():bidderDetailGridBean.getNotResponsive());
			bidderDetailBean.setRemarks(bidderDetailGridBean.getRemarks());
			bidderDetailBean.setMisAuditBean(misAuditBean);
			bidderDetailBeans.add(bidderDetailBean);
		}
		return bidderDetailBeans;
		}
		
	
	@SuppressWarnings("unchecked")
	private List<BidderDetailBean> populateBidderDetailBeans2(BiddersDetailForm biddersDetailForm, long bidderInfoId, MISSessionBean misSessionBean, String status){
		List<BidderDetailBean> bidderDetailBeans = new ArrayList<BidderDetailBean>();
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		BidderDetailBean bidderDetailBean = null;
		 
		Collection<BidderDetailGridBean> addedBidderDetailGridBeans =  biddersDetailForm.getBidderDetailDatagrid().getAddedData();
		for (BidderDetailGridBean bidderDetailGridBean : addedBidderDetailGridBeans) {
			bidderDetailBean = new BidderDetailBean();
			bidderDetailBean.setSeqBidId(bidderDetailGridBean.getSeqBidId());
			bidderDetailBean.setBidInfoId(bidderInfoId);
			bidderDetailBean.setBidderName(bidderDetailGridBean.getBidderName());
			bidderDetailBean.setBankName(bidderDetailGridBean.getBankName());
			bidderDetailBean.setBidSaleDate(MisUtility.convertStringToDate(bidderDetailGridBean.getBidSaleDate()));
			bidderDetailBean.setBidSubmitted(bidderDetailGridBean.getBidSubmitted());
			bidderDetailBean.setBidAmount(bidderDetailGridBean.getBidAmount());
			bidderDetailBean.setContactNumber(bidderDetailGridBean.getContactNumber());
			bidderDetailBean.setEmdAmount(bidderDetailGridBean.getEmdAmount());
			bidderDetailBean.setEmdInstrumentType(bidderDetailGridBean.getEmdInstrumentType());
			bidderDetailBean.setEmdValidUpto(MisUtility.convertStringToDate(bidderDetailGridBean.getEmdValidUpto()));
			bidderDetailBean.setNotResponsive("Others".equalsIgnoreCase(bidderDetailGridBean.getNotResponsive())?bidderDetailGridBean.getOther():bidderDetailGridBean.getNotResponsive());
			bidderDetailBean.setRemarks(bidderDetailGridBean.getRemarks());
			bidderDetailBean.setMisAuditBean(misAuditBean);
			bidderDetailBeans.add(bidderDetailBean);
		}
	
		 
		return bidderDetailBeans;
	}
	@SuppressWarnings("unchecked")
	private List<BidderDetailBean> populateBidderDetailBeans3(BiddersDetailForm biddersDetailForm, long bidderInfoId, MISSessionBean misSessionBean, String status){
		List<BidderDetailBean> bidderDetailBeans = new ArrayList<BidderDetailBean>();
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		BidderDetailBean bidderDetailBean = null;
		Collection<BidderDetailGridBean> deletedBidderDetailGridBeans =  biddersDetailForm.getBidderDetailDatagrid().getDeletedData();
		
		for (BidderDetailGridBean bidderDetailGridBean : deletedBidderDetailGridBeans) {
			bidderDetailBean = new BidderDetailBean();
			bidderDetailBean.setSeqBidId(bidderDetailGridBean.getSeqBidId());
			bidderDetailBean.setBidInfoId(bidderDetailGridBean.getBidInfoId());
			bidderDetailBean.setBidderName(bidderDetailGridBean.getBidderName());
			bidderDetailBean.setBankName(bidderDetailGridBean.getBankName());
			bidderDetailBean.setBidSaleDate(MisUtility.convertStringToDate(bidderDetailGridBean.getBidSaleDate()));
			bidderDetailBean.setBidSubmitted(bidderDetailGridBean.getBidSubmitted());
			bidderDetailBean.setBidAmount(bidderDetailGridBean.getBidAmount());
			bidderDetailBean.setContactNumber(bidderDetailGridBean.getContactNumber());
			bidderDetailBean.setEmdAmount(bidderDetailGridBean.getEmdAmount());
			bidderDetailBean.setEmdInstrumentType(bidderDetailGridBean.getEmdInstrumentType());
			bidderDetailBean.setEmdValidUpto(MisUtility.convertStringToDate(bidderDetailGridBean.getEmdValidUpto()));
			bidderDetailBean.setNotResponsive("Others".equalsIgnoreCase(bidderDetailGridBean.getNotResponsive())?bidderDetailGridBean.getOther():bidderDetailGridBean.getNotResponsive());
			bidderDetailBean.setRemarks(bidderDetailGridBean.getRemarks());
			MISAuditBean misAuditBean2 = new MISAuditBean();
			misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
			bidderDetailBean.setMisAuditBean(misAuditBean2);
			bidderDetailBeans.add(bidderDetailBean);
		}
		return bidderDetailBeans;
		}
	
	@SuppressWarnings("unchecked")
	private List<BidderDetailBean> populateBidderDetailBeans(BiddersDetailForm biddersDetailForm, long bidderInfoId, MISSessionBean misSessionBean, String status){
		List<BidderDetailBean> bidderDetailBeans = new ArrayList<BidderDetailBean>();
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);
		BidderDetailBean bidderDetailBean = null;
		 
		Collection<BidderDetailGridBean> addedBidderDetailGridBeans =  biddersDetailForm.getBidderDetailDatagrid().getAddedData();
		for (BidderDetailGridBean bidderDetailGridBean : addedBidderDetailGridBeans) {
			bidderDetailBean = new BidderDetailBean();
			bidderDetailBean.setSeqBidId(bidderDetailGridBean.getSeqBidId());
			bidderDetailBean.setBidInfoId(bidderInfoId);
			bidderDetailBean.setBidderName(bidderDetailGridBean.getBidderName());
			bidderDetailBean.setBankName(bidderDetailGridBean.getBankName());
			bidderDetailBean.setBidSaleDate(MisUtility.convertStringToDate(bidderDetailGridBean.getBidSaleDate()));
			bidderDetailBean.setBidSubmitted(bidderDetailGridBean.getBidSubmitted());
			bidderDetailBean.setBidAmount(bidderDetailGridBean.getBidAmount());
			bidderDetailBean.setContactNumber(bidderDetailGridBean.getContactNumber());
			bidderDetailBean.setEmdAmount(bidderDetailGridBean.getEmdAmount());
			bidderDetailBean.setEmdInstrumentType(bidderDetailGridBean.getEmdInstrumentType());
			bidderDetailBean.setEmdValidUpto(MisUtility.convertStringToDate(bidderDetailGridBean.getEmdValidUpto()));
			bidderDetailBean.setNotResponsive("Others".equalsIgnoreCase(bidderDetailGridBean.getNotResponsive())?bidderDetailGridBean.getOther():bidderDetailGridBean.getNotResponsive());
			bidderDetailBean.setRemarks(bidderDetailGridBean.getRemarks());
			bidderDetailBean.setMisAuditBean(misAuditBean);
			bidderDetailBeans.add(bidderDetailBean);
		}
		Collection<BidderDetailGridBean> modifiedBidderDetailGridBeans =  biddersDetailForm.getBidderDetailDatagrid().getModifiedData();
		Collection<BidderDetailGridBean> deletedBidderDetailGridBeans =  biddersDetailForm.getBidderDetailDatagrid().getDeletedData();
		
		//****************************************************************************************************************
	// Code for removing the objects from modified data collection which are present in Deleted Data as Well.
		if(!MisUtility.ifEmpty(deletedBidderDetailGridBeans)){
		Iterator<BidderDetailGridBean> deletedIteratoreBidderDetailGridBeans = deletedBidderDetailGridBeans.iterator();
		Iterator<BidderDetailGridBean> modifiedIteratoreBidderDetailGridBeans = modifiedBidderDetailGridBeans.iterator();
		BidderDetailGridBean bean = new BidderDetailGridBean();
		BidderDetailGridBean bean2 = new BidderDetailGridBean();
		int i=0;
		int j=0;
		while(modifiedIteratoreBidderDetailGridBeans.hasNext()){
			bean = modifiedIteratoreBidderDetailGridBeans.next();
			while(deletedIteratoreBidderDetailGridBeans.hasNext()){
				
				bean2 = deletedIteratoreBidderDetailGridBeans.next();
				
				if(bean.getSeqBidId() == bean2.getSeqBidId()){
					modifiedIteratoreBidderDetailGridBeans.remove();
				}
				
				}
			}
	
		}
		//*****************************************************************************************************************
		
		
		for (BidderDetailGridBean bidderDetailGridBean : modifiedBidderDetailGridBeans) {
			bidderDetailBean = new BidderDetailBean();
				
			bidderDetailBean.setSeqBidId(bidderDetailGridBean.getSeqBidId());
			bidderDetailBean.setBidInfoId(bidderDetailGridBean.getBidInfoId());
			bidderDetailBean.setBidderName(bidderDetailGridBean.getBidderName());
			bidderDetailBean.setBankName(bidderDetailGridBean.getBankName());
			bidderDetailBean.setBidSaleDate(MisUtility.convertStringToDate(bidderDetailGridBean.getBidSaleDate()));
			bidderDetailBean.setBidSubmitted(bidderDetailGridBean.getBidSubmitted());
			bidderDetailBean.setBidAmount(bidderDetailGridBean.getBidAmount());
			bidderDetailBean.setContactNumber(bidderDetailGridBean.getContactNumber());
			bidderDetailBean.setEmdAmount(bidderDetailGridBean.getEmdAmount());
			bidderDetailBean.setEmdInstrumentType(bidderDetailGridBean.getEmdInstrumentType());
			bidderDetailBean.setEmdValidUpto(MisUtility.convertStringToDate(bidderDetailGridBean.getEmdValidUpto()));
			bidderDetailBean.setNotResponsive("Others".equalsIgnoreCase(bidderDetailGridBean.getNotResponsive())?bidderDetailGridBean.getOther():bidderDetailGridBean.getNotResponsive());
			bidderDetailBean.setRemarks(bidderDetailGridBean.getRemarks());
			bidderDetailBean.setMisAuditBean(misAuditBean);
			bidderDetailBeans.add(bidderDetailBean);
		
		}
		
		for (BidderDetailGridBean bidderDetailGridBean : deletedBidderDetailGridBeans) {
			bidderDetailBean = new BidderDetailBean();
			bidderDetailBean.setSeqBidId(bidderDetailGridBean.getSeqBidId());
			bidderDetailBean.setBidInfoId(bidderDetailGridBean.getBidInfoId());
			bidderDetailBean.setBidderName(bidderDetailGridBean.getBidderName());
			bidderDetailBean.setBankName(bidderDetailGridBean.getBankName());
			bidderDetailBean.setBidSaleDate(MisUtility.convertStringToDate(bidderDetailGridBean.getBidSaleDate()));
			bidderDetailBean.setBidSubmitted(bidderDetailGridBean.getBidSubmitted());
			bidderDetailBean.setBidAmount(bidderDetailGridBean.getBidAmount());
			bidderDetailBean.setContactNumber(bidderDetailGridBean.getContactNumber());
			bidderDetailBean.setEmdAmount(bidderDetailGridBean.getEmdAmount());
			bidderDetailBean.setEmdInstrumentType(bidderDetailGridBean.getEmdInstrumentType());
			bidderDetailBean.setEmdValidUpto(MisUtility.convertStringToDate(bidderDetailGridBean.getEmdValidUpto()));
			bidderDetailBean.setNotResponsive("Others".equalsIgnoreCase(bidderDetailGridBean.getNotResponsive())?bidderDetailGridBean.getOther():bidderDetailGridBean.getNotResponsive());
			bidderDetailBean.setRemarks(bidderDetailGridBean.getRemarks());
			MISAuditBean misAuditBean2 = new MISAuditBean();
			misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
			bidderDetailBean.setMisAuditBean(misAuditBean2);
			bidderDetailBeans.add(bidderDetailBean);
		}
		 
		return bidderDetailBeans;
	}
}
