package com.prwss.mis.admin.divisional.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.admin.struts.DivisionalForm;
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
import com.prwss.mis.masters.zone.dao.ZoneBean;

public class DivisionalBOImpl implements DivisionalBO {
	
	private Logger log = Logger.getLogger(DivisionalBOImpl.class);
	private DivisionalDao divisionalDao;
	private LocationDao locationDao;
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}	
	public void setDivisionalDao(DivisionalDao divisionalDao) {
		this.divisionalDao = divisionalDao;
	}

	@Override
	public List<DivisionalBean> findDivisional(DivisionalForm divisionalForm, List<String> statusList) throws MISException {
		List<DivisionalBean> divisionalBeans = null;
		try {
			DivisionalBean divisionalBean = new DivisionalBean();
			divisionalBean.setDivisionalId(divisionalForm.getDivisionalId());
			
			DistrictBean district = new DistrictBean();
			district.setDistrictId(divisionalForm.getDistrictId());
			divisionalBean.setDistrict(district);
			divisionalBeans = divisionalDao.findDivisional(divisionalBean, statusList);
			System.out.println("----divisional Bean"+divisionalBeans.size());
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return divisionalBeans;
	}

	@Override
	public boolean saveDivisional(DivisionalForm divisionalForm, MISSessionBean misAuditBean) throws MISException {
		boolean status = false;
		List<DivisionalBean> divisionalBeans = null;
		try {
			
			DivisionalBean divisionalBean = new DivisionalBean();
			divisionalBean.setDivisionalId("");
			divisionalBean.setDivisionalName("");
			
			DivisionalBean divisionalBean2 = new DivisionalBean();
			
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList.add(MISConstants.MASTER_STATUS_DELETED);			
			divisionalBeans = divisionalDao.findDivisional(divisionalBean, statusList);
			if(!MisUtility.ifEmpty(divisionalBeans)){		
				for(DivisionalBean divisionalBean3:divisionalBeans){
					if(divisionalForm.getDivisionalName().equalsIgnoreCase(divisionalBean3.getDivisionalName())){
						StringBuffer message = new StringBuffer();
						message.append(" Divisional Name ").append(divisionalForm.getDivisionalName());
						log.debug("Duplicate Entry for "+divisionalForm.getDivisionalId()+" and "+ divisionalForm.getDivisionalName());
						log.debug("Divisional Already exist \n"+divisionalBeans);
						throw new MISException(MISExceptionCodes.MIS001 , message.toString());		
					}
				}
				System.out.println("Size is "+divisionalBeans.size());
				if(divisionalBeans.size()%10==0){
					divisionalBean2.setDivisionalId("DIV"+(divisionalBeans.size()+1));	
				}
				else{
					DivisionalBean bean = divisionalBeans.get(divisionalBeans.size()-1);
					String id = bean.getDivisionalId().substring(3);
					int lastId = new Integer(id)+1;
					System.out.println("String Id is "+id+" lastId Created "+lastId);
					if(lastId<10){
						divisionalBean2.setDivisionalId("DIV0"+lastId);
					}
					else{
						divisionalBean2.setDivisionalId("DIV"+lastId);	
					}
				}
			}
			divisionalBean2.setDivisionalName(divisionalForm.getDivisionalName());
			divisionalBean2.setIsSPMC_DPMC("DO");
			DistrictBean district = new DistrictBean();
			district.setDistrictId(divisionalForm.getDistrictId());
			divisionalBean2.setDistrict(district);
			divisionalBean2.setEnteredBy(misAuditBean.getEnteredBy());
			divisionalBean2.setEnteredDate(misAuditBean.getEnteredDate());
			divisionalBean2.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			status = divisionalDao.saveDivisional(divisionalBean2);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} 
		
		return status;
	}

	@Override
	public boolean updateDivisional(DivisionalForm divisionalForm, MISSessionBean misAuditBean,List<String> statusList) throws MISException {
		boolean status = false;
		List<DivisionalBean> divisionalBeans = null;
		try {
			DivisionalBean divisionalBean = populateDivisionalBean(divisionalForm);		
			divisionalBean.setEnteredBy(misAuditBean.getEnteredBy());
			divisionalBean.setEnteredDate(misAuditBean.getEnteredDate());
			divisionalBean.setStatus(statusList.get(0));
			
			DivisionalBean bean = new DivisionalBean();
			bean.setDivisionalId("");
			bean.setDivisionalName("");
			
			List<String> statusList1 = new ArrayList<String>();
			statusList1.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList1.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList1.add(MISConstants.MASTER_STATUS_DELETED);
			divisionalBeans = divisionalDao.findDivisional(bean, statusList1);
			if(!MisUtility.ifEmpty(divisionalBeans)){	
				for(DivisionalBean divisionalBean3:divisionalBeans){
					if(divisionalForm.getDivisionalName().equalsIgnoreCase(divisionalBean3.getDivisionalName())){
						StringBuffer message = new StringBuffer();
						message.append(" Divisional Name ").append(divisionalForm.getDivisionalName());
						log.debug("Duplicate Entry for "+divisionalForm.getDivisionalId()+" and "+ divisionalForm.getDivisionalName());
						log.debug("Divisional Already exist \n"+divisionalBeans);
						
						throw new MISException(MISExceptionCodes.MIS001 , message.toString());		
					}
				}
			}			
			status = divisionalDao.updateDivisional(divisionalBean);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} 	
		return status;
	}

	@Override
	public boolean deleteDivisional(DivisionalForm divisionalForm, MISSessionBean misAuditBean) throws MISException {
		boolean status = false; 
		try {
			DivisionalBean bean = new DivisionalBean();
			bean.setDivisionalId(divisionalForm.getDivisionalId());
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			List<DivisionalBean> divisionalBeans = divisionalDao.findDivisional(bean,statusList);
			for (DivisionalBean divisionalBean : divisionalBeans) {
				divisionalBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				divisionalBean.setEnteredBy(misAuditBean.getEnteredBy());
				divisionalBean.setEnteredDate(misAuditBean.getEnteredDate());
				}
			status = divisionalDao.updateDivisional(divisionalBeans);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

		return status;
	}

	@Override
	public boolean postDivisional(DivisionalForm divisionalForm, MISSessionBean misAuditBean) throws MISException {
		boolean status = false; 
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		DivisionalBean bean = new DivisionalBean();
		bean.setDivisionalId(divisionalForm.getDivisionalId());
		LocationBean locationBean = null;
		try {
			List<DivisionalBean> divisionalBeans = divisionalDao.findDivisional(bean,statusList);
			for (DivisionalBean divisionalBean : divisionalBeans) {
				divisionalBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				divisionalBean.setAuthorizedBy(misAuditBean.getEnteredBy());
				divisionalBean.setAuthorizedDate(misAuditBean.getEnteredDate());
				}
			status = divisionalDao.updateDivisional(divisionalBeans);
			for (DivisionalBean divisionalBean : divisionalBeans) {
				locationBean = new LocationBean();
				locationBean.setLocationId(divisionalBean.getDivisionalId());
				locationBean.setLocationName(divisionalBean.getDivisionalName());
				locationBean.setParentLocation(divisionalBean.getDistrict().getDistrictId());
				locationBean.setLocationType("DO");
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
	
	private DivisionalBean populateDivisionalBean(DivisionalForm divisionalForm){
		
		DivisionalBean divisionalBean = new DivisionalBean();
		
		divisionalBean.setDivisionalId(divisionalForm.getDivisionalId());
		divisionalBean.setDivisionalName(divisionalForm.getDivisionalName());
		DistrictBean district = new DistrictBean();
		district.setDistrictId(divisionalForm.getDistrictId());
		divisionalBean.setDistrict(district);
		
		return divisionalBean;
	}

	
}
