<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/onlynumber.js"></script>
<script type="text/javascript" src="js/calendarDateInput.js"></script>
<script type="text/javascript">
	function de_find(){		
		document.attendanceForm.action="attendanceAction.do?method=find&d__mode="+d__mode+"&menuId=HRD001";
		document.attendanceForm.submit();
	}

	function de_modify(){		
		if(d__mode=='ent_modify') { 
			document.attendanceForm.action="attendanceAction.do?method=update&d__mode="+d__mode+"&menuId=HRD001";
			document.attendanceForm.submit();
		}
	}
	function de_confirm(){
		if(d__mode=='ent_post') {
			document.attendanceForm.action="attendanceAction.do?method=post&d__mode="+d__mode+"&menuId=HRD001";
			document.attendanceForm.submit();
		}
	}

	function de_add() {
		if (d__mode == 'ent_add') {
			document.attendanceForm.action="attendanceAction.do?method=save&d__mode="+ d__mode+"&menuId=HRD001";
			document.attendanceForm.submit();
		}

	}
	function getAttendance(employeeId,attendanceDate){
		document.attendanceForm.action="attendanceAction.do?method=populate&employeeId="+employeeId+"&attendanceDate="+attendanceDate+"&d__mode="+d__mode+"&menuId=HRD001";
		document.attendanceForm.submit();
	}
</script>
<script language="JavaScript" src="js/calendar_us.js"></script>
<link rel="stylesheet" href="css/calendar.css">
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>
<html:html>

<body bgcolor="#6699FF">
<center>
</center>
<html:form action="attendanceAction" styleId="attendanceForm">
	
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
	<fieldset><legend>Particulars</legend>
	<center>
	<table>
		<tr>
			<td><label>Location</label></td>
			<td><html:select property="locationId" styleId="locationId"
				styleClass="cs1"
				onchange="ajaxFunction('attendanceAction.do?locationId='+this.value+'&method=fetchEmployees', 'employeeId');">
				<html:option value="">Select Location</html:option>
				<html:options collection="userLocations" labelProperty="label"
					property="value"></html:options>
			</html:select></td>

			<td><label>Select Date</label></td>
			<td><html:text property="attendanceDate"
				styleId="attendanceDateId" styleClass="ci5" readonly="true"/> <!--<script>
	new tcal( {// form name
		'formname' : 'attendanceForm',
		// input name
		'controlname' : 'attendanceDateId'
	});
</script>--></td>

		  <td><label>Employee Name/Id</label></td>
		  <td><html:select property="employeeId" styleId="employeeId"></html:select></td>
		</tr>
		<tr><td><html:hidden property="attendenceId" ></html:hidden></td></tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Attendance Status</legend>
	<center>
	<table class="form2" style="margin: 0 1em 1em 0;">
	<tr class="form2TH">
	<td><label><b>Attendance Status</b></label></td>
	<td><html:radio property="attendanceStatus" value="<%=MISConstants.HR_ATTENDANCE_PRESENT%>">Present</html:radio></td>
	<td><html:radio property="attendanceStatus" value="<%=MISConstants.HR_ATTENDANCE_ABSENT%>">Absent</html:radio></td>
	<td><html:radio property="attendanceStatus" value="<%=MISConstants.HR_ATTENDANCE_HALF_DAY%>">Half Day</html:radio></td>
	<td><html:radio property="attendanceStatus" value="<%=MISConstants.HR_ATTENDANCE_ONE_THIRD%>">One Third</html:radio></td>
	</tr>
	</table>
	</center>
	</fieldset>

	<div id="dispTag">
	<logic:present name="attendanceList" scope="request">
		<logic:notEmpty name="attendanceList" scope="request">
			<center><display:table name="attendanceList" id="attendance" class="mars" style="margin:0 1em 1em 0;" pagesize="10" requestURI="/attendanceAction.do">
				<display:column title="Employee Id"><a href="javascript:getAttendance('${attendance.employeeBean.employeeId}','${attendance.attendanceDate}')">${attendance.employeeBean.employeeId}</a></display:column>
				<display:column title="Employee Name" property="employeeBean.employeeName" sortable="true"></display:column>
				<display:column property="attendanceDate" title="Attendance Date" sortable="true" decorator="com.prwss.mis.common.util.DateColumnDecorator"/>
				<display:column property="attendanceStatus" title="Attendance Status"/>
			</display:table></center>
		</logic:notEmpty>
	</logic:present></div>
</html:form>
</body>
<script>
triggerEvent(document.getElementById('locationId'), 'onchange');
<%if(MisUtility.ifEmpty(request.getAttribute("employeeId"))){%>
document.getElementById("employeeId").value="<%=request.getAttribute("employeeId")%>";
<%}%>
</script>
</html:html>