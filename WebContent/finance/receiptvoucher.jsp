<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
	<%@page import="com.prwss.mis.common.util.MISConstants"%>
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
			document.receiptVoucherForm.action="receiptVoucherAction.do?method=find&d__mode="+d__mode+"&menuId=FIN008";
			document.receiptVoucherForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.receiptVoucherForm.action="receiptVoucherAction.do?method=update&d__mode="+d__mode+"&menuId=FIN008";
				document.receiptVoucherForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.receiptVoucherForm.action="receiptVoucherAction.do?method=delete&d__mode="+d__mode+"&menuId=FIN008";
				document.receiptVoucherForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.receiptVoucherForm.action="receiptVoucherAction.do?method=post&d__mode="+d__mode+"&menuId=FIN008";
				document.receiptVoucherForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.receiptVoucherForm.action="receiptVoucherAction.do?method=save&d__mode="+d__mode+"&menuId=FIN008";
				document.receiptVoucherForm.submit();
			}
		}
		</script>

<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
--></head>
<html:html>
<body bgcolor="#6699FF">

<html:form action="receiptVoucherAction" method="post">

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
				
	<fieldset><legend>Receipt Voucher </legend>
	<center>
	<table>

		<tr>

			
			</tr>
			
			<tr>
			<td><label>Location</label></td>
			<td><html:select property="locationId" styleId="locationId"
				styleClass="cs1" onchange="ajaxFunction('receiptVoucherAction.do?locationId='+this.value+'&method=fetchRequestVocId', 'voucherNo');triggerEvent(document.getElementById('payerType'), 'onchange');">
				<html:option value="">Select Location</html:option>
				<html:options collection="userLocations" labelProperty="label" property="value"/>
			</html:select></td>
			
			<td><label>System Generated Number</label></td>
			<td><html:select property="voucherNo" styleId="voucherNo"
				styleClass="cs1" onkeypress="return validateKey(event,	this,'9@20@2')">
			</html:select></td>
			
			</tr>
			<tr>
			<td><label>Voucher Date</label></td>
			<td><html:text property="voucherDate" styleId="voucherDate"
				styleClass="ci5" />
			</td>
<td><label>Transaction  Date</label></td>
			<td><html:text property="transactionDate" styleId="transactionDate"
				styleClass="ci5" />
				 </td>
			</tr>
			<tr>	
			<td><label>Payer Type</label></td>
			<td><html:select property="payerType" 	styleId="payerType " styleClass="cs1" onchange="ajaxFunction('receiptVoucherAction.do?payerType='+this.value+'&method=fetchPayeeId&locationId='+document.getElementById('locationId').value, 'payerName');">
			<html:option value="">Select</html:option>
			<html:option value="<%=MISConstants.FIN_PAYMENT_EMPLOYEE %>">Employee </html:option>
            <html:option value="<%=MISConstants.FIN_PAYMENT_OFFICE %>">Office</html:option>
            <html:option value="<%=MISConstants.FIN_PAYMENT_VENDOR %>">Vendor</html:option>
			</html:select></td>
				<td><label>Payer </label></td>

			<td><html:select property="payerName" styleId="payerName"
				styleClass="cs1">
			</html:select></td>
		</tr>
			<tr>
              <td><label>Receipt Mode</label></td>
			<td><html:radio property="receiptMode" value="CASH">Cash</html:radio>
			<html:radio property="receiptMode" value="BANK">Bank</html:radio></td>
			
			<td><label>Receipt Type</label></td>
			<td><html:radio property="receiptType" value="SECURITY-DEPOSIT">Security Deposit/BG</html:radio>
			<html:radio property="receiptType" value="OTHERS">Others</html:radio></td>

		</tr>
		<tr>	
			<td><label>Project </label></td>
			<td><html:select property="programId" styleId="programId"
				styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="NA">N/A</html:option>
				
				<html:options collection="programIds" labelProperty="label" property="value"/>
			</html:select></td>
				<td><label>Voucher Number </label></td>
			<td><html:text property="documentNo" styleId="documentNo"
				styleClass="cs1" onkeypress="return validateKey(event,	this,'9@20@2')">
			</html:text></td>
            
		</tr>
		<tr>

			<td><label>Instrument Type </label></td>
			<td><html:select property="instrumentType"
				styleId="instrumentType " styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:option value="Cheque"> Cheque</html:option>
				<html:option value="DD">DD</html:option>
				<html:option value="Cash"> Cash</html:option>
				<html:option value="Others"> Others</html:option>
			</html:select></td>

			<td><label>Amount </label></td>
			<td><html:text property="instrAmount" styleId="instrAmount"
				styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"/></td>

		</tr>
		<tr>
			<td><label>Instrument No</label></td>
			<td><html:text property="instrumentNo" styleId="instrumentNo"
				styleClass="ci5" /></td>

			<td><label>Instrument Date </label></td>
			<td><html:text property="instrumentDate"
				styleId="instrumentDate" styleClass="ci5" />

