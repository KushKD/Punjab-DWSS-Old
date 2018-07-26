package com.prwss.mis.masters.store;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.store.struts.StoreForm;
import com.prwss.mis.masters.store.dao.StoreBean;

public interface StoreBO {
	
	public List<StoreBean> findStore(StoreForm storeForm, List<String> statusList) throws MISException;
	
	public long saveStore(StoreForm storeForm, MISSessionBean misSessionBean) throws MISException;
	
	/*public boolean updateStore(StoreForm storeForm, MISSessionBean misSessionBean) throws MISException;
	*/
	public boolean deleteStore(StoreForm storeForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postStore(StoreForm storeForm, MISSessionBean misSessionBean) throws MISException;
	public List<Long> getStoreIds(StoreForm storeForm, List<String> statusList) throws MISException;

	boolean updateStore(StoreForm storeForm, MISSessionBean misSessionBean,
			List<String> statusList) throws MISException;

}
