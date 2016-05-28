<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link href="${pageContext.request.contextPath}/resources/theme1/css/sample2705.css" rel="stylesheet" >
<html>
<head>
	<title><spring:message text="User Page"/></title>
</head>
<h1 class="choseLang"><span><spring:message code="choseLang"/>:</span>
				<a href=
				   <c:url value=''>
					   <c:param name="locale" value="en"/>
					   <c:param name="query" value="${param.query}"/>
					</c:url>
				>en</a> / <a href=
					 <c:url value=''>
						<c:param name="locale" value="ru"/>
					   <c:param name="query" value="${param.query}"/>
					</c:url>
				>ru</a>
</h1>
<body>
<c:url var="addAction" value="/jdbcInsert" ></c:url>
<c:url var="findAction" value="/jdbcSearchByName" ></c:url>
<h1>
	<spring:message code="addUser"/>
</h1>
<form:form modelAttribute="user" method="POST" action="${addAction}">
	<form:input path="username"/>
	<form:errors path="username" cssClass="error" /><br/>
	<input type="submit" value=<spring:message code='button.add'/>>
</form:form>
<br>
<h1>
	<spring:message code="findUser"/>
</h1>
<form action="${findAction}" method="get">
	<input type="text" value="" name="query" placeholder="<spring:message code='placeholder.findUser'/>">
	<input type="submit" value=<spring:message code='button.find'/>>
</form>
<h1>
	<spring:message code="userList"/>
</h1>
<c:if test="${!empty resultObject}">
	<table class="tg">
	<tr>
		<th width="80">	<spring:message code="td.UserID"/> </th>
		<th width="120"><spring:message code="td.UserName"/> </th>
	</tr>
	<c:forEach items="${resultObject}" var="user">
		<tr>
			<td>${user.idUser}</td>
			<td>${user.username}</td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
