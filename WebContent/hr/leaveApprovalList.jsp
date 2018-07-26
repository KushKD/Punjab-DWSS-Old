<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
<script language="JavaScript" src="js/calendar_us.js"></script>
<link rel="stylesheet" href="css/calendar.css">
<script type="text/javascript">
	function de_find() {
		document.leaveApprovalForm.action = "leaveApprovalAction.do?method=find&d__mode="+ d__mode+"&menuId=HRD010";
		document.leaveApprovalForm.submit();
	}

	function getLeave(leaveId) {
		document.leaveApprovalForm.action ="leaveApprovalAction.do?method=getLeaveToApproval&leaveId="+ leaveId + "&d__mode=" + d__mode+"&menuId=HRD010";
		document.leaveApprovalForm.submit();
	}
</script>
</head>
<html:html>
<body>
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
	<center>
	<table>
		<tr height="245px">
			<td><logic:present name="leaveApprovalList" scope="request">
				<logic:notEmpty name="leaveApprovalList" scope="request">
					<center>
					<div id="dispTag">
					<display:table name="leaveApprovalList"
						id="leave" class="mars" style="margin:0 1em 1em 0;">
						<display:column title="Leave Id">
							<a href="javascript:getLeave('${leave.leaveId}')">${leave.leaveId}</a>
						</display:column>
						<display:column property="entByEmployeeBean.employeeName" title="Leave Requester"  />
						<display:column property="entByEmployeeBean.employeeId" title="Leave Requester Employee Id" />
						<display:column property="entDate" title="Leave Request Entered On" decorator="com.prwss.mis.common.util.DateColumnDecorator"/>
						<display:column property="leaveStatus" title="Leave Status" />
					</display:table>
					</div></center>
				</logic:notEmpty>
				<logic:empty name="leaveApprovalList" scope="request">
					<center>
					<table border="1" style="background-color: #fc0;">
						<tr>
							<td>
							<h3><b>You Have No Leave Request, To Respond To</b></h3>
							</td>
						</tr>
					</table>
					</center>
				</logic:empty>
			</logic:present></td>
		</tr>
		<tr>
			<td>
			<fieldset><legend>Search Leaves</legend>
			<table>
				<tr>
					<td nowrap><label>Enter Leave Id</label></td>
					<td><html:text property="leaveId" styleClass="ci5" styleId="leaveId"></html:text></td>

					<td><label>Leave Status</label></td>
					<td><html:select property="leaveStatus" styleClass="cs1" styleId="leaveStatus">
						<html:option value="<%=MISConstants.HR_LEAVE_NO_ACTION %>">No-Action-Taken</html:option>
						<html:option value="<%=MISConstants.HR_LEAVE_ACCEPTED %>">Accepted</html:option>
						<html:option value="<%=MISConstants.HR_LEAVE_REJECTED %>">Rejected</html:option>
					</html:select></td>
					
					<td><label>Leave Request Period:</label></td>
					<td colspan="3"><html:text property="leaveRequestFromDate"  styleId="leaveRequestFromDate" styleClass="ci3"></html:text>
				<script>
	new tcal( {// form name
		'formname' : 'leaveApprovalForm',
		// input name
		'controlname' : 'leaveRequestFromDate'
	});
</script></td>
					<td><label>---</label></td>
					<td><html:text property="leaveRequestToDate" styleId="leaveRequestToDate" styleClass="ci3"></html:text>
				<script>
	new tcal( {// form name
		'formname' : 'leaveApprovalForm',
		// input name
		'controlname' : 'leaveRequestToDate'
	});
</script></td>


				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
	</table>
	</center>
</html:form>
</body>
</html:html>