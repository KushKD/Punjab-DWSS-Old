/**
 * 
 */
package com.prwss.mis.masters.component;

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
import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.component.dao.ComponentDao;
import com.prwss.mis.masters.component.struts.ComponentForm;
import com.prwss.mis.masters.zone.dao.ZoneBean;

/**
 * @author vgadiraju
 *
 */
public class ComponentBOImpl implements ComponentBO {
	
	private Logger log = Logger.getLogger(ComponentBOImpl.class);
	
	private ComponentDao componentDao;
	

	@Override
	public List<ComponentBean> findComponent(ComponentForm componentForm, List<String> statusList) throws MISException {
		List<ComponentBean> componentList;
		try {
			ComponentBean componentBean = new ComponentBean();
			componentBean.setComponentId(componentForm.getComponentId());
			componentBean.setComponentName(componentForm.getComponentName());
			componentBean.setComponentDescription(componentForm.getComponentDescription());
			componentList = componentDao.findComponent(componentBean, statusList);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e){
			throw new MISException(e);
		}
		
		return componentList;
	}

	@Override
	public boolean saveComponent(ComponentForm componentForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
		List<ComponentBean> componentBeans = null;
		ComponentBean componentBean = new ComponentBean();
		try {
			
			componentBean.setComponentId(componentForm.getComponentId());
			//User entered Value
			String componentName = componentForm.getComponentName();
			componentBeans = componentDao.findComponent(componentBean, null);
			if(!MisUtility.ifEmpty(componentBeans)){					
				StringBuffer message = new StringBuffer();
					message.append(" Component code ").append(componentForm.getComponentId());
					if(componentName.equalsIgnoreCase(componentBeans.get(0).getComponentName())){
						message.append(" and Component Name ").append(componentName);
					}
				log.debug("Duplicate Entry for "+componentForm.getComponentId()+" and "+ componentForm.getComponentName());
				log.debug("Component Already exist \n"+componentBeans);
				throw new MISException(MISExceptionCodes.MIS001 , message.toString());
			}
			componentBean.setComponentName(componentForm.getComponentName());
			componentBean.setComponentDescription(componentForm.getComponentDescription());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			componentBean.setMisAuditBean(misAuditBean);
			
			result = componentDao.saveComponent(componentBean);
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
	public boolean updateComponent(ComponentForm componentForm, MISSessionBean misSessionBean,List<String> statusList) throws MISException {
		boolean result = false;
		try {
			ComponentBean componentBean = new ComponentBean();
			componentBean.setComponentId(componentForm.getComponentId());
//			System.out.println("componentBean after change\t"+componentBean);
			componentBean.setComponentId(componentForm.getComponentId());
			componentBean.setComponentName(componentForm.getComponentName());
			componentBean.setComponentDescription(componentForm.getComponentDescription());
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(statusList.get(0));
			componentBean.setMisAuditBean(misAuditBean);
//			System.out.println("before updating\t\n"+componentBean);
			result = componentDao.updateComponent(componentBean);
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}


	public void setComponentDao(ComponentDao componentDao) {
		this.componentDao = componentDao;
	}

	@Override
	public boolean deleteComponent(ComponentForm componentForm,	MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
//		List<ComponentBean> componentBeans = componentDao.findComponent(Arrays.asList(componentForm.getComponentIds()));
		ComponentBean bean = new ComponentBean();
		bean.setComponentId(componentForm.getComponentId());
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		List<ComponentBean> componentBeans = componentDao.findComponent(bean,statusList);
		try {
			MISAuditBean misAuditBean = null;
			for (ComponentBean componentBean : componentBeans) {
			misAuditBean = new MISAuditBean();
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				componentBean.setMisAuditBean(misAuditBean);
				
			}
			result = componentDao.updateComponent(componentBeans);
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
	public boolean postComponent(ComponentForm componentForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
//		List<ComponentBean> componentBeans = componentDao.findComponent(Arrays.asList(componentForm.getComponentIds()));
		ComponentBean componentBean = new ComponentBean();
		componentBean.setComponentId(componentForm.getComponentId());
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			ComponentBean componentBean2 = componentDao.findComponent(componentBean, statusList).get(0);
//			MISAuditBean misAuditBean = null;
//			for (ComponentBean componentBean : componentBeans) {
//				misAuditBean = new MISAuditBean();
//				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
//				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
//				misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
//				componentBean.setMisAuditBean(misAuditBean);
//			}
			
			MISAuditBean misAuditBean = componentBean2.getMisAuditBean();
			misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
			misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			componentBean2.setMisAuditBean(misAuditBean);
			result = componentDao.updateComponent(componentBean2);
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
