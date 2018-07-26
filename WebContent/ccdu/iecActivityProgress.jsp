<%@page import="com.prwss.mis.common.util.MisUtility"%>
<%@page autoFlush="true" language="java"
	pageEncoding="ISO-8859-1" contentType="text/html; charset=ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.application-servers.com/layout" prefix="layout"%>
<html:html>

<head>
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
--><script type="text/javascript">
function de_find() {
		document.iecActivityProgressForm.action = "iecActivityProgressAction.do?method=find&d__mode="+d__mode+"&menuId=CCDU002";
		document.iecActivityProgressForm.submit();
	}
	function de_modify() {
		if (d__mode == 'ent_modify') {
			document.iecActivityProgressForm.action = "iecActivityProgressAction.do?method=update&d__mode="+d__mode+"&menuId=CCDU002";
			document.iecActivityProgressForm.submit();
		}
	}
	function de_remove() {
		if (d__mode == 'ent_delete') {
			document.iecActivityProgressForm.action = "iecActivityProgressAction.do?method=delete&d__mode="+d__mode+"&menuId=CCDU002";
			document.iecActivityProgressForm.submit();
		}
	}
	function de_add() {
		if (d__mode == 'ent_add') {
			document.iecActivityProgressForm.action = "iecActivityProgressAction.do?method=save&d__mode="+d__mode+"&menuId=CCDU002";
			document.iecActivityProgressForm.submit();
		}
	}
	function de_confirm() {
		if (d__mode == 'ent_post') {
			document.iecActivityProgressForm.action = "iecActivityProgressAction.do?method=post&d__mode="+ d__mode+"&menuId=CCDU002";
			document.iecActivityProgressForm.submit();
		}
	}

</script>
<script type="text/javascript" src="js/calendarDateInput.js"></script>
<script type="text/javascript" src="js/StrutsLayout.js" ></script>
<link href="css/layout.css" rel="stylesheet" type="text/css">
<link href="css/form2.css" rel="stylesheet" type="text/css">

