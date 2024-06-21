<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList, bean.Order, util.MyFormat, bean.Admin" %>

<%
//金額フォーマット
MyFormat mf = new MyFormat();

//セッションからユーザー情報を取得
Admin objAdmin = (Admin)session.getAttribute("objAdmin");
//セッション切れか確認
if(objAdmin == null){
	//セッション切れならerror.jspへフォワード
	request.setAttribute("error","セッション切れの為、詳細画面が表示できませんでした。");
	request.setAttribute("cmd","adminlogin");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}

Order order = (Order)request.getAttribute("order");
%>

<html>
<head>
<title>受注管理一覧（orderList）</title>
<link rel="stylesheet" type="text/css"
	href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

	<div style="text-align: center">
		<!-- メニューデザイン -->
		<!-- フッター -->
		<%@include file="/common/header.jsp"%>
		<p class="space2"></p>

						<table style="width:100%">
						<tr>
							<td align:left , style="width:10%;" > [<a  href="<%=request.getContextPath() %>/view/ordermenu.jsp">メニューに戻る</a>]
							[<a href="<%=request.getContextPath() %>/orderList">受注管理一覧に戻る</a>]
							<td style="width:30%"></td>
							<h2>入金・発送状況変更</h2>
							</tr>
						</table>
						
						<hr
					style="text-align: center; height: 5px; background-color: #008000;">
					
					<div style="margin-bottom: 250px">
					<table style="margin: auto; width: 850px">
						<tr>
							<td style="background-color: #00b16b; width: 200">No</td>
							<td style="background-color: #00b16b; width: 200">氏名</td>
							<td style="background-color: #00b16b; width: 200">種類</td>
							<td style="background-color: #00b16b; width: 200">個数</td>
							<td style="background-color: #00b16b; width: 200">合計金額</td>
							<td style="background-color: #00b16b; width: 200">発注日</td>
							<td colspa n="3">&nbsp;</td>
						</tr>
					</table>
					<%
					if(order != null){
					%>
					
					<table style="margin: auto; width: 850px">
						<tr>
							<td style="background-color: #00b16b; width: 200"><%= order.getOrderno() %></td>
							<td style="background-color: #00b16b; width: 200"><%= order.getUser() %></td>
							<td style="background-color: #00b16b; width: 200"><%= order.getType() %></td>
							<td style="background-color: #00b16b; width: 200"><%= order.getQuantity() %></td>
							<td style="background-color: #00b16b; width: 200"><%= mf.moneyFormat( order.getPrice() * order.getQuantity() )  %></td>
							<td style="background-color: #00b16b; width: 200"><%= order.getDate() %></td>
							<td colspa n="3">&nbsp;</td>
						</tr>
					</table>
					
					<form action="<%=request.getContextPath()%>/orderUpdate">
					<table>
						<tr>
							<td></td>
							<td></td>
							<td style="background-color: #00b16b; width:100">変更前</td>
							<td style="background-color: #00b16b; width:100">変更後</td>
						</tr>
						<tr>
							<td></td>
							<td style="background-color: #00b16b; width: 100">入金状況</td>
							<td style="background-color: #00b16b; width: 100"><%= order.getPayment() %></td>
							<td>
								<select name="payment" style="text-align: center; width:100;">
									<option value="入金待ち">入金待ち</option>
									<option value="入金済み">入金済み</option>
									<input type="hidden" name="orderno" value=<%= order.getOrderno() %>
								</select>
								</td>
							<tr>
							<td></td>
							<td style="background-color: #00b16b; width: 100">発送状況</td>
							<td style="background-color: #00b16b; width: 100"><%= order.getSend() %></td>
							<td>
								<select name="send" style="text-align:center; width:100;">
									<option value="未">未</option>
									<option value="済">済</option>
								</select>
								</td>
							</tr>
							<%
							}
							%>
					</table>
					<table>
						<input type="submit" value="登録">
					</table>
					</form>
				</div>
			</div>
				<!-- フッター -->
	<p class="push"></p>
	<%@include file="/common/footer.jsp"%>
		</body>
</html>
				