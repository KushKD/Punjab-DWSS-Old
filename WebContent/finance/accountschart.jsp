<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<script type="text/javascript">
		function de_find(){		
			document.accountsChartForm.action="accountsChartAction.do?method=find&d__mode="+d__mode+"&menuId=FIN001";
			document.accountsChartForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.accountsChartForm.action="accountsChartAction.do?method=update&d__mode="+d__mode+"&menuId=FIN001";
				document.accountsChartForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.accountsChartForm.action="accountsChartAction.do?method=delete&d__mode="+d__mode+"&menuId=FIN001";
				document.accountsChartForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.accountsChartForm.action="accountsChartAction.do?method=post&d__mode="+d__mode+"&menuId=FIN001";
				document.accountsChartForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.accountsChartForm.action="accountsChartAction.do?method=save&d__mode="+d__mode+"&menuId=FIN001";
				document.accountsChartForm.submit();
			}
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>

<html:html>
<body bgcolor="#6699FF">
<html:form action="accountsChartAction" method="post">

<logic:messagesPresent >
					<div id="errorDiv" class="error displaynone" style="width: 470px%; margin-bottom: 7px; height: 13px;">
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

	<fieldset><legend>Charts Of Accounts </legend>
	<center>
	<table>
		<tr>
		<td><label>Code Head</label></td>
		<td><html:text property="codeHeadId" styleId="codeHeadId" styleClass="ci5"></html:text></td>
		<td><label> Description</label></td>
		
		<td><html:textarea  property="codeHeadIdDescription" styleId="codeHeadIdDescription"
				styleClass="ci5" /></td>
		
		</tr>
		
				
		<tr>
		<td><label>Account Type</label></td>
		<td><html:radio property="accountType" value="CASH">Cash</html:radio>
		<html:radio property="accountType" value="BANK">Bank</html:radio>
		<html:radio property="accountType" value="OTHERS">Others</html:radio>
		</td>
		</tr>
		
		<tr>
		<td><label>Account Head Nature</label></td>
		<td><html:radio property="accountNature" value="PAYMENT">Payment</html:radio>
		<html:radio property="accountNature" value="RECEIPT">Receipt</html:radio>
		<html:radio property="accountNature" value="BOTH">Both</html:radio>
		</td>
		</tr>
		
		<tr>
		<td><label>Major Head</label></td>
		<td><html:text property="majorHeadId" styleId="testElementId" styleClass="ci5"></html:text></td>
		</tr>
		
		<tr>
		<td><label>Minor Head</label></td>
		<td><html:text property="minorHeadId" styleId="testElementId" styleClass="ci5"></html:text></td>
		</tr>
		
		<tr>
		<td><label>Sub Head</label></td>
		<td><html:text property="subHeadId" styleId="testElementId" styleClass="ci5"></html:text></td>
		</tr>
		
		</table>
	</center>
	
	</fieldset>
	
</html:form>
</html:html>
