<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>
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
			document.employeeAppraisalForm.action="employeeAppraisalAction.do?method=find&d__mode="+d__mode+"&menuId=HRD005";
			document.employeeAppraisalForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.employeeAppraisalForm.action="employeeAppraisalAction.do?method=update&d__mode="+d__mode+"&menuId=HRD005";
				document.employeeAppraisalForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.employeeAppraisalForm.action="employeeAppraisalAction.do?method=delete&d__mode="+d__mode+"&menuId=HRD005";
				document.employeeAppraisalForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.employeeAppraisalForm.action="employeeAppraisalAction.do?method=post&d__mode="+d__mode+"&menuId=HRD005";
				document.employeeAppraisalForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.employeeAppraisalForm.action="employeeAppraisalAction.do?method=save&d__mode="+d__mode+"&menuId=HRD005";
				document.employeeAppraisalForm.submit();
			}
		}
		    
	</script>
	<link href="css/form.css" rel="stylesheet" type="text/css">
	<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">
	
	<html:form action="employeeAppraisalAction" method="post">
	<logic:messagesPresent >
					<div id="errorDiv" class="error displaynone" style="width: 470px%; margin-bottom: 7px; height: 13px;">
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
	<fieldset><legend>Employee Target Plan</legend>
	<center>
	<br>
	<table>
		<tr>
			<td><label>Location</label></td>
			<td><html:select property="locationId" styleClass="cs1" styleId="locationId">
				<html:option value="">Select Location</html:option>
				<html:options collection="userLocations" labelProperty="label"
					property="value"></html:options>
			</html:select></td>
			<td><label>Reporting Officer</label></td>
			<td><html:text property="reportingOfficerName" styleClass="ci5" disabled="true" styleId="reportingOfficerNameId" onkeypress="return charKey(event);"></html:text></td>
		</tr>
		<tr>
			<td><label>Plan From</label></td>
			<td><html:text property="planFromDate" styleId="planFromDateId" styleClass="ci5" disabled="true"></html:text>
			</td>
			
			<td><label>Plan To</label></td>
			<td><html:text property="planToDate" styleId="planToDateId" styleClass="ci5" disabled="true"></html:text>
			</td>
		</tr>
		<tr>
			<td><label>Employee Name</label></td>
			<td><html:text property="employeeName" styleClass="ci5" disabled="true" onkeypress="return charKey(event);"></html:text></td>
			<td><label>Target Plan</label></td>
			<td><html:select property="targetPlanId" styleClass="cs1" styleId="targetPlanId">
			<html:option value="">Select</html:option>
			<html:options collection="employeeTargetPlan" labelProperty="label" property="value"/> 
			</html:select></td>
		</tr>
		</table>
	</center>
	</fieldset>
	<fieldset><legend>Employee Targets</legend>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font><b>*Please Provide Target Completion Date In Specified Format --> DD-MM-YYYY<b></font>
	<center>
	<table>
		<tr>
			<td>
			<div class="divgrid"><layout:datagrid styleClass="DATAGRID" property="employeeTargetDatagrid"	selectionAllowed="true" multipleSelectionAllowed="false" model="datagrid">
			<layout:datagridColumn property="id" title="Id" mode="N,N,N"></layout:datagridColumn>
			<layout:datagridColumn property="targetName" title="Target(As Set By Reporting Officer)" mode="I,I,I"></layout:datagridColumn>
			<layout:datagridColumn property="targetCompletionDate" title="Target Completion Date" mode="I,I,I"></layout:datagridColumn>
			<layout:datagridColumn property="plannerRemarks" title="Reporting Officer Remarks" mode="I,I,I"></layout:datagridColumn>
			<layout:datagridColumn property="targetActualCompletionDate" title="Target Actual Completion Date"></layout:datagridColumn>
			<layout:datagridColumn property="employeeRemarks" title="Your Remarks"></layout:datagridColumn>
			<layout:datagridSelect property="targetStatus" title="Status of Completion" >
			<layout:option value="PENDING" key="Pending"></layout:option>
			<layout:option value="ON-GOING" key="On-Going"></layout:option>
			<layout:option value="COMPLETED" key="Completed"></layout:option>
			</layout:datagridSelect>
			</layout:datagrid></div>
		</tr>
	</table>
	</center>
	</fieldset>
	<script language="javascript">
    <%if(MisUtility.ifEmpty(session.getAttribute("reportingOfficerName"))){%>
	document.getElementById("reportingOfficerNameId").value="<%=session.getAttribute("reportingOfficerName")%>";
	<%}%>
	</script>
		
</html:form>
</html:html>