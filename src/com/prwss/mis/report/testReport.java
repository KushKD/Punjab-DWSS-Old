package com.prwss.mis.report;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;


import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JREmptyDataSource;


import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperExportManager;

import net.sf.jasperreports.engine.JasperFillManager;

import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.JasperReport;




public class testReport  {
	public static void main(String args[]) {		 
		Connection connection;
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try{
			Class.forName("org.postgresql.Driver");
		 	connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/prwss_db_ver1","postgres","3xchange!");
		 	jasperReport = JasperCompileManager.compileReport("C:\\vikash\\test\\report2.jrxml");
		 	System.out.println("Testing");
      		jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), connection);
      		JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\vikash\\test\\Simple_Report.pdf");
      		JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\vikash\\test\\Simple_Report.html");
      		JasperExportManager.exportReportToXmlFile(jasperPrint, "C:\\vikash\\test\\Simple_Report.xml",true);
		}catch(Exception jrException){
			jrException.printStackTrace();
		}
	}
}

