package com.prwss.mis.masters.test;

import junit.framework.TestCase;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.masters.district.DistrictBO;
import com.prwss.mis.masters.district.struts.DistrictForm;

public class TestDistrict extends TestCase {

	private DistrictBO districtBO;
	private MISSessionBean misAuditBean;
	
	protected void setUp() throws Exception {
		districtBO = (DistrictBO)SpringContextLoader.getBean("districtBO");
		misAuditBean = new MISSessionBean();
	}

//	public void testFindDistrict() {
//		
//		DistrictForm districtForm = new DistrictForm();
//		districtForm.setAddress1("3-12-117/A/6");
//		districtForm.setAddress2("Ganesh Nagar");
//		districtForm.setStreet("Ramanthapur");
//		districtForm.setCircleCode("1");
//		districtForm.setCity("Hyderabad");
//		districtForm.setDistrictCode("1");
//		districtForm.setDistrictName("Ranga Reddy");
//		districtForm.setEmail("vinaygadiraju@gmail.com");
//		districtForm.setIsSpmc(true);
//		districtForm.setPinCode(500013);
//		
//		try {
//			System.out.println(districtBO.findDistrict(districtForm, null));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

	public void testSaveDistrict() {
		DistrictForm districtForm = new DistrictForm();
		districtForm.setAddress1("3-12-117/A/6");
		districtForm.setAddress2("Ganesh Nagar");
		districtForm.setStreet("Ramanthapur");
		districtForm.setCircleCode("CERAMT");
		districtForm.setCity("Amritsar");
		districtForm.setDistrictCode("AMT");
		districtForm.setDistrictName("Ranga Reddy");
		districtForm.setEmail("vikash@gmail.com");
		districtForm.setIsSpmc(false);
		districtForm.setPinCode(500013);
		
		
		try {
			districtBO.saveDistrict(districtForm, misAuditBean);
		} catch (MISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void testUpdateDistrict() {
//		DistrictForm districtForm = new DistrictForm();
//		districtForm.setAddress1("3-12-117/A/6");
//		districtForm.setAddress2("Ganesh Nagar");
//		districtForm.setStreet("Ramanthapur");
//		districtForm.setCircleCode("1");
//		districtForm.setCity("Hyderabad");
//		districtForm.setDistrictCode("1");
//		districtForm.setDistrictName("Ranga Reddy");
//		districtForm.setEmail("vinaygadiraju@yahoomail.com");
//		districtForm.setIsSpmc(false);
//		districtForm.setPinCode(500013);
//		
//		try {
//			districtBO.updateDistrict(districtForm, misAuditBean);
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public void testDeleteDistrict() {
//		
//		DistrictForm districtForm = new DistrictForm();
//		
//		String[] districtCodes = {"1"};
//		districtForm.setDistrictCodes(districtCodes );
//		
//		try {
//			districtBO.deleteDistrict(districtForm,misAuditBean);
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public void testPostDistrict() {
//		DistrictForm districtForm = new DistrictForm();
//		
//		String[] districtCodes = {"1"};
//		districtForm.setDistrictCodes(districtCodes );
//		
//		try {
//			districtBO.postDistrict(districtForm,misAuditBean);
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
