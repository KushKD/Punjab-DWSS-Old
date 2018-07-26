<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
--><script language="JavaScript" src="js/calendar_us.js"></script>
<script type="text/javascript">
		function de_find(){		
			document.outwardDakForm.action="outwardDakAction.do?method=find&d__mode="+d__mode+"&menuId=DTM002";
			document.outwardDakForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.outwardDakForm.action="outwardDakAction.do?method=update&d__mode="+d__mode+"&menuId=DTM002";
				document.outwardDakForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.outwardDakForm.action="outwardDakAction.do?method=delete&d__mode="+d__mode+"&menuId=DTM002";
				document.outwardDakForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.outwardDakForm.action="outwardDakAction.do?method=post&d__mode="+d__mode+"&menuId=DTM002";
				document.outwardDakForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.outwardDakForm.action="outwardDakAction.do?method=save&d__mode="+d__mode+"&menuId=DTM002";
				document.outwardDakForm.submit();
			}
		}

		function getDocument(documentId){
			document.outwardDakForm.action="outwardDakAction.do?method=populate&documentNo="+documentId+"&d__mode="+d__mode+"&menuId=DTM002";
			document.outwardDakForm.submit();
		}	
		    
	</script>
	<link href="css/form.css" rel="stylesheet" type="text/css">
	<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/calendar.css">
	
</head>
<html:html>
<body bgcolor="#6699FF" >
<html:form action="outwardDakAction">

	<logic:messagesPresent >
					<div id="errorDiv" class="error displaynone" style="width: 470px%; margin-bottom: 7px; height: 13px;">
 						<html:errors bundle="DakTask"/>   
					</div>
				</logic:messagesPresent >
				<logic:messagesPresent message="true">
					<div id="successDiv" class="success diplaynone" style="width: 470px;">
						<html:messages id="message" bundle="DakTask" message="true">
       						<li><bean:write name="message" /></li>
   						</html:messages>
					</div>
				</logic:messagesPresent >
	<fieldset><legend>Outward Dak</legend>
	<center>
	<table>
		<tr>
			<td><label>Loaction</label></td>
			<td><html:select property="locationId" styleId="locationId" styleClass="cs1">
				<html:option value="">Select Location</html:option>
				<html:options collection="userLocations" labelProperty="label"
					property="value"></html:options></html:select>
			</td>	
					
		</tr>
		<tr>
			<td><label>Document Type</label></td>
			<td><html:text property="documentType" styleId="documentType" styleClass="ci5"/></td>			
			
			<td><label>Document Ref No.</label></td>
			<td><html:text property="documentReferenceNo" styleId="documentReferenceId" styleClass="ci5"/></td>
			
		</tr>
		
		<tr>
			<td><label>Dispatch Through</label></td>
			<td><html:text property="dispatchThrough" styleId="dispatchThrough" styleClass="ci5"/></td>
			<td><label>Dispatch Date</label></td>
			<td><html:text property="dispatchDate" styleId="dispatchDate" styleClass="ci5" readonly="readonly"/>
					
		</tr>
		<tr>
			<td><label>Receiver Name</label></td>
			<td colspan="3"><html:text property="receiverName" size="68" styleId="receiverName" styleClass="ci5"/></td>
		</tr>	
		<tr>
			<td><label>Document Subject</label></td>
			<td colspan="3"><html:text property="subject" size="68" styleId="subject" styleClass="ci5"/></td>
		</tr>
		<tr>
			<td><label>Receiver Address</label></td>
			<td colspan="3"><html:textarea property="receiverAddress" cols="53" rows="2" styleId="receiverAddress" styleClass="ci5"></html:textarea>
			</td>			
		</tr>
		<tr>
			<td><label>Enclosures (If any)</label></td>
			<td colspan="3"><html:text property="enclosures" size="68" styleId="enclosures" styleClass="ci5"/></td>
		</tr>
		<tr>
			<td><label>After Issue</label></td>
			<td colspan="3"><html:text property="afterIssue" size="68" styleId="afterIssue" styleClass="ci5"/></td>
		</tr>	
	<tr>
			<td ><label >Postal Charge</label></td>
			<td><html:text property="postalCharge" styleId="postalCharge" styleClass="ci5" onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
			<td></td>
			<td></td>
	</tr>
	
	</table>
	</center>
	</fieldset>
	<div id="dispTag">
	<logic:present name="outwardDakBean" scope="request">
		<logic:notEmpty name="outwardDakBean" scope="request">
			<center><display:table name="outwardDakBean" id="outward" class="mars" style="margin:0 1em 1em 0;" pagesize="10" requestURI="/outwardDakAction.do">
				<display:column title="Click to Display" media="html"><a href="javascript:getDocument('${outward.documentRefNo}')">${outward.documentRefNo}</a></display:column>
				<display:column property="documentRefNo" title="Document No" />
				<display:column property="dispatchDate" title="Dispatch Date" decorator="com.prwss.mis.common.util.DateColumnDecorator"/>
				<display:column property="receiverName" title="Receipient Name" />
				<display:column property="subject" title="Subject" />
				<display:column property="afterIssue" title="After Issue" />
				<display:column property="dispatchThrough" title="Dispatch Through " />
				<display:setProperty name="export.excel.filename" value="outwardDakCheckList.xls"/>
           <display:setProperty name="export.xml" value="false" />
            <display:setProperty name="export.csv" value="false"/>
			</display:table></center>
			
		</logic:notEmpty>
	</logic:present></div>	
</html:form>
</html:html>

