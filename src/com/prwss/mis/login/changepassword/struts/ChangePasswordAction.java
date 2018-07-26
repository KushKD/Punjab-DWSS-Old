package com.prwss.mis.login.changepassword.struts;

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
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserBean;
import com.prwss.mis.login.dao.LoginUserDao;

public class ChangePasswordAction extends DispatchAction {
	private MISSessionBean misSessionBean;
	private LoginUserDao loginUserDao;
	private Logger log = Logger.getLogger(ChangePasswordAction.class);
	

	public void setLoginUserDao(LoginUserDao loginUserDao) {
		this.loginUserDao = loginUserDao;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		System.out.println("unspecified");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		
		return mapping.findForward("display");
	}
	
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		boolean status = false;
		ChangePasswordForm changePasswordForm = (ChangePasswordForm)form;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		System.out.println(misSessionBean.getUserId());
		try {
			LoginUserBean loginUserBean = new LoginUserBean();
			loginUserBean.setUserId(misSessionBean.getUserId());
			LoginUserBean loginUserBean2 = loginUserDao.findLoginUser(loginUserBean, statusList).get(0);
			
			if(MisUtility.ifEmpty(loginUserBean2)){
				if(loginUserBean2.getUserPassword().equals(changePasswordForm.getOldPassword())){
					if(changePasswordForm.getNewPassword().equals(changePasswordForm.getConfirmPassword())){
					loginUserBean2.setUserPassword(changePasswordForm.getNewPassword());
					status = loginUserDao.updateLoginBean(loginUserBean2);
					if(status){
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("change.password.succ");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
						refresh(changePasswordForm);
					}else{
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("change.password.fail","Operation failed please contact admin.");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
					}
				}else{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("change.password.mismatch");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
					
				}else{
					throw new MISException(MISExceptionCodes.MIS001,"Process can not be completed. Your old password is wrong. Please Check !");
				}
				
			}else{
				throw new MISException(MISExceptionCodes.MIS002,"Unknown exception has occured. Please contact admin.");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
//				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				System.out.println("Inside MIS001");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("change.password.fail",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Saving failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS002.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				System.out.println("Inside MIS002");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("change.password.fail",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}
		return mapping.findForward("display");
	}
	
	private void refresh(ChangePasswordForm changePasswordForm){
		changePasswordForm.setConfirmPassword(null);
		changePasswordForm.setNewPassword(null);
		changePasswordForm.setOldPassword(null);
	}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode","d__mode");
//		request.setAttribute("d__ky", "villageId@districtId");	
//		request.setAttribute("d__auto", "villageId");	
	}

}
