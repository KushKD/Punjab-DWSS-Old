package com.prwss.mis.procurement.workspackage;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.procurement.packageheader.dao.PackageDetailBean;
import com.prwss.mis.procurement.workspackage.struts.PackageWorksForm;

public interface PackageWorksBO {
public PackageDetailBean findPackageWorksFrom(PackageWorksForm packageWorksForm, List<String> statusList) throws MISException;
	
	public boolean savePackageWorksFrom(PackageWorksForm packageWorksForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updatePackageWorksFrom(PackageWorksForm packageWorksForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean deletePackageWorksFrom(PackageWorksForm packageWorksForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postPackageWorksFrom(PackageWorksForm packageWorksForm, MISSessionBean misSessionBean) throws MISException;

}
