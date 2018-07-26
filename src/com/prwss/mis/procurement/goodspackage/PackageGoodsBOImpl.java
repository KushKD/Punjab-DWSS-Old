package com.prwss.mis.procurement.goodspackage;

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
import com.prwss.mis.procurement.goodspackage.dao.PackageGoodsDao;
import com.prwss.mis.procurement.goodspackage.struts.PackageGoodsForm;
import com.prwss.mis.procurement.packagecomponents.dao.PackageComponentsBean;
import com.prwss.mis.procurement.packagecomponents.dao.PackageComponentsDao;
import com.prwss.mis.procurement.packageheader.dao.PackageDetailBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderBean;
import com.prwss.mis.procurement.packageheader.dao.PackageHeaderDao;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;

public class PackageGoodsBOImpl implements PackageGoodsBO {

	private Logger log = Logger.getLogger(PackageGoodsBOImpl.class);
	private PackageGoodsDao packageGoodsDao;
	private PackageComponentsDao packageComponentsDao;
	private PackageHeaderDao packageHeaderDao;
	private DocumentNumberDAO documentNumberDao;	
	
	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
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

	public PackageGoodsDao getPackageGoodsDao() {
		return packageGoodsDao;
	}

	public void setPackageGoodsDao(PackageGoodsDao packageGoodsDao) {
		this.packageGoodsDao = packageGoodsDao;
	}

