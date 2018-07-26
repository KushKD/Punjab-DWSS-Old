<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/onlynumber.js"></script>
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function de_find(){		
	document.constituencyForm.action="constituencyAction.do?method=find&d__mode="+d__mode+"&menuId=ADM012";
	document.constituencyForm.submit();
}
function de_repost(){			 
	if(d__mode=='ent_repost') {
		document.constituencyForm.action="constituencyAction.do?method=update&d__mode="+d__mode+"&menuId=ADM012";
		document.constituencyForm.submit();
	}
}
function de_modify(){		
	if(d__mode=='ent_modify') { 
		document.constituencyForm.action="constituencyAction.do?method=update&d__mode="+d__mode+"&menuId=ADM012";
		document.constituencyForm.submit();
	}
}
function de_remove(){
	if(d__mode=='ent_delete') {
		document.constituencyForm.action="constituencyAction.do?method=delete&d__mode="+d__mode+"&menuId=ADM012";
		document.constituencyForm.submit();
	}
}
function de_confirm(){
	if(d__mode=='ent_post') {
		document.constituencyForm.action="constituencyAction.do?method=post&d__mode="+d__mode+"&menuId=ADM012";
		document.constituencyForm.submit();
	}
}
function de_add(){
	if(d__mode=='ent_add') {
		document.constituencyForm.action="constituencyAction.do?method=save&d__mode="+d__mode+"&menuId=ADM012";
		document.constituencyForm.submit();
	}

	}
function getConstituency(constituencyId)
{
	document.constituencyForm.action="constituencyAction.do?method=populate&constituencyId="+constituencyId+"&d__mode="+d__mode+"&menuId=ADM012";
	document.constituencyForm.submit();
	}

</script>

<title>Constituency Master</title>

</head>
<html:html>

<body bgcolor="#6699FF">
<center>
<h1>Constituency Master</h1>
</center>
<html:form action="constituencyAction" styleId="constituencyForm"
	method="Post">
	<logic:messagesPresent>
		<div id="errorDiv" class="error displaynone"
			style="width: 470px %; margin-bottom: 7px; height: 13px;"><html:errors />
		</div>
	</logic:messagesPresent>
	<logic:messagesPresent message="true">
		<div id="successDiv" class="success diplaynone" style="width: 470px;">
		<html:messages id="message" message="true">
			<li><bean:write name="message" /></li>
		</html:messages></div>
	</logic:messagesPresent>

	<fieldset><legend>General Information</legend>
	<center>
	<table>
		<tr>
			<td><label>District Id</label></td>
			<td><html:select property="districtId" styleId="districtId">
				<html:option value="">Select</html:option>
				<html:options collection="districts" labelProperty="label"
					property="value"></html:options>
			</html:select></td>

		</tr>
		<tr>
			<td nowrap><label>Constituency Id</label></td>
			<td><logic:present name="constituencyBean" scope="request">
				<html:text property="constituencyId" styleId="constituencyId"
					value='${constituencyBean.constituencyId}' />
			</logic:present> <logic:notPresent name="constituencyBean" scope="request">
				<html:text property="constituencyId" styleId="constituencyId" />
			</logic:notPresent></td>
		</tr>
		<tr>
			<td nowrap><label>Constituency Name</label></td>
			<td><logic:present name="constituencyBean" scope="request">
				<html:text property="constituencyName"
					value='${constituencyBean.constituencyName}' />
			</logic:present> <logic:notPresent name="constituencyBean" scope="request">
				<html:text property="constituencyName" />
			</logic:notPresent></td>
		</tr>



	</table>
	</center>
	</fieldset>

	<div id="dispTag"><logic:present name="constituencyList"
		scope="request">
		<logic:notEmpty name="constituencyList" scope="request">
			<center><display:table name="constituencyList"
				id="constituency" class="mars" style="margin:0 1em 1em 0;"
				requestURI="/constituencyAction.do">

				<display:column title="Constituency Id">
					<a
						href="javascript:getConstituency('${constituency.constituencyId}')">${constituency.constituencyId}</a>
				</display:column>
				<display:column property="constituencyName"
					title="Constituency Name" />
				<display:column property="districtId" title="District Id" />
				<display:column property="misAuditBean.status" title="Status" />
			</display:table></center>
		</logic:notEmpty>
	</logic:present></div>
</html:form>
</body>
</html:html>