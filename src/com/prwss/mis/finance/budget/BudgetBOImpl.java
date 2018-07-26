/**
 * 
 */
package com.prwss.mis.finance.budget;

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
import com.prwss.mis.finance.budget.dao.BudgetDao;
import com.prwss.mis.finance.budget.struts.BudgetForm;

/**
 * @author pjha
 *
 */
public class BudgetBOImpl implements BudgetBO {
	
	private BudgetDao budgetDao;
	private Logger log = Logger.getLogger(BudgetBOImpl.class);
	
	

	public BudgetDao getBudgetDao() {
		return budgetDao;
	}

	public void setBudgetDao(BudgetDao budgetDao) {
		this.budgetDao = budgetDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<BudgetBean> findBudgetPlanFrom(BudgetForm budgetForm,
			List<String> statusList) throws MISException {
		List<BudgetBean> budgetBeans = null;
		try{
			BudgetBean budgetBean = new BudgetBean();
			if(MisUtility.ifEmpty(budgetForm.getBudgetId())){
				budgetBean.setBudgetId(budgetForm.getBudgetId());
				System.out.println("INBOIMPL-----------------"+budgetForm.getBudgetId());
				
				budgetBeans = budgetDao.findBudgetPlan(budgetBean, statusList);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return budgetBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveBudgetPlanFrom(BudgetForm budgetForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = true;
		try {
			BudgetBean budgetBean = new BudgetBean();
			budgetBean.setBudgetId(budgetForm.getBudgetId());
		
			
			List<BudgetBean> budgetBeans = budgetDao.findBudgetPlan(budgetBean, null);
			if(!MisUtility.ifEmpty(budgetBeans)){
				System.out.println("in duplicate exception throwing");
				throw new MISException(MISExceptionCodes.MIS001, "Budget Plan Id\t"+budgetForm.getBudgetId());			
			}
			BudgetBean budgetBean2 = populateBudgetBean(budgetForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
			budgetBean2.setMisAuditBean(misAuditBean);
			budgetDao.saveBudgetPlan(budgetBean2);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateBudgetPlanFrom(BudgetForm budgetForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			BudgetBean budgetBean = populateBudgetBean(budgetForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
			budgetBean.setMisAuditBean(misAuditBean);
			status = budgetDao.saveOrUpdateBudgetPlan(budgetBean);
		} catch (DataAccessException e) {
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
	public boolean deleteBudgetPlanFrom(BudgetForm budgetForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
		BudgetBean  budgetBean = populateBudgetBean(budgetForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED); 
			budgetBean.setMisAuditBean(misAuditBean);
			status = budgetDao.saveOrUpdateBudgetPlan(budgetBean);
		} catch (DataAccessException e) {
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
	public boolean postBudgetPlanFrom(BudgetForm budgetForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status = false;
			BudgetBean budgetBean = new BudgetBean();
			budgetBean.setBudgetId(budgetForm.getBudgetId());
			budgetBean.setProgramId(budgetForm.getProgramId());
			
			
			budgetBean= budgetDao.findBudgetPlan(budgetBean, statusList).get(0);
			
			
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = budgetBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			budgetBean.setMisAuditBean(misAuditBean);			
			status = budgetDao.saveOrUpdateBudgetPlan(budgetBean);
			if(!status){
				log.error(budgetBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Budget Plan details");
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
	
	private BudgetBean populateBudgetBean(BudgetForm budgetForm){
		BudgetBean budgetBean = new BudgetBean();
		budgetBean.setBudgetId(budgetForm.getBudgetId());
	
		budgetBean.setProgramId(budgetForm.getProgramId());
		budgetBean.setBudgetFromDate(MisUtility.convertStringToDate(budgetForm.getBudgetFromDate()));
		budgetBean.setBudgetToDate(MisUtility.convertStringToDate(budgetForm.getBudgetToDate()));
		return budgetBean;
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public long createBudgetPlan(BudgetForm budgetForm,
			MISSessionBean misSessionBean) throws MISException {
		long plainId = 0;
		 try {
			BudgetBean budgetBean = new BudgetBean();
			budgetBean.setBudgetId(budgetForm.getBudgetId());
			
			budgetBean.setProgramId(budgetForm.getProgramId());
			budgetBean.setBudgetFromDate(MisUtility.convertStringToDate(budgetForm.getBudgetFromDate()));
			budgetBean.setBudgetToDate(MisUtility.convertStringToDate(budgetForm.getBudgetToDate()));
			 
			 MISAuditBean misAuditBean = new MISAuditBean();
			 misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			 misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			 misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			 
			 budgetBean.setMisAuditBean(misAuditBean);
			 plainId = budgetDao.saveBudgetPlan(budgetBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		 
		return plainId;
	}

}
