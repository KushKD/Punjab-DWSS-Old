package com.prwss.mis.procurement.nonconsultancypackage;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.procurement.nonconsultancypackage.struts.PackageNonConsultancyForm;
import com.prwss.mis.procurement.packageheader.dao.PackageDetailBean;

public interface PackageNonConsultancyBO {
public PackageDetailBean findPackageNonConsltFrom(PackageNonConsultancyForm packageNonConsultancyForm, List<String> statusList) throws MISException;
	
	public boolean savePackageNonConsltFrom(PackageNonConsultancyForm packageNonConsultancyForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updatePackageNonConsltFrom(PackageNonConsultancyForm packageNonConsultancyForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean deletePackageNonConsltFrom(PackageNonConsultancyForm packageNonConsultancyForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postPackageNonConsltFrom(PackageNonConsultancyForm packageNonConsultancyForm, MISSessionBean misSessionBean) throws MISException;

}
