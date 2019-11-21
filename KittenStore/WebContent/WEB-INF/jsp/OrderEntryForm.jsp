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
<c:out value="${validQuantity}"/>
<c:set var = "validQuantity" scope = "session" value = "${validQuantity}"/>
<jsp:include page="Header.jsp" />
<form:form modelAttribute="order" method="post" action="purchase/submitItems">

    <table style="width:100%; border: 1px solid black">
    	<tr>
    		<th>Item Name</th>
    		<th>Price</th>
    		<th>Quantity</th>
    		<th>ID</th>
    	</tr>
	<c:forEach items="${order.lineItems}" var="item" varStatus="loop">
		<tr>
			<!--<td><c:out value="${item.itemName}" /></td>-->
			<td><form:input path="lineItems[${loop.index}].itemName" readonly="true"/></td>
			<td>$<c:out value="${item.price}"   /></td>
			<td><form:input path="lineItems[${loop.index}].quantity" /></td>
			<td><form:input path="lineItems[${loop.index}].id" readonly="true"/></td>		
		</tr>
	</c:forEach>

	  <tr>
		<td colspan="2"><input type="submit" value="Purchase"></td>
	  </tr>
	

    </table>
</form:form>
<jsp:include page="Footer.jsp" />
</body>
</html>