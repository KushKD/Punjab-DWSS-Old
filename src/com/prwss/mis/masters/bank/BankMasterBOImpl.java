package com.prwss.mis.masters.bank;

import java.util.ArrayList;
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
import com.prwss.mis.masters.bank.dao.BankMasterBean;
import com.prwss.mis.masters.bank.dao.BankMasterDao;
import com.prwss.mis.masters.bank.struts.BankMasterForm;
import com.prwss.mis.masters.location.dao.LocationBean;

public class BankMasterBOImpl implements BankMasterBO {
	private Logger log = Logger.getLogger(BankMasterBOImpl.class);
	private BankMasterDao bankMasterDao;


	public void setBankMasterDao(BankMasterDao bankMasterDao) {
		this.bankMasterDao = bankMasterDao;
	}

	@Override
	public List<BankMasterBean> findBank(BankMasterForm bankMasterForm,
			List<String> statusList) throws MISException {
		List<BankMasterBean> bankMasterBeans = null;
		
		try {
			BankMasterBean bankMasterBean = new BankMasterBean();
			
			if(MisUtility.ifEmpty(bankMasterForm.getBankId())){
				
				bankMasterBean.setBankId(bankMasterForm.getBankId());}
			else {															//Code is correct to be un-comment when banks have districts attached
				LocationBean bean = new LocationBean();
				bean.setLocationId(bankMasterForm.getLocationId());
				bankMasterBean.setDistrcit(bean);
			}
			
			
			
			bankMasterBeans = bankMasterDao.findBank(bankMasterBean, statusList);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return bankMasterBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long saveBank(BankMasterForm bankMasterForm,
			MISSessionBean misSessionBean) throws MISException {
		long bankId = 0;
		
		try {
			BankMasterBean bankMasterFindBean = new BankMasterBean();
			bankMasterFindBean.setBankName(bankMasterForm.getBankName());
			bankMasterFindBean.setBankBranch(bankMasterForm.getBankBranch());
			
			List<String> statusFindList = new ArrayList<String>();
			statusFindList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusFindList.add(MISConstants.MASTER_STATUS_VERIFIED);
			List<BankMasterBean> bankMasterBeans = bankMasterDao.findBank(bankMasterFindBean, statusFindList);
			if(!MisUtility.ifEmpty(bankMasterBeans)){
				throw new MISException(MISExceptionCodes.MIS001,"Bank Name : " + bankMasterForm.getBankName()+" already exists");
			}
			BankMasterBean bankMasterBean = populateBankMasterBean(bankMasterForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			bankMasterBean.setMisAuditBean(misAuditBean);
			bankId = bankMasterDao.saveBank(bankMasterBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return bankId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateBank(BankMasterForm bankMasterForm,
			MISSessionBean misSessionBean,List<String> statusList) throws MISException {
		boolean status = false;
		
		try {
			BankMasterBean bankMasterBean = populateBankMasterBean(bankMasterForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(statusList.get(0));
			bankMasterBean.setMisAuditBean(misAuditBean);
			status = bankMasterDao.updateBank(bankMasterBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteBank(BankMasterForm bankMasterForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
//		Long[] bankIds = ArrayUtils.toObject(bankMasterForm.getBankIds());
		try {
//			List<BankMasterBean> bankMasterBeans = bankMasterDao.findBank(Arrays.asList(bankIds));
			
			BankMasterBean bankMasterBean = populateBankMasterBean(bankMasterForm);
			
				MISAuditBean misAuditBean = new MISAuditBean();
//				if(!MisUtility.ifEmpty(bankMasterBeans)){
//				for (BankMasterBean bankMasterBean : bankMasterBeans) {
//					misAuditBean = bankMasterBean.getMisAuditBean();
					misAuditBean.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean.setEntDate(misSessionBean.getEnteredDate());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
					bankMasterBean.setMisAuditBean(misAuditBean);
//				}
				status = bankMasterDao.updateBank(bankMasterBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new MISException(e);
		}
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postBank(BankMasterForm bankMasterForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
//		Long[] bankIds = ArrayUtils.toObject(bankMasterForm.getBankIds());
		try {
//			List<BankMasterBean> bankMasterBeans = bankMasterDao.findBank(Arrays.asList(bankIds));
			BankMasterBean bankMasterBean = new BankMasterBean();
			bankMasterBean.setBankId(bankMasterForm.getBankId());
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			BankMasterBean bankMasterBean2 = bankMasterDao.findBank(bankMasterBean, statusList).get(0);
			MISAuditBean misAuditBean = null;
//				if(!MisUtility.ifEmpty(bankMasterBeans)){
//				for (BankMasterBean bankMasterBean : bankMasterBeans) {
					misAuditBean = bankMasterBean2.getMisAuditBean();
					misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
					misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
					bankMasterBean2.setMisAuditBean(misAuditBean);
//				}
				status = bankMasterDao.updateBank(bankMasterBean2);
//				}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new MISException(e);
		}
		return status;
	}
	
	private BankMasterBean populateBankMasterBean(BankMasterForm bankMasterForm){
		BankMasterBean bankMasterBean = new BankMasterBean();
		bankMasterBean.setBankAddress(bankMasterForm.getBankAddress());
		bankMasterBean.setBankBranch(bankMasterForm.getBankBranch());
		bankMasterBean.setBankId(bankMasterForm.getBankId());
		bankMasterBean.setBankName(bankMasterForm.getBankName());
		LocationBean bean = new LocationBean();
		bean.setLocationId(bankMasterForm.getLocationId());
		bankMasterBean.setDistrcit(bean);
		return bankMasterBean;
	}

}
