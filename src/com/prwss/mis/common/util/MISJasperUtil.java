package com.prwss.mis.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prwss.mis.common.MISJdcDaoImpl;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;

public class MISJasperUtil {
	private MISJdcDaoImpl misJdcDaoImpl;
	
	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}
	public void exportToExcel(String jasperFile,String fileTitle, Map parameters,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String currentDate=MisUtility.now("dd-MM-yyyy"); 
		Connection connection = null;
		response.setContentType("application/vnd.ms-excel"); 
		response.addHeader("Content-Disposition","attachment; filename="+fileTitle+currentDate+".xls");
		try{
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			File reportFile = new File(request.getSession().getServletContext().getRealPath(jasperFile));
			if (!reportFile.exists())
				throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
			JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
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

	public void exportToPDF(String jasperFile,String fileTitle, Map parameters,HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		String currentDate=MisUtility.now("dd-MM-yyyy"); 
		Connection connection = null;
		InputStream reportStream = request.getSession().getServletContext().getResourceAsStream(jasperFile);
		try { 
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			ServletOutputStream servletOutputStream=response.getOutputStream();
			response.setContentType("application/pdf"); 
			response.addHeader("Content-Disposition","attachment; filename="+fileTitle+currentDate+".pdf");
			JasperRunManager.runReportToPdfStream( reportStream,servletOutputStream, parameters, connection );
			servletOutputStream.flush();
			servletOutputStream.close();
		}catch (Exception e) { 
			e.printStackTrace();
			if(connection !=  null){
				connection.close();
			}
		}
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
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "reports/image?image=");


			exporter.exportReport();
	        
			//servletOutputStream.write(baos.toByteArray());
			out.flush();
			out.close();	
			
		
			
		}catch (Exception e) { 
			e.printStackTrace();
			if(connection !=  null){
				connection.close();
			}
		}
}
	public void fileWord(String jasperFile, Map parameters,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String currentDate=MisUtility.now("dd-MM-yyyy"); 
		Connection connection = null;
		try{
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			File reportFile = new File(request.getSession().getServletContext().getRealPath(jasperFile));
			if (!reportFile.exists())
				throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
			JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
			jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport,parameters, connection);
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			JRRtfExporter exporter=new JRRtfExporter(); 
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
	        exporter.exportReport();
			ServletOutputStream servletOutputStream=response.getOutputStream();
			response.setContentType("application/vnd.ms-word"); 
			response.addHeader("Content-Disposition","attachment; filename=committedLiability"+currentDate+".doc");
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
}
