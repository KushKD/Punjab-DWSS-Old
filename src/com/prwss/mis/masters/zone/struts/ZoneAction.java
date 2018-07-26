package com.prwss.mis.masters.zone.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.circle.CircleBO;
import com.prwss.mis.masters.circle.dao.CircleBean;
import com.prwss.mis.masters.circle.struts.CircleForm;
import com.prwss.mis.masters.zone.ZoneBO;
import com.prwss.mis.masters.zone.dao.ZoneBean;

public class ZoneAction extends DispatchAction {

	private ZoneBO zoneBO;
	private MISSessionBean misAuditBean = new MISSessionBean();
	private CircleBO circleBO;
		
	public void setCircleBO(CircleBO circleBO) {
		this.circleBO = circleBO;
	}

	public void setZoneBO(ZoneBO zoneBO) {
		this.zoneBO = zoneBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
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
		request.setAttribute("level2", "true");
		try {
			List<ZoneBean> zoneBeanList = null;
			ZoneForm zoneform = (ZoneForm) form;
			zoneBeanList = zoneBO.findZone(zoneform, statusList);
			log.debug("ZoneBean List\t" + zoneBeanList);
			
		
		if(!MisUtility.ifEmpty(zoneBeanList)){
			request.setAttribute("zoneBeanList", zoneBeanList);
			}else{
				//refershZoneForm(zoneform);
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
			HttpServletResponse response) throws Exception {

		this.setAttrib(request);
		request.setAttribute("level2", "true");
		ZoneForm zoneForm = null;
		boolean status = false;
		try{
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
			
				zoneForm = (ZoneForm) form;
				if(checkForgienKey(zoneForm)){
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save","Zone Master is used in some Other Entry");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);						
				}
				 status = zoneBO.deleteZone(zoneForm, misAuditBean);
				 if(status){
						ActionMessages messages= new ActionMessages();
						ActionMessage message = new ActionMessage("success.delete","Zone Id ---->"+zoneForm.getZoneId());
						messages.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveMessages(request, messages);
					}else{
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("error.delete","Zone Id ---->"+zoneForm.getZoneId());
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
					}
				}  catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Zone Master information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
					log.error(e);
					e.printStackTrace();
				} catch (Exception e) {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save","Zone Master information");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
					log.error(e);
					e.printStackTrace();
				}	 
				 
				log.debug("Deleted\t" + status);
		// TODO Auto-generated method stub
		return mapping.findForward("display");
	}

	private boolean checkForgienKey(ZoneForm zoneForm) {
		boolean flag = false;
		List<CircleBean> circleBeans = null;
		try{
			CircleForm circleForm = new CircleForm();
			circleForm.setZoneId(zoneForm.getZoneId());
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			circleBeans = circleBO.findCircle(circleForm, statusList);
			if(circleBeans.size()>0){
				flag = true;
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		this.setAttrib(request);
		
		request.setAttribute("level2", "true");
		ZoneForm zoneForm = (ZoneForm) form;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
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
			
			boolean status = zoneBO.updateZone(zoneForm, misAuditBean,statusList);
		log.debug("Update/Zone" + status);
				if (status){
					ActionMessages msg = new ActionMessages();
					ActionMessage message = new ActionMessage("success.update", "Zone", zoneForm.getZoneId());
					msg.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, msg);
		
				} else {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.update","Zone Master information","Zone Id -->"+zoneForm.getZoneId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			} catch (MISException e) {
				if (MISExceptionCodes.MIS001.equals(e.getCode())) {
					log.error(e.getLocalizedMessage(),e);
					System.out.println("---------check2----------");
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				} else {
					log.error(e.getLocalizedMessage(),e);
					System.out.println("---------check3----------");
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);				
				}
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Zone Master information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}
		refershZoneForm(zoneForm);
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		ZoneForm zoneform = (ZoneForm) form;
		String zoneId = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
			zoneId = zoneBO.saveZone(zoneform, misAuditBean);
			System.out.println(zoneId);
			if (MisUtility.ifEmpty(zoneId)){
				ActionMessages msg = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Zone ", zoneform.getZoneName());
				msg.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, msg);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Zone", zoneform.getZoneName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				System.out.println("---------check2----------");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {
				log.error(e.getLocalizedMessage(),e);
				System.out.println("---------check3----------");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);				
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.update",zoneform.getZoneName());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.debug("Error in updating tender \t" + e);
		}
		refershZoneForm(zoneform);
		return mapping.findForward("display");
	}

	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("save:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

		ZoneForm zoneForm = (ZoneForm) form;

		boolean status = zoneBO.postZone(zoneForm, misAuditBean);
		log.debug(status);
					if (status){
						ActionMessages msg = new ActionMessages();
						ActionMessage message = new ActionMessage("success.post", "Zone", zoneForm.getZoneId());
						msg.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveMessages(request, msg);
			
					} else {
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("error.post","Zone Master information","Zone Id -->"+zoneForm.getZoneId());
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
					}
				} catch (MISException e) {
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("fatal.error.save","Zone Master information");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
					
					log.error(e.getLocalizedMessage(),e);
					e.printStackTrace();
				} catch (Exception e) {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save","Zone Master information");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
					log.error(e);
					e.printStackTrace();
				}
	
		return mapping.findForward("display");
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		String zoneId = request.getParameter("zoneId");
		System.out.println("Populate:" + zoneId);
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		ZoneForm zoneForm = (ZoneForm) form;
		zoneForm.setZoneId(zoneId);
		List<ZoneBean> zoneBeanList = zoneBO.findZone(zoneForm, null);
		if (zoneBeanList == null) {
			log.error("zoneBeanList is\t" + zoneBeanList);
		}

		for (ZoneBean zoneBean : zoneBeanList) {
			if (zoneId.equalsIgnoreCase(zoneBean.getZoneId())) {

				request.setAttribute("zoneBean", zoneBean);
			}
		}

		return mapping.findForward("display");
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		System.out.println("Unspecified........Zone");

		refershZoneForm((ZoneForm) form);
		
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "zoneId");
		request.setAttribute("d__auto","zoneId");
	}

	private void refershZoneForm(ZoneForm zoneForm) {
		zoneForm.setZoneId("");
		zoneForm.setZoneName("");
		zoneForm.setZoneIds(null);

	};


}
