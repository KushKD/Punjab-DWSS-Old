package com.prwss.mis.miscreports.struts;

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
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;

public class WorksProgrammeReportsAction extends DispatchAction {
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
		WorksProgrammeReportsForm worksProgrammeReportsForm=(WorksProgrammeReportsForm)form;
		request.setAttribute("misJdcDaoImpl",misJdcDaoImpl);
		request.setAttribute("form",worksProgrammeReportsForm);
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	public void filePDF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WorksProgrammeReportsForm worksProgrammeReportsForm=(WorksProgrammeReportsForm)form;
		String jasperFile=worksProgrammeReportsForm.getJasperFile();		
		String fileTitle=worksProgrammeReportsForm.getFileTitle();
		setWhere(worksProgrammeReportsForm,request);
		JRSwapFileVirtualizer virtualizer = null; 
		//virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("C://tmp", 10000, 15000), false);
		//parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		misJasperUtil.exportToPDF(jasperFile,fileTitle, parameters, request, response);	
	}	
	public void fileExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WorksProgrammeReportsForm worksProgrammeReportsForm=(WorksProgrammeReportsForm)form;
		String jasperFile=worksProgrammeReportsForm.getJasperFile();
		String fileTitle=worksProgrammeReportsForm.getFileTitle();
		setWhere(worksProgrammeReportsForm,request);
		JRSwapFileVirtualizer virtualizer = null;
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
	private void refreshWorksProgrammeReportsForm(WorksProgrammeReportsForm worksProgrammeReportsForm){
		worksProgrammeReportsForm.setSelectZone(null);
		worksProgrammeReportsForm.setSelectCircle(null);
		worksProgrammeReportsForm.setSelectDistrict(null);
		worksProgrammeReportsForm.setSelectProgram(null);
		worksProgrammeReportsForm.setApprovalStatus(null);
		worksProgrammeReportsForm.setAsOnDate(null);		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setWhere(WorksProgrammeReportsForm worksProgrammeReportsForm,HttpServletRequest request){
		String innerWhere=" and 1=1 ";
		String selectZone=worksProgrammeReportsForm.getSelectZone();
		String zoneId=worksProgrammeReportsForm.getZoneId();
		String selectCircle=worksProgrammeReportsForm.getSelectCircle();
		String circleId=worksProgrammeReportsForm.getCircleId();
		String selectDistrict=worksProgrammeReportsForm.getSelectDistrict();
		String districtId=worksProgrammeReportsForm.getDistrictId();
		String selectProgram=worksProgrammeReportsForm.getSelectProgram();
		String programId=worksProgrammeReportsForm.getProgramId();
		String approvalStatus=worksProgrammeReportsForm.getApprovalStatus();
		String selectPeriod=worksProgrammeReportsForm.getSelectPeriod();
		String fromPeriod=worksProgrammeReportsForm.getFromDate();
		String toPeriod=worksProgrammeReportsForm.getToDate();
		String selectReport=worksProgrammeReportsForm.getSelectReport();
		String  finYearId =worksProgrammeReportsForm.getFinYearId();
		String  monthId =worksProgrammeReportsForm.getMonthId();
		String swap=worksProgrammeReportsForm.getSwap();
		String from = "All";
		String to = "All";
		String from_date = "All";
		String to_date = "All";
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
	
	if(selectProgram.equals("S")){
		if(selectReport.equals("WRKRPT001_2")||selectReport.equals("WRKRPT001_4")||selectReport.equals("WRKRPT001_6")||selectReport.equals("WRKRPT001_8")||selectReport.equals("WRKRPT001_10")){
			where=where+"and prog_id='"+programId+"' ";		
		}		
		
		queryString=queryString+", Program: "+programId;
	}else{
		queryString=queryString+", Program: All";
		
	}
	if(selectReport.equals("WRKRPT001_2")||selectReport.equals("WRKRPT001_4")||selectReport.equals("WRKRPT001_6")||selectReport.equals("WRKRPT001_8")||selectReport.equals("WRKRPT001_10")||selectReport.equals("PMMRPT001_4")||selectReport.equals("WRKRPT001_17")||selectReport.equals("WRKRPT001_18")||selectReport.equals("WRKRPT001_19")){
		System.out.println("swap: "+swap);
		if(swap.equals("NONSWAP")){
			where=where+" and swap_nonswap='NONSWAP' and prog_id<>'PROG15'";
		}else if(swap.equals("SWAP-NON IDA")){
			where=where+" and swap_nonswap='SWAP-NON IDA' and prog_id<>'PROG15'";
		}else if(swap.equals("SWAP-IDA")){
			where=where+" and swap_nonswap='SWAP-IDA' and prog_id='PROG15'";
		}	
	}
	if(approvalStatus.equals("A") || approvalStatus.equals("U")){
		where=where+"and approval_status='"+approvalStatus+"' ";
		queryString=queryString+", Approval Status: "+approvalStatus;
	}
	System.out.println(fromPeriod);
	
	
	parameters.put("where", where);
	//parameters.put("queryString", queryString);
	
		System.out.println("selectReport: "+selectReport);
		System.out.println("Action: where : "+where);
		System.out.println("Action: innerWhere : "+innerWhere);
		System.out.println("Action: queryString: "+queryString);
		parameters.put("fincode", finYearId);
		parameters.put("month", monthId);
	}	
}
