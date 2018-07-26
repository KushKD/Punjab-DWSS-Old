package com.prwss.mis.masters.scheme;

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
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.scheme.dao.SchemeBean;
import com.prwss.mis.masters.scheme.dao.SchemeDao;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderBean;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderDao;
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;
import com.prwss.mis.masters.scheme.dao.SchemeVillageDao;
import com.prwss.mis.masters.scheme.struts.ExtentionSchemeMasterForm;

public class ExtentionSchemeMasterBOImpl implements ExtentionSchemeMasterBO {
	
	private LocationDao locationDao;
	private SchemeHeaderDao schemeHeaderDao;
	private SchemeVillageDao schemeVillageDao;
	private SchemeDao schemeDao;
	 
	
	public void setSchemeDao(SchemeDao schemeDao) {
		this.schemeDao = schemeDao;
	}
	public void setSchemeVillageDao(SchemeVillageDao schemeVillageDao) {
		this.schemeVillageDao = schemeVillageDao;
	}
	public void setSchemeHeaderDao(SchemeHeaderDao schemeHeaderDao) {
		this.schemeHeaderDao = schemeHeaderDao;
	}
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	
	private Logger log = Logger.getLogger(ExtentionSchemeMasterBOImpl.class);
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateSchemeMaster(ExtentionSchemeMasterForm extentionSchemeMasterForm,MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		List<SchemeVillageBean> villageBeans = new ArrayList<SchemeVillageBean>();
		
		String[] str1 = extentionSchemeMasterForm.getSchemeCode().split("-");
	
		try {
			List<SchemeHeaderBean> schemeHeaderBeans = null;
			SchemeHeaderBean schemeHeaderBean2 = new SchemeHeaderBean();
			schemeHeaderBean2.setSchemeId(str1[0]);
			schemeHeaderBean2.setLocationId(extentionSchemeMasterForm.getLocationId());
			String extention = null;
			SchemeHeaderBean schemeHeaderBean = null;
			List<String> statusList = new ArrayList<String>();
			//statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			//statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			System.out.println("in extension scheme master=====");
			schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean2, statusList);
			long totalScheme = 0;
			int extentionScheme = schemeHeaderBeans.size();
			if(extentionScheme==1){
				extention = "ext";
			}else{
				for(int i=1;i<extentionScheme;i++){
					totalScheme = i ;
				}
				extention = "ext"+totalScheme;
				}
			
				schemeHeaderBean = new SchemeHeaderBean();
				String[] str = extentionSchemeMasterForm.getSchemeCode().split("-");
				schemeHeaderBean.setSchemeId(str[0]);
				schemeHeaderBean.setSchemeName(extentionSchemeMasterForm.getSchemeName());
				schemeHeaderBean.setProgId(extentionSchemeMasterForm.getProgramId());			
				schemeHeaderBean.setLocationId(extentionSchemeMasterForm.getLocationId());
				schemeHeaderBean.setWaterWorksExistingNew("EXISTING");
				schemeHeaderBean.setSchemeSource(extentionSchemeMasterForm.getSchemeSource());
				schemeHeaderBean.setSchemeUpgraded(extention);
				//schemeHeaderBean.setRefSchemeId(schemeHeaderVillageForm.getRefSchemeCode());
				schemeHeaderBean.setWatersupply(extentionSchemeMasterForm.getWatersupply());			
			
			// System.out.println("111111111111111"+schemeHeaderBean);
			// This code sinpet is written to enter district id which is fetch from  user location
			/* ****************************************************************************** */
			LocationBean locationBean = new LocationBean();
			locationBean.setLocationId(extentionSchemeMasterForm.getLocationId());
			locationBean = locationDao.getLocation(locationBean);
			schemeHeaderBean.setDistrictId(locationBean.getParentLocation());
			/* ****************************************************************************** */
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			schemeHeaderBean.setMisAuditBean(misAuditBean);
			status = schemeHeaderDao.saveOrUpdateSchemeHeader(schemeHeaderBean);
				if(status){								
					List<SchemeVillageBean> schemeVillageBeans = populateSchemeVillageBeans(extentionSchemeMasterForm,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
					for(SchemeVillageBean villageBean:schemeVillageBeans){
					/*	String extention1 = null;
						SchemeVillageBean villageBean2=new SchemeVillageBean();
						List<SchemeVillageBean> beans = null;
						List<String> statusList1 = new ArrayList<String>();
						beans  = schemeVillageDao.findSchemeVillage(villageBean, statusList1);
						long totalScheme1 = 0;
						long extentionScheme1 = beans.size();
						if(extentionScheme1!=0){
							if(extentionScheme1==1){
								extention1 = "ext";
								//System.out.println("eeeeeeeee"+extention1);							 
							}else{
								for(int i = 0;i<extentionScheme1;i++){
									totalScheme1 = i ;
								}
								extention1 = "ext" + totalScheme1;
								//System.out.println("eeeeeeeee"+extention1);							
							}
							villageBean2.setSchemeUpgraded(extention1);
							villageBean2.setSchemeId(villageBean.getSchemeId());
							villageBean2.setVillageId(villageBean.getVillageId());
							villageBean2.setVillageName(villageBean.getVillageName());
							villageBean2.setMisAuditBean(villageBean.getMisAuditBean());
							villageBean2.setSupplyServiceLevel(villageBean.getSupplyServiceLevel());				
								boolean schemeVillageStatus = schemeVillageDao.updateSchemeVillage(villageBean2);
								if(!schemeVillageStatus){
									throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Scheme Village details");
								}											
						
						}
						else{*/
									villageBean.setSchemeUpgraded(extention);	
							boolean schemeVillageStatus = schemeVillageDao.updateSchemeVillage(villageBean);
							if(!schemeVillageStatus){
								throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Scheme Village details");
							}											
					}
				}
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}
	
	
private SchemeHeaderBean populateSchemeHeaderBean(ExtentionSchemeMasterForm extentionSchemeMasterForm, String extention){
		
		SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
		String[] str1 = extentionSchemeMasterForm.getSchemeCode().split("-");
		try {
			schemeHeaderBean.setSchemeId(str1[0]);
			schemeHeaderBean.setSchemeName(extentionSchemeMasterForm.getSchemeName());
			schemeHeaderBean.setProgId(extentionSchemeMasterForm.getProgramId());
			schemeHeaderBean.setLocationId(extentionSchemeMasterForm.getLocationId());
			schemeHeaderBean.setSchemeSource(extentionSchemeMasterForm.getSchemeSource());
			 schemeHeaderBean.setSchemeUpgraded(extention);
			//schemeHeaderBean.setRefSchemeId(schemeHeaderVillageForm.getRefSchemeCode());
			schemeHeaderBean.setWatersupply(extentionSchemeMasterForm.getWatersupply());
			schemeHeaderBean.setWaterWorksExistingNew("extended");
			//System.out.println("innn tryyyyyyyyyyyyy");
		} catch (Exception e) {
			//System.out.println("innn catchhhh");
			e.printStackTrace();
			log.error(e);
		}
		return schemeHeaderBean;
		
	}
	
@SuppressWarnings("unchecked")
private List<SchemeVillageBean> populateSchemeVillageBeans(ExtentionSchemeMasterForm schemeHeaderVillageForm, MISSessionBean misSessionBean, String status){
	List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
	MISAuditBean misAuditBean1 = new MISAuditBean();
	if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
		misAuditBean1.setAuthBy(misSessionBean.getEnteredBy());
		misAuditBean1.setAuthDate(misSessionBean.getEnteredDate());
	} else{
		misAuditBean1.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean1.setEntDate(misSessionBean.getEnteredDate());
	}
	misAuditBean1.setStatus(status);
	
	MISAuditBean misAuditBean2 = new MISAuditBean();
	if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
		misAuditBean2.setAuthBy(misSessionBean.getEnteredBy());
		misAuditBean2.setAuthDate(misSessionBean.getEnteredDate());
	} else{
		misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
	}
	misAuditBean2.setStatus(status);
	MISAuditBean misAuditBean3 = new MISAuditBean();
	if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
		misAuditBean3.setAuthBy(misSessionBean.getEnteredBy());
		misAuditBean3.setAuthDate(misSessionBean.getEnteredDate());
	} else{
		misAuditBean3.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean3.setEntDate(misSessionBean.getEnteredDate());
	}
	misAuditBean3.setStatus(status);
	String str[] = schemeHeaderVillageForm.getSchemeCode().split("-");
	String schemeCode = str[0];
	
	Collection<SchemeVillageBean> addedSchemeVillageBeans = schemeHeaderVillageForm.getVillageDatagrid().getAddedData();
	if(!MisUtility.ifEmpty(addedSchemeVillageBeans)){
		for (SchemeVillageBean schemeVillageBean : addedSchemeVillageBeans) {
			schemeVillageBean.setSchemeId(schemeCode);
			schemeVillageBean.setMisAuditBean(misAuditBean1);
			if(MisUtility.ifEmpty(schemeVillageBean.getVillageCategory()))
			schemeVillageBeans.add(schemeVillageBean);
			
		}
	}
	
	Collection<SchemeVillageBean> modifiedSchemeVillageBeans = schemeHeaderVillageForm.getVillageDatagrid().getModifiedData();
	
	if(!MisUtility.ifEmpty(modifiedSchemeVillageBeans)){
		for (SchemeVillageBean schemeVillageBean : modifiedSchemeVillageBeans) {
			schemeVillageBean.setSchemeId(schemeCode);
			schemeVillageBean.setMisAuditBean(misAuditBean2);
			schemeVillageBean.setSchemeCommissionedDate(null);
			if(MisUtility.ifEmpty(schemeVillageBean.getVillageCategory()))
			schemeVillageBeans.add(schemeVillageBean);
			
		}
	}
	for (SchemeVillageBean schemeVillageBean : schemeVillageBeans) {
//		System.out.println("1.HASH CODE of Scheme Village"+schemeVillageBean.hashCode());
	}
