<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.amazonaws.*" %>
<%@ page import="com.amazonaws.auth.*" %>


<%
 
    if (request.getMethod().equals("HEAD")) return;
%>



<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Hello AWS Web World!</title>
    <link rel="stylesheet" href="styles.css" type="text/css" media="screen">
    
</head>
<body>
   <p style="text-align:center"><img src="images/google.png" width="700" height="240"></p>
 <center>
 <form id="input" action="SearchServlet" method="get">
 <input type="text" name="search" size="50"> <input type="submit" value="SEARCH">
 </form>
 </center>
 
 <br /><br />
 
 
 
 
</body>
</html>