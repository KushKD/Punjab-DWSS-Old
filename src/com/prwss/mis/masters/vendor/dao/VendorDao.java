package com.prwss.mis.masters.vendor.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface VendorDao {
	
	public List<VendorBean> findVendor(VendorBean vendorBeans, List<String> statusList) throws DataAccessException;
	
	public List<VendorBean> findVendor(List<String> vendorIds) throws DataAccessException;

	public boolean saveVendor(VendorBean vendorBean) throws DataAccessException;

	public boolean updateVendor(VendorBean vendorBeans)	throws DataAccessException;
	
	public boolean updateVendor(List<VendorBean> vendorBeans) throws DataAccessException;

	public Set<VendorBean> getDistinctVendorCodes() throws DataAccessException;

	public List<VendorBean> getVendersNames()throws DataAccessException;

}
