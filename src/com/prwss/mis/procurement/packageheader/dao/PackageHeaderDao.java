package com.prwss.mis.procurement.packageheader.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface PackageHeaderDao {
	
	public  List<PackageHeaderBean> findPackage(PackageHeaderBean packageHeaderBean ,List<String> statusList) throws DataAccessException;

	public boolean savePackage(PackageHeaderBean packageHeaderBean) throws DataAccessException;
	public boolean savePackageHeader(List<PackageHeaderBean> packageHeaderBean)throws DataAccessException;
	public boolean updatePackage(PackageHeaderBean packageHeaderBean) throws DataAccessException;

	public Set<PackageHeaderBean> getPackageIds(String schemeId , Long subPlanId)  throws DataAccessException;
	public Set<PackageHeaderBean> getPackageIds(String schemeId , Long subPlanId, List<String> statusList)  throws DataAccessException;
	public Set<PackageHeaderBean> getPackageIdsAll(String schemeId , long subPlanId)  throws DataAccessException;
	public List<PackageHeaderBean> findPackageForPriorReview(PackageHeaderBean packageHeaderBean, List<String> statusList) throws DataAccessException;

	public List<PackageHeaderBean> getPackageForSubPlan(PackageHeaderBean packageHeaderBean)throws DataAccessException;


}
