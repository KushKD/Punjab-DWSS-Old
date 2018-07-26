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
<link rel="stylesheet" href="css/tab_pane.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/calendar.css">
<script type="text/javascript">
hide_ctrl('FD',true);
hide_ctrl('tabbed_box_1',false);
		function de_find(){		
			document.releaseLOCForm.action="releaseLOCAction.do?method=find&d__mode="+d__mode+"&menuId=FIN006";
			document.releaseLOCForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.releaseLOCForm.action="releaseLOCAction.do?method=update&d__mode="+d__mode+"&menuId=FIN006";
				document.releaseLOCForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.releaseLOCForm.action="releaseLOCAction.do?method=delete&d__mode="+d__mode+"&menuId=FIN006";
				document.releaseLOCForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.releaseLOCForm.action="releaseLOCAction.do?method=post&d__mode="+d__mode+"&menuId=FIN006";
				document.releaseLOCForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.releaseLOCForm.action="releaseLOCAction.do?method=save&d__mode="+d__mode+"&menuId=FIN006";
				document.releaseLOCForm.submit();
			}
		}
		function ifFD(){
			var locationId=document.getElementById("locationId").value;
			var requestFromLocationId=document.getElementById("requestFromLocationId").value;
			var listLocationId="FD,WB,GOI,GOP";
			var listRequestFromLocationId="FD";

			if(locationId!=requestFromLocationId && (inList(locationId,listLocationId)||inList(requestFromLocationId,listRequestFromLocationId))){
				hide_ctrl('FD',false);
				hide_ctrl('tabbed_box_1',true);
			}else{
				hide_ctrl('FD',true);
				hide_ctrl('tabbed_box_1',false);
			}
		}
		</script>

<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />

</head>
<html:html>
<body bgcolor="#6699FF">

<html:form action="releaseLOCAction" method="post">

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
				
	<fieldset><legend>Release of LOC </legend>
	<center>
	<table>

		<tr>

			<td><label>Project </label></td>
			<td colspan="3"><html:select property="programId" styleId="programId"
				styleClass="cs3" style="width:585" onchange="ajaxFunction('releaseLOCAction.do?programId='+this.value+'&method=fetchLocationId', 'locationId');triggerEvent(document.getElementById('locationId'), 'onchange');">
				<html:option value="">Select Project</html:option>
				<html:options collection="programIds" labelProperty="label"	property="value" />
			</html:select></td>
		</tr>
		<tr>
			<td><label>Release By </label></td>
			<td colspan="3"><html:select property="locationId" styleId="locationId"
				styleClass="cs3" style="width:585" 
				onchange="ifFD();ajaxFunction('releaseLOCAction.do?locationId='+this.value+'&programId='+document.getElementById('programId').value+'&method=fetchRequestFromLocationId', 'requestFromLocationId');triggerEvent(document.getElementById('requestFromLocationId'), 'onchange');">
				<!--<html:option value="">Select Location</html:option>
				<html:options collection="userLocations" labelProperty="label" 	property="value" />-->
			</html:select>
			
			</td>

		</tr>
		<tr>

			<td><label>LOC Request From </label></td>
			<td colspan="3"><html:select property="requestFromLocationId" styleId="requestFromLocationId"
				styleClass="cs3" style="width:585" onchange="ifFD();ajaxFunction('releaseLOCAction.do?requestFromLocationId='+this.value+'&locationId='+document.getElementById('locationId').value+'&programId='+document.getElementById('programId').value+'&method=fetchLOCRequestNo', 'locRequestNo');triggerEvent(document.getElementById('locRequestNo'), 'onchange');">
				
				</html:select>
				</td>

		</tr>
		<tr>
			<td><label>LOC Request Number</label></td>
			<td colspan="3">
			<html:select property="locRequestNo" styleId="locRequestNo"
				styleClass="cs3" style="width:585" >
				
				</html:select></td>
		</tr>
		<tr>
			<td><label>Request Date</label></td>
			<td><html:text property="requestDate" styleId="requestDate"	styleClass="cs1" />	</td>
			<td><label>LOC(Release) Date</label></td>
			<td><html:text property="locDate" styleId="locDate"	styleClass="cs1" /></td>
		</tr>
		<tr id="FD">
			<td><label>Amount Requested &nbsp;&nbsp;(in Rs.)</label></td>
			<td ><html:text property="amountFDRequest" styleId="amountFDRequest" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')" /></td>
			<td><label>Amount Released to &nbsp;&nbsp;(FD/SPMC)&nbsp;&nbsp;(in Rs.)</label></td>
			<td><html:text property="amountFDRelease" styleId="amountFDRelease" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"/></td>
		</tr>
		</table>
	</center>
	
	</fieldset>
	<!--<fieldset><legend>Release LOC Component C Details</legend>-->
	

 <div id="tabbed_box_1" class="tabbed_box">  
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font><b>*Please Provide Audit Completed Date In Specified Format --> DD-MM-YYYY<b></font>
    <div class="tabbed_area"> 	
	<ul class="tabs">  
    <li><a href="javascript:tabSwitch_2(1, 2, 'tab_', 'content_');" id="tab_1" class="active">Release Detail Component C</a></li>  
    <li><a href="javascript:tabSwitch_2(2, 2, 'tab_', 'content_');" id="tab_2">Release Detail Component A & B</a></li>
