package com.prwss.mis.procurement.subplan.dao;

import java.math.BigDecimal;
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
import com.prwss.mis.procurement.plan.CreateProcPlanBean;
import com.prwss.mis.procurement.plan.dao.CreateProcPlanDao;
import com.prwss.mis.procurement.subplan.ProcSubPlanHeaderBean;
import com.prwss.mis.procurement.subplan.struts.ProcSubPlanForm;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class ProcSubPlanBOImpl implements ProcSubPlanBO {

	private CreateProcPlanDao createProcPlanDao;
	private ProcSubPlanDao procSubPlanDao;
	private ProcSubPlanSchemeDao procSubPlanSchemeDao;
	private Logger log = Logger.getLogger(ProcSubPlanBOImpl.class);
	private DocumentNumberDAO documentNumberDao;
	private String packageType;
	
	public void setProcSubPlanDao(ProcSubPlanDao procSubPlanDao) {
		this.procSubPlanDao = procSubPlanDao;
	}

	public void setCreateProcPlanDao(CreateProcPlanDao createProcPlanDao) {
		this.createProcPlanDao = createProcPlanDao;
	}

	public void setProcSubPlanSchemeDao(ProcSubPlanSchemeDao procSubPlanSchemeDao) {
		this.procSubPlanSchemeDao = procSubPlanSchemeDao;
	}
	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}
	@Override
	public List<ProcSubPlanHeaderBean> findSubPlan(
			ProcSubPlanForm procSubPlanForm, List<String> statusList)
			throws MISException {
		List<ProcSubPlanHeaderBean> procSubPlanHeaderBeans = new  ArrayList<ProcSubPlanHeaderBean>();
		try {
			ProcSubPlanHeaderBean procSubPlanHeaderBean = new ProcSubPlanHeaderBean();
			procSubPlanHeaderBean.setSubPlanId(procSubPlanForm.getSubPlanId());
			procSubPlanHeaderBean.setLocationId(procSubPlanForm.getLocationId());
			procSubPlanHeaderBeans= procSubPlanDao.findSubplan(procSubPlanHeaderBean, statusList);
			Set<SubPlanSchemeBean> subPlanSchemeBeans = null;
			Iterator<SubPlanSchemeBean> subPlanSchemeIterator = null;
			SubPlanSchemeBean  subPlanSchemeBean = null;
			if(!MisUtility.ifEmpty(procSubPlanHeaderBeans)){

				for (ProcSubPlanHeaderBean bean : procSubPlanHeaderBeans) {
					
					subPlanSchemeBeans = bean.getSubPlanSchemeBeans();
					if(!MisUtility.ifEmpty(subPlanSchemeBeans)){
						subPlanSchemeIterator = subPlanSchemeBeans.iterator();					
						while(subPlanSchemeIterator.hasNext()){
							subPlanSchemeBean = subPlanSchemeIterator.next();
							if(MISConstants.MASTER_STATUS_DELETED.equals(subPlanSchemeBean.getMisAuditBean().getStatus())){
								subPlanSchemeIterator.remove();
								break;
							}
						}
					}
				}
			}
		}catch (DataAccessException e) {
				log.error(e);
				throw e;
			}
		
		return procSubPlanHeaderBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String saveSubPlan(ProcSubPlanForm procSubPlanForm,
			MISSessionBean misSessionBean) throws MISException {
		long subPlanId = 0;
		String subPlanName=null;
		try {
			DocumentNumberBean documentNumebrBean=new DocumentNumberBean();
			documentNumebrBean.setDocumentType("SUBPLAN");
			DocumentNumberBean documentNumberBean = documentNumberDao.getDocumentNumber(documentNumebrBean).get(0);
			if(!(MisUtility.ifEmpty(documentNumberBean))){
				throw new MISException();
			}
			subPlanId=documentNumberBean.getLastNumber();
			
			CreateProcPlanBean createProcPlanBean=new CreateProcPlanBean();
			createProcPlanBean.setPlanId(procSubPlanForm.getPlanId());
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);

			List<CreateProcPlanBean> createProcPlanBeans=createProcPlanDao.findCreateProcPlanFrom(createProcPlanBean, statusList);
			if(!MisUtility.ifEmpty(createProcPlanBeans)){
				packageType=createProcPlanBeans.get(0).getPlanType();
			}
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			
			
			procSubPlanForm.setSubPlanId(subPlanId);
			ProcSubPlanHeaderBean procSubPlanHeaderBean = populateProcSubPlanHeaderBean(procSubPlanForm);
			List<SubPlanSchemeBean> subPlanSchemeBeans = new ArrayList<SubPlanSchemeBean>();
			if(packageType.equals("WORKS")){
				subPlanSchemeBeans=populateProcSubPlanSchemeBeans(procSubPlanForm, subPlanId, misSessionBean,	MISConstants.MASTER_STATUS_VERIFIED); 
			}else if(packageType.equals("CONSULTANCY")){
				SubPlanSchemeBean subPlanSchemeBean=new SubPlanSchemeBean();
				subPlanSchemeBean.setSubPlanId(subPlanId);
				subPlanSchemeBean.setSchemeId("CONSULTANCY");
				subPlanSchemeBean.setSchemeName("CONSULTANCY");
				subPlanSchemeBean.setMisAuditBean(misAuditBean);
				subPlanSchemeBean.setSchemeEstimatedCost(new BigDecimal(0));
				subPlanSchemeBean.setTotalPackages(0);
				subPlanSchemeBeans.add(subPlanSchemeBean);
			}else if(packageType.equals("GOODS")){
				SubPlanSchemeBean subPlanSchemeBean=new SubPlanSchemeBean();
				subPlanSchemeBean.setSubPlanId(subPlanId);
				subPlanSchemeBean.setSchemeId("GOODS");
				subPlanSchemeBean.setSchemeName("GOODS");
				subPlanSchemeBean.setMisAuditBean(misAuditBean);
				subPlanSchemeBean.setSchemeEstimatedCost(new BigDecimal(0));
				subPlanSchemeBean.setTotalPackages(0);
				subPlanSchemeBeans.add(subPlanSchemeBean);
			}else if(packageType.equals("SERVICES")){
				SubPlanSchemeBean subPlanSchemeBean=new SubPlanSchemeBean();
				subPlanSchemeBean.setSubPlanId(subPlanId);
				subPlanSchemeBean.setSchemeId("SERVICES");
				subPlanSchemeBean.setSchemeName("SERVICES");
				subPlanSchemeBean.setMisAuditBean(misAuditBean);
				subPlanSchemeBean.setSchemeEstimatedCost(new BigDecimal(0));
				subPlanSchemeBean.setTotalPackages(0);
				subPlanSchemeBeans.add(subPlanSchemeBean);
			}
				
			String schemeName="";
			if(!MisUtility.ifEmpty(subPlanSchemeBeans)){
				//System.out.println("schemeName is not blank");
				schemeName=subPlanSchemeBeans.get(0).getSchemeName()+"-";
			}else{
				//System.out.println("schemeName is  blank");
				schemeName="";
			}
			
			subPlanName=schemeName+procSubPlanForm.getSubPlanId();
			procSubPlanHeaderBean.setSubPlanName(subPlanName);
		
			procSubPlanHeaderBean.setMisAuditBean(misAuditBean);
			System.out.println("1 save:procSubPlanHeaderBean-->"+procSubPlanHeaderBean);
			subPlanId = procSubPlanDao.saveSubPlan(procSubPlanHeaderBean);
			if(MisUtility.ifEmpty(subPlanId)){
				System.out.println("2 save:SubPlanSchemeBeans-->"+subPlanSchemeBeans);
				if(!MisUtility.ifEmpty(subPlanSchemeBeans)){
					boolean subPlanSchemeStatus = procSubPlanSchemeDao.saveOrUpdateSubPlanScheme(subPlanSchemeBeans);
					if(!subPlanSchemeStatus){
						log.error(subPlanSchemeBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Scheme details");
					}
				}
				boolean updateStatus = documentNumberDao.saveOrUpdateDocumentNumberBeans(documentNumberBean);
				if(!updateStatus){
					throw new MISException();
				}
			}
			
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw e;
		}
		return subPlanName;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateSubPlan(ProcSubPlanForm procSubPlanForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		String subPlanName=null;
		try {
			ProcSubPlanHeaderBean procSubPlanHeaderBean = populateProcSubPlanHeaderBean(procSubPlanForm);
			long subPlanId = procSubPlanHeaderBean.getSubPlanId();
			List<SubPlanSchemeBean> subPlanSchemeBeans = populateProcSubPlanSchemeBeans(procSubPlanForm, subPlanId, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			procSubPlanHeaderBean.setMisAuditBean(misAuditBean);
			/*String schemeName="";
			if(!MisUtility.ifEmpty(subPlanSchemeBeans)){
				System.out.println("schemeName is not blank");
				schemeName=subPlanSchemeBeans.get(0).getSchemeName()+"-";
			}else{
				System.out.println("schemeName is  blank");
				schemeName="";
			}
			subPlanName=schemeName+procSubPlanForm.getSubPlanId();
			procSubPlanHeaderBean.setSubPlanName(subPlanName);*/
			
			status = procSubPlanDao.updateSubPlan(procSubPlanHeaderBean);
			if(status){				
				if(!MisUtility.ifEmpty(subPlanSchemeBeans)){
					boolean subPlanSchemeStatus = procSubPlanSchemeDao.saveOrUpdateSubPlanScheme(subPlanSchemeBeans);
					if(!subPlanSchemeStatus){
						log.error(subPlanSchemeBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Employee History details");
					}
				}
			}
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteSubPlan(ProcSubPlanForm procSubPlanForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			ProcSubPlanHeaderBean procSubPlanHeaderBean = new ProcSubPlanHeaderBean();
			procSubPlanHeaderBean.setLocationId(procSubPlanForm.getLocationId());
			procSubPlanHeaderBean.setSubPlanId(procSubPlanForm.getSubPlanId());
			//System.out.println("PROC SUB LOCATION ID-"+procSubPlanForm.getLocationId());
			//System.out.println("PROC SUB Plan ID-"+procSubPlanForm.getSubPlanDescription());
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			ProcSubPlanHeaderBean procSubPlanHeaderBean2 = procSubPlanDao.findSubplan(procSubPlanHeaderBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			procSubPlanHeaderBean2.setMisAuditBean(misAuditBean);
			long subPlanId = procSubPlanHeaderBean2.getSubPlanId();
			status  = procSubPlanDao.updateSubPlan(procSubPlanHeaderBean2);
			SubPlanSchemeBean subPlanSchemeBean = new SubPlanSchemeBean();
			subPlanSchemeBean.setSubPlanId(subPlanId);

			if(status){
				List<SubPlanSchemeBean> subPlanSchemeBeans = procSubPlanSchemeDao.findSubPlanScheme(subPlanSchemeBean, statusList);
				if(!MisUtility.ifEmpty(subPlanSchemeBeans)){
					for (SubPlanSchemeBean subPlanSchemeBean2 : subPlanSchemeBeans) {
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
					    subPlanSchemeBean2.setMisAuditBean(misAuditBean);
					}
					boolean subPlanSchemeStatus = procSubPlanSchemeDao.saveOrUpdateSubPlanScheme(subPlanSchemeBeans);
					if(!subPlanSchemeStatus){
						log.error(subPlanSchemeBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed");
					}
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}

		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postSubPlan(ProcSubPlanForm procSubPlanForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			ProcSubPlanHeaderBean procSubPlanHeaderBean = populateProcSubPlanHeaderBean(procSubPlanForm);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			procSubPlanHeaderBean = procSubPlanDao.findSubplan(procSubPlanHeaderBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = procSubPlanHeaderBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			procSubPlanHeaderBean.setMisAuditBean(misAuditBean);
			long subPlanId = procSubPlanHeaderBean.getSubPlanId();
			status  = procSubPlanDao.updateSubPlan(procSubPlanHeaderBean);
			SubPlanSchemeBean subPlanSchemeBean = new SubPlanSchemeBean();
			subPlanSchemeBean.setSubPlanId(subPlanId);

			if(status){
				List<SubPlanSchemeBean> subPlanSchemeBeans = procSubPlanSchemeDao.findSubPlanScheme(subPlanSchemeBean, statusList);
				if(!MisUtility.ifEmpty(subPlanSchemeBeans)){
					for (SubPlanSchemeBean subPlanSchemeBean2 : subPlanSchemeBeans) {
						misAuditBean = subPlanSchemeBean2.getMisAuditBean();
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
						misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
						misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
					    subPlanSchemeBean2.setMisAuditBean(misAuditBean);
					}
					boolean subPlanSchemeStatus = procSubPlanSchemeDao.saveOrUpdateSubPlanScheme(subPlanSchemeBeans);
					if(!subPlanSchemeStatus){
						log.error(subPlanSchemeBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed");
					}
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}

		return status;
	}
	
	@SuppressWarnings({ "unchecked" })
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	private List<SubPlanSchemeBean> populateProcSubPlanSchemeBeans(ProcSubPlanForm procSubPlanForm,long subPlanId, MISSessionBean misSessionBean, String status) throws MISException{
		//System.out.println("1 populateProcSubPlanSchemeBeans:Sub Plan Id-->"+subPlanId);
		MISAuditBean misAuditBean = new MISAuditBean();
		if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		} else{
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		}
		misAuditBean.setStatus(status);

		List<SubPlanSchemeBean> subPlanSchemeBeans = new ArrayList<SubPlanSchemeBean>();

		Datagrid attachedSchemeDatagrid = procSubPlanForm.getAttachedSchemeDatagrid();

		Collection<SubPlanSchemeBean> addedSubPlanSchemeBeanBeans = attachedSchemeDatagrid.getAddedData();
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		
		if(!MisUtility.ifEmpty(addedSubPlanSchemeBeanBeans)){
			for (SubPlanSchemeBean subPlanSchemeBean : addedSubPlanSchemeBeanBeans) {
				List<SubPlanSchemeBean> subPlanSchemeBeanFind=procSubPlanSchemeDao.findSubPlanScheme(subPlanSchemeBean, statusList);
				if(MisUtility.ifEmpty(subPlanSchemeBeanFind)){
					subPlanSchemeBean.setSubPlanId(subPlanId);
					subPlanSchemeBean.setMisAuditBean(misAuditBean);
					subPlanSchemeBeans.add(subPlanSchemeBean);
				}else{
					throw new MISException(MISExceptionCodes.MIS001,subPlanSchemeBeanFind.get(0).getSchemeId()+ ". This scheme is already planned under sub plan id "+subPlanSchemeBeanFind.get(0).getSubPlanId());
				}
			}
		}
	
		
		log.debug(":::::::::::::::::::::BEFORE Update::::::::::****************************************************");
		
		Collection<SubPlanSchemeBean> modifiedSubPlanSchemeBeanBeans = attachedSchemeDatagrid.getModifiedData();
		log.debug(modifiedSubPlanSchemeBeanBeans);
		if(!MisUtility.ifEmpty(modifiedSubPlanSchemeBeanBeans)){
			for (SubPlanSchemeBean subPlanSchemeBean : modifiedSubPlanSchemeBeanBeans) {
				subPlanSchemeBean.setMisAuditBean(misAuditBean);
				subPlanSchemeBeans.add(subPlanSchemeBean);
			}
		}
		
		Collection<SubPlanSchemeBean> deletedSubPlanSchemeBeanBeans = attachedSchemeDatagrid.getDeletedData();
		if(!MisUtility.ifEmpty(deletedSubPlanSchemeBeanBeans)){
			for (SubPlanSchemeBean subPlanSchemeBean : deletedSubPlanSchemeBeanBeans) {
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				subPlanSchemeBean.setMisAuditBean(misAuditBean);
				subPlanSchemeBeans.add(subPlanSchemeBean);
			}
		}
		return subPlanSchemeBeans;
	}
	
	private ProcSubPlanHeaderBean populateProcSubPlanHeaderBean(ProcSubPlanForm procSubPlanForm){
		ProcSubPlanHeaderBean procSubPlanHeaderBean = new ProcSubPlanHeaderBean();
		procSubPlanHeaderBean.setLocationId(procSubPlanForm.getLocationId());
		procSubPlanHeaderBean.setPlanId(procSubPlanForm.getPlanId());
		procSubPlanHeaderBean.setSubPlanDescription(procSubPlanForm.getSubPlanDescription());
		procSubPlanHeaderBean.setSubPlanId(procSubPlanForm.getSubPlanId());
		procSubPlanHeaderBean.setSubPlanName(procSubPlanForm.getSubPlanName());
		return procSubPlanHeaderBean;
	}
}
