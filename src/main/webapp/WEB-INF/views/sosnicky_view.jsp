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
    <title>${info.tObject.name}</title>
</head>
<body>
<div class="main">
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <div class="header">
        <a href="/AltrSpringProject/common"><img class="logo" src="<c:url value="/resources/css/logo.png" />"></a>

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
        <div class="tools-and-groups">
            <div class="groups">
                <c:forEach items="${info.subgroups}" var="subgroup">
                    <a href="?id=${info.tObject.id}&tab=${subgroup.urlSubgroup}"
                       class="<c:choose><c:when test="${subgroup.active==true}">groupIsActive</c:when><c:otherwise>group</c:otherwise></c:choose>">${subgroup.tabName}</a>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="content">
        <c:if test="${info.externalActiveSubgroup.subgroupType == 0}">
            <div class="parameters">
                <form action="" method="get">
                    <input type="hidden" name="id" value="${info.tObject.id}">
                    <input type="hidden" name="tab" value="${info.externalActiveSubgroup.urlSubgroup}">
                    <input type="hidden" name="mode" value="1">
                    <input type="submit" value="Edit" class="button-objects">
                </form>
                <table>
                    <tr>
                        <td class="td-content">Name:</td>
                        <td class="td-content-value">${info.tObject.name}</td>
                    </tr>
                    <tr>
                        <td class="td-content">Description:</td>
                        <td class="td-content-value">${info.tObject.description}</td>
                    </tr>
                    <tr>
                        <td class="td-content">Object Type:</td>
                        <td class="td-content-value">${info.tObject.objectType.name}</td>
                    </tr>
                </table>
            </div>
        </c:if>
        <c:forEach items="${info.externalActiveSubgroup.groups}" var="group">
            <script>
                function goToURL(command, id) {
                    var selected = [];
                    $('.parameters input:checked').each(function () {
                        selected.push($(this).attr('name'));
                    });
                    if (selected.length == 0) {
                        alert("You must choose at least one object");
                        return false;
                    }
                    var url = "/AltrSpringProject/common?";
                    for (var i = 0; i < selected.length; i++) {
                        url = url + i + "=" + selected[i] + "&";
                    }
                    url = url + "objectid=" + '<c:out value="${info.tObject.id}"/>' + "&tab=" + '<c:out value="${info.externalActiveSubgroup.urlSubgroup}"/>' + "&" + command + "&aid=" + id;
                    window.location.href = url;
                }
            </script>
            <p class="group-name">${group.name}</p>

            <div class="parameters">
                <c:forEach items="${group.buttons}" var="button">
                    <div style="display: inline-block;">
                        <a href="javascript:void(0)"
                           onclick="javascript:goToURL(<c:out value="${button.command}"/>, <c:out
                                   value="${button.attrId}"/>)" class="button-href">${button.name}</a>
                    </div>
                </c:forEach>
                <c:if test="${not empty group.tObjects}">
                    <table>
                        <thead>
                        <tr>
                            <td class="thead-content"></td>
                            <td class="thead-content">Name</td>
                            <td class="thead-content">Object Type</td>
                            <td class="thead-content">Description</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${group.tObjects}" var="childObj">
                            <tr class="tr-content">
                                <td><input type="checkbox" name="${childObj.id}"></td>
                                <td><a href="?id=${childObj.id}" class="object-content">${childObj.name}</a></td>
                                <td style="padding-left:10px;">${childObj.objectType.name}</td>
                                <td style="padding-left:10px;">${childObj.objectType.description}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${not empty group.attributes}">
                    <table>
                        <c:forEach items="${group.attributes}" var="attribute">
                            <tr>
                                <td class="td-content">${attribute.attribute.name}:</td>
                                <c:choose>
                                    <c:when test="${attribute.attrType == 1}">
                                        <td class="td-content-value">${attribute.value}</td>
                                    </c:when>
                                    <c:when test="${attribute.attrType == 6}">
                                        <c:if test="${attribute.valid}">
                                            <td class="td-content-value">${attribute.listValue.value}</td>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="td-content-value">${attribute.value}</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </c:forEach>
        <c:if test="${not empty info.error}">
            <div class="error">
                    ${info.error}
            </div>
        </c:if>
    </div>
    <div class="footer">
        ©Алексей Третьяков, 2015
    </div>
</div>
</body>
</html>