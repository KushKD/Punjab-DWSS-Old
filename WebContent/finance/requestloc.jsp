<%@page import="com.prwss.mis.common.util.MISConstants"%>
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
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
--><link rel="stylesheet" href="css/calendar.css">
<script type="text/javascript">
hide_ctrl('FD',true);
hide_ctrl('tabbed_box_1',false);
		function de_find(){		
			document.requestLOCForm.action="requestLOCAction.do?method=find&d__mode="+d__mode+"&menuId=FIN005";
			document.requestLOCForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.requestLOCForm.action="requestLOCAction.do?method=update&d__mode="+d__mode+"&menuId=FIN005";
				document.requestLOCForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.requestLOCForm.action="requestLOCAction.do?method=delete&d__mode="+d__mode+"&menuId=FIN005";
				document.requestLOCForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.requestLOCForm.action="requestLOCAction.do?method=post&d__mode="+d__mode+"&menuId=FIN005";
				document.requestLOCForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.requestLOCForm.action="requestLOCAction.do?method=save&d__mode="+d__mode+"&menuId=FIN005";
				document.requestLOCForm.submit();
			}
		}
		
		function cal_compoc(){
			
			document.requestLOCForm.componentC.value = parseInt(document.requestLOCForm.componentC.value)+parseInt(document.requestLOCForm.amount.value);
		}
		function ifFD(){
			var requestToLocationId=document.getElementById("requestToLocationId").value;
			var locationId=document.getElementById("locationId").value;
			var listLocationId="FD";
			var listRequestToLocationId="FD,WB,GOI,GOP";
			if(inList(requestToLocationId,listRequestToLocationId) || inList(locationId,listLocationId)){
				hide_ctrl('FD',false);
				hide_ctrl('tabbed_box_1',true);
			}else{
				hide_ctrl('FD',true);
				hide_ctrl('tabbed_box_1',false);
			}
		}
	
	
		</script>
<link rel="stylesheet" href="css/tab_pane.css" type="text/css" media="screen" />
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>
<html:html>
<body bgcolor="#6699FF">

<html:form action="requestLOCAction" method="post">

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
	<fieldset><center><label style="font-size:20px;font-family:times;">Data pertaining to this screen   should be entered by 10th of following month</label></center></fieldset>
				
	<fieldset><legend>Request for LOC </legend>
	<center>
	<table>

		<tr>
		<td ><label>Location</label></td>
			<td colspan="3"><html:select property="locationId" styleId="locationId"
				styleClass="cs3" style="width:585"  onchange="ifFD();ajaxFunction('requestLOCAction.do?locationId='+this.value+'&method=fetchRequestToLocationId','requestToLocationId');ajaxFunction('requestLOCAction.do?locationId='+document.getElementById('locationId').value+'&method=fetchRequestLocId', 'locRequestNo');ajaxFunction('GetFilterValues.to?locationId='+document.getElementById('locationId').value, 'blockId');triggerEvent(document.getElementById('blockId'), 'onchange');">
				<html:option value="">Select </html:option>
				
				<html:options collection="userLocations" labelProperty="label"
					property="value" />
			</html:select></td>
		</tr>
			<tr>
			<td><label>LOC Request to</label></td>
			<td colspan="3"><html:select property="requestToLocationId" styleId="requestToLocationId"
				styleClass="cs3" style="width:585" onchange="ifFD();">				
			</html:select></td>

		</tr>
		
		<tr>
			<td><label>Request Date</label></td>
			<td><html:text property="requestDate" styleId="requestDate"
				styleClass="ci5" /><!--<input class=ci4 type=button onclick="c1.innerpopup('requestDate','calendar_frame');" value="..." styleClass="ci3"/>--></td>
			<td><label>Project </label></td>
			<td><html:select property="programId" styleId="programId"
				styleClass="cs1" >
				<html:option value="">Select</html:option>
				<html:options collection="programIds" labelProperty="label"
					property="value" />
			</html:select></td>

		</tr>		
		<tr >		
			<td><label>LOC Request Number</label></td>
			
				
				<td colspan="3"><html:select property="locRequestNo" styleId="locRequestNo"
				styleClass="cs3" style="width:585" >
			</html:select></td>
		</tr>
		<tr id="FD">
			<td colspan="2"><label>Amount Requested to &nbsp;&nbsp;(FD/GOI/GOP/WB)&nbsp;&nbsp;(in Rs.)</label></td>
			<td colspan="2"><html:text property="amountFD" styleId="amountFD" styleClass="ci5" value="0" onkeypress="return validateKey(event,	this,'9@20@2')"/></td>
		</tr>
		
					<!--
		<tr>
			<td><label>Component A </label></td>
			<td><html:text property="componentA" styleId="componentA"
				styleClass="ci5" /></td>


			<td><label>Component B </label></td>
			<td><html:text property="componentB" styleId="componentB"
				styleClass="ci5" /></td>
		</tr>
		--><!--<tr>
			<td><label>Component C </label></td>
			<td><html:text property="componentC" styleId="componentC"
				styleClass="ci5"  disabled="true"  /></td>

		</tr>
	--></table>
	</center>
	
	</fieldset>
