package com.prwss.mis.pmm.DSRBuilding.struts;

import java.math.BigDecimal;
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
import com.prwss.mis.pmm.DSRBuilding.DSRBuildingBO;
import com.prwss.mis.pmm.DSRBuilding.dao.DSRBuildingBean;

public class DSRBuildingAction extends DispatchAction {
	Logger log = Logger.getLogger(DSRBuildingAction.class);
	private MISSessionBean misSessionBean;
	private DSRBuildingBO dsrBuildingBO;
	
	public void setDsrBuildingBO(DSRBuildingBO dsrBuildingBO) {
		this.dsrBuildingBO = dsrBuildingBO;
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
		DSRBuildingForm dsrBuildingForm = (DSRBuildingForm)form;
		List<DSRBuildingBean> dsrBuildingBeans = null;
		dsrBuildingBeans = dsrBuildingBO.findDSRBuilding(dsrBuildingForm, statusList);
		String villageId = dsrBuildingForm.getVillageId();
		String blockId = dsrBuildingForm.getBlockId();
		if(!MisUtility.ifEmpty(dsrBuildingBeans)){
			request.setAttribute("level2", "true");
			for (DSRBuildingBean dsrBuildingBean : dsrBuildingBeans) {
				request.setAttribute("villageId", villageId);
				request.setAttribute("blockId", blockId);
				request.setAttribute("locationId", dsrBuildingBean.getLocationId());
				request.setAttribute("schemeId", dsrBuildingBean.getSchemeId());
				dsrBuildingForm.setDsrDate(MisUtility.convertDateToString(dsrBuildingBean.getDsrDate()));
				dsrBuildingForm.setAcplantCost(dsrBuildingBean.getAcplantCost());
				dsrBuildingForm.setContigencyCharges(dsrBuildingBean.getContigencyCharges());
				dsrBuildingForm.setDepartmentalCharges(dsrBuildingBean.getDepartmentalCharges());
				dsrBuildingForm.setEstatePipelineCost(dsrBuildingBean.getEstatePipelineCost());
				dsrBuildingForm.setEstatePipelineLength(dsrBuildingBean.getEstatePipelineLength());
				dsrBuildingForm.setFiresystemCost(dsrBuildingBean.getFiresystemCost());
				dsrBuildingForm.setHeadworksCost(dsrBuildingBean.getHeadworksCost());
				dsrBuildingForm.setHeadworksDischarge(dsrBuildingBean.getHeadworksDischarge());
				dsrBuildingForm.setHeadworksHead(dsrBuildingBean.getHeadworksHead());
				dsrBuildingForm.setHeadworksPumpchamberCost(dsrBuildingBean.getHeadworksPumpchamberCost());
				dsrBuildingForm.setInternalPipelineCost(dsrBuildingBean.getInternalPipelineCost());
				dsrBuildingForm.setInternalPipelineLength(dsrBuildingBean.getInternalPipelineLength());
				dsrBuildingForm.setLocationId(dsrBuildingBean.getLocationId());
				dsrBuildingForm.setOhsrCapacity(dsrBuildingBean.getOhsrCapacity());
				dsrBuildingForm.setOhsrCost(dsrBuildingBean.getOhsrCost());
				dsrBuildingForm.setRainwaterCost(dsrBuildingBean.getRainwaterCost());
				dsrBuildingForm.setSanitaryOwcEwcCost(dsrBuildingBean.getSanitaryOwcEwcCost());
				dsrBuildingForm.setSanitaryOwcEwcQuantity(dsrBuildingBean.getSanitaryOwcEwcQuantity());
				dsrBuildingForm.setSanitaryUrinalCost(dsrBuildingBean.getSanitaryUrinalCost());
				dsrBuildingForm.setSanitaryUrinalQuantity(dsrBuildingBean.getSanitaryUrinalQuantity());
				dsrBuildingForm.setSanitaryWashbasinCost(dsrBuildingBean.getSanitaryWashbasinCost());
				dsrBuildingForm.setSchemeId(dsrBuildingBean.getSchemeId());
				dsrBuildingForm.setSewerageCost(dsrBuildingBean.getSewerageCost());
				dsrBuildingForm.setSewerageLength(dsrBuildingBean.getSewerageLength());
				dsrBuildingForm.setSplitacCost(dsrBuildingBean.getSplitacCost());
				dsrBuildingForm.setSplitacQuantity(dsrBuildingBean.getSplitacQuantity());
				dsrBuildingForm.setStormSewerageCost(dsrBuildingBean.getStormSewerageCost());
				dsrBuildingForm.setStormSewerageLength(dsrBuildingBean.getStormSewerageLength());
				dsrBuildingForm.setTotalCost(dsrBuildingBean.getTotalCost());
			}
		}else{
			refreshDSRBuildingForm(dsrBuildingForm);
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
		DSRBuildingForm dsrBuildingForm = (DSRBuildingForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = dsrBuildingBO.deleteDSRBuilding(dsrBuildingForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","DSR Information ","Scheme ID -->"+dsrBuildingForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","DSR Information ","Scheme ID -->"+dsrBuildingForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshDSRBuildingForm(dsrBuildingForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		DSRBuildingForm dsrBuildingForm = (DSRBuildingForm)form;
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

			status = dsrBuildingBO.saveDSRBuilding(dsrBuildingForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "DSR Information ","Scheme ID -->"+dsrBuildingForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "DSR Information");
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
				ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		DSRBuildingForm dsrBuildingForm = (DSRBuildingForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = dsrBuildingBO.updateDSRBuilding(dsrBuildingForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update",  "DSR Information ","Scheme ID -->"+dsrBuildingForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "DSR Information ","Scheme ID -->"+dsrBuildingForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshDSRBuildingForm(dsrBuildingForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("save:"+request.getParameter("d__mode"));
		this.setAttrib(request);
		DSRBuildingForm dsrBuildingForm = (DSRBuildingForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = dsrBuildingBO.postDSRBuilding(dsrBuildingForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","DSR Information","Scheme ID -->"+dsrBuildingForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","DSR Information","Scheme ID -->"+dsrBuildingForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
			refreshDSRBuildingForm(dsrBuildingForm);
		
		return mapping.findForward("display");
	}
	
	
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "villageId@locationId@schemeId@blockId");
		
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		DSRBuildingForm dsrBuildingForm = (DSRBuildingForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		refreshDSRBuildingForm(dsrBuildingForm);
		return mapping.findForward("display");
	}

	private void refreshDSRBuildingForm(DSRBuildingForm dsrBuildingForm){
	
		dsrBuildingForm.setAcplantCost(0);
		dsrBuildingForm.setContigencyCharges(0);
		dsrBuildingForm.setDepartmentalCharges(0);
		dsrBuildingForm.setEstatePipelineCost(0);
		dsrBuildingForm.setEstatePipelineLength(0);
		dsrBuildingForm.setFiresystemCost(0);
		dsrBuildingForm.setHeadworksCost(0);
		dsrBuildingForm.setHeadworksDischarge(null);
		dsrBuildingForm.setHeadworksHead(null);
		dsrBuildingForm.setHeadworksPumpchamberCost(0);
		dsrBuildingForm.setInternalPipelineCost(0);
		dsrBuildingForm.setInternalPipelineLength(null);
		dsrBuildingForm.setLocationId(null);
		dsrBuildingForm.setOhsrCapacity(null);
		dsrBuildingForm.setOhsrCost(0);
		dsrBuildingForm.setRainwaterCost(0);
		dsrBuildingForm.setSanitaryOwcEwcCost(0);
		dsrBuildingForm.setSanitaryOwcEwcQuantity(0);
		dsrBuildingForm.setSanitaryUrinalCost(0);
		dsrBuildingForm.setSanitaryUrinalQuantity(0);
		dsrBuildingForm.setSanitaryWashbasinCost(0);
		dsrBuildingForm.setSchemeId(null);
		dsrBuildingForm.setSewerageCost(0);
		dsrBuildingForm.setSewerageLength(null);
		dsrBuildingForm.setSplitacCost(0);
		dsrBuildingForm.setSplitacQuantity(0);
		dsrBuildingForm.setStormSewerageCost(0);
		dsrBuildingForm.setStormSewerageLength(null);
		dsrBuildingForm.setTotalCost(new BigDecimal(0.00));
		dsrBuildingForm.setDsrDate(null);
	}
}
