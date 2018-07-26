package com.prwss.mis.pmm.dsrsewerage.struts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.prwss.mis.pmm.dsrsewerage.DSRSewerageBO;
import com.prwss.mis.pmm.dsrsewerage.DSRSewerageBean;
import com.prwss.mis.pmm.dsrsewerage.dao.DSRSewerageSewerBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class DSRSewerageAction extends DispatchAction {
	Logger log = Logger.getLogger(DSRSewerageAction.class);
	private MISSessionBean misSessionBean;
	private DSRSewerageBO dsrSewerageBO;
	
	

	public void setDsrSewerageBO(DSRSewerageBO dsrSewerageBO) {
		this.dsrSewerageBO = dsrSewerageBO;
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
		DSRSewerageForm dsrSewerageForm = (DSRSewerageForm)form;
		List<DSRSewerageBean> dsrSewerageBeans = null;
		dsrSewerageBeans = dsrSewerageBO.findDSRSewerage(dsrSewerageForm, statusList);
		String villageId = dsrSewerageForm.getVillageId();
		String blockId = dsrSewerageForm.getBlockId();
		if(!MisUtility.ifEmpty(dsrSewerageBeans)){
			request.setAttribute("level2", "true");
			for (DSRSewerageBean dsrSewerageBean : dsrSewerageBeans) {
				request.setAttribute("villageId", villageId);
				request.setAttribute("blockId", blockId);
				request.setAttribute("locationId", dsrSewerageBean.getLocationId());
				request.setAttribute("schemeId", dsrSewerageBean.getSchemeId());
				dsrSewerageForm.setCollectionIpsCost(dsrSewerageBean.getCollectionIpsCost());
				dsrSewerageForm.setCollectionIpsDimeter(dsrSewerageBean.getCollectionIpsDimeter());
				dsrSewerageForm.setCollectionIpsQuantity(dsrSewerageBean.getCollectionIpsQuantity());
				dsrSewerageForm.setCollectionStpCost(dsrSewerageBean.getCollectionStpCost());
				dsrSewerageForm.setCollectionStpDiameter(dsrSewerageBean.getCollectionStpDiameter());
				dsrSewerageForm.setCompositionCost(dsrSewerageBean.getCompositionCost());
				dsrSewerageForm.setContigencyCharges(dsrSewerageBean.getContigencyCharges());
				dsrSewerageForm.setEnvironmentCost(dsrSewerageBean.getEnvironmentCost());
				dsrSewerageForm.setGensetCapacity(dsrSewerageBean.getGensetCapacity());
				dsrSewerageForm.setGensetElectricConnectionCost(dsrSewerageBean.getGensetElectricConnectionCost());
				dsrSewerageForm.setGensetElectricConnectionLoad(dsrSewerageBean.getGensetElectricConnectionLoad());
				dsrSewerageForm.setGensetPannelCost(dsrSewerageBean.getGensetPannelCost());
				dsrSewerageForm.setGensetPannelQuantity(dsrSewerageBean.getGensetPannelQuantity());
				dsrSewerageForm.setGensetQuantity(dsrSewerageBean.getGensetQuantity());
				dsrSewerageForm.setGrandTotal(dsrSewerageBean.getGrandTotal());
				dsrSewerageForm.setLocationId(dsrSewerageBean.getLocationId());
				dsrSewerageForm.setMachineryCost(dsrSewerageBean.getMachineryCost());
				dsrSewerageForm.setMachineryDischarge(dsrSewerageBean.getMachineryDischarge());
				dsrSewerageForm.setMachineryHead(dsrSewerageBean.getMachineryHead());
				dsrSewerageForm.setMachineryQuantity(dsrSewerageBean.getMachineryQuantity());
				dsrSewerageForm.setMachineryType(dsrSewerageBean.getMachineryType());
				dsrSewerageForm.setManholeDepthMax(dsrSewerageBean.getManholeDepthMax());
				dsrSewerageForm.setManholeDepthMin(dsrSewerageBean.getManholeDepthMin());
				dsrSewerageForm.setManholeQuantity(dsrSewerageBean.getManholeQuantity());
				dsrSewerageForm.setManholeSize(dsrSewerageBean.getManholeSize());
				dsrSewerageForm.setOMcostForSevenYears(dsrSewerageBean.getOMcostForSevenYears());
				dsrSewerageForm.setPumpingCost(dsrSewerageBean.getPumpingCost());
				dsrSewerageForm.setPumpingDischarge(dsrSewerageBean.getPumpingDischarge());
				dsrSewerageForm.setPumpingHead(dsrSewerageBean.getPumpingHead());
				dsrSewerageForm.setPumpingMachineryDischarge(dsrSewerageBean.getPumpingMachineryDischarge());
				dsrSewerageForm.setRestroomCost(dsrSewerageBean.getRestroomCost());
				dsrSewerageForm.setRestroomQuantity(dsrSewerageBean.getRestroomQuantity());
				dsrSewerageForm.setRisingCost(dsrSewerageBean.getRisingCost());
				dsrSewerageForm.setRisingSize(dsrSewerageBean.getRisingSize());
				dsrSewerageForm.setRisingType(dsrSewerageBean.getRisingType());
				dsrSewerageForm.setSchemeId(dsrSewerageBean.getSchemeId());
				dsrSewerageForm.setSewerageCost(dsrSewerageBean.getSewerageCost());
				dsrSewerageForm.setSewerageTechnology(dsrSewerageBean.getSewerageTechnology());
				dsrSewerageForm.setSiteDevelopmentCost(dsrSewerageBean.getSiteDevelopmentCost());
				dsrSewerageForm.setSludgeCuringCost(dsrSewerageBean.getSludgeCuringCost());
				dsrSewerageForm.setSludgeDryingCost(dsrSewerageBean.getSludgeDryingCost());
				dsrSewerageForm.setTotalCostOfStructure(dsrSewerageBean.getTotalCostOfStructure());
				dsrSewerageForm.setTotalCostPipeSewer(dsrSewerageBean.getTotalCostPipeSewer());
				dsrSewerageForm.setSewerageSystemDatagrid(getSewerageSystemDatagrid(dsrSewerageBean.getDsrSewerageSewerBeans()));
				dsrSewerageForm.setDsrDate(MisUtility.convertDateToString(dsrSewerageBean.getDsrDate()));
			}
		}else{
			refreshDSRSewerageForm(dsrSewerageForm);
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
		DSRSewerageForm dsrSewerageForm = (DSRSewerageForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = dsrSewerageBO.deleteDSRSewerage(dsrSewerageForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","DSR Information ","Scheme ID -->"+dsrSewerageForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","DSR Information ","Scheme ID -->"+dsrSewerageForm.getSchemeId());
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
		refreshDSRSewerageForm(dsrSewerageForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		DSRSewerageForm dsrSewerageForm = (DSRSewerageForm)form;
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

			status = dsrSewerageBO.saveDSRSewerage(dsrSewerageForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "DSR Information ","Scheme ID -->"+dsrSewerageForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "DSR Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","DSR Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
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
		refreshDSRSewerageForm(dsrSewerageForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		DSRSewerageForm dsrSewerageForm = (DSRSewerageForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = dsrSewerageBO.updateDSRSewerage(dsrSewerageForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update",  "DSR Information ","Scheme ID -->"+dsrSewerageForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "DSR Information ","Scheme ID -->"+dsrSewerageForm.getSchemeId());
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
		refreshDSRSewerageForm(dsrSewerageForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("save:"+request.getParameter("d__mode"));
		this.setAttrib(request);
		DSRSewerageForm dsrSewerageForm = (DSRSewerageForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = dsrSewerageBO.postDSRSewerage(dsrSewerageForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","DSR Information","Scheme ID -->"+dsrSewerageForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","DSR Information","Scheme ID -->"+dsrSewerageForm.getSchemeId());
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
		
		refreshDSRSewerageForm(dsrSewerageForm);
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
		DSRSewerageForm dsrSewerageForm = (DSRSewerageForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		refreshDSRSewerageForm(dsrSewerageForm);
		return mapping.findForward("display");
	}
	private Datagrid getSewerageSystemDatagrid(Set<DSRSewerageSewerBean> dsrSewerageSewerBeans) {
		Datagrid sewerageSystemDatagrid = null;
		try {
			sewerageSystemDatagrid = Datagrid.getInstance();
			sewerageSystemDatagrid.setDataClass(DSRSewerageSewerBean.class);
			if(!MisUtility.ifEmpty(dsrSewerageSewerBeans)){
			List<DSRSewerageSewerBean> dsrSewerageSewerBeans2 = new ArrayList<DSRSewerageSewerBean>(dsrSewerageSewerBeans);
			sewerageSystemDatagrid.setData(dsrSewerageSewerBeans2);		
			}else{
			List<DSRSewerageSewerBean> dsrSewerageSewerBeans2 = new ArrayList<DSRSewerageSewerBean>();
			sewerageSystemDatagrid.setData(dsrSewerageSewerBeans2);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return sewerageSystemDatagrid;
	}

	private void refreshDSRSewerageForm(DSRSewerageForm dsrSewerageForm){
		
		dsrSewerageForm.setCollectionIpsCost(0);
		dsrSewerageForm.setCollectionIpsDimeter(0);
		dsrSewerageForm.setCollectionIpsQuantity(0);
		dsrSewerageForm.setCollectionStpCost(0);
		dsrSewerageForm.setCollectionStpDiameter(0);
		dsrSewerageForm.setCompositionCost(0);
		dsrSewerageForm.setContigencyCharges(new BigDecimal(0.0));
		dsrSewerageForm.setEnvironmentCost(new BigDecimal(0.0));
		dsrSewerageForm.setGensetCapacity(0);
		dsrSewerageForm.setGensetElectricConnectionCost(0);
		dsrSewerageForm.setGensetElectricConnectionLoad(null);
		dsrSewerageForm.setGensetPannelCost(0);
		dsrSewerageForm.setGensetPannelQuantity(0);
		dsrSewerageForm.setGensetQuantity(0);
		dsrSewerageForm.setGrandTotal(new BigDecimal(0.0));
		dsrSewerageForm.setLocationId(null);
		dsrSewerageForm.setMachineryCost(0);
		dsrSewerageForm.setMachineryDischarge(null);
		dsrSewerageForm.setMachineryHead(0);
		dsrSewerageForm.setMachineryQuantity(0);
		dsrSewerageForm.setMachineryType(null);
		dsrSewerageForm.setManholeDepthMax(0);
		dsrSewerageForm.setManholeDepthMin(0);
		dsrSewerageForm.setManholeQuantity(0);
		dsrSewerageForm.setManholeSize(null);
		dsrSewerageForm.setOMcostForSevenYears(new BigDecimal(0.0));
		dsrSewerageForm.setPumpingCost(0);
		dsrSewerageForm.setPumpingDischarge(null);
		dsrSewerageForm.setPumpingHead(null);
		dsrSewerageForm.setPumpingMachineryDischarge(null);
		dsrSewerageForm.setRestroomCost(0);
		dsrSewerageForm.setRestroomQuantity(0);
		dsrSewerageForm.setRisingCost(0);
		dsrSewerageForm.setRisingSize(null);
		dsrSewerageForm.setRisingType(null);
		dsrSewerageForm.setSchemeId(null);
		dsrSewerageForm.setSewerageCost(0);
		dsrSewerageForm.setSewerageTechnology(null);
		dsrSewerageForm.setSiteDevelopmentCost(0);
		dsrSewerageForm.setSludgeCuringCost(0);
		dsrSewerageForm.setSludgeDryingCost(0);
		dsrSewerageForm.setTotalCostOfStructure(new BigDecimal(0.0));
		dsrSewerageForm.setTotalCostPipeSewer(0);
		dsrSewerageForm.setSewerageSystemDatagrid(getSewerageSystemDatagrid(null));
		dsrSewerageForm.setDsrDate(null);
		dsrSewerageForm.setVillageId(null);
		
	}
}
