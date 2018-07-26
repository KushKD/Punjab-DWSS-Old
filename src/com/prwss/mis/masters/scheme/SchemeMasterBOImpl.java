 /**
 * 
 */
package com.prwss.mis.masters.scheme;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import com.prwss.mis.masters.scheme.struts.SchemeHeaderVillageForm;
import com.prwss.mis.masters.scheme.struts.SchemeVillageCommisionForm;
import com.prwss.mis.masters.scheme.struts.SchemeVillageCommissioningVillageGridBean;
import com.prwss.mis.pmm.operatingarrangement.OperatingArrangementForm;
import com.prwss.mis.pmm.operatingarrangement.VillageOperatingGridBean;
import com.prwss.mis.pmm.village.ncpcstatus.dao.VillageNCPCStatusBean;
import com.prwss.mis.pmm.village.ncpcstatus.dao.VillageNCPCStatusDao;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;

/**
 * @author pjha
 *
 */
public class SchemeMasterBOImpl implements SchemeMasterBO {

private Logger log = Logger.getLogger(SchemeMasterBOImpl.class);
	
	private SchemeHeaderDao schemeHeaderDao;
	private SchemeDao schemeDao;
	private DocumentNumberDAO documentNumberDao;
	private LocationDao locationDao;
	private VillageNCPCStatusDao villageNCPCStatusDao;
	
	int count=0;
	int count1=0;
	int count3=0;
	String date1;
	
	
	public void setVillageNCPCStatusDao(VillageNCPCStatusDao villageNCPCStatusDao) {
		this.villageNCPCStatusDao = villageNCPCStatusDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}

	public SchemeDao getSchemeDao() {
		return schemeDao;
	}

	public void setSchemeDao(SchemeDao schemeDao) {
		this.schemeDao = schemeDao;
	}

	public SchemeHeaderDao getSchemeHeaderDao() {
		return schemeHeaderDao;
	}

	public void setSchemeHeaderDao(SchemeHeaderDao schemeHeaderDao) {
		this.schemeHeaderDao = schemeHeaderDao;
	}

	public SchemeVillageDao getSchemeVillageDao() {
		return schemeVillageDao;
	}

	public void setSchemeVillageDao(SchemeVillageDao schemeVillageDao) {
		this.schemeVillageDao = schemeVillageDao;
	}

	private SchemeVillageDao schemeVillageDao;

	
	@Override
	public SchemeMasterBean findSchemeMaster(
			SchemeHeaderVillageForm schemeHeaderVillageForm,
			List<String> statusList) throws MISException {
		
		SchemeMasterBean schemeMasterBean = null;
		List <SchemeHeaderBean> schemeHeaderBeans = new ArrayList<SchemeHeaderBean>();
		
		List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
		try {
			
			SchemeHeaderBean  schemeHeaderBean =new SchemeHeaderBean();
//			if((MisUtility.ifEmpty(schemeHeaderVillageForm.getSchemeCode()))&& MisUtility.ifEmpty(schemeHeaderVillageForm.getLocationId())){
			schemeHeaderBean.setSchemeId(schemeHeaderVillageForm.getSchemeCode());
			//if(MisUtility.ifEmpty(schemeHeaderVillageForm.getLocationId()))
			schemeHeaderBean.setLocationId(schemeHeaderVillageForm.getLocationId());
			schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
			/*for(SchemeHeaderBean schemeHeaderbean:schemeHeaderBeans){
				if(schemeHeaderbean.getWatersupply()=="wm"||schemeHeaderbean.getWatersupply().equals("wm")){
					schemeHeaderbean.setSchemeSource(MISConstants.VILLAGE_WATER_SOURCE_WATER_METER);
					schemeHeaderbean.setWatersupply("wm");
				}
				
			}*/
			SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
			schemeVillageBean.setSchemeId(schemeHeaderVillageForm.getSchemeCode());
			
			if(!MisUtility.ifEmpty(schemeHeaderBeans)){
				schemeMasterBean = new SchemeMasterBean(); 
				schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
			}
//			}else{
			// throw new
			// MISException(MISExceptionCodes.MIS004,"No Scheme Id or Scheme Name and Location Provided");
//			}
			
			schemeMasterBean.setSchemeHeaderBeans(schemeHeaderBeans);
			schemeMasterBean.setSchemeVillageBeans(schemeVillageBeans);
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return schemeMasterBean;
	}
	
	
	@Override
	public SchemeMasterBean findSchemeMasterForExtendedScheme(
			ExtentionSchemeMasterForm extentionSchemeMasterForm,
			List<String> statusList) throws MISException {
		System.out.println("in upgradid scheme in bo ");
		SchemeMasterBean schemeMasterBean = null;
		List <SchemeHeaderBean> schemeHeaderBeans = new ArrayList<SchemeHeaderBean>();
		
		List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
		try {
			System.out.println("inside metod ");
			statusList.add("A");
			SchemeHeaderBean  schemeHeaderBean =new SchemeHeaderBean();
//			if((MisUtility.ifEmpty(schemeHeaderVillageForm.getSchemeCode()))&& MisUtility.ifEmpty(schemeHeaderVillageForm.getLocationId())){
			String value = extentionSchemeMasterForm.getSchemeCode();
			String[] str = value.split("-");
			
			schemeHeaderBean.setSchemeId(str[0]);
			
		 
			schemeHeaderBean.setLocationId(extentionSchemeMasterForm.getLocationId());
		
			schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
			
			SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
			schemeVillageBean.setSchemeId(str[0]);
			schemeVillageBean.setSchemeUpgraded(str[1]);
			schemeMasterBean = new SchemeMasterBean(); 
			
			if(!MisUtility.ifEmpty(schemeHeaderBeans)){
					schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
				
			}
//			}else{
//				throw new MISException(MISExceptionCodes.MIS004,"No Scheme Id or Scheme Name and Location Provided");
//			}
			
			schemeMasterBean.setSchemeHeaderBeans(schemeHeaderBeans);
			schemeMasterBean.setSchemeVillageBeans(schemeVillageBeans);
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return schemeMasterBean;
	}

	@Override
	public SchemeMasterBean findSchemeMasterForExtendedScheme1(
			ExtentionSchemeMasterForm extentionSchemeMasterForm,
			List<String> statusList) throws MISException {
		
		SchemeMasterBean schemeMasterBean = null;
		List <SchemeHeaderBean> schemeHeaderBeans = new ArrayList<SchemeHeaderBean>();
		
		List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
		try {
			//System.out.println("inside metod ");
			statusList.add("A");
			SchemeHeaderBean  schemeHeaderBean =new SchemeHeaderBean();
//			if((MisUtility.ifEmpty(schemeHeaderVillageForm.getSchemeCode()))&& MisUtility.ifEmpty(schemeHeaderVillageForm.getLocationId())){
			
			schemeHeaderBean.setSchemeId(extentionSchemeMasterForm.getSchemeCode());
			
			schemeHeaderBean.setSchemeUpgraded(extentionSchemeMasterForm.getSchemeUpgraded());
			
		 
			schemeHeaderBean.setLocationId(extentionSchemeMasterForm.getLocationId());
			//schemeHeaderBean.setSchemeUpgraded(extentionSchemeMasterForm.getSchemeUpgraded());
			schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
			
			
			
			SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
			
			schemeVillageBean.setSchemeId(extentionSchemeMasterForm.getSchemeCode());
			
			
			
			if(!MisUtility.ifEmpty(schemeHeaderBeans)){
				schemeMasterBean = new SchemeMasterBean(); 
				String extention=schemeHeaderBeans.get(0).getSchemeUpgraded();
				if(extention.equals("NO")){
					String arr[]={"NO"};
					schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
				}
				else if(extention.equals("ext")){
					String arr[]={"NO","ext"};
					schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
				}
				else if(extention.equals("ext1"))
				{
					String arr[]={"NO","ext","ext1"};
					schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
				}
				else if(extention.equals("ext2")){
					String arr[]={"NO","ext","ext1","ext2"};
					schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
				}
				else if(extention.equals("ext3")){
					String arr[]={"NO","ext","ext1","ext2","ext3"};
					schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
				}
				else if(extention.equals("ext4")){
					String arr[]={"NO","ext","ext1","ext2","ext3","ext4"};
					schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
				}
				else if(extention.equals("ext5")){
					String arr[]={"NO","ext","ext1","ext2","ext3","ext4","ext5"};
					schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
				}
				else{
					schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
				}
			}
//			}else{
//				throw new MISException(MISExceptionCodes.MIS004,"No Scheme Id or Scheme Name and Location Provided");
//			}
			
			schemeMasterBean.setSchemeHeaderBeans(schemeHeaderBeans);
			schemeMasterBean.setSchemeVillageBeans(schemeVillageBeans);
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return schemeMasterBean;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String saveSchemeMaster(SchemeHeaderVillageForm schemeHeaderVillageForm,MISSessionBean misSessionBean) throws MISException {
		String prefix = null;
		String schemeId = null;
		HttpServletRequest request=null;
		SchemeVillageBean schemeVillageBean=new SchemeVillageBean();
		
		System.out.println(schemeHeaderVillageForm);
		try {
			boolean status = false;
			SchemeHeaderBean schemeHeaderBean = populateSchemeHeaderBean(schemeHeaderVillageForm);
			
			
			
			// This code sinpet is written to enter district id which is fetch from  user location
			/* ****************************************************************************** */
			LocationBean locationBean = new LocationBean();
			System.out.println("-----schemeHeaderVillageForm.getLocationId()------"+schemeHeaderVillageForm.getLocationId());
			locationBean.setLocationId(schemeHeaderVillageForm.getLocationId());
			locationBean = locationDao.getLocation(locationBean);
			schemeHeaderBean.setDistrictId(locationBean.getParentLocation());
			schemeHeaderBean.setSubDivisionId(schemeHeaderVillageForm.getSubDivisionId());
			
			System.out.println("schemeHeaderVillageForm.getSubdivisionId()----------------"+schemeHeaderVillageForm.getSubDivisionId());
			/* ****************************************************************************** */
			
			System.out.println("water suply in bo==="+schemeHeaderVillageForm.getWatersupply());
			System.out.println("scheme source in bo==="+schemeHeaderVillageForm.getSchemeSource());
			
			if(schemeHeaderBean.getSchemeSource().equals("WATERMETER")){
				schemeHeaderBean.setWaterWorksExistingNew(MISConstants.SCHEME_TYPE_EXISTING);
				schemeHeaderBean.setWatersupply("wm");
				schemeHeaderBean.setSchemeSource("IMPROVEMENT");
				}
			if(schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_SEWERAGE)){
				schemeVillageBean.setWaterWorksLocation(false);
			}
			if(schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_HAND_PUMP)){
				schemeVillageBean.setWaterWorksLocation(false);
			}
			if(schemeHeaderBean.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_HAND_PUMP)){
				prefix = "HP";
			}
			if(schemeHeaderBean.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_CANNAL)){
				prefix = "CA";
			}
			if(schemeHeaderBean.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_PERCULATION_WELL)){
				prefix = "PW";
			}
			if(schemeHeaderBean.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_TUBE_WELL)){
				prefix = "TW";
			}
			if(schemeHeaderBean.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_LIFTING_WATER_RSD_LAKE)){
				prefix = "SPL";
			}
			if(schemeHeaderBean.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_DISTRIBUTION)){
				prefix = "SPL";
			}
			if(schemeHeaderBean.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_TUBE_WELL_WITH_RO)){
				prefix = "TW";
			}
			if(schemeHeaderBean.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_PONDS)){
				prefix = "PO";
			}
			if(schemeHeaderBean.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_ROOF_TOP)){
				prefix = "RT";
			}
			if(schemeHeaderBean.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_SEWERAGE)){
				prefix = "SEW";
			}
			if(schemeHeaderBean.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_IMPROVEMENT)){
				prefix = "IMP";
			}
			if(schemeHeaderBean.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_WATER_METER)){
				prefix = "IMP";
			}
			DocumentNumberBean documentNumebrBean=new DocumentNumberBean();
