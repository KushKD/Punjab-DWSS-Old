<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
	<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<script type="text/javascript">
		function de_find(){		
			document.budgetApprovalForm.action="budgetApprovalAction.do?method=find&d__mode="+d__mode+"&menuId=FIN003";
			document.budgetApprovalForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.budgetApprovalForm.action="budgetApprovalAction.do?method=update&d__mode="+d__mode+"&menuId=FIN003";
				document.budgetApprovalForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.budgetApprovalForm.action="budgetApprovalAction.do?method=delete&d__mode="+d__mode+"&menuId=FIN003";
				document.budgetApprovalForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.budgetApprovalForm.action="budgetApprovalAction.do?method=post&d__mode="+d__mode+"&menuId=FIN003";
				document.budgetApprovalForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.budgetApprovalForm.action="budgetApprovalAction.do?method=save&d__mode="+d__mode+"&menuId=FIN003";
				document.budgetApprovalForm.submit();
			}
		}
		    
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>

<html:html>
<body bgcolor="#6699FF">

<html:form action="budgetApprovalAction" method="post">

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
	<fieldset><legend>Budget Allocation  </legend>
	<center>
	<table>
		
		<tr>

		<td><label>Location</label></td>
		<td><html:select property="locationId" styleId = "locationId" styleClass="cs1">
		<html:option value="">Select</html:option>
		<html:options collection="userLocations" labelProperty="label" property="value"/>
		</html:select>
	    </td>

		<td><label>Project Code </label></td>
		<td><html:select property="programId" styleId="programId" styleClass="cs1" onchange="ajaxFunction('budgetApprovalAction.do?programId='+this.value+'&method=fetchBudgetId', 'budgetId');"> 
		<html:option value="">Select</html:option>
		<html:options collection="programIds" labelProperty="label" property="value"/>
		</html:select>
		</td>

	    <td><label>Budget Code</label></td>
		<td><html:select property="budgetId" styleId ="budgetId" styleClass="cs1">
		</html:select>
	    </td>
	    
		</tr>
		</table>
	</center>
	
	</fieldset>
	<fieldset>
	<center>
	
	
	<table>
			<tr>
				<td>
				<div class="divgrid">
					<layout:datagrid styleClass="DATAGRID" property="budgetApprovalDatagrid" selectionAllowed="true" multipleSelectionAllowed="false" model="datagrid">
						<layout:datagridColumn property="id" title="Id" mode="N,N,N"/>
						<layout:datagridColumn property="componentId" title="Component Name" mode="I,I,I"/>
						<layout:datagridColumn property="subComponentId" title="Sub-Component Name" mode="I,I,I"/>
						<layout:datagridColumn property="activityId" title="Activity Name" mode="I,I,I"/>
						<layout:datagridColumn property="codeHeadId" title="Applicable Code Heads" mode="I,I,I" />
						<layout:datagridColumn property="estPhysicalBudgt" title="Physical(Budgt)" mode="I,I,I"/>
						<layout:datagridColumn property="appPhysicalBudgt" title="Physical(Allocation)"/>
						<layout:datagridColumn property="budgtUnit" title="Units" mode="I,I,I"/>
						<layout:datagridColumn property="estFinanceBudgt" title="Financial(Budgt)" mode="I,I,I"/>
						<layout:datagridColumn property="appFinanceBudgt" title="Financial(Allocation)"/>
					</layout:datagrid>
					</div>
				</td>
				
			</tr>
		</table>
	</center>
	
	</fieldset>
	<script>
	triggerEvent(document.getElementById('programId'), 'onchange');
	<%if(MisUtility.ifEmpty(request.getAttribute("budgetId"))){%>
	document.getElementById("budgetId").value="<%=request.getAttribute("budgetId")%>";
	<%}%>
	triggerEvent(document.getElementById('componentId'), 'onchange');
	triggerEvent(document.getElementById('subComponentId'), 'onchange');
	</script>
	
</html:form>
</html:html>
