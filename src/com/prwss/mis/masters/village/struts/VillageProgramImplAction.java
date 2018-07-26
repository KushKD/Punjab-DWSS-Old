package com.prwss.mis.masters.village.struts;

import java.util.ArrayList;
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
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.masters.village.VillageProgramImplementedBean;
import com.prwss.mis.masters.village.dao.VillageProgramImplementedBO;
import com.prwss.mis.masters.village.dao.VillageStatusNCPC2008ViewDao;
import com.prwss.mis.masters.village.dao.VillageStatusNCPCView2008Bean;

public class VillageProgramImplAction extends DispatchAction {
private MISSessionBean misSessionBean;
private ProgramDao programDao;
private VillageProgramImplementedBO villageProgramImplementedBO;
private VillageStatusNCPC2008ViewDao villageStatusNCPC2008ViewDao;
private Logger log = Logger.getLogger(VillageProgramImplAction.class);




	public void setVillageStatusNCPC2008ViewDao(
		VillageStatusNCPC2008ViewDao villageStatusNCPC2008ViewDao) {
	this.villageStatusNCPC2008ViewDao = villageStatusNCPC2008ViewDao;
}

	public void setVillageProgramImplementedBO(
		VillageProgramImplementedBO villageProgramImplementedBO) {
	this.villageProgramImplementedBO = villageProgramImplementedBO;
}

