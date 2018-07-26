package com.prwss.mis.pmm.operatingarrangement;

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
import org.jfree.util.Log;
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
import com.prwss.mis.masters.scheme.SchemeMasterBO;
import com.prwss.mis.masters.scheme.SchemeMasterBean;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderBean;
import com.prwss.mis.masters.scheme.dao.SchemeHeaderDao;
import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;
import com.prwss.mis.masters.scheme.struts.SchemeVillageCommisionAction;
import com.prwss.mis.masters.scheme.struts.SchemeVillageCommisionForm;
import com.prwss.mis.masters.scheme.struts.SchemeVillageCommissioningVillageGridBean;
import com.prwss.mis.masters.village.dao.VillageBean;
import com.prwss.mis.masters.village.dao.VillageDao;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class OperatingArrangementAction extends DispatchAction{
	Logger log = Logger.getLogger(SchemeVillageCommisionAction.class);
	private MISSessionBean misSessionBean;
	private SchemeMasterBO schemeMasterBO;
	private ProgramDao programDao;
	private SchemeHeaderDao schemeHeaderDao;
	private LocationDao locationDao;
	private VillageDao villageDao;
	private MISJdcDaoImpl misJdcDaoImpl;
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	
	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}

	public void setVillageDao(VillageDao villageDao) {
		this.villageDao = villageDao;
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
		System.out.println("In Find R");
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
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		}
		
		this.setAttrib(request);
		try {
			OperatingArrangementForm operatingArrangementForm = (OperatingArrangementForm)form;
		
			String schemeCode = operatingArrangementForm.getSchemeCode();
			System.out.println("scheme Code----"+schemeCode);
			String[] str = schemeCode.split("-");
			System.out.println("str[0]---->"+str[0]);
			operatingArrangementForm.setSchemeCode(str[0]);
			operatingArrangementForm.setSchemeUpgrade(str[1]);
			
			SchemeMasterBean schemeMasterBean = schemeMasterBO.findSchemeMaster(operatingArrangementForm, statusList);
			List<SchemeHeaderBean> schemeHeaderBeans = null;
			List<SchemeVillageBean> schemeVillageBeans = null;
			if(MisUtility.ifEmpty(schemeMasterBean)){
			request.setAttribute("level2", "true");
			schemeHeaderBeans = schemeMasterBean.getSchemeHeaderBeans();
			for (SchemeHeaderBean schemeHeaderBean : schemeHeaderBeans) {
				operatingArrangementForm.setSchemeCode(schemeHeaderBean.getSchemeId());
				request.setAttribute("schemeId", schemeHeaderBean.getSchemeId()+"-"+schemeHeaderBean.getSchemeUpgraded());
				operatingArrangementForm.setProgramId(schemeHeaderBean.getProgId());
				operatingArrangementForm.setSchemeName(schemeHeaderBean.getSchemeName());
				operatingArrangementForm.setLocationId(schemeHeaderBean.getLocationId());
				operatingArrangementForm.setSchemeSource(schemeHeaderBean.getSchemeSource());
				}
			schemeVillageBeans = schemeMasterBean.getSchemeVillageBeans();
			//System.out.println("SchemeVillageBeans"+schemeVillageBeans);
			if(!MisUtility.ifEmpty(schemeVillageBeans))
				operatingArrangementForm.setVillageOperatingDatagrid(getVillageOperatingDatagrid(schemeVillageBeans));
			
			}else{
				refreshOperatingArrangement(operatingArrangementForm);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				System.out.println("Message from"+message);
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
		System.out.println("--------------------------OperatingArrangementForm-----------------------------------");
		this.setAttrib(request);
		OperatingArrangementForm operatingArrangementForm = (OperatingArrangementForm)form;
		System.out.println("--------------------------OperatingArrangementForm-----------------------------------scheme source=="+operatingArrangementForm.getSchemeSource()+"scheme name="+operatingArrangementForm.getSchemeName()+"village operting data grid=============="+operatingArrangementForm.getVillageOperatingDatagrid());
		
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
			
			System.out.println("@: "+operatingArrangementForm);
			//villageBusinessSchemeCommissionedDateLogicCall(operatingArrangementForm.getVillageOperatingDatagrid(), operatingArrangementForm.getSchemeCode());
			operatingBusinessOperationDateLogicCall(operatingArrangementForm.getVillageOperatingDatagrid(),operatingArrangementForm.getVillageId());
			status = schemeMasterBO.updateSchemeMaster(operatingArrangementForm, misSessionBean);
			if (status){
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.update", "Scheme Information ","Scheme ID -->"+operatingArrangementForm.getSchemeCode());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Operating Arrangement Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		} catch (MISException e) {
			/*if (MISExceptionCodes.MIS013.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("MST024.PRO.error",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}*/
			
			 if(MISExceptionCodes.MIS013.equals(e.getCode())){
				 System.out.println("Error is:- "+ e.getLocalizedMessage());
					System.out.println("Error is:- "+ e.getMessage());
				System.out.println("e.getCode()==="+e.getCode());
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			else if(MISExceptionCodes.MIS012.equals(e.getCode())){
				System.out.println("Error is:- "+ e.getLocalizedMessage());
				System.out.println("Error is:- "+ e.getMessage());
				log.error(e.getLocalizedMessage(),e);
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields",e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			
			
		else{
			System.out.println("Error is:- "+ e.getLocalizedMessage());
			System.out.println("Error is:- "+ e.getMessage());
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.dateNotPresent1","");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		    log.error(e.getLocalizedMessage(),e);
		    e.printStackTrace();
			} 
		}
		catch (Exception e) {
			System.out.println("Error is:- "+ e.getLocalizedMessage());
			System.out.println("Error is:- "+ e.getMessage());
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Updatation Operating Arrangement Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			log.error(e);
			e.printStackTrace();
		}
		refreshOperatingArrangement(operatingArrangementForm);
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode",request.getParameter("d__mode"));
		request.setAttribute("d__ky", "schemeId@locationId");
		if(request.getParameter("locationId")!=null&&request.getParameter("locationId")!=""){
			request.setAttribute("d__ky", "schemeId@locationId@programId@schemeSource");
		}
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		ConvertUtils.register(new BigDecimalConverter(null), java.math.BigDecimal.class);
		this.setAttrib(request);
		OperatingArrangementForm operatingArrangementForm = (OperatingArrangementForm)form;
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			//System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		Set<LabelValueBean> locationIds = getLocationIds();
		request.getSession().setAttribute("locationIds", locationIds);
		
		Set<LabelValueBean> programIds = getProgramIds();
		request.getSession().setAttribute("programIds", programIds);
		refreshOperatingArrangement(operatingArrangementForm);
		return mapping.findForward("display");
	}
	
	private void refreshOperatingArrangement(OperatingArrangementForm operatingArrangementForm){
		operatingArrangementForm.setProgramId(null);
		operatingArrangementForm.setSchemeCode(null);
		operatingArrangementForm.setSchemeName(null);
		operatingArrangementForm.setLocationId(null);
		operatingArrangementForm.setSchemeSource(null);
		operatingArrangementForm.setVillageOperatingDatagrid(getVillageOperatingDatagrid(null));
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
	
	private Datagrid getVillageOperatingDatagrid(List<SchemeVillageBean> schemeVillageBeans) {
		 Datagrid villageOperatingDatagrid= null;
		 List<String> statusList = new ArrayList<String>();
		 statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		 statusList.add(MISConstants.MASTER_STATUS_VERIFIED); // Temperary to be removed
			List<VillageOperatingGridBean> schemeVillageCommissioningVillageGridBeans = new ArrayList<VillageOperatingGridBean>();
			try {
				System.out.println("-----------------test----------------------------");
				villageOperatingDatagrid = Datagrid.getInstance();
				villageOperatingDatagrid.setDataClass(VillageOperatingGridBean.class);
				if (!MisUtility.ifEmpty(schemeVillageBeans)) {
					VillageOperatingGridBean gridBean = null;
					for (SchemeVillageBean bean : schemeVillageBeans) {
						gridBean = new VillageOperatingGridBean();
						gridBean.setSchemeId(bean.getSchemeId());
						gridBean.setVillageCommissioningDate(MisUtility.convertDateToString(bean.getSchemeCommissionedDate()));
						gridBean.setVillageId(bean.getVillageId());
						gridBean.setSchemeUpgraded(bean.getSchemeUpgraded());
						gridBean.setSchemeOperatedBy(bean.getSchemeOperatedBy());
						System.out.println("scheme operated by ==="+bean.getSchemeOperatedBy());
						gridBean.setSupplyServiceLevel(bean.getSupplyServiceLevel());
						gridBean.setOperationArrangementDate(MisUtility.convertDateToString((bean.getOperationDate())));
						
						/**Earliew Code Working KD
						
						gridBean.setSchemeHours(String.valueOf(bean.getSchemeHours()));
						gridBean.setSchemeFP(bean.getSchemeFP());
						 */
						if(!(bean.getVillageCategory()==null || bean.getVillageCategory()=="" )){
							gridBean.setVillagecategory(bean.getVillageCategory().trim());
						}else{
							gridBean.setVillagecategory("");
						}
						
						/**
						 * KD WOK
						 */

						VillageBean villageBean = new VillageBean();
						villageBean.setVillageId(bean.getVillageId());
						VillageBean villageFindBean = villageDao.findVillage(villageBean, statusList).get(0);
						//System.out.println("villageFindBean"+villageFindBean);
						gridBean.setVillageName(villageFindBean.getVillageName());
						gridBean.setHabitationType(villageFindBean.getHabitationType());
						schemeVillageCommissioningVillageGridBeans.add(gridBean);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
			}
			villageOperatingDatagrid.setData(schemeVillageCommissioningVillageGridBeans);
			return villageOperatingDatagrid;
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
	
	public ActionForward fetchScheme(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		List<SchemeHeaderBean> schemeHeaderBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		SchemeHeaderBean schemeHeaderBean = new SchemeHeaderBean();
		try {
			if(MisUtility.ifEmpty(request.getParameter("locationId"))){
				schemeHeaderBean.setLocationId(request.getParameter("locationId")); 
				schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean, statusList);
				buffer.append("<option value='' selected>");
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
	private void operatingBusinessOperationDateLogicCall(Datagrid operationDatagrid,String villageId) throws MISException{
		String message = null;
		StringBuilder argument2 = new StringBuilder();
        String argument1 = "village_id@operation_date";
        @SuppressWarnings("unchecked")
		Collection<VillageOperatingGridBean> villageOperatingBeans = operationDatagrid.getModifiedData();
        int count = 0;
        if(!MisUtility.ifEmpty(villageOperatingBeans)){
              
              for (VillageOperatingGridBean villageOperatingBean : villageOperatingBeans) {
            	 System.out.println(":date is"+villageOperatingBean.getOperationArrangementDate());
                    if(count==0){
                    argument2.append(villageOperatingBean.getVillageId()+"~"+villageOperatingBean.getOperationArrangementDate());
                    ++count;
                    }
                    else if (count>0)
                    {
                          argument2.append("@"+villageOperatingBean.getVillageId()+"~"+villageOperatingBean.getOperationArrangementDate());
                    }
            	  }
            	 
//            		  System.out.println("otherwise");
//            		  throw new MISException(MISExceptionCodes.MIS012,"Village Commissionig date can not be blank");
//            	  }
            	  
              }
        

		
		try{
		DataSource db = misJdcDaoImpl.getDataSource();
		Connection con = db.getConnection();
        CallableStatement cs = con.prepareCall("{ call prwss_main.bl_operation_date(?,?) }");
        System.out.println("argument1:"+argument1);
        System.out.println("argument2:"+argument2.toString());
        cs.setString(1, argument1);
        cs.setString(2, argument2.toString());
        cs.registerOutParameter(1, Types.VARCHAR);
        cs.execute();
        message = cs.getString(1);
        System.out.println("error message"+message);
		}catch(SQLException e){
			e.printStackTrace();
			 System.out.println("error message"+e.getLocalizedMessage().toString());
		}
		if(MisUtility.ifEmpty(message)){
			 System.out.println("error message:-  " +message);
            throw new MISException(MISExceptionCodes.MIS013,message);
      }

	}
	
}
