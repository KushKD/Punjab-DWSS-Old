package com.prwss.mis.masters.item;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.item.dao.ItemBean;
import com.prwss.mis.masters.item.struts.ItemForm;

public interface ItemBO {
	
	public List<ItemBean> findItem(ItemForm itemForm, List<String> statusList) throws MISException;
	
	public boolean saveItem(ItemForm itemForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateItem(ItemForm itemForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteItem(ItemForm itemForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postItem(ItemForm itemForm, MISSessionBean misSessionBean) throws MISException;

	

}
