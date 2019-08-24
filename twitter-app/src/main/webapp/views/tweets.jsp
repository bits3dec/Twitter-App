<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tweets</title>
</head>
<body>
<h2>
<a href="/twitter-app/${tweet.user.userId}/postTweet">Post Tweet !!</a></h2>
    <h2>My tweets</h2>  
     <ul>
        <c:forEach var="tweet" items="${tweets}">
         <li>${tweet.tweetMsg}</a></li>
        </c:forEach>
    </ul>
</body>
</html>
