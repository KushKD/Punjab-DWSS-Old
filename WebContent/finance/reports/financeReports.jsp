<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
<script type="text/javascript">

	function de_view() {
		document.financeReportsForm.action = "financeReportsAction.do?method=view&d__mode="+ d__mode+"&menuId=FINRPT001";
		document.financeReportsForm.submit();
	}
	function de_filePrint() {
			document.financeReportsForm.action = "financeReportsAction.do?method=filePrint&d__mode="+ d__mode+"&menuId=FINRPT001";
			document.financeReportsForm.submit();		
	}
	function de_filePDF() {
		isPDF=true;
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today=(day + "-" + month + "-" + year);
    	var periodReport='';
			if(document.getElementById("selectReport19").checked)
				periodReport=document.getElementById('selectReport19').value;
			if(document.getElementById("selectReport9").checked)
				periodReport=document.getElementById('selectReport9').value;
		var periodLocationReport="";
		if (document.getElementById('selectReport17').checked)
				periodLocationReport=document.getElementById('selectReport17').value;
			else periodLocationReport='';
		var divisionReport="";
		if (document.getElementById('selectReport6').checked)
			divisionReport=document.getElementById('selectReport6').value;
			else divisionReport='';
		
		var listPeriod="FINRPT001_19,FINRPT001_9";
		var listUpto="abc";
		var activityReport=document.getElementById("selectReport11").checked?document.getElementById('selectReport11').value:'';
		var gpwscReport=document.getElementById("selectReport21").checked?document.getElementById('selectReport21').value:'';
    	var listActivity="FINRPT001_11";
    	var listGPWSC="FINRPT001_21";
		var listPeriodLocation="FINRPT001_17";
		var listDivision="FINRPT001_6";
		
		if(inList(periodReport,listPeriod)){
			hide_ctrl('modalPeriod',false);					
			document.getElementById("selectPeriod_a_period").checked=true;
			document.getElementById("period_ok").focus();
		}else if (inList(periodReport,listUpto)){
			hide_ctrl('modalUpto',false);
			document.getElementById("selectPeriod_a_upto").checked=true;
			document.getElementById("upto_ok").focus();
		}else if(inList(activityReport,listActivity)){
			hide_ctrl('divActivity',false);
			document.getElementById("selectActivity_a").checked=true;
			document.getElementById("activity_ok").focus();
		}else if(inList(gpwscReport,listGPWSC)){
			hide_ctrl('divGPWSC',false);
			document.getElementById("gpwsc_ok").focus();
		}else if(inList(periodLocationReport,listPeriodLocation)){
			hide_ctrl('modalPeriodLocation',false);
			document.getElementById("periodLocation_ok").focus();
		}else if(inList(divisionReport,listDivision)){
			hide_ctrl('modalDivision',false);
			document.getElementById("division_ok").focus();
		}else{
			document.getElementById("fromDate").value="01-04-2008";
			document.getElementById("toDate").value=today;
			
			document.financeReportsForm.action = "financeReportsAction.do?method=filePDF&d__mode="+ d__mode+"&menuId=FINRPT001";
			document.financeReportsForm.submit();		          				
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
			if(document.getElementById("selectReport19").checked)
				periodReport=document.getElementById('selectReport19').value;
			if(document.getElementById("selectReport9").checked)
				periodReport=document.getElementById('selectReport9').value;
			
		
		var periodLocationReport="";
		if (document.getElementById('selectReport17').checked)
				periodLocationReport=document.getElementById('selectReport17').value;
			else periodLocationReport='';
		var divisionReport="";
		if (document.getElementById('selectReport6').checked)
			divisionReport=document.getElementById('selectReport6').value;
			else divisionReport='';
		
		var listPeriod="FINRPT001_19,FINRPT001_9";
		var listUpto="abc";
		var activityReport=document.getElementById("selectReport11").checked?document.getElementById('selectReport11').value:'';
		var gpwscReport=document.getElementById("selectReport21").checked?document.getElementById('selectReport21').value:'';
    	var listActivity="FINRPT001_11";
    	var listGPWSC="FINRPT001_21";
		var listPeriodLocation="FINRPT001_17";
		var listDivision="FINRPT001_6";
		
		if(inList(periodReport,listPeriod)){
			hide_ctrl('modalPeriod',false);					
			document.getElementById("selectPeriod_a_period").checked=true;
			document.getElementById("period_ok").focus();
		}else if (inList(periodReport,listUpto)){
			hide_ctrl('modalUpto',false);
			document.getElementById("selectPeriod_a_upto").checked=true;
			document.getElementById("upto_ok").focus();
		}else if(inList(activityReport,listActivity)){
			hide_ctrl('divActivity',false);
			document.getElementById("selectActivity_a").checked=true;
			document.getElementById("activity_ok").focus();
		}else if(inList(gpwscReport,listGPWSC)){
			hide_ctrl('divGPWSC',false);
			document.getElementById("gpwsc_ok").focus();
		}else if(inList(periodLocationReport,listPeriodLocation)){
			hide_ctrl('modalPeriodLocation',false);
			document.getElementById("periodLocation_ok").focus();
		}else if(inList(divisionReport,listDivision)){
			hide_ctrl('modalDivision',false);
			document.getElementById("division_ok").focus();
		}else{
			document.getElementById("fromDate").value="01-04-2008";
			document.getElementById("toDate").value=today;
			
			document.financeReportsForm.action = "financeReportsAction.do?method=fileExcel&d__mode="+ d__mode+"&menuId=FINRPT001";
			document.financeReportsForm.submit();              				
		}            
	}
	function de_file_period(){
			
		var currentTime = new Date();
		var month = currentTime.getMonth() + 1;
		var day = currentTime.getDate();
		var year = currentTime.getFullYear();
		var today=(day + "-" + month + "-" + year);
		if(document.getElementById("selectPeriod_s_period").checked && 
				(document.getElementById('selectReport19').checked||document.getElementById('selectReport9').checked)
		){
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
			//document.getElementById("fromDate").value=(fromDate_pop==null||fromDate_pop==''||fromDate_pop==undefined)?'01-04-2008':fromDate_pop;
			//document.getElementById("toDate").value=(toDate_pop==null||toDate_pop==''||toDate_pop==undefined)?today:toDate_pop;
		}else if(document.getElementById("selectPeriod_s_period2").checked && document.getElementById('selectReport17').checked){
			var fromDate_pop=document.getElementById("fromDate_pop2").value;
			var toDate_pop=document.getElementById("toDate_pop2").value;
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
			//document.getElementById("fromDate").value=(fromDate_pop==null||fromDate_pop==''||fromDate_pop==undefined)?alert("Please select dates."):fromDate_pop;
			//document.getElementById("toDate").value=(toDate_pop==null||toDate_pop==''||toDate_pop==undefined)?today:toDate_pop;
		}else{
			document.getElementById("fromDate").value="01-04-2008";
			document.getElementById("toDate").value=today;
		}
		document.financeReportsForm.action = "financeReportsAction.do?method="+(isPDF?"filePDF":"fileExcel")+"&d__mode="+ d__mode+"&menuId=FINRPT001";
        document.financeReportsForm.submit();              				
	}
</script>
<link href="css/layout.css" rel="stylesheet" type="text/css">

</head>
<body>

		<html:form action="financeReportsAction" styleId="financeReportsForm">
		<fieldset>
		<legend>Reports Filter</legend>
		<center>
		<table>	<tr>	
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
					<html:select property="districtId" styleId="districtId" style="width: 255px" styleClass="ci5" onchange="selectRadioDistrict();">
						
					</html:select>
				</td>
			</tr>	
			
			<tr>
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
						<option value="11">2017-18</option>
<option value="12">2018-19</option>
	

						</html:select></td>
			</tr>
			<tr>
			<td nowrap>Select Quarter</td>
						<td colspan="2"><html:select property="qtr">
						<option value="">Select</option>
						<option value="q1">Quarter 1</option>
						<option value="q2">Quarter 2</option>
						<option value="q3">Quarter 3</option>
						<option value="q4">Quarter 4</option>
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
					<!--<input class=ci4 type=button onclick="c1.innerpopup('fromDate','calendar_frame');" value="..." />-->
				</td>
				<td>To:&nbsp;&nbsp;&nbsp;
					<html:text property="toDate" styleId="toDate"	styleClass="ci3"></html:text> 
					<!--<input class=ci4 type=button onclick="c1.innerpopup('toDate','calendar_frame');" value="..." />-->
				</td>  					
			</tr>
		</table>
		</center>
		</fieldset>
			
		<fieldset>
		<legend> Reports</legend>
		<center>
<table>
<tr>
	<td>
		<fieldset style="width:33%px; height:200px;">
		<legend>A. Budget</legend>
		<table>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport1" value="FINRPT001_1">A.1. Activity-wise Annual Estimated Budget</html:radio>  </td>
			</tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport5"  value="FINRPT001_5">A.2. Component-Wise Annual Estimated Budget</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport3"  value="FINRPT001_3">A.3. Budget Approved by SWSM </html:radio>  </td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport2" value="FINRPT001_2">A.4. Budget Allocated by SPMC</html:radio>  </td></tr>
			<tr><td colspan="3"> </td></tr>
		</table>
		</fieldset>
	</td>
	<td><fieldset style="width:30%px; height:200px;"><legend>B. LOC</legend>
		<table>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport6"  value="FINRPT001_6">B.1. Distribution of LOC under Component-C (during the Year)</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport7"  value="FINRPT001_7">B.2. Component-Wise funds released and Expenditure incurred (during the Year)</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport9"  value="FINRPT001_9">B.3. DPMC/Scheme-Wise Distribution of LOC under Component-C</html:radio></td></tr>
			<tr><td colspan="3">. </td></tr>
		</table></fieldset>
	</td>
	<td><fieldset style="width:30%px; height:200px;"><legend>C. Account Books/Registers</legend>
		<table>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport11"  value="FINRPT001_11">C.1. Activity Register: Activity Wise Payment Detail</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport17"  value="FINRPT001_17">C.2. Cash-Book</html:radio></td></tr>
			<!--<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport18"  value="FINRPT001_18">10. Cash-Book (Receipt-Side)</html:radio></td></tr>
			--><tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport20"  value="FINRPT001_20">C.3. Scheme Control Register </html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport21"  value="FINRPT001_21">C.4. GPWSC Advance Control Register</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport24"  value="FINRPT001_24">C.5.Abstract of Scheme Control Register </html:radio></td></tr>
	
		</table></fieldset>
	</td>
</tr>
<tr>
	<td><fieldset style="width:30%px; height:200px;"><legend>D. Expenditure</legend>
		<table>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport10"  value="FINRPT001_10">D.1. Monthly/Quarterly Expenditure of SPMC/DPMC </html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport19"  value="FINRPT001_19">D.2. DPMC's Compiled Yearly Expenditure Report</html:radio></td></tr>
		</table></fieldset>
	</td>
	<td><fieldset style="width:30%px; height:200px;"><legend>E. World Bank Reports</legend>
		<table>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport12"  value="FINRPT001_12">E.1.1 IUFR-I (Page 1)</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport12_1"  value="FINRPT001_12_1">E.1.2 IUFR-I (Page 2)</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport13"  value="FINRPT001_13">E.2. IUFR-II</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport14"  value="FINRPT001_14">E.3. IUFR-III</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport15"  value="FINRPT001_15">E.4. IUFR-IV</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport16"  value="FINRPT001_16">E.5. IUFR-V</html:radio></td></tr>
		</table></fieldset>
	</td>
	<td>
		<fieldset style="width:30%px; height:200px;"><legend>F. Statements Showing Funds Status   </legend>
		<table>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport25"  value="FINRPT001_25">F.1 Statement showing funds received as WB/ GOP/GOI Share</html:radio></td></tr>
			<tr><td colspan="3"> <html:radio property="selectReport" styleId="selectReport26"  value="FINRPT001_26">F.2 Statement showing funds received & Issued by Finance Department as WB Share</html:radio></td></tr>
			 
		</table></fieldset>
	</td>
</tr>
</table>	
	</center>		
			</fieldset>
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
									<div id="modalUpto" style="position:absolute;left:325px;top:375px;border:3px solid black;  background-color:#00A2E2; padding:25px; font-size:150%; text-align:center; display:none;">
										<table>

											<tr>
												<tr>
													<td colspan="3">Please select the Report Period (upto)</td>
												</tr>
												<tr>
												<td>Period</td>
												<td>	
													<html:radio property="selectPeriod" title="Selection" value="S" styleId="selectPeriod_s_upto">Selection</html:radio>
													<html:radio property="selectPeriod" title="All" value="A" styleId="selectPeriod_a_upto" >All</html:radio>
												</td>	
											</tr>
												<tr>
													<td nowrap>Month</td>
													<td>
														<select id="month" style="width: 255px" styleClass="ci5">
															<option value="">Select Month</option>
															<option value="01">JAN</option>
															<option value="02">FEB</option>
															<option value="03">MAR</option>
															<option value="04">APR</option>
															<option value="05">MAY</option>
															<option value="06">JUN</option>
															<option value="07">JUL</option>
															<option value="08">AUG</option>
															<option value="09" selected>SEP</option>
															<option value="10">OCT</option>
															<option value="11">NOV</option>
															<option value="12">DEC</option>
														</select>
													</td>
													<td nowrap>Year</td>
													<td>
														<select id ="year" style="width: 255px" styleClass="ci5">
															<option value="">Select Year</option>
															<option value="1970">1970</option>
															<option value="1971">1971</option>
															<option value="1972">1972</option>
															<option value="1973">1973</option>
															<option value="1974">1974</option>
															<option value="1975">1975</option>
															<option value="1976">1976</option>
															<option value="1977">1977</option>
															<option value="1978">1978</option>
															<option value="1979">1979</option>
															<option value="1981">1981</option>
															<option value="1982">1982</option>
															<option value="1983">1983</option>
															<option value="1984">1984</option>
															<option value="1985">1985</option>
															<option value="1986">1986</option>
															<option value="1987">1987</option>
															<option value="1988">1988</option>
															<option value="1989">1989</option>
															<option value="1990">1990</option>
															<option value="1991">1991</option>
															<option value="1992">1992</option>
															<option value="1993">1993</option>
															<option value="1994">1994</option>
															<option value="1995">1995</option>
															<option value="1996">1996</option>
															<option value="1997">1997</option>
															<option value="1998">1998</option>
															<option value="1999">1999</option>
															<option value="2000">2000</option>
															<option value="2001">2001</option>
															<option value="2002">2002</option>
															<option value="2003">2003</option>
															<option value="2004">2004</option>
															<option value="2005">2005</option>
															<option value="2006">2006</option>
															<option value="2007">2007</option>
															<option value="2008">2008</option>
															<option value="2009">2009</option>
															<option value="2010">2010</option>
															<option value="2011" selected>2011</option>
															<option value="2012">2012</option>
															<option value="2013">2013</option>
															<option value="2014">2014</option>
															<option value="2015">2015</option>
															<option value="2016">2016</option>
															<option value="2017">2017</option>
															<option value="2018">2018</option>
															<option value="2019">2019</option>
															<option value="2020">2020</option>
														</select>
													</td>
												</tr>
			
										</table>  		
										<input type="button" value="OK" id="upto_ok" onClick="de_file_upto();hide_ctrl('modalUpto',true);return false;">
										<input type="button" value="Cancel" id="upto_cancel" onClick="hide_ctrl('modalUpto',true);return false;">
									</div>
									<div id="divActivity" style="position:absolute;left:325px;top:375px;border:3px solid black;  background-color:#00A2E2; padding:25px; font-size:150%; text-align:center; display:none;">
										<table>
											<tr>
												<td colspan="2"><b><label>Please Select Activity</label></b></td>
												<td colspan="4">	
													<html:radio property="selectActivity" title="Selection" value="S" styleId="selectActivity_s">Selection</html:radio>
													<html:radio property="selectActivity" title="All" value="A" styleId="selectActivity_a" >All</html:radio>
												</td>
											</tr>
											<tr>
												<td><label>Component</label></td>
												<td>
													<html:select property="componentId" styleId="componentId" styleClass="cs2"	onchange="ajaxFunction('paymentVoucherAction.do?componentId='+this.value+'&method=fetchSubComponent', 'subComponentId'); triggerEvent(document.getElementById('subComponentId'), 'onchange');">
														<html:options collection="componentIds" labelProperty="label" property="value" />
													</html:select>
												</td>
												<td><label>Sub Component</label></td>
												<td>
													<html:select property="subComponentId" styleId="subComponentId" styleClass="cs2" onchange="ajaxFunction('paymentVoucherAction.do?subComponentId='+this.value+'&method=fetchActivity', 'activityId'); ">
													</html:select>
												</td>
												<td><label>Activity</label></td>
												<td>
													<html:select property="activityId" styleId="activityId"	styleClass="cs2">
													</html:select>
												</td>
										</table>
										<input type="button" value="OK" id="activity_ok" onClick="de_file_period();hide_ctrl('divActivity',true);return false;">
										<input type="button" value="Cancel" id="activity_cancel" onClick="hide_ctrl('divActivity',true);return false;">
									</div>
									<div id="divGPWSC" style="position:absolute;left:325px;top:375px;border:3px solid black;  background-color:#00A2E2; padding:25px; font-size:150%; text-align:center; display:none;">
										<table>
											<tr>
<td>Location</td>
			
			<td colspan="3"><html:select property="locationId" styleId="locationId"
				style="width:560px;" onchange="ajaxFunction('GetFilterValues.to?locationId='+this.value, 'blockId');triggerEvent(document.getElementById('blockId'), 'onchange');">
				<html:option value="Select location"></html:option>
				<html:options collection="userLocations" labelProperty="label" property="value"/>
			</html:select></td>
			</tr>
			<tr>
			<td>Select Block</td>
			<td colspan="3"><html:select property="blockId" styleId="blockId" style="width:560px;"
				onchange="ajaxFunction('GetSchemeFilterValues.to?blockId='+this.value, 'villageId');triggerEvent(document.getElementById('villageId'), 'onchange');">
				<html:option value="Select Block"></html:option>
			</html:select></td>
		</tr>
		<tr>
			<td>Select Village</td>
			<td colspan="3"><html:select property="villageId" styleId="villageId" style="width:560px;"
				onchange="ajaxFunction('GetSchemeFilterValues.to?villageId='+this.value, 'schemeId');triggerEvent(document.getElementById('schemeId'), 'onchange');">
				<html:option value="Select Village"></html:option>
			</html:select></td>
		</tr>
		<tr>
		
<td>Scheme Name</td>
<td colspan="3"><html:select property="schemeId" styleId="schemeId"
				style="width:560px;" 
				onchange="ajaxFunction('gpwscRegisterAction.do?villageId='+document.getElementById('villageId').value+'&schemeId='+this.value+'&method=fetchGPWSCIds', 'gpwcId2');triggerEvent(document.getElementById('gpwcId2'), 'onchange');">				
		
			</html:select>
		</td>
		</tr>
		<tr>	
<td>GPWSC</td>
<td colspan="3"><html:select property="gpwscId" styleId="gpwcId2"
				style="width:560px;" >
			</html:select>
</td>
<td></td>			
</tr>
										</table>										
										
										<input type="button" value="OK" id="gpwsc_ok" onClick="de_file_period();hide_ctrl('divGPWSC',true);return false;">
										<input type="button" value="Cancel" id="gpwsc_cancel" onClick="hide_ctrl('divGPWSC',true);return false;">
									</div>
									<div id="modalPeriodLocation" style="position:absolute;left:325px;top:375px;width:650px; border:3px solid black; background-color:#00A2E2; padding:25px; font-size:150%; text-align:center; display:none;">
										<table>
										<tr>
												<td colspan="2">Please select the Report Period</td>
										</tr>
											<tr>
												<td>Period</td>
												<td>	
													<html:radio property="selectPeriod" title="Selection" value="S" styleId="selectPeriod_s_period2" >Selection</html:radio>
													
												</td>	
											</tr>
											<tr>
												<td>From:&nbsp;&nbsp;&nbsp;
													<input type="text" Id="fromDate_pop2"	Class="ci3">
												</td>
												<td>To:&nbsp;&nbsp;&nbsp;
													<input type="text" Id="toDate_pop2"	Class="ci3">
												</td>
											</tr>
											<tr>
											<td>Select Division/SPMC/DPMC</td>
														<td colspan="3"><html:select property="divisionId" styleId="locationId2"
															styleClass="cs2" >
															<html:option value="">Please Select</html:option>
															<html:options collection="userLocations" labelProperty="label" property="value"/>
														</html:select></td>
										</table>  		
										<input type="button" value="OK" id="periodLocation_ok" onClick="de_file_period();hide_ctrl('modalPeriodLocation',true);return false;">
										<input type="button" value="Cancel" id="periodLocation_cancel" onClick="hide_ctrl('modalPeriodLocation',true);return false;">
									</div>
									<div id="modalDivision" style="position:absolute;left:325px;top:375px;width:650px; border:3px solid black; background-color:#00A2E2; padding:25px; font-size:150%; text-align:center; display:none;">
										<table>
											<tr>
												<td>Select Division/SPMC/DPMC</td>
												<td colspan="3"><html:select property="divisionId3" styleId="locationId3" styleClass="cs2" >
												<html:option value="">Please Select</html:option>
													<html:options collection="userLocations" labelProperty="label" property="value"/></html:select>
												</td>
											</tr>	
										</table>  		
										<input type="button" value="OK" id="division_ok" onClick="de_file_period();hide_ctrl('modalDivision',true);return false;">
										<input type="button" value="Cancel" id="division_cancel" onClick="hide_ctrl('modalDivision',true);return false;">
									</div>
									
		</html:form>
	<script>
hide_ctrl('modalPeriod',true);
hide_ctrl('modalUpto',true);
hide_ctrl('divActivity',true);
hide_ctrl('divGPWSC',true);
hide_ctrl('modalPeriodLocation',true);
hide_ctrl('modalDivision',true);
document.getElementById("fromDate_pop").value="";
document.getElementById("toDate_pop").value="";
document.getElementById("selectPeriod_s_period2").checked=true;
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
					}
		}

		function selectRadioCircle(){
					var circle = document.getElementById('circleId').value;
					if(circle!=""){
							document.getElementById('circleSelection').checked=true;
					}else{
							document.getElementById('circleAll').checked=true;
							document.getElementById('districtAll').checked=true;
					}
		}

		function selectRadioDistrict(){
					var district = document.getElementById('districtId').value;
					if(district!=""){
							document.getElementById('districtSelection').checked=true;
					}else{
							document.getElementById('districtAll').checked=true;
					}
		}


	</script>


</body>
</html>