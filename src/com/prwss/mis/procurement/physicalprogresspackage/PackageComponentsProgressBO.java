package com.prwss.mis.procurement.physicalprogresspackage;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.procurement.physicalprogresspackage.dao.PhysicalProgressComponentsBean;
import com.prwss.mis.procurement.physicalprogresspackage.struts.PhysicalProgressPackageForm;

public interface PackageComponentsProgressBO {
	
public List<PhysicalProgressComponentsBean> findPhysicalProgressPackageForm(PhysicalProgressPackageForm physicalProgressPackageForm, List<String> statusList) throws MISException;
	
	public boolean savePhysicalProgressPackageForm(PhysicalProgressPackageForm physicalProgressPackageForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updatePhysicalProgressPackageForm(PhysicalProgressPackageForm physicalProgressPackageForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean deletePhysicalProgressPackageForm(PhysicalProgressPackageForm physicalProgressPackageForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postPhysicalProgressPackageForm(PhysicalProgressPackageForm physicalProgressPackageForm, MISSessionBean misSessionBean) throws MISException;

}
