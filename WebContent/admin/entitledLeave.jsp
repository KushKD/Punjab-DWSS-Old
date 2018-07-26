<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
<script type="text/javascript">
		function de_find(){		
			document.entitledLeaveForm.action="entitledLeaveAction.do?method=find&d__mode="+d__mode+"&menuId=ADM004";
			document.entitledLeaveForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.entitledLeaveForm.action="entitledLeaveAction.do?method=update&d__mode="+d__mode+"&menuId=ADM004";
				document.entitledLeaveForm.submit();
			}
		}
		function de_add(){
			if(d__mode=='ent_add') {
				document.entitledLeaveForm.action="entitledLeaveAction.do?method=save&d__mode="+d__mode+"&menuId=ADM004";
				document.entitledLeaveForm.submit();
			}
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>
<html:html>
<body bgcolor="#6699FF">
<html:form action="entitledLeaveAction" method="post"
	styleId="entitledLeaveForm">
	<logic:messagesPresent>
		<div id="errorDiv" class="error displaynone"
			style="width: 470px %; margin-bottom: 7px; height: 13px;"><html:errors
			bundle="admin" /></div>
	</logic:messagesPresent>
	<logic:messagesPresent message="true">
		<div id="successDiv" class="success diplaynone" style="width: 470px;">
		<html:messages id="message" bundle="admin" message="true">
			<li><bean:write name="message" /></li>
		</html:messages></div>
	</logic:messagesPresent>

	<br>
	<br>
	<fieldset><legend>Leave Entitled</legend>
	<center><br>
	<table>
		<tr>
			<td><label>Location</label></td>
			<td><html:select property="locationId" styleId="locationId"
				styleClass="cs1" style="width:250px"
				onchange="ajaxFunction('entitledLeaveAction.do?locationId='+this.value+'&method=fetchEmployee', 'employeeId');triggerEvent(document.getElementById('employeeId'), 'onchange');">
				<html:option value="">Select Location</html:option>
				<html:options collection="userLocations" labelProperty="label"
					property="value"></html:options>
			</html:select></td>
			<td><label>Employee Name</label></td>
			<td><html:select property="employeeId" styleId="employeeId"
				styleClass="cs1" style="width:250px;">
			</html:select></td>
		</tr>
		<tr>
			<td nowrap>Year</td>
			<td><html:select property="yearFor" style="width:250px;">
				<option value="0">Select</option>
				<option value="1">2007-08</option>
				<option value="2">2008-09</option>
				<option value="3">2009-10</option>
				<option value="4">2010-11</option>
				<option value="5">2011-12</option>
				<option value="6">2012-13</option>
				<option value="7">2013-14</option>
				<option value="8">2014-15</option>
				<option value="9">2015-16</option>
				<option value="10">2016-17</option>

			</html:select></td>
			<td><label>Leave Entitled</label></td>
			<td><html:text property="totalEligibileLeave"
				styleId="totalEligibileLeave" styleClass="ci5" style="width:250px;"></html:text>
			</td>

		</tr>
	</table>
	</center>
	</fieldset>
	<script language="javascript">
triggerEvent(document.getElementById('locationId'), 'onchange');
document.getElementById("employeeId").value="<%=request.getAttribute("employeeId")%>";
document.getElementById("yearFor").value="<%=request.getAttribute("yearFor")%>"; 
</script>
</html:form>
</html:html>
