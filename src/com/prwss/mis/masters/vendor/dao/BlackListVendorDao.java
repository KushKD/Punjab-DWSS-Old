package com.prwss.mis.masters.vendor.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.procurement.plan.CreateProcPlanBean;

public interface BlackListVendorDao {
	public List<BlackListVendorBean> findBlackListVendor(BlackListVendorBean blackListVendorBean, List<String> statusList) throws DataAccessException;
	public List<BlackListVendorBean> findBlackListVendor(List<String> vendorIds) throws DataAccessException;
	public boolean updateBlackListVendor(BlackListVendorBean blackListVendorBean)	throws DataAccessException;
	public boolean updateBlackListVendor(List<BlackListVendorBean> blackListVendorBeans) throws DataAccessException;
	public boolean saveBlackListVendor(BlackListVendorBean blackListVendorBean) throws DataAccessException;
}
