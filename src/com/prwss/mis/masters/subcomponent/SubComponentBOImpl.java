package com.prwss.mis.masters.subcomponent;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.subcomponent.dao.SubComponentBean;
import com.prwss.mis.masters.subcomponent.dao.SubComponentDao;
import com.prwss.mis.masters.subcomponent.struts.SubComponentForm;

public class SubComponentBOImpl implements SubComponentBO {
	
	private Logger log = Logger.getLogger(SubComponentBOImpl.class);
	private SubComponentDao subComponentDao;

	@Override
	public List<SubComponentBean> findSubComponent(SubComponentForm subComponent, List<String> statusList) throws MISException{
		List<SubComponentBean> subComponentBeans = null;
		try {
			SubComponentBean subComponentBean = new SubComponentBean();
			ComponentBean componentBean = new ComponentBean();
			
			componentBean.setComponentId(subComponent.getComponentId());
			subComponentBean.setComponentBean(componentBean);
			subComponentBean.setSubComponentId(subComponent.getSubComponentId());
			subComponentBean.setSubComponentDescription(subComponent.getSubComponentDescription());
			subComponentBean.setSubComponentName(subComponent.getSubComponentName());
			
			subComponentBeans = subComponentDao.findSubComponent(subComponentBean, statusList);
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return subComponentBeans;
	}

	@Override
	public boolean saveSubComponent(SubComponentForm subComponent, MISSessionBean misAuditBean,List<String> statusList) throws MISException{
		boolean status = false;
		SubComponentBean subComponentBean = null;
		List<SubComponentBean> subComponentBeans = null;
		try {
			subComponentBean = new SubComponentBean();
		
			subComponentBean.setSubComponentId(subComponent.getSubComponentId());
			//User entered Value
			String subComponentName = subComponent.getSubComponentName();
			subComponentBeans = subComponentDao.findSubComponent(subComponentBean, null);
			if(!MisUtility.ifEmpty(subComponentBeans)){					
				StringBuffer message = new StringBuffer();
					message.append("Sub Component code ").append(subComponent.getSubComponentId());
					if(subComponentName.equalsIgnoreCase(subComponentBeans.get(0).getSubComponentName())){
						message.append(" and Sub Component Name ").append(subComponentName);
					}
				log.debug("Duplicate Entry for "+subComponent.getComponentId()+" and "+ subComponent.getSubComponentName());
				log.debug("Sub Component Already exist \n"+subComponentBeans);
				throw new MISException(MISExceptionCodes.MIS001 , message.toString());
			}
			ComponentBean componentBean = new ComponentBean();
			componentBean.setComponentId(subComponent.getComponentId());
			subComponentBean.setComponentBean(componentBean);
			subComponentBean.setSubComponentId(subComponent.getSubComponentId());
			subComponentBean.setSubComponentName(subComponent.getSubComponentName());
			subComponentBean.setSubComponentDescription(subComponent.getSubComponentDescription());
			subComponentBean.setInsertedBy(misAuditBean.getEnteredBy());
			subComponentBean.setInsertedDate(misAuditBean.getEnteredDate());
			subComponentBean.setStatus(statusList.get(0));
			
			status = subComponentDao.saveSubComponent(subComponentBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}
	
	@Override
	public boolean updateSubComponent(SubComponentForm subComponent, MISSessionBean misAuditBean) throws MISException {
		boolean status = false;
		try {
			SubComponentBean subComponentBean = new SubComponentBean();
			ComponentBean componentBean = new ComponentBean();
			subComponentBean.setSubComponentId(subComponent.getSubComponentId());
			subComponentBean.setSubComponentName(subComponent.getSubComponentName());
			subComponentBean.setSubComponentDescription(subComponent.getSubComponentDescription());
			componentBean.setComponentId(subComponent.getComponentId());
			subComponentBean.setComponentBean(componentBean);
			subComponentBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			subComponentBean.setInsertedBy(misAuditBean.getEnteredBy());
			subComponentBean.setInsertedDate(misAuditBean.getEnteredDate());
			
			status = subComponentDao.updateSubComponent(subComponentBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}

	@Override
	public boolean postSubComponent(SubComponentForm subComponent, MISSessionBean misAuditBean) throws MISException{
		boolean status = false;
		List<SubComponentBean> subComponentBeans = null;
		try {
//			subComponentBeans = subComponentDao.findSubComponent(Arrays.asList(subComponent.getSubComponentIds()));
//			log.debug("subComponentBeans before updating status\n"+subComponentBeans);
//			for (SubComponentBean subComponentBean : subComponentBeans) {
//				subComponentBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
//				subComponentBean.setAuthorizedBy(misAuditBean.getEnteredBy());
//				subComponentBean.setAuthorizedDate(misAuditBean.getEnteredDate());
//			}
//			log.debug("subComponentBeans after updating status\n"+subComponentBeans);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			SubComponentBean subComponentBean = new SubComponentBean();
			subComponentBean.setSubComponentId(subComponent.getSubComponentId());
			SubComponentBean subComponentBean2 = subComponentDao.findSubComponent(subComponentBean, statusList).get(0);
			subComponentBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			subComponentBean.setAuthorizedBy(misAuditBean.getEnteredBy());
			subComponentBean.setAuthorizedDate(misAuditBean.getEnteredDate());
			status = subComponentDao.updateSubComponent(subComponentBean2);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e){
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return status;
	}

	@Override
	public boolean deleteSubComponent(SubComponentForm subComponent, MISSessionBean misAuditBean) throws MISException {
		boolean status = false;
		List<SubComponentBean> subComponentBeans = null;
		try {
//		subComponentBeans = subComponentDao.findSubComponent(Arrays.asList(subComponent.getSubComponentIds()));
			
			SubComponentBean subComponentBean = new SubComponentBean();
			ComponentBean componentBean = new ComponentBean();
			subComponentBean.setSubComponentId(subComponent.getSubComponentId());
			subComponentBean.setSubComponentName(subComponent.getSubComponentName());
			subComponentBean.setSubComponentDescription(subComponent.getSubComponentDescription());
			componentBean.setComponentId(subComponent.getComponentId());
			subComponentBean.setComponentBean(componentBean);
			subComponentBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
			subComponentBean.setInsertedBy(misAuditBean.getEnteredBy());
			subComponentBean.setInsertedDate(misAuditBean.getEnteredDate());
//		log.debug("subComponentBeans before updating status\n"+subComponentBeans);
//		for (SubComponentBean subComponentBean : subComponentBeans) {
//			subComponentBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
//			subComponentBean.setInsertedBy(misAuditBean.getEnteredBy());
//			subComponentBean.setInsertedDate(misAuditBean.getEnteredDate());
//		}
//		log.debug("subComponentBeans after updating status\n"+subComponentBeans);
		status = subComponentDao.updateSubComponent(subComponentBean);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			throw new MISException(e);
		}
		return status;
	}

	
	public void setSubComponentDao(SubComponentDao subComponentDao) {
		this.subComponentDao = subComponentDao;
	}
	
	
}
