package com.prwss.mis.masters.test;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.masters.store.struts.StoreForm;
import com.prwss.mis.masters.store.StoreBO;

import junit.framework.TestCase;

public class TestStore extends TestCase {
	
	private StoreBO storeBO;
	private MISSessionBean misSessionBean = new MISSessionBean();
	
	public void setStoreBO(StoreBO storeBO) {
		this.storeBO = storeBO;
	}


	protected void setUp() throws Exception {
		storeBO = (StoreBO)SpringContextLoader.getBean("storeBO");
	}


//	public void testFindStore() {
//		fail("Not yet implemented"); // TODO
//	}

	public void testSaveStore() {
		StoreForm storeForm = new StoreForm();
		storeForm.setStoreId(1);
		try {
			storeBO.saveStore(storeForm, misSessionBean);
		} catch (MISException e) {
			e.printStackTrace();
		}
		
	}

//	public void testUpdateStore() {
//		fail("Not yet implemented"); // TODO
//	}

//	public void testDeleteStore() {
//		fail("Not yet implemented"); // TODO
//	}

//	public void testPostStore() {
//		fail("Not yet implemented"); // TODO
//	}

}
