<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script type="text/javascript">

	function de_find(){		
		document.roleMasterForm.action="roleMasterAction.do?method=find&d__mode="+d__mode+"&menuId=ADM006";
		document.roleMasterForm.submit();
	}
	function de_modify(){		
		if(d__mode=='ent_modify') { 
			document.roleMasterForm.action="roleMasterAction.do?method=update&d__mode="+d__mode+"&menuId=ADM006";
			document.roleMasterForm.submit();
		}
	}
	function de_remove(){
		if(d__mode=='ent_delete') {
			document.roleMasterForm.action="roleMasterAction.do?method=delete&d__mode="+d__mode+"&menuId=ADM006";
			document.roleMasterForm.submit();
		}
	}
	function de_confirm(){
		if(d__mode=='ent_post') {
			document.roleMasterForm.action="roleMasterAction.do?method=post&d__mode="+d__mode+"&menuId=ADM006";
			document.roleMasterForm.submit();
		}
	}
	function de_add(){
		if(d__mode=='ent_add') {
			document.roleMasterForm.action="roleMasterAction.do?method=save&d__mode="+d__mode+"&menuId=ADM006";
			document.roleMasterForm.submit();
		}

		}

	function de_repost(){				 
		if(d__mode=='ent_repost') {
			document.roleMasterForm.action="roleMasterAction.do?method=update&d__mode="+d__mode+"&menuId=ADM006";
			document.roleMasterForm.submit();
		}
	}

	function getRole(roleId){
		document.roleMasterForm.action="roleMasterAction.do?method=populate&roleId="+roleId+"&d__mode="+d__mode+"&menuId=ADM006";
		document.roleMasterForm.submit();
	}	
	
</script>

<script type="text/javascript" src="js/datagrid.js"></script>
</head>
<html:html>
<body bgcolor="#6699FF">
<html:form action="roleMasterAction" method="post">
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
	<fieldset><legend>Create Role</legend>
	<center>
	<table>
		<tr>
			<td><label>Role Id</label></td>
			<td><logic:present name="roleBean" scope="request">
				<html:text property="roleId" styleId="roleId"
					value="${roleBean.roleId}" size="110%" styleClass="ci5" />
			</logic:present> <logic:notPresent name="roleBean" scope="request">
				<html:text property="roleId" styleId="roleId" size="110%"
					styleClass="ci5" />
			</logic:notPresent></td>
		</tr>
		<tr>
			<td><label>Role Name</label></td>
			<td><logic:present name="roleBean">
				<html:text property="roleName" styleId="roleName"
					value="${roleBean.roleName}" size="110%" styleClass="ci5" />
			</logic:present> <logic:notPresent name="roleBean">
				<html:text property="roleName" styleId="roleName" size="110%"
					styleClass="ci5" />
			</logic:notPresent></td>
		</tr>
		<tr>
			<td><label>Component Description</label></td>
			<td><logic:present name="roleBean">
				<html:text property="roleDesc" styleId="roleDesc"
					value="${roleBean.roleDesc}" size="110%" styleClass="ci5" />
			</logic:present> <logic:notPresent name="roleBean">
				<html:textarea property="roleDesc" styleId="roleDesc" cols="82"
					rows="3" styleClass="ci5" />
			</logic:notPresent></td>
		</tr>
	</table>
	</center>
	</fieldset>

	<div id="dispTag"><logic:present name="roleBeans" scope="request">
		<logic:notEmpty name="roleBeans" scope="request">
			<center><display:table name="roleBeans" id="role"
				class="mars" style="margin:0 1em 1em 0;" pagesize="3"
				requestURI="/roleMasterAction.do">
				<display:column title="Role Id">
					<a href="javascript:getRole('${role.roleId}')">${role.roleId}</a>
				</display:column>
				<display:column property="roleName" title="Role Name"
					sortable="true" />
				<display:column property="roleDesc" title="Role Description"
					sortable="true" />
				<display:column property="misAuditBean.status" title="Status" />
			</display:table></center>




		</logic:notEmpty>


	</logic:present></div>

</html:form>
</body>
</html:html>