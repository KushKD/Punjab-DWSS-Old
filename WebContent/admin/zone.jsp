<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/onlynumber.js"></script>
<script type="text/javascript" src="js/calendarDateInput.js"></script>
<script type="text/javascript">
	function getZone(zoneId){
		document.zoneForm.action="zoneAction.do?method=populate&zoneId="+zoneId+"&d__mode="+d__mode+"&menuId=MST004";
		document.zoneForm.submit();
	}
	function de_find(){		
		document.zoneForm.action="zoneAction.do?method=find&d__mode="+d__mode+"&menuId=MST004";
		document.zoneForm.submit();
	}
	function de_repost(){			 
		if(d__mode=='ent_repost') {
			document.zoneForm.action="zoneAction.do?method=update&d__mode="+d__mode+"&menuId=MST004";
			document.zoneForm.submit();
		}
	}
	function de_modify(){		
		if(d__mode=='ent_modify') { 
			document.zoneForm.action="zoneAction.do?method=update&d__mode="+d__mode+"&menuId=MST004";
			document.zoneForm.submit();
		}
	}
	function de_remove(){
		if(d__mode=='ent_delete') {
			document.zoneForm.action="zoneAction.do?method=delete&d__mode="+d__mode+"&menuId=MST004";
			document.zoneForm.submit();
		}
	}
	function de_confirm(){
		if(d__mode=='ent_post') {
			document.zoneForm.action="zoneAction.do?method=post&d__mode="+d__mode+"&menuId=MST004";
			document.zoneForm.submit();
		}
	}
	function de_add(){
		if(d__mode=='ent_add') {
			document.zoneForm.action="zoneAction.do?method=save&d__mode="+d__mode+"&menuId=MST004";
			document.zoneForm.submit();
		}

		}

	function resetForm(){
		
		   var form = document.getElementById("zoneForm").elements;

		   for(i=0;i<=form.length;i++)
		   {
			   if (form[i].getAttribute('type') == 'submit' || form[i].getAttribute('type') == 'button') {
				   continue;
			   }
			   else{ 
	form[i].value = "";
				   }
	}
						
			   }
</script>
<title>Zone Master</title>
<link href="css/form.css" rel="stylesheet" type="text/css">


</head>

<html:html>

<body bgcolor="#6699FF">

<html:form action="zoneAction" styleId="zoneForm">
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

	<fieldset><legend>Zone Master</legend>
	<center>
	<table>

		<tr>
			<td><label>Zone Code</label></td>
			<td><logic:present name="zoneBean" scope="request">
				<html:text property="zoneId" styleId="zoneId"
					value="${zoneBean.zoneId}" />
			</logic:present> <logic:notPresent name="zoneBean" scope="request">
				<html:text property="zoneId" styleId="zoneId" />
			</logic:notPresent></td>
		</tr>

		<tr>
			<td><label>Zone Name</label></td>
			<td><logic:present name="zoneBean" scope="request">
				<html:text property="zoneName" value="${zoneBean.zoneName}"></html:text>
			</logic:present> <logic:notPresent name="zoneBean" scope="request">
				<html:text property="zoneName"></html:text>
			</logic:notPresent></td>
		</tr>
	</table>
	<br>
	</center>
	</fieldset>
	<div id="dispTag"><logic:present name="zoneBeanList"
		scope="request">
		<logic:notEmpty name="zoneBeanList" scope="request">
			<center><display:table name="zoneBeanList" id="zone"
				class="mars" style="margin:0 1em 1em 0;" pagesize="3"
				requestURI="/zoneAction.do">
				<logic:equal value="ent_delete" parameter="d__mode" scope="request">
					<display:column>
						<html:checkbox property="zoneIds" value="${zone.zoneId}"></html:checkbox>
					</display:column>
				</logic:equal>
				<logic:equal value="ent_post" parameter="d__mode" scope="request">
					<display:column>
						<html:checkbox property="zoneIds" value="${zone.zoneId}"></html:checkbox>
					</display:column>
				</logic:equal>
				<display:column title="Zone Code">
					<a href="javascript:getZone('${zone.zoneId}')">${zone.zoneId}</a>
				</display:column>
				<display:column property="zoneName" title="Zone Name"
					sortable="true" />
				<display:column property="status" title="Status" />
			</display:table></center>




		</logic:notEmpty>


	</logic:present></div>
</html:form>
</body>
</html:html>