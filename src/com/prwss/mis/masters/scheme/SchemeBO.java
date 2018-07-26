package com.prwss.mis.masters.scheme;


import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.scheme.dao.SchemeBean;
import com.prwss.mis.masters.scheme.struts.SchemeForm;
import com.prwss.mis.masters.scheme.struts.SchemeVillageCommisionForm;

public interface SchemeBO {
public List<SchemeBean> findScheme(SchemeForm schemeForm,MISSessionBean misSessionBean, List<String> statusList) throws MISException;
	
	public boolean saveScheme(SchemeForm schemeForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateScheme(SchemeForm schemeForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteScheme(SchemeForm schemeForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postScheme(SchemeForm schemeForm,  MISSessionBean misSessionBean) throws MISException;
	
//rohit	public List<SchemeBean> findAllData(SchemeVillageCommisionForm schemeVillageCommisionForm) throws MISException;
	
}

