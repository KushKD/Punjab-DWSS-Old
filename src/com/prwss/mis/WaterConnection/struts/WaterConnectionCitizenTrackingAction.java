package com.prwss.mis.WaterConnection.struts;

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

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.location.dao.LocationDao;

public class WaterConnectionCitizenTrackingAction extends DispatchAction {

	private MISSessionBean misSessionBean;
	private Logger log = Logger.getLogger(WaterConnectionAction.class);
	private LocationDao locationDao;
	
	
	
	
	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("In Water Connection Citizen");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			//System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		setAttrib(request);
		System.out.println("In Water Connection Success Citizen ");
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request) {
		System.out.println("Mode 1111111111"+request.getParameter("d__mode"));
		request.setAttribute("Rpt", "ent");		
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky","locationId@advUploadId");
		request.setAttribute("d__auto", "advUploadId");

	}
	
	
	public ActionForward getStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String status = null;
		System.out.println("We are inside the Action  Citizen Tracking");
		WaterConnectionCitizenTrackingForm water= (WaterConnectionCitizenTrackingForm)form;
		System.out.println(water.toString());
		 
		try{
			
			//Send Data To Bean
			 status = locationDao.getApplicationStatus(water.getAppId());
			System.out.println(status);
			water.setAppId(null);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("required.fields",status);
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("WaterConnection.SaveError", e.getLocalizedMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		
		
		
		
		return mapping.findForward("display");
	}	
	
	
	
	
}
