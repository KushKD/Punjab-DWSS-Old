package com.prwss.mis.procurement.workspackage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;
import com.prwss.mis.masters.scheme.dao.SchemeVillageDao;
import com.prwss.mis.masters.village.dao.PrwssVillageViewBean;
import com.prwss.mis.masters.village.dao.PrwssVillageViewDao;
import com.prwss.mis.masters.village.dao.VillageBean;
import com.prwss.mis.procurement.packagecomponents.dao.PackageComponentsBean;
import com.prwss.mis.procurement.packagecomponents.dao.PackageComponentsDao;
import com.prwss.mis.procurement.packageheader.dao.PackageDetailBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderDao;
import com.prwss.mis.procurement.plan.CreateProcPlanBean;
import com.prwss.mis.procurement.plan.dao.CreateProcPlanDao;
import com.prwss.mis.procurement.subplan.dao.ProcSubPlanSchemeDao;
import com.prwss.mis.procurement.subplan.dao.SubPlanSchemeBean;
import com.prwss.mis.procurement.workspackage.dao.PackageWorksDao;
import com.prwss.mis.procurement.workspackage.struts.PackageWorksForm;
import com.prwss.mis.procurement.workspackage.struts.PackageWorksGridBean;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class PackageWorksBOImpl implements PackageWorksBO {
	private Logger log = Logger.getLogger(PackageWorksBOImpl.class);
	private PackageWorksDao packageWorksDao;
	private PackageComponentsDao packageComponentsDao;
	private PackageHeaderDao packageHeaderDao;
	private DocumentNumberDAO documentNumberDao;
	private SchemeVillageDao schemeVillageDao;
	private PrwssVillageViewDao prwssVillageViewDao;
	private CreateProcPlanDao createProcPlanDao;
	private ProcSubPlanSchemeDao procSubPlanSchemeDao;
    private MISJdcDaoImpl misJdcDaoImpl;
    
    public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}
	
	public void setProcSubPlanSchemeDao(ProcSubPlanSchemeDao procSubPlanSchemeDao) {
		this.procSubPlanSchemeDao = procSubPlanSchemeDao;
	}
	
	public PackageComponentsDao getPackageComponentsDao() {
		return packageComponentsDao;
	}

	public void setPackageComponentsDao(PackageComponentsDao packageComponentsDao) {
		this.packageComponentsDao = packageComponentsDao;
	}

	public PackageHeaderDao getPackageHeaderDao() {
		return packageHeaderDao;
	}

	public void setPackageHeaderDao(PackageHeaderDao packageHeaderDao) {
		this.packageHeaderDao = packageHeaderDao;
	}

	public PackageWorksDao getPackageWorksDao() {
		return packageWorksDao;
	}

	public void setPackageWorksDao(PackageWorksDao packageWorksDao) {
		this.packageWorksDao = packageWorksDao;
	}

	@Override
	public PackageDetailBean findPackageWorksFrom(
			PackageWorksForm packageWorksForm, List<String> statusList)
			throws MISException {
		List<PackageWorksBean> packageWorksBeans = null;
		List<PackageHeaderBean> packageHeaderBeans = null;
		List<PackageComponentsBean> packageComponentsBeans = null;
		PackageDetailBean packageDetailBean = new PackageDetailBean();
		try {
			
			PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
			packageHeaderBean.setLocationId(packageWorksForm.getLocationId());
			packageHeaderBean.setPackageId(packageWorksForm.getPackageId().trim());
			packageHeaderBean.setPackageType(MISConstants.MIS_TYPE_OF_TENDER_WORKS);
			packageHeaderBeans = packageHeaderDao.findPackage(packageHeaderBean, statusList);
			
			PackageWorksBean packageWorksBean = new PackageWorksBean();
			packageWorksBean.setPackageId(packageWorksForm.getPackageId());
			
			PackageComponentsBean packageComponentsBean = new PackageComponentsBean();
			packageComponentsBean.setPackageId(packageWorksForm.getPackageId());
			
			if(!MisUtility.ifEmpty(packageHeaderBeans)){
				packageWorksBeans = packageWorksDao.findWorksPackage(packageWorksBean, statusList);
				packageComponentsBeans = packageComponentsDao.findServicesPackageComponent(packageComponentsBean, statusList);
			}
			packageDetailBean.setPackageHeaderBeans(packageHeaderBeans);
			packageDetailBean.setPackageWorksBeans(packageWorksBeans);
			packageDetailBean.setPackageComponentsBeans(packageComponentsBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return packageDetailBean;
	
	}


	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}
	
	public void setSchemeVillageDao(SchemeVillageDao schemeVillageDao) {
		this.schemeVillageDao = schemeVillageDao;
	}
	
	public void setPrwssVillageViewDao(PrwssVillageViewDao prwssVillageViewDao) {
		this.prwssVillageViewDao = prwssVillageViewDao;
	}

	public void setCreateProcPlanDao(CreateProcPlanDao createProcPlanDao) {
		this.createProcPlanDao = createProcPlanDao;
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean savePackageWorksFrom(PackageWorksForm packageWorksForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			String isPrwss=null;
			String packageId=null;
			String planType=null;
			PackageHeaderBean packageFindHeaderBean = new PackageHeaderBean();
			DocumentNumberBean documentNumebrBean=new DocumentNumberBean();
			documentNumebrBean.setDocumentType("PACKAGE");
			DocumentNumberBean documentNumberBean = documentNumberDao.getDocumentNumber(documentNumebrBean).get(0);
			if(!(MisUtility.ifEmpty(documentNumberBean))){
				throw new MISException();
			}
			Long docLastNo=documentNumberBean.getLastNumber();

			System.out.println("package id====="+packageWorksForm.getPackageId());
			packageFindHeaderBean.setPackageId(packageWorksForm.getPackageId());
			List<PackageHeaderBean> packageHeaderBeans = packageHeaderDao.findPackage(packageFindHeaderBean, null);
			if(!MisUtility.ifEmpty(packageHeaderBeans)){
				throw new MISException(MISExceptionCodes.MIS001, "Package Id\t"+packageWorksForm.getPackageId());			
			}
			List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
			
			SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
			schemeVillageBean.setSchemeId(packageWorksForm.getSchemeId());
			schemeVillageBeans=schemeVillageDao.findSchemeVillage(schemeVillageBean, null);
			SchemeVillageBean schemeVillageBean2=schemeVillageBeans.get(0);
			VillageBean villageBean=new VillageBean();
			villageBean.setVillageId(schemeVillageBean2.getVillageId());
			PrwssVillageViewBean prwssVillageViewBean=new PrwssVillageViewBean();
			System.out.println("1 Save:villageBean-->"+villageBean);
			List <PrwssVillageViewBean> prwssVillageViewBeans= prwssVillageViewDao.findPrwssVillageFromView(villageBean);
			System.out.println("2 Save:prwssVillageViewBeans-->"+prwssVillageViewBeans);
			if(!MisUtility.ifEmpty(prwssVillageViewBeans)){
			   isPrwss="PRWSS";
			
			}else{
				isPrwss="DWSS";
			}
			Set<CreateProcPlanBean> createProcPlanBeans = new TreeSet<CreateProcPlanBean>();
			createProcPlanBeans = createProcPlanDao.getProcPlanTypeOnPlanIds(packageWorksForm.getLocationId(), packageWorksForm.getPlanId());
			for (CreateProcPlanBean createProcPlanBean : createProcPlanBeans) {
				planType=createProcPlanBean.getPlanType();
			}			
			if(planType.equals(MISConstants.MIS_TYPE_OF_TENDER_GOODS)){
				planType = "G";
			}
			if(planType.equals(MISConstants.MIS_TYPE_OF_TENDER_WORKS)){
				planType= "W";
			}
			if(planType.equals(MISConstants.MIS_TYPE_OF_TENDER_CONSULTANCY)){
				planType = "S";
			}
			if(planType.equals(MISConstants.MIS_TYPE_OF_TENDER_SERVICES)){
				planType = "NC";
			}
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			long totalPackages=0;
		
			List<SubPlanSchemeBean> subPlanSchemeBeans = new ArrayList<SubPlanSchemeBean>();
			SubPlanSchemeBean subPlanSchemeBean = new SubPlanSchemeBean();
			
			if(MisUtility.ifEmpty(packageWorksForm.getSubPlanId())){
				subPlanSchemeBean.setSubPlanId(packageWorksForm.getSubPlanId());
				subPlanSchemeBean.setSchemeId(packageWorksForm.getSchemeId());
				
				subPlanSchemeBeans = procSubPlanSchemeDao.findSubPlanScheme(subPlanSchemeBean, statusList);
				totalPackages=subPlanSchemeBeans.get(0).getTotalPackages();
				}
			
			
			
			System.out.println("3 Save:SchemeId,SubPlanId-->"+packageWorksForm.getSchemeId()+","+packageWorksForm.getSubPlanId());
			Set<PackageHeaderBean> packageHeaderBeans2= new TreeSet<PackageHeaderBean>();
			packageHeaderBeans2=packageHeaderDao.getPackageIdsAll(packageWorksForm.getSchemeId(), packageWorksForm.getSubPlanId());
			
			List<String> statusList1 = new ArrayList<String>();
			statusList1.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList1.add(MISConstants.MASTER_STATUS_VERIFIED);
			
			Set<PackageHeaderBean> packageHeaderBeans3= new TreeSet<PackageHeaderBean>();
			packageHeaderBeans3=packageHeaderDao.getPackageIds(packageWorksForm.getSchemeId(),null,statusList1);
			
			
			System.out.println("4 Save:packageHeaderBeans2-->"+packageHeaderBeans2);
			int packegeNo=packageHeaderBeans2.size()+1;
			int packegeNo3=packageHeaderBeans3.size()+1;
			if(packegeNo3>totalPackages){
				throw new MISException(MISExceptionCodes.MIS014,"You can not create more packages for scheme id "+packageWorksForm.getSchemeId()+", since specified package limit for this scheme is "+totalPackages+".");
			}
			if(packegeNo==1){			
				packageId=isPrwss+"-"+planType+"-"+packageWorksForm.getLocationId()+"-"+docLastNo+"-"+packegeNo;
				boolean updateStatus = documentNumberDao.saveOrUpdateDocumentNumberBeans(documentNumberBean);
				if(!updateStatus){
					throw new MISException();
				}
			}else{
				String tempPackageId=null;
				for (PackageHeaderBean packageHeaderBean : packageHeaderBeans2) {
					tempPackageId=packageHeaderBean.getPackageId();
				}
			    System.out.println("5 Save:tempPackageId-->"+tempPackageId);
				String temp[]=tempPackageId.split("-");
			 
				String temp2="";
				System.out.println("length of ayyar"+temp.length);
				for (int i = 0; i <temp.length-1; i++) {
				
					temp2+=temp[i]+"-";
					System.out.println("temp2 split"+temp2);
				}
				System.out.println(temp2);
				temp2=temp2.substring(0, temp2.length()-1);
				 
				 
			 
				System.out.println("111111111new temp2"+temp2);
				System.out.println("6 Save:temp2-->"+temp2);                         
				packageId=temp2+"-"+packegeNo;
			}
		System.out.println("7 Save:packageId-->"+packageId);	
			packageWorksForm.setPackageId(packageId);
			PackageHeaderBean packageHeaderBean = populatePackageHeaderBean(packageWorksForm);
			PackageWorksBean packageWorksBean = populatePackageWorksBean(packageWorksForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
			packageHeaderBean.setMisAuditBean(misAuditBean);
			status = packageHeaderDao.savePackage(packageHeaderBean);
			if(status){
				if(MisUtility.ifEmpty(packageWorksBean)){
					packageWorksBean.setMisAuditBean(misAuditBean);
					boolean packageWorksStatus =packageWorksDao.saveWorksPackage(packageWorksBean);
					if(!packageWorksStatus){
						log.error(packageWorksBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Works details");
					}
					
				}
				List<PackageComponentsBean> packageComponentsBeans = populatePackageComponentsBeans(packageWorksForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				for(PackageComponentsBean packageComponentsBean:packageComponentsBeans){
					System.out.println("components name is==="+packageComponentsBean.getComponentName());
				}
					for(int i=0;i<packageComponentsBeans.size();i++){
						
						for(int j=i+1;j<packageComponentsBeans.size();j++){
							
							if((packageComponentsBeans.get(i).getComponentName()).equals(packageComponentsBeans.get(j).getComponentName())||(packageComponentsBeans.get(i).getComponentName()==packageComponentsBeans.get(j).getComponentName())){
								
								throw new MISException(MISExceptionCodes.MIS004, "Addition of duplicate components is not allowed");
								
							}
						}
					}
				
				if(!MisUtility.ifEmpty(packageComponentsBeans)){
					boolean packageCompponentStatus = packageComponentsDao.saveOrUpdateServicesPackageComponent(packageComponentsBeans);
					if(!packageCompponentStatus){
						log.error(packageComponentsBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Components details");
					}
				}
			}			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		
		
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updatePackageWorksFrom(PackageWorksForm packageWorksForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			PackageHeaderBean packageHeaderBean = populatePackageHeaderBean(packageWorksForm);
			PackageWorksBean packageWorksBean = populatePackageWorksBean(packageWorksForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
			packageHeaderBean.setMisAuditBean(misAuditBean);
			status = packageHeaderDao.updatePackage(packageHeaderBean);
			if(status){
				if(MisUtility.ifEmpty(packageWorksBean)){
					packageWorksBean.setMisAuditBean(misAuditBean);
					boolean packageWorksStatus = packageWorksDao.updateWorksPackage(packageWorksBean);
					if(!packageWorksStatus){
						log.error(packageWorksBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Service details");
					}
					
				}
				System.out.println("before call populate method in update====");
				List<PackageComponentsBean> packageComponentsBeans = populatePackageComponentsBeans(packageWorksForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				for(PackageComponentsBean packageComponentsBean:packageComponentsBeans){
					System.out.println("component name is==="+packageComponentsBean.getComponentName());
				}
				for(int i=0;i<packageComponentsBeans.size();i++){
					System.out.println("inside first loop   i====");
					for(int j=i+1;j<packageComponentsBeans.size();j++){
						System.out.println("inside second loop   j====");
						if(!(packageComponentsBeans.get(i).getMisAuditBean().getStatus().equals("D"))){
						if((packageComponentsBeans.get(i).getComponentName()).equals(packageComponentsBeans.get(j).getComponentName())||(packageComponentsBeans.get(i).getComponentName()==packageComponentsBeans.get(j).getComponentName())){
							System.out.println("inside inner for loop");
							throw new MISException(MISExceptionCodes.MIS004, "Addition of duplicate components is not allowed");
							
						}
					  }
					}
				}
				if(!MisUtility.ifEmpty(packageComponentsBeans)){
					System.out.println("before update save =====");
					boolean packageCompponentStatus = packageComponentsDao.saveOrUpdateServicesPackageComponent(packageComponentsBeans);
					if(!packageCompponentStatus){
						log.error(packageComponentsBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Package Components details");
					}
				}
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deletePackageWorksFrom(PackageWorksForm packageWorksForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			PackageHeaderBean packageHeaderBean = populatePackageHeaderBean(packageWorksForm);
			PackageWorksBean packageWorksBean = populatePackageWorksBean(packageWorksForm);
			String packageId = packageWorksForm.getPackageId();
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED); 
			packageHeaderBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			status = packageHeaderDao.updatePackage(packageHeaderBean);
			if(status){
				if(MisUtility.ifEmpty(packageWorksBean)){
					packageWorksBean.setMisAuditBean(misAuditBean);
					boolean packageWorksStatus = packageWorksDao.updateWorksPackage(packageWorksBean);
					if(!packageWorksStatus){
						log.error(packageWorksBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Package Works details");
					}
					
				}
				PackageComponentsBean packageComponentsBean = new PackageComponentsBean();
				packageComponentsBean.setPackageId(packageId);
					List<PackageComponentsBean> packageComponentsBeans = packageComponentsDao.findServicesPackageComponent(packageComponentsBean, statusList);
					if(!MisUtility.ifEmpty(packageComponentsBeans)){
						for (PackageComponentsBean packageComponentsBean2 : packageComponentsBeans) {
							misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
							packageComponentsBean2.setMisAuditBean(misAuditBean);
						}
						boolean packageCompponentStatus = packageComponentsDao.saveOrUpdateServicesPackageComponent(packageComponentsBeans);
						if(!packageCompponentStatus){
							log.error(packageComponentsBeans);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Components details");
						}
					}
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postPackageWorksFrom(PackageWorksForm packageWorksForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
			packageHeaderBean.setPackageId(packageWorksForm.getPackageId().trim());
			packageHeaderBean.setLocationId(packageWorksForm.getLocationId());
			packageHeaderBean= packageHeaderDao.findPackage(packageHeaderBean, statusList).get(0);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean = packageHeaderBean.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			packageHeaderBean.setMisAuditBean(misAuditBean);			
			status = packageHeaderDao.updatePackage(packageHeaderBean);
			if(!status){
				log.error(packageHeaderBean);
				throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Package details");
			}else{
			PackageWorksBean packageWorksBean = new PackageWorksBean();
			packageWorksBean.setPackageId(packageWorksForm.getPackageId());
			packageWorksBean = packageWorksDao.findWorksPackage(packageWorksBean, statusList).get(0);
				if(MisUtility.ifEmpty(packageWorksBean)){
					misAuditBean = packageWorksBean.getMisAuditBean();
					misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
					misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
					packageWorksBean.setMisAuditBean(misAuditBean);
					boolean packageWorksStatus = packageWorksDao.updateWorksPackage(packageWorksBean);
					if(!packageWorksStatus){
						log.error(packageWorksBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Package Service details");
					}
					
				}
			
				PackageComponentsBean packageComponentsBean = new PackageComponentsBean();
				packageComponentsBean.setPackageId(packageWorksForm.getPackageId());
					List<PackageComponentsBean> packageComponentsBeans = packageComponentsDao.findServicesPackageComponent(packageComponentsBean, statusList);
					if(!MisUtility.ifEmpty(packageComponentsBeans)){
						for (PackageComponentsBean packageComponentsBean2 : packageComponentsBeans) {
							misAuditBean = packageComponentsBean2.getMisAuditBean();
							misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
							misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
							misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
							packageComponentsBean2.setMisAuditBean(misAuditBean);
						}
						boolean packageCompponentStatus = packageComponentsDao.saveOrUpdateServicesPackageComponent(packageComponentsBeans);
						if(!packageCompponentStatus){
							log.error(packageComponentsBeans);
							throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Package Components details");
						}
					}
			
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		
		
		return status;
	}
	
	private PackageWorksBean populatePackageWorksBean(PackageWorksForm packageWorksForm){
		PackageWorksBean packageWorksBean = new PackageWorksBean();
		packageWorksBean.setMethodOfProcurement(packageWorksForm.getMethodOfProcurement());
		packageWorksBean.setPrepBidDocDate(MisUtility.convertStringToDate(packageWorksForm.getPrepBidDocDate()));
		packageWorksBean.setDesignInvestigationDate(MisUtility.convertStringToDate(packageWorksForm.getDesignInvestigationDate()));
		packageWorksBean.setEstPreparedSanctionDate(MisUtility.convertStringToDate(packageWorksForm.getEstPreparedSanctionDate()));
		packageWorksBean.setBankNocBidDate(MisUtility.convertStringToDate(packageWorksForm.getBankNocBidDate()));
		packageWorksBean.setBidOpeningDate(MisUtility.convertStringToDate(packageWorksForm.getBidOpeningDate()));
		packageWorksBean.setBidInvitationDate(MisUtility.convertStringToDate(packageWorksForm.getBidInvitationDate()));
		packageWorksBean.setConAwardDecideDate(MisUtility.convertStringToDate(packageWorksForm.getConAwardDecideDate()));
		packageWorksBean.setBankNocConAwardDate(MisUtility.convertStringToDate(packageWorksForm.getConAwardDecideDate()));
		packageWorksBean.setConSignDate(MisUtility.convertStringToDate(packageWorksForm.getConSignDate()));
		packageWorksBean.setConCompletionDate(MisUtility.convertStringToDate(packageWorksForm.getConCompletionDate()));
		packageWorksBean.setPackageId(packageWorksForm.getPackageId().trim());
		packageWorksBean.setTargetCommissioningDate(MisUtility.convertStringToDate(packageWorksForm.getTargetCommissioningDate()));
		return packageWorksBean;
	}
	

	private PackageHeaderBean populatePackageHeaderBean(PackageWorksForm packageWorksForm){
		PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
		packageHeaderBean.setLocationId(packageWorksForm.getLocationId());
		packageHeaderBean.setPackageId(packageWorksForm.getPackageId().trim());
		packageHeaderBean.setSubPlanId(packageWorksForm.getSubPlanId());
		packageHeaderBean.setSchemeId(packageWorksForm.getSchemeId());
		packageHeaderBean.setPlanId(packageWorksForm.getPlanId());
		packageHeaderBean.setPackageDescription(packageWorksForm.getPackageDescription().trim());
		packageHeaderBean.setEstimatePackageCost(packageWorksForm.getEstimatePackageCost());
		packageHeaderBean.setPackageType(MISConstants.MIS_TYPE_OF_TENDER_WORKS);
		packageHeaderBean.setPostPriorStatus(packageWorksForm.getPostPriorStatus());
		packageHeaderBean.setAnticipatedExpenditureCost(packageWorksForm.getAnticipatedExpenditureCost());
		packageHeaderBean.setVillageId(packageWorksForm.getVillageId());
		return packageHeaderBean;
		
	}
	
	@SuppressWarnings("unchecked")
	private List<PackageComponentsBean> populatePackageComponentsBeans(PackageWorksForm packageWorksForm, MISSessionBean misSessionBean, String status){
		List <PackageComponentsBean> packageComponentsBeans = new ArrayList<PackageComponentsBean>();
		PackageComponentsBean packageComponentsBean=null;
		try {
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
			
			
			
			Collection<PackageWorksGridBean> addedPackageComponentsBeans = packageWorksForm.getPackageComponentsDatagrid().getAddedData();
			if(!MisUtility.ifEmpty(addedPackageComponentsBeans)){
				System.out.println("in added grid=====");
				for (PackageWorksGridBean packageComponentsBeanss : addedPackageComponentsBeans) {
					System.out.println("in add==="+packageComponentsBeanss.getComponentName());
					
					packageComponentsBean=new PackageComponentsBean();
					packageComponentsBean.setPackageId(packageWorksForm.getPackageId().trim());
					packageComponentsBean.setComponentName(packageComponentsBeanss.getComponentName());
					packageComponentsBean.setId(packageComponentsBeanss.getId());
					packageComponentsBean.setMisAuditBean(misAuditBean1);
					packageComponentsBeans.add(packageComponentsBean);
					 
				}
			}
			
			Collection<PackageWorksGridBean> modifiedPackageComponentsBeans = packageWorksForm.getPackageComponentsDatagrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedPackageComponentsBeans)){
				System.out.println("in modify grid=====");
				for (PackageWorksGridBean packageComponentsBeanss : modifiedPackageComponentsBeans) {
					System.out.println("in modify==="+packageComponentsBeanss.getComponentName());
					
					packageComponentsBean=new PackageComponentsBean();
					packageComponentsBean.setPackageId(packageWorksForm.getPackageId().trim());
					packageComponentsBean.setId(packageComponentsBeanss.getId());
					packageComponentsBean.setComponentName(packageComponentsBeanss.getComponentName());
					packageComponentsBean.setMisAuditBean(misAuditBean2);
					packageComponentsBeans.add(packageComponentsBean);
				}
			}
			
			Collection<PackageWorksGridBean> deletedPackageComponentsBeans = packageWorksForm.getPackageComponentsDatagrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedPackageComponentsBeans)){
				System.out.println("in delete  grid=====");
				for (PackageWorksGridBean packageComponentsBeanss : deletedPackageComponentsBeans) {
					System.out.println("in delete==="+packageComponentsBeanss.getComponentName());
					packageComponentsBean=new PackageComponentsBean();
					packageComponentsBean.setPackageId(packageWorksForm.getPackageId().trim());
					packageComponentsBean.setComponentName(packageComponentsBeanss.getComponentName());
					packageComponentsBean.setId(packageComponentsBeanss.getId());
					MISAuditBean misAuditBean4 = new MISAuditBean();
					misAuditBean4.setEntBy(misSessionBean.getEnteredBy());
					misAuditBean4.setEntDate(misSessionBean.getEnteredDate());
					misAuditBean4.setStatus(MISConstants.MASTER_STATUS_DELETED);
					
					//misAuditBean3.setStatus(MISConstants.MASTER_STATUS_DELETED);
					packageComponentsBean.setMisAuditBean(misAuditBean4);
					packageComponentsBeans.add(packageComponentsBean);
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		
		return packageComponentsBeans;
	}
	
}
