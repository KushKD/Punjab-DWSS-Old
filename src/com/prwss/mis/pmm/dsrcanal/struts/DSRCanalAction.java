package com.prwss.mis.pmm.dsrcanal.struts;

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
import com.prwss.mis.pmm.dsrcanal.DSRCanalBO;
import com.prwss.mis.pmm.dsrcanal.DSRCanalBean;
import com.prwss.mis.pmm.dsrcanal.dao.DSRCanalDistributionBean;
import com.prwss.mis.pmm.dsrcanal.dao.DSRCanalInletBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class DSRCanalAction extends DispatchAction {
	Logger log = Logger.getLogger(DSRCanalAction.class);
	
	private MISSessionBean misSessionBean;
	private DSRCanalBO dsrCanalBO;
	
	

	public void setDsrCanalBO(DSRCanalBO dsrCanalBO) {
		this.dsrCanalBO = dsrCanalBO;
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
		DSRCanalForm dsrCanalForm = (DSRCanalForm)form;
		List<DSRCanalBean> dsrCanalBeans = null;
		dsrCanalBeans = dsrCanalBO.findDSRCanal(dsrCanalForm, statusList);
		String villageId = dsrCanalForm.getVillageId();
		String blockId = dsrCanalForm.getBlockId();
		if(!MisUtility.ifEmpty(dsrCanalBeans)){
			request.setAttribute("level2", "true");
			for (DSRCanalBean dsrCanalBean : dsrCanalBeans) {
				request.setAttribute("villageId", villageId);
				request.setAttribute("blockId", blockId);
				request.setAttribute("locationId", dsrCanalBean.getLocationId());
				request.setAttribute("schemeId", dsrCanalBean.getSchemeId());
				dsrCanalForm.setDsrDate(MisUtility.convertDateToString(dsrCanalBean.getDsrDate()));
				dsrCanalForm.setBulkCount(dsrCanalBean.getBulkCount());
				dsrCanalForm.setBulkSize(dsrCanalBean.getBulkSize());
				dsrCanalForm.setBulkTotalCost(dsrCanalBean.getBulkTotalCost());
				dsrCanalForm.setClearCost(dsrCanalBean.getClearCost());
				dsrCanalForm.setClearQuantity(dsrCanalBean.getClearQuantity());
				dsrCanalForm.setClearSize(dsrCanalBean.getClearSize());
				dsrCanalForm.setClearType(dsrCanalBean.getClearType());
				dsrCanalForm.setContigencyCharges(dsrCanalBean.getContigencyCharges());
				dsrCanalForm.setCostOfElectricConnectionProvision(dsrCanalBean.getCostOfElectricConnectionProvision());
				dsrCanalForm.setDisinfectionCost(dsrCanalBean.getDisinfectionCost());
				dsrCanalForm.setDisinfectionQuantity(dsrCanalBean.getDisinfectionQuantity());
				dsrCanalForm.setDisinfectionType(dsrCanalBean.getDisinfectionType());
				dsrCanalForm.setDistributionCost(dsrCanalBean.getDistributionCost());
				dsrCanalForm.setElectronicCost(dsrCanalBean.getElectronicCost());
				dsrCanalForm.setElectronicQuantity(dsrCanalBean.getElectronicQuantity());
				dsrCanalForm.setEnvironmentCost(dsrCanalBean.getEnvironmentCost());
				dsrCanalForm.setFilterCost(dsrCanalBean.getFilterCost());
				dsrCanalForm.setFilterQuantity(dsrCanalBean.getFilterQuantity());
				dsrCanalForm.setFilterSize(dsrCanalBean.getFilterSize());
				dsrCanalForm.setFilterType(dsrCanalBean.getFilterType());
				dsrCanalForm.setGrandTotal(dsrCanalBean.getGrandTotal());
				dsrCanalForm.setHighCost(dsrCanalBean.getHighCost());
				dsrCanalForm.setHighQuantity(dsrCanalBean.getHighQuantity());
				dsrCanalForm.setHighSize(dsrCanalBean.getHighSize());
				dsrCanalForm.setHighType(dsrCanalBean.getHighType());
				dsrCanalForm.setInletTotalCost(dsrCanalBean.getInletTotalCost());
				dsrCanalForm.setLocationId(dsrCanalBean.getLocationId());
				dsrCanalForm.setPercapitaCost(dsrCanalBean.getPercapitaCost());
				dsrCanalForm.setPiplineCost(dsrCanalBean.getPiplineCost());
				dsrCanalForm.setPiplineLength(dsrCanalBean.getPiplineLength());
				dsrCanalForm.setPiplineSize(dsrCanalBean.getPiplineSize());
				dsrCanalForm.setPiplineType(dsrCanalBean.getPiplineType());
				dsrCanalForm.setPlinthCost(dsrCanalBean.getPlinthCost());
				dsrCanalForm.setPumpCost(dsrCanalBean.getPumpCost());
				dsrCanalForm.setPumpingCentrifugal(dsrCanalBean.getPumpingCentrifugal());
				dsrCanalForm.setPumpingCost(dsrCanalBean.getPumpingCost());
				dsrCanalForm.setPumpingDischarge(dsrCanalBean.getPumpingDischarge());
				dsrCanalForm.setPumpingHead(dsrCanalBean.getPumpingHead());
				dsrCanalForm.setPumpingMachineryApplicable(dsrCanalBean.isPumpingMachineryApplicable());
				dsrCanalForm.setPumpingMachineryCost(dsrCanalBean.getPumpingMachineryCost());
				dsrCanalForm.setPumpingMachineryDischarge(dsrCanalBean.getPumpingMachineryDischarge());
				dsrCanalForm.setPumpingMachinerySize(dsrCanalBean.getPumpingMachinerySize());
				dsrCanalForm.setPumpingPower(dsrCanalBean.getPumpingPower());
				dsrCanalForm.setPumpingQuantity(dsrCanalBean.getPumpingQuantity());
				dsrCanalForm.setPumpingSubmersible(dsrCanalBean.getPumpingSubmersible());
				dsrCanalForm.setPumpQuantity(dsrCanalBean.getPumpQuantity());
				dsrCanalForm.setPumpSelect(dsrCanalBean.getPumpSelect());
				dsrCanalForm.setRccCapacity(dsrCanalBean.getRccCapacity());
				dsrCanalForm.setRccCost(dsrCanalBean.getRccCost());
				dsrCanalForm.setRccFsl(dsrCanalBean.getRccFsl());
				dsrCanalForm.setRccQuantity(dsrCanalBean.getRccQuantity());
				dsrCanalForm.setSchemeId(dsrCanalBean.getSchemeId());
				dsrCanalForm.setSlueiceCost(dsrCanalBean.getSlueiceCost());
				dsrCanalForm.setSlueiceSize(dsrCanalBean.getSlueiceSize());
				dsrCanalForm.setStorageCost(dsrCanalBean.getStorageCost());
				dsrCanalForm.setStorageDepth(dsrCanalBean.getStorageDepth());
				dsrCanalForm.setStorageQuantity(dsrCanalBean.getStorageQuantity());
				dsrCanalForm.setStorageSize(dsrCanalBean.getStorageSize());
				dsrCanalForm.setSuctionCost(dsrCanalBean.getSuctionCost());
				dsrCanalForm.setSuctionDimeter(dsrCanalBean.getSuctionDimeter());
				dsrCanalForm.setSuctionQuantity(dsrCanalBean.getSuctionQuantity());
				dsrCanalForm.setDsrCanalDistributionDatagrid(getDSRCanalDistributionDatagrid(dsrCanalBean.getDsrCanalDistributionBeans()));
				dsrCanalForm.setDsrCanalInletDatagrid(getDSRCanalInletDatagrid(dsrCanalBean.getDsrCanalInletBeans()));
				dsrCanalForm.setRccCapacity1(dsrCanalBean.getRccCapacity1());
				dsrCanalForm.setRccCost1(dsrCanalBean.getRccCost1());
				dsrCanalForm.setRccFsl1(dsrCanalBean.getRccFsl1());
				dsrCanalForm.setRccQuantity1(dsrCanalBean.getRccQuantity1());
				dsrCanalForm.setPumpCost1(dsrCanalBean.getPumpCost1());
				dsrCanalForm.setPumpingCentrifugal1(dsrCanalBean.getPumpingCentrifugal1());
				dsrCanalForm.setPumpQuantity1(dsrCanalBean.getPumpQuantity1());
				dsrCanalForm.setPumpingDischarge1(dsrCanalBean.getPumpingDischarge1());
				dsrCanalForm.setPumpingHead1(dsrCanalBean.getPumpingHead1());
				dsrCanalForm.setPumpingSubmersible1(dsrCanalBean.getPumpingSubmersible1());
				
			}
		}else{
			
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
		DSRCanalForm dsrCanalForm = (DSRCanalForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = dsrCanalBO.deleteDSRCanal(dsrCanalForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","DSR Information ","Scheme ID -->"+dsrCanalForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","DSR Information ","Scheme ID -->"+dsrCanalForm.getSchemeId());
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
		refreshDSRCanalForm(dsrCanalForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		DSRCanalForm dsrCanalForm = (DSRCanalForm)form;
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
			System.out.println("Canal Save: "+dsrCanalForm.getSchemeId());
			DSRCanalBean bean = dsrCanalBO.validateDSRCanalBean(dsrCanalForm);
			if(bean!=null){
			
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.alreadyExist", "DSR Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else {
				status = dsrCanalBO.saveDSRCanal(dsrCanalForm, misSessionBean);
				
					if (status){
						ActionMessages errors = new ActionMessages();
						ActionMessage message = new ActionMessage("success.save", "DSR Information ","Scheme ID -->"+dsrCanalForm.getSchemeId());
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
		refreshDSRCanalForm(dsrCanalForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		DSRCanalForm dsrCanalForm = (DSRCanalForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			System.out.println("Canal Modify: "+dsrCanalForm.getSchemeId());
			status = dsrCanalBO.updateDSRCanal(dsrCanalForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update",  "DSR Information ","Scheme ID -->"+dsrCanalForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "DSR Information ","Scheme ID -->"+dsrCanalForm.getSchemeId());
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
		refreshDSRCanalForm(dsrCanalForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("save:"+request.getParameter("d__mode"));
		this.setAttrib(request);
		DSRCanalForm dsrCanalForm = (DSRCanalForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			System.out.println("Canal Post: "+dsrCanalForm.getSchemeId());
			status = dsrCanalBO.postDSRCanal(dsrCanalForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","DSR Information","Scheme ID -->"+dsrCanalForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","DSR Information","Scheme ID -->"+dsrCanalForm.getSchemeId());
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
		
		refreshDSRCanalForm(dsrCanalForm);
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
		System.out.println("IN CANNAL UNSPECIFIED");
		this.setAttrib(request);
		DSRCanalForm dsrCanalForm = (DSRCanalForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		refreshDSRCanalForm(dsrCanalForm);
		return mapping.findForward("display");
	}

	private void refreshDSRCanalForm(DSRCanalForm dsrCanalForm){
		
		dsrCanalForm.setBulkCount(0);
		dsrCanalForm.setBulkSize(null);
		dsrCanalForm.setBulkTotalCost(0);
		dsrCanalForm.setClearCost(0);
		dsrCanalForm.setClearQuantity(0);
		dsrCanalForm.setClearType(null);
		dsrCanalForm.setContigencyCharges(new BigDecimal(0.0));
		dsrCanalForm.setTotalCostOfStructure(new BigDecimal(0.0));
		dsrCanalForm.setCostOfElectricConnectionProvision(0);
		dsrCanalForm.setDisinfectionCost(0);
		dsrCanalForm.setDsrDate(null);
		dsrCanalForm.setDisinfectionQuantity(0);
		dsrCanalForm.setDisinfectionType(null);
		dsrCanalForm.setDistributionCost(0);
		dsrCanalForm.setElectronicCost(0);
		dsrCanalForm.setElectronicQuantity(0);
		dsrCanalForm.setEnvironmentCost(new BigDecimal(0.0));
		dsrCanalForm.setFilterCost(0);
		dsrCanalForm.setFilterQuantity(0);
		dsrCanalForm.setFilterSize(null);
		dsrCanalForm.setFilterType(null);
		dsrCanalForm.setGrandTotal(new BigDecimal(0.00));
		dsrCanalForm.setHighCost(0);
		dsrCanalForm.setHighQuantity(0);
		dsrCanalForm.setHighSize(null);
		dsrCanalForm.setHighType(null);
		dsrCanalForm.setInletTotalCost(0);
		dsrCanalForm.setLocationId(null);
		dsrCanalForm.setPercapitaCost(new BigDecimal(0.0));
		dsrCanalForm.setPiplineCost(0);
		dsrCanalForm.setPiplineLength(0);
		dsrCanalForm.setPiplineSize(null);
		dsrCanalForm.setPiplineType(null);
		dsrCanalForm.setPlinthCost(0);
		dsrCanalForm.setPumpCost(0);
		dsrCanalForm.setPumpingCentrifugal(null);
		dsrCanalForm.setPumpingCost(0);
		dsrCanalForm.setPumpingDischarge(0);
		dsrCanalForm.setPumpingHead(0);
		dsrCanalForm.setPumpingMachineryApplicable(false);
		dsrCanalForm.setPumpingMachineryCost(0);
		dsrCanalForm.setPumpingMachineryDischarge(null);
		dsrCanalForm.setPumpingMachinerySize(null);
		dsrCanalForm.setPumpingPower(null);
		dsrCanalForm.setPumpingQuantity(0);
		dsrCanalForm.setPumpingSubmersible(null);
		dsrCanalForm.setPumpQuantity(0);
		dsrCanalForm.setPumpSelect(null);
		dsrCanalForm.setRccCapacity(0);
		dsrCanalForm.setRccCost(0);
		dsrCanalForm.setRccFsl(0);
		dsrCanalForm.setRccQuantity(0);
		dsrCanalForm.setSchemeId(null);
		dsrCanalForm.setSlueiceCost(0);
		dsrCanalForm.setSlueiceSize(null);
		dsrCanalForm.setStorageCost(0);
		dsrCanalForm.setStorageDepth(0);
		dsrCanalForm.setStorageQuantity(0);
		dsrCanalForm.setStorageSize(dsrCanalForm.getStorageSize());
		dsrCanalForm.setSuctionCost(dsrCanalForm.getSuctionCost());
		dsrCanalForm.setSuctionDimeter(dsrCanalForm.getSuctionDimeter());
		dsrCanalForm.setSuctionQuantity(dsrCanalForm.getSuctionQuantity());
		dsrCanalForm.setDsrCanalDistributionDatagrid(getDSRCanalDistributionDatagrid(null));
		dsrCanalForm.setDsrCanalInletDatagrid(getDSRCanalInletDatagrid(null));
		dsrCanalForm.setRccCapacity1(0);
		dsrCanalForm.setRccCost1(0);
		dsrCanalForm.setRccFsl1(0);
		dsrCanalForm.setRccQuantity1(0);
		dsrCanalForm.setPumpCost1(0);
		dsrCanalForm.setPumpingCentrifugal1(null);
		dsrCanalForm.setPumpingDischarge1(0);
		dsrCanalForm.setPumpingHead1(0);
		dsrCanalForm.setPumpingSubmersible1(null);
		dsrCanalForm.setPumpQuantity1(0);
		
	}
	
	private Datagrid getDSRCanalInletDatagrid(Set<DSRCanalInletBean> dsrCanalInletBeans) {
		Datagrid dsrCanalInletDatagrid = null;
		try {
			dsrCanalInletDatagrid = Datagrid.getInstance();
			dsrCanalInletDatagrid.setDataClass(DSRCanalInletBean.class);
			if(!MisUtility.ifEmpty(dsrCanalInletBeans)){
			List<DSRCanalInletBean> dsrCanalInletBeans2 = new ArrayList<DSRCanalInletBean>(dsrCanalInletBeans);
			dsrCanalInletDatagrid.setData(dsrCanalInletBeans2);		
			}else{
			List<DSRCanalInletBean> dsrCanalInletBeans2 = new ArrayList<DSRCanalInletBean>();
			dsrCanalInletDatagrid.setData(dsrCanalInletBeans2);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return dsrCanalInletDatagrid;
	}
	
	private Datagrid getDSRCanalDistributionDatagrid(Set<DSRCanalDistributionBean> dsrCanalDistributionBeans) {
		Datagrid dsrCanalDistributionDatagrid = null;
		try {
			dsrCanalDistributionDatagrid = Datagrid.getInstance();
			dsrCanalDistributionDatagrid.setDataClass(DSRCanalDistributionBean.class);
			if(!MisUtility.ifEmpty(dsrCanalDistributionBeans)){
			List<DSRCanalDistributionBean> dsrCanalDistributionBeans2 = new ArrayList<DSRCanalDistributionBean>(dsrCanalDistributionBeans);
			dsrCanalDistributionDatagrid.setData(dsrCanalDistributionBeans2);		
			}else{
			List<DSRCanalDistributionBean> dsrCanalDistributionBeans2 = new ArrayList<DSRCanalDistributionBean>();
			dsrCanalDistributionDatagrid.setData(dsrCanalDistributionBeans2);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return dsrCanalDistributionDatagrid;
	}
	
}
