
package com.prwss.mis.masters.scheme.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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

import org.apache.commons.dbcp.BasicDataSource;
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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.common.util.servlets.GetFilterValues;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
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
import com.prwss.mis.masters.village.dao.PrwssVillageViewDao;
import com.prwss.mis.masters.village.dao.PrwssVillageViewWithMhOhDao;
import com.prwss.mis.masters.village.dao.VillageBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class SchemeHeaderVillageAction extends DispatchAction {
	Logger log = Logger.getLogger(SchemeHeaderVillageAction.class);
	
	
	private static DataSource dataSource;
	
	
	private MISSessionBean misSessionBean;
	private SchemeMasterBO schemeMasterBO;
	private ProgramDao programDao;
	private LocationDao locationDao;
	private SchemeHeaderDao schemeHeaderDao;
	private MISJdcDaoImpl misJdcDaoImpl;
	private PrwssVillageAllHabitationDao prwssVillageAllHabitationDao ;
	private CurrentFcVillageStatusDao currentFcVillageStatusDao;
	private PrwssVillageViewDao prwssVillageViewDao;
	private PrwssVillageViewWithMhOhDao prwssVillageViewWithMhOhDao;
	
	int count=0;
	int count1=0;
    int count2=0;
    int count4=0;
    public void setPrwssVillageViewWithMhOhDao(
			PrwssVillageViewWithMhOhDao prwssVillageViewWithMhOhDao) {
		this.prwssVillageViewWithMhOhDao = prwssVillageViewWithMhOhDao;
	}
	 public void setPrwssVillageViewDao(PrwssVillageViewDao prwssVillageViewDao) {
		this.prwssVillageViewDao = prwssVillageViewDao;
	}
    public void setCurrentFcVillageStatusDao(
			CurrentFcVillageStatusDao currentFcVillageStatusDao) {
		this.currentFcVillageStatusDao = currentFcVillageStatusDao;
	}
	public SchemeHeaderVillageAction() {
		// TODO Auto-generated constructor stub
	 
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


	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
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
			SchemeHeaderVillageForm schemeHeaderVillageForm = (SchemeHeaderVillageForm)form;
			SchemeMasterBean schemeMasterBean = schemeMasterBO.findSchemeMaster(schemeHeaderVillageForm, statusList);
			
			System.out.print("in find method of Action"+schemeMasterBean);
			
			//getSubDivisonalId(String divisionalId)
			//GetFilterValues
			
			List<SchemeHeaderBean> schemeHeaderBeans = null;
			StringBuffer buffer=new StringBuffer();
			//List<SchemeVillageBean> schemeVillageBeans = null;
			if(MisUtility.ifEmpty(schemeMasterBean)){
				schemeHeaderBeans = schemeMasterBean.getSchemeHeaderBeans();
				
				
				if(!MisUtility.ifEmpty(schemeHeaderBeans)){
					
					/*for(SchemeHeaderBean scheme:schemeHeaderBeans){
						 buffer=getSubDivisonalId(scheme.getSubDivisionId());
						 scheme.setSubDivisionId(scheme.getSubDivisionId());
						
					}*/
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
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		SchemeHeaderVillageForm schemeHeaderVillageForm = (SchemeHeaderVillageForm)form;
		boolean status = false;
		
		System.out.println("IN DELETE------------------");
		
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
		refreshSchemeHeaderVillage(schemeHeaderVillageForm);
		return mapping.findForward("display");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		
		request.setAttribute("level2","true");
		SchemeHeaderVillageForm schemeHeaderVillageForm = (SchemeHeaderVillageForm)form;
		System.out.println("location id---->"+schemeHeaderVillageForm.toString());
		String schemeId = null;
		String message2 = null;

		
		count1=0; 
		count2=0;
		
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			StringBuffer strngMessage = new StringBuffer();
			
			
			//System.out.println(schemeHeaderVillageForm);
		//if(!MisUtility.ifEmpty(schemeHeaderVillageForm.getSchemeName())|| !MisUtility.ifEmpty(schemeHeaderVillageForm.getProgramId())||!MisUtility.ifEmpty(schemeHeaderVillageForm.getLocationId())||!MisUtility.ifEmpty(schemeHeaderVillageForm.getSchemeSource())||schemeHeaderVillageForm.getBlockId().equals("Select Block")|| !MisUtility.ifEmpty(schemeHeaderVillageForm.getBlockId())||schemeHeaderVillageForm.getVillageId().equals("Select Village")||!MisUtility.ifEmpty(schemeHeaderVillageForm.getVillageId())||!MisUtility.ifEmpty(schemeHeaderVillageForm.getWaterWorksExistingNew())){
			
				
				
//				if(!(MisUtility.ifEmpty(schemeHeaderVillageForm.getSchemeName())))
//				{
//					strngMessage.append("<br> Please enter Scheme Name");
//				}			
//				if(!(MisUtility.ifEmpty(schemeHeaderVillageForm.getProgramId())))
//				{
//						strngMessage.append("<br> Please select Program");	
//				}
//				if(schemeHeaderVillageForm.getLocationId().equals("NOT SPECIFIED") )
//				{
//							strngMessage.append("<br> Please select Location");
//		     	}
//				if(!MisUtility.ifEmpty(schemeHeaderVillageForm.getSchemeSource())){
//					strngMessage.append("<br> Please select Scheme Source");
//				}
//				if(!MisUtility.ifEmpty(schemeHeaderVillageForm.getWaterWorksExistingNew())){
//					strngMessage.append("<br> Please select Scheme Type");
//				}
//				if(schemeHeaderVillageForm.getBlockId().equals("Select Block")|| !MisUtility.ifEmpty(schemeHeaderVillageForm.getBlockId())){
//					strngMessage.append("<br> Please select Block");
//				}
//				if(schemeHeaderVillageForm.getVillageId().equals("Select Village")||!MisUtility.ifEmpty(schemeHeaderVillageForm.getVillageId())){
//					strngMessage.append("<br> Please select Village");
//				}
//				
//				throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
//		}
//			if(schemeHeaderVillageForm.getSupplyServiceLevel().equals("0"))
//			{
//				 strngMessage.append("<br> Please select Water Supply Service Level");
//					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
//			}
			
			
		
			
			strngMessage = new StringBuffer();
			SchemeVillagesBean schemeVillagesBean=new SchemeVillagesBean();
			System.out.println("scheme type====in update==1=="+schemeHeaderVillageForm.getWaterWorksExisting());
			System.out.println("scheme type====in update==2=="+schemeHeaderVillageForm.getWaterWorksExistingNew());
			
			System.out.println("scheme type is ====="+schemeHeaderVillageForm.getWaterWorksExisting());
		
			if(MisUtility.ifEmpty(schemeHeaderVillageForm.getVillageDatagrid().getAddedData())){
				strngMessage.append("Please attach Habitations.");
				throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
			}
			/*if(schemeHeaderVillageForm.getProgramId().equals("PROG15")||schemeHeaderVillageForm.getProgramId()=="PROG15"){
				strngMessage.append("Entry for PROG15 is not allowed.Please select another Program Id" );
				throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
			}*/
			List<SchemeVillagesBean> schemeVillagesBeanLst=null;
			
			
			if(schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_TUBE_WELL_WITH_RO)
			||schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_ROOF_TOP)){
				
				schemeVillagesBean.setScheme_source(schemeHeaderVillageForm.getSchemeSource());
				schemeVillagesBean.setVillageId(schemeHeaderVillageForm.getVillageId());
				
				schemeVillagesBeanLst=schemeHeaderDao.getSchemevillages(schemeVillagesBean);
				
				/*if(!MisUtility.ifEmpty(schemeVillagesBeanLst)){
					strngMessage.append("Scheme already has been created for the source("+schemeHeaderVillageForm.getSchemeSource()+")");
					throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
				}*/
				if(!MisUtility.ifEmpty(schemeVillagesBeanLst)){
					message2 = villageBusinessSchemeCommissionedDateLogicCall(schemeHeaderVillageForm, MISConstants.D_MODE_PROCEDURE_ADD, misSessionBean.getUserId());
					if(message2 == null ){
						message2 = "";
					}
				}
			}
			
			schemeHeaderDao.getSchemevillages(schemeVillagesBean);
			
			if(!(schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_TUBE_WELL_WITH_RO)
					||schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_ROOF_TOP))){
			if(!(schemeHeaderVillageForm.getSchemeSource().equals("SEWERAGE"))){
			message2 = villageBusinessSchemeCommissionedDateLogicCall(schemeHeaderVillageForm, MISConstants.D_MODE_PROCEDURE_ADD, misSessionBean.getUserId());
				if(message2 == null ){
					message2 = "";
				}
			}
			}
			
			if(schemeHeaderVillageForm.getSchemeSource().equals("IMPROVEMENT")){
			schemeHeaderVillageForm.setWaterWorksExistingNew(MISConstants.SCHEME_TYPE_EXISTING);
			schemeHeaderVillageForm.setWatersupply("imp");
			}
			if(schemeHeaderVillageForm.getSchemeSource().equals("SEWERAGE")){
				schemeHeaderVillageForm.setWaterWorksExistingNew(MISConstants.SCHEME_TYPE_NA);
				schemeHeaderVillageForm.setWatersupply("sw");
				
				}
			/*if(schemeHeaderVillageForm.getSchemeSource().equals("WATERMETER")){
				schemeHeaderVillageForm.setWaterWorksExistingNew(MISConstants.SCHEME_TYPE_EXISTING);
				schemeHeaderVillageForm.setWatersupply("wm");
				schemeHeaderVillageForm.setSchemeSource("IMPROVEMENT");
				}*/
			if(schemeHeaderVillageForm.getWaterWorksExistingNew()==null){
				schemeHeaderVillageForm.setWaterWorksExistingNew(schemeHeaderVillageForm.getWaterWorksExisting());
				}
			
			/*if(schemeHeaderVillageForm.getSchemeSource().equals("SEWERAGE")&&schemeHeaderVillageForm.getSchemeSource().equals("HANDPUM")){
				schemeHeaderVillageForm.setWaterWorksLocation(false);
				}*/
			System.out.println("schemeHeaderVillageForm after if statement"+schemeHeaderVillageForm.toString());
			System.out.println("schemeHeaderVillageForm after if statement get water works existing"+schemeHeaderVillageForm.getWaterWorksExistingNew());
			
			System.out.println("schemeHeaderVillageForm after if statement"+schemeHeaderVillageForm.getWaterWorksExisting());
			System.out.println("schemeHeaderVillageForm after t"+request.getParameter("waterWorksExisting"));
			System.out.println("schemeHeaderVillageForm after t"+request.getParameter("waterWorksExisting"));
			
			schemeId = schemeMasterBO.saveSchemeMaster(schemeHeaderVillageForm, misSessionBean);
			
			
			if (MisUtility.ifEmpty(schemeId)){
				ActionMessage message =null;
				ActionErrors errors = new ActionErrors();
				if(schemeHeaderVillageForm.getSchemeSource().equals("SEWERAGE")){
					  message = new ActionMessage("success.scheme.save","<BR><font color='green'>Scheme Information is saved. Auto generated Scheme Id :"+schemeId+"</font>");
				}
				
				else if(schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_TUBE_WELL_WITH_RO)
						||schemeHeaderVillageForm.getSchemeSource().equals(MISConstants.VILLAGE_WATER_SOURCE_ROOF_TOP)){
					
					if(message2==null ){
						 message = new ActionMessage("success.scheme.save","<BR><font color='green'>Scheme Information is saved. Auto generated Scheme Id :"+schemeId+"</font>");
							
					}else{
						 message = new ActionMessage("success.scheme.save",message2+"<BR><font color='green'>Scheme Information is saved. Auto generated Scheme Id :"+schemeId+"</font>");
							
					}
					
					
				}
				
				
				else{
					 message = new ActionMessage("success.scheme.save",message2+"<BR><font color='green'>Scheme Information is saved. Auto generated Scheme Id :"+schemeId+"</font>");
				}
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				System.out.println(message);
				//refreshSchemeHeaderVillage(schemeHeaderVillageForm);
			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Scheme Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS015.equals(e.getCode())) {
				
				System.out.println("e.getcode in action------------++++++++++"+e.getCode());
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				
				
			}
			
			if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.location", e.getMessage());
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
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else if(MISExceptionCodes.MIS013.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}else{

			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Scheme Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Scheme Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		refreshSchemeHeaderVillage(schemeHeaderVillageForm);
		return mapping.findForward("display");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		this.setAttrib(request);
		String message2 = null;
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
			//System.out.println("@: "+schemeHeaderVillageForm);
			System.out.println("scheme type====in update==1=="+schemeHeaderVillageForm.getWaterWorksExisting());
			System.out.println("scheme type====in update==2=="+schemeHeaderVillageForm.getWaterWorksExistingNew());
			
			
			message2 = villageBusinessSchemeCommissionedDateLogicCall(schemeHeaderVillageForm, MISConstants.D_MODE_PROCEDURE_MODIFY, misSessionBean.getUserId());
			if(schemeHeaderVillageForm.getSchemeSource().equals("IMPROVEMENT")){
				schemeHeaderVillageForm.setWaterWorksExistingNew(MISConstants.SCHEME_TYPE_EXISTING);
				schemeHeaderVillageForm.setWatersupply("imp");
				}
			if(schemeHeaderVillageForm.getWaterWorksExistingNew()==null){
				schemeHeaderVillageForm.setWaterWorksExistingNew(schemeHeaderVillageForm.getWaterWorksExisting());
				}
			if(schemeHeaderVillageForm.getSchemeSource().equals("SEWERAGE")){
				schemeHeaderVillageForm.setWaterWorksExistingNew(MISConstants.SCHEME_TYPE_NA);
				schemeHeaderVillageForm.setWatersupply("sw");
				
				}
			if(schemeHeaderVillageForm.getSchemeSource().equals("IMPROVEMENT")){
				schemeHeaderVillageForm.setWaterWorksExistingNew(MISConstants.SCHEME_TYPE_EXISTING);
				schemeHeaderVillageForm.setWatersupply("imp");
				}
				if(schemeHeaderVillageForm.getSchemeSource().equals("SEWERAGE")){
					schemeHeaderVillageForm.setWaterWorksExistingNew(MISConstants.SCHEME_TYPE_NA);
					schemeHeaderVillageForm.setWatersupply("sw");
					
					}
		
			
			System.out.println("water supply"+schemeHeaderVillageForm.getWatersupply());
			
			System.out.println("Sub Category Program:"+schemeHeaderVillageForm.getSubCategoryProgramme());
			
			//Check he Category and the Sub category
			if(schemeHeaderVillageForm.getProgramId().equals("PROG30") ){
				if((schemeHeaderVillageForm.getSubCategoryProgramme().equalsIgnoreCase(" ")))
				{
				throw new MISException(MISExceptionCodes.MIS021, "In case of Prog IBRD 30 , you need to select the category.");
			}
				}else if(!schemeHeaderVillageForm.getProgramId().equals("PROG30")){
					if(!(schemeHeaderVillageForm.getSubCategoryProgramme().equalsIgnoreCase(" "))){
						throw new MISException(MISExceptionCodes.MIS022, "Sub Caegory can only be entered if Program Id  is IBRD 30.");
					}
					
				}
			
			
			status = schemeMasterBO.updateSchemeMaster(schemeHeaderVillageForm, misSessionBean);
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
				System.out.println("mis code"+e.getCode());
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.duplicate.entry", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			
			
			else if(MISExceptionCodes.MIS013.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else if(MISExceptionCodes.MIS021.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			} else if(MISExceptionCodes.MIS022.equals(e.getCode())){
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			
			else{
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
		refreshSchemeHeaderVillage(schemeHeaderVillageForm);
		return mapping.findForward("display");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		//System.out.println("Delete ... IEC Progress");
		this.setAttrib(request);
		String message2 = null;
		SchemeHeaderVillageForm schemeHeaderVillageForm = (SchemeHeaderVillageForm)form;
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
			message2 = villageBusinessSchemeCommissionedDateLogicCall(schemeHeaderVillageForm, MISConstants.D_MODE_PROCEDURE_POST, misSessionBean.getUserId());

			status = schemeMasterBO.postSchemeMaster(schemeHeaderVillageForm, misSessionBean);
			if(message2 == null ){
				message2 = "";
			}
			if (status){
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.post", "Scheme Information ","Scheme ID -->"+schemeHeaderVillageForm.getSchemeCode()+"<BR>"+message2);
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
		refreshSchemeHeaderVillage(schemeHeaderVillageForm);
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "schemeCode@locationId");
		request.setAttribute("d__auto", "schemeCode");
		if(request.getParameter("locationId")!=""&&request.getParameter("locationId")!=null){
			//System.out.println("before d_key");
			request.setAttribute("d__ky", "schemeCode@locationId@water@sewerage@improvement@schemeSource");
			//System.out.println("after d_key");
			
		}
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		count1=0;
		count2=0;
		this.setAttrib(request);
		SchemeHeaderVillageForm schemeHeaderVillageForm = (SchemeHeaderVillageForm)form;
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
		
		refreshSchemeHeaderVillage(schemeHeaderVillageForm);
		
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

	private void refreshSchemeHeaderVillage(SchemeHeaderVillageForm schemeHeaderVillageForm){
		count1=0;
		count2=0;
		schemeHeaderVillageForm.setProgramId(null);
		schemeHeaderVillageForm.setSchemeCode(null);
		schemeHeaderVillageForm.setSchemeName(null);
		schemeHeaderVillageForm.setLocationId(null);
		schemeHeaderVillageForm.setSchemeUpgraded("NO");
		schemeHeaderVillageForm.setRefSchemeCode(null);
		schemeHeaderVillageForm.setSchemeSource(null);
		schemeHeaderVillageForm.setVillageDatagrid(getVillageDatagrid(null));
		schemeHeaderVillageForm.setWaterWorksExistingNew(null);
		schemeHeaderVillageForm.setSubCategoryProgramme(" ");
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
		}
		catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
		}
		return programIds;	
	}
	
	private Set<LabelValueBean> getLocationIds(){
		Set<LabelValueBean> locationIds = null;
		Set<LocationBean> locationBeans = null;
		try{
//			ProgramBean programBean = new ProgramBean();
		 
		 
			locationBeans = locationDao.getLocationIds("DO");
			locationIds = new TreeSet<LabelValueBean>();
			for (LocationBean locationBean1 : locationBeans) {
				locationIds.add(new LabelValueBean(locationBean1.getLocationName()+" - ("+locationBean1.getLocationId()+")",locationBean1.getLocationId()));
				
			}
			
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
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
		String subDivisionId= request.getParameter("subDivisionId");
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		SchemeHeaderVillageForm schemeHeaderVillageForm = (SchemeHeaderVillageForm)form;
		try {
			List<SchemeVillageBean> schemeVillageBeans = null;
			schemeHeaderVillageForm.setSchemeCode(schemeId);
			SchemeMasterBean schemeMasterBean = schemeMasterBO.findSchemeMaster(schemeHeaderVillageForm, statusList);
			StringBuffer buffer=getSubDivisonalId1(subDivisionId);
			String subDivision=subDivisionId+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+buffer.toString();
			List<SchemeHeaderBean> schemeHeaderBeans = schemeMasterBean.getSchemeHeaderBeans();
			request.setAttribute("subDivisionId1", subDivisionId);
			if(!MisUtility.ifEmpty(schemeHeaderBeans)){
				for (SchemeHeaderBean schemeHeaderBean : schemeHeaderBeans) {
					
				System.out.println("subDivision-------inside---->"+schemeHeaderBean.getSubDivisionId());
				schemeHeaderVillageForm.setSchemeCode(schemeHeaderBean.getSchemeId());
				schemeHeaderVillageForm.setProgramId(schemeHeaderBean.getProgId());
				schemeHeaderVillageForm.setSchemeName(schemeHeaderBean.getSchemeName());
				schemeHeaderVillageForm.setLocationId(schemeHeaderBean.getLocationId());
				schemeHeaderVillageForm.setSchemeSource(schemeHeaderBean.getSchemeSource());
				schemeHeaderVillageForm.setWaterWorksExistingNew(schemeHeaderBean.getWaterWorksExistingNew());
				schemeHeaderVillageForm.setSchemeUpgraded(schemeHeaderBean.getSchemeUpgraded());
				schemeHeaderVillageForm.setRefSchemeCode(schemeHeaderBean.getRefSchemeId());
				schemeHeaderVillageForm.setWatersupply(schemeHeaderBean.getWatersupply());
				schemeHeaderVillageForm.setSubDivisionId(schemeHeaderBean.getSubDivisionId());
				request.setAttribute("refSchemeId",schemeHeaderBean.getRefSchemeId());
				//request.setAttribute("AssetsList", "Kush Kumar Dhawan");
			}
				schemeVillageBeans = schemeMasterBean.getSchemeVillageBeans();
				if(!MisUtility.ifEmpty(schemeVillageBeans)){
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
					buffer.append("<option value=\"").append(schemeHeaderBean2.getSchemeId()).append("\">");
					buffer.append(schemeHeaderBean2.getSchemeName()+" - ("+schemeHeaderBean2.getSchemeId()+")");
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
				SchemeHeaderVillageForm schemeHeaderVillageForm = (SchemeHeaderVillageForm)form;
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
						
							pid=schemeHeaderVillageForm.getProgramId();
						//	System.out.println("programis"+pid);
							if((prwssVillageAllHabitationBean1.getNcPcStatus().equalsIgnoreCase("NC"))||(prwssVillageAllHabitationBean1.getNcPcStatus().equalsIgnoreCase("PC"))){
									//System.out.println("in-side");
								if((pid.equals("PROG06"))||(pid.equals("PROG11"))||(pid.equals("PROG15"))||(pid.equals("PROG20"))||(pid.equals("PROG26"))||(pid.equals("PROG30"))){
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
					/*if(pid.equals("PROG15")||pid.equals("PROG30")){
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
					}*/
					
					
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
	
	
	
	public ActionForward fetchCurrentSubProgramIda(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res)
			throws MISException {
			//	Set<TenderHeaderBean> tenders = null;
				boolean status = false; 
				//List<PrwssVillageViewBeanWithMhOh> current3016VillageStatusBeans = null;
				try {
					/*if(req.getParameter("programId").equals("PROG15")||req.getParameter("programId")=="PROG15"){
						//strngMessage.append("Entry for PROG15 is not allowed.Please select another Program Id" );
						//throw new MISException(MISExceptionCodes.MIS012,strngMessage.toString());
					}*/
					//StringBuffer buffer = new StringBuffer();
					System.out.println("----------------------ida check--------------------3016-------------------");
					PrintWriter out = res.getWriter();
					/*if(MisUtility.ifEmpty(req.getParameter("villageId")))
					{ */
//						PrwssVillageViewBean prwssVillageViewBean = new PrwssVillageViewBean();
//						prwssVillageViewBean.setVillageId(req.getParameter("villageId"));
						System.out.println(req.getParameter("villageId"));
						log.debug("villageId"+req.getParameter("villageId"));
						
						//List<PrwssVillageAllHabitationBean> prwssVillageAllHabitationBean= prwssVillageAllHabitationDao.findPrwssAllHabitationView(villageBean);
						//current3016VillageStatusBeans = prwssVillageViewWithMhOhDao.find3016Villages(req.getParameter("villageId"));
						if(req.getParameter("programId").equals("PROG15")||req.getParameter("programId")=="PROG15"){
								status = false;
						}else{
							status = true;
						}
					//}
					if(status){
						out.print("OK");
					}else{
						out.print("Please select IDA-II program from the list");
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
	private String villageBusinessSchemeCommissionedDateLogicCall(SchemeHeaderVillageForm schemeHeaderVillageForm,String handler, String userId) throws MISException{
		String message = null;
		StringBuilder argument2 = new StringBuilder();
        String argument1 = "scheme_id@village_id";
        String schemeId = schemeHeaderVillageForm.getSchemeCode();
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
	
	
	/**
	 * KD WORK
	 */
	
	public ActionForward fetchblockfromsubdivision(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		List<ModelsubdivisionBlock> newschemeHeaderBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("subDivisionId"))){
				//schemeHeaderBean.setLocationId(request.getParameter("locationId")); 
				//schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
				
				// Call the Function Here 
				//TODO
				//getSchemeFromVillage
				
				newschemeHeaderBeans = getDataVillageScheme(request.getParameter("subDivisionId"));
				
				
				
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
				for (ModelsubdivisionBlock schemeHeaderBean2 : newschemeHeaderBeans) {
					buffer.append("<option value=\"").append(schemeHeaderBean2.getBlock_id()).append("\">");
					buffer.append(schemeHeaderBean2.getBlock_name()).append(" - (").append(schemeHeaderBean2.getBlock_id()).append(")");
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
	
	
	public static BasicDataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		// DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(MISConstants.driverClassName);
		dataSource.setUrl(MISConstants.url);
		dataSource.setUsername(MISConstants.dbUsername);
		dataSource.setPassword(MISConstants.dbPassword);
		return dataSource;
	}
	
	private List<ModelsubdivisionBlock> getDataVillageScheme(String parameter) {
		List<ModelsubdivisionBlock> outPatients2 = null;
		
		try {
			dataSource = getDataSource();

			NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("subdiv_id", parameter);
			
			String query32 ="select distinct district_id,divisional_office_id as location_id,sub_div,block_id, prwss_main.get_location_name(block_id) as block_name from prwss_main.mst_village where status<>'D' and sub_div = :subdiv_id";

			outPatients2 = jdbcTemplate.query(query32, params,new getblockfromsuvdiv());

			for (ModelsubdivisionBlock pmmtable : outPatients2) {
				System.out.println(pmmtable.toString());
			}

		} catch (Exception ex) {
			System.out.println("Death!! is Here" + ex.getLocalizedMessage());
		} finally {
			try {
				dataSource.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return outPatients2;
	}
	
	
	private class getblockfromsuvdiv implements RowMapper<ModelsubdivisionBlock> {

		@Override
		public ModelsubdivisionBlock mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			// TODO Auto-generated method stub

			ModelsubdivisionBlock villagescheme = new ModelsubdivisionBlock();
			villagescheme.setBlock_id(rs.getString("block_id"));
			villagescheme.setDistrict_id(rs.getString("district_id"));
			villagescheme.setLocation_id(rs.getString("location_id"));
			villagescheme.setSub_div(rs.getString("sub_div"));
			villagescheme.setBlock_name(rs.getString("block_name")); 
			


			return villagescheme;
		}

}
	public StringBuffer getSubDivisonalId1(String subDivision) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if(MisUtility.ifEmpty(subDivision)){
				locationBeans = locationDao.getChildLocationIdss(subDivision);
				for (LocationBean locationBean : locationBeans) {
					buffer.append(locationBean.getLocationName());
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}
	
	
}