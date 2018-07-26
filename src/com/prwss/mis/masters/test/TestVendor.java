package com.prwss.mis.masters.test;

import junit.framework.TestCase;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.SpringContextLoader;
import com.prwss.mis.masters.vendor.VendorBO;
import com.prwss.mis.masters.vendor.struts.VendorForm;

public class TestVendor extends TestCase {
	
	private VendorBO vendorBO;
	private MISSessionBean misAuditBean;

	public void setVendorBO(VendorBO vendorBO) {
		this.vendorBO = vendorBO;
	}

	
	@Override
	protected void setUp() throws Exception {

		vendorBO = (VendorBO)SpringContextLoader.getBean("vendorBO");
		misAuditBean = new MISSessionBean();
		
	}

//	public void testFindVendor() {
//		VendorForm vendorForm = new VendorForm();
//		vendorForm.setVendorId("B");
//		vendorForm.setPinCode(5000013);
//		try {
//			System.out.println(vendorBO.findVendor(vendorForm, null));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void testSaveVendor() {
		VendorForm vendorForm = new VendorForm();
		vendorForm.setVendorId("B222223");
		vendorForm.setVendorName("KPMG");
	
		vendorForm.setAddress1("address1");
		vendorForm.setAddress2("address2");
		vendorForm.setCity("city");
		vendorForm.setEmail("email");
		vendorForm.setMobPhoneNo(9866744498L);
		vendorForm.setPanNo("panNo");
		vendorForm.setPinCode(5000013);
		vendorForm.setState("AP");
		vendorForm.setStreet("street");
		vendorForm.setWorkPhoneNo(9999999999L);
		
		try {
			System.out.println(vendorBO.saveVendor(vendorForm, misAuditBean));
		} catch (MISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void testUpdateVendor() {
//		VendorForm vendorForm = new VendorForm();
//		vendorForm.setVendorId("B");
//		vendorForm.setVendorName("Vendor B");
//		vendorForm.setAddress1("address");
//		vendorForm.setAddress2("address2");
//		vendorForm.setCity("city");
//		vendorForm.setEmail("email");
//		vendorForm.setMobPhoneNo(9866744498L);
//		vendorForm.setPanNo("panNo");
//		vendorForm.setPinCode(5000013);
//		vendorForm.setState("AP");
//		vendorForm.setStreet("street");
//		vendorForm.setWorkPhoneNo(9999999999L);
//		
//		try {
//			System.out.println(vendorBO.updateVendor(vendorForm, misAuditBean));
//		} catch (MISException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public void testDeleteVendor() {
//		VendorForm vendorForm = new VendorForm();
//		String[] vendorIds = {"A"}; 
//		vendorForm.setVendorIds(vendorIds);
//		try {
//			System.out.println(vendorBO.deleteVendor(vendorForm,misAuditBean));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	public void testPostVendor() {
//		VendorForm vendorForm = new VendorForm();
//		String[] vendorIds = {"B"}; 
//		vendorForm.setVendorIds(vendorIds);
//		try {
//			System.out.println(vendorBO.postVendor(vendorForm,misAuditBean));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
