/**
 * 
 */
package com.prwss.mis.pmm.labtesting.struts;

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
import com.prwss.mis.pmm.labtesting.LabTestingBO;
import com.prwss.mis.pmm.labtesting.LabTestingBean;

/**
 * @author pjha
 *
 */
public class LabTestingAction extends DispatchAction {

	Logger log = Logger.getLogger(LabTestingAction.class);
	private MISSessionBean misSessionBean;
	private LabTestingBO labTestingBO;
	public void setLabTestingBO(LabTestingBO labTestingBO) {
		this.labTestingBO = labTestingBO;
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
		
		LabTestingForm labTestingForm = (LabTestingForm)form;
		List<LabTestingBean> labTestingBeans = null;
		labTestingBeans = labTestingBO.findLabTesting(labTestingForm, statusList);
//		String villageId = labTestingForm.getVillageId();
//		String blockId = labTestingForm.getBlockId();
		if(!MisUtility.ifEmpty(labTestingBeans)){
			request.setAttribute("level2", "true");
			for (LabTestingBean labTestingBean : labTestingBeans) {
				request.setAttribute("villageId", labTestingBean.getVillageId());
				request.setAttribute("blockId", labTestingBean.getBlockId());
				request.setAttribute("locationId", labTestingBean.getLocationId());
				labTestingForm.setLocationId(labTestingBean.getLocationId());
				labTestingForm.setAlkalinityActual(labTestingBean.getAlkalinityActual());
				labTestingForm.setAlkalinityResult(labTestingBean.getAlkalinityResult());
				labTestingForm.setArsenicActual(labTestingBean.getArsenicActual());
				labTestingForm.setArsenicResult(labTestingBean.getArsenicResult());
				labTestingForm.setCadmiumActual(labTestingBean.getCadmiumActual());
				labTestingForm.setCadmiumResult(labTestingBean.getCadmiumResult());
				labTestingForm.setCalciumActual(labTestingBean.getCalciumActual());
				labTestingForm.setCalciumResult(labTestingBean.getCalciumResult());
				labTestingForm.setChlorideActual(labTestingBean.getChlorideActual());
				labTestingForm.setChlorideResult(labTestingBean.getChlorideResult());
				labTestingForm.setColourActual(labTestingBean.getColourActual());
				labTestingForm.setColourResult(labTestingBean.getColourResult());
				labTestingForm.setDissolvedActual(labTestingBean.getDissolvedActual());
				labTestingForm.setDissolvedResult(labTestingBean.getDissolvedResult());
				labTestingForm.setFluoridesActual(labTestingBean.getFluoridesActual());
				labTestingForm.setFluoridesResult(labTestingBean.getFluoridesResult());
				labTestingForm.setHabitation(labTestingBean.getHabitation());
				labTestingForm.setHardnessActual(labTestingBean.getHardnessActual());
				labTestingForm.setHardnessResult(labTestingBean.getHardnessResult());
				labTestingForm.setIronActual(labTestingBean.getIronActual());
				labTestingForm.setIronResult(labTestingBean.getIronResult());
				labTestingForm.setLabName(labTestingBean.getLabName());
				labTestingForm.setTestId(labTestingBean.getLabTestid());
				labTestingForm.setNitrateActual(labTestingBean.getNitrateActual());
				labTestingForm.setNitrateResult(labTestingBean.getNitrateResult());
				labTestingForm.setOther1Actual(labTestingBean.getOther1Actual());
				labTestingForm.setOther1Desirable(labTestingBean.getOther1Desirable());
				labTestingForm.setOther1Name(labTestingBean.getOther1Name());
				labTestingForm.setOther1Permissible(labTestingBean.getOther1Permissible());
				labTestingForm.setOther1Result(labTestingBean.getOther1Result());
				labTestingForm.setOther2Actual(labTestingBean.getOther2Actual());
				labTestingForm.setOther2Desirable(labTestingBean.getOther2Desirable());
				labTestingForm.setOther2Name(labTestingBean.getOther2Name());
				labTestingForm.setOther2Permissible(labTestingBean.getOther2Permissible());
				labTestingForm.setOther2Result(labTestingBean.getOther2Result());
				labTestingForm.setOther3Actual(labTestingBean.getOther3Actual());
				labTestingForm.setOther3Desirable(labTestingBean.getOther3Desirable());
				labTestingForm.setOther3Name(labTestingBean.getOther3Name());
				labTestingForm.setOther3Permissible(labTestingBean.getOther3Permissible());
				labTestingForm.setOther3Result(labTestingBean.getOther3Result());
				labTestingForm.setOther4Actual(labTestingBean.getOther4Actual());
				labTestingForm.setOther4Desirable(labTestingBean.getOther4Desirable());
				labTestingForm.setOther4Name(labTestingBean.getOther4Name());
				labTestingForm.setOther4Permissible(labTestingBean.getOther4Permissible());
				labTestingForm.setOther4Result(labTestingBean.getOther4Result());
				labTestingForm.setOtherbact1Actual(labTestingBean.getOtherbact1Actual());
				labTestingForm.setOtherbact1Desirable(labTestingBean.getOtherbact1Desirable());
				labTestingForm.setOtherbact1Name(labTestingBean.getOtherbact1Name());
				labTestingForm.setOtherbact1Permissible(labTestingBean.getOtherbact1Permissible());
				labTestingForm.setOtherbact1Result(labTestingBean.getOtherbact1Result());
				labTestingForm.setOtherbact2Actual(labTestingBean.getOtherbact2Actual());
				labTestingForm.setOtherbact2Desirable(labTestingBean.getOtherbact2Desirable());
				labTestingForm.setOtherbact2Name(labTestingBean.getOtherbact2Name());
				labTestingForm.setOtherbact2Permissible(labTestingBean.getOtherbact2Permissible());
				labTestingForm.setOtherbact2Result(labTestingBean.getOtherbact2Result());
				labTestingForm.setPhActual(labTestingBean.getPhActual());
				labTestingForm.setPhResult(labTestingBean.getPhResult());
				labTestingForm.setResidualActual(labTestingBean.getResidualActual());
				labTestingForm.setResidualResult(labTestingBean.getResidualResult());
				labTestingForm.setSeleniumActual(labTestingBean.getSeleniumActual());
				labTestingForm.setSeleniumResult(labTestingBean.getSeleniumResult());
				labTestingForm.setSulphateActual(labTestingBean.getSulphateActual());
				labTestingForm.setSulphateResult(labTestingBean.getSulphateResult());
				labTestingForm.setTasteOdourActual(labTestingBean.getTasteOdourActual());
				labTestingForm.setTasteOdourResult(labTestingBean.getTasteOdourResult());
				labTestingForm.setTestDate( MisUtility.convertDateToString(labTestingBean.getTestDate()));
				labTestingForm.setTurbidityActual(labTestingBean.getTurbidityActual());
				labTestingForm.setTurbidityResult(labTestingBean.getTurbidityResult());
				labTestingForm.setTypeOfParameter(labTestingBean.getTypeOfParameter());
				labTestingForm.setTypeOfWaterSource(labTestingBean.getTypeOfWaterSource());
				labTestingForm.setUraniumActual(labTestingBean.getUraniumActual());
				labTestingForm.setUraniumResult(labTestingBean.getUraniumResult());
				labTestingForm.setOverallSampleResult(labTestingBean.getOverallSampleResult());
			}
		}else{
			refreshLabTestingForm(labTestingForm);
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
		LabTestingForm labTestingForm = (LabTestingForm)form;
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
			status = labTestingBO.deleteLabTesting(labTestingForm, misSessionBean);
			
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Lab Test Information","Village ID -->"+labTestingForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Lab Test Information","Village ID -->"+labTestingForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Lab Test Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Lab Test Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
			
			
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		
		LabTestingForm labTestingForm = (LabTestingForm)form;
		long labTestNumber = 0;
		
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			labTestNumber = labTestingBO.saveLabTesting(labTestingForm, misSessionBean);
			
			if (MisUtility.ifEmpty(labTestNumber)){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Lab Test Information","the Sample. Auto Generated Lab Test Number for the Sample is -->"+labTestNumber);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save","Lab Test Information","Village ID -->"+labTestingForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Lab Test Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Lab Test Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
			
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		LabTestingForm labTestingForm = (LabTestingForm)form;
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
			status = labTestingBO.updateLabTesting(labTestingForm, misSessionBean);
			
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Lab Test Information","Village ID -->"+labTestingForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Lab Test Information","Village ID -->"+labTestingForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Lab Test Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Lab Test Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		refreshLabTestingForm(labTestingForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("save:"+request.getParameter("d__mode"));
		this.setAttrib(request);
		LabTestingForm labTestingForm = (LabTestingForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status= labTestingBO.postLabTesting(labTestingForm, misSessionBean);
			
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Lab Test Information","Village ID -->"+labTestingForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Lab Test Information","Village ID -->"+labTestingForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Lab Test Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Lab Test Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		refreshLabTestingForm(labTestingForm);
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId@blockId@villageId@testElementId@testDateId");
		
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		LabTestingForm labTestingForm = (LabTestingForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		refreshLabTestingForm(labTestingForm);
		return mapping.findForward("display");
	}
	

	private void refreshLabTestingForm(LabTestingForm labTestingForm){
		
		
		labTestingForm.setVillageId(null);
		labTestingForm.setAlkalinityActual(null);
		labTestingForm.setAlkalinityResult(null);
		labTestingForm.setArsenicActual(null);
		labTestingForm.setArsenicResult(null);
		labTestingForm.setCadmiumActual(null);
		labTestingForm.setCadmiumResult(null);
		labTestingForm.setCalciumActual(null);
		labTestingForm.setCalciumResult(null);
		labTestingForm.setChlorideActual(null);
		labTestingForm.setChlorideResult(null);
		labTestingForm.setColourActual(null);
		labTestingForm.setColourResult(null);
		labTestingForm.setDissolvedActual(null);
		labTestingForm.setDissolvedResult(null);
		labTestingForm.setFluoridesActual(null);
		labTestingForm.setFluoridesResult(null);
		labTestingForm.setHabitation(null);
		labTestingForm.setHardnessActual(null);
		labTestingForm.setHardnessResult(null);
		labTestingForm.setIronActual(null);
		labTestingForm.setIronResult(null);
		labTestingForm.setLabName(null);
		labTestingForm.setLocationId(null);
		labTestingForm.setNitrateActual(null);
		labTestingForm.setNitrateResult(null);
		labTestingForm.setOther1Actual(null);
		labTestingForm.setOther1Desirable(null);
		labTestingForm.setOther1Name(null);
		labTestingForm.setOther1Permissible(null);
		labTestingForm.setOther1Result(null);
		labTestingForm.setOther2Actual(null);
		labTestingForm.setOther2Desirable(null);
		labTestingForm.setOther2Name(null);
		labTestingForm.setOther2Permissible(null);
		labTestingForm.setOther2Result(null);
		labTestingForm.setOther3Actual(null);
		labTestingForm.setOther3Desirable(null);
		labTestingForm.setOther3Name(null);
		labTestingForm.setOther3Permissible(null);
		labTestingForm.setOther3Result(null);
		labTestingForm.setOther4Actual(null);
		labTestingForm.setOther4Desirable(null);
		labTestingForm.setOther4Name(null);
		labTestingForm.setOther4Permissible(null);
		labTestingForm.setOther4Result(null);
		labTestingForm.setOtherbact1Actual(null);
		labTestingForm.setOtherbact1Desirable(null);
		labTestingForm.setOtherbact1Name(null);
		labTestingForm.setOtherbact1Permissible(null);
		labTestingForm.setOtherbact1Result(null);
		labTestingForm.setOtherbact2Actual(null);
		labTestingForm.setOtherbact2Desirable(null);
		labTestingForm.setOtherbact2Name(null);
		labTestingForm.setOtherbact2Permissible(null);
		labTestingForm.setOtherbact2Result(null);
		labTestingForm.setPhActual(null);
		labTestingForm.setPhResult(null);
		labTestingForm.setResidualActual(null);
		labTestingForm.setResidualResult(null);
		labTestingForm.setSeleniumActual(null);
		labTestingForm.setSeleniumResult(null);
		labTestingForm.setSulphateActual(null);
		labTestingForm.setSulphateResult(null);
		labTestingForm.setTasteOdourActual(null);
		labTestingForm.setTasteOdourResult(null);
		labTestingForm.setTestDate(null);
		labTestingForm.setTurbidityActual(null);
		labTestingForm.setTurbidityResult(null);
		labTestingForm.setTypeOfParameter(null);
		labTestingForm.setTypeOfWaterSource(null);
		labTestingForm.setUraniumActual(null);
		labTestingForm.setUraniumResult(null);
		
		
		
	}
	
}
