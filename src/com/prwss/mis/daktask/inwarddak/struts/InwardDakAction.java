package com.prwss.mis.daktask.inwarddak.struts;

import java.util.ArrayList;
import java.util.HashSet;
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
import org.apache.struts.util.LabelValueBean;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.daktask.documenttype.dao.DocumentTypeDao;
import com.prwss.mis.daktask.inwarddak.InwardDakBO;
import com.prwss.mis.daktask.inwarddak.dao.InwardDakBean;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;
import com.prwss.mis.sd.document.dao.DocumentNumberDAO;


public class InwardDakAction extends DispatchAction {
	Logger log = Logger.getLogger(InwardDakAction.class);
	private MISSessionBean misSessionBean;
	private InwardDakBO inwardDakBO;
	private DocumentNumberDAO documentNumberDao;
	private DocumentTypeDao documentTypeDao;
	
	
	public void setDocumentTypeDao(DocumentTypeDao documentTypeDao) {
		this.documentTypeDao = documentTypeDao;
	}


	public void setDocumentNumberDao(DocumentNumberDAO documentNumberDao) {
		this.documentNumberDao = documentNumberDao;
	}


	public void setInwardDakBO(InwardDakBO inwardDakBO) {
		this.inwardDakBO = inwardDakBO;
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
		InwardDakForm inwardDakForm = (InwardDakForm)form;
		try {
			
			List <InwardDakBean> inwardDakBeans = null;
			
			inwardDakBeans = inwardDakBO.findInwardDak(inwardDakForm, statusList);
			
			if(!MisUtility.ifEmpty(inwardDakBeans)){
				request.setAttribute("inwardDakList", inwardDakBeans);
//				log.debug("inside-----------getbean");
//				for (InwardDakBean inwardDakBean : inwardDakBeans) {
//					
//					inwardDakForm.setLocationId(inwardDakBean.getLocationId());	
//					inwardDakForm.setDocumentNo(inwardDakBean.getDocumentNo());		
//					inwardDakForm.setDocumentType(inwardDakBean.getDocumentType());
//					inwardDakForm.setDocumentReferenceNo(inwardDakBean.getDocumentRefNo());
//					inwardDakForm.setSenderAddress(inwardDakBean.getSenderAddress());
//					inwardDakForm.setSenderName(inwardDakBean.getSenderName());
//					inwardDakForm.setSubject(inwardDakBean.getSubject());
//					inwardDakForm.setRecieptDate(MisUtility.convertDateToString(inwardDakBean.getReceiptDate()));
//					inwardDakForm.setForwardedTo(inwardDakBean.getForwardedTo());
//				}
				
			}else{
				refreshInwardDakForm(inwardDakForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.find","Inquiry of Inward Dak");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshInwardDakForm(inwardDakForm);
		return mapping.findForward("display");
	}
	
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		
		this.setAttrib(request);
		request.setAttribute("level2","true");
		InwardDakForm inwardDakForm = (InwardDakForm)form;
		
		
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
			
			if(inwardDakForm.getDocumentType().equals(null)||inwardDakForm.getDocumentType().trim().equals("")||inwardDakForm.getDocumentType().equals("''")){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values:DocumentType");
			}
			if(inwardDakForm.getDocumentReferenceNo().equals(null)||inwardDakForm.getDocumentReferenceNo().trim().equals("")||inwardDakForm.getDocumentReferenceNo().equals("''")){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values:Reference Number");
			}
			
			if(!MisUtility.ifEmpty(inwardDakForm.getRecieptDate())){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values:Reference Number");
			}
			DocumentNumberBean documentNumebrBean=new DocumentNumberBean();
			
			documentNumebrBean.setLocationId(inwardDakForm.getLocationId());
			
			System.out.println("Document Type-------------------"+inwardDakForm.getDocumentType());
			
			documentNumebrBean.setDocumentType(MISConstants.DAK_TASK_INWARD_DAK_TYPE);
			
			List <DocumentNumberBean> db = documentNumberDao.getDocumentNumberBeans(documentNumebrBean);
			status = inwardDakBO.saveInwardDak(inwardDakForm, misSessionBean, db.get(0));
			
			
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Inward Dak","DocumentNumber -->"+(db.get(0).getLastNumber()-1));
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Inward Dak");
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
			}else{
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Inward Dak");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Inward dak");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshInwardDakForm(inwardDakForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		log.debug("inside update");
		this.setAttrib(request);
		InwardDakForm inwardDakForm = (InwardDakForm)form;
		
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
			
			if(inwardDakForm.getDocumentType().equals(null)||inwardDakForm.getDocumentType().trim().equals("")||inwardDakForm.getDocumentType().equals("''")){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			DocumentNumberBean documentNumberBean=new DocumentNumberBean();
			
				documentNumberBean.setLastNumber(Long.valueOf(inwardDakForm.getDocumentNo()).longValue());					
				status = inwardDakBO.updateInwardDak(inwardDakForm, misSessionBean, documentNumberBean);			
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save","Inward Dak","DocumentNumber -->"+(documentNumberBean.getLastNumber()));
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Inward Dak");
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
			}else{
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Inward Dak");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Inward Dak");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshInwardDakForm(inwardDakForm);
		return mapping.findForward("display");
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.getSession().setAttribute("documentTypes",getDocumentTypes());
		InwardDakForm inwardDakForm = (InwardDakForm)form;
		refreshInwardDakForm(inwardDakForm);
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "documentNo@locationId@recieptDate");
		request.setAttribute("d__auto", "documentNo");	
	}
	
	private void refreshInwardDakForm(InwardDakForm inwardDakForm)
	{
		inwardDakForm.setDocumentNo(null);
		inwardDakForm.setDocumentReferenceNo(null);
		inwardDakForm.setDocumentType(null);
		inwardDakForm.setLocationId(null);
		inwardDakForm.setRecieptDate(null);
		inwardDakForm.setSenderAddress(null);
		inwardDakForm.setSenderName(null);
		inwardDakForm.setSubject(null);	
		inwardDakForm.setForwardedTo(null);
		
	}
	private Set<LabelValueBean> getDocumentTypes()
	{
		Set<LabelValueBean> documentType=new HashSet<LabelValueBean>() ;
		List<String> documentTypeBeans=null;
	try{
		documentTypeBeans=documentTypeDao.getDocumentType();
		for(String document:documentTypeBeans)
		{
			documentType.add(new LabelValueBean("select",null));
			documentType.add(new LabelValueBean(document,document));
		}
		
	} catch (DataAccessException e){
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		return documentType;
	}
	
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		String documentNo = request.getParameter("documentNumber");
		InwardDakForm inwardDakForm = (InwardDakForm)form;
		refreshInwardDakForm(inwardDakForm);
		inwardDakForm.setDocumentNo(documentNo);
		inwardDakForm.setLocationId(request.getParameter("locationId"));
		System.out.println("inwardDakForm"+inwardDakForm);
		List <InwardDakBean> inwardDakBeans =  inwardDakBO.findInwardDak(inwardDakForm, null);
		if(!MisUtility.ifEmpty(inwardDakBeans)){
			for (InwardDakBean bean : inwardDakBeans) {
				inwardDakForm.setLocationId(bean.getLocationId());	
				inwardDakForm.setDocumentNo(bean.getDocumentNo());		
				inwardDakForm.setDocumentType(bean.getDocumentType());
				inwardDakForm.setDocumentReferenceNo(bean.getDocumentRefNo());
				inwardDakForm.setSenderAddress(bean.getSenderAddress());
				inwardDakForm.setSenderName(bean.getSenderName());
				inwardDakForm.setSubject(bean.getSubject());
				inwardDakForm.setRecieptDate(MisUtility.convertDateToString(bean.getReceiptDate()));
				inwardDakForm.setForwardedTo(bean.getForwardedTo());
			}
			
		}
		
		return mapping.findForward("display");
	}
}
