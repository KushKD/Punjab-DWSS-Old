<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.application-servers.com/layout"
	prefix="layout"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script type="text/javascript">

	function de_find(){		
		document.rolePermissionForm.action="rolePermissionAction.do?method=find&d__mode="+d__mode+"&menuId=ADM002";
		document.rolePermissionForm.submit();
	}
	function de_modify(){		
		if(d__mode=='ent_modify') { 
			document.rolePermissionForm.action="rolePermissionAction.do?method=update&d__mode="+d__mode+"&menuId=ADM002";
			document.rolePermissionForm.submit();
		}
	}
	function de_remove(){
		if(d__mode=='ent_delete') {
			document.rolePermissionForm.action="rolePermissionAction.do?method=delete&d__mode="+d__mode+"&menuId=ADM002";
			document.rolePermissionForm.submit();
		}
	}
	function de_confirm(){
		if(d__mode=='ent_post') {
			document.rolePermissionForm.action="rolePermissionAction.do?method=post&d__mode="+d__mode+"&menuId=ADM002";
			document.rolePermissionForm.submit();
		}
	}
	function de_add(){
		if(d__mode=='ent_add') {
			document.rolePermissionForm.action="rolePermissionAction.do?method=save&d__mode="+d__mode+"&menuId=ADM002";
			document.rolePermissionForm.submit();
		}
	}
	
</script>
<link href="css/layout.css" rel="stylesheet" type="text/css">
<link href="css/form.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/datagrid.js"></script>
</head>
<html:html>
<body bgcolor="#6699FF">
<html:form action="rolePermissionAction" styleId="rolePermissionForm"
	method="post">
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
	<fieldset><legend>Role wise Permissions</legend>
	<center>
	<table>
		<tr>
			<td><label>Select Role</label></td>
			<td><html:select property="role_id" style="width:250px;"
				styleId="roleId">
				<html:option value="">Select </html:option>
				<html:options collection="roles" property="value"
					labelProperty="label" />
			</html:select></td>
		</tr>
		<tr>
			<td><label>Menu Id</label></td>
			<td><html:select property="menuId" styleId="menuId"
				onchange="ajaxFunctionForText('rolePermissionAction.do?menuid='+this.value+'&method=fetchMenuName', 'menuName')">
				<html:option value="">Select</html:option>
				<html:options collection="menuids" property="value"
					labelProperty="label"></html:options>
			</html:select></td>
			<td><label>Menu Name</label></td>
			<td><html:text property="menuName" styleId="menuName"></html:text></td>
		</tr>
	</table>
	<table>
		<tr>
			<td><input type="checkBox" id="canInquire"
				style="visibility: hidden;" /></td>
			<td><input type="checkBox" id="canAdd"
				style="visibility: hidden;" /></td>
			<td><input type="checkBox" id="canModify"
				style="visibility: hidden;" /></td>
			<td><input type="checkBox" id="canDelete"
				style="visibility: hidden;" /></td>
			<td><input type="checkBox" id="canPost"
				style="visibility: hidden;" /></td>
		</tr>
	</table>
	<table>
		<tr>
			<td><layout:datagrid property="rolePermissionGrid"
				styleClass="DATAGRID" selectionAllowed="true"
				multipleSelectionAllowed="true" model="datagrid">
				<layout:datagridColumn property="menuId" title="Menu Id"
					mode="I,I,I" />
				<layout:datagridColumn property="menuName" title="Menu Name"
					mode="I,I,I" />
				<layout:datagridCheckbox property="canInquire" title="Inquire" />
				<layout:datagridCheckbox property="canAdd" title="Add" />
				<layout:datagridCheckbox property="canModify" title="Modify" />
				<layout:datagridCheckbox property="canDelete" title="Delete" />
				<layout:datagridCheckbox property="canPost" title="Post" />
			</layout:datagrid></td>
			<td><img src="images/add.png"
				onclick="StrutsLayout.addDatagridLine('rolePermissionGrid','menuId~menuName~canInquire~canAdd~canModify~canDelete~canPost')"
				width="20px" /> <br>
			<img src="images/delete.png"
				onclick="StrutsLayout.setDatagridLineState('rolePermissionGrid', 'removed')"
				width="20px" /></td>
		</tr>
	</table>
	</center>
	</fieldset>

</html:form>
</body>
</html:html>
