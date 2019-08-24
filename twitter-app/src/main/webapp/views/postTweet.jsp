<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post Tweet</title>
 <style type="text/css">
        .error {
            color: red;
        }
    </style>
</head>
<body>
<div class="container">
    <form:form action="/twitter-app/postTweet/submit" method="post" modelAttribute="tweet">
        <label>Tweet Message</label>
        <form:input path="tweetMsg" placeholder="Post your tweet"/>
        <form:errors path="tweetMsg" cssClass="error"/>
        <button type="submit">Submit</button>
    </form:form>
    </div>
</body>
</html>