//			documentNumebrBean.setLocationId(inwardDakForm.getLocationId());
			documentNumebrBean.setDocumentType("SCHEME");
			DocumentNumberBean documentNumberBean = documentNumberDao.getDocumentNumber(documentNumebrBean).get(0);
			if(!(MisUtility.ifEmpty(documentNumberBean))){
				throw new MISException();
			}
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			schemeHeaderBean.setMisAuditBean(misAuditBean);
			schemeHeaderBean.setSchemeId(prefix+documentNumberBean.getLastNumber());
			status = schemeHeaderDao.saveSchemeHeader(schemeHeaderBean);
			 
			if(status){
				schemeHeaderVillageForm.setSchemeCode(prefix+documentNumberBean.getLastNumber());
             	System.out.println("Scheme Form"+schemeHeaderVillageForm.getSchemeCode());
				List<SchemeVillageBean> schemeVillageBeans = populateSchemeVillageBeans(schemeHeaderVillageForm,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				System.out.println("before loop====--------");
				
				for(SchemeVillageBean schemeVillagebean:schemeVillageBeans){
					System.out.println("scheme id ===="+schemeVillagebean.getSchemeId());
					System.out.println("scheme upgraded===="+schemeVillagebean.getSchemeUpgraded());
					
				}
			
			 	/*String villageId=schemeVillageBean.getVillageId();
				System.out.println("before search ");
			 	
			 	List<SchemeVillageBean>  villagebean=schemeVillageDao.findVillage(villageId, statusList);
			 	*/System.out.println("after search ");
			 	/*for(SchemeVillageBean schemeBean:villagebean){
			 		System.out.println("scheme operated by =="+schemeBean.getSchemeOperatedBy());
			 		System.out.println("operation date =="+schemeBean.getOperationDate());
			 		if((schemeBean.getSchemeOperatedBy()=="GPWSC-Self"||schemeBean.getSchemeOperatedBy().equals("GPWSC-Self"))&&schemeBean.getOperationDate()!=null){
			 			 //schemeVillageDao.saveSchemeVillage(villagebean);
			 			
					}
			 	}*/
				for(SchemeVillageBean schemeVillagebean:schemeVillageBeans){
					if(schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_SEWERAGE)){
					schemeVillagebean.setWaterWorksLocation(false);
				}
				if(schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_HAND_PUMP)){
					schemeVillagebean.setWaterWorksLocation(false);
				}
				System.out.println("scheme operated by====inside for loop"+schemeVillagebean);
				
				}
				
				if(!MisUtility.ifEmpty(schemeVillageBeans)){
					System.out.println("inside schemeVillageBean boimpl======before for loop==+++----++++++"+schemeVillageBeans.toString());
					
				if(!(schemeHeaderVillageForm.getSchemeSource().equals("SEWERAGE")||schemeHeaderVillageForm.getSchemeSource().equals("HANDPUMP"))){
					
					count3=0;
					
					
					for(SchemeVillageBean schemeVillage:schemeVillageBeans){
							if(schemeVillage.isWaterWorksLocation()==true){
								count3++;
							 		
								}
							}
					if(count3<1){
							throw new MISException(MISExceptionCodes.MIS013,"Please select location of water works");
							
							}	
					if(count3>1){
						throw new MISException(MISExceptionCodes.MIS013,"Water works can not have multiple locations.Please tick only one Water works location");
						
						}
					}
					
					System.out.println("before save ===");
					boolean schemeVillageStatus = schemeVillageDao.saveSchemeVillage(schemeVillageBeans);
					if(!schemeVillageStatus){
						
						log.error(schemeVillageBeans);
						
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Scheme Village details");
					}else{
						schemeId = schemeHeaderVillageForm.getSchemeCode();
					}
				}

			 documentNumberDao.saveOrUpdateDocumentNumberBeans(documentNumberBean);
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

		
		return schemeId;
	}

	@SuppressWarnings("unused")
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateSchemeMaster(SchemeHeaderVillageForm schemeHeaderVillageForm,MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		boolean flag=false;
		int count1=0;
		
		try {
			SchemeHeaderBean schemeHeaderBean = populateSchemeHeaderBean(schemeHeaderVillageForm);
			if(schemeHeaderBean.getSchemeSource().equals("WATERMETER")||schemeHeaderBean.getSchemeSource()=="WATERMETER"){
				schemeHeaderBean.setWaterWorksExistingNew(MISConstants.SCHEME_TYPE_EXISTING);
				schemeHeaderBean.setWatersupply("wm");
				schemeHeaderBean.setSchemeSource("IMPROVEMENT");
				}
			// This code sinpet is written to enter district id which is fetch from  user location
			/* ****************************************************************************** */
			LocationBean locationBean = new LocationBean();
			locationBean.setLocationId(schemeHeaderVillageForm.getLocationId());
			schemeHeaderBean.setSubDivisionId(schemeHeaderVillageForm.getSubDivisionId());
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
					List<SchemeVillageBean> schemeVillageBeans = updatePopulateSchemeVillageBeans(schemeHeaderVillageForm,  misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
					System.out.println("schemeHeaderVillageForm===========in bo========"+schemeHeaderVillageForm.toString());
					for(SchemeVillageBean schemeVillagebean:schemeVillageBeans){
						if(schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_SEWERAGE)){
						schemeVillagebean.setWaterWorksLocation(false);
					}
					if(schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_HAND_PUMP)){
						schemeVillagebean.setWaterWorksLocation(false);
					}
					}
					//System.out.println("populate scheme village"+schemeVillageBeans.toString());
					if(!MisUtility.ifEmpty(schemeVillageBeans)){
						if(!(schemeHeaderVillageForm.getSchemeSource().equals("SEWERAGE")||schemeHeaderVillageForm.getSchemeSource().equals("HANDPUMP"))){
							
							
						count=0;
						
						
					//	System.out.println("schemeHeaderVillageForm===========in bo========"+schemeHeaderVillageForm.getVillageDatagrid().getAddedData().toString());
						//System.out.println("inside schemeVillageBean boimpl========+++----++++++"+schemeVillageBeans.toString());
						for(SchemeVillageBean schemeVillage:schemeVillageBeans){
							System.out.println("schemeVillage.getMisAuditBean().getStatus()==========="+schemeVillage.getMisAuditBean().getStatus());
							if(!schemeVillage.getMisAuditBean().getStatus().equals("D")||schemeVillage.getMisAuditBean().getStatus()!="D"){
								if((!schemeVillage.getVillageName().equals(""))||schemeVillage.getVillageName()!=""){
									count1++;
								}
								if(schemeVillage.isWaterWorksLocation()==true){
									flag=true;
									count++;
									System.out.println("count in for  loop-------------"+count);
								 	}
								}
						
						/*if(MisUtility.ifEmpty(schemeHeaderVillageForm.getVillageDatagrid().getAddedData())){
							count=count-1;
						}*/
						if((count1==0)){
								throw new MISException(MISExceptionCodes.MIS013,"Please attach Habitations.");
							}
						if((flag==false && count==0)){
							throw new MISException(MISExceptionCodes.MIS013,"Please select location of water works");
							
						}
						if((count>1)){
							throw new MISException(MISExceptionCodes.MIS013,"Water works can not have multiple locations.Please tick only one Water works location");
							}
						
						
						}
						}
					}
						
						
						
					    boolean schemeVillageStatus = schemeVillageDao.saveSchemeVillage(schemeVillageBeans);
						if(!schemeVillageStatus){
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Scheme Village details");
						}
					
			
			}
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} /*catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}*/
		return status;
	}
	
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteSchemeMaster(
			SchemeHeaderVillageForm schemeHeaderVillageForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			SchemeHeaderBean schemeHeaderBean = populateSchemeHeaderBean(schemeHeaderVillageForm);
			// This code sinpet is written to enter district id which is fetch from  user location
			/* ****************************************************************************** */
			LocationBean locationBean = new LocationBean();
			locationBean.setLocationId(schemeHeaderVillageForm.getLocationId());
			locationBean = locationDao.getLocation(locationBean);
			schemeHeaderBean.setDistrictId(locationBean.getParentLocation());
			/* ****************************************************************************** */
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			schemeHeaderBean.setMisAuditBean(misAuditBean);
			status = schemeHeaderDao.saveOrUpdateSchemeHeader(schemeHeaderBean);
			SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
			String[] str = schemeHeaderVillageForm.getSchemeCode().split("-");
			schemeVillageBean.setSchemeId(str[0]);
			
			System.out.println("SCHEME MASTERRRRRRRRRRRRRRRRR");
			
				if(status){
					System.out.println("daoooooooooooooo---------------");
					List<SchemeVillageBean> schemeVillageBeans = schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
					for (SchemeVillageBean schemeVillageBean2 : schemeVillageBeans) {
						System.out.println("in schememaster dao full---------------------"+schemeVillageBean2);
						
						System.out.println("in schememaster dao---------------------"+schemeVillageBean2.isWaterWorksLocation());
						misAuditBean = schemeVillageBean2.getMisAuditBean();
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
						schemeVillageBean2.setMisAuditBean(misAuditBean);
					}
					
					if(!MisUtility.ifEmpty(schemeVillageBeans)){
						if(!schemeVillageDao.saveSchemeVillage(schemeVillageBeans)){
							throw new MISException(MISExceptionCodes.MIS003, "Scheme Details  not approved for the Scheme Id : "+schemeHeaderVillageForm.getSchemeCode());
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

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postSchemeMaster(
			SchemeHeaderVillageForm schemeHeaderVillageForm,
			MISSessionBean misSessionBean) throws MISException {
boolean status = false;
		
		try {
			SchemeHeaderBean headerBean = new SchemeHeaderBean();
			headerBean.setSchemeId(schemeHeaderVillageForm.getSchemeCode());
			headerBean = schemeHeaderDao.findSchemeHeader(headerBean, null).get(0);
//			System.out.println("++++++++++++++++="+headerBean);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = headerBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			headerBean.setMisAuditBean(misAuditBean);
//			System.out.println("++++++++++++++++="+headerBean);
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			status = schemeHeaderDao.saveOrUpdateSchemeHeader(headerBean);
			SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
			schemeVillageBean.setSchemeId(schemeHeaderVillageForm.getSchemeCode());
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
							throw new MISException(MISExceptionCodes.MIS003, "Scheme Details  not approved for the Scheme Id : "+schemeHeaderVillageForm.getSchemeCode());
						}else{
							System.out.println("before create instatiate scheme bean class ==========");
							SchemeBean schemeBean = new SchemeBean();
							refreshSchemeForm(schemeBean);
							schemeBean.setSchemeId(schemeHeaderVillageForm.getSchemeCode());
							schemeBean.setSchemeUpgraded("NO");
							List<SchemeBean>schemeBeans = schemeDao.findScheme(schemeBean, statusList); //To check wether scheme code already present
							if(MisUtility.ifEmpty(schemeBeans)){
							System.out.println("in if condition");
							MISAuditBean misAuditBean2 = new MISAuditBean();
							misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
							misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
							misAuditBean2.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
							schemeBean.setMisAuditBean(misAuditBean2);
							}
							System.out.println("before details save in post------------"+schemeBean.toString());
							schemeDao.saveScheme(schemeBean);
							
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
	
	
	@SuppressWarnings("unchecked")
	private List<SchemeVillageBean> updatePopulateSchemeVillageBeans(SchemeHeaderVillageForm schemeHeaderVillageForm, MISSessionBean misSessionBean, String status){
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
		
		Collection<SchemeVillageBean> addedSchemeVillageBeans = schemeHeaderVillageForm.getVillageDatagrid().getAddedData();
		System.out.println("added------------------"+addedSchemeVillageBeans.toString());
		if(!MisUtility.ifEmpty(addedSchemeVillageBeans)){
			for (SchemeVillageBean schemeVillageBean : addedSchemeVillageBeans) {
				schemeVillageBean.setSchemeId(schemeHeaderVillageForm.getSchemeCode());
				schemeVillageBean.setSchemeUpgraded(schemeHeaderVillageForm.getSchemeUpgraded());
				/**
				 * KD CODE
				 */
				schemeVillageBean.setVillageCategory(schemeHeaderVillageForm.getSubCategoryProgramme());
				System.out.println("Scheme Village Category --------------"+schemeHeaderVillageForm.getSubCategoryProgramme());
				/**
				 * KD CODE
				 */
				System.out.println("Scheme Upgraded --------------"+schemeHeaderVillageForm.getSchemeUpgraded());
				schemeVillageBean.setMisAuditBean(misAuditBean1);
				schemeVillageBeans.add(schemeVillageBean);
				//System.out.println("from added for loop------------------"+schemeVillageBeans);
			}
		}
		List<SchemeVillageBean> schemeVillage=schemeHeaderVillageForm.getSchemeVillageBeans();
		if(!MisUtility.ifEmpty(schemeVillage)){
			for(SchemeVillageBean schemeVillagebean:schemeVillage){
			schemeVillageBeans.add(schemeVillagebean);
				}
			}
		
		Collection<SchemeVillageBean> modifiedSchemeVillageBeans = schemeHeaderVillageForm.getVillageDatagrid().getModifiedData();
		if(!MisUtility.ifEmpty(modifiedSchemeVillageBeans)){
			for (SchemeVillageBean schemeVillageBean : modifiedSchemeVillageBeans) {
				schemeVillageBean.setSchemeId(schemeHeaderVillageForm.getSchemeCode());
				/**
				 * KD CODE
				 */
				schemeVillageBean.setVillageCategory(schemeHeaderVillageForm.getSubCategoryProgramme());
				System.out.println("Scheme Village Category --------------"+schemeHeaderVillageForm.getSubCategoryProgramme());
				/**
				 * KD CODE
				 */
				schemeVillageBean.setMisAuditBean(misAuditBean2);
				schemeVillageBeans.add(schemeVillageBean);
				
			}
		}
		for (SchemeVillageBean schemeVillageBean : schemeVillageBeans) {
			System.out.println("1.HASH CODE of Scheme Village"+schemeVillageBean.hashCode());
		}
//		System.out.println("outside delete for loop------------------"+schemeVillageBeans);
		Collection<SchemeVillageBean> deletedSchemeVillageBeans = schemeHeaderVillageForm.getVillageDatagrid().getDeletedData();
//		System.out.println("Deleted------------------"+deletedSchemeVillageBeans);
		if(!MisUtility.ifEmpty(deletedSchemeVillageBeans)){
			for (SchemeVillageBean schemeVillageBean : deletedSchemeVillageBeans) {
				schemeVillageBean.setSchemeId(schemeHeaderVillageForm.getSchemeCode());
				misAuditBean3.setStatus(MISConstants.MASTER_STATUS_DELETED);
//				System.out.println(" 1 Scheme Village Beans -----"+schemeVillageBean+":"+schemeVillageBean.hashCode());
				schemeVillageBean.setMisAuditBean(misAuditBean3);
//				System.out.println(" 2 Scheme Village Beans -----"+schemeVillageBean+":"+schemeVillageBean.hashCode());
				schemeVillageBeans.add(schemeVillageBean);
//				System.out.println("from deleted for loop------------------"+schemeVillageBeans);
			}
		}
		
//		System.out.println("*************************************"+schemeVillageBeans);
		return schemeVillageBeans;
	}
	
	@SuppressWarnings("unchecked")
	private List<SchemeVillageBean> populateSchemeVillageBeans(SchemeHeaderVillageForm schemeHeaderVillageForm, MISSessionBean misSessionBean, String status){
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
		
		Collection<SchemeVillageBean> addedSchemeVillageBeans = schemeHeaderVillageForm.getVillageDatagrid().getAddedData();
		System.out.println("added------------------"+addedSchemeVillageBeans);
		
		if(!MisUtility.ifEmpty(addedSchemeVillageBeans)){
			ArrayList<String> status4=new ArrayList<String>();
		 	status4.add(MISConstants.MASTER_STATUS_APPROVED);
			for (SchemeVillageBean schemeVillageBean : addedSchemeVillageBeans) {
				/*if(schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_SEWERAGE)||schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_HAND_PUMP)){
					schemeVillageBean.setWaterWorksLocation(false);
				}*/
				/*String villageId=schemeVillageBean.getVillageId();
				System.out.println("before search ");
			 	ArrayList<String> status4=new ArrayList<String>();
			 	status4.add(MISConstants.MASTER_STATUS_APPROVED);*/
				
				schemeVillageBean.setVillageCategory(schemeHeaderVillageForm.getSubCategoryProgramme());
				schemeVillageBean.setSchemeId(schemeHeaderVillageForm.getSchemeCode());
				schemeVillageBean.setSchemeUpgraded(schemeHeaderVillageForm.getSchemeUpgraded());
				System.out.println("Scheme Upgraded --------------"+schemeHeaderVillageForm.getSchemeUpgraded());
				schemeVillageBean.setMisAuditBean(misAuditBean1);
				schemeVillageBeans.add(schemeVillageBean);
				System.out.println("from added for loop------------------"+schemeVillageBeans);
			}
		}
		/*List<SchemeVillageBean> schemeVillage=schemeHeaderVillageForm.getSchemeVillageBeans();
		if(!MisUtility.ifEmpty(schemeVillage)){
			for(SchemeVillageBean schemeVillagebean:schemeVillage){
			schemeVillageBeans.add(schemeVillagebean);
				}
			}*/
		
		Collection<SchemeVillageBean> modifiedSchemeVillageBeans = schemeHeaderVillageForm.getVillageDatagrid().getModifiedData();
		if(!MisUtility.ifEmpty(modifiedSchemeVillageBeans)){
			for (SchemeVillageBean schemeVillageBean : modifiedSchemeVillageBeans) {
				schemeVillageBean.setSchemeId(schemeHeaderVillageForm.getSchemeCode());
				schemeVillageBean.setMisAuditBean(misAuditBean2);
				schemeVillageBeans.add(schemeVillageBean);
				
			}
		}
		for (SchemeVillageBean schemeVillageBean : schemeVillageBeans) {
			System.out.println("1.HASH CODE of Scheme Village"+schemeVillageBean.hashCode());
		}
//		System.out.println("outside delete for loop------------------"+schemeVillageBeans);
		Collection<SchemeVillageBean> deletedSchemeVillageBeans = schemeHeaderVillageForm.getVillageDatagrid().getDeletedData();
//		System.out.println("Deleted------------------"+deletedSchemeVillageBeans);
		if(!MisUtility.ifEmpty(deletedSchemeVillageBeans)){
			for (SchemeVillageBean schemeVillageBean : deletedSchemeVillageBeans) {
				schemeVillageBean.setSchemeId(schemeHeaderVillageForm.getSchemeCode());
				misAuditBean3.setStatus(MISConstants.MASTER_STATUS_DELETED);
//				System.out.println(" 1 Scheme Village Beans -----"+schemeVillageBean+":"+schemeVillageBean.hashCode());
				schemeVillageBean.setMisAuditBean(misAuditBean3);
//				System.out.println(" 2 Scheme Village Beans -----"+schemeVillageBean+":"+schemeVillageBean.hashCode());
				schemeVillageBeans.add(schemeVillageBean);
//				System.out.println("from deleted for loop------------------"+schemeVillageBeans);
			}
		}
		/*List<String> statusList=new ArrayList<String>();
	 	statusList.add("U");
	 	statusList.add("A");
	 	String villageId=schemeHeaderVillageForm.getVillageId();
		System.out.println("before search ");
	 	
	 	List<SchemeVillageBean>  villagebean=schemeVillageDao.findVillage(villageId, statusList);
	 	System.out.println("after search ");
	 	for(SchemeVillageBean schemeBean:villagebean){
	 		System.out.println("scheme operated by =="+schemeBean.getSchemeOperatedBy());
	 		System.out.println("operation date =="+schemeBean.getOperationDate());
	 		if((schemeBean.getSchemeOperatedBy()=="GPWSC-Self"||schemeBean.getSchemeOperatedBy().equals("GPWSC-Self"))&&schemeBean.getOperationDate()!=null){
	 			 //schemeVillageDao.saveSchemeVillage(villagebean);
	 			
	 			schemeBean.setSchemeOperatedBy(schemeBean.getSchemeOperatedBy());
	 			schemeVillageBeans.add(schemeBean);
			}
	 	}*/
		
//		System.out.println("*************************************"+schemeVillageBeans);
		return schemeVillageBeans;
	}


	
	private SchemeHeaderBean populateSchemeHeaderBean(SchemeHeaderVillageForm schemeHeaderVillageForm){
		
		SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
		try {
			schemeHeaderBean.setSchemeId(schemeHeaderVillageForm.getSchemeCode());
			schemeHeaderBean.setSchemeName(schemeHeaderVillageForm.getSchemeName());
			schemeHeaderBean.setProgId(schemeHeaderVillageForm.getProgramId());
			schemeHeaderBean.setLocationId(schemeHeaderVillageForm.getLocationId());
			schemeHeaderBean.setSchemeSource(schemeHeaderVillageForm.getSchemeSource());
			schemeHeaderBean.setSchemeUpgraded(schemeHeaderVillageForm.getSchemeUpgraded());
			schemeHeaderBean.setRefSchemeId(schemeHeaderVillageForm.getRefSchemeCode());
			schemeHeaderBean.setWatersupply(schemeHeaderVillageForm.getWatersupply());
			schemeHeaderBean.setSchemeUpgraded("NO");
			schemeHeaderBean.setWaterWorksExistingNew(schemeHeaderVillageForm.getWaterWorksExistingNew());
			schemeHeaderBean.setSubDivisionId(schemeHeaderVillageForm.getSubDivisionId());
			
			/**
			 * KD Work Done Working
			 */
			//schemeHeaderBean.setSubCategoryProgramme(schemeHeaderVillageForm.getSubCategoryProgramme());
			/**
			 * KD Work Completed
			 */
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return schemeHeaderBean;
	}
		
	
	//This method is to set default values of scheme bean before it is saved from post method
private void refreshSchemeForm(SchemeBean schemeBean){
		
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
		
	}

//This is an overloaded find method for screen Scheme Village Commissioning Information
@Override
public SchemeMasterBean findSchemeMaster(
		SchemeVillageCommisionForm schemeVillageCommisionForm,
		List<String> statusList) throws MISException {
	SchemeMasterBean schemeMasterBean = null;
	List <SchemeHeaderBean> schemeHeaderBeans = new ArrayList<SchemeHeaderBean>();
	
	List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
	try {
		SchemeHeaderBean  schemeHeaderBean =new SchemeHeaderBean();
		if((MisUtility.ifEmpty(schemeVillageCommisionForm.getSchemeCode()))&& MisUtility.ifEmpty(schemeVillageCommisionForm.getLocationId())){
		schemeHeaderBean.setSchemeId(schemeVillageCommisionForm.getSchemeCode());
		schemeHeaderBean.setLocationId(schemeHeaderBean.getLocationId());
		
		schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
		
		SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
		schemeVillageBean.setSchemeId(schemeVillageCommisionForm.getSchemeCode());
	
		if(!MisUtility.ifEmpty(schemeHeaderBeans)){
			schemeMasterBean = new SchemeMasterBean(); 
			schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
		}
		}else{
			throw new MISException(MISExceptionCodes.MIS004,"No Scheme Id or Scheme Name and Location Provided");
		}
		
		schemeMasterBean.setSchemeHeaderBeans(schemeHeaderBeans);
		schemeMasterBean.setSchemeVillageBeans(schemeVillageBeans);
		
	} catch (DataAccessException e) {
		log.error(e.getLocalizedMessage(),e);
		throw new MISException(e);
	}
	return schemeMasterBean;
}

@Override
public SchemeMasterBean findSchemeMaster1(
		SchemeVillageCommisionForm schemeVillageCommisionForm,
		List<String> statusList) throws MISException {
	SchemeMasterBean schemeMasterBean = null;
	List <SchemeHeaderBean> schemeHeaderBeans = new ArrayList<SchemeHeaderBean>();
	
	List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
	try {
		SchemeHeaderBean  schemeHeaderBean =new SchemeHeaderBean();
	
		if((MisUtility.ifEmpty(schemeVillageCommisionForm.getSchemeCode()))&& MisUtility.ifEmpty(schemeVillageCommisionForm.getLocationId())){
		schemeHeaderBean.setSchemeId(schemeVillageCommisionForm.getSchemeCode());
		schemeHeaderBean.setSchemeUpgraded(schemeVillageCommisionForm.getSchemeUpgrade());
		schemeHeaderBean.setLocationId(schemeHeaderBean.getLocationId());
		
		
		schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
		
		SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
		schemeVillageBean.setSchemeId(schemeVillageCommisionForm.getSchemeCode());
		
		
		if(!MisUtility.ifEmpty(schemeHeaderBeans)){
			schemeMasterBean = new SchemeMasterBean(); 
			String extention=schemeVillageCommisionForm.getSchemeUpgrade();
			if(extention.equals("NO")){
				String arr[]={"NO"};
				schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
			}
			else if(extention.equals("ext")){
				//Earlier Working 	
				//String arr[]={"NO","ext"};
				String arr[]={"ext"};  
				schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
			}
			else if(extention.equals("ext1"))
			{
				//Earlier Working
				 //String arr[]={"NO","ext","ext1"};
				String arr[]={"ext1"};  
				schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
			}
			else if(extention.equals("ext2")){
				//Earlier Working
				//String arr[]={"NO","ext","ext1","ext2"};
				String arr[]={"ext2"}; //String arr[]={"NO","ext","ext1","ext2"};
				schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
			}
			else if(extention.equals("ext3")){
				//Earlier Working
				//String arr[]={"NO","ext","ext1","ext2","ext3"};
				String arr[]={"ext3"};   //String arr[]={"NO","ext","ext1","ext2","ext3"};
				schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
			}
			else{
				schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
			}
		}
		}else{
			throw new MISException(MISExceptionCodes.MIS004,"No Scheme Id or Scheme Name and Location Provided");
		}
		
		schemeMasterBean.setSchemeHeaderBeans(schemeHeaderBeans);
		schemeMasterBean.setSchemeVillageBeans(schemeVillageBeans);
		
	} catch (DataAccessException e) {
		log.error(e.getLocalizedMessage(),e);
		throw new MISException(e);
	}
	return schemeMasterBean;
}

@Override
public SchemeMasterBean findSchemeMaster(
		OperatingArrangementForm operatingArrangementForm,
		List<String> statusList) throws MISException {
	SchemeMasterBean schemeMasterBean = null;
	List <SchemeHeaderBean> schemeHeaderBeans = new ArrayList<SchemeHeaderBean>();
	
	List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
	try {
		SchemeHeaderBean  schemeHeaderBean =new SchemeHeaderBean();
		if((MisUtility.ifEmpty(operatingArrangementForm.getSchemeCode()))&& MisUtility.ifEmpty(operatingArrangementForm.getLocationId())){
		schemeHeaderBean.setSchemeId(operatingArrangementForm.getSchemeCode());
		schemeHeaderBean.setSchemeUpgraded(operatingArrangementForm.getSchemeUpgrade());
		schemeHeaderBean.setLocationId(schemeHeaderBean.getLocationId());
		
		schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
		
		SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
		schemeVillageBean.setSchemeId(operatingArrangementForm.getSchemeCode());
		
		
		if(!MisUtility.ifEmpty(schemeHeaderBeans)){
			schemeMasterBean = new SchemeMasterBean(); 
			String extention=operatingArrangementForm.getSchemeUpgrade();
			if(extention.equals("NO")){
				String arr[]={"NO"};
				schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
			}
			else if(extention.equals("ext")){
				String arr[]={"ext"};
				schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
			}
			else if(extention.equals("ext1"))
			{
				String arr[]={"ext1"};
				schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
			}
			else if(extention.equals("ext2")){
				String arr[]={"ext2"};
				schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
			}
			else if(extention.equals("ext3")){
				String arr[]={"ext3"};
				schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList,arr);
			}
			else{
				schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
			}
		}
		}else{
			throw new MISException(MISExceptionCodes.MIS004,"No Scheme Id or Scheme Name and Location Provided");
		}
		
		schemeMasterBean.setSchemeHeaderBeans(schemeHeaderBeans);
		schemeMasterBean.setSchemeVillageBeans(schemeVillageBeans);
		
	} catch (DataAccessException e) {
		log.error(e.getLocalizedMessage(),e);
		throw new MISException(e);
	}
	return schemeMasterBean;
}



/* Note this method is created for updating Village Commissioning Information in SchemeVillage Table. Currently it will override the mis-audit bean status of the entries to the current entries at the time of modification of this data*/
@Override
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public boolean updateSchemeMaster(SchemeVillageCommisionForm schemeVillageCommisionForm,MISSessionBean misSessionBean) throws MISException {
	boolean status = false;

	try {
		System.out.println("commision form-------");
		
		List<SchemeVillageBean> schemeVillageBeans = populateSchemeVillageBeanFromSchemeCommissionForm(schemeVillageCommisionForm, misSessionBean);
		if(!MisUtility.ifEmpty(schemeVillageBeans)){
			System.out.println("inside if in bo before save ---------");
		
			for(SchemeVillageBean schemeVillagebean:schemeVillageBeans){
				System.out.println("scheme operated by after set ===="+schemeVillagebean.getSchemeOperatedBy());
				
				if((schemeVillagebean.getSchemeOperatedBy()==null ||schemeVillagebean.getSchemeOperatedBy().equals(null) )){
					schemeVillagebean.setSchemeOperatedBy("DWSS-Self");
					}
			}
			status = schemeVillageDao.updateSchemeVillages(schemeVillageBeans);
			System.out.println("after if in bo after save ---------");
			
		}

	} catch (DataAccessException e) {
		log.error(e.getLocalizedMessage(),e);
		e.printStackTrace();
		throw new MISException(e);
	}

	return status;
}

@Override
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public boolean updateSchemeMaster(OperatingArrangementForm operatingArrangementForm,MISSessionBean misSessionBean) throws MISException, ParseException {
	boolean status = false;

	try {
		List<SchemeVillageBean> schemeVillageBeanss=new ArrayList<SchemeVillageBean>();

		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
		schemeVillageBean.setSchemeId(operatingArrangementForm.getSchemeCode());
		
		
			schemeVillageBeanss=schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
		
		List<SchemeVillageBean> schemeVillageBeans = populateSchemeVillageBeanFromSchemeCommissionForm(operatingArrangementForm, misSessionBean);
		
		if(!MisUtility.ifEmpty(schemeVillageBeans)){
			for(SchemeVillageBean schemeVillage:schemeVillageBeans){
				
				/*for(SchemeVillageBean schemeVillageBeansss:schemeVillageBeanss){
					
					if(MisUtility.convertDateToString(schemeVillageBeansss.getSchemeCommissionedDate()).equals(MisUtility.convertDateToString(schemeVillage.getSchemeCommissionedDate())) && schemeVillageBeansss.getVillageId().equalsIgnoreCase(schemeVillage.getVillageId())){
						if(MisUtility.ifEmpty(schemeVillageBeansss.getOperationDate())){
						if(!(MisUtility.convertDateToString(schemeVillageBeansss.getOperationDate()).equalsIgnoreCase(MisUtility.convertDateToString(schemeVillage.getOperationDate())))){
							throw new MISException(MISExceptionCodes.MIS013, "HandedOverDate could not be change");	
						}
					}
					}
				}*/
				if(schemeVillage.getSchemeOperatedBy().equals("GP-Self") || schemeVillage.getSchemeOperatedBy().equals("GP-Outsourced") ||schemeVillage.getSchemeOperatedBy().equals("GPWSC-Self") ||schemeVillage.getSchemeOperatedBy().equals("GPWSC-Outsourced")){
					System.out.println("date ======"+schemeVillage.getOperationDate());
					if(schemeVillage.getOperationDate()==null){
					throw new MISException(MISExceptionCodes.MIS013, "HandedOverDate can not be blank in case of GP and GPWSC");	
					}
				}
					
				 if(schemeVillage.getOperationDate()==null && schemeVillage.getSchemeOperatedBy().equals("")){
						
						 throw new MISException(MISExceptionCodes.MIS013, "Please first select appropriate  Scheme Operated By field");	
			 }
				 if(schemeVillage.getOperationDate()!=null && schemeVillage.getSchemeOperatedBy().equals("")){
					  throw new MISException(MISExceptionCodes.MIS013, "Please first select appropriate Scheme Operated By field");	
				 }
				System.out.println("date befor e if--"+schemeVillage.getOperationDate());
				 if(schemeVillage.getOperationDate()!=null){
					   // boolean isDate=true;
					    Date current = new Date();
					    System.out.println("current date "+current);
					    String datePattern = "dd-MM-yyyy";
					    SimpleDateFormat df = new SimpleDateFormat(datePattern);
					    String currentDate=MisUtility.convertDateToString(schemeVillage.getOperationDate());
					   
					    System.out.println("entered date========="+currentDate);
					    Date givenDate = df.parse(currentDate);
					    Long l = givenDate.getTime();
					     //create date object
					    Date next = new Date(l);
					   
					    //compare both dates
					    if(next.after(current)){
					    	throw new MISException(MISExceptionCodes.MIS013, "Future date is not  allowed");	
						} 
				}
					if(schemeVillage.getSchemeOperatedBy().equals("DWSS-Self")||schemeVillage.getSchemeOperatedBy().equals("DWSS-Outsourced")){
						if(schemeVillage.getOperationDate()!=null){
									throw new MISException(MISExceptionCodes.MIS013, "HandedOverDate should be blank in case of DWSS");	
					  }
					}
					 if( schemeVillage.getOperationDate()!=null && schemeVillage.getSchemeCommissionedDate()!=null){
						 
						//HandedOverDate ;   
						 	String datePattern = "dd-MM-yyyy";
						    SimpleDateFormat df = new SimpleDateFormat(datePattern);
						    String currentDate=MisUtility.convertDateToString(schemeVillage.getOperationDate());
						   
						    System.out.println("entered date========="+currentDate);
						    Date givenDate = df.parse(currentDate);
						    Long l = givenDate.getTime();
						     //create date object
						    Date handedOverDate = new Date(l);
						    
						//SchemeCommissionedDate
						    String datePatternForCm = "dd-MM-yyyy";
						    SimpleDateFormat sdf = new SimpleDateFormat(datePatternForCm);
						    String commissioningDate=MisUtility.convertDateToString(schemeVillage.getSchemeCommissionedDate());
							
						    System.out.println("entered date========="+currentDate);
						    Date commDate = sdf.parse(commissioningDate);
						    Long lc = commDate.getTime();
						     //create date object
						    Date commissionedDate = new Date(lc);
						    
						 //difference  b/w HandedOverDate && SchemeCommissionedDate
						    
						    //need to be change later
						if(handedOverDate.before(commissionedDate)){
								  throw new MISException(MISExceptionCodes.MIS013, "HandedOverDate can not be less than SchemeCommissionedDate ");	
							
						}
				 }		
							
			}
			status = schemeVillageDao.updateSchemeVillages(schemeVillageBeans);
		}

	} catch (DataAccessException e) {
		log.error(e.getLocalizedMessage(),e);
		e.printStackTrace();
		throw new MISException(e);
		
	}

	return status;
}
/* Note this method is created for updating Village Commissioning Information in SchemeVillage Table. Currently it will override the mis-audit bean status of the entries to the current entries at the time of modification of this data*/
@SuppressWarnings("unchecked")
private List<SchemeVillageBean> populateSchemeVillageBeanFromSchemeCommissionForm(OperatingArrangementForm operatingArrangementForm, MISSessionBean misSessionBean) throws MISException{
		List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
		try {
			MISAuditBean misAuditBean = new MISAuditBean();
			
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			
			
			SchemeVillageBean schemeVillageBean  = null;
			/*Only modified data as User can only modify its Village Commissioing information and cannot add or delete*/
			Collection<VillageOperatingGridBean> operatingGridBeans = operatingArrangementForm.getVillageOperatingDatagrid().getModifiedData();
			System.out.println("operatingGridBeans----->"+operatingGridBeans.toString());
//			System.out.println("IN POPULATE");
			Collection<VillageNCPCStatusBean> villageNCPCStatusBeans = new ArrayList<VillageNCPCStatusBean>();
			VillageNCPCStatusBean villageNCPCStatusBean = null;
			List<String> statusList = new ArrayList<String>();
			statusList.add("A");
			if(!MisUtility.ifEmpty(operatingGridBeans)){
				for (VillageOperatingGridBean bean : operatingGridBeans) {
					schemeVillageBean = new SchemeVillageBean();
					schemeVillageBean.setSchemeId(bean.getSchemeId());
					schemeVillageBean.setVillageId(bean.getVillageId());
					schemeVillageBean.setSchemeUpgraded(bean.getSchemeUpgraded());
					schemeVillageBean.setSupplyServiceLevel(bean.getSupplyServiceLevel());
					
					List<SchemeVillageBean>  waterWorksbean=schemeVillageDao.findWaterWorks(bean.getSchemeId(),bean.getVillageId(),statusList);
				 	for(SchemeVillageBean waterWorksbeans:waterWorksbean){
				 		schemeVillageBean.setWaterWorksLocation(waterWorksbeans.isWaterWorksLocation());
				 	}
                    
				/*	if(date1.equals("")) {
						System.out.println("---------- if");
				       schemeVillageBean.setOperationDate(MisUtility.convertStringToDate(bean.getOperationArrangementDate())); }
					*/
					
					String date1=bean.getOperationArrangementDate();
				
					if(date1!=""){
							System.out.println("else if date----------"+date1);
							boolean isDate = false;
						    String datePattern = "\\d{1,2}-\\d{1,2}-\\d{4}";
						    isDate= date1.matches(datePattern);
						if(!isDate){
							System.out.println("IF is true"+isDate);
							log.error(date1);
							throw new MISException(MISExceptionCodes.MIS003, "Check Date Format");	
						}
						else {System.out.println("else");
						schemeVillageBean.setOperationDate(MisUtility.convertStringToDate(bean.getOperationArrangementDate())); }
						}

					schemeVillageBean.setSchemeOperatedBy(bean.getSchemeOperatedBy());
					
					System.out.println("commisioning date-------------------->"+MisUtility.convertStringToDate(bean.getVillageCommissioningDate()));
					schemeVillageBean.setSchemeCommissionedDate(MisUtility.convertStringToDate(bean.getVillageCommissioningDate()));
					//schemeVillageBean.setSchemeHours(Integer.parseInt(bean.getSchemeHours()));
					//schemeVillageBean.setSchemeFP(bean.getSchemeFP());
					schemeVillageBean.setVillageCategory(bean.getVillagecategory());
					schemeVillageBean.setMisAuditBean(misAuditBean);
					schemeVillageBean.setVillageName(bean.getVillageName());
					schemeVillageBeans.add(schemeVillageBean);
					
					/* This code is to add an entry in mst_village_nc_pc_status table for those villages which have scheme commission date*/
					if(MisUtility.ifEmpty(bean.getVillageCommissioningDate())){
						villageNCPCStatusBean = new VillageNCPCStatusBean();
						villageNCPCStatusBean.setVillageId(bean.getVillageId());
						MISAuditBean misAuditBean2 = new MISAuditBean();
						misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
						misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
						misAuditBean2.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
						villageNCPCStatusBean.setMisAuditBean(misAuditBean2);
						villageNCPCStatusBean.setNcPcStatus("FC");
						villageNCPCStatusBean.setMonthOfStatus(MisUtility.getMonthFromStringDate(bean.getVillageCommissioningDate()));
						villageNCPCStatusBean.setYearOfStatus(MisUtility.getYearFromStringDate(bean.getVillageCommissioningDate()));
						villageNCPCStatusBeans.add(villageNCPCStatusBean);
					}
				}
				
				if(!MisUtility.ifEmpty(villageNCPCStatusBeans)){
					try{
					villageNCPCStatusDao.saveVillageNCPCStatus(villageNCPCStatusBeans);
					}
					catch(Exception e){
						System.out.println("--------Check1---------------");						
						e.printStackTrace();
					}
				}
				
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return schemeVillageBeans;
	}

@SuppressWarnings("unchecked")
private List<SchemeVillageBean> populateSchemeVillageBeanFromSchemeCommissionForm(SchemeVillageCommisionForm schemeVillageCommisionForm, MISSessionBean misSessionBean){
		List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
		for(SchemeVillageBean schemeVillageBean:schemeVillageBeans){
			System.out.println(MisUtility.convertDateToString(schemeVillageBean.getSchemeCommissionedDate()));
		}
		try {
			MISAuditBean misAuditBean = new MISAuditBean();
			
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				
				
				
				
				SchemeVillageBean schemeVillageBean  = null;
				/*Only modified data as User can only modify its Village Commissioing information and cannot add or delete*/
				Collection<SchemeVillageCommissioningVillageGridBean> modifiedCommissioningVillageGridBeans = schemeVillageCommisionForm.getVillageDatagrid().getModifiedData();
				System.out.println("Modification grid bean modifiedCommissioningVillageGridBeans"+modifiedCommissioningVillageGridBeans);
			//			System.out.println("IN POPULATE");
			Collection<VillageNCPCStatusBean> villageNCPCStatusBeans = new ArrayList<VillageNCPCStatusBean>();
			VillageNCPCStatusBean villageNCPCStatusBean = null;
			List<String> statusList = new ArrayList<String>();
			statusList.add("A");
			
			
			if(!MisUtility.ifEmpty(modifiedCommissioningVillageGridBeans)){
				for (SchemeVillageCommissioningVillageGridBean bean : modifiedCommissioningVillageGridBeans) {
					schemeVillageBean = new SchemeVillageBean();
					schemeVillageBean.setSchemeId(bean.getSchemeId());
					schemeVillageBean.setVillageId(bean.getVillageId());
					schemeVillageBean.setSchemeUpgraded(bean.getSchemeUpgraded());
					schemeVillageBean.setSupplyServiceLevel(bean.getSupplyServiceLevel());
					schemeVillageBean.setVillageCategory(bean.getVillageCategory());
					System.out.print("Village Category is:  " + bean.getVillageCategory());
					
					List<SchemeVillageBean>  villagebean=schemeVillageDao.findVillage(bean.getVillageId(),statusList);
				 	for(SchemeVillageBean schemeBean:villagebean){
				 		System.out.println("scheme operated by before  set ===="+schemeBean.getSchemeOperatedBy());
						
				 			if((schemeBean.getOperationDate()!=null)&&(schemeBean.getSchemeOperatedBy()!=null)){
				 			if(schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GP_SELF)||schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GP_OUTSOURCED)||schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GPWSC_OUTSOURCED)||schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GPWSC_SELF)){
				 			System.out.println("scheme operated inside if ==="+schemeBean.getSchemeOperatedBy());
				 			System.out.println("operation date inside if ==="+schemeBean.getOperationDate());
				 			schemeVillageBean.setSchemeOperatedBy(schemeBean.getSchemeOperatedBy());
				 			schemeVillageBean.setOperationDate(schemeBean.getOperationDate());
				 		 }
				 		}
				 			else if(schemeBean.getSchemeOperatedBy()!=null && schemeBean.getOperationDate()==null){
				 				if(schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GP_SELF)||schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GP_OUTSOURCED)||schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GPWSC_OUTSOURCED)||schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GPWSC_SELF)){
				 					schemeVillageBean.setSchemeOperatedBy(schemeBean.getSchemeOperatedBy());
				 				}
				 			}
				 			else{
				 				schemeVillageBean.setSchemeOperatedBy(schemeBean.getSchemeOperatedBy());
				 			}
				 	}
				 	List<SchemeVillageBean>  waterWorksbean=schemeVillageDao.findWaterWorks(bean.getSchemeId(),bean.getVillageId(),statusList);
				 	for(SchemeVillageBean waterWorksbeans:waterWorksbean){
				 		schemeVillageBean.setWaterWorksLocation(waterWorksbeans.isWaterWorksLocation());
				 	}
				 	System.out.println("after for loop in grid=====33====");
					
					String date2=bean.getVillageCommissioningDate();
					System.out.println("date2 value inside grid modification in bo"+date2);
					
					//schemeVillageBean.setSchemeOperatedBy(bean.getSchemeOperatedBy());
					schemeVillageBean.setSchemeCommissionedDate(MisUtility.convertStringToDate(bean.getVillageCommissioningDate()));
					schemeVillageBean.setMisAuditBean(misAuditBean);
					schemeVillageBean.setVillageName(bean.getVillageName());
					schemeVillageBeans.add(schemeVillageBean);
					
					/* This code is to add an entry in mst_village_nc_pc_status table for those villages which have scheme commission date*/
					if(MisUtility.ifEmpty(bean.getVillageCommissioningDate())){
						villageNCPCStatusBean = new VillageNCPCStatusBean();
						villageNCPCStatusBean.setVillageId(bean.getVillageId());
						MISAuditBean misAuditBean2 = new MISAuditBean();
						misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
						misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
						misAuditBean2.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
						villageNCPCStatusBean.setMisAuditBean(misAuditBean2);
						villageNCPCStatusBean.setNcPcStatus("FC");
						villageNCPCStatusBean.setMonthOfStatus(MisUtility.getMonthFromStringDate(bean.getVillageCommissioningDate()));
						villageNCPCStatusBean.setYearOfStatus(MisUtility.getYearFromStringDate(bean.getVillageCommissioningDate()));
						villageNCPCStatusBeans.add(villageNCPCStatusBean);
					}
				}
				
				if(!MisUtility.ifEmpty(villageNCPCStatusBeans)){
					try{
					villageNCPCStatusDao.saveVillageNCPCStatus(villageNCPCStatusBeans);
					}
					catch(Exception e){
						System.out.println("--------Check1---------------");						
						e.printStackTrace();
					}
				}
				
			}
	} 
		catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		
		return schemeVillageBeans;
	}

