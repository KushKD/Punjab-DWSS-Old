package com.prwss.mis.admin.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_role", schema=MISConstants.MIS_DB_SCHEMA_NAME)

public class RoleBean implements Serializable, Comparable<RoleBean> {

	private static final long serialVersionUID = 5596145896979436932L;

	@Id
	@Column(name="role_id", nullable=false)
	private String roleId;
	
	@Column(name="role_name")
	private String roleName;
	
	@Column(name="role_desc")
	private String roleDesc;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
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

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	
	@Override
	public String toString() {
		return "RoleBean [roleId=" + roleId + ", roleName=" + roleName + ", roleDesc="
				+ roleDesc + "]";
	}

	@Override
	public int compareTo(RoleBean o) {

		return o.getRoleId().compareTo(this.getRoleId());
	}
}
