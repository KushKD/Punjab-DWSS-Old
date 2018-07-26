package com.prwss.mis.masters.iecmaterial;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.iecmaterial.dao.IECMaterialBean;
import com.prwss.mis.masters.iecmaterial.struts.IecMaterialForm;

public interface IECMaterialBO {
	
	public List<IECMaterialBean> findIECMaterial(IecMaterialForm iecMaterialForm, List<String> statusList) throws MISException;
	
	public String saveIECMaterial(IecMaterialForm iecMaterialForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean updateIECMaterial(IecMaterialForm iecMaterialForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean deleteIECMaterial(IecMaterialForm iecMaterialForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postIECMaterial(IecMaterialForm iecMaterialForm, MISSessionBean misAuditBean) throws MISException;


}
