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
	document.blockForm.action="blockMasterAction.do?method=find&d__mode="+d__mode+"&menuId=ADM013";
	document.blockForm.submit();
}
function de_repost(){			 
	if(d__mode=='ent_repost') {
		document.blockForm.action="blockMasterAction.do?method=update&d__mode="+d__mode+"&menuId=ADM013";
		document.blockForm.submit();
	}
}
function de_modify(){		
	if(d__mode=='ent_modify') { 
		document.blockForm.action="blockMasterAction.do?method=update&d__mode="+d__mode+"&menuId=ADM013";
		document.blockForm.submit();
	}
}
function de_remove(){
	if(d__mode=='ent_delete') {
		document.blockForm.action="blockMasterAction.do?method=delete&d__mode="+d__mode+"&menuId=ADM013";
		document.blockForm.submit();
	}
}
function de_confirm(){
	if(d__mode=='ent_post') {
		document.blockForm.action="blockMasterAction.do?method=post&d__mode="+d__mode+"&menuId=ADM013";
		document.blockForm.submit();
	}
}
function de_add(){
	if(d__mode=='ent_add') {
		document.blockForm.action="blockMasterAction.do?method=save&d__mode="+d__mode+"&menuId=ADM013";
		document.blockForm.submit();
	}

	}
function getBlock(blockId)
{
	document.blockForm.action="blockMasterAction.do?method=populate&blockId="+blockId+"&d__mode="+d__mode+"&menuId=ADM013";
	document.blockForm.submit();
	}

	</script>

<title>Block Master</title>

</head>
<html:html>

<body bgcolor="#6699FF">
<center>
<h1>Block Master</h1>
</center>
<html:form action="blockMasterAction" styleId="blockForm" method="Post">
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
			<td nowrap><label>Block Id</label></td>
			<td><logic:present name="blockBean" scope="request">
				<html:text property="blockId" value='${blockBean.blockId}'
					styleId="blockId" />
			</logic:present> <logic:notPresent name="blockBean" scope="request">
				<html:text property="blockId" styleId="blockId" />
			</logic:notPresent></td>
		</tr>
		<tr>
			<td nowrap><label>Block Name</label></td>
			<td><logic:present name="blockBean" scope="request">
				<html:text property="blockName" value='${blockBean.blockName}' />
			</logic:present> <logic:notPresent name="blockBean" scope="request">
				<html:text property="blockName" />
			</logic:notPresent></td>
		</tr>



	</table>
	</center>
	</fieldset>

	<div id="dispTag"><logic:present name="blockBeanList"
		scope="request">
		<logic:notEmpty name="blockBeanList" scope="request">
			<center><display:table name="blockBeanList" id="block"
				class="mars" style="margin:0 1em 1em 0;">

				<display:column title="BLock Code">
					<a href="javascript:getBlock('${block.blockId}')">${block.blockId}</a>
				</display:column>
				<display:column property="blockName" title="BLock Name" />
				<display:column property="locationId" title="Location" />
				<display:column property="misAuditBean.status" title="Status" />
			</display:table></center>
		</logic:notEmpty>
	</logic:present></div>
</html:form>
</body>
</html:html>