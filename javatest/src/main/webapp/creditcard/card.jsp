<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  font-family: Arial;
  font-size: 17px;
  padding: 8px;
}

* {
  box-sizing: border-box;
}

.row {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  -ms-flex-wrap: wrap; /* IE10 */
  flex-wrap: wrap;
  margin: 0 -16px;
}

.col-50 {
  -ms-flex: 50%; /* IE10 */
  flex: 50%;
}

.col-75 {
  -ms-flex: 75%; /* IE10 */
  flex: 75%;
}

.col-50,
.col-75 {
  padding: 0 16px;
}

.container {
  background-color: #f2f2f2;
  padding: 5px 25% 15px 25%;
  border: 1px solid lightgrey;
  border-radius: 3px;
  height: 350px;
}

input[type=number]::-webkit-inner-spin-button, 
input[type=number]::-webkit-outer-spin-button { 
  -webkit-appearance: none; 
  margin: 0; 
}

input[type=number],
input[type=text] {
  width: 100%;
  margin-bottom: 20px;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

label {
  margin-bottom: 10px;
  display: block;
}


button {
  color: white;
  padding: 12px;
  margin: 10px 0;
  border: none;
  width: 100%;
  border-radius: 3px;
  cursor: pointer;
  font-size: 17px;
}

.savebtn {
  background-color: #4CAF50;
}

.cancelbtn {
  background-color: #f44336;
}

button:hover {
  opacity: 0.8;
}


hr {
  border: 1px solid lightgrey;
}


/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
@media (max-width: 800px) {
  .row {
    flex-direction: column-reverse;
  }
}
</style>
</head>
<body>

<h2>Credit Card</h2>

<div class="row">
  <div class="col-75">
    <div class="container">
      <form action="/javatest/ccard" method="post">
		<div id="msg"></div>      
        <div class="row">
          <div class="col-50">
          	<input type="hidden" id="id" name="id" value="${card.id }">
            <label for="cardholder">Cardholder</label>
            <input type="text" id="cardholder" name="cardholder" placeholder="John More Doe" value="${card.cardHolder }" <c:if test="${not empty card.id}">readonly</c:if> maxlength="60" required>
            <label for="cardnumber">Credit card number</label>
            <input type="number" id="cardnumber" name="cardnumber" placeholder="1111 2222 3333 4444" value="${card.cardNumber }" <c:if test="${not empty card.id}">readonly</c:if> min="0" onKeyPress="if(this.value.length==16) return false;" required>
            <label for="expirydate">Expiry Date</label>
            <input type="text" id="expirydate" name="expirydate" placeholder="YY/MM" minlength="5" value="${card.expiryDate }" maxlength="5" required>
          </div>
          
        </div>
        <button type="submit" class="savebtn" style="float:left; display: inline; width: 49%;">Save</button>
        <button type="button" class="cancelbtn" style="float:right; display: inline; width: 49%;" onclick="window.location='/javatest/creditcardlist';">Cancel</button>
      </form>
    </div>
  </div>
</div>

</body>
<script>
document.onreadystatechange = function() {
    if (document.readyState === "complete") {
		var url = new URL(window.location.href);
		var result = url.searchParams.get("r");
		if (result && result === "fail") {
			document.getElementById("msg").append("Error: Try again!!! Please note the following requirements:");
			document.getElementById("msg").appendChild(document.createElement("br")); 
			document.getElementById("msg").append("- Card number must have 16 digits and number only.");
			document.getElementById("msg").appendChild(document.createElement("br"));
			document.getElementById("msg").append("- Expiry date must have be in the YY/MM format.");
			document.getElementById("msg").appendChild(document.createElement("br"));
		}
    } 
}
</script>
</html>
