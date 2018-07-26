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
		document.cbTrainingProgressForm.action = "cbTrainingProgressAction.do?method=find&d__mode="+ d__mode+"&menuId=CCDU001";
		document.cbTrainingProgressForm.submit();
	}
	function de_modify() {
		if (d__mode == 'ent_modify') {
			document.cbTrainingProgressForm.action = "cbTrainingProgressAction.do?method=update&d__mode="+ d__mode+"&menuId=CCDU001";
			document.cbTrainingProgressForm.submit();
		}
	}
	function de_remove() {
		if (d__mode == 'ent_delete') {
			document.cbTrainingProgressForm.action = "cbTrainingProgressAction.do?method=delete&d__mode="+ d__mode+"&menuId=CCDU001";
			document.cbTrainingProgressForm.submit();
		}
	}
	function de_confirm() {
		if (d__mode == 'ent_post') {
			document.cbTrainingProgressForm.action = "cbTrainingProgressAction.do?method=post&d__mode="+ d__mode+"&menuId=CCDU001";
			document.cbTrainingProgressForm.submit();
		}
	}
	function de_add() {
		if (d__mode == 'ent_add') {
			document.cbTrainingProgressForm.action = "cbTrainingProgressAction.do?method=save&d__mode="+ d__mode+"&menuId=CCDU001";
			document.cbTrainingProgressForm.submit();
		}

	}
</script>
<script type="text/javascript" src="js/calendarDateInput.js"></script>
<script type="text/javascript" src="js/StrutsLayout.js" ></script>
<link href="css/layout.css" rel="stylesheet" type="text/css">
<link href="css/form2.css" rel="stylesheet" type="text/css">