</td>
		</tr>
		<tr>
			<td><label>Bank </label></td>
			<td colspan="6"><html:select property="bankId" styleId="bankId" styleClass="cs3">
			<html:option value="">Select</html:option>
			<html:options collection="bankIds" labelProperty="label" property="value"/>
			</html:select></td>
		</tr>
		
		<tr>
			<td><label>Notes</label></td>
			<td colspan="6"><html:textarea rows="4" cols="50" property="notes"  styleId="notes"
				styleClass="ci5"  /></td>

		</tr>
	</table>
	</center>
	
	</fieldset>
	<fieldset><legend>Receipt Voucher Details </legend>
	<center>
	<table>
		<tr>
			<td><label>Code Head</label></td>
		<td><html:select property="codeHeadId" styleId="codeHeadId " styleClass="cs1"> 
		<html:option value="">Select</html:option>
		<html:options collection="codeHeadIds" labelProperty="label" property="value"/>
		</html:select>
		</td>

			<td><label>Component</label></td>
			<td><html:select property="componentId" styleId="componentId"
				styleClass="cs1"
				onchange="ajaxFunction('receiptVoucherAction.do?componentId='+this.value+'&method=fetchSubComponent', 'subComponentId'); triggerEvent(document.getElementById('subComponentId'), 'onchange');">
				<html:option value="">Select</html:option>
				<html:options collection="componentIds" labelProperty="label"
					property="value" />
			</html:select></td>

		</tr>
		<tr>

			
			<td><label>Sub Component</label></td>
		<td><html:select property="subComponentId" styleId="subComponentId" styleClass="cs1" onchange="ajaxFunction('receiptVoucherAction.do?subComponentId='+this.value+'&method=fetchActivity', 'activityId'); ">
		</html:select>
		</td>


			<td><label>Activity</label></td>
			<td><html:select property="activityId" styleId="activityId"
				styleClass="cs1">

			</html:select></td>

		</tr>
		<tr>
			
			
			<td><label> Amount</label></td>
			<td><input type="text" id="amount" class="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"/></td>
			
			<td><label>Remarks</label></td>
			
			<td><html:textarea  property="remarks" styleId="notes"
				styleClass="ci5" /></td>

		</tr>
		
	</table>
	
	<table>
			<tr>
				<td>
				<div class="divgrid">
					<layout:datagrid styleClass="DATAGRID" property="receiptVoucherDatagrid" selectionAllowed="true" multipleSelectionAllowed="false" model="datagrid">
						<layout:datagridColumn property="id" title="Id" mode="N,N,N"/>
						<layout:datagridColumn property="codeHeadId" title="Code Head"/>
						<layout:datagridColumn property="componentId" title="Component"/>
						<layout:datagridColumn property="subComponentId" title=" Sub-Component"/>
						<layout:datagridColumn property="activityId" title="Activity"/>
						<layout:datagridColumn property="amount" title="Amount"/>
						<layout:datagridColumn property="remarks" title="Remarks"/>
						
					</layout:datagrid>
					</div>
				</td>
				<td>
					<img id="emp_hist1" src="images/add.png" 
					onclick="StrutsLayout.addDatagridLine('receiptVoucherDatagrid','codeHeadId~componentId~subComponentId~activityId~amount~remarks')" width="20px"/><br>
					<img id="emp_hist2" src="images/delete.png" 
					onclick="StrutsLayout.setDatagridLineState('receiptVoucherDatagrid', 'removed')" 
					width="20px"/>
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
		 <%if(MisUtility.ifEmpty(request.getAttribute("documentNo"))){%>
			document.getElementById("documentNo").value="<%=request.getAttribute("documentNo")%>";
			<%}%>
			 <%if(MisUtility.ifEmpty(request.getAttribute("payerName"))){%>
				document.getElementById("payerName").value="<%=request.getAttribute("payerName")%>";
				<%}%>
	triggerEvent(document.getElementById('componentId'), 'onchange');
	triggerEvent(document.getElementById('subComponentId'), 'onchange');
	</script>
</html:form>
</html:html>