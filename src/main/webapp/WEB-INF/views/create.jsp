<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>
<html>
<head>
    <link href="<c:url value="/resources/css/sosnicky_style.css" />" rel="stylesheet">
    <title>Create Object</title>
</head>
<body>
<div class="main">
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <div class="header">
        <div class="logo-and-opt">
            <div class="user-info">${pageContext.request.userPrincipal.name}</div>
            <img class="userpic" src="<c:url value="${info.user.tPicture.url}" />">
            <c:url value="/j_spring_security_logout" var="logoutUrl"/>
            <form action="${logoutUrl}" method="post" id="logoutForm">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
            <script>
                function formSubmit() {
                    document.getElementById("logoutForm").submit();
                }
            </script>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <a href="javascript:formSubmit()" class="option">Log off</a>
            </c:if>
        </div>
    </div>
    <div class="content">
        <form action="" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <input type="text" name="newname">
            <input type="submit">
        </form>
    </div>
</div>
</body>
</html>