@Override
public boolean updateSchemeMaster(SchemeVillageCommisionForm schemeVillageCommisionForm,MISSessionBean misSessionBean, HashMap<String,String> VillageCategory)throws MISException {
	boolean status = false;

	try {
		System.out.println("Printing Map Size from Update :- " + VillageCategory.size());
		
		ShowHashMapValues(VillageCategory);
		
		System.out.println("commision form-------");
		
		List<SchemeVillageBean> schemeVillageBeans = populateSchemeVillageBeanFromSchemeCommissionFormString(schemeVillageCommisionForm, misSessionBean , VillageCategory);
		if(!MisUtility.ifEmpty(schemeVillageBeans)){
			System.out.println("inside if in bo before save ---------");
		
			for(SchemeVillageBean schemeVillagebean:schemeVillageBeans){
				System.out.println("scheme operated by after set ===="+schemeVillagebean.getSchemeOperatedBy());
				
				if((schemeVillagebean.getSchemeOperatedBy()==null ||schemeVillagebean.getSchemeOperatedBy().equals(null) )){
					schemeVillagebean.setSchemeOperatedBy("DWSS-Self");
					}
			}
			status = schemeVillageDao.updateSchemeVillages(schemeVillageBeans);
			System.out.println("after if in bo after save ---------");
			
		}

	} catch (DataAccessException e) {
		log.error(e.getLocalizedMessage(),e);
		e.printStackTrace();
		throw new MISException(e);
	}

	return status;
	
}

