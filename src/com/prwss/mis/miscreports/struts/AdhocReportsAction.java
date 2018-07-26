package com.prwss.mis.miscreports.struts;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Map;
import java.util.Random;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSwapFile;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;

public class AdhocReportsAction extends DispatchAction {

	@SuppressWarnings("unused")
	private MISSessionBean misAuditBean;
	private MISJdcDaoImpl misJdcDaoImpl;
	String sql="";
	@SuppressWarnings("rawtypes")
	private Map parameters;
	private Logger log = Logger.getLogger(WorksProgrammeReportsAction.class);

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
		AdhocReportsForm adhocReportsForm=(AdhocReportsForm)form;
		request.setAttribute("misJdcDaoImpl",misJdcDaoImpl);
		request.setAttribute("form",adhocReportsForm);
		this.setAttrib(request);
		return mapping.findForward("display");
	}
	private String getType(String type){
		if(type.equalsIgnoreCase("String")) return String.class.getName();
		if(type.equalsIgnoreCase("Long")) return Long.class.getName();
		if(type.equalsIgnoreCase("BigDecimal")) return BigDecimal.class.getName();
		if(type.equalsIgnoreCase("Date")) return Date.class.getName();
		return null;
	}
	private String getSqlOperator(String op, String val){	
		String criteriaString="";
		if(op.equals("like")){
			criteriaString+=" like '%"+val+"%'";
		}else if(op.equals("notlike")){
			criteriaString+=" not like '%"+val+"%'";
		}else if(op.equals("blank")){
			criteriaString+=" is null";
		}else if(op.equals("notblank")){
			criteriaString+=" is not null";
		}else{
			criteriaString+=op+" '"+val+"'";
		}
		
		return criteriaString;
	}
	public DynamicReport buildReport(AdhocReportsForm adhocReportsForm,HttpServletRequest request) throws ColumnBuilderException, ClassNotFoundException { 
		String sql_select="";
		String sql_from=adhocReportsForm.getSelectReport();
		//String sql_where=adhocReportsForm.getCriteria();
		StringBuffer sql_where=new StringBuffer();
		if(MisUtility.ifEmpty(adhocReportsForm.getcField1())){
			sql_where.append(adhocReportsForm.getcField1()+" ");
			sql_where.append(this.getSqlOperator(adhocReportsForm.getOpt1(), adhocReportsForm.getcValue1())+" ");
		}
		if(MisUtility.ifEmpty(adhocReportsForm.getcField2())){
			sql_where.append(adhocReportsForm.getRad1()+" ");
			sql_where.append(adhocReportsForm.getcField2()+" ");	
			sql_where.append(this.getSqlOperator(adhocReportsForm.getOpt2(), adhocReportsForm.getcValue2())+" ");
		}
		if(MisUtility.ifEmpty(adhocReportsForm.getcField3())){
			sql_where.append(adhocReportsForm.getRad2()+" ");
			sql_where.append(adhocReportsForm.getcField3()+" ");
			sql_where.append(this.getSqlOperator(adhocReportsForm.getOpt3(), adhocReportsForm.getcValue3())+" ");
		}
		if(MisUtility.ifEmpty(adhocReportsForm.getcField4())){
			sql_where.append(adhocReportsForm.getRad3()+" ");
			sql_where.append(adhocReportsForm.getcField4()+" ");	
			sql_where.append(this.getSqlOperator(adhocReportsForm.getOpt4(), adhocReportsForm.getcValue4())+" ");
		}
		if(MisUtility.ifEmpty(adhocReportsForm.getCriteria())){
			sql_where.append(adhocReportsForm.getRad4()+" ");
			sql_where.append(adhocReportsForm.getCriteria()+" ");			
		}
		FastReportBuilder drb = new FastReportBuilder();
		String arr_select_fields[] = adhocReportsForm.getSelect_fields();
		for (int i = 0; i < arr_select_fields.length; i++) {
			String tempArray[]=arr_select_fields[i].split("@");
			drb.addColumn(tempArray[0],tempArray[1],getType(tempArray[2]),Integer.parseInt(tempArray[3]));
			sql_select+=tempArray[1]+",";				
		}
		sql_select=sql_select.substring(0, sql_select.length()-1);
		sql="select "+sql_select+" from prwss_main."+sql_from+" where "+(sql_where==null || sql_where.toString().equals("")?"1=1":sql_where.toString());
		System.out.println("sql : "+sql);
		
		
		
		Font fontTitle = new Font(11,"SansSerif","Helvetica",Font.PDF_ENCODING_CP1252_Western_European_ANSI, false);
		Style titleStyle = new StyleBuilder(false).setFont(fontTitle).build();
		titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		titleStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		
		
		Font fontSubTitle = new Font(8,"SansSerif","Helvetica",Font.PDF_ENCODING_CP1252_Western_European_ANSI, false);
		Style subtitleStyle = new StyleBuilder(false).setFont(fontSubTitle).build();
		subtitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		subtitleStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		
		Font fontColumnHeader= new Font(9,"SansSerif","Helvetica",Font.PDF_ENCODING_CP1252_Western_European_ANSI, false);
		Style columnHeaderStyle = new StyleBuilder(false).setFont(fontColumnHeader).build();
		columnHeaderStyle.setBackgroundColor(new Color(202, 204, 255));
		columnHeaderStyle.setTransparency(Transparency.OPAQUE);
		columnHeaderStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		columnHeaderStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		columnHeaderStyle.setBorder(Border.THIN);
				
		Font fontColumnDetail= new Font(8,"SansSerif","Helvetica",Font.PDF_ENCODING_CP1252_Western_European_ANSI, false);
		Style columnDetailStyle = new StyleBuilder(false).setFont(fontColumnDetail).build();
		columnDetailStyle.setBackgroundColor(new Color(255, 255, 204));
		columnDetailStyle.setTransparency(Transparency.OPAQUE);
		columnDetailStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		columnDetailStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		columnDetailStyle.setBorder(Border.THIN);
		
		drb.setPrintColumnNames(true) 
		.setIgnorePagination(true) 
		.setMargins(0, 0, 0, 0)  
		.setTitle(adhocReportsForm.getReportTitle())  
		.setSubtitle("This report was generated at " + new Date())  
		.setTitleStyle(titleStyle)
		.setDefaultStyles(titleStyle, subtitleStyle, columnHeaderStyle, columnDetailStyle)
		.setUseFullPageWidth(true); 
		
		drb.setReportName(adhocReportsForm.getReportTitle());
		drb.setQuery(sql, DJConstants.QUERY_LANGUAGE_SQL);
		DynamicReport dr = drb.build(); 
		return dr;
	}
	public void filePDF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdhocReportsForm adhocReportsForm=(AdhocReportsForm)form;
		/*JRSwapFileVirtualizer virtualizer = null; 
		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 90000, 15000), false);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);*/
		System.out.println("-1. Start Connection");
		Connection connection = null;
		connection = misJdcDaoImpl.getMISDataSource().getConnection();
		connection.setAutoCommit(false);
		System.out.println("0. Finish Connection " +connection);
		System.out.println("1. Start Building buildSchemeCommissioningReport()");
		DynamicReport dr = buildReport(adhocReportsForm,request);
		System.out.println("2. Finish Building buildSchemeCommissioningReport()");
		JasperReport jr = DynamicJasperHelper.generateJasperReport(dr,new ClassicLayoutManager(), parameters); 
		System.out.println("3. Start Filling report");
		JasperPrint jp =JasperFillManager.fillReport(jr,parameters, connection);
		System.out.println("4. Finish Filling report");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		System.out.println("5. Start Exporting");
		JRPdfExporter exporter = new JRPdfExporter();
		// Here we assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		 

		exporter.exportReport();
		//export(jp, baos);
		System.out.println("6. Finish Exporting");
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100000);
		String fileName = adhocReportsForm.getFileName()+randomInt+".pdf";
		response.setContentType("application/pdf"); 
		response.addHeader("Content-Disposition","attachment; filename="+fileName);
		//response.setContentLength(baos.size());
		System.out.println("7. Start Streaming");
		writeReportToResponseStream(response, baos);
		System.out.println("8. Finish Streaming");
	}
	public void export(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
		// Create a JRXlsExporter instance
		//JRXlsExporter exporter = new JRXlsExporter();
		JExcelApiExporter exporter = new JExcelApiExporter();


		// Here we assign the parameters jp and baos to the exporter
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		// Excel specific parameters
		// Check the Jasper (not DynamicJasper) docs for a description of these settings. Most are 
		// self-documenting
		exporter.setParameter(JExcelApiExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		exporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
		// Retrieve the exported report in XLS format
		exporter.exportReport();
	}
	private void writeReportToResponseStream(HttpServletResponse response,ByteArrayOutputStream baos) { 
		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			baos.writeTo(outputStream);
			// Flush the stream
			outputStream.flush(); 
			} catch (Exception e) {}
	}	
	public void fileExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String currentDate=MisUtility.now("dd-MM-yyyy"); 
		AdhocReportsForm adhocReportsForm=(AdhocReportsForm)form;
		Connection connection = null;
		response.setContentType("application/vnd.ms-excel"); 
		response.addHeader("Content-Disposition","attachment; filename="+adhocReportsForm.getFileName()+currentDate+".xls");
		try{
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			
			
			DynamicReport dr = buildReport(adhocReportsForm,request);
			System.out.println("2. Finish Building buildSchemeCommissioningReport()");
			JasperReport jasperReport = DynamicJasperHelper.generateJasperReport(dr,new ClassicLayoutManager(), parameters);
			
			//JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport,parameters, connection);
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			JRXlsExporter exporter = new JRXlsExporter();
	        //System.out.println(jasperPrint);
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);	        
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
	        exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.FALSE);
	        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
	        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
	        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
	        exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS,Boolean.TRUE);
	        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
	        exporter.exportReport();
	        ServletOutputStream servletOutputStream=response.getOutputStream();
			servletOutputStream.write(xlsReport.toByteArray());
			servletOutputStream.flush();
			servletOutputStream.close();		
		}catch (Exception e) { 
			e.printStackTrace();
			if(connection !=  null){
				connection.close();
			}
		}
	}
	public void fileExcel1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdhocReportsForm adhocReportsForm=(AdhocReportsForm)form;
		/*JRSwapFileVirtualizer virtualizer = null; 
		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 90000, 15000), false);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);*/
		System.out.println("-1. Start Connection");
		Connection connection = null;
		connection = misJdcDaoImpl.getMISDataSource().getConnection();
		connection.setAutoCommit(false);
		System.out.println("0. Finish Connection " +connection);
		System.out.println("1. Start Building buildSchemeCommissioningReport()");
		DynamicReport dr = buildReport(adhocReportsForm,request);
		System.out.println("2. Finish Building buildSchemeCommissioningReport()");
		JasperReport jr = DynamicJasperHelper.generateJasperReport(dr,new ClassicLayoutManager(), parameters); 
		System.out.println("3. Start Filling report");
		JasperPrint jp =JasperFillManager.fillReport(jr, parameters, connection);
		System.out.println("4. Finish Filling report");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		System.out.println("5. Start Exporting");
		export(jp, baos);
		System.out.println("6. Finish Exporting");
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100000);
		String fileName = adhocReportsForm.getFileName()+randomInt+".xls";
		//response.setHeader("Content-Disposition", "inline; filename="+ fileName);
		//response.setContentType("application/vnd.ms-excel");
		response.setContentType("application/vnd.ms-excel"); 
		response.addHeader("Content-Disposition","attachment; filename="+fileName);
		response.setContentLength(baos.size());
		System.out.println("7. Start Streaming");
		writeReportToResponseStream(response, baos);
		System.out.println("8. Finish Streaming");
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
			System.out.println("Ad-hoc Action");
			//EstimatesAwardContractsReportForm estimatesAwardContractsReportForm = (EstimatesAwardContractsReportForm)form;
			//refreshEstimatesAwardContractsReportForm(estimatesAwardContractsReportForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		System.out.println("Unspecified.......EstimatesAwardContractsReport");
		return mapping.findForward("cScreen");
	}	
	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "rpt");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
	}
}
