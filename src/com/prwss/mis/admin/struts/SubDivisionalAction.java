package com.prwss.mis.admin.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import com.prwss.mis.admin.divisional.dao.DivisionalBean;
import com.prwss.mis.admin.divisional.dao.DivisionalDao;
import com.prwss.mis.admin.divisional.dao.SubDivisionalBO;
import com.prwss.mis.admin.divisional.dao.SubDivisionalBean;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.district.dao.DistrictBean;
import com.prwss.mis.masters.district.dao.DistrictDao;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;

public class SubDivisionalAction extends DispatchAction {
	
	private SubDivisionalBO subDivisionalBO;
	private MISSessionBean misAuditBean = new MISSessionBean();
	private DivisionalDao divisionalDao;
	private DistrictDao districtDao;

	public void setDistrictDao(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}

	public void setDivisionalDao(DivisionalDao divisionalDao) {
		this.divisionalDao = divisionalDao;
	}

	public void setSubDivisionalBO(SubDivisionalBO subDivisionalBO) {
		this.subDivisionalBO = subDivisionalBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	/*	this.setAttrib(request);
		request.setAttribute("level2", "true");
		SubDivisionalForm subdivisionalform = (SubDivisionalForm)form;
		System.out.println(subdivisionalform.getAddress1());
		
		return mapping.findForward("display");*/
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		try {
			this.setAttrib(request);
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
		request.setAttribute("level2", "true");
		System.out.println("find :" + request.getParameter("d__mode"));
		
		if (request.getSession().getAttribute("districts") != null) {
			getDistrictIds(request);
		}
		SubDivisionalForm subdivisionalform = (SubDivisionalForm)form;
		List<SubDivisionalBean> subdivisionalList = null;
		System.out.println(subdivisionalform);
		subdivisionalList = subDivisionalBO.findSubDivisional(subdivisionalform, statusList);
		System.out.println(subdivisionalList);
		
		if(!MisUtility.ifEmpty(subdivisionalList)){
			request.setAttribute("subdivisionalList", subdivisionalList);
			}else{
				
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		
	} catch (Exception e) {
		log.error(e);
		e.printStackTrace();
	}
	return mapping.findForward("display");
	}
	
	public ActionForward fetchDivisionalOffice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException{
		
		List<DivisionalBean> locationBeans  = null;
		List<String> locationType = new ArrayList<String>();
		locationType.add("DO");
		locationType.add("DPMC");
		StringBuffer buffer = new StringBuffer();
		try {
			if(MisUtility.ifEmpty(request.getParameter("districtId"))){
				System.out.println("--------------District"+request.getParameter("districtId"));
				locationBeans = divisionalDao.getDivisions(request.getParameter("districtId"), locationType);
				System.out.println("Size of Divisiona found "+locationBeans.size());
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (DivisionalBean bean : locationBeans) {
					buffer.append("<option value=\"").append(bean.getDivisionalId()).append("\">");
					buffer.append(bean.getDivisionalName()+" - ("+bean.getDivisionalId()+")");
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return null;
	}
	

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	/*	SubDivisionalForm subdivisionalform = (SubDivisionalForm)form;
		System.out.println(subdivisionalform.getPinId());
		// TODO Auto-generated method stub
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		return mapping.findForward("display");*/
		
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		try{
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
		SubDivisionalForm subdivisionalform = (SubDivisionalForm)form;
		boolean status = subDivisionalBO.deleteSubDivisional(subdivisionalform, misAuditBean);
		log.debug("SubDivisional delete" + status);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","SubDivisional Id ---->"+subdivisionalform.getSubdivisionId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","SubDivisional Id ---->"+subdivisionalform.getSubdivisionId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","SubDivisional Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","SubDivisional Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		if (request.getSession().getAttribute("districts") != null) {
			getDistrictIds(request);
		}
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*// TODO Auto-generated method stub
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		return mapping.findForward("display");*/
		
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		String mode = (String) request.getParameter("d__mode");
		List<String> statusList = new ArrayList<String>();
		if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {				
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {				
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}

		SubDivisionalForm subdivisionalform = (SubDivisionalForm)form;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
		boolean status = subDivisionalBO.updateSubDivisional(subdivisionalform, misAuditBean,statusList);
		log.debug("SubDivisional update" + status);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","SubDivisional Id ---->"+subdivisionalform.getSubdivisionId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","SubDivisional Id ---->"+subdivisionalform.getSubdivisionId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);				
			}
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","SubDivisional Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		refreshSubDivisionalForm(subdivisionalform);
		System.out.println("modify :" + request.getParameter("d__mode"));
		
		if (request.getSession().getAttribute("districts") != null) {
			getDistrictIds(request);
		}
		return mapping.findForward("display");
	}
	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("save:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		SubDivisionalForm subdivisionalform = (SubDivisionalForm)form;
		try{
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
		boolean status = subDivisionalBO.postSubDivisional(subdivisionalform, misAuditBean);
		
		log.debug(status);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","SubDivisional Id ---->"+subdivisionalform.getSubdivisionId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","SubDivisional Id ---->"+subdivisionalform.getSubdivisionId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","SubDivisional Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","SubDivisional Master information");
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
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				return mapping.findForward("login");
			}
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		SubDivisionalForm subdivisionalform = (SubDivisionalForm)form;
		boolean status = false;
		try {
			status = subDivisionalBO.saveSubDivisional(subdivisionalform,misAuditBean);
			 if(status){
					ActionMessages messages= new ActionMessages();
					ActionMessage message = new ActionMessage("success.save","SubDivisional Name ---->"+subdivisionalform.getSubdivisionName());
					messages.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, messages);
				}else{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.save","SubDivisional Name ---->"+subdivisionalform.getSubdivisionName());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}  catch (MISException e) {
				if (MISExceptionCodes.MIS001.equals(e.getCode())) {
					log.error(e.getLocalizedMessage(),e);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				} else {
					log.error(e.getLocalizedMessage(),e);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);				
				}
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","SubDivisional Master information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}	 
			
			if (request.getSession().getAttribute("districts") != null) {
				getDistrictIds(request);
			}
		
		log.debug(status);
		
		return mapping.findForward("display");
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		/*this.setAttrib(request);
		
		System.out.println("Unspecified........SubDivisional");
		return mapping.findForward("display");*/
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				return mapping.findForward("login");
			}
		this.setAttrib(request);
		Set<LabelValueBean> districts = getDistrictIds(request);
		request.getSession().setAttribute("districts", districts);
		SubDivisionalForm subdivisionalform = (SubDivisionalForm)form;
		refreshSubDivisionalForm(subdivisionalform);
		System.out.println("Unspecified........SubDivisional");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "districtId@divisionId@subdivisionId");
		request.setAttribute("d__auto", "subdivisionId");
	}
	
