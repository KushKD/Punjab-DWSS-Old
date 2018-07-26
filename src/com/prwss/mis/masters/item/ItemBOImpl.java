package com.prwss.mis.masters.item;

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
import com.prwss.mis.masters.item.dao.ItemBean;
import com.prwss.mis.masters.item.dao.ItemDao;
import com.prwss.mis.masters.item.dao.ItemGroupBean;
import com.prwss.mis.masters.item.struts.ItemForm;
import com.prwss.mis.masters.itemgroup.ItemGroupBOImpl;
import com.prwss.mis.masters.store.dao.StoreBean;
import com.prwss.mis.masters.unitofmeasurement.dao.UnitOfMeasurementBean;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;

public class ItemBOImpl extends ItemGroupBOImpl implements ItemBO {
	
	private Logger log = Logger.getLogger(ItemBOImpl.class);
	private  ItemDao itemDao;
	private DocumentNumberDAO documentNumberDao;
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}
	@Override
	public List<ItemBean> findItem(ItemForm itemForm, List<String> statusList) throws MISException {
		List<ItemBean> itemBeans = null;
		try {
			ItemBean itemBean = new ItemBean();
			StoreBean storeBean = new StoreBean();
			storeBean.setStoreId(itemForm.getStoreId());
			itemBean.setItemId(itemForm.getItemId());
			itemBean.setStore(storeBean);
			itemBean.setItemName(itemForm.getItemName());
			itemBeans = itemDao.findItem(itemBean, statusList);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return itemBeans;
	}

	@Override
	public boolean saveItem(ItemForm itemForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		log.debug("-----------in boimpl-------------");
		log.debug(itemForm.toString());
		try {
			DocumentNumberBean documentNumebrBean=new DocumentNumberBean();
			documentNumebrBean.setDocumentType("ITEM");
			DocumentNumberBean documentNumberBean = documentNumberDao.getDocumentNumber(documentNumebrBean).get(0);
			if(!(MisUtility.ifEmpty(documentNumberBean))){
				throw new MISException();
			}
			Long docLastNo=documentNumberBean.getLastNumber();
			
			List<String> itemGroupIds = new ArrayList<String>();
			itemGroupIds.add(itemForm.getItemGroupId());
			List<ItemGroupBean> itemGroupBeans = itemGroupDao.findItemGroup(itemGroupIds);
			if(MisUtility.ifEmpty(itemGroupBeans)){				
				throw new MISException(MISExceptionCodes.MIS001,itemForm.getItemGroupId()+ " is not available.");
			}
			ItemGroupBean itemGroupBean=itemGroupBeans.get(0);
			//System.out.println("itemGroupBean: "+itemGroupBean);			
			
			String itemId=itemForm.getLocationId()+"-"+itemGroupBean.getItemGroupName()+"-"+docLastNo;
			//System.out.println("itemId: "+itemId);
			StoreBean storeBean = new StoreBean();
			storeBean.setStoreId(itemForm.getStoreId());
			ItemBean itemFindBean = new ItemBean();
			itemFindBean.setItemId(itemId);
			itemFindBean.setStore(storeBean);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			List<ItemBean> itemBeans = itemDao.findItem(itemFindBean, statusList);
			
			if(!MisUtility.ifEmpty(itemBeans)){	
				StringBuffer message = new StringBuffer();
				String itemName = itemBeans.get(0).getItemName();
				message.append(" Item Id ").append(itemForm.getItemId());				
				message.append(" and Item Name ").append(itemName);			
				log.debug("Duplicate Entry for "+itemForm.getItemId()+" and "+ itemForm.getItemName());
				log.debug("Item Already exist \n"+itemBeans);
				throw new MISException(MISExceptionCodes.MIS001 , message.toString());
			}else itemForm.setItemId(itemId);
			
			ItemBean itemBean = populateItemBean(itemForm);
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			
			itemBean.setMisAuditBean(misAuditBean);
		//	System.out.println("Save******************"+misAuditBean);
			status = itemDao.saveItem(itemBean);
			if(MisUtility.ifEmpty(status)){
				documentNumberBean.setLastNumber(docLastNo);
			}
			boolean updateStatus = documentNumberDao.saveOrUpdateDocumentNumberBeans(documentNumberBean);
			if(!updateStatus){
				throw new MISException();
			}
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
	public boolean updateItem(ItemForm itemForm, MISSessionBean misSessionBean) throws MISException {
		boolean status = false;
		
		try {
			ItemBean itemBean =  populateItemBean(itemForm);
			
			MISAuditBean misAuditBean = new MISAuditBean();
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			
			itemBean.setMisAuditBean(misAuditBean);
			
			status = itemDao.updateItem(itemBean);
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
	public boolean deleteItem(ItemForm itemForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
//		List<ItemBean> itemBeans = itemDao.findItem(Arrays.asList(itemForm.getItemIds()));
	try{
		ItemBean itemBean = populateItemBean(itemForm);
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
		
//		try {
//			for (ItemBean itemBean : itemBeans) {
				itemBean.setMisAuditBean(misAuditBean);
//			}
			
			result = itemDao.updateItem(itemBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}

	@Override
	public boolean postItem(ItemForm itemForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
//		List<ItemBean> itemBeans = itemDao.findItem(Arrays.asList(itemForm.getItemIds()));
		try {
			MISAuditBean misAuditBean = null;
			ItemBean itemBean = new ItemBean();
			itemBean.setItemId(itemForm.getItemId());
			ItemBean itemBean2 = itemDao.findItem(itemBean, null).get(0);
//			for (ItemBean itemBean : itemBeans) {
				misAuditBean = itemBean2.getMisAuditBean();
				misAuditBean.setAuthBy(misSessionBean.getEnteredBy());
				misAuditBean.setAuthDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
				itemBean2.setMisAuditBean(misAuditBean);
//			}
			result = itemDao.updateItem(itemBean2);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}
	
	private ItemBean populateItemBean(ItemForm itemForm) throws Exception{
		ItemBean itemBean;
		try {
			itemBean = new ItemBean();
			itemBean.setItemId(itemForm.getItemId());
			itemBean.setItemName(itemForm.getItemName());			
			ItemGroupBean itemGroupBean = new ItemGroupBean();
			UnitOfMeasurementBean unitOfMeasurementBean=new UnitOfMeasurementBean();
			StoreBean storeBean=new StoreBean();
			itemGroupBean.setItemGroupId(itemForm.getItemGroupId());
			unitOfMeasurementBean.setMeasurementId(itemForm.getUnitOfMeasurement());
			storeBean.setStoreId(itemForm.getStoreId());
			itemBean.setItemGroup(itemGroupBean);	
			itemBean.setUnitOfMeasurement(unitOfMeasurementBean);
			itemBean.setStore(storeBean);
			itemBean.setOpeningBalance(itemForm.getOpeningBalance());			
			itemBean.setServiceable(itemForm.getServiceable());	
			itemBean.setStoreIssueRate(itemForm.getStoreIssueRate());
			itemBean.setLocationId(itemForm.getLocationId());
//			System.out.println("itemForm.getStoreIssueRate()"+itemForm.getStoreIssueRate());
//			System.out.println("itemBean"+itemBean.getStoreIssueRate());
		} catch (Exception e) {
			throw e;
		}
		
		return itemBean;
		
	}

}
