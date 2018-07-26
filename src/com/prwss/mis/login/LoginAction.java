/**
 * 
 */
package com.prwss.mis.login;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserBean;
import com.prwss.mis.masters.location.dao.LocationBean;

/**
 * @author vgadiraju
 *
 */
public class LoginAction extends Action {
	
	Logger log = Logger.getLogger(LoginAction.class);
	
	private LoginBO loginBO;
	

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		try {
			LoginForm loginForm = (LoginForm)form;
			LoginUserBean loginUserBean = null;
			LoginUserBean loginUserBean2 = null;
			String userName = loginForm.getUserName();
			String password = loginForm.getPassword();
			if(MisUtility.ifEmpty(request.getSession().getAttribute("misSessionBean"))){
				request.getSession().invalidate();
			}
			if( userName!= null && !userName.equals("") && password != null && !password.equals("")){
				log.debug(userName+"\t"+password);
//			loginBO = (LoginBO)WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()).getBean("loginBo");
				loginUserBean = loginBO.verifyLogin(userName, password);
			}
//			System.out.println("1 After throw MIS Exception ");
			if(loginUserBean == null || loginUserBean.getUserName() == null || "".equals(loginUserBean.getUserName().trim())){
//				System.out.println("2 After throw MIS Exception ");
				ActionMessages actionErrors = new ActionMessages();
				actionErrors.add("login.failed", new ActionMessage("Login Failed.. Please try again"));
				return mapping.findForward("fail");
			}
			Timestamp loginTimestamp = new Timestamp(System.currentTimeMillis());
			MISSessionBean misSessionBean = new MISSessionBean();
			misSessionBean.setEnteredBy(loginUserBean.getEmployeeId());		
			misSessionBean.setUserId(userName.trim());
			misSessionBean.setUserId1(loginUserBean.getUserId());
			
		   // misSessionBean.setLocationId(loginBO.getUserLocations(userName));
			misSessionBean.setEnteredDate(loginTimestamp);
			try {
				loginUserBean2=	loginBO.findUserLogin(userName).get(0);
				loginUserBean2.setLastLoginOn(loginTimestamp);
				loginBO.saveLoginUser(loginUserBean2);
			} catch (Exception e) {
	//			System.out.println("3 After throw MIS Exception ");
				log.error(e);
				e.printStackTrace();
				throw new MISException(e);
			}
			
			misSessionBean.setEmployeeName(loginUserBean.getUserName());
			request.getSession().setAttribute("misSessionBean", misSessionBean);
			Set<LabelValueBean> locations = null;
			Set<LabelValueBean> villages = null;
			Set<LocationBean> locationBeans = null;
			locationBeans = loginBO.getUserLocations(userName);
			locations = new TreeSet<LabelValueBean>();
			villages=new TreeSet<LabelValueBean>();
			for (LocationBean locationBean : locationBeans){
				locations.add(new LabelValueBean(locationBean.getLocationName()+" - ("+locationBean.getLocationId()+")",locationBean.getLocationId()));
			}
						 
		 request.getSession().setAttribute("userLocations", locations);					 
		 villages.add(new LabelValueBean("select villages","villages"));
		 request.getSession().setAttribute("villages", villages);	
		 Map<String,String> loginUserMenuBeans =  loginBO.getUserMenuPermission(loginUserBean.getRoleId());
		 if (!MisUtility.ifEmpty(loginUserMenuBeans)){
			 request.getSession().setAttribute("menuPermission", loginUserMenuBeans);
		 }
		} catch (MISException e) {
			System.out.println(" With In MIS Exception "+e.getMessage());
			log.info(e.getMessage());
			log.error(e.getLocalizedMessage(),e);
		/*	ActionMessages actionErrors = new ActionMessages();
			actionErrors.add("login.failed", new ActionMessage(e.getMessage()));*/
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("login.failed",e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			System.out.println("*****************************************************************");
			return mapping.findForward("fail");
			
		} catch (Exception e) {
			System.out.println("Exception "+e.getMessage());
			log.error(e);
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("login.failed");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			return mapping.findForward("fail");
		}
		return mapping.findForward("success");
	}

	/**
	 * @param loginBO the loginBO to set
	 */
	public void setLoginBO(LoginBO loginBO) {
		this.loginBO = loginBO;
	}
	
	
	
}

