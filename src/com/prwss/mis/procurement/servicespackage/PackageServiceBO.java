package com.prwss.mis.procurement.servicespackage;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.procurement.packageheader.dao.PackageDetailBean;
import com.prwss.mis.procurement.servicespackage.struts.PackageServiceForm;

public interface PackageServiceBO {
	
public PackageDetailBean findPackageServicesFrom(PackageServiceForm packageServiceForm, List<String> statusList) throws MISException;
	
	public boolean savePackageServicesFrom(PackageServiceForm packageServiceForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updatePackageServicesFrom(PackageServiceForm packageServiceForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean deletePackageServicesFrom(PackageServiceForm packageServiceForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postPackageServicesFrom(PackageServiceForm packageServiceForm, MISSessionBean misSessionBean) throws MISException;



}
