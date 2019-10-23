<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Confirmation</title>
</head>
<body>
<jsp:include page="Header.jsp" />
<c:forEach var="lineItem" items="${order.getLineItems()}">
    <c:out value="Name: ${lineItem.getName()}\n"/> 
    <c:out value="Price: ${lineItem.getPrice()}\n"/> 
    <c:out value="Quantity: ${lineItem.getQuantity()}\n"/> 
</c:forEach>

<table style="width:100%; border: 1px solid black">
	<tr><th>Order Confirmation</th></tr>
	<tr><td>Confirmation Code: <b></b><c:out value="${confirmationCode}"/></b>.</td></tr>
	<tr><td> Thank You for your order <b></b><c:out value="${shipping.getName()}"/></b>.</td></tr>
	<c:set var = "payment" scope = "session" value = "${payment}"/>
	<c:set var = "shipping" scope = "session" value = "${shipping}"/>
	<c:set var = "confirmationCode" scope = "session" value = "${confirmationCode}"/>
	<tr><td>Please Confirm that the payment info for <c:out value = "${payment.getCardName()}"/> is correct:</td></tr>
	<tr><td>Credit Card Number: <c:out value = "${payment.getCcNumber()}"/></td></tr>
	<tr><td>Expiration Date: <c:out value = "${payment.getExpiration()}"/></td></tr>
	<tr><td>CVV: <c:out value = "${payment.getCvvCode()}"/></tr>


	<tr><td><div>Your Order will be shipped to: <c:out value="${shipping.getAddressLine1()}"/>, <c:out value="${shipping.getAddressLine2()}"/>
	<c:out value="${shipping.getCity()}"/>, <c:out value="${shipping.getState()}"/>, <c:out value="${shipping.getZip()}"/></div></td></tr>
</table>
<jsp:include page="Footer.jsp" />
</body>
</html>