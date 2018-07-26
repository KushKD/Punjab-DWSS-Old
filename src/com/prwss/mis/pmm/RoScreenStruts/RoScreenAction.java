package com.prwss.mis.pmm.RoScreenStruts;

import java.io.PrintWriter;
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

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.RoScreen.RoScreenBean;
import com.prwss.mis.pmm.RoScreen.RoScreenBo;
import com.prwss.mis.pmm.RoScreenDao.RoScreenDao;

public class RoScreenAction extends DispatchAction{

	Logger log = Logger.getLogger(RoScreenAction.class);
	
	private MISSessionBean misAuditBean = new MISSessionBean();
	private MISSessionBean misSessionBean;
	private RoScreenDao roScreenDao;
	private RoScreenBo roScreenBo;
	
	
	public MISSessionBean getMisAuditBean() {
		return misAuditBean;
	}
	public void setMisAuditBean(MISSessionBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	public MISSessionBean getMisSessionBean() {
		return misSessionBean;
	}
	public void setMisSessionBean(MISSessionBean misSessionBean) {
		this.misSessionBean = misSessionBean;
	}
	public RoScreenDao getRoScreenDao() {
		return roScreenDao;
	}
	public void setRoScreenDao(RoScreenDao roScreenDao) {
		this.roScreenDao = roScreenDao;
	}
	public RoScreenBo getRoScreenBo() {
		return roScreenBo;
	}
	public void setRoScreenBo(RoScreenBo roScreenBo) {
		this.roScreenBo = roScreenBo;
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		RoScreenForm roScreenForm = (RoScreenForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		refreshRoScreenForm(roScreenForm);
		return mapping.findForward("display");
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "transactionDateId@locationId@division@subDivision@village");
		
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	private void refreshRoScreenForm(RoScreenForm roScreenForm){
		
		roScreenForm.setAdminAppCost(null);
		roScreenForm.setAllotCost(null);
		roScreenForm.setCapacityOfRO(null);
		roScreenForm.setCardHolders(null);
		roScreenForm.setCommisioningDate(null);
		roScreenForm.setCompleteDate(null);
		roScreenForm.setCompOandMDate(null);
		roScreenForm.setFunctionalDate(null);
		roScreenForm.setDivision(null);
		roScreenForm.setElecConnStatus(null);
		roScreenForm.setExecutingAgency(null);
		roScreenForm.setExpenTilldate(null);
		roScreenForm.setHeadProgramme(null);
		roScreenForm.setHousingStr(null);
		roScreenForm.setLockedHouses(null);
		roScreenForm.setMachinery(null);
		roScreenForm.setMachinery(null);
		roScreenForm.setNoHHsVillage(null);
		roScreenForm.setPercenPenetration(null);
		roScreenForm.setPlant(null);
		roScreenForm.setPlantCommissioned(null);
		roScreenForm.setPlantComplete(null);
		roScreenForm.setPlantFunction(null);
		roScreenForm.setPlatform(null);
		roScreenForm.setReason(null);
		roScreenForm.setSinceNonFunctionDate(null);
		roScreenForm.setSubDivision(null);
		roScreenForm.setTargetCommDate(null);
		roScreenForm.setTargetCopmDate(null);
		roScreenForm.setTubeInstStatus(null);
		roScreenForm.setVillage(null);
		roScreenForm.setDivisionName(null);
		roScreenForm.setSubDivisionName(null);
		roScreenForm.setVillageName(null);
		
	}

//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	public ActionForward getDivisionNameAndId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getDivisionNameAndId");

		List<RoScreenDto> roScreenDtos = null;

		StringBuffer buffer = new StringBuffer();

		try {
			roScreenDtos = roScreenDao.getDivisionNameAndId();
			
			System.out.println(roScreenDtos.toString());
			buffer.append("<option value='' selected>");
			buffer.append("Select Division");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(roScreenDtos)) {
				for (RoScreenDto roScreenDto : roScreenDtos) {
					buffer.append("<option value=\"").append(roScreenDto.getDivision()).append("\">");
					buffer.append(roScreenDto.getDivisionName());
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	public ActionForward getSubDivisionNameAndId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getSubDivisionNameAndId");
		
		String divisionId = null;
		
		divisionId = request.getParameter("division1");
		
		System.out.println("division id is----------" + divisionId);

		List<RoScreenDto> roScreenDtos = null;

		StringBuffer buffer = new StringBuffer();

		try {
			roScreenDtos = roScreenDao.getSubDivisionNameAndId(divisionId);
			
			System.out.println(roScreenDtos.toString());
			buffer.append("<option value='' selected>");
			buffer.append("Select Sub Division");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(roScreenDtos)) {
				for (RoScreenDto roScreenDto : roScreenDtos) {
					buffer.append("<option value=\"").append(roScreenDto.getSubDivision()).append("\">");
					buffer.append(roScreenDto.getSubDivisionName());
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	public ActionForward getVillageNameAndId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside getVillageNameAndId");
		
		String divisionId = null;
		String subDivisionId = null;
		
		divisionId = request.getParameter("division1");
		subDivisionId = request.getParameter("subDivision1");
		
		System.out.println("division id is----------" + divisionId);
		System.out.println("sub division id is----------" + subDivisionId);

		List<RoScreenDto> roScreenDtos = null;

		StringBuffer buffer = new StringBuffer();

		try {
			roScreenDtos = roScreenDao.getVillageNameAndId(divisionId, subDivisionId);
			
			System.out.println(roScreenDtos.toString());
			buffer.append("<option value='' selected>");
			buffer.append("Select Village");
			buffer.append("</option>");
			if (!MisUtility.ifEmpty(roScreenDtos)) {
				for (RoScreenDto roScreenDto : roScreenDtos) {
					buffer.append("<option value=\"").append(roScreenDto.getVillage()).append("\">");
					buffer.append(roScreenDto.getVillageName());
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	public ActionForward getCapacityOfRO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws MISException {

		System.out.println("inside getCapacityOfRO");
		
		String divisionId = null;
		String subDivisionId = null;
		String villageId = null;
		
		divisionId = request.getParameter("division1");
		subDivisionId = request.getParameter("subDivision1");
		villageId = request.getParameter("village1");
		
		System.out.println("divisionId is----------" + divisionId);
		System.out.println("subDivisionId is----------" + subDivisionId);
		System.out.println("villageId is----------" + villageId);

		String capacityOfRO;
		
		StringBuffer buffer = new StringBuffer();

		try {
			capacityOfRO = roScreenDao.getCapacityOfRO(divisionId, subDivisionId, villageId);
			System.out.println(capacityOfRO);

			buffer.append(capacityOfRO);

			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	public ActionForward getProgram(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws MISException {

		System.out.println("inside getProgram");
		
		String divisionId = null;
		String subDivisionId = null;
		String villageId = null;
		
		divisionId = request.getParameter("division1");
		subDivisionId = request.getParameter("subDivision1");
		villageId = request.getParameter("village1");
		
		System.out.println("divisionId is----------" + divisionId);
		System.out.println("subDivisionId is----------" + subDivisionId);
		System.out.println("villageId is----------" + villageId);

		String program;
		
		StringBuffer buffer = new StringBuffer();

		try {
			program = roScreenDao.getProgram(divisionId, subDivisionId, villageId);
			System.out.println(program);

			buffer.append(program);

			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	public ActionForward getExecutingAgency(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws MISException {

		System.out.println("inside getExecutingAgency");
		
		String divisionId = null;
		String subDivisionId = null;
		String villageId = null;
		
		divisionId = request.getParameter("division1");
		subDivisionId = request.getParameter("subDivision1");
		villageId = request.getParameter("village1");
		
		System.out.println("divisionId is----------" + divisionId);
		System.out.println("subDivisionId is----------" + subDivisionId);
		System.out.println("villageId is----------" + villageId);

		String execAgency;
		
		StringBuffer buffer = new StringBuffer();

		try {
			execAgency = roScreenDao.getExecutingAgency(divisionId, subDivisionId, villageId);
			System.out.println(execAgency);

			buffer.append(execAgency);

			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	/*public ActionForward getOnMDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws MISException {

		System.out.println("inside getOnMDate");
		
		String divisionId = null;
		String subDivisionId = null;
		String villageId = null;
		
		divisionId = request.getParameter("division1");
		subDivisionId = request.getParameter("subDivision1");
		villageId = request.getParameter("village1");
		
		System.out.println("divisionId is----------" + divisionId);
		System.out.println("subDivisionId is----------" + subDivisionId);
		System.out.println("villageId is----------" + villageId);

		String oNmDate;
		
		StringBuffer buffer = new StringBuffer();

		try {
			oNmDate = roScreenDao.getOnMDate(divisionId, subDivisionId, villageId);
			System.out.println(oNmDate);

			buffer.append(oNmDate);

			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}*/
//-----------------------------------------------------------------------------------------------------------------------------------------------

	/*public ActionForward getAdminApproveCost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws MISException {

		System.out.println("inside getAdminApproveCost");
		
		String divisionId = null;
		String subDivisionId = null;
		String villageId = null;
		
		divisionId = request.getParameter("division1");
		subDivisionId = request.getParameter("subDivision1");
		villageId = request.getParameter("village1");
		
		System.out.println("divisionId is----------" + divisionId);
		System.out.println("subDivisionId is----------" + subDivisionId);
		System.out.println("villageId is----------" + villageId);

		String adminAppCost;
		
		StringBuffer buffer = new StringBuffer();

		try {
			adminAppCost = roScreenDao.getAdminApproveCost(divisionId, subDivisionId, villageId);
			System.out.println(adminAppCost);

			buffer.append(adminAppCost);

			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
*///-----------------------------------------------------------------------------------------------------------------------------------------------

	public ActionForward getSeasonalRO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws MISException {

		System.out.println("inside getSeasonalRO");
		
		String divisionId = null;
		String subDivisionId = null;
		String villageId = null;
		
		divisionId = request.getParameter("division1");
		subDivisionId = request.getParameter("subDivision1");
		villageId = request.getParameter("village1");
		
		System.out.println("divisionId is----------" + divisionId);
		System.out.println("subDivisionId is----------" + subDivisionId);
		System.out.println("villageId is----------" + villageId);

		String roType;
		
		StringBuffer buffer = new StringBuffer();

		try {
			roType = roScreenDao.getSeasonalRO(divisionId, subDivisionId, villageId);
			System.out.println(roType);

			buffer.append(roType);

			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------

	public ActionForward getHouseholds(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws MISException {

		System.out.println("inside getHouseholds");
		
		String villageId = null;
		
		villageId = request.getParameter("village1");
		
		System.out.println("villageId is----------" + villageId);

		String households;
		
		StringBuffer buffer = new StringBuffer();

		try {
			households = roScreenDao.getHouseholds(villageId);
			System.out.println(households);

			buffer.append(households);

			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------

	public ActionForward save(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		boolean status = false;
		RoScreenForm roScreenForm = null;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			this.setAttrib(request);
			roScreenForm = (RoScreenForm) actionForm;

			if (MisUtility.ifEmpty(roScreenForm)) {
				System.out.println("inside sa-ve----------------------------" + roScreenForm.toString());
				 status = roScreenBo.saveData(roScreenForm, Integer.parseInt(String.valueOf(misAuditBean.getEnteredBy())));
			}

			if (status) {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Successfully mapped Villages and Divisions" );
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshRoScreenForm(roScreenForm);
			} else {
				request.setAttribute("level2", "true");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Internal error Please check logs..s");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("duplicate.entry", "Internal error Please check logs.");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------
	
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
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		this.setAttrib(request);
		RoScreenForm roScreenForm = (RoScreenForm)form;
		List<RoScreenBean> roScreenBeans= null;
		roScreenBeans = roScreenBo.find(roScreenForm, statusList);
		if(!MisUtility.ifEmpty(roScreenBeans)){
			request.setAttribute("level2", "true");
			for (RoScreenBean roBean : roScreenBeans) {
				if(MisUtility.ifEmpty(roBean.getAdminAppCost())){
					//roScreenForm.setAdminAppCost(String.valueOf(roBean.getAdminAppCost()));
					request.setAttribute("adminAppCost", roBean.getAdminAppCost());
				}
				if(MisUtility.ifEmpty(roBean.getAllotCost())){
				//roScreenForm.setAllotCost(String.valueOf(roBean.getAllotCost()));
					request.setAttribute("allotCost", roBean.getAllotCost());
				}
				if(MisUtility.ifEmpty(roBean.getCapacityOfRO())){
				roScreenForm.setCapacityOfRO(String.valueOf(roBean.getCapacityOfRO()));
				}
				if(MisUtility.ifEmpty(roBean.getCardHolders())){
				roScreenForm.setCardHolders(String.valueOf(roBean.getCardHolders()));
				}
				if(MisUtility.ifEmpty(roBean.getCommisioningDate())){
				roScreenForm.setCommisioningDate(MisUtility.convertDateToString(roBean.getCommisioningDate()));
				}
				if(MisUtility.ifEmpty(roBean.getCompleteDate())){
				roScreenForm.setCompleteDate(MisUtility.convertDateToString(roBean.getCompleteDate()));
				}
				if(MisUtility.ifEmpty(roBean.getCompOandMDate())){
				roScreenForm.setCompOandMDate(MisUtility.convertDateToString(roBean.getCompOandMDate()));
				}
				if(MisUtility.ifEmpty(roBean.getDivision())){
				request.setAttribute("divisionId", roBean.getDivision());
				}
				if(MisUtility.ifEmpty(roBean.getSubDivision())){
				request.setAttribute("subDivisionId", roBean.getSubDivision());
				}
				if(MisUtility.ifEmpty(roBean.getVillage())){
				request.setAttribute("villageId", roBean.getVillage());
				}
				if(MisUtility.ifEmpty(roBean.getDivisionName())){
				roScreenForm.setDivisionName(roBean.getDivisionName());
				}
				if(MisUtility.ifEmpty(roBean.getElecConnStatus())){
				request.setAttribute("elecConnStatus", roBean.getElecConnStatus());
				}
				if(MisUtility.ifEmpty(roBean.getExecutingAgency())){
				roScreenForm.setExecutingAgency(String.valueOf(roBean.getExecutingAgency()));
				}
				if(MisUtility.ifEmpty(roBean.getExpenTillDate())){
				//roScreenForm.setExpenTilldate(String.valueOf(roBean.getExpenTillDate()));
					request.setAttribute("expenTilldate", roBean.getExpenTillDate());
				}
				if(MisUtility.ifEmpty(roBean.getFunctionalDate())){
				roScreenForm.setFunctionalDate(MisUtility.convertDateToString(roBean.getFunctionalDate()));
				}
				if(MisUtility.ifEmpty(roBean.getHeadProgramme())){
				roScreenForm.setHeadProgramme(roBean.getHeadProgramme());
				}
				if(MisUtility.ifEmpty(roBean.getHousingStr())){
				request.setAttribute("housingStr", roBean.getHousingStr());
				}
				if(MisUtility.ifEmpty(roBean.getLockedHouses())){
				roScreenForm.setLockedHouses(String.valueOf(roBean.getLockedHouses()));
				}
				if(MisUtility.ifEmpty(roBean.getMachinery())){
				request.setAttribute("machinery", roBean.getMachinery());
				}
				if(MisUtility.ifEmpty(roBean.getNoHHsVillage())){
				roScreenForm.setNoHHsVillage(String.valueOf(roBean.getNoHHsVillage()));
				}
				if(MisUtility.ifEmpty(roBean.getOthersReason())){
				roScreenForm.setOthersReason(roBean.getOthersReason());
				}
				if(MisUtility.ifEmpty(roBean.getPercenPenetration())){
				roScreenForm.setPercenPenetration(String.valueOf(roBean.getPercenPenetration()));
				}
				if(MisUtility.ifEmpty(roBean.getPlant())){
				request.setAttribute("plant", roBean.getPlant());
				}
				if(MisUtility.ifEmpty(roBean.getPlantCommissioned())){
				request.setAttribute("plantCommissioned", roBean.getPlantCommissioned());
				}
				if(MisUtility.ifEmpty(roBean.getPlantComplete())){
				request.setAttribute("plantComplete", roBean.getPlantComplete());
				}
				if(MisUtility.ifEmpty(roBean.getPlantFunction())){
				request.setAttribute("plantFunction", roBean.getPlantFunction());
				}
				if(MisUtility.ifEmpty(roBean.getPlatform())){
				request.setAttribute("platform", roBean.getPlatform());
				}
				if(MisUtility.ifEmpty(roBean.getReason())){
				request.setAttribute("reason", roBean.getReason());
				}
				if(MisUtility.ifEmpty(roBean.getSeasonalRO())){
				roScreenForm.setSeasonalRO(roBean.getSeasonalRO());
				}
				if(MisUtility.ifEmpty(roBean.getSinceNonFunction())){
				roScreenForm.setSinceNonFunctionDate(MisUtility.convertDateToString(roBean.getSinceNonFunction()));
				}
				if(MisUtility.ifEmpty(roBean.getSubDivisionName())){
				roScreenForm.setSubDivisionName(roBean.getSubDivisionName());
				}
				if(MisUtility.ifEmpty(roBean.getTargetCommDate())){
				roScreenForm.setTargetCommDate(MisUtility.convertDateToString(roBean.getTargetCommDate()));
				}
				if(MisUtility.ifEmpty(roBean.getTargetCopmDate())){
				roScreenForm.setTargetCopmDate(MisUtility.convertDateToString(roBean.getTargetCopmDate()));
				}
				if(MisUtility.ifEmpty(roBean.getTubeInstStatus())){
				request.setAttribute("tubeInstStatus", roBean.getTubeInstStatus());
				}
				if(MisUtility.ifEmpty(roBean.getVillageName())){
				roScreenForm.setVillageName(roBean.getVillageName());
				}
			}
		}else{
			//refreshInstPlantsForm(roScreenForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	
}
