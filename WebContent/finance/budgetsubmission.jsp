<%@page import="com.prwss.mis.common.util.MisUtility"%>
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
			document.budgetSubmissionForm.action="budgetSubmissionAction.do?method=find&d__mode="+d__mode+"&menuId=FIN002";
			document.budgetSubmissionForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.budgetSubmissionForm.action="budgetSubmissionAction.do?method=update&d__mode="+d__mode+"&menuId=FIN002";
				document.budgetSubmissionForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.budgetSubmissionForm.action="budgetSubmissionAction.do?method=delete&d__mode="+d__mode+"&menuId=FIN002";
				document.budgetSubmissionForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.budgetSubmissionForm.action="budgetSubmissionAction.do?method=post&d__mode="+d__mode+"&menuId=FIN002";
				document.budgetSubmissionForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.budgetSubmissionForm.action="budgetSubmissionAction.do?method=save&d__mode="+d__mode+"&menuId=FIN002";
				document.budgetSubmissionForm.submit();
			}
		}
		    
		
		
	</script>
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>

<html:html>
<body bgcolor="#6699FF">

<html:form action="budgetSubmissionAction" method="post">

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
	<fieldset><legend>Budget Submission </legend>
	<center>
	<table>
		
		<tr>

		<td><label>Location</label></td>
		<td><html:select property="locationId" styleId = "locationId" styleClass="cs1" onchange="triggerEvent(document.getElementById('programId'), 'onchange');">
		<html:option value="">Select</html:option>
		<html:options collection="userLocations" labelProperty="label" property="value"/>
		</html:select>
	    </td>
	    
		<td><label>Project Code </label></td>
		<td><html:select property="programId" styleId="programId" styleClass="cs1"  onchange="ajaxFunction('budgetSubmissionAction.do?programId='+this.value+'&method=fetchBudgetId', 'budgetId');"> 
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
	<legend>Budget Submission Details</legend>
	<center>
	<table>
	<tr>
		<td><label>Component</label></td>
		<td><html:select property="componentId" styleId="componentId" styleClass="cs1" onchange="ajaxFunction('budgetSubmissionAction.do?componentId='+this.value+'&method=fetchSubComponent', 'subComponentId'); triggerEvent(document.getElementById('subComponentId'), 'onchange');"> 
		<html:option value="" >Select</html:option>
		<html:options collection="componentIds" labelProperty="label" property="value"/>
		</html:select>
		</td>
		
		<td><label>Sub Component</label></td>
		<td><html:select property="subComponentId" styleId="subComponentId" styleClass="cs1" onchange="ajaxFunction('budgetSubmissionAction.do?subComponentId='+this.value+'&method=fetchActivity', 'activityId'); ">
		</html:select>
		</td>
		
		<td><label>Activity</label></td>
		<td><html:select property="activityId" styleId="activityId" styleClass="cs1"> 
		
		</html:select>
		</td>
		</tr>
		<tr>
		<td><label>Code Head</label></td>
		<td><html:select property="codeHeadId" styleId="codeHeadId"  styleClass="cs1"> 
		<html:option value="">Select</html:option>
		<html:options collection="codeHeadIds" labelProperty="label" property="value"/>
		</html:select>
		</td>
			<td><label>Physical(Budget)</label></td>
			<td><input type="text" id="estPhysicalBudgt" class="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"/></td>

			<td><label>Units</label></td>
		    <td><input type = "text" id="budgtUnit" class="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"/></td>
		</tr>
		<tr>
			<td><label>Financial(Budget)</label></td>
			<td><input type="text" id="estFinanceBudgt" class="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"/></td>

		</tr>
	</table>
	
	<table>
			<tr>
				<td>
				<div class="divgrid">
					<layout:datagrid styleClass="DATAGRID" property="budgetSubmissionDatagrid" selectionAllowed="true" multipleSelectionAllowed="false" model="datagrid">
						<layout:datagridColumn property="id" title="Id" mode="N,N,N"/>
						<layout:datagridColumn property="componentId" title="Component Name" mode="I,I,I"/>
						<layout:datagridColumn property="subComponentId" title="Sub-Component Name" mode="I,I,I"/>
						<layout:datagridColumn property="activityId" title="Activity Name" mode="I,I,I"/>
						<layout:datagridColumn property="codeHeadId" title="Applicable Code Heads" mode="I,I,I" />
						<layout:datagridColumn property="estPhysicalBudgt" title="Physical(Budgt)" mode="I,I,I"/>
						
						<layout:datagridColumn property="budgtUnit" title="Units" mode="I,I,I"/>
						<layout:datagridColumn property="estFinanceBudgt" title="Financial(Budgt)" mode="I,I,I"/>
					</layout:datagrid>
					</div>
				</td>
				<td>
					<img id="budg_subm1" src="images/add.png" 
					onclick="return checkField();" width="20px"/><br>
					<img id="emp_hist2" src="images/delete.png" 
					onclick="StrutsLayout.setDatagridLineState('budgetSubmissionDatagrid', 'removed')" 
					width="20px"/>
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
	//triggerEvent(document.getElementById('componentId'), 'onchange');
	//triggerEvent(document.getElementById('subComponentId'), 'onchange');
	</script>
	<script language="javascript">
	function checkField(){
		var activityId = document.getElementById('activityId').value;
		var estPhysicalBudgt = document.getElementById('estPhysicalBudgt').value;
		var budgtUnit = document.getElementById('budgtUnit').value;
		var estFinanceBudgt = document.getElementById('estFinanceBudgt').value;
		
				if(activityId == ''){
					alert("Please Select Activity Id");
					return false;
				}
				else if(estPhysicalBudgt == ''){
					alert("Please Enter Estimate Physical Budget");
					return false;
				}
				else if(budgtUnit == ''){
					alert("Please Enter Budget Unit");
					return false;
				}
				else if(estFinanceBudgt == ''){
					alert("Please Enter Financial Budget");
					return false;
				}
				else{
					StrutsLayout.addDatagridLine('budgetSubmissionDatagrid','componentId~subComponentId~activityId~codeHeadId~estPhysicalBudgt~budgtUnit~estFinanceBudgt');
					return true;
				}
				
	}
	</script>
	
</html:form>
</html:html>

 