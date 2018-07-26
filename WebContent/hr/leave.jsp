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
	function de_find() {
		document.leaveForm.action = "leaveAction.do?method=find&d__mode="+ d__mode+"&menuId=HRD002";
		document.leaveForm.submit();
	}
	function de_modify() {
		if (d__mode == 'ent_modify') {
			document.leaveForm.action = "leaveAction.do?method=update&d__mode="+ d__mode+"&menuId=HRD002";
			document.leaveForm.submit();
		}
	}
	function de_remove() {
		if (d__mode == 'ent_delete') {
			document.leaveForm.action = "leaveAction.do?method=delete&d__mode="+d__mode+"&menuId=HRD002";
			document.leaveForm.submit();
		}
	}
	function de_confirm() {
		if (d__mode == 'ent_post') {
			document.leaveForm.action = "leaveAction.do?method=post&d__mode="+ d__mode+"&menuId=HRD002";
			document.leaveForm.submit();
		}
	}
	function de_add() {
		if (d__mode == 'ent_add') {
			document.leaveForm.action = "leaveAction.do?method=save&d__mode="+ d__mode+"&menuId=HRD002";
			document.leaveForm.submit();
		}

	}

	function getLeave(leaveId){
		document.leaveForm.action="leaveAction.do?method=populate&leaveId="+leaveId+"&d__mode="+d__mode+"&menuId=HRD002";
		document.leaveForm.submit();
	}
</script>

</head>
<html:html>

<body bgcolor="#6699FF">
<center>
</center>
<html:form action="leaveAction" styleId="leaveForm">
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
	<fieldset><legend>Apply for Leave</legend>
	<center>
	<table>
		<tr>
			<td><label>Location</label></td>
			<td><html:select property="locationId" styleId="locationId"
				styleClass="cs1">
				<html:option value="">Select Location</html:option>
				<html:options collection="userLocations" labelProperty="label"
					property="value"></html:options>
			</html:select></td>
			<td><label>Leave Id</label></td>
			<td><html:text property="leaveId" styleClass="ci5" styleId="leaveId"></html:text></td>
		</tr>
		<tr>
			<td><label>Leave From</label></td>
			<td><html:text property="leaveFromDate" styleId="leaveFromDate" styleClass="ci5"></html:text><!--<script>
	new tcal( {// form name
		'formname' : 'leaveForm',
		// input name
		'controlname' : 'leaveFromDate'
	});
</script>--></td>

			<td><label>Leave Till</label></td>
			<td><html:text property="leaveToDate" styleId="leaveToDate" styleClass="ci5"></html:text><!--<script>
	new tcal( {// form name
		'formname' : 'leaveForm',
		// input name
		'controlname' : 'leaveToDate'
	});
</script>--></td>
		</tr>
		<tr>
			<td><label>Resaons</label></td>
			<td colspan="3"><html:textarea property="leaveReason" styleClass="ci5"
				cols="50" rows="6"></html:textarea></td>
		</tr>
	</table>
	<table class="form2" style="margin: 0 1em 1em 0;">
	<tr class="form2TH">
	<td><label><b>Leave Entitled</b></label></td>
	<td><html:text property="leaveEntitled" readonly="readonly" onkeypress="return validateKey(event,this,'9@20@2')"></html:text></td>
	<td><label><b>Leave Availed</b></label></td>
	<td><html:text property="leaveAvailed" readonly="readonly" onkeypress="return validateKey(event,this,'9@20@2')"></html:text></td>
	<td><label><b>Leave Balance</b></label></td>
	<td><html:text property="leaveBalance" readonly="readonly" onkeypress="return validateKey(event,this,'9@20@2')"></html:text></td>
	</tr>
	</table>
	</center>
	</fieldset>
	
	<logic:present name="leaveManagementBeans" scope="request">
	<logic:notEmpty name="leaveManagementBeans" scope="request">
	<fieldset>
	<legend>Your Leave History</legend>
	<div id="dispTag">
			<center><display:table name="leaveManagementBeans" id="leave" class="mars" style="margin:0 1em 1em 0;" pagesize="5" requestURI="/leaveAction.do">
				<display:column title="Leave Id" property="leaveId" sortable="true"></display:column>
				<display:column title="Request Date" property="entDate" sortable="true" decorator="com.prwss.mis.common.util.DateColumnDecorator"></display:column>
				<display:column property="leaveFromDate" title="Leave From" sortable="true" decorator="com.prwss.mis.common.util.DateColumnDecorator"/>
				<display:column property="leaveToDate" title="Leave Till" sortable="true" decorator="com.prwss.mis.common.util.DateColumnDecorator"/>
				<display:column property="leaveStatus" title="Action" />
				<display:column property="officerComments" title="Reporting Officer Comments" />
			</display:table></center>
	</div>
	</fieldset>
	</logic:notEmpty>
	</logic:present>
	
</html:form>
</body>
</html:html>