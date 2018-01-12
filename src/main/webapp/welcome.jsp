<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>Welcome ${pageContext.request.userPrincipal.name} </h2>

        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Log Out</button>
            <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
        </form>
    </c:if>

    <div class="panel panel-default">
                  <!-- Default panel contents -->
                <div class="panel-heading"><span class="lead">Faculties</span></div>
                <div class="tablecontainer">
                    <table class="table table-hover">
                        <tbody>
                        <c:forEach items="${faculty}" var="fac" varStatus="counter">
                            <tr>
                                <td>${fac.id}</td>
                                <td>${fac.name}</td>
                                <td><a href="<c:url value='/courses-${fac.id}' />" class="btn btn-danger custom-width">Go to</a></td>
                                <td><a href="<c:url value='/add-course-${fac.id}' />" class="btn btn-danger custom-width">Create course</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
</div>


<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
