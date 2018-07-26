<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
	<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout"  %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script language="JavaScript" src="js/calendar_us.js"></script>

<link rel="stylesheet" href="css/calendar.css">
<script type="text/javascript">
		function de_find(){		
			document.gpwscRegisterForm.action="gpwscRegisterAction.do?method=find&d__mode="+d__mode+"&menuId=FIN012";
			document.gpwscRegisterForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.gpwscRegisterForm.action="gpwscRegisterAction.do?method=update&d__mode="+d__mode+"&menuId=FIN012";
				document.gpwscRegisterForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.gpwscRegisterForm.action="gpwscRegisterAction.do?method=delete&d__mode="+d__mode+"&menuId=FIN012";
				document.gpwscRegisterForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.gpwscRegisterForm.action="gpwscRegisterAction.do?method=post&d__mode="+d__mode+"&menuId=FIN012";
				document.gpwscRegisterForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.gpwscRegisterForm.action="gpwscRegisterAction.do?method=save&d__mode="+d__mode+"&menuId=FIN012";
				document.gpwscRegisterForm.submit();
			}
		}

		function getTransaction(transactionNumber){
			document.gpwscRegisterForm.action="gpwscRegisterAction.do?method=populate&transactionNumber="+transactionNumber+"&d__mode="+d__mode+"&menuId=MST009";
			document.gpwscRegisterForm.submit();
		}	

		function hideControl(value){
			if(value == 'PAYMENT'){
				hide_ctrl('receive',true);
				hide_ctrl('pay',false);
				}else if (value == 'RECEIPT'){
					hide_ctrl('pay',true);
					hide_ctrl('receive',false);
					}
			}
		
		</script>

<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
--></head>
<html:html>
<body bgcolor="#6699FF">

<html:form action="gpwscRegisterAction" method="post">

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
<fieldset><legend>Transaction Details</legend>
<center>
<table>
<tr>
<td>Location</td>
			<td colspan="3"><html:select property="locationId" styleId="locationId"
				style="width:560px;" onchange="ajaxFunction('GetFilterValues.to?locationId='+this.value, 'blockId');triggerEvent(document.getElementById('blockId'), 'onchange');">
				<html:option value="">Select Location</html:option>
				<html:options collection="userLocations" labelProperty="label" property="value"/>
			</html:select></td>
			</tr>
			<tr>
			<td>Select Block</td>
			<td colspan="3"><html:select property="blockId" styleId="blockId" style="width:560px;"
				onchange="ajaxFunction('GetSchemeFilterValues.to?blockId='+this.value, 'villageId');triggerEvent(document.getElementById('villageId'), 'onchange');">
				<html:option value="Select Block"></html:option>
			</html:select></td>
		</tr>
		<tr>
			<td>Select Village</td>
			<td colspan="3"><html:select property="villageId" styleId="villageId" style="width:560px;"
				onchange="ajaxFunction('GetSchemeFilterValues.to?villageId='+this.value, 'schemeId');">
				<html:option value="Select Village"></html:option>
			</html:select></td>
		</tr>
		<tr>
		
<td>Scheme Name</td>
<td colspan="3"><html:select property="schemeId" styleId="schemeId"
				style="width:560px;" 
				onchange="ajaxFunction('gpwscRegisterAction.do?villageId='+document.getElementById('villageId').value+'&schemeId='+this.value+'&method=fetchGPWSCIds', 'gpwcId');">				
		
			</html:select>
		</td>
		</tr>
		<tr>	
<td>GPWSC</td>
<td colspan="3"><html:select property="gpwcId" styleId="gpwcId"
				style="width:560px;" onchange="ajaxFunction('gpwscRegisterAction.do?gpwcId='+this.value+'&method=fetchBankIds', 'bankId');">
			</html:select>
</td>
<td></td>			
</tr>
<tr>
<td>Transaction Number</td>
<td><html:text property="transactionNumber" styleId="transactionNumberId"
				styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')">
</html:text></td>
			
			<td>Transaction Type</td>
<td><html:select property="transactionType" styleId="transactionType"
				styleClass="cs2" onchange="hideControl(this.value);">
			<html:option value="">Select</html:option>
			<html:option value="<%=MISConstants.FIN_VOC_TYPE_PAYMENT%>"><%=MISConstants.FIN_VOC_TYPE_PAYMENT%></html:option>
			<html:option value="<%=MISConstants.FIN_VOC_TYPE_RECEIPT%>"><%=MISConstants.FIN_VOC_TYPE_RECEIPT%></html:option>				
			</html:select></td>
</tr>

<tr>
<td>Date of Transaction</td>
<td colspan="2"><html:text property="dateOfTransaction" styleId="transactionDate" styleClass="ci5" ></html:text></td>
</tr>

<tr><td>Bank Id</td>
	<td colspan="3"><html:select property="bankId" styleId="bankId" style="width:560px;" onchange="ajaxFunction('gpwscRegisterAction.do?bankId='+this.value+'&gpwcId='+document.getElementById('gpwcId').value+'&method=fetchReciptType', 'receiptType');">
	</html:select></td></tr>
