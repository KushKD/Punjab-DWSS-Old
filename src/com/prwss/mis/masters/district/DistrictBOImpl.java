package com.prwss.mis.masters.district;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.circle.dao.CircleBean;
import com.prwss.mis.masters.constituency.dao.ConstituencyBean;
import com.prwss.mis.masters.district.dao.DistrictBean;
import com.prwss.mis.masters.district.dao.DistrictDao;
import com.prwss.mis.masters.district.struts.DistrictForm;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;

public class DistrictBOImpl implements DistrictBO {
	
	private Logger log = Logger.getLogger(DistrictBOImpl.class);
	private DistrictDao districtDao;
	private LocationDao locationDao;
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	
	public void setDistrictDao(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}

	@Override
	public List<DistrictBean> findDistrict(DistrictForm districtForm, List<String> statusList) throws MISException {
		List<DistrictBean> districtBeans = null;
		try {
			DistrictBean districtBean = new DistrictBean();
			districtBean.setDistrictId(districtForm.getDistrictCode());
			districtBean.setDistrictName(districtForm.getDistrictName());
			CircleBean circle = new CircleBean();
			circle.setCircleId(districtForm.getCircleCode());
			districtBean.setCircle(circle);
			districtBeans = districtDao.findDistrict(districtBean, statusList);
			System.out.println("----district Bean"+districtBeans.size());
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return districtBeans;
	}

	@Override
	public boolean saveDistrict(DistrictForm districtForm, MISSessionBean misAuditBean) throws MISException {
		boolean status = false;
		List<DistrictBean> districtBeans = null;
		try {
			DistrictBean districtBean = new DistrictBean();
			districtBean.setDistrictId("");
			districtBean.setDistrictName("");
			
			DistrictBean districtBean2 = new DistrictBean();
			
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList.add(MISConstants.MASTER_STATUS_DELETED);
			districtBeans = districtDao.findDistrict(districtBean, statusList);
			if(!MisUtility.ifEmpty(districtBeans)){	
				for(DistrictBean districtBean3:districtBeans){
					if(districtForm.getDistrictName().equalsIgnoreCase(districtBean3.getDistrictName())){
						StringBuffer message = new StringBuffer();
						message.append(" District Name ").append(districtForm.getDistrictName());
						log.debug("Duplicate Entry for "+districtForm.getDistrictCode()+" and "+ districtForm.getDistrictName());
						log.debug("District Already exist \n"+districtBeans);
						throw new MISException(MISExceptionCodes.MIS001 , message.toString());		
					}
				}
				System.out.println("Size is => "+districtBeans.size());
				if(districtBeans.size()%10==0){
					districtBean2.setDistrictId("D"+(districtBeans.size()+1));
				}
				else{
					DistrictBean bean = districtBeans.get(districtBeans.size()-1);
					String id = bean.getDistrictId().substring(1);
					int lastId = districtBeans.size()+1;
					System.out.println("String Id is "+id+" lastId Created "+lastId);
					if(lastId<10){
						districtBean2.setDistrictId("D0"+lastId);
					}
					else{
						districtBean2.setDistrictId("D"+lastId);	
					}
				}
			}
			districtBean2.setDistrictName(districtForm.getDistrictName());
			CircleBean circle = new CircleBean();
			circle.setCircleId(districtForm.getCircleCode());
			districtBean2.setCircle(circle);
			districtBean2.setEnteredBy(misAuditBean.getEnteredBy());
			districtBean2.setEnteredDate(misAuditBean.getEnteredDate());
			districtBean2.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			status = districtDao.saveDistrict(districtBean2);
		} catch (DataAccessException e) {
			throw new MISException(e);
		}		
		return status;
	}

	@Override
	public boolean updateDistrict(DistrictForm districtForm, MISSessionBean misAuditBean,List<String> statusList) throws MISException {
		boolean status = false;
		List<DistrictBean> districtBeans = null;
		try {
			DistrictBean districtBean = populateDistrictBean(districtForm);		
			districtBean.setEnteredBy(misAuditBean.getEnteredBy());
			districtBean.setEnteredDate(misAuditBean.getEnteredDate());
			districtBean.setStatus(statusList.get(0));
			
			DistrictBean bean = new DistrictBean();
			bean.setDistrictId("");
			bean.setDistrictName("");
			
			List<String> statusList1 = new ArrayList<String>();
			statusList1.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList1.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList1.add(MISConstants.MASTER_STATUS_DELETED);
			districtBeans = districtDao.findDistrict(bean, statusList1);
			if(!MisUtility.ifEmpty(districtBeans)){	
				for(DistrictBean districtBean3:districtBeans){
					if(districtForm.getDistrictName().equalsIgnoreCase(districtBean3.getDistrictName())){
						StringBuffer message = new StringBuffer();
						message.append(" District Name ").append(districtForm.getDistrictName());
						log.debug("Duplicate Entry for "+districtForm.getDistrictCode()+" and "+ districtForm.getDistrictName());
						log.debug("District Already exist \n"+districtBeans);
						
						throw new MISException(MISExceptionCodes.MIS001 , message.toString());		
					}
				}
			}			
			status = districtDao.updateDistrict(districtBean);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} 	
		return status;
	}

	@Override
	public boolean deleteDistrict(DistrictForm districtForm, MISSessionBean misAuditBean) throws MISException {
		boolean status = false; 
		try {
			DistrictBean bean = new DistrictBean();
			bean.setDistrictId(districtForm.getDistrictCode());
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			List<DistrictBean> districtBeans = districtDao.findDistrict(bean,statusList);
			for (DistrictBean districtBean : districtBeans) {
				districtBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				districtBean.setEnteredBy(misAuditBean.getEnteredBy());
				districtBean.setEnteredDate(misAuditBean.getEnteredDate());
				}
			status = districtDao.updateDistrict(districtBeans);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

		return status;
	}

	@Override
	public boolean postDistrict(DistrictForm districtForm, MISSessionBean misAuditBean) throws MISException {
		boolean status = false; 
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		DistrictBean bean = new DistrictBean();
		bean.setDistrictId(districtForm.getDistrictCode());
		LocationBean locationBean = null;
		try {
			List<DistrictBean> districtBeans = districtDao.findDistrict(bean,statusList);
			for (DistrictBean districtBean : districtBeans) {
				districtBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				districtBean.setAuthorizedBy(misAuditBean.getEnteredBy());
				districtBean.setAuthorizedDate(misAuditBean.getEnteredDate());
				}
			status = districtDao.updateDistrict(districtBeans);
			for (DistrictBean districtBean : districtBeans) {
				locationBean = new LocationBean();
				locationBean.setLocationId(districtBean.getDistrictId());
				locationBean.setLocationName(districtBean.getDistrictName());
				locationBean.setParentLocation(districtBean.getCircle().getCircleId());
				locationBean.setLocationType("DISTRICT");
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
	
	private DistrictBean populateDistrictBean(DistrictForm districtForm){
		
		DistrictBean districtBean = new DistrictBean();
		
		districtBean.setDistrictId(districtForm.getDistrictCode());
		districtBean.setDistrictName(districtForm.getDistrictName());
		districtBean.setAddress1(districtForm.getAddress1());
		districtBean.setAddress2(districtForm.getAddress2());
		CircleBean circle = new CircleBean();
		circle.setCircleId(districtForm.getCircleCode());
		districtBean.setCircle(circle);
		districtBean.setCity(districtForm.getCity());
		districtBean.setEmail(districtForm.getEmail());
		System.out.println("SPMC\t"+districtForm.getIsSpmc());
		districtBean.setIsSPMC_DPMC(districtForm.getIsSpmc()?"Y":"N");
		districtBean.setPin(districtForm.getPinCode());
		districtBean.setEmail(districtForm.getEmail());
		districtBean.setStreet(districtForm.getStreet());
		
		return districtBean;
	}

}