</head>
<body>
<html:form action="cbTrainingProgressAction" method="POST" styleId="cbTrainingProgressForm">
	 
	
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
				
	<%@include file="../JSPF/blockVillageFilter.jspf" %>
	<fieldset><legend>Capacity Building Training Progress</legend> 
	<center>
	<script language="javascript">
	obj = document.getElementById("locationId");
	obj.onchange = function(){
	ajaxFunction('GetFilterValues.to?locationId='+this.value, 'blockId');
	triggerEvent(document.getElementById('blockId'), 'onchange');
	triggerEvent(document.getElementById('planId'), 'onchange');
	ajaxFunction('cbTrainingProgressAction.do?locationId='+this.value+'&method=fetchTrainingOfficers', 'officerId');
	//if((document.getElementById('blockId').value=='')&&(document.getElementById('villageId').value=='null')){
		//alert("Block");
		//ajaxFunction('cbTrainingProgressAction.do?locationId='+this.value+'&method=fetchTrasanctionIdForLocation', 'trasanctionId');
		//triggerEvent(document.getElementById('trasanctionId'), 'onchange');
	//}
	};
	obj.onchange();
	</script>
	<script language="javascript">
	obj2 = document.getElementById("blockId");
	obj2.onchange = function(){
	ajaxFunction('GetFilterValues.to?blockId='+this.value, 'villageId');
	triggerEvent(document.getElementById('villageId'), 'onchange');
		//if(document.getElementById('villageId').value==''){
			//alert("Block");
			//ajaxFunction('cbTrainingProgressAction.do?blockId='+this.value+'&method=fetchTrasanctionIdForBlock', 'trasanctionId');
			//triggerEvent(document.getElementById('trasanctionId'), 'onchange');
		//}
	
	};
	obj2.onchange();
	</script>
	<table>	
		<tr>
			<td><label>Select Level of Training</label></td>
			<td><html:select property="levelOfTraining" styleId="levelOfTraining" styleClass="cs1" style="width:400px;" onchange="ajaxFunction('cbTrainingProgressAction.do?levelOfTraining='+this.value+'&locationId='+document.getElementById('locationId').value+'&blockId='+document.getElementById('blockId').value+'&villageId='+document.getElementById('villageId').value+'&method=fetchTrasanctionId', 'trasanctionId');triggerEvent(document.getElementById('trasanctionId'), 'onchange');">
			<html:option value="Not Specified">Select</html:option>
			<html:option value="State Level">State Level</html:option>
			<html:option value="District Level">District Level</html:option>
			<html:option value="Block Level">Block Level</html:option>
			<html:option value="Village Level">Village Level</html:option>
			</html:select></td>
		</tr>
		<tr>
			<td><label>Select Progress</label></td>
			<td><html:select property="cbProgressId" styleId="trasanctionId"
				style="width:400px;" styleClass="ci5">
			</html:select></td>


			<td nowrap><label>Phase of Village</label></td>
			<td><html:text property="phaseOfVillage" style="width:250px;" styleClass="ci5" onkeypress="return charKey(event);"></html:text></td>

		</tr>
		<tr>
			<td><label>Select Plan</label></td>
			<td><html:select property="planId" style="width:400px;" styleClass="cs1" styleId="planId" onchange="ajaxFunction('cbTrainingProgressAction.do?planId='+this.value+'&method=fetchTrainingsByPlan', 'trainingId');triggerEvent(document.getElementById('trainingId'), 'onchange');">
				<html:option value="">Select</html:option>
				<html:options collection="plans" labelProperty="label" property="value"></html:options>
			</html:select></td>
			<td><label>Select Training</label></td>
			<td><html:select property="trainingId" style="width:250px;" styleId="trainingId" styleClass="cs1">
			<html:option value="">Select </html:option>
			</html:select></td>
		
		</tr>
		<tr>
			<td><label>Date of Training</label></td>
			<td><html:text property="dateOfTraining" style="width:400px;" styleClass="ci3" styleId="DateOfTrainingId" readonly="readonly"></html:text>
			<!--<input class=ci4 type=button onclick="c1.innerpopup('dateOfTrainingId','calendar_frame');" value="..."/>-->
			</td>
			<td><label>Total Participants</label></td>
			<td><html:text property="totalParticipants" style="width:250px;" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		
		<tr>
			<td><label>Issues Emerged</label></td>
			<td><html:select property="issues" styleClass="cs3">
			<html:option value="Not Specified">Select</html:option>
			<html:options collection="allIssues" labelProperty="label" property="value"></html:options>
			</html:select></td>
			<td nowrap><label>If other please specify</label></td>
			<td><html:text property="otherIssue" style="width:250px;" styleClass="ci5" onkeypress="return charKey(event);"></html:text></td>
		</tr>
		
		<tr>
			<td><label>Recommendations of Training</label></td>
			<td style="height: 10px;"><html:select property="recommendationsOfWssDpmc" styleClass="cs3">
				<html:option value="Not Specified">Select</html:option>
				<html:option value="Door to Door Visit Required">Door to Door Visit Required</html:option>
				<html:option value="FGD required with opposition group of GP">FGD required with opposition group of GP</html:option>
				<html:option value="IEC activities/campaign is required">IEC activities/campaign is required</html:option>
				<html:option value="Gram Sabha meeting is required">Gram Sabha meeting is required</html:option>
				<html:option value="Consistent monitoring by DPMC-RWS required">Consistent monitoring by DPMC/RWS required</html:option>
				<html:option
					value="Legal action is required to check illegal connections and use of tullu pump">Legal action is required to check illegal connections and use of tullu pump</html:option>
				<html:option value="Door to Door Visit Required">Other</html:option>
			</html:select></td>

		
			<td><label>If other please specify</label></td>
			<td><html:text property="otherRecommendation" style="width:250px;" styleClass="ci5" onkeypress="return charKey(event);"></html:text></td>
		</tr>
		
		<tr>
			<td><label>Action by Whom</label></td>
			<td style="height: 10px;"><html:select
				property="actionByWhom" styleClass="cs3">
				<html:option value="Not Specified">Select</html:option>
				<html:option value="SE cum DPD">SE cum DPD</html:option>
				<html:option value="XEN RWS">XEN RWS</html:option>
				<html:option value="XEN DPMC">XEN DPMC</html:option>
				<html:option value="HRD SPECIALIST">HRD Specialist</html:option>
				<html:option value="IEC SPECIALIST">IEC Specialist</html:option>
				<html:option value="SDE">SDE</html:option>
				<html:option value="JE">JE</html:option>
				<html:option value="FO">FO</html:option>
				<html:option value="Team of RWS officials">Team of RWS officials</html:option>
				<html:option value="Team of DPMC">Team of DPMC</html:option>
				<html:option value="Action by SPMC">Action by SPMC</html:option>
			</html:select></td>

			<td><label>Action by When(Date)</label></td>
			<td><html:text property="actionByWhen" style="width:250px;" styleClass="ci5" styleId="actionByWhenIdDate" readonly="readonly"></html:text>
			<!--<input class=ci4 type=button onclick="c1.innerpopup('actionByWhenId','calendar_frame');" value="..."/>-->
			</td>
		</tr>
		
		<tr>
			<td><label>Outcome</label></td>
			<td><html:select property="outcome" styleClass="cs3">
				<html:option value="Not Specified">Select</html:option>
				<html:option value="Villagers agreed to take-up the scheme">Villagers agreed to take-up the scheme</html:option>
				<html:option value="Resolution received">Resolution received</html:option>
				<html:option value="Bank account opened">Bank account opened</html:option>
				<html:option value="DSR displayed in the village">DSR displayed in the village</html:option>
				<html:option
					value="Contractor agreed to replace the poor quality material">Contractor agreed to replace the poor quality material</html:option>
				<html:option
					value="IEC campaign launched to increase the number of connections">IEC campaign launched to increase the no. of connections</html:option>
				<html:option value="Record maintained properly by GPWSC">Record maintained properly by GPWSC</html:option>
				<html:option
					value="Illegal connections and tullu pumps removed by GPWSC">Illegal connections and tullu pumps removed by GPWSC</html:option>
				<html:option
					value="Rules and regulations of water supply formed and printed by GPWSC">Rules and regulations of water supply formed and printed by GPWSC</html:option>
				<html:option value="Regular tariff collection by GPWSC">Regular tariff collection by GPWSC</html:option>
				<html:option value="Leakages recified by the contractor">Leakages rectified by the contractor</html:option>
				<html:option
					value="Surplus revenue generated and deposited in the O&M account">Surplus revenue generated and deposited in the O&M account</html:option>
				<html:option value="Any other">Any other</html:option>
			</html:select></td>

		
			<td><label>If other please specify</label></td>
			<td><html:text property="otherOutcome" style="width:250px;" styleClass="ci5" onkeypress="return charKey(event);"></html:text></td>
		</tr>
		<tr>
		<td><label>Expenditure(In Rs.)</label></td>
		<td><html:text property="expenditure" style="width:250px;" onkeypress="return validateKey(event,this,'9@20@2')"></html:text></td>
		</tr>
			
		<tr>		
			<td><label>Remarks</label></td>
			<td colspan="3"><html:textarea property="remarks" cols="90" styleClass="ci5"></html:textarea></td>
		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>Training Officer's</legend>
	<table>
	<tr>
			<td colspan="1"><label>Select Training Officers</label></td>
			<td colspan="3"><html:select property="trainingOfficers" styleId="officerId" styleClass="cs2" 
			onchange="ajaxFunctionForText('cbTrainingProgressAction.do?officerId='+this.value+'&method=fetchOfficerName', 'employeeName')">
			</html:select></td>
			<td><input type="hidden" id="employeename">
		</tr>
	</table>
	
	<table>
			<tr>
				<td>
					<div class="divgrid">
					<layout:datagrid styleClass="DATAGRID" property="trainingOfficerDatagrid" selectionAllowed="true" multipleSelectionAllowed="false" model="datagrid" >
						<layout:datagridColumn property="employeeId" title="Employee Id"></layout:datagridColumn>
						<layout:datagridColumn property="employeeName" title="Employee Name"/>
					</layout:datagrid>
					</div>
				</td>
				<td>
					<img src="images/add.png" onclick="StrutsLayout.addDatagridLine('trainingOfficerDatagrid','officerId~employeename')" width="20px"/><br>
					<img src="images/delete.png" onclick="StrutsLayout.setDatagridLineState('trainingOfficerDatagrid', 'removed')" width="20px"/>
				</td>
			</tr>
		</table>
	</fieldset>
	
	<fieldset><legend>Material Utilization</legend>
	<table>
	<tr>
	<td><label>Store</label></td>
	<td><html:select property="stores"  style="width:270px;" styleClass="cs1" styleId="storeId" onchange="ajaxFunction('cbTrainingProgressAction.do?method=fetchItem&storeId='+this.value+'&itemGroupId='+document.getElementById('itemGroupId').value, 'itemId');">
	<html:option value="">Select</html:option>
	<html:options collection="allStores" labelProperty="label" property="value"></html:options>
	</html:select></td>
	<td><label>Item Group</label></td>
	<td><html:select property="itemGroup" style="width:270px;" styleClass="cs1" styleId="itemGroupId" onchange="ajaxFunction('cbTrainingProgressAction.do?method=fetchItem&itemGroupId='+this.value+'&storeId='+document.getElementById('storeId').value, 'itemId');">
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
					<layout:datagrid styleClass="DATAGRID" property="materialDatagrid" selectionAllowed="true" multipleSelectionAllowed="false" model="datagrid" >
						<layout:datagridColumn property="itemId" title="Material ID"/>
						<layout:datagridColumn property="quantity" title="Quantity Distributed"></layout:datagridColumn>
					</layout:datagrid>
					</div>
				</td>
				<td>
					<img src="images/add.png" onclick="StrutsLayout.addDatagridLine('materialDatagrid','itemId~quantityId')" width="20px"/><br>
					<img src="images/delete.png" onclick="StrutsLayout.setDatagridLineState('materialDatagrid', 'removed')" width="20px"/>
				</td>
			</tr>
		</table>
	</fieldset>
	<script language="javascript">
		triggerEvent(document.getElementById('storeId'), 'onchange');
		 <%if(MisUtility.ifEmpty(request.getAttribute("locationId"))){%>
			document.getElementById("locationId").value="<%=request.getAttribute("locationId")%>";
		<%}%>
		triggerEvent(document.getElementById('locationId'), 'onchange');
		 <%if(MisUtility.ifEmpty(request.getAttribute("blockId"))){%>
			document.getElementById("blockId").value="<%=request.getAttribute("blockId")%>";
		<%}%>
		triggerEvent(document.getElementById('blockId'), 'onchange');
		 <%if(MisUtility.ifEmpty(request.getAttribute("villageId"))){%>
			document.getElementById("villageId").value="<%=request.getAttribute("villageId")%>";
		<%}%>

		triggerEvent(document.getElementById('levelOfTraining'), 'onchange');
		 <%if(MisUtility.ifEmpty(request.getAttribute("trasanctionId"))){%>
			document.getElementById("trasanctionId").value="<%=request.getAttribute("trasanctionId")%>";
		<%}%>
		 <%if(MisUtility.ifEmpty(request.getAttribute("trainingId"))){%>
			document.getElementById("trainingId").value="<%=request.getAttribute("trainingId")%>";
		<%}%>
		<%//!String[] trainingOfficerIds = null; %>
		<%//if(MisUtility.ifEmpty(session.getAttribute("trainingOfficerIds"))){trainingOfficerIds = (String[])session.getAttribute("trainingOfficerIds");
		//System.out.println("FROM JSP"+trainingOfficerIds.length);}%>
		
	//	var trainingOfficerIds = '<%//<%=(String)session.getAttribute("trainingOfficerIds")%>
		//var trainingOfficerIds=trainingOfficerIds.split(',');
		//var i = 0;
		//var j = 0;
		//var p = document.getElementById('officerId').options.length;
		//var myarray = new Array();
		//myarray[0]="1";       // argument to control array's size)
       // myarray[1]="2";
       // myarray[2]="4";
		//var k = trainingOfficerIds.length;
		//alert(k);
		//for(i=0;i<k;i++)
		//{
		//for(j=0;j<p;j++){
		//if(document.getElementById('officerId').options[j].value==trainingOfficerIds[i])
			//{  document.getElementById('officerId').options[j].selected = true; }
		//}
		//}
		</script> 
</html:form>
</body>
</html:html>