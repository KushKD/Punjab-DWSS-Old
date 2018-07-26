package com.prwss.mis.masters.scheme;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.scheme.struts.ExtentionSchemeMasterForm;
import com.prwss.mis.masters.scheme.struts.SchemeHeaderVillageForm;
import com.prwss.mis.masters.scheme.struts.SchemeVillageCommisionForm;
import com.prwss.mis.pmm.operatingarrangement.OperatingArrangementForm;

public interface SchemeMasterBO {

	public SchemeMasterBean findSchemeMaster(SchemeHeaderVillageForm  schemeHeaderVillageForm, List<String> statusList) throws MISException;
	
	public SchemeMasterBean findSchemeMaster(SchemeVillageCommisionForm  schemeVillageCommisionForm, List<String> statusList) throws MISException;
	
	public String saveSchemeMaster(SchemeHeaderVillageForm  schemeHeaderVillageForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateSchemeMaster(SchemeHeaderVillageForm  schemeHeaderVillageForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateSchemeMaster(SchemeVillageCommisionForm  schemeVillageCommisionForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateSchemeMaster(SchemeVillageCommisionForm  schemeVillageCommisionForm,  MISSessionBean misSessionBean , HashMap<String,String> VillageCategory) throws MISException;
	
	public boolean deleteSchemeMaster(SchemeHeaderVillageForm  schemeHeaderVillageForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postSchemeMaster(SchemeHeaderVillageForm  schemeHeaderVillageForm,  MISSessionBean misSessionBean) throws MISException;

	public SchemeMasterBean findSchemeMasterForExtendedScheme(ExtentionSchemeMasterForm extentionSchemeMasterForm,List<String> statusList) throws MISException;

	public SchemeMasterBean findSchemeMasterForExtendedScheme1(
			ExtentionSchemeMasterForm extentionSchemeMasterForm,
			List<String> statusList) throws MISException;

	public SchemeMasterBean findSchemeMaster1(
			SchemeVillageCommisionForm schemeVillageCommisionForm,
			List<String> statusList) throws MISException;

	public SchemeMasterBean findSchemeMaster(
			OperatingArrangementForm operatingArrangementForm,
			List<String> statusList) throws MISException;

	public boolean updateSchemeMaster(
			OperatingArrangementForm operatingArrangementForm,
			MISSessionBean misSessionBean) throws MISException, ParseException;
}
