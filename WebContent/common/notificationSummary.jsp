<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" autoFlush="true" errorPage="/errorPage.jsp"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<display:table name="componentBeanList" id="component" class="mars"
	style="margin:0 1em 1em 0;">
	<display:column property="componentName" title="Component Name" />
	<display:column property="componentDescription"
		title="Component Description" />
	<display:column property="misAuditBean.status" title="Status" />
</display:table>
</body>
</html>