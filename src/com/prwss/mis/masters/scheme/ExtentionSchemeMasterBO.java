package com.prwss.mis.masters.scheme;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.scheme.struts.ExtentionSchemeMasterForm;
import com.prwss.mis.masters.scheme.struts.SchemeHeaderVillageForm;

public interface ExtentionSchemeMasterBO {

	public boolean updateSchemeMaster(ExtentionSchemeMasterForm  ectExtentionSchemeMasterForm,  MISSessionBean misSessionBean) throws MISException;

	boolean postSchemeMaster(ExtentionSchemeMasterForm extentionSchemeMasterForm,MISSessionBean misSessionBean) throws MISException;
}
