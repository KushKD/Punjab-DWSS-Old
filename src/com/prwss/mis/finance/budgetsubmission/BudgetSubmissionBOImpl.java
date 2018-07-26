/**
 * 
 */
package com.prwss.mis.finance.budgetsubmission;

import java.math.BigDecimal;
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
import com.prwss.mis.finance.budgetsubmission.struts.BudgetSubmissionForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class BudgetSubmissionBOImpl implements BudgetSubmissionBO{
	
	private Logger log = Logger.getLogger(BudgetSubmissionBOImpl.class);
	private BudgetDetailDao budgetDetailDao;
	
	


	public void setBudgetDetailDao(BudgetDetailDao budgetDetailDao) {
		this.budgetDetailDao = budgetDetailDao;
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public boolean saveBudgetSubmission(
                BudgetSubmissionForm budgetSubmissionForm,
                MISSessionBean misSessionBean) throws MISException {
          boolean budgetSubmissionStatus = false;
          try {
                System.out.println("In save Budget Submission ");
                List<String> statusList = new ArrayList<String>();
                statusList.add(MISConstants.MASTER_STATUS_VERIFIED);

                long budgetId = budgetSubmissionForm.getBudgetId();
                BudgetDetailBean budgetDetailBean = new BudgetDetailBean();
                budgetDetailBean.setBudgetId(budgetId);
                budgetDetailBean.setLocationId(budgetSubmissionForm.getLocationId());
                List<BudgetDetailBean> budgetDetailFindBeans = budgetDetailDao.findBudgetDetails(budgetDetailBean, statusList);
                if(!MisUtility.ifEmpty(budgetDetailFindBeans)){
                      throw new MISException(MISExceptionCodes.MIS001,"Budget ID - "+budgetId);
                }
                if(MisUtility.ifEmpty(budgetId)){
                      List<BudgetDetailBean> budgetDetailBeans = populateBudgetSubmissionDetailBeans(budgetSubmissionForm,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
                      
                      
                      if(!MisUtility.ifEmpty(budgetDetailBeans)){
                            System.out.println("In save Budget Submission 2");
                            budgetSubmissionStatus = budgetDetailDao.saveBudgetDetails(budgetDetailBeans);
                            
                            System.out.println("In save Budget Submission 3 ");
                            if(!budgetSubmissionStatus){
                                  log.error(budgetSubmissionStatus);
                                  throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Budget Submission  details");
                            }
                      }
                      
                }
                
                
          } catch (DataAccessException e) {
                log.error(e.getLocalizedMessage(),e);
                throw new MISException(e);
          } 
return budgetSubmissionStatus;

	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateBudgetSubmission(
			BudgetSubmissionForm budgetSubmissionForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean budgetSubmissionStatus= false;
		try {
			System.out.println("In Update Budget Submission ");
			

			long budgetId = budgetSubmissionForm.getBudgetId();
		
			if(MisUtility.ifEmpty(budgetId)){
				List<BudgetDetailBean> budgetDetailBeans = populateBudgetSubmissionDetailBeans(budgetSubmissionForm,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(budgetDetailBeans)){
					budgetSubmissionStatus = budgetDetailDao.updateBudgetDetails(budgetDetailBeans);
					if(!budgetSubmissionStatus){
						log.error(budgetSubmissionStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Budget Submission  details");
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
return budgetSubmissionStatus;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteBudgetSubmission(
			BudgetSubmissionForm budgetSubmissionForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean budgetSubmissionStatus = false;
		try {
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			long budgetId=budgetSubmissionForm.getBudgetId();
			
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
					budgetSubmissionStatus = budgetDetailDao.updateBudgetDetails(budgetDetailBeans);
					if(!budgetSubmissionStatus){
						log.error(budgetSubmissionStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Budget Submission  details");
					}
				}
         

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return budgetSubmissionStatus;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postBudgetSubmission(
			BudgetSubmissionForm budgetSubmissionForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean budgetSubmissionStatus= false;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			
			long budgetId = budgetSubmissionForm.getBudgetId();

			BudgetDetailBean budgetDetailBean = new BudgetDetailBean();
			budgetDetailBean.setBudgetId(budgetId);
			
				List<BudgetDetailBean> budgetDetailBeans = budgetDetailDao.findBudgetDetails(budgetDetailBean, statusList);
				if(!MisUtility.ifEmpty(budgetDetailBeans)){
					for (BudgetDetailBean budgetDetailBean2 : budgetDetailBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						budgetDetailBean2.setMisAuditBean(misAuditBean);
					}
					budgetSubmissionStatus = budgetDetailDao.updateBudgetDetails(budgetDetailBeans);
					if(!budgetSubmissionStatus){
						log.error(budgetSubmissionStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Budget Submission  details");
					}
				}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
return budgetSubmissionStatus;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public List<BudgetDetailBean> findBudgetSubmission(
			BudgetSubmissionForm budgetSubmissionForm, List<String> statusList)
			throws MISException {
		List<BudgetDetailBean> budgetDetailBeans = new  ArrayList<BudgetDetailBean>();
		try {
			
			BudgetDetailBean budgetDetailBean = new BudgetDetailBean();
			
			budgetDetailBean.setBudgetId(budgetSubmissionForm.getBudgetId());
			budgetDetailBean.setLocationId(budgetSubmissionForm.getLocationId());
			budgetDetailBean.setProgramId(budgetSubmissionForm.getProgramId());
			/*budgetDetailBean.setComponentId(budgetSubmissionForm.getComponentId());
			budgetDetailBean.setSubComponentId(budgetSubmissionForm.getSubComponentId());*/
			budgetDetailBeans= budgetDetailDao.findBudgetDetails(budgetDetailBean, statusList);
			System.out.println("budgetDetailBeans in BO "+budgetDetailBeans.size());
		
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return budgetDetailBeans;
	}

private List<BudgetDetailBean> populateBudgetSubmissionDetailBeans(BudgetSubmissionForm budgetSubmissionForm,  MISSessionBean misSessionBean, String status){
		
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


		Datagrid budgetSubmissionDatagrid = budgetSubmissionForm.getBudgetSubmissionDatagrid();

		@SuppressWarnings("unchecked")
		Collection<BudgetDetailBean> addedBudgetSubmissionDetailBeans = budgetSubmissionDatagrid.getAddedData();
		if(!MisUtility.ifEmpty(addedBudgetSubmissionDetailBeans)){
			for (BudgetDetailBean budgetDetailBean : addedBudgetSubmissionDetailBeans) {
				//budgetSubmissionDetailBean.setBudgetId(budgetId);
				//budgetSubmissionDetailBean.setLocationId(budgetSubmissionForm.getLocationId());
				budgetDetailBean.setLocationId(budgetSubmissionForm.getLocationId());
				budgetDetailBean.setProgramId(budgetSubmissionForm.getProgramId());
				budgetDetailBean.setBudgetId(budgetSubmissionForm.getBudgetId());
				budgetDetailBean.setMisAuditBean(misAuditBean);
				budgetDetailBean.setAppFinanceBudgt(new BigDecimal(0.0));
				budgetDetailBean.setAppPhysicalBudgt(new BigDecimal(0.0));
				budgetDetailBeans.add(budgetDetailBean);
			}
		}

		@SuppressWarnings("unchecked")
		Collection<BudgetDetailBean> modifiedBudgetSubmissionDetailBeans = budgetSubmissionDatagrid.getModifiedData();
		if(!MisUtility.ifEmpty(modifiedBudgetSubmissionDetailBeans)){
			for (BudgetDetailBean budgetDetailBean : modifiedBudgetSubmissionDetailBeans) {
				budgetDetailBean.setLocationId(budgetSubmissionForm.getLocationId());
				budgetDetailBean.setProgramId(budgetSubmissionForm.getProgramId());
				budgetDetailBean.setBudgetId(budgetSubmissionForm.getBudgetId());
				budgetDetailBean.setMisAuditBean(misAuditBean);
				budgetDetailBean.setAppFinanceBudgt(new BigDecimal(0.0));
				budgetDetailBean.setAppPhysicalBudgt(new BigDecimal(0.0));
				budgetDetailBeans.add(budgetDetailBean);
			}
				
			}
	
		
		@SuppressWarnings("unchecked")
		Collection<BudgetDetailBean> deletedBudgetSubmissionDetailBeans = budgetSubmissionDatagrid.getDeletedData();
		if(!MisUtility.ifEmpty(deletedBudgetSubmissionDetailBeans)){
			for (BudgetDetailBean budgetDetailBean : deletedBudgetSubmissionDetailBeans) {
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				budgetDetailBean.setLocationId(budgetSubmissionForm.getLocationId());
				budgetDetailBean.setProgramId(budgetSubmissionForm.getProgramId());
				budgetDetailBean.setBudgetId(budgetSubmissionForm.getBudgetId());
				budgetDetailBean.setMisAuditBean(misAuditBean);
				budgetDetailBeans.add(budgetDetailBean);
			}
				
			}
		
		return budgetDetailBeans;
	}
	
	
	
}