</ul>
<div id="content_1" class="content">
	<center>
	<table>
			<tr>
				<td>
				<div class="divgrid">
					<layout:datagrid styleClass="DATAGRID" property="releaselocDatagrid" selectionAllowed="true" multipleSelectionAllowed="false" model="datagrid">
						<layout:datagridColumn property="id" title="Id" mode="N,N,N"/>
						<layout:datagridColumn property="schemeId" title="Scheme Code" mode="I,I,I"/>
						<layout:datagridColumn property="schemeName" title="Scheme Name" mode="I,I,I"/>
						<layout:datagridColumn property="locFor" title="Loc Requested For" mode="I,I,I" />
						<layout:datagridColumn property="auditCompletedDate" title="Audit Completed Date"/>
						<layout:datagridColumn property="auditedAmount" title="Audited Amount"/>
						<layout:datagridColumn property="amount" title="Requested Amount" mode="I,I,I"/>
						<layout:datagridColumn property="releaseAmount" title="Release Amount"/>
					</layout:datagrid>
					</div>
				</td>
			</tr>
		</table>
	</center>
	<!--</fieldset>-->
	</div>
	 <div id="content_2" class="content">
	<!--<fieldset><legend>Release LOC Component A & B Details</legend>
	--><center>
	<table>
	    	<tr>
				<td>
				<div class="divgrid">
					<layout:datagrid styleClass="DATAGRID" property="locActivityDatagrid" selectionAllowed="true" multipleSelectionAllowed="false" model="datagrid" styleId="locActivityDatagridId">
						<layout:datagridColumn property="componentId" title="Component Code" mode="I,I,I"/>
						<layout:datagridColumn property="subComponentId" title="Sub-Component Code" mode="I,I,I"/>
						<layout:datagridColumn property="activityId" title="Activity Code" mode="I,I,I"/>
						<layout:datagridColumn property="requestedAmount" title="Request Amount(Rs)"mode="I,I,I"/>
						<layout:datagridColumn property="releaseAmount" title="Release Amount(Rs)"/>
					</layout:datagrid>
					</div>
				</td>
			</tr>
		</table>
	</center>
	</div>
	</div>
	</div>
	
	<script>
	
	triggerEvent(document.getElementById('programId'), 'onchange');
	<%if(MisUtility.ifEmpty(request.getAttribute("locationId"))){%>
		document.getElementById("locationId").value="<%=request.getAttribute("locationId")%>";
		triggerEvent(document.getElementById('locationId'), 'onchange');
		//alert("locationId:"+document.getElementById("locationId").value);
	<%}%>	
	<%if(MisUtility.ifEmpty(request.getAttribute("requestFromLocationId"))){%>
		document.getElementById("requestFromLocationId").value="<%=request.getAttribute("requestFromLocationId")%>";
	triggerEvent(document.getElementById('requestFromLocationId'), 'onchange');
	//alert("requestFromLocationId:"+document.getElementById("requestFromLocationId").value);
	<%}%>	
	 <%if(MisUtility.ifEmpty(request.getAttribute("locId"))){%>
	 //alert("1: "+document.getElementById("locRequestNo").value);	
	 document.getElementById("locRequestNo").value="<%=request.getAttribute("locId")%>";
	//alert("2: "+document.getElementById("locRequestNo").value);
		<%}%>
	</script>
	
</html:form>
</html:html>