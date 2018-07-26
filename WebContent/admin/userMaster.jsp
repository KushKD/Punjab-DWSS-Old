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
		document.userMasterForm.action="userMasterAction.do?method=find&d__mode="+d__mode+"&menuId=ADM003";
		document.userMasterForm.submit();
	}
	function de_modify(){		
		if(d__mode=='ent_modify') { 
			document.userMasterForm.action="userMasterAction.do?method=update&d__mode="+d__mode+"&menuId=ADM003";
			document.userMasterForm.submit();
		}
	}
	function de_remove(){
		if(d__mode=='ent_delete') {
			document.userMasterForm.action="userMasterAction.do?method=delete&d__mode="+d__mode+"&menuId=ADM003";
			document.userMasterForm.submit();
		}
	}
	function de_confirm(){
		if(d__mode=='ent_post') {
			document.userMasterForm.action="userMasterAction.do?method=post&d__mode="+d__mode+"&menuId=ADM003";
			document.userMasterForm.submit();
		}
	}
	function de_add(){
		if(d__mode=='ent_add') {
			document.userMasterForm.action="userMasterAction.do?method=save&d__mode="+d__mode+"&menuId=ADM003";
			document.userMasterForm.submit();
		}

		}

	function getUser(userId){
		document.userMasterForm.action="userMasterAction.do?method=populate&userId="+userId+"&d__mode="+d__mode+"&menuId=ADM003";
		document.userMasterForm.submit();
	}	
	
</script>

<script type="text/javascript" src="js/datagrid.js"></script>
</head>
<html:html>
<body bgcolor="#6699FF">
<html:form action="userMasterAction" method="post">
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
	<fieldset><legend>Create User</legend>
	<center>
	<table>
		<tr>
			<td><label>User Id</label></td>
			<td><html:text property="userId" styleId="userId"></html:text></td>

			<td nowrap><label>Enter User Password</label></td>
			<td><html:text property="userPassword"></html:text></td>
		</tr>
		<tr>
			<td><label>User Name</label></td>
			<td><html:text property="userName"></html:text></td>

			<td><label>Select Role</label></td>
			<td><html:select property="roleId" styleId="roleId">
				<html:option value="">Select</html:option>
				<html:options collection="roles" property="value"
					labelProperty="label" />
			</html:select></td>
		</tr>
		<tr>
			<td><label>Employee Id</label></td>
			<td><html:text property="employeeId" styleId="employeeId"></html:text></td>

			<td nowrap><label>User Contact Number</label></td>
			<td><html:text property="userMobile"></html:text></td>
		</tr>
		<tr>
			<td><label>User Email Id</label></td>
			<td><html:text property="userEmail"></html:text></td>
			<td><label>Gender</label></td>
			<td><html:radio property="gender" value="M">Male</html:radio> <html:radio
				property="gender" value="F">Female</html:radio></td>
		</tr>
		<tr>
			<td><label>User Address line-1</label></td>
			<td><html:text property="userAddress1"></html:text></td>
			<td><label>User Address line-2</label></td>
			<td><html:text property="userAddress2"></html:text></td>

		</tr>
	</table>
	</center>
	</fieldset>
	<fieldset><legend>User Location Access Mapping</legend>
	<table>
		<tr>
			<td><label>Add Location</label></td>
			<td><html:select property="locationId" style="width:300px;"
				styleClass="cs1" styleId="locationId">
				<html:option value="">Select Location</html:option>
				<html:options collection="userLocations" labelProperty="label"
					property="value"></html:options>
			</html:select></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>
			<div style="overflow: auto; width: 900px; height: 200px;"><layout:datagrid
				styleClass="DATAGRID" property="userLocationGrid"
				selectionAllowed="true" multipleSelectionAllowed="false"
				model="datagrid">
				<layout:datagridColumn property="locationId" title="Location Id"></layout:datagridColumn>
				<layout:datagridColumn property="userId" title="User Id"
					mode="N,N,N"></layout:datagridColumn>
			</layout:datagrid></div>
			</td>
			<td><img src="images/add.png"
				onclick="StrutsLayout.addDatagridLine('userLocationGrid','locationId')"
				width="20px" /><br>
			<img src="images/delete.png"
				onclick="StrutsLayout.setDatagridLineState('userLocationGrid', 'removed')"
				width="20px" /></td>
		</tr>
	</table>
	</fieldset>
	<div id="dispTag"><logic:present name="loginUserBeans"
		scope="request">
		<logic:notEmpty name="loginUserBeans" scope="request">
			<center><display:table name="loginUserBeans" id="user"
				class="mars" style="margin:0 1em 1em 0;" pagesize="3"
				requestURI="/userMasterAction.do">
				<display:column title="User Id">
					<a href="javascript:getUser('${user.userId}')">${user.userId}</a>
				</display:column>
				<display:column property="userName" title="User Name"
					sortable="true" />
				<display:column property="userTelephone" title="Contact Number"
					sortable="true" />
				<display:column property="userEmail" title="Email" sortable="true" />
				<display:column property="misAuditBean.status" title="Status" />
			</display:table></center>




		</logic:notEmpty>


	</logic:present></div>

</html:form>
</body>
</html:html>