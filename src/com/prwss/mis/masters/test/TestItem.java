package com.prwss.mis.masters.test;

import junit.framework.TestCase;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.masters.item.ItemBO;

public class TestItem extends TestCase {

	private ItemBO itemBO;
	private MISSessionBean misAuditBean;
	
	@Override
	protected void setUp() throws Exception {
		itemBO = (ItemBO)SpringContextLoader.getBean("itemBO");
		misAuditBean = new MISSessionBean();
	}

//	public void testFindItem() {
//		ItemForm itemForm = new ItemForm();
//		itemForm.setItemId("A");
//		itemForm.setItemName("AAA");
//		itemForm.setItemNature("A nature");
//
//		try {
//			System.out.println(itemBO.findItem(itemForm, null));
//		} catch (MISException e) {
//			e.printStackTrace();
//		}
//	}

//	public void testSaveItem() {
//		ItemForm itemForm = new ItemForm();
//		itemForm.setItemId("A");
//		itemForm.setItemName("AAA");
//		itemForm.setItemNature("A nature");
//		try {
//			itemBO.saveItem(itemForm, misAuditBean);
//		} catch (MISException e) {
//			e.printStackTrace();
//		}
//	}

//	public void testUpdateItem() {
//		ItemForm itemForm = new ItemForm();
//		itemForm.setItemId("A");
//		itemForm.setItemName("AAAaaaaaaaa");
//		itemForm.setItemNature("A nature");
//		try {
//			itemBO.updateItem(itemForm, misAuditBean);
//		} catch (MISException e) {
//			e.printStackTrace();
//		}
//	}

//	public void testDeleteItem() {
//		String[] itemIds ={"A"};
//		ItemForm itemForm = new ItemForm();
//		itemForm.setItemIds(itemIds);
//		try {
//			itemBO.deleteItem(itemForm, misAuditBean);
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public void testPostItem() {
//		String[] itemIds ={"A"};
//		ItemForm itemForm = new ItemForm();
//		itemForm.setItemIds(itemIds);
//		try {
//			itemBO.postItem(itemForm, misAuditBean);
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
