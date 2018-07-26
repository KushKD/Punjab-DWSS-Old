package com.prwss.mis.hr.changeOfficer.struts;

import java.io.IOException;
import java.io.PrintWriter;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

 
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
 
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.EmployeeBO;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;
import com.prwss.mis.masters.employee.dao.EmployeePromotionHistoryBean;
 
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;

public class ChangeOfficerLocationAction extends DispatchAction{
	
	private LocationDao locationDao;
	private EmployeeDao employeeDao;
	private MISSessionBean misSessionBean;
	private EmployeeBO employeeBO;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		ChangeOfficerLocationForm changeOfficerLocationForm = (ChangeOfficerLocationForm)form;
		refreshChangeOfficerLocationForm(changeOfficerLocationForm);
		try {
			Set<LabelValueBean> locationIds = null;
			locationIds = getLocations();
			request.getSession().setAttribute("locationIds", locationIds);
			System.out.println("Unspecified------------------------------------");
		
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return mapping.findForward("display");
	}
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "@locationId@oldReportingOfficerId@changeLocation@reportingOfficer@location@employeeIds");
	}
	 
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		 System.out.println("In update");
		 
		try {
			int rows = 0;
			if (request.getSession().getAttribute("misSessionBean") != null) {
				
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				
			} else {
				return mapping.findForward("login");
			}
			StringBuffer strngMessage = new StringBuffer();
			ChangeOfficerLocationForm changeOfficerLocationForm = (ChangeOfficerLocationForm)form;
			if(changeOfficerLocationForm.getChangeLocationOfficer().equals("reportingOfficer")){
				if(!MisUtility.ifEmpty(changeOfficerLocationForm.getNewReportingOfficerId())){
					strngMessage.append("Please Select New Reporting Officer");
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}
				String reportingOfficerLocaion = changeOfficerLocationForm.getNewLocationId();
				long reportingOfficer = changeOfficerLocationForm.getNewReportingOfficerId();
				long empIds[]=changeOfficerLocationForm.getSelectedIds();			 
				rows = employeeDao.updateEmployeeReportingOfficer(empIds, reportingOfficer, reportingOfficerLocaion);
				if(rows>0){
					ActionMessages errors = new ActionMessages();
					ActionMessage message = new ActionMessage("success.update.save", "Reporting Officer is modified for "+rows+" employee new reporting officer is -->"+changeOfficerLocationForm.getNewReportingOfficerId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, errors);
					refreshChangeOfficerLocationForm(changeOfficerLocationForm);
				}
				else{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save.modified", "Reporting Officer is not modified");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}else{
				if(!MisUtility.ifEmpty(changeOfficerLocationForm.getChangeLocationId())){
					strngMessage.append("Please Select Change Location");
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}
				int employeeId = (int)changeOfficerLocationForm.getEmployeeIds();
				String changeLocation = changeOfficerLocationForm.getChangeLocationId();
				String previousLocation = changeOfficerLocationForm.getLocation();
				int status = employeeDao.updateEmployeeLocation(employeeId,previousLocation,changeLocation);
				if(status>0){
					ActionMessages errors = new ActionMessages();
					ActionMessage message = new ActionMessage("success.update.save", "Location is modified for employeeId -->"+employeeId+"  previous Location was "+ previousLocation +" new Location is -->"+changeLocation);
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, errors);
					refreshChangeOfficerLocationForm(changeOfficerLocationForm);
				}
				else{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save.modified", "Location is not modified");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}
			
		}catch(MISException e){
			 if(MISExceptionCodes.MIS012.equals(e.getCode())){
					log.error(e.getLocalizedMessage(),e);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("required.fields", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
					
				}else{
					log.error(e.getLocalizedMessage(),e);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save","Updatation of Reporting Officer ");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}catch(Exception e){
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updatation of Reporting Officer ");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("In find");
		this.setAttrib(request);
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
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
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}
		StringBuffer strngMessage = new StringBuffer();
		this.setAttrib(request);
		try{
			List<EmployeeBean> employeeBeans = null;
		ChangeOfficerLocationForm changeOfficerLocationForm = (ChangeOfficerLocationForm)form;
		if(changeOfficerLocationForm.getChangeLocationOfficer().equals("reportingOfficer")){
		if(!MisUtility.ifEmpty(changeOfficerLocationForm.getLocationId())||!MisUtility.ifEmpty(changeOfficerLocationForm.getOldReportingOfficerId())){
			if(!MisUtility.ifEmpty(changeOfficerLocationForm.getLocationId())){
				strngMessage.append("Please Select Location");
			}
			if(!MisUtility.ifEmpty(changeOfficerLocationForm.getOldReportingOfficerId())){
				strngMessage.append("<br> Please Select Old reporting Officer");
			}
			throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
		}
		
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.setReportingOfficerId(changeOfficerLocationForm.getOldReportingOfficerId());
		employeeBean.setLocationId(changeOfficerLocationForm.getLocationId());
		employeeBeans = employeeDao.getEmployeeByOldOfficer(employeeBean, statusList);
		if(!MisUtility.ifEmpty(employeeBeans)){
			request.setAttribute("level2", "true");
			System.out.println("inside");
			 request.setAttribute("newReportingOfficerId","");
			 request.setAttribute("oldReportingOfficerId",employeeBeans.get(0).getReportingOfficerId());
			//System.out.println("listtttttttttt-----"+employeeBeans);
			request.setAttribute("employeeCodeList",employeeBeans);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("find.list");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
 
		}else{
		//	refreshEmployeeForm(employeeForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		}else{
			if(!MisUtility.ifEmpty(changeOfficerLocationForm.getLocation())||!MisUtility.ifEmpty(changeOfficerLocationForm.getEmployeeIds())){
				if(!MisUtility.ifEmpty(changeOfficerLocationForm.getLocation())){
					strngMessage.append("Please Select Location");
				}
				if(!MisUtility.ifEmpty(changeOfficerLocationForm.getEmployeeIds())){
					strngMessage.append("<br> Please Select Employee");
				}
				throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
			}
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmployeeId(changeOfficerLocationForm.getEmployeeIds());
			//employeeBean.setLocationId(changeOfficerLocationForm.getLocation());
			employeeBeans = employeeDao.getEmployeeByOldOfficer(employeeBean, statusList);
			if(!MisUtility.ifEmpty(employeeBeans)){
				request.setAttribute("level2", "true");
				System.out.println("inside");
				// request.setAttribute("newReportingOfficerId","");
				 request.setAttribute("employeeIds",employeeBeans.get(0).getEmployeeId());
				//System.out.println("listtttttttttt-----"+employeeBeans);
				request.setAttribute("employeeCodeList",employeeBeans);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("find.list");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
	 
			}else{
			//	refreshEmployeeForm(employeeForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}
		
	}catch(MISException e){
		 if(MISExceptionCodes.MIS012.equals(e.getCode())){
			log.error(e.getLocalizedMessage(),e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("required.fields", e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}else{
			log.error(e.getLocalizedMessage(),e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
	}
		catch (Exception e) {
	
		e.printStackTrace();
		log.error(e.getLocalizedMessage(),e);
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
	}
		return mapping.findForward("display");
	}
	 
	private Set<LabelValueBean> getLocations(){
		Set<LabelValueBean> locationIds = null;
		Set<LocationBean> locationBeans = null;
		try {
			List<String> locationTypeList = new ArrayList<String>();
			locationTypeList.add("DPMC");
			locationTypeList.add("DO");
			locationTypeList.add("SPMC");
			locationTypeList.add("CIRCLE");
			locationTypeList.add("ZONE");
			locationBeans = locationDao.getLocationIdOnTypeList(locationTypeList);
			locationIds = new HashSet<LabelValueBean>();
			for (LocationBean locationBean : locationBeans) {
				locationIds.add(new LabelValueBean(locationBean.getLocationName()+" - ("+locationBean.getLocationId()+")",locationBean.getLocationId()));
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return locationIds;
	}
	public ActionForward getEmployeeByDeployedLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<EmployeeBean> employeeBeans= new TreeSet<EmployeeBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
				EmployeeBean employeeBean=new EmployeeBean();
				employeeBean.setLocationId(request.getParameter("locationId"));
				employeeBeans = employeeDao.getEmployeeByDeployedLocation(employeeBean,statusList);
				buffer.append("<option value='0'>Please Select </option>");
				
				for (EmployeeBean employeeBean2: employeeBeans) {
					String designation=null;
					buffer.append("<option value=\"").append(employeeBean2.getEmployeeId()).append("\">");
					Set<EmployeePromotionHistoryBean> employeePromotionHistoryBeans= employeeBean2.getEmployeePromotionHistoryBeans();
					if(!MisUtility.ifEmpty(employeePromotionHistoryBeans)){
						List<EmployeePromotionHistoryBean> employeePromotionHistoryBeans2=new ArrayList<EmployeePromotionHistoryBean>();
						
						for (EmployeePromotionHistoryBean employeePromotionHistoryBean : employeePromotionHistoryBeans) {
							employeePromotionHistoryBeans2.add(employeePromotionHistoryBean);
							
							/*	designation=employeePromotionHistoryBean.getPromotedDesignation();							
							break;*/
						}
						Collections.sort(employeePromotionHistoryBeans2);
						designation=employeePromotionHistoryBeans2.get(employeePromotionHistoryBeans2.size()-1).getPromotedDesignation();
					}else{
						designation=employeeBean2.getDesignationId();
					}					
					buffer.append(employeeBean2.getEmployeeName()+" - "+designation+" - ("+employeeBean2.getEmployeeId()+")");
					buffer.append("</option>");
				}		
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
	
	public ActionForward getEmployeeByNewLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<EmployeeBean> employeeBeans= new TreeSet<EmployeeBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			if(MisUtility.ifEmpty(request.getParameter("newLocationId"))){
				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
				EmployeeBean employeeBean=new EmployeeBean();
				employeeBean.setLocationId(request.getParameter("newLocationId"));
				employeeBeans = employeeDao.getEmployeeByDeployedLocation(employeeBean,statusList);
				buffer.append("<option value='0'>Please Select </option>");
				
				for (EmployeeBean employeeBean2: employeeBeans) {
					String designation=null;
					buffer.append("<option value=\"").append(employeeBean2.getEmployeeId()).append("\">");
					Set<EmployeePromotionHistoryBean> employeePromotionHistoryBeans= employeeBean2.getEmployeePromotionHistoryBeans();
					if(!MisUtility.ifEmpty(employeePromotionHistoryBeans)){
						List<EmployeePromotionHistoryBean> employeePromotionHistoryBeans2=new ArrayList<EmployeePromotionHistoryBean>();
						
						for (EmployeePromotionHistoryBean employeePromotionHistoryBean : employeePromotionHistoryBeans) {
							employeePromotionHistoryBeans2.add(employeePromotionHistoryBean);
							
							/*	designation=employeePromotionHistoryBean.getPromotedDesignation();							
							break;*/
						}
						Collections.sort(employeePromotionHistoryBeans2);
						designation=employeePromotionHistoryBeans2.get(employeePromotionHistoryBeans2.size()-1).getPromotedDesignation();
					}else{
						designation=employeeBean2.getDesignationId();
					}					
					buffer.append(employeeBean2.getEmployeeName()+" - "+designation+" - ("+employeeBean2.getEmployeeId()+")");
					buffer.append("</option>");
				}		
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
	
	public ActionForward getEmployeeByLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<EmployeeBean> employeeBeans= new TreeSet<EmployeeBean>();
		StringBuffer buffer = new StringBuffer();
		try {
			if(MisUtility.ifEmpty(request.getParameter("location"))){
				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
				EmployeeBean employeeBean=new EmployeeBean();
				employeeBean.setLocationId(request.getParameter("location"));
				employeeBeans = employeeDao.getEmployeeByDeployedLocation(employeeBean,statusList);
				buffer.append("<option value='0'>Please Select </option>");
				
				for (EmployeeBean employeeBean2: employeeBeans) {
					String designation=null;
					buffer.append("<option value=\"").append(employeeBean2.getEmployeeId()).append("\">");
					Set<EmployeePromotionHistoryBean> employeePromotionHistoryBeans= employeeBean2.getEmployeePromotionHistoryBeans();
					if(!MisUtility.ifEmpty(employeePromotionHistoryBeans)){
						List<EmployeePromotionHistoryBean> employeePromotionHistoryBeans2=new ArrayList<EmployeePromotionHistoryBean>();
						
						for (EmployeePromotionHistoryBean employeePromotionHistoryBean : employeePromotionHistoryBeans) {
							employeePromotionHistoryBeans2.add(employeePromotionHistoryBean);
							
							/*	designation=employeePromotionHistoryBean.getPromotedDesignation();							
							break;*/
						}
						Collections.sort(employeePromotionHistoryBeans2);
						designation=employeePromotionHistoryBeans2.get(employeePromotionHistoryBeans2.size()-1).getPromotedDesignation();
					}else{
						designation=employeeBean2.getDesignationId();
					}					
					buffer.append(employeeBean2.getEmployeeName()+" - "+designation+" - ("+employeeBean2.getEmployeeId()+")");
					buffer.append("</option>");
				}		
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
	private void refreshChangeOfficerLocationForm(ChangeOfficerLocationForm changeOfficerLocationForm){
		changeOfficerLocationForm.setLocationId(null);
		changeOfficerLocationForm.setLocation(null);
		changeOfficerLocationForm.setNewLocationId(null);
		changeOfficerLocationForm.setChangeLocationId(null);
		changeOfficerLocationForm.setEmployeeIds(0);
		changeOfficerLocationForm.setOldReportingOfficerId(0);
		changeOfficerLocationForm.setNewReportingOfficerId(0);
		
	}
	
}
