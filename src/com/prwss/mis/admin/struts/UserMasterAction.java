package com.prwss.mis.admin.struts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.apache.struts.util.LabelValueBean;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.admin.UserAdminBean;
import com.prwss.mis.admin.dao.RoleBean;
import com.prwss.mis.admin.dao.RoleDao;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.LoginBO;
import com.prwss.mis.login.dao.LoginUserBean;
import com.prwss.mis.login.dao.LoginUserLocationBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class UserMasterAction extends DispatchAction {
	private MISSessionBean misSessionBean;
	private RoleDao roleDao;
	private Logger log = Logger.getLogger(UserMasterAction.class);
	private LoginBO loginBO;

	public void setLoginBO(LoginBO loginBO) {
		this.loginBO = loginBO;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		String mode = (String) request.getParameter("d__mode");
		try {
			List<String> statusList = new ArrayList<String>();
			if (mode != null
					&& MISConstants.D_MODE_ENQUIRE
							.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null
					&& MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null
					&& MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			if (mode != null
					&& MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			}
			UserMasterForm userMasterForm = (UserMasterForm) form;
			UserAdminBean userAdminBean = loginBO.findUserMaster(
					userMasterForm, statusList);
			if (!MisUtility.ifEmpty(userAdminBean.getLoginUserBeans())) {
				request.setAttribute("level2", "true");
				List<LoginUserBean> loginUserBeans = userAdminBean
						.getLoginUserBeans();
				request.setAttribute("loginUserBeans", loginUserBeans);
			} else {
				refreshUserMasterForm(userMasterForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record",
						"found or available for this mode for ",
						"the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}

	//
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		UserMasterForm userMasterForm = (UserMasterForm) form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = loginBO.deleteUserMaster(userMasterForm, misSessionBean);
			if (status) {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete",
						"User Master information", "User Id -->"
								+ userMasterForm.getUserId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete",
						"User Master information", "User Id -->"
								+ userMasterForm.getUserId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"User Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"User Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshUserMasterForm(userMasterForm);
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		UserMasterForm userMasterForm = (UserMasterForm) form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			status = loginBO.saveUserMaster(userMasterForm, misSessionBean);

			if (status) {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save",
						"User Master information", "User Id -->"
								+ userMasterForm.getUserId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save",
						"User Master information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage(
						"error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"User Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"User Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		UserMasterForm userMasterForm = (UserMasterForm) form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = loginBO.updateUserMaster(userMasterForm, misSessionBean);
			if (status) {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update",
						"User Master information", "User Id -->"
								+ userMasterForm.getUserId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update",
						"User Master information", "User Id -->"
								+ userMasterForm.getUserId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"User Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);

			log.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"User Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		try {
			refreshUserMasterForm(userMasterForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}

	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("save:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		UserMasterForm userMasterForm = (UserMasterForm) form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = loginBO.postUserMaster(userMasterForm, misSessionBean);
			if (status) {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post",
						"User Master information", "User Id -->"
								+ userMasterForm.getUserId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post",
						"User Master information", "User Id -->"
								+ userMasterForm.getUserId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"User Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save",
					"User Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}

		refreshUserMasterForm(userMasterForm);

		return mapping.findForward("display");
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		try {
			UserMasterForm userMasterForm = (UserMasterForm) form;
			Set<LabelValueBean> roles = getRoleIds();
			request.getSession().setAttribute("roles", roles);
			refreshUserMasterForm(userMasterForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
		}
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "userId@employeeId@roleId");
	}

	private Datagrid getUserLocationsDatagrid(
			List<LoginUserLocationBean> loginUserLocationBeans) {
		Datagrid userLocation = null;
		try {
			userLocation = Datagrid.getInstance();
			userLocation.setDataClass(LoginUserLocationBean.class);
			if (!MisUtility.ifEmpty(loginUserLocationBeans)) {
				List<LoginUserLocationBean> userLocationsBeans2 = new ArrayList<LoginUserLocationBean>(
						loginUserLocationBeans);
				userLocation.setData(userLocationsBeans2);
			} else {
				List<LoginUserLocationBean> userLocationsBeans2 = new ArrayList<LoginUserLocationBean>();
				userLocation.setData(userLocationsBeans2);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		return userLocation;
	}

	private Set<LabelValueBean> getRoleIds() {
		Set<LabelValueBean> roles = null;
		try {
			Set<RoleBean> roleIds = roleDao.getRoleIds();
			roles = new HashSet<LabelValueBean>();
			log.debug("RoleIds" + roleIds);
			for (RoleBean role : roleIds) {
				roles.add(new LabelValueBean(role.getRoleId()
						+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
						+ role.getRoleName(), role.getRoleId()));
			}

		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return roles;
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		String userId = request.getParameter("userId");
		UserMasterForm userMasterForm = (UserMasterForm) form;
		userMasterForm.setUserId(userId);
		UserAdminBean userAdminBean = loginBO.findUserMaster(userMasterForm,
				null);

		if (MisUtility.ifEmpty(userAdminBean)) {
			List<LoginUserBean> userMasterBeans = userAdminBean
					.getLoginUserBeans();
			for (LoginUserBean userMasterBean : userMasterBeans) {
				userMasterForm.setEmployeeId(userMasterBean.getEmployeeId());
				userMasterForm.setRoleId(userMasterBean.getRoleId());
				userMasterForm
						.setUserAddress1(userMasterBean.getUserAddress1());
				userMasterForm
						.setUserAddress2(userMasterBean.getUserAddress2());
				userMasterForm.setUserEmail(userMasterBean.getUserEmail());
				userMasterForm.setUserId(userMasterBean.getUserId());
				userMasterForm.setUserMobile(userMasterBean.getUserTelephone());
				userMasterForm
						.setUserPassword(userMasterBean.getUserPassword());
				userMasterForm.setUserName(userMasterBean.getUserName());
				userMasterForm.setGender(userMasterBean.getGender());
			}
			userMasterForm
					.setUserLocationGrid(getUserLocationsDatagrid(userAdminBean
							.getUserLocationBeans()));

		}

		return mapping.findForward("display");
	}

	private void refreshUserMasterForm(UserMasterForm userMasterForm) {
		userMasterForm.setEmployeeId(0);
		userMasterForm.setRoleId(null);
		userMasterForm.setUserAddress1(null);
		userMasterForm.setUserAddress2(null);
		userMasterForm.setUserEmail(null);
		userMasterForm.setUserId(null);
		userMasterForm.setUserMobile(null);
		userMasterForm.setUserPassword(null);
		userMasterForm.setUserName(null);
		userMasterForm.setUserLocationGrid(getUserLocationsDatagrid(null));
	}
}
