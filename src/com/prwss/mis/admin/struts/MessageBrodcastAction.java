package com.prwss.mis.admin.struts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.prwss.mis.admin.MessageBrodcastBO;
import com.prwss.mis.admin.MessageBrodcastBean;
import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.tender.dao.AdvUploadBean;
import com.prwss.mis.tender.struts.AdvUploadForm;
import com.prwss.mis.tender.struts.TenderManagementForm;

public class MessageBrodcastAction extends DispatchAction{
	
	private MISSessionBean misSessionBean;
	private MessageBrodcastBO messageBrodcastBO;

	
 
	
	public void setMessageBrodcastBO(MessageBrodcastBO messageBrodcastBO) {
		this.messageBrodcastBO = messageBrodcastBO;
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			//System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		MessageBrodcastForm messageBrodcastForm = (MessageBrodcastForm) form;
		long status = 0;
		try {
			 System.out.println("Inside Save");
			//  LocationBean locationBean = new LocationBean();
			MessageBrodcastBean messageBrodcastBean = new MessageBrodcastBean();
			messageBrodcastBean.setMessageDetail(messageBrodcastForm.getMessageDetail());
			messageBrodcastBean.setExpiryDate(MisUtility.convertStringToDate(messageBrodcastForm.getExpiryDate()));
			
			  
			 
			  status = messageBrodcastBO.saveBrodcastedMessage(messageBrodcastBean, misSessionBean);
			  System.out.println(status);
			  if (status>0){
				  		ActionMessages errors= new ActionMessages();
						ActionMessage message = new ActionMessage("message.bodcast.success.save","Message Detail Saved for Message Id "+status);
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveMessages(request, errors);
					//	refreshAdvUploadForm(advUploadForm);
						refreshMessageBrodcastForm(messageBrodcastForm);
				} else {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", "Message Description Failed");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS004.equals(e.getCode())) {
				//System.out.println("in MIS004");
				log.error(e.getLocalizedMessage(),e);
				//e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("tender.upload.key.field");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}if (MISExceptionCodes.MIS003.equals(e.getCode())) {
				//System.out.println("in MIS003");
				log.error(e.getLocalizedMessage(),e);
				//e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("tender.upload.file.type");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				//System.out.println("in MIS001");
				log.error(e.getLocalizedMessage(),e);
				//e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("tender.upload.duplicate");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			
		}catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			//e.printStackTrace();
			//log.error(e.getLocalizedMessage(),e);
			//e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Tender");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		  
		return mapping.findForward("display");
	}
	
	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException,SQLException{
	 
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		String mode = (String) request.getParameter("d__mode");
		System.out.println("Mode is----:"+request.getParameter("d__mode"));
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
		MessageBrodcastForm messageBrodcastForm = (MessageBrodcastForm)form;
	
		List<MessageBrodcastBean> messageBrodcastBeans = null;
		messageBrodcastBeans = messageBrodcastBO.findBrodcastedMessages(messageBrodcastForm,statusList);
		if(!MisUtility.ifEmpty(messageBrodcastBeans)){
			request.setAttribute("level2", "true");		
							MessageBrodcastBean messageBrodcastBean = messageBrodcastBeans.get(0);
							messageBrodcastForm.setMessageId(messageBrodcastBean.getMessageId());
							messageBrodcastForm.setMessageDetail(messageBrodcastBean.getMessageDetail());
							messageBrodcastForm.setExpiryDate(MisUtility.convertDateToString(messageBrodcastBean.getExpiryDate()));
			 		}else{
	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//TenderManagementForm tenderManagementForm = (TenderManagementForm) form;
		System.out.println("update list h ji ");
		boolean status = false;
		try {
			StringBuffer stringMessage = new StringBuffer();
			this.setAttrib(request);
			//tenderManagementForm = setLocation(tenderManagementForm);
			MessageBrodcastForm messageBrodcastForm = (MessageBrodcastForm)form;
		
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				log.debug(" Your session timed out");
				return mapping.findForward("login");
			}
			
			
			status = messageBrodcastBO.updateMesageBrodcast(messageBrodcastForm, misSessionBean);
			System.out.println("hhhhhhhfffffff"+status);
			if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("message.bodcast.success.save","Message Detail Saved for Message Id "+messageBrodcastForm.getMessageId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
				refreshMessageBrodcastForm(messageBrodcastForm);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Message Description Failed");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch(MISException e){
			if(MISExceptionCodes.MIS012.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.openingDate.Error",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}	catch (Exception e) {
			e.printStackTrace();
			StackTraceElement l = new Exception().getStackTrace()[0]; 
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.update",e.toString()+ " at line number "+l.getClassName()+"/"+l.getMethodName()+":"+l.getLineNumber());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.debug("Error in updating tender \t" + e);
		}
		log.debug("Update Status"+ status);
		 
		return mapping.findForward("display");
	}
	
	
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		MessageBrodcastForm messageBrodcastForm = (MessageBrodcastForm)form;
		System.out.println("Message Brodcast Unspecified");
		refreshMessageBrodcastForm(messageBrodcastForm);
		//TenderManagementForm tenderManagementForm = (TenderManagementForm) form;
		//refreshTenderForm(tenderManagementForm);
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "MessageId");
		request.setAttribute("d__auto", "MessageId");
		 
	}
	
	
	private void refreshMessageBrodcastForm(MessageBrodcastForm messageBrodcastForm) {
		
		 messageBrodcastForm.setMessageId(0);
		 messageBrodcastForm.setMessageDetail(null);
		 messageBrodcastForm.setExpiryDate(null);
	}
}
