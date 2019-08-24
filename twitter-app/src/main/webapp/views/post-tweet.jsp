<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>post tweet</title>
 <style type="text/css">
        .error {
            color: red;
        }
    </style>
</head>
<body>
<div class="container">

    <form:form action="/twitter-app/post-tweet/submit" method="post" modelAttribute="tweet">
       <label>User Id</label>
        <form:input path="user" placeholder="Enter your user Id"/>
        <form:errors path="user" cssClass="error"/></small>
        <label>Tweet Message</label>
        <form:input path="message" placeholder="Enter tweet message"/>
        <form:errors path="message" cssClass="error"/></small>
        <button type="submit">Submit</button>
    </form:form>
    </div>
</body>
</html>