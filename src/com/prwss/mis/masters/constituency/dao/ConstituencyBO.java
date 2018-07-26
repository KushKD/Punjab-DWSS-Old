package com.prwss.mis.masters.constituency.dao;

import java.util.List;

import com.prwss.mis.admin.struts.ConstituencyForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

public interface ConstituencyBO {
	public List<ConstituencyBean> findConstituency(ConstituencyForm constituencyForm, List<String> statusList) throws MISException;
	
	public boolean saveConstituency(ConstituencyForm constituencyForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean updateConstituency(ConstituencyForm constituencyForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean deleteConstituency(ConstituencyForm constituencyForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postConstituency(ConstituencyForm constituencyForm, MISSessionBean misAuditBean) throws MISException;

}
