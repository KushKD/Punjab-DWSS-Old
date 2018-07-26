<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
	<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script language="JavaScript" src="js/calendar_us.js"></script>

<link rel="stylesheet" href="css/calendar.css">
<script type="text/javascript">
		function de_find(){		
			document.adjustmentVoucherForm.action="adjustmentVoucherAction.do?method=find&d__mode="+d__mode+"&menuId=FIN009";
			document.adjustmentVoucherForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.adjustmentVoucherForm.action="adjustmentVoucherAction.do?method=update&d__mode="+d__mode+"&menuId=FIN009";
				document.adjustmentVoucherForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.adjustmentVoucherForm.action="adjustmentVoucherAction.do?method=delete&d__mode="+d__mode+"&menuId=FIN009";
				document.adjustmentVoucherForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.adjustmentVoucherForm.action="adjustmentVoucherAction.do?method=post&d__mode="+d__mode+"&menuId=FIN009";
				document.adjustmentVoucherForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.adjustmentVoucherForm.action="adjustmentVoucherAction.do?method=save&d__mode="+d__mode+"&menuId=FIN009";
				document.adjustmentVoucherForm.submit();
			}
		}
		</script>

<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
--></head>
<html:html>
<body bgcolor="#6699FF">

<html:form action="adjustmentVoucherAction" method="post">

