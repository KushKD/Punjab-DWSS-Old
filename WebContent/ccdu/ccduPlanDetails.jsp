<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/datagrid.js" ></script>
<script type="text/javascript" src="js/calendarDateInput.js"></script>
<link href="css/layout.css" rel="stylesheet" type="text/css">
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function de_find() {
		document.ccduPlanDetailsForm.action = "ccduPlanDetailsAction.do?method=find&d__mode="+ d__mode+"&menuId=CCDU004";
		document.ccduPlanDetailsForm.submit();
	}
	function de_modify() {
		if (d__mode == 'ent_modify') {
			document.ccduPlanDetailsForm.action = "ccduPlanDetailsAction.do?method=update&d__mode=" + d__mode+"&menuId=CCDU004";
			document.ccduPlanDetailsForm.submit();
		}
	}
	function de_add() {
		if (d__mode == 'ent_add') {
			document.ccduPlanDetailsForm.action = "ccduPlanDetailsAction.do?method=save&d__mode=" + d__mode+"&menuId=CCDU004";
			document.ccduPlanDetailsForm.submit();
		}
	}
	function de_remove(){
		if(d__mode=='ent_delete') {
			document.ccduPlanDetailsForm.action="ccduPlanDetailsAction.do?method=delete&d__mode="+d__mode+"&menuId=CCDU004";
			document.ccduPlanDetailsForm.submit();
		}
	}
</script>
</head>
<html:html>
<body  bgcolor="#6699FF">
	<html:form action="ccduPlanDetailsAction" styleId="ccduPlanDetailsForm" >

	<logic:messagesPresent >
					<div id="errorDiv" class="error displaynone" style="width: 470px%; margin-bottom: 7px; height: 13px;">
 						<html:errors bundle="ccdu"/>   
					</div>
				</logic:messagesPresent >
				<logic:messagesPresent message="true">
					<div id="successDiv" class="success diplaynone" style="width: 470px;">
						<html:messages id="message" bundle="ccdu" message="true">
       						<li><bean:write name="message" /></li>
   						</html:messages>
					</div>
				</logic:messagesPresent >
	
	<fieldset><legend>CCDU Plan Details</legend>
		<center>
			<table>
				<tr>
					<td><label>CCDU Capacity Building Plan ID</label></td>
					<td colspan="3"><html:select property="planId" styleId="planId" styleClass="cs3" style="width:585">
					<html:option value="">Select</html:option>
					<html:options collection="ccduPlanIds" labelProperty="label" property="value"/>
					</html:select></td>
					</tr>
					<tr>
					<td>Location</td>
					<td colspan="3">
						<html:select property="locationId" styleClass="cs3" style="width:585" styleId="locationId" onchange="ajaxFunction('ccduPlanDetailsAction.do?locationId='+this.value+'&method=getTrainingIds', 'trainingId');triggerEvent(document.getElementById('trainingId'), 'onchange');
						if(this.value=='SPMC'){
							disable_ctrl('prePlanningCount~planningCount~implementationCount~operationMaintenanceCount~prePlanningCountCS~implementationCountCS~planningCountCS~operationMaintenanceCountCS~prePlanningCountNGO~planningCountNGO~implementationCountNGO~operationMaintenanceCountNGO~sustainabilityWssCount~cbSewerageCount',true);
						}">
							<html:option value="">Select Location</html:option>
							<html:options collection="locationIds" labelProperty="label" property="value"/>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td>Pre Planning Count</td>
					<td><html:text property="prePlanningCount" styleId="prePlanningCount" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text> </td>
					<td>Planning count</td>
					<td><html:text property="planningCount" styleId="planningCount" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text> </td>
				</tr>
				
				<tr>
					<td>Implementation Count</td>
					<td><html:text property="implementationCount" styleId="implementationCount" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text> </td>
					<td>Operation Maintenance count</td>
					<td><html:text property="operationMaintenanceCount" styleId="operationMaintenanceCount" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text> </td>
				</tr>				
				
				<tr>
					<td colspan="4"><hr/></td>
				</tr>
				<tr>
					<td>Pre Planning Count (Consultants)</td>
					<td><html:text property="prePlanningCountCS" styleId="prePlanningCountCS" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text> </td>
					<td>Planning count (Consultants)</td>
					<td><html:text property="planningCountCS" styleId="planningCountCS" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text> </td>
				</tr>
				
				<tr>
					<td>Implementation Count (Consultants)</td>
					<td><html:text property="implementationCountCS" styleId="implementationCountCS" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text> </td>
					<td>Operation Maintenance count (Consultants)</td>
					<td><html:text property="operationMaintenanceCountCS" styleId="operationMaintenanceCountCS" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text> </td>
				</tr>
				<tr>
					<td colspan="4"><hr/></td>
				</tr>
				
				<tr>
					<td>Pre Planning Count(NGO)</td>
					<td><html:text property="prePlanningCountNGO" styleId="prePlanningCountNGO" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text> </td>
					<td>Planning count (NGO)</td>
					<td><html:text property="planningCountNGO" styleId="planningCountNGO" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text> </td>
				</tr>
				
				<tr>
					<td>Implementation Count (NGO)</td>
					<td><html:text property="implementationCountNGO" styleId="implementationCountNGO" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text> </td>
					<td>Operation Maintenance count (NGO)</td>
					<td><html:text property="operationMaintenanceCountNGO" styleId="operationMaintenanceCountNGO" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text> </td>
				</tr>				
				<tr>
					<td>Sustainability of WSS</td>
					<td><html:text property="sustainabilityWssCount" styleId="sustainabilityWssCount" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
					<td>CB for Sewerage</td>
					<td><html:text property="cbSewerageCount" styleId="cbSewerageCount" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
				</tr>
			</table>
		</center>
	</fieldset>
	<fieldset><legend>Plan Details</legend>
	<table>
	<tr>
	<td><label>Training Id</label></td>
	<td><html:select property = "trainingId" styleId="trainingId" styleClass="cs3">
	</html:select></td>
	<td><label>Training Count</label></td>
	<td><input type="text" id="countId" Class="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></td>
	</tr>
	</table>
	
		<table>
			<tr>
				<td>
				<div class="divgrid">
				<layout:datagrid property="ccduPlanDetailsDatagrid" indexId="row" styleClass="DATAGRID" selectionAllowed="true" multipleSelectionAllowed="true" model="datagrid">
						<layout:datagridColumn property="trainingId" title="Training Id" mode="I,I,I"/>
						<layout:datagridColumn property="count" title="Count" />
				</layout:datagrid>
				</div>
				</td>
				<td><img src="images/add.png" onclick="StrutsLayout.addDatagridLine('ccduPlanDetailsDatagrid','trainingId~countId')" width="20px" /><br>
				<img src="images/delete.png" onclick="StrutsLayout.setDatagridLineState('ccduPlanDetailsDatagrid', 'removed')" width="20px" />
				</td>
			</tr>
		</table>
	</fieldset>
<script language="javascript">

triggerEvent(document.getElementById('locationId'), 'onchange');

</script>
	</html:form>
</body>
</html:html>
</html>