package com.prwss.mis.admin.struts;

import org.apache.struts.validator.ValidatorActionForm;
import org.apache.struts.validator.ValidatorForm;

public class RoleMasterForm extends ValidatorActionForm {

	private static final long serialVersionUID = -5590830558172787735L;
	private String roleId;
	private String roleName;
	private String roleDesc;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	
}