</table>
</center>
</fieldset>
<div id="pay">
<fieldset>
<legend>Payment Side</legend>
<center>
<table>
<tr>
<td>Payee Name</td>
<td><html:text property="payeeName" onkeypress="return charKey(event);"></html:text></td>
<td>Bill No</td>
<td><html:text property="billNo"></html:text></td>
</tr>
<tr>
<td>Payment Type</td>
<td colspan="2"><html:select property="paymentType" style="width:300px;">
<html:option value="">Select</html:option>
<html:option value="<%=MISConstants.STAFF_COST%>">Staff Cost</html:option>
<html:option value="<%=MISConstants.ELECTRICITY_COST%>">Electricity Cost</html:option>
<html:option value="<%=MISConstants.MTC_REPAIR_COST%>">Mtc. & Repair Cost</html:option>
<html:option value="Other">Other</html:option>
</html:select></td>
<td>Amount (Rs)</td>
<td><html:text property="paymentAmount" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text>
</tr>
<tr>
<td>Payment Activity</td>
<td colspan="6"><html:textarea property="paymentActivity" cols="80" rows="2"></html:textarea></td>
</tr>
</table>
</center>
</fieldset>
</div>
<div id="receive">
<fieldset>
<legend>Receipt Side</legend>
<center>
<table>
<tr>
<td>Receipt Type</td>
<td ><html:select property="receiptType" styleId="receiptType" style="width:300px;">
<html:option value="">Select</html:option>

<html:option value="<%=MISConstants.LOC_FOR_INSTL_1%>">Installment-1</html:option>
<html:option value="<%=MISConstants.LOC_FOR_INSTL_2%>">Installment-2</html:option>
<html:option value="<%=MISConstants.LOC_FOR_INSTL_3%>">Installment-3</html:option>
<html:option value="<%=MISConstants.GPWSC_BENEFICIARY_SHARE%>">Beneficiary Share - Community</html:option>
<html:option value="<%=MISConstants.LOC_FOR_GAP_FUND%>">Gap Fund - GOP</html:option>
<html:option value="<%=MISConstants.GAP_FUND_VOLUNTARILY%>">Gap Fund - Voluntarily from Community</html:option>
<html:option value="<%=MISConstants.GAP_FUND_NON_BUDGETARY%>">Gap Fund - Non Budgetary Resources</html:option>
<html:option value="<%=MISConstants.GAP_FUND_UNTIED_FUNDS%>">Gap Fund - Untied Funds</html:option>
<html:option value="<%=MISConstants.MONTHLY_REVENUE%>">Monthly Revenue</html:option>
<html:option value="<%=MISConstants.OTHER_INCOME%>">Other-Income</html:option>
<html:option value="<%=MISConstants.TDS%>"><%=MISConstants.TDS%></html:option>
<html:option value="<%=MISConstants.VAT%>"><%=MISConstants.VAT%></html:option>
<html:option value="<%=MISConstants.LABOUR_CESS%>"><%=MISConstants.LABOUR_CESS%></html:option>
<html:option value="<%=MISConstants.SECURITY_RETENTION%>"><%=MISConstants.SECURITY_RETENTION%></html:option>
</html:select></td>

<td>Amount (Rs)</td>
<td ><html:text property="receiptAmount" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text>
</tr>
<tr>
<td>Receipt Activity</td>
<td colspan ="4"><html:textarea property="receiptActivity" cols="70" rows="2"></html:textarea></td>
</tr>
</table>
</center>
</fieldset>
</div>

	<div id="dispTag">
	<logic:present name="gpwscRegisterBeans" scope="request">
		<logic:notEmpty name="gpwscRegisterBeans" scope="request">
			<center><display:table name="gpwscRegisterBeans" id="gpwsc" class="mars" style="margin:0 1em 1em 0;" pagesize="10" requestURI="/gpwscRegisterAction.do" export="true">
				<display:column title="Click to Display" media="html"><a href="javascript:getTransaction('${gpwsc.transactionNumber}')">${gpwsc.transactionNumber}</a></display:column>
				<display:column property="dateOfTransaction" title="Date of Transaction" sortable="true" decorator="com.prwss.mis.common.util.DateColumnDecorator"/>
				<display:column property="committeeBean.committeeName" title="Gpwsc Name" media="html"/>
				<display:column property="committeeBean.committeeId" title="Gpwsc Id" media="html"/>
				<display:column property="transactionType" title="Transaction Type" media="html"/>
				<display:column property="misAuditBean.status" title="Status" media="html"/>
		   <display:setProperty name="export.excel.filename" value="gpwscTransactionCheckList.xls"/>
           <display:setProperty name="export.xml" value="false" />
            <display:setProperty name="export.csv" value="false"/>
			</display:table></center>
			
		</logic:notEmpty>
	</logic:present></div>
	
<script>
triggerEvent(document.getElementById('locationId'), 'onchange');
triggerEvent(document.getElementById('transactionType'), 'onchange');

<%if(MisUtility.ifEmpty(request.getAttribute("blockId"))){%>
document.getElementById("blockId").value="<%=request.getAttribute("blockId")%>";
triggerEvent(document.getElementById('blockId'), 'onchange');
<%}%>

<%if(MisUtility.ifEmpty(request.getAttribute("villageId"))){%>
document.getElementById("villageId").value="<%=request.getAttribute("villageId")%>";
triggerEvent(document.getElementById('villageId'), 'onchange');
<%}%>

<%if(MisUtility.ifEmpty(request.getAttribute("schemeId"))){%>
document.getElementById('schemeId').value="<%=request.getAttribute("schemeId")%>";
triggerEvent(document.getElementById('schemeId'), 'onchange');
<%}%>

<%if(MisUtility.ifEmpty(request.getAttribute("gpwcId"))){%>
	//alert(request.getAttribute("gpwcId2"));
     document.getElementById('gpwcId').value="<%=request.getAttribute("gpwcId")%>";
triggerEvent(document.getElementById('gpwcId'), 'onchange');
<%}%>

<%if(MisUtility.ifEmpty(request.getAttribute("bankId"))){%>
document.getElementById('bankId').value="<%=request.getAttribute("bankId")%>";
<%}%>


</script>
</html:form>
</html:html>