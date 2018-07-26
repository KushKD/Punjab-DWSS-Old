package com.prwss.mis.pmm.environment.struts;

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
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.environment.EnvironmentBO;
import com.prwss.mis.pmm.environment.EnvironmentBean;

public class EnvironmentAction extends DispatchAction {

	Logger log = Logger.getLogger(EnvironmentAction.class);
	private MISSessionBean misSessionBean;
	private EnvironmentBO environmentBO;
	
	public void setEnvironmentBO(EnvironmentBO environmentBO) {
		this.environmentBO = environmentBO;
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
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		this.setAttrib(request);
		EnvironmentForm environmentForm = (EnvironmentForm)form;
		List<EnvironmentBean> environmentBeans = null;
		String blockId = environmentForm.getBlockId();
		environmentBeans = environmentBO.findEnvironment(environmentForm, statusList);
		if(!MisUtility.ifEmpty(environmentBeans)){
			request.setAttribute("level2", "true");
			for (EnvironmentBean environmentBean : environmentBeans) {
				request.setAttribute("locationId", environmentBean.getLocationId());
				request.setAttribute("villageId", environmentBean.getVillageId());
				request.setAttribute("blockId", blockId);
				environmentForm.setAffectNaturalHabitats(environmentBean.getAffectNaturalHabitats());
				environmentForm.setAntWaterAvlbIssue(environmentBean.getAntWaterAvlbIssue());
				environmentForm.setAntWaterAvlbMitigation(environmentBean.getAntWaterAvlbMitigation());
				environmentForm.setAntWaterQultIssue(environmentBean.getAntWaterQultIssue());
				environmentForm.setAntWaterQultMitigation(environmentBean.getAntWaterQultMitigation());
				environmentForm.setConstructionIssue(environmentBean.getConstructionIssue());
				environmentForm.setConstructionMitigation(environmentBean.getConstructionMitigation());
				environmentForm.setConstructionWastesIssue(environmentBean.getConstructionWastesIssue());
				environmentForm.setConstructionWastesMitigation(environmentBean.getConstructionWastesMitigation());
				environmentForm.setContaminationDrinkingWater(environmentBean.getContaminationDrinkingWater());
				environmentForm.setContaminationDrinkingWaterSource(environmentBean.getContaminationDrinkingWaterSource());
				environmentForm.setDiseaseName(environmentBean.getDiseaseName());
				environmentForm.setDisinfectionExists(environmentBean.getDisinfectionExists());
				environmentForm.setDomesticSolidWaste(environmentBean.getDomesticSolidWaste());
				environmentForm.setExistingRoads(environmentBean.getExistingRoads());
				environmentForm.setExistingWaterBody(environmentBean.getExistingWaterBody());
				environmentForm.setGreyBlackWaterMix(environmentBean.getGreyBlackWaterMix());
				environmentForm.setHistoricalImportance(environmentBean.getHistoricalImportance());
				environmentForm.setIncidentWaterborn(environmentBean.getIncidentWaterborn());
				environmentForm.setInfringeLocalRights(environmentBean.getInfringeLocalRights());
				environmentForm.setIntensityOfRainfall(environmentBean.getIntensityOfRainfall());
				environmentForm.setIonisationPlant(environmentBean.getIonisationPlant());
				environmentForm.setLandfillSiteSolidWaste(environmentBean.getLandfillSiteSolidWaste());
				environmentForm.setLandUse(environmentBean.getLandUse());
				environmentForm.setLandUsePattern(environmentBean.getLandUsePattern());
				environmentForm.setLocalVegetation(environmentBean.getLocalVegetation());
				environmentForm.setMajorSourceIncome(environmentBean.getMajorSourceIncome());
				environmentForm.setMethodTreatment(environmentBean.getMethodTreatment());
				environmentForm.setNatureOfProblem(environmentBean.getNatureOfProblem());
				environmentForm.setNumberHousehold(environmentBean.getNumberHousehold());
				environmentForm.setPondsDistanceSchool(environmentBean.getPondsDistanceSchool());
				environmentForm.setPondsDistanceSettlement(environmentBean.getPondsDistanceSettlement());
				environmentForm.setPondsExpansionLand(environmentBean.getPondsExpansionLand());
				environmentForm.setPondsExpansionRequired(environmentBean.getPondsExpansionRequired());
				environmentForm.setPondsSitePlantation(environmentBean.getPondsSitePlantation());
				environmentForm.setPondsSitePlantation(environmentBean.getPondsSitePlantation());
				environmentForm.setPondsStp(environmentBean.getPondsStp());
				environmentForm.setPondsWaterQuality(environmentBean.getPondsWaterQuality());
				environmentForm.setPondUse(environmentBean.getPondUse());
				environmentForm.setPopulation(environmentBean.getPopulation());
				environmentForm.setPopulationEffectedWaterLogging(environmentBean.getPopulationEffectedWaterLogging());
				environmentForm.setRiskContamination(environmentBean.getRiskContamination());
				environmentForm.setRoadWidthMaxMin(environmentBean.getRoadWidthMaxMin());
				environmentForm.setSanitationFeedback(environmentBean.getSanitationFeedback());
				environmentForm.setSanitationIssue(environmentBean.getSanitationIssue());
				environmentForm.setSanitationMitigation(environmentBean.getSanitationMitigation());
				environmentForm.setSchemeType(environmentBean.getSchemeType());
				environmentForm.setSewagePattern(environmentBean.getSewagePattern());
				environmentForm.setSewagePractices(environmentBean.getSewagePractices());
				environmentForm.setSewageSchemeType(environmentBean.getSewageSchemeType());
				environmentForm.setSlopeOfLand(environmentBean.getSlopeOfLand());
				environmentForm.setSourceOfDrinkingWater(environmentBean.getSourceOfDrinkingWater());
				environmentForm.setSourceWaterAssessed(environmentBean.getSourceWaterAssessed());
				environmentForm.setTempMax(environmentBean.getTempMax());
				environmentForm.setTempMin(environmentBean.getTempMin());
				environmentForm.setTopography(environmentBean.getTopography());
				environmentForm.setTreatmentTechnology(environmentBean.getTreatmentTechnology());
				environmentForm.setTreamentProposed(environmentBean.getTreamentProposed());
				environmentForm.setTypeOfSoil(environmentBean.getTypeOfSoil());
				environmentForm.setVectorDisease(environmentBean.getVectorDisease());
				environmentForm.setWastewaterCattle(environmentBean.getWastewaterCattle());
				environmentForm.setWastewaterQuantity(environmentBean.getWastewaterQuantity());
				environmentForm.setWaterAvailability(environmentBean.getWaterAvailability());
				environmentForm.setWaterLoggingArea(environmentBean.getWaterLoggingArea());
				environmentForm.setWaterLoggingAreaName(environmentBean.getWaterLoggingAreaName());
				environmentForm.setWaterLoggingPeriod(environmentBean.getWaterLoggingPeriod());
				environmentForm.setWaterTable(environmentBean.getWaterTable());
				environmentForm.setWindDirection(environmentBean.getWindDirection());
				environmentForm.setDateOfTransaction(MisUtility.convertDateToString(environmentBean.getDateOfTransaction()));
				environmentForm.setExistingWaterBodyOther(environmentBean.getExistingWaterBodyOther());
			}
		}else{
			refreshEnvironmentForm(environmentForm);
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
		this.setAttrib(request);
		EnvironmentForm environmentForm = (EnvironmentForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = environmentBO.deleteEnvironment(environmentForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Environment Details","Village ID -->"+environmentForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Environment Details","Village ID -->"+environmentForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Environment Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Environment Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshEnvironmentForm(environmentForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		EnvironmentForm environmentForm = (EnvironmentForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}

			status = environmentBO.saveEnvironment(environmentForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Environment Details","Village ID -->"+environmentForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Environment Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Environment Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Environment Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshEnvironmentForm(environmentForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		EnvironmentForm environmentForm = (EnvironmentForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = environmentBO.updateEnvironment(environmentForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Environment Details","Village ID -->"+environmentForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Environment Details","Village ID -->"+environmentForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Environment Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Environment Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshEnvironmentForm(environmentForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		EnvironmentForm environmentForm = (EnvironmentForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = environmentBO.postEnvironment(environmentForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Environment Details","Village ID -->"+environmentForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Environment Details","Village ID -->"+environmentForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Environment Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Environment Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		refreshEnvironmentForm(environmentForm);
		return mapping.findForward("display");
	}
	
	
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "villageId@schemeId@locationId@blockId");
		
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		EnvironmentForm environmentForm = (EnvironmentForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		refreshEnvironmentForm(environmentForm);
		return mapping.findForward("display");
	}

	private void refreshEnvironmentForm(EnvironmentForm environmentForm){
		
		environmentForm.setLocationId(null);
		environmentForm.setDateOfTransaction(null);
		environmentForm.setVillageId(null);
		environmentForm.setBlockId(null);
		environmentForm.setAffectNaturalHabitats(null);
		environmentForm.setAntWaterAvlbIssue(null);
		environmentForm.setAntWaterAvlbMitigation(null);
		environmentForm.setAntWaterQultIssue(null);
		environmentForm.setAntWaterQultMitigation(null);
		environmentForm.setConstructionIssue(null);
		environmentForm.setConstructionMitigation(null);
		environmentForm.setConstructionWastesIssue(null);
		environmentForm.setConstructionWastesMitigation(null);
		environmentForm.setContaminationDrinkingWater(null);
		environmentForm.setContaminationDrinkingWaterSource(null);
		environmentForm.setDiseaseName(null);
		environmentForm.setDisinfectionExists(null);
		environmentForm.setDomesticSolidWaste(null);
		environmentForm.setExistingRoads(null);
		environmentForm.setExistingWaterBody(null);
		environmentForm.setGreyBlackWaterMix(null);
		environmentForm.setHistoricalImportance(null);
		environmentForm.setIncidentWaterborn(null);
		environmentForm.setInfringeLocalRights(null);
		environmentForm.setIntensityOfRainfall(null);
		environmentForm.setIonisationPlant(null);
		environmentForm.setLandfillSiteSolidWaste(null);
		environmentForm.setLandUse(null);
		environmentForm.setLandUsePattern(null);
		environmentForm.setLocalVegetation(null);
		environmentForm.setLocationId(null);
		environmentForm.setMajorSourceIncome(null);
		environmentForm.setMethodTreatment(null);
		environmentForm.setNatureOfProblem(null);
		environmentForm.setNumberHousehold(0);
		environmentForm.setPondsDistanceSchool(null);
		environmentForm.setPondsDistanceSettlement(null);
		environmentForm.setPondsExpansionLand(null);
		environmentForm.setPondsExpansionRequired(null);
		environmentForm.setPondsSitePlantation(null);
		environmentForm.setPondsSitePlantation(null);
		environmentForm.setPondsStp(null);
		environmentForm.setPondsWaterQuality(null);
		environmentForm.setPondUse(null);
		environmentForm.setPopulation(0);
		environmentForm.setPopulationEffectedWaterLogging(0);
		environmentForm.setRiskContamination(null);
		environmentForm.setRoadWidthMaxMin(null);
		environmentForm.setSanitationFeedback(null);
		environmentForm.setSanitationIssue(null);
		environmentForm.setSanitationMitigation(null);
		environmentForm.setSchemeType(null);
		environmentForm.setSewagePattern(null);
		environmentForm.setSewagePractices(null);
		environmentForm.setSewageSchemeType(null);
		environmentForm.setSlopeOfLand(null);
		environmentForm.setSourceOfDrinkingWater(null);
		environmentForm.setSourceWaterAssessed(null);
		environmentForm.setTempMax(null);
		environmentForm.setTempMin(null);
		environmentForm.setTopography(null);
		environmentForm.setTreatmentTechnology(null);
		environmentForm.setTreamentProposed(null);
		environmentForm.setTypeOfSoil(null);
		environmentForm.setVectorDisease(null);
		environmentForm.setWastewaterCattle(null);
		environmentForm.setWastewaterQuantity(null);
		environmentForm.setWaterAvailability(null);
		environmentForm.setWaterLoggingArea(null);
		environmentForm.setWaterLoggingAreaName(null);
		environmentForm.setWaterLoggingPeriod(null);
		environmentForm.setWaterTable(null);
		environmentForm.setWindDirection(null);
		
	}
}
