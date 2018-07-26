/**
 * 
 */
package com.prwss.mis.finance.budgetrevision.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import com.prwss.mis.finance.accountschart.AccountsChartBean;
import com.prwss.mis.finance.accountschart.dao.AccountsChartDao;
import com.prwss.mis.finance.budget.BudgetBean;
import com.prwss.mis.finance.budget.dao.BudgetDao;
import com.prwss.mis.finance.budgetdetail.BudgetDetailBean;
import com.prwss.mis.finance.budgetrevision.BudgetRevisionBO;
import com.prwss.mis.masters.activity.dao.ActivityBean;
import com.prwss.mis.masters.activity.dao.ActivityDao;
import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.component.dao.ComponentDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.masters.subcomponent.dao.SubComponentBean;
import com.prwss.mis.masters.subcomponent.dao.SubComponentDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class BudgetRevisionAction extends DispatchAction {
	private Logger log = Logger.getLogger(BudgetRevisionAction.class);
	
	private MISSessionBean misSessionBean;
	private ProgramDao programDao;
	private BudgetDao  budgetDao;
     private AccountsChartDao accountsChartDao;
     private ComponentDao componentDao;
     private SubComponentDao subComponentDao;
     private ActivityDao activityDao;
     private BudgetRevisionBO budgetRevisionBO;
     
     
     
     
     

	public void setProgramDao(ProgramDao programDao) {
		this.programDao = programDao;
	}


	public void setBudgetDao(BudgetDao budgetDao) {
		this.budgetDao = budgetDao;
	}


	public void setAccountsChartDao(AccountsChartDao accountsChartDao) {
		this.accountsChartDao = accountsChartDao;
	}


	public void setComponentDao(ComponentDao componentDao) {
		this.componentDao = componentDao;
	}


	public void setSubComponentDao(SubComponentDao subComponentDao) {
		this.subComponentDao = subComponentDao;
	}


	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}


	public void setBudgetRevisionBO(BudgetRevisionBO budgetRevisionBO) {
		this.budgetRevisionBO = budgetRevisionBO;
	}

	
	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		System.out.println("In Find ::::::::::::::::::::::::::::::::");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		
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
		BudgetRevisionForm budgetRevisionForm = (BudgetRevisionForm)form;
		
		long budgetId = budgetRevisionForm.getBudgetId();
		
		String locationId = budgetRevisionForm.getLocationId();
		
		List<BudgetDetailBean>budgetDetailBeans = null; 
		try {
			budgetDetailBeans = budgetRevisionBO.findBudgetRevision(budgetRevisionForm, statusList);
			
			
			if(!MisUtility.ifEmpty(budgetDetailBeans)){
				request.setAttribute("level2", "true");
				request.setAttribute("budgetId", budgetId);
				request.setAttribute("locationId", locationId);
				for (BudgetDetailBean budgetRevisionDetailBean : budgetDetailBeans) {
					//budgetSubmissionForm.setBudgetSubmissionDatagrid(budgetSubmissionDatagrid)
					budgetRevisionForm.setBudgetId(budgetRevisionDetailBean.getBudgetId());
//					budgetRevisionForm.setActivityId(budgetRevisionDetailBean.getActivityId());
//					budgetRevisionForm.setCodeHeadId(budgetRevisionDetailBean.getCodeHeadId());
//					budgetRevisionForm.setComponentId(budgetRevisionDetailBean.getComponentId());
					budgetRevisionForm.setLocationId(budgetRevisionDetailBean.getLocationId());
				}
				budgetRevisionForm.setBudgetRevisionDatagrid(getBudgetRevisionDatagrid(budgetDetailBeans));
			}else{
				refreshBudgetRevisionForm(budgetRevisionForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("budget.no.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("budget.error.find");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			e.printStackTrace();
			
		}
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		BudgetRevisionForm budgetRevisionForm = (BudgetRevisionForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = budgetRevisionBO.deleteBudgetRevision(budgetRevisionForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Budget submission","Plan ID -->"+budgetRevisionForm.getBudgetId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Budget Plan","Plan ID -->"+budgetRevisionForm.getBudgetId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Budget Revision Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Budget Revision Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshBudgetRevisionForm(budgetRevisionForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("In Save");
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		BudgetRevisionForm budgetRevisionForm = (BudgetRevisionForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}


			if(!MisUtility.ifEmpty(budgetRevisionForm.getBudgetId())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			
			if(MisUtility.ifEmpty(budgetRevisionForm.getBudgetRevisionDatagrid().getAddedData())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			status = budgetRevisionBO.saveBudgetRevision(budgetRevisionForm, misSessionBean);
			//System.out.println("Plan ID :::::::::::::"+planID);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("budgetSubmission.success.save", "Your Budget Submission");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("budgetSubmission.error.save");
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
				ActionMessage message = new ActionMessage("fatal.error.save","Budget Revision");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("budgetSubmission.error.save");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		try {
			refreshBudgetRevisionForm(budgetRevisionForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		refreshBudgetRevisionForm(budgetRevisionForm);
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("In update Budget Revision ");
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		BudgetRevisionForm budgetRevisionForm = (BudgetRevisionForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			if(budgetRevisionForm.getBudgetId()==0){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}	
			status = budgetRevisionBO.updateBudgetRevision(budgetRevisionForm, misSessionBean);
			//System.out.println("Plan ID :::::::::::::"+planID);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("budgetRevision.success.save");
				//ActionMessage message = new ActionMessage("budgetSubmission.success.save", "Your Budget Submission");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("budgetRevision.error.save");
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
				ActionMessage message = new ActionMessage("fatal.error.save","Budget Revision");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("budgetRevision.error.save");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		try {
			refreshBudgetRevisionForm(budgetRevisionForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		refreshBudgetRevisionForm(budgetRevisionForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		BudgetRevisionForm budgetRevisionForm = (BudgetRevisionForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = budgetRevisionBO.postBudgetRevision(budgetRevisionForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Budget Revision","Plan ID -->"+budgetRevisionForm.getBudgetId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Post of Budget Revision","Plan ID -->"+budgetRevisionForm.getBudgetId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Post of Budget Revision Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Post of Budget Revision Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		refreshBudgetRevisionForm(budgetRevisionForm);
		return mapping.findForward("display");
	}
	
	
	
	

	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId@programId@budgetId");
		
	}
	
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("unspecified:BudgetRevisionAction"+request.getParameter("d__mode"));
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		
		try {
			BudgetRevisionForm budgetRevisionForm = (BudgetRevisionForm)form;
			
			Set<LabelValueBean> programIds = getProgramIds();
			request.getSession().setAttribute("programIds", programIds);
			
			Set<LabelValueBean> componentIds = getComponentIds();
			request.getSession().setAttribute("componentIds", componentIds);
			
			
			Set<LabelValueBean> codeHeadIds = getCodeHeadIds();
			request.getSession().setAttribute("codeHeadIds", codeHeadIds);
			
			refreshBudgetRevisionForm(budgetRevisionForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		BudgetRevisionForm budgetRevisionForm = (BudgetRevisionForm)form;
		budgetRevisionForm.setBudgetRevisionDatagrid(getBudgetRevisionDatagrid(null));
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	
	
	private Datagrid getBudgetRevisionDatagrid(List<BudgetDetailBean> budgetDetailBeans) {
		Datagrid budgetRevisionDatagrid = null;
		try {
			budgetRevisionDatagrid = Datagrid.getInstance();
			budgetRevisionDatagrid.setDataClass(BudgetDetailBean.class);
			if(!MisUtility.ifEmpty(budgetDetailBeans)){
			List<BudgetDetailBean> budgetDetailBeans2 = new ArrayList<BudgetDetailBean>(budgetDetailBeans);
			budgetRevisionDatagrid.setData(budgetDetailBeans2);		
			}else{
			List<BudgetDetailBean> budgetDetailBeans2 = new ArrayList<BudgetDetailBean>();
			budgetRevisionDatagrid.setData(budgetDetailBeans2);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return budgetRevisionDatagrid;
	}
	
	private Set<LabelValueBean> getCodeHeadIds() {
		Set<LabelValueBean> codeHeadIds = null;
		List<AccountsChartBean> accountsChartBeans = null;
		try{
			AccountsChartBean accountsChartBean = new AccountsChartBean();
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			accountsChartBeans = accountsChartDao.findAccountsChart(accountsChartBean, statusList);
			codeHeadIds = new HashSet<LabelValueBean>();
			for (AccountsChartBean accountsChartBean2 : accountsChartBeans) {
				codeHeadIds.add(new LabelValueBean(accountsChartBean2.getCodeHeadId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+accountsChartBean2.getCodeHeadIdDescription(),accountsChartBean2.getCodeHeadId()));
				
			}
			
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		return codeHeadIds;	
	}


	private Set<LabelValueBean> getComponentIds() {
		Set<LabelValueBean> componentIds = null;
		List<ComponentBean> componentBeans = null;
		try{
			ComponentBean componentBean = new ComponentBean();
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			componentBeans = componentDao.findComponent(componentBean, statusList);
			componentIds = new HashSet<LabelValueBean>();
			for (ComponentBean componentBean2 : componentBeans) {
				componentIds.add(new LabelValueBean(componentBean2.getComponentId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+componentBean2.getComponentName(),componentBean2.getComponentId()));
				
			}
			
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		return componentIds;	
	}
	
private void refreshBudgetRevisionForm(BudgetRevisionForm budgetRevisionForm){
		
	
	
	budgetRevisionForm.setRevFinanceBudgt(new BigDecimal(0.00));
	budgetRevisionForm.setRevPhysicalBudgt(new BigDecimal(0.00));
	budgetRevisionForm.setBudgetRevisionDatagrid(getBudgetRevisionDatagrid(null));	
	
	}
	
	private Set<LabelValueBean> getProgramIds(){
		Set<LabelValueBean> programIds = null;
		List<ProgramBean> programBeans = null;
		try{
			ProgramBean programBean = new ProgramBean();
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			programBeans = programDao.findProgram(programBean, statusList);
			programIds = new HashSet<LabelValueBean>();
			for (ProgramBean programBean1 : programBeans) {
				programIds.add(new LabelValueBean(programBean1.getProgramName(),programBean1.getProgramId()));
				
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
	public ActionForward fetchBudgetId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<BudgetBean> budgetBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		BudgetBean budgetBean = new BudgetBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("programId"))){
				budgetBean.setProgramId(request.getParameter("programId"));
				budgetBeans = new TreeSet<BudgetBean>(budgetDao.findBudgetPlan(budgetBean, statusList));
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(budgetBeans)){
					for (BudgetBean budgetBean2 : budgetBeans) {
						buffer.append("<option value=\"").append(budgetBean2.getBudgetId()).append("\">");
						buffer.append(budgetBean2.getBudgetId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+"("+MisUtility.convertDateToString(budgetBean2.getBudgetFromDate())+MISConstants.LABEL_VALUE_DATE_SEPARATOR+MisUtility.convertDateToString(budgetBean2.getBudgetToDate())+")");
						buffer.append("</option>");
					}
				}
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return null;
	}
	
	public ActionForward fetchActivity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<ActivityBean> activityBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		ActivityBean activityBean = new ActivityBean();
		SubComponentBean subComponentBean = new SubComponentBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("subComponentId"))){
				subComponentBean.setSubComponentId(request.getParameter("subComponentId"));
				activityBean.setSubComponent(subComponentBean);
				activityBeans = new TreeSet<ActivityBean>(activityDao.findActivity(activityBean, statusList));
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(activityBeans)){
					for (ActivityBean activityBean2 : activityBeans) {
						buffer.append("<option value=\"").append(activityBean2.getActivityId()).append("\">");
						buffer.append(activityBean2.getActivityId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+activityBean2.getActivityName());
						buffer.append("</option>");
					}
				}
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return null;
	}
	
	public ActionForward fetchSubComponent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Set<SubComponentBean> subComponentBeans = null;
			StringBuffer buffer = new StringBuffer();
			if(MisUtility.ifEmpty(request.getParameter("componentId"))){
				String componentId = request.getParameter("componentId");
				subComponentBeans = subComponentDao.getDistinctSubComponentCodes(componentId);

				log.debug("subComponentBeans\t"+subComponentBeans);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for(SubComponentBean subComponent: subComponentBeans){
					buffer.append("<option value=\"").append(subComponent.getSubComponentId()).append("\">");
					buffer.append(subComponent.getSubComponentId()).append(MISConstants.LABEL_VALUE_BEAN_SEPARATOR).append(subComponent.getSubComponentName());
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}		
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}	
	
	
}
