package com.prwss.mis.tender.reports.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.prwss.mis.hr.reports.struts.HRReportsAction;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;

public class TenderReportsAction extends DispatchAction {
	private ProgramDao programDao;
	private LocationDao locationDao;
	@SuppressWarnings("unused")
	private MISSessionBean misAuditBean;
	private MISJdcDaoImpl misJdcDaoImpl;
	private MISJasperUtil misJasperUtil;
	@SuppressWarnings("rawtypes")
	private Map parameters;
	private Logger log = Logger.getLogger(HRReportsAction.class);

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
		TenderReportsForm tenderReportsForm = (TenderReportsForm)form;
		request.setAttribute("misJdcDaoImpl",misJdcDaoImpl);
		request.setAttribute("form",tenderReportsForm);
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	public void filePDF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TenderReportsForm tenderReportsForm = (TenderReportsForm)form;
		String jasperFile=tenderReportsForm.getJasperFile();		
		String fileTitle=tenderReportsForm.getFileTitle();
		setWhere(tenderReportsForm,request);
		/*JRFileVirtualizer virtualizer = new JRFileVirtualizer(1, "tmp");
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);*/
		misJasperUtil.exportToPDF(jasperFile,fileTitle, parameters, request, response);				
	}	
	public void fileExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TenderReportsForm tenderReportsForm = (TenderReportsForm)form;
		String jasperFile=tenderReportsForm.getJasperFile();
		String fileTitle=tenderReportsForm.getFileTitle();
		setWhere(tenderReportsForm,request);
		/*JRFileVirtualizer virtualizer = new JRFileVirtualizer(1, "tmp");
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);*/
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
	private void refreshTenderReportForm(TenderReportsForm tenderReportsForm){
		tenderReportsForm.setSelectZone(null);
		tenderReportsForm.setSelectCircle(null);
		tenderReportsForm.setSelectDistrict(null);
		tenderReportsForm.setSelectProgram(null);
		tenderReportsForm.setApprovalStatus(null);
		tenderReportsForm.setAsOnDate(null);		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setWhere(TenderReportsForm tenderReportsForm,HttpServletRequest request){
		String innerWhere=" and 1=1 ";
		String selectZone=tenderReportsForm.getSelectZone();
		String zoneId=tenderReportsForm.getZoneId();
		String selectCircle=tenderReportsForm.getSelectCircle();
		String circleId=tenderReportsForm.getCircleId();
		String selectDistrict=tenderReportsForm.getSelectDistrict();
		String districtId=tenderReportsForm.getDistrictId();
		String selectProgram=tenderReportsForm.getSelectProgram();
		String programId=tenderReportsForm.getProgramId();
		String approvalStatus=tenderReportsForm.getApprovalStatus();
		String selectReport=tenderReportsForm.getSelectReport();
		String fromPeriod=tenderReportsForm.getFromDate();
		String toPeriod=tenderReportsForm.getToDate();
		parameters = new HashMap();
		String queryString="";
		String where="1=1 ";
		if(selectZone.equals("S")){
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
		}
		if(selectProgram.equals("S")){
			if(selectReport.equals("PMMRPT001_6")||selectReport.equals("PMMRPT001_7")||selectReport.equals("PMMRPT001_8")||selectReport.equals("PMMRPT001_9")
				||selectReport.equals("PMMRPT001_10")		
			){
				innerWhere=innerWhere+"and prog_id='"+programId+"' ";
			}else{
				if(!selectReport.equals("PMMRPT001_18")){
					where=where+"and prog_id='"+programId+"' ";
				}
			}
			
			queryString=queryString+", Program: "+programId;
		}else{
			queryString=queryString+", Program: All";
			if(selectReport.equals("PMMRPT001_18")||selectReport.equals("PMMRPT001_19")){
				innerWhere="A";
			}
		}
		if(approvalStatus.equals("A") || approvalStatus.equals("U")){
			where=where+"and approval_status='"+approvalStatus+"' ";
			queryString=queryString+", Approval Status: "+approvalStatus;
		}
		if(selectReport.equals("PMMRPT001_5")){
			String nc_pc_status=request.getParameter("nc_pc_status");
			System.out.println("nc_pc_status: "+nc_pc_status);
			if(MisUtility.ifEmpty(nc_pc_status)){
				if(nc_pc_status.equals("ALL")){
					queryString=queryString+", NC/PC Status: (NC|PC|FC)";
				}else{
					where=where+"and nc_pc_status='"+nc_pc_status+"' ";
					queryString=queryString+", NC/PC Status: "+nc_pc_status;
				}
			}			
		}
		parameters.put("where", where);
		parameters.put("queryString", queryString);
		parameters.put("innerWhere", innerWhere);
		System.out.println("selectReport: "+selectReport);
		System.out.println("Action: where : "+where);
		System.out.println("Action: innerWhere : "+innerWhere);
		System.out.println("Action: queryString: "+queryString);
	}	
}
