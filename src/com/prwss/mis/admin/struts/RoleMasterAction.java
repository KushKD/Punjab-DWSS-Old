package com.prwss.mis.admin.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.prwss.mis.admin.UserAdminBean;
import com.prwss.mis.admin.dao.RoleBean;
import com.prwss.mis.admin.dao.RoleDao;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.exception.MISSessionTimeOutException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserBean;
import com.prwss.mis.master.role.bo.RoleMasterBO;
import com.prwss.mis.masters.component.ComponentBO;
import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.component.struts.ComponentForm;

public class RoleMasterAction extends DispatchAction {
	private MISSessionBean misSessionBean;
	private RoleMasterBO roleMasterBO ;
	private Logger log = Logger.getLogger(RoleMasterAction.class);
	

	

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException, MISSessionTimeOutException {
		System.out.println("find:" + request.getParameter("d__mode"));
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
		if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}
		this.setAttrib(request);
		
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
		{misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
		else
		{
			return mapping.findForward("login");
		}
			
		try {
			List<RoleBean> roleList = null;
			RoleMasterForm roleForm = (RoleMasterForm) form;
			System.out.println("----------roleAction"+roleForm.getRoleId());
			roleList = roleMasterBO.findRole(roleForm, statusList);
			log.debug("RoleBean List\t" + roleList);
			if(!MisUtility.ifEmpty(roleList)){
//			if (mode != null && MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
//				request.setAttribute("level2", "true");
//			}
//			if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
//				request.setAttribute("level2", "true");
//			}
				request.setAttribute("roleBeans", roleList);
			}else{
				refreshRoleForm(roleForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("delete:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		RoleMasterForm roleForm = null;
		boolean status = false;
		try {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				return mapping.findForward("login");
			}
		
		
			roleForm = (RoleMasterForm) form;
			status = roleMasterBO.deleteRole(roleForm, misSessionBean);
			if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Role Name ---->"+roleForm.getRoleName());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Role Name ---->"+roleForm.getRoleName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","Role Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Role Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		log.debug("Deleted\t" + status);

		refreshRoleForm(roleForm);
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("save:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		request.setAttribute("level2", "true");

		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				return mapping.findForward("login");
			}
		
		RoleMasterForm roleForm = (RoleMasterForm) form;

		boolean status = false;
		try {
			status = roleMasterBO.saveRole(roleForm, misSessionBean);
			if (status) {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Role", roleForm.getRoleId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			}
		} catch (MISException e) {
				System.out.println("MISExceptionCodes.MIS001:"+MISExceptionCodes.MIS001);
				System.out.println("e.getCode():"+e.getCode());
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {
				throw e;
			}

		}
		
		log.debug("Role Save" + status);
		refreshRoleForm(roleForm);
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("update:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		RoleMasterForm roleForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
			String mode = (String) request.getParameter("d__mode");
			List<String> statusList = new ArrayList<String>();
			if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {				
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {				
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			}
			roleForm = (RoleMasterForm) form;
			boolean status = roleMasterBO.updateRole(roleForm, misSessionBean,statusList);
			log.debug(status);
			if (status){
				ActionMessages msg = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "Role Name ", roleForm.getRoleName());
				msg.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, msg);
	
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Role Master information","Zone Id -->"+roleForm.getRoleId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Role Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		
		log.error(e.getLocalizedMessage(),e);
		e.printStackTrace();
	} catch (Exception e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","Role Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
		log.error(e);
		e.printStackTrace();
	}	
		refreshRoleForm(roleForm);
		return mapping.findForward("display");
	}

	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("save:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		RoleMasterForm roleForm = null;
		try{
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				return mapping.findForward("login");
			}
		 roleForm = (RoleMasterForm) form;
		
		boolean status = roleMasterBO.postRole(roleForm, misSessionBean);
		log.debug(status);
				if(status){
					ActionMessages errors = new ActionMessages();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Role.post", roleForm.getRoleName()));
					saveMessages(request, errors);
				}
			} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Role Master information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Role Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	
		
		refreshRoleForm(roleForm);
		return mapping.findForward("display");
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		String roleId = request.getParameter("roleId");
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		RoleMasterForm roleForm = (RoleMasterForm) form;
		roleForm.setRoleId(roleId);
		List<RoleBean> roleList = roleMasterBO.findRole(roleForm, null);
		if (roleList == null) {
			log.error("roleList is\t" + roleList);
		}

		for (RoleBean roleBean : roleList) {
			if (roleId.equalsIgnoreCase(roleBean.getRoleId())) {
				System.out.println("roleBean" + roleBean);
				request.setAttribute("roleBean", roleBean);
			}
		}

		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "roleId");

	}


	/**
	 * @param componentBo
	 *            the componentBo to set
	 */
	

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		// System.out.println("unspecified:"+request.getParameter("d__mode"));
		this.setAttrib(request);
		RoleMasterForm roleForm = (RoleMasterForm) form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				log.error("Login session timed out");
				return mapping.findForward("login");
			}
		refreshRoleForm(roleForm);
		return mapping.findForward("display");
	}

	private void refreshRoleForm(RoleMasterForm roleForm) {
		//componentForm=null;
		roleForm.setRoleDesc(null);
		roleForm.setRoleName(null);
	/*	roleForm.setRoleIds(null);*/
		roleForm.setRoleId(null);
	}

	public void setRoleMasterBO(RoleMasterBO roleMasterBO) {
		this.roleMasterBO = roleMasterBO;
	}



}
