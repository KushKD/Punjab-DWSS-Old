package com.prwss.mis.ccdu.plan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.ccdu.plan.dao.CCDUPlanDetailBean;
import com.prwss.mis.ccdu.plan.dao.CCDUPlanDetailDao;
import com.prwss.mis.ccdu.plan.dao.CCDUPlanHeaderBean;
import com.prwss.mis.ccdu.plan.dao.CCDUPlanHeaderDao;
import com.prwss.mis.ccdu.plan.struts.CCDUPlanDetailsForm;
import com.prwss.mis.ccdu.plan.struts.CCDUPlanSummaryBean;
import com.prwss.mis.ccdu.plan.struts.CreateCCDUPlanForm;
import com.prwss.mis.ccdu.plan.struts.PlanTrainingDetailGridBean;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.master.training.dao.TrainingBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public abstract class CCDUPlanDetailBOImpl implements CCDUPlanBO {
	private Logger log = Logger.getLogger(CCDUPlanDetailBOImpl.class);
	
	protected CCDUPlanHeaderDao ccduPlanHeaderDao;
	protected CCDUPlanDetailDao ccduPlanDetailDao;
	
	public void setCcduPlanHeaderDao(CCDUPlanHeaderDao ccduPlanHeaderDao) {
		this.ccduPlanHeaderDao = ccduPlanHeaderDao;
	}

	public void setCcduPlanDetailDao(CCDUPlanDetailDao ccduPlanDetailDao) {
		this.ccduPlanDetailDao = ccduPlanDetailDao;
	}

	@Override
	public List<CCDUPlanHeaderBean> findCCDUPlanDetail(CCDUPlanDetailsForm ccduPlanDetailsForm, List<String> statusList)
			throws MISException {
		List<CCDUPlanHeaderBean> ccduPlanHeaderBeans = null;

		try {
			CCDUPlanHeaderBean ccduPlanHeaderBean = new CCDUPlanHeaderBean();
			ccduPlanHeaderBean.setPlanId(ccduPlanDetailsForm.getPlanId());
			ccduPlanHeaderBean.setLocationId(ccduPlanDetailsForm.getLocationId());
			ccduPlanHeaderBeans = ccduPlanHeaderDao.findCCDUPlanHeader(ccduPlanHeaderBean, statusList);
			Set<CCDUPlanDetailBean> ccduPlanDetailBeans = null;
			Iterator<CCDUPlanDetailBean> iteratorDetail = null;
			CCDUPlanDetailBean  ccduPlanDetailBean = null;
			if(!MisUtility.ifEmpty(ccduPlanHeaderBeans)){
				for (CCDUPlanHeaderBean bean : ccduPlanHeaderBeans) {
					ccduPlanDetailBeans = bean.getCcduPlanDetailBeans();
					if(!MisUtility.ifEmpty(ccduPlanDetailBeans)){
						iteratorDetail = ccduPlanDetailBeans.iterator();					
						while(iteratorDetail.hasNext()){
							ccduPlanDetailBean = iteratorDetail.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(ccduPlanDetailBean.getMisAuditBean().getStatus())){
								iteratorDetail.remove();
								break;
							}
						}
					}
				}
			}
		} catch (DataAccessException e){
			log.error(e.getMostSpecificCause(), e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return ccduPlanHeaderBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveCCDUPlanDetail(CCDUPlanDetailsForm ccduPlanDetailsForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean headerStatus = false;
		boolean childStatus = false;
		try {
			CCDUPlanHeaderBean ccduPlanHeaderBean = populateCCDUPlanHeaderBean(ccduPlanDetailsForm);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			List<CCDUPlanHeaderBean> ccduPlanHeaderFindBean=ccduPlanHeaderDao.findCCDUPlanHeader(ccduPlanHeaderBean, statusList);
			if(!MisUtility.ifEmpty(ccduPlanHeaderFindBean)){
				throw new MISException(MISExceptionCodes.MIS001, "CCDU Plan is already exist, for the Plan Id "+ccduPlanDetailsForm.getPlanId()+" and Location Id "+ccduPlanDetailsForm.getLocationId());
			}
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			ccduPlanHeaderBean.setMisAuditBean(misAuditBean);
			
			headerStatus = ccduPlanHeaderDao.saveCCDUPlanHeader(ccduPlanHeaderBean);
			if(headerStatus){
				Collection<CCDUPlanDetailBean> ccduPlanDetailBeans = populateCCDUPlanDetailBeans(ccduPlanDetailsForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);				
				childStatus = ccduPlanDetailDao.saveOrUpdateCCDUPlanDetail(ccduPlanDetailBeans);
				if(!childStatus){
					throw new MISException(MISExceptionCodes.MIS003, "Failed to save CCDU Plan Details");
				}
			}
			
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause(), e);
			throw new MISException(e);
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return headerStatus;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateCCDUPlanDetail(CCDUPlanDetailsForm ccduPlanDetailsForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean headerStatus = false;
		
		try {
			CCDUPlanHeaderBean ccduPlanHeaderBean = populateCCDUPlanHeaderBean(ccduPlanDetailsForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			ccduPlanHeaderBean.setMisAuditBean(misAuditBean);
			
			headerStatus = ccduPlanHeaderDao.updateCCDUPlanHeader(ccduPlanHeaderBean);
			if(headerStatus){
				Collection<CCDUPlanDetailBean> ccduPlanDetailBeans = populateCCDUPlanDetailBeans(ccduPlanDetailsForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);				
				boolean childStatus = ccduPlanDetailDao.saveOrUpdateCCDUPlanDetail(ccduPlanDetailBeans);
				if(!childStatus){
					throw new MISException(MISExceptionCodes.MIS003, "Failed to update CCDU Plan Details");
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause(),e);
			throw new MISException(e);
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return headerStatus;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteCCDUPlanDetail(CCDUPlanDetailsForm ccduPlanDetailsForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean headerStatus = false;
		
		try {
			CCDUPlanHeaderBean ccduPlanHeaderBean = populateCCDUPlanHeaderBean(ccduPlanDetailsForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			ccduPlanHeaderBean.setMisAuditBean(misAuditBean);
			
			headerStatus = ccduPlanHeaderDao.updateCCDUPlanHeader(ccduPlanHeaderBean);
			if(headerStatus){
				Collection<CCDUPlanDetailBean> ccduPlanDetailBeans = populateCCDUPlanDetailBeans(ccduPlanDetailsForm, misSessionBean, MISConstants.MASTER_STATUS_DELETED);				
				boolean childStatus = ccduPlanDetailDao.saveOrUpdateCCDUPlanDetail(ccduPlanDetailBeans);
				if(!childStatus){
					throw new MISException(MISExceptionCodes.MIS003, "Failed to delete CCDU Plan Details");
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause(), e);
			throw new MISException(e);
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return true;
	}
	
	public List<CCDUPlanSummaryBean> getPlanSummary(CreateCCDUPlanForm createCCDUPlanForm, List<String> statusList) throws MISException {
		List<CCDUPlanSummaryBean> ccduPlanSummaryBeans = null;
		try {
			ccduPlanSummaryBeans = ccduPlanDetailDao.getPlanSummary(createCCDUPlanForm.getPlanId(), statusList);
		} catch (DataAccessException e) {
			log.error(e.getMostSpecificCause(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return ccduPlanSummaryBeans;
	}
	
	private CCDUPlanHeaderBean populateCCDUPlanHeaderBean(CCDUPlanDetailsForm ccduPlanDetailsForm) throws MISException{
		CCDUPlanHeaderBean ccduPlanHeaderBean = new CCDUPlanHeaderBean();
		try {
			ccduPlanHeaderBean.setPlanId(ccduPlanDetailsForm.getPlanId());
			ccduPlanHeaderBean.setLocationId(ccduPlanDetailsForm.getLocationId());
			ccduPlanHeaderBean.setPrePlanningCount(ccduPlanDetailsForm.getPrePlanningCount());
			ccduPlanHeaderBean.setPlanningCount(ccduPlanDetailsForm.getPlanningCount());
			ccduPlanHeaderBean.setImplementationCount(ccduPlanDetailsForm.getImplementationCount());
			ccduPlanHeaderBean.setOperationMaintenanceCount(ccduPlanDetailsForm.getOperationMaintenanceCount());

			ccduPlanHeaderBean.setPrePlanningCountCS(ccduPlanDetailsForm.getPrePlanningCountCS());
			ccduPlanHeaderBean.setPlanningCountCS(ccduPlanDetailsForm.getPlanningCountCS());
			ccduPlanHeaderBean.setImplementationCountCS(ccduPlanDetailsForm.getImplementationCountCS());
			ccduPlanHeaderBean.setOperationMaintenanceCountCS(ccduPlanDetailsForm.getOperationMaintenanceCountCS());
			ccduPlanHeaderBean.setCbSewerageCount(ccduPlanDetailsForm.getCbSewerageCount());
			ccduPlanHeaderBean.setSustainabilityWssCount(ccduPlanDetailsForm.getSustainabilityWssCount());
			ccduPlanHeaderBean.setPrePlanningCountNGO(ccduPlanDetailsForm.getPrePlanningCountNGO());
			ccduPlanHeaderBean.setPlanningCountNGO(ccduPlanDetailsForm.getPlanningCountNGO());
			ccduPlanHeaderBean.setImplementationCountNGO(ccduPlanDetailsForm.getImplementationCountNGO());
			ccduPlanHeaderBean.setOperationMaintenanceCountNGO(ccduPlanDetailsForm.getOperationMaintenanceCountNGO());
			
		} catch (Exception e) {
			throw new MISException(e);
		}
		
		return ccduPlanHeaderBean;
	}
	
	@SuppressWarnings("unchecked")
	private List<CCDUPlanDetailBean> populateCCDUPlanDetailBeans(CCDUPlanDetailsForm ccduPlanDetailsForm, MISSessionBean misSessionBean,String status) throws MISException{
		
		MISAuditBean misAuditBean = new MISAuditBean();
		List<CCDUPlanDetailBean> ccduPlanDetailBeans = new ArrayList<CCDUPlanDetailBean>();
		try {
			if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			} else{
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			}
			misAuditBean.setStatus(status);
			
			Datagrid ccduPlanDetailsDatagrid = ccduPlanDetailsForm.getCcduPlanDetailsDatagrid();
			Collection<PlanTrainingDetailGridBean> addedCcduPlanDetailBeans = ccduPlanDetailsDatagrid.getAddedData();
			CCDUPlanDetailBean ccduPlanDetailBean = null;
			if(!MisUtility.ifEmpty(addedCcduPlanDetailBeans)){
				for (PlanTrainingDetailGridBean planTrainingDetailGridBean : addedCcduPlanDetailBeans) {
					ccduPlanDetailBean = new CCDUPlanDetailBean();
					ccduPlanDetailBean.setPlanId(ccduPlanDetailsForm.getPlanId());
					ccduPlanDetailBean.setCount(planTrainingDetailGridBean.getCount());
					ccduPlanDetailBean.setLocationId(ccduPlanDetailsForm.getLocationId());
					TrainingBean trainingBean = new TrainingBean();
					trainingBean.setTrainingId(planTrainingDetailGridBean.getTrainingId());
					ccduPlanDetailBean.setTrainingBean(trainingBean);
					ccduPlanDetailBean.setMisAuditBean(misAuditBean);
					ccduPlanDetailBeans.add(ccduPlanDetailBean);
				}
			}
			
			Collection<PlanTrainingDetailGridBean> modifiedCcduPlanDetailBeans = ccduPlanDetailsDatagrid.getModifiedData();
			if(!MisUtility.ifEmpty(modifiedCcduPlanDetailBeans)){
				for (PlanTrainingDetailGridBean planTrainingDetailGridBean : modifiedCcduPlanDetailBeans) {
					ccduPlanDetailBean = new CCDUPlanDetailBean();
					ccduPlanDetailBean.setPlanId(ccduPlanDetailsForm.getPlanId());
					ccduPlanDetailBean.setCount(planTrainingDetailGridBean.getCount());
					ccduPlanDetailBean.setLocationId(ccduPlanDetailsForm.getLocationId());
					TrainingBean trainingBean = new TrainingBean();
					trainingBean.setTrainingId(planTrainingDetailGridBean.getTrainingId());
					ccduPlanDetailBean.setTrainingBean(trainingBean);
					ccduPlanDetailBean.setMisAuditBean(misAuditBean);
					ccduPlanDetailBeans.add(ccduPlanDetailBean);
				}
			}
			
			Collection<PlanTrainingDetailGridBean> deletedCcduPlanDetailBeans = ccduPlanDetailsDatagrid.getDeletedData();
			if(!MisUtility.ifEmpty(deletedCcduPlanDetailBeans)){
				for (PlanTrainingDetailGridBean planTrainingDetailGridBean : deletedCcduPlanDetailBeans) {
					ccduPlanDetailBean = new CCDUPlanDetailBean();
					ccduPlanDetailBean.setPlanId(ccduPlanDetailsForm.getPlanId());
					ccduPlanDetailBean.setCount(planTrainingDetailGridBean.getCount());
					ccduPlanDetailBean.setLocationId(ccduPlanDetailsForm.getLocationId());
					TrainingBean trainingBean = new TrainingBean();
					trainingBean.setTrainingId(planTrainingDetailGridBean.getTrainingId());
					ccduPlanDetailBean.setTrainingBean(trainingBean);
					MISAuditBean misAuditBean2 = new MISAuditBean();
					misAuditBean2.setStatus(MISConstants.MASTER_STATUS_DELETED);
					misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
					ccduPlanDetailBean.setMisAuditBean(misAuditBean2);
					ccduPlanDetailBeans.add(ccduPlanDetailBean);
				}
			}
		} catch (Exception e) {
			throw new MISException(e);
		}
		return ccduPlanDetailBeans;
	}

}
