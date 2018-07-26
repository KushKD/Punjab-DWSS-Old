package com.prwss.mis.finance.report.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MISJasperUtil;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.component.dao.ComponentDao;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.finance.report.struts.FinanceReportsForm;


public class FinanceReportsAction extends DispatchAction {
private ProgramDao programDao;
private LocationDao locationDao;

private ComponentDao componentDao;
@SuppressWarnings("unused")
private MISSessionBean misAuditBean;
private MISJdcDaoImpl misJdcDaoImpl;private MISJasperUtil misJasperUtil;
@SuppressWarnings("rawtypes")
private Map parameters;

private Logger log = Logger.getLogger(FinanceReportsAction.class);

public void setLocationDao(LocationDao locationDao) {
	this.locationDao = locationDao;
}
public void setProgramDao(ProgramDao programDao) {
	this.programDao = programDao;
}
public void setMisJasperUtil(MISJasperUtil misJasperUtil) {
	this.misJasperUtil = misJasperUtil;
}
public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
	this.misJdcDaoImpl = misJdcDaoImpl;
}

public ComponentDao getComponentDao() {
	return componentDao;
}
public void setComponentDao(ComponentDao componentDao) {
	this.componentDao = componentDao;
}

@SuppressWarnings("rawtypes")
public void setParameters(Map parameters) {
	this.parameters = parameters;
}
@SuppressWarnings("rawtypes")
public Map getParameters() {
	return parameters;
}
public ActionForward view(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws MISException {
	FinanceReportsForm financeReportsForm=(FinanceReportsForm)form;
	request.setAttribute("misJdcDaoImpl",misJdcDaoImpl);
	request.setAttribute("form",financeReportsForm);
	this.setAttrib(request);
	return mapping.findForward("display");
}
@SuppressWarnings("unchecked")
public void filePDF(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	FinanceReportsForm financeReportsForm=(FinanceReportsForm)form;
	String jasperFile=financeReportsForm.getJasperFile();		
	String fileTitle=financeReportsForm.getFileTitle();
	setWhere(financeReportsForm,request);
	JRSwapFileVirtualizer virtualizer = null; 
	virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 90000, 15000), false);
	parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
	misJasperUtil.exportToPDF(jasperFile,fileTitle, parameters, request, response);	
}	
@SuppressWarnings("unchecked")
public void fileExcel(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	FinanceReportsForm financeReportsForm=(FinanceReportsForm)form;
	String jasperFile=financeReportsForm.getJasperFile();
	String fileTitle=financeReportsForm.getFileTitle();
	setWhere(financeReportsForm,request);
	JRSwapFileVirtualizer virtualizer = null; 
	virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 90000, 15000), false);
	parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
	misJasperUtil.exportToExcel(jasperFile,fileTitle, parameters, request, response);
}	
@Override
protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	this.setAttrib(request);
	try {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misAuditBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		Set<LabelValueBean> zones = getZones();
		request.getSession().setAttribute("zones", zones);
	    Set<LabelValueBean> circles = getCircles();
		request.getSession().setAttribute("circles", circles);
		Set<LabelValueBean> districts = getDistricts();
		request.getSession().setAttribute("districts",districts );
		Set<LabelValueBean> programs = getPrograms();
		request.getSession().setAttribute("programs", programs);
		Set<LabelValueBean> componentIds = getComponentIds();
		request.getSession().setAttribute("componentIds", componentIds);
		//EstimatesAwardContractsReportForm estimatesAwardContractsReportForm = (EstimatesAwardContractsReportForm)form;
		//refreshEstimatesAwardContractsReportForm(estimatesAwardContractsReportForm);
	} catch (Exception e) {
		e.printStackTrace();
		log.error(e);
	}
	System.out.println("Unspecified.......EstimatesAwardContractsReport");
	return mapping.findForward("cScreen");
}
private Set<LabelValueBean> getComponentIds() {
	Set<LabelValueBean> componentIds = null;
	List<ComponentBean> componentBeans = null;
	try{
		ComponentBean componentBean = new ComponentBean();
		List <String> statusList = new ArrayList<String>() ;
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		componentBeans = componentDao.findComponent(componentBean, statusList);
		componentIds = new HashSet<LabelValueBean>();
		for (ComponentBean componentBean2 : componentBeans) {
			componentIds.add(new LabelValueBean(componentBean2.getComponentId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+componentBean2.getComponentName(),componentBean2.getComponentId()));
			
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
private Set<LabelValueBean> getZones(){
	Set<LabelValueBean> zones = null;
	Set<LocationBean> locationBeans = null;
	try{
		locationBeans = locationDao.getLocationIds("ZONE");
		zones = new HashSet<LabelValueBean>();
		for (LocationBean locationBean2 : locationBeans) {
			zones.add(new LabelValueBean(locationBean2.getLocationId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+locationBean2.getLocationName(),locationBean2.getLocationId()));
		}			
	}catch(DataAccessException e){
		log.error(e);
		e.printStackTrace();
	}
	catch(Exception e){
		log.error(e);
		e.printStackTrace();
	}		
	return zones;	
}
private Set<LabelValueBean> getCircles(){
	Set<LabelValueBean> circles = null;
	Set<LocationBean> locationBeans = null;
	try{
		locationBeans = locationDao.getLocationIds("CIRCLE");
		circles = new HashSet<LabelValueBean>();
		for (LocationBean locationBean2 : locationBeans) {
			circles.add(new LabelValueBean(locationBean2.getLocationId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+locationBean2.getLocationName(),locationBean2.getLocationId()));
		}
	}catch(DataAccessException e){
		log.error(e);
		e.printStackTrace();
	}
	catch(Exception e){
		log.error(e);
		e.printStackTrace();
	}		
	return circles;	
}	
private Set<LabelValueBean> getDistricts(){
	Set<LabelValueBean> districts = null;
	Set<LocationBean> locationBeans = null;
	try{
		locationBeans = locationDao.getLocationIds("DISTRICT");
		districts = new HashSet<LabelValueBean>();
		for (LocationBean locationBean2 : locationBeans) {
			districts.add(new LabelValueBean(locationBean2.getLocationId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+locationBean2.getLocationName(),locationBean2.getLocationId()));
		}			
	}catch(DataAccessException e){
		log.error(e);
		e.printStackTrace();
	}
	catch(Exception e){
		log.error(e);
		e.printStackTrace();
	}		
	return districts;	
}
private Set<LabelValueBean> getPrograms(){
	Set<LabelValueBean> programs = null;
	List<ProgramBean> programBeans = null;
	try{
		ProgramBean programBean = new ProgramBean();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		programBeans = programDao.findProgram(programBean, statusList);
		programs = new HashSet<LabelValueBean>();
		for (ProgramBean programBean2 : programBeans) {
			programs.add(new LabelValueBean(programBean2.getProgramId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+programBean2.getProgramName(),programBean2.getProgramId()));				
		}			
	}catch(DataAccessException e){
		log.error(e);
		e.printStackTrace();
	}
	catch(Exception e){
		log.error(e);
		e.printStackTrace();
	}		
	return programs;	
}	
private void setAttrib(HttpServletRequest request) {
	request.setAttribute("Rpt", "rpt");
	request.setAttribute("menuId", request.getParameter("menuId"));
	request.setAttribute("d__mode", request.getParameter("d__mode"));
	request.setAttribute("d__ky", "locationId");
}
@SuppressWarnings("unused")
private void refreshAgendaReportsForm(FinanceReportsForm financeReportsForm){
	financeReportsForm.setSelectZone(null);
	financeReportsForm.setSelectCircle(null);
	financeReportsForm.setSelectDistrict(null);
	financeReportsForm.setSelectProgram(null);
	financeReportsForm.setApprovalStatus(null);
	financeReportsForm.setAsOnDate(null);		
}
@SuppressWarnings({ "rawtypes", "unchecked" })
private void setWhere(FinanceReportsForm financeReportsForm,HttpServletRequest request){
	String innerWhere=" and 1=1 ";
	String selectZone=financeReportsForm.getSelectZone();
	String zoneId=financeReportsForm.getZoneId();
	String selectCircle=financeReportsForm.getSelectCircle();
	String circleId=financeReportsForm.getCircleId();
	String selectDistrict=financeReportsForm.getSelectDistrict();
	String districtId=financeReportsForm.getDistrictId();
	String selectProgram=financeReportsForm.getSelectProgram();
	String programId=financeReportsForm.getProgramId();
	String activityId=financeReportsForm.getActivityId();
	String locationId=financeReportsForm.getLocationId();
	String divisionId=financeReportsForm.getDivisionId();
	String divisionId3=financeReportsForm.getDivisionId3();
	String blockId=financeReportsForm.getBlockId();
	String villageId=financeReportsForm.getVillageId();
	String gpwscId=financeReportsForm.getGpwscId();
	String approvalStatus=financeReportsForm.getApprovalStatus();
	String selectPeriod=financeReportsForm.getSelectPeriod();
	String selectActivity=financeReportsForm.getSelectActivity();
	String fromPeriod=financeReportsForm.getFromDate();
	String toPeriod=financeReportsForm.getToDate();
	String selectReport=financeReportsForm.getSelectReport();
    String  finYearId =financeReportsForm.getFinYearId();
    String  monthId =financeReportsForm.getMonthId();
    String  qtr =financeReportsForm.getQtr();
	parameters = new HashMap();
	String from = "All";
	String to = "All";
	String from_date = "All";
	String to_date = "All";
	String where="1=1 ";
	
	
	if(selectZone.equals("S")){
		where=where+" and zone_id='"+zoneId+"' ";
	}
	if(selectCircle.equals("S")){
		where=where+" and circle_id='"+circleId+"' ";
	}
	if(selectDistrict.equals("S")){		
			where=where+" and district_id='"+districtId+"' ";
	}
	if(selectActivity.equals("S") && selectReport.equals("FINRPT001_11")){
		where=where+" and activity_id='"+activityId+"' ";
	}
	if(approvalStatus.equals("A") || approvalStatus.equals("U")){		
		where=where+" and approval_status='"+approvalStatus+"' ";
	}
	if(selectReport.equals("FINRPT001_21")){
		where=where+" and committee_id='"+gpwscId+"' ";
		from_date="1940-04-01";
	}
	if(selectReport.equals("FINRPT001_17")){
		where=where+" and location_id='"+divisionId+"' ";
	}
	if(selectReport.equals("FINRPT001_6")){
		if((divisionId3!=null) && (!divisionId3.equals(""))){
			//where=where+" and request_to_location_id='"+divisionId3+"' ";
			where=where+" and location_id='"+divisionId3+"' ";
			
		//	System.out.println("where in loop"+where);
		}
	}	
	System.out.println("selectPeriod: "+selectPeriod);
	System.out.println("fromPeriod: "+fromPeriod);
	System.out.println("toPeriod: "+toPeriod);
	from = fromPeriod==null?"All":fromPeriod;
	//System.out.println("from: "+from);
	to = toPeriod==null?"All":toPeriod;
	//System.out.println("to: "+to);
	from_date = fromPeriod==null?"All":MisUtility.convertStringToDate(fromPeriod).toString();
	//System.out.println("from_date: "+from_date);
	to_date = toPeriod==null?"All":MisUtility.convertStringToDate(toPeriod).toString();
	
	parameters.put("where", where);
	parameters.put("qtr", qtr);
	parameters.put("fincode", finYearId);
	parameters.put("month", monthId);
	parameters.put("from", from);
	parameters.put("to", to);
	parameters.put("from_date", from_date);
	parameters.put("to_date", to_date);
	parameters.put("SUBREPORT_DIR",
	"/usr/apache-tomcat-6.0.32/webapps/PRWSS_MIS/finance/reports/");
	//parameters.put("innerWhere", innerWhere);
	System.out.println("selectReport: "+selectReport);
	System.out.println("Action: where : "+where);
	System.out.println("Action: fincode : "+finYearId);
	System.out.println("Action: month: "+monthId);
	System.out.println("Action: Quarter: "+qtr);
}
	}
