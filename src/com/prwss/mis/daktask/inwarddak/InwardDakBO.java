package com.prwss.mis.daktask.inwarddak;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.daktask.inwarddak.dao.InwardDakBean;
import com.prwss.mis.daktask.inwarddak.struts.InwardDakForm;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;


public interface InwardDakBO {
	public List<InwardDakBean> findInwardDak(InwardDakForm inwardDakForm , List<String> statusList) throws MISException;
	
	public boolean saveInwardDak(InwardDakForm inwardDakForm, MISSessionBean misSessionBean,DocumentNumberBean documentNumberBean) throws MISException;
	public boolean updateInwardDak(InwardDakForm inwardDakForm, MISSessionBean misSessionBean,DocumentNumberBean documentNumberBean) throws MISException;
		
}