	public void setProgramDao(ProgramDao programDao) {
	this.programDao = programDao;
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
		this.setAttrib(request);
		try {
			VillageProgramImplForm villageProgramImplForm = (VillageProgramImplForm)form;
			VillageProgramImplementedBean implementedBean = villageProgramImplementedBO.findVillageProgImpl(villageProgramImplForm, statusList);
			if(MisUtility.ifEmpty(implementedBean)){
				
				request.setAttribute("level2", "true");
				villageProgramImplForm.setVillageId(implementedBean.getVillageId());
				villageProgramImplForm.setPrgm_to_be_impl_nc_vill_habit_arp(implementedBean.getPrgm_to_be_impl_nc_vill_habit_arp());
				villageProgramImplForm.setPrgm_to_be_impl_nc_vill_habit_nabard_xii(implementedBean.getPrgm_to_be_impl_nc_vill_habit_nabard_xii());
				villageProgramImplForm.setPrgm_to_be_impl_nc_vill_habit_qp(implementedBean.getPrgm_to_be_impl_nc_vill_habit_qp());
				villageProgramImplForm.setPrgm_to_be_impl_nc_vill_habit_rws(implementedBean.getPrgm_to_be_impl_nc_vill_habit_rws());
				villageProgramImplForm.setPrgm_to_be_impl_nc_vill_habit_wb_ada(implementedBean.getPrgm_to_be_impl_nc_vill_habit_wb_ada());
		
				villageProgramImplForm.setPrgm_to_be_impl_pc_vill_habit_arp(implementedBean.getPrgm_to_be_impl_pc_vill_habit_arp());
				villageProgramImplForm.setPrgm_to_be_impl_pc_vill_habit_nabard_xii(implementedBean.getPrgm_to_be_impl_pc_vill_habit_nabard_xii());
				villageProgramImplForm.setPrgm_to_be_impl_pc_vill_habit_qp(implementedBean.getPrgm_to_be_impl_pc_vill_habit_qp());
				villageProgramImplForm.setPrgm_to_be_impl_pc_vill_habit_rws(implementedBean.getPrgm_to_be_impl_pc_vill_habit_rws());
				villageProgramImplForm.setPrgm_to_be_impl_pc_vill_habit_wb_ada(implementedBean.getPrgm_to_be_impl_pc_vill_habit_wb_ada());
		        
			//	System.out.println("Data he bhai data he"+villageProgramImplForm);
				
				VillageStatusNCPCView2008Bean bean = new VillageStatusNCPCView2008Bean();
				bean.setVillageId(implementedBean.getVillageId());
				List<VillageStatusNCPCView2008Bean> villageStatusNCPCView2008Beans = villageStatusNCPC2008ViewDao.find(bean);
				if(!MisUtility.ifEmpty(villageStatusNCPCView2008Beans)){
					villageProgramImplForm.setNcPcStatus(villageStatusNCPCView2008Beans.get(0).getNcPcStatus());
				}
			}

			else{
				refreshVillageProgImplForm(villageProgramImplForm);
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
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		VillageProgramImplForm villageProgramImplForm = (VillageProgramImplForm)form;
		this.setAttrib(request);
		System.out.println("hello");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		
		Set<LabelValueBean> programIds = getProgramIds();
		request.getSession().setAttribute("programIds", programIds);
		refreshVillageProgImplForm(villageProgramImplForm);
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "villageId@ncpcId");	
	}
	
	private Set<LabelValueBean> getProgramIds(){
		Set<LabelValueBean> programIds = null;
		Set<ProgramBean> programBeans = null;
		try{
//			ProgramBean programBean = new ProgramBean();
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			programBeans = programDao.getDistinctPrograms();
			programIds = new TreeSet<LabelValueBean>();
			programIds.add(new LabelValueBean("NA","NA"));
			for (ProgramBean programBean1 : programBeans) {
				programIds.add(new LabelValueBean(programBean1.getProgramName()+" - ("+programBean1.getProgramId()+")",programBean1.getProgramId()));
				
			}
			
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		return programIds;	
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		VillageProgramImplForm villageProgramImplForm = (VillageProgramImplForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = villageProgramImplementedBO.updateVillageProgImpl(villageProgramImplForm, misSessionBean);
			if (status){
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.update", "Village Information ","Village ID -->"+villageProgramImplForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "Village Information ","Village ID -->"+villageProgramImplForm.getVillageId());
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
		refreshVillageProgImplForm(villageProgramImplForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		VillageProgramImplForm villageProgramImplForm = (VillageProgramImplForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = villageProgramImplementedBO.saveVillageProgImpl(villageProgramImplForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Village Information ","Village ID -->"+villageProgramImplForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save", "Village Information ","Village ID -->"+villageProgramImplForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry","Saving failed as ",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.save","Information","Village Id->"+villageProgramImplForm.getVillageId()+e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				} 
		}
		
		catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Village Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshVillageProgImplForm(villageProgramImplForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		VillageProgramImplForm villageProgramImplForm = (VillageProgramImplForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			status = villageProgramImplementedBO.postVillageProgImp(villageProgramImplForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post", "Village Information ","Village ID -->"+villageProgramImplForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post", "Village Information ","Village ID -->"+villageProgramImplForm.getVillageId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry","Saving failed as ",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Information","Village Id->"+villageProgramImplForm.getVillageId()+e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				} 
		}
		
		catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.post","Village Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshVillageProgImplForm(villageProgramImplForm);
		return mapping.findForward("display");
	}
		
	private void refreshVillageProgImplForm(VillageProgramImplForm villageProgramImplForm){
		villageProgramImplForm.setVillageId(null);
		villageProgramImplForm.setNcPcStatus(null);
		villageProgramImplForm.setPrgm_to_be_impl_nc_vill_habit_arp("NA");
		villageProgramImplForm.setPrgm_to_be_impl_nc_vill_habit_nabard_xii("NA");
		villageProgramImplForm.setPrgm_to_be_impl_nc_vill_habit_qp("NA");
		villageProgramImplForm.setPrgm_to_be_impl_nc_vill_habit_rws("NA");
		villageProgramImplForm.setPrgm_to_be_impl_nc_vill_habit_wb_ada("NA");

		villageProgramImplForm.setPrgm_to_be_impl_pc_vill_habit_arp("NA");
		villageProgramImplForm.setPrgm_to_be_impl_pc_vill_habit_nabard_xii("NA");
		villageProgramImplForm.setPrgm_to_be_impl_pc_vill_habit_qp("NA");
		villageProgramImplForm.setPrgm_to_be_impl_pc_vill_habit_rws("NA");
		villageProgramImplForm.setPrgm_to_be_impl_pc_vill_habit_wb_ada("NA");
	}
	
}
