<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
<script type="text/javascript">

	function de_view() {
		document.hrReportsForm.action = "hrReportsAction.do?method=view&d__mode="+ d__mode+"&menuId=HRRPT001";
		document.hrReportsForm.submit();
	}
	function de_filePrint() {
			document.hrReportsForm.action = "hrReportsAction.do?method=filePrint&d__mode="+ d__mode+"&menuId=HRRPT001";
			document.hrReportsForm.submit();		
	}
	function de_filePDF() {
		isPDF=true;
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today=(day + "-" + month + "-" + year);
		
		var periodReport='';
		if(document.getElementById("selectReport6").checked)
			periodReport=document.getElementById('selectReport6').value;
		var listPeriod="HRRPT001_05";
		if(inList(periodReport,listPeriod)){
		//alert("if");
			hide_ctrl('modalPeriod',false);					
			document.getElementById("selectPeriod_a_period").checked=true;
			document.getElementById("period_ok").focus();
		}
		//Recent changes
		if(document.getElementById("selectReport_06").checked)
			desigReport=document.getElementById('selectReport_06').value;
		var listDisg="HRRPT001_06";
		if(inList(desigReport,listDisg)){
			hide_ctrl('modalPeriodDes',false);					
			document.getElementById("selectDesig_a_desig").checked=true;
			document.getElementById("desig_ok").focus();
		}
		
		else{
			//alert("else");
			//alert(today);
			document.getElementById("fromDate").value="01-04-2008";
			document.getElementById("toDate").value=today;
			document.hrReportsForm.action = "hrReportsAction.do?method=filePDF&d__mode="+d__mode+"&menuId=HRRPT001";
			document.hrReportsForm.submit();
			
		}		
	}
	
	function de_fileExcel() {
		isPDF=false;
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today=(day + "-" + month + "-" + year);
		var periodReport='';
		var desigReport='';
		if(document.getElementById("selectReport6").checked)
			periodReport=document.getElementById('selectReport6').value;
		var listPeriod="HRRPT001_05";
		if(inList(periodReport,listPeriod)){
			hide_ctrl('modalPeriod',false);					
			document.getElementById("selectPeriod_a_period").checked=true;
			document.getElementById("period_ok").focus();
		}
		//Recent changes
		if(document.getElementById("selectReport_06").checked)
			desigReport=document.getElementById('selectReport_06').value;
		var listDisg="HRRPT001_06";
		if(inList(desigReport,listDisg)){
			hide_ctrl('modalPeriodDes',false);					
			document.getElementById("selectDesig_a_desig").checked=true;
			document.getElementById("desig_ok").focus();
		}
		else{
			document.getElementById("fromDate").value="01-04-2008";
			document.getElementById("toDate").value=today;
			document.hrReportsForm.action = "hrReportsAction.do?method=fileExcel&d__mode="+d__mode+"&menuId=HRRPT001";
			document.hrReportsForm.submit();
			
		}
	}
	function de_file_desig(){
		if(document.getElementById("selectDesig_s_desig").checked){
			var desig_pop=document.getElementById("designation_pop").value;
				
			if(desig_pop==""||desig_pop==''||desig_pop==undefined){
				alert("Please select From designation.");
				return;
			}else {
				//alert(fromDate_pop);
				document.getElementById("designation").value=desig_pop;
			}
						
		}else{
			document.getElementById("designation").value="";
		}	
		
			document.hrReportsForm.action ="hrReportsAction.do?method="+(isPDF?"filePDF":"fileExcel")+"&d__mode="+ d__mode+"&menuId=HRRPT001";
			document.hrReportsForm.submit();
	}
	function de_file_period(){
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today=(day + "-" + month + "-" + year);
		if(document.getElementById("selectPeriod_s_period").checked){
			var fromDate_pop=document.getElementById("fromDate_pop").value;
			var toDate_pop=document.getElementById("toDate_pop").value;			
			if(fromDate_pop==null||fromDate_pop==''||fromDate_pop==undefined){
				alert("Please select From date.");
				return;
			}else {
				//alert(fromDate_pop);
				document.getElementById("fromDate").value=fromDate_pop;
			}
			if(toDate_pop==null||toDate_pop==''||toDate_pop==undefined){
				alert("Please select To date.");
				return;
			}else {
				//alert(toDate_pop);
				document.getElementById("toDate").value=toDate_pop;
			}			
		}else{
			document.getElementById("fromDate").value="01-04-2008";
			document.getElementById("toDate").value=today;
		}
		 
		document.hrReportsForm.action ="hrReportsAction.do?method="+(isPDF?"filePDF":"fileExcel")+"&d__mode="+ d__mode+"&menuId=HRRPT001";
		document.hrReportsForm.submit();              				
	}
