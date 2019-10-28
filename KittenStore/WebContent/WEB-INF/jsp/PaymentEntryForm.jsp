<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
</head>
<body>
<jsp:include page="Header.jsp" />
<form:form modelAttribute="payment" method="post" action="submitPayment">
    <table style="width:100%; border: 1px solid black">
    	<tr><th>Credit Card Information</th></tr>
    	<tr><td>Name: </td><td><form:input path="cardName" /></td></tr>
    	<tr><td>CC Number: </td><td><form:input path="ccNumber" /></td></tr>
    	<tr><td>Expiration: </td><td><form:input path="expiration" /></td></tr>
    	<tr><td>CVV Code: </td><td><form:input path="cvvCode" /></td></tr>
	    <tr><td>Email Address </td><td><form:input path="emailAddress" /></td></tr>
    	<tr>
		<td colspan="2"><input type="submit" value="Payment"></td>
	  	</tr>
    </table>
</form:form>

</body>
<jsp:include page="Footer.jsp" />
</html>