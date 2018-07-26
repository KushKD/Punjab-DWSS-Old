/**
 * 
 */
package com.prwss.mis.finance.requestloc.struts;

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
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.notification.dao.MailNotificationBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.loc.LOCHeaderBean;
import com.prwss.mis.finance.loc.dao.LOCActivityBean;
import com.prwss.mis.finance.loc.dao.LOCDetailBean;
import com.prwss.mis.finance.loc.dao.LOCHeaderDao;
import com.prwss.mis.finance.requestloc.RequestLOCBO;
import com.prwss.mis.masters.activity.dao.ActivityBean;
import com.prwss.mis.masters.activity.dao.ActivityDao;
import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.component.dao.ComponentDao;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderBean;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderDao;
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;
import com.prwss.mis.masters.subcomponent.dao.SubComponentBean;
import com.prwss.mis.masters.subcomponent.dao.SubComponentDao;
import com.prwss.mis.masters.village.dao.VillageSchemeViewBean;
import com.prwss.mis.masters.village.dao.VillageSchemeViewDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class RequestLOCAction  extends DispatchAction{

	private MISSessionBean misSessionBean;
	private Logger log = Logger.getLogger(RequestLOCAction.class);
	private ProgramDao programDao;
	private SchemeHeaderDao schemeHeaderDao  ;
	private RequestLOCBO requestLOCBO;
    private LOCHeaderDao locHeaderDao ;
    private LocationDao locationDao;
    private ComponentDao componentDao;
    private SubComponentDao subComponentDao;
    private ActivityDao activityDao;
    private VillageSchemeViewDao villageSchemeViewDao;
	
	public void setVillageSchemeViewDao(VillageSchemeViewDao villageSchemeViewDao) {
		this.villageSchemeViewDao = villageSchemeViewDao;
	}


	public void setSubComponentDao(SubComponentDao subComponentDao) {
		this.subComponentDao = subComponentDao;
	}


	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}


	public void setComponentDao(ComponentDao componentDao) {
		this.componentDao = componentDao;
	}


	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}


	public void setProgramDao(ProgramDao programDao) {
		this.programDao = programDao;
	}


	public void setRequestLOCBO(RequestLOCBO requestLOCBO) {
		this.requestLOCBO = requestLOCBO;
	}

	

	public void setSchemeHeaderDao(SchemeHeaderDao schemeHeaderDao) {
		this.schemeHeaderDao = schemeHeaderDao;
	}

	

	public void setLocHeaderDao(LOCHeaderDao locHeaderDao) {
		this.locHeaderDao = locHeaderDao;
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
			RequestLOCForm requestLOCForm = (RequestLOCForm)form;
			List<LOCHeaderBean> locHeaderBeans = null;
			locHeaderBeans = requestLOCBO.findRequestLOC(requestLOCForm, statusList);
			if(!MisUtility.ifEmpty(locHeaderBeans)){
			request.setAttribute("level2", "true");
			for (LOCHeaderBean locHeaderBean : locHeaderBeans) {
				requestLOCForm.setRequestDate(MisUtility.convertDateToString(locHeaderBean.getLocRequestDate()));
				requestLOCForm.setLocRequestNo(locHeaderBean.getLocId());
				request.setAttribute("locId", locHeaderBean.getLocId());
				requestLOCForm.setProgramId(locHeaderBean.getProgramId()); 
				requestLOCForm.setLocationId(locHeaderBean.getLocationId());
				requestLOCForm.setLocationName(locHeaderBean.getLocationName());
				requestLOCForm.setRequestToLocationId(locHeaderBean.getRequestToLocationId());
				requestLOCForm.setComponentA(locHeaderBean.getComponentAReqAmount());
				requestLOCForm.setComponentB(locHeaderBean.getComponentBReqAmount());
				requestLOCForm.setComponentC(locHeaderBean.getComponentCReqAmount());
				requestLOCForm.setAmountFD(locHeaderBean.getAmountFDRequest());
				requestLOCForm.setRequestlocDatagrid(getrequestlocDatagrid(locHeaderBean.getLocDetailBeans()));
				requestLOCForm.setLocActivityDatagrid(getrequestlocActivityDatagrid(locHeaderBean.getLocActivtyBeans()));
				
			}
			}else{
				refreshRequestLOCForm(requestLOCForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Inquire of Request LOC Details");
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
		RequestLOCForm requestLOCForm = (RequestLOCForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = requestLOCBO.deleteRequestLOC(requestLOCForm, misSessionBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete","Request LOC ","LOC Request No -->"+requestLOCForm.getLocRequestNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete","Request LOC","LOC Request No -->"+requestLOCForm.getLocRequestNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Request LOC Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Deletion of Request LOC Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		refreshRequestLOCForm(requestLOCForm);
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("In Save");
		this.setAttrib(request);
		request.setAttribute("level2", "true");
		RequestLOCForm requestLOCForm = (RequestLOCForm)form;
		long locId = 0;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			
			if( !(MisUtility.ifEmpty(requestLOCForm.getRequestToLocationId()))){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			if(MisUtility.ifEmpty(requestLOCForm.getRequestDate())){
				String strngMessage = checkDateFlow(requestLOCForm.getRequestDate());
				if(strngMessage!=null){
					requestLOCForm.setRequestDate(null);
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}
			}
			List<String> locationIdList = new ArrayList<String>();
			locationIdList.add(requestLOCForm.getLocationId());
			Set<LocationBean> locationBeans= locationDao.getLocationBeanOnLocationIdList(locationIdList);
			System.out.println("Request Action locationBeans: "+locationBeans);
			if(!MisUtility.ifEmpty(locationBeans)){
				for (LocationBean locationBean2 : locationBeans) {
					System.out.println("Request Action locationBean2: "+locationBean2);
					requestLOCForm.setLocationName(locationBean2.getLocationName());
				}
			}			
			locId = requestLOCBO.saveRequestLOC(requestLOCForm, misSessionBean);
			if (locId!=0){
				if(sendMail(requestLOCForm)){
					ActionMessages errors = new ActionMessages();
					ActionMessage message = new ActionMessage("Loc.save.success",locId);
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, errors);
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
				ActionMessage message = new ActionMessage("fatal.error.save","Request LOC");
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
		try {
			refreshRequestLOCForm(requestLOCForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		refreshRequestLOCForm(requestLOCForm);
		return mapping.findForward("display");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("In Save");
		this.setAttrib(request);
		RequestLOCForm requestLOCForm = (RequestLOCForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			if(requestLOCForm.getLocRequestNo()==0){
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}	
			if(MisUtility.ifEmpty(requestLOCForm.getRequestDate())){
				String strngMessage = checkDateFlow(requestLOCForm.getRequestDate());
				if(strngMessage!=null){
					requestLOCForm.setRequestDate(null);
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}
			}
			List<String> locationIdList = new ArrayList<String>();
			locationIdList.add(requestLOCForm.getLocationId());
			Set<LocationBean> locationBeans= locationDao.getLocationBeanOnLocationIdList(locationIdList);
			if(!MisUtility.ifEmpty(locationBeans)){
				for (LocationBean locationBean2 : locationBeans) {
					requestLOCForm.setLocationName(locationBean2.getLocationName());
				}
			}	
			status = requestLOCBO.updateRequestLOC(requestLOCForm, misSessionBean);
			//System.out.println("Plan ID :::::::::::::"+planID);
			if (status){
				if(sendMail(requestLOCForm)){
					
					
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
			refreshRequestLOCForm(requestLOCForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("display");
	}
	
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		RequestLOCForm requestLOCForm = (RequestLOCForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}

			status = requestLOCBO.postRequestLOC(requestLOCForm, misSessionBean);
			if (status){					
					ActionMessages errors = new ActionMessages();
					ActionMessage message = new ActionMessage("success.post","Request LOC","LOC No -->"+requestLOCForm.getLocRequestNo());
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, errors);				

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.post","Post of Request LOC","LOC No -->"+requestLOCForm.getLocRequestNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Post of Request LOC Details");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Post of Request LOC Details");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		
		refreshRequestLOCForm(requestLOCForm);
		return mapping.findForward("display");
	}
	
	
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "programId@locationId@locRequestNo@requestToLocationId");
		request.setAttribute("d__auto", "locRequestNo");
		
	}
	
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("unspecified:"+request.getParameter("d__mode"));
		RequestLOCForm requestLOCForm = (RequestLOCForm)form;
		
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
			/*Set<LabelValueBean> locationIds = getLocations();
			request.getSession().setAttribute("requestToLocationIds", locationIds);*/
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		
		this.setAttrib(request);
		
		try {
			Set<LabelValueBean> programIds = getProgramIds();
			request.getSession().setAttribute("programIds", programIds);
			
			Set<LabelValueBean> componentIds = getComponentIds();
			request.getSession().setAttribute("componentIds", componentIds);
			
			
//			Set<LabelValueBean> schemeIds = getSchemeIds();
//			request.getSession().setAttribute("schemeIds", schemeIds);
			
			
			refreshRequestLOCForm(requestLOCForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return mapping.findForward("display");
	}
	
	private Datagrid getrequestlocDatagrid(Set<LOCDetailBean> locDetailBeans) {
		Datagrid requestlocDatagrid = null;
		try {
			requestlocDatagrid = Datagrid.getInstance();
			requestlocDatagrid.setDataClass(LOCDetailBean.class);
			if(!MisUtility.ifEmpty(locDetailBeans)){
			List<LOCDetailBean> locDetailBeans2 = new ArrayList<LOCDetailBean>(locDetailBeans);
			requestlocDatagrid.setData(locDetailBeans2);		
			}else{
			List<LOCDetailBean> locDetailBeans2 = new ArrayList<LOCDetailBean>();
			requestlocDatagrid.setData(locDetailBeans2);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return requestlocDatagrid;
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
	
	private Set<LabelValueBean> getComponentIds() {
		Set<LabelValueBean> componentIds = null;
		List<ComponentBean> componentBeans = null;
		try{
			ComponentBean componentBean = new ComponentBean();
			List <String> statusList = new ArrayList<String>() ;
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			componentBeans = componentDao.findComponent(componentBean, statusList);
			
			Iterator<ComponentBean> iterator1= componentBeans.iterator();
			
			while(iterator1.hasNext()){
				ComponentBean componentBean2 = iterator1.next();
				if(componentBean2.getComponentId().equals("com3")){
					iterator1.remove();
				}
					
			}
			
			componentIds = new HashSet<LabelValueBean>();
			for (ComponentBean componentBean2 : componentBeans) {
//				if(componentBean2.getComponentId().equals("com3")){ // This if condition is placed to remove component C from the list
//					componentBeans.remove(componentBean2);
//				}else{
				componentIds.add(new LabelValueBean(componentBean2.getComponentName()+" - ("+componentBean2.getComponentId()+")",componentBean2.getComponentId()));
//				}
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
	
	private void refreshRequestLOCForm(RequestLOCForm requestLOCForm){
	    requestLOCForm.setComponentA(new BigDecimal(0.00));
	    requestLOCForm.setComponentB(new BigDecimal(0.00));
	    requestLOCForm.setComponentC(new BigDecimal(0.00));
	    requestLOCForm.setAmountFD(new BigDecimal(0.0));
		requestLOCForm.setRequestlocDatagrid(getrequestlocDatagrid(null));
		requestLOCForm.setRequestDate(null);
		requestLOCForm.setLocActivityDatagrid(getrequestlocActivityDatagrid(null));
		requestLOCForm.setRequestToLocationId(null);
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
	
//	private Set<LabelValueBean> getSchemeIds(){
//		Set<LabelValueBean> schemeIds = null;
//		List<SchemeHeaderBean> schemeHeaderBeans = null;
//		try{
//			SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
//			List <String> statusList = new ArrayList<String>() ;
//			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//			schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
//			schemeIds = new HashSet<LabelValueBean>();
//			for (SchemeHeaderBean schemeHeaderBean2 : schemeHeaderBeans) {
//				schemeIds.add(new LabelValueBean(schemeHeaderBean2.getSchemeId()+MISConstants.LABEL_VALUE_DATE_SEPARATOR+schemeHeaderBean2.getSchemeName(),new String(schemeHeaderBean2.getSchemeId())));
//				
//			}
//			
//		}catch(DataAccessException e){
//			log.error(e.getLocalizedMessage(),e);
//			e.printStackTrace();
//		}
//		catch(Exception e){
//			log.error(e.getLocalizedMessage(),e);
//			e.printStackTrace();
//		}
//		return schemeIds;	
//	}	
	
//	private Set<LabelValueBean> getlocId(){
//		
//		Set<LabelValueBean> locRequestNo = null;
//		List<LOCHeaderBean> locHeaderBeans = null;
//		try {
//			
//			LOCHeaderBean locHeaderBean = new LOCHeaderBean();
//			locHeaderBeans = locHeaderDao.findLOCHeader(locHeaderBean, null);
//			
//			locRequestNo = new HashSet<LabelValueBean>();
//			for (LOCHeaderBean locHeaderBean2 : locHeaderBeans) {
//				locRequestNo.add(new LabelValueBean(locHeaderBean2.getLocId()+"",new String(locHeaderBean2.getLocId()+"")));
//			}
//			
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//			log.error(e.getMessage(),e);
//		}catch (Exception e) {
//			e.printStackTrace();
//			log.error(e);
//		}
//		return locRequestNo;
//	}
	
	public ActionForward fetchRequestLocId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		Set<LOCHeaderBean> locHeaderBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		LOCHeaderBean locHeaderBean = new LOCHeaderBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				locHeaderBean.setLocationId(request.getParameter("locationId"));
				
				locHeaderBeans = new TreeSet<LOCHeaderBean>(locHeaderDao.findLOCHeader(locHeaderBean, statusList));
				
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
	
	public ActionForward fetchSchemeId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		List<SchemeHeaderBean> schemeHeaderBeans = null;
		StringBuilder buffer = new StringBuilder();
		List<String> statusList = new ArrayList<String>();
//		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				
				LocationBean locationBean = new LocationBean();
				locationBean.setLocationId(request.getParameter("locationId"));
				locationBean = locationDao.getLocation(locationBean);
				schemeHeaderBean.setDistrictId(locationBean.getParentLocation()); // Scheme is fetched district wise, for same we have to find user location parent location and add that in district id
				schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
				if(!MisUtility.ifEmpty(schemeHeaderBeans)){
					for (SchemeHeaderBean schemeHeaderBean2 : schemeHeaderBeans) {
						buffer.append("<option value=\"").append(schemeHeaderBean2.getSchemeId()).append("\">");
						buffer.append(schemeHeaderBean2.getSchemeName()+"("+schemeHeaderBean2.getSchemeId()+")");
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
				if(!MisUtility.ifEmpty(activityBeans)){
					for (ActivityBean activityBean2 : activityBeans) {
						buffer.append("<option value=\"").append(activityBean2.getActivityId()).append("\">");
						buffer.append(activityBean2.getActivityName()+" - ("+activityBean2.getActivityId()+")");
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
				for(SubComponentBean subComponent: subComponentBeans){
					buffer.append("<option value=\"").append(subComponent.getSubComponentId()).append("\">");
					buffer.append(subComponent.getSubComponentName()+" - ("+subComponent.getSubComponentId()+")");
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
	public ActionForward fetchRequestToLocationId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		//Set<LabelValueBean> locationIds = null;
		Set<LocationBean> locationBeans = null;
		Set<LocationBean> locationBeans2 = null;
		boolean flag = false;
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				
				buffer.append("<option value=''>Please Select</option>");
				String locationId=request.getParameter("locationId");
				List<String> locationTypeList = new ArrayList<String>();
				locationTypeList.add("DPMC");
				locationBeans2 = locationDao.getLocationIdOnTypeList(locationTypeList);
				for(LocationBean locationBean:locationBeans2){
					if(locationBean.getLocationId().equals(locationId)){
						buffer.append("<option value=\"").append("SPMC").append("\">");
						buffer.append("SPMC"+" - (SPMC)");
						buffer.append("</option>");
						flag = true;						
					}					
				}
				if(!flag){
					
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
	
	
	public ActionForward fetchSchemeIds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		StringBuffer buffer = new StringBuffer();
		try {
			List <SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//			SchemeVillageBean schemeVillageBean = new SchemeVillageBean();
//			schemeVillageBean.setVillageId(id);
//			schemeVillageBeans = schemeVillageDao.findSchemeVillage(schemeVillageBean, statusList);
			VillageSchemeViewBean villageSchemeViewBean = new VillageSchemeViewBean();
			villageSchemeViewBean.setVillageId(request.getParameter("villageId"));
			villageSchemeViewBean.setProgramId(request.getParameter("programId"));
			List<VillageSchemeViewBean> schemeVillageBeans2 = villageSchemeViewDao.findVillageSchemeFromView(villageSchemeViewBean, statusList);
			buffer.append("<option value='' selected>");
			buffer.append("Select");
			buffer.append("</option>");
			if(!MisUtility.ifEmpty(schemeVillageBeans2)){
				for (VillageSchemeViewBean schemeVillageBean2 : schemeVillageBeans2) {
					buffer.append("<option value=\"").append(schemeVillageBean2.getSchemeId()).append("\">");
					buffer.append(schemeVillageBean2.getSchemeName()+" -("+schemeVillageBean2.getSchemeId()+")-"+schemeVillageBean2.getProgramId());
					buffer.append("</option>");
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
		    	 	strngMessage.append("Invalid Request Date");
					strngMessage.append("<br> Request Date should be before today's Date");
					return strngMessage.toString();
			  }
		     if(date1.before(date3)){
		    	 if(date1.after(date4) && date1.before(date3)){																	//date2=current date
		    		 return null;																								//date4=1st of last month
		    	 }else{																											//date5=1st of current month
		    		 strngMessage.append("Invalid Request Date");
						strngMessage.append("<br> Request Date should be greater than"+" ("+fLMonthDate+") "+"and"+" less than or equal("+tenDate+")");
						return strngMessage.toString();
		    	 }  
		     }
		     if(date1.after(date3)){
		    	 if(date1.after(date5) && (date1.before(date2)||date1.equals(date2))){
		    		 return null;
		    	 }else{
		    		 strngMessage.append("Invalid Request Date");
						strngMessage.append("<br> Request Date should be between"+" ("+fCMonthDate+") "+"and"+" ("+prDate+")");
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
	private boolean sendMail(RequestLOCForm requestLOCForm){
		boolean flag = false;
		
		
		try{
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
			
					helper = new MimeMessageHelper(message, true);
					helper.setTo("financespmc@gmail.com");
					helper.setSubject("Requested LOC");
					helper.setFrom("alert.prwss@gmail.com");
					helper.setSentDate(new Date(System.currentTimeMillis()));
					helper.setText(addText(requestLOCForm));

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
	
	private String addText(RequestLOCForm requestLOCForm){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Dear Sir/Madam, \n\n  Your Division:  "+requestLOCForm.getLocationId()+"->"+requestLOCForm.getLocationName()+
				"  has sent request for loc as per following details.\n ");
		Collection<LOCDetailBean> addedLOCDetailBeans = requestLOCForm.getRequestlocDatagrid().getAddedData();
		if(!MisUtility.ifEmpty(addedLOCDetailBeans)){
			for (LOCDetailBean locDetailBean : addedLOCDetailBeans) {
				buffer.append("\n\t LOC Request for scheme "+locDetailBean.getSchemeId()+"->"+locDetailBean.getSchemeName()+
						"\n\t\t\t- LOC Request ID "+locDetailBean.getLocId()+
						"\n\t\t\t- LOC Requested Amount(In lacs) "+locDetailBean.getAmount()+
						"\n\t\t\t- LOC Reuested for "+locDetailBean.getLocFor()+"\n\n");				
			}
		}
		Collection<LOCDetailBean> modifiedLOCDetailBeans = requestLOCForm.getRequestlocDatagrid().getModifiedData();
		if(!MisUtility.ifEmpty(modifiedLOCDetailBeans)){
			for (LOCDetailBean locDetailBean : modifiedLOCDetailBeans) {
				buffer.append("\n\t LOC Request for scheme "+locDetailBean.getSchemeId()+"->"+locDetailBean.getSchemeName()+
						"\n\t\t\t- LOC Request ID "+locDetailBean.getLocId()+
						"\n\t\t\t- LOC Requested Amount(In lacs) "+locDetailBean.getAmount()+
						"\n\t\t\t- LOC Reuested for "+locDetailBean.getLocFor()+"\n\n");			
			}
		}
		/*Collection<LOCDetailBean> deletedLOCDetailBeans = requestLOCForm.getRequestlocDatagrid().getDeletedData();
		if(!MisUtility.ifEmpty(deletedLOCDetailBeans)){
			for (LOCDetailBean locDetailBean : deletedLOCDetailBeans) {
				buffer.append("\n\t LOC Request for scheme "+locDetailBean.getSchemeId()+"->"+locDetailBean.getSchemeName()+
						"\n\t\t\t- LOC Request ID "+locDetailBean.getLocId()+
						"\n\t\t\t- LOC Requested Amount(In lacs) "+locDetailBean.getAmount()+
						"\n\t\t\t- LOC Reuested for "+locDetailBean.getLocFor()+"\n\n");		
			}
		}*/
		buffer.append("\n\nRegards \n IT-Help Desk\n Department of Water Supply and Sanitation, Punjab");
		return buffer.toString();
	}
	
	}
