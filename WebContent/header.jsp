<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.util.Enumeration"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script>
	function logout() {
		// 로그아웃 아이프레임
		$("body").append(
				"<iframe id='logoutIframe' style='display: none;'></iframe>");
		$("#logoutIframe").attr("src", "http://nid.naver.com/nidlogin.logout");
		// 로그아웃 처리
		$("#naver_id_login").hide();
		$("#naver_id_logout").show();
	}
	function login() {
		// 로그인 처리		
		$("#naver_id_login").show();
		$("#naver_id_logout").hide();
	}
</script>
</head>
<body>

	<ul class="nav">
		<li><a href="./Main"> main page </a></li>
		<li><a href="my.jsp"> my page </a></li>
		<li><a href="./Search"> search page</a></li>
		<li><a href="./MakePlan">make plan page</a>
	</ul>

	<!-- 네이버아이디로로그인 버튼 노출 영역 -->
	<div id="naver_id_login" style="display: none;"></div>
	<!-- //네이버아이디로로그인 버튼 노출 영역 -->
	<!-- 로그인 한 경우 -->
	<div id="naver_id_logout" style="display: none;">
		<a href="./Logout">로그아웃</a>
	</div>
	<!-- 로그인 한 경우 -->
	<%

	if (session.getAttribute("email") == null) {
		//if(request.isRequestedSessionIdValid()){
	%><script>login();</script>
	<%} else {%>
	<script>logout();</script>
	<%}%>


	<!-- 네이버아디디로로그인 초기화 Script -->
	<script type="text/javascript">
		var naver_id_login = new naver_id_login("eJ0os_7GUeAlYCYJNCoG",	"http://localhost/WAP/Main");
		var state = naver_id_login.getUniqState();
		naver_id_login.setButton("white", 2, 40);
		naver_id_login.setDomain("localhost/WAP/");
		naver_id_login.setState(state);
		naver_id_login.init_naver_id_login();
	</script>
	<!-- 네이버아디디로로그인 Callback페이지 처리 Script -->
	<script type="text/javascript">
	var i = 0;
		// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
		function naverSignInCallback() {
			// 로그인 처리
			if (i++ == 0) {
				location.href = "http://localhost/WAP/Login?state=".concat(
						naver_id_login.getAccessToken(), "&email=",
						naver_id_login.getProfileData('email'), "&name=",
						naver_id_login.getProfileData('name'), "&id=",
						naver_id_login.getProfileData('id'));
			}
			
		}
		// 네이버 사용자 프로필 조회
		naver_id_login.get_naver_userprofile("naverSignInCallback()");
	</script>
	<!-- //네이버아디디로로그인 Callback페이지 처리 Script -->

</body>
</html>