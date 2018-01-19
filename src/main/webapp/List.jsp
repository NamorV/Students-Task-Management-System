<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

    <title>Log in with your account</title>

    <link href="${contextPath}/resources/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="panel panel-default">
              <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Users </span></div>
            <div class="tablecontainer">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>User Name</th>
                            <th>Role</th>
                            <th>Faculty</th>
                            <th width="100"></th>
                            <th width="100"></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user" varStatus="counter">
                        <tr>
                            <td>${counter.index + 1}</td>
                            <td>${user.username}</td>
                            <td>${user.role}</td>
                            <td>${user.faculty}</td>
                            <td><a href="<c:url value='/delete-user-${user.id}' />" class="btn btn-danger custom-width">Delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <td><a href="<c:url value='/welcome' />" class="btn btn-danger custom-width">Back</a></td>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>