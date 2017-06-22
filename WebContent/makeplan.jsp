<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>어디갈까?</title>
<link rel="stylesheet" type="text/css" href="./main.css">
</head>
<body style="width: 100%; overflow: hidden">
	<table width="100%" border="1" cellpadding="2" cellspacing="0">
		<tr>
			<td colspan="3"><jsp:include page="header.jsp" flush="false" />

			</td>
		</tr>
		<tr>
			
			<td width="25%" height = "400px" valign="top">
			<%String a = (String)session.getAttribute("email"); 
			out.print(a);%>
			<div style="width: 100%; height: 400px; overflow: auto"></div>
			</td>
			
				<td width="25%" height = "400px" valign="top">어디갈까?
				<div style="width: 100%; height: 400px; overflow: auto"></div>
				</td>
			
			<td width="50%" valign="top">어디갈까?</td>
		</tr>
		<tr>
			<td colspan="3"><jsp:include page="footer.jsp" flush="false" />
			</td>
		</tr>
</body>
</html>