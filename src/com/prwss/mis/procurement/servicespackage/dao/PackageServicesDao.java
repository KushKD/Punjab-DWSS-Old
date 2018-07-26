package com.prwss.mis.procurement.servicespackage.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.procurement.servicespackage.PackageServicesBean;

public interface PackageServicesDao {

	public  List<PackageServicesBean> findServicesPackage(PackageServicesBean packageServicesBean ,List<String> statusList) throws DataAccessException;

	public boolean saveServicesPackage(PackageServicesBean packageServicesBean) throws DataAccessException;

	public boolean updateServicesPackage(PackageServicesBean packageServicesBean) throws DataAccessException;
	
	public Set<PackageServicesBean> getPackageIds(String schemeId)  throws DataAccessException;

}
