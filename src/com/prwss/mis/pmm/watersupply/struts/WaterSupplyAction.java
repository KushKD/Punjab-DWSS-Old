package com.prwss.mis.pmm.watersupply.struts;

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
import com.prwss.mis.pmm.watersupply.WaterSupplyBO;
import com.prwss.mis.pmm.watersupply.WaterSupplyBean;

public class WaterSupplyAction extends DispatchAction {

	Logger log = Logger.getLogger(WaterSupplyAction.class);
	private MISSessionBean misSessionBean;
	private WaterSupplyBO waterSupplyBO;
	
	

	public void setWaterSupplyBO(WaterSupplyBO waterSupplyBO) {
		this.waterSupplyBO = waterSupplyBO;
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
		try {
			WaterSupplyForm waterSupplyForm = (WaterSupplyForm)form;
			List<WaterSupplyBean> waterSupplyBeans = null;
			String blockId = waterSupplyForm.getBlockId();
			waterSupplyBeans = waterSupplyBO.findWaterSupply(waterSupplyForm, statusList);
			if(!MisUtility.ifEmpty(waterSupplyBeans)){
				request.setAttribute("level2", "true");
				for (WaterSupplyBean waterSupplyBean : waterSupplyBeans) {
					
					waterSupplyForm.setAvailabilityOfLand(waterSupplyBean.getAvailabilityOfLand());
					waterSupplyForm.setCattlePopulation(waterSupplyBean.getCattlePopulation());
					waterSupplyForm.setDisposalLocation(waterSupplyBean.getDisposalLocation());
					waterSupplyForm.setGroundwaterLevel(waterSupplyBean.getGroundwaterLevel());
					waterSupplyForm.setLandOwnership(waterSupplyBean.getLandOwnership());
					waterSupplyForm.setLandRequirment(waterSupplyBean.getLandRequirment());
					waterSupplyForm.setLanduseStpSite(waterSupplyBean.getLanduseStpSite());
					waterSupplyForm.setMunicipalCorporataion(waterSupplyBean.getMunicipalCorporataion());
					waterSupplyForm.setNameOfDisease(waterSupplyBean.getNameOfDisease());
					waterSupplyForm.setNoWaterbornDisease(waterSupplyBean.getNoWaterbornDisease());
					waterSupplyForm.setReceptorsHealthcenterDistance(waterSupplyBean.getReceptorsHealthcenterDistance());
					waterSupplyForm.setReceptorsReligiousStrDistance(waterSupplyBean.getReceptorsReligiousStrDistance());
					waterSupplyForm.setReceptorsSchoolDistance(waterSupplyBean.getReceptorsSchoolDistance());
					waterSupplyForm.setReceptorsWildlifeDistance(waterSupplyBean.getReceptorsWildlifeDistance());
					waterSupplyForm.setSettlementsDistance(waterSupplyBean.getSettlementsDistance());
					waterSupplyForm.setSourceWater(waterSupplyBean.getSourceWater());
					waterSupplyForm.setTopography(waterSupplyBean.getTopography());
					waterSupplyForm.setTypeOfRoads(waterSupplyBean.getTypeOfRoads());
					waterSupplyForm.setTypeWaterSupply(waterSupplyBean.getTypeWaterSupply());
					waterSupplyForm.setUsageWaterBody(waterSupplyBean.getUsageWaterBody());
					waterSupplyForm.setWaterBody(waterSupplyBean.getWaterBody());
					waterSupplyForm.setWaterBodyDistance(waterSupplyBean.getWaterBodyDistance());
					waterSupplyForm.setWaterLevel(waterSupplyBean.getWaterLevel());
					waterSupplyForm.setWaterQuality(waterSupplyBean.getWaterQuality());
					waterSupplyForm.setWidthOfRoad(waterSupplyBean.getWidthOfRoad());
					waterSupplyForm.setDateOfTransaction(MisUtility.convertDateToString(waterSupplyBean.getDateOfTransaction()));
					waterSupplyForm.setProvisionLawnPlantation(waterSupplyBean.getProvisionLawnPlantation());
					waterSupplyForm.setRainWaterHarvesting(waterSupplyBean.getRainWaterHarvesting());
					waterSupplyForm.setRepairCleaning(waterSupplyBean.getRepairCleaning());
					waterSupplyForm.setSolidWasteRemoval(waterSupplyBean.getSolidWasteRemoval());
					waterSupplyForm.setPublicAwareness(waterSupplyBean.getPublicAwareness());
					waterSupplyForm.setCleaningPondRequired(waterSupplyBean.getCleaningPondRequired());
					waterSupplyForm.setWasteWaterQuantity(waterSupplyBean.getWasteWaterQuantity());
					waterSupplyForm.setCurrentSanitation(waterSupplyBean.getCurrentSanitation());
					waterSupplyForm.setExistingPondsSewageDisharge(waterSupplyBean.getExistingPondsSewageDischarge());
					waterSupplyForm.setDateOfTransaction(MisUtility.convertDateToString(waterSupplyBean.getDateOfTransaction()));
					waterSupplyForm.setHouseHolds(waterSupplyBean.getHouseHolds());
					request.setAttribute("locationId", waterSupplyBean.getLocationId());
					request.setAttribute("villageId", waterSupplyBean.getVillageId());
					request.setAttribute("blockId", blockId);
				}
			}else{
				refreshWaterSupplyForm(waterSupplyForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Environment Details (PSR)");
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
		WaterSupplyForm waterSupplyForm = (WaterSupplyForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = waterSupplyBO.deleteWaterSupply(waterSupplyForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Environment Details (PSR)","Village ID -->"+waterSupplyForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Environment Details (PSR)","Village ID -->"+waterSupplyForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Environment Details (PSR)");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Environment Details (PSR)");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshWaterSupplyForm(waterSupplyForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		WaterSupplyForm waterSupplyForm = (WaterSupplyForm)form;
		System.out.println("INSAVE village Id"+waterSupplyForm.getVillageId());
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

			System.out.println(waterSupplyForm.toString());
			status = waterSupplyBO.saveWaterSupply(waterSupplyForm, misSessionBean);
			
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Environment Details (PSR)","Village ID -->"+waterSupplyForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Environment Details (PSR)");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Environment Details (PSR)");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Environment Details (PSR)");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshWaterSupplyForm(waterSupplyForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		WaterSupplyForm waterSupplyForm = (WaterSupplyForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = waterSupplyBO.updateWaterSupply(waterSupplyForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Environment Details (PSR)","Village ID -->"+waterSupplyForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Environment Details (PSR)","Village ID -->"+waterSupplyForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Environment Details (PSR)");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Environment Details (PSR)");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshWaterSupplyForm(waterSupplyForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		WaterSupplyForm waterSupplyForm = (WaterSupplyForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = waterSupplyBO.postWaterSupply(waterSupplyForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Environment Details (PSR)","Village ID -->"+waterSupplyForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Environment Details (PSR)","Village ID -->"+waterSupplyForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Environment Details (PSR)");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Environment Details (PSR)");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		refreshWaterSupplyForm(waterSupplyForm);
		return mapping.findForward("display");
	}
	
	
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId@blockId@villageId@schemeId");
		
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		WaterSupplyForm waterSupplyForm = (WaterSupplyForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		refreshWaterSupplyForm(waterSupplyForm);
		return mapping.findForward("display");
	}

	private void refreshWaterSupplyForm(WaterSupplyForm waterSupplyForm){
		
		waterSupplyForm.setAvailabilityOfLand(null);
		waterSupplyForm.setCattlePopulation(null);
		waterSupplyForm.setDisposalLocation(null);
		waterSupplyForm.setGroundwaterLevel(null);
		waterSupplyForm.setLandOwnership(null);
		waterSupplyForm.setLandRequirment(null);
		waterSupplyForm.setLanduseStpSite(null);
		waterSupplyForm.setLocationId(null);
		waterSupplyForm.setMunicipalCorporataion(null);
		waterSupplyForm.setNameOfDisease(null);
		waterSupplyForm.setNoWaterbornDisease(null);
		waterSupplyForm.setReceptorsHealthcenterDistance(null);
		waterSupplyForm.setReceptorsReligiousStrDistance(null);
		waterSupplyForm.setReceptorsSchoolDistance(null);
		waterSupplyForm.setReceptorsWildlifeDistance(null);
		waterSupplyForm.setSettlementsDistance(null);
		waterSupplyForm.setSourceWater(null);
		waterSupplyForm.setTopography(null);
		waterSupplyForm.setTypeOfRoads(null);
		waterSupplyForm.setTypeWaterSupply(null);
		waterSupplyForm.setUsageWaterBody(null);
		waterSupplyForm.setWaterBody(null);
		waterSupplyForm.setWaterBodyDistance(null);
		waterSupplyForm.setWaterLevel(null);
		waterSupplyForm.setWaterQuality(null);
		waterSupplyForm.setWidthOfRoad(null);
		waterSupplyForm.setDateOfTransaction(null);
		waterSupplyForm.setVillageId(null);
		waterSupplyForm.setProvisionLawnPlantation(null);
		waterSupplyForm.setRainWaterHarvesting(null);
		waterSupplyForm.setRepairCleaning(null);
		waterSupplyForm.setSolidWasteRemoval(null);
		waterSupplyForm.setPublicAwareness(null);
		waterSupplyForm.setCleaningPondRequired(null);
		waterSupplyForm.setWasteWaterQuantity(null);
		waterSupplyForm.setCurrentSanitation(null);
		waterSupplyForm.setExistingPondsSewageDisharge(null);
		waterSupplyForm.setDateOfTransaction(null);
		waterSupplyForm.setVillageId(null);
		
	}
}
