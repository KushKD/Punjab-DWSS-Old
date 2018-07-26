
<%@page import="com.prwss.mis.common.util.MISConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<script type="text/javascript" src="js/codethatcalendarstd.js"></script>
-->
<link href="css/layout.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function de_find() {
		document.messageBrodcastForm.action = "messageBrodcastAction.do?method=find&d__mode="+ d__mode+"&menuId=ADM005";
		document.messageBrodcastForm.submit();
	}
	function de_modify() {
		if (d__mode == 'ent_modify') {
			document.messageBrodcastForm.action = "messageBrodcastAction.do?method=update&d__mode="+ d__mode+"&menuId=ADM005";
			document.messageBrodcastForm.submit();
		}
	}
	function de_remove() {
		if (d__mode == 'ent_delete') {
			document.messageBrodcastForm.action = "messageBrodcastAction.do?method=delete&d__mode="+ d__mode+"&menuId=ADM005";
			document.messageBrodcastForm.submit();
		}
	}
	function de_confirm() {
		if (d__mode == 'ent_post') {
			document.messageBrodcastForm.action = "messageBrodcastAction.do?method=post&d__mode="+ d__mode+"&menuId=ADM005";
			document.messageBrodcastForm.submit();
		}
	}
	function de_add() {
		if (d__mode == 'ent_add') {
			document.messageBrodcastForm.action = "messageBrodcastAction.do?method=save&d__mode="+ d__mode+"&menuId=ADM005";
			document.messageBrodcastForm.submit();
		}
	}
	 
</script>
<link href="css/form2.css" rel="stylesheet" type="text/css" />
</head>
<html:html>

<body>
<html:form action="messageBrodcastAction" method="post"
	enctype="multipart/form-data">
	<logic:messagesPresent>
		<div id="errorDiv" class="error displaynone"
			style="width: 470px %; margin-bottom: 7px; height: 13px;"><html:errors
			bundle="admin" /></div>
	</logic:messagesPresent>
	<logic:messagesPresent message="true">
		<div id="successDiv" class="success diplaynone" style="width: 470px;">
		<html:messages id="message" bundle="admin" message="true">
			<li><bean:write name="message" /></li>
		</html:messages></div>
	</logic:messagesPresent>
	<br>

	<fieldset><legend>Message Detail</legend>
	<center><br>
	<table>
		<tr>
			<td>Message Brodcast Number (System Generated)</td>
			<td><html:text property="messageId" styleId="MessageId"
				onkeypress="return validateKey(event,	this,'9@20@2')"></html:text></td>
		</tr>
		<tr>
			<td>Description</td>
			<td colspan="4"><html:textarea property="messageDetail"
				cols="30" rows="5" styleId="messageDetail"></html:textarea>
		</tr>
		<tr>
			<td>Last date for document availability on website</td>
			<td><html:text property="expiryDate" styleId="expiryDateId"
				readonly="readonly"></html:text></td>
		</tr>
	</table>
	</center>
	</fieldset>



</html:form>
</body>
</html:html>