<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
<script type="text/javascript">
	
		function de_find(){		
			
			document.changeOfficerLocationForm.action="changeOfficerLocationAction.do?method=find&d__mode="+d__mode+"&menuId=HRD012";
			document.changeOfficerLocationForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.changeOfficerLocationForm.action="changeOfficerLocationAction.do?method=update&d__mode="+d__mode+"&menuId=HRD012";
				document.changeOfficerLocationForm.submit();
			}
		}
		function de_add(){
			if(d__mode=='ent_add') {
				document.changeOfficerLocationForm.action="changeOfficerLocationAction.do?method=save&d__mode="+d__mode+"&menuId=HRD012";
				document.changeOfficerLocationForm.submit();
			}
		}
	
		
		    
	</script>
	<link href="css/form.css" rel="stylesheet" type="text/css">
	<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>
<html:html>
<body bgcolor="#6699FF" >
	<html:form action="changeOfficerLocationAction" method="post" styleId="changeOfficerLocationForm">
<logic:messagesPresent >
					<div id="errorDiv" class="error displaynone" style="width: 470px%; margin-bottom: 7px; min-height: 13px;">
 						<html:errors bundle="HR"/>   
					</div>
				</logic:messagesPresent >
				<logic:messagesPresent message="true">
					<div id="successDiv" class="success diplaynone" style="width: 470px;">
						<html:messages id="message" bundle="HR" message="true">
       						<li><bean:write name="message" /></li>
   						</html:messages>
					</div>
				</logic:messagesPresent >

	<br>
	<br>
	<fieldset><legend>Change Officer</legend>
	<center>
	<br>
		<label>Change Officer Location</label>
		<html:radio property="changeLocationOfficer" value="changeLocation" styleId="changeLocation" title="Change Officer Location" onclick="hide_ctrl('changeReprotingOfficerDiv',true);hide_ctrl('changeLocationDiv',false);"></html:radio>
		<label>Change Reporting Officer</label>
		<html:radio property="changeLocationOfficer" value="reportingOfficer" styleId="reportingOfficer" title="Change Reporting Officer" onclick="hide_ctrl('changeReprotingOfficerDiv',false);hide_ctrl('changeLocationDiv',true);"></html:radio>
	
	<div id = "changeLocationDiv">
	<table>
		<tr>
			<td><label>Location</label></td>
			<td><html:select property="location" styleId="location" styleClass="cs1" style="width:500px" onchange="ajaxFunction('changeOfficerLocationAction.do?location='+this.value+'&method=getEmployeeByLocation', 'employeeIds');">
				<html:option value="">Select Location</html:option>
				<html:options collection="locationIds" labelProperty="label" property="value"/>
			</html:select>
			</td>
		</tr>
		<tr>
			<td><label>Employee</label></td>
			<td><html:select property="employeeIds" styleId="employeeIds" styleClass="cs1" style="width:500px"> </html:select>
			</td>
		</tr>
		<tr>
			<td><label>Change Location</label></td>
			<td><html:select property="changeLocationId" styleId="changeLocationId" styleClass="cs1" style="width:500px">
			<html:option value="">Select Location</html:option>
				<html:options collection="locationIds" labelProperty="label" property="value"/>
			</html:select>
			</td>
		</tr>
	</table>
	</div>
	
	
	<div id="changeReprotingOfficerDiv">
	<table>
		<tr>
			<td><label>Location</label></td>
			<td><html:select property="locationId" styleId="locationId" styleClass="cs1" style="width:500px" onchange="ajaxFunction('changeOfficerLocationAction.do?locationId='+this.value+'&method=getEmployeeByDeployedLocation', 'oldReportingOfficerId');">
			<html:option value="">Select Location</html:option>
				<html:options collection="locationIds" labelProperty="label" property="value"/>
			</html:select></td>
		</tr>
		<tr >
			<td><label>Old Reporting Officer</label></td>
			<td><html:select property="oldReportingOfficerId" styleId="oldReportingOfficerId" styleClass="cs1" style="width:500px;">
			</html:select></td>
		</tr>
		<tr>
			<td><label>Reporting Officer Location</label></td>
			<td><html:select property="newLocationId" styleId="newLocationId" styleClass="cs1" style="width:500px" onchange="ajaxFunction('changeOfficerLocationAction.do?newLocationId='+this.value+'&method=getEmployeeByNewLocation', 'newReportingOfficerId');">
			<html:option value="">Select Location</html:option>
				<html:options collection="locationIds" labelProperty="label" property="value"/>
			</html:select>
			</td>
		
		</tr>
		<tr>
			<td><label>New Reporting Officer</label></td>
			<td><html:select property="newReportingOfficerId" styleId="newReportingOfficerId" styleClass="cs1" style="width:500px;">
			
			</html:select></td>
		</tr>
		<tr>
			<td><input type="hidden" name="employeeId" id="employeeId"></td>
			<td><input type="hidden" name="employeeName" id="employeeName"></td>
		</tr>
		</table>
		</div>
	</center>
	</fieldset>
	<div id="dispTag">
	<logic:present name="employeeCodeList" scope="request">
		<logic:notEmpty name="employeeCodeList" scope="request">
			<center><display:table name="employeeCodeList" id="employee" class="mars" style="margin:0 1em 1em 0;" pagesize="10" requestURI="/changeOfficerLocationAction.do" export="true">
			<display:column title="Select"><input type="checkbox" name="selectedIds" value="${employee.employeeId}" } checked="checked"></display:column>
				<display:column property="employeeId" title="Employee ID" sortable="true"/>
				<display:column property="employeeName" title="Employee Name" sortable="true"/>
				<display:column property="dateOfBirth" title="Date of Birth" sortable="true"/>
				<display:column property="reportingOfficerId" title="Reporting Officer Id" sortable="true"/>
		   <display:setProperty name="export.excel.filename" value="employeeCodeList"/>
           <display:setProperty name="export.xml" value="false" />
            <display:setProperty name="export.csv" value="false"/>
			</display:table></center>
			
		</logic:notEmpty>
	</logic:present></div>
	<script language="javascript">
				

			triggerEvent(document.getElementById('locationId'), 'onchange');
			<%if(MisUtility.ifEmpty(request.getAttribute("oldReportingOfficerId"))){%>
					document.getElementById("oldReportingOfficerId").value="<%=request.getAttribute("oldReportingOfficerId")%>";
			<%}%>
			triggerEvent(document.getElementById('newLocationId'), 'onchange');
			document.getElementById("newReportingOfficerId").value="<%=request.getAttribute("newReportingOfficerId")%>";
			
			triggerEvent(document.getElementById('location'), 'onchange');
			document.getElementById("employeeIds").value="<%=request.getAttribute("employeeIds")%>";
	</script>
	 

<script language="javascript">

if(document.getElementById('changeLocation').checked){
	 hide_ctrl('changeLocationDiv',false);
	hide_ctrl('changeReprotingOfficerDiv',true);
}else if(document.getElementById('reportingOfficer').checked){
	hide_ctrl('changeLocationDiv',true);
	hide_ctrl('changeReprotingOfficerDiv',false);
}
</script>
 

</html:form>

</html:html>
	