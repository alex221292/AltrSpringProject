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
        <form id="myform" action="" method="post">
            <input type="hidden" name="objectid" value="${info.tObject.id}">
            <input type="hidden" name="command" value="update">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <c:if test="${info.externalActiveSubgroup.subgroupType == 0}">
            <div class="parameters">
                <input type="submit" value="Update" class="button">
                <table>
                    <tr>
                        <td class="td-content">Name:</td>
                        <td class="td-content">${info.tObject.name}</td>
                    </tr>
                    <tr>
                        <td class="td-content">Description:</td>
                        <td class="td-content">${info.tObject.description}</td>
                    </tr>
                    <tr>
                        <td class="td-content">Object Type:</td>
                        <td class="td-content">${info.tObject.objectType.name}</td>
                    </tr>
                </table>
            </div>
            </c:if>
            <c:forEach items="${info.externalActiveSubgroup.groups}" var="group">
                ${group.name}
            <div class="parameters">
                <c:if test="${not empty group.tObjects}">
                    <table>
                        <thead class="thead-content">
                        <tr>
                            <td>Name</td>
                            <td>Object Type</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${group.tObjects}" var="childObj">
                            <tr class="tr-content">
                                <td><a href="?id=${childObj.id}">${childObj.name}</a></td>
                                <td>${childObj.objectType.name}</td>
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
                                        <td class="td-content-value">
                                            <c:if test="${not attribute.readOnly}"><input type="text"
                                                                                          class="td-content-value"
                                                                                          name=""
                                                                                          data-name="${attribute.attribute.attrId}"
                                                                                          id=""
                                                                                          value="${attribute.value}">
                                            </c:if>
                                            <c:if test="${attribute.readOnly}">${attribute.value}
                                            </c:if>
                                        </td>
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
                    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
                    <script type="text/javascript">

                        $('#myform input').on('change', function () {

                            if (!$(this).attr('name')) {
                                $(this).attr('name', $(this).data('name'));
                                $(this).attr('class', 'td-content-value-edit');
                            }

                        });

                    </script>
                </c:if>
            </div>
            </c:forEach>
            <c:if test="${not empty info.error}">
            <div class="error">
                    ${info.error}
            </div>
            </c:if>
    </div>
    </form>
    <div class="footer">
        ©Алексей Третьяков, 2015
    </div>
</div>
</body>
</html>