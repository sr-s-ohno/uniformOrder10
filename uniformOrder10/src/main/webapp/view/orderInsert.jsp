<!-- 最新盤 -->

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Uniform"%>
<%@page import="dao.UniformDAO"%>
<%@page import="java.util.ArrayList, bean.Uniform"%>
<%@page import="bean.User"%>

<%
UniformDAO objUniDao = new UniformDAO();
ArrayList<Uniform> uniList = new ArrayList<Uniform>();
uniList = objUniDao.selectAll();

String member = null;
User objUser = null;

//入力ミス
String error = (String) request.getAttribute("error");
if (error == null) {
	error = "";
}

//ユーザー情報取得
objUser = (User) session.getAttribute("objUser");

//会員権限の強制
if (objUser == null) {
	objUser = new User();
	objUser.setMember("2");
}

session.setAttribute("objUser", objUser);
%>
<html>
<head>
<title>ユニフォーム受注管理システム</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

	<div style="text-align: center">

		<!-- メニューデザイン -->
		<!-- ヘッダー -->
		<%@include file="/common/userHeader.jsp"%>
		<p class="space"></p>

		<!-- メニュー部分 -->
		<div id="menu">
			<div class="container">
				<!-- ナビゲーション -->
				<div id="nav">
					<ul>
						<%
						if (objUser.getMember().equals("1")) {
						%>
						<!-- メニューリンク -->
						<A href="<%=request.getContextPath()%>/view/userMenu.jsp">
							【メニュー画面へ】</A>
						<%
						} else {
						%>
						<!-- ログインリンク -->
						<A href="<%=request.getContextPath()%>/userLogout">
							【ログイン画面へ】</A>
						<%
						}
						%>
					</ul>
				</div>
			</div>
		</div>

		<h2>注文登録</h2>
		<hr style="height: 5; background-color: #a0d0ff" />
		<p class="space2"></p>

		<!-- 入力フォーム -->
		<form action="<%=request.getContextPath()%>/orderInsert"
			style="text-align: center" method="post">
			<table style="margin: 0 auto;">
				<tr>
					<td style="background-color: #0095d9; width: 200">氏名</td>
					<td style="background-color: #e0ffff; width: 150">
						<%
						if (objUser.getUser() == null) {
						%> <input type="text" name="user"> <%
 } else {
 %> <%=objUser.getUser()%> <input type="hidden" name="user"
						value=<%=objUser.getUser()%>> <%
 }
 %>
					</td>
				</tr>

				<tr>
					<td style="background-color: #0095d9; width: 200">メールアドレス</td>
					<td style="background-color: #e0ffff; width: 150">
						<%
						if (objUser.getMail() == null) {
						%> <input type="text" name="mail"> <%
 } else {
 %> <%=objUser.getMail()%> <input type="hidden" name="mail"
						value=<%=objUser.getMail()%>> <%
 }
 %>
					</td>
				</tr>

				<tr>
					<td style="background-color: #0095d9; width: 200">住所</td>
					<td style="background-color: #e0ffff; width: 150">
						<%
						if (objUser.getAddress() == null) {
						%> <input type="text" name="address"> <%
 } else {
 %> <%=objUser.getAddress()%> <input type="hidden" name="address"
						value=<%=objUser.getAddress()%>> <%
 }
 %>
					</td>
				</tr>

				<tr>
					<td style="background-color: #0095d9; width: 200">商品</td>
					<td style="background-color: #e0ffff; width: 150"><select
						name="type">
							<option value="">▼選択してください</option>
							<%
							for (int i = 0; i < uniList.size(); i++) {
							%>
							<option value="<%="000" + (i + 1)%>">
								<%=uniList.get(i).getType()%>
							</option>
							<%
							}
							%>
					</select></td>
				</tr>

				<tr>
					<td style="background-color: #0095d9; width: 200">個数</td>
					<td style="background-color: #e0ffff; width: 150"><input
						type="text" name="quantity"></td>
				</tr>

				<tr>
					<td style="background-color: #0095d9; width: 200">備考欄</td>
					<td style="background-color: #e0ffff; width: 150"><textarea
							rows="8" cols="20" name="text"></textarea></td>
				</tr>

			</table>
			<input type="submit" value="注文">
		</form>

		<h3 style="color: #a52a2a;"><%= error %></h3>

	</div>

	<!-- フッター -->
	<div class="push"></div>
	<%@include file="/common/userFooter.jsp"%>

</body>
</html>