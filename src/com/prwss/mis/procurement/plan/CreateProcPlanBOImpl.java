package com.prwss.mis.procurement.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;
import com.prwss.mis.procurement.plan.dao.CreateProcPlanDao;
import com.prwss.mis.procurement.plan.struts.CreateProcPlanForm;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;

public class CreateProcPlanBOImpl implements CreateProcPlanBO {
	
	private CreateProcPlanDao createProcPlanDao;
	private Logger log = Logger.getLogger(CreateProcPlanBOImpl.class);
	private DocumentNumberDAO documentNumberDao;
	
	public void setCreateProcPlanDao(CreateProcPlanDao createProcPlanDao) {
		this.createProcPlanDao = createProcPlanDao;
	}
	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}
	@Override
	public List<CreateProcPlanBean> findCreateProcPlanFrom(
			CreateProcPlanForm createProcPlanForm, List<String> statusList)
			throws MISException {
		List<CreateProcPlanBean> createProcPlanBeans = null;
		try{
			CreateProcPlanBean createProcPlanBean = new CreateProcPlanBean();
			//if(MisUtility.ifEmpty(createProcPlanForm.getPlanId())){
				createProcPlanBean.setPlanId(createProcPlanForm.getPlanId());
				createProcPlanBean.setLocationId(createProcPlanForm.getLocationId());				
				createProcPlanBeans = createProcPlanDao.findCreateProcPlanFrom(createProcPlanBean, statusList);
				System.out.println("BO createProcPlanBeans: "+createProcPlanBeans);
			//}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return createProcPlanBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveCreateProcPlanFrom(
			CreateProcPlanForm createProcPlanForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status = false;
		try {
//			CreateProcPlanBean createProcFindPlanBean = new CreateProcPlanBean();
//			//createProcFindPlanBean.setPlanId(createProcPlanForm.getPlanId().trim());
//			createProcFindPlanBean.setLocationId(createProcPlanForm.getLocationId().trim());
//			List<CreateProcPlanBean> createProcPlanBeans = createProcPlanDao.findCreateProcPlanFrom(createProcFindPlanBean, null);
//			if(!MisUtility.ifEmpty(createProcPlanBeans)){
//				System.out.println("in duplicate exception throwing");
//				throw new MISException(MISExceptionCodes.MIS001, "Procurement Plan Id\t"+createProcPlanForm.getPlanId());			
//			}
			CreateProcPlanBean createProcPlanBean = new CreateProcPlanBean();
			
			StringTokenizer stringTokenizerFrom = new StringTokenizer(createProcPlanForm.getPlanFrom(), "-");
			int fromYear = 0 ;
			int fromMonth = 0;
			int fromDate = 0;
			while(stringTokenizerFrom.hasMoreTokens()){
				fromDate = new Integer(stringTokenizerFrom.nextToken());
				fromMonth = new Integer(stringTokenizerFrom.nextToken()) - 1; // subtracting 1 because Calender.MONTH starts with 0(Jan) 
				fromYear = new Integer(stringTokenizerFrom.nextToken());
			}
			StringTokenizer stringTokenizerTo = new StringTokenizer(createProcPlanForm.getPlanTo(), "-");
			int toYear = 0 ;
			int toMonth = 0;
			int toDate = 0;
			while(stringTokenizerTo.hasMoreTokens()){
				toDate = new Integer(stringTokenizerTo.nextToken());
				toMonth = new Integer(stringTokenizerTo.nextToken()) - 1; // subtracting 1 because Calender.MONTH starts with 0(Jan) 
				toYear = new Integer(stringTokenizerTo.nextToken());
			}
			String planPrefix="";
			if(createProcPlanForm.getPlanType().equals(MISConstants.MIS_TYPE_OF_TENDER_GOODS)){
				planPrefix = "G";
			}
			if(createProcPlanForm.getPlanType().equals(MISConstants.MIS_TYPE_OF_TENDER_WORKS)){
				planPrefix = "W";
			}
			if(createProcPlanForm.getPlanType().equals(MISConstants.MIS_TYPE_OF_TENDER_CONSULTANCY)){
				//planPrefix = "S";
				planPrefix = "C";
			}
			if(createProcPlanForm.getPlanType().equals(MISConstants.MIS_TYPE_OF_TENDER_SERVICES)){
				planPrefix = "NC";
			}
			String planId=createProcPlanForm.getLocationId()+"-"+planPrefix+"-"+fromYear+"-"+toYear+"-"+createProcPlanForm.getProgramId();
			//System.out.println("Plan Id: "+planId);
			createProcPlanBean.setPlanId(planId);
			createProcPlanBean.setLocationId(createProcPlanForm.getLocationId().trim());
			createProcPlanBean.setProgramId(createProcPlanForm.getProgramId().trim());
			createProcPlanBean.setPlanTo(MisUtility.convertStringToDate(createProcPlanForm.getPlanTo()));
			createProcPlanBean.setPlanFrom(MisUtility.convertStringToDate(createProcPlanForm.getPlanFrom()));
			createProcPlanBean.setPlanDescription(createProcPlanForm.getPlanDescription().trim());
			createProcPlanBean.setPlanType(createProcPlanForm.getPlanType().trim());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
			createProcPlanBean.setMisAuditBean(misAuditBean);
			status = createProcPlanDao.saveCreateProcPlanFrom(createProcPlanBean);
			if(status){
				if(!MisUtility.ifEmpty(createProcPlanBean)){
					log.error(createProcPlanBean);
					throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Plan");
				}else{
					createProcPlanForm.setPlanId(planId);
				}
			}
			} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateCreateProcPlanFrom(
			CreateProcPlanForm createProcPlanForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status = false;
		try {
			CreateProcPlanBean createProcPlanBean = populateCreateProcPlanBean(createProcPlanForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
			createProcPlanBean.setMisAuditBean(misAuditBean);
			status = createProcPlanDao.updateCreateProcPlanFrom(createProcPlanBean);
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
	public boolean deleteCreateProcPlanFrom(
			CreateProcPlanForm createProcPlanForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status = false;
		try {
			CreateProcPlanBean createProcPlanBean = populateCreateProcPlanBean(createProcPlanForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED); 
			createProcPlanBean.setMisAuditBean(misAuditBean);
			status = createProcPlanDao.updateCreateProcPlanFrom(createProcPlanBean);
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
	public boolean postCreateProcPlanFrom(
			CreateProcPlanForm createProcPlanForm, MISSessionBean misSessionBean)
			throws MISException {
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status = false;
			CreateProcPlanBean createProcPlanBean = new CreateProcPlanBean();
			createProcPlanBean.setPlanId(createProcPlanForm.getPlanId());
			createProcPlanBean.setLocationId(createProcPlanForm.getLocationId());
			createProcPlanBean= createProcPlanDao.findCreateProcPlanFrom(createProcPlanBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = createProcPlanBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			createProcPlanBean.setMisAuditBean(misAuditBean);			
			status = createProcPlanDao.updateCreateProcPlanFrom(createProcPlanBean);
			if(!status){
				log.error(createProcPlanBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Procurement Plan details");
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
	
	private CreateProcPlanBean populateCreateProcPlanBean(CreateProcPlanForm createProcPlanForm){
		CreateProcPlanBean createProcPlanBean = new CreateProcPlanBean();
		createProcPlanBean.setPlanId(createProcPlanForm.getPlanId().trim());
		createProcPlanBean.setLocationId(createProcPlanForm.getLocationId().trim());
		createProcPlanBean.setProgramId(createProcPlanForm.getProgramId().trim());
		createProcPlanBean.setPlanTo(MisUtility.convertStringToDate(createProcPlanForm.getPlanTo()));
		createProcPlanBean.setPlanFrom(MisUtility.convertStringToDate(createProcPlanForm.getPlanFrom()));
		createProcPlanBean.setPlanDescription(createProcPlanForm.getPlanDescription().trim());
		createProcPlanBean.setPlanType(createProcPlanForm.getPlanType().trim());
		return createProcPlanBean;
		
	}

}
