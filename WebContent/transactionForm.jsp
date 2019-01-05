<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link href="https://fonts.googleapis.com/css?family=Catamaran" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/styles/searchbarStyle.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/styles/transactionFormStyle.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/styles/footerStyle.css" rel="stylesheet" type="text/css" />
<title>Checkout</title>
</head>
<body>
	<%@ include file="/searchbar.jsp"%>
	 <div class="wrapper content">
    <div class="big">
      <h2>YOUR CART</h2>
      <div class="cart">
        <div class="item">
          <div class="img-box">
            <img src="data:image/jpg;base64,${product.image}"
											alt="product-image" />
          </div>
          <div class="item-info">
            <div class="b1">${product.name}</div>
            <div class="b2">$${product.price}</div>
            <div class="b3">${product.description}</div>
            <div class="b4">By ${seller.username}</div>
            <div class="b5">At ${seller.city}</div>
          </div>
        </div>
      </div>
    </div>
    <div class="small">
      <div class="sum">SUMMARY</div>
      <div class="sum-item">
        <table>
          <tr>
            <td class="right">${product.name}</td>
            <td class="left">$${product.price}</td>
          </tr>
        </table>
      </div>
      <div class="total">
        <table>
          <tr>
            <td class="right">Tax</td>
            <td class="left">$0.00</td>
          </tr>
          <tr>
            <td class="right">TOTAL</td>
            <td class="left">$${product.price}</td>
          </tr>
        </table>
      </div>
      <div class="form">

        <form action="${pageContext.request.contextPath}/transaction" method="post">
          <input type="hidden" name="action" value="buy" />
          <input type="submit" value="Pay" />
        </form>
        <p>OR</p>
        <form action="${pageContext.request.contextPath}/transaction" method="post">
          <input type="hidden" name="action" value="cancel" />
          <input type="submit" value="Cancel" id="cancelbtn" />
        </form>
      </div>

    </div>
    </div>
		<%@ include file="/footer_div.jsp"%>
</body>
</html>