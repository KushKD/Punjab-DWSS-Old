/**
 * 
 */
package com.prwss.mis.finance.budgetapproval;
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
import com.prwss.mis.finance.budgetapproval.struts.BudgetApprovalForm;
import com.prwss.mis.finance.budgetdetail.BudgetDetailBean;
import com.prwss.mis.finance.budgetdetail.dao.BudgetDetailDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class BudgetApprovalBOImpl implements BudgetApprovalBO {
	private Logger log = Logger.getLogger(BudgetApprovalBOImpl.class);
	private BudgetDetailDao budgetDetailDao;

	
	
	public void setBudgetDetailDao(BudgetDetailDao budgetDetailDao) {
		this.budgetDetailDao = budgetDetailDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveBudgetApproval(BudgetApprovalForm budgetApprovalForm,
			MISSessionBean misSessionBean) throws MISException {
		
		boolean budgetApprovalStatus=false;
		try {
			System.out.println("In save Budget Approval ");
			

			long budgetId = budgetApprovalForm.getBudgetId();
		
			if(MisUtility.ifEmpty(budgetId)){
				List<BudgetDetailBean> budgetDetailBeans = populateBudgetApprovalDetailBeans(budgetApprovalForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(budgetDetailBeans)){
					
					budgetApprovalStatus = budgetDetailDao.saveBudgetDetails(budgetDetailBeans);
					System.out.println("In save Budget Approval3 "+budgetApprovalStatus);
					if(!budgetApprovalStatus){
						log.error(budgetApprovalStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Budget Approval  details");
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
return budgetApprovalStatus;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateBudgetApproval(BudgetApprovalForm budgetApprovalForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean budgetApprovalStatus=false;
		try {
			System.out.println("In Update Budget Approval ");
			

			long budgetId = budgetApprovalForm.getBudgetId();
		
			if(MisUtility.ifEmpty(budgetId)){
				List<BudgetDetailBean> budgetDetailBeans = populateBudgetApprovalDetailBeans(budgetApprovalForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(budgetDetailBeans)){
					
					 budgetApprovalStatus = budgetDetailDao.updateBudgetDetails(budgetDetailBeans);
					if(!budgetApprovalStatus){
						log.error(budgetApprovalStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Budget Approval  details");
					}
				}
				
			}
			
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return budgetApprovalStatus;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteBudgetApproval(BudgetApprovalForm budgetApprovalForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean budgetApprovalStatus=false;
		try {
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			long budgetId=budgetApprovalForm.getBudgetId();
			
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
					 budgetApprovalStatus = budgetDetailDao.updateBudgetDetails(budgetDetailBeans);
					if(!budgetApprovalStatus){
						log.error(budgetApprovalStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Budget Approval  details");
					}
				}
         

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return budgetApprovalStatus;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postBudgetApproval(BudgetApprovalForm budgetApprovalForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean budgetApprovalStatus=false;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			
			long budgetId = budgetApprovalForm.getBudgetId();

			BudgetDetailBean budgetDetailBean = new BudgetDetailBean();
			budgetDetailBean.setBudgetId(budgetId);
			
				List<BudgetDetailBean> budgetDetailBeans = budgetDetailDao.findBudgetDetails(budgetDetailBean, statusList);
				if(!MisUtility.ifEmpty(budgetDetailBeans)){
					for (BudgetDetailBean budgetDetailBean2 : budgetDetailBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						budgetDetailBean2.setMisAuditBean(misAuditBean);
					}
					 budgetApprovalStatus = budgetDetailDao.updateBudgetDetails(budgetDetailBeans);
					if(!budgetApprovalStatus){
						log.error(budgetApprovalStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Budget Approval  details");
					}
				}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return budgetApprovalStatus;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<BudgetDetailBean> findBudgetApproval(
			BudgetApprovalForm budgetApprovalForm, List<String> statusList)
			throws MISException {
		List<BudgetDetailBean> budgetDetailBeans = new  ArrayList<BudgetDetailBean>();
		try {
			
			BudgetDetailBean budgetDetailBean = new BudgetDetailBean();
			
			budgetDetailBean.setBudgetId(budgetApprovalForm.getBudgetId());
			budgetDetailBean.setLocationId(budgetApprovalForm.getLocationId());
			budgetDetailBeans= budgetDetailDao.findBudgetDetails(budgetDetailBean, statusList);
		
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return budgetDetailBeans;
	}

private List<BudgetDetailBean> populateBudgetApprovalDetailBeans(BudgetApprovalForm budgetApprovalForm,  MISSessionBean misSessionBean, String status) throws MISException{
		
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);

		List<BudgetDetailBean> budgetDetailBeans= new ArrayList<BudgetDetailBean>();
		Datagrid budgetApprovalDatagrid = budgetApprovalForm.getBudgetApprovalDatagrid();

		@SuppressWarnings("unchecked")
		Collection<BudgetDetailBean> addedBudgetApprovalDetailBeans = budgetApprovalDatagrid.getAddedData();
		if(!MisUtility.ifEmpty(addedBudgetApprovalDetailBeans)){
			for (BudgetDetailBean budgetDetailBean : addedBudgetApprovalDetailBeans) {
//				if( !(budgetDetailBean.getEstFinanceBudgt().longValue()> budgetDetailBean.getAppFinanceBudgt().longValue()) ||!(budgetDetailBean.getEstPhysicalBudgt().longValue()> budgetDetailBean.getAppPhysicalBudgt().longValue())  )
//				{
//					throw new MISException(MISExceptionCodes.MIS003, "Approved budget is greter than estimated budget");	
//					
//				}
				budgetDetailBean.setMisAuditBean(misAuditBean);
				budgetDetailBeans.add(budgetDetailBean);
			}
		}

		@SuppressWarnings("unchecked")
		Collection<BudgetDetailBean> modifiedBudgetApprovalDetailBeans = budgetApprovalDatagrid.getModifiedData();
		if(!MisUtility.ifEmpty(modifiedBudgetApprovalDetailBeans)){
			for (BudgetDetailBean budgetDetailBean : modifiedBudgetApprovalDetailBeans) {
//				if( !(budgetDetailBean.getEstFinanceBudgt().longValue()> budgetDetailBean.getAppFinanceBudgt().longValue()) ||!(budgetDetailBean.getEstPhysicalBudgt().longValue()> budgetDetailBean.getAppPhysicalBudgt().longValue())  )
//				{
//					throw new MISException(MISExceptionCodes.MIS003, "Approved budget is greter than estimated budget");	
//					
//				}
				budgetDetailBean.setMisAuditBean(misAuditBean);
				budgetDetailBeans.add(budgetDetailBean);
			}
				
			}
	
		
		@SuppressWarnings("unchecked")
		Collection<BudgetDetailBean> deletedBudgetApprovalDetailBeans = budgetApprovalDatagrid.getDeletedData();
		if(!MisUtility.ifEmpty(deletedBudgetApprovalDetailBeans)){
			for (BudgetDetailBean budgetDetailBean : deletedBudgetApprovalDetailBeans) {
				MISAuditBean misAuditBean3 = new MISAuditBean();
				misAuditBean3.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean3.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean3.setStatus(MISConstants.MASTER_STATUS_DELETED);
				budgetDetailBean.setMisAuditBean(misAuditBean3);
				budgetDetailBeans.add(budgetDetailBean);
			}
				
			}
		
		return budgetDetailBeans;
	}
	
	
	
}
