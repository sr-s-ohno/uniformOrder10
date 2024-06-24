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

//会員権限の強制
member = request.getParameter("member");
if (member == null) {
	objUser = (User) session.getAttribute("objUser");
	objUser.setMember("1");
	session.setAttribute("objUser", objUser);
}
%>
<html>
<head>
   <title>注文登録</title>
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

	<div style="text-align: center">

		<!-- メニューデザイン -->
		<!-- ヘッダー -->
		<%@include file="/common/header.jsp"%>
		<p class="space2"></p>

		<h2>注文登録</h2>
		<hr style="height: 3; background-color: blue" />
		<p class="space2"></p>

		<!-- 入力フォーム -->
		<form action="<%=request.getContextPath()%>/orderInsert"
			style="text-align: center" method="post">
			<table style="margin: 0 auto;">
				<tr>
					<td style="background-color: #a9a9a9; width:200">氏名</td>
					<td style="width:150">
						<%
						if (objUser == null) {
						%>
						<input type="text" name="user">
						<%
						} else {
						%>
						<%= objUser.getUser() %>
						<%
						}
						%>
					</td>
				</tr>

				<tr>
					<td style="background-color: #a9a9a9; width:200">メールアドレス</td>
					<td style="width:150">
						<%
						if (objUser == null) {
						%>
						<input type="text" name="mail">
						<%
						} else {
						%>
						<%= objUser.getMail() %>
						<%
						}
						%>
					</td>
				</tr>

				<tr>
					<td style="background-color: #a9a9a9; width:200">住所</td>
					<td style="width:150">
						<%
						if (objUser == null) {
						%>
						<input type="text" name="address">
						<%
						} else {
						%>
						<%= objUser.getAddress() %>
						<%
						}
						%>
					</td>
				</tr>

				<tr>
					<td style="background-color: #a9a9a9; width:200">商品</td>
					<td style="width:150"><select name="type">
							<%
							for (int i = 0; i < uniList.size(); i++) {
							%>
							<option value="<%= "000" + (i + 1) %>">
								<%= uniList.get(i).getType() %>
							</option>
							<%
							}
							%>
					</select></td>
				</tr>

				<tr>
					<td style="background-color: #a9a9a9; width:200">個数</td>
					<td style="width:150"><input type="text" name="quantity"></td>
				</tr>

				<tr>
					<td style="background-color: #a9a9a9; width:200">備考欄</td>
					<td style="width:150"><textarea rows="8" cols="20" name="address"></textarea></td>
				</tr>

			</table>
			<input type="submit" value="注文">
		</form>
		
	</div>

	<div class="push"></div>
	<!-- フッター -->
	<%@include file="/common/footer.jsp"%>

	</div>

</body>
</html>