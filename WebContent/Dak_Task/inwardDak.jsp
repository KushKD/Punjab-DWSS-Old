<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
--><script language="JavaScript" src="js/calendar_us.js"></script>
<script type="text/javascript">
		function de_find(){		
			document.InwardDakForm.action="inwardDakAction.do?method=find&d__mode="+d__mode+"&menuId=DTM001";
			document.InwardDakForm.submit();
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.InwardDakForm.action="inwardDakAction.do?method=update&d__mode="+d__mode+"&menuId=DTM001";
				document.InwardDakForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.InwardDakForm.action="inwardDakAction.do?method=delete&d__mode="+d__mode+"&menuId=DTM001";
				document.InwardDakForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.InwardDakForm.action="inwardDakAction.do?method=post&d__mode="+d__mode+"&menuId=DTM001";
				document.InwardDakForm.submit();
			}
		}		
		function de_add(){
			if(d__mode=='ent_add') {
				document.InwardDakForm.action="inwardDakAction.do?method=save&d__mode="+d__mode+"&menuId=DTM001";
				document.InwardDakForm.submit();
			}
		}

		function getDocument(documentId,locationId){
			document.InwardDakForm.action="inwardDakAction.do?method=populate&documentNumber="+documentId+"&locationId="+locationId+"&d__mode="+d__mode+"&menuId=DTM001";
			document.InwardDakForm.submit();
		}	 
	</script>
	<link href="css/form.css" rel="stylesheet" type="text/css">
	<link href="css/displaytag.css" rel="stylesheet" type="text/css" />	
	<link rel="stylesheet" href="css/calendar.css">
</head>
<html:html>
<body bgcolor="#6699FF" >
<html:form action="/inwardDakAction">

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
	
<fieldset><legend>Inward Dak</legend>
	<center>
	<table>
		<tr>
			<td ><label>Location</label></td>
			<td ><html:select property="locationId" styleId="locationId" styleClass="cs1">
				<html:option value="">Select Location</html:option>
				<html:options collection="userLocations" labelProperty="label"
					property="value"></html:options></html:select></td>						
			<td><label>Document No.</label></td>
			<td><html:text property="documentNo" styleId="documentNo" styleClass="ci5"/>
			
		</tr>
		<tr>
			<td><label>Document Type</label></td>
			<td><html:select property="documentType" styleId="DocumentType" styleClass="ci5">
			<html:options collection="documentTypes" labelProperty="label" property="value"/>
			</html:select></td>
			<td><label>Document Ref No.</label></td>
			<td><html:text property="documentReferenceNo" styleId="documentReferenceNo" styleClass="ci5"/></td>
		</tr>
		
		<tr>
			<td><label>Receipt Date</label></td>
			<td><html:text property="recieptDate" styleId="recieptDate" styleClass="ci5" readonly="readonly"/>
			
			</td>
			<td></td>
			<td></td>
		</tr>
		<tr>			
		</tr>
		<tr>
			<td><label>Sender Name</label></td>
			<td colspan="4"><html:text property="senderName" size="68"  styleId="senderName" styleClass="ci5"/></td>
		</tr>
		<tr>
			<td><label>Subject</label></td>
			<td colspan="4"><html:text property="subject" size="68" styleId="subject" styleClass="ci5"/></td>
		</tr>
		<tr>
			<td><label>Sender Address</label></td>
			<td colspan="4"><html:textarea property="senderAddress" cols="52" rows="2" styleId="senderAddress" styleClass="ci5"></html:textarea></td>
		</tr>
		<tr>
			<td><label>Forwarded To</label></td>
			<td colspan="4"><html:text property="forwardedTo" size="68" styleId="subject" styleClass="ci5"/></td>
		</tr>
		<tr>
		<td><html:hidden property="committeeId"></html:hidden></td>
		</tr>
				
	</table>
	</center>
	</fieldset>
	<div id="dispTag">
	<logic:present name="inwardDakList" scope="request">
		<logic:notEmpty name="inwardDakList" scope="request">
			<center><display:table name="inwardDakList" id="inward" class="mars" style="margin:0 1em 1em 0;" pagesize="10" requestURI="/inwardDakAction.do" export="true">
				<display:column title="Click to Display" media="html"><a href="javascript:getDocument('${inward.documentNo}','${inward.locationId}')">${inward.documentNo}</a></display:column>
				<display:column property="documentNo" title="Document Number" sortable="true"/>
				<display:column property="documentRefNo" title="Document Reference Number" sortable="true"/>
				<display:column property="receiptDate" title="Letter Receipt Date" decorator="com.prwss.mis.common.util.DateColumnDecorator"/>
				<display:column property="senderName" title="Sender Name" />
				<display:column property="subject" title="Subject" />
				<display:column property="forwardedTo" title="Forwarded To" />
		   <display:setProperty name="export.excel.filename" value="inwardDakCheckList.xls"/>
           <display:setProperty name="export.xml" value="false" />
            <display:setProperty name="export.csv" value="false"/>
			</display:table></center>
			
		</logic:notEmpty>
	</logic:present></div>	
</html:form>
</html:html>
