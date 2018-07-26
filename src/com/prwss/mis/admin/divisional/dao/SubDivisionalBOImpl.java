package com.prwss.mis.admin.divisional.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.admin.struts.SubDivisionalForm;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.circle.dao.CircleBean;
import com.prwss.mis.masters.constituency.dao.ConstituencyBean;
import com.prwss.mis.masters.district.dao.DistrictBean;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;

public class SubDivisionalBOImpl implements SubDivisionalBO {
	
	private Logger log = Logger.getLogger(SubDivisionalBOImpl.class);
	private SubDivisionalDao subDivisionalDao;
	private LocationDao locationDao;
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	
	public void setSubDivisionalDao(SubDivisionalDao subDivisionalDao) {
		this.subDivisionalDao = subDivisionalDao;
	}

	@Override
	public List<SubDivisionalBean> findSubDivisional(SubDivisionalForm subdivisionalForm, List<String> statusList) throws MISException {
		List<SubDivisionalBean> subdivisionalBeans = null;
		try {
			SubDivisionalBean subdivisionalBean = new SubDivisionalBean();
			subdivisionalBean.setSubdivisionId(subdivisionalForm.getSubdivisionId());
			System.out.println("@@@@@@@@@@@@@"+subdivisionalForm);
			DivisionalBean district = new DivisionalBean();
			district.setDivisionalId(subdivisionalForm.getDivisionId());
			subdivisionalBean.setDivision(district);
			subdivisionalBeans = subDivisionalDao.findSubDivisional(subdivisionalBean, statusList);
			System.out.println("----subdivisional Bean"+subdivisionalBeans.size());
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw new MISException(e);			
		}
		
		return subdivisionalBeans;
	}

	@Override
	public boolean saveSubDivisional(SubDivisionalForm subdivisionalForm, MISSessionBean misAuditBean) throws MISException {
		boolean status = false;
		List<SubDivisionalBean> subdivisionalBeans = null;
		try {
						
			SubDivisionalBean subdivisionalBean2 = new SubDivisionalBean();
			
			//subdivisionalBeans = subDivisionalDao.findSubDivisional(subdivisionalBean, statusList);
			subdivisionalBean2.setSubdivisionId(subdivisionalForm.getDivisionId()+"S"+subdivisionalForm.getDistrictId());			
			System.out.println("----------SubDivision Id" +subdivisionalBean2.getSubdivisionId());
			subdivisionalBean2.setSubdivisionName(subdivisionalForm.getSubdivisionName());
			DivisionalBean division = new DivisionalBean();
			division.setDivisionalId(subdivisionalForm.getDivisionId());
			subdivisionalBean2.setDivision(division);
			subdivisionalBean2.setEnteredBy(misAuditBean.getEnteredBy());
			subdivisionalBean2.setEnteredDate(misAuditBean.getEnteredDate());
			subdivisionalBean2.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			status = subDivisionalDao.saveSubDivisional(subdivisionalBean2);
		} catch (DataAccessException e) {
			throw new MISException(e);
		}
		
		return status;
	}

	@Override
	public boolean updateSubDivisional(SubDivisionalForm subdivisionalForm, MISSessionBean misAuditBean,List<String> statusList) throws MISException {
		boolean status = false;
		List<SubDivisionalBean> subDivisionalBeans = null;
		try {
			SubDivisionalBean subdivisionalBean = populateSubDivisionalBean(subdivisionalForm);		
			subdivisionalBean.setEnteredBy(misAuditBean.getEnteredBy());
			subdivisionalBean.setEnteredDate(misAuditBean.getEnteredDate());
			subdivisionalBean.setStatus(statusList.get(0));
			
			SubDivisionalBean bean = new SubDivisionalBean();
			bean.setSubdivisionId("");
			bean.setSubdivisionName("");
			
			List<String> statusList1 = new ArrayList<String>();
			statusList1.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList1.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList1.add(MISConstants.MASTER_STATUS_DELETED);
			subDivisionalBeans = subDivisionalDao.findSubDivisional(bean, statusList1);
			if(!MisUtility.ifEmpty(subDivisionalBeans)){	
				for(SubDivisionalBean bean1:subDivisionalBeans){
					if(subdivisionalForm.getSubdivisionName().equalsIgnoreCase(bean1.getSubdivisionName())){
						StringBuffer message = new StringBuffer();
						message.append(" Subdivision Name ").append(subdivisionalForm.getSubdivisionName());
						log.debug("Duplicate Entry for "+subdivisionalForm.getSubdivisionId()+" and "+ subdivisionalForm.getSubdivisionName());
						log.debug("Subdivision Already exist \n"+subDivisionalBeans);
						
						throw new MISException(MISExceptionCodes.MIS001 , message.toString());		
					}
				}
			}			
			status = subDivisionalDao.updateSubDivisional(subdivisionalBean);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} 	
		return status;
	}

