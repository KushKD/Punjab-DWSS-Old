package com.prwss.mis.admin.struts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.block.dao.BlockBO;
import com.prwss.mis.masters.block.dao.BlockBean;
import com.prwss.mis.masters.district.dao.DistrictBean;
import com.prwss.mis.masters.district.dao.DistrictDao;

public class BlockMasterAction extends DispatchAction {
	private BlockBO blockBO;
	private DistrictDao districtDao;

	public void setDistrictDao(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}

	private MISSessionBean misAuditBean = new MISSessionBean();

	public void setBlockBO(BlockBO blockBO) {
		this.blockBO = blockBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		try {
			BlockForm blockForm = (BlockForm) form;
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
			if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			}
			this.setAttrib(request);
			request.setAttribute("level2", "true");
			System.out.println("find :" + request.getParameter("d__mode"));
			if (request.getSession().getAttribute("districts") != null) {
				getDistrictIds(request);
			}
			List<BlockBean> blockBeanList = null;
			System.out.println(blockForm);
			blockBeanList = blockBO.findBlock(blockForm, statusList);
			System.out.println(blockBeanList);
			
			if(!MisUtility.ifEmpty(blockBeanList)){
				request.setAttribute("blockBeanList", blockBeanList);
				}else{
					//refershZoneForm(zoneform);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		try{
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
		BlockForm blockForm = (BlockForm) form;
		boolean status = blockBO.deleteBlock(blockForm, misAuditBean);
		log.debug("Block delete" + status);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Block Id ---->"+blockForm.getBlockId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Block Id ---->"+blockForm.getBlockId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","Block Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Block Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		if (request.getSession().getAttribute("districts") != null) {
			getDistrictIds(request);
		}
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		String mode = (String) request.getParameter("d__mode");
		List<String> statusList = new ArrayList<String>();
		if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {				
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_REPOST.equalsIgnoreCase(mode.trim())) {				
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}
		BlockForm blockForm = (BlockForm) form;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
		boolean status = blockBO.updateBlock(blockForm, misAuditBean,statusList);
		log.debug("Block update" + status);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Block Id ---->"+blockForm.getBlockId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Block Id ---->"+blockForm.getBlockId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else {
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);				
			}
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Block Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		refreshBlockForm(blockForm);
		System.out.println("modify :" + request.getParameter("d__mode"));
		if (request.getSession().getAttribute("districts") != null) {

			getDistrictIds(request);

		}
		return mapping.findForward("display");
	}

	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		String blockId = request.getParameter("blockId");
		BlockForm blockForm = (BlockForm) form;
		blockForm.setBlockId(blockId);
		List<BlockBean> blockBeanList = blockBO.findBlock(blockForm, null);

		if (blockBeanList == null) {
			log.error("blockBeanList is\t" + blockBeanList);
		}

		for (BlockBean blockBean : blockBeanList) {
			if (blockId.equalsIgnoreCase(blockBean.getBlockId())) {

				request.setAttribute("blockBean", blockBean);
			}
		}
		if (request.getSession().getAttribute("districts") != null) {
			getDistrictIds(request);
		}
		
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				return mapping.findForward("login");
			}
		boolean status = false;
		BlockForm blockForm = (BlockForm) form;
		try {
			status = blockBO.saveBlock(blockForm, misAuditBean);
			 if(status){
					ActionMessages messages= new ActionMessages();
					ActionMessage message = new ActionMessage("success.save","Block Id ---->"+blockForm.getBlockId());
					messages.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, messages);
				}else{
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.save","Block Id ---->"+blockForm.getBlockId());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
			}  catch (MISException e) {
				if (MISExceptionCodes.MIS001.equals(e.getCode())) {
					log.error(e.getLocalizedMessage(),e);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				} else {
					log.error(e.getLocalizedMessage(),e);
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", e.getMessage());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);				
				}
			} catch (Exception e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Block Master information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				log.error(e);
				e.printStackTrace();
			}	 
			if (request.getSession().getAttribute("districts") != null) {
				getDistrictIds(request);
			}
		
		refreshBlockForm(blockForm);
		return mapping.findForward("display");
	}

	private Set<LabelValueBean> getDistrictIds(HttpServletRequest request) {
		System.out.println("in getdistrict");
		Set<LabelValueBean> districts = null;
		Set<DistrictBean> districtCodes = null;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);		
		try {
			districtCodes = districtDao.getDistinctDistrictCodes(statusList);
			districts = new HashSet<LabelValueBean>();
			for (DistrictBean districtCode : districtCodes) {
				districts.add(new LabelValueBean(districtCode.getDistrictId() + MISConstants.LABEL_VALUE_BEAN_SEPARATOR
						+ districtCode.getDistrictName(), districtCode.getDistrictId()));

			}
		} catch (Exception e) {
			log.error(e);
		}
		System.out.println("before setting");
		return districts;
	}

	private void refreshBlockForm(BlockForm blockForm) {
		blockForm.setBlockId("");
		blockForm.setBlockName("");
		blockForm.setBlockIds(null);
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("save:" + request.getParameter("d__mode"));
		this.setAttrib(request);
		BlockForm blockForm = (BlockForm) form;
		try{
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
				else
				{
					return mapping.findForward("login");
				}
		boolean status = blockBO.postBlock(blockForm, misAuditBean);
		log.debug(status);
		 if(status){
				ActionMessages messages= new ActionMessages();
				ActionMessage message = new ActionMessage("success.post","Block Id ---->"+blockForm.getBlockId());
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Block Id ---->"+blockForm.getBlockId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}  catch (MISException e) {
		ActionErrors errors = new ActionErrors();
		ActionMessage message = new ActionMessage("fatal.error.save","Block Master information");
		errors.add(ActionMessages.GLOBAL_MESSAGE, message);
		saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Block Master information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}	 
		return mapping.findForward("display");
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");}}
			else
			{
				return mapping.findForward("login");
			}
		this.setAttrib(request);
		Set<LabelValueBean> districts = getDistrictIds(request);
		request.getSession().setAttribute("districts", districts);		
		BlockForm blockForm = (BlockForm)form;
		refreshBlockForm(blockForm);
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
	
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "districtId@blockId");
		request.setAttribute("d__auto", "blockId");
	}


}
