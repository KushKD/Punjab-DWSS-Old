package com.prwss.mis.masters.village.struts;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.constituency.dao.ConstituencyBean;
import com.prwss.mis.masters.constituency.dao.ConstituencyDao;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.village.VillageBO;
import com.prwss.mis.masters.village.dao.VillageBean;
import com.prwss.mis.masters.village.dao.VillageDao;
import com.prwss.mis.masters.village.dao.VillageViewBean;

public class VillageAction extends DispatchAction {

	Logger log = Logger.getLogger(VillageAction.class);
	private MISSessionBean misSessionBean;
	private VillageBO villageBO;
	private LocationDao locationDao;
	private VillageDao villageDao;
	private ConstituencyDao constituencyDao;
	public void setConstituencyDao(ConstituencyDao constituencyDao) {
		this.constituencyDao = constituencyDao;
	}

	public void setVillageDao(VillageDao villageDao) {
		this.villageDao = villageDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setVillageBO(VillageBO villageBO) {
		this.villageBO = villageBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("In Find");
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
//			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
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
		try {
			VillageForm villageForm = (VillageForm)form;
			List<VillageViewBean> villageViewBeans = null;
			villageViewBeans = villageBO.findVillageView(villageForm, statusList);
			if(!MisUtility.ifEmpty(villageViewBeans)){
				request.setAttribute("villageList", villageViewBeans);
//		request.setAttribute("level2", "true");
//		for (VillageBean villageBean : villageBeans) {
//			villageForm.setVillageId(villageBean.getVillageId());
//			villageForm.setVillageName(villageBean.getVillageName());
//			villageForm.setHadBastNo(villageBean.getHadBastNo());
//			villageForm.setPcDate(MisUtility.convertDateToString(villageBean.getPcDate()));
//			villageForm.setFcDate(MisUtility.convertDateToString(villageBean.getFcDate()));
//			villageForm.setDistrict(villageBean.getDistrictId());
//			request.setAttribute("blockId", villageBean.getBlockId());
//			villageForm.setBlockId(villageBean.getBlockId());
////			villageForm.setCategory(villageBean.getCategories());
//			villageForm.setConstituencyId(villageBean.getConstituencyId());
//			request.setAttribute("constituencyId",villageBean.getConstituencyId());
//			villageForm.setHabitationType(villageBean.getHabitationType());
//			villageForm.setNcPcVillageStatus(villageBean.getNcPcVillageStatus());
//			villageForm.setNcPcVillageListSerialNo(villageBean.getNcpcVillageListSerialNo());
//			villageForm.setNumberOfPonds(villageBean.getNumberOfPonds());
//			villageForm.setParentHabitationId(villageBean.getParentHabitationId());
//			request.setAttribute("parentHabitationId",villageBean.getParentHabitationId());
//			villageForm.setWaterSource(villageBean.getWaterSource());
//			villageForm.setDivisionalOfficeId(villageBean.getDivisionalOfficeId());
//			villageForm.setdWSCApproved(villageBean.isdWSCApproved());
//			villageForm.setMisAuditBean(villageBean.getMisAuditBean());
//			
//			if(MisUtility.ifEmpty(villageBean.getCategory())){
//			String[] category =	villageBean.getCategory().split("~");
//			villageForm.setCategory(category);
//			}
//		}
			}else{
				refreshVillageForm(villageForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	 	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("Delete ... IEC Progress");
		this.setAttrib(request);
		VillageForm villageForm = (VillageForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = villageBO.deleteVillage(villageForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Village Details","ID -->"+villageForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete", "Village Details","ID -->"+villageForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Village Deletion");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Village Deletion");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		try {
			refreshVillageForm(villageForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		VillageForm villageForm = (VillageForm)form;
		String villageId = null;
		try {
			StringBuffer stringMessage = new StringBuffer();
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			if(!MisUtility.ifEmpty(villageForm.getVillageName()) || !MisUtility.ifEmpty(villageForm.getDistrict())|| !MisUtility.ifEmpty(villageForm.getBlockId())|| !MisUtility.ifEmpty(villageForm.getConstituencyId())||!MisUtility.ifEmpty(villageForm.getDivisionalOfficeId())||!MisUtility.ifEmpty(villageForm.getSubDiv())||!MisUtility.ifEmpty(villageForm.getHabitationType())||!MisUtility.ifEmpty(villageForm.getParliamentConstituencyName())||!MisUtility.ifEmpty(villageForm.getNameOfGramPanchayat())){
				if(!MisUtility.ifEmpty(villageForm.getVillageName())){
					stringMessage.append("Please Enter Village Name");
				}
				if(!MisUtility.ifEmpty(villageForm.getDistrict())){
					stringMessage.append("<br>Please select District");
				}
				if(!MisUtility.ifEmpty(villageForm.getBlockId())){
					stringMessage.append("<br>Please select Block");
				}
				if(!MisUtility.ifEmpty(villageForm.getConstituencyId())){
					stringMessage.append("<br>Please select Constituency");
				}
				if(!MisUtility.ifEmpty(villageForm.getDivisionalOfficeId())){
					stringMessage.append("<br>Please select Divisional Office");
				}
				if(!MisUtility.ifEmpty(villageForm.getSubDiv())){
					stringMessage.append("<br>Please select Sub Division");
				}
				if(!MisUtility.ifEmpty(villageForm.getHabitationType())){
					stringMessage.append("<br>Please select Habitation Type");
				}
				if(!MisUtility.ifEmpty(villageForm.getParliamentConstituencyName())){
					stringMessage.append("<br>Please enter Parliament Constituency Name");
				}
				if(!MisUtility.ifEmpty(villageForm.getNameOfGramPanchayat())){
					stringMessage.append("<br>Please enter Name of Gram Panchayat");
				}
				throw new MISException(MISExceptionCodes.MIS004,stringMessage.toString());
			}
			villageId = villageBO.saveVillage(villageForm, misSessionBean);
			if (MisUtility.ifEmpty(villageId)){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Village Information ","Village ID -->"+villageId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Village Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				System.out.println("-----------------Duplicate------------------");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry","Saving failed as ",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Saving failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Village Master");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Village Master");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshVillageForm(villageForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		VillageForm villageForm = (VillageForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
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
			status = villageBO.updateVillage(villageForm, misSessionBean,statusList);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "Village Information ","Village ID -->"+villageForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "Village Information ","Village ID -->"+villageForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Village Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Village Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		try {
			refreshVillageForm(villageForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("save:"+request.getParameter("d__mode"));
		this.setAttrib(request);
		VillageForm villageForm = (VillageForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = villageBO.postVillage(villageForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post", "Village Information ","Village ID -->"+villageForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post", "Village Information ","Village ID -->"+villageForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Village Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Village Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
			refreshVillageForm(villageForm);
		
		return mapping.findForward("display");
	}
	
	
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "villageId@districtId@divisionalOfficeId");	
		request.setAttribute("d__auto", "villageId");	
		
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
//		long heapSize = Runtime.getRuntime().totalMemory();           //Print the jvm heap size.         
//		System.out.println("Heap Size = " + heapSize); 
		VillageForm villageForm = (VillageForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		Set<LabelValueBean> districts = getDistricts();
		request.getSession().setAttribute("districts", districts);
		//Set<LabelValueBean> divisional = getDivisionalOffice();
		//request.getSession().setAttribute("divisional", divisional);
		//Set<LabelValueBean> subdivisional = getSubDivisionalOffice();
		//request.getSession().setAttribute("subdivisional", subdivisional);
		refreshVillageForm(villageForm);
		return mapping.findForward("display");
	}

	public ActionForward fetchSubDivisionalOffice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException{
		Set<LocationBean> locationBeans  = null;
		List<String> locationType = new ArrayList<String>();
		locationType.add("Sub-Division");
		StringBuffer buffer = new StringBuffer();
		try {
			if(MisUtility.ifEmpty(request.getParameter("divisionalOfficeId"))){
				System.out.println("-------SubDivision-------District"+request.getParameter("divisionalOfficeId"));
				locationBeans = locationDao.getChildLocationIds(request.getParameter("divisionalOfficeId"), locationType);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (LocationBean bean : locationBeans) {
					buffer.append("<option value=\"").append(bean.getLocationId()).append("\">");
					buffer.append(bean.getLocationName()+" - ("+bean.getLocationId()+")");
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

	private Set<LabelValueBean> getDistricts(){
		Set<LabelValueBean> districts = null;
		Set<LocationBean> locationBeans  = null;
		List<String> locationType = new ArrayList<String>();
		locationType.add("DISTRICT");
		try{
			
			locationBeans = locationDao.getLocationIdOnTypeList(locationType);
			districts = new TreeSet<LabelValueBean>();
			for (LocationBean bean : locationBeans) {
				districts.add(new LabelValueBean(bean.getLocationName()+" - ("+bean.getLocationId()+")",bean.getLocationId()));
			}
			
		}catch(DataAccessException e){
			log.error(e);
			e.printStackTrace();
		}
		catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
		return districts;	
	}
	
	public ActionForward fetchDivisionalOffice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException{
		
		Set<LocationBean> locationBeans  = null;
		List<String> locationType = new ArrayList<String>();
		locationType.add("DO");
		StringBuffer buffer = new StringBuffer();
		try {
			if(MisUtility.ifEmpty(request.getParameter("districtId"))){
				System.out.println("--------------District"+request.getParameter("districtId"));
				locationBeans = locationDao.getChildLocationIds(request.getParameter("districtId"), locationType);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (LocationBean bean : locationBeans) {
					buffer.append("<option value=\"").append(bean.getLocationId()).append("\">");
					buffer.append(bean.getLocationName()+" - ("+bean.getLocationId()+")");
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
	
	public ActionForward fetchBlockId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if(MisUtility.ifEmpty(request.getParameter("districtId"))){
				locationBeans = locationDao.getChildLocationIds(request.getParameter("districtId"), "BLOCK");
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (LocationBean bean : locationBeans) {
					buffer.append("<option value=\"").append(bean.getLocationId()).append("\">");
					buffer.append(bean.getLocationName()+" - ("+bean.getLocationId()+")");
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
	
	public ActionForward fetchConstituency(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		Set<ConstituencyBean> constituencyBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if(MisUtility.ifEmpty(request.getParameter("districtId"))){
				constituencyBeans = constituencyDao.findConstituency(request.getParameter("districtId"));
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (ConstituencyBean bean : constituencyBeans) {
					buffer.append("<option value=\"").append(bean.getConstituencyId()).append("\">");
					buffer.append(bean.getConstituencyName()+"("+bean.getConstituencyId()+")");
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
	
	public ActionForward fetchVillageIdOnHabitation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		List<VillageBean> villageBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if(MisUtility.ifEmpty(request.getParameter("habiationType")) && "OH".equals(request.getParameter("habiationType")) && MisUtility.ifEmpty(request.getParameter("blockIdForHabitation"))){
				villageBeans = villageDao.getVillageOnHabitationType("MH",request.getParameter("blockIdForHabitation"));
				for (VillageBean bean : villageBeans) {
					buffer.append("<option value=\"").append(bean.getVillageId()).append("\">");
					buffer.append(bean.getVillageName()+"("+bean.getVillageId()+")");
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
	
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		String villageId = request.getParameter("villageId");
		VillageForm villageForm = (VillageForm)form;
		villageForm.setVillageId(villageId);
		VillageBean villageBean = villageBO.findVillage(villageForm, null).get(0);
		if(MisUtility.ifEmpty(villageBean)){
			
//			for (ProgramBean programBean : programBeans) {
			villageForm.setVillageId(villageBean.getVillageId());
			villageForm.setVillageName(villageBean.getVillageName());
			villageForm.setHadBastNo(villageBean.getHadBastNo());
			villageForm.setPcDate(MisUtility.convertDateToString(villageBean.getPcDate()));
			villageForm.setFcDate(MisUtility.convertDateToString(villageBean.getFcDate()));
			villageForm.setDistrict(villageBean.getDistrictId());
			request.setAttribute("blockId", villageBean.getBlockId());
			villageForm.setBlockId(villageBean.getBlockId());
//			villageForm.setCategory(villageBean.getCategories());
			villageForm.setConstituencyId(villageBean.getConstituencyId());
			request.setAttribute("constituencyId",villageBean.getConstituencyId());
			villageForm.setHabitationType(villageBean.getHabitationType());
			villageForm.setNcPcVillageStatus(villageBean.getNcPcVillageStatus());
			villageForm.setNcPcVillageListSerialNo(villageBean.getNcpcVillageListSerialNo());
			villageForm.setNumberOfPonds(villageBean.getNumberOfPonds());
			villageForm.setParentHabitationId(villageBean.getParentHabitationId());
			request.setAttribute("parentHabitationId",villageBean.getParentHabitationId());
			villageForm.setWaterSource(villageBean.getWaterSource());
			villageForm.setDivisionalOfficeId(villageBean.getDivisionalOfficeId());
			villageForm.setSubDiv(villageBean.getSubDiv());
			System.out.println("Sub Division "+villageBean.getSubDiv()+" Divisional "+villageBean.getDivisionalOfficeId());
			request.setAttribute("subDiv",villageBean.getSubDiv());
			request.setAttribute("divisionalOffice",villageBean.getDivisionalOfficeId());
			villageForm.setdWSCApproved(villageBean.isdWSCApproved());
			villageForm.setMisAuditBean(villageBean.getMisAuditBean());
			villageForm.setParliamentConstituencyName(villageBean.getParliamentConstituencyName());
			villageForm.setNameOfGramPanchayat(villageBean.getNameOfGramPanchayat());
			if(MisUtility.ifEmpty(villageBean.getCategory())){
			String[] category =	villageBean.getCategory().split("~");
			villageForm.setCategory(category);
			}
			List<VillageViewBean> villageViewBeans = null;
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			System.out.println("111111111111"+villageForm.getVillageId());
			System.out.println("222222222222"+villageForm.getParentHabitationId());
			villageViewBeans = villageBO.findOtherHabitation(villageForm, statusList);
			if(!MisUtility.ifEmpty(villageViewBeans)){
				System.out.println("inside displayyy");
				request.setAttribute("villageList", villageViewBeans);
			
//			}
			}
		}
		
		return mapping.findForward("display");
	}
	
	private void refreshVillageForm(VillageForm villageForm){
		villageForm.setVillageId(null);
		villageForm.setVillageName(null);
		villageForm.setHadBastNo(null);
		villageForm.setPcDate(null);
		villageForm.setFcDate(null);
		villageForm.setBlockId(null);
		villageForm.setCategory(null);
		villageForm.setConstituencyId(null);
		villageForm.setHabitationType(null);
		villageForm.setNcPcVillageStatus(null);
		villageForm.setNcPcVillageListSerialNo(0);
		villageForm.setNumberOfPonds(0);
		villageForm.setParentHabitationId(null);
		villageForm.setWaterSource(null);
		villageForm.setDivisionalOfficeId(null);
		villageForm.setSubDiv(null);
		villageForm.setdWSCApproved(false);
		villageForm.setParliamentConstituencyName(null);
		villageForm.setNameOfGramPanchayat(null);
	}
}
