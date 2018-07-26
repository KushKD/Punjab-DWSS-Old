<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
<script type="text/javascript">

	function de_view() {
		document.datTaskReportForm.action = "datTaskReportAction.do?method=view&d__mode="+ d__mode+"&menuId=DTMRPT001";
		document.datTaskReportForm.submit();
	}
	function de_filePrint() {
			document.datTaskReportForm.action = "datTaskReportAction.do?method=filePrint&d__mode="+ d__mode+"&menuId=DTMRPT001";
			document.datTaskReportForm.submit();		
	}
	function de_filePDF() {
		document.datTaskReportForm.action = "datTaskReportAction.do?method=filePDF&d__mode="+ d__mode+"&menuId=DTMRPT001";
		document.datTaskReportForm.submit();		
	}
	function de_fileExcel() {
		document.datTaskReportForm.action = "datTaskReportAction.do?method=fileExcel&d__mode="+ d__mode+"&menuId=DTMRPT001";
		document.datTaskReportForm.submit();		
	}
</script>
<link href="css/layout.css" rel="stylesheet" type="text/css">

</head>
<body>

		<html:form action="datTaskReportAction" styleId="datTaskReport">
		<fieldset>
		<legend>Reports Filter</legend>
		<center>
		<table>		
		<tr>
		<td>Location</td>
			<td colspan="4">
				<html:select property="locationId" styleClass="cs2" styleId="locationId">
					<html:option value="">Select Location</html:option>
					<html:options collection="userLocations" labelProperty="label" property="value"></html:options>
				</html:select>
			</td>
		</tr>
		<tr>
				<td>Period</td>
				<td>	
					<html:radio property="selectPeriod" title="Selection" value="S" styleId="selectPeriod">Selection</html:radio>
					<html:radio property="selectPeriod" title="All" value="A" styleId="selectPeriod">All</html:radio>
				</td>		
				<td>From:&nbsp;&nbsp;&nbsp;
					<html:text property="fromDate" styleId="fromDate"	styleClass="ci3"></html:text> 
					<!--<input class=ci4 type=button onclick="c1.innerpopup('fromDate','calendar_frame');" value="..." />-->
				</td>
				<td>To:&nbsp;&nbsp;&nbsp;
					<html:text property="toDate" styleId="toDate"	styleClass="ci3"></html:text> 
					<!--<input class=ci4 type=button onclick="c1.innerpopup('toDate','calendar_frame');" value="..." />-->
				</td>  					
			</tr>
		</table>
		</center>
		</fieldset>
			
		<fieldset>
		<legend> Reports</legend>
		<center>
		<table>
			<tr><td colspan="3"><html:radio property="selectReport" value="DTMRPT001_1">Inward Dak Report</html:radio>  </td></tr>
			<tr><td colspan="3"><html:radio property="selectReport" value="DTMRPT001_2">Outward Dak Report</html:radio>  </td></tr>
			</table>
			</center>
			</fieldset>
		</html:form>
	

</body>
</html>