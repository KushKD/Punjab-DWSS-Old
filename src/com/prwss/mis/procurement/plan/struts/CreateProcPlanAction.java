package com.prwss.mis.procurement.plan.struts;

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
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.masters.village.dao.VillageBean;
import com.prwss.mis.masters.village.struts.VillageForm;
import com.prwss.mis.procurement.plan.CreateProcPlanBO;
import com.prwss.mis.procurement.plan.CreateProcPlanBean;

public class CreateProcPlanAction extends DispatchAction {
		Logger log = Logger.getLogger(CreateProcPlanAction.class);
		private MISSessionBean misSessionBean;
		private CreateProcPlanBO createProcPlanBO;
		private ProgramDao programDao;
		
		public void setProgramDao(ProgramDao programDao) {
			this.programDao = programDao;
		}

		public void setCreateProcPlanBO(CreateProcPlanBO createProcPlanBO) {
			this.createProcPlanBO = createProcPlanBO;
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
				CreateProcPlanForm createProcPlanForm = (CreateProcPlanForm)form;
				List<CreateProcPlanBean> createProcPlanBeans = null;
				createProcPlanBeans = createProcPlanBO.findCreateProcPlanFrom(createProcPlanForm, statusList);
				if(!MisUtility.ifEmpty(createProcPlanBeans)){
					request.setAttribute("planList", createProcPlanBeans);
					/*request.setAttribute("level2", "true");
					for (CreateProcPlanBean createProcPlanBean : createProcPlanBeans) {
						
						createProcPlanForm.setPlanId(createProcPlanBean.getPlanId());
						createProcPlanForm.setLocationId(createProcPlanBean.getLocationId());
						createProcPlanForm.setProgramId(createProcPlanBean.getProgramId());
						createProcPlanForm.setPlanTo(MisUtility.convertDateToString(createProcPlanBean.getPlanTo()));
						createProcPlanForm.setPlanFrom(MisUtility.convertDateToString(createProcPlanBean.getPlanFrom()));
						createProcPlanForm.setPlanDescription(createProcPlanBean.getPlanDescription());
						createProcPlanForm.setPlanType(createProcPlanBean.getPlanType());
					}*/
				}else{
					refreshProcPlanForm(createProcPlanForm);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Inquire of Procurement Plan Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			return mapping.findForward("display");
		}
		
		public ActionForward delete(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws MISException {
			this.setAttrib(request);
			CreateProcPlanForm createProcPlanForm = (CreateProcPlanForm)form;
			boolean status = false;
			try {
				if (request.getSession().getAttribute("misSessionBean") != null) {
					{
						misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
					}
				} else {
					return mapping.findForward("login");
				}

				status = createProcPlanBO.deleteCreateProcPlanFrom(createProcPlanForm, misSessionBean);
				if (status){
					ActionMessages errors = new ActionMessages();
					ActionMessage message = new ActionMessage("success.delete","Procurement Plan","Plan ID -->"+createProcPlanForm.getPlanId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, errors);

				} else {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.delete","Procurement Plan","Plan ID -->"+createProcPlanForm.getPlanId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			} catch (MISException e) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Procurement Plan Details");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Procurement Plan Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			refreshProcPlanForm(createProcPlanForm);
			return mapping.findForward("display");
		}
		
		public ActionForward save(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws MISException {
			this.setAttrib(request);
			request.setAttribute("level2","true");
			CreateProcPlanForm createProcPlanForm = (CreateProcPlanForm)form;
			boolean status = false;
			try {
				//StringBuffer stringMessage = new StringBuffer();
				if (request.getSession().getAttribute("misSessionBean") != null) {
					{
						misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
					}
				} else {
					System.out.println("NO AUDIT");
					return mapping.findForward("login");
				}
				System.out.println("createProcPlanForm"+createProcPlanForm);
				/*if(createProcPlanForm.getPlanId().equals(null)||createProcPlanForm.getPlanId().trim().equals("")||createProcPlanForm.getPlanId().trim().equals("''")||createProcPlanForm.getLocationId().equals("''")){
					throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
				}*/
//				if(!MisUtility.ifEmpty(createProcPlanForm.getLocationId()) || !MisUtility.ifEmpty(createProcPlanForm.getPlanId())||!MisUtility.ifEmpty(createProcPlanForm.getProgramId())|| !MisUtility.ifEmpty(createProcPlanForm.getPlanFrom())|| !MisUtility.ifEmpty(createProcPlanForm.getPlanTo())|| !MisUtility.ifEmpty(createProcPlanForm.getPlanType())){
//					if(!MisUtility.ifEmpty(createProcPlanForm.getLocationId())){
//						stringMessage.append("Please select Location");
//					}
//					if(!MisUtility.ifEmpty(createProcPlanForm.getPlanId())){
//						stringMessage.append("<br> Please enter Work Plan");
//					}
//					if(!MisUtility.ifEmpty(createProcPlanForm.getProgramId())){
//						stringMessage.append("<br> Please select Sub Program");
//					}
//					if(!MisUtility.ifEmpty(createProcPlanForm.getPlanFrom())){
//						stringMessage.append("<br> Please enter Plan From");
//					}
//					if(!MisUtility.ifEmpty(createProcPlanForm.getPlanTo())){
//						stringMessage.append("<br> Please enter Plan Till");
//					}
//					if(!MisUtility.ifEmpty(createProcPlanForm.getPlanType())){
//						stringMessage.append("<br> Please select Plan Type");
//					}
//					throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
//				}
				status = createProcPlanBO.saveCreateProcPlanFrom(createProcPlanForm, misSessionBean);
				
				if (status){
					ActionMessages errors = new ActionMessages();
					ActionMessage message = new ActionMessage("success.save","Procurement Plan","Plan ID -->"+createProcPlanForm.getPlanId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, errors);
					refreshProcPlanForm(createProcPlanForm);
				} else {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", "Procurement Plan Details");
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
				}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
					log.error(e.getLocalizedMessage(),e);
					e.printStackTrace();
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("value.missing","Saving failed as ");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
//				else if(MISExceptionCodes.MIS012.equals(e.getCode())){
//					log.error(e.getLocalizedMessage(),e);
//					e.printStackTrace();
//					ActionErrors errors = new ActionErrors();
//					ActionMessage message = new ActionMessage("required.fields",e.getMessage());
//					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
//					saveErrors(request, errors);
//				}
				else{
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();	
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Saving of Procurement Plan Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				}
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Saving of Procurement Plan Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}
			
			return mapping.findForward("display");
		}
		
		public ActionForward update(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws MISException {
			this.setAttrib(request);
			CreateProcPlanForm createProcPlanForm = (CreateProcPlanForm)form;
			boolean status = false;
			try {
				//StringBuffer stringMessage = new StringBuffer();
				if (request.getSession().getAttribute("misSessionBean") != null) {
					{
						misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
					}
				} else {
					return mapping.findForward("login");
				}
//				if(!MisUtility.ifEmpty(createProcPlanForm.getLocationId()) || !MisUtility.ifEmpty(createProcPlanForm.getPlanId())||!MisUtility.ifEmpty(createProcPlanForm.getProgramId())|| !MisUtility.ifEmpty(createProcPlanForm.getPlanFrom())|| !MisUtility.ifEmpty(createProcPlanForm.getPlanTo())|| !MisUtility.ifEmpty(createProcPlanForm.getPlanType())){
//					if(!MisUtility.ifEmpty(createProcPlanForm.getLocationId())){
//						stringMessage.append("Please select Location");
//					}
//					if(!MisUtility.ifEmpty(createProcPlanForm.getPlanId())){
//						stringMessage.append("<br> Please enter Work Plan");
//					}
//					if(!MisUtility.ifEmpty(createProcPlanForm.getProgramId())){
//						stringMessage.append("<br> Please select Sub Program");
//					}
//					if(!MisUtility.ifEmpty(createProcPlanForm.getPlanFrom())){
//						stringMessage.append("<br> Please enter Plan From");
//					}
//					if(!MisUtility.ifEmpty(createProcPlanForm.getPlanTo())){
//						stringMessage.append("<br> Please enter Plan Till");
//					}
//					if(!MisUtility.ifEmpty(createProcPlanForm.getPlanType())){
//						stringMessage.append("<br> Please select Plan Type");
//					}
//					throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
//				}
				status = createProcPlanBO.updateCreateProcPlanFrom(createProcPlanForm, misSessionBean);
				if (status){
					ActionMessages errors = new ActionMessages();
					ActionMessage message = new ActionMessage("success.update","Procurement Plan","Plan ID -->"+createProcPlanForm.getPlanId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, errors);
					refreshProcPlanForm(createProcPlanForm);
				} else {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.update","Procurement Plan","Plan ID -->"+createProcPlanForm.getPlanId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			} catch (MISException e) {
//				 if(MISExceptionCodes.MIS012.equals(e.getCode())){
//					log.error(e.getLocalizedMessage(),e);
//					e.printStackTrace();
//					ActionErrors errors = new ActionErrors();
//					ActionMessage message = new ActionMessage("required.fields",e.getMessage());
//					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
//					saveErrors(request, errors);
//				}else{
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save","Updation of Procurement Plan Details (PSR)");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
			//	}	
			
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Updation of Procurement Plan Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}
			
			return mapping.findForward("display");
		}
		
		public ActionForward post(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws MISException {
			this.setAttrib(request);
			CreateProcPlanForm createProcPlanForm = (CreateProcPlanForm)form;
			boolean status = false;
			try {
				if (request.getSession().getAttribute("misSessionBean") != null) {
					{
						misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
					}
				} else {
					return mapping.findForward("login");
				}

				status = createProcPlanBO.postCreateProcPlanFrom(createProcPlanForm, misSessionBean);
				if (status){
					ActionMessages errors = new ActionMessages();
					ActionMessage message = new ActionMessage("success.post","Procurement Plan","Plan ID -->"+createProcPlanForm.getPlanId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, errors);

				} else {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.post","Post of Procurement Plan","Plan ID -->"+createProcPlanForm.getPlanId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			} catch (MISException e) {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save","Post of Procurement Plan Details");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Post of Procurement Plan Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}
			
			refreshProcPlanForm(createProcPlanForm);
			return mapping.findForward("display");
		}
		
		
		
		private void setAttrib(HttpServletRequest request){
			request.setAttribute("Rpt","ent");
			request.setAttribute("menuId", request.getParameter("menuId"));
			request.setAttribute("d__mode",request.getParameter("d__mode"));
			request.setAttribute("d__ky", "planId@locationId");
			request.setAttribute("d__auto", "planId");	
		}
		
		protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws MISException {
			this.setAttrib(request);
			CreateProcPlanForm createProcPlanForm = (CreateProcPlanForm)form;
			System.out.println("IN UNSPCIFIED PROC PLAN");
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			try {
				Set<LabelValueBean> programIds = getProgramIds();
				request.getSession().setAttribute("programIds", programIds);
				refreshProcPlanForm(createProcPlanForm);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getLocalizedMessage(),e);
			}
			return mapping.findForward("display");
		}

		private void refreshProcPlanForm(CreateProcPlanForm createProcPlanForm){
			createProcPlanForm.setLocationId(null); 
			createProcPlanForm.setPlanDescription(null);
			createProcPlanForm.setPlanFrom(null);
			createProcPlanForm.setPlanTo(null);
			createProcPlanForm.setPlanId(null);
			createProcPlanForm.setPlanType(null);
			
		}
		
		private Set<LabelValueBean> getProgramIds(){
			Set<LabelValueBean> programIds = null;
			Set<ProgramBean> programBeans = null;
			try{
//				ProgramBean programBean = new ProgramBean();
				List <String> statusList = new ArrayList<String>() ;
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				programBeans = programDao.getDistinctPrograms();
				programIds = new TreeSet<LabelValueBean>();
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
		public ActionForward populate(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			this.setAttrib(request);
			String planId = request.getParameter("planId");
			CreateProcPlanForm createProcPlanForm= (CreateProcPlanForm)form;
			createProcPlanForm.setPlanId(planId);
			List<CreateProcPlanBean> createProcPlanBeans = null;
			createProcPlanBeans = createProcPlanBO.findCreateProcPlanFrom(createProcPlanForm, null);
			if(!MisUtility.ifEmpty(createProcPlanBeans)){
				request.setAttribute("level2", "true");
				for (CreateProcPlanBean createProcPlanBean : createProcPlanBeans) {
					createProcPlanForm.setPlanId(createProcPlanBean.getPlanId());
					createProcPlanForm.setLocationId(createProcPlanBean.getLocationId());
					createProcPlanForm.setProgramId(createProcPlanBean.getProgramId());
					createProcPlanForm.setPlanTo(MisUtility.convertDateToString(createProcPlanBean.getPlanTo()));
					createProcPlanForm.setPlanFrom(MisUtility.convertDateToString(createProcPlanBean.getPlanFrom()));
					createProcPlanForm.setPlanDescription(createProcPlanBean.getPlanDescription());
					createProcPlanForm.setPlanType(createProcPlanBean.getPlanType());
				}
			}
			return mapping.findForward("display");
		}
	}



