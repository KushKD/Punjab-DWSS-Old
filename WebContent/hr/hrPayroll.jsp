<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
<script type="text/javascript">
		function de_find(){		
			document.hrPayrollForm.action="hrPayrollAction.do?method=find&d__mode="+d__mode+"&menuId=HRD008";
			document.hrPayrollForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.hrPayrollForm.action="hrPayrollAction.do?method=update&d__mode="+d__mode+"&menuId=HRD008";
				document.hrPayrollForm.submit();
			}
		}
		function de_add(){
			if(d__mode=='ent_add') {
				document.getElementById("amountId").disabled = false;
				document.hrPayrollForm.action="hrPayrollAction.do?method=save&d__mode="+d__mode+"&menuId=HRD008";
				document.hrPayrollForm.submit();
			}
		}

		    
	</script>
	<link href="css/form.css" rel="stylesheet" type="text/css">
	<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>


<html:html>
<body bgcolor="#6699FF">
	<html:form action="hrPayrollAction" method="post">
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
	<fieldset><legend>Generate Payroll Voucher</legend>
	<center>
	<br>
	<table>
		<tr>
			<td><label>Location</label></td>
			<td><html:select property="locationId" styleClass="cs1" styleId="locationId" onchange="ajaxFunction('hrPayrollAction.do?locationId='+this.value+'&method=fetchEmployees', 'employeeId');triggerEvent(document.getElementById('employeeId'), 'onchange');">
				<html:option value="">Select Location</html:option>
				<html:options collection="userLocations" labelProperty="label"
					property="value"></html:options>
			</html:select></td>
			<td><label>Employee Name</label></td>
			<td><html:select property="employeeId" styleId="employeeId" styleClass="cs1" onchange="ajaxFunctionForText('hrPayrollAction.do?employeeId='+this.value+'&method=fetchSalary', 'amountId');">
			</html:select></td>
		</tr>
		<tr>
			<td nowrap>Month</td>
			<td><html:select property="payrollMonth">
				<option value="">Select Month</option>>
				<option value="01">JAN</option>
				<option value="02">FEB</option>
				<option value="03">MAR</option>
				<option value="04">APR</option>
				<option value="05">MAY</option>
				<option value="06">JUN</option>
				<option value="07">JUL</option>
				<option value="08">AUG</option>
				<option value="09">SEP</option>
				<option value="10">OCT</option>
				<option value="11">NOV</option>
				<option value="12">DEC</option>
			</html:select></td>
			<td nowrap>Year</td>
			<td><html:select property="payrollYear">
				<option value="">Select Year</option>>
				<option value="2007">2007</option>
				<option value="2008">2008</option>
				<option value="2009">2009</option>
				<option value="2010">2010</option>
				<option value="2011">2011</option>
				<option value="2012">2012</option>
				<option value="2013">2013</option>
				<option value="2014">2014</option>
				<option value="2015">2015</option>
				<option value="2016">2016</option>
				<option value="2017">2017</option>
				<option value="2018">2018</option>
				<option value="2019">2019</option>
				<option value="2020">2020</option>
			</html:select></td>
		</tr>
	<tr>
	<td><label><b>Current Payable Monthly Salary</b></label></td>
	<td><html:text property="totalAmount" styleId="amountId" disabled="true"></html:text></td>
	</tr>
	</table>
	</center>
	</fieldset>
	<div id="dispTag">
	<logic:present name="hrPayrollBeans" scope="request">
		<logic:notEmpty name="hrPayrollBeans" scope="request">
			<center><display:table name="hrPayrollBeans" id="hrPayrollBean" class="mars" style="margin:0 1em 1em 0;" pagesize="5" requestURI="/hrPayrollAction.do">
				<display:column title="Employee Name" property="employeeBean.employeeName" sortable="true"></display:column>
				<display:column property="employeeBean.employeeId" title="Employee Id" sortable="true"/>
				<display:column property="payrollMonth" title="Month For"/>
				<display:column property="payrollYear" title="Year For"/>
				<display:column property="totalAmount" title="Total Salary Amount"/>
				<display:column property="vocId" title="Payroll Vocher Number"/>
				<display:column property="misAuditBean.entDate" title="Voucher Generation Date" decorator="com.prwss.mis.common.util.DateColumnDecorator"/>
			</display:table></center>
		</logic:notEmpty>
	</logic:present></div>
	<script language="javascript">
	triggerEvent(document.getElementById('locationId'), 'onchange');
	 <%if(MisUtility.ifEmpty(request.getAttribute("employeeId"))){%>
		document.getElementById("employeeId").value="<%=request.getAttribute("employeeId")%>";
	<%}%>
	
	</script>
</html:form>
</html:html>