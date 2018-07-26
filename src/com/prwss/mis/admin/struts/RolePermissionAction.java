package com.prwss.mis.admin.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.admin.dao.MenuBean;
import com.prwss.mis.admin.dao.MenuDao;
import com.prwss.mis.admin.dao.RoleBean;
import com.prwss.mis.admin.dao.RoleDao;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.LoginBO;
import com.prwss.mis.login.dao.LoginUserPermissionBean;
import com.prwss.mis.login.dao.LoginUserPermissionDao;
import com.prwss.mis.masters.item.dao.ItemBean;
import com.prwss.mis.masters.store.dao.StoreBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;
import fr.improve.struts.taglib.layout.menu.MenuBase;


public class RolePermissionAction extends DispatchAction {

	private MISSessionBean misSessionBean = null;	
	private RoleDao roleDao ;
	private MenuDao menuDao;
	private LoginUserPermissionDao loginUserPermissionDao; 
	private LoginBO loginBO;
	
	
	public void setLoginBO(LoginBO loginBO) {
		this.loginBO = loginBO;
	}
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setLoginUserPermissionDao(
			LoginUserPermissionDao loginUserPermissionDao) {
		this.loginUserPermissionDao = loginUserPermissionDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		}else{ 
			log.error("Your Session Timed Out");
			return mapping.findForward("login");
		}
		
		this.setAttrib(request);
		String mode = (String) request.getParameter("d__mode");
		List<String> statusList = new ArrayList<String>();
		if (mode != null && MISConstants.D_MODE_ENQUIRE.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
			
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		try {
			RolePermissionForm rolePermissionForm = (RolePermissionForm)form;
			String roleId=rolePermissionForm.getRole_id();
			List <LoginUserPermissionBean> loginUserBeans = loginUserPermissionDao.getUserSpecificMenus(rolePermissionForm.getRole_id());
			if(!MisUtility.ifEmpty(loginUserBeans)){
				request.setAttribute("level2", "true");
				Datagrid rolePermissionGrid = Datagrid.getInstance();			
				rolePermissionGrid.setDataClass(LoginUserPermissionBean.class);						
				rolePermissionGrid.setData(loginUserBeans);
				rolePermissionForm.setRolePermissionGrid(rolePermissionGrid);
				rolePermissionForm.setRole_id(roleId);
			}
			else{
				ActionErrors messages= new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode","");
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, messages);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("RolePermissionAction: ");
		return mapping.findForward("display");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		}else{ 
			log.error("Your Session Timed Out");
			return mapping.findForward("login");
		}
		
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println("Update");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		}else{ 
			log.error("Your Session Timed Out");
			return mapping.findForward("login");
		}
		this.setAttrib(request);		
		try{
			RolePermissionForm rolePermissionForm=(RolePermissionForm)form;
			List<LoginUserPermissionBean> loginUserPermissionBeans = populateRolePermissionBean(rolePermissionForm,misSessionBean);
		//	Collection <LoginUserPermissionBean> loginUserPermissionBeans =  rolePermissionForm.getRolePermissionGrid().getModifiedData();
		//	System.out.println("Role Permission: "+loginUserPermissionBeans);
			boolean status=loginUserPermissionDao.saveUserSpecificMenus(loginUserPermissionBeans);
			System.out.println("STATUS+++++"+status);
			if (status){
				System.out.println("if if if if if if if ");
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "User Permissions have been updated for", "Role  -->"+rolePermissionForm.getRole_id());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				System.out.println(message);
				saveMessages(request, errors);				
			} else {
				System.out.println("else else else else else");
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("error.update", "User Permissions have not been updated for ", "Role  -->"+rolePermissionForm.getRole_id());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			}
		} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","User Permissions Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e);
			e.printStackTrace();		
		}
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		}else{ 
			log.error("Your Session Timed Out");
			return mapping.findForward("login");
		}

		this.setAttrib(request);
		request.setAttribute("level1", "true");
		System.out.println("RolePermissionAction: Save");
		RolePermissionForm rolePermissionForm = (RolePermissionForm)form;
		
		Collection <LoginUserPermissionBean> loginUserBeans = loginUserPermissionDao.getUserSpecificMenus(rolePermissionForm.getRole_id());
		System.out.println("RolePermissionAction: "+loginUserBeans);
