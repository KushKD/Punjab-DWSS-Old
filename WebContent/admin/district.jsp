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
	document.districtForm.action="districtAction.do?method=find&d__mode="+d__mode+"&menuId=MST007";
	document.districtForm.submit();
}
function de_repost(){			 
	if(d__mode=='ent_repost') {
		document.districtForm.action="districtAction.do?method=update&d__mode="+d__mode+"&menuId=MST007";
		document.districtForm.submit();
	}
}
function de_modify(){		
	if(d__mode=='ent_modify') { 
		document.districtForm.action="districtAction.do?method=update&d__mode="+d__mode+"&menuId=MST007";
		document.districtForm.submit();
	}
}
function de_remove(){
	if(d__mode=='ent_delete') {
		document.districtForm.action="districtAction.do?method=delete&d__mode="+d__mode+"&menuId=MST007";
		document.districtForm.submit();
	}
}
function de_confirm(){
	if(d__mode=='ent_post') {
		document.districtForm.action="districtAction.do?method=post&d__mode="+d__mode+"&menuId=MST007";
		document.districtForm.submit();
	}
}
function de_add(){
	if(d__mode=='ent_add') {
		document.districtForm.action="districtAction.do?method=save&d__mode="+d__mode+"&menuId=MST007";
		document.districtForm.submit();
	}

	}
function getDistrict(districtId)
{
	document.districtForm.action="districtAction.do?method=populate&districtCode="+districtId+"&d__mode="+d__mode+"&menuId=MST007";
	document.districtForm.submit();
	}

	</script>

<title>District Master</title>

</head>
<html:html>

<body bgcolor="#6699FF">
<center>
<h1>District Master</h1>
</center>
<html:form action="districtAction" styleId="districtForm" method="Post">
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
			<td><label>Circle Code</label></td>
			<td><html:select property="circleCode" styleId="circleCode">
				<html:option value="">Select Circle</html:option>
				<html:options collection="circles" labelProperty="label"
					property="value"></html:options>
			</html:select></td>

		</tr>
		<tr>
			<td nowrap><label>District Code</label></td>
			<td><logic:present name="districtBean" scope="request">
				<html:text property="districtCode"
					value='${districtBean.districtId}' styleId="districtCode" />
			</logic:present> <logic:notPresent name="districtBean" scope="request">
				<html:text property="districtCode" styleId="districtCode" />
			</logic:notPresent></td>
		</tr>
		<tr>
			<td nowrap><label>District Name</label></td>
			<td><logic:present name="districtBean" scope="request">
				<html:text property="districtName"
					value='${districtBean.districtName}' />
			</logic:present> <logic:notPresent name="districtBean" scope="request">
				<html:text property="districtName" />
			</logic:notPresent></td>
		</tr>



	</table>
	</center>
	</fieldset>

	<div id="dispTag"><logic:present name="districtList"
		scope="request">
		<logic:notEmpty name="districtList" scope="request">
			<center><display:table name="districtList" id="district"
				class="mars" style="margin:0 1em 1em 0;">
				<logic:equal value="ent_delete" parameter="d__mode" scope="request">
					<display:column>
						<html:checkbox property="districtCodes"
							value="${district.districtId}"></html:checkbox>
					</display:column>
				</logic:equal>
				<logic:equal value="ent_post" parameter="d__mode" scope="request">
					<display:column>
						<html:checkbox property="districtCodes"
							value="${district.districtId}"></html:checkbox>
					</display:column>
				</logic:equal>
				<display:column title="District Code">
					<a href="javascript:getDistrict('${district.districtId}')">${district.districtId}</a>
				</display:column>
				<display:column property="districtName" title="District Name" />
				<display:column property="circle.circleName" title="Circle Name" />
				<display:column property="status" title="Status" />
			</display:table></center>
		</logic:notEmpty>
	</logic:present></div>
</html:form>
</body>
</html:html>