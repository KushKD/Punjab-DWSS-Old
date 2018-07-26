package com.prwss.mis.daktask.outwarddak.struts;

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
import com.prwss.mis.daktask.outwarddak.OutwardDakBO;
import com.prwss.mis.daktask.outwarddak.dao.OutwardDakBean;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;

public class OutwardDakAction extends DispatchAction {
	Logger log=Logger.getLogger(OutwardDakAction.class);
	private MISSessionBean misSessionBean;
	private OutwardDakBO outwardDakBO;
	private DocumentNumberDAO documentNumberDao;
	
	
	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}

	public void setOutwardDakBO(OutwardDakBO outwardDakBO) {
		this.outwardDakBO = outwardDakBO;
	}
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {

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
		if (mode != null) {
			
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}		
		this.setAttrib(request);
		OutwardDakForm outwardDakForm=(OutwardDakForm)form;
		try {
			
			List<OutwardDakBean> outwardDakBeans=null;
			outwardDakBeans=outwardDakBO.findOutwardDak(outwardDakForm, statusList);
			
			if(!MisUtility.ifEmpty(outwardDakBeans))
			{
				request.setAttribute("outwardDakBean", outwardDakBeans);
//				for(OutwardDakBean outwardDakBean:outwardDakBeans)
//				{
//					outwardDakForm.setDispatchDate(MisUtility.convertDateToString(outwardDakBean.getDispatchDate()));
//					outwardDakForm.setDispatchThrough(outwardDakBean.getDispatchThrough());
//					outwardDakForm.setDocumentReferenceNo(outwardDakBean.getDocumentRefNo());
//					outwardDakForm.setDocumentType(outwardDakBean.getDocumentType());
//					outwardDakForm.setLocationId(outwardDakBean.getLocationId());
//					outwardDakForm.setPostalCharge(outwardDakBean.getPostal_charge());
//					outwardDakForm.setReceiverAddress(outwardDakBean.getReceiver_Address());
//					outwardDakForm.setReceiverName(outwardDakBean.getReceiverName());
//					outwardDakForm.setSubject(outwardDakBean.getSubject());
//					outwardDakForm.setAfterIssue(outwardDakBean.getAfterIssue());
//					outwardDakForm.setEnclosures(outwardDakBean.getEnclosures());
//				}
				
			}else{
				refreshOutwardDakForm(outwardDakForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		
		}catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.find","Inquiry of Inward Dak");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshOutwardDakForm(outwardDakForm);
		return mapping.findForward("display");
	}
		
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
			log.debug("in save");
			this.setAttrib(request);
			request.setAttribute("level2","true");
			OutwardDakForm outwardDakForm = (OutwardDakForm)form;
			long outwardDakId = 0;
			try {
				if (request.getSession().getAttribute("misSessionBean") != null) {
					{
						misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
					}
				} else {
					System.out.println("NO AUDIT");
					return mapping.findForward("login");
				}
				if(!MisUtility.ifEmpty(outwardDakForm.getDispatchDate())||!MisUtility.ifEmpty(outwardDakForm.getReceiverName())){
					throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values:Reference Number");
				}
				
				DocumentNumberBean documentNumebrBean=new DocumentNumberBean();

				documentNumebrBean.setLocationId(outwardDakForm.getLocationId());
				documentNumebrBean.setDocumentType(MISConstants.DAK_TASK_OUTWARD_DAK_TYPE);
				
				
				List <DocumentNumberBean> db = documentNumberDao.getDocumentNumberBeans(documentNumebrBean);
				log.debug("getting Status");
				outwardDakId = outwardDakBO.saveOutwardDak(outwardDakForm, misSessionBean,db.get(0));
				
				if (outwardDakId!=0){
					ActionMessages errors = new ActionMessages();
					ActionMessage message = new ActionMessage("success.save","Outward Dak","Auto Generated Document Receipt Number -->"+outwardDakId);
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, errors);

				} else {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", "Outward Dak");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}catch (MISException e) {
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
				}else{
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();	
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Saving of Outward Dak");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				}
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Saving of Outward dak");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}
			refreshOutwardDakForm(outwardDakForm);
			return mapping.findForward("display");
			
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
			this.setAttrib(request);
			OutwardDakForm outwardDakForm = (OutwardDakForm)form;
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
//				if(outwardDakForm.getDocumentReferenceNo().equals(null)||outwardDakForm.getDocumentReferenceNo().trim().equals("")||outwardDakForm.getDocumentReferenceNo().equals("''")){
//					throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values:Reference Number");
//				}
				status = outwardDakBO.updateOutwardDak(outwardDakForm, misSessionBean);
				if (status){
					ActionMessages errors = new ActionMessages();
					ActionMessage message = new ActionMessage("success.save","Outward Dak","Document Receipt Number -->"+outwardDakForm.getDocumentReferenceNo());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, errors);

				} else {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", "Outward Dak");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}catch (MISException e) {
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
				}else{
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();	
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Saving of Outward Dak");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				}
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Saving of Outward dak");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}
			refreshOutwardDakForm(outwardDakForm);
			return mapping.findForward("display");
			
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		OutwardDakForm outwardDakForm = (OutwardDakForm)form;
		refreshOutwardDakForm(outwardDakForm);
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId@documentReferenceNo@dispatchDate");
		request.setAttribute("d__auto", "documentReferenceId");
	}
	
	
	
	private void refreshOutwardDakForm(OutwardDakForm outwardDakForm)
	{
		outwardDakForm.setPostalCharge(0.0);
		outwardDakForm.setDocumentReferenceNo(0);
		outwardDakForm.setDocumentType(null);
		outwardDakForm.setLocationId(null);
		outwardDakForm.setDispatchDate(null);
		outwardDakForm.setReceiverAddress(null);
		outwardDakForm.setReceiverName(null);
		outwardDakForm.setSubject(null);	
		outwardDakForm.setDispatchThrough(null);
		outwardDakForm.setAfterIssue(null);
		outwardDakForm.setEnclosures(null);
	}
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		String documentNo = request.getParameter("documentNo");
		OutwardDakForm outwardDakForm=(OutwardDakForm)form;
		refreshOutwardDakForm(outwardDakForm);
		outwardDakForm.setDocumentReferenceNo(new Long(documentNo));
		List <OutwardDakBean> outwardDakBeans=outwardDakBO.findOutwardDak(outwardDakForm, null);
		if(!MisUtility.ifEmpty(outwardDakBeans)){
			for (OutwardDakBean bean : outwardDakBeans) {
				outwardDakForm.setDispatchDate(MisUtility.convertDateToString(bean.getDispatchDate()));
				outwardDakForm.setDispatchThrough(bean.getDispatchThrough());
				outwardDakForm.setDocumentReferenceNo(bean.getDocumentRefNo());
				outwardDakForm.setDocumentType(bean.getDocumentType());
				outwardDakForm.setLocationId(bean.getLocationId());
				outwardDakForm.setPostalCharge(bean.getPostal_charge());
				outwardDakForm.setReceiverAddress(bean.getReceiver_Address());
				outwardDakForm.setReceiverName(bean.getReceiverName());
				outwardDakForm.setSubject(bean.getSubject());
				outwardDakForm.setAfterIssue(bean.getAfterIssue());
				outwardDakForm.setEnclosures(bean.getEnclosures());
			}
			
		}
		
		return mapping.findForward("display");
	}
}
