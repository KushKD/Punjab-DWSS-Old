package com.prwss.mis.procurement.packagecomponents.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.procurement.physicalprogresspackage.dao.PrwssSigningContractViewBean;

public interface PackageComponentsDao {
	
	 public List<PackageComponentsBean> findServicesPackageComponent(PackageComponentsBean packageComponentsBean , List<String> statusList) throws DataAccessException;
	    
	 public List<PrwssSigningContractViewBean> findSigningOfContract(
				String  packageId)
				throws DataAccessException;
		public boolean saveOrUpdateServicesPackageComponent(Collection<PackageComponentsBean> packageComponentsBean) throws DataAccessException;



}
