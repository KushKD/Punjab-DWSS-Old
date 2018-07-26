package com.prwss.mis.procurement.nonconsultancypackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import com.prwss.mis.procurement.nonconsultancypackage.dao.PackageNonConsultancyDao;
import com.prwss.mis.procurement.nonconsultancypackage.struts.PackageNonConsultancyForm;
import com.prwss.mis.procurement.packagecomponents.dao.PackageComponentsBean;
import com.prwss.mis.procurement.packagecomponents.dao.PackageComponentsDao;
import com.prwss.mis.procurement.packageheader.dao.PackageDetailBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderDao;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;

public class PackageNonConsultancyBOImpl implements PackageNonConsultancyBO {
	
	private Logger log = Logger.getLogger(PackageNonConsultancyBOImpl.class);
	private PackageNonConsultancyDao packageNonConsultancyDao;
	private PackageComponentsDao packageComponentsDao;
	private PackageHeaderDao packageHeaderDao;
	private DocumentNumberDAO documentNumberDao;
	
	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}
	
	public void setPackageNonConsultancyDao(
			PackageNonConsultancyDao packageNonConsultancyDao) {
		this.packageNonConsultancyDao = packageNonConsultancyDao;
	}

	public void setPackageComponentsDao(PackageComponentsDao packageComponentsDao) {
		this.packageComponentsDao = packageComponentsDao;
	}

	public void setPackageHeaderDao(PackageHeaderDao packageHeaderDao) {
		this.packageHeaderDao = packageHeaderDao;
	}

	@Override
	public PackageDetailBean findPackageNonConsltFrom(
			PackageNonConsultancyForm packageNonConsultancyForm,
			List<String> statusList) throws MISException {
		List<PackageNonConsultancyBean> packageNonConsultancyBeans = null;
		List<PackageHeaderBean> packageHeaderBeans = null;
		List<PackageComponentsBean> packageComponentsBeans = null;
		PackageDetailBean packageDetailBean = new PackageDetailBean();
		try {
			
			PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
			packageHeaderBean.setLocationId(packageNonConsultancyForm.getLocationId());
			packageHeaderBean.setPackageId(packageNonConsultancyForm.getPackageId().trim());
			packageHeaderBean.setPackageType(MISConstants.MIS_TYPE_OF_TENDER_SERVICES);
			packageHeaderBeans = packageHeaderDao.findPackage(packageHeaderBean, statusList);
			
			PackageNonConsultancyBean packageNonConsultancyBean = new PackageNonConsultancyBean();
			packageNonConsultancyBean.setPackageId(packageNonConsultancyForm.getPackageId());
			
			PackageComponentsBean packageComponentsBean = new PackageComponentsBean();
			packageComponentsBean.setPackageId(packageNonConsultancyForm.getPackageId());
			
			if(!MisUtility.ifEmpty(packageHeaderBeans)){
				packageNonConsultancyBeans = packageNonConsultancyDao.findNonConsltPackage(packageNonConsultancyBean, statusList);
				packageComponentsBeans = packageComponentsDao.findServicesPackageComponent(packageComponentsBean, statusList);
			}
			packageDetailBean.setPackageHeaderBeans(packageHeaderBeans);
			packageDetailBean.setPackageNonConsultancyBeans(packageNonConsultancyBeans);
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

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean savePackageNonConsltFrom(
			PackageNonConsultancyForm packageNonConsultancyForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		String packageId=null;
		try {
			PackageHeaderBean packageFindHeaderBean = new PackageHeaderBean();
			DocumentNumberBean documentNumebrBean=new DocumentNumberBean();
			documentNumebrBean.setDocumentType("PACKAGE");
			DocumentNumberBean documentNumberBean = documentNumberDao.getDocumentNumber(documentNumebrBean).get(0);
			if(!(MisUtility.ifEmpty(documentNumberBean))){
				throw new MISException();
			}
			Long docLastNo=documentNumberBean.getLastNumber();

			
			System.out.println("package id==="+packageNonConsultancyForm.getPackageId());
			packageNonConsultancyForm.setPackageId(packageNonConsultancyForm.getPackageId());
			List<PackageHeaderBean> packageHeaderBeans = packageHeaderDao.findPackage(packageFindHeaderBean, null);
			if(!MisUtility.ifEmpty(packageHeaderBeans)){
				throw new MISException(MISExceptionCodes.MIS001, "Package Id\t"+packageNonConsultancyForm.getPackageId());			
			}
			
			System.out.println("3 Save:SchemeId,SubPlanId-->"+packageNonConsultancyForm.getSchemeId()+","+packageNonConsultancyForm.getSubPlanId());
			Set<PackageHeaderBean> packageHeaderBeans2= new TreeSet<PackageHeaderBean>();
			packageHeaderBeans2=packageHeaderDao.getPackageIdsAll(packageNonConsultancyForm.getSchemeId(), packageNonConsultancyForm.getSubPlanId());
			
			int packageNo=packageHeaderBeans2.size()+1;
			
		//	packageId="PRWSS-S"+"-"+packageNonConsultancyForm.getLocationId()+"-"+docLastNo+"-"+packageNo;
			
			//packageNonConsultancyForm.setPackageId(packageId);
		//	packageFindHeaderBean.setPackageId(packageNonConsultancyForm.getPackageId());
			if(packageNo==1){			
				packageId="PRWSS-S"+"-"+packageNonConsultancyForm.getLocationId()+"-"+docLastNo+"-"+packageNo;
				boolean updateStatus = documentNumberDao.saveOrUpdateDocumentNumberBeans(documentNumberBean);
				if(!updateStatus){
					throw new MISException();
				}
			}else{
				String tempPackageId=null;
				for (PackageHeaderBean packageHeaderBean : packageHeaderBeans2) {
					tempPackageId=packageHeaderBean.getPackageId();
				}
			//	System.out.println("5 Save:tempPackageId-->"+tempPackageId);
				String temp[]=tempPackageId.split("-");
			 
				String temp2="";
				/*for (int i = 0; i <temp.length-1; i++) {
				
					temp2+=temp[i]+"-";
				}*/
				if(temp.length==4){
					for (int i = 0; i <=temp.length-1; i++) {
						System.out.println("value in for loop temp1===="+temp[i]);
						temp2+=temp[i]+"-";
						System.out.println("value in for loop in temp2===="+temp2);
						
					}
				}
					if(temp.length>4){
						for (int i = 0; i <temp.length-1; i++) {
							System.out.println("value in for loop temp1===="+temp[i]);
							temp2+=temp[i]+"-";
							System.out.println("value in for loop in temp2===="+temp2);
							
						}
					}
				temp2=temp2.substring(0, temp2.length()-1);
				packageId=temp2+"-"+packageNo;
			}
			System.out.println("7 Save:packageId-->"+packageId);	
			packageNonConsultancyForm.setPackageId(packageId);
			
//			packageFindHeaderBean.setLocationId(packageNonConsultancyForm.getLocationId());
			
			PackageHeaderBean packageHeaderBean = populatePackageHeaderBean(packageNonConsultancyForm);
			PackageNonConsultancyBean packageNonConsultancyBean = populatePackageNonConsltBean(packageNonConsultancyForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
			packageHeaderBean.setMisAuditBean(misAuditBean);
			status = packageHeaderDao.savePackage(packageHeaderBean);
			if(status){
				if(MisUtility.ifEmpty(packageNonConsultancyBean)){
					packageNonConsultancyBean.setMisAuditBean(misAuditBean);
					boolean packageNonConsultStatus =packageNonConsultancyDao.saveNonConsltPackage(packageNonConsultancyBean);
					if(!packageNonConsultStatus){
						log.error(packageNonConsultStatus);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Works details");
					}
					
				}
				List<PackageComponentsBean> packageComponentsBeans = populatePackageComponentsBeans(packageNonConsultancyForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(packageComponentsBeans)){
					boolean packageCompponentStatus = packageComponentsDao.saveOrUpdateServicesPackageComponent(packageComponentsBeans);
					if(!packageCompponentStatus){
						log.error(packageComponentsBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Components details");
					}
				}
				boolean updateStatus = documentNumberDao.saveOrUpdateDocumentNumberBeans(documentNumberBean);
				if(!updateStatus){
					throw new MISException();
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
	public boolean updatePackageNonConsltFrom(
			PackageNonConsultancyForm packageNonConsultancyForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			PackageHeaderBean packageHeaderBean = populatePackageHeaderBean(packageNonConsultancyForm);
			PackageNonConsultancyBean packageNonConsultancyBean = populatePackageNonConsltBean(packageNonConsultancyForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
			packageHeaderBean.setMisAuditBean(misAuditBean);
			status = packageHeaderDao.updatePackage(packageHeaderBean);
			if(status){
				if(MisUtility.ifEmpty(packageNonConsultancyBean)){
					packageNonConsultancyBean.setMisAuditBean(misAuditBean);
					boolean packageNonConsltStatus = packageNonConsultancyDao.updateNonConsltPackage(packageNonConsultancyBean);
					if(!packageNonConsltStatus){
						log.error(packageNonConsultancyBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Non Consultancy details");
					}
					
				}
				List<PackageComponentsBean> packageComponentsBeans = populatePackageComponentsBeans(packageNonConsultancyForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
				if(!MisUtility.ifEmpty(packageComponentsBeans)){
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
	public boolean deletePackageNonConsltFrom(
			PackageNonConsultancyForm packageNonConsultancyForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			PackageHeaderBean packageHeaderBean = populatePackageHeaderBean(packageNonConsultancyForm);
			PackageNonConsultancyBean packageNonConsultancyBean = populatePackageNonConsltBean(packageNonConsultancyForm);
			String packageId = packageNonConsultancyForm.getPackageId();
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED); 
			packageHeaderBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			status = packageHeaderDao.updatePackage(packageHeaderBean);
			if(status){
				if(MisUtility.ifEmpty(packageNonConsultancyBean)){
					packageNonConsultancyBean.setMisAuditBean(misAuditBean);
					boolean packageNonConsltStatus = packageNonConsultancyDao.updateNonConsltPackage(packageNonConsultancyBean);
					if(!packageNonConsltStatus){
						log.error(packageNonConsultancyBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Package non consultancy details");
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
	public boolean postPackageNonConsltFrom(
			PackageNonConsultancyForm packageNonConsultancyForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
			packageHeaderBean.setPackageId(packageNonConsultancyForm.getPackageId());
			packageHeaderBean.setLocationId(packageNonConsultancyForm.getLocationId());
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
			PackageNonConsultancyBean packageNonConsultancyBean = new PackageNonConsultancyBean();
			packageNonConsultancyBean.setPackageId(packageNonConsultancyForm.getPackageId());
			packageNonConsultancyBean = packageNonConsultancyDao.findNonConsltPackage(packageNonConsultancyBean, statusList).get(0);
				if(MisUtility.ifEmpty(packageNonConsultancyBean)){
					misAuditBean = packageNonConsultancyBean.getMisAuditBean();
					misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
					misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
					packageNonConsultancyBean.setMisAuditBean(misAuditBean);
					boolean packageNonConsltStatus = packageNonConsultancyDao.updateNonConsltPackage(packageNonConsultancyBean);
					if(!packageNonConsltStatus){
						log.error(packageNonConsultancyBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Package Non Consultancy details");
					}
					
				}
			
				PackageComponentsBean packageComponentsBean = new PackageComponentsBean();
				packageComponentsBean.setPackageId(packageNonConsultancyForm.getPackageId());
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
	
	private PackageNonConsultancyBean populatePackageNonConsltBean(PackageNonConsultancyForm packageNonConsultancyForm){
		PackageNonConsultancyBean packageNonConsultancyBean = new PackageNonConsultancyBean();
		packageNonConsultancyBean.setMethodOfProcurement(packageNonConsultancyForm.getMethodOfProcurement());
		packageNonConsultancyBean.setDesignInvestigationDate(MisUtility.convertStringToDate(packageNonConsultancyForm.getDesignInvestigationDate()));
		packageNonConsultancyBean.setEstPreparedSanctionDate(MisUtility.convertStringToDate(packageNonConsultancyForm.getEstPreparedSanctionDate()));
		packageNonConsultancyBean.setBankNocBidDate(MisUtility.convertStringToDate(packageNonConsultancyForm.getBankNocBidDate()));
		packageNonConsultancyBean.setBidOpeningDate(MisUtility.convertStringToDate(packageNonConsultancyForm.getBidOpeningDate()));
		packageNonConsultancyBean.setBidInvitationDate(MisUtility.convertStringToDate(packageNonConsultancyForm.getBidInvitationDate()));
		packageNonConsultancyBean.setConAwardDecideDate(MisUtility.convertStringToDate(packageNonConsultancyForm.getConAwardDecideDate()));
		packageNonConsultancyBean.setPrepBidDocDate(MisUtility.convertStringToDate(packageNonConsultancyForm.getPrepBidDocDate()));
		packageNonConsultancyBean.setBankNocConAwardDate(MisUtility.convertStringToDate(packageNonConsultancyForm.getConAwardDecideDate()));
		packageNonConsultancyBean.setConSignDate(MisUtility.convertStringToDate(packageNonConsultancyForm.getConSignDate()));
		packageNonConsultancyBean.setConCompletionDate(MisUtility.convertStringToDate(packageNonConsultancyForm.getConCompletionDate()));
		packageNonConsultancyBean.setPackageId(packageNonConsultancyForm.getPackageId().trim());
		return packageNonConsultancyBean;
	}
	

	private PackageHeaderBean populatePackageHeaderBean(PackageNonConsultancyForm packageNonConsultancyForm){
		PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
		packageHeaderBean.setLocationId(packageNonConsultancyForm.getLocationId());
		packageHeaderBean.setPackageId(packageNonConsultancyForm.getPackageId().trim());
		packageHeaderBean.setSubPlanId(packageNonConsultancyForm.getSubPlanId());
		packageHeaderBean.setSchemeId(packageNonConsultancyForm.getSchemeId());
		packageHeaderBean.setPlanId(packageNonConsultancyForm.getPlanId());
		packageHeaderBean.setPackageDescription(packageNonConsultancyForm.getPackageDescription().trim());
		packageHeaderBean.setEstimatePackageCost(packageNonConsultancyForm.getEstimatePackageCost());
		packageHeaderBean.setPackageType(MISConstants.MIS_TYPE_OF_TENDER_SERVICES);
		packageHeaderBean.setPostPriorStatus(packageNonConsultancyForm.getPostPriorStatus());
		packageHeaderBean.setWingId(packageNonConsultancyForm.getWingId());
		return packageHeaderBean;
		
	}
	
	@SuppressWarnings("unchecked")
	private List<PackageComponentsBean> populatePackageComponentsBeans(PackageNonConsultancyForm packageNonConsultancyForm, MISSessionBean misSessionBean, String status){
		List <PackageComponentsBean> packageComponentsBeans = new ArrayList<PackageComponentsBean>();
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
			
			
			
			Collection<PackageComponentsBean> addedPackageComponentsBeans = packageNonConsultancyForm.getPackageComponentsDatagrid().getAddedData();
			if(!MisUtility.ifEmpty(addedPackageComponentsBeans)){
				for (PackageComponentsBean packageComponentsBean : addedPackageComponentsBeans) {
					packageComponentsBean.setPackageId(packageNonConsultancyForm.getPackageId().trim());
					packageComponentsBean.setMisAuditBean(misAuditBean1);
					packageComponentsBeans.add(packageComponentsBean);
				}
			}
			
			Collection<PackageComponentsBean> modifiedPackageComponentsBeans = packageNonConsultancyForm.getPackageComponentsDatagrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedPackageComponentsBeans)){
				for (PackageComponentsBean packageComponentsBean : addedPackageComponentsBeans) {
					packageComponentsBean.setPackageId(packageNonConsultancyForm.getPackageId().trim());
					packageComponentsBean.setMisAuditBean(misAuditBean2);
					packageComponentsBeans.add(packageComponentsBean);
				}
			}
			
			Collection<PackageComponentsBean> deletedPackageComponentsBeans = packageNonConsultancyForm.getPackageComponentsDatagrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedPackageComponentsBeans)){
				for (PackageComponentsBean packageComponentsBean : addedPackageComponentsBeans) {
					packageComponentsBean.setPackageId(packageNonConsultancyForm.getPackageId().trim());
					misAuditBean3.setStatus(MISConstants.MASTER_STATUS_DELETED);
					packageComponentsBean.setMisAuditBean(misAuditBean3);
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