</script>
<link href="css/layout.css" rel="stylesheet" type="text/css">

</head>
<body>

		<html:form action="hrReportsAction">
		<fieldset>
		<legend>Reports Filter</legend>
		<center>
		<table><tr>
				<td>Select Zone</td>
				<td>
					<html:radio property="selectZone" title="Selection" value="S" styleId="zoneSelection">
					Selection</html:radio>
					<html:radio property="selectZone" title="All" value="A" styleId="zoneAll">
					All</html:radio>
				</td>
				<td colspan="2">
					<html:select property="zoneId" styleId="zoneId" style="width: 255px" styleClass="ci5" 
					onchange="ajaxFunction('GetFilterValues.to?zoneId='+this.value, 'circleId');
					triggerEvent(document.getElementById('circleId'), 'onchange');selectRadioZone();">
						<html:option value="">Select Zone</html:option>
						<html:options collection="zones" labelProperty="label" property="value"></html:options>						
					</html:select>
				</td>
				</tr>	
			<tr>
				<td>Select Circle</td>
				<td >
					<html:radio property="selectCircle" title="Selection" value="S" styleId="circleSelection" >
					Selection</html:radio>
					<html:radio property="selectCircle" title="All" value="A" styleId="circleAll" >
					All</html:radio>
				</td>
				<td colspan="2">
					<html:select property="circleId" styleId="circleId" style="width: 255px" styleClass="ci5" 
					onchange="ajaxFunction('GetFilterValues.to?circleId='+this.value, 'districtId');selectRadioCircle();">
						
					</html:select>
				</td>
			</tr>	
			<tr>
			<td>Select District</td>
				<td>
					<html:radio property="selectDistrict" title="Selection" value="S" styleId="districtSelection" >
					Selection</html:radio>
					<html:radio property="selectDistrict" title="All" value="A" styleId="districtAll" >
					All</html:radio>
				   </td>
				<td colspan="2">
					<html:select property="districtId" styleId="districtId" style="width: 255px" styleClass="ci5"
					onchange="ajaxFunction('GetFilterValues.to?districtId='+this.value, 'divisionalOfficeId');selectRadioDistrict();">
						
					</html:select>
				</td>
			</tr>	
			<tr>
			<td>Select Division</td>
				<td>
					<html:radio property="selectDivisionalOfficeId" title="Selection" value="S" styleId="divisionOfficeSelection" >
					Selection</html:radio>
					<html:radio property="selectDivisionalOfficeId" title="All" value="A" styleId="divisionOfficeAll" >
					All</html:radio>
				</td>
				<td>
					<html:select property="divisionalOfficeId" styleId="divisionalOfficeId" style="width: 255px" styleClass="ci5" onchange="selectRadioDivisionOffice();">
					<html:option value="">Select Division/SPMC</html:option>
					<html:option value="SPMC">SPMC</html:option>
					</html:select>
				</td>
			</tr>
		<!--<tr>	
		<td nowrap><label>Office Location</label></td>
			<td><html:select property="locationId" styleClass="cs2">
				<html:option value="">Select</html:option>
				<html:options collection="locationIds" labelProperty="label" property="value"/>
		</html:select></td></tr>
		--><tr>
			<td nowrap>Report for Month   </td>
						<td><html:select property="monthId">
						<option value="">Select</option>
						<option value="01">JAN</option>
						<option value="02">FEB</option>
						<option value="03">MAR</option>
						<option value="04">APR</option>
						<option value="05">MAY</option>
						<option value="06">JUN</option>
						<option value="07">JUL</option>
						<option value="08">AUG</option>
						<option value="09">SEP</option>
						<option value="10">OCT</option>
						<option value="11">NOV</option>
						<option value="12">DEC</option>
						</html:select></td>
						<td nowrap>Financial Year</td>
						<td><html:select property ="finYearId">
						<option value="">Select</option>
						<option value="1">2007-08</option>
						<option value="2">2008-09</option>
						<option value="3">2009-10</option>
						<option value="4">2010-11</option>
						<option value="5">2011-12</option>
						<option value="6">2012-13</option>
						<option value="7">2013-14</option>
						<option value="8">2014-15</option>
						<option value="9">2015-16</option>
						<option value="10">2016-17</option>
						</html:select></td>
			</tr>
			<tr>
				<td><label>Employee Type</label></td>
				<td><html:select property="employeeType" styleId="employeeTypeId" styleClass="cs1">
			
				
				<html:option value="">Select</html:option>
				<html:option value="All">All</html:option>
				<html:option value="<%=MISConstants.MIS_EMPLOYEE_TYPE_CONTRACTUAL%>">Contractual</html:option>
				<html:option value="<%=MISConstants.MIS_EMPLOYEE_TYPE_PERMANENT%>">Permanent</html:option>
				</html:select></td>
			</tr>
				<tr id="td1" style="visibility: hidden;">
				<td>Period</td>
				<td>	
					<html:radio property="selectPeriod" title="Selection" value="S" styleId="selectPeriod_s">Selection</html:radio>
					<html:radio property="selectPeriod" title="All" value="A" styleId="selectPeriod_a">All</html:radio>
				</td>		
				<td>From:&nbsp;&nbsp;&nbsp;
					<html:text property="fromDate" styleId="fromDate"	styleClass="ci3"></html:text> 
				</td>
				<td>To:&nbsp;&nbsp;&nbsp;
					<html:text property="toDate" styleId="toDate"	styleClass="ci3"></html:text> 
				</td>  					
			</tr>
			<tr id="td1" style="visibility: hidden;">
				<td>Period</td>
				<td>	
					<html:radio property="selectDesignation" title="Selection" value="S" styleId="selectDesig_s">Selection</html:radio>
					<html:radio property="selectDesignation" title="All" value="All" styleId="selectDesig_a">All</html:radio>
				</td>		
				<td>From:&nbsp;&nbsp;&nbsp;
					<html:text property="designation" styleId="designation"	styleClass="ci3"></html:text> 
				</td>
				 					
			</tr>
		</table>
		</center>
		</fieldset>
			
		<fieldset>
		<legend> Reports</legend>
		<center>
		<table>
			<tr><td colspan="3"><html:radio property="selectReport"  styleId="selectReport_06" value="HRRPT001_06">Employee Information</html:radio></td></tr>
			<tr><td colspan="3"><html:radio property="selectReport"  value="HRRPT001_02">Attendance Register</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="HRRPT001_01">Salary Slip</html:radio></td></tr>
			
			<tr>
				<td colspan="2"> <html:radio property="selectReport"  value="HRRPT001_04">Leave Register</html:radio></td>
			</tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport6" value="HRRPT001_05">Appraisal</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  value="HRRPT001_07">Training Maintenance</html:radio></td></tr>
			</table>
			<div id="modalPeriod" style="position:absolute;left:325px;top:375px;width:650px; border:3px solid black; background-color:#00A2E2; padding:25px; font-size:150%; text-align:center; display:none;">
				<table>
				<tr>
						<td colspan="2">Please select the Report Period</td>
				</tr>
					<tr>
						<td>Period</td>
						<td>	
							<html:radio property="selectPeriod" title="Selection" value="S" styleId="selectPeriod_s_period">Selection</html:radio>
							<html:radio property="selectPeriod" title="All" value="A" styleId="selectPeriod_a_period">All</html:radio>
						</td>	
					</tr>
					<tr>
						<td>From:&nbsp;&nbsp;&nbsp;
							<input type="text" Id="fromDate_pop"	Class="ci3">
						</td>
						<td>To:&nbsp;&nbsp;&nbsp;
							<input type="text" Id="toDate_pop"	Class="ci3">
						</td>
					</tr>
				</table>  		
				<input type="button" value="OK" id="period_ok" onClick="de_file_period();hide_ctrl('modalPeriod',true);return false;">
				<input type="button" value="Cancel" id="period_cancel" onClick="hide_ctrl('modalPeriod',true);return false;">
			</div>
			<!-- Recent changes -->
			<div id="modalPeriodDes" style="position:absolute;left:325px;top:375px;width:650px; border:3px solid black; background-color:#00A2E2; padding:25px; font-size:150%; text-align:center; display:none;">
				<table>
				<tr>
						<td colspan="2">Please select the Designation</td>
				</tr>
					<tr>
						<td>Designation</td>
						<td>	
							<html:radio property="selectDesignation" title="Selection" value="S" styleId="selectDesig_s_desig">Selection</html:radio>
							<html:radio property="selectDesignation" title="All" value="All" styleId="selectDesig_a_desig">All</html:radio>
						</td>	
					</tr>
					<tr>
						
					<tr>
					<td><html:select property ="designation" styleId="designation_pop" styleClass="cs3" onchange="
			       if(this.value!=''){
						document.getElementById('selectDesig_s_desig').checked=true;
					} 
					
					">
						<option value="">Select</option>
					    <option value="Chief Engineer">Chief Engineer</option>
						<option value="Superintending Engineer">Superintending Engineer</option>
						<option value="Unit Coordinator">Unit Coordinator</option>
						<option value="Executive Engineer">Executive Engineer</option>
						<option value="Joint Controller">Joint Controller</option>
						<option value="Superintending Engineer">Superintending Engineer</option>
						<option value="Senior Chemist">Senior Chemist</option>
						<option value="Sr. CM Specialist">Sr. CM Specialist</option>
						<option value="Sr. HRD Specialist">Sr. HRD Specialist</option>
						<option value="Finance Manager">Finance Manager</option>
						<option value="Assistant System Analyst">Assistant System Analyst</option>
						<option value="Sub Divisional Engineer">Sub Divisional Engineer</option>
						<option value="Junior Engineer">Junior Engineer</option>
						<option value="Chemist">Chemist</option>
						<option value="Environment Specialist">Environment Specialist</option>
						<option value="Program Management Officer">Program Management Officer</option>
						<option value="Sustainability Assessment Specialist">Sustainability Assessment Specialist</option>
						<option value="Social Development Specialist">Social Development Specialist</option>
						<option value="Communication Specialist">Communication Specialist</option>
						<option value="Junior Engineer">Junior Engineer</option>
						<option value="MIS Specialist">MIS Specialist</option>
						<option value="IEC Specialist">IEC Specialist</option>
						<option value="HRD Specialist">HRD Specialist</option>
						<option value="Administrative officer">Administrative officer</option>
						<option value="Superintendent Grade-1">Superintendent Grade-1</option>
						<option value="Finance Officer">Finance Officer</option>
						<option value="Superintendent Grade-2">Superintendent Grade-2</option>
						<option value="Circle Head Draftsman">Circle Head Draftsman</option>
						<option value="Divisional Accountant">Divisional Accountant</option>
						<option value="Account Assistant">Account Assistant</option>
						<option value="MIS Tech. Operator">MIS Tech. Operator</option>
						<option value="Office Assistant">Office Assistant</option>
						<option value="Head Draftsman">Head Draftsman</option>
						<option value="Legal Assistant">Legal Assistant</option>
						<option value="Assistant">Assistant</option>
						<option value="Personal Assistant">Personal Assistant</option>
						<option value="Assistant Chemist">Assistant Chemist</option>
						<option value="Draftsman">Draftsman</option>
						<option value="Stenographer">Stenographer</option>
						<option value="Auditor">Auditor</option>
						<option value="Sub Divisional Clerk">Sub Divisional Clerk</option>
						<option value="Junior Draftsman">Junior Draftsman</option>
						<option value="Steno- Typist">Steno- Typist</option>
						<option value="Laboratory Assistant">Laboratory Assistant</option>
						<option value="Block Coordinator">Block Coordinator</option>
						<option value="Data Entry Operator">Data Entry Operator</option>
						<option value="Clerk-cum-typist">Clerk-cum-typist</option>
						<option value="Clerk">Clerk</option>
						<option value="Bill Clerk">Bill Clerk</option>
						<option value="Meter Clerk">Meter Clerk</option>
						<option value="Complaint Clerk">Complaint Clerk</option>
						<option value="Store Munshi">Store Munshi</option>
						<option value="Work Munshi">Work Munshi</option>
						<option value="Feroprinter">Feroprinter</option>
						<option value="Vehicle Driver">Vehicle Driver</option>
						<option value="Surveyor">Surveyor</option>
						<option value="Skilled Worker">Skilled Worker</option>
						<option value="Vehicle Driver">Vehicle Driver</option>
						<option value="Survey Khalasi">Survey Khalasi</option>
						<option value="Jamadaar">Jamadaar</option>
						<option value="Daftari">Daftari</option>
						<option value="Peon">Peon</option>
						<option value="Peon cum Chowkidar">Peon cum Chowkidar</option>
						<option value="Mali cum Chowkidar">Mali cum Chowkidar</option>
						<option value="Watchman/ Chowkidar">Watchman/ Chowkidar</option>
						<option value="Sweeper">Sweeper</option>
						<option value="Unskilled Worker">Unskilled Worker</option>
						<option value="Pump Operator">Pump Operator</option>
						<option value="Others">Others</option>
					
					
					</html:select></td> 
			</tr>
			</table>
			<input type="button" value="OK" id="desig_ok" onClick="de_file_desig();hide_ctrl('modalPeriodDes',true);return false;">
			<input type="button" value="Cancel" id="desig_cancel" onClick="hide_ctrl('modalPeriodDes',true);return false;">
			</div>
			
			
			</center>
			</fieldset>
		</html:form>
	<script type="text/javascript">
