<%@page import="java.sql.Connection"%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.j2ee.servlets.*" %>
<%@page import="net.sf.jasperreports.engine.fill.JRFileVirtualizer" %>
<%@page import="net.sf.jasperreports.engine.type.WhenNoDataTypeEnum" %>
<%@page import="com.prwss.mis.common.MISJdcDaoImpl" %>
<%@page import="com.prwss.mis.finance.report.struts.FinanceReportsForm" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import=" java.sql.DriverManager" %>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout"  %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<head>
</head>
<html:html>
<body>
<%
	FinanceReportsForm financeReportsForm=(FinanceReportsForm)request.getAttribute("form");
	String jasperFile=financeReportsForm.getJasperFile();
	System.out.println("jsp jasperfile: "+jasperFile);
	File reportFile = new File(application.getRealPath(jasperFile));
	if (!reportFile.exists())
		throw new JRRuntimeException("File WebappReport.jasper not found. The report design must be compiled first.");
	JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
	jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
    MISJdcDaoImpl misJdcDaoImpl=(MISJdcDaoImpl)request.getAttribute("misJdcDaoImpl");
	String currentDate= com.prwss.mis.common.util.MisUtility.now("dd-MM-yyyy");	
	String selectZone=financeReportsForm.getSelectZone();
	String zoneId=financeReportsForm.getZoneId();
	String selectCircle=financeReportsForm.getSelectCircle();
	String circleId=financeReportsForm.getCircleId();
	String selectDistrict=financeReportsForm.getSelectDistrict();
	String districtId=financeReportsForm.getDistrictId();
	String selectProgram=financeReportsForm.getSelectProgram();
	String programId=financeReportsForm.getProgramId();
	String selectReport=financeReportsForm.getSelectReport();
	String queryString="";
	String where="1=1 ";
	String innerWhere=" and 1=1 ";
	String approvalStatus=financeReportsForm.getApprovalStatus();
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
		if(selectReport.equals("PMMRPT001_6")||selectReport.equals("PMMRPT001_7")||selectReport.equals("PMMRPT001_8")
				||selectReport.equals("PMMRPT001_9")||selectReport.equals("PMMRPT001_10")){
			innerWhere=innerWhere+"and prog_id='"+programId+"' ";
		}else{
			if(!selectReport.equals("PMMRPT001_18"))
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
	if(selectReport.equals("PMMRPT001_5")){
		String nc_pc_status=request.getParameter("nc_pc_status");
		if(MisUtility.ifEmpty(nc_pc_status)){
			if(nc_pc_status.equals("ALL")){
				queryString=queryString+", NC/PC Status: (NC|PC|FC)";
			}else{
				where=where+"and nc_pc_status='"+nc_pc_status+"' ";
				queryString=queryString+", NC/PC Status: "+nc_pc_status;
			}
		}			
	}
	System.out.println("jsp: "+where);	
	Map parameters = new HashMap();
	//parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
	parameters.put("where", where);
	parameters.put("innerWhere", innerWhere);
	parameters.put("queryString", queryString);
    Map imageMap = new HashMap();
	Connection connection=null;
	try{
		connection = misJdcDaoImpl.getMISDataSource().getConnection();
		connection.setAutoCommit(false);
	    //Class.forName("org.postgresql.Driver");
		//connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/prwss_db_ver1","postgres","3xchange!");
		JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport,parameters, connection);
		JRHtmlExporter exporter = new JRHtmlExporter();
	
		StringBuffer sbuffer = new StringBuffer();		
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imageMap);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "image?image=");
		exporter.exportReport();
		connection.close();
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		if(connection !=  null){
			connection.close();
		}
	}
%>

</body>
</html:html>
