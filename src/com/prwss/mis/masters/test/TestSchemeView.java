package com.prwss.mis.masters.test;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.masters.scheme.dao.TestSchemeViewBean;
import com.prwss.mis.masters.scheme.dao.TestSchemeViewDao;


public class TestSchemeView extends TestCase {

	private TestSchemeViewDao testSchemeViewDao;
	private MISSessionBean misAuditBean;
	
	
	protected void setUp() throws Exception {
		testSchemeViewDao = (TestSchemeViewDao)SpringContextLoader.getBean("testSchemeViewDao");
		misAuditBean = new MISSessionBean();
	}
	
	public void testFindDistrict() {
		TestSchemeViewBean testSchemeViewBean = new TestSchemeViewBean();
		testSchemeViewBean.setDistrictId("D13");
		try {
			List<TestSchemeViewBean> testSchemeViewBeans = testSchemeViewDao.finTestView(testSchemeViewBean, null);
			for (TestSchemeViewBean testSchemeViewBean2 : testSchemeViewBeans) {
				System.out.println("DistrictId \t"+testSchemeViewBean2.getDistrictId());
				System.out.println("Scheme Name \t"+testSchemeViewBean2.getSchemeName());
				System.out.println("Admin Approval \t"+testSchemeViewBean2.getAdminApprovalNumber());
			}
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
