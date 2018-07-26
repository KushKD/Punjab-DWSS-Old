/**
 * 
 */
package com.prwss.mis.miscreports.struts;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;
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
import com.prwss.mis.masters.constituency.dao.ConstituencyBean;
import com.prwss.mis.masters.constituency.dao.ConstituencyDao;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;

public class DashboardReportsAction extends DispatchAction {

	private ProgramDao programDao;
	private LocationDao locationDao;
	@SuppressWarnings("unused")
	private MISSessionBean misAuditBean;
	private MISJdcDaoImpl misJdcDaoImpl;
	private MISJasperUtil misJasperUtil;
	private ConstituencyDao constituencyDao;
	@SuppressWarnings("rawtypes")
	private Map parameters;
	private Logger log = Logger.getLogger(DashboardReportsAction.class);

	
	public void setConstituencyDao(ConstituencyDao constituencyDao) {
		this.constituencyDao = constituencyDao;
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
		DashboardReportsForm dashboardReportsForm=(DashboardReportsForm)form;
		request.setAttribute("misJdcDaoImpl",misJdcDaoImpl);
		request.setAttribute("form",dashboardReportsForm);
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	public void filePDF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DashboardReportsForm dashboardReportsForm=(DashboardReportsForm)form;
		String jasperFile=dashboardReportsForm.getFileName();		
		String fileTitle=dashboardReportsForm.getReportTitle();
		misJasperUtil.exportToPDF(jasperFile,fileTitle, parameters, request, response);
		//exportToHTML(jasperFile, fileTitle, parameters, request, response);	
	}	
	public void exportToHTML(String jasperFile,String fileTitle, Map parameters,HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		String currentDate=MisUtility.now("dd-MM-yyyy"); 
		Connection connection = null;
		response.setContentType("text/html"); 
		//response.addHeader("Content-Disposition","attachment; filename="+fileTitle+currentDate+".html");
		try{
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			File reportFile = new File(request.getSession().getServletContext().getRealPath(jasperFile));
			if (!reportFile.exists())
				throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
			JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport,parameters, connection);
			Map imageMap = new HashMap();
			//ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//ServletOutputStream servletOutputStream=response.getOutputStream();
			PrintWriter out = response.getWriter();
			JRHtmlExporter exporter = new JRHtmlExporter();
	        //System.out.println(jasperPrint);
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imageMap);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "../report/image?image=");


			exporter.exportReport();
	        
			//servletOutputStream.write(baos.toByteArray());
			//out.flush();
			//out.close();	
			
		
			
		}catch (Exception e) { 
			e.printStackTrace();
			if(connection !=  null){
				connection.close();
			}
		}
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
			//DashboardReportsForm dashboardReportsForm = (DashboardReportsForm)form;
			//refreshDashboardReportsForm(dashboardReportsForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		//System.out.println("Unspecified.......EstimatesAwardContractsReport");
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
	
	public ActionForward fetchDivisions(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuilder buffer = new StringBuilder();
		List<String> locationTypeList = new ArrayList<String>();
		locationTypeList.add("SPMC");
		locationTypeList.add("DO");
		locationTypeList.add("DPMC");
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("districtId"))){
				locationBeans = locationDao.getChildLocationIds(request.getParameter("districtId"), locationTypeList);
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"").append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName()+" - ("+locationBean.getLocationId()+")");
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
	
	public ActionForward fetchBlock(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuilder buffer = new StringBuilder();
		List<String> locationTypeList = new ArrayList<String>();
		locationTypeList.add("BLOCK");
//		locationTypeList.add("DO");
//		locationTypeList.add("DPMC");
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("districtId"))){
				locationBeans = locationDao.getChildLocationIds(request.getParameter("districtId"), locationTypeList);
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"").append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName()+" - ("+locationBean.getLocationId()+")");
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
	
	public ActionForward fetchConstituency(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		Set<ConstituencyBean> constituencyBeans = null;
		StringBuilder buffer = new StringBuilder();
//		locationTypeList.add("DO");
//		locationTypeList.add("DPMC");
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
//		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if(MisUtility.ifEmpty(request.getParameter("districtId"))){
				constituencyBeans = constituencyDao.findConstituency(request.getParameter("districtId"));
				for (ConstituencyBean bean : constituencyBeans) {
					buffer.append("<option value=\"").append(bean.getConstituencyId()).append("\">");
					buffer.append(bean.getConstituencyName()+" - ("+bean.getConstituencyId()+")");
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
}