//		try {
//			Collection <LoginUserPermissionBean> loginUserBeans =  rolePermissionForm.getRolePermissionGrid().getModifiedData();
//			System.out.println("RolePermissionAction: in try "+loginUserBeans);
//			for (LoginUserPermissionBean loginUserPermissionBean : loginUserBeans) {
//				System.out.println(loginUserPermissionBean.getMenuId()+loginUserPermissionBean.isCanAdd());
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return mapping.findForward("display");
	}
	
	
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		return mapping.findForward("display");
	}

	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		this.setAttrib(request);
		return mapping.findForward("display");
		
	}
		
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		this.setAttrib(request);		
		try {
				RolePermissionForm rolePermissionForm = (RolePermissionForm)form;
				refreshRolePermisinForm(rolePermissionForm);
				Set<LabelValueBean> roles = getRoleIds();
				Set<LabelValueBean> menuids =getMenuIds();
				request.getSession().setAttribute("roles", roles);
				request.getSession().setAttribute("menuids", menuids);
				Set<MenuBean> menuIds = menuDao.getMenuIds();
				if(!MisUtility.ifEmpty(menuIds)){
					Datagrid rolePermissionGrid = Datagrid.getInstance();			
					rolePermissionGrid.setDataClass(LoginUserPermissionBean.class);				
					List<LoginUserPermissionBean> loginUserPermissionList = new ArrayList<LoginUserPermissionBean>();
					LoginUserPermissionBean loginUserPermissionBean = null;					
					for (MenuBean menuBean : menuIds) {
						loginUserPermissionBean = new LoginUserPermissionBean();
						loginUserPermissionBean.setMenuBean(menuBean);
						loginUserPermissionList.add(loginUserPermissionBean);
					}
					rolePermissionGrid.setData(loginUserPermissionList);
					rolePermissionForm.setRolePermissionGrid(rolePermissionGrid);
				}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
//			request.getSession().setAttribute("menus", menus);
			
			
		
		return mapping.findForward("display");
	}
	public ActionForward fetchMenuName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		//Set<ItemBean> itemBeans = null;
		System.out.println("menuid------"+request.getParameter("menuid"));
		List<MenuBean> menuBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			System.out.println("inside iffffff");
			if(MisUtility.ifEmpty(request.getParameter("menuid"))){
				 MenuBean menuBean = new MenuBean();
				 menuBean.setMenuId(request.getParameter("menuid"));
				 menuBeans = menuDao.getMenuNames(menuBean);
				 String menuName = menuBeans.get(0).getMenuName();
				 buffer.append(menuName);
				 System.out.println(buffer);
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return null;
	}

	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "roleId");
		//request.setAttribute("d__auto", "roleId");
	}
	private Set<LabelValueBean> getRoleIds(){
		Set<LabelValueBean> roles = null;
		try {
			Set<RoleBean> roleIds = roleDao.getRoleIds();
			roles = new HashSet<LabelValueBean>();
			log.debug("RoleIds"+roleIds);
			for(RoleBean role: roleIds){
				roles.add( new LabelValueBean(role.getRoleId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+role.getRoleName(),role.getRoleId()));	
			}
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
		}
		return roles;
	}
	private Set<LabelValueBean> getMenuIds(){
		Set<LabelValueBean> menuIds = null;
		try {
			Set<MenuBean> menuId = menuDao.getMenuIds();
			menuIds = new HashSet<LabelValueBean>();
			log.debug("MenuIds"+menuIds);
			for(MenuBean menu: menuId){
				menuIds.add( new LabelValueBean(menu.getMenuId(),menu.getMenuId()));	
			}
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
		}
		return menuIds;
	}
	@SuppressWarnings("unchecked")
	private List<LoginUserPermissionBean> populateRolePermissionBean(RolePermissionForm rolePermissionForm, MISSessionBean misSessionBean2){
		List<LoginUserPermissionBean> loginUserPermissionBeans = new ArrayList<LoginUserPermissionBean>();
	try{
		MISAuditBean misAuditBean = new MISAuditBean();
		misAuditBean.setStatus(MISConstants.MASTER_STATUS_APPROVED);
		misAuditBean.setEntBy(misSessionBean2.getEnteredBy());
		misAuditBean.setEntDate(misSessionBean2.getEnteredDate());
		misAuditBean.setAuthBy(0);
		misAuditBean.setFreezedBy(0);
		
		
		System.out.println("inside populateeeeeeee");
		Collection<LoginUserPermissionBean> addedLoginUserPermissionBean = rolePermissionForm.getRolePermissionGrid().getAddedData();
		if(!MisUtility.ifEmpty(addedLoginUserPermissionBean)){
			System.out.println("inside add");
			for(LoginUserPermissionBean loginUserPermissionBean : addedLoginUserPermissionBean ){
				loginUserPermissionBean.setRoleId(rolePermissionForm.getRole_id());
				loginUserPermissionBean.setMisAuditBean(misAuditBean);
				loginUserPermissionBeans.add(loginUserPermissionBean);
			}
		}
		Collection<LoginUserPermissionBean> modifiedLoginUserPermissionBean = rolePermissionForm.getRolePermissionGrid().getModifiedData();
		if(!MisUtility.ifEmpty(modifiedLoginUserPermissionBean)){
			System.out.println("inside modify");
			for(LoginUserPermissionBean loginUserPermissionBean : modifiedLoginUserPermissionBean ){
				loginUserPermissionBean.setRoleId(rolePermissionForm.getRole_id());
				loginUserPermissionBean.setMisAuditBean(misAuditBean);
				loginUserPermissionBeans.add(loginUserPermissionBean);
			}
		}
		
		Collection<LoginUserPermissionBean> deletedLoginUserPermissionBean = rolePermissionForm.getRolePermissionGrid().getDeletedData();
		if(!MisUtility.ifEmpty(deletedLoginUserPermissionBean)){
			System.out.println("inside delete");
			for(LoginUserPermissionBean loginUserPermissionBean : deletedLoginUserPermissionBean ){
				loginUserPermissionBean.setRoleId(rolePermissionForm.getRole_id());
				
				misAuditBean.setStatus(MISConstants.MASTER_STATUS_DELETED);
				loginUserPermissionBean.setMisAuditBean(misAuditBean);
				loginUserPermissionBeans.add(loginUserPermissionBean);
			}
		}
		}catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		return loginUserPermissionBeans;
	}
		private void refreshRolePermisinForm(RolePermissionForm rolePermissionForm){
			rolePermissionForm.setMenuId(null);
			rolePermissionForm.setMenuName(null);
			rolePermissionForm.setRole_id(null);
			
		}
}
