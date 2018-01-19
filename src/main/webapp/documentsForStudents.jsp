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
<div class="container">

<div class="panel panel-default">
    <h2>Due date ${dueDate} </h2>
</div>


<div class="panel panel-default">
              <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Documents </span></div>
            <div class="tablecontainer">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>File Name</th>
                            <th>Type</th>
                            <th>Description</th>
                            <th>Author</th>
                            <th width="100"></th>
                            <th width="100"></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${documents}" var="doc" varStatus="counter">
                        <tr>
                            <td>${counter.index + 1}</td>
                            <td>${doc.name}</td>
                            <td>${doc.type}</td>
                            <td>${doc.description}</td>
                            <td>${doc.author}</td>
                            <td><a href="<c:url value='/download-document-${doc.id}-${courseId}' />" class="btn btn-success custom-width">download</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

 <div class="panel panel-default">

            <div class="panel-heading"><span class="lead">Upload New Document</span></div>
            <div class="uploadcontainer ${status.error ? 'has-error' : ''}">

                <form method="POST" action="documents-${courseId}-${facultyId}" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                		File1 to upload: <input type="file" name="file">

                		Description: <input type="text" name="description">

                		<input type="submit" value="Upload"> Press here to upload the file!
                	</form>
                </div>
            </div>
 </div>
<td><a href="<c:url value='/courses-${facultyId}' />" class="btn btn-danger custom-width">Back</a></td>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>