	@Override
	public boolean deleteSubDivisional(SubDivisionalForm subdivisionalForm, MISSessionBean misAuditBean) throws MISException {
		boolean status = false; 
		try {
			SubDivisionalBean bean = new SubDivisionalBean();
			bean.setSubdivisionId(subdivisionalForm.getSubdivisionId());
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			List<SubDivisionalBean> subdivisionalBeans = subDivisionalDao.findSubDivisional(bean,statusList);
			for (SubDivisionalBean subdivisionalBean : subdivisionalBeans) {
				subdivisionalBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				subdivisionalBean.setEnteredBy(misAuditBean.getEnteredBy());
				subdivisionalBean.setEnteredDate(misAuditBean.getEnteredDate());
				}
			status = subDivisionalDao.updateSubDivisional(subdivisionalBeans);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

		return status;
	}

	@Override
	public boolean postSubDivisional(SubDivisionalForm subdivisionalForm, MISSessionBean misAuditBean) throws MISException {
		boolean status = false; 
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		SubDivisionalBean bean = new SubDivisionalBean();
		bean.setSubdivisionId(subdivisionalForm.getSubdivisionId());
		LocationBean locationBean = new LocationBean();
		try {
			List<SubDivisionalBean> subdivisionalBeans = subDivisionalDao.findSubDivisional(bean,statusList);
			for (SubDivisionalBean subdivisionalBean : subdivisionalBeans) {
				subdivisionalBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				subdivisionalBean.setAuthorizedBy(misAuditBean.getEnteredBy());
				subdivisionalBean.setAuthorizedDate(misAuditBean.getEnteredDate());
				}
			status = subDivisionalDao.updateSubDivisional(subdivisionalBeans);
			for (SubDivisionalBean subdivisionalBean : subdivisionalBeans) {
				locationBean = new LocationBean();
				locationBean.setLocationId(subdivisionalBean.getSubdivisionId());
				locationBean.setLocationName(subdivisionalBean.getSubdivisionName());
				locationBean.setParentLocation(subdivisionalBean.getDivision().getDivisionalId());
				locationBean.setLocationType("Sub-Division");
				MISAuditBean auditBean = new MISAuditBean();
				auditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				auditBean.setEntBy(misAuditBean.getEnteredBy());
				auditBean.setEntDate(misAuditBean.getEnteredDate());
				locationBean.setMisAuditBean(auditBean);
				locationDao.saveLocation(locationBean);
			}	
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

		return status;
	}
	
	private SubDivisionalBean populateSubDivisionalBean(SubDivisionalForm subdivisionalForm){
		
		SubDivisionalBean subdivisionalBean = new SubDivisionalBean();
		
		subdivisionalBean.setSubdivisionId(subdivisionalForm.getSubdivisionId());
		subdivisionalBean.setSubdivisionName(subdivisionalForm.getSubdivisionName());
		DivisionalBean district = new DivisionalBean();
		district.setDivisionalId(subdivisionalForm.getDivisionId());
		subdivisionalBean.setDivision(district);
		
		return subdivisionalBean;
	}

	
}