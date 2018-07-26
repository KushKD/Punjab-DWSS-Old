<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
--><script type="text/javascript">
	function de_find() {
		document.createCCDUPlanForm.action = "createCCDUPlanAction.do?method=find&d__mode="+ d__mode;
		document.createCCDUPlanForm.submit();
	}
	function de_modify() {
		if (d__mode == 'ent_modify') {
			document.createCCDUPlanForm.action = "createCCDUPlanAction.do?method=update&d__mode=" + d__mode;
			document.createCCDUPlanForm.submit();
		}
	}
	function de_confirm(){
		if(d__mode=='ent_post') {
			document.schemeMasterForm.action= "createCCDUPlanAction.do?method=post&d__mode=" + d__mode;
			document.schemeMasterForm.submit();
		}
	}
	function de_add() {
		if (d__mode == 'ent_add') {
			document.createCCDUPlanForm.action = "createCCDUPlanAction.do?method=save&d__mode=" + d__mode;
			document.createCCDUPlanForm.submit();
		}

	}
</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>
<html:html>
<body bgcolor="#6699FF">
	<html:form action="createCCDUPlanAction" styleId="createCCDUPlanForm">

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
	
	<br>
	<br>
	
		<fieldset><legend>Create CCDU Plan</legend>
		<center>
		<table>
			<tr>
				<td>CCDU Capacity Building Plan ID</td>
				<td colspan="4">
					<html:select property="planId" styleClass="cs3" styleId="planId">
					<html:option value="">Select CCDU Capacity Building Plan ID</html:option>
					<html:options collection="ccduPlanIds" labelProperty="label" property="value"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td><label>Select Period:</label></td>
				<td><html:text property="planStartDate" styleId="planStartDateId" styleClass="ci5"></html:text>
				<!--<input class=ci4 type=button onclick="c1.innerpopup('planStartDateId','calendar_frame');" value="..."/>--></td>
				<td><label>------</label></td>
				<td><html:text property = "planEndDate" styleId="planEndDateId" styleClass="ci5"></html:text>
				<!--<input class=ci4 type=button onclick="c1.innerpopup('planEndDateId','calendar_frame');" value="..."/>-->
				</td>
			</tr>
		</table>
			</center>
		</fieldset>

		
		<logic:present name="planSummary" scope="request">
		<display:table name="planSummary" id="plan" class="mars" style="margin:0 1em 1em 0;"  >
			<display:column title="Location">${plan.locationName}</display:column>
			<display:column title="Number of Trainings registered">${plan.trainingsCount}</display:column>
		</display:table>
		</logic:present>
	
	</html:form>
</body>
</html:html>
</html>