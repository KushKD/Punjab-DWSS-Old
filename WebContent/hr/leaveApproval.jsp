<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/calendarDateInput.js"></script>
<script language="JavaScript" src="js/calendar_us.js"></script>
<link rel="stylesheet" href="css/calendar.css">
<script type="text/javascript">
	function de_add() {
		if (d__mode == 'ent_add') {
			document.leaveApprovalForm.action = "leaveApprovalAction.do?method=save&d__mode="+ d__mode+"&menuId=HRD010";
			document.leaveApprovalForm.submit();
		}

	}
</script>

</head>
<html:html>

<body bgcolor="#6699FF">
<center>
</center>
<html:form action="leaveApprovalAction">
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
	<fieldset><legend>Leave Register</legend>
		<center>
		<table>
			<tr>
				<td><label>Leave Status</label></td>
					<td><html:select property="leaveStatus" styleClass="cs1" styleId="leaveStatus">
						<html:option value="<%=MISConstants.HR_LEAVE_NO_ACTION %>">No-Action-Taken</html:option>
						<html:option value="<%=MISConstants.HR_LEAVE_ACCEPTED %>">Accept</html:option>
						<html:option value="<%=MISConstants.HR_LEAVE_REJECTED %>">Reject</html:option>
					</html:select></td>
					</tr>
					<tr>
					
				<td valign="top"><label>Officer Comments</label></td>
				<td colspan="3"><html:textarea property="officerComments" cols="50"
					rows="5" styleClass="ci5"></html:textarea></td>
			</tr>
		</table>
		</center>
		</fieldset>
	<fieldset><legend>Leave Details</legend>
	<center>
	<table>
		<tr>
			<td><html:hidden property="leaveId" styleClass="ci5" styleId="leaveId"></html:hidden></td>
		</tr>
		<tr>
			<td><label>Leave From</label></td>
			<td><html:text property="leaveFromDate" styleId="leaveFromDate" styleClass="ci5" disabled="true"></html:text></td>

			<td><label>Leave Till</label></td>
			<td><html:text property="leaveToDate" styleId="leaveToDate" styleClass="ci5" disabled="true"></html:text></td>
		</tr>
		<tr>
			<td><label>Resaons</label></td>
			<td colspan="3"><html:textarea property="leaveReason" styleClass="ci5" cols="50" rows="5" disabled="true"></html:textarea></td>
		</tr>
	</table>
	</center>
	</fieldset>
	</html:form>
	</body>
	</html:html>
	</html>