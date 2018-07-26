package com.prwss.mis.masters.itemgroup;

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
import com.prwss.mis.masters.item.dao.ItemGroupBean;
import com.prwss.mis.masters.item.dao.ItemGroupDao;



import com.prwss.mis.masters.itemgroup.struts.ItemGroupForm;

public class ItemGroupBOImpl implements ItemGroupBO {
	
	private Logger log = Logger.getLogger(ItemGroupBOImpl.class);
	
	protected ItemGroupDao itemGroupDao;
	
	public void setItemGroupDao(ItemGroupDao itemGroupDao) {
		this.itemGroupDao = itemGroupDao;
	}
	@Override
	public java.util.List<ItemGroupBean> findItemGroup(ItemGroupForm itemGroupForm, java.util.List<String> statusList) throws MISException 
	{

		List<ItemGroupBean> itemGroupBeans = null;
		
		try {
			ItemGroupBean itemGroupBean = populateItemGroupBean(itemGroupForm);
			itemGroupBeans = itemGroupDao.findItemGroup(itemGroupBean, statusList);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e) {
			throw new MISException(e);
		}
		
		return itemGroupBeans;
	}
	

	@Override
	public boolean saveItemGroup(ItemGroupForm itemGroupForm, MISSessionBean misSessionBean) throws MISException {
		boolean status=false;
		try {
			List<String> itemGroupIds = new ArrayList<String>();
			itemGroupIds.add(itemGroupForm.getItemGroupId());
			List<ItemGroupBean> itemGroupBeans = itemGroupDao.findItemGroup(itemGroupIds);
			if(!MisUtility.ifEmpty(itemGroupBeans)){				
				throw new MISException(MISExceptionCodes.MIS001,"Duplicate Entry");
			}
			
			ItemGroupBean itemGroupBean = populateItemGroupBean(itemGroupForm);
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			itemGroupBean.setMisAuditBean(misAuditBean);			
			status = itemGroupDao.saveItemGroup(itemGroupBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return status;
	}

	@Override
	public boolean updateItemGroup(ItemGroupForm itemGroupForm, MISSessionBean misSessionBean) throws MISException {
		try {
			ItemGroupBean itemGroupBean = populateItemGroupBean(itemGroupForm);
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			
			itemGroupDao.updateItemGroup(itemGroupBean);
		} catch (DataAccessException e) {
			throw new MISException(e);
		} catch (Exception e) {
			throw new MISException(e);
		}
		
		return true;
	}

	@Override
	public boolean deleteItemGroup(ItemGroupForm itemGroupForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
		List<ItemGroupBean> itemGroupBeans = itemGroupDao.findItemGroup((Arrays.asList(itemGroupForm.getItemGroupIds())));
		
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
		
		try {
			for (ItemGroupBean itemGroupBean : itemGroupBeans) {
				itemGroupBean.setMisAuditBean(misAuditBean);
			}
			
			result = itemGroupDao.updateItemGroup(itemGroupBeans);
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
	public boolean postItemGroup(ItemGroupForm itemGroupForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
		List<ItemGroupBean> itemGroupBeans = itemGroupDao.findItemGroup((Arrays.asList(itemGroupForm.getItemGroupIds())));
		
		MISAuditBean misAuditBean = null;
		
		try {
			for (ItemGroupBean itemGroupBean : itemGroupBeans) {
				misAuditBean = itemGroupBean.getMisAuditBean();
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				itemGroupBean.setMisAuditBean(misAuditBean);
			}
			
			result = itemGroupDao.updateItemGroup(itemGroupBeans);
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
		
	}
	
	private ItemGroupBean populateItemGroupBean(ItemGroupForm itemGroupForm) throws Exception{
		
		ItemGroupBean itemGroupBean;
		try {
			itemGroupBean = new ItemGroupBean();
			itemGroupBean.setItemGroupId(itemGroupForm.getItemGroupId());
			itemGroupBean.setItemGroupName(itemGroupForm.getItemGroupName());
		} catch (Exception e) {
			throw e;
		}
		
		return itemGroupBean;
	}


}
