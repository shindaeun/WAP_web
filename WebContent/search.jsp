<%@page import="org.apache.jasper.tagplugins.jstl.core.Url"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="search.TourInfo"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<%
	String keyword = request.getAttribute("kw").toString();
	List<TourInfo> tourList = new ArrayList<TourInfo>();
	tourList = (List<TourInfo>) request.getAttribute("tourList");
	String url = request.getAttribute("url").toString();
	int pageNo = Integer.parseInt(request.getAttribute("pageNo").toString());
	int totalCount = Integer.parseInt(request.getAttribute("totalCount").toString());
%>
<body>

	<table width="100%" border="1" cellpadding="2" cellspacing="0">
		<tr class="headerImage">
			<td colspan="2"><jsp:include page="header.jsp" flush="false" />
				<div class="search">
					<form method="GET" action="Search">
						<input type="text" id="kw" name="kw" value="<%=keyword%>" en="ko">
						<input type="submit" value="검색">
					</form>
				</div>
			</td>
		</tr>
		<tr>
			<td valign="top">
			<div style="width: 100%; height: 400px; overflow: auto">
				
			
			<% if(totalCount == 0){ %>				
				<div style= "text-align: center; padding:30px"> 검색결과가 없습니다!!! </div>
			<% } else{%>
				<div style= "text-align: center; padding:30px 0px 0px">"<%=keyword%>"에 대한 검색결과입니다.</div>
				<div style= "text-align: center; padding:0px 0px 30px 0px">페이지 번호 : <<%=pageNo%>></div>
			<%} %>
			<%
			for(int i=0;i<tourList.size();i++){ 
				TourInfo tourInfo = tourList.get(i);
			%>	
				<div style="width:400px; height: 300px; display:inline-block; padding: 10px">
					<div style= "text-align: center"><img src="<%=tourInfo.getImageUrl()%>" width=200 height=200></div>
					<div style= "text-align: center"><%=tourInfo.getTitle() %></div>
					<div style= "text-align: center"><%=tourInfo.getAddr1() %></div>
					<div>
					<form method="GET" action="Basket">
						<INPUT TYPE="hidden" NAME="kw" value="<%=keyword %>">
						<INPUT TYPE="hidden" NAME="pageNo" value="<%=pageNo %>">
						<center><button  name="contentId" value="<%=tourInfo.getContentid() %>">추가하기</button></center>
					</form>	
					</div>
				</div>
			<% } %>
			
				<ul style= "text-align: center">
					<% if(pageNo > 1) { %>
						<a href="Search?kw=<%=keyword %>&pageNo=<%=pageNo - 1 %>">[이전]</a>
					<% } %>
					<% if(pageNo < (totalCount/10 + 1)) { %>
						<a href="Search?kw=<%=keyword %>&pageNo=<%=pageNo + 1 %>">[다음]</a>
					<% } %>
				</ul>
				
			</div>
			
			</td>
			<td width="20%" valign="top"><jsp:include page="recom.jsp"/></td>
		</tr>
		<tr>
			<td colspan="2"><jsp:include page="footer.jsp" flush="false" />
			</td>
		</tr>
</body>
</html>