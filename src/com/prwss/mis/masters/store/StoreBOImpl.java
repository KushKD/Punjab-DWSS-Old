package com.prwss.mis.masters.store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.store.struts.StoreForm;
import com.prwss.mis.masters.store.dao.StoreBean;
import com.prwss.mis.masters.store.dao.StoreDao;

public class StoreBOImpl implements StoreBO {
	
	private Logger log = Logger.getLogger(StoreBOImpl.class);
	
	private StoreDao storeDao;
	
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	@Override
	public List<StoreBean> findStore(StoreForm storeForm, List<String> statusList) throws MISException {
		List<StoreBean> storeBeans = null;
		
		try {
			if(MisUtility.ifEmpty(storeForm.getLocationId())){
				StoreBean storeBean = new StoreBean();
					storeBean.setLocationId(storeForm.getLocationId());
				if(MisUtility.ifEmpty(storeForm.getStoreId())){
					storeBean.setStoreId(storeForm.getStoreId());
				}
			storeBeans = storeDao.findStore(storeBean, statusList);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		
		return storeBeans;
	}

	@Override
	public long saveStore(StoreForm storeForm, MISSessionBean misSessionBean) throws MISException {
		long storeId = 0;
		try {
			StoreBean storeBean = populateStoreBean(storeForm);
			MISAuditBean misAuditBean = new MISAuditBean();			
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(MISConstants.MASTER_STATUS_VERIFIED);
			storeBean.setMisAuditBean(misAuditBean);
			System.out.println("Before Saving in BO"+storeBean);
			storeId = storeDao.saveStore(storeBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		System.out.println("StoreID"+storeId);
		return storeId;
	}

	@Override
	public boolean updateStore(StoreForm storeForm, MISSessionBean misSessionBean,List<String> statusList) throws MISException {
		try {
			StoreBean storeBean = populateStoreBean(storeForm);
			
			MISAuditBean misAuditBean = new MISAuditBean();			
			misAuditBean.setEntBy(misSessionBean.getEnteredBy());
			misAuditBean.setEntDate(misSessionBean.getEnteredDate());
			misAuditBean.setStatus(statusList.get(0));
			storeBean.setMisAuditBean(misAuditBean);
			
			storeDao.updateStore(storeBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return true;
	}

	@Override
	public boolean deleteStore(StoreForm storeForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
		List<StoreBean> storeBeans = storeDao.findStore(Arrays.asList(storeForm.getStoreIds()));
		MISAuditBean misAuditBean = new MISAuditBean();			
		misAuditBean.setEntBy(misSessionBean.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean.getEnteredDate());
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
		
		try {
			for (StoreBean storeBean : storeBeans) {
				storeBean.setMisAuditBean(misAuditBean);
			}
			result = storeDao.updateStore(storeBeans);
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}

	@Override
	public boolean postStore(StoreForm storeForm, MISSessionBean misSessionBean) throws MISException {
		boolean result = false;
		List<StoreBean> storeBeans = storeDao.findStore(Arrays.asList(storeForm.getStoreIds()));
		MISAuditBean misAuditBean = null;	
		
		try {
			for (StoreBean storeBean : storeBeans) {
				misAuditBean = storeBean.getMisAuditBean();
				misAuditBean.setEntBy(misSessionBean.getEnteredBy());
				misAuditBean.setEntDate(misSessionBean.getEnteredDate());
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
			}
			result = storeDao.updateStore(storeBeans);
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}
		return result;
	}
	
	private StoreBean populateStoreBean(StoreForm storeForm){
		StoreBean storeBean;
		
			storeBean = new StoreBean();
			storeBean.setStoreId(storeForm.getStoreId());
			storeBean.setStoreName(storeForm.getStoreName());
			storeBean.setStoreAddress(storeForm.getStoreAddress());
			storeBean.setLocationId(storeForm.getLocationId());
		
		return storeBean;
	}

	@Override
	public List<Long> getStoreIds(StoreForm storeForm, List<String> statusList)
			throws MISException {
		List<StoreBean>stb=findStore(storeForm, statusList);
		List<Long> storeIds=new ArrayList<Long>();
		if(!MisUtility.ifEmpty(stb))
		{
			for(StoreBean storeBean:stb)
			{
				storeIds.add(storeBean.getStoreId());
			}
		}
		return storeIds;
	}

}
