package com.prwss.mis.pmm.dsrtubewell.struts;

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
import com.prwss.mis.pmm.dsrcanal.DSRCanalBean;
import com.prwss.mis.pmm.dsrtubewell.DSRTubeWellBO;
import com.prwss.mis.pmm.dsrtubewell.DSRTubeWellBean;
import com.prwss.mis.pmm.dsrtubewell.dao.DSRTubeWellDistributionBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class DSRTubeWellAction extends DispatchAction {

	Logger log = Logger.getLogger(DSRTubeWellAction.class);
	private MISSessionBean misSessionBean;
	private DSRTubeWellBO dsrTubeWellBO; 

	public void setDsrTubeWellBO(DSRTubeWellBO dsrTubeWellBO) {
		this.dsrTubeWellBO = dsrTubeWellBO;
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
		DSRTubeWellForm dsrTubeWellForm = (DSRTubeWellForm)form;
		List<DSRTubeWellBean> dsrTubeWellBeans = null;
		dsrTubeWellBeans = dsrTubeWellBO.findDSRTubeWell(dsrTubeWellForm, statusList);
		String villageId = dsrTubeWellForm.getVillageId();
		String blockId = dsrTubeWellForm.getBlockId();
		if(!MisUtility.ifEmpty(dsrTubeWellBeans)){
			request.setAttribute("level2", "true");
			for (DSRTubeWellBean dsrTubeWellBean : dsrTubeWellBeans) {
				request.setAttribute("villageId", villageId);
				request.setAttribute("blockId", blockId);
				request.setAttribute("locationId", dsrTubeWellBean.getLocationId());
				request.setAttribute("schemeId", dsrTubeWellBean.getSchemeId());
				dsrTubeWellForm.setBulkCount(dsrTubeWellBean.getBulkCount());
				dsrTubeWellForm.setSchemeId(dsrTubeWellBean.getSchemeId());
				dsrTubeWellForm.setLocationId(dsrTubeWellBean.getLocationId());
				dsrTubeWellForm.setTubewellSize(dsrTubeWellBean.getTubewellSize());
				dsrTubeWellForm.setTubewellDepth(dsrTubeWellBean.getTubewellDepth());
				dsrTubeWellForm.setTubewellCost(dsrTubeWellBean.getTubewellCost());
				dsrTubeWellForm.setPumpSelect(dsrTubeWellBean.getPumpSelect());
				dsrTubeWellForm.setPumpQuantity(dsrTubeWellBean.getPumpQuantity());
				dsrTubeWellForm.setPumpTotalCost(dsrTubeWellBean.getPumpTotalCost());
				dsrTubeWellForm.setPumpingCentrifugal(dsrTubeWellBean.getPumpingCentrifugal());
				dsrTubeWellForm.setPumpingDischarge(dsrTubeWellBean.getPumpingDischarge());
				dsrTubeWellForm.setPumpingSubmersible(dsrTubeWellBean.getPumpingSubmersible());
				dsrTubeWellForm.setPumpingQuantity(dsrTubeWellBean.getPumpingQuantity());
				dsrTubeWellForm.setPumpingHead(dsrTubeWellBean.getPumpingHead());
				dsrTubeWellForm.setPumpingCost(dsrTubeWellBean.getPumpingCost());
				dsrTubeWellForm.setCostOfElectricConnection(dsrTubeWellBean.getCostOfElectricConnection());
				dsrTubeWellForm.setCostOfCIFitting(dsrTubeWellBean.getCostOfCIFitting());
				dsrTubeWellForm.setVnotchQuantity(dsrTubeWellBean.getVnotchQuantity());
				dsrTubeWellForm.setVnotchCost(dsrTubeWellBean.getVnotchCost());
				dsrTubeWellForm.setDisinfectionType(dsrTubeWellBean.getDisinfectionType());
				dsrTubeWellForm.setDisinfectionQuantity(dsrTubeWellBean.getDisinfectionQuantity());
				dsrTubeWellForm.setDisinfectionCost(dsrTubeWellBean.getDisinfectionCost());
				dsrTubeWellForm.setRccQuantity(dsrTubeWellBean.getRccQuantity());
				dsrTubeWellForm.setRccCapacity(dsrTubeWellBean.getRccCapacity());
				dsrTubeWellForm.setRccFsl(dsrTubeWellBean.getRccFsl());
				dsrTubeWellForm.setRccCost(dsrTubeWellBean.getRccCost());
				dsrTubeWellForm.setElectronicCost(dsrTubeWellBean.getElectronicCost());
				dsrTubeWellForm.setElectronicQuantity(dsrTubeWellBean.getElectronicQuantity());
				dsrTubeWellForm.setPlinthCost(dsrTubeWellBean.getPlinthCost());
				dsrTubeWellForm.setRisingSize(dsrTubeWellBean.getRisingSize());
				dsrTubeWellForm.setRisingLength(dsrTubeWellBean.getRisingLength());
				dsrTubeWellForm.setRisingCost(dsrTubeWellBean.getRisingCost());
				dsrTubeWellForm.setRisingType(dsrTubeWellBean.getRisingType());
				dsrTubeWellForm.setTotalCostTopographical(dsrTubeWellBean.getTotalCostTopographical());
				dsrTubeWellForm.setTotalCostDevelopmentWater(dsrTubeWellBean.getTotalCostDevelopmentWater());
				dsrTubeWellForm.setBulkSize(dsrTubeWellBean.getBulkSize());
				dsrTubeWellForm.setBulkCount(dsrTubeWellBean.getBulkCount());
				dsrTubeWellForm.setBulkTotalCost(dsrTubeWellBean.getBulkTotalCost());
				dsrTubeWellForm.setDistributionTotalCost(dsrTubeWellBean.getDistributionTotalCost());
				dsrTubeWellForm.setSlueiceSize(dsrTubeWellBean.getSlueiceSize());
				dsrTubeWellForm.setTotalCostStructure(dsrTubeWellBean.getTotalCostStructure());
				dsrTubeWellForm.setPercapitaCost(dsrTubeWellBean.getPercapitaCost());
				dsrTubeWellForm.setEnvironmentCost(dsrTubeWellBean.getEnvironmentCost());
				dsrTubeWellForm.setGrandTotal(dsrTubeWellBean.getGrandTotal());
				dsrTubeWellForm.setDsrDate(MisUtility.convertDateToString(dsrTubeWellBean.getDsrDate()));
				dsrTubeWellForm.setTubewellSize1(dsrTubeWellBean.getTubewellSize1());
				dsrTubeWellForm.setTubewellDepth1(dsrTubeWellBean.getTubewellDepth1());
				dsrTubeWellForm.setTubewellCost1(dsrTubeWellBean.getTubewellCost1());
				dsrTubeWellForm.setPumpingCentrifugal1(dsrTubeWellBean.getPumpingCentrifugal1());
				dsrTubeWellForm.setPumpingDischarge1(dsrTubeWellBean.getPumpingDischarge1());
				dsrTubeWellForm.setPumpingSubmersible1(dsrTubeWellBean.getPumpingSubmersible1());
				dsrTubeWellForm.setPumpingQuantity1(dsrTubeWellBean.getPumpingQuantity1());
				dsrTubeWellForm.setPumpingHead1(dsrTubeWellBean.getPumpingHead1());
				dsrTubeWellForm.setPumpingCost1(dsrTubeWellBean.getPumpingCost1());
				dsrTubeWellForm.setCostOfElectricConnection1(dsrTubeWellBean.getCostOfElectricConnection1());
				dsrTubeWellForm.setCostOfCIFitting1(dsrTubeWellBean.getCostOfCIFitting1());
				dsrTubeWellForm.setVnotchQuantity1(dsrTubeWellBean.getVnotchQuantity1());
				dsrTubeWellForm.setVnotchCost1(dsrTubeWellBean.getVnotchCost1());
				dsrTubeWellForm.setRccQuantity1(dsrTubeWellBean.getRccQuantity1());
				dsrTubeWellForm.setRccCapacity1(dsrTubeWellBean.getRccCapacity1());
				dsrTubeWellForm.setRccFsl1(dsrTubeWellBean.getRccFsl1());
				dsrTubeWellForm.setRccCost1(dsrTubeWellBean.getRccCost1());
				dsrTubeWellForm.setElectronicCost1(dsrTubeWellBean.getElectronicCost1());
				dsrTubeWellForm.setPlinthCost1(dsrTubeWellBean.getPlinthCost1());
				dsrTubeWellForm.setElectronicQuantity1(dsrTubeWellBean.getElectronicQuantity1());
				dsrTubeWellForm.setRisingSize1(dsrTubeWellBean.getRisingSize1());
				dsrTubeWellForm.setRisingLength1(dsrTubeWellBean.getRisingLength1());
				dsrTubeWellForm.setRisingCost1(dsrTubeWellBean.getRisingCost1());
				dsrTubeWellForm.setRisingType1(dsrTubeWellBean.getRisingType1());
				dsrTubeWellForm.setTotalCostTopographical1(dsrTubeWellBean.getTotalCostTopographical1());
				dsrTubeWellForm.setTotalCostDevelopmentWater1(dsrTubeWellBean.getTotalCostDevelopmentWater1());
				dsrTubeWellForm.setDsrTubeWellDistributionGrid(getDSRTubeWellDistributionGrid(dsrTubeWellBean.getDsrTubeWellDistributionBeans()));
			}
		}else{
			refreshDSRTubeWellForm(dsrTubeWellForm);
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
		DSRTubeWellForm dsrTubeWellForm = (DSRTubeWellForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = dsrTubeWellBO.deleteDSRTubeWell(dsrTubeWellForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","DSR Information ","Scheme ID -->"+dsrTubeWellForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","DSR Information ","Scheme ID -->"+dsrTubeWellForm.getSchemeId());
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
		refreshDSRTubeWellForm(dsrTubeWellForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		DSRTubeWellForm dsrTubeWellForm = (DSRTubeWellForm)form;
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
			DSRTubeWellBean bean = dsrTubeWellBO.validateDSRTubeWellBean(dsrTubeWellForm);
			if(bean!=null){
				System.out.println("------------Error occured Scheme ID Exists++++++++++");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.alreadyExist", "DSR Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else {
				status = dsrTubeWellBO.saveDSRTubeWell(dsrTubeWellForm, misSessionBean);
				if (status){
					ActionMessages errors = new ActionMessages();
					ActionMessage message = new ActionMessage("success.save", "DSR Information ","Scheme ID -->"+dsrTubeWellForm.getSchemeId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, errors);
	
				} else {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", "DSR Information");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
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
		refreshDSRTubeWellForm(dsrTubeWellForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		DSRTubeWellForm dsrTubeWellForm = (DSRTubeWellForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = dsrTubeWellBO.updateDSRTubeWell(dsrTubeWellForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update",  "DSR Information ","Scheme ID -->"+dsrTubeWellForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "DSR Information ","Scheme ID -->"+dsrTubeWellForm.getSchemeId());
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
		refreshDSRTubeWellForm(dsrTubeWellForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("save:"+request.getParameter("d__mode"));
		this.setAttrib(request);
		DSRTubeWellForm dsrTubeWellForm = (DSRTubeWellForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = dsrTubeWellBO.postDSRTubeWell(dsrTubeWellForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","DSR Information","Scheme ID -->"+dsrTubeWellForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","DSR Information","Scheme ID -->"+dsrTubeWellForm.getSchemeId());
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
		
		refreshDSRTubeWellForm(dsrTubeWellForm);
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
		DSRTubeWellForm dsrTubeWellForm = (DSRTubeWellForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		refreshDSRTubeWellForm(dsrTubeWellForm);
		return mapping.findForward("display");
	}
	private Datagrid getDSRTubeWellDistributionGrid(Set<DSRTubeWellDistributionBean> dsrTubeWellDistributionBeans) {
		Datagrid dsrTubeWellDistributionGrid = null;
		try {
			dsrTubeWellDistributionGrid = Datagrid.getInstance();
			dsrTubeWellDistributionGrid.setDataClass(DSRTubeWellDistributionBean.class);
			if(!MisUtility.ifEmpty(dsrTubeWellDistributionBeans)){
			List<DSRTubeWellDistributionBean> dsrTubeWellDistributionBeans2 = new ArrayList<DSRTubeWellDistributionBean>(dsrTubeWellDistributionBeans);
			dsrTubeWellDistributionGrid.setData(dsrTubeWellDistributionBeans2);		
			}else{
			List<DSRTubeWellDistributionBean> dsrTubeWellDistributionBeans2 = new ArrayList<DSRTubeWellDistributionBean>();
			dsrTubeWellDistributionGrid.setData(dsrTubeWellDistributionBeans2);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return dsrTubeWellDistributionGrid;
	}

	private void refreshDSRTubeWellForm(DSRTubeWellForm dsrTubeWellForm){
		dsrTubeWellForm.setBulkCount(0);
		dsrTubeWellForm.setSchemeId(null);
		dsrTubeWellForm.setLocationId(null);
		dsrTubeWellForm.setTubewellSize(null);
		dsrTubeWellForm.setTubewellDepth(0);
		dsrTubeWellForm.setTubewellCost(0);
		dsrTubeWellForm.setPumpSelect(null);
		dsrTubeWellForm.setPumpQuantity(0);
		dsrTubeWellForm.setPumpTotalCost(0);
		dsrTubeWellForm.setPumpingCentrifugal(null);
		dsrTubeWellForm.setPumpingDischarge(0);
		dsrTubeWellForm.setPumpingSubmersible(null);
		dsrTubeWellForm.setPumpingQuantity(0);
		dsrTubeWellForm.setPumpingHead(0);
		dsrTubeWellForm.setPumpingCost(new BigDecimal(0.00));
		dsrTubeWellForm.setCostOfElectricConnection(new BigDecimal(0.00));
		dsrTubeWellForm.setCostOfCIFitting(new BigDecimal(0.00));
		dsrTubeWellForm.setVnotchQuantity(0);
		dsrTubeWellForm.setVnotchCost(0);
		dsrTubeWellForm.setDisinfectionType(null);
		dsrTubeWellForm.setDisinfectionQuantity(null);
		dsrTubeWellForm.setDisinfectionCost(0);
		dsrTubeWellForm.setRccQuantity(0);
		dsrTubeWellForm.setRccCapacity(null);
		dsrTubeWellForm.setRccFsl(0);
		dsrTubeWellForm.setRccCost(0);
		dsrTubeWellForm.setElectronicCost(0);
		dsrTubeWellForm.setPlinthCost(0);
		dsrTubeWellForm.setRisingSize(null);
		dsrTubeWellForm.setRisingLength(0);
		dsrTubeWellForm.setRisingCost(0);
		dsrTubeWellForm.setRisingType(null);
		dsrTubeWellForm.setTotalCostTopographical(new BigDecimal(0.00));
		dsrTubeWellForm.setTotalCostDevelopmentWater(new BigDecimal(0.00));
		dsrTubeWellForm.setBulkSize(null);
		dsrTubeWellForm.setBulkCount(0);
		dsrTubeWellForm.setBulkTotalCost(new BigDecimal(0.00));
		dsrTubeWellForm.setDistributionTotalCost(new BigDecimal(0.00));
		dsrTubeWellForm.setSlueiceSize(null);
		dsrTubeWellForm.setTotalCostStructure(new BigDecimal(0.00));
		dsrTubeWellForm.setPercapitaCost(new BigDecimal(0.00));
		dsrTubeWellForm.setEnvironmentCost(new BigDecimal(0.00));
		dsrTubeWellForm.setGrandTotal(new BigDecimal(0.00));
		dsrTubeWellForm.setDsrTubeWellDistributionGrid(getDSRTubeWellDistributionGrid(null));
		dsrTubeWellForm.setSlueiceCost(new BigDecimal(0.00));
		dsrTubeWellForm.setDsrDate(null);
		dsrTubeWellForm.setTubewellSize1(null);
		dsrTubeWellForm.setTubewellDepth1(0);
		dsrTubeWellForm.setTubewellCost1(0);
		dsrTubeWellForm.setPumpingCentrifugal1(null);
		dsrTubeWellForm.setPumpingDischarge1(0);
		dsrTubeWellForm.setPumpingSubmersible1(null);
		dsrTubeWellForm.setPumpingQuantity1(0);
		dsrTubeWellForm.setPumpingHead1(0);
		dsrTubeWellForm.setPumpingCost1(new BigDecimal(0.00));
		dsrTubeWellForm.setCostOfElectricConnection1(new BigDecimal(0.00));
		dsrTubeWellForm.setCostOfCIFitting1(new BigDecimal(0.00));
		dsrTubeWellForm.setVnotchQuantity1(0);
		dsrTubeWellForm.setVnotchCost1(0);
		dsrTubeWellForm.setRccQuantity1(0);
		dsrTubeWellForm.setRccCapacity1(null);
		dsrTubeWellForm.setRccFsl1(0);
		dsrTubeWellForm.setRccCost1(0);
		dsrTubeWellForm.setElectronicCost1(0);
		dsrTubeWellForm.setPlinthCost1(0);
		dsrTubeWellForm.setElectronicQuantity1(0);
		dsrTubeWellForm.setRisingSize1(null);
		dsrTubeWellForm.setRisingLength1(0);
		dsrTubeWellForm.setRisingCost1(0);
		dsrTubeWellForm.setRisingType1(null);
		dsrTubeWellForm.setTotalCostTopographical1(new BigDecimal(0.00));
		dsrTubeWellForm.setTotalCostDevelopmentWater1(new BigDecimal(0.00));
	}
}