<logic:messagesPresent >
					<div id="errorDiv" class="error displaynone" style="width: 470px%; margin-bottom: 7px; min-height: 13px;">
 						<html:errors bundle="finance"/>   
					</div>
				</logic:messagesPresent >
				<logic:messagesPresent message="true">
					<div id="successDiv" class="success diplaynone" style="width: 470px;">
						<html:messages id="message" bundle="finance" message="true">
       						<li><bean:write name="message" /></li>
   						</html:messages>
					</div>
				</logic:messagesPresent >
		
	<fieldset><center><label style="font-size:20px;font-family:times;">Data pertaining to this screen should be entered by 10th of following month</label></center></fieldset>
				
	<fieldset><legend>Adjustment Voucher </legend>
	<center>
	<table>

		<tr>

			<td><label>Project </label></td>
			<td><html:select property="programId" styleId="programId"
				styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:options collection="programIds" labelProperty="label" property="value"/>
			</html:select></td>
			
			<td><label>Location</label></td>
		<td><html:select property="locationId" styleId = "locationId" styleClass="cs1" onchange="ajaxFunction('adjustmentVoucherAction.do?locationId='+this.value+'&method=fetchRequestVocId', 'voucherNo');">
		<html:option value="">Select </html:option>
		<html:options collection="userLocations" labelProperty="label" property="value"/>
		</html:select>
	    </td>
			</tr>
			
		<tr>

			<td><label>Voucher Number</label></td>
			<td><html:select property="voucherNo" styleId="voucherNo"
				styleClass="cs1" >
			</html:select></td>
			<td><label>Voucher Date</label></td>
			<td><html:text property="voucherDate" styleId="voucherDate"
				styleClass="ci5" readonly="readonly"/></td>
			
			</tr>
		
		<tr>
			<td><label>Notes </label></td>
				<td colspan="3"><html:textarea rows="4" cols="50" property="notes"  styleId="notes"
				styleClass="ci5"  /></td>

		</tr>
	</table>
	</center>
	
	</fieldset>
	<fieldset><legend>Adjustment Voucher Details </legend>
	<center>
	<table border="1" >
		<tr>
		<td>
		<table align="left">
		<tr><td colspan="4"><center><b>Debit Details</b></center></td></tr>
		<tr>
			<td><label>Code Head</label></td>
		<td><html:select property="debitCodeHeadId" styleId="debitCodeHeadId " styleClass="cs1"> 
		<html:option value="">Select</html:option>
		<html:options collection="debitCodeHeadIds" labelProperty="label" property="value"/>
		</html:select>
		</td>

			<td><label>Component</label></td>
			<td><html:select property="debitComponentId" styleId="debitComponentId"
				styleClass="cs1"
				onchange="ajaxFunction('adjustmentVoucherAction.do?componentId='+this.value+'&method=fetchSubComponent', 'debitSubComponentId'); triggerEvent(document.getElementById('debitSubComponentId'), 'onchange');">
				<html:option value="">Select</html:option>
				<html:options collection="componentIds" labelProperty="label"
					property="value" />
			</html:select></td>

		</tr>
		<tr>

			<td><label>Sub Component</label></td>
		<td><html:select property="debitSubComponentId" styleId="debitSubComponentId" styleClass="cs1" onchange="ajaxFunction('adjustmentVoucherAction.do?subComponentId='+this.value+'&method=fetchActivity', 'debitActivityId'); ">
		</html:select>
		</td>


			<td><label>Activity</label></td>
			<td><html:select property="debitActivityId" styleId="debitActivityId"
				styleClass="cs1">

			</html:select></td>

		</tr>
		<tr>
			
			
			<td><label>Debit Amount</label></td>
			
			
			<td><html:text  property="debitAmountId" styleId="debitAmountId" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"/></td>
			
			<td><label>Remarks</label></td>
			
			<td><html:textarea  property="debitRemarks" styleId="debitRemarks"
				styleClass="ci5"  cols="17" /></td>
		</tr>
		
		
		</table>
		</td>
		<td></td>
		<td>
		<table align="right">
		<tr><td colspan="4"><center><b>Credit Details</b></center></td></tr>
		<tr>
			<td><label>Code Head</label></td>
		<td><html:select property="creditCodeHeadId" styleId="creditCodeHeadId " styleClass="cs1"> 
		<html:option value="">Select</html:option>
		<html:options collection="creditCodeHeadIds" labelProperty="label" property="value"/>
		</html:select>
		</td>

			<td><label>Component</label></td>
			<td><html:select property="creditComponentId" styleId="creditComponentId"
				styleClass="cs1"
				onchange="ajaxFunction('adjustmentVoucherAction.do?componentId='+this.value+'&method=fetchSubComponent', 'creditSubComponentId'); triggerEvent(document.getElementById('creditSubComponentId'), 'onchange');">
				<html:option value="">Select</html:option>
				<html:options collection="componentIds" labelProperty="label"
					property="value" />
			</html:select></td>

		</tr>
		<tr>

			<td><label>Sub Component</label></td>
		<td><html:select property="creditSubComponentId" styleId="creditSubComponentId" styleClass="cs1" onchange="ajaxFunction('adjustmentVoucherAction.do?subComponentId='+this.value+'&method=fetchActivity', 'creditActivityId'); ">
		</html:select>
		</td>


			<td><label>Activity</label></td>
			<td><html:select property="creditActivityId" styleId="creditActivityId"
				styleClass="cs1">

			</html:select></td>

		</tr>
		<tr>
			
			
			<td><label>Credit Amount</label></td>
			<td><html:text  property="creditAmountId" styleId="creditAmountId" styleClass="ci5" onkeypress="return validateKey(event,this,'9@20@2')"/></td>
			
			<td><label>Remarks</label></td>
			
			<td><html:textarea  property="creditRemarks" styleId="creditRemarks"
				styleClass="ci5" cols="17"  /></td>
		</tr>
		
		
		</table>
		
		</td>
		
		</tr>
		
	</table>
	
	</center>
	
	</fieldset>
	<script>
	triggerEvent(document.getElementById('locationId'), 'onchange');
	
	<%if(MisUtility.ifEmpty(request.getAttribute("vocId"))){%>
	document.getElementById("voucherNo").value="<%=request.getAttribute("vocId")%>";
	<%}%>

	triggerEvent(document.getElementById('debitComponentId'), 'onchange');
	
	<%if(MisUtility.ifEmpty(request.getAttribute("debitSubComponentId"))){%>
	document.getElementById("debitSubComponentId").value="<%=request.getAttribute("debitSubComponentId")%>";
	<%}%>

	triggerEvent(document.getElementById('debitSubComponentId'), 'onchange');
	
	<%if(MisUtility.ifEmpty(request.getAttribute("debitActivityId"))){%>
	document.getElementById("debitActivityId").value="<%=request.getAttribute("debitActivityId")%>";
	<%}%>

	triggerEvent(document.getElementById('creditComponentId'), 'onchange');
	
	<%if(MisUtility.ifEmpty(request.getAttribute("creditSubComponentId"))){%>
	document.getElementById("creditSubComponentId").value="<%=request.getAttribute("creditSubComponentId")%>";
	<%}%>

	triggerEvent(document.getElementById('creditSubComponentId'), 'onchange');

	<%if(MisUtility.ifEmpty(request.getAttribute("creditActivityId"))){%>
	document.getElementById("creditActivityId").value="<%=request.getAttribute("creditActivityId")%>";
	<%}%>
	
	</script>
</html:form>
</html:html>