<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList, bean.Admin, util.MyFormat, bean.Order"%>

<%
//金額フォーマット
MyFormat mf = new MyFormat();

//セッションからユーザー情報を取得
Admin objAdmin = (Admin)session.getAttribute("objAdmin");
//セッション切れか確認
if(objAdmin == null){
	//セッション切れならerror.jspへフォワード
	request.setAttribute("error","セッション切れの為、受注管理一覧画面が表示できませんでした。");
	request.setAttribute("cmd","adminlogin");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}

ArrayList<Order> list = (ArrayList<Order>)request.getAttribute("order_list");
%>

<html>
<head>
<title>受注管理一覧（orderList）</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

	<div style="text-align: center">
		<!-- メニューデザイン -->
		<!-- フッター -->
		<%@include file="/common/header.jsp"%>
		<p class="space2"></p>

		<!-- メニュー部分 -->
		<div id="menu">
			<div class="container">
				<!-- ナビゲーション -->
				<div id="nav">
					<ul>
						<!-- メニューリンク -->
						<A href="<%= request.getContextPath() %>/view/adminMenu.jsp">
							【メニューに戻る】</A>
					</ul>
				</div>
			</div>
		</div>


		<h2>受注管理一覧</h2>
		<hr style="height: 3; background-color:#00b16b"/>
		<p class="space2"></p>

			<div style="margin-bottom: 250px">
				<table style="margin: auto;">
					<tr>
						<td style="background-color: #00b16b; width: 200">No</td>
						<td style="background-color: #00b16b; width: 200">氏名</td>
						<td style="background-color: #00b16b; width: 500">種類</td>
						<td style="background-color: #00b16b; width: 100">個数</td>
						<td style="background-color: #00b16b; width: 300">合計金額</td>
						<td style="background-color: #00b16b; width: 300">発注日</td>
						<td style="background-color: #00b16b; width: 300">入金状況</td>
						<td style="background-color: #00b16b; width: 300">発送状況</td>
						<td style="background-color: #00b16b; width: 300"></td>
						<td colspa n="3">&nbsp;</td>
					</tr>

				<%
				if (list != null) {
				   for (int i = 0; i < list.size(); i++) {
				%>

					<tr>
						<!-- 注文番号 -->
						<td style="text-align: center; width: 200"><%= list.get(i).getOrderno() %></td>
						<!-- 氏名 -->
						<td style="text-align: center; width: 300"><%= list.get(i).getUser() %></td>
						<!-- 種類 -->
						<td style="text-align: center; width: 500"><%= list.get(i).getType() %></td>
						<!-- 個数 -->
						<td style="text-align: center; width: 100"><%= list.get(i).getQuantity() %></td>
						<!-- 合計金額 -->
						<td style="text-align: center; width: 300">
						<%= mf.moneyFormat( list.get(i).getPrice() * list.get(i).getQuantity() ) %></td>
						<!-- 発注日 -->
						<td style="text-align: center; width: 300"><%= list.get(i).getDate() %></td>
						<!-- 入金状況 -->
						<td style="text-align: center; width: 300"><%= list.get(i).getPayment() %></td>
						<!-- 発送状況 -->
						<td style="text-align: center; width: 300"><%= list.get(i).getSend() %></td>

						<td style="text-align: center">
						<a href="<%= request.getContextPath() %>/orderDetail?orderno=<%=list.get(i).getOrderno()%>&cmd=detail">詳細
						<a href="<%= request.getContextPath() %>/orderDetail?orderno=<%=list.get(i).getOrderno()%>&cmd=update">更新
						</td>
						<td colspa n="3">&nbsp;</td>
					</tr>
						
					<%
					   }
				    }
				    %>
				        
					</table>
					<p class="space"></p>

				</div>

				<!-- フッター -->
				<div class="push"></div>
				<%@include file="/common/footer.jsp"%>
</body>
</html>