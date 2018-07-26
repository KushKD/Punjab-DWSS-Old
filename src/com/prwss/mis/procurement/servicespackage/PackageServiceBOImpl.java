package com.prwss.mis.procurement.servicespackage;

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
import com.prwss.mis.procurement.packagecomponents.dao.PackageComponentsBean;
import com.prwss.mis.procurement.packagecomponents.dao.PackageComponentsDao;
import com.prwss.mis.procurement.packageheader.dao.PackageDetailBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderDao;
import com.prwss.mis.procurement.servicespackage.dao.PackageServicesDao;
import com.prwss.mis.procurement.servicespackage.struts.PackageServiceForm;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;

public class PackageServiceBOImpl implements PackageServiceBO {
	private Logger log = Logger.getLogger(PackageServiceBOImpl.class);
	private PackageServicesDao packageServicesDao;
	private PackageComponentsDao packageComponentsDao;
	private PackageHeaderDao packageHeaderDao;
	private DocumentNumberDAO documentNumberDao;
	
	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}
	public void setPackageServicesDao(PackageServicesDao packageServicesDao) {
		this.packageServicesDao = packageServicesDao;
	}

	public void setPackageComponentsDao(PackageComponentsDao packageComponentsDao) {
		this.packageComponentsDao = packageComponentsDao;
	}

	public void setPackageHeaderDao(PackageHeaderDao packageHeaderDao) {
		this.packageHeaderDao = packageHeaderDao;
	}

	@Override
	public PackageDetailBean findPackageServicesFrom(
			PackageServiceForm packageServiceForm, List<String> statusList)
			throws MISException {
		List<PackageServicesBean> packageServicesBeans = null;
		List<PackageHeaderBean> packageHeaderBeans = null;
		List<PackageComponentsBean> packageComponentsBeans = null;
		PackageDetailBean packageDetailBean = new PackageDetailBean();
		try {
			
			PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
			packageHeaderBean.setLocationId(packageServiceForm.getLocationId());
			packageHeaderBean.setPackageId(packageServiceForm.getPackageId().trim());
			packageHeaderBean.setPackageType(MISConstants.MIS_TYPE_OF_TENDER_CONSULTANCY);
			packageHeaderBeans = packageHeaderDao.findPackage(packageHeaderBean, statusList);
			
			PackageServicesBean packageServicesBean = new PackageServicesBean();
			packageServicesBean.setPackageId(packageServiceForm.getPackageId());
			
			PackageComponentsBean packageComponentsBean = new PackageComponentsBean();
			packageComponentsBean.setPackageId(packageServiceForm.getPackageId());
			
			if(!MisUtility.ifEmpty(packageHeaderBeans)){
				packageServicesBeans = packageServicesDao.findServicesPackage(packageServicesBean, statusList);
				packageComponentsBeans = packageComponentsDao.findServicesPackageComponent(packageComponentsBean, statusList);
			}
			packageDetailBean.setPackageHeaderBeans(packageHeaderBeans);
			packageDetailBean.setPackageServicesBeans(packageServicesBeans);
			packageDetailBean.setPackageComponentsBeans(packageComponentsBeans);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return packageDetailBean;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean savePackageServicesFrom(
			PackageServiceForm packageServiceForm, MISSessionBean misSessionBean)
			throws MISException {
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

			
			System.out.println("package id==="+packageServiceForm.getPackageId());
			packageServiceForm.setPackageId(packageServiceForm.getPackageId());
			List<PackageHeaderBean> packageHeaderBeans = packageHeaderDao.findPackage(packageFindHeaderBean, null);
			if(!MisUtility.ifEmpty(packageHeaderBeans)){
				throw new MISException(MISExceptionCodes.MIS001, "Package Id\t"+packageServiceForm.getPackageId());			
			}
			
			System.out.println("3 Save:SchemeId,SubPlanId-->"+packageServiceForm.getSchemeId()+","+packageServiceForm.getSubPlanId());
			Set<PackageHeaderBean> packageHeaderBeans2= new TreeSet<PackageHeaderBean>();
			packageHeaderBeans2=packageHeaderDao.getPackageIdsAll(packageServiceForm.getSchemeId(), packageServiceForm.getSubPlanId());
			
			int packageNo=packageHeaderBeans2.size()+1;
			
			
			//packageId="PRWSS-C"+"-"+packageServiceForm.getLocationId()+"-"+docLastNo+"-"+packageNo;
			
			///packageServiceForm.setPackageId(packageId);
			System.out.println("packageNo===="+packageNo);
			
			
			if(packageNo==1){	
					
						packageId="PRWSS-C"+"-"+packageServiceForm.getLocationId()+"-"+docLastNo+"-"+packageNo;
					
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
			packageServiceForm.setPackageId(packageId);
			
			//packageFindHeaderBean.setPackageId(packageServiceForm.getPackageId());
//			packageFindHeaderBean.setLocationId(packageServiceForm.getLocationId());
			
			PackageHeaderBean packageHeaderBean = populatePackageHeaderBean(packageServiceForm);
			PackageServicesBean packageServicesBean = populateServicesBean(packageServiceForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
			//for(PackageHeaderBean packageHeaderBeanss:packageHeaderBean ){
			packageHeaderBean.setMisAuditBean(misAuditBean);
			//}
			status = packageHeaderDao.savePackage(packageHeaderBean);
			if(status){
				if(MisUtility.ifEmpty(packageServicesBean)){
					packageServicesBean.setMisAuditBean(misAuditBean);
					System.out.println("before save of consul====");
					boolean packageServiceStatus = packageServicesDao.saveServicesPackage(packageServicesBean);
					if(!packageServiceStatus){
						log.error(packageServicesBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Service details");
					}
					
				}
				List<PackageComponentsBean> packageComponentsBeans = populatePackageComponentsBeans(packageServiceForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
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
	public boolean updatePackageServicesFrom(
			PackageServiceForm packageServiceForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status = false;
		try {
			PackageHeaderBean packageHeaderBean = populatePackageHeaderBean(packageServiceForm);
			PackageServicesBean packageServicesBean = populateServicesBean(packageServiceForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
			packageHeaderBean.setMisAuditBean(misAuditBean);
			
			status = packageHeaderDao.updatePackage(packageHeaderBean);
			if(status){
				if(MisUtility.ifEmpty(packageServicesBean)){
					packageServicesBean.setMisAuditBean(misAuditBean);
					boolean packageServiceStatus = packageServicesDao.updateServicesPackage(packageServicesBean);
					if(!packageServiceStatus){
						log.error(packageServicesBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Service details");
					}
					
				}
				List<PackageComponentsBean> packageComponentsBeans = populatePackageComponentsBeans(packageServiceForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
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
	public boolean deletePackageServicesFrom(
			PackageServiceForm packageServiceForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status = false;
		try {
			PackageHeaderBean packageHeaderBean = populatePackageHeaderBean(packageServiceForm);
			PackageServicesBean packageServicesBean = populateServicesBean(packageServiceForm);
			String packageId = packageServiceForm.getPackageId();
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED); 
			packageHeaderBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			status = packageHeaderDao.updatePackage(packageHeaderBean);
			if(status){
				if(MisUtility.ifEmpty(packageServicesBean)){
					packageServicesBean.setMisAuditBean(misAuditBean);
					boolean packageServiceStatus = packageServicesDao.updateServicesPackage(packageServicesBean);
					if(!packageServiceStatus){
						log.error(packageServicesBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Service details");
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
	public boolean postPackageServicesFrom(
			PackageServiceForm packageServiceForm, MISSessionBean misSessionBean)
			throws MISException {
		boolean status = false;
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
			packageHeaderBean.setPackageId(packageServiceForm.getPackageId());
			packageHeaderBean.setLocationId(packageServiceForm.getLocationId());
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
			PackageServicesBean packageServicesBean = new PackageServicesBean();
			packageServicesBean.setPackageId(packageServiceForm.getPackageId());
			packageServicesBean = packageServicesDao.findServicesPackage(packageServicesBean, statusList).get(0);
				if(MisUtility.ifEmpty(packageServicesBean)){
					misAuditBean = packageServicesBean.getMisAuditBean();
					misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
					misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
					packageServicesBean.setMisAuditBean(misAuditBean);
					boolean packageServiceStatus = packageServicesDao.updateServicesPackage(packageServicesBean);
					if(!packageServiceStatus){
						log.error(packageServicesBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Package Service details");
					}
					
				}
			
				PackageComponentsBean packageComponentsBean = new PackageComponentsBean();
				packageComponentsBean.setPackageId(packageServiceForm.getPackageId());
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
	
	@SuppressWarnings("unchecked")
	private List<PackageComponentsBean> populatePackageComponentsBeans(PackageServiceForm packageServiceForm, MISSessionBean misSessionBean, String status){
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
			
			
			
			Collection<PackageComponentsBean> addedPackageComponentsBeans = packageServiceForm.getPackageComponentsDatagrid().getAddedData();
			if(!MisUtility.ifEmpty(addedPackageComponentsBeans)){
				for (PackageComponentsBean packageComponentsBean : addedPackageComponentsBeans) {
					packageComponentsBean.setPackageId(packageServiceForm.getPackageId().trim());
					packageComponentsBean.setMisAuditBean(misAuditBean1);
					packageComponentsBeans.add(packageComponentsBean);
				}
			}
			
			Collection<PackageComponentsBean> modifiedPackageComponentsBeans = packageServiceForm.getPackageComponentsDatagrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedPackageComponentsBeans)){
				for (PackageComponentsBean packageComponentsBean : modifiedPackageComponentsBeans) {
					packageComponentsBean.setPackageId(packageServiceForm.getPackageId().trim());
					packageComponentsBean.setMisAuditBean(misAuditBean2);
					packageComponentsBeans.add(packageComponentsBean);
				}
			}
			
			Collection<PackageComponentsBean> deletedPackageComponentsBeans = packageServiceForm.getPackageComponentsDatagrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedPackageComponentsBeans)){
				for (PackageComponentsBean packageComponentsBean : deletedPackageComponentsBeans) {
					packageComponentsBean.setPackageId(packageServiceForm.getPackageId().trim());
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
	
	private List<PackageHeaderBean> populatePackageHeaderBeans(PackageServiceForm packageServiceForm){
		List<PackageHeaderBean> packageHeaderBeans=new ArrayList<PackageHeaderBean>();
		PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
		packageHeaderBean.setLocationId(packageServiceForm.getLocationId());
		packageHeaderBean.setPackageId(packageServiceForm.getPackageId().trim());
		packageHeaderBean.setSubPlanId(packageServiceForm.getSubPlanId());
		packageHeaderBean.setSchemeId(packageServiceForm.getSchemeId());
		packageHeaderBean.setPlanId(packageServiceForm.getPlanId());
		packageHeaderBean.setPackageDescription(packageServiceForm.getPackageDescription().trim());
		packageHeaderBean.setEstimatePackageCost(packageServiceForm.getEstimatePackageCost());
		packageHeaderBean.setPackageType(MISConstants.MIS_TYPE_OF_TENDER_CONSULTANCY);
		packageHeaderBean.setPostPriorStatus(packageServiceForm.getPostPriorStatus());
		packageHeaderBean.setWingId(packageServiceForm.getWingId());
		packageHeaderBeans.add(packageHeaderBean);
		return packageHeaderBeans;
		
	}
	private PackageHeaderBean populatePackageHeaderBean(PackageServiceForm packageServiceForm){
		List<PackageHeaderBean> packageHeaderBeans=new ArrayList<PackageHeaderBean>();
		PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
		packageHeaderBean.setLocationId(packageServiceForm.getLocationId());
		packageHeaderBean.setPackageId(packageServiceForm.getPackageId().trim());
		packageHeaderBean.setSubPlanId(packageServiceForm.getSubPlanId());
		packageHeaderBean.setSchemeId(packageServiceForm.getSchemeId());
		packageHeaderBean.setPlanId(packageServiceForm.getPlanId());
		packageHeaderBean.setPackageDescription(packageServiceForm.getPackageDescription().trim());
		packageHeaderBean.setEstimatePackageCost(packageServiceForm.getEstimatePackageCost());
		packageHeaderBean.setPackageType(MISConstants.MIS_TYPE_OF_TENDER_CONSULTANCY);
		packageHeaderBean.setPostPriorStatus(packageServiceForm.getPostPriorStatus());
		packageHeaderBean.setWingId(packageServiceForm.getWingId());
		return packageHeaderBean;
		
	}
	private PackageServicesBean populateServicesBean(PackageServiceForm packageServiceForm){
		PackageServicesBean packageServicesBean = new PackageServicesBean();
		packageServicesBean.setPackageId(packageServiceForm.getPackageId().trim());
		packageServicesBean.setMethodOfSelection(packageServiceForm.getMethodOfSelection());
		packageServicesBean.setBankNocCombinedDate(MisUtility.convertStringToDate(packageServiceForm.getBankNocCombinedDate()));
		packageServicesBean.setBankNocFinalContractDate(MisUtility.convertStringToDate(packageServiceForm.getBankNocFinalContractDate()));
		packageServicesBean.setBankNocForRfpDate(MisUtility.convertStringToDate(packageServiceForm.getBankNocForRfpDate()));
		packageServicesBean.setBankNocForShortlistDate(MisUtility.convertStringToDate(packageServiceForm.getBankNocForShortlistDate()));
		packageServicesBean.setBankNocForTorDate(MisUtility.convertStringToDate(packageServiceForm.getBankNocForTorDate()));
		packageServicesBean.setBankNocTechnicalDate(MisUtility.convertStringToDate(packageServiceForm.getBankNocTechnicalDate()));
		packageServicesBean.setDateAdvertisingShortlisting(MisUtility.convertStringToDate(packageServiceForm.getDateAdvertisingShortlisting()));
		packageServicesBean.setEvaluationFinalCombinedDate(MisUtility.convertStringToDate(packageServiceForm.getEvaluationFinalCombinedDate()));
		packageServicesBean.setEvaluationFinalContractDate(MisUtility.convertStringToDate(packageServiceForm.getEvaluationFinalContractDate()));
		packageServicesBean.setEvaluationFinalDraftDate(MisUtility.convertStringToDate(packageServiceForm.getEvaluationFinalDraftDate()));
		packageServicesBean.setEvaluationFinalTechnicalDate(MisUtility.convertStringToDate(packageServiceForm.getEvaluationFinalTechnicalDate()));
		packageServicesBean.setRfpDraftBankDate(MisUtility.convertStringToDate(packageServiceForm.getRfpDraftBankDate()));
		packageServicesBean.setRfpIssuedDate(MisUtility.convertStringToDate(packageServiceForm.getRfpIssuedDate()));
		packageServicesBean.setProposalReciptDate(MisUtility.convertStringToDate(packageServiceForm.getProposalReciptDate()));
		packageServicesBean.setServiceCompletionDate(MisUtility.convertStringToDate(packageServiceForm.getServiceCompletionDate()));
		packageServicesBean.setFinancialProposalOpeningDate(MisUtility.convertStringToDate(packageServiceForm.getFinancialProposalOpeningDate()));
		packageServicesBean.setProposalReciptDateFinancial(MisUtility.convertStringToDate(packageServiceForm.getProposalReciptDateFinancial()));
		return packageServicesBean;
	}

}
