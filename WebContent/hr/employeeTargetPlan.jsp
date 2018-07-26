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
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
--><script type="text/javascript">
		function de_find(){		
			document.employeeTargetPlanForm.action="employeeTargetPlanAction.do?method=find&d__mode="+d__mode+"&menuId=HRD003";
			document.employeeTargetPlanForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.employeeTargetPlanForm.action="employeeTargetPlanAction.do?method=update&d__mode="+d__mode+"&menuId=HRD003";
				document.employeeTargetPlanForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.employeeTargetPlanForm.action="employeeTargetPlanAction.do?method=delete&d__mode="+d__mode+"&menuId=HRD003";
				document.employeeTargetPlanForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.employeeTargetPlanForm.action="employeeTargetPlanAction.do?method=post&d__mode="+d__mode+"&menuId=HRD003";
				document.employeeTargetPlanForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.employeeTargetPlanForm.action="employeeTargetPlanAction.do?method=save&d__mode="+d__mode+"&menuId=HRD003";
				document.employeeTargetPlanForm.submit();
			}
		}
		    
	</script>
	<link href="css/form.css" rel="stylesheet" type="text/css">
	<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">

	<html:form action="employeeTargetPlanAction" method="post">
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
			<td><html:text property="reportingOfficerName" disabled="true" onkeypress="return charKey(event);"></html:text></td>
		</tr>
		<tr>
			<td><label>Plan From</label></td>
			<td><html:text property="planFromDate" styleId="planFromDateId" styleClass="ci5"></html:text>
			<!--<input class=ci4 type=button onclick="c1.innerpopup('planFromDateId','calendar_frame');" value="..."/>-->
			</td>
			
			<td><label>Plan To</label></td>
			<td><html:text property="planToDate" styleId="planToDateId" styleClass="ci5"></html:text>
				<!--<input class=ci4 type=button onclick="c1.innerpopup('planToDateId','calendar_frame');" value="..."/>-->
			</td>
		</tr>
		<tr>
			<td><label>Employee Name</label></td>
			<td><html:select property="employeeId" styleId="employeeId" styleClass="cs1" onchange="ajaxFunction('employeeTargetPlanAction.do?employeeId='+this.value+'&method=fetchTargetPlanId', 'targetPlanId');">
				<html:option value="">Select</html:option>
				<html:options collection="employeeIds" labelProperty="label" property="value"/>
			</html:select></td>
			<td><label>Target Plan (System Generated)</label></td>
			<td><html:select property="targetPlanId" styleClass="cs1" styleId="targetPlanId"></html:select></td>
		</tr>
		</table>
	</center>
	</fieldset>
	<fieldset><legend>Employee Targets</legend>
	<center>
	<table>
		<tr>
		<td><label>Target</label></td>
		
		
		
		
		 <td>
		 <html:select property="targetId" styleId="targetId"
				styleClass="cs2"
				>
				<html:option
					value=""></html:option>
				<html:option
					value="<%=MISConstants.TARGET_TYPE_EXISTING1 %>">EXISTING1</html:option>
				<html:option
					value="<%=MISConstants.TARGET_TYPE_EXISTING2 %>">EXISTING2</html:option>
				<html:option value="<%=MISConstants.TARGET_TYPE_EXISTING3  %>">EXISTING3</html:option>
				<html:option
					value="<%=MISConstants.TARGET_TYPE_EXISTING4  %>">EXISTING4</html:option>
				<html:option
					value="<%=MISConstants.TARGET_TYPE_EXISTING5  %>">EXISTING5</html:option>
				<html:option
					value="<%=MISConstants.TARGET_TYPE_EXISTING6  %>">EXISTING6</html:option>
				
				
				
			</html:select>
		 
		 </td>
		 
		  
		<td><label>Target Completion Date</label></td>
		<td><input type="text" id="targetCompletionDateId" class="ci5" readonly="true"/>
		<!--<input class=ci4 type=button onclick="c1.innerpopup('targetCompletionDateId','calendar_frame');" value="..."/>-->
		</td>
		<td><label>Remarks</label></td>
		<td><input type="text" id="remarksId" class="ci5"/></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>
			<div class="divgrid"><layout:datagrid
				styleClass="DATAGRID" property="employeeTargetDatagrid"
				selectionAllowed="true" multipleSelectionAllowed="false"
				model="datagrid">
			<layout:datagridColumn property="id" title="Id" mode="N,N,N"></layout:datagridColumn>
			<layout:datagridColumn property="targetName" title="Target"></layout:datagridColumn>
			<layout:datagridColumn property="targetCompletionDate" title="Target Completion Date"></layout:datagridColumn>
			<layout:datagridColumn property="plannerRemarks" title="Remarks"></layout:datagridColumn>
			</layout:datagrid></div>
			</td>
			<td><img src="images/add.png"
				onclick="StrutsLayout.addDatagridLine('employeeTargetDatagrid','targetId~targetCompletionDateId~remarksId~id1')"
				width="20px" /><br>
			<img src="images/delete.png"
				onclick="StrutsLayout.setDatagridLineState('employeeTargetDatagrid', 'removed')"
				width="20px" /></td>
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