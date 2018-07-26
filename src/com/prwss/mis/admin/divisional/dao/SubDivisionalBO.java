package com.prwss.mis.admin.divisional.dao;

import java.util.List;

import com.prwss.mis.admin.struts.SubDivisionalForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

public interface SubDivisionalBO {

	public List<SubDivisionalBean> findSubDivisional(SubDivisionalForm subdivisionalForm, List<String> statusList) throws MISException;
	
	public boolean saveSubDivisional(SubDivisionalForm subdivisionalForm, MISSessionBean misAuditBean) throws MISException;
	
	/*public boolean updateSubDivisional(SubDivisionalForm subdivisionalForm, MISSessionBean misAuditBean) throws MISException;
	*/
	public boolean deleteSubDivisional(SubDivisionalForm subdivisionalForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postSubDivisional(SubDivisionalForm subdivisionalForm, MISSessionBean misAuditBean) throws MISException;

	boolean updateSubDivisional(SubDivisionalForm subdivisionalForm,
			MISSessionBean misAuditBean, List<String> statusList)
			throws MISException;

}
