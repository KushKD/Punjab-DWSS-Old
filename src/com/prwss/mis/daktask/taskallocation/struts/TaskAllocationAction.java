package com.prwss.mis.daktask.taskallocation.struts;

import java.util.ArrayList;
import java.util.Collection;
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
import com.prwss.mis.daktask.inwarddak.dao.InwardDakBean;
import com.prwss.mis.daktask.inwarddak.dao.InwardDakDao;
import com.prwss.mis.daktask.taskallocation.TaskAllocationBO;
import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class TaskAllocationAction extends DispatchAction {
	Logger log= Logger.getLogger(TaskAllocationForm.class);
	
	private TaskAllocationForm taskAllocationForm;
	private TaskAllocationBO taskAllocationBO;
	private MISSessionBean misSessionBean;
	private InwardDakDao inwardDakDao;
	
	public void setTaskAllocationBO(TaskAllocationBO taskAllocationBO)
	{
		this.taskAllocationBO=taskAllocationBO;
	}
	public void setInwardDakDao(InwardDakDao inwardDakDao)
	{
		this.inwardDakDao=inwardDakDao;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		List <InwardDakBean> inwardDakBeans=null;
		taskAllocationForm=(TaskAllocationForm)form;
		log.debug("in action---------"+taskAllocationForm.toString());
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean)request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}		
		this.setAttrib(request);
		try{
		String mode = (String) request.getParameter("d__mode");
		List<String> statusList = new ArrayList<String>();
		if (mode != null && MISConstants.D_MODE_ENQUIRE.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		this.setAttrib(request);
		
		
		InwardDakBean inwardDakBean=new InwardDakBean();
		if(MisUtility.ifEmpty(taskAllocationForm.getLocationId()))
		{
		inwardDakBean.setLocationId(taskAllocationForm.getLocationId());
		}else
		{
			log.debug("The taskAllocationForm does not contain location id which is essential for search");
			throw new MISException("Please Provide Location Id For Search");
		}
//		if(MisUtility.ifEmpty(taskAllocationForm.getAllocationDate()))
//		{
//		inwardDakBean.setReceiptDate(MisUtility.convertStringToDate(taskAllocationForm.getAllocationDate()));
//		}
		
		log.debug("getting inwardDakBean-------------"+inwardDakBean.toString());
		
		inwardDakBeans=inwardDakDao.getInwardDakBeans(inwardDakBean, statusList);
		
		log.debug("got inwardDakBean-------------");
		if(!MisUtility.ifEmpty(inwardDakBeans))
		{
			request.setAttribute("level2", "true");
			
			log.debug("Setting Datagrid-------------");
			
			log.debug("inwarddakbean size-------------"+inwardDakBeans.size());	
			log.debug("inwarddakbean-------------"+inwardDakBeans.get(0).toString());
			
//				taskAllocationForm.setTaskAllocationDataGrid(getTaskDataGrid(inwardDakBeans));
			log.debug("Datagrid Set-------------");
			
		}else
		{
			refreshTaskAllocationForm(taskAllocationForm, misSessionBean);
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		}
		}catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Error Occured While retrieving the records");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		
		
		return mapping.findForward("display");
	
	}
	@SuppressWarnings("unchecked")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		log.debug("inside update");
		this.setAttrib(request);
		request.setAttribute("level2","true");
		TaskAllocationForm taskAllocationForm=(TaskAllocationForm)form;
		List<InwardDakBean> inwardDakBeans=new ArrayList<InwardDakBean>();
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
//			Collection<TaskAllocationGridBean> taskAllocationGridBeans=taskAllocationForm.getTaskAllocationDataGrid().getModifiedData();
			log.debug("inside update-1");			
//			for(TaskAllocationGridBean taskAllocationGridBean:taskAllocationGridBeans)
//			{
//				InwardDakBean inbean=new InwardDakBean();
//				inbean.setDocumentNo(taskAllocationGridBean.getDocumentNumber());
//				inbean.setAssignTo(taskAllocationGridBean.getAssignTo());
//				inbean.setDocumentType(taskAllocationGridBean.getDocumentType());
//				inbean.setTargetDate(MisUtility.convertStringToDate(taskAllocationGridBean.getTargetDate()));
//				inbean.setSenderName(taskAllocationGridBean.getSenderName());
//				inwardDakBeans.add(inbean);				
//				
//			}
			log.debug("inside update-2 size  "+inwardDakBeans.size());
			status =taskAllocationBO.updateTaskAllocationDeatils(inwardDakBeans, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Target Details","Task Allocation");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("error.update","Target Details","Task Allocation");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			}
			
		}catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Task Allocation");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			
		
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Task Allocation");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshTaskAllocationForm(taskAllocationForm, misSessionBean);
		return mapping.findForward("display");
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		TaskAllocationForm taskAllocationForm = (TaskAllocationForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		try {
			refreshTaskAllocationForm(taskAllocationForm, misSessionBean);		
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
		}
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId@allocationDate");
	}
	
	private void refreshTaskAllocationForm(TaskAllocationForm taskAllocationForm,MISSessionBean misSessionBean)
	{
//		taskAllocationForm.setAllocationDate(null);
//		taskAllocationForm.setLocationId(null);
//		taskAllocationForm.setTaskAllocationDataGrid(getTaskDataGrid(null));
		
	}
	
	private Datagrid getTaskDataGrid(Collection <InwardDakBean> inBeans) {
	    Datagrid taskDataGrid= null;
		List<TaskAllocationGridBean> taskAllocationGridBeans = new ArrayList<TaskAllocationGridBean>();
		try {
			taskDataGrid = Datagrid.getInstance();
			taskDataGrid.setDataClass(TaskAllocationGridBean.class);
			if (!MisUtility.ifEmpty(inBeans)) {
				
				for (InwardDakBean inwardDakBean : inBeans) {	
					TaskAllocationGridBean taskAllocationGridBean=new TaskAllocationGridBean();
					taskAllocationGridBean.setDocumentNumber(inwardDakBean.getDocumentNo());
					taskAllocationGridBean.setDocumentType(inwardDakBean.getDocumentType());
					taskAllocationGridBean.setSenderName(inwardDakBean.getSenderName());
					taskAllocationGridBean.setAssignTo(inwardDakBean.getAssignTo());
					taskAllocationGridBean.setTargetDate(MisUtility.convertDateToString(inwardDakBean.getTargetDate()));
					taskAllocationGridBeans.add(taskAllocationGridBean);					
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		taskDataGrid.setData(taskAllocationGridBeans);
		return taskDataGrid;
	}
}
