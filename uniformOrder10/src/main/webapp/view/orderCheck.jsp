<!-- 最新盤 -->
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Uniform, bean.Order, util.MyFormat, bean.User"%>

<%
//金額フォーマット
MyFormat mf = new MyFormat();

//セッションからユーザー情報を取得
User objUser = (User) session.getAttribute("objUser");
//セッション切れか確認
if (objUser == null) {
	//セッション切れならerror.jspへフォワード
	request.setAttribute("error", "セッション切れの為、注文完了画面が表示できませんでした。");
	request.setAttribute("cmd", "userlogin");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}

Uniform uniform = (Uniform) session.getAttribute("uniform");
Order objOrder = (Order) session.getAttribute("order");
int quantity = objOrder.getQuantity();
%>

<html>
<head>
<title>注文確認</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<div style="text-align: center">

		<!-- メニューデザイン -->
		<!-- ヘッダー -->
		<%@include file="/common/header.jsp"%>
		<p class="space2"></p>

		<h2 style="text-align: center">注文確認</h2>
		<hr style="height: 3; background-color: blue" />
		<p class="space2"></p>

		<table style="margin: auto;">

			<tr>
				<td style="width:150px; background-color: lightskyblue">商品名</td>
				<td style="width:150px; background-color: powderblue"><%=uniform.getType()%></td>
			</tr>

			<tr>
				<td style="width:150px; background-color: lightskyblue">個数</td>
				<td style="width:150px; background-color: powderblue"><%=quantity%></td>
			</tr>

			<tr>
				<td style="width:150px; background-color: lightskyblue">合計価格</td>
				<td style="width:150px; background-color: powderblue"><%=mf.moneyFormat(uniform.getPrice() * quantity)%>
			</tr>

		</table>

		<form action="<%=request.getContextPath()%>/orderCheck" style="text-align: center">
			<input type="submit" value="注文">
		</form>
	</div>

	<div class="push"></div>
	<!-- フッター -->
	<%@include file="/common/footer.jsp"%>

	</div>
</body>
</html>