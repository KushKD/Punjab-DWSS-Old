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
			document.employeeEvaluationForm.action="employeeEvaluationAction.do?method=find&d__mode="+d__mode+"&menuId=HRD003";
			document.employeeEvaluationForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.employeeEvaluationForm.action="employeeEvaluationAction.do?method=update&d__mode="+d__mode+"&menuId=HRD003";
				document.employeeEvaluationForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.employeeEvaluationForm.action="employeeEvaluationAction.do?method=delete&d__mode="+d__mode+"&menuId=HRD003";
				document.employeeEvaluationForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.employeeEvaluationForm.action="employeeEvaluationAction.do?method=post&d__mode="+d__mode+"&menuId=HRD003";
				document.employeeEvaluationForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.employeeEvaluationForm.action="employeeEvaluationAction.do?method=save&d__mode="+d__mode+"&menuId=HRD003";
				document.employeeEvaluationForm.submit();
			}
		}
		    
	</script>
	<link href="css/form.css" rel="stylesheet" type="text/css">
	<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">

	<html:form action="employeeEvaluationAction" method="post">
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
			<td><html:text property="reportingOfficerName" disabled="true"></html:text></td>
		</tr>
		<tr>
			<td><label>Plan From</label></td>
			<td><html:text property="planFromDate" styleId="planFromDateId" styleClass="ci5"></html:text>
			</td>
			
			<td><label>Plan To</label></td>
			<td><html:text property="planToDate" styleId="planToDateId" styleClass="ci5"></html:text>
			</td>
		</tr>
		<tr>
			<td><label>Employee Name</label></td>
			<td><html:select property="employeeId" styleId="employeeId" styleClass="cs1" onchange="ajaxFunction('employeeEvaluationAction.do?employeeId='+this.value+'&method=fetchTargetPlanId', 'targetPlanId');">
				<html:option value="">Select</html:option>
				<html:options collection="employeeIds" labelProperty="label" property="value"/>
			</html:select></td>
			<td><label>Target Plan</label></td>
			<td><html:select property="targetPlanId" styleClass="cs1" styleId="targetPlanId"></html:select></td>
		</tr>
		</table>
	</center>
	</fieldset>
	<fieldset><legend>Targets Evaluation</legend>
	<center>
	<table>
		<tr>
			<td>
			<div class="divgrid"><layout:datagrid
				styleClass="DATAGRID" property="employeeTargetDatagrid"
				selectionAllowed="true" multipleSelectionAllowed="false"
				model="datagrid">
			<layout:datagridColumn property="id" title="Id" mode="N,N,N"></layout:datagridColumn>
			<layout:datagridColumn property="targetName" title="Target(As Set By Reporting Officer)" mode="I,I,I"></layout:datagridColumn>
			<layout:datagridColumn property="targetCompletionDate" title="Target Completion Date" mode="I,I,I"></layout:datagridColumn>
			<layout:datagridColumn property="plannerRemarks" title="Target Remarks(By Reporting Officer)" mode="I,I,I"></layout:datagridColumn>
			<layout:datagridColumn property="targetActualCompletionDate" title="Actual Completion Date" mode="I,I,I"></layout:datagridColumn>
			<layout:datagridColumn property="employeeRemarks" title="Employee Remarks" mode="I,I,I"></layout:datagridColumn>
			<layout:datagridColumn property="targetStatus" title="Status of Completion" mode="I,I,I"></layout:datagridColumn>
			<layout:datagridColumn property="evaluatorRemarks" title="Evaluation Remarks"></layout:datagridColumn>
			</layout:datagrid></div>
		</tr>
	</table>
	</center>
	<center>
	<table>
	<tr>
	<td><label>Overall Performance Rating</label></td>
	<td><html:select property="performanceRating" styleClass="cs1" styleId="performanceRatingId">
	<html:option value="">Select</html:option> 
	<html:option value="BELOW-AVERAGE">Below-Average</html:option>
	<html:option value="GOOD">Good</html:option>
	<html:option value="VERY-GOOD">Very-Good</html:option>
	<html:option value="OUTSTANDING">Outstanding</html:option>
	</html:select></td>
	
	<td><label>Appraisal</label></td>
	<td><html:select property="appraisal" styleClass="cs1" styleId="appraisalId">
	<html:option value="">Select</html:option>
	<html:option value="<%=MISConstants.HR_APRAISAL_NOT_RECOMMENDED%>">Not-Recommended</html:option>
	<html:option value="<%=MISConstants.HR_APRAISAL_ON_HOLD%>">On-Hold</html:option>
	<html:option value="<%=MISConstants.HR_APRAISAL_RECOMMENDED%>">Recommended</html:option>
	</html:select></td>
	</tr>
	</table>
	</center>
	</fieldset>
	<script language="javascript">
	triggerEvent(document.getElementById('employeeId'), 'onchange');
	 <%if(MisUtility.ifEmpty(request.getAttribute("targetPlanId"))){%>
		document.getElementById("targetPlanId").value="<%=request.getAttribute("targetPlanId")%>";
		<%}%>
	</script>
		
</html:form>
</html:html>