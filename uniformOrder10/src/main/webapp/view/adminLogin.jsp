<!-- /*
 * プログラム名 : ユニフォーム受注管理システム
 * プログラムの説明 : ログイン機能の実装
 * 作成者 : 山田彩乃
 * 作成日 : 2024年 6月20日
 */ -->

<%@page contentType="text/html; charset=UTF-8"%>

<%
String cmd = (String) request.getAttribute("cmd");
String error = (String) request.getAttribute("error");
if(error == null){
	error = "";
}

String admin = ""; //ユーザーID
String password = ""; //パスワード

Cookie[] adminCookie = request.getCookies(); //クッキー取得

//クッキーがあるか判定
if (adminCookie != null) {
	for (int i = 0; i < adminCookie.length; i++) {
		//クッキーからユーザ情報の取得
		if (adminCookie[i].getName().equals("admin")) {
			admin = adminCookie[i].getValue();
		}
		//クッキーからパスワード情報の取得
		if (adminCookie[i].getName().equals("password")) {
			password = adminCookie[i].getValue();
		}
	}
}
%>

<html>
<head>
   <title>ログイン</title>
   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
 <div style="text-align:center">
 
    <!-- ヘッダー -->
	<%@include file="/common/adminHeader.jsp"%>
	<p class="space"></p>

	<h2>管理者ログイン</h2>
	<hr style="height: 5; background-color: #7fef6f" />
	<p class="space2"></p>

	<div id="main">
		<form action="<%=request.getContextPath()%>/adminLogin" method="post">
			<table>
				<tr>
					<th class="detail-table">ID</th>
					<td><input type="text" name="admin" value=<%= admin %>></td>
				</tr>
				<tr>
					<th class="detail-table">パスワード</th>
					<td><input type="password" name="password" value=<%= password %>></td>
				</tr>
			</table>
			<table>
				<tr>
					<td class="input-table"><input type="submit" value="ログイン"></td>
				</tr>
			</table>
		</form>
		
		<h3 style="color: #a52a2a;"><%= error %></h3>
		
	</div>
	</div>
	
	<!-- フッター -->
	<p class="push"></p>
	<%@include file="/common/adminFooter.jsp"%>
	
</body>
</html>