	@Override
	public PackageDetailBean findPackageGoodsFrom(
			PackageGoodsForm packageGoodsForm, List<String> statusList)
			throws MISException {
		List<PackageGoodsBean> packageGoodsBeans = null;
		List<PackageHeaderBean> packageHeaderBeans = null;
		List<PackageComponentsBean> packageComponentsBeans = null;
		PackageDetailBean packageDetailBean = new PackageDetailBean();
		try {
			
			PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
			packageHeaderBean.setLocationId(packageGoodsForm.getLocationId().trim());
			packageHeaderBean.setPackageId(packageGoodsForm.getPackageId().trim());
			packageHeaderBean.setPackageType(MISConstants.MIS_TYPE_OF_TENDER_GOODS);
			packageHeaderBeans = packageHeaderDao.findPackage(packageHeaderBean, statusList);
			
			PackageGoodsBean packageGoodsBean = new PackageGoodsBean();
			packageGoodsBean.setPackageId(packageGoodsForm.getPackageId());
			
			PackageComponentsBean packageComponentsBean = new PackageComponentsBean();
			packageComponentsBean.setPackageId(packageGoodsForm.getPackageId());
			
			if(!MisUtility.ifEmpty(packageHeaderBeans)){
				packageGoodsBeans = packageGoodsDao.findGoodsPackage(packageGoodsBean, statusList);
				packageComponentsBeans = packageComponentsDao.findServicesPackageComponent(packageComponentsBean, statusList);
			}
			packageDetailBean.setPackageHeaderBeans(packageHeaderBeans);
			packageDetailBean.setPackageGoodsBeans(packageGoodsBeans);
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
	public boolean savePackageGoodsFrom(PackageGoodsForm packageGoodsForm,
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

			
			System.out.println("package id==="+packageGoodsForm.getPackageId());
			packageGoodsForm.setPackageId(packageGoodsForm.getPackageId());
			
			List<PackageHeaderBean> packageHeaderBeans = packageHeaderDao.findPackage(packageFindHeaderBean, null);
			if(!MisUtility.ifEmpty(packageHeaderBeans)){
				throw new MISException(MISExceptionCodes.MIS001, "Package Id\t"+packageGoodsForm.getPackageId());			
			}
			//System.out.println("3 Save:SchemeId,SubPlanId-->"+packageGoodsForm.getSchemeId()+","+packageGoodsForm.getSubPlanId());
			Set<PackageHeaderBean> packageHeaderBeans2= new TreeSet<PackageHeaderBean>();
			packageHeaderBeans2=packageHeaderDao.getPackageIdsAll(packageGoodsForm.getSchemeId(), packageGoodsForm.getSubPlanId());
			
			int packageNo=packageHeaderBeans2.size()+1;
			
			if(packageNo==1){			
				packageId="PRWSS-G"+"-"+packageGoodsForm.getLocationId()+"-"+docLastNo+"-"+packageNo;
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
				/*for (int i = 0; i < temp.length-1; i++) {
				
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
			packageGoodsForm.setPackageId(packageId);
			
		//	packageId="PRWSS-G"+"-"+packageGoodsForm.getLocationId()+"-"+docLastNo+"-"+packageNo;
			System.out.println("packageId==="+packageId);
			//packageGoodsForm.setPackageId(packageId);
			
			//packageFindHeaderBean.setPackageId(packageGoodsForm.getPackageId());
//			packageFindHeaderBean.setLocationId(packageGoodsForm.getLocationId());
			
			PackageHeaderBean packageHeaderBean = populatePackageHeaderBean(packageGoodsForm);
			PackageGoodsBean packageGoodsBean = populatePackageGoodsBean(packageGoodsForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
			packageHeaderBean.setMisAuditBean(misAuditBean);
			status = packageHeaderDao.savePackage(packageHeaderBean);
			if(status){
				if(MisUtility.ifEmpty(packageGoodsBean)){
					packageGoodsBean.setMisAuditBean(misAuditBean);
					boolean packageGoodsStatus =packageGoodsDao.saveGoodsPackage(packageGoodsBean);
					if(!packageGoodsStatus){
						log.error(packageGoodsBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Goods details");
					}
					
				}
				List<PackageComponentsBean> packageComponentsBeans = populatePackageComponentsBeans(packageGoodsForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
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
			throw new MISException(e);
		}
		
		
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updatePackageGoodsFrom(PackageGoodsForm packageGoodsForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			PackageHeaderBean packageHeaderBean = populatePackageHeaderBean(packageGoodsForm);
			PackageGoodsBean packageGoodsBean = populatePackageGoodsBean(packageGoodsForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED); 
			packageHeaderBean.setMisAuditBean(misAuditBean);
			status = packageHeaderDao.updatePackage(packageHeaderBean);
			if(status){
				if(MisUtility.ifEmpty(packageGoodsBean)){
					packageGoodsBean.setMisAuditBean(misAuditBean);
					boolean packageWorksStatus = packageGoodsDao.updateGoodsPackage(packageGoodsBean);
					if(!packageWorksStatus){
						log.error(packageGoodsBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Update Package Service details");
					}
					
				}
				List<PackageComponentsBean> packageComponentsBeans = populatePackageComponentsBeans(packageGoodsForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
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
			throw new MISException(e);
		}
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deletePackageGoodsFrom(PackageGoodsForm packageGoodsForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			PackageHeaderBean packageHeaderBean = populatePackageHeaderBean(packageGoodsForm);
			PackageGoodsBean packageGoodsBean = populatePackageGoodsBean(packageGoodsForm);
			String packageId = packageGoodsForm.getPackageId();
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED); 
			packageHeaderBean.setMisAuditBean(misAuditBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			status = packageHeaderDao.updatePackage(packageHeaderBean);
			if(status){
				if(MisUtility.ifEmpty(packageGoodsBean)){
					packageGoodsBean.setMisAuditBean(misAuditBean);
					boolean packageWorksStatus = packageGoodsDao.updateGoodsPackage(packageGoodsBean);
					if(!packageWorksStatus){
						log.error(packageGoodsBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Package Goods details");
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
			throw new MISException(e);
		}
		return status;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean postPackageGoodsFrom(PackageGoodsForm packageGoodsForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
			packageHeaderBean.setPackageId(packageGoodsForm.getPackageId());
			packageHeaderBean.setLocationId(packageGoodsForm.getLocationId());
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
			PackageGoodsBean packageGoodsBean = new PackageGoodsBean();
			packageGoodsBean.setPackageId(packageGoodsForm.getPackageId());
			packageGoodsBean = packageGoodsDao.findGoodsPackage(packageGoodsBean, statusList).get(0);
				if(MisUtility.ifEmpty(packageGoodsBean)){
					misAuditBean = packageGoodsBean.getMisAuditBean();
					misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
					misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
					packageGoodsBean.setMisAuditBean(misAuditBean);
					boolean packageWorksStatus = packageGoodsDao.updateGoodsPackage(packageGoodsBean);
					if(!packageWorksStatus){
						log.error(packageGoodsBean);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Post Package Service details");
					}
					
				}
			
				PackageComponentsBean packageComponentsBean = new PackageComponentsBean();
				packageComponentsBean.setPackageId(packageGoodsForm.getPackageId());
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
			throw new MISException(e);
		}
		
		return status;
	}
	
	private PackageGoodsBean populatePackageGoodsBean(PackageGoodsForm packageGoodsForm){
		PackageGoodsBean packageGoodsBean = new PackageGoodsBean();
		packageGoodsBean.setPrepBidDocDate(MisUtility.convertStringToDate(packageGoodsForm.getPrepBidDocDate()));
		packageGoodsBean.setMethodOfProcurement(packageGoodsForm.getMethodOfProcurement());
		packageGoodsBean.setDesignInvestigationDate(MisUtility.convertStringToDate(packageGoodsForm.getDesignInvestigationDate()));
		packageGoodsBean.setNumberOfUnit(packageGoodsForm.getNumberOfUnit());
		packageGoodsBean.setEstPreparedSanctionDate(MisUtility.convertStringToDate(packageGoodsForm.getEstPreparedSanctionDate()));
		packageGoodsBean.setBankNocBidDate(MisUtility.convertStringToDate(packageGoodsForm.getBankNocBidDate()));
		packageGoodsBean.setBidOpeningDate(MisUtility.convertStringToDate(packageGoodsForm.getBidOpeningDate()));
		packageGoodsBean.setBidInvitationDate(MisUtility.convertStringToDate(packageGoodsForm.getBidInvitationDate()));
		packageGoodsBean.setConAwardDecideDate(MisUtility.convertStringToDate(packageGoodsForm.getConAwardDecideDate()));
		packageGoodsBean.setBankNocConAwardDate(MisUtility.convertStringToDate(packageGoodsForm.getConAwardDecideDate()));
		packageGoodsBean.setConSignDate(MisUtility.convertStringToDate(packageGoodsForm.getConSignDate()));
		packageGoodsBean.setConCompletionDate(MisUtility.convertStringToDate(packageGoodsForm.getConCompletionDate()));
		packageGoodsBean.setPackageId(packageGoodsForm.getPackageId().trim());
		return packageGoodsBean;
	}
	
	private PackageHeaderBean populatePackageHeaderBean(PackageGoodsForm packageGoodsForm){
		PackageHeaderBean packageHeaderBean = new PackageHeaderBean();
		packageHeaderBean.setLocationId(packageGoodsForm.getLocationId());
		packageHeaderBean.setPackageId(packageGoodsForm.getPackageId().trim());
		packageHeaderBean.setSubPlanId(packageGoodsForm.getSubPlanId());
		packageHeaderBean.setSchemeId(packageGoodsForm.getSchemeId());
		packageHeaderBean.setPlanId(packageGoodsForm.getPlanId());
		packageHeaderBean.setPackageDescription(packageGoodsForm.getPackageDescription().trim());
		packageHeaderBean.setEstimatePackageCost(packageGoodsForm.getEstimatePackageCost());
		packageHeaderBean.setPackageType(MISConstants.MIS_TYPE_OF_TENDER_GOODS);
		packageHeaderBean.setPostPriorStatus(packageGoodsForm.getPostPriorStatus());
		packageHeaderBean.setWingId(packageGoodsForm.getWingId());
		return packageHeaderBean;
	}
	
	@SuppressWarnings("unchecked")
	private List<PackageComponentsBean> populatePackageComponentsBeans(PackageGoodsForm packageGoodsForm, MISSessionBean misSessionBean, String status){
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
			
			
			
			Collection<PackageComponentsBean> addedPackageComponentsBeans = packageGoodsForm.getPackageComponentsDatagrid().getAddedData();
			if(!MisUtility.ifEmpty(addedPackageComponentsBeans)){
				for (PackageComponentsBean packageComponentsBean : addedPackageComponentsBeans) {
					packageComponentsBean.setPackageId(packageGoodsForm.getPackageId().trim());
					packageComponentsBean.setMisAuditBean(misAuditBean1);
					packageComponentsBeans.add(packageComponentsBean);
				}
			}
			
			Collection<PackageComponentsBean> modifiedPackageComponentsBeans = packageGoodsForm.getPackageComponentsDatagrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedPackageComponentsBeans)){
				for (PackageComponentsBean packageComponentsBean : modifiedPackageComponentsBeans) {
					packageComponentsBean.setPackageId(packageGoodsForm.getPackageId().trim());
					packageComponentsBean.setMisAuditBean(misAuditBean2);
					packageComponentsBeans.add(packageComponentsBean);
				}
			}
			
			Collection<PackageComponentsBean> deletedPackageComponentsBeans = packageGoodsForm.getPackageComponentsDatagrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedPackageComponentsBeans)){
				for (PackageComponentsBean packageComponentsBean : deletedPackageComponentsBeans) {
					packageComponentsBean.setPackageId(packageGoodsForm.getPackageId().trim());
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
