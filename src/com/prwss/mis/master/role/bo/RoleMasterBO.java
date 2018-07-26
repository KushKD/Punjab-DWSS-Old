package com.prwss.mis.master.role.bo;

import java.util.List;

import com.prwss.mis.admin.dao.RoleBean;
import com.prwss.mis.admin.struts.RoleMasterForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

public interface RoleMasterBO {

	public List<RoleBean> findRole(RoleMasterForm roleForm, List<String> statusList) throws MISException;
	
	public boolean saveRole(RoleMasterForm roleForm, MISSessionBean misAuditBean) throws MISException;
	
	/*public boolean updateComponent(ComponentForm componentForm, MISSessionBean misAuditBean) throws MISException;*/
	
	public boolean deleteRole(RoleMasterForm roleForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postRole(RoleMasterForm roleForm, MISSessionBean misAuditBean) throws MISException;

	boolean updateRole(RoleMasterForm roleForm,
			MISSessionBean misSessionBean, List<String> statusList)
			throws MISException;
	
}
