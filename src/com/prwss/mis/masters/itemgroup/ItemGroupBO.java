package com.prwss.mis.masters.itemgroup;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.item.dao.ItemGroupBean;
import com.prwss.mis.masters.itemgroup.struts.ItemGroupForm;

public interface ItemGroupBO {
	
	public List<ItemGroupBean> findItemGroup(ItemGroupForm itemGroupForm, List<String> statusList) throws MISException;
	
	public boolean saveItemGroup(ItemGroupForm itemGroupForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateItemGroup(ItemGroupForm itemGroupForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteItemGroup(ItemGroupForm itemGroupForm, MISSessionBean misSessionBean) throws MISException ;
	
	public boolean postItemGroup(ItemGroupForm itemGroupForm, MISSessionBean misSessionBean) throws MISException;

	

}
