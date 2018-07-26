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
		document.subdivisionalForm.action="subdivisionalAction.do?method=find&d__mode="+d__mode+"&menuId=ADM011";
		document.subdivisionalForm.submit();
	}
	function de_modify(){		
		if(d__mode=='ent_modify') { 
			document.subdivisionalForm.action="subdivisionalAction.do?method=update&d__mode="+d__mode+"&menuId=ADM011";
			document.subdivisionalForm.submit();
		}
	}
	function de_remove(){
		if(d__mode=='ent_delete') {
			document.subdivisionalForm.action="subdivisionalAction.do?method=delete&d__mode="+d__mode+"&menuId=ADM011";
			document.subdivisionalForm.submit();
		}
	}
	function de_confirm(){
		if(d__mode=='ent_post') {
			document.subdivisionalForm.action="subdivisionalAction.do?method=post&d__mode="+d__mode+"&menuId=ADM011";
			document.subdivisionalForm.submit();
		}
	}
	function de_add(){
		if(d__mode=='ent_add') {
			document.subdivisionalForm.action="subdivisionalAction.do?method=save&d__mode="+d__mode+"&menuId=ADM011";
			document.subdivisionalForm.submit();
		}

		}

	function de_repost(){				 
		if(d__mode=='ent_repost') {
			document.subdivisionalForm.action="subdivisionalAction.do?method=update&d__mode="+d__mode+"&menuId=ADM011";
			document.subdivisionalForm.submit();
		}
	}

	function getSubDivision(subdivisionId){
		document.subdivisionalForm.action="subdivisionalAction.do?method=populate&subdivisionId="+subdivisionId+"&d__mode="+d__mode+"&menuId=ADM011";
		document.subdivisionalForm.submit();
	}	
	
</script>

<script type="text/javascript" src="js/datagrid.js"></script>
</head>
<html:html>
<body bgcolor="#6699FF">
<html:form action="subdivisionalAction" method="post">
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
	<fieldset><legend>Sub-Divisional Master</legend>
	<center>
	<table>
		<tr>
			<td><label>District </label></td>
			<td><html:select property="districtId" styleId="districtId"
				onchange="ajaxFunction('subdivisionalAction.do?districtId='+document.getElementById('districtId').value+'&method=fetchDivisionalOffice', 'divisionId'); ">

				<html:options collection="districts" labelProperty="label"
					property="value"></html:options>
			</html:select></td>

		</tr>
		<tr>
			<td><label>Division </label></td>
			<td><logic:present name="subdivisionalBean" scope="request">
				<html:text property="divisionId" styleId="divisionId"
					value='${subdivisionalBean.division.divisionalName}->${subdivisionalBean.division.divisionalId}' />
			</logic:present> <logic:notPresent name="subdivisionalBean" scope="request">
				<html:select property="divisionId" styleId="divisionId">

				</html:select>
			</logic:notPresent></td>

		</tr>
		<tr>
			<td nowrap><label>Sub-Divisional Id</label></td>
			<td><logic:present name="subdivisionalBean" scope="request">
				<html:text property="subdivisionId" styleId="subdivisionId"
					value='${subdivisionalBean.subdivisionId}' />
			</logic:present> <logic:notPresent name="subdivisionalBean" scope="request">
				<html:text property="subdivisionId" styleId="subdivisionId" />
			</logic:notPresent></td>
		</tr>
		<tr>
			<td nowrap><label>Sub-Divisional Name</label></td>
			<td><logic:present name="subdivisionalBean" scope="request">
				<html:text property="subdivisionName"
					value='${subdivisionalBean.subdivisionName}' />
			</logic:present> <logic:notPresent name="subdivisionalBean" scope="request">
				<html:text property="subdivisionName" />
			</logic:notPresent></td>
		</tr>



	</table>
	</center>
	</fieldset>

	<div id="dispTag"><logic:present name="subdivisionalList"
		scope="request">
		<logic:notEmpty name="subdivisionalList" scope="request">
			<center><display:table name="subdivisionalList"
				id="subdivision" class="mars" style="margin:0 1em 1em 0;"
				pagesize="3" requestURI="/subdivisionalAction.do">
				<display:column title="Role Id">
					<a href="javascript:getSubDivision('${subdivision.subdivisionId}')">${subdivision.subdivisionId}</a>
				</display:column>
				<display:column property="subdivisionName" title="Role Name"
					sortable="true" />
				<display:column property="division.divisionalId"
					title="Division Name" />
				<display:column property="status" title="Status" />
			</display:table></center>




		</logic:notEmpty>


	</logic:present></div>

</html:form>
</body>
</html:html>