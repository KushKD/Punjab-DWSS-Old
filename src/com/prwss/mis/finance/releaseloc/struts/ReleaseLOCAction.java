/**
 * 
 */
package com.prwss.mis.finance.releaseloc.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
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
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.loc.LOCHeaderBean;
import com.prwss.mis.finance.loc.dao.LOCActivityBean;
import com.prwss.mis.finance.loc.dao.LOCDetailBean;
import com.prwss.mis.finance.loc.dao.LOCHeaderDao;
import com.prwss.mis.finance.loc.dao.LOCRequestFromLocationBean;
import com.prwss.mis.finance.releaseloc.ReleaseLOCBO;
import com.prwss.mis.finance.requestloc.struts.RequestLOCForm;
import com.prwss.mis.login.dao.LoginUserBean;
import com.prwss.mis.login.dao.LoginUserDao;
import com.prwss.mis.login.dao.LoginUserLocationBean;
import com.prwss.mis.login.dao.LoginUserLocationDao;
import com.prwss.mis.masters.committee.dao.CommitteeBankBean;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;
import com.prwss.mis.masters.employee.dao.EmployeeDaoImpl;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;


/**
 * @author PJHA
 *
 */
public class ReleaseLOCAction extends DispatchAction {
	private MISSessionBean misSessionBean;
	private ProgramDao programDao;
	private Logger log = Logger.getLogger(ReleaseLOCAction.class);
	private ReleaseLOCBO releaseLOCBO;
	private LOCHeaderDao locHeaderDao ;
	private LocationDao locationDao;	
	private EmployeeDaoImpl employeeDao;
	private  LoginUserLocationDao loginUserLocationDao;
	private LoginUserDao loginUserDao;

