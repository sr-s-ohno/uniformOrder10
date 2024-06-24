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
   <title>ユニフォーム受注管理システム</title>
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<div style="text-align: center">

		<!-- メニューデザイン -->
		<!-- ヘッダー -->
		<%@include file="/common/userHeader.jsp"%>
		<p class="space"></p>
		
		<div id="menu">
			<div class="container">
				<div id="nav">
					<ul>
						<!-- メニューリンク -->
						<A href="<%=request.getContextPath()%>/view/orderInsert.jsp">
						【注文登録に戻る】</A>
					</ul>
				</div>
			</div>
		</div>

		<h2 style="text-align: center">注文確認</h2>
		<hr style="height: 3; background-color: #0095d9" />
		<p class="space2"></p>

		<table style="margin: auto;">

			<tr>
				<td style="width:150px; background-color: #0095d9">商品名</td>
				<td style="width:150px; background-color: #e0ffff"><%= uniform.getType() %></td>
			</tr>

			<tr>
				<td style="width:150px; background-color: #0095d9">個数</td>
				<td style="width:150px; background-color: #e0ffff"><%= quantity %></td>
			</tr>

			<tr>
				<td style="width:150px; background-color: #0095d9">合計価格</td>
				<td style="width:150px; background-color: #e0ffff"><%= mf.moneyFormat(uniform.getPrice() * quantity) %>
			</tr>

		</table>

		<form action="<%=request.getContextPath()%>/orderCheck"">
			<input type="submit" value="注文">
		</form>
	</div>

	<div class="push"></div>
	<!-- フッター -->
	<%@include file="/common/userFooter.jsp"%>

</body>
</html>