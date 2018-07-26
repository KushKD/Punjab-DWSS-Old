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
		document.divisionalForm.action="divisionalAction.do?method=find&d__mode="+d__mode+"&menuId=ADM010";
		document.divisionalForm.submit();
	}
	function de_modify(){		
		if(d__mode=='ent_modify') { 
			document.divisionalForm.action="divisionalAction.do?method=update&d__mode="+d__mode+"&menuId=ADM010";
			document.divisionalForm.submit();
		}
	}
	function de_remove(){
		if(d__mode=='ent_delete') {
			document.divisionalForm.action="divisionalAction.do?method=delete&d__mode="+d__mode+"&menuId=ADM010";
			document.divisionalForm.submit();
		}
	}
	function de_confirm(){
		if(d__mode=='ent_post') {
			document.divisionalForm.action="divisionalAction.do?method=post&d__mode="+d__mode+"&menuId=ADM010";
			document.divisionalForm.submit();
		}
	}
	function de_add(){
		if(d__mode=='ent_add') {
			document.divisionalForm.action="divisionalAction.do?method=save&d__mode="+d__mode+"&menuId=ADM010";
			document.divisionalForm.submit();
		}

		}

	function de_repost(){				 
		if(d__mode=='ent_repost') {
			document.divisionalForm.action="divisionalAction.do?method=update&d__mode="+d__mode+"&menuId=ADM010";
			document.divisionalForm.submit();
		}
	}

	function getRole(divisionalId){
		document.divisionalForm.action="divisionalAction.do?method=populate&divisionalId="+divisionalId+"&d__mode="+d__mode+"&menuId=ADM010";
		document.divisionalForm.submit();
	}	
	
</script>

<script type="text/javascript" src="js/datagrid.js"></script>
</head>
<html:html>
<body bgcolor="#6699FF">
<html:form action="divisionalAction" method="post">
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
	<fieldset><legend>Divisional Master</legend>
	<center>
	<table>
		<tr>
			<td><label>District </label></td>
			<td><html:select property="districtId" styleId="districtId">
				<html:option value="">Select District</html:option>
				<html:options collection="districts" labelProperty="label"
					property="value"></html:options>
			</html:select></td>

		</tr>
		<tr>
			<td nowrap><label>Divisional Id</label></td>
			<td><logic:present name="divisionalBean" scope="request">
				<html:text property="divisionalId"
					value='${divisionalBean.divisionalId}' styleId="divisionalId" />
			</logic:present> <logic:notPresent name="divisionalBean" scope="request">
				<html:text property="divisionalId" styleId="divisionalId" />
			</logic:notPresent></td>
		</tr>
		<tr>
			<td nowrap><label>Divisional Name</label></td>
			<td><logic:present name="divisionalBean" scope="request">
				<html:text property="divisionalName"
					value='${divisionalBean.divisionalName}' />
			</logic:present> <logic:notPresent name="divisionalBean" scope="request">
				<html:text property="divisionalName" />
			</logic:notPresent></td>
		</tr>



	</table>
	</center>
	</fieldset>

	<div id="dispTag"><logic:present name="divisionalList"
		scope="request">
		<logic:notEmpty name="divisionalList" scope="request">
			<center><display:table name="divisionalList" id="divisional"
				class="mars" style="margin:0 1em 1em 0;" pagesize="3"
				requestURI="/divisionalAction.do">
				<display:column title="Role Id">
					<a href="javascript:getRole('${divisional.divisionalId}')">${divisional.divisionalId}</a>
				</display:column>
				<display:column property="divisionalName" title="Role Name"
					sortable="true" />
				<display:column property="district.districtName"
					title="District Name" />
				<display:column property="status" title="Status" />
			</display:table></center>




		</logic:notEmpty>


	</logic:present></div>

</html:form>
</body>
</html:html>