	public void setEmployeeDao(EmployeeDaoImpl employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void setLoginUserDao(LoginUserDao loginUserDao) {
		this.loginUserDao = loginUserDao;
	}
		
	public void setLoginUserLocationDao(LoginUserLocationDao loginUserLocationDao) {
		this.loginUserLocationDao = loginUserLocationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	
	public void setLocHeaderDao(LOCHeaderDao locHeaderDao) {
		this.locHeaderDao = locHeaderDao;
	}


	public void setReleaseLOCBO(ReleaseLOCBO releaseLOCBO) {
		this.releaseLOCBO = releaseLOCBO;
	}


	public void setProgramDao(ProgramDao programDao) {
		this.programDao = programDao;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setAttrib(request);
		
		System.out.println("In Find ::::::::::::::::::::::::::::::::");
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
		//statusList.add(MISConstants.MASTER_STATUS_APPROVED);
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
		
		try {
			ReleaseLOCForm releaseLOCForm = (ReleaseLOCForm)form;
			List<LOCHeaderBean> locHeaderBeans = null;
			locHeaderBeans = releaseLOCBO.findReleaseLOC(releaseLOCForm, statusList);
			
			if(!MisUtility.ifEmpty(locHeaderBeans)){
			request.setAttribute("level2", "true");
			for (LOCHeaderBean locHeaderBean : locHeaderBeans) {
				releaseLOCForm.setRequestDate(MisUtility.convertDateToString(locHeaderBean.getLocRequestDate()));
							
				request.setAttribute("requestFromLocationId", locHeaderBean.getLocationId());
				request.setAttribute("locationId", locHeaderBean.getRequestToLocationId());
				request.setAttribute("locId", locHeaderBean.getLocId());
				releaseLOCForm.setProgramId(locHeaderBean.getProgramId()); 
				releaseLOCForm.setLocationId(locHeaderBean.getRequestToLocationId());
				releaseLOCForm.setLocationName(locHeaderBean.getLocationName());
				releaseLOCForm.setRequestFromLocationId(locHeaderBean.getLocationId());
				releaseLOCForm.setLocRequestNo(locHeaderBean.getLocId());
//				releaseLOCForm.setComponentA(locHeaderBean.getComponentAReqAmount());
//				releaseLOCForm.setComponentB(locHeaderBean.getComponentBReqAmount());
//				releaseLOCForm.setComponentC(locHeaderBean.getComponentCReqAmount());
				releaseLOCForm.setLocDate(MisUtility.convertDateToString(locHeaderBean.getLocReleaseDate()));
//				releaseLOCForm.setReleaseAmountA(locHeaderBean.getComponentARelsAmount());
//				releaseLOCForm.setReleaseAmountB(locHeaderBean.getComponentBRelsAmount());
//				releaseLOCForm.setReleaseAmountC(locHeaderBean.getComponentCRelsAmount());
				releaseLOCForm.setAmountFDRequest(locHeaderBean.getAmountFDRequest());
				releaseLOCForm.setAmountFDRelease(locHeaderBean.getAmountFDRelease());
				System.out.println("locHeaderBean.getAmountFDRequest(): "+locHeaderBean.getAmountFDRequest());
				System.out.println("locHeaderBean.getAmountFDRelease(): "+locHeaderBean.getAmountFDRelease());
				System.out.println("releaseLOCForm.getAmountFDRequest(): "+releaseLOCForm.getAmountFDRequest());
				System.out.println("releaseLOCForm.getAmountFDRelease(): "+releaseLOCForm.getAmountFDRelease());
				releaseLOCForm.setLocDate(MisUtility.convertDateToString(locHeaderBean.getLocReleaseDate()));
			    releaseLOCForm.setReleaselocDatagrid(getreleaselocDatagrid(locHeaderBean.getLocDetailBeans()));
			    releaseLOCForm.setLocActivityDatagrid(getrequestlocActivityDatagrid(locHeaderBean.getLocActivtyBeans()));
		//	    System.out.println("FROM ACTION ___________________________"+locHeaderBean);
			    //System.out.println("FROM ACTION ___________________________"+locHeaderBean.getLocActivtyBeans());
			    //System.out.println("FROM ACTION____________________________"+locHeaderBean.getLocDetailBeans());
			}
			}else{
				refreshReleaseLOCForm(releaseLOCForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Inquire of Release LOC Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		ReleaseLOCForm releaseLOCForm = (ReleaseLOCForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = releaseLOCBO.deleteReleaseLOC(releaseLOCForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Release LOC ","LOC Request No -->"+releaseLOCForm.getLocRequestNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Release LOC","LOC Request No -->"+releaseLOCForm.getLocRequestNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Release LOC Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Release LOC Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshReleaseLOCForm(releaseLOCForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("In Save");
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		ReleaseLOCForm releaseLOCForm = (ReleaseLOCForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}
			StringBuffer stringMessage = new StringBuffer();
			if(!MisUtility.ifEmpty(releaseLOCForm.getProgramId())||!MisUtility.ifEmpty(releaseLOCForm.getRequestFromLocationId())||!MisUtility.ifEmpty(releaseLOCForm.getLocationId())||!MisUtility.ifEmpty(releaseLOCForm.getLocDate())||!MisUtility.ifEmpty(releaseLOCForm.getRequestDate())||!MisUtility.ifEmpty(releaseLOCForm.getLocRequestNo())){
				
				if(!MisUtility.ifEmpty(releaseLOCForm.getProgramId())){
					stringMessage.append("Please select Project");
				}
				if(!MisUtility.ifEmpty(releaseLOCForm.getLocationId())){
					stringMessage.append("<br> Please select Release By Location");
				}
				if(!MisUtility.ifEmpty(releaseLOCForm.getRequestFromLocationId())){
					stringMessage.append("<br> Please select LOC Request from Location");
				}			
				if(!MisUtility.ifEmpty(releaseLOCForm.getLocDate())){
					stringMessage.append("<br> Please enter Location Date");
				}
				if(!MisUtility.ifEmpty(releaseLOCForm.getRequestDate())){
					stringMessage.append("<br> Please enter Request Date");
				}
				if(!MisUtility.ifEmpty(releaseLOCForm.getLocRequestNo())){
					stringMessage.append("<br> Please enter LOC Request Number");
				}
				
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			if(MisUtility.ifEmpty(releaseLOCForm.getLocDate())){
				String strngMessage = checkDateFlow(releaseLOCForm.getLocDate());
				if(strngMessage!=null){
					 releaseLOCForm.setLocDate(null);
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}
			}
			List<String> locationIdList = new ArrayList<String>();
			locationIdList.add(releaseLOCForm.getRequestFromLocationId());
			Set<LocationBean> locationBeans= locationDao.getLocationBeanOnLocationIdList(locationIdList);
			System.out.println("Release Action locationBeans: "+locationBeans);
			if(!MisUtility.ifEmpty(locationBeans)){
				for (LocationBean locationBean2 : locationBeans) {
					System.out.println("Release Action locationBean2: "+locationBean2);
					releaseLOCForm.setLocationName(locationBean2.getLocationName());
				}
			}
			
			status = releaseLOCBO.saveReleaseLOC(releaseLOCForm, misSessionBean);
			if (status){
				if(sendMail(releaseLOCForm)){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("LOC.success.save", "Your release LOC");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshReleaseLOCForm(releaseLOCForm);
				}

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("LOC.error.save");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS004.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("value.missing","Saving failed as ");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Release LOC");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("LOC.error.save");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
//		try {
//			refreshReleaseLOCForm(releaseLOCForm);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("In Update");
		this.setAttrib(request);
		ReleaseLOCForm releaseLOCForm = (ReleaseLOCForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				return mapping.findForward("login");
			}

			if(releaseLOCForm.getLocRequestNo()==0){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			StringBuffer stringMessage = new StringBuffer();
			if(!MisUtility.ifEmpty(releaseLOCForm.getProgramId())||!MisUtility.ifEmpty(releaseLOCForm.getRequestFromLocationId())||!MisUtility.ifEmpty(releaseLOCForm.getLocationId())||!MisUtility.ifEmpty(releaseLOCForm.getLocDate())||!MisUtility.ifEmpty(releaseLOCForm.getRequestDate())||!MisUtility.ifEmpty(releaseLOCForm.getLocRequestNo())){
				
				if(!MisUtility.ifEmpty(releaseLOCForm.getProgramId())){
					stringMessage.append("Please select Project");
				}
				if(!MisUtility.ifEmpty(releaseLOCForm.getLocationId())){
					stringMessage.append("<br> Please select Release By Location");
				}
				if(!MisUtility.ifEmpty(releaseLOCForm.getRequestFromLocationId())){
					stringMessage.append("<br> Please select LOC Request from Location");
				}
				if(!MisUtility.ifEmpty(releaseLOCForm.getLocDate())){
					stringMessage.append("<br> Please enter Location Date");
				}
				if(!MisUtility.ifEmpty(releaseLOCForm.getRequestDate())){
					stringMessage.append("<br> Please enter Request Date");
				}
				if(!MisUtility.ifEmpty(releaseLOCForm.getLocRequestNo())){
					stringMessage.append("<br> Please enter LOC Request Number");
				}
				
				throw new MISException(MISExceptionCodes.MIS012,stringMessage.toString());
			}
			if(MisUtility.ifEmpty(releaseLOCForm.getLocDate())){
				String strngMessage = checkDateFlow(releaseLOCForm.getLocDate());
				if(strngMessage!=null){
					releaseLOCForm.setLocDate(null);
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}
			}
			//if(checkReleaseAmount(releaseLOCForm)){
					List<String> locationIdList = new ArrayList<String>();
					locationIdList.add(releaseLOCForm.getRequestFromLocationId());
					Set<LocationBean> locationBeans= locationDao.getLocationBeanOnLocationIdList(locationIdList);
					System.out.println("Release Action locationBeans: "+locationBeans);
					if(!MisUtility.ifEmpty(locationBeans)){
						for (LocationBean locationBean2 : locationBeans) {
							System.out.println("Release Action locationBean2: "+locationBean2);
							releaseLOCForm.setLocationName(locationBean2.getLocationName());
						}
					}
					
					status = releaseLOCBO.updateReleaseLOC(releaseLOCForm, misSessionBean);
					//System.out.println("Plan ID :::::::::::::"+planID);
					if (status){
						if(sendMail(releaseLOCForm)){
							ActionMessages errors = new ActionMessages();
							ActionMessage message = new ActionMessage("LOC.success.save");
							//ActionMessage message = new ActionMessage("budgetSubmission.success.save", "Your Budget Submission");
							errors.add(ActionMessages.GLOBAL_MESSAGE, message);
							saveMessages(request, errors);
						}
		
					} else {
						ActionErrors errors = new ActionErrors();
						ActionMessage message = new ActionMessage("LOC.error.save");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveErrors(request, errors);
					}
		/*	}
			else{
				throw new MISException(MISExceptionCodes.MIS012,"Could not release more amount than requested");
			}*/
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS012.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
			}else{
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Budget Submission");
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
			refreshReleaseLOCForm(releaseLOCForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("display");
	}
	
	

	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locRequestNo@programId@locationId@requestDate@componentA@componentB@requestFromLocationId");
	}
	
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("unspecified:"+request.getParameter("d__mode"));
	
		ConvertUtils.register(new BigDecimalConverter(null), java.math.BigDecimal.class);


		ReleaseLOCForm releaseLOCForm = (ReleaseLOCForm)form;
		
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
			/*Set<LabelValueBean> locationIds = getLocations();
			request.getSession().setAttribute("locationIds", locationIds);*/
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		refreshReleaseLOCForm(releaseLOCForm);
		
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	
	
	private Datagrid getreleaselocDatagrid(Set<LOCDetailBean> locDetailBeans) {
		Datagrid releaselocDatagrid = null;
		List<ReleaseLocGridBean> releaseLocGridBeans = new ArrayList<ReleaseLocGridBean>();
		try {
			releaselocDatagrid = Datagrid.getInstance();
			releaselocDatagrid.setDataClass(ReleaseLocGridBean.class);
			if (!MisUtility.ifEmpty(locDetailBeans)) {
				ReleaseLocGridBean releaseLocGridBean = null;
				for (LOCDetailBean bean : locDetailBeans) {
					releaseLocGridBean = new ReleaseLocGridBean();
					releaseLocGridBean.setId(bean.getId());
					releaseLocGridBean.setAmount(bean.getAmount());
					releaseLocGridBean.setAuditCompletedDate(MisUtility.convertDateToString(bean.getAuditCompletedDate()));
					releaseLocGridBean.setInstallmentNo(bean.getInstallmentNo());
					releaseLocGridBean.setAuditedAmount(bean.getAuditedAmount());
					releaseLocGridBean.setSchemeId(bean.getSchemeId());
					releaseLocGridBean.setReleaseStatus(bean.getReleaseStatus());
					releaseLocGridBean.setReleaseAmount(bean.getReleaseAmount());
					releaseLocGridBean.setSchemeName(bean.getSchemeName());
					releaseLocGridBean.setLocFor(bean.getLocFor());
					releaseLocGridBeans.add(releaseLocGridBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		releaselocDatagrid.setData(releaseLocGridBeans);
		return releaselocDatagrid;
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
	private void refreshReleaseLOCForm(ReleaseLOCForm releaseLOCForm){

//		releaseLOCForm.setReleaseAmountA(new BigDecimal(0.00));
//		releaseLOCForm.setReleaseAmountB(new BigDecimal(0.00));
//		releaseLOCForm.setReleaseAmountC(new BigDecimal(0.00));
//		releaseLOCForm.setComponentA(new BigDecimal(0.00));
//		releaseLOCForm.setComponentB(new BigDecimal(0.00));
//		releaseLOCForm.setComponentC(new BigDecimal(0.00));
		releaseLOCForm.setLocDate(null);
		releaseLOCForm.setRequestDate(null);
		releaseLOCForm.setReleaselocDatagrid(getreleaselocDatagrid(null));
		releaseLOCForm.setLocActivityDatagrid(getrequestlocActivityDatagrid(null));
		releaseLOCForm.setAuditCompletedDate(null);
		releaseLOCForm.setAmountFDRelease(new BigDecimal(0.0));
		releaseLOCForm.setAmountFDRequest(new BigDecimal(0.0));

	}
	public ActionForward fetchRequestLocId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<LOCHeaderBean> locHeaderBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		//statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		LOCHeaderBean locHeaderBean = new LOCHeaderBean();
		try {

			//System.out.println("location id:"+request.getParameter("locationId"));
			//System.out.println("program id:"+request.getParameter("programId"));
			//System.out.println("requestFromLocationId:"+request.getParameter("requestFromLocationId"));
			if(MisUtility.ifEmpty(request.getParameter("locationId"))&&MisUtility.ifEmpty(request.getParameter("programId"))){
				locHeaderBean.setLocationId(request.getParameter("requestFromLocationId"));
				locHeaderBean.setProgramId(request.getParameter("programId"));
				locHeaderBean.setRequestToLocationId(request.getParameter("locationId"));
				locHeaderBeans = new TreeSet<LOCHeaderBean>(locHeaderDao.findLOCHeader(locHeaderBean, statusList));
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				if(!MisUtility.ifEmpty(locHeaderBeans)){
					for (LOCHeaderBean locHeaderBean2 : locHeaderBeans) {
						buffer.append("<option value=\"").append(locHeaderBean2.getLocId()).append("\">");
						buffer.append(locHeaderBean2.getLocId());
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
	
	/*private Set<LabelValueBean> getLocations(){
		Set<LabelValueBean> locationIds = null;
		Set<LocationBean> locationBeans = null;
		try {
			List<String> locationTypeList = new ArrayList<String>();
			locationTypeList.add("DPMC");
			locationTypeList.add("DO");
			locationTypeList.add("SPMC");
			locationTypeList.add("CIRCLE");
			locationTypeList.add("ZONE");
			locationTypeList.add("SPONSER");
			locationBeans = locationDao.getLocationIdOnTypeList(locationTypeList);
			locationIds = new HashSet<LabelValueBean>();
			for (LocationBean locationBean : locationBeans) {
				locationIds.add(new LabelValueBean(locationBean.getLocationName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+locationBean.getLocationId(),locationBean.getLocationId()));
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return locationIds;
	}*/
	
	public ActionForward fetchLOCRequestNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		StringBuffer buffer = new StringBuffer();		
		List<LocNotReleasedBean> locNotReleasedBeans = null;
		LocNotReleasedBean locNotReleasedBean = new LocNotReleasedBean();
		try {
			buffer.append("<option value='' selected>");
			buffer.append("Select");
			buffer.append("</option>");
			
			if(MisUtility.ifEmpty(request.getParameter("requestFromLocationId"))&&MisUtility.ifEmpty(request.getParameter("locationId"))&&MisUtility.ifEmpty(request.getParameter("programId"))){
				locNotReleasedBean.setProgramId(request.getParameter("programId"));
				locNotReleasedBean.setLocationId(request.getParameter("requestFromLocationId"));
				locNotReleasedBean.setRequestToLocationId(request.getParameter("locationId"));
				
				locNotReleasedBeans = locHeaderDao.fetchReleaseLoc(locNotReleasedBean);
				
				for(LocNotReleasedBean bean:locNotReleasedBeans){
					buffer.append("<option value=\"").append(bean.getLocId()).append("\">");
					buffer.append(bean.getLocId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+bean.getSchemeName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+bean.getSchemeId());
					buffer.append("</option>");
				}
				
				/*ReleaseLOCForm releaseLOCForm = new ReleaseLOCForm();
				releaseLOCForm.setLocationId(request.getParameter("locationId"));
				releaseLOCForm.setRequestFromLocationId(request.getParameter("requestFromLocationId"));
				releaseLOCForm.setProgramId(request.getParameter("programId"));
				locHeaderBeans = releaseLOCBO.findReleaseLOC(releaseLOCForm, statusList);
				System.out.println("--------Check1 "+locHeaderBeans.size());
				locDetailBeans = releaseLOCBO.findReleaseLOCDetail(releaseLOCForm, statusList);
				System.out.println("--------Check2 "+locDetailBeans.size());
				
				for(LOCHeaderBean locHeaderBean:locHeaderBeans){
					for(LOCDetailBean locDetailBean:locDetailBeans){
						if(locHeaderBean.getLocId()==locDetailBean.getLocId()){
							if(locHeaderBean.getLocReleaseDate().equals("")||locHeaderBean.getLocReleaseDate().equals(null)){
								buffer.append("<option value=\"").append(locDetailBean.getLocId()).append("\">");
								buffer.append(locDetailBean.getLocId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+locDetailBean.getSchemeName());
								buffer.append("</option>");
							}
						}
					}
				}*/
				
			}
			
			PrintWriter out = response.getWriter();
			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 )
				out.print(buffer.toString());
		
		}catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	
	public ActionForward fetchRequestFromLocationId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);		
		Set<LocationBean> locationBeans = null;
		
		try {
			System.out.println("***********************************************************************************");
			
			System.out.println("location id:"+request.getParameter("locationId"));
			System.out.println("program id:"+request.getParameter("programId"));
			
			System.out.println("***********************************************************************************");
			
			buffer.append("<option value='' selected>");
			buffer.append("Select");
			buffer.append("</option>");
			if(MisUtility.ifEmpty(request.getParameter("locationId"))&&MisUtility.ifEmpty(request.getParameter("programId"))){
				List<String> locationTypeList = new ArrayList<String>();				
				if(request.getParameter("programId").equals("PROG15")&&request.getParameter("locationId").equals("SPMC")){
					locationTypeList.add("DPMC");
					locationBeans = locationDao.getLocationIdOnTypeList(locationTypeList);
					for(LocationBean locationBean:locationBeans){
						buffer.append("<option value=\"").append(locationBean.getLocationId()).append("\">");
						buffer.append(locationBean.getLocationName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+locationBean.getLocationId());
						buffer.append("</option>");
					}
				}
				else{
					locationTypeList.add("DPMC");
					locationTypeList.add("DO");
					locationTypeList.add("SPMC");
					locationTypeList.add("CIRCLE");
					locationTypeList.add("ZONE");
					locationTypeList.add("SPONSER");
					locationBeans = locationDao.getLocationIdOnTypeList(locationTypeList);
					for(LocationBean locationBean:locationBeans){
						buffer.append("<option value=\"").append(locationBean.getLocationId()).append("\">");
						buffer.append(locationBean.getLocationName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+locationBean.getLocationId());
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
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return null;
	}

	
	private Datagrid getrequestlocActivityDatagrid(Set<LOCActivityBean> locActivityBeans) {
		Datagrid locActivityDatagrid = null;
		try {
			locActivityDatagrid = Datagrid.getInstance();
			locActivityDatagrid.setDataClass(LOCActivityBean.class);
			if(!MisUtility.ifEmpty(locActivityBeans)){
			List<LOCActivityBean> locDetailBeans2 = new ArrayList<LOCActivityBean>(locActivityBeans);
			locActivityDatagrid.setData(locDetailBeans2);		
			}else{
			List<LOCActivityBean> locDetailBeans2 = new ArrayList<LOCActivityBean>();
			locActivityDatagrid.setData(locDetailBeans2);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return locActivityDatagrid;
	}
	
	public ActionForward fetchLocationId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws MISException {
			
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		//Set<LabelValueBean> locationIds = null;
		Set<LocationBean> locationBeans = null;
		try {
			if(MisUtility.ifEmpty(request.getParameter("programId"))){
				List<String> locationTypeList = new ArrayList<String>();
				buffer.append("<option value=''>Please Select</option>");
				String programId=request.getParameter("programId");
				if(programId.equals("PROG15")){
					locationTypeList.add("SPONSER");
					locationTypeList.add("SPMC");
					locationBeans = locationDao.getLocationIdOnTypeList(locationTypeList);						
						for (LocationBean locationBean1 : locationBeans) {
							buffer.append("<option value=\"").append(locationBean1.getLocationId()).append("\">");
							buffer.append(locationBean1.getLocationName()+" - ("+locationBean1.getLocationId()+")");
							buffer.append("</option>");
						} 
				}
				else{
					locationTypeList.add("DPMC");
					locationTypeList.add("DO");
					locationTypeList.add("SPMC");
					locationTypeList.add("CIRCLE");
					locationTypeList.add("ZONE");
					locationTypeList.add("SPONSER");
					locationBeans = locationDao.getLocationIdOnTypeList(locationTypeList);
					//locationIds = new TreeSet<LabelValueBean>();
						
						for (LocationBean locationBean1 : locationBeans) {
					//		locationIds.add(new LabelValueBean(locationBean1.getLocationName()+" - ("+locationBean1.getLocationId()+")",locationBean1.getLocationId()));
							
							buffer.append("<option value=\"").append(locationBean1.getLocationId()).append("\">");
							buffer.append(locationBean1.getLocationName()+" - ("+locationBean1.getLocationId()+")");
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
	
	private boolean checkReleaseAmount(ReleaseLOCForm  releaseLOCForm){
		boolean flag = true;
		
		Collection<ReleaseLocGridBean> modifiedLOCDetailBeans = releaseLOCForm.getReleaselocDatagrid().getModifiedData();
		if(!MisUtility.ifEmpty(modifiedLOCDetailBeans)){
			for (ReleaseLocGridBean releaseLocGridBean : modifiedLOCDetailBeans) {
				int res = releaseLocGridBean.getAmount().compareTo(releaseLocGridBean.getReleaseAmount());								
				if(res==-1)
					flag = false;
				}
			}
		
		return flag;
	}
	
	private String checkDateFlow(String voucherDate) {
		   Date date1 = null;
		   Date date2 = null;
		   Date date3 = null; 
		   Date date4 = null;
		   Date date5 = null;
		   Calendar cal = new GregorianCalendar(); 
		   StringBuffer prevDatev= new StringBuffer();
		   //getting current date
		   prevDatev.append(cal.get(Calendar.DAY_OF_MONTH));
		   prevDatev.append("-");
		   prevDatev.append(cal.get(Calendar.MONTH)+1);
		   prevDatev.append("-");
		   prevDatev.append(cal.get(Calendar.YEAR));
		   String prDate = prevDatev.toString();
		   
		   //setting 10th date of current month
		   Calendar cal1 = new GregorianCalendar();
		   cal1.set(Calendar.DAY_OF_MONTH, 10);
		   StringBuffer tenDate = new StringBuffer();
		   tenDate.append(cal1.get(Calendar.DAY_OF_MONTH));
		   tenDate.append("-");
		   tenDate.append(cal1.get(Calendar.MONTH)+1);
		   tenDate.append("-");
		   tenDate.append(cal1.get(Calendar.YEAR));
		   
		   //setting last month 1 date
		   Calendar cal2 = new GregorianCalendar();
		   cal2.set(Calendar.DAY_OF_MONTH, 1);
		   cal2.add(Calendar.MONTH,-1);
		   cal2.add(Calendar.DAY_OF_MONTH, -1);
		   StringBuffer fLMonthDate = new StringBuffer();
		   fLMonthDate.append(cal2.get(Calendar.DAY_OF_MONTH));
		   fLMonthDate.append("-");
		   fLMonthDate.append(cal2.get(Calendar.MONTH)+1);
		   fLMonthDate.append("-");
		   fLMonthDate.append(cal2.get(Calendar.YEAR));
		   
		   //setting current month 1st date
		   Calendar cal3 = new GregorianCalendar();
		   StringBuffer fCMonthDate = new StringBuffer();
		   cal3.set(Calendar.DAY_OF_MONTH, 1);
		   fCMonthDate.append(cal3.get(Calendar.DAY_OF_MONTH));
		   fCMonthDate.append("-");
		   fCMonthDate.append(cal3.get(Calendar.MONTH)+1);
		   fCMonthDate.append("-");
		   fCMonthDate.append(cal3.get(Calendar.YEAR));
		   System.out.println("mmmmmm"+fLMonthDate);
		    
		   String myFormatString = "dd-MM-yyyy"; // for example
		   SimpleDateFormat df = new SimpleDateFormat(myFormatString);
		   try{
			   StringBuffer strngMessage = new StringBuffer();
		     date1 = df.parse(voucherDate);
		     date2 = df.parse(prDate);
		     date3 = df.parse(tenDate.toString());
		     date4 = df.parse(fLMonthDate.toString());
		     date5 = df.parse(fCMonthDate.toString());
		     
		     if(date2.before(date1)){
		    	 	strngMessage.append("Invalid LOC Date");
					strngMessage.append("<br> LOC Date should be before today's Date");
					return strngMessage.toString();
			  }
		     if(date1.before(date3)){
		    	 if(date1.after(date4) && date1.before(date3)){																	//date2=current date
		    		 return null;																								//date4=1st of last month
		    	 }else{																											//date5=1st of current month
		    		 strngMessage.append("Invalid LOC Date");
						strngMessage.append("<br> LOC Date should be greater than"+" ("+fLMonthDate+") "+"and"+" less than or equal("+tenDate+")");
						return strngMessage.toString();
		    	 }  
		     }
		     if(date1.after(date3)){
		    	 if(date1.after(date5) && (date1.before(date2)||date1.equals(date2))){
		    		 return null;
		    	 }else{
		    		 strngMessage.append("Invalid LOC Date");
						strngMessage.append("<br> LOC Date should be between"+" ("+fCMonthDate+") "+"and"+" ("+prDate+")");
						return strngMessage.toString();
		    	 }  
		     }
		   } 
		   catch (java.text.ParseException e) {
			e.printStackTrace();
		   } 

		return null;
	}
	
	
	/*sends a mail to the finance Department*/
	private boolean sendMail(ReleaseLOCForm releaseLOCForm){
		boolean flag = false;
		StringBuffer buffer = new StringBuffer();
		buffer.append("Dear Sir/Madam, \n\n  " );
		
		Collection<ReleaseLocGridBean> modifiedLOCDetailBeans = releaseLOCForm.getReleaselocDatagrid().getModifiedData();
		if(!MisUtility.ifEmpty(modifiedLOCDetailBeans)){
			for (ReleaseLocGridBean releaseLocGridBean : modifiedLOCDetailBeans) {
				buffer.append(releaseLOCForm.getLocationId() +" has released amount of Rs "+releaseLocGridBean.getReleaseAmount()+" against your LOC request Id "+releaseLocGridBean.getId()
						+" for "+releaseLocGridBean.getLocFor()+" of scheme "+releaseLocGridBean.getSchemeId()+"->"+releaseLocGridBean.getSchemeName()+"\n");
			}
		}
		buffer.append("\n\nRegards \n IT-Help Desk\n Department of Water Supply and Sanitation, Punjab");
	
		try{
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_DELETED);
		List<String> statusList1 = new ArrayList<String>();
		statusList1.add(MISConstants.MASTER_STATUS_APPROVED);
		List<EmployeeBean> employeeBeans = null;
		List<LoginUserBean> userBeans = null;
		EmployeeBean employeeBean = new EmployeeBean();
		employeeBean.setLocationId(releaseLOCForm.getRequestFromLocationId());
		employeeBean.setDesigId(0);
		employeeBeans = employeeDao.findEmployeeForLocation(employeeBean, statusList);
		
		if(employeeBeans!=null){
			LoginUserBean loginUserBean = new LoginUserBean();
			for(EmployeeBean bean:employeeBeans){
				loginUserBean.setRoleId("XEN");
				loginUserBean.setEmployeeId(bean.getEmployeeId());
				userBeans = loginUserDao.findLoginUser(loginUserBean, statusList1);
			}
		}
		
		/*Mail firing*/
			System.out.println("do mail in");
			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			final String SMTP_HOST_NAME = "smtp.gmail.com";
			final String SMTP_PORT = "465";
//			final String emailMsgTxt = "Test Message Contents";
//			final String emailSubjectTxt = "A test from gmail";
//			final String emailFromAddress = "";
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			Properties javaMailProperties = System.getProperties();
			javaMailProperties.put("mail.smtp.host", SMTP_HOST_NAME);
			javaMailProperties.put("mail.smtp.auth", "true");
			javaMailProperties.put("mail.debug", "true");
			javaMailProperties.put("mail.smtp.port", SMTP_PORT);
			javaMailProperties.put("mail.smtp.socketFactory.port", SMTP_PORT);
			javaMailProperties.put("mail.smtp.socketFactory.class", SSL_FACTORY);
			javaMailProperties.put("mail.smtp.socketFactory.fallback", "false");
			sender.setJavaMailProperties(javaMailProperties);
			sender.setUsername("alert.prwss@gmail.com");
			sender.setPassword("3xchange!");
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = null;
			int count = 0;
					helper = new MimeMessageHelper(message, true);
					for(LoginUserBean loginUserBean:userBeans){
						if(count==0){
							
							helper.setTo(loginUserBean.getUserEmail());
							count++;
						}
						else if(count>0){
							helper.setCc(loginUserBean.getUserEmail());
						}
					}
					helper.setSubject("Released LOC");
					helper.setFrom("negi.kavita923@gmail.com");
					helper.setSentDate(new Date(System.currentTimeMillis()));
					helper.setText(buffer.toString());

					sender.send(message);
					flag = true;
		
			} catch (MailException e) {
				// Just logging the exception and kill so that the rest of the notifications are processed
				log.error(e.getLocalizedMessage(),e);
				
			} catch (MessagingException e) {
				// Just logging the exception and kill so that the rest of the notifications are processed
				log.error(e.getLocalizedMessage(),e);
			} catch (Exception e) {
				// Just logging the exception and kill so that the rest of the notifications are processed
				log.error(e.getLocalizedMessage(),e);
			}
		return flag;
	}
}
