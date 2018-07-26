package com.prwss.mis.masters.zone;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.zone.dao.ZoneBean;
import com.prwss.mis.masters.zone.dao.ZoneDao;
import com.prwss.mis.masters.zone.struts.ZoneForm;

public class ZoneBOImpl implements ZoneBO {
	
	private Logger log = Logger.getLogger(ZoneBOImpl.class);
	
	private ZoneDao zoneDao;
	private LocationDao locationDao;
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setZoneDao(ZoneDao zoneDao) {
		this.zoneDao = zoneDao;
	}

	@Override
	public List<ZoneBean> findZone(ZoneForm zoneForm, List<String> statusList) throws MISException {
		
		List<ZoneBean> zoneBeans = null;
		
		ZoneBean zoneBean;
		try {
			zoneBean = new ZoneBean();		
			zoneBean.setZoneId(zoneForm.getZoneId());
			zoneBean.setZoneName(zoneForm.getZoneName());
		
		zoneBeans = zoneDao.findZone(zoneBean, statusList);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return zoneBeans;
	}

	@Override
	public String saveZone(ZoneForm zoneForm, MISSessionBean misSessionBean) throws MISException {
		String zoneId = null;
		List<ZoneBean> zoneBeans = null;
		try {
			ZoneBean zoneBean = new ZoneBean();
			zoneBean.setZoneId("");
			zoneBean.setZoneName("");
			ZoneBean zoneBean2 = new ZoneBean();
			
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList.add(MISConstants.MASTER_STATUS_DELETED);
			zoneBeans = zoneDao.findZone(zoneBean, statusList);
			if(!MisUtility.ifEmpty(zoneBeans)){	
				for(ZoneBean zoneBean3:zoneBeans){
					if(zoneForm.getZoneName().equalsIgnoreCase(zoneBean3.getZoneName())){
						StringBuffer message = new StringBuffer();
						message.append(" Zone Name ").append(zoneForm.getZoneName());
						log.debug("Duplicate Entry for "+zoneForm.getZoneId()+" and "+ zoneForm.getZoneName());
						log.debug("Zone Already exist \n"+zoneBeans);
						System.out.println("-----Check1--------");
						throw new MISException(MISExceptionCodes.MIS001 , message.toString());		
					}
				}
				System.out.println("Size is "+zoneBeans.size());
				if(zoneBeans.size()%10==0){
					zoneBean2.setZoneId("Z"+(zoneBeans.size()+1));
				}
				else{
					ZoneBean bean = zoneBeans.get(zoneBeans.size()-1);
					String id = bean.getZoneId().substring(1);
					int lastId = zoneBeans.size()+1;
					System.out.println("String Id is "+id+" lastId Created "+lastId);
					if(lastId<10){
						zoneBean2.setZoneId("Z0"+lastId);
					}
					else{
						zoneBean2.setZoneId("Z"+lastId);	
					}
				}
			}
			zoneBean2.setZoneName(zoneForm.getZoneName());
			zoneBean2.setEnteredBy(misSessionBean.getEnteredBy());
			zoneBean2.setEnteredDate(misSessionBean.getEnteredDate());
			zoneBean2.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			zoneId = zoneDao.saveZone(zoneBean2);
		} catch (DataAccessException e) {
			zoneId = null;
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			throw new MISException(e);
		}
		return zoneId;
	}

	@Override
	public boolean updateZone(ZoneForm zoneForm, MISSessionBean misSessionBean,List<String> statusList) throws MISException {
		boolean status = false;
		List<ZoneBean> zoneBeans = null;
		try {
			ZoneBean zoneBean = new ZoneBean();
			
			zoneBean.setZoneId(zoneForm.getZoneId());
			zoneBean.setZoneName(zoneForm.getZoneName());
			zoneBean.setEnteredBy(misSessionBean.getEnteredBy());
			zoneBean.setEnteredDate(misSessionBean.getEnteredDate());
			zoneBean.setStatus(statusList.get(0));
			
			ZoneBean bean = new ZoneBean();
			bean.setZoneId("");
			bean.setZoneName("");
			
			List<String> statusList1 = new ArrayList<String>();
			statusList1.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList1.add(MISConstants.MASTER_STATUS_VERIFIED);
			statusList1.add(MISConstants.MASTER_STATUS_DELETED);
			zoneBeans = zoneDao.findZone(bean, statusList1);
			if(!MisUtility.ifEmpty(zoneBeans)){	
				for(ZoneBean zoneBean3:zoneBeans){
					if(zoneForm.getZoneName().equalsIgnoreCase(zoneBean3.getZoneName())){
						StringBuffer message = new StringBuffer();
						message.append(" Zone Name ").append(zoneForm.getZoneName());
						log.debug("Duplicate Entry for "+zoneForm.getZoneId()+" and "+ zoneForm.getZoneName());
						log.debug("Zone Already exist \n"+zoneBeans);
						System.out.println("-----Check1--------");
						throw new MISException(MISExceptionCodes.MIS001 , message.toString());		
					}
				}
			}			
			
			status = zoneDao.updateZone(zoneBean);
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean deleteZone(ZoneForm zoneForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
		ZoneBean bean = new ZoneBean();
		bean.setZoneId(zoneForm.getZoneId());
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		List<ZoneBean> zoneBeans = zoneDao.findZone(bean,statusList);
		try {
			for (ZoneBean zoneBean : zoneBeans) {
				zoneBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				zoneBean.setEnteredBy(misSessionBean.getEnteredBy());
				zoneBean.setEnteredDate(misSessionBean.getEnteredDate());
			}
			result = zoneDao.updateZone(zoneBeans);
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}

	@Override
	public boolean postZone(ZoneForm zoneForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		ZoneBean bean = new ZoneBean();
		bean.setZoneId(zoneForm.getZoneId());
		List<ZoneBean> zoneBeans = zoneDao.findZone(bean,statusList);
		LocationBean locationBean = null;
		try {
			for (ZoneBean zoneBean : zoneBeans) {
				zoneBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				zoneBean.setAuthorizedBy(misSessionBean.getEnteredBy());
				zoneBean.setAuthorizedDate(misSessionBean.getEnteredDate());	
			}			
			result = zoneDao.updateZone(zoneBeans);
			for (ZoneBean zoneBean : zoneBeans) {
				locationBean = new LocationBean();
				locationBean.setLocationId(zoneBean.getZoneId());
				locationBean.setLocationName(zoneBean.getZoneName());
				locationBean.setLocationType("ZONE");
				MISAuditBean auditBean = new MISAuditBean();
				auditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				auditBean.setEntBy(misSessionBean.getEnteredBy());
				auditBean.setEntDate(misSessionBean.getEnteredDate());
				locationBean.setMisAuditBean(auditBean);
				locationDao.saveLocation(locationBean);
			}	
			
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}

}