<div id="tabbed_box_1" class="tabbed_box">  
    <div class="tabbed_area"> 	
	<ul class="tabs">  
    <li><a href="javascript:tabSwitch_2(1, 2, 'tab_', 'content_');" id="tab_1" class="active">Component C</a></li>  
    <li><a href="javascript:tabSwitch_2(2, 2, 'tab_', 'content_');" id="tab_2">Component A & B</a></li>
</ul>
<div id="content_1" class="content">
	<!-- <fieldset><legend>Scheme Based Amount</legend>-->
	<center>
	<font><b>Request Amount for Component C</b></font>
	<br>
	<br>
	<table>
	<tr>
		<td><label>Select Block</label></td>
			<td><html:select property="blockId" styleId="blockId" styleClass="cs1" style="width: 195px"
				onchange="ajaxFunction('GetSchemeFilterValues.to?blockId='+this.value, 'villageId');">
				<html:option value="Select Block"></html:option>
			</html:select></td>

			<td><label>Select Village</label></td>
			<td colspan="2"><html:select property="villageId" styleId="villageId" styleClass="cs1" style="width: 195px"
				onchange="ajaxFunction('requestLOCAction.do?villageId='+this.value+'&programId='+document.getElementById('programId').value+'&method=fetchSchemeIds', 'schemeId');">
				<html:option value="Select Village"></html:option>
			</html:select></td>
	
		<td><label>Scheme </label></td>
		<td><html:select property="schemeId" styleId="schemeId" styleClass="cs1" style="width:400px"
		onchange="ajaxFunctionForText('procSubPlanAction.do?schemeId='+this.value+'&method=fetchSchemeName', 'schname');">
		</html:select>
		</td>
		</tr>
		
		<tr>
			<td><label>Amount</label></td>
			<td><input type = "text" id="amount"   class="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"/></td>
			<td><label>Request Loc For</label></td>
			<td><select id="reqLocFor">
						<option value="">Select</option>
						<option value="<%=MISConstants.LOC_FOR_INSTL_1%>" >Installment - 1</option>
						<option value="<%=MISConstants.LOC_FOR_INSTL_2%>" >Installment - 2</option>
						<option value="<%=MISConstants.LOC_FOR_INSTL_3%>" >Installment - 3</option>
						<option value="<%=MISConstants.LOC_FOR_GAP_FUND%>">Gap Funds</option>
						</select></td>
		</tr>
		<tr> <td><input type="hidden" id="schname"></td></tr>
	</table>
	<table>
			<tr>
				<td>
				<div class="divgrid">
					<layout:datagrid styleClass="DATAGRID" property="requestlocDatagrid" selectionAllowed="true" multipleSelectionAllowed="false" model="datagrid" styleId="datagridId">
						<layout:datagridColumn property="schemeId" title="Scheme" mode="I,I,I"/>
						<layout:datagridColumn property="schemeName" title="Scheme Name" mode="I,I,I"/>
						<layout:datagridColumn property="amount" title="Amount"/>
						<layout:datagridColumn property="locFor" title="Request Loc For" mode="I,I,I"/>
						
					</layout:datagrid>
					</div>
				</td>
				<td>
					<img id="emp_hist1" src="images/add.png" 
					onclick="StrutsLayout.addDatagridLine('requestlocDatagrid','schemeId~schname~amount~reqLocFor')" width="20px"/><br>
					<img id="emp_hist2" src="images/delete.png" 
					onclick="StrutsLayout.setDatagridLineState('requestlocDatagrid', 'removed')" 
					width="20px"/>
				</td>
			</tr>
		</table>
	</center>
	</div>
	<div id="content_2" class="content">
	<center><font><b>Request Amount for Component A & B Activity wise</b></font></center>
	<br>
	<table>
	<tr>
	<td><label>Component</label></td>
		<td><html:select property="componentId" styleId="componentId" styleClass="cs1" style="width:500px;" onchange="ajaxFunction('requestLOCAction.do?componentId='+this.value+'&method=fetchSubComponent', 'subComponentId'); triggerEvent(document.getElementById('subComponentId'), 'onchange');"> 
		<html:option value="">Select</html:option>
		<html:options collection="componentIds" labelProperty="label" property="value"/>
		</html:select>
		</td>
		</tr>
		<tr>
		<td><label>Sub Component</label></td>
		<td><html:select property="subComponentId" styleId="subComponentId" style="width:500px;" styleClass="cs1" onchange="ajaxFunction('requestLOCAction.do?subComponentId='+this.value+'&method=fetchActivity', 'activityId'); ">
		</html:select>
		</td>
		</tr>
	<tr>
		<td><label>Activity</label></td>
		<td><html:select property="activityId" styleId="activityId" styleClass="cs1" style="width:500px;"> </html:select></td>
	</tr>
	<tr>
	<td><label>Request Amount</label></td>
	<td><input type="text" id="requestAmountId" value="0" onkeypress="return validateKey(event,	this,'9@20@2')"></td>
	</tr>
	</table>
	<table>
			<tr>
				<td>
				<div class="divgrid">
					<layout:datagrid styleClass="DATAGRID" property="locActivityDatagrid" selectionAllowed="true" multipleSelectionAllowed="false" model="datagrid" styleId="locActivityDatagridId">
						<layout:datagridColumn property="componentId" title="Component Code" mode="I,I,I"/>
						<layout:datagridColumn property="subComponentId" title="Sub-Component Code" mode="I,I,I"/>
						<layout:datagridColumn property="activityId" title="Activity Code" mode="I,I,I"/>
						<layout:datagridColumn property="requestedAmount" title="Request Amount" />
					</layout:datagrid>
					</div>
				</td>
				<td>
					<img id="emp_hist1" src="images/add.png" 
					onclick="StrutsLayout.addDatagridLine('locActivityDatagrid','componentId~subComponentId~activityId~requestAmountId')" width="20px"/><br>
					<img id="emp_hist2" src="images/delete.png" 
					onclick="StrutsLayout.setDatagridLineState('locActivityDatagrid', 'removed')" 
					width="20px"/>
				</td>
			</tr>
		</table>
	</div>
	</div>
	</div>
	<!--  </fieldset>-->
	</html:form>
	<script>
	
	triggerEvent(document.getElementById('locationId'), 'onchange');
	
	 <%if(MisUtility.ifEmpty(request.getAttribute("requestToLocationId"))){%>
		document.getElementById("requestToLocationId").value="<%=request.getAttribute("requestToLocationId")%>";
		triggerEvent(document.getElementById('requestToLocationId'), 'onchange');
		<%}%>
	 <%if(MisUtility.ifEmpty(request.getAttribute("locId"))){%>
		document.getElementById("locRequestNo").value="<%=request.getAttribute("locId")%>";
		<%}%>
		
	</script>
	

</html:html>