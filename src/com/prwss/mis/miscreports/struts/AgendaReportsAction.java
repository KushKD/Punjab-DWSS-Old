package com.prwss.mis.miscreports.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;

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
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;

public class AgendaReportsAction extends DispatchAction {

	private ProgramDao programDao;
	private LocationDao locationDao;
	@SuppressWarnings("unused")
	private MISSessionBean misAuditBean;
	private MISJdcDaoImpl misJdcDaoImpl;
	private MISJasperUtil misJasperUtil;
	@SuppressWarnings("rawtypes")
	private Map parameters;
	private Logger log = Logger.getLogger(WorksProgrammeReportsAction.class);

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
		AgendaReportsForm agendaReportsForm=(AgendaReportsForm)form;
		request.setAttribute("misJdcDaoImpl",misJdcDaoImpl);
		request.setAttribute("form",agendaReportsForm);
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	public void filePDF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AgendaReportsForm agendaReportsForm=(AgendaReportsForm)form;
		String jasperFile=agendaReportsForm.getJasperFile();		
		String fileTitle=agendaReportsForm.getFileTitle();
		setWhere(agendaReportsForm,request);
//		JRSwapFileVirtualizer virtualizer = null; 
//		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("C://tmp", 10000, 15000), false);
//		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		misJasperUtil.exportToPDF(jasperFile,fileTitle, parameters, request, response);	
	}	
	public void fileExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AgendaReportsForm agendaReportsForm=(AgendaReportsForm)form;
		String jasperFile=agendaReportsForm.getJasperFile();
		String fileTitle=agendaReportsForm.getFileTitle();
		setWhere(agendaReportsForm,request);
		//parameters.put("fincode", "4");
		//parameters.put("month", "05");
		//JRSwapFileVirtualizer virtualizer = null;
		
//		virtualizer = new JRSwapFileVirtualizer(20, new JRSwapFile("C://tmp", 5000, 10000), false);
//		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
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
			//EstimatesAwardContractsReportForm estimatesAwardContractsReportForm = (EstimatesAwardContractsReportForm)form;
			//refreshEstimatesAwardContractsReportForm(estimatesAwardContractsReportForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		System.out.println("Unspecified.......EstimatesAwardContractsReport");
		return mapping.findForward("cScreen");
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
	private void refreshAgendaReportsForm(AgendaReportsForm agendaReportsForm){
		agendaReportsForm.setSelectZone(null);
		agendaReportsForm.setSelectCircle(null);
		agendaReportsForm.setSelectDistrict(null);
		agendaReportsForm.setSelectProgram(null);
		agendaReportsForm.setApprovalStatus(null);
		agendaReportsForm.setAsOnDate(null);		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setWhere(AgendaReportsForm agendaReportsForm,HttpServletRequest request){
		String innerWhere=" and 1=1 ";
		String selectZone=agendaReportsForm.getSelectZone();
		String zoneId=agendaReportsForm.getZoneId();
		String selectCircle=agendaReportsForm.getSelectCircle();
		String circleId=agendaReportsForm.getCircleId();
		String selectDistrict=agendaReportsForm.getSelectDistrict();
		String districtId=agendaReportsForm.getDistrictId();
		String selectProgram=agendaReportsForm.getSelectProgram();
		String programId=agendaReportsForm.getProgramId();
		String approvalStatus=agendaReportsForm.getApprovalStatus();
		String selectPeriod=agendaReportsForm.getSelectPeriod();
		String fromPeriod=agendaReportsForm.getFromDate();
		String toPeriod=agendaReportsForm.getToDate();
		String selectReport=agendaReportsForm.getSelectReport();
	    String  finYearId =agendaReportsForm.getFinYearId();
	    String  monthId =agendaReportsForm.getMonthId();
	    String swap=agendaReportsForm.getSwap();
	    
	    String toDate=agendaReportsForm.getToDate1();
	    
	    System.out.println("--------------------sd-----------------date------------"+toDate);
		parameters = new HashMap();
		String queryString="";
		String where="1=1 ";
		
		
		if(selectZone.equals("S")){
			
				where=where+" and zone_id='"+zoneId+"' ";
			
			queryString=queryString+" Zone: "+zoneId;
		}else{
			queryString=queryString+" Zone: All";
		}
		if(selectCircle.equals("S")){
			
				where=where+" and circle_id='"+circleId+"' ";
			
			queryString=queryString+", Circle: "+circleId;
		}else{
			queryString=queryString+", Circle: All";
		}
		if(selectDistrict.equals("S")){
			
				where=where+"and district_id='"+districtId+"' ";
						
			queryString=queryString+", District: "+districtId;
		}else{
			queryString=queryString+", District: All";
		}
		if(selectProgram.equals("S")){
			if(selectReport.equals("AGDRPT001_4")||selectReport.equals("AGDRPT001_7")){
				where=where+"and prog_id='"+programId+"' ";
			
			}
			
			queryString=queryString+", Program: "+programId;
		}else{
			queryString=queryString+", Program: All";
			
		}
		if(approvalStatus.equals("A") || approvalStatus.equals("U")){
			where=where+"and approval_status='"+approvalStatus+"' ";
			queryString=queryString+", Approval Status: "+approvalStatus;
		}
		if(selectReport.equals("AGDRPT001_4")||selectReport.equals("AGDRPT001_7")){
			System.out.println(" inside 34 and 34 dtl----------------- "+swap);
			if(swap.equals("NONSWAP")){
				where=where+" and swap_nonswap='NONSWAP'";
			}else if(swap.equals("SWAP-NON IDA")){
				where=where+" and swap_nonswap='SWAP-NON IDA'";
			}else if(swap.equals("SWAP-IDA")){
				where=where+" and swap_nonswap='SWAP-IDA'";
			}	
		}
	System.out.println(fromPeriod);
		if(finYearId.equals("0"))
			finYearId="5";
		if(monthId.equals("0"))
			monthId="03";
		parameters.put("where", where);
		parameters.put("swap", swap);
		//parameters.put("queryString", queryString);
		parameters.put("fincode", finYearId);
		parameters.put("month", monthId);
		parameters.put("to_date",toDate);
		//parameters.put("innerWhere", innerWhere);
		System.out.println("Parameters: "+parameters.size()+":"+parameters.toString());
		System.out.println("selectReport: "+selectReport);
		System.out.println("Action: where : "+where);
		System.out.println("Action: fincode : "+finYearId);
		System.out.println("Action: month: "+monthId);
	}
}
