<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%><%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
<script language="JavaScript" src="js/calendar_us.js"></script>
<script type="text/javascript">
		function de_find(){		
			document.taskUpdateForm.action="taskUpdateAction.do?method=find&d__mode="+d__mode+"&menuId=DTM004";
			document.taskUpdateForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.taskUpdateForm.action="taskUpdateAction.do?method=update&d__mode="+d__mode+"&menuId=DTM004";
				document.taskUpdateForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.taskUpdateForm.action="taskUpdateAction.do?method=delete&d__mode="+d__mode+"&menuId=DTM004";
				document.taskUpdateForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.taskUpdateForm.action="taskUpdateAction.do?method=post&d__mode="+d__mode+"&menuId=DTM004";
				document.taskUpdateForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.taskUpdateForm.action="taskUpdateAction.do?method=save&d__mode="+d__mode+"&menuId=DTM001";
				document.taskUpdateForm.submit();
			}
		}
		
		    
	</script>
	<link href="css/form.css" rel="stylesheet" type="text/css">
	<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
	
	<link rel="stylesheet" href="css/calendar.css">
	
</head>
<html:html>
<body bgcolor="#6699FF" >
<html:form action="/taskUpdateAction">

	<logic:messagesPresent >
					<div id="errorDiv" class="error displaynone" style="width: 470px%; margin-bottom: 7px; height: 13px;">
 						<html:errors bundle="DakTask"/>   
					</div>
				</logic:messagesPresent >
				<logic:messagesPresent message="true">
					<div id="successDiv" class="success diplaynone" style="width: 470px;">
						<html:messages id="message" bundle="DakTask" message="true">
       						<li><bean:write name="message" /></li>
   						</html:messages>
					</div>
				</logic:messagesPresent >
<fieldset><legend>Task Update</legend>
	
	<table style="{margin-left:150px;}">
		<tr>
			<td><label>Location</label></td>
			<td><html:select property="locationId" styleId="locationId" styleClass="cs1">
				<html:options collection="userLocations" labelProperty="label"
					property="value"></html:options></html:select></td>	
							
		
		<td><label>Date</label></td>
		<td><html:text property="updateDate" styleId="updateDate" styleClass="ci5"/>
			</td>
		</tr>		
	</table>
	
</fieldset>	
<fieldset><legend>Update Task</legend>	
	<center>
	<table >
		<tr>
			<td>
			<div class="divgrid">
			<layout:datagrid styleClass="DATAGRID" property="taskAllocationDataGrid"	selectionAllowed="true" multipleSelectionAllowed="false" model="datagrid">
			<layout:datagridColumn property="documentType" title="Document Type" mode="III" ></layout:datagridColumn>
			<layout:datagridColumn property="documentNumber" title="Document Number" mode="I,I,I"></layout:datagridColumn>
			<layout:datagridColumn property="senderName" title="Sender Name" mode="I,I,I"></layout:datagridColumn>		
			<layout:datagridColumn property="assignTo" title="Assign To" mode="I,I,I"></layout:datagridColumn>
			<layout:datagridColumn property="targetDate" title="Target Date" mode="I,I,I"></layout:datagridColumn>	
			<layout:datagridColumn property="completedDate" title="Completed Date" ></layout:datagridColumn>		
			</layout:datagrid></div>
			</td>
		</tr>
	</table>
	</center>
	</fieldset>	
</html:form>
</body>
</html:html>
