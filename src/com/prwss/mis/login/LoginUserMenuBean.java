package com.prwss.mis.login;

import java.io.Serializable;

public class LoginUserMenuBean implements Serializable,Comparable<LoginUserMenuBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -8532880951048309297L;

	private String menuId;
	
	private String toolBarButtionPermission;
	

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getToolBarButtionPermission() {
		return toolBarButtionPermission;
	}

	public void setToolBarButtionPermission(String toolBarButtionPermission) {
		this.toolBarButtionPermission = toolBarButtionPermission;
	}

	@Override
	public int compareTo(LoginUserMenuBean o) {
		return this.menuId.compareTo(o.getMenuId());
	}

	@Override
	public String toString() {
		return "\nLoginUserMenuBean [menuId=" + menuId + ", toolBarButtionPermission=" + toolBarButtionPermission + "]\n";
	}

}
