
package com.prwss.mis.masters.scheme.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

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

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.masters.scheme.ExtentionSchemeMasterBO;
import com.prwss.mis.masters.scheme.SchemeMasterBO;
import com.prwss.mis.masters.scheme.SchemeMasterBean;
import com.prwss.mis.masters.scheme.dao.CurrentFcVillageStatusBean;
import com.prwss.mis.masters.scheme.dao.CurrentFcVillageStatusDao;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderBean;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderDao;
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;
import com.prwss.mis.masters.village.dao.PrwssVillageAllHabitationBean;
import com.prwss.mis.masters.village.dao.PrwssVillageAllHabitationDao;
import com.prwss.mis.masters.village.dao.PrwssVillageViewBeanWithMhOh;
import com.prwss.mis.masters.village.dao.PrwssVillageViewWithMhOhDao;
import com.prwss.mis.masters.village.dao.VillageBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class ExtentionSchemeMasterAction extends DispatchAction {
	Logger log = Logger.getLogger(SchemeHeaderVillageAction.class);
	private MISSessionBean misSessionBean;
	private SchemeMasterBO schemeMasterBO;
	private ProgramDao programDao;
	
	private SchemeHeaderDao schemeHeaderDao;
	private MISJdcDaoImpl misJdcDaoImpl;
	private PrwssVillageAllHabitationDao prwssVillageAllHabitationDao ;
	private CurrentFcVillageStatusDao currentFcVillageStatusDao;
	private LocationDao locationDao;	
	private PrwssVillageViewWithMhOhDao prwssVillageViewWithMhOhDao;
	private ExtentionSchemeMasterBO extentionSchemeMasterBO;
	  
	int count1=0;
    int count2=0;
    
    public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
    
    public void setExtentionSchemeMasterBO(
			ExtentionSchemeMasterBO extentionSchemeMasterBO) {
		this.extentionSchemeMasterBO = extentionSchemeMasterBO;
	}
    public void setPrwssVillageViewWithMhOhDao(
			PrwssVillageViewWithMhOhDao prwssVillageViewWithMhOhDao) {
		this.prwssVillageViewWithMhOhDao = prwssVillageViewWithMhOhDao;
	}
    public void setCurrentFcVillageStatusDao(
			CurrentFcVillageStatusDao currentFcVillageStatusDao) {
		this.currentFcVillageStatusDao = currentFcVillageStatusDao;
	}
	 
	
	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}
 
	  public void setPrwssVillageAllHabitationDao(
			PrwssVillageAllHabitationDao prwssVillageAllHabitationDao) {
		this.prwssVillageAllHabitationDao = prwssVillageAllHabitationDao;
	}
	public void setSchemeHeaderDao(SchemeHeaderDao schemeHeaderDao) {
		this.schemeHeaderDao = schemeHeaderDao;
	}

 


	public void setProgramDao(ProgramDao programDao) {
		this.programDao = programDao;
	}
	

	public void setSchemeMasterBO(SchemeMasterBO schemeMasterBO) {
		this.schemeMasterBO = schemeMasterBO;
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
		//	SchemeHeaderVillageForm schemeHeaderVillageForm = (SchemeHeaderVillageForm)form;
			ExtentionSchemeMasterForm extentionSchemeMasterForm  = (ExtentionSchemeMasterForm)form;
			SchemeMasterBean schemeMasterBean = schemeMasterBO.findSchemeMasterForExtendedScheme(extentionSchemeMasterForm, statusList);
			List<SchemeHeaderBean> schemeHeaderBeans = null;
			
			if(MisUtility.ifEmpty(schemeMasterBean)){
				schemeHeaderBeans = schemeMasterBean.getSchemeHeaderBeans();
				if(!MisUtility.ifEmpty(schemeHeaderBeans)){
					request.setAttribute("schemeCodeList", schemeHeaderBeans);
				}
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("find.list");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
//				refreshSchemeHeaderVillage(schemeHeaderVillageForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	



	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		String message2 = null;
		//SchemeHeaderVillageForm schemeHeaderVillageForm = (SchemeHeaderVillageForm)form;
			ExtentionSchemeMasterForm extentionSchemeMasterForm = (ExtentionSchemeMasterForm)form;
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
            //System.out.println("@: "+schemeHeaderVillageForm);
			message2 = villageBusinessSchemeCommissionedDateLogicCall(extentionSchemeMasterForm, MISConstants.D_MODE_PROCEDURE_MODIFY, misSessionBean.getUserId());
			status = extentionSchemeMasterBO.updateSchemeMaster(extentionSchemeMasterForm, misSessionBean);
			if(message2 == null ){
				message2 = "";
			}
			if (status){
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.scheme.save",message2+"<BR><font color = 'green'>Scheme Information is modified</font>");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Scheme Information");
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
				ActionMessage message = new ActionMessage("fatal.error.save","Updatation Scheme Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updatation Scheme Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
	//	refreshSchemeHeaderVillage(schemeHeaderVillageForm);
		return mapping.findForward("display");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		
		SchemeHeaderVillageForm schemeHeaderVillageForm = (SchemeHeaderVillageForm)form;
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
			status = schemeMasterBO.deleteSchemeMaster(schemeHeaderVillageForm, misSessionBean);
			if (status){
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.delete", "Scheme Information ","Scheme ID -->"+schemeHeaderVillageForm.getSchemeCode());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Deletion Scheme Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
				log.error(e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Deletion Scheme Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Deletion Scheme Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		System.out.println("I m in post---------");
		this.setAttrib(request);
		String message2 = null;
		ExtentionSchemeMasterForm extentionSchemeMasterForm = (ExtentionSchemeMasterForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				//System.out.println("NO AUDIT");
				return mapping.findForward("login");
			}
//		
			message2 = villageBusinessSchemeCommissionedDateLogicCall(extentionSchemeMasterForm, MISConstants.D_MODE_PROCEDURE_POST, misSessionBean.getUserId());
//			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@"+schemeHeaderVillageForm.getSchemeVillageBeans());
			System.out.println("Message is -----:"+message2);
			status = extentionSchemeMasterBO.postSchemeMaster(extentionSchemeMasterForm, misSessionBean);
			System.out.println("Status:--"+status);
			if(message2 == null ){
				message2 = "";
			}
			if (status){
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.post", "Scheme Information ","Scheme ID -->"+extentionSchemeMasterForm.getSchemeCode()+"<BR>"+message2);
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Authorization Scheme Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}if (MISExceptionCodes.MIS013.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("MST024.PRO.error", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{
				log.error(e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save","Authorization Scheme Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Authorization Scheme Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "schemeCode@locationId");
		request.setAttribute("d__auto", "schemeCode");
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		count1=0;
		count2=0;
		this.setAttrib(request);
		System.out.println("unspecified......................");
	//	SchemeHeaderVillageForm schemeHeaderVillageForm = (SchemeHeaderVillageForm)form;
		ExtentionSchemeMasterForm extentionSchemeMasterForm  = (ExtentionSchemeMasterForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		Set<LabelValueBean> locationIds = getLocationIds();
		request.getSession().setAttribute("locationIds", locationIds);
		
		Set<LabelValueBean> programIds = getProgramIds();
		request.getSession().setAttribute("programIds", programIds);
		
	    refreshSchemeHeaderVillage(extentionSchemeMasterForm);
		
		return mapping.findForward("display");
	}
	
	
	private Datagrid getVillageDatagrid(List<SchemeVillageBean> schemeVillageBeans) {
		Datagrid villageDatagrid = null;
		try {
			villageDatagrid = Datagrid.getInstance();
			villageDatagrid.setDataClass(SchemeVillageBean.class);
			if(!MisUtility.ifEmpty(schemeVillageBeans)){
			List<SchemeVillageBean> villageBeans = new ArrayList<SchemeVillageBean>(schemeVillageBeans);
			villageDatagrid.setData(villageBeans);		
			}else{
			List<SchemeVillageBean> villageBeans = new ArrayList<SchemeVillageBean>();
			villageDatagrid.setData(villageBeans);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
		return villageDatagrid;
	}

	
	private Set<LabelValueBean> getProgramIds(){
		Set<LabelValueBean> programIds = null;
		Set<ProgramBean> programBeans = null;
		try{
//			ProgramBean programBean = new ProgramBean();
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


	
	private Set<LabelValueBean> getLocationIds() {
		Set<LabelValueBean> locationIds = null;
		Set<LocationBean> locationBeans = null;
		try{
				 
				 
				locationBeans = locationDao.getLocationIds("DO");
				locationIds = new TreeSet<LabelValueBean>();
				for(LocationBean locationBean : locationBeans){
					locationIds.add(new LabelValueBean(locationBean.getLocationName()+" - ("+locationBean.getLocationId()+")",locationBean.getLocationId()));
				}
		}catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
//			e.printStackTrace();
		}catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
//			e.printStackTrace();
		}
		return locationIds;
		
	}
	public ActionForward fetchVillageName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
//		List<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
//		VillageBean villageBean = new VillageBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("villageId"))){
				LocationBean locationBean = new LocationBean();
				locationBean.setLocationId(request.getParameter("villageId"));
				LocationBean locationBean2 = locationDao.getLocation(locationBean);
//				for (SchemeHeaderBean schemeHeaderBean2 : schemeHeaderBeans) {
//					buffer.append("<option value=\"").append(schemeHeaderBean2.getSchemeId()).append("\">");
//					buffer.append(schemeHeaderBean2.getSchemeName()+" - ("+schemeHeaderBean2.getSchemeId()+")");
					buffer.append(locationBean2.getLocationName());
//				}
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
	
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		String schemeId = request.getParameter("schemeId");
		System.out.println("=================populate=========scheme_id============="+schemeId);
		
		String schemeUpgraded = request.getParameter("schemeUpgraded");
		System.out.println("==========================================================================");
		System.out.println("Scheme--Upgraded"+schemeUpgraded);
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		ExtentionSchemeMasterForm schemeHeaderVillageForm = (ExtentionSchemeMasterForm)form;
		try {
			List<SchemeVillageBean> schemeVillageBeans = null;
			schemeHeaderVillageForm.setSchemeCode(schemeId);
		//	schemeHeaderVillageForm.setSchemeUpgraded(schemeUpgraded);
			SchemeMasterBean schemeMasterBean = schemeMasterBO.findSchemeMasterForExtendedScheme1(schemeHeaderVillageForm, statusList);
			List<SchemeHeaderBean> schemeHeaderBeans = schemeMasterBean.getSchemeHeaderBeans();
			if(!MisUtility.ifEmpty(schemeHeaderBeans)){
				for (SchemeHeaderBean schemeHeaderBean : schemeHeaderBeans) {
					System.out.println("=============inside For Loop=============== ");
				schemeHeaderVillageForm.setSchemeCode(schemeHeaderBean.getSchemeId()+"-"+schemeHeaderBean.getSchemeUpgraded());
				schemeHeaderVillageForm.setProgramId(schemeHeaderBean.getProgId());
				schemeHeaderVillageForm.setLocationId(schemeHeaderBean.getLocationId());
				schemeHeaderVillageForm.setSchemeName(schemeHeaderBean.getSchemeName());
				schemeHeaderVillageForm.setSchemeSource(schemeHeaderBean.getSchemeSource());
				schemeHeaderVillageForm.setSchemeUpgraded(schemeHeaderBean.getSchemeUpgraded());
				schemeHeaderVillageForm.setRefSchemeCode(schemeHeaderBean.getRefSchemeId());
				schemeHeaderVillageForm.setWatersupply(schemeHeaderBean.getWatersupply());
				request.setAttribute("schemeCode",schemeHeaderBean.getSchemeId()+"-"+schemeHeaderBean.getSchemeUpgraded());
				request.setAttribute("locationId",schemeHeaderBean.getLocationId());
			}
				schemeVillageBeans = schemeMasterBean.getSchemeVillageBeans();
				if(!MisUtility.ifEmpty(schemeVillageBeans)){
					System.out.println("===================schemeVillageBeans======================"+schemeVillageBeans);
				schemeHeaderVillageForm.setVillageDatagrid(getVillageDatagrid(schemeVillageBeans));
				schemeHeaderVillageForm.setSchemeVillageBeans(schemeVillageBeans);
				}
			
}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		return mapping.findForward("display");
	}
	
	public ActionForward fetchScheme(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		List<SchemeHeaderBean> schemeHeaderBeans = null;
		System.out.println("request location"+request.getParameter("locationId"));
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				schemeHeaderBean.setLocationId(request.getParameter("locationId")); 
				schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
				buffer.append("<option value='NA'>");
				buffer.append("Select");
				buffer.append("</option>");
				for (SchemeHeaderBean schemeHeaderBean2 : schemeHeaderBeans) {
					if(schemeHeaderBean2.getSchemeUpgraded().equals("NO")){
					buffer.append("<option value=\"").append(schemeHeaderBean2.getSchemeId()+"-"+schemeHeaderBean2.getSchemeUpgraded()).append("\">");
					buffer.append(schemeHeaderBean2.getSchemeName()+" - ("+schemeHeaderBean2.getSchemeId()+")");
					buffer.append("</option>");
					}else{
						buffer.append("<option value=\"").append(schemeHeaderBean2.getSchemeId()+"-"+schemeHeaderBean2.getSchemeUpgraded()).append("\">");
						buffer.append(schemeHeaderBean2.getSchemeName()+" - ("+schemeHeaderBean2.getSchemeId()+") -"+schemeHeaderBean2.getSchemeUpgraded() );
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
	
	public ActionForward fetchSchemeName(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("come.........");
		List<SchemeHeaderBean> schemeHeaderBeans = null;
		System.out.println("request location"+request.getParameter("schemeCode"));
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("schemeCode"))){
				schemeHeaderBean.setSchemeId(request.getParameter("schemeCode")); 
				 
				schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
				System.out.println("schemeHeaderBeans.get(0).getSchemeName()"+schemeHeaderBeans.get(0).getSchemeName());
				
				 
					buffer.append( schemeHeaderBeans.get(0).getSchemeName()) ;
					 
				 
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

	
	
	public ActionForward fetchCurrentFcVillageStatus(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
	throws MISException {
	//	Set<TenderHeaderBean> tenders = null;
		boolean status = false; 
		List<CurrentFcVillageStatusBean> currentFcVillageStatusBeans = null;
		try {
		//	StringBuffer buffer = new StringBuffer();
			PrintWriter out = res.getWriter();
			if(MisUtility.ifEmpty(req.getParameter("villageId")))
			{ 
				System.out.println(req.getParameter("villageId"));
				log.debug("villageId"+req.getParameter("villageId"));
				//List<PrwssVillageAllHabitationBean> prwssVillageAllHabitationBean= prwssVillageAllHabitationDao.findPrwssAllHabitationView(villageBean);
				currentFcVillageStatusBeans = currentFcVillageStatusDao.getVillageStatus(req.getParameter("villageId")); 
				if(!MisUtility.ifEmpty(currentFcVillageStatusBeans)){
					status = true;
				}else{
					status = false;
				}
			}
			if(status){
				out.print("OK");
			}else{
				out.print("Error: Selected Village can not be covered under Performance Improvement Scheme as its latest NC/PC status is not FC");
			}
			
	    
      }
				
		 catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	
	public ActionForward fetchCurrent3016VillageStatus(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
	throws MISException {
	//	Set<TenderHeaderBean> tenders = null;
		boolean status = false; 
		List<PrwssVillageViewBeanWithMhOh> current3016VillageStatusBeans = null;
		try {
			//StringBuffer buffer = new StringBuffer();
			System.out.println("----------------------3016--------------------3016-------------------");
			PrintWriter out = res.getWriter();
			if(MisUtility.ifEmpty(req.getParameter("villageId")))
			{ 
//				PrwssVillageViewBean prwssVillageViewBean = new PrwssVillageViewBean();
//				prwssVillageViewBean.setVillageId(req.getParameter("villageId"));
				System.out.println(req.getParameter("villageId"));
				log.debug("villageId"+req.getParameter("villageId"));
				
				//List<PrwssVillageAllHabitationBean> prwssVillageAllHabitationBean= prwssVillageAllHabitationDao.findPrwssAllHabitationView(villageBean);
				current3016VillageStatusBeans = prwssVillageViewWithMhOhDao.find3016Villages(req.getParameter("villageId"));
				if(!MisUtility.ifEmpty(current3016VillageStatusBeans)){
					status = true;
				}else{
					status = false;
				}
			}
			if(status){
				out.print("OK");
			}else{
				out.print("Error: Selected Village can not be covered under Water Supply Scheme as it is not in 3016 village list");
			}
			
	    
      }
				
		 catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	//This method is to call a business logic procedure in the database for logic check. Throws MISException for Error: and Return String for HINT:
	@SuppressWarnings("unchecked")
	private String villageBusinessSchemeCommissionedDateLogicCall(ExtentionSchemeMasterForm schemeHeaderVillageForm,String handler, String userId) throws MISException{
		String message = null;
		StringBuilder argument2 = new StringBuilder();
        String argument1 = "scheme_id@village_id";
        String[] str = schemeHeaderVillageForm.getSchemeCode().split("-");
        String schemeId = str[0];
        int count = 0;
        if(handler.equals(MISConstants.D_MODE_PROCEDURE_ADD)||handler.equals(MISConstants.D_MODE_PROCEDURE_MODIFY)){
        
		Collection<SchemeVillageBean> schemeVillageBeans = schemeHeaderVillageForm.getVillageDatagrid().getAddedData();
        
        if(!MisUtility.ifEmpty(schemeVillageBeans)){
              
              for (SchemeVillageBean schemeVillageBean : schemeVillageBeans) {
                    if(count==0){
                    argument2.append(schemeId+"~"+schemeVillageBean.getVillageId());
                    ++count;
                    }
                    else if (count>0)
                    {
                          argument2.append("@"+schemeId+"~"+schemeVillageBean.getVillageId());
                    }
              }
        }
        
        } 
        if(handler.equals(MISConstants.D_MODE_PROCEDURE_POST)){
        	List<SchemeVillageBean> schemeVillageBeans = schemeHeaderVillageForm.getSchemeVillageBeans();
        	if(!MisUtility.ifEmpty(schemeVillageBeans)){
                for (SchemeVillageBean schemeVillageBean : schemeVillageBeans) {
                      if(count==0){
                      argument2.append(schemeId+"~"+schemeVillageBean.getVillageId());
                      ++count;
                      }
                      else if (count>0)
                      {
                            argument2.append("@"+schemeId+"~"+schemeVillageBean.getVillageId());
                      }
                }
          }
        
        
        
        }

        
		
		try{
		DataSource db = misJdcDaoImpl.getDataSource();
		Connection con = db.getConnection();
        CallableStatement cs = con.prepareCall("{ call prwss_main.bl_MST021(?,?,?,?) }");
        System.out.println("$1,$2,$3,$4: "+argument1+":::"+argument2.toString()+":::"+handler+":::"+userId);
        cs.setString(1, argument1);
        cs.setString(2, argument2.toString());
        cs.setString(3, handler);
        cs.setString(4, userId);
//      System.out.println("SCHEMEHEADERVILLAGEACTION@argument1:"+argument1+"argument2"+argument2.toString()+handler);
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.execute();
        message = cs.getString(1);
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(MisUtility.ifEmpty(message)){
			message = message.trim();
			
			if(message.contains("ERROR:")){
				if(!schemeHeaderVillageForm.getSchemeSource().equals("SEWERAGE")){
			throw new MISException(MISExceptionCodes.MIS013,message);
			}}
			else if(message.contains("WARNING:")){
				return message;
			}
		}
return null;
	}

	private void refreshSchemeHeaderVillage(ExtentionSchemeMasterForm extentionSchemeMasterForm){
		count1=0;
		count2=0;
		extentionSchemeMasterForm.setProgramId(null);
		extentionSchemeMasterForm.setSchemeCode(null);
		extentionSchemeMasterForm.setSchemeName(null);
		extentionSchemeMasterForm.setLocationId(null);
		extentionSchemeMasterForm.setSchemeUpgraded("NO");
		extentionSchemeMasterForm.setRefSchemeCode(null);
		extentionSchemeMasterForm.setSchemeSource(null);
		extentionSchemeMasterForm.setVillageDatagrid(getVillageDatagrid(null));
		 
	}
	
	public ActionForward fetchNcPcStatus(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
	throws MISException {
	//	Set<TenderHeaderBean> tenders = null;
		String pid = null;
		 System.out.println("in the method");
		  
		List<String> statusList = new ArrayList<String>();
		try {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			PrintWriter out = res.getWriter();
			if(MisUtility.ifEmpty(req.getParameter("villageId")))
			{ 
			//	SchemeHeaderVillageForm schemeHeaderVillageForm = (SchemeHeaderVillageForm)form;
				
				ExtentionSchemeMasterForm extentionSchemeMasterForm = (ExtentionSchemeMasterForm)form;
				VillageBean villageBean=new VillageBean();
				 
				villageBean.setVillageId(req.getParameter("villageId"));
				System.out.println(req.getParameter("villageId"));
				log.debug("villageId"+req.getParameter("villageId"));
				List<PrwssVillageAllHabitationBean> prwssVillageAllHabitationBean= prwssVillageAllHabitationDao.findPrwssAllHabitationView(villageBean);
 
				if(!MisUtility.ifEmpty(prwssVillageAllHabitationBean))
				{
					// System.out.println("nulllllllllllllllllllll");
					PrwssVillageAllHabitationBean prwssVillageAllHabitationBean1=prwssVillageAllHabitationBean.get(0);
					
					System.out.println(prwssVillageAllHabitationBean1);
					
			 
					if(MisUtility.ifEmpty(prwssVillageAllHabitationBean1.getNcPcStatus()))
					{
						
							pid=extentionSchemeMasterForm.getProgramId();
						//	System.out.println("programis"+pid);
							if((prwssVillageAllHabitationBean1.getNcPcStatus().equalsIgnoreCase("NC"))||(prwssVillageAllHabitationBean1.getNcPcStatus().equalsIgnoreCase("PC"))){
									//System.out.println("in-side");
								if((pid.equals("PROG06"))||(pid.equals("PROG11"))||(pid.equals("PROG15"))||(pid.equals("PROG20"))||(pid.equals("PROG26"))){
									out.print("OK");
								}else{
								 System.out.println(2);	
								 out.print("Error: Village having NC/PC status can not be covered under Non-SWAp ");	
								 }
						    }else {
						    	 //System.out.println("status"+prwssVillageAllHabitationBean1.getNcPcStatus()); 
									out.print("OK");
							}
					}	 
					if(req.getParameter("schemeSource").equals("HANDPUMP")){ 
						out = res.getWriter();
						++count2;
						if(count2<=1){
							out.print("OK");
						}else{
							out.print("Error: More than one habitation can not be covered in HandPump scheme");
						}
					}else{
						count2=0;
					}
					if(pid.equals("PROG15")){
						System.out.println(pid);
						if(prwssVillageAllHabitationBean1.getHabitationType().equals("MH")){
							count1=1;
							out = res.getWriter();
						}
						if(count1==1){
							out.print("OK");
						}else{
							out.print("Error:Please attach atleast one MH ");
						}
					}
					
					
//			if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
				
			}
			
	    }
      }
				
		 catch (DataAccessException e) {
			log.error(e.getMostSpecificCause());
		} catch (IOException e) {
			log.error(e);
		}
		return null;
	}
	
	
	
	

	
}