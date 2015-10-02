<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page session="true" %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <title>Login</title>
</head>
<body onload='document.loginForm.username.focus();'>
<div class="main">
    <div class="header"><a href="/AltrSpringProject/common"><img class="logo"
                                                                 src="<c:url value="/resources/css/logo.png" />"></a>
    </div>
    <div class="content">
        <div id="login-box">
            <h2>Please log-in for access</h2>
            <c:if test="${not empty error}">
                <div class="login-error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>
            <form name='loginForm'
                  action="<c:url value='/j_spring_security_check' />" method='POST'>
                <table>
                    <tr>
                        <td>User:</td>
                        <td><input type='text' name='username'></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type='password' name='password'/></td>
                    </tr>
                    <tr>
                        <td colspan='2'><input name="submit" type="submit"
                                               value="submit" class="button"/></td>
                    </tr>
                </table>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
        </div>
    </div>
    <div class="footer">
        ©Алексей Третьяков, 2015
    </div>
</div>
</body>
</html>