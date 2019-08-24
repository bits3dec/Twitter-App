<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Success</title>
    <style type="text/css">
        .error {
            color: red;
        }
    </style>
</head>
<body>
<div class="container">
    <form:form action="/twitter-app/submit" method="post" modelAttribute="user">
        <label>First Name</label>
        <form:input path="firstName" placeholder="Enter your first name"/>
        <form:errors path="firstName" cssClass="error"/>
        <label>Age</label>
        <form:input path="age" placeholder = "Enter your age"/>
        <form:errors path="age" cssClass="error"/>
        <button type="submit">Submit</button>
    </form:form>
</div>

</body>
</html>
