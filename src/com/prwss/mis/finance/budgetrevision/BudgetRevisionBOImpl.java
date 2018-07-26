/**
 * 
 */
package com.prwss.mis.finance.budgetrevision;

import java.util.ArrayList;
import java.util.Collection;
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
import com.prwss.mis.finance.budgetdetail.BudgetDetailBean;
import com.prwss.mis.finance.budgetdetail.dao.BudgetDetailDao;
import com.prwss.mis.finance.budgetrevision.struts.BudgetRevisionForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class BudgetRevisionBOImpl implements BudgetRevisionBO {
	
	private Logger log = Logger.getLogger(BudgetRevisionBOImpl.class);
	private BudgetDetailDao budgetDetailDao;
	
	

	
	public void setBudgetDetailDao(BudgetDetailDao budgetDetailDao) {
		this.budgetDetailDao = budgetDetailDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<BudgetDetailBean> findBudgetRevision(
			BudgetRevisionForm budgetRevisionForm, List<String> statusList)
			throws MISException {
		List<BudgetDetailBean> budgetDetailBeans = new  ArrayList<BudgetDetailBean>();
		try {
			
			BudgetDetailBean budgetDetailBean = new BudgetDetailBean();
			
			budgetDetailBean.setBudgetId(budgetRevisionForm.getBudgetId());
			budgetDetailBean.setLocationId(budgetRevisionForm.getLocationId());
			budgetDetailBeans= budgetDetailDao.findBudgetDetails(budgetDetailBean, statusList);
		
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return budgetDetailBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveBudgetRevision(BudgetRevisionForm budgetRevisionForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean budgetRevisionStatus= false;
		try {
			

			long budgetId = budgetRevisionForm.getBudgetId();
		
			if(MisUtility.ifEmpty(budgetId)){
				List<BudgetDetailBean> budgetDetailBeans = populateBudgetRevisionDetailBeans(budgetRevisionForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				
				if(!MisUtility.ifEmpty(budgetDetailBeans)){
					
					budgetRevisionStatus= budgetDetailDao.saveBudgetDetails(budgetDetailBeans);
					if(!budgetRevisionStatus){
						log.error(budgetRevisionStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Budget Revision  details");
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
return budgetRevisionStatus;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateBudgetRevision(BudgetRevisionForm budgetRevisionForm,
			MISSessionBean misSessionBean) throws MISException {
		
		boolean budgetRevisionStatus= false;
		try {
			

			long budgetId = budgetRevisionForm.getBudgetId();
		
			if(MisUtility.ifEmpty(budgetId)){
				List<BudgetDetailBean> budgetDetailBeans = populateBudgetRevisionDetailBeans(budgetRevisionForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				
				if(!MisUtility.ifEmpty(budgetDetailBeans)){
					
					 budgetRevisionStatus = budgetDetailDao.updateBudgetDetails(budgetDetailBeans);
					if(!budgetRevisionStatus){
						log.error(budgetRevisionStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Budget Revision  details");
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
return budgetRevisionStatus;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteBudgetRevision(BudgetRevisionForm budgetRevisionForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean budgetRevisionStatus=false;
		try {
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			long budgetId=budgetRevisionForm.getBudgetId();
			BudgetDetailBean budgetDetailBean = new BudgetDetailBean();
			budgetDetailBean.setBudgetId(budgetId);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
				List<BudgetDetailBean> budgetDetailBeans = budgetDetailDao.findBudgetDetails(budgetDetailBean, statusList);
				if(!MisUtility.ifEmpty(budgetDetailBeans)){
					for (BudgetDetailBean budgetDetailBean2 : budgetDetailBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						budgetDetailBean2.setMisAuditBean(misAuditBean);
					}
					budgetRevisionStatus= budgetDetailDao.updateBudgetDetails(budgetDetailBeans);
					if(!budgetRevisionStatus){
						log.error(budgetRevisionStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Budget Revision  details");
					}
				}
         

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return budgetRevisionStatus;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postBudgetRevision(BudgetRevisionForm budgetRevisionForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean budgetRevisionStatus= false;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			
			long budgetId = budgetRevisionForm.getBudgetId();

			BudgetDetailBean budgetDetailBean = new BudgetDetailBean();
			budgetDetailBean.setBudgetId(budgetId);
			
				List<BudgetDetailBean> budgetDetailBeans = budgetDetailDao.findBudgetDetails(budgetDetailBean, statusList);
				if(!MisUtility.ifEmpty(budgetDetailBeans)){
					for (BudgetDetailBean budgetDetailBean2 : budgetDetailBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						budgetDetailBean2.setMisAuditBean(misAuditBean);
					}
					budgetRevisionStatus = budgetDetailDao.updateBudgetDetails(budgetDetailBeans);
					if(!budgetRevisionStatus){
						log.error(budgetRevisionStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Budget Revision  details");
					}
				}
				
			


		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return budgetRevisionStatus;
	}

private List<BudgetDetailBean> populateBudgetRevisionDetailBeans(BudgetRevisionForm budgetRevisionForm,  MISSessionBean misSessionBean, String status){
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);

		List<BudgetDetailBean> budgetDetailBeans = new ArrayList<BudgetDetailBean>();


		Datagrid budgetRevisionDatagrid = budgetRevisionForm.getBudgetRevisionDatagrid();

		@SuppressWarnings("unchecked")
		Collection<BudgetDetailBean> addedBudgetRevisionDetailBeans = budgetRevisionDatagrid.getAddedData();
		if(!MisUtility.ifEmpty(addedBudgetRevisionDetailBeans)){
			for (BudgetDetailBean budgetDetailBean : addedBudgetRevisionDetailBeans) {
				budgetDetailBean.setLocationId(budgetRevisionForm.getLocationId());
				budgetDetailBean.setProgramId(budgetRevisionForm.getProgramId());
				budgetDetailBean.setBudgetId(budgetRevisionForm.getBudgetId());
				budgetDetailBean.setMisAuditBean(misAuditBean);
				budgetDetailBeans.add(budgetDetailBean);
			}
		}

		@SuppressWarnings("unchecked")
		Collection<BudgetDetailBean> modifiedBudgetRevisionDetailBeans = budgetRevisionDatagrid.getModifiedData();
		if(!MisUtility.ifEmpty(modifiedBudgetRevisionDetailBeans)){
			for (BudgetDetailBean budgetDetailBean : modifiedBudgetRevisionDetailBeans) {
				budgetDetailBean.setLocationId(budgetRevisionForm.getLocationId());
				budgetDetailBean.setProgramId(budgetRevisionForm.getProgramId());
				budgetDetailBean.setBudgetId(budgetRevisionForm.getBudgetId());
				budgetDetailBean.setMisAuditBean(misAuditBean);
				budgetDetailBeans.add(budgetDetailBean);
			}
				
			}
	
		
		@SuppressWarnings("unchecked")
		Collection<BudgetDetailBean> deletedBudgetRevisionDetailBeans = budgetRevisionDatagrid.getDeletedData();
		if(!MisUtility.ifEmpty(deletedBudgetRevisionDetailBeans)){
			for (BudgetDetailBean budgetDetailBean : deletedBudgetRevisionDetailBeans) {
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				budgetDetailBean.setLocationId(budgetRevisionForm.getLocationId());
				budgetDetailBean.setProgramId(budgetRevisionForm.getProgramId());
				budgetDetailBean.setBudgetId(budgetRevisionForm.getBudgetId());
				budgetDetailBean.setMisAuditBean(misAuditBean);
				budgetDetailBeans.add(budgetDetailBean);
			}
				
			}
		
		return budgetDetailBeans;
	}
	
		
	
}