private void ShowHashMapValues(HashMap<String, String> village_Category_to_be_send2) {
	
	try{
		
		System.out.println("##### Printing the Key and Value from Update Funcion #####");
		
		//Get the Set of Keys from HashMap
		Set setOfKeys = village_Category_to_be_send2.keySet();
		
		//Get the Itrator Instance from the Set
		Iterator  iterator = setOfKeys.iterator();
		
		//Loop the Iterator till we reach the end of HashMAp
		while(iterator.hasNext()){
			String Key = (String) iterator.next();
			String Value = village_Category_to_be_send2.get(Key);
			System.out.println("KEY is : " + Key + ", Value is: " + Value);
		}
		
	}catch(Exception ex){
		System.out.println(ex.getLocalizedMessage().toString().trim());
	}
	// TODO Auto-generated method stub
	
}

@SuppressWarnings("unchecked")
private List<SchemeVillageBean> populateSchemeVillageBeanFromSchemeCommissionFormString(SchemeVillageCommisionForm schemeVillageCommisionForm, MISSessionBean misSessionBean , HashMap<String,String> VillageCategory){
		List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
		for(SchemeVillageBean schemeVillageBean:schemeVillageBeans){
			System.out.println("Converted Date is:- "+MisUtility.convertDateToString(schemeVillageBean.getSchemeCommissionedDate()));
		}
		try {
			MISAuditBean misAuditBean = new MISAuditBean();
			
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				
				
				
				
				SchemeVillageBean schemeVillageBean  = null;
				/*Only modified data as User can only modify its Village Commissioing information and cannot add or delete*/
				Collection<SchemeVillageCommissioningVillageGridBean> modifiedCommissioningVillageGridBeans = schemeVillageCommisionForm.getVillageDatagrid().getModifiedData();
				System.out.println("Modification grid bean modifiedCommissioningVillageGridBeans"+modifiedCommissioningVillageGridBeans);
						System.out.println("IN POPULATE");
			Collection<VillageNCPCStatusBean> villageNCPCStatusBeans = new ArrayList<VillageNCPCStatusBean>();
			VillageNCPCStatusBean villageNCPCStatusBean = null;
			List<String> statusList = new ArrayList<String>();
			statusList.add("A");
			
		
		
			
			
			if(!MisUtility.ifEmpty(modifiedCommissioningVillageGridBeans)){
				int i=0;
				for (SchemeVillageCommissioningVillageGridBean bean : modifiedCommissioningVillageGridBeans) {
					
					System.out.println("SIZe of BEAN is:- "+modifiedCommissioningVillageGridBeans.size());
					
					schemeVillageBean = new SchemeVillageBean();
					schemeVillageBean.setSchemeId(bean.getSchemeId());
					schemeVillageBean.setVillageId(bean.getVillageId());
					schemeVillageBean.setSchemeUpgraded(bean.getSchemeUpgraded());
					schemeVillageBean.setSupplyServiceLevel(bean.getSupplyServiceLevel());
					
					System.out.println("     ");
					System.out.println("###########Printing the Bean #############");
					
					System.out.println("SchemeID: " + bean.getSchemeId() );
					System.out.println("VillageID: " +bean.getVillageId() );
					System.out.println("SchemeUpgraded: " + bean.getSchemeUpgraded());
					System.out.println("GetSypply Service Level: " + bean.getSupplyServiceLevel());
					
					
					/**
					 * Here To DO
					 */
					//Get the Set of Keys from HashMap
					Set setOfKeys = VillageCategory.keySet();
					
					//Get the Itrator Instance from the Set
					Iterator  iterator = setOfKeys.iterator();
					
					//Loop the Iterator till we reach the end of HashMAp
					System.out.println("Replacing the # value back to  empty  and printing the Key Value Pair");
					while(iterator.hasNext()){
						String Key = (String) iterator.next();
						String Value = VillageCategory.get(Key);
						if(Value.equalsIgnoreCase("#")){
							Value = "";
							//schemeVillageBean.setVillageCategory(Value);
						}else{
							//schemeVillageBean.setVillageCategory(Value);
						}
						System.out.println("KEY is : " + Key + ", Value is: " + Value);
					}
					
					/*if(i<VillageCategory.size()){
						System.out.println("i is  :- " + i);
						schemeVillageBean.setVillageCategory(VillageCategory.get(i));
						System.out.println("Village Category " + VillageCategory.get(i));
						i++;
					}*//*else{
						schemeVillageBean.setVillageCategory(VillageCategory.get(0));
						System.out.println("Village Category  is  Last Stand:  " + VillageCategory.get(i));
					}*/
					
					
					List<SchemeVillageBean>  villagebean=schemeVillageDao.findVillage(bean.getVillageId(),statusList);
				 	for(SchemeVillageBean schemeBean:villagebean){
				 		System.out.println("scheme operated by before  set ===="+schemeBean.getSchemeOperatedBy());
						
				 			if((schemeBean.getOperationDate()!=null)&&(schemeBean.getSchemeOperatedBy()!=null)){
				 			if(schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GP_SELF)||schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GP_OUTSOURCED)||schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GPWSC_OUTSOURCED)||schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GPWSC_SELF)){
				 			System.out.println("scheme operated inside if ==="+schemeBean.getSchemeOperatedBy());
				 			System.out.println("operation date inside if ==="+schemeBean.getOperationDate());
				 			schemeVillageBean.setSchemeOperatedBy(schemeBean.getSchemeOperatedBy());
				 			schemeVillageBean.setOperationDate(schemeBean.getOperationDate());
				 		 }
				 		}
				 			else if(schemeBean.getSchemeOperatedBy()!=null && schemeBean.getOperationDate()==null){
				 				if(schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GP_SELF)||schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GP_OUTSOURCED)||schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GPWSC_OUTSOURCED)||schemeBean.getSchemeOperatedBy().equals(MISConstants.SCHEME_OPERATED_BY_GPWSC_SELF)){
				 					schemeVillageBean.setSchemeOperatedBy(schemeBean.getSchemeOperatedBy());
				 				}
				 			}
				 			else{
				 				schemeVillageBean.setSchemeOperatedBy(schemeBean.getSchemeOperatedBy());
				 			}
				 	}
				 	List<SchemeVillageBean>  waterWorksbean=schemeVillageDao.findWaterWorks(bean.getSchemeId(),bean.getVillageId(),statusList);
				 	for(SchemeVillageBean waterWorksbeans:waterWorksbean){
				 		schemeVillageBean.setWaterWorksLocation(waterWorksbeans.isWaterWorksLocation());
				 	}
				 	System.out.println("after for loop in grid=====33====");
					
					String date2=bean.getVillageCommissioningDate();
					System.out.println("date2 value inside grid modification in bo"+date2);
					
					//schemeVillageBean.setSchemeOperatedBy(bean.getSchemeOperatedBy());
					schemeVillageBean.setSchemeCommissionedDate(MisUtility.convertStringToDate(bean.getVillageCommissioningDate()));
					schemeVillageBean.setMisAuditBean(misAuditBean);
					schemeVillageBean.setVillageName(bean.getVillageName());
					schemeVillageBeans.add(schemeVillageBean);
					
					/* This code is to add an entry in mst_village_nc_pc_status table for those villages which have scheme commission date*/
					if(MisUtility.ifEmpty(bean.getVillageCommissioningDate())){
						villageNCPCStatusBean = new VillageNCPCStatusBean();
						villageNCPCStatusBean.setVillageId(bean.getVillageId());
						MISAuditBean misAuditBean2 = new MISAuditBean();
						misAuditBean2.setEntBy(misSessionBean.getEnteredBy());
						misAuditBean2.setEntDate(misSessionBean.getEnteredDate());
						misAuditBean2.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
						villageNCPCStatusBean.setMisAuditBean(misAuditBean2);
						villageNCPCStatusBean.setNcPcStatus("FC");
						villageNCPCStatusBean.setMonthOfStatus(MisUtility.getMonthFromStringDate(bean.getVillageCommissioningDate()));
						villageNCPCStatusBean.setYearOfStatus(MisUtility.getYearFromStringDate(bean.getVillageCommissioningDate()));
						villageNCPCStatusBeans.add(villageNCPCStatusBean);
					}
				}
				
				if(!MisUtility.ifEmpty(villageNCPCStatusBeans)){
					try{
					villageNCPCStatusDao.saveVillageNCPCStatus(villageNCPCStatusBeans);
					}
					catch(Exception e){
						System.out.println("--------Check1---------------");						
						e.printStackTrace();
					}
				}
				
			}
	} 
		catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		
		return schemeVillageBeans;
	}

	
}
