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
			document.circleForm.action="circleAction.do?method=find&d__mode="+d__mode+"&menuId=MST005";
			document.circleForm.submit();
		}
		function de_repost(){			 
			if(d__mode=='ent_repost') {
				document.circleForm.action="circleAction.do?method=update&d__mode="+d__mode+"&menuId=MST005";
				document.circleForm.submit();
			}
		}
		function de_modify(){		
			if(d__mode=='ent_modify') { 
				document.circleForm.action="circleAction.do?method=update&d__mode="+d__mode+"&menuId=MST005";
				document.circleForm.submit();
			}
		}
		function de_remove(){
			if(d__mode=='ent_delete') {
				document.circleForm.action="circleAction.do?method=delete&d__mode="+d__mode+"&menuId=MST005";
				document.circleForm.submit();
			}
		}
		function de_confirm(){
			if(d__mode=='ent_post') {
				document.circleForm.action="circleAction.do?method=post&d__mode="+d__mode+"&menuId=MST005";
				document.circleForm.submit();
			}
		}
		function de_add(){
			if(d__mode=='ent_add') {
				document.circleForm.action="circleAction.do?method=save&d__mode="+d__mode+"&menuId=MST005";
				document.circleForm.submit();
			}

			}
		function getCircle(circleId)
		{
			document.circleForm.action="circleAction.do?method=populate&circleId="+circleId+"&d__mode="+d__mode+"&menuId=MST005";
			document.circleForm.submit();
			}
		
		</script>

<title>Circle Master</title>

</head>
<html:html>

<body bgcolor="#6699FF">

<html:form action="circleAction" styleId="circleForm" method="Post">

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
	<fieldset><legend>Circle Master</legend>
	<center>
	<table>

		<tr>
			<td><label>Zone Name</label></td>
			<td><html:select property="zoneId" styleId="zoneId">
				<html:option value="">Select Zone</html:option>
				<html:options collection="zones" labelProperty="label"
					property="value"></html:options>
			</html:select></td>

		</tr>
		<tr>
			<td><label>Circle Code</label></td>
			<td><logic:present name="circleBean" scope="request">
				<html:text property="circleId" value='${circleBean.circleId}'
					styleId="circleId" />
			</logic:present> <logic:notPresent name="circleBean" scope="request">
				<html:text property="circleId" styleId="circleId" />
			</logic:notPresent></td>
		</tr>
		<tr>
			<td><label>Circle Name</label></td>
			<td><logic:present name="circleBean" scope="request">
				<html:text property="circleName" value='${circleBean.circleName}'></html:text>
			</logic:present> <logic:notPresent name="circleBean" scope="request">
				<html:text property="circleName"></html:text>
			</logic:notPresent></td>
		</tr>




	</table>

	<br>
	<font color="red"></font></center>
	</fieldset>
	<div id="dispTag"><logic:present name="circleBeanList"
		scope="request">
		<logic:notEmpty name="circleBeanList" scope="request">
			<center><display:table name="circleBeanList" id="circle"
				class="mars" style="margin:0 1em 1em 0;">
				<logic:equal value="ent_delete" parameter="d__mode" scope="request">
					<display:column>
						<html:checkbox property="circleIds" value="${circle.circleId}"></html:checkbox>
					</display:column>
				</logic:equal>
				<logic:equal value="ent_post" parameter="d__mode" scope="request">
					<display:column>
						<html:checkbox property="circleIds" value="${circle.circleId}"></html:checkbox>
					</display:column>
				</logic:equal>
				<display:column title="Circle Code">
					<a href="javascript:getCircle('${circle.circleId}')">${circle.circleId}</a>
				</display:column>
				<display:column property="circleName" title="Circle Name" />
				<display:column property="zone.zoneName" title="Zone Name" />
				<display:column property="status" title="Status" />
			</display:table></center>
		</logic:notEmpty>
	</logic:present></div>
</html:form>
</body>
</html:html>