<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shipping</title>
</head>
<body>
<jsp:include page="Header.jsp" />
<form:form modelAttribute="shipping" method="post" action="submitShipping">
    <table style="width:100%; border: 1px solid black">
    	<tr><th>Shipping Address</th></tr>
    	<tr><td>Name: </td><td><form:input path="name" /></td></tr>
    	<tr><td>AddressLine 1: </td><td><form:input path="addressLine1" /></td></tr>
    	<tr><td>AddressLine 2: </td><td><form:input path="addressLine2" /></td></tr>
    	<tr><td>City: </td><td><form:input path="city" /></td></tr>
    	<tr><td>State: </td><td><form:input path="state" /></td></tr>
 	    <tr><td>Country: </td><td><form:input path="country" /></td></tr>
    	<tr><td>Zip: </td><td><form:input path="zip" /></td></tr>
    	<tr>
		<td colspan="2"><input type="submit" value="Payment"></td>
	  	</tr>
    </table>
</form:form>
</body>
<jsp:include page="Footer.jsp" />
</html>