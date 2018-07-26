<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page import="com.prwss.mis.common.util.MISConstants"%>
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
<script language="JavaScript" src="js/calendar_us.js"></script>
<script type="text/javascript">
		function de_find(){		
			document.taskAllocationForm.action="taskAllocationAction.do?method=find&d__mode="+d__mode+"&menuId=DTM001";
			document.taskAllocationForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.taskAllocationForm.action="taskAllocationAction.do?method=update&d__mode="+d__mode+"&menuId=DTM001";
				document.taskAllocationForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.taskAllocationForm.action="taskAllocationAction.do?method=delete&d__mode="+d__mode+"&menuId=DTM001";
				document.taskAllocationForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.taskAllocationForm.action="taskAllocationAction.do?method=post&d__mode="+d__mode+"&menuId=DTM001";
				document.taskAllocationForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.taskAllocationForm.action="taskAllocationAction.do?method=save&d__mode="+d__mode+"&menuId=DTM001";
				document.taskAllocationForm.submit();
			}
		}
		
		    
	</script>
	<link href="css/form.css" rel="stylesheet" type="text/css">
	<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
	
	<link rel="stylesheet" href="css/calendar.css">
	
</head>
<html:html>
<body bgcolor="#6699FF" >
<html:form action="/taskAllocationAction">

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
<!--<fieldset><legend>Task Allocation</legend>
	
	<table style="{margin-left:150px;}">
		<tr>
			<td><label>Location</label></td>
			<td><html:select property="locationId" styleId="locationId" styleClass="cs1">
				<html:options collection="userLocations" labelProperty="label"
					property="value"></html:options></html:select></td>	
		
		<td><label>Date</label></td>
		<td><html:text property="allocationDate" styleId="allocationDate" styleClass="ci5"/></td>
		</tr>		
	</table>
	
</fieldset>	
<fieldset><legend>Allocate</legend>	
	<center>
	<table >
		<tr>
			<td>
			<div class="divgrid">
			<layout:datagrid styleClass="DATAGRID" property="taskAllocationDataGrid"	selectionAllowed="true" multipleSelectionAllowed="false" model="datagrid">
			<layout:datagridColumn property="documentType" title="Document Type" mode="III"></layout:datagridColumn>
			<layout:datagridColumn property="documentNumber" title="Document Number" mode="I,I,I"></layout:datagridColumn>
			<layout:datagridColumn property="senderName" title="Sender Name" mode="I,I,I"></layout:datagridColumn>		
			<layout:datagridColumn property="assignTo" title="Assign To"></layout:datagridColumn>
			<layout:datagridColumn property="targetDate" title="Target Date"></layout:datagridColumn>			
			</layout:datagrid></div>
			</td>
		</tr>
	</table>
	</center>
	</fieldset>	
-->
<table>
		<tr height="245px">
			<td><logic:present name="complaintBookBeanList" scope="request">
				<logic:notEmpty name="complaintBookBeanList" scope="request">
					<center><display:table name="complaintBookBeanList"
						id="ticket" class="mars" style="margin:0 1em 1em 0;">
						<display:column title="Ticket Id">
							<a href="javascript:getTicketHistory('${ticket.ticketId}')">${ticket.ticketId}</a>
						</display:column>
						<display:column property="entDate" title="Ticket Raised on" decorator="com.prwss.mis.common.util.DateColumnDecorator" />
						<display:column property="subject" title="Subject" />
						<display:column property="status" title="Status" />
					</display:table></center>
				</logic:notEmpty>
				<logic:empty name="complaintBookBeanList" scope="request">
					<center>
					<table border="1" style="background-color: #fc0;">
						<tr>
							<td>
							<h3><b>You have no Dak's'</b></h3>
							</td>
						</tr>
					</table>
					</center>
				</logic:empty>
			</logic:present></td>
		</tr>
		<tr>
			<td>
			<fieldset><legend>Search Dak</legend>
			<table>
				<tr>
				<td><label>Location</label></td>
				<td>
				<html:select property="locationId" styleClass="wide">
					<html:options collection="userLocations" labelProperty="label" property="value"></html:options>
				</html:select>
				</td>
					<td nowrap><label>Enter Inwar Dak Number</label></td>
					<td><html:text property="documentNo" styleClass="ci5"></html:text></td>

					<td><label>Forward Status</label></td>
					<td><html:select property="dakForwardStatus" styleClass="cs1">
						<html:option value="Open">Open</html:option>
						<html:option value="Close">Closed</html:option>
						<html:option value="">All</html:option>
					</html:select></td>
					<td><label>Period:</label></td>
					<td colspan="3"><html:text property="fromDate" styleClass="ci3"></html:text>
				<input class=ci4 type=button onclick="c1.innerpopup('fromDate','calendar_frame');" value="..."/> 
					<label>---</label>
					<html:text property="toDate" styleClass="ci3"></html:text>
				<input class=ci4 type=button onclick="c1.innerpopup('toDate','calendar_frame');" value="..."/> </td>


				</tr>
			</table>
			</fieldset>
			</td>
			</tr>
			</table>


</html:form>
</body>
</html:html>