//	System.out.println("outside delete for loop------------------"+schemeVillageBeans);
	Collection<SchemeVillageBean> deletedSchemeVillageBeans = schemeHeaderVillageForm.getVillageDatagrid().getDeletedData();
//	System.out.println("Deleted------------------"+deletedSchemeVillageBeans);
	if(!MisUtility.ifEmpty(deletedSchemeVillageBeans)){
		for (SchemeVillageBean schemeVillageBean : deletedSchemeVillageBeans) {
			schemeVillageBean.setSchemeId(schemeHeaderVillageForm.getSchemeCode());
			misAuditBean3.setStatus(MISConstants.MASTER_STATUS_DELETED);
//			System.out.println(" 1 Scheme Village Beans -----"+schemeVillageBean+":"+schemeVillageBean.hashCode());
			schemeVillageBean.setMisAuditBean(misAuditBean3);
//			System.out.println(" 2 Scheme Village Beans -----"+schemeVillageBean+":"+schemeVillageBean.hashCode());
			schemeVillageBeans.add(schemeVillageBean);
//			System.out.println("from deleted for loop------------------"+schemeVillageBeans);
		}
	}
	return schemeVillageBeans;
}

@Override
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public boolean postSchemeMaster(
		ExtentionSchemeMasterForm extentionSchemeMasterForm,
		MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
	
	try {
		String schemeUpgraded = null;
		List <String> statusList = new ArrayList<String>() ;
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		SchemeHeaderBean headerBean = new SchemeHeaderBean();
		String[] str = extentionSchemeMasterForm.getSchemeCode().split("-");
		headerBean.setSchemeId(str[0]);
		headerBean.setSchemeUpgraded(str[1]);
		
		headerBean = schemeHeaderDao.findSchemeHeader(headerBean, statusList).get(0);
//		System.out.println("++++++++++++++++="+headerBean);
//  	System.out.println("SchemeUpgraded"+headerBean.getSchemeUpgraded());
		schemeUpgraded = headerBean.getSchemeUpgraded();
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean = headerBean.getMisAuditBean();
		misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
		misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
		headerBean.setMisAuditBean(misAuditBean);
//		System.out.println("++++++++++++++++="+headerBean);
	
		
		status = schemeHeaderDao.saveOrUpdateSchemeHeader(headerBean);
		
		SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
		schemeVillageBean.setSchemeId(str[0]);
		schemeVillageBean.setSchemeUpgraded(str[1]);
//		
			if(status){
				List<SchemeVillageBean> schemeVillageBeans = schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
				
				for (SchemeVillageBean schemeVillageBean2 : schemeVillageBeans) {
					misAuditBean = schemeVillageBean2.getMisAuditBean();
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
					misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
					misAuditBean.setAuthDate((misSessionBean.getEnteredDate()));
					schemeVillageBean2.setMisAuditBean(misAuditBean);
					
				}
//
				if(!MisUtility.ifEmpty(schemeVillageBeans)){
					if(!schemeVillageDao.saveSchemeVillage(schemeVillageBeans)){
						System.out.println("Enter in mst_scheme_village A");
						throw new MISException(MISExceptionCodes.MIS003, "Scheme Details  not approved for the Scheme Id : "+extentionSchemeMasterForm.getSchemeCode());
					}else{
						SchemeBean schemeBean = new SchemeBean();
						refreshSchemeForm(schemeBean,schemeUpgraded);
						schemeBean.setSchemeId(str[0]);
						schemeBean.setSchemeUpgraded(str[1]);
						List<SchemeBean>schemeBeans = schemeDao.findScheme(schemeBean, statusList); //To check wether scheme code already present
						if(MisUtility.ifEmpty(schemeBeans)){
							System.out.println("Enter in mst_scheme_details U");
						MISAuditBean misAuditBean2 = new MISAuditBean();
						misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
						misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
						misAuditBean2.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
						schemeBean.setMisAuditBean(misAuditBean2);
						schemeDao.saveScheme(schemeBean);
						}
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
	return status;
}

private void refreshSchemeForm(SchemeBean schemeBean, String schemeUpgraded){
	System.out.println("refresh form");
	schemeBean.setAdminAppGoPAmount(new BigDecimal(0.00));
	schemeBean.setAdminAppGoPLetterDate(null);	

	schemeBean.setAdminAppGoPLetterNo(null);

	schemeBean.setAdminAppLyingWithEeAmount(new BigDecimal(0.00));


	schemeBean.setAdminAppSentCeVideSeAmount(new BigDecimal(0.00));

	schemeBean.setAdminAppSentCeVideSeDate(null);
	schemeBean.setAdminAppSentCeVideSeNo(null);


	schemeBean.setAdminAppSentGovtVideCeAmount(new BigDecimal(0.00));

	schemeBean.setAdminAppSentGovtVideCeDate(null);

	schemeBean.setAdminAppSentGovtVideCeNo(null);			


	schemeBean.setAdminAppSentSeVideEeAmount(new BigDecimal(0.00));
	
	
	
	//new
	schemeBean.setTechAppSentToSeVideEeAmount(new BigDecimal(0.00));
	


	schemeBean.setAdminAppSentSeVideEeDate(null);

	schemeBean.setAdminAppSentSeVideEeNo(null);

	schemeBean.setConstitutionDateSLC(null);

	schemeBean.setMouSigned(null);

	schemeBean.setResolutionDate(null);

	schemeBean.setSchemeCommisionedDate(null);
	schemeBean.setSchemeCompletedDate(null);

	schemeBean.setSchemeId(null);

	schemeBean.setSchemeSource(null);

	schemeBean.setTechAppCeAmount(new BigDecimal(0.00));

	schemeBean.setTechAppCeLetterDate(null);


	schemeBean.setTechAppCeLetterNo(null);

	schemeBean.setTechAppEeAmount(new BigDecimal(0.00));

	schemeBean.setTechAppEeDate(null);

	schemeBean.setTechAppEeLetterNo(null);


	schemeBean.setTechAppLyingWithEeAmount(new BigDecimal(0.00));

	schemeBean.setTechAppSeAmount(new BigDecimal(0.00));

	schemeBean.setTechAppSeLetterDate(null);

	schemeBean.setTechAppSeLetterNo(null);


	schemeBean.setTechAppSentToCeVideSeAmount(new BigDecimal(0.00));


	schemeBean.setTechAppSentToCeVideSeDate(null);
	schemeBean.setTechAppSentToCeVideSeNo(null);

	schemeBean.setTechAppSentToSeVideEeAmount(new BigDecimal(0.00));

	schemeBean.setTechAppSentToSeVideEeDate(null);

	schemeBean.setTechAppSentToSeVideEeNo(null);
	
	schemeBean.setBeneficiaryByCommunity(new BigDecimal(0.00));
	schemeBean.setBeneficiaryShareDue(new BigDecimal(0.00));
	schemeBean.setBeneficiaryShareNonBudgGp(new BigDecimal(0.00));
	schemeBean.setBeneficiaryShareStateGovtGrant(new BigDecimal(0.00));
	schemeBean.setBeneficiaryShareUntiedDistrict(new BigDecimal(0.00));
	schemeBean.setBeneficiaryShareVoluntarily(new BigDecimal(0.00));
	schemeBean.setShareLessThanUpperLimit(false);
	schemeBean.setSchemeCompleted(false);
	schemeBean.setSchemeUpgraded(schemeUpgraded);
	
}
}
