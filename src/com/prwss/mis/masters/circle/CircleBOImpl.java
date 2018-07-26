package com.prwss.mis.masters.circle;

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
import com.prwss.mis.masters.circle.dao.CircleDao;
import com.prwss.mis.masters.circle.struts.CircleForm;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.zone.dao.ZoneBean;

public class CircleBOImpl implements CircleBO {
	
	private Logger log = Logger.getLogger(CircleBOImpl.class);
	private CircleDao circleDao;		
	private LocationDao locationDao;
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setCircleDao(CircleDao circleDao) {
		this.circleDao = circleDao;
	}

	@Override
	public List<CircleBean> findCircle(CircleForm circleForm, List<String> statusList) throws MISException {
		List<CircleBean> circleBeans = null;
		
		try {
			CircleBean circleBean = new CircleBean();
			circleBean.setCircleId(circleForm.getCircleId());
			circleBean.setCircleName(circleForm.getCircleName());
			
			ZoneBean zone = new ZoneBean();
			zone.setZoneId(circleForm.getZoneId());
			circleBean.setZone(zone);
			
			circleBeans = circleDao.findCircle(circleBean, statusList);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return circleBeans;
	}

	@Override
	public boolean saveCircle(CircleForm circleForm, MISSessionBean misAuditBean) throws MISException {
		boolean status = false;
		List<CircleBean> circleBeans = null;
		try {
			CircleBean circleBean = new CircleBean();
			circleBean.setCircleId("");
			circleBean.setCircleName("");
			
			CircleBean circleBean2 = new CircleBean();
			
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList.add(MISConstants.MASTER_STATUS_DELETED);
			circleBeans = circleDao.findCircle(circleBean, statusList);
			if(!MisUtility.ifEmpty(circleBeans)){
				for(CircleBean circleBean3:circleBeans){
					if(circleForm.getCircleName().equalsIgnoreCase(circleBean3.getCircleName())){
						StringBuffer message = new StringBuffer();
						message.append(" Circle Name ").append(circleForm.getCircleName());
						log.debug("Duplicate Entry for "+circleForm.getCircleId()+" and "+ circleForm.getCircleName());
						log.debug("Circle Already exist \n"+circleBeans);
						throw new MISException(MISExceptionCodes.MIS001 , message.toString());		
					}
				}
				System.out.println("Size is "+circleBeans.size());
				if(circleBeans.size()%10==0){
					circleBean2.setCircleId("C"+(circleBeans.size()+1));
				}
				else{
					CircleBean bean = circleBeans.get(circleBeans.size()-1);
					String id = bean.getCircleId().substring(1);
					int lastId = circleBeans.size()+1;
					System.out.println("String Id is "+id+" lastId Created "+lastId);
					if(lastId<10){
						circleBean2.setCircleId("C0"+lastId);
					}
					else{
						circleBean2.setCircleId("C"+lastId);	
					}
				}
			}
			circleBean2.setCircleName(circleForm.getCircleName());
			ZoneBean zone = new ZoneBean();
			zone.setZoneId(circleForm.getZoneId());
			circleBean2.setZone(zone);
			circleBean2.setEnteredBy(misAuditBean.getEnteredBy());
			circleBean2.setEnteredDate(misAuditBean.getEnteredDate());
			circleBean2.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			status = circleDao.saveCircle(circleBean2);
		}catch (DataAccessException e) {
			throw new MISException(e);
		} 
		
		return status;
	}

	@Override
	public boolean updateCircle(CircleForm circleForm, MISSessionBean misAuditBean,List<String> statusList) throws MISException {
		boolean status = false;
		List<CircleBean> circleBeans= null;
		try {
			CircleBean circleBean = new CircleBean();
			circleBean.setCircleId(circleForm.getCircleId());
			circleBean.setCircleName(circleForm.getCircleName());
			
			ZoneBean zone = new ZoneBean();
			zone.setZoneId(circleForm.getZoneId());
			circleBean.setZone(zone);
			circleBean.setEnteredBy(misAuditBean.getEnteredBy());
			circleBean.setEnteredDate(misAuditBean.getEnteredDate());
			circleBean.setStatus(statusList.get(0));
			
			CircleBean bean = new CircleBean();
			bean.setCircleId("");
			bean.setCircleName("");
			
			List<String> statusList1 = new ArrayList<String>();
			statusList1.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList1.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList1.add(MISConstants.MASTER_STATUS_DELETED);
			circleBeans = circleDao.findCircle(bean, statusList1);
			if(!MisUtility.ifEmpty(circleBeans)){	
				for(CircleBean circleBean3:circleBeans){
					if(circleForm.getCircleName().equalsIgnoreCase(circleBean3.getCircleName())){
						StringBuffer message = new StringBuffer();
						message.append(" Circle Name ").append(circleForm.getCircleName());
						log.debug("Duplicate Entry for "+circleForm.getCircleId()+" and "+ circleForm.getCircleName());
						log.debug("Circle Already exist \n"+circleBeans);
						
						throw new MISException(MISExceptionCodes.MIS001 , message.toString());		
					}
				}
			}			
			status = circleDao.updateCircle(circleBean);
		}catch (DataAccessException e) {
			throw new MISException(e);
		}
		
		return status;
	}

	@Override
	public boolean deleteCircle(CircleForm circleForm, MISSessionBean misAuditBean) throws MISException {
		boolean result = false;
		CircleBean bean = new CircleBean();
		bean.setCircleId(circleForm.getCircleId());
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		List<CircleBean> circleBeans = circleDao.findCircle(bean,statusList);
		try {
			for (CircleBean circleBean : circleBeans) {
				circleBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				circleBean.setEnteredBy(misAuditBean.getEnteredBy());
				circleBean.setEnteredDate(misAuditBean.getEnteredDate());
			}
			result = circleDao.updateCircle(circleBeans);
		}catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}

	@Override
	public boolean postCircle(CircleForm circleForm, MISSessionBean misAuditBean) throws MISException {
		boolean result = false;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		CircleBean bean = new CircleBean();
		bean.setCircleId(circleForm.getCircleId());
		List<CircleBean> circleBeans = circleDao.findCircle(bean,statusList);
		LocationBean locationBean = null;
		try {
			for (CircleBean circleBean : circleBeans) {
				circleBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				circleBean.setAuthorizedBy(misAuditBean.getEnteredBy());
				circleBean.setAuthorizedDate(misAuditBean.getEnteredDate());
			}
			result = circleDao.updateCircle(circleBeans);
			for (CircleBean circleBean : circleBeans) {
				locationBean = new LocationBean();
				locationBean.setLocationId(circleBean.getCircleId());
				locationBean.setLocationName(circleBean.getCircleName());
				locationBean.setParentLocation(circleBean.getZone().getZoneId());
				locationBean.setLocationType("CIRCLE");
				MISAuditBean auditBean = new MISAuditBean();
				auditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				auditBean.setEntBy(misAuditBean.getEnteredBy());
				auditBean.setEntDate(misAuditBean.getEnteredDate());
				locationBean.setMisAuditBean(auditBean);
				locationDao.saveLocation(locationBean);
			}	
			
		}catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}

}
