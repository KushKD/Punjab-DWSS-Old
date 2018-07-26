/**
 * 
 */
package com.prwss.mis.finance.accountschart;

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
import com.prwss.mis.finance.accountschart.dao.AccountsChartDao;
import com.prwss.mis.finance.accountschart.struts.AccountsChartForm;
import com.prwss.mis.masters.complaint.dao.ComplaintBean;

/**
 * @author PJHA
 *
 */
public class AccountsChartBOImpl implements  AccountsChartBO {
	private Logger log = Logger.getLogger(AccountsChartBOImpl.class);
	private AccountsChartDao accountsChartDao;
	
	
	

	public void setAccountsChartDao(AccountsChartDao accountsChartDao) {
		this.accountsChartDao = accountsChartDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<AccountsChartBean> findAccountsChart(
			AccountsChartForm accountsChartForm, List<String> statusList)
			throws MISException {
		List<AccountsChartBean> accountsChartBeans  = new ArrayList<AccountsChartBean>();
		AccountsChartBean accountsChartBean = new AccountsChartBean();
		
		try {
			if(!MisUtility.ifEmpty(accountsChartForm.getCodeHeadId()))
				accountsChartBean.setCodeHeadId("");			
			else			
			accountsChartBean.setCodeHeadId(accountsChartForm.getCodeHeadId());
			/*accountsChartBean.setMajorHeadId(accountsChartForm.getMajorHeadId());
			accountsChartBean.setMinorHeadId(accountsChartForm.getMinorHeadId());
			accountsChartBean.setAccountNature(accountsChartForm.getAccountNature());*/
			
			accountsChartBeans = accountsChartDao.findAccountsChart(accountsChartBean, statusList);
		
	} catch (DataAccessException e) {
		log.error(e.getLocalizedMessage(),e);
		throw e;
	}
	 catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
	return accountsChartBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveAccountsChart(AccountsChartForm accountsChartForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			AccountsChartBean accountsChartBean = populateAccountsChartBean(accountsChartForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			accountsChartBean.setMisAuditBean(misAuditBean);

			status = accountsChartDao.saveAccountsChart(accountsChartBean);

			if(!status){
				log.error(accountsChartBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to save Accounts chart details");
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
	public boolean updateAccountsChart(AccountsChartForm accountsChartForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			AccountsChartBean accountsChartBean = populateAccountsChartBean(accountsChartForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			accountsChartBean.setMisAuditBean(misAuditBean);
			status = accountsChartDao.saveOrUpdateAccountsChart(accountsChartBean);
			if(!status){
				log.error(accountsChartBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Accounts chart details");
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
	public boolean deleteAccountsChart(AccountsChartForm accountsChartForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			AccountsChartBean accountsChartBean = populateAccountsChartBean(accountsChartForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			accountsChartBean.setMisAuditBean(misAuditBean);
			status = accountsChartDao.saveOrUpdateAccountsChart(accountsChartBean);
			if(!status){
				log.error(accountsChartBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Accounts chart details");
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
	public boolean postAccountsChart(AccountsChartForm accountsChartForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status = false;
			AccountsChartBean accountsChartBean= new AccountsChartBean();
			accountsChartBean.setCodeHeadId(accountsChartForm.getCodeHeadId());
			accountsChartBean=accountsChartDao.findAccountsChart(accountsChartBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = accountsChartBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			accountsChartBean.setMisAuditBean(misAuditBean);			
		
			
			status = accountsChartDao.saveOrUpdateAccountsChart(accountsChartBean);

			if(!status){
				log.error(accountsChartBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Account Chart details");
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
	
private AccountsChartBean populateAccountsChartBean(AccountsChartForm accountsChartForm){
		
		
	AccountsChartBean accountsChartBean = new AccountsChartBean();
		try {
			accountsChartBean.setAccountType(accountsChartForm.getAccountType());
			accountsChartBean.setAccountNature(accountsChartForm.getAccountNature());
			accountsChartBean.setCodeHeadId(accountsChartForm.getCodeHeadId());
			accountsChartBean.setCodeHeadIdDescription(accountsChartForm.getCodeHeadIdDescription());
			accountsChartBean.setMajorHeadId(accountsChartForm.getMajorHeadId());
			accountsChartBean.setMinorHeadId(accountsChartForm.getMinorHeadId());
			accountsChartBean.setSubHeadId(accountsChartForm.getSubHeadId());
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return accountsChartBean;
		
		
		
	}

}
