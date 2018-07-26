<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
<script type="text/javascript">
	function de_filePDF() {
		isPDF=true;
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today=(day + "-" + month + "-" + year);
		
		var periodReport='';
		if(document.getElementById("selectReport3").checked)
			periodReport=document.getElementById('selectReport3').value;
		if(document.getElementById("selectReport4").checked)
			periodReport=document.getElementById('selectReport4').value;
		if(document.getElementById("selectReport5").checked)
			periodReport=document.getElementById('selectReport5').value;
		if(document.getElementById("selectReport6").checked)
			periodReport=document.getElementById('selectReport6').value;
		if(document.getElementById("selectReport7").checked)
			periodReport=document.getElementById('selectReport7').value;
		var listPeriod="CCDURPT001_3,CCDURPT001_4,CCDURPT001_5,CCDURPT001_6,CCDURPT001_8";
		
		//var financialReport='';
		//if(document.getElementById("selectReport1").checked)
		//	financialReport=document.getElementById('selectReport1').value;
		//if(document.getElementById("selectReport2").checked)
		//	financialReport=document.getElementById('selectReport2').value;
		
		//var listFinancial="CCDURPT001_1,CCDURPT001_2";
		
		if(inList(periodReport,listPeriod)){
			hide_ctrl('modalPeriod',false);					
			document.getElementById("selectPeriod_a_period").checked=true;
			document.getElementById("period_ok").focus();
		}
	//	else if(inList(financialReport,listFinancial)){
		//	hide_ctrl('modalFinancial',false);					
			//document.getElementById("financial_ok").focus();
		//}
		else{
			document.getElementById("fromDate").value="01-04-2008";
			document.getElementById("toDate").value=today;
			
			document.ccduReportsForm.action = "ccduReportsAction.do?method=filePDF&d__mode="+ d__mode+"&menuId=CCDURPT001";
			document.ccduReportsForm.submit();		          				
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
		if(document.getElementById("selectReport3").checked)
			periodReport=document.getElementById('selectReport3').value;
		if(document.getElementById("selectReport4").checked)
			periodReport=document.getElementById('selectReport4').value;
		if(document.getElementById("selectReport5").checked)
			periodReport=document.getElementById('selectReport5').value;
		if(document.getElementById("selectReport6").checked)
			periodReport=document.getElementById('selectReport6').value;
		if(document.getElementById("selectReport7").checked)
			periodReport=document.getElementById('selectReport7').value;
		var listPeriod="CCDURPT001_3,CCDURPT001_4,CCDURPT001_5,CCDURPT001_6,CCDURPT001_8";
		
		//var financialReport='';
		//if(document.getElementById("selectReport1").checked)
		//	financialReport=document.getElementById('selectReport1').value;
		//if(document.getElementById("selectReport2").checked)
			//financialReport=document.getElementById('selectReport2').value;
		
		//var listFinancial="CCDURPT001_1,CCDURPT001_2";
		
		if(inList(periodReport,listPeriod)){
			hide_ctrl('modalPeriod',false);					
			document.getElementById("selectPeriod_a_period").checked=true;
			document.getElementById("period_ok").focus();
		}
	//	else if(inList(financialReport,listFinancial)){
		//	hide_ctrl('modalFinancial',false);					
			//document.getElementById("financial_ok").focus();
	//	}
		else{
			document.getElementById("fromDate").value="01-04-2008";
			document.getElementById("toDate").value=today;
			
			document.ccduReportsForm.action = "ccduReportsAction.do?method=fileExcel&d__mode="+ d__mode+"&menuId=CCDURPT001";
			document.ccduReportsForm.submit();              				
		}            
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
				document.getElementById("fromDate").value=fromDate_pop;
			}
			if(toDate_pop==null||toDate_pop==''||toDate_pop==undefined){
				alert("Please select To date.");
				return;
			}else {
				document.getElementById("toDate").value=toDate_pop;
			}			
		}else{
			document.getElementById("fromDate").value="01-04-2008";
			document.getElementById("toDate").value=today;
		}
		document.ccduReportsForm.action = "ccduReportsAction.do?method="+(isPDF?"filePDF":"fileExcel")+"&d__mode="+ d__mode+"&menuId=CCDURPT001";
        document.ccduReportsForm.submit();              				
	}

	function yearCheck(){
		var val="";
		var finYearId=document.getElementById("finYearId").value;
		if(document.getElementById("selectReport1").checked){
			if(finYearId=="7")	val = "CCDURPT001_1_new";
			else val = "CCDURPT001_1";			
		}
		return val;
	}
