<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ page import="javax.servlet.http.HttpSession"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>어디갈까?</title>

<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3-min.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css" href="./main.css">
</head>

<body>

<table width="100%" border="1" cellpadding="2" cellspacing="0">
<tr class="headerImage">
    <td colspan="2">
       <jsp:include page="header.jsp" flush="false" />
       <h1>어디갈까?</h1>
	<h2>
		
		
		<span></span> 고객님만의 국내여행을 계획하세요!
   
    <p><span>이메일 : </span>${sessionScope.email}</p>
   

	</h2>
    </td>
</tr>
<tr>
    <td width="80%" valign="top">
       <jsp:include page="main_images.jsp" flush="false" />
    </td>
    <td width="20%" valign="top">
         <jsp:include page="recom.jsp" flush="false" />
    </td>
</tr>
<tr>
    <td colspan="2">
         <jsp:include page="footer.jsp" flush="false" />
    </td>
</tr>
</body>
</html>