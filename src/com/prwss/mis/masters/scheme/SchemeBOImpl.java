package com.prwss.mis.masters.scheme;

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
import com.prwss.mis.masters.scheme.dao.SchemeBean;
import com.prwss.mis.masters.scheme.dao.SchemeDao;
import com.prwss.mis.masters.scheme.struts.SchemeForm;
import com.prwss.mis.masters.scheme.struts.SchemeVillageCommisionForm;

public class SchemeBOImpl implements SchemeBO {
	
	private Logger log = Logger.getLogger(SchemeBOImpl.class);
	private SchemeDao schemeDao;
	

	public void setSchemeDao(SchemeDao schemeDao) {
		this.schemeDao = schemeDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean saveScheme(SchemeForm schemeForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			SchemeBean schemeBean = populateSchemeBean(schemeForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			schemeBean.setMisAuditBean(misAuditBean);

			status = schemeDao.saveScheme(schemeBean);



			if(!status){
				log.error(schemeBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to save Scheme details");
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
	public boolean updateScheme(SchemeForm schemeForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			SchemeBean schemeBean = populateSchemeBean(schemeForm);
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			schemeBean.setMisAuditBean(misAuditBean);
			System.out.println("value of scheme bean "+schemeBean.toString());
			System.out.println("value of scheme bean "+schemeBean.getSchemeUpgraded());
			
			status = schemeDao.saveOrUpdateScheme(schemeBean);



			if(!status){
				log.error(schemeBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to update Scheme details");
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
	public boolean deleteScheme(SchemeForm schemeForm,
			MISSessionBean misSessionBean) throws MISException {
		try {
			boolean status = false;
			SchemeBean schemeBean = populateSchemeBean(schemeForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			schemeBean.setMisAuditBean(misAuditBean);

			status = schemeDao.saveOrUpdateScheme(schemeBean);



			if(!status){
				log.error(schemeBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to delete Scheme details");
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
	public boolean postScheme(SchemeForm schemeForm,
			MISSessionBean misSessionBean) throws MISException {
try {
			
			
			
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			boolean status = false;
			SchemeBean schemeBean= new SchemeBean();
			schemeBean.setSchemeId(schemeForm.getSchemeId());
			schemeBean.setSchemeUpgraded(schemeForm.getSchemeStatus());
			schemeBean= schemeDao.findScheme(schemeBean,statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = schemeBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			schemeBean.setMisAuditBean(misAuditBean);			
		
			
			status = schemeDao.saveOrUpdateScheme(schemeBean);

			if(!status){
				log.error(schemeBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Scheme details");
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

	
private SchemeBean populateSchemeBean(SchemeForm schemeForm){
		
	
		
	SchemeBean schemeBean = new SchemeBean();
		try {
				 
			schemeBean.setDesignInvestigationDate(MisUtility.convertStringToDate(schemeForm.getDesignInvestigationDate()));
			schemeBean.setAdminAppGoPAmount(schemeForm.getAdminAppGoPAmount());
			schemeBean.setAdminAppGoPLetterDate(MisUtility.convertStringToDate(schemeForm.getAdminAppGoPLetterDate()));	

			schemeBean.setAdminAppGoPLetterNo(schemeForm.getAdminAppGoPLetterNo());

			schemeBean.setAdminAppLyingWithEeAmount(schemeForm.getAdminAppLyingWithEeAmount());

			
			schemeBean.setAdminAppSentCeVideSeAmount(schemeForm.getAdminAppSentCeVideSeAmount());

			schemeBean.setAdminAppSentCeVideSeDate(MisUtility.convertStringToDate(schemeForm.getAdminAppSentCeVideSeDate()));
			schemeBean.setAdminAppSentCeVideSeNo(schemeForm.getAdminAppSentCeVideSeNo());


			schemeBean.setAdminAppSentGovtVideCeAmount(schemeForm.getAdminAppSentGovtVideCeAmount());

			schemeBean.setAdminAppSentGovtVideCeDate(MisUtility.convertStringToDate(schemeForm.getAdminAppSentGovtVideCeDate()));

			schemeBean.setAdminAppSentGovtVideCeNo(schemeForm.getAdminAppSentGovtVideCeNo());			


			schemeBean.setAdminAppSentSeVideEeAmount(schemeForm.getAdminAppSentSeVideEeAmount());


			schemeBean.setAdminAppSentSeVideEeDate(MisUtility.convertStringToDate(schemeForm.getAdminAppSentSeVideEeDate()));

			schemeBean.setAdminAppSentSeVideEeNo(schemeForm.getAdminAppSentSeVideEeNo());

			schemeBean.setConstitutionDateSLC(MisUtility.convertStringToDate(schemeForm.getConstitutionDateSLC()));

			schemeBean.setMouSigned(schemeForm.getMouSigned());

			schemeBean.setResolutionDate(MisUtility.convertStringToDate(schemeForm.getResolutionDate()));

			schemeBean.setSchemeId(schemeForm.getSchemeId());

			schemeBean.setSchemeSource(schemeForm.getSchemeSource());

			schemeBean.setTechAppCeAmount(schemeForm.getTechAppCeAmount());

			schemeBean.setTechAppCeLetterDate(MisUtility.convertStringToDate(schemeForm.getTechAppCeLetterDate()));


			schemeBean.setTechAppCeLetterNo(schemeForm.getTechAppCeLetterNo());

			schemeBean.setTechAppEeAmount(schemeForm.getTechAppEeAmount());

			schemeBean.setTechAppEeDate(MisUtility.convertStringToDate(schemeForm.getTechAppEeDate()));

			schemeBean.setTechAppEeLetterNo(schemeForm.getTechAppEeLetterNo());


			schemeBean.setTechAppLyingWithEeAmount(schemeForm.getTechAppLyingWithEeAmount());

			schemeBean.setTechAppSeAmount(schemeForm.getTechAppSeAmount());

			schemeBean.setTechAppSeLetterDate(MisUtility.convertStringToDate(schemeForm.getTechAppSeLetterDate()));

			schemeBean.setTechAppSeLetterNo(schemeForm.getTechAppSeLetterNo());

			schemeBean.setTechAppSentToCeVideSeAmount(schemeForm.getTechAppSentToCeVideSeAmount());
			

			schemeBean.setTechAppSentToCeVideSeDate(MisUtility.convertStringToDate(schemeForm.getTechAppSentToCeVideSeDate()));
			schemeBean.setTechAppSentToCeVideSeNo(schemeForm.getTechAppSentToCeVideSeNo());

			schemeBean.setTechAppSentToSeVideEeAmount(schemeForm.getTechAppSentToSeVideEeAmount());

			schemeBean.setTechAppSentToSeVideEeDate(MisUtility.convertStringToDate(schemeForm.getTechAppSentToSeVideEeDate()));

			schemeBean.setTechAppSentToSeVideEeNo(schemeForm.getTechAppSentToSeVideEeNo());
			
			schemeBean.setBeneficiaryByCommunity(schemeForm.getBeneficiaryByCommunity());
			schemeBean.setBeneficiaryShareDue(schemeForm.getBeneficiaryShareDue());
			schemeBean.setBeneficiaryShareNonBudgGp(schemeForm.getBeneficiaryShareNonBudgGp());
			schemeBean.setBeneficiaryShareStateGovtGrant(schemeForm.getBeneficiaryShareStateGovtGrant());
			schemeBean.setBeneficiaryShareUntiedDistrict(schemeForm.getBeneficiaryShareUntiedDistrict());
			schemeBean.setBeneficiaryShareVoluntarily(schemeForm.getBeneficiaryShareVoluntarily());
			schemeBean.setShareLessThanUpperLimit(schemeForm.isShareLessThanUpperLimit());
			schemeBean.setSchemeCommisionedDate(MisUtility.convertStringToDate(schemeForm.getSchemeCommisionedDate()));
			schemeBean.setSchemeCompleted(schemeForm.isSchemeCompleted());
			schemeBean.setSchemeCompletedDate(MisUtility.convertStringToDate(schemeForm.getSchemeCompletedDate()));
			
			schemeBean.setDigitalSurveyDate(MisUtility.convertStringToDate(schemeForm.getDigitalSurveyDate()));
			schemeBean.setDigitalSurveyCompleted(schemeForm.isDigitalSurveyCompleted());
			schemeBean.setSchemeUpgraded(schemeForm.getSchemeStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return schemeBean;
	}


@Override
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public List<SchemeBean> findScheme(SchemeForm schemeForm,
		MISSessionBean misSessionBean, List<String> statusList)
		throws MISException {
	List<SchemeBean> schemeBeans  = new ArrayList<SchemeBean>();
	
	try {
		SchemeBean schemeBean = new SchemeBean();
		if(MisUtility.ifEmpty(schemeForm.getSchemeId())){
		schemeBean.setSchemeId(schemeForm.getSchemeId());	
		schemeBean.setSchemeUpgraded(schemeForm.getSchemeStatus());
		schemeBeans = schemeDao.findScheme(schemeBean, statusList);
		}
} catch (DataAccessException e) {
	log.error(e.getLocalizedMessage(),e);
	throw e;
}
return schemeBeans;
}


/*//rohit
@Override
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public List<SchemeBean> findAllData(SchemeVillageCommisionForm schemeVillageCommisionForm)
		throws MISException {
	System.out.println("=============BO==========");
	List<SchemeBean> schemeBeans  = new ArrayList<SchemeBean>();
	
	try {
		SchemeBean schemeBean = new SchemeBean();
		
		if((MisUtility.ifEmpty(schemeVillageCommisionForm.getSchemeCode()))){
			schemeBean.setSchemeId(schemeVillageCommisionForm.getSchemeCode());
			schemeBeans = schemeDao.findAllData(schemeBean);
			
		}
} catch (DataAccessException e) {
	log.error(e.getLocalizedMessage(),e);
	throw e;
}
return schemeBeans;
}*/




}


	


