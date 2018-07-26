<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<script language="JavaScript" src="js/calendar_us.js"></script>

<link rel="stylesheet" href="css/calendar.css">
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
--><script type="text/javascript">
		function de_find(){
			
			document.budgetForm.action="budgetAction.do?method=find&d__mode="+d__mode+"&menuId=FIN010";
			document.budgetForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.budgetForm.action="budgetAction.do?method=update&d__mode="+d__mode+"&menuId=FIN010";
				document.budgetForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.budgetForm.action="budgetAction.do?method=delete&d__mode="+d__mode+"&menuId=FIN010";
				document.budgetForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.budgetForm.action="budgetAction.do?method=post&d__mode="+d__mode+"&menuId=FIN010";
				document.budgetForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.budgetForm.action="budgetAction.do?method=save&d__mode="+d__mode+"&menuId=FIN010";
				document.budgetForm.submit();
			}
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>

<html:html>
<body bgcolor="#6699FF">
	<html:form action="budgetAction" styleId="budgetForm">
	
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
	
	
		<fieldset><legend>Create Budget Plan</legend>
		<center>
		<table>
		<tr>
			<td>Budget Plan ID</td>
			<td><html:select property="budgetId" styleClass="cs1" styleId="budgetId">
			<html:option value="">Select</html:option>
			<html:options collection="budgetIds" labelProperty="label" property="value"/>

			</html:select></td>
		</tr>

		<tr>
			<td>Project Code</td>
			<td><html:select property="programId" styleClass="cs1" styleId="programId">
			<html:option value="">Select</html:option>
			<html:options collection="programIds" labelProperty="label" property="value"/>
     
			</html:select></td>
		</tr>

		

		<tr>
				<td><label>Select Period(From):</label></td>
				<td><html:text property="budgetFromDate" styleId="budgetFromDate" styleClass="ci3" readonly="true"></html:text>
				
				<td><label>To:----</label></td>
				<td><html:text property = "budgetToDate" styleId="budgetToDate" styleClass="ci3" readonly="true"></html:text>
				
				</td>
			</tr>
		</table>
		</center>
		</fieldset>
	
		
		
	
	</html:form>
</body>
</html:html>