</head>
<body>
<html:form action="iecActivityProgressAction" method="POST" styleId="iecActivityProgressForm">
	<logic:messagesPresent >
					<div id="errorDiv" class="error displaynone" style="width: 470px%; margin-bottom: 7px; height: 13px;">
 						<html:errors bundle="ccdu"/>   
					</div>
				</logic:messagesPresent >
				<logic:messagesPresent message="true">
					<div id="successDiv" class="success diplaynone" style="width: 470px;">
						<html:messages id="message" bundle="ccdu" message="true">
       						<li><bean:write name="message" /></li>
   						</html:messages>
					</div>
				</logic:messagesPresent >
	<fieldset><center><label style="font-size:20px;font-family:times;">Data pertaining to this screen should be entered by 7th of following month</label></center></fieldset>
	
	
	<%@include file="../JSPF/blockVillageFilter.jspf"%>
	<fieldset><legend>IEC Activity Progress</legend>
	<center>
			<script language="javascript">
	obj2 = document.getElementById("blockId");
	obj2.onchange = function(){
	ajaxFunction('GetFilterValues.to?blockId='+this.value, 'villageId');
	triggerEvent(document.getElementById('villageId'), 'onchange');
	};
	obj2.onchange();
	</script>
		<script language="javascript">
	obj3 = document.getElementById("villageId");
	obj3.onchange = function(){
	ajaxFunction('iecActivityProgressAction.do?villageId='+this.value+'&method=fetchTrasanctionId', 'iecProgressId');
	};
	obj3.onchange();

	</script>
	<table>
		<tr>
			<td><label>IEC Activity</label></td>
			<td><html:select property="iecActivityId"  style="width:275px;" styleClass="cs1">
				<html:option value="">Select</html:option>
				<html:options collection="iecActivities" labelProperty="label" property="value"></html:options>.
			</html:select></td>
			<td><label>Select IEC Progress ID</label></td>
			<td><html:select property="iecProgressId" styleId="iecProgressId" style="width:155px;" styleClass="cs1">
			</html:select></td>
			
		</tr>
		<tr>
			<td><label>Date of Activity (DD-MM-YYYY)</label></td>
			<td><html:text property="dateOfActivity" styleClass="ci3" readonly="readonly" styleId="DateOfActivityId" ></html:text>
			<!--<input class=ci4 type=button onclick="c1.innerpopup('dateOfActivityProgressId','calendar_frame');" value="..."/>-->
			</td>
			
			
			
			<td nowrap><label>Venue of Activity</label></td>
			<td><html:text property="venueOfActivity" styleClass="ci5" onkeypress="return charKey(event);"></html:text></td>

		</tr>
		<tr>
			<td nowrap><label>Number of Participants</label></td>
			<td><html:text property="numberOfParticipants" styleClass="ci5" onkeypress="return validateKey(event,this,'9@20@2')"></html:text></td>

			<td nowrap><label>Remarks</label></td>
			<td><html:text property="remarks" styleClass="ci5" ></html:text></td>
		</tr>
		<tr>
		<td><label>Expenditure(In Rs.)</label></td>
		<td><html:text property="expenditure" style="width:155px;"  onkeypress="return validateKey(event,this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td><label>Outcomes</label></td>
			
			<td colspan="3"><html:textarea property="outcomes" cols="60" styleClass="ci5"></html:textarea></td>
		</tr>
		</table>
	</center>
	</fieldset>
	<fieldset><legend>Material Utilization</legend>
	<table>
	<tr>
	<td><label>Store</label></td>
	<td><html:select property="stores" style="width:270px;" styleClass="cs1" styleId="storeId" onchange="ajaxFunction('iecActivityProgressAction.do?method=fetchItem&storeId='+this.value+'&itemGroupId='+document.getElementById('itemGroupId').value, 'itemId');">
	<html:option value="">Select</html:option>
	<html:options collection="allStores" labelProperty="label" property="value"></html:options>
	</html:select></td>
	<td><label>Item Group</label></td>
	<td><html:select property="itemGroup" style="width:270px;" styleClass="cs1" styleId="itemGroupId" onchange="ajaxFunction('iecActivityProgressAction.do?method=fetchItem&itemGroupId='+this.value+'&storeId='+document.getElementById('storeId').value, 'itemId');">
	<html:option value="">Select</html:option>
	<html:options collection="itemGroups" labelProperty="label" property="value"></html:options>
	</html:select></td>
	<td>
	<label>Material</label>
	</td>
	<td><select id="itemId" Class="cs1"></select></td>
	<td>
	<label>Quantity Distributed</label>
	</td>
	<td><input type="text" id="quantityId" Class="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"/></td>
	</tr>	
	</table>
	<table>
			<tr>
				<td>
					<div class="divgrid">
					<layout:datagrid styleClass="DATAGRID" property="materialUtilizationDatagrid" selectionAllowed="true" multipleSelectionAllowed="false" model="datagrid" >
						<layout:datagridColumn property="itemId" title="Material ID"/>
						<layout:datagridColumn property="quantity" title="Quantity Distributed"></layout:datagridColumn>
					</layout:datagrid>
					</div>
				</td>
				<td>
					<img src="images/add.png" onclick="StrutsLayout.addDatagridLine('materialUtilizationDatagrid','itemId~quantityId')" width="20px"/><br>
					<img src="images/delete.png" 
					onclick="StrutsLayout.setDatagridLineState('materialUtilizationDatagrid', 'removed')" 
					width="20px"/>
				</td>
			</tr>
		</table>
	</fieldset>
</html:form>
<script language="javascript">
triggerEvent(document.getElementById('storeId'), 'onchange');
triggerEvent(document.getElementById('locationId'), 'onchange');
document.getElementById("locationId").value="<%=request.getAttribute("locationId")%>";
triggerEvent(document.getElementById('locationId'), 'onchange');
document.getElementById("blockId").value="<%=request.getAttribute("blockId")%>";
triggerEvent(document.getElementById('blockId'), 'onchange');
document.getElementById("villageId").value="<%=request.getAttribute("villageId")%>";
triggerEvent(document.getElementById('villageId'), 'onchange');
document.getElementById("iecProgressId").value="<%=request.getAttribute("iecProgressId")%>";

</script>
</body>
</html:html> 