package com.prwss.mis.masters.committee.struts;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.prwss.mis.masters.bank.dao.BankMasterBean;
import com.prwss.mis.masters.bank.dao.BankMasterDao;
import com.prwss.mis.masters.committee.CommitteeBO;
import com.prwss.mis.masters.committee.dao.CommitteeBankBean;
import com.prwss.mis.masters.committee.dao.CommitteeBean;
import com.prwss.mis.masters.committee.dao.CommitteeMemberBean;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderDao;
import com.prwss.mis.masters.village.dao.VillageBean;
import com.prwss.mis.masters.village.dao.VillageDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class CommitteeAction extends DispatchAction {
	
	Logger log = Logger.getLogger(CommitteeAction.class);
	private MISSessionBean misSessionBean;
	private CommitteeBO committeeBO;
	private SchemeHeaderDao schemeHeaderDao;
	private BankMasterDao bankMasterDao;
	private LocationDao locationDao;
	private VillageDao villageDao;
	
	public void setVillageDao(VillageDao villageDao) {
		this.villageDao = villageDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setBankMasterDao(BankMasterDao bankMasterDao) {
		this.bankMasterDao = bankMasterDao;
	}

	public void setCommitteeBO(CommitteeBO committeeBO) {
		this.committeeBO = committeeBO;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("In Find Commette Action");
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
		CommitteeForm committeeForm = (CommitteeForm)form;
		List<CommitteeBean> committeeBeans = null;
		committeeBeans = committeeBO.findCommittee(committeeForm, statusList);
		
		if(!MisUtility.ifEmpty(committeeBeans)){
			request.setAttribute("level2", "true");
		for (CommitteeBean committeeBean : committeeBeans) {
			committeeForm.setCommitteeConstitutionDate(MisUtility.convertDateToString(committeeBean.getCommitteeConstitutionDate()));
			committeeForm.setLocationId(committeeBean.getLocationId());
			request.setAttribute("locationId", committeeBean.getLocationId());
			request.setAttribute("blockId", committeeBean.getBlockId());
			request.setAttribute("villageId", committeeBean.getVillageId());
			request.setAttribute("schemeId", committeeBean.getSchemeId());
			committeeForm.setSchemeId(committeeBean.getSchemeId());
			committeeForm.setCommitteeName(committeeBean.getCommitteeName());
			committeeForm.setCommitteeId(committeeBean.getCommitteeId());
			committeeForm.setsLCStatus(committeeBean.getsLCStatus());
			committeeForm.setCommitteeBankGrid(getCommitteeBankDatagrid(committeeBean.getCommitteeBankBeans()));
			committeeForm.setCommitteeMemberGrid(getCommitteeMemberDatagrid(committeeBean.getCommitteeMemberBeans()));
		}
		}else{
			refreshCommitteeForm(committeeForm);
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		}
		return mapping.findForward("display");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("Delete ... IEC Progress");
		this.setAttrib(request);
		CommitteeForm committeeForm = (CommitteeForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = committeeBO.deleteCommittee(committeeForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Committee Information","Committee -->"+committeeForm.getCommitteeName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete", "Committee Information","Committee -->"+committeeForm.getCommitteeName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Committee Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Committee Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshCommitteeForm(committeeForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		CommitteeForm committeeForm = (CommitteeForm)form;
		long committeeId = 0;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
			
			if(committeeForm.getsLCStatus().equalsIgnoreCase("SLC")){
			if(!MisUtility.ifEmpty(committeeForm.getSchemeId())){
				throw new MISException(MISExceptionCodes.MIS001,"Scheme Id missing");
			}
			}else if(committeeForm.getsLCStatus().equalsIgnoreCase("GPWSC")){
				
				if(!MisUtility.ifEmpty(committeeForm.getVillageId())){
					throw new MISException(MISExceptionCodes.MIS002,"Village Id missing");
				}
			}
			
			if(!MisUtility.ifEmpty(committeeForm.getCommitteeName())){
				throw new MISException(MISExceptionCodes.MIS004,"committee name is missing");
			}
			committeeId = committeeBO.saveCommittee(committeeForm, misSessionBean);
			if (committeeId!=0){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "Committee Information","Auto Generated Committee ID -->"+committeeId);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Committee Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				System.out.println("INside MIS001");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry.error","scheme code is required for committee type 'SLC'");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			if (MISExceptionCodes.MIS002.equals(e.getCode())){
				System.out.println("INside MIS002");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry.error","village code is required for committee type 'GPWSC'");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			if (MISExceptionCodes.MIS004.equals(e.getCode())){
				System.out.println("INside MIS004");
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry.error", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}if (MISExceptionCodes.MIS011.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("duplicate.entry.error", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}catch(Exception e){
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save", "Committee Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshCommitteeForm(committeeForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		CommitteeForm committeeForm = (CommitteeForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = committeeBO.updateCommittee(committeeForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update","Committee Information","Committee -->"+committeeForm.getCommitteeName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update","Committee Information","Committee -->"+committeeForm.getCommitteeName());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Committee Information");
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
		refreshCommitteeForm(committeeForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		CommitteeForm committeeForm = (CommitteeForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = committeeBO.postCommittee(committeeForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.post", "Committee Information","Scheme ID -->"+committeeForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post", "Committee Information","Scheme ID -->"+committeeForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Committee Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Committee Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
			refreshCommitteeForm(committeeForm);
		
		return mapping.findForward("display");
	}
	
	
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "schemeId@locationId@sLCStatusId@villageId@blockId");
		request.setAttribute("d__auto", "bankBranchId@bankNameId");
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		CommitteeForm committeeForm = (CommitteeForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		refreshCommitteeForm(committeeForm);
		return mapping.findForward("display");
	}

	private void refreshCommitteeForm(CommitteeForm committeeForm){
		committeeForm.setSchemeId(null);
		committeeForm.setsLCStatus(null);
		committeeForm.setCommitteeConstitutionDate(null);
		committeeForm.setCommitteeMemberGrid(getCommitteeMemberDatagrid(null));
		committeeForm.setCommitteeBankGrid(getCommitteeBankDatagrid(null));
		committeeForm.setCommitteeName(null);
	}
	
	
	private Datagrid getCommitteeBankDatagrid(Set<CommitteeBankBean> committeeBankBeans) {
		Datagrid committeeBankDatagrid = null;
		try {
			committeeBankDatagrid = Datagrid.getInstance();
			committeeBankDatagrid.setDataClass(CommitteeBankBean.class);
			if(!MisUtility.ifEmpty(committeeBankBeans)){
			List<CommitteeBankBean> committeeBankBeans2 = new ArrayList<CommitteeBankBean>(committeeBankBeans);
			committeeBankDatagrid.setData(committeeBankBeans2);		
			}else{
				List<CommitteeBankBean> committeeBankBeans2 = new ArrayList<CommitteeBankBean>();
			committeeBankDatagrid.setData(committeeBankBeans2);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return committeeBankDatagrid;
	}
	
	private Datagrid getCommitteeMemberDatagrid(Set<CommitteeMemberBean> committeeMemberBeans) {
		Datagrid committeeMemberDatagrid = null;
		try {
			committeeMemberDatagrid = Datagrid.getInstance();
			committeeMemberDatagrid.setDataClass(CommitteeMemberBean.class);
			if(!MisUtility.ifEmpty(committeeMemberBeans)){
			List<CommitteeMemberBean> committeeMemberBeans2 = new ArrayList<CommitteeMemberBean>(committeeMemberBeans);
			committeeMemberDatagrid.setData(committeeMemberBeans2);		
			}else{
				List<CommitteeMemberBean> committeeMemberBeans2 = new ArrayList<CommitteeMemberBean>();
			committeeMemberDatagrid.setData(committeeMemberBeans2);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return committeeMemberDatagrid;
	}
	
	
	public ActionForward fetchBankIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		BankMasterBean bankMasterBean = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
		/*	if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				LocationBean locationBean = new LocationBean();
				locationBean.setLocationId(request.getParameter("locationId"));
				locationBean = locationDao.getLocation(locationBean);
				bankMasterBean = new BankMasterBean();
				LocationBean bean = new LocationBean();
				bean.setLocationId(locationBean.getParentLocation());
				bankMasterBean.setDistrcit(bean);
				List<BankMasterBean> bankMasterBeans = bankMasterDao.findBank(bankMasterBean, statusList);*/
			
			if(MisUtility.ifEmpty(request.getParameter("villageId"))){
				VillageBean villageBean = new VillageBean();
				villageBean.setVillageId(request.getParameter("villageId"));
				List<VillageBean> villageBeans = villageDao.findVillage(villageBean, statusList);
				String districtId = villageBeans.get(0).getDistrictId();
				System.out.println("@@@@@@@@@@@@@@"+districtId);
				bankMasterBean = new BankMasterBean();
				LocationBean locationBean = new LocationBean();
				locationBean.setLocationId(districtId);
				bankMasterBean.setDistrcit(locationBean);
				List<BankMasterBean> bankMasterBeans = bankMasterDao.findBank(bankMasterBean, statusList);
				
				buffer.append("<option value='0'>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(bankMasterBeans)){
					for (BankMasterBean bankBean : bankMasterBeans) {
						buffer.append("<option value=\"").append(bankBean.getBankId()).append("\">");
						buffer.append(bankBean.getBankName()+" - ("+bankBean.getBankId()+")");
						buffer.append("</option>");
					}
				}
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward fetchBankName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		BankMasterBean bankMasterBean = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("bankId"))){
				bankMasterBean = new BankMasterBean();
				bankMasterBean.setBankId(new Long(request.getParameter("bankId")));
				BankMasterBean bankMasterBean2 = bankMasterDao.findBank(bankMasterBean, statusList).get(0);
				if(MisUtility.ifEmpty(bankMasterBean2.getBankName()))	
				buffer.append(bankMasterBean2.getBankName());
				
				System.out.println(buffer);
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward fetchBankBranch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		BankMasterBean bankMasterBean = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("bankId"))){
				bankMasterBean = new BankMasterBean();
				bankMasterBean.setBankId(new Long(request.getParameter("bankId")));
				BankMasterBean bankMasterBean2 = bankMasterDao.findBank(bankMasterBean, statusList).get(0);
				if(MisUtility.ifEmpty(bankMasterBean2.getBankBranch()))	
				buffer.append(bankMasterBean2.getBankBranch());
			}
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return null;
	}
}
