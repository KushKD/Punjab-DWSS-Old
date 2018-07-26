package com.prwss.mis.daktask.reports.struts;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISJasperUtil;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.report.struts.FinanceReportsAction;
import com.prwss.mis.masters.location.dao.LocationDao;

public class DakTaskReportAction extends DispatchAction {
	
	private LocationDao locationDao;
	@SuppressWarnings("unused")
	private MISSessionBean misAuditBean;
	private MISJdcDaoImpl misJdcDaoImpl;
	private MISJasperUtil misJasperUtil;
	@SuppressWarnings("rawtypes")
	private Map parameters;
	private Logger log = Logger.getLogger(FinanceReportsAction.class);

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
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
		DakTaskReportForm dakTaskReportForm=(DakTaskReportForm)form;
		request.setAttribute("misJdcDaoImpl",misJdcDaoImpl);
		request.setAttribute("form",dakTaskReportForm);
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	public void filePDF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DakTaskReportForm dakTaskReportForm=(DakTaskReportForm)form;
		String jasperFile=dakTaskReportForm.getJasperFile();		
		String fileTitle=dakTaskReportForm.getFileTitle();
		setWhere(dakTaskReportForm,request);
		/*JRFileVirtualizer virtualizer = new JRFileVirtualizer(1, "tmp");
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);*/
		misJasperUtil.exportToPDF(jasperFile,fileTitle, parameters, request, response);				
	}	
	public void fileExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DakTaskReportForm dakTaskReportForm=(DakTaskReportForm)form;
		String jasperFile=dakTaskReportForm.getJasperFile();
		String fileTitle=dakTaskReportForm.getFileTitle();
		setWhere(dakTaskReportForm,request);
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
//			Set<LabelValueBean> locations = getLocations();
//			request.getSession().setAttribute("locations", locations);
		    
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return mapping.findForward("cScreen");
	}
//	private Set<LabelValueBean> getLocations(){
//		Set<LabelValueBean> locations = null;
//		Set<LocationBean> locationBeans = null;
//		try{
//			List<String> locationType = new ArrayList<String>();
//			locationType.add("SPMC");
//			locationType.add("DO");
//			locationType.add("DPMC");
//			locationBeans = locationDao.getLocationIdOnTypeList(locationType);
//			locations = new HashSet<LabelValueBean>();
//			for (LocationBean locationBean2 : locationBeans) {
//				locations.add(new LabelValueBean(locationBean2.getLocationId()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR+locationBean2.getLocationName(),locationBean2.getLocationId()));
//			}			
//		}catch(DataAccessException e){
//			log.error(e);
//			e.printStackTrace();
//		}
//		catch(Exception e){
//			log.error(e);
//			e.printStackTrace();
//		}		
//		return locations;	
//	}
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "rpt");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId");
	}
	@SuppressWarnings("unused")
	private void refreshFinanceReportsForm(DakTaskReportForm dakTaskReportForm){
		
		dakTaskReportForm.setApprovalStatus(null);
		dakTaskReportForm.setAsOnDate(null);		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setWhere(DakTaskReportForm dakTaskReportForm,HttpServletRequest request){
		String locationId=dakTaskReportForm.getLocationId();
		String selectPeriod=dakTaskReportForm.getSelectPeriod();
		String fromPeriod=dakTaskReportForm.getFromDate();
		String toPeriod=dakTaskReportForm.getToDate();
		String selectReport=dakTaskReportForm.getSelectReport();
		parameters = new HashMap();
		String queryString="";
		String where="1=1 ";
		
		
		where=where+" and location_id='"+locationId+"' ";
		queryString=queryString+" Location: "+locationId;
		
		if(selectPeriod.equals("S")){
			if(selectReport.equals("DTMRPT001_1")){
				where=where+" and  receipt_date between '"+MisUtility.convertStringToDate(fromPeriod)+"' and '"+MisUtility.convertStringToDate(toPeriod)+"' ";
			}
			if(selectReport.equals("DTMRPT001_2")){
				where=where+" and  dispatch_date between '"+MisUtility.convertStringToDate(fromPeriod)+"' and '"+MisUtility.convertStringToDate(toPeriod)+"' ";
			}
			queryString=queryString+", Period: "+fromPeriod + " to "+toPeriod;
		}
		parameters.put("where", where);
		parameters.put("queryString", queryString);
	}
}
