package com.prwss.mis.masters.vendor;

import java.util.List;
import java.util.Set;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.vendor.dao.VendorBean;
import com.prwss.mis.masters.vendor.struts.VendorForm;

public interface VendorBO {

	public List<VendorBean> findVendor(VendorForm vendorForm, List<String> statusList) throws MISException;
	
	public String saveVendor(VendorForm vendorForm, MISSessionBean misAuditBean) throws MISException;
	
	/*public boolean updateVendor(VendorForm vendorForm, MISSessionBean misAuditBean) throws MISException;
	*/
	public boolean deleteVendor(VendorForm vendorForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postVendor(VendorForm vendorForm, MISSessionBean misAuditBean) throws MISException;
	
	public Set<VendorBean> getDistinctVendorCodes() throws MISException;

	boolean updateVendor(VendorForm vendorForm, MISSessionBean misAuditBean,
			List<String> statusList) throws MISException;

}
