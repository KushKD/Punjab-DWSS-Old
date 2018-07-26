package com.prwss.mis.procurement.physicalprogresspackage;

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
import com.prwss.mis.procurement.physicalprogresspackage.dao.PhysicalProgressComponentsBean;
import com.prwss.mis.procurement.physicalprogresspackage.dao.PhysicalProgressComponentsDao;
import com.prwss.mis.procurement.physicalprogresspackage.struts.PhysicalProgressComponentsGridBean;
import com.prwss.mis.procurement.physicalprogresspackage.struts.PhysicalProgressPackageForm;

public class PackageComponentsProgressBOImpl implements
		PackageComponentsProgressBO {
	private PhysicalProgressComponentsDao physicalProgressComponentsDao;
	private Logger log = Logger.getLogger(PackageComponentsProgressBOImpl.class);
	public void setPhysicalProgressComponentsDao(
		PhysicalProgressComponentsDao physicalProgressComponentsDao) {
	this.physicalProgressComponentsDao = physicalProgressComponentsDao;
}

	@Override
	public List<PhysicalProgressComponentsBean> findPhysicalProgressPackageForm(
			PhysicalProgressPackageForm physicalProgressPackageForm,
			List<String> statusList) throws MISException {
		List<PhysicalProgressComponentsBean> physicalProgressComponentsBeans=null;
		try {
			PhysicalProgressComponentsBean physicalProgressComponentsBean = new PhysicalProgressComponentsBean();
			physicalProgressComponentsBean.setPackageId(physicalProgressPackageForm.getPackageId().trim());
			physicalProgressComponentsBean.setLocationId(physicalProgressPackageForm.getLocationId());
			physicalProgressComponentsBean.setComponentName(physicalProgressPackageForm.getComponentName());
			physicalProgressComponentsBeans = physicalProgressComponentsDao.findPhysicalProgressComponents(physicalProgressComponentsBean, statusList);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return physicalProgressComponentsBeans;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean savePhysicalProgressPackageForm(
			PhysicalProgressPackageForm physicalProgressPackageForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			PhysicalProgressComponentsBean physicalFindProgressComponentsBean = new PhysicalProgressComponentsBean();
			physicalFindProgressComponentsBean.setPackageId(physicalProgressPackageForm.getPackageId());
			physicalFindProgressComponentsBean.setLocationId(physicalProgressPackageForm.getLocationId());
			List<PhysicalProgressComponentsBean> physicalProgressComponentsBeans = physicalProgressComponentsDao.findPhysicalProgressComponents(physicalFindProgressComponentsBean,null);
			if(!MisUtility.ifEmpty(physicalProgressComponentsBeans)){
				throw new MISException(MISExceptionCodes.MIS001, "Package Id\t"+physicalProgressPackageForm.getPackageId()+"\t , please use  modify option to enter progress ");			
			}
			List<PhysicalProgressComponentsBean> physicalProgressComponentsBeans2 = populatePhysicalProgressComponentsBeans(physicalProgressPackageForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
			for(int i=0;i<physicalProgressComponentsBeans2.size();i++){
				
				for(int j=i+1;j<physicalProgressComponentsBeans2.size();j++){
					
					if(((physicalProgressComponentsBeans2.get(i).getComponentName()).equals(physicalProgressComponentsBeans2.get(j).getComponentName())||(physicalProgressComponentsBeans2.get(i).getComponentName()==physicalProgressComponentsBeans2.get(j).getComponentName()))&& (physicalProgressComponentsBeans2.get(i).getAsOnDate()).equals(physicalProgressComponentsBeans2.get(j).getAsOnDate())||(physicalProgressComponentsBeans2.get(i).getAsOnDate()==physicalProgressComponentsBeans2.get(j).getAsOnDate())){
						
						throw new MISException(MISExceptionCodes.MIS003, "More than one entry of progress of same components on same date  is not allowed");
						
					}
				}
			}
			if(!MisUtility.ifEmpty(physicalProgressComponentsBeans2)){
				status = physicalProgressComponentsDao.saveOrUpdatePhysicalProgressComponents(physicalProgressComponentsBeans2);
				if(!status){
					log.error(physicalProgressComponentsBeans2);
					throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Components details");
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
	public boolean updatePhysicalProgressPackageForm(
			PhysicalProgressPackageForm physicalProgressPackageForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			
			
			List<String> statusList=new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			
			List<PhysicalProgressComponentsBean> physicalProgressComponentsBeans1=physicalProgressComponentsDao.findPhysicalProgressAsOnDate(physicalProgressPackageForm.getPackageId(),statusList);
			List<PhysicalProgressComponentsBean> physicalProgressComponentsBeans2 = populatePhysicalProgressComponentsBeans(physicalProgressPackageForm, misSessionBean, MISConstants.MASTER_STATUS_VERIFIED);
			
			for(int i=0;i<physicalProgressComponentsBeans2.size();i++){
				System.out.println("inside first loop   i====");
				for(int j=i+1;j<physicalProgressComponentsBeans2.size();j++){
					System.out.println("inside second loop   j====");
					if(!(physicalProgressComponentsBeans2.get(i).getMisAuditBean().getStatus().equals("D"))){
						if(((physicalProgressComponentsBeans2.get(i).getComponentName()).equals(physicalProgressComponentsBeans2.get(j).getComponentName())||(physicalProgressComponentsBeans2.get(i).getComponentName()==physicalProgressComponentsBeans2.get(j).getComponentName()))&& (physicalProgressComponentsBeans2.get(i).getAsOnDate()).equals(physicalProgressComponentsBeans2.get(j).getAsOnDate())||(physicalProgressComponentsBeans2.get(i).getAsOnDate()==physicalProgressComponentsBeans2.get(j).getAsOnDate())){
								System.out.println("inside inner for loop");
						throw new MISException(MISExceptionCodes.MIS003, "More than one entry of progress of same components on same date  is not allowed");
						
					}
							
				  }
				}
			
				for(int k=0;k<physicalProgressComponentsBeans1.size();k++){
					if(!(physicalProgressComponentsBeans2.get(i).getMisAuditBean().getStatus().equals("D"))){
						if((physicalProgressComponentsBeans1.get(k).getAsOnDate()).compareTo(physicalProgressComponentsBeans2.get(i).getAsOnDate())>0){
							
							throw new MISException(MISExceptionCodes.MIS003, "Entry in previous date is not allowed ");
							
					}
					
						if(Integer.parseInt(physicalProgressComponentsBeans1.get(k).getPercentCompletion())>(Integer.parseInt(physicalProgressComponentsBeans2.get(i).getPercentCompletion()))){
							
							throw new MISException(MISExceptionCodes.MIS003, "The progress can not be decreased than previously reported progress");
							
					}
						
				   }
					if(physicalProgressComponentsBeans2.get(i).getMisAuditBean().getStatus().equals("D")){
						/*System.out.println("inside delete=====");
						System.out.println("component name of physicalProgressComponentsBeans2 ====="+physicalProgressComponentsBeans2.get(i).getComponentName());
						System.out.println("component name of physicalProgressComponentsBeans1 ====="+physicalProgressComponentsBeans1.get(k).getComponentName());
						System.out.println("as on date of physicalProgressComponentsBeans2 ====="+physicalProgressComponentsBeans2.get(i).getAsOnDate());
						System.out.println("as on date of physicalProgressComponentsBeans1 ====="+physicalProgressComponentsBeans2.get(k).getAsOnDate());
						System.out.println("as on date of physicalProgressComponentsBeans2 ====="+physicalProgressComponentsBeans2.get(i).getPercentCompletion());
						System.out.println("as on date of physicalProgressComponentsBeans1 ====="+physicalProgressComponentsBeans2.get(k).getPercentCompletion());
						
						
						*/
						
						
						if(Integer.parseInt(physicalProgressComponentsBeans1.get(k).getPercentCompletion())==(Integer.parseInt(physicalProgressComponentsBeans2.get(i).getPercentCompletion()))){
							System.out.println("inside delete= double====");
							
							throw new MISException(MISExceptionCodes.MIS003, " Already reported progress in grid can not be deleted,Please contact system administrator");
						}
				}
			}
			
		
	}
			if(!MisUtility.ifEmpty(physicalProgressComponentsBeans2)){
				status = physicalProgressComponentsDao.saveOrUpdatePhysicalProgressComponents(physicalProgressComponentsBeans2);
				if(!status){
					log.error(physicalProgressComponentsBeans2);
					throw new MISException(MISExceptionCodes.MIS003, "Failed to Save Package Components details");
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
	public boolean deletePhysicalProgressPackageForm(
			PhysicalProgressPackageForm physicalProgressPackageForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED); 
			PhysicalProgressComponentsBean physicalProgressComponentsBean = new PhysicalProgressComponentsBean();
			physicalProgressComponentsBean.setPackageId(physicalProgressPackageForm.getPackageId());
			physicalProgressComponentsBean.setLocationId(physicalProgressPackageForm.getLocationId());
				List<PhysicalProgressComponentsBean> physicalProgressComponentsBeans = physicalProgressComponentsDao.findPhysicalProgressComponents(physicalProgressComponentsBean, statusList);
				if(!MisUtility.ifEmpty(physicalProgressComponentsBeans)){
					for (PhysicalProgressComponentsBean physicalProgressComponentsBean2 : physicalProgressComponentsBeans) {
						physicalProgressComponentsBean2.setMisAuditBean(misAuditBean);
					}
					status = physicalProgressComponentsDao.saveOrUpdatePhysicalProgressComponents(physicalProgressComponentsBeans);
					if(!status){
						log.error(physicalProgressComponentsBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Package Components details");
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
	public boolean postPhysicalProgressPackageForm(
			PhysicalProgressPackageForm physicalProgressPackageForm,
			MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		try {
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			MISAuditBean misAuditBean = new MISAuditBean();
			
			PhysicalProgressComponentsBean physicalProgressComponentsBean = new PhysicalProgressComponentsBean();
			physicalProgressComponentsBean.setPackageId(physicalProgressPackageForm.getPackageId());
			physicalProgressComponentsBean.setLocationId(physicalProgressPackageForm.getLocationId());
				List<PhysicalProgressComponentsBean> physicalProgressComponentsBeans = physicalProgressComponentsDao.findPhysicalProgressComponents(physicalProgressComponentsBean, statusList);
				if(!MisUtility.ifEmpty(physicalProgressComponentsBeans)){
					for (PhysicalProgressComponentsBean physicalProgressComponentsBean2 : physicalProgressComponentsBeans) {
						misAuditBean = physicalProgressComponentsBean2.getMisAuditBean();
						misAuditBean.setEntBy(misSessionBean.getEnteredBy());
						misAuditBean.setEntDate(misSessionBean.getEnteredDate());
						misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED); 
						physicalProgressComponentsBean2.setMisAuditBean(misAuditBean);
					}
					status = physicalProgressComponentsDao.saveOrUpdatePhysicalProgressComponents(physicalProgressComponentsBeans);
					if(!status){
						log.error(physicalProgressComponentsBeans);
						throw new MISException(MISExceptionCodes.MIS003, "Failed to Delete Package Components details");
					}
				}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	private List<PhysicalProgressComponentsBean> populatePhysicalProgressComponentsBeans(PhysicalProgressPackageForm physicalProgressPackageForm, MISSessionBean misSessionBean, String status){
		List <PhysicalProgressComponentsBean> physicalProgressComponentsBeans = new ArrayList<PhysicalProgressComponentsBean>();
		try {
			MISAuditBean misAuditBean = new MISAuditBean();
			if(MisUtility.ifEmpty(status) && MISConstants.MASTER_STATUS_APPROVED.equalsIgnoreCase(status)){
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			} else{
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			}
			misAuditBean.setStatus(status);
			
			
			PhysicalProgressComponentsBean physicalProgressComponentsBean = null;
			Collection<PhysicalProgressComponentsGridBean> addedProgressComponentsGridBeans = physicalProgressPackageForm.getComponentPhysicalProgressDatagrid().getAddedData();
			if(!MisUtility.ifEmpty(addedProgressComponentsGridBeans)){
				for (PhysicalProgressComponentsGridBean progressComponentsGridBean : addedProgressComponentsGridBeans) {
					physicalProgressComponentsBean = new PhysicalProgressComponentsBean();
					physicalProgressComponentsBean.setPackageId(physicalProgressPackageForm.getPackageId().trim());
					physicalProgressComponentsBean.setLocationId(physicalProgressPackageForm.getLocationId());
					physicalProgressComponentsBean.setMisAuditBean(misAuditBean);
					physicalProgressComponentsBean.setAsOnDate(MisUtility.convertStringToDate(progressComponentsGridBean.getAsOnDate()));
					physicalProgressComponentsBean.setPercentCompletion(progressComponentsGridBean.getPercentCompletion().trim());
					physicalProgressComponentsBean.setComponentName(progressComponentsGridBean.getComponentName().trim());
					physicalProgressComponentsBean.setId(progressComponentsGridBean.getId());
					physicalProgressComponentsBeans.add(physicalProgressComponentsBean);
				}
			}
			
			Collection<PhysicalProgressComponentsGridBean> modifiedProgressComponentsGridBeans = physicalProgressPackageForm.getComponentPhysicalProgressDatagrid().getModifiedData();
			if(!MisUtility.ifEmpty(modifiedProgressComponentsGridBeans)){
				for (PhysicalProgressComponentsGridBean progressComponentsGridBean : modifiedProgressComponentsGridBeans) {
					physicalProgressComponentsBean = new PhysicalProgressComponentsBean();
					physicalProgressComponentsBean.setPackageId(physicalProgressPackageForm.getPackageId().trim());
					physicalProgressComponentsBean.setLocationId(physicalProgressPackageForm.getLocationId());
					physicalProgressComponentsBean.setMisAuditBean(misAuditBean);
					physicalProgressComponentsBean.setAsOnDate(MisUtility.convertStringToDate(progressComponentsGridBean.getAsOnDate()));
					physicalProgressComponentsBean.setPercentCompletion(progressComponentsGridBean.getPercentCompletion().trim());
					physicalProgressComponentsBean.setComponentName(progressComponentsGridBean.getComponentName().trim());
					physicalProgressComponentsBean.setId(progressComponentsGridBean.getId());
					physicalProgressComponentsBeans.add(physicalProgressComponentsBean);
				}
			}
			
			Collection<PhysicalProgressComponentsGridBean> deletedProgressComponentsGridBeans = physicalProgressPackageForm.getComponentPhysicalProgressDatagrid().getDeletedData();
			if(!MisUtility.ifEmpty(deletedProgressComponentsGridBeans)){
				for (PhysicalProgressComponentsGridBean progressComponentsGridBean : deletedProgressComponentsGridBeans) {
					physicalProgressComponentsBean = new PhysicalProgressComponentsBean();
					physicalProgressComponentsBean.setPackageId(physicalProgressPackageForm.getPackageId().trim());
					physicalProgressComponentsBean.setLocationId(physicalProgressPackageForm.getLocationId());
					misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
					physicalProgressComponentsBean.setMisAuditBean(misAuditBean);
					physicalProgressComponentsBean.setAsOnDate(MisUtility.convertStringToDate(progressComponentsGridBean.getAsOnDate()));
					physicalProgressComponentsBean.setPercentCompletion(progressComponentsGridBean.getPercentCompletion().trim());
					physicalProgressComponentsBean.setComponentName(progressComponentsGridBean.getComponentName().trim());
					physicalProgressComponentsBean.setId(progressComponentsGridBean.getId());
					physicalProgressComponentsBeans.add(physicalProgressComponentsBean);
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		
		return physicalProgressComponentsBeans;
	}


}
