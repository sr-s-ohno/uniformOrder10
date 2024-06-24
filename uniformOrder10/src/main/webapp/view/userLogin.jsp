<%@page contentType="text/html; charset=UTF-8"%>

<%
String error = (String)request.getAttribute("error");
if(error == null){
	error = "";
}

String user = ""; //ユーザーID
String password = ""; //パスワード

Cookie[] userCookie = request.getCookies(); //クッキー取得

//クッキーがあるか判定
if (userCookie != null) {
	for (int i = 0; i < userCookie.length; i++) {
		//クッキーからユーザ情報の取得
		if (userCookie[i].getName().equals("user")) {
			user = userCookie[i].getValue();
		}
		//クッキーからパスワード情報の取得
		if (userCookie[i].getName().equals("password")) {
			password = userCookie[i].getValue();
		}
	}
}
%>
<html>
	<head>
		<title>ログイン画面</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
	
	    <%@include file="/common/header.jsp" %>
		<p class="space2"></p>
		
		<h2 style="text-align:center">ログイン画面</h2>
		
		<div style="margin-bottom:250px">
			<table style="margin:auto">
				<form action="<%=request.getContextPath() %>/userLogin" method="post">
					<tr>
						<td colspan="2" style="text-align:center">
						<h3 style="color: 0000ff;"><%=error %></h3>
						</td>
					</tr>
					<tr>
						<td>名前(ID)</td>
						<td><input type="text" name="user"></td>
					</tr>
					<tr>
						<td>パスワード</td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center">
						<input type="submit" value="ログイン"></td>
					</tr>
				</form>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
					<a href="<%=request.getContextPath() %>/view/userInsert.jsp">新規会員登録</a></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
					登録しない場合は<a href="<%=request.getContextPath() %>/view/orderInsert.jsp">こちら</a>から</td>
				</tr>
			</table>
		</div>
		
		<p class="push"></p>
		<%@include file="/common/footer.jsp" %>
		
	</body>
</html>