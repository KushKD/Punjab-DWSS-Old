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
			document.hrSalaryStructureForm.action="hrSalaryStructureAction.do?method=find&d__mode="+d__mode+"&menuId=HRD009";
			document.hrSalaryStructureForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.hrSalaryStructureForm.action="hrSalaryStructureAction.do?method=update&d__mode="+d__mode+"&menuId=HRD009";
				document.hrSalaryStructureForm.submit();
			}
		}
		function de_add(){
			if(d__mode=='ent_add') {
				document.hrSalaryStructureForm.action="hrSalaryStructureAction.do?method=save&d__mode="+d__mode+"&menuId=HRD009";
				document.hrSalaryStructureForm.submit();
			}
		}
		    
	</script>
	<link href="css/form.css" rel="stylesheet" type="text/css">
	<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">
	<html:form action="hrSalaryStructureAction" method="post">
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
	<fieldset><legend>Employee Particulars</legend>
	<center>
	<br>
	<table>
		<tr>
			<td><label>Location</label></td>
			<td><html:select property="locationId" styleClass="cs1" styleId="locationId" style="width:250px;" onchange="ajaxFunction('hrSalaryStructureAction.do?locationId='+this.value+'&method=fetchEmployees', 'employeeId');">
				<html:option value="">Select Location</html:option>
				<html:options collection="userLocations" labelProperty="label"
					property="value"></html:options>
			</html:select></td>
			<td><label>Employee Name</label></td>
			<td><html:select property="employeeId" styleId="employeeId" styleClass="cs1" style="width:250px;" onchange="ajaxFunctionForText('hrSalaryStructureAction.do?employeeId='+this.value+'&method=fetchEmployeeType', 'employeeType');ajaxFunctionForText('hrSalaryStructureAction.do?employeeId='+this.value+'&method=fetchEmployeeWing', 'wing');ajaxFunctionForText('hrSalaryStructureAction.do?employeeId='+this.value+'&method=fetchEmployeeDesignation', 'designation');ajaxFunctionForText('hrSalaryStructureAction.do?employeeId='+this.value+'&method=fetchEmployeeJoiningDate', 'dateOfJoining');ajaxFunctionForText('hrSalaryStructureAction.do?employeeId='+this.value+'&method=fetchEmployeeAppNo', 'appointmentNo');ajaxFunctionForText('hrSalaryStructureAction.do?employeeId='+this.value+'&method=fetchEmployeePanNo', 'panNo');">
			</html:select></td>
		</tr>
		<tr>
			<td><label>Employee Type</label></td>
			<td><html:text property="employeeType" styleClass="ci5" style="width:250px;" readonly="true"></html:text>
			</td>
			
			<td><label>Wing</label></td>
			<td><html:text property="wing" styleId="wingId" style="width:250px;" readonly="true" styleClass="ci5"></html:text>
			</td>
		</tr>
		<tr>
			<td><label>Designation</label></td>
			<td><html:text property="designation" styleClass="ci5" style="width:250px;" readonly="true"></html:text>
			</td>
			
			<td><label>Date Of Joining</label></td>
			<td><html:text property="dateOfJoining" styleId="dateOfJoiningId" styleClass="ci5" style="width:250px;" readonly="true"></html:text>
			</td>
		</tr>
		<tr>
			<td><label>Appointment Number</label></td>
			<td><html:text property="appointmentNo" styleClass="ci5" style="width:250px;" readonly="true"></html:text>
			</td>
			
			<td><label>Pan No</label></td>
			<td><html:text property="panNo" styleId="wingId" style="width:250px;" readonly="true" styleClass="ci5"></html:text>
			</td>
		</tr>
		</table>
	</center>
	</fieldset>
	<fieldset><legend>Monthly Salary Structure</legend>
	<center>
	<table>
		<tr>
			<td><label>Basic Pay</label></td>
			<td><html:text property="basicPay" styleClass="ci5" onkeypress="return validateKey(event,this,'9@20@2')" ></html:text>
			</td>
			<td><label>HRA</label></td>
			<td><html:text property="hra" styleClass="ci5" onkeypress="return validateKey(event,this,'9@20@2')"></html:text>
			</td>
		</tr>
		<tr>
			<td><label>Conveyance Allowance</label></td>
			<td><html:text property="conveyanceAllowance" styleClass="ci5"  onkeypress="return validateKey(event,this,'9@20@2')"></html:text>
			</td>
			
			<td><label>Personal Allowance</label></td>
			<td><html:text property="personalAllowance" styleClass="ci5" onkeypress="return validateKey(event,this,'9@20@2')"></html:text>
			</td>
		</tr>
		<tr>
			<td><label>Education Allowance</label></td>
			<td><html:text property="educationAllowance" styleClass="ci5"  onkeypress="return validateKey(event,this,'9@20@2')"></html:text>
			</td>
			
			<td><label>TDS</label></td>
			<td><html:text property="tds" styleId="tdsId"   styleClass="ci5" onkeypress="return validateKey(event,this,'9@20@2')"></html:text>
			</td>
		</tr>
		<tr>
			<td><label>Income Tax</label></td>
			<td><html:text property="incomeTax" styleClass="ci5" onkeypress="return validateKey(event,this,'9@20@2')"></html:text>
			</td>
			
			<td><label>Provident Fund</label></td>
			<td><html:text property="providentFund" styleClass="ci5" onkeypress="return validateKey(event,this,'9@20@2')"></html:text>
			</td>
		</tr>
		<tr>
			<td><label>Leave Without Pay</label></td>
			<td><html:text property="leaveWithoutPay" styleClass="ci5" onkeypress="return validateKey(event,this,'9@20@2')"></html:text>
			</td>
			
			<td><label>Reimbursement</label></td>
			<td><html:text property="reimbursement" styleClass="ci5" onkeypress="return validateKey(event,this,'9@20@2')"></html:text>
			</td>
		</tr>
		<tr>
			<td><label>Dearness Allowance</label></td>
			<td><html:text property="dearnessAllowance" styleClass="ci5" onkeypress="return validateKey(event,this,'9@20@2')"></html:text>
			</td>
			
			<td><label>Total Payable Amount</label></td>
			<td><html:text property="totalAmount" styleClass="ci5" onkeypress="return validateKey(event,this,'9@20@2')"></html:text>
			</td>
		</tr>
		<tr>
		<td><label>Others</label></td>
		<td><html:text property="others" styleClass="ci5" onkeypress="return validateKey(event,this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
		<td><html:hidden property="fromDate"></html:hidden></td>
		</tr>
		</table>
	</center>
	
	</fieldset>
	<script language="javascript">
	triggerEvent(document.getElementById('locationId'), 'onchange');
	 <%if(MisUtility.ifEmpty(request.getAttribute("employeeId"))){%>
		document.getElementById("employeeId").value="<%=request.getAttribute("employeeId")%>";
		<%}%>
		triggerEvent(document.getElementById('employeeId'), 'onchange');
	</script>
		
</html:form>
</html:html>