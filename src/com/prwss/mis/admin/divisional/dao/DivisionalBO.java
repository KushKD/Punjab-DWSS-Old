package com.prwss.mis.admin.divisional.dao;

import java.util.List;

import com.prwss.mis.admin.struts.DivisionalForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

public interface DivisionalBO {

	public List<DivisionalBean> findDivisional(DivisionalForm divisionalForm, List<String> statusList) throws MISException;
	
	public boolean saveDivisional(DivisionalForm divisionalForm, MISSessionBean misAuditBean) throws MISException;
	
	/*public boolean updateDivisional(DivisionalForm divisionalForm, MISSessionBean misAuditBean) throws MISException;
	*/
	public boolean deleteDivisional(DivisionalForm divisionalForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postDivisional(DivisionalForm divisionalForm, MISSessionBean misAuditBean) throws MISException;

	boolean updateDivisional(DivisionalForm divisionalForm,
			MISSessionBean misAuditBean, List<String> statusList)
			throws MISException;

}