</script>
<link href="css/layout.css" rel="stylesheet" type="text/css">

</head>
<body>

		<html:form action="ccduReportsAction" styleId="ccduReportsForm">
		<fieldset>
		<legend>Reports Filter</legend>
		<center>
		<table>		
		<tr>
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
						<html:option value="">Select Circle</html:option>
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
					onchange="ajaxFunction('GetFilterValues.to?type=withDPMC&districtId='+this.value, 'divisionalOfficeId');selectRadioDistrict();">
						<html:option value="">Select District</html:option>
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
			<tr>
			<td nowrap>Report for Month   </td>
						<td><select id="monthId"   >
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
						</select></td>
						<td nowrap>Financial Year</td>
						<td><html:select property="finYear" styleId="finYear" 
						>
							<html:option value="">Select</html:option>
							<html:option value="1">2007-08</html:option>
							<html:option value="2">2008-09</html:option>
							<html:option value="3">2009-10</html:option>
							<html:option value="4">2010-11</html:option>
							<html:option value="5">2011-12</html:option>
							<html:option value="6">2012-13</html:option>
							<html:option value="7">2013-14</html:option>
							<html:option value="8">2014-15</html:option>
							<html:option value="9">2015-16</html:option>
							<html:option value="10">2016-17</html:option>
							<html:option value="10">2017-18</html:option>
							<html:option value="10">2018-19</html:option>
							<html:option value="10">2019-20</html:option>
						
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
		</table>
		</center>
		</fieldset>
			
		<fieldset>
		<legend> Reports</legend>
		<center>
		<table>
			<tr><td colspan="2"> <html:radio property="selectReport" styleId="selectReport1" value="CCDURPT001_1" onchange="document.getElementById('selectReport1').value = yearCheck();">1. Capacity Building Plan for SWAp Villages</html:radio>  </td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport2" value="CCDURPT001_2">2. Capacity Building Plan for SPMC</html:radio>  </td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport"  styleId="selectReport3" value="CCDURPT001_3">3. Monthly Monitoring Compiled Report</html:radio>  </td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport4" value="CCDURPT001_4">4. Monthly Capacity Training Report Of GPWSC/GP</html:radio>  </td></tr>
		
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport5" value="CCDURPT001_5">5. Village Wise Detail Of IEC Activities Undertaken</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport6" value="CCDURPT001_6">6. Details Of IEC Activities Undertaken By DPMC</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport7" value="CCDURPT001_8">7. Abstract Of IEC Activities Undertaken</html:radio></td></tr>			
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport8" value="CCDURPT001_9">8. Status of capacity building activities(Target Vs Achievement)</html:radio></td></tr>
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
		<!--  	<div id="modalFinancial" style="position:absolute;left:325px;top:375px;width:650px; border:3px solid black; background-color:#00A2E2; padding:25px; font-size:150%; text-align:center; display:none;">
				<table>
					<tr>
						<td>Please select the Financial year</td>
						<td>
							<html:select property ="finYearId">
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
							</html:select>
						</td>
					</tr>				
				</table>  		
				<input type="button" value="OK" id="financial_ok" onClick="de_file_period();hide_ctrl('modalFinancial',true);return false;">
				<input type="button" value="Cancel" id="financial_cancel" onClick="hide_ctrl('modalFinancial',true);return false;">
			</div> -->
			</center>
			</fieldset>
		</html:form>
	<script>
hide_ctrl('modalPeriod',true);
//hide_ctrl('modalFinancial',true);
document.getElementById("fromDate_pop").value="";
document.getElementById("toDate_pop").value="";
						</script>
						<script>
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