hide_ctrl('modalPeriod',true);
//hide_ctrl('modalFinancial',true);
document.getElementById("fromDate_pop").value="";
document.getElementById("toDate_pop").value="";

function selectRadioZone(){
					var zone = document.getElementById('zoneId').value;
				//	alert(zone);
					if(zone!=""){
				//		alert("inside");
						document.getElementById('zoneSelection').checked=true;
					}else{
						document.getElementById('zoneAll').checked=true;
						document.getElementById('circleAll').checked=true;
						document.getElementById('districtAll').checked=true;
						document.getElementById('divisionOfficeAll').checked=true;
					}
		}

		function selectRadioCircle(){
					var circle = document.getElementById('circleId').value;
					if(circle!=""){
							document.getElementById('circleSelection').checked=true;
					}else{
							document.getElementById('circleAll').checked=true;
							document.getElementById('districtAll').checked=true;
							document.getElementById('divisionOfficeAll').checked=true;
					}
		}

		function selectRadioDistrict(){
					var district = document.getElementById('districtId').value;
					if(district!=""){
							document.getElementById('districtSelection').checked=true;
					}else{
							document.getElementById('districtAll').checked=true;
							document.getElementById('divisionOfficeAll').checked=true;
					}
		}

		function selectRadioDivisionOffice(){
					var division = document.getElementById('divisionalOfficeId').value;
					if(division!=""){
							document.getElementById('divisionOfficeSelection').checked=true;
					}
					else{
							document.getElementById('divisionOfficeAll').checked=true;
					}
		}
</script>

</body>
</html>