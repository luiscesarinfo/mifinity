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

/* Full-width input fields */
input[type=text] {
  width: 25%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
  
}

input[type=text]:focus {
  background-color: #ddd;
  outline: none;
}

.newbtn, .searchbtn {
  background-color: #008CBA;
}

.editbtn {
  background-color: #4CAF50;
}

.deletebtn {
  background-color: #f44336;
}


button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  border: none;
  cursor: pointer;
  width: auto;
}

button:hover {
  opacity: 0.8;
}

</style>
</head>
<body>

<h2>Credit Card list</h2>

<button type="button" class="newbtn" onclick="window.location='/javatest/creditcard/';">New Credit Card</button>
<input type="text" id="searchbox" placeholder="Enter part or the whole number of the credit card"></input>
<button type="button" class="searchbtn" onclick="search();">Search</button>

<table>
  <tr>
    <th>#</th>
    <th>Cardholder</th>
    <th>Card number</th>
    <th>Expiry date</th>
    <th>Actions</th>
  </tr>
  <c:if test="${not empty cardsList}">
    <c:forEach items="${cardsList}" var="card" varStatus="loop"> 
      <tr>
        <td>${loop.count}</td>
        <td>${card.cardHolder}</td>
        <td>${card.cardNumber}</td>
        <td>${card.expiryDate}</td>
        <td>
			<button type="button" class="editbtn" onclick="">Edit</button>
			<button type="button" class="deletebtn" onclick="">Delete</button>
        </td>
      </tr>
    </c:forEach>  
  </c:if>
</table>

</body>
<script>
function search() {
	window.location='/javatest/creditcardlist?search='+ document.getElementById("searchbox").value
}
</script>
</html>
