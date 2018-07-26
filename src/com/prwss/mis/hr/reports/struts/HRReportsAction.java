package com.prwss.mis.hr.reports.struts;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.prwss.mis.masters.employee.dao.EmployeeDesignationBean;
import com.prwss.mis.masters.employee.dao.EmployeeDesignationDao;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;
import com.prwss.mis.pmm.report.struts.EstimatesAwardContractsReportForm;

public class HRReportsAction extends DispatchAction {
	private ProgramDao programDao;
	private LocationDao locationDao;
	@SuppressWarnings("unused")
	private MISSessionBean misAuditBean;
	private MISJdcDaoImpl misJdcDaoImpl;
	private MISJasperUtil misJasperUtil;
	@SuppressWarnings("rawtypes")
	private Map parameters;
	private Logger log = Logger.getLogger(HRReportsAction.class);
	private EmployeeDesignationDao employeeDesignationDao;
	
	public void setEmployeeDesignationDao(
			EmployeeDesignationDao employeeDesignationDao) {
		this.employeeDesignationDao = employeeDesignationDao;
	}
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
		HRReportsForm hrReportsForm=(HRReportsForm)form;
		request.setAttribute("misJdcDaoImpl",misJdcDaoImpl);
		request.setAttribute("form",hrReportsForm);
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	public void filePDF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HRReportsForm hrReportsForm=(HRReportsForm)form;
		String jasperFile=hrReportsForm.getJasperFile();		
		String fileTitle=hrReportsForm.getFileTitle();
		setWhere(hrReportsForm,request);
		JRSwapFileVirtualizer virtualizer = null; 
		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 40000, 15000), false);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		misJasperUtil.exportToPDF(jasperFile,fileTitle, parameters, request, response);				
	}	
	public void fileExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HRReportsForm hrReportsForm=(HRReportsForm)form;
		String jasperFile=hrReportsForm.getJasperFile();
		String fileTitle=hrReportsForm.getFileTitle();
		setWhere(hrReportsForm,request);
		JRSwapFileVirtualizer virtualizer = null; 
		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 40000, 15000), false);
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
			Set<LabelValueBean> locationIds = getLocations();
			request.getSession().setAttribute("locationIds", locationIds);
			
			//EstimatesAwardContractsReportForm estimatesAwardContractsReportForm = (EstimatesAwardContractsReportForm)form;
			//refreshEstimatesAwardContractsReportForm(estimatesAwardContractsReportForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		System.out.println("Unspecified.......EstimatesAwardContractsReport");
		return mapping.findForward("cScreen");
	}
	
	public ActionForward getDesignations(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws MISException {
		List<EmployeeDesignationBean> employeeDesignationBeans = new ArrayList<EmployeeDesignationBean>();
		EmployeeDesignationBean employeeDesignationBean = new EmployeeDesignationBean();
		StringBuffer buffer = new StringBuffer();
		try {
			System.out.println("employee type================="+request.getParameter("employeeType"));
			if(MisUtility.ifEmpty(request.getParameter("employeeType"))){
				employeeDesignationBean.setEmployeeType(request.getParameter("employeeType"));
				employeeDesignationBeans = employeeDesignationDao.getEmployeeDesignation(employeeDesignationBean);
				buffer.append("<option value=''>Select </option>");
			}
			else{
				buffer.append("<option value=''>Select </option>");
			}
			if(!MisUtility.ifEmpty(employeeDesignationBeans))	{
				for(EmployeeDesignationBean empDesignationBean : employeeDesignationBeans){
					buffer.append("<option value=\"").append(empDesignationBean.getDesignationId()).append("\">");
					buffer.append(empDesignationBean.getDesignationName());
					buffer.append("</option>");
				//	System.out.println(buffer);
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
	private void refreshhrReportForm(HRReportsForm hrReportsForm){
		hrReportsForm.setSelectZone(null);
		hrReportsForm.setSelectCircle(null);
		hrReportsForm.setSelectDistrict(null);
		hrReportsForm.setSelectProgram(null);
		hrReportsForm.setApprovalStatus(null);
		hrReportsForm.setAsOnDate(null);		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setWhere(HRReportsForm hrReportsForm,HttpServletRequest request){
		String innerWhere=" and 1=1 ";
		String selectZone=hrReportsForm.getSelectZone();	
		System.out.println("Zone--"+selectZone);
		String zoneId=hrReportsForm.getZoneId();
		System.out.println("Zone ID="+zoneId);
		String selectCircle=hrReportsForm.getSelectCircle();
		String circleId=hrReportsForm.getCircleId();
		String selectDistrict=hrReportsForm.getSelectDistrict();
		String districtId=hrReportsForm.getDistrictId();
		String selectProgram=hrReportsForm.getSelectProgram();
		String selectEmployeeType=hrReportsForm.getSelectEmployeeType();
		String programId=hrReportsForm.getProgramId();
		String selectDivisionalOfficeId=hrReportsForm.getSelectDivisionalOfficeId();
		String divisionalOfficeId=hrReportsForm.getDivisionalOfficeId();		
		String approvalStatus=hrReportsForm.getApprovalStatus();
		String selectReport=hrReportsForm.getSelectReport();
		String fromPeriod=hrReportsForm.getFromDate();
		String toPeriod=hrReportsForm.getToDate();
		String locationId = hrReportsForm.getLocationId();
		String fin_code = hrReportsForm.getFinYearId();
		String month_code = hrReportsForm.getMonthId();
		String employeeType=hrReportsForm.getEmployeeType();
		String selectPeriod = hrReportsForm.getSelectPeriod();
		String selectDesignation=hrReportsForm.getSelectDesignation();
		String designation=hrReportsForm.getDesignation();
		String from = "All";
		String to = "All";
		String from_date = "All";
		String to_date = "All";
		String desig_id="All";
		//String desig_id="1=1";
		parameters = new HashMap();
	    String queryString="";
		String where="1=1";
		/*if(selectZone.equals("S")){
			where=where+" and zone_name='"+zoneId+"' ";
			queryString=queryString+" Zone: "+zoneId;
		}else{
			queryString=queryString+" Zone: All";
		}
		if(selectCircle.equals("S")){
			where=where+" and circle_name='"+circleId+"' ";
			queryString=queryString+", Circle: "+circleId;
		}else{
			queryString=queryString+", Circle: All";
		}
		if(selectDistrict.equals("S")){
			where=where+"and district_name='"+districtId+"' ";
			queryString=queryString+", District: "+districtId;
		}else{
			queryString=queryString+", District: All";
		}*/
		
		if(selectZone.equals("S"))	
			{
			where=where+" and zone_id='"+zoneId+"'";
			System.out.println(where);
			}
		if(selectCircle.equals("S"))
			{
			where=where+" and circle_id='"+circleId+"' ";
			System.out.println(where);
			}
		if(selectDistrict.equals("S"))
			{
			where=where+"and district_id='"+districtId+"' ";
			System.out.println(where);
			}
		if(selectDivisionalOfficeId.equals("S"))
			{
			where=where+"and location_id='"+divisionalOfficeId+"' ";
			System.out.println(where);
			}
		
		
	/*	if(selectReport.equals("HRRPT001_06")||selectReport.equals("HRRPT001_02")){
			where = "location_id = '"+locationId+"'";
		}*/
		System.out.println("selectEmployeeType: "+selectEmployeeType);
		if(!employeeType.equals("All")){
			System.out.println("employee");
			if(selectReport.equals("HRRPT001_06")){
				where = where+" and employee_type='"+employeeType+"'";
			}
		}
		
		if(selectDesignation.equals("S"))	{
				System.out.println("inside if selectDesignation");
			if(selectReport.equals("HRRPT001_06")){
					where=where+" and desig='"+designation+"'";
			}
		}
		System.out.println("selectPeriod: " + selectPeriod);
		System.out.println("fromPeriod: " + fromPeriod);
		System.out.println("toPeriod: " + toPeriod);
		from = fromPeriod == null ? "All" : fromPeriod;
		System.out.println("from: " + from);
		to = toPeriod == null ? "All" : toPeriod;
		System.out.println("to: " + to);
		from_date = fromPeriod == null ? "All" : MisUtility
				.convertStringToDate(fromPeriod).toString();
		System.out.println("from_date: " + from_date);
		to_date = toPeriod == null ? "All" : MisUtility.convertStringToDate(
				toPeriod).toString();
		System.out.println("to_date: " + to_date);
		
		//design_id=designation==""?"All":designation;
		System.out.println("desig_id="+desig_id);
		
		parameters.put("where", where);
	    parameters.put("queryString", queryString);
		parameters.put("innerWhere", innerWhere);
		parameters.put("fin_code", fin_code);
		parameters.put("from", from);
		parameters.put("to", to);
		parameters.put("from_date", from_date);
		parameters.put("to_date", to_date);
		parameters.put("month_code", month_code);
		//parameters.put("designation_id",desig_id);
		parameters.put("employee_type",employeeType);
		if(selectDesignation.equals("S")){
		parameters.put("desig_id",designation);
		}
		else{
			parameters.put("desig_id",selectDesignation);
			
		}
		
		
		
		System.out.println("selectDesignation="+selectDesignation);
		System.out.println("selectReport: "+selectReport);
		System.out.println("Action: where : "+where);
		System.out.println("Action: innerWhere : "+innerWhere);
		System.out.println("Action: queryString: "+queryString);
		System.out.println("Designation===="+designation);
	}	
	
	private Set<LabelValueBean> getLocations(){
		Set<LabelValueBean> locationIds = null;
		Set<LocationBean> locationBeans = null;
		try {
			List<String> locationTypeList = new ArrayList<String>();
			locationTypeList.add("DPMC");
			locationTypeList.add("DO");
			locationTypeList.add("SPMC");
			locationTypeList.add("CIRCLE");
			locationTypeList.add("ZONE");
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
	}

}