	private void refreshSubDivisionalForm(SubDivisionalForm subdivisionalForm) {
		subdivisionalForm.setSubdivisionId("");
		subdivisionalForm.setSubdivisionName("");
		subdivisionalForm.setSubdivisionalIds(null);
		subdivisionalForm.setDivisionId("");
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		String distristId = request.getParameter("subdivisionId");
		SubDivisionalForm subdivisionalform = (SubDivisionalForm)form;		
		subdivisionalform.setSubdivisionId(distristId);
		List<SubDivisionalBean> subdivisionalList = subDivisionalBO.findSubDivisional(subdivisionalform, null);

		if (subdivisionalList == null) {
			log.error("divisionBeanList is\t" + subdivisionalList);
		}

		for (SubDivisionalBean subdivisionalBean : subdivisionalList) {
			if (distristId.equalsIgnoreCase(subdivisionalBean.getSubdivisionId())) {

				request.setAttribute("subdivisionalBean", subdivisionalBean);
			}
			
		}

	
		if (request.getSession().getAttribute("districts") != null) {
			getDistrictIds(request);
		}
		return mapping.findForward("display");
	}
	
	private Set<LabelValueBean> getDistrictIds(HttpServletRequest request) {
		System.out.println("in getdistricts");
		Set<LabelValueBean> districts = null;
		Set<DistrictBean> districtIds = null;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);			
		try {
			districtIds = districtDao.getDistinctDistrictCodes(statusList);
			districts = new HashSet<LabelValueBean>();
			for (DistrictBean districtId : districtIds) {
				districts.add(new LabelValueBean(districtId.getDistrictId() + MISConstants.LABEL_VALUE_BEAN_SEPARATOR
						+ districtId.getDistrictName(), districtId.getDistrictId()));

			}
		} catch (Exception e) {
			log.error(e);
		}
		System.out.println("before setting");
		return districts;
	}

}
