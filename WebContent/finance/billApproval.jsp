<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<script language="JavaScript" src="js/calendar_us.js"></script>

<link rel="stylesheet" href="css/calendar.css">
<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
<script type="text/javascript">
		function de_find(){
			
			document.billApprovalForm.action="billApprovalAction.do?method=find&d__mode="+d__mode+"&menuId=FIN011";
			document.billApprovalForm.submit();
		}
		
		function de_add(){
			if(d__mode=='ent_add') {
				document.getElementById("billNo").disabled = false;
				document.billApprovalForm.action="billApprovalAction.do?method=save&d__mode="+d__mode+"&menuId=FIN011";
				document.billApprovalForm.submit();
			}
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>

<html:html>
<body bgcolor="#6699FF">
	<html:form action="billApprovalAction" styleId="billApprovalForm">
	<html:errors bundle="finance"/>
	
	<center>
		<fieldset><legend>Contract Bill Approval</legend>
		<table>
		<tr>
			<td><label>Location</label></td>
			<td><html:select property="locationId" styleId="locationId"
				styleClass="cs1" onchange="ajaxFunction('billApprovalAction.do?locationId='+this.value+'&method=fetchContracts', 'contractId'); triggerEvent(document.getElementById('contractId'), 'onchange');ajaxFunction('billApprovalAction.do?locationId='+this.value+'&method=fetchDocumentNo', 'documentNo');triggerEvent(document.getElementById('documentNo'), 'onchange');">
				<html:options collection="userLocations" labelProperty="label"
					property="value" />
			</html:select></td>
			
			<td>Contract Number</td>
			<td><html:select property="contractId" styleClass="cs1" styleId="contractId" onchange="ajaxFunction('billApprovalAction.do?contractId='+this.value+'&method=fetchContractsDetail', 'milestonId');">
			</html:select></td>
		</tr>

		<tr>
			<td>Document Number</td>
			<td><html:select property="documentNo" styleClass="cs1" styleId="documentNo" onchange="ajaxFunctionForText('billApprovalAction.do?documentNo='+this.value+'&method=fetchDocumentReferenceNo', 'billNo');">
			</html:select></td>
			
			<td>Bill Number</td>
			<td><html:text property="documentReferenceNo" styleClass="ci5" disabled="true" styleId="billNo">
			</html:text></td>
		</tr>
		<tr>
			<td>Milestone</td>
			<td colspan="6"><html:select property="milestonId" styleClass="cs3" styleId="milestonId">
			</html:select></td>
		</tr>
		<tr>
			<td>Bill Amount</td>
			<td><html:text property="billAmount" styleClass="ci5">
			</html:text></td>
			
			<td>Released Amount</td>
			<td><html:text property="releasedAmount" styleClass="ci5">
			</html:text></td>
		</tr>
		
		</table>
		</fieldset>
	</center>
	<div id="dispTag">
	<logic:present name="billApprovalBeans" scope="request">
		<logic:notEmpty name="billApprovalBeans" scope="request">
			<center><display:table name="billApprovalBeans" id="billApproval" class="mars" style="margin:0 1em 1em 0;" pagesize="5" requestURI="/billApprovalAction.do">
				<display:column title="Contract Number" property="contractHeaderBean.contractId" sortable="true"></display:column>
				<display:column property="documentNo" title="Document Number"/>
				<display:column property="documentRefNo" title="Document Ref Number"/>
				<display:column property="billAmount" title="Bill Amount"/>
				<display:column property="releasedAmount" title="Released Amount"/>
				<display:column property="vocId" title="Vocher Number"/>
				<display:column property="misAuditBean.entDate" title="Voucher Generation Date" decorator="com.prwss.mis.common.util.DateColumnDecorator"/>
			</display:table></center>
		</logic:notEmpty>
	</logic:present></div>
	</html:form>
	<script>
	triggerEvent(document.getElementById('locationId'), 'onchange');
	</script>
</body>
</html:html>