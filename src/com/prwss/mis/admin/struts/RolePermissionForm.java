package com.prwss.mis.admin.struts;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class RolePermissionForm extends ValidatorForm {

	private static final long serialVersionUID = 5612938426832433038L;
	
	private String role_id;
//	private String menu_id;
	private String menuId;
	private String menuName;
//	private String menu_name;
//	private boolean can_inquire;
//	private boolean can_add;
//	private boolean can_modify;
//	private boolean can_delete;
//	private boolean can_post;
//	private boolean can_view;
//	private boolean can_print;
//	private boolean can_email;
//	private boolean can_export;
//	private boolean can_graph;
//	private boolean can_file;
	private Datagrid rolePermissionGrid;
	
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
//	public String getMenu_id() {
//		return menu_id;
//	}
//	public void setMenu_id(String menu_id) {
//		this.menu_id = menu_id;
//	}
//	public boolean isCan_inquire() {
//		return can_inquire;
//	}
//	public void setCan_inquire(boolean can_inquire) {
//		this.can_inquire = can_inquire;
//	}
//	public boolean isCan_add() {
//		return can_add;
//	}
//	public void setCan_add(boolean can_add) {
//		this.can_add = can_add;
//	}
//	public boolean isCan_modify() {
//		return can_modify;
//	}
//	public void setCan_modify(boolean can_modify) {
//		this.can_modify = can_modify;
//	}
//	public boolean isCan_delete() {
//		return can_delete;
//	}
//	public void setCan_delete(boolean can_delete) {
//		this.can_delete = can_delete;
//	}
//	public boolean isCan_post() {
//		return can_post;
//	}
//	public void setCan_post(boolean can_post) {
//		this.can_post = can_post;
//	}
//	public boolean isCan_view() {
//		return can_view;
//	}
//	public void setCan_view(boolean can_view) {
//		this.can_view = can_view;
//	}
//	public boolean isCan_print() {
//		return can_print;
//	}
//	public void setCan_print(boolean can_print) {
//		this.can_print = can_print;
//	}
//	public boolean isCan_email() {
//		return can_email;
//	}
//	public void setCan_email(boolean can_email) {
//		this.can_email = can_email;
//	}
//	public boolean isCan_export() {
//		return can_export;
//	}
//	public void setCan_export(boolean can_export) {
//		this.can_export = can_export;
//	}
//	public boolean isCan_graph() {
//		return can_graph;
//	}
//	public void setCan_graph(boolean can_graph) {
//		this.can_graph = can_graph;
//	}
//	public boolean isCan_file() {
//		return can_file;
//	}
//	public void setCan_file(boolean can_file) {
//		this.can_file = can_file;
//	}
	public void setRolePermissionGrid(Datagrid rolePermissionGrid) {
		this.rolePermissionGrid = rolePermissionGrid;
	}
	public Datagrid getRolePermissionGrid() {
		return rolePermissionGrid;
	}
//	public void setMenu_name(String menu_name) {
//		this.menu_name = menu_name;
//	}
//	public String getMenu_name() {
//		return menu_name;
////	}
//	public void setMenuid(String menuid) {
//		this.menuid = menuid;
//	}
//	public String getMenuid() {
//		return menuid;
//	}
//	public void setMenuname(String menuname) {
//		this.menuname = menuname;
//	}
//	public String getMenuname() {
//		return menuname;
//	}
//	 
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuName() {
		return menuName;
	}
	
	
}
