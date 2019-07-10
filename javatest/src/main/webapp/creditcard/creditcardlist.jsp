<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

.newcardbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #2196F3;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

</style>
</head>
<body>

<h2>Credit Card list</h2>

<button type="button" class="newcardbtn" onclick="window.location='/javatest/creditcard/';">New Credit Card</button>

<table>
  <tr>
    <th>Cardholder</th>
    <th>Card number</th>
    <th>Expiry date</th>
  </tr>
  <c:if test="${not empty cardsList}">
    <c:forEach items="${cardsList}" var="card"> 
      <tr>
        <td>${card.cardHolder}</td>
        <td>${card.cardNumber}</td>
        <td>${card.expiryDate}</td>
      </tr>
    </c:forEach>  
  </c:if>
</table>

</